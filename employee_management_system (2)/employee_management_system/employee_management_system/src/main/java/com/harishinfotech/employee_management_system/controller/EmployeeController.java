package com.harishinfotech.employee_management_system.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.harishinfotech.employee_management_system.dto.Employee;
import com.harishinfotech.employee_management_system.service.EmployeeService;
import com.harishinfotech.employee_management_system.util.ResponseStructure;
@CrossOrigin(origins = "*",methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	@PostMapping("/empsave")
	public ResponseEntity<ResponseStructure<Employee>> registration(@RequestBody Employee employee) {
		return service.registration(employee);
		
	}
	@PutMapping("/employee/update")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee) {
		return service.updateEmployee(employee);
	}
	@PutMapping("/employee/updatefile/{id}")
	public  ResponseEntity<ResponseStructure<Employee>> updateImg(@PathVariable int id,@RequestBody MultipartFile file) throws IOException {
		return service.updateImg(id, file);
		
	}
	@PutMapping("/employee/findmail")
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(@RequestParam String email,String password) {
		return service.findByEmail(email, password);
		
	}
	@PostMapping("/login/{email}/{pw}")
	  public ResponseEntity<ResponseStructure<Employee>> login(@PathVariable String email,@PathVariable String pw) throws IOException {
		 System.out.println("Email: " + email + ", Password: " + pw);
	    return service.login(email,pw);
	  }
	

}
