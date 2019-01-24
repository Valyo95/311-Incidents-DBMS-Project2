package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IncidentDAO extends MongoRepository<Incident, Integer>{

	
	@Query("{ 'ZIP' : 60629 }")
	List<Incident> findByBy();
	
	List<Incident> findByCommunityArea(Integer communityArea);
	
}
