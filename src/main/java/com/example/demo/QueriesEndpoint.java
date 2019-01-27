package com.example.demo;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("query")
public class QueriesEndpoint {

	@Autowired
	private QueriesImpl service;
		
	
	@RequestMapping(value = "/1", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> init(@RequestParam("start") String start, @RequestParam("end") String end) throws ParseException{
		return service.first(start, end);
	}
	
}
