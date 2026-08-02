/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class BankBean{
	
    private Long bankId;
    
    private String bankName;    

    private String bankNameH;
    
    private Short enabled;
   
    private int index;
    
    public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public BankBean() {
    }

    public BankBean(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNameH() {
		return bankNameH;
	}

	public void setBankNameH(String bankNameH) {
		this.bankNameH = bankNameH;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}