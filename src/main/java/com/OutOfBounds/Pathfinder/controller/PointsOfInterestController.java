package com.OutOfBounds.Pathfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OutOfBounds.Pathfinder.model.PointOfInterest;
import com.OutOfBounds.Pathfinder.service.PointOfInterestService;

@RestController
@RequestMapping("/PointsOfInterest")
public class PointsOfInterestController {
	
	@Autowired
	private PointOfInterestService service;
	
	
	@GetMapping(value="/all")
	public List<PointOfInterest> getAll(){
		return this.service.getAll();
	}
	
	//TODO remove those or add more specific authorization requirements - just for testing 
	@PostMapping(value="/add")
	public void add(@RequestBody PointOfInterest pointOfInterest) {
		this.service.add(pointOfInterest);
	}
	
	@PostMapping(value="/addAll")
	public void addAll(@RequestBody List<PointOfInterest> pointsOfInterest) {
		this.service.addAll(pointsOfInterest);
	}
}
