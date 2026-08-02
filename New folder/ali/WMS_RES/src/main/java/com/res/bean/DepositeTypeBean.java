package com.res.bean;

public class DepositeTypeBean {
	
	
	private Long id;
	private String depositetypenameE;
	private String depositetypenameH;
	private Short enabled;
       public DepositeTypeBean() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepositetypenameE() {
		return depositetypenameE;
	}
	public void setDepositetypenameE(String depositetypenameE) {
		this.depositetypenameE = depositetypenameE;
	}
	public String getDepositetypenameH() {
		return depositetypenameH;
	}
	public void setDepositetypenameH(String depositetypenameH) {
		this.depositetypenameH = depositetypenameH;
	}
	public Short getEnabled() {
		return enabled;
	}
	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

}
