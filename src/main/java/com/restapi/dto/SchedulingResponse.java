package com.restapi.dto;
@AppPojo
public class SchedulingResponse extends AbstractResponse {

	private Double utilizationRatio;

	public Double getUtilizationRatio() {
		return utilizationRatio;
	}

	public void setUtilizationRatio(Double utilizationRatio) {
		this.utilizationRatio = utilizationRatio;
	}
}
