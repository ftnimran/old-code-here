package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mst_deposite_category")
public class DepositeCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

    @Column(name = "deposite_cat_nameE")
    private String depositecatnameE;

    @Column(name = "deposite_cat_nameH")
    private String depositecatnameH;
    
    @Column(name = "enabled")
    private Short enabled;
    
    public DepositeCategory() {
    	
    }
    
    public DepositeCategory(Long id)
    {
    	this.id= id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepositecatnameE() {
		return depositecatnameE;
	}

	public void setDepositecatnameE(String depositecatnameE) {
		this.depositecatnameE = depositecatnameE;
	}

	public String getDepositecatnameH() {
		return depositecatnameH;
	}

	public void setDepositecatnameH(String depositecatnameH) {
		this.depositecatnameH = depositecatnameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
	

}
