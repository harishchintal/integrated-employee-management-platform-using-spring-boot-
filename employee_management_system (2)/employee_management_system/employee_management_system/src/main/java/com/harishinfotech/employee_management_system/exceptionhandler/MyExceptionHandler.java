package com.harishinfotech.employee_management_system.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.harishinfotech.employee_management_system.exception.IdNotFound;
import com.harishinfotech.employee_management_system.util.ResponseStructure;


@RestControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> idNotFound(IdNotFound idNotFound){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMsg("Data Not Found");
		structure.setData(idNotFound.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}

}
