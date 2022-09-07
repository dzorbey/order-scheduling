package com.restapi.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import com.restapi.dto.SchedulingResponse;
import com.restapi.mock.MockSchedulingServiceImpl;
import com.restapi.utility.Utility;

@Component
@Profile("test")
public class SchedulingServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MockSchedulingServiceImpl service;

	public SchedulingServiceTest() {
		service = new MockSchedulingServiceImpl();
	}
	
	

	@SuppressWarnings("static-access")
	//@Test
	public void decimalPointsAllocationRatioSatisfied() throws Exception {

		service.initilizeCollections();

		service.loadUtilization(service.getOrderCompartments(), 123, 4.0, "gas", "Exxon", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 123, 4.4, "diesel", "Exxon", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 123, 3.2, "gas", "Shell", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 123, 4.0, "diesel", "Shell", "PointA");

		service.loadUtilization(service.getOrderCompartments(), 124, 3.0, "diesel", "Exxon", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 124, 4.1, "gas", "Exxon", "PointB");
		service.loadUtilization(service.getOrderCompartments(), 124, 3.6, "diesel", "Shell", "PointB");
		service.loadUtilization(service.getOrderCompartments(), 124, 4.0, "gas", "Exxon", "PointB");

		service.loadUtilization(service.getOrderCompartments(), 125, 3.2, "gas", "Exxon", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 125, 5.1, "diesel", "Exxon", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 125, 13.2, "gas", "Shell", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 125, 14.0, "diesel", "Shell", "PointA");

		service.loadUtilization(service.getOrderCompartments(), 126, 12.0, "diesel", "Exxon", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 126, 4.0, "gas", "Exxon", "PointB");
		service.loadUtilization(service.getOrderCompartments(), 126, 15.0, "diesel", "Exxon", "PointB");
		service.loadUtilization(service.getOrderCompartments(), 126, 16.0, "gas", "Shell", "PointB");
		
		service.reArrangeOrderContents();
		SchedulingResponse response = service.orderSchedule(/*Long.valueOf(100)*/ null);

		logger.info("");
		String logMessage = "custom data with decimal points success ratio..: "+ response.getUtilizationRatio();
		logger.info("\u001B[32m" + logMessage + "\u001B[0m");
		assertTrue(response.getUtilizationRatio() > Double.valueOf("50"));
	}
	
	


	@SuppressWarnings("static-access")
	//@Test
	public void randomizedSetOfAllocationsRatioSatisfied() throws Exception {

		List<Double> multipleAllocationRatios = new ArrayList<Double>();
		for (int i = 0; i < 20; i++) {
			service.initilizeCollections();

			service.loadUtilization(service.getOrderCompartments(), 123, randomDoubleGenerator(), "gas", "Exxon", "PointA");
			service.loadUtilization(service.getOrderCompartments(), 123, randomDoubleGenerator(), "diesel", "Exxon", "PointA");
			service.loadUtilization(service.getOrderCompartments(), 123, randomDoubleGenerator(), "gas", "Shell", "PointA");
			service.loadUtilization(service.getOrderCompartments(), 123, randomDoubleGenerator(), "diesel", "Shell", "PointA");

			service.loadUtilization(service.getOrderCompartments(), 124, randomDoubleGenerator(), "diesel", "Exxon", "PointA");
			service.loadUtilization(service.getOrderCompartments(), 124, randomDoubleGenerator(), "gas", "Exxon", "PointB");
			service.loadUtilization(service.getOrderCompartments(), 124, randomDoubleGenerator(), "diesel", "Exxon", "PointB");
			service.loadUtilization(service.getOrderCompartments(), 124, randomDoubleGenerator(), "gas", "Exxon", "PointB");

			service.reArrangeOrderContents();
			SchedulingResponse response = service.orderSchedule(null);
			multipleAllocationRatios.add(response.getUtilizationRatio());

		}
		Double totalRatios = multipleAllocationRatios.stream().mapToDouble(Double::doubleValue).sum();
		Double utilizationRatio = totalRatios / multipleAllocationRatios.size();
		
		logger.info("");
		String logMessage = 
				"generic utilization ratio over " + multipleAllocationRatios.size() + " random trials..: "+ Utility.formatter2Dec.format(utilizationRatio);
		logger.info("\u001B[32m" + logMessage + "\u001B[0m");
		assertTrue(utilizationRatio > Double.valueOf("50"));
	}
	
	
	
	@SuppressWarnings("static-access")
	@Test
	public void requestedTestRatioSatisfied() throws Exception {
		
		service.initilizeCollections();

		service.loadUtilization(service.getOrderCompartments(), 123, 3.0, "gas", "Exxon", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 123, 4.0, "diesel", "Exxon", "PointA");

		service.loadUtilization(service.getOrderCompartments(), 124, 2.0, "diesel", "Exxon", "PointA");
		service.loadUtilization(service.getOrderCompartments(), 124, 4.0, "gas", "Exxon", "PointB");

		service.reArrangeOrderContents();
		SchedulingResponse response = service.orderSchedule(Long.valueOf(100));

		logger.info("");
		String logMessage = "requestedTestSuccessRatio success ratio..: "+ response.getUtilizationRatio();
		logger.info("\u001B[32m" + logMessage + "\u001B[0m");
		assertTrue(response.getUtilizationRatio() > Double.valueOf("50"));
	}
	
	
	public Double randomDoubleGenerator() {
		Double random = ThreadLocalRandom.current().nextDouble(1.0, 16.0);
		//return Double.valueOf(Utility.formatterNoDec.format(random));
		return Double.valueOf(Utility.formatter1Dec.format(random));
	}
}