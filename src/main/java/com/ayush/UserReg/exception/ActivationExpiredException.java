package com.ayush.UserReg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ActivationExpiredException extends UserRegistrationException{

	
	private static final long serialVersionUID = 7386879792429165258L;

	public ActivationExpiredException(String msg) {
		super(msg);
	}
}
