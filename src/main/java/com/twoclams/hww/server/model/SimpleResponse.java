package com.twoclams.hww.server.model;

public class SimpleResponse {
	private int returnCode;
	private String error;
	
	public SimpleResponse() {
	
	}	

	public SimpleResponse(int returnCode, String error) {
		this.returnCode = returnCode;
		this.error = error;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int errorCode) {
		this.returnCode = errorCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String errorDescription) {
		this.error = errorDescription;
	}
}
