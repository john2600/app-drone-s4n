package com.s4n.drone.exceptions;

public class DroneException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DroneException(String errorMessage) {
		super(errorMessage);
	}

}
