package com.example.demo;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Zip;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;

@Service
public class CitizenImpl {

	@Autowired
	CitizenDAO citizenDAO;

	@Autowired
	IncidentDAO incidentDAO;

	@Autowired
	IncidentImpl incidentService;

	@Autowired
	MongoTemplate mongoTemplate;

	private static final int CITIZENS = 2000;

	public Citizen create(String name, String address, String phone) {
		Citizen c = new Citizen(name, address, phone);
		return c;
	}
	
	@Transactional
	public List<Citizen> init() {
		HashSet<String> incidentUpvoteSet = new HashSet<String>();
		HashMap<String, Integer> citizenUpvotes = new HashMap<String, Integer>();
		
		List<Citizen> l = citizenDAO.findAll();

		Faker faker = new Faker();
		for (int i = 0; i < CITIZENS; i++) {
			Citizen citizen = new Citizen(faker.name().fullName(),
					faker.address().fullAddress(), faker.phoneNumber().phoneNumber());
			l.add(citizen);
			citizenUpvotes.put(citizen.getName(), 0);
		}

		long size = incidentDAO.count();
		long upvotedIncidents = 0;
		long target = (long) (size / 3);
		/*
		 * System.out.println("size: " + size); System.out.println("target: " + target);
		 */

		while (upvotedIncidents <= target) {
			for (Citizen citizen : l) {
				System.out.println(citizen);
				
				if (citizenUpvotes.get(citizen.getName()) <= 1000) {
					Incident incident = incidentService.getNextRandomIncident();
					System.out.println(incident);

					if(!incident.getUpvotes().contains(citizen)) {
						incident.getUpvotes().add(citizen);
						/*
						 * System.out.println(incident); System.out.println("upvotedIncidents: " +
						 * upvotedIncidents);
						 */
						if(!incidentUpvoteSet.contains(incident.getId()))
						{
							incidentUpvoteSet.add(incident.getId());
							upvotedIncidents++;
						}
						incidentDAO.save(incident);
					}
				}	
				if(upvotedIncidents > target)
					break;
			}
		}
		System.err.println("Byebye");
		return l;

	}

}
