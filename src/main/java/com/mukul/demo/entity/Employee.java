package com.mukul.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Digits;
import jakarta.validation.*;

@Data
@Entity
@Builder
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "EMP_SYSTEM")
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@Column(name = "name")
	
	@Pattern(regexp = "[a-zA-Z ]+", message = "Name should only contain alphabets")
	private String name;


	
	@Size(max = 50, message = "Address should be maximum 50 characters long")
	private String address;
	
	@Email(message = "Email should be in correct format")
	private String email;
	
	@Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
	private String phno;
	
	@Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Salary should only contain integers")
	private int salary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee(int id, String name, String address, String email, String phno, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phno = phno;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phno=" + phno
				+ ", salary=" + salary + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}
