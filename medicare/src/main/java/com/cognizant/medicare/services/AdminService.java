package com.cognizant.medicare.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicare.exception.PatientAlreadyExistsException;
import com.cognizant.medicare.exception.UserAlreadyExistsException;
import com.cognizant.medicare.model.Doctor;
import com.cognizant.medicare.model.MedicalTestHistory;
import com.cognizant.medicare.model.Patient;
import com.cognizant.medicare.model.Role;
import com.cognizant.medicare.model.User;
import com.cognizant.medicare.repository.DoctorRepository;
import com.cognizant.medicare.repository.MedicalTestHistoryRepository;
import com.cognizant.medicare.repository.PatientRepository;
import com.cognizant.medicare.repository.RoleRepository;
import com.cognizant.medicare.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired 
	MedicalTestHistoryRepository medicalTestHistoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	public List<Doctor> getDoctorList() {
		return doctorRepository.findAll();
	}
	
	public List<Patient> getPatientList() {
		return patientRepository.findAll();
	}

	public Boolean validatePatient(String username, String patientId) throws UserAlreadyExistsException  {
		// TODO Auto-generated method stub
			if(username.equalsIgnoreCase("admin"))
			{
				User user1 = userRepository.findByUsername(patientId);				
				
				if(user1 != null)
				{
					throw new UserAlreadyExistsException();
				}
				
				Patient patient = patientRepository.findByPatientId(patientId);
				patient.setValid(true);
				patientRepository.save(patient);
				
				List<Role> rolelist = new ArrayList<Role>();
				
				Role role = roleRepository.findById(4).get();
				rolelist.add(role);
				
				User user = new User();
				user.setUsername(patient.getPatientId());
				user.setFirstname(patient.getFirstName());
				user.setLastname(patient.getLastName());
				System.out.println("***************************");
					System.out.println(patient.getPassword());
				System.out.println("****************************");
				user.setPassword(patient.getPassword());
				user.setRoles(rolelist);
				userRepository.save(user);
				
				return true;
			}
			
			else
				return false;
	}

	public Boolean validateDoctor(String username, String doctorId) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		if(username.equalsIgnoreCase("admin"))
		{
			User user1 = userRepository.findByUsername(doctorId);				
			
			if(user1 != null)
			{
				throw new UserAlreadyExistsException();
			}		
			
			Doctor doctor = doctorRepository.findByDoctorId(doctorId);
			doctor.setStatus(true);
			doctorRepository.save(doctor);
			
			List<Role> rolelist = new ArrayList<Role>();	
			Role role = roleRepository.findById(3).get();
			rolelist.add(role);
					
			User user = new User();
			user.setUsername(doctor.getDoctorId());
			user.setFirstname(doctor.getFirstName());
			user.setLastname(doctor.getLastName());
			System.out.println("***************************");
				System.out.println(doctor.getPassword());
			System.out.println("****************************");
			user.setPassword(doctor.getPassword());
			user.setRoles(rolelist);
			userRepository.save(user);

			return true;
		}
		
		else
			return false;

	}

	public Boolean validateReport(String username, int reportId) {
		// TODO Auto-generated method stub
		
		if(username.equalsIgnoreCase("doctor"))
		{
			MedicalTestHistory medicalTestHistory = medicalTestHistoryRepository.findById(reportId).get();
			
			
			
			
			medicalTestHistory.setStatus(true);
			medicalTestHistoryRepository.save(medicalTestHistory);
			
			System.out.println("********************* test Validate Report *****************");
				System.out.println(medicalTestHistory);
			System.out.println("********************* test Validate Report *****************");
			return true;
		}
		
		else
			return false;

	}

	public Boolean removePatientRequest(String username, int reportId) {
		// TODO Auto-generated method stub
		System.out.println("log 2"+username);
		System.out.println("log 3"+reportId);
		
		if(username.equalsIgnoreCase("doctor"))
		{
			MedicalTestHistory medicalTestHistory = medicalTestHistoryRepository.findById(reportId).get();
			System.out.println("*********** Delete patient request !"+username);
			medicalTestHistory.setStatus(false);
			medicalTestHistoryRepository.save(medicalTestHistory);
			
			
			System.out.println(medicalTestHistory);
			System.out.println("*********** Delete patient request !");
			return true;
		}
		
		else
			return false;
		 
	}
	
	
	
}
