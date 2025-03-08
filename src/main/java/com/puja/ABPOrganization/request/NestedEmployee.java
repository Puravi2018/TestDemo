package com.puja.ABPOrganization.request;

import java.util.List;

import lombok.Data;

@Data
public class NestedEmployee {
	
	private String firstname;
	private String lastname;
	private String dateOfBirth;
	private String email;
	private String phone;
	private Address address;
	private Department department;
	private Position position;
	private Skills skills;

}
