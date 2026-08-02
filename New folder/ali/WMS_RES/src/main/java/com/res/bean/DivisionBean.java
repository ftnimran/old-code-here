package com.res.bean;

public class DivisionBean {
	
	private Long id;

	private String divisionName;

	private String divisionNameH;
	
	private Short enabled;

	public DivisionBean() {
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
