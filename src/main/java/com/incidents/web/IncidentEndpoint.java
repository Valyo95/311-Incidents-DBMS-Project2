package com.incidents.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public Incident create(Principal principal, @RequestParam("status") String status,
			@RequestParam(name="streetAddress", required=false) String streetAddress, @RequestParam(name="zipCode", required=false) String zipCode,
			@RequestParam(name="xCoordinate", required=false) Double xCoordinate, @RequestParam(name="yCoordinate", required=false) Double yCoordinate,
			@RequestParam(name="ward", required=false) Integer ward, @RequestParam(name="policeDistrict", required=false) Integer policeDistrict,
			@RequestParam(name="communityArea", required=false) Integer communityArea, @RequestParam(name="latitude", required=false) Double latitude,
			@RequestParam(name="longitude", required=false) Double longitude, @RequestParam(name="location", required=false) String location,
			@RequestParam(name="licensePlate", required=false) String licensePlate, @RequestParam(name="model", required=false) String model,
			@RequestParam(name="color", required=false) String color, @RequestParam(name="currentActivity", required=false) String currentActivity,
			@RequestParam(name="mostRecentAction", required=false) String mostRecentAction,
			@RequestParam(name="daysAbandoned", required=false) Double daysAbandoned, @RequestParam(name="ssa", required=false) String ssa,
			@RequestParam(name="blackCartsDelivered", required=false) Long blackCartsDelivered, @RequestParam(name="typeOfSurface", required=false) String typeOfSurface,
			@RequestParam(name="located", required=false) String located, @RequestParam(name="potHoles", required=false) Double potHoles,
			@RequestParam(name="premisesBaited", required=false) Double premisesBaited,
			@RequestParam(name="premisesWithGarbage", required=false) Double premisesWithGarbage,
			@RequestParam(name="premisesWithRats", required=false) Double premisesWithRats,
			@RequestParam(name="natureOfViolation", required=false) String natureOfViolation,
			@RequestParam(name="location2", required=false) String location2) {
		
		return service.create(status, streetAddress, zipCode, xCoordinate, yCoordinate, ward, policeDistrict,
				communityArea, latitude, longitude, location, licensePlate, model, color, currentActivity,
				mostRecentAction, daysAbandoned, ssa, blackCartsDelivered, typeOfSurface, located, potHoles,
				premisesBaited, premisesWithGarbage, premisesWithRats, natureOfViolation, location2);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public Incident Ids(@PathVariable("id") String id) {
		return inDao.findById(id).orElse(null);
	}
	
	@RequestMapping(value = "/srn/{srn}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Incident> getBySrn(@PathVariable("srn") String srn) {
		return inDao.findBySrn(srn);
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
