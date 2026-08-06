package com.rest_webservices.RestFul_web_services.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
	private LocalDateTime TimeStamp;
	private String message;
	private String details;
	
	public ErrorDetails(LocalDateTime timeStamp, String message, String details) {
		super();
		TimeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	
	public LocalDateTime getTimeStamp() {
		return TimeStamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	
}
