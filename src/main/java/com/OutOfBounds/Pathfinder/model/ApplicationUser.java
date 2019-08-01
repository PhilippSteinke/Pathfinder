package com.OutOfBounds.Pathfinder.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;


public class ApplicationUser implements Comparable<ApplicationUser> {
	@Id
	private String id;
	private String username;
	private String password;

	private List<ApplicationItem> achievements;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ApplicationItem> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<ApplicationItem> achievements) {
		this.achievements = achievements;
	}

	public void addAchievement(ApplicationItem achievement) {
		if (this.achievements == null)
			this.achievements = new ArrayList<>();
		this.achievements.add(achievement);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getScore() {
		return achievements.stream().map(a -> a.getValue()).reduce(0, Integer::sum);
	}

	@Override
	public int compareTo(ApplicationUser o) {
		return this.getScore() - o.getScore();
	}
}
