package com.OutOfBounds.Pathfinder.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.OutOfBounds.Pathfinder.exception.UsernameNotUniqueException;
import com.OutOfBounds.Pathfinder.model.ApplicationItem;
import com.OutOfBounds.Pathfinder.model.ApplicationUser;
import com.OutOfBounds.Pathfinder.repository.UserRepostirory;

@Service
public class UserService {
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepostirory userRepo;

	public void signUpNewUser(ApplicationUser user) throws UsernameNotUniqueException {
		Logger log = LoggerFactory.getLogger(UserService.class);
		log.error("sign-up attempt of: {}", user.getUsername());
		if (userRepo.findByUsername(user.getUsername()) != null)
			throw new UsernameNotUniqueException();

		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
	}

	public void addAchievement(String principal, ApplicationItem achievement) {
		ApplicationUser user = getUser(principal);
		user.addAchievement(achievement);
		userRepo.save(user);
	}

	public List<ApplicationItem> getAchievements(String principal) {
		ApplicationUser user = getUser(principal);
		return user.getAchievements();
	}


	public List<ApplicationUser> getHighscore(int highScoreLength) {
		return userRepo.findAll().stream().sorted().limit(highScoreLength).collect(Collectors.toList());
	}

	
	private ApplicationUser getUser(String principal) {
		ApplicationUser user = userRepo.findByUsername(principal);
		if (user == null)
			throw new UsernameNotFoundException("user not found");
		return user;
	}
}
