package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncidentEndpoint {

	@Autowired
	private IncidentDAO inDao;

	@RequestMapping(value = "/view", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public List<Incident> create(@RequestParam(name="communityArea") Integer communityArea) {
		System.out.println("communityArea: " + communityArea);
		List<Incident> s = inDao.findByCommunityArea(communityArea);
		System.out.println(s.size());
/*		for (Incident incident : s) {
			System.out.println(s.toString());
		}
*/		System.out.println(s.size());
		return s;
	}
}
