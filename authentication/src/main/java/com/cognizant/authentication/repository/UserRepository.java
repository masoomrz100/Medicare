package com.cognizant.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.authentication.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	 public User findByUsername(String name);
}
