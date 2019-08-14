package com.OutOfBounds.Pathfinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OutOfBounds.Pathfinder.model.PointOfInterest;
import com.OutOfBounds.Pathfinder.repository.PointOfInterestRepository;

@Service
public class PointOfInterestService {

	@Autowired
	private PointOfInterestRepository repo;

	public List<PointOfInterest> getAll() {
		return this.repo.findAll();
	}

	public void add(PointOfInterest pointOfInterest) {
		this.repo.save(pointOfInterest);
	}

	public void addAll(List<PointOfInterest> pointsOfInterest) {
		this.repo.saveAll(pointsOfInterest);
	}

}
