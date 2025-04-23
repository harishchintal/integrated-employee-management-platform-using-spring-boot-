package com.harishinfotech.employee_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harishinfotech.employee_management_system.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	public Employee findByEmail(String email);
//	public Employee findById(int id);
	
	

}
