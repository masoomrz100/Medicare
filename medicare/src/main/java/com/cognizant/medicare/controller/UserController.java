package com.cognizant.medicare.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.medicare.exception.AdminAlreadyExistsException;
import com.cognizant.medicare.exception.DoctorAlreadyExistsException;
import com.cognizant.medicare.exception.PatientAlreadyExistsException;

import com.cognizant.medicare.model.Admin;
import com.cognizant.medicare.model.Doctor;
import com.cognizant.medicare.model.Patient;

import com.cognizant.medicare.security.AppUserDetailsService;
import com.cognizant.medicare.services.UserDetailsService;

@RestController
@RequestMapping("/users")
public class UserController {

	/*@Autowired
	InMemoryUserDetailsManager inMemoryUserDetailsManager; */
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@GetMapping
	public List<Admin> getAdminList() {
		return userDetailsService.getAdminList();
	}

	@PostMapping("/admin")
	public ResponseEntity<?> AdminSignUp(@RequestBody @Valid Admin admin) throws AdminAlreadyExistsException{
/*		return null;
		System.out.println("Inside User Signup");
		if (appUserDetailsService.userExists(user.getUsername())) {
			return new ResponseEntity<String>(user.getUsername()+" Already Exists", HttpStatus.BAD_REQUEST);
		} else {
			inMemoryUserDetailsManager.createUser(User.withUsername(user.getUsername())
					.password(new BCryptPasswordEncoder().encode(user.getPassword())).roles("USER").build());
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}  
	}  */
			if(userDetailsService.AdminSignUp(admin)) {
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>(admin.getAdminId()+" Already Exists", HttpStatus.BAD_REQUEST);
			}
	}	
	
	@PostMapping("/doctor")
	public ResponseEntity<?> DoctorSignUp(@RequestBody @Valid Doctor doctor) throws DoctorAlreadyExistsException{

		System.out.println("inside doctor controller");
			if(userDetailsService.DoctorSignUp(doctor)) {
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>(doctor.getDoctorId()+" Already Exists", HttpStatus.BAD_REQUEST);
			}
	}
	
	@PostMapping("/patient")
	public ResponseEntity<?> PatientSignUp(@RequestBody @Valid Patient patient) throws PatientAlreadyExistsException {

			if(userDetailsService.PatientSignUp(patient)) {
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>(patient.getPatientId()+" Already Exists", HttpStatus.BAD_REQUEST);
			}
	}
	
}
