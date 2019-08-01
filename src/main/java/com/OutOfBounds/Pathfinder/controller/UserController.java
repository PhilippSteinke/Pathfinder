package com.OutOfBounds.Pathfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OutOfBounds.Pathfinder.exception.UsernameNotUniqueException;
import com.OutOfBounds.Pathfinder.model.ApplicationItem;
import com.OutOfBounds.Pathfinder.model.ApplicationUser;
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

	@PostMapping("/addAchievementItem")
	public void add(@AuthenticationPrincipal String principal, @RequestBody ApplicationItem achievement) {
		userService.addAchievement(principal, achievement);
	}

	@PostMapping("/achievementList")
	public List<ApplicationItem> getAchievements(@AuthenticationPrincipal String principal) {
		return userService.getAchievements(principal);
	}

	@GetMapping("/highscore")
	public List<ApplicationUser> getHighscore() {
		return userService.getHighscore(10);
	}
}
