package com.smoothstack.springbatch.JdbcCursorItemReader.model;

/**
 CREATE TABLE `student` (
 `id` int NOT NULL AUTO_INCREMENT,
 `email` varchar(255) DEFAULT NULL,
 `first_name` varchar(255) DEFAULT NULL,
 `last_name` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 SELECT * FROM hb_student_tracker.student;
 */
public class Student {
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	public Student() {}

	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
}
