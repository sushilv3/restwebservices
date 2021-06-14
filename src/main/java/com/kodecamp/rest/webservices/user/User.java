package com.kodecamp.rest.webservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the user. ")
public class User {

	private Integer id;
	
	@ApiModelProperty(notes="Name should have atleast 2 characters")
	@Size(min=2, message = "Name Should have Atleast 2 Characters")
	private String name;
	
	
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date birthdate;

	public User(Integer id, String name, Date birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
	
	
	
	
}
