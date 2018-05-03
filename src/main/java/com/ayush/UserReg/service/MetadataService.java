package com.ayush.UserReg.service;

import java.util.Collection;

import com.ayush.UserReg.dto.User;
import com.ayush.UserReg.exception.UserRegistrationException;


public interface MetadataService {

	public String createTempUser (User u);
	public User deleteTempUser (String id);
	public User activateTempUser(String id) throws UserRegistrationException;
	
	public void updateUser(User u) throws UserRegistrationException;
	
	public void deleteUser(User u) throws UserRegistrationException ;
	
	public void clearTempUsers(long time) throws UserRegistrationException ;
	
	public User getUser(String id) throws UserRegistrationException ;
	
	public User getTempUser(String id) throws UserRegistrationException ;
	
	public Collection<User> getUserList() throws UserRegistrationException ;
	
	
	
	
}
