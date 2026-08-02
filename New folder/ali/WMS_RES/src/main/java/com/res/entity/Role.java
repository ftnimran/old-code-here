package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

//@Audited
@Entity
@Table(name = "MST_ROLE")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ROLE_CODE")
	private String roleCode;
	
	@Column(name = "ROLE_NAME")
    private String roleName;
	
	@Column(name = "ORDERING")
	private Integer order;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Role(String roleCode) {
		super();
		this.roleCode = roleCode;
	}

	public Role() {
		super();
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
