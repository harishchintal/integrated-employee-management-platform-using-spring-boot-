package com.harishinfotech.employee_management_system.dao;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.harishinfotech.employee_management_system.dto.Employee;
import com.harishinfotech.employee_management_system.repository.EmployeeRepo;

@Repository
public class EmployeeDao {
	
	@Autowired
	private EmployeeRepo repo;
	public Employee registration(Employee employee) {
		return  repo.save(employee);
	}
	
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> empdb=repo.findById(employee.getId());
		if (empdb.isPresent()) {
			repo.save(employee);
			return empdb.get();
		} else {
			return null;

		}
		
	}
	
	public Employee findEmployeeById(int id) {
		Optional<Employee> empdb=repo.findById(id);
		if (empdb.isPresent()) {
			return empdb.get();
		} else {
			return null;

		}
		
	}
	
	public Employee deleteEmployee(int eid) {
	      Optional<Employee> employee = repo.findById(eid);
	      if (employee.isPresent()) {
	          repo.deleteById(eid); // delete the employee
	          return employee.get(); // return the deleted employee object
	      } else {
	          return null; // or throw an exception if preferred
	      }
	  }
//	public Employee updateImg(int id,MultipartFile file) {
//		Optional<Employee> empdb=repo.findById(id);
//		if (empdb.isPresent()) {
//			return empdb.get();
//		} else {
//			return null;
//		}
//	}
	public Employee loginEmployee(String email,String pw) {
	    Employee employee=repo.findByEmail(email);
	    if(employee!=null) {
	      return employee;
	    }
	    else {
	      return null;
	    }
	  }
	

}
