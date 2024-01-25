package edu.fisa.lab.finance.role.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "roles")
@Getter
public class Role {

	@Id
	private Long roleId;

	private String roleName;

}
