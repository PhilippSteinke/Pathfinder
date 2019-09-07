package com.OutOfBounds.Pathfinder.service;

import static com.OutOfBounds.Pathfinder.security.SecurityConstants.ADMIN_PASSWORD;

import org.springframework.security.access.AccessDeniedException;

public class AdminPasswordValidationService {

	public static void validatePassword(String password) throws AccessDeniedException {
		if (!password.equals(ADMIN_PASSWORD)) {
			throw new AccessDeniedException("YOU SHALL NOT PASS!");
		}
	}
}
