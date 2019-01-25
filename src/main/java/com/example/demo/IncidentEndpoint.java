package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class IncidentEndpoint {

	@Autowired
	private IncidentDAO inDao;
	
	@Autowired
	private IncidentImpl service;
	
	@RequestMapping(value = "/first", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> first(@RequestParam("start") String start, @RequestParam("end") String end){
			return service.first(start, end);
	}
	
	@RequestMapping(value = "/second", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Incident> second(@RequestParam("start") String start, @RequestParam("end") String end, @RequestParam("type") String type){
		
		List<Incident> l = service.second(start, end, type);
		System.out.println("Size: " + l.size());
		return l;
	}
	
	@RequestMapping(value = "/type", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Incident> type(@RequestParam("start") String start, @RequestParam("end") String end, @RequestParam("type") String type){
		System.out.println("type: " + type);
		List<Incident> l = service.second(start, end, type);
		System.out.println("Size: " + l.size());
		return l;
	}
}
