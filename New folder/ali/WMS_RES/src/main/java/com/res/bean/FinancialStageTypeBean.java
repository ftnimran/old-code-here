package com.res.bean;

public class FinancialStageTypeBean  {
	 
	private Long financialStageId;

	private String financialStageNameE;

	private String financialStageNameH;

	private Short enabled;

	private Integer order;

	public FinancialStageTypeBean() {
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Long getFinancialStageId() {
		return financialStageId;
	}

	public void setFinancialStageId(Long financialStageId) {
		this.financialStageId = financialStageId;
	}

	public String getFinancialStageNameE() {
		return financialStageNameE;
	}

	public void setFinancialStageNameE(String financialStageNameE) {
		this.financialStageNameE = financialStageNameE;
	}

	public String getFinancialStageNameH() {
		return financialStageNameH;
	}

	public void setFinancialStageNameH(String financialStageNameH) {
		this.financialStageNameH = financialStageNameH;
	}

}