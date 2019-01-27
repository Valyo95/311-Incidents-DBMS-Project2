package com.incidents.web;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incidents.services.impl.QueriesImpl;



@RestController
@RequestMapping("query")
public class QueriesEndpoint {

	@Autowired
	private QueriesImpl service;
		
	
	@RequestMapping(value = "/1", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> first(@RequestParam("start") String start, @RequestParam("end") String end) throws ParseException{
		return service.first(start, end);
	}
	
	@RequestMapping(value = "/2", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> second(@RequestParam("type") String type, @RequestParam("start") String start, @RequestParam("end") String end) throws ParseException{
		return service.second(type, start, end);
	}
	
	@RequestMapping(value = "/3", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> second(@RequestParam("day") String day) throws ParseException{
		return service.third(day);
	}
	
	@RequestMapping(value = "/4", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> forth(@RequestParam("type") String type) throws ParseException{
		return service.forth(type);
	}
	
	@RequestMapping(value = "/5", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> fifth(@RequestParam("start") String start, @RequestParam("end") String end) throws ParseException{
		return service.fifth(start, end);
	}
	
	@RequestMapping(value = "/7", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> seventh(@RequestParam("day") String day){
		try {
			return service.seventh(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
	
	@RequestMapping(value = "/8", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> eight() throws ParseException{
		return service.eight();
	}
	
	@RequestMapping(value = "/9", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> nine() throws ParseException{
		return service.nine();
	}
	
	@RequestMapping(value = "/10", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> ten() throws ParseException{
		return service.ten();
	}
	
	@RequestMapping(value = "/11", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> eleven(@RequestParam("name") String name) throws ParseException{
		return service.eleven(name);
	}
}
