package com.puja.ABPOrganization.request;

import lombok.Data;

@Data
public class Employees {
	
	private int emp_id;
    private String firstname;
    private String lastname;
    private int age;
    private String email;
    private String phone;
    private int deptId;
    private int posId;
    private int skillId;

}
