package com.restapi.domain;

import java.math.BigDecimal;

public class Compartment {

	private Integer weight;
	private Integer price;
	private Integer orderId;
	private String source;
	private String destination;
	private String fuelType;
	private Boolean utilized;

	public Compartment(Integer weight, Integer price, Boolean utilized) {
		super();
		this.weight = weight;
		this.price = price;
		this.utilized = utilized;
	}

	public Compartment() {
	}

	public Boolean getUtilized() {
		return utilized;
	}

	public void setUtilized(Boolean utilized) {
		this.utilized = utilized;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public BigDecimal getDecimalWeight() {
		return new BigDecimal(weight);
	}
	
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String str() {
		return this.getOrderId() + " [value = " + this.getPrice() + ", weight = " + this.getWeight() + "]";
	}
}
