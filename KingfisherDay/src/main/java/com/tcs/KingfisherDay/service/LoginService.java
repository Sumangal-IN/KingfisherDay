package com.tcs.KingfisherDay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.KingfisherDay.model.Employee;
import com.tcs.KingfisherDay.repository.EmployeeRepository;

@Service
public class LoginService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	 public boolean validateEmployeeLogin(String pEmployeeEmail, String pEmpPassword) {
		 boolean validUser=false;
		 
		 List<Employee> employees = employeeRepository.findByEmail(pEmployeeEmail);
			if (!employees.isEmpty()) {
				Employee emp=employees.get(0);
				if(emp.getPassword().equals(pEmpPassword)) {
					validUser= true;
				}
			}
			System.out.println("Is Valid User:"+validUser);
			return validUser;
	    }
}
