package com.puja.ABPOrganization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employees", schema = "public")
public class EmployeeEntity {

	@Id
	private int emp_id;
	private String firstname;
    private String lastname;
    private int age;
    private String email;
    private String phone;
    private int dept_id;
    private int pos_id;
    private int skill_id;
    
    private String skill_name;


}
