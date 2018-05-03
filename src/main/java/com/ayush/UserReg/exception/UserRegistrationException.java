package com.ayush.UserReg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserRegistrationException extends Exception {
	
	private static final long serialVersionUID = -6489275271296522212L;

	public UserRegistrationException() {
		super();
	}
	
	public UserRegistrationException(Exception e) {
		super(e);
	}
	
	public UserRegistrationException(String msg) {
		super(msg);
	}
}
