package com.ayush.UserReg.resource;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.UserReg.dto.User;
import com.ayush.UserReg.exception.UserRegistrationException;
import com.ayush.UserReg.service.UserRegService;

@RestController
@RequestMapping(value="/v1/users")
public class UserController {

	public static final String SERVICE_NAME = "USER_REG_SERVICE";
	
	
	private UserRegService userSvc;
	
	@Autowired
	public UserController(UserRegService userSvc) {
		this.userSvc = userSvc;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/register")
	public String createUser(@RequestBody User user) throws UserRegistrationException {
		return userSvc.createUser(user);
	}
	
	@RequestMapping(value="/{id}/activate", method=RequestMethod.PUT)
	public User activateUser(@PathVariable String id){
		return userSvc.activateUser(id);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<User> getUsers(){
		return userSvc.getUsers(); 
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable String id){
		return userSvc.getUser(id); 
	}
	
}
