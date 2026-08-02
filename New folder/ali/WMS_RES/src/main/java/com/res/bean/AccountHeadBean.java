/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class AccountHeadBean{

	private Long accountHeadId;

    private String accountHeadNameE;

    private String accountHeadNameH;
    
    private Short enabled;
    
	private Integer index;
	
    public AccountHeadBean() {
    }
    
	public Long getAccountHeadId() {
		return accountHeadId;
	}

	public void setAccountHeadId(Long accountHeadId) {
		this.accountHeadId = accountHeadId;
	}

	public String getAccountHeadNameE() {
		return accountHeadNameE;
	}

	public void setAccountHeadNameE(String accountHeadNameE) {
		this.accountHeadNameE = accountHeadNameE;
	}

	public String getAccountHeadNameH() {
		return accountHeadNameH;
	}

	public void setAccountHeadNameH(String accountHeadNameH) {
		this.accountHeadNameH = accountHeadNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}
