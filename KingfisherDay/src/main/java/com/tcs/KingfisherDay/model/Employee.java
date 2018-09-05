package com.tcs.KingfisherDay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@Column(name = "EMPLOYEE_ID", nullable = false)
	private long employeeID;
	@Column(name = "EMAIL_NAME", nullable = false)
	private String employeeName;
	@Column(name = "EMAIL_ID", nullable = false)
	private String email;
	@Column(name = "FOOD_PREFERENCE", nullable = false)
	private FoodPreference foodPreference;
	@Column(name = "PHOTO_PATH", nullable = false)
	private String photoPath;

	public Employee() {

	}

	public Employee(long employeeID, String employeeName, String email, FoodPreference foodPreference,
			String photoPath) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.email = email;
		this.foodPreference = foodPreference;
		this.photoPath = photoPath;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FoodPreference getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(FoodPreference foodPreference) {
		this.foodPreference = foodPreference;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", employeeName=" + employeeName + ", email=" + email
				+ ", foodPreference=" + foodPreference + ", photoPath=" + photoPath + "]";
	}
	

}
