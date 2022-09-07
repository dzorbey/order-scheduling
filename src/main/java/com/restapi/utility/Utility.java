package com.restapi.utility;

import java.text.DecimalFormat;
import com.restapi.dto.AbstractResponse;
import com.restapi.dto.ErrorResponse;

public class Utility {

	public static DecimalFormat formatter2Dec = new DecimalFormat("##.00");
	public static DecimalFormat formatter1Dec = new DecimalFormat("##.0");
	public static DecimalFormat formatterNoDec = new DecimalFormat("##");

	public static AbstractResponse error(String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(message);
		errorResponse.setSuccess(false);
		return errorResponse;
	}
}
