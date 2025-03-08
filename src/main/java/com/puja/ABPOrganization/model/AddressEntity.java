package com.puja.ABPOrganization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses", schema = "public")
public class AddressEntity {

	@Id
	private int address_id;
	private int emp_id;
	private String street;
	private String city;
	private String state;
	private int zip;

}
