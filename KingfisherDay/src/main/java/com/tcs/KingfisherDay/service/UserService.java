package com.tcs.KingfisherDay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Employee;
import com.tcs.KingfisherDay.model.enums.FoodPreference;
import com.tcs.KingfisherDay.repository.EmployeeRepository;

@Service
public class UserService {

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee register(String name, String emailID, String foodPreference, String photoFile,
			String password) {
		return employeeRepository.save(new Employee(name, emailID,
				FoodPreference.valueOf(foodPreference), photoFile, password));
	}

	public boolean isValidLogin(String emailID, String password) {
		return !employeeRepository.findByEmailIDAndPassword(emailID, password).isEmpty();
	}

	public Employee findByEmailID(String emailID) {
		List<Employee> employeeList = employeeRepository.findByEmailID(emailID);
		if (employeeList.isEmpty())
			return null;
		else
			return employeeList.get(0);
	}

	public Boolean isValidEmployee(String emailID, String password) {
		List<Employee> employeeList = employeeRepository.findByEmailIDAndPassword(emailID, password);
		return !employeeList.isEmpty();
	}
}
