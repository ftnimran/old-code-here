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
@Table(name = "MST_REQUEST_STATUS")
public class RequestStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

    @Column(name = "STATUS_NAME_E")
    private String statusNameE;

    @Column(name = "STATUS_NAME_H")
    private String statusNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public RequestStatus() {
    }
    
	public RequestStatus(Long workRequestStatusId) {
		this.id=workRequestStatusId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusNameE() {
		return statusNameE;
	}

	public void setStatusNameE(String statusNameE) {
		this.statusNameE = statusNameE;
	}

	public String getStatusNameH() {
		return statusNameH;
	}

	public void setStatusNameH(String statusNameH) {
		this.statusNameH = statusNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
    
}
