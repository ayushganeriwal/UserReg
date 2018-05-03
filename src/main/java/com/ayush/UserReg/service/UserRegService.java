package com.ayush.UserReg.service;

import java.util.Collection;

import com.ayush.UserReg.dto.User;
import com.ayush.UserReg.exception.UserRegistrationException;

public interface UserRegService {

	public String createUser(User usr) throws UserRegistrationException;
	
	public Collection<User> getUsers();
	
	public User activateUser(String id);
	
	public User getUser(String id);
	
	public void updateUser(User u);
	
	public void deleteUser(String id);
}
