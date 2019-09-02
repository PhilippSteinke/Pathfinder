package com.OutOfBounds.Pathfinder.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OutOfBounds.Pathfinder.model.PointOfInterest;
import com.OutOfBounds.Pathfinder.repository.PointOfInterestRepository;

@Service
public class PointOfInterestService {

	@Autowired
	private PointOfInterestRepository repo;

	public List<PointOfInterest> getAll() {
		return repo.findAll();
	}

	public void addAll(List<PointOfInterest> pointsOfInterest) {
		Logger log = LoggerFactory.getLogger(PointOfInterestService.class);
		log.info("added {} new PointOfInterest", pointsOfInterest.size());
		repo.saveAll(pointsOfInterest);
	}

	public void deleteAll() {
		repo.deleteAll();
	}

	public void deleteById(String id) {
		repo.deleteById(id);
	}
}
