package com.puja.ABPOrganization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "skills", schema = "public")
public class SkillsEntity {

	@Id
	private int skill_id;
	private String skill_name;

}
