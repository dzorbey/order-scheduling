package com.restapi.dto;

import java.util.ArrayList;
import java.util.List;

@AppPojo
public class OrderAllocationRequest {

	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}