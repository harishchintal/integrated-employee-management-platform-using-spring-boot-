package com.harishinfotech.employee_management_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double sal;
	@Column(unique = true)
	private String email;
	private String password;
	private long contact;
	@Lob // to consider as large object
	@Column(columnDefinition = "longblob",length = 999999999)//img size or object
	private byte img[];

}
