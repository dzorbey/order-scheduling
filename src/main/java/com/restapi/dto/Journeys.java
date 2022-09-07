package com.restapi.dto;

import java.util.ArrayList;
import java.util.List;

@AppPojo
public class Journeys extends AbstractResponse {

	List<Journey> allocatedJourneys = new ArrayList<Journey>();

	public List<Journey> getAllocatedJourneys() {
		return allocatedJourneys;
	}

	public void setAllocatedJourneys(List<Journey> allocatedJourneys) {
		this.allocatedJourneys = allocatedJourneys;
	}
}