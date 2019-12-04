package com.cognizant.medicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.medicare.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	public Patient findByPatientId(String name);
	
	/*@Query("FROM PATIENT p WHERE p.patientId = ?1")
	public Boolean getPatient(String username);  */
}
