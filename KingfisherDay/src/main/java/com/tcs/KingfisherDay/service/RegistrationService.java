package com.tcs.KingfisherDay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Employee;
import com.tcs.KingfisherDay.repository.EmployeeRepository;

@Service
public class RegistrationService {

	@Autowired
	EmployeeRepository employeeRepository;

	public void registerEmployee(Employee pEmployee) {
		employeeRepository.save(pEmployee);
	}

	public Employee getEmployeeById(long pEmployeeId) {
		List<Employee> employees = employeeRepository.findByEmployeeID(pEmployeeId);
		if (employees.isEmpty()) {
			return null;
		}
		return employees.get(0);
	}

}
