/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MST_BANK")
public class Bank implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANK_ID")
    private Long bankId;
    
    @Column(name = "BANK_NAME")
    private String bankName;    

    @Column(name = "BANK_NAME_H")
    private String bankNameH;
    
    @Column(name = "enabled")
    private Short enabled;
   
    public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Bank() {
    }

    public Bank(Long bankId) {
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
}