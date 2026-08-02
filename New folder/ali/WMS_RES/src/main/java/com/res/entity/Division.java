package com.res.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MST_DIVISION")
public class Division implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	private Long id;

	@Column(name = "DIVISION_NAME")
	private String divisionName;

	@Column(name = "DIVISION_NAME_H")
	private String divisionNameH;
	
	@Column(name = "ENABLED")
	private Short enabled;

	public Division() {
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDivisionNameH() {
		return divisionNameH;
	}

	public void setDivisionNameH(String divisionNameH) {
		this.divisionNameH = divisionNameH;
	}
}
