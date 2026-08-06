package com.rest_webservices.RestFul_web_services.user;

import java.time.LocalDate;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class user {
	private Integer id;
	@Size(min=2,max=20)
	private String name;
	@PastOrPresent
	private LocalDate birthDate;
	
	public user(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "user [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
}
