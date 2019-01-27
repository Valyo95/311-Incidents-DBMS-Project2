package com.incidents.services.impl;

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
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.AggregationSpELExpression;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Zip;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;
import com.incidents.entities.Incident;
import com.incidents.repositories.CitizenDAO;
import com.incidents.repositories.IncidentDAO;
import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.model.Accumulators;

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
		Aggregation agg = newAggregation(
				match(Criteria.where("creationDate")
						.gt(LocalDateTime.parse(start))
						.lt(LocalDateTime.parse(end))),
				group("serviceType").count().as("total"),
				project("total").and("serviceType").as("serviceType"),
				sort(Sort.Direction.DESC, "total")

		);
		// Convert the aggregation result into a List
		AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Incident.class, Object.class);
		List<Object> result = groupResults.getMappedResults();
		System.out.println("returned: " + result);
		return result;
	}
	
	@Transactional
	public List<Object> second(String serviceType, String start, String end) throws ParseException {
		Aggregation agg = newAggregation(
				match(Criteria.where("creationDate")
						.gt(LocalDateTime.parse(start))
						.lt(LocalDateTime.parse(end))
						.and("serviceType").is(serviceType)),
				group("creationDate").count().as("total"),
				project("total").and("creationDate").as("date"),
				sort(Sort.Direction.DESC, "total")

		);
		// Convert the aggregation result into a List
		AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Incident.class, Object.class);
		List<Object> result = groupResults.getMappedResults();
		System.out.println("returned: " + result);
		return result;
	}

	@Transactional
	public List<Object> third(String day) throws ParseException {
		Aggregation agg = newAggregation(
				match(Criteria.where("creationDate")
						.is(LocalDateTime.parse(day))),
				group("zipCode", "serviceType").count().as("totalRequestsPerType"),
				sort(Sort.Direction.ASC, "_id.zipCode").and(Sort.Direction.DESC, "totalRequestsPerType"),
				group("_id.zipCode").push(new BasicDBObject("serviceType", "_id.serviceType")
		            ).as("zipRequestTypes"),
				project("_id").and("zipRequestTypes").slice(3).as("topThreeTypes")

		);
		// Convert the aggregation result into a List
		AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Incident.class, Object.class);
		List<Object> result = groupResults.getMappedResults();
		System.out.println("returned: " + result);
		return result;
	}
	
	@Transactional
	public List<Object> forth(String serviceType) throws ParseException {
		Aggregation agg = newAggregation(
				match(Criteria.where("serviceType").is(serviceType)
						.and("ward").ne(null).ne(""	)),
				group("ward").count().as("total"),
				sort(Sort.Direction.ASC, "total"),
				Aggregation.limit(3),
				project("total").andInclude("_id")
		);
		// Convert the aggregation result into a List
		AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Incident.class, Object.class);
		List<Object> result = groupResults.getMappedResults();
		System.out.println("returned: " + result);
		return result;
	}
	
	@Transactional
	public List<Object> fifth(String start, String end) throws ParseException {
		Aggregation agg = newAggregation(
				match(Criteria.where("creationDate")
						.ne(null)
						.gt(LocalDateTime.parse(start))
						.lt(LocalDateTime.parse(end))
						.and("completionDate").ne(null)),
				project("serviceType").and("$completionDate").minus("creationDate").as("duration"),
				group("serviceType").avg("duration").as("avg"),
				project("avg").andInclude("_id")	
			    

		);
		// Convert the aggregation result into a List	
		AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Incident.class, Object.class);
		List<Object> result = groupResults.getMappedResults();
		System.out.println("returned: " + result);
		return result;
	}

	@Transactional
	public List<Object> seventh(String day) throws ParseException {
		Aggregation agg = newAggregation(
				match(Criteria.where("creationDate")
						.is(LocalDateTime.parse(day))),
						project("upvotes").and("upvotes").size().as("upvotesCount"),
				sort(Sort.Direction.DESC, "upvotesCount"),
				Aggregation.limit(50)

		);
		// Convert the aggregation result into a List
		AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Incident.class, Object.class);
		List<Object> result = groupResults.getMappedResults();
		System.out.println("returned: " + result);
		return result;
	}

	@Transactional
	public List<Object> eight() throws ParseException {
		System.out.println("erroroorsoads");
		Aggregation agg = newAggregation(
				Aggregation.unwind("upvotes"),
				project().and("upvotes").size().as("upvotesCount"),
				group("upvotes.name").count().as("userUpvotes"),
				sort(Sort.Direction.DESC, "userUpvotes"),
				Aggregation.limit(50),
				project("id")
//
		);
		// Convert the aggregation result into a List
		AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Incident.class, Object.class);
		List<Object> result = groupResults.getMappedResults();
		System.out.println("returned: " + result);
		return result;
	}
	
}
