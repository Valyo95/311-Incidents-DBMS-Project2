package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("citizen")
public class CitizenEndpoint {

	@Autowired
	private CitizenDAO citizenDAO;
		
	@Autowired
	private CitizenImpl service;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Citizen> init(){
		return service.init();
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Citizen> view(){
		System.out.println("here");
		return citizenDAO.findAll();
	}
	
}
