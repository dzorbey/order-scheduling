package com.restapi.dto;

@AppPojo
public class AbstractResponse {

	private Boolean success = null;
	private String message = null;

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public Boolean getSuccess() {
		return success;
	}
}