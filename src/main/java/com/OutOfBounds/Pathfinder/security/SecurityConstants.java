package com.OutOfBounds.Pathfinder.security;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/user/sign-up";
	public static final String USER_DELETE_URL = "/user/delete/*";
	public static final String POINT_OF_INTEREST_ALL_URL = "/pointsofinterest/all";
	public static final String POINT_OF_INTEREST_ADD_URL = "/pointsofinterest/add";
	public static final String POINT_OF_INTEREST_DELETE_URL = "/pointsofinterest/delete/*";
	public static final String ADMIN_PASSWORD = "brilliant";
}
