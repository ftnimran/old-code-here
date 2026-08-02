package com.res.bean;

public class YearBean {
	public YearBean() {
	}

	private Long id;
	
	private String year;

	public YearBean(String year) {
		super();
		this.year = year;
	}

	private String enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
}
