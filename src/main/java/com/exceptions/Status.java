package com.exceptions;

public class Status {
	public final static String NOT_FOUND_ERROR =		
			  "{"
			+ "\"status:\""+404+","
			+ "\"message:\": \"NOT_FOUND_ERROR\""
			+ "}";
	public final static String BAD_REQUEST_ERROR =		
			  "{"
			+ "\"status:\""+400+","
			+ "\"message:\": \"BAD_REQUEST_ERROR\""
			+ "}";
	public final static String  INTERNAL_SERVER_ERROR =
			  "{"
			+ "\"status:\""+500+","
			+ "\"message:\": \"INTERNAL_SERVER_ERROR\""
			+ "}";
	public final static String CONFLICT_ERROR =		
			  "{"
			+ "\"status:\""+409+","
			+ "\"message:\": \"CONFLICT_ERROR\""
			+ "}";
}
