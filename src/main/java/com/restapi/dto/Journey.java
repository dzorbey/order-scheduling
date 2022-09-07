package com.restapi.dto;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AppPojo
public class Journey {

	private BigDecimal successRate; 
	private Boolean arranged;
	private String container1;
	private String container2;
	private String container3;
	private String container4;
	private List<CompartmentAllocation> allocations = new ArrayList<CompartmentAllocation>();
	

	
	public String getContainer1() {
		return container1;
	}
	public void setContainer1(String container1) {
		this.container1 = container1;
	}
	public String getContainer2() {
		return container2;
	}
	public void setContainer2(String container2) {
		this.container2 = container2;
	}
	public String getContainer3() {
		return container3;
	}
	public void setContainer3(String container3) {
		this.container3 = container3;
	}
	public String getContainer4() {
		return container4;
	}
	public void setContainer4(String container4) {
		this.container4 = container4;
	}
	public Boolean getArranged() {
		return arranged;
	}
	public void setArranged(Boolean arranged) {
		this.arranged = arranged;
	}
	public BigDecimal getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(BigDecimal successRate) {
		this.successRate = successRate;
	}
	public List<CompartmentAllocation> getAllocations() {
		return allocations;
	}
	public void setAllocations(List<CompartmentAllocation> allocations) {
		this.allocations = allocations;
	}
}