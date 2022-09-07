package com.restapi.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.restapi.domain.Compartment;
import com.restapi.domain.RigidAllocation;
import com.restapi.domain.Solution;
import com.restapi.dto.SchedulingResponse;
import com.restapi.service.SchedulingServiceImpl;

public class MockSchedulingServiceImpl extends SchedulingServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Compartment> processCompartments(String key, List<Compartment> currentOrders, RigidAllocation allocator, Long sleep)
			throws Exception {

		if (sleep != null) {
			TimeUnit.MILLISECONDS.sleep(sleep);
		}

		if (allocator != null) {
			Compartment[] compartmentArray = currentOrders.stream().toArray(Compartment[]::new);

			allocator.setAllocated(true);
			display(allocator.getCompartments(), allocator.getCapacity());

			allocator.setCompartments(compartmentArray);
			Solution solution = allocator.compartmentAllocation();
			display(solution.compartments, solution.value);
			
			for (Integer x : allocator.getIndexesToRemove()) {
				if (getOnHold().size() > 3) {
					allocator.setAllocated(false);
					return currentOrders;
				}
				Integer availableSpace = 140 - calculateCurrentLoad();
				if (availableSpace < compartmentArray[x].getWeight()) {
					allocator.setAllocated(false);
					return currentOrders;
				}
				getOnHold().add(compartmentArray[x]);
				compartmentArray = ArrayUtils.remove(compartmentArray, x);
			}
			List<Compartment> newList = Arrays.asList(compartmentArray);
			compartmentArray = null;

			for (Compartment compartment : newList) {
				logger.info("remaining List: " + compartment.str());
			}
			if (newList != null) {
				getOrderCompartments().put(key, newList);
			}
			allocator.setAllocated(false);
			return getOrderCompartments().get(key);
		}
		return currentOrders;
	}

	public void display(Compartment[] compartments, Integer capacity) {
		if (compartments != null && compartments.length > 0) {
			logger.info("Capacity : " + capacity);
			logger.info("Compartments :");

			for (Compartment compartment : compartments) {
				logger.info("remaining List: " + compartment.str());
			}
		}
	}

	public void display(List<Compartment> compartments, Integer value) {
		if (compartments != null && !compartments.isEmpty()) {
			logger.info("Value = " + value);
			logger.info("Items to pick :");

			for (Compartment compartment : compartments) {
				logger.info("-   " + compartment.str());
			}
		}
	}

	@Override
	public SchedulingResponse orderSchedule(Long sleep) {

		List<Compartment> currentOrders = Collections.synchronizedList(new ArrayList<Compartment>());
		Date startDate = new Date();

		SchedulingResponse response = new SchedulingResponse();
		while (true) {

			int numSeconds = (int) ((Calendar.getInstance().getTimeInMillis() - startDate.getTime()) / 1000);
			if (numSeconds > getWaitMaxSeconds()) {
				response.setSuccess(false);
				response.setUtilizationRatio(null);
				response.setMessage(
						"space complexity likely hitting jvm upper bound, check your settings and criterias..");
				return response;
			}
			
			if (remainingItems() == 0) {
				break;
			}
			for (String key : getSourceVolumeMapped().keySet()) {
				currentOrders = getOrderCompartments().get(key);
				try {
					currentOrders = processCompartments(key, currentOrders, findFreeAllocator(), sleep);
					currentOrders = processCompartments(key, currentOrders, findFreeAllocator(), sleep);
					currentOrders = processCompartments(key, currentOrders, findFreeAllocator(), sleep);
					processCompartments(key, currentOrders, findFreeAllocator(), sleep);

					if (getOnHold().size() > 0) {
						Integer currentAllocation = calculateCurrentLoad();
						logger.info("- currentAllocation: " + currentAllocation);
						setSuccessRatio((Double.valueOf(currentAllocation) / 140) * 100);
						logger.info("- journey-utilizationRatio: " + getSuccessRatio());
						getSuccessRatios().add(getSuccessRatio());

						for (Compartment compartment : getOnHold()) {
							logger.info("onHold.weight: " + compartment.getWeight());
						}
						setOnHold(new ArrayList<Compartment>());
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Exception thrown during allocation: " + e.getMessage());
				}
			}
		}
		response.setSuccess(true);
		response.setUtilizationRatio(utilizationSuccessRatio());
		response.setMessage("orders utilized..");

		return response;
	}

}
