package com.res.bean;

public class DepositeCategoryBean {
	
	private Long id;
	 private String depositecatnameE;
	 private String depositecatnameH;
	 private Short enabled;
	 public DepositeCategoryBean() {
		 
	 }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepositecatnameE() {
		return depositecatnameE;
	}
	public void setDepositecatnameE(String depositecatnameE) {
		this.depositecatnameE = depositecatnameE;
	}
	public String getDepositecatnameH() {
		return depositecatnameH;
	}
	public void setDepositecatnameH(String depositecatnameH) {
		this.depositecatnameH = depositecatnameH;
	}
	public Short getEnabled() {
		return enabled;
	}
	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}


}
