package com.incidents.web;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incidents.entities.Incident;
import com.incidents.repositories.IncidentDAO;
import com.incidents.services.impl.CitizenImpl;
import com.incidents.services.impl.IncidentImpl;

@RestController
@RequestMapping("incident")
public class IncidentEndpoint {

	@Autowired
	private IncidentDAO inDao;

	@Autowired
	private IncidentImpl service;
	
	@Autowired
	private CitizenImpl citizenImpl;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public Incident Ids(@PathVariable("id") String id) {
		return inDao.findById(id).orElse(null);
	}
	
	@RequestMapping(value = "/getRandom/{count}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Incident> getRandom(@PathVariable("count") long count){
		List<Incident> l = new ArrayList<Incident>();
		
		for (int i=0; i<count; i++) {
			l.add(service.getNextRandomIncident());
		}
		
		return l;
	}
	
	@RequestMapping(value = "/first", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Object> first(@RequestParam("start") String start, @RequestParam("end") String end) {
		return service.first(start, end);
	}
	
	@RequestMapping(value = "/upvote/{id}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public void upvote(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("address") String address, @RequestParam("phone") String phone) throws Exception {
		System.out.println("id" + id);
		System.out.println("name: " + name);
		System.out.println("address: " + address);
		System.out.println("phone: " + phone);
		
		service.upvote(id, citizenImpl.create(name, address, phone));
	}

}
