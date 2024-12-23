package com.luv2code.web.jdbc;

public class Student {
	int id;
	String first_name;
	String last_name;
	String email;
	 String phoneNumber;
	
	public Student(String first_name, String last_name, String email, String phoneNumber) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phoneNumber=phoneNumber;
	}
	public Student(int id, String first_name, String last_name, String email,String phoneNumber) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phoneNumber=phoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ "]";
	}
	
	
	

}
