/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class RequestStatusBean{

	private Long requestStatusId;

    private String statusNameE;

    private String statusNameH;
    
    private Short enabled;
    
    public RequestStatusBean() {
    }
    
	public Long getRequestStatusId() {
		return requestStatusId;
	}

	public void setRequestStatusId(Long requestStatusId) {
		this.requestStatusId = requestStatusId;
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
