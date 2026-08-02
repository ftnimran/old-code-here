package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the mst_work_status database table.
 * 
 */
@Entity
@Table(name="mst_work_status")
public class WorkStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private short enabled;

	@Column(name="work_status_name_e")
	private String workStatusNameE;

	@Column(name="work_status_name_h")
	private String workStatusNameH;
	
	

	public WorkStatus() {
	}
	
	public WorkStatus(Long id) {
		this.id=id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public short getEnabled() {
		return enabled;
	}

	public void setEnabled(short enabled) {
		this.enabled = enabled;
	}

	public String getWorkStatusNameE() {
		return this.workStatusNameE;
	}

	public void setWorkStatusNameE(String workStatusNameE) {
		this.workStatusNameE = workStatusNameE;
	}

	public String getWorkStatusNameH() {
		return this.workStatusNameH;
	}

	public void setWorkStatusNameH(String workStatusNameH) {
		this.workStatusNameH = workStatusNameH;
	}

}