package com.restapi.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.restapi.das.GenericDas;
import com.restapi.dto.AbstractResponse;
import com.restapi.dto.Journeys;
import com.restapi.dto.OrderAllocationRequest;
import com.restapi.dto.SchedulingResponse;
import com.restapi.service.SchedulingService;
import com.restapi.utility.Utility;

@RestController
@RequestMapping(value = "/schedule", produces = { "application/json" })
@Api(value = "order-scheduling", description = "Order-Scheduling Operations")
public class SchedulingController {

	@Autowired
	private SchedulingService schedulingService;

	@Autowired
	private GenericDas das;

	@RequestMapping(method = RequestMethod.POST, value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Schedule given orders for allocation", response = SchedulingResponse.class, nickname = "scheduleOrders")
	public AbstractResponse scheduleOrders(@RequestBody @Valid OrderAllocationRequest request) throws Exception {

		try {
			return schedulingService.processOrders(request);
		} catch (Exception e1) {
			return Utility.error(e1.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/allocatedJourneys")
	@ApiOperation(value = "Schedule given orders for allocation", response = Journeys.class, nickname = "allocatedJourneys")
	public AbstractResponse getAllocatedJourneys() throws Exception {

		try {
			Journeys journeys = das.fetchAllJourneys();
			journeys.setMessage("journeys returned..");
			journeys.setSuccess(true);

			return journeys;
		} catch (Exception e1) {
			return Utility.error(e1.getMessage());
		}
	}
}
