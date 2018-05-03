package com.ayush.UserReg.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.UserReg.service.CommunicationService;

@RestController
@RequestMapping(value="/v1/communication")
public class CommunicationController {

	public static final String SERVICE_NAME = "COMMUNICATION_SERVICE";
	
	private CommunicationService commService;
	
	@Autowired
	public CommunicationController(CommunicationService service) {
		this.commService = service;
	}
	
	@RequestMapping(value="/message/{userId}", method=RequestMethod.POST)
	public @ResponseBody String sendMessage(@PathVariable String userId) {
		
		return commService.sendMessage(userId);
		
	}
	
}
