package com.cognizant.medicare.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicare.model.Doctor;
import com.cognizant.medicare.model.MedicalTestHistory;
import com.cognizant.medicare.model.MedicareService;
import com.cognizant.medicare.model.Patient;
import com.cognizant.medicare.repository.DoctorRepository;
import com.cognizant.medicare.repository.MedicalTestHistoryRepository;
import com.cognizant.medicare.repository.MedicareServiceRepository;
import com.cognizant.medicare.repository.PatientRepository;

@Service
public class MedicareServiceService {

	@Autowired
	MedicareServiceRepository medicareServiceRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	MedicalTestHistoryRepository medicalTestHistoryRepository;
	
	public List<MedicareService> getMedicareServiceList() {
		return medicareServiceRepository.findAll();
	}
	
	public List<Doctor> getDoctorMenuList() {
		return doctorRepository.findAll();
	}
	
	public List<Patient> getPatientMenuList() {
		return patientRepository.findAll();
	}
	
	public void addRequestAppointment(String username, String doctorId) {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		Patient patient = patientRepository.findByPatientId(username);
		System.out.println(patient);
		
		Doctor doctor = doctorRepository.findByDoctorId(doctorId);
		System.out.println(doctor);
		
		List<Doctor> doctorList = new ArrayList<>();
		doctorList.add(doctor);
		
	//	patient.setDoctorList(doctorList);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^ Done appointing ^^^^^^^^^^^^^^^^^^^^^^^^^");
		
		MedicalTestHistory medicalTestHistory = new MedicalTestHistory();
		medicalTestHistory.setDoctorId(doctor.getDoctorId());
		medicalTestHistory.setMedicalServiceId(doctor.getMedicareServiceId());
		medicalTestHistory.setPatientId(patient.getPatientId());
		medicalTestHistory.setReqDoctorId(doctor);
		medicalTestHistory.setReqPatientId(patient);
		medicalTestHistoryRepository.save(medicalTestHistory);
		
	}

	public List<MedicalTestHistory> getMedicalTestHistoryList(String username, String role) {
		// TODO Auto-generated method stub
		if(role.equalsIgnoreCase("patient"))
		return medicalTestHistoryRepository.findByPatientId(username);
		
		else if(role.equalsIgnoreCase("doctor"))
			return medicalTestHistoryRepository.findByDoctorId(username);
		
		return null;
	}

	public Patient getPatient(String username) {
		// TODO Auto-generated method stub
		return patientRepository.findByPatientId(username);
	}


	public MedicalTestHistory getMedicalTestHistory(int id) {
		// TODO Auto-generated method stub
		return medicalTestHistoryRepository.findById(id).get();
	}

	public void saveTestReport(MedicalTestHistory medicalTestHistory) {
		// TODO Auto-generated method stub
		medicalTestHistoryRepository.save(medicalTestHistory);
	}

	public Doctor getDoctorById(String username) {
		// TODO Auto-generated method stub
		return doctorRepository.findByDoctorId(username);
	}

	public void saveDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		MedicareService medicareService = doctor.getMedicareService();
		int medicareServiceId = medicareService.getId();
		
		MedicareService medicareServiceNew = medicareServiceRepository.findById(medicareServiceId).get();
		
		medicareServiceNew.setMedicareService(medicareService.getMedicareService());
		medicareServiceNew.setServiceDescription(medicareService.getServiceDescription());
		medicareServiceNew.setAmount(medicareService.getAmount());
		
		medicareServiceRepository.save(medicareServiceNew);
		
		MedicareService medicareService2 = medicareServiceRepository.findById(medicareServiceId).get();
		
		doctor.setMedicareService(medicareService2);
		doctorRepository.save(doctor);
	}
	
	
}
