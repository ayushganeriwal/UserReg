package com.ayush.UserReg.resource;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.UserReg.dto.User;
import com.ayush.UserReg.exception.UserRegistrationException;
import com.ayush.UserReg.service.MetadataService;

@RestController
@RequestMapping(value="/v1/metadata")
public class MetadataController {
	
	public static final String SERVICE_NAME = "METADATA_SERVICE";

	MetadataService metadataSvc;
	
	@Autowired
	public MetadataController(MetadataService ms) {
		this.metadataSvc = ms;
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public @ResponseBody String createUser(@RequestBody User usr) {
		return metadataSvc.createTempUser(usr);
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public @ResponseBody Collection<User> getUsers() throws UserRegistrationException{
		return metadataSvc.getUserList();
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable String id) throws UserRegistrationException{
		return metadataSvc.getUser(id);
	}
	
	@RequestMapping(value="/users/tempusers/{id}", method=RequestMethod.GET)
	public @ResponseBody User getTempUser(@PathVariable String id) throws UserRegistrationException{
		return metadataSvc.getUser(id);
	}
	
	@RequestMapping(value="/users/tempusers/clean", method=RequestMethod.POST)
	public void cleanupTempUsers(@RequestParam long time) throws UserRegistrationException{
		 metadataSvc.clearTempUsers(time);
	}
	
	@RequestMapping(value="/users/{id}/activate", method=RequestMethod.POST)
	public void activateUser(@PathVariable String id) throws UserRegistrationException {
		metadataSvc.activateTempUser(id);
	}
}
