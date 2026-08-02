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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MST_STANDARD_TEMPLATE_TYPE")
public class StandardTemplateType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "ID")
	private Long id;

	@Column(name = "TYPE_DESC")
	private String typeDesc;

	@Column(name = "TYPE_DESC_H")
	private String typeDescH;

	@Column(name = "ENABLED")
	private Short enabled;

	@JoinColumn(name = "WORK_TYPE_ID", referencedColumnName = "WORK_TYPE_ID")
	@ManyToOne
	private WorkType workType;

	public StandardTemplateType() {
		super();
	}

	public StandardTemplateType(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getTypeDescH() {
		return typeDescH;
	}

	public void setTypeDescH(String typeDescH) {
		this.typeDescH = typeDescH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

}
