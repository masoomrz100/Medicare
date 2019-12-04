package com.cognizant.medicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.medicare.model.MedicareService;

@Repository
public interface MedicareServiceRepository extends JpaRepository<MedicareService, Integer>{
	
	public MedicareService findByMedicareService(String medicareServiceName);
	
}
