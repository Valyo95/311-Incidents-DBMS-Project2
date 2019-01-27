package com.incidents.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.incidents.entities.Incident;

public interface IncidentDAO extends MongoRepository<Incident, String>{

	List<Incident> findByCommunityArea(Integer communityArea);

	List<Incident> findByIncId(long incId);
	
	List<Incident> findBySrn(String srn);
	
}
