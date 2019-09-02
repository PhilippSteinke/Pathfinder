package com.OutOfBounds.Pathfinder.controller;

import static com.OutOfBounds.Pathfinder.security.SecurityConstants.ADMIN_PASSWORD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OutOfBounds.Pathfinder.model.PointOfInterest;
import com.OutOfBounds.Pathfinder.service.PointOfInterestService;

@RestController
@RequestMapping("/pointsofinterest")
public class PointsOfInterestController {

	@Autowired
	private PointOfInterestService service;

	@GetMapping("/all")
	public List<PointOfInterest> getAll() {
		return service.getAll();
	}

	@PostMapping("/add")
	public void addAll(@RequestParam String password,
			@RequestBody List<PointOfInterest> pointsOfInterest) throws AccessDeniedException {
		if (password.equals(ADMIN_PASSWORD)) {
			service.addAll(pointsOfInterest);
		} else {
			throw new AccessDeniedException("YOU SHALL NOT PASS!");
		}
	}

	@DeleteMapping("/delete-all")
	public void deleteAll(@RequestParam String password) throws AccessDeniedException {
		if (password.equals(ADMIN_PASSWORD)) {
			service.deleteAll();
		} else {
			throw new AccessDeniedException("YOU SHALL NOT PASS!");
		}
	}
}
