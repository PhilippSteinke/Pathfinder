package com.OutOfBounds.Pathfinder.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	public void testInit() {
		addTestingPointOfInterests();
	}

	public List<PointOfInterest> getAll() {
		return repo.findAll();
	}

	public void addAll(List<PointOfInterest> pointsOfInterest) {
		Logger log = LoggerFactory.getLogger(PointOfInterestService.class);
		log.info("added {} new PointOfInterest", pointsOfInterest.size());
		repo.saveAll(pointsOfInterest);
	}

	private void addTestingPointOfInterests() {
		if (getAll().size() == 0) {
			addAll(Arrays.asList(new PointOfInterest(1.2f, 2.4f), new PointOfInterest(4.5f, 3.1f)));
		}
	}
}
