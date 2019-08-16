package com.OutOfBounds.Pathfinder.model;

import org.springframework.data.annotation.Id;

public class PointOfInterest {

	@Id
	private String id;
	
	private final float lat, lng;
	
	public PointOfInterest(float lat, float lng) {
		this.lat=lat;
		this.lng = lng;
	}

	public float getLat() {
		return lat;
	}

	public float getLng() {
		return lng;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
		
	
	
	
	
	
}
