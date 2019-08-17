package com.OutOfBounds.Pathfinder.security;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/user/sign-up";
	public static final String POINT_OF_INTEREST_ALL_URL = "/pointofinterest/all";
	public static final String POINT_OF_INTEREST_ADD_URL = "/pointofinterest/add";
	public static final String POINT_OF_INTEREST_PASSWORD = "brilliant";
}
