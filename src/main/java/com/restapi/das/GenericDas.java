package com.restapi.das;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.dao.CompartmentAllocationDao;
import com.restapi.dao.JourneyDao;
import com.restapi.dao.JourneyLineDao;
import com.restapi.dao.OrderDao;
import com.restapi.dao.OrderLineDao;
import com.restapi.domain.Compartment;
import com.restapi.dto.CompartmentAllocation;
import com.restapi.dto.Journey;
import com.restapi.dto.Journeys;
import com.restapi.dto.OrderLine;
import com.restapi.jooq.model.tables.pojos.BpCompartmentAllocation;
import com.restapi.jooq.model.tables.pojos.BpJourney;
import com.restapi.jooq.model.tables.pojos.BpJourneyLine;
import com.restapi.jooq.model.tables.pojos.BpOrder;
import com.restapi.jooq.model.tables.pojos.BpOrderLine;
import com.restapi.utility.Utility;

@Service
public class GenericDas {

	@Autowired
	private JourneyDao journeyDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderLineDao orderLineDao;

	@Autowired
	private JourneyLineDao journeyLineDao;

	@Autowired
	private CompartmentAllocationDao compartmentAllocationDao;

	static class AllocationComperator implements Comparator<BpCompartmentAllocation> {
		@Override
		public int compare(BpCompartmentAllocation arg0, BpCompartmentAllocation arg1) {
			return arg1.getWeight().compareTo(arg0.getWeight());
		}
	}

	public Journeys fetchAllJourneys() {

		Journeys journeys = new Journeys();
		List<BpCompartmentAllocation> bpCompartmentAllocations = compartmentAllocationDao.fetchAll();

		Map<Long, List<BpCompartmentAllocation>> allocationsPerJourney = bpCompartmentAllocations.stream()
				.collect(Collectors.groupingBy(w -> w.getJourneyId()));

		for (Long key : allocationsPerJourney.keySet()) {
			Journey journey = new Journey();
			BpJourney bpJourney = this.journeyDao.fetchOneById(key);
			journey.setSuccessRate(bpJourney.getAllocationRatio());
			journey.setArranged(bpJourney.getArranged());
			journey.setContainer1(String.valueOf(bpJourney.getContainer1()));
			journey.setContainer2(String.valueOf(bpJourney.getContainer2()));
			journey.setContainer3(String.valueOf(bpJourney.getContanier3()));
			journey.setContainer4(String.valueOf(bpJourney.getContanier4()));

			for (BpCompartmentAllocation allocation : allocationsPerJourney.get(key)) {
				CompartmentAllocation compartmentAllocation = new CompartmentAllocation();
				compartmentAllocation.setDestination(allocation.getDestination());
				compartmentAllocation.setFuelType(allocation.getFuelType());
				compartmentAllocation.setSource(allocation.getSource());
				compartmentAllocation.setOrderId(String.valueOf(allocation.getOrderId()));
				compartmentAllocation.setAllocationId(String.valueOf(allocation.getId()));
				compartmentAllocation.setWeight(allocation.getWeight().doubleValue());

				journey.getAllocations().add(compartmentAllocation);
			}
			journeys.getAllocatedJourneys().add(journey);
		}
		return journeys;
	}

	public void arrangeJourneyContainers() {
		List<BpCompartmentAllocation> bpCompartmentAllocations = compartmentAllocationDao.fetchAll();

		Map<Long, List<BpCompartmentAllocation>> allocationsPerJourney = bpCompartmentAllocations.stream()
				.collect(Collectors.groupingBy(w -> w.getJourneyId()));

		for (Long key : allocationsPerJourney.keySet()) {
			BpJourney journey = this.journeyDao.fetchOneById(key);

			if (journey.getArranged() == true) {
				continue;
			}
			List<BpCompartmentAllocation> allocations = allocationsPerJourney.get(key);
			Collections.sort(allocations, new AllocationComperator());

			if (allocations.size() == 1) {
				journey.setContainer1(allocations.get(0).getId());
			}
			if (allocations.size() == 2) {
				journey.setContainer1(allocations.get(0).getId());
				journey.setContainer2(allocations.get(1).getId());
			}
			if (allocations.size() == 3) {
				journey.setContainer1(allocations.get(0).getId());
				journey.setContainer2(allocations.get(1).getId());
				journey.setContanier3(allocations.get(2).getId());
			}

			if (allocations.size() == 4) {
				journey.setContainer1(allocations.get(0).getId());
				journey.setContainer2(allocations.get(1).getId());
				journey.setContanier3(allocations.get(2).getId());
				journey.setContanier4(allocations.get(3).getId());
			}
			journey.setArranged(true);
			journeyDao.update(journey);
		}
	}

	public Long createJourney(Double successRatio) {
		
		BpJourney bpJourney = new BpJourney();
		bpJourney.setAllocationRatio(new BigDecimal(Utility.formatter2Dec.format(successRatio)));
		bpJourney.setArranged(false);
		journeyDao.insert(bpJourney);
		return bpJourney.getId();
	}

	public void createAllocation(Long journeyId, Compartment compartment) {
		
		BpCompartmentAllocation compartmentAllocation = new BpCompartmentAllocation();
		compartmentAllocation.setDestination(compartment.getDestination());
		compartmentAllocation.setSource(compartment.getSource());
		compartmentAllocation.setOrderId(compartment.getOrderId().longValue());
		compartmentAllocation.setJourneyId(journeyId);
		compartmentAllocation.setFuelType(compartment.getFuelType());
		compartmentAllocation.setWeight(compartment.getDecimalWeight().divide(new BigDecimal(10)));
		compartmentAllocationDao.insert(compartmentAllocation);

		BpJourneyLine bpJourneyLine = new BpJourneyLine();
		bpJourneyLine.setJourneyId(journeyId);
		bpJourneyLine.setCompartmentId(compartmentAllocation.getId());
		journeyLineDao.insert(bpJourneyLine);
	}

	public Long createOrder(BigDecimal orderLoad) {
		
		BpOrder bpOrder = new BpOrder();
		bpOrder.setWeight(orderLoad);
		orderDao.insert(bpOrder);
		return bpOrder.getId();
	}

	public Long createOrderLine(Long orderId, OrderLine orderLine) {
		
		BpOrderLine bpOrderLine = new BpOrderLine();
		bpOrderLine.setOrderId(orderId);
		bpOrderLine.setDestination(orderLine.getDestination());
		bpOrderLine.setSource(orderLine.getSource());
		bpOrderLine.setWeight(new BigDecimal(orderLine.getWeight()));
		orderLineDao.insert(bpOrderLine);
		return bpOrderLine.getId();
	}

}
