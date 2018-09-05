package com.tcs.KingfisherDay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.KingfisherDay.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	List<Employee> findByEmployeeID(final long employeeID);
	
	List<Employee> findByEmail(final String pEmpEmailId);
}
