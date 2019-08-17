package com.OutOfBounds.Pathfinder.model;

public class Highscore implements Comparable<Highscore> {

	private int position;

	private String username;

	private Integer score;

	public Highscore() {
	}

	public Highscore(String username, Integer score) {
		setUsername(username);
		setScore(score);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public int compareTo(Highscore o) {
		return score.compareTo(o.getScore());
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
