package com.ayush.UserReg.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ayush.UserReg.common.ServiceRegistry;
import com.ayush.UserReg.dto.User;
import com.ayush.UserReg.exception.UserRegistrationException;
import com.ayush.UserReg.resource.CommunicationController;
import com.ayush.UserReg.resource.MetadataController;
import com.ayush.UserReg.resource.UserController;

public class UserRegServiceImpl implements UserRegService{

	private boolean isMonitorStarted = false;
	public final String METADATA_SERVICE_URI = "http://localhost:8080/v1/";
	
	private ServiceRegistry serviceRegistry;
	private UserActivationMonitor activationMonitor;
	
	
	
	@Autowired
	public UserRegServiceImpl(ServiceRegistry registry, UserActivationMonitor activationMonitor) {
		this.serviceRegistry = registry;
		this.activationMonitor = activationMonitor;
	}
	
	
	
	@Override
	public String createUser(User usr) throws UserRegistrationException{
		
//		if(!isMonitorStarted) {
//			activationMonitor.startMonitor();
//			isMonitorStarted = true;
//		}
		
		validateAndTransformUser(usr);
		
		RestTemplate template = new RestTemplate();
		String uriMetadata = serviceRegistry.lookupService(MetadataController.SERVICE_NAME)+"/users";
		ResponseEntity<String> respEntity = template.postForEntity(uriMetadata, usr, String.class);
		String userUoid = respEntity.getBody();
		
//		String uriComm = serviceRegistry.lookupService(CommunicationController.SERVICE_NAME)+"/message";
//		ResponseEntity<String> respEntity1 = template.postForEntity(uriComm+"/"+userUoid, "test", String.class);
		return userUoid;
	}
	
	@Override
	public Collection<User> getUsers() {
		
		RestTemplate template = new RestTemplate();
		String uri = serviceRegistry.lookupService(MetadataController.SERVICE_NAME)+"/users";
		return template.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>(){}).getBody();
	}
	
	
	
	
	
	@Override
	public User activateUser(String id) {
		RestTemplate template = new RestTemplate();
		String uri = serviceRegistry.lookupService(MetadataController.SERVICE_NAME)+"/users";
		
		return template.postForObject(uri+"/"+id+"/activate", null, User.class);
	}

	@Override
	public User getUser(String id) {
		
		RestTemplate template = new RestTemplate();
		String uri = serviceRegistry.lookupService(MetadataController.SERVICE_NAME)+"/users";
		
		return template.getForObject(uri+"/"+id, User.class);
	}

	@Override
	public void updateUser(User u) {
		
		RestTemplate template = new RestTemplate();
		String uri = serviceRegistry.lookupService(MetadataController.SERVICE_NAME)+"/users";
		
		template.put(uri, u);
		
	}

	@Override
	public void deleteUser(String id) {
		RestTemplate template = new RestTemplate();
		String uri = serviceRegistry.lookupService(MetadataController.SERVICE_NAME)+"/users";
		
		template.delete(uri+"/"+id);
		
	}
	
	private void validateAndTransformUser(User usr) throws UserRegistrationException {
		validatePassword(usr.getPassword());
		validateCommMethod(usr);
		validateUserId(usr);
		validateName(usr.getFirstName(), usr.getLastName());
	}
	
	private void validatePassword (String psswd) throws UserRegistrationException {
		if(psswd == null || psswd.trim().length() == 0) {
			throw new UserRegistrationException("Password must be at least 8 chars long");
		} 
		
	}
	
	private void validateUserId (User u) throws UserRegistrationException {
		if (u.getIdPref() == User.UseridPref.EMAIL) {
			validateEmail(u.getEmail());
			u.setId(u.getEmail());
		} if (u.getIdPref() == User.UseridPref.PHOME) {
			validatePhone(u.getPhone(), u.getCountry());
			u.setId(u.getPhone());
		} else {
			validateUserName(u.getUserName());
			u.setId(u.getUserName());
		}
		
	}
	
	private void validateUserName (String userName) throws UserRegistrationException {
		if(userName == null || userName.trim().length() == 0) {
			throw new UserRegistrationException("Username must be at least 8 chars long");
		} 
		
	}
	
	private void validateCommMethod(User u) throws UserRegistrationException {
		if(u.getPrefContactMethod() == User.CommunicationMethod.EMAIL) {
			validateEmail(u.getEmail());
		} else if (u.getPrefContactMethod() == User.CommunicationMethod.SMS) {
			u.setPhone(validatePhone(u.getPhone(), u.getCountry()));
		}
		
	}
	
	private void validateEmail(String email) throws UserRegistrationException {
		if(email == null || email.trim().length()==0 || !email.contains("@")) {
			throw new UserRegistrationException("Invalid email.");
		} 
	}
	
	private void validateName(String firstName, String lastName) throws UserRegistrationException {
		if(firstName == null || firstName.trim().length()==0 ) {
			throw new UserRegistrationException("Invalid First Name");
		}
		if(lastName == null || lastName.trim().length()==0 ) {
			throw new UserRegistrationException("Invalid First Name");
		}
	}
	
	private String validatePhone(String phone, String country) throws UserRegistrationException {
		if(phone == null || phone.trim().length() < 0 ) {
			throw new UserRegistrationException("Invalid phone.");
		} 
		
		StringBuffer sb = new StringBuffer();
		for (char c: phone.toCharArray()) {
			if (c>=0 && c<=9 ) {
				sb.append(c);
			}
		}
		
		if(sb.length() <10) {
			throw new UserRegistrationException("Invalid phone. please provide 10 digit number.");
		} else if (sb.length() == 10) {
			//country code not specified. assuming phone number should be 10 digit long
			return lookupCountryCode(country)+sb.toString();
		} else {
			return sb.toString();
		}
	}
	
	
	private int lookupCountryCode(String country) {
		//TODO for POC return USA country code
		return 1;
	}

	
}
