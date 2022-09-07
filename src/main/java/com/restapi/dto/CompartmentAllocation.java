package com.restapi.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

@AppPojo
public class CompartmentAllocation {

	private String allocationId;
	private String orderId;
	private String source;
	private String destination;
	private Double weight;
	private String fuelType;
	

	@ApiModelProperty(required = true, value = "allocationSystemId", example = "1000,1001")
	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	@ApiModelProperty(required = true, value = "associatedOrderSystemId", example = "1000,1001")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@ApiModelProperty(required = true, value = "allocationSource", example = "Exxon, Shell")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@ApiModelProperty(required = true, value = "allocationDestination", example = "PointA, PointB")
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@ApiModelProperty(required = true, value = "allocationWeight", example = "1.3, 3.0, ... m3")
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public BigDecimal getDecimalWeight() {
		return new BigDecimal(weight);
	}

	@ApiModelProperty(required = true, value = "allocationFuelType", example = "gas, diesel")
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
}