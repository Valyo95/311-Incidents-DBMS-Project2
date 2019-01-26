package com.example.demo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CitizenDAO extends MongoRepository<Citizen, String>{

	List<Incident> findByName();
	List<Incident> findByTelephone();
	List<Incident> findByAddress();
	
}
