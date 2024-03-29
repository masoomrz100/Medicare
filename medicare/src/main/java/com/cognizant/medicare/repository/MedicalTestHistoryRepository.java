package com.cognizant.medicare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.medicare.model.MedicalTestHistory;

@Repository
public interface MedicalTestHistoryRepository extends JpaRepository<MedicalTestHistory, Integer> {

	public List<MedicalTestHistory> findByPatientId(String name);
	
	public List<MedicalTestHistory> findByDoctorId(String name);
}
