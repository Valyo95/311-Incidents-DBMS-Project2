package com.incidents.services.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.incidents.entities.Citizen;
import com.incidents.entities.Incident;
import com.incidents.repositories.IncidentDAO;

@Service
public class IncidentImpl {

	@Autowired
	IncidentDAO inDAO;

	@Autowired
	MongoTemplate mongoTemplate;
	
	private static long tempIncidentCount = -1;
	public static Random randomGen = new Random();

	public List<Object> first(String start, String end) {
		System.out.println("start date: " + start);
		System.out.println("end date: " + end);
		Aggregation agg = newAggregation(
				match(Criteria.where("CREATION DATE").gt(start).andOperator(Criteria.where("CREATION DATE").lt(end))),
				group("type").count().as("total"), project("total").and("TYPE OF SERVICE REQUEST").previousOperation(),
				sort(Sort.Direction.DESC, "total"));

		// Convert the aggregation result into a List
		AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Incident.class, Object.class);

		List<Object> result = groupResults.getMappedResults();
		return result;
	}

	
	public Incident getByOrderNum(long orderNum) {
		Aggregation agg = newAggregation(match(Criteria.where("incId").is(orderNum)));
		
		AggregationResults<Incident> groupResults = mongoTemplate.aggregate(agg, Incident.class, Incident.class);
		
		List<Incident> incidents = groupResults.getMappedResults();
		
		return incidents.isEmpty() ? null : incidents.get(0);
	}
	
	public Incident getNextRandomIncident() {
		if (tempIncidentCount == -1) {
			tempIncidentCount = inDAO.count();
		}
		
		int orderNum = randomGen.nextInt((int) tempIncidentCount);
		return getByOrderNum(orderNum);
	}
	
	@Transactional
	public void upvote(String id, Citizen citizen) throws Exception {
		Incident i = inDAO.findById(id).orElse(null);
		if(i == null)
			throw new Exception("No such incident");
		if(i.getUpvotes().contains(citizen))
			throw new Exception("Citizen" + citizen + "already upvoted this incident");
		
		i.getUpvotes().add(citizen);
		inDAO.save(i);
	}


	@Transactional
	public Incident create(String status, String streetAddress, String zipCode, Double xCoordinate, Double yCoordinate,
			Integer ward, Integer policeDistrict, Integer communityArea, Double latitude, Double longitude,
			String location, String licensePlate, String model, String color, String currentActivity,
			String mostRecentAction, Double daysAbandoned, String ssa, Long blackCartsDelivered, String typeOfSurface,
			String located, Double potHoles, Double premisesBaited, Double premisesWithGarbage, Double premisesWithRats,
			String natureOfViolation, String location2) {
		
		Incident incident = new Incident();
		incident.setSrn(UUID.randomUUID().toString());
		incident.setStatus(status);
		incident.setStreetAddress(streetAddress);
		incident.setZipCode(zipCode);
		incident.setxCoordinate(xCoordinate);
		incident.setyCoordinate(yCoordinate);
		incident.setWard(ward);
		incident.setPoliceDistrict(policeDistrict);
		incident.setCommunityArea(communityArea);
		incident.setLatitude(latitude);
		incident.setLongitude(longitude);
		incident.setLocation(location);
		incident.setLicensePlate(licensePlate);
		incident.setModel(model);
		incident.setColor(color);
		incident.setCurrentActivity(currentActivity);
		incident.setMostRecentAction(mostRecentAction);
		incident.setDaysAbandoned(daysAbandoned);
		incident.setSsa(ssa);
		incident.setBlackCartsDelivered(blackCartsDelivered);
		incident.setTypeOfSurface(typeOfSurface);
		incident.setLocated(located);
		incident.setPotHoles(potHoles);
		incident.setPremisesBaited(premisesBaited);
		incident.setNatureOfViolation(natureOfViolation);
		incident.setLocation2(location2);
		
		inDAO.save(incident);
		
		return incident;
	}

}
