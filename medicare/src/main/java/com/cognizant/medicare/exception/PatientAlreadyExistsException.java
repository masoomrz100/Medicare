package com.cognizant.medicare.exception;

public class PatientAlreadyExistsException extends Exception {
	
	public PatientAlreadyExistsException() {
		System.out.println("Patient already exists");
	}
}
