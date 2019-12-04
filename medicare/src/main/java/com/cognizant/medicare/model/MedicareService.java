package com.cognizant.medicare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



/**
 * @author 799497
 *
 */
@Entity 
@Table (name = "medicare_service")
public class MedicareService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "medicare_service_id")
	private int id;
	
	@Column (name = "medicare_service")
	private String medicareService;
	
	@Column (name = "service_description")
	private String serviceDescription;
	
	@Column (name = "amount")
	private int amount;

	@Column (name = "image")
	private String image;
	/*@JsonIgnore
	@ManyToMany
	@JoinTable (name = "patient_doctor_medicare", joinColumns = @JoinColumn(name="dm_me_id"), inverseJoinColumns = @JoinColumn(name = "dm_do_id"))
	private List<Doctor> doctorList;  */

	public MedicareService() {
		super();
	}
	public MedicareService(int id, String medicareService, String serviceDescription, int amount, String image) {
		super();
		this.id = id;
		this.medicareService = medicareService;
		this.serviceDescription = serviceDescription;
		this.amount = amount;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMedicareService() {
		return medicareService;
	}
	public void setMedicareService(String medicareService) {
		this.medicareService = medicareService;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "MedicareService [id=" + id + ", medicareService=" + medicareService + ", serviceDescription="
				+ serviceDescription + ", amount=" + amount + ", image=" + image + "]";
	}

	
}
