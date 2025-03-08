package com.puja.ABPOrganization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "departments", schema = "public")
public class DepartmentEntity {

	@Id
	private int dept_id;
	private String dept_name;

}
