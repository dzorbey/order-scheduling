package com.restapi.service;

import java.util.List;
import java.util.Map;

import com.restapi.domain.Compartment;
import com.restapi.domain.RigidAllocation;
import com.restapi.dto.OrderAllocationRequest;
import com.restapi.dto.SchedulingResponse;

public interface SchedulingService {

	public List<Compartment> processCompartments(String key, List<Compartment> currentOrders, RigidAllocation allocator,
			Long seconds) throws Exception;

	public SchedulingResponse orderSchedule(Long sleep);

	public RigidAllocation findFreeAllocator();

	public Map<Integer, List<Compartment>> compartmentsPerLoad(List<Compartment> orderedCompartments);

	public Map<String, List<Compartment>> loadUtilization(Map<String, List<Compartment>> pointACompartments,
			int orderId, Double volume, String type, String source, String destination);

	public void reArrangeOrderContents();

	public SchedulingResponse processOrders(OrderAllocationRequest request);

	public Integer calculateCurrentLoad();

}