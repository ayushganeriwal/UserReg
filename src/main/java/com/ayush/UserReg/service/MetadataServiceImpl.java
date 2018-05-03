package com.ayush.UserReg.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.ayush.UserReg.common.ServiceRegistry;
import com.ayush.UserReg.dto.TempUser;
import com.ayush.UserReg.dto.User;
import com.ayush.UserReg.exception.UserRegistrationException;


/**
 * Temp implementation for POC. to be replaced with MongoDB based impl.
 * 
 */
public class MetadataServiceImpl implements MetadataService {

	private HashMap<String, User> activeUserMap = new HashMap<String, User>();
	private HashMap<String, TempUser> tempUserMap = new HashMap<String, TempUser>();
	
	private ServiceRegistry serviceRegistry;
	
	@Autowired
	public MetadataServiceImpl(ServiceRegistry registry) {
		this.serviceRegistry = registry;
	}
	
	@Override
	public String createTempUser (User u) {
		String id = UUID.randomUUID().toString();
		tempUserMap.put(id, new TempUser(u));
		return id;
	}
	
	@Override
	public User deleteTempUser (String id) {
		return tempUserMap.remove(id).getUser();
	}
	
	
	@Override
	public User activateTempUser(String id) throws UserRegistrationException {
		if(!tempUserMap.containsKey(id)) {
			throw new UserRegistrationException("Invalid activation request.");
		}
		
		if (activeUserMap.containsKey(id)) {
			throw new UserRegistrationException("Already activated.");
		} 
		User u = tempUserMap.remove(id).getUser();
		activeUserMap.put(u.getId(), u);
		
		return activeUserMap.get(id);
	}
	
	@Override
	public void updateUser(User u) throws UserRegistrationException {
		
		if (!activeUserMap.containsKey(u.getId())) {
			throw new UserRegistrationException("Invalid User.");
		} 
	
		activeUserMap.put(u.getId(), u);
		
	}
	
	@Override
	public void deleteUser(User u) throws UserRegistrationException {
		
		if (!activeUserMap.containsKey(u.getId())) {
			throw new UserRegistrationException("Invalid User.");
		} 
	
		activeUserMap.remove(u.getId());
		
	}
	
	@Override
	public void clearTempUsers(long time) throws UserRegistrationException {
		
		ArrayList<User> expiredUsers = new ArrayList<User>();
		
		tempUserMap.values().forEach(t-> {if (t.getCreationTime() < time) expiredUsers.add(t.getUser());});
		
		expiredUsers.forEach(u -> {tempUserMap.remove(u.getId());});
		
	}
	
	@Override
	public User getUser(String id) throws UserRegistrationException {
		
		if (!activeUserMap.containsKey(id)) {
			throw new UserRegistrationException("Invalid User.");
		} 
	
		return activeUserMap.get(id);
		
	}
	
	@Override
	public Collection<User> getUserList() throws UserRegistrationException {
			return activeUserMap.values();
	}

	@Override
	public User getTempUser(String id) throws UserRegistrationException {
		if (!tempUserMap.containsKey(id)) {
			throw new UserRegistrationException("Invalid Temp User.");
		} 
	
		return tempUserMap.get(id).getUser();
	}
	
	
	
	
}
