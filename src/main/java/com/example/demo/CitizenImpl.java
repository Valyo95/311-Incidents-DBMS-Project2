package com.example.demo;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;

@Service
public class CitizenImpl {

	@Autowired
	CitizenDAO citizenDAO;

	@Autowired
	MongoTemplate mongoTemplate;

	private static final int CITIZENS = 100;
	
	@Transactional
	public List<Citizen> init() {
		List<Citizen> l = new ArrayList<Citizen>();
		Faker faker = new Faker();
		for (int i = 0; i < CITIZENS; i++) {
			Citizen citizen = new Citizen(new ObjectId(new Date(), i).toString(), faker.name().fullName(), faker.address().fullAddress(), faker.phoneNumber().phoneNumber());
			l.add(citizen);
			citizenDAO.save(citizen);
		}
		return l;
	}
}
