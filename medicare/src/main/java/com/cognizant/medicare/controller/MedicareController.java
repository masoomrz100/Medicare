package com.cognizant.medicare.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.medicare.model.Doctor;
import com.cognizant.medicare.model.MedicalTestHistory;
import com.cognizant.medicare.model.MedicareService;
import com.cognizant.medicare.model.Patient;
import com.cognizant.medicare.repository.PatientRepository;
import com.cognizant.medicare.security.AppUserDetailsService;
import com.cognizant.medicare.services.AdminService;
import com.cognizant.medicare.services.MedicareServiceService;


@RestController
@RequestMapping("/medicare")
public class MedicareController {

	String role;
	@Autowired
	MedicareServiceService medicareServiceService;
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@Autowired
	AdminService adminService;
	
	
/*	@GetMapping
	public List<MedicareService> getMedicareServiceList() {
		return medicareServiceService.getMedicareServiceList();
	}	 */
	
/*	MedicareController() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		if(!user.equalsIgnoreCase("anonymoususer")){
		UserDetails userDetails = appUserDetailsService.loadUserByUsername(user);
		 role = userDetails.getAuthorities().toArray()[0].toString();
		 System.out.println("*************** Contructor *************");
		 	System.out.println(role);
		 System.out.println("****************** Cons End **************");	
		}
	}  */
	
/*	@GetMapping("admin")
	public List<MedicareService> getMedicareServiceList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		if(!user.equalsIgnoreCase("anonymoususer")){
		UserDetails userDetails = appUserDetailsService.loadUserByUsername(user);
		String role = userDetails.getAuthorities().toArray()[0].toString();
		
		if(role.equals("admin"))
			return medicareServiceService.getMedicareServiceList();
		else if (role.equals("user")) {
			return medicareServiceService.getMedicareServiceList();			
		}
		
	}
			return medicareServiceService.getMedicareServiceList();
}  */
	
	@GetMapping("/doctor")
	public List<Doctor> getDoctorMenuList() {
		return medicareServiceService.getDoctorMenuList();
	}
	
	
	@GetMapping("/patient")
	public List<Patient> getPatientMenuList() {
		return medicareServiceService.getPatientMenuList();	
	}
	
	@GetMapping("/patient/{username}")
	public Patient getPatient(@PathVariable String username) {
		return medicareServiceService.getPatient(username);	
	}
	
	@PutMapping("/patient/{username}/{doctorId}")
	public void addRequestAppointment(@PathVariable String username, @PathVariable String doctorId) {
		System.out.println("request appointment"+username+"**"+doctorId);
		medicareServiceService.addRequestAppointment(username, doctorId);
	}

	@GetMapping("/medicalTestHistory/{username}/{role}")
	public List<MedicalTestHistory> getMedicalTestHistoryList(@PathVariable String username, @PathVariable String role) {
		System.out.println("******$$$$$ "+role);
		return medicareServiceService.getMedicalTestHistoryList(username, role);
	}
	
	@GetMapping("/medicalTestHistory/{id}")
	public MedicalTestHistory getMedicalTestHistory(@PathVariable int id) {
		
		return medicareServiceService.getMedicalTestHistory(id);
	}
	
	@PutMapping("/saveReport")
	public void saveTestReport(@RequestBody MedicalTestHistory medicalTestHistory) {
		System.out.println("111111111111111111");
		System.out.println(medicalTestHistory);
		medicareServiceService.saveTestReport(medicalTestHistory);
	}
	
	@GetMapping("/doctor/{username}")
	public Doctor getDoctorById(@PathVariable String username) {
		return medicareServiceService.getDoctorById(username);
	}
	
	@PutMapping("/saveDoctor")
	public void saveDoctor(@RequestBody Doctor doctor) {
		System.out.println("111111111111111111");
		System.out.println(doctor);
		medicareServiceService.saveDoctor(doctor);
	}
	
}	
