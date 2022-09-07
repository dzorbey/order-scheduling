package com.restapi.domain;

import java.util.List;

public class Solution {

	public List<Compartment> compartments;
	public int value;

	public Solution(List<Compartment> compartments, int value) {
		this.compartments = compartments;
		this.value = value;
	}

	public List<Compartment> selectedItems() {
		return compartments;
	}
}