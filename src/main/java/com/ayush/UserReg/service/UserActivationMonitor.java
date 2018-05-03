package com.ayush.UserReg.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.ayush.UserReg.common.ServiceRegistry;
import com.ayush.UserReg.resource.MetadataController;

public class UserActivationMonitor {

	private boolean stop= false;
	
	private ServiceRegistry registry;
	
	@Autowired
	public UserActivationMonitor(ServiceRegistry svcRegistry) {
		this.registry = svcRegistry;
	}
	
	public void startMonitor() {
		RestTemplate template = new RestTemplate();
		String uri = registry.lookupService(MetadataController.SERVICE_NAME)+"/users/tempusers/clean"; 
		
		HashMap<String, Long> varMap = new HashMap<String, Long>();
		
		new Thread(() -> {
		
			while(!stop){
				varMap.put("time", System.currentTimeMillis()-30000);
				template.postForLocation(uri, null, varMap);
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
		System.out.println("Activation Monitor Started...");
	}
	
	public void stopMonitor() {
		this.stop = true;
	}
	
}
