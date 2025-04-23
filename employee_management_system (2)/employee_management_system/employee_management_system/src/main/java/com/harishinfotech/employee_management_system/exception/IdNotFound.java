package com.harishinfotech.employee_management_system.exception;




public class IdNotFound extends RuntimeException{
	
	private String s="employee id not found";

	public IdNotFound() {	
	}
	public IdNotFound(String s) {
		this.s = s;
	}
	
	@Override
	public String getMessage() {
		return s;
	}
	
	
	

}
