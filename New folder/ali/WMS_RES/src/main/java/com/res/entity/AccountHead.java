/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MST_ACCOUNT_HEAD")
public class AccountHead extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACCOUNT_HEAD_NAME_E")
    private String accountHeadNameE;

    @Column(name = "ACCOUNT_HEAD_NAME_H")
    private String accountHeadNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public AccountHead() {
    }

    public AccountHead(Long id) {
        this.id = id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
