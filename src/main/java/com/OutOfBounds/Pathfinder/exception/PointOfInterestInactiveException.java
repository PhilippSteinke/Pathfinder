package com.OutOfBounds.Pathfinder.exception;

@SuppressWarnings("serial")
public class PointOfInterestInactiveException extends Exception {
	public PointOfInterestInactiveException(String id) {
		super(String.format("PointOfInterest with id %s was already completed!", id));
	}
}
