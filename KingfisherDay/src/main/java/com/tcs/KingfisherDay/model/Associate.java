package com.tcs.KingfisherDay.model;

public class Associate {
	private int employeeID;
	private String email;
	private FoodPreference foodPreference;
	private String photoPath;

	public Associate() {

	}

	public Associate(int employeeID, String email, FoodPreference foodPreference, String photoPath) {
		super();
		this.employeeID = employeeID;
		this.email = email;
		this.foodPreference = foodPreference;
		this.photoPath = photoPath;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
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

}
