package com.ayush.UserReg.common;

import java.util.HashMap;

import com.ayush.UserReg.resource.CommunicationController;
import com.ayush.UserReg.resource.MetadataController;
import com.ayush.UserReg.resource.UserController;

public class ServiceRegistry {

	private HashMap<String, String> serviceMap = new HashMap<String, String>(); 
	
	public ServiceRegistry() {
		init();
	}
	
	private void init() {
		serviceMap.put(MetadataController.SERVICE_NAME, "http://localhost:8080/v1/metadata");
		serviceMap.put(UserController.SERVICE_NAME, "http://localhost:8080/v1/users");
		serviceMap.put(CommunicationController.SERVICE_NAME, "http://localhost:8080/v1/communication");
	}
	
	public String lookupService(String serviceName) {
		return serviceMap.get(serviceName);
	}
}
