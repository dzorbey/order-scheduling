package com.restapi.domain;

import java.util.ArrayList;
import java.util.List;

public class RigidAllocation {

	private Compartment[] compartments;
	private Integer capacity;
	private Integer allocatedSpace;
	private Boolean allocated;
	private List<Integer> indexesToRemove;

	public Integer getAllocatedSpace() {
		return allocatedSpace;
	}

	public void setAllocatedSpace(Integer allocatedSpace) {
		this.allocatedSpace = allocatedSpace;
	}

	public Boolean getAllocated() {
		return allocated;
	}

	public RigidAllocation(Compartment[] compartments, Integer capacity, Integer allocatedSpace, Boolean allocated) {
		this.compartments = compartments;
		this.capacity = capacity;
		this.allocated = allocated;
		this.allocatedSpace = allocatedSpace;
	}

	public RigidAllocation(int capacity, Integer allocatedSpace, Boolean allocated) {
		this.capacity = capacity;
		this.allocated = allocated;
		this.allocatedSpace = allocatedSpace;
	}

	public Compartment[] getCompartments() {
		return compartments;
	}

	public void setCompartments(Compartment[] compartments) {
		this.compartments = compartments;
	}

	public Boolean isAllocated() {
		return allocated;
	}

	public void setAllocated(Boolean allocated) {
		this.allocated = allocated;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Integer> getIndexesToRemove() {
		return indexesToRemove;
	}

	public void setIndexesToRemove(List<Integer> indexesToRemove) {
		this.indexesToRemove = indexesToRemove;
	}

	/*
	 * knapsack algorithm
	 */
	public Solution compartmentAllocation() {

		indexesToRemove = new ArrayList<Integer>();
		int NB_ITEMS = compartments.length;
		// we use a matrix to store the max value at each n-th item
		int[][] matrix = new int[NB_ITEMS + 1][capacity + 1];

		// first line is initialized to 0
		for (int i = 0; i <= capacity; i++)
			matrix[0][i] = 0;

		// we iterate on items
		for (int i = 1; i <= NB_ITEMS; i++) {
			// we iterate on each capacity
			for (int j = 0; j <= capacity; j++) {
				if (compartments[i - 1].getWeight() > j)
					matrix[i][j] = matrix[i - 1][j];
				else
					// we maximize value at this rank in the matrix
					matrix[i][j] = Math.max(matrix[i - 1][j],
							matrix[i - 1][j - compartments[i - 1].getWeight()] + compartments[i - 1].getPrice());
			}
		}

		int res = matrix[NB_ITEMS][capacity];
		int w = capacity;
		List<Compartment> itemsSolution = new ArrayList<>();

		for (int i = NB_ITEMS; i > 0 && res > 0; i--) {
			if (res != matrix[i - 1][w]) {
				itemsSolution.add(compartments[i - 1]);

				Integer tmp = i - 1;
				indexesToRemove.add(tmp);
				// we remove items value and weight
				res -= compartments[i - 1].getPrice();
				w -= compartments[i - 1].getWeight();
			}
		}
		//compartments = null;
		return new Solution(itemsSolution, matrix[NB_ITEMS][capacity]);
	}
}
