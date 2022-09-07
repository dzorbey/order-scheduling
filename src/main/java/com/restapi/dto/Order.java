package com.restapi.dto;

import java.util.ArrayList;
import java.util.List;

@AppPojo
public class Order {

	List<OrderLine> orderLines = new ArrayList<OrderLine>();

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
}