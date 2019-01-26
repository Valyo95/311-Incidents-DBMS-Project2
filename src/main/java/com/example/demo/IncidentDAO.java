package com.example.demo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IncidentDAO extends MongoRepository<Incident, String>{

	@Query(value="{'_id' : {'$oid' : '5c4b1bd88fdbd23cac3abf76'}}")
	List<Incident> findLala();
	
	@Query(value="{'_id' : ?0}}")
	List<Incident> findLala(ObjectId id);
	
	@Query("{'_id' : {'$oid' : '5c4b1bd88fdbd23cac3abf76'}}, {_id:1}")
	Object findTest();
	
	List<Incident> findByCommunityArea(Integer communityArea);
	
	List<Incident> findByCreatedAtBetweenAndType(String d1, String d2, String type);
	
	
	@Query("{ 'TYPE OF SERVICE REQUEST' : ?0 }")
	List<Incident> findByType(String type);
	
//	@Query("{'CREATION DATE' : { '$gte' : ISODate('2008-01-01T00:00:00.000+0000'), '$lte' : ISODate('2009-04-01T00:00:00.000+0000')}")
	

}
