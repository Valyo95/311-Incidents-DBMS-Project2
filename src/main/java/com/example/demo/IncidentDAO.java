package com.example.demo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IncidentDAO extends MongoRepository<Incident, String>{

	List<Incident> findByCommunityArea(Integer communityArea);

	List<Incident> findByIncId(long incId);
	
	List<Incident> findByCreatedAtBetweenAndType(String d1, String d2, String type);	

}
