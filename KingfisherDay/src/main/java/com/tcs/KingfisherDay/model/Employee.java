package com.tcs.KingfisherDay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tcs.KingfisherDay.model.enums.FoodPreference;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Column(name = "NAME", nullable = false)
	private String name;
	@Id
	@Column(name = "EMAIL_ID", nullable = false)
	private String emailID;
	@Column(name = "FOOD_PREFERENCE", nullable = false)
	private FoodPreference foodPreference;
	@Column(name = "PHOTO_FILE", nullable = false)
	private String photoFile;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "MOBILE", nullable = false)
	private String mobile;
	@Column(name = "PHOTO", nullable = false)
	private String photo;

	public Employee() {

	}

	public Employee(String name, String emailID, FoodPreference foodPreference, String photoFile, String password,
			String mobile, String photo) {
		super();
		this.name = name;
		this.emailID = emailID;
		this.foodPreference = foodPreference;
		this.photoFile = photoFile;
		this.password = password;
		this.mobile = mobile;
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setPhotoFile(String photoFile) {
		this.photoFile = photoFile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", emailID=" + emailID + ", foodPreference=" + foodPreference + ", photoFile="
				+ photoFile + ", password=" + password + ", mobile=" + mobile + "]";
	}

}
