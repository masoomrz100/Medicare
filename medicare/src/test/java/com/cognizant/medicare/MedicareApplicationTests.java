package com.cognizant.medicare;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.medicare.exception.AdminAlreadyExistsException;
import com.cognizant.medicare.exception.PatientAlreadyExistsException;
import com.cognizant.medicare.exception.UserAlreadyExistsException;
import com.cognizant.medicare.model.Admin;
import com.cognizant.medicare.model.Patient;
import com.cognizant.medicare.repository.AdminRepository;
import com.cognizant.medicare.repository.PatientRepository;
import com.cognizant.medicare.services.UserDetailsService;

import javax.validation.constraints.AssertTrue;

import org.apache.logging.log4j.util.Timer.Status;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MedicareApplicationTests {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	 public void contextLoads() {
	}
	
	@Test public void testPatientSignup() throws PatientAlreadyExistsException, AdminAlreadyExistsException {
	/*	Patient patient = new Patient();
		patient.setPatientId("P006");
		patient.setFirstName("Rahul");
		patient.setLastName("Pandey");
		patient.setAge(25);
		patient.setGender("Male");
		patient.setDateOfBirth("09/03/1996");
		patient.setContact(1111);
		patient.setAltContact(2222);
		patient.setEmail("zxc@gmail");
		patient.setPassword("rahul");
		patient.setAddress1("Aundh");
		patient.setAddress2("Pune");
		patient.setCity("Pune");
		patient.setState("Maharashtra");
		patient.setZip(112121);
		patient.setValid(false);
		
		userDetailsService.PatientSignUp(patient);
		System.out.println(patientRepository.getPatient("P006"));
		
	       Assert.assertTrue(patientRepository.getPatient("P006"));		 */
		
		Admin admin = new Admin("admin123", "sujit", "shinde", 22, "Male", "01/02/1998", 6645, "9987", "qwe@gmail", "sujitpwd");
		userDetailsService.AdminSignUp(admin);
		
		System.out.println(adminRepository.existsById("admin123"));
		
		Assert.assertTrue(adminRepository.existsById("admin123"));
		
		
	}
	
	@Test public void testGetPatient() throws Exception {
		ResultActions resultActions  = mvc.perform(get("/medicare/patient/P004"));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.patientId").exists());
        resultActions.andExpect(jsonPath("$.patientId").value("P004"));
		
	}
	
	@Test public void testGetMadicareRequest() throws Exception {
		ResultActions resultActions  = mvc.perform(get("/medicare/medicalTestHistory/1"));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.id").value(1));   	
	}
	
	@Test public void testGetPatientByUsername() {
		
		Assert.assertNotNull(patientRepository.findByPatientId("P004"));
		Assert.assertTrue(adminRepository.existsById("admin123"));
		
	}
}
