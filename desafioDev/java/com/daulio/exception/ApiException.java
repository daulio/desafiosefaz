package com.daulio.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ApiException extends Exception{

	private static final long serialVersionUID = 3715060480087172695L;
	private Date timestamp;
    private int status;
    private String error;
    private String message;

    public ApiException(HttpStatus status, String message) {
        this.timestamp = new Date();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
    }
    
    public ApiException(Date timestamp, int status, String error, String message) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
	}



	public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
