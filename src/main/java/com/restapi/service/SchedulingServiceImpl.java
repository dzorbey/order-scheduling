package com.restapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.das.GenericDas;
import com.restapi.domain.Compartment;
import com.restapi.domain.RigidAllocation;
import com.restapi.dto.Order;
import com.restapi.dto.OrderAllocationRequest;
import com.restapi.dto.OrderLine;
import com.restapi.dto.SchedulingResponse;
import com.restapi.utility.Utility;

@Service
public class SchedulingServiceImpl implements SchedulingService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final Integer rigidCapacity = 140;

	private static Map<String, List<Compartment>> orderCompartments;
	private static Map<String, Map<Integer, List<Compartment>>> sourceVolumeMapped;
	private static List<RigidAllocation> rigidAllocations;
	private static List<Compartment> onHold;

	private static Integer waitMaxSeconds;
	private static Double successRatio;
	private static List<Double> successRatios;

	@Autowired
	private GenericDas das;

	public void initilizeCollections() {
		orderCompartments = new HashMap<String, List<Compartment>>();
		sourceVolumeMapped = new HashMap<String, Map<Integer, List<Compartment>>>();
		rigidAllocations = new ArrayList<RigidAllocation>();
		onHold = new ArrayList<Compartment>();
		successRatios = new ArrayList<Double>();
		successRatio = 0.0;
		waitMaxSeconds = 30;

		rigidAllocations.add(new RigidAllocation(40, 0, false));
		rigidAllocations.add(new RigidAllocation(40, 0, false));
		rigidAllocations.add(new RigidAllocation(30, 0, false));
		rigidAllocations.add(new RigidAllocation(30, 0, false));
	}

	public static Map<String, List<Compartment>> getOrderCompartments() {
		return orderCompartments;
	}

	public static void setOrderCompartments(Map<String, List<Compartment>> orderCompartments) {
		SchedulingServiceImpl.orderCompartments = orderCompartments;
	}

	public static Map<String, Map<Integer, List<Compartment>>> getSourceVolumeMapped() {
		return sourceVolumeMapped;
	}

	public static void setSourceVolumeMapped(Map<String, Map<Integer, List<Compartment>>> sourceVolumeMapped) {
		SchedulingServiceImpl.sourceVolumeMapped = sourceVolumeMapped;
	}

	public static List<Compartment> getOnHold() {
		return onHold;
	}

	public static void setOnHold(List<Compartment> onHold) {
		SchedulingServiceImpl.onHold = onHold;
	}

	public static Integer getWaitMaxSeconds() {
		return waitMaxSeconds;
	}

	public static void setWaitMaxSeconds(Integer waitMaxSeconds) {
		SchedulingServiceImpl.waitMaxSeconds = waitMaxSeconds;
	}

	public static Integer getRigidcapacity() {
		return rigidCapacity;
	}

	public static List<RigidAllocation> getRigidAllocations() {
		return rigidAllocations;
	}

	public static Double getSuccessRatio() {
		return successRatio;
	}

	public static void setSuccessRatio(Double successRatio) {
		SchedulingServiceImpl.successRatio = successRatio;
	}

	@PostConstruct
	public void initilizeRigidAllocations() {
		initilizeCollections();
	}

	public SchedulingResponse processOrders(OrderAllocationRequest request) {
		initilizeCollections();
		logger.info("");

		for (Order order : request.getOrders()) {

			Long orderId = das.createOrder(calculateOrderLoad(order.getOrderLines()));
			for (OrderLine orderLine : order.getOrderLines()) {

				das.createOrderLine(orderId, orderLine);
				loadUtilization(orderCompartments, orderId.intValue(), orderLine.getWeight(), orderLine.getFuelType(),
						orderLine.getSource(), orderLine.getDestination());
			}
		}
		reArrangeOrderContents();
		return orderSchedule(null);
	}

	public List<Compartment> processCompartments(String key, List<Compartment> currentOrders, RigidAllocation allocator, Long sleep)
			throws Exception {

		if (sleep != null) {
			TimeUnit.MILLISECONDS.sleep(sleep);
		}
		if (allocator != null) {
			Compartment[] compartmentArray = currentOrders.stream().toArray(Compartment[]::new);

			allocator.setAllocated(true);
			allocator.setCompartments(compartmentArray);
			allocator.compartmentAllocation();

			for (Integer x : allocator.getIndexesToRemove()) {
				if (onHold.size() > 3) {
					allocator.setAllocated(false);
					return currentOrders;
				}

				Integer availableSpace = rigidCapacity - calculateCurrentLoad();
				if (availableSpace < compartmentArray[x].getWeight()) {
					allocator.setAllocated(false);
					return currentOrders;
				}
				onHold.add(compartmentArray[x]);
				compartmentArray = ArrayUtils.remove(compartmentArray, x);
			}
			List<Compartment> newList = Arrays.asList(compartmentArray);
			compartmentArray= null;
			
			if (newList != null) {
				orderCompartments.put(key, newList);
			}
			allocator.setAllocated(false);
			return orderCompartments.get(key);
		}
		return currentOrders;
	}

	public Integer calculateCurrentLoad() {
		BigDecimal sum = onHold.stream().map(Compartment::getDecimalWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
		return sum.intValue();
	}

	public BigDecimal calculateOrderLoad(List<OrderLine> orderLines) {
		BigDecimal sum = orderLines.stream().map(OrderLine::getDecimalWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
		return sum;
	}

	public SchedulingResponse orderSchedule(Long sleep) {
		List<Compartment> currentOrders = Collections.synchronizedList(new ArrayList<Compartment>());
		Date startDate = new Date();
		
		SchedulingResponse response = new SchedulingResponse();
		while (true) {

			int numSeconds = (int) ((Calendar.getInstance().getTimeInMillis() - startDate.getTime()) / 1000);
			if (numSeconds > waitMaxSeconds) {
				response.setSuccess(false);
				response.setUtilizationRatio(null);
				response.setMessage(
						"space complexity likely hitting jvm space upper bound, check your settings and criterias..");
				return response;
			}

			if (remainingItems() == 0) {
				break;
			}
			for (String key : sourceVolumeMapped.keySet()) {
				currentOrders = orderCompartments.get(key);
				try {
					currentOrders = processCompartments(key, currentOrders, findFreeAllocator(), sleep);
					currentOrders = processCompartments(key, currentOrders, findFreeAllocator(), sleep);
					currentOrders = processCompartments(key, currentOrders, findFreeAllocator(), sleep);
					processCompartments(key, currentOrders, findFreeAllocator(), sleep);

					if (onHold.size() > 0) {
						Integer currentAllocation = calculateCurrentLoad();

						setSuccessRatio((Double.valueOf(currentAllocation) / 140) * 100);
						logger.info("journey-utilizationRatio: " + getSuccessRatio());
						getSuccessRatios().add(getSuccessRatio());

						Long journeyId = das.createJourney(getSuccessRatio());
						for (Compartment compartment : onHold) {
							das.createAllocation(journeyId, compartment);
						}
						onHold = new ArrayList<Compartment>();
					}
				} catch (Exception e) {
					logger.error("Exception thrown during allocation: " + e.getMessage());
				}
			}
		}
		das.arrangeJourneyContainers();
		response.setSuccess(true);
		response.setUtilizationRatio(utilizationSuccessRatio());
		response.setMessage("orders utilized..");

		return response;
	}

	public static List<Double> getSuccessRatios() {
		return successRatios;
	}

	public static void setSuccessRatios(List<Double> successRatios) {
		SchedulingServiceImpl.successRatios = successRatios;
	}
		
	public RigidAllocation findFreeAllocator() {
		for (RigidAllocation allocation : rigidAllocations) {
			if (allocation.isAllocated() == false) {
				return allocation;
			}
		}
		return null;
	}
	
	public static void setRigidAllocations(List<RigidAllocation> rigidAllocations) {
		SchedulingServiceImpl.rigidAllocations = rigidAllocations;
	}

	public Double utilizationSuccessRatio() {

		Double totalRatios = getSuccessRatios().stream().mapToDouble(Double::doubleValue).sum();
		Double utilizationRatio = totalRatios / getSuccessRatios().size();
		logger.info("orders-utilizationRatio: " + Utility.formatter2Dec.format(utilizationRatio));

		return Double.valueOf(Utility.formatter2Dec.format(utilizationRatio));
	}

	public Integer remainingItems() {

		Integer remainingCount = 0;
		for (String key : sourceVolumeMapped.keySet()) {
			remainingCount = remainingCount + orderCompartments.get(key).size();
		}
		return remainingCount;
	}

	public Map<Integer, List<Compartment>> compartmentsPerLoad(List<Compartment> orderedCompartments) {

		Map<Integer, List<Compartment>> compartmentsPerLoad = new HashMap<Integer, List<Compartment>>();
		for (Compartment compartment : orderedCompartments) {

			if (compartmentsPerLoad.get(compartment.getWeight()) == null) {
				List<Compartment> compartmantList = new ArrayList<Compartment>();
				compartmantList.add(compartment);
				compartmentsPerLoad.put(compartment.getWeight(), compartmantList);
			} else {
				compartmentsPerLoad.get(compartment.getWeight()).add(compartment);
			}
		}
		return compartmentsPerLoad;
	}

	public Map<String, List<Compartment>> loadUtilization(Map<String, List<Compartment>> pointACompartments,
			int orderId, Double volume, String type, String source, String destination) {

		List<Compartment> compartments = new ArrayList<Compartment>();

		volume = volume * 10;
		Integer number = volume.intValue();

		int thirdTimes = 0;
		int fourtTimes = 0;
		int remainder = 0;
		while (true) {
			if (number % 40 == 0) {
				fourtTimes = number / 40;
				break;
			}
			if (number < 30) {
				remainder = number;

				Compartment compartment = new Compartment();
				compartment.setOrderId(orderId);
				compartment.setWeight(remainder);
				compartment.setPrice(remainder);
				compartment.setSource(source);
				compartment.setDestination(destination);
				compartment.setFuelType(type);
				compartments.add(compartment);
				break;
			}
			number = number - 30;
			thirdTimes++;
		}

		for (int i = 0; i < fourtTimes; i++) {
			Compartment compartment = new Compartment();
			compartment.setOrderId(orderId);
			compartment.setWeight(40);
			compartment.setPrice(40);
			compartment.setSource(source);
			compartment.setDestination(destination);
			compartment.setFuelType(type);
			compartments.add(compartment);
		}

		for (int i = 0; i < thirdTimes; i++) {
			Compartment compartment = new Compartment();
			compartment.setOrderId(orderId);
			compartment.setWeight(30);
			compartment.setPrice(30);
			compartment.setSource(source);
			compartment.setDestination(destination);
			compartment.setFuelType(type);
			compartments.add(compartment);
		}
		if (pointACompartments.get(source) == null) {
			pointACompartments.put(source, compartments);
		} else {
			pointACompartments.get(source).addAll(compartments);
		}
		return pointACompartments;
	}

	public void reArrangeOrderContents() {
		for (String key : orderCompartments.keySet()) {

			List<Compartment> groupedBySource = orderCompartments.get(key);
			Map<Integer, List<Compartment>> result = compartmentsPerLoad(groupedBySource);
			sourceVolumeMapped.put(key, result);
		}
	}

}
