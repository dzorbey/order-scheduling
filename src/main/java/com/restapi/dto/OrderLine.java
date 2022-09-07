package com.restapi.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

@AppPojo
public class OrderLine {

	private String source;
	private String destination;
	private Double weight;
	private String fuelType;

	@ApiModelProperty(required = true, value = "orderSource", example = "Exxon, Shell")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@ApiModelProperty(required = true, value = "orderDestination", example = "PointA, PointB")
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@ApiModelProperty(required = true, value = "orderWeight", example = "1.3, 3.0, ... m3")
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public BigDecimal getDecimalWeight() {
		return new BigDecimal(weight);
	}

	@ApiModelProperty(required = true, value = "orderFuelType", example = "gas, diesel")
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
}