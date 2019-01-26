package com.example.demo;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

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

	public List<Incident> second(String start, String end, String type) {
		System.out.println("start date: " + start);
		System.out.println("end date: " + end);
		
		return inDAO.findByType(type);
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

}
