package com.OutOfBounds.Pathfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OutOfBounds.Pathfinder.exception.EntityNotFoundException;
import com.OutOfBounds.Pathfinder.exception.UsernameNotUniqueException;
import com.OutOfBounds.Pathfinder.model.Achievement;
import com.OutOfBounds.Pathfinder.model.ApplicationUser;
import com.OutOfBounds.Pathfinder.model.Highscore;
import com.OutOfBounds.Pathfinder.model.PointOfInterest;
import com.OutOfBounds.Pathfinder.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user) throws UsernameNotUniqueException {
		userService.signUpNewUser(user);
	}

	@PostMapping("/add-achievement")
	public void add(@AuthenticationPrincipal String principal, @RequestBody Achievement achievement)
			throws EntityNotFoundException {
		userService.addAchievement(principal, achievement);
	}

	@GetMapping("/achievements")
	public List<Achievement> getAchievements(@AuthenticationPrincipal String principal) {
		return userService.getAchievements(principal);
	}

	@GetMapping("/highscore")
	public Highscore getHighscore(@AuthenticationPrincipal String principal) {
		return userService.getHighscore(principal);
	}

	@GetMapping("/pointofinterests")
	public List<PointOfInterest> getPointOfInterests(@AuthenticationPrincipal String principal) {
		return userService.getPointOfInterests(principal);
	}
}
