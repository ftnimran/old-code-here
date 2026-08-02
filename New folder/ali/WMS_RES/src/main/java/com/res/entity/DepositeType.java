package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_deposite_type")
public class DepositeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "deposite_type_nameE")
	private String depositetypenameE;

	@Column(name = "deposite_type_nameH")
	private String depositetypenameH;

	@Column(name = "enabled")
	private Short enabled;

	public DepositeType() {
	}

	public DepositeType(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepositetypenameE() {
		return depositetypenameE;
	}

	public void setDepositetypenameE(String depositetypenameE) {
		this.depositetypenameE = depositetypenameE;
	}

	public String getDepositetypenameH() {
		return depositetypenameH;
	}

	public void setDepositetypenameH(String depositetypenameH) {
		this.depositetypenameH = depositetypenameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

}
