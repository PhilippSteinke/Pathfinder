package com.OutOfBounds.Pathfinder.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.OutOfBounds.Pathfinder.exception.EntityNotFoundException;
import com.OutOfBounds.Pathfinder.exception.PointOfInterestInactiveException;
import com.OutOfBounds.Pathfinder.exception.UsernameNotUniqueException;
import com.OutOfBounds.Pathfinder.model.Achievement;
import com.OutOfBounds.Pathfinder.model.ApplicationUser;
import com.OutOfBounds.Pathfinder.model.Highscore;
import com.OutOfBounds.Pathfinder.model.PointOfInterest;
import com.OutOfBounds.Pathfinder.model.PointOfInterestDistance;
import com.OutOfBounds.Pathfinder.repository.UserRepostirory;

@Service
public class UserService {
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepostirory userRepo;

	@Autowired
	private HighscoreService highscoreService;

	@Autowired
	private PointOfInterestService pointOfInterestService;

	@PostConstruct
	public void init() {
		updateHighscore();
	}

	public void signUpNewUser(ApplicationUser user) throws UsernameNotUniqueException {
		Logger log = LoggerFactory.getLogger(UserService.class);
		log.info("sign-up attempt of: {}", user.getUsername());

		if (userRepo.findByUsername(user.getUsername()).isPresent()) {
			throw new UsernameNotUniqueException(user.getUsername());
		}
		user.setPassword(encoder.encode(user.getPassword()));
		user.setPointOfInterests(pointOfInterestService.getAll());
		userRepo.save(user);
		updateHighscore();
	}

	public List<PointOfInterest> completePointOfInterest(String principal, Achievement achievement)
			throws EntityNotFoundException, PointOfInterestInactiveException {
		ApplicationUser user = getUser(principal);
		String pointOfInterestId = achievement.getPointOfInterestId();

		if (user.isPointOfInterestActive(pointOfInterestId)) {
			user.addAchievement(achievement);
			user.setPointOfInterestInactive(pointOfInterestId);
			userRepo.save(user);
			updateHighscore();
			return user.getPointOfInterests();
		} else {
			throw new PointOfInterestInactiveException(pointOfInterestId);
		}
	}

	public List<Achievement> getAchievements(String principal) {
		return getUser(principal).getAchievements();
	}

	public Highscore getHighscore(String principal) {
		return highscoreService.getHighscoreByUsername(getUser(principal).getUsername());
	}

	public List<PointOfInterest> getPointsOfInterest(String principal) {
		return getUser(principal).getPointOfInterests();
	}

	public PointOfInterestDistance getClosestPointOfInterest(String principal,
			BigDecimal userLat,
			BigDecimal userLng) {
		return getPointsOfInterest(principal).stream().filter(p -> p.isActive())
				.map(p -> new PointOfInterestDistance(p.getId(),
						calculateDistance(userLat.doubleValue(),
								userLng.doubleValue(),
								p.getLat().doubleValue(),
								p.getLng().doubleValue())))
				.min(Comparator.naturalOrder()).get();
	}

	public void deleteAllUsers() {
		userRepo.deleteAll();
		updateHighscore();
	}

	public void deleteByUsername(String username) {
		userRepo.deleteById(getUser(username).getId());
		updateHighscore();
	}

	public boolean isUserAuthenticationValid(String username) {
		return userRepo.findByUsername(username).isPresent();
	}

	private double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.abs(Math.hypot(x1 - x2, y1 - y2));
	}

	private ApplicationUser getUser(String principal) {
		ApplicationUser user = userRepo.findByUsername(principal)
				.orElseThrow(() -> new AccessDeniedException("Token not valid!"));
		return user;
	}

	private void updateHighscore() {
		highscoreService.updateBuffer(userRepo.findAll());
	}
}
