package com.ayush.UserReg.service;

import org.springframework.web.client.RestTemplate;

import com.ayush.UserReg.common.ServiceRegistry;
import com.ayush.UserReg.dto.User;
import com.ayush.UserReg.resource.MetadataController;
import com.ayush.UserReg.resource.UserController;

public class CommunicationServiceImpl implements CommunicationService {

	private ServiceRegistry registry;
	
	public CommunicationServiceImpl(ServiceRegistry serviceRegistry) {
		this.registry = serviceRegistry;
	}
	
	@Override
	public String sendMessage(String userId) {
		
		RestTemplate template = new RestTemplate();
		
		String uri = registry.lookupService(MetadataController.SERVICE_NAME)+"/users/tempusers/"+userId;
		
		String msg = "Thankes for registering. Please activate user by clicking on the link : "
				+registry.lookupService(UserController.SERVICE_NAME)+"/Users/"+userId+"/activate";
		
		User user = template.getForObject(uri, User.class);
		
		//Idealy the different handlers should be registered with the comm service so that we can dynamicaly choose handlers. 
		// but for POC print to console
	
		if (user.getPrefContactMethod() == User.CommunicationMethod.EMAIL) {
			
			System.out.println("Send email to registered email address"+user.getEmail()+" : "+msg);
			
		} else if ( user.getPrefContactMethod() == User.CommunicationMethod.SMS){
			
			System.out.println("Send SMS to user phone"+user.getPhone()+" : "+msg);
		}
		
		return "Please active using the link provided in the message sent to the preferred communication channel.";

	}

}
