package com.harishinfotech.employee_management_system.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.harishinfotech.employee_management_system.dao.EmployeeDao;
import com.harishinfotech.employee_management_system.dto.Employee;
import com.harishinfotech.employee_management_system.exception.IdNotFound;
import com.harishinfotech.employee_management_system.repository.EmployeeRepo;
import com.harishinfotech.employee_management_system.util.MyEmailService;
import com.harishinfotech.employee_management_system.util.ResponseStructure;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao dao;
	@Autowired
	private EmployeeRepo repo;
	@Autowired
	private MyEmailService emailService;
	
	
	ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
	
	
	public ResponseEntity<ResponseStructure<Employee>> registration(Employee employee){
		structure.setMsg("Data Saved");
		structure.setData(dao.registration(employee));
		structure.setStatusCode(HttpStatus.CREATED.value());
		emailService.sendEmail(employee.getEmail());
		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.CREATED);	
	}
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employee){	
		// first finding wether the object is present or not
		Employee employee2=dao.findEmployeeById(employee.getId());
		if(employee2 !=null) {
		structure.setMsg("Employee  Data updated");
		structure.setData(dao.updateEmployee(employee));
		structure.setStatusCode(HttpStatus.OK.value());
	
		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {
			throw new IdNotFound();
		}
	}	
	public ResponseEntity<ResponseStructure<Employee>> updateImg(int id,MultipartFile file) throws IOException {
		Employee employee=dao.findEmployeeById(id);
		if(employee.getImg()==null) {
			employee.setId(id);
			employee.setImg(file.getBytes());
			structure.setData(dao.updateEmployee(employee));
			structure.setMsg("employee image updated....");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
			} else {
				throw new IdNotFound("image not uploaded.......");
			}
			
			
		}
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email,String password){
		Employee employee=repo.findByEmail(email);
		if(employee!=null) {
			if(employee.getPassword().equals(password)) {
			structure.setData(employee);
			structure.setMsg("employee mail foubd....");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
			} else {
				throw new IdNotFound();
			}
			
		}else {
			throw new RuntimeException();
		}	
	}
		
//	public ResponseEntity<ResponseStructure<Employee>> loginEmployee(String email1, String pw){
//		// TODO Auto-generated method stub
//		Employee employee1 = dao.loginEmployee(email1, pw);
//	    if (employee1 != null) {
//	      if (employee1.getPassword().equals(pw)) {
//	        structure.setMsg("login successfully");
//	        structure.setData(dao.loginEmployee(email1, pw));
//	        structure.setStatusCode(HttpStatus.CREATED.value());
//	        return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
//	      } else {
//	        System.out.println("not");
//	      }
//	    } else {
//	      throw new RuntimeException("Incorrect email");
//	    }
//	  }
	public ResponseEntity<ResponseStructure<Employee>> login(String email,String password) throws IOException{
		Employee employee=repo.findByEmail(email);
		if(employee!=null) {
			if(employee.getPassword().equals(password)) {
				structure.setMsg("password is match");
				structure.setData(dao.updateEmployee(employee));
				structure.setStatusCode(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
			}
			else {
				throw new IdNotFound("password is incorect");
			}
			
		}else {
			throw new IdNotFound("check the email and password");
		}
	}
	}
	
//	public ResponseEntity<ResponseStructure<Employee>> findEmployeeById(int id){
//		Employee employee=dao.findEmployeeById(id);
//		if(employee !=null) {
//		structure.setMsg("Employee  Data found");
//		structure.setData(employee);
//		structure.setStatusCode(HttpStatus.FOUND.value());
//	//	emailService.sendEmail(.getEmail());
//		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
//		
//	} 
//		

	


