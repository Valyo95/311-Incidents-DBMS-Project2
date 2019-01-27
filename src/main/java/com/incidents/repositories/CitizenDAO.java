package com.incidents.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.incidents.entities.Citizen;
import com.incidents.entities.Incident;

public interface CitizenDAO extends MongoRepository<Citizen, String>{

	List<Incident> findByName();
	List<Incident> findByTelephone();
	List<Incident> findByAddress();
	
}
