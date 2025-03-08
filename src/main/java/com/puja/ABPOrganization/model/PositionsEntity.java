package com.puja.ABPOrganization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "positions", schema = "public")
public class PositionsEntity {

	@Id
	private int pos_id;
	private String pos_name;
	private String level;

}
