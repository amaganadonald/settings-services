package com.amagana.settingsservice.exceptions;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1194827914545006497L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
