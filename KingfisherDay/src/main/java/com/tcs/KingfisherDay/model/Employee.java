package com.tcs.KingfisherDay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tcs.KingfisherDay.model.enums.FoodPreference;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	@Id
	@Column(name = "EMAIL_ID", nullable = false)
	private String emailID;
	@Column(name = "FOOD_PREFERENCE", nullable = false)
	private FoodPreference foodPreference;
	@Column(name = "PHOTO_FILE", nullable = false)
	private String photoFile;
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	public Employee() {

	}

	public Employee(String firstName, String lastName, String emailID, FoodPreference foodPreference, String photoFile,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.foodPreference = foodPreference;
		this.photoFile = photoFile;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public FoodPreference getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(FoodPreference foodPreference) {
		this.foodPreference = foodPreference;
	}

	public String getPhotoFile() {
		return photoFile;
	}

	public void setPhotoPath(String photoFile) {
		this.photoFile = photoFile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", email=" + emailID
				+ ", foodPreference=" + foodPreference + ", photoPath=" + photoFile + ", password=" + password + "]";
	}

}
