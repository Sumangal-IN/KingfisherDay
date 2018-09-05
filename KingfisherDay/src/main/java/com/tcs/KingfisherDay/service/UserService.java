package com.tcs.KingfisherDay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Employee;
import com.tcs.KingfisherDay.model.enums.FoodPreference;
import com.tcs.KingfisherDay.repository.EmployeeRepository;

@Service
public class UserService {

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee register(String firstName, String lastName, String emailID, String foodPreference, String photoFile,
			String password) {
		return employeeRepository.save(new Employee(firstName, lastName, emailID,
				FoodPreference.valueOf(foodPreference), photoFile, password));
	}

	public boolean isValidLogin(String emailID, String password) {
		return !employeeRepository.findByEmailIDAndPassword(emailID, password).isEmpty();
	}
}
