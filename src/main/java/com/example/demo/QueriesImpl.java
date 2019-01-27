package com.example.demo;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Zip;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;

@Service
public class QueriesImpl {

	@Autowired
	CitizenDAO citizenDAO;

	@Autowired
	IncidentDAO incidentDAO;

	@Autowired
	IncidentImpl incidentService;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	CitizenImpl citizenImpl;

	@Transactional
	public List<Object> first(String start, String end) throws ParseException {
		Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS\'Z\'").parse(start);
		Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS\'Z\'").parse(end);
		Aggregation agg = newAggregation(
				match(Criteria.where("creationDate")
						.gt(startDate)
						.lt(endDate)),
				group("serviceType").count().as("total"), project("total").and("serviceType"),
				sort(Sort.Direction.DESC, "total")

		);
		// Convert the aggregation result into a List
		AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Incident.class, Object.class);
		List<Object> result = groupResults.getMappedResults();
		System.out.println("returned: " + result);
		return result;
	}

	private MatchOperation getMatchOperation(String start, String end) {
		Criteria priceCriteria = Criteria.where("creationDate").gt(start)
				.andOperator(Criteria.where("creationDate").lt(end));
		return match(priceCriteria);

	}
}
