package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_measurement_unit")
public class Unit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "UNIT")
	private String unit;
	
	@Column(name = "length_applicable")
	private String lengthApplicable;
	
	@Column(name = "width_applicable")
	private String widthApplicable;
	
	@Column(name = "height_depth_applicable")
	private String heightDepthApplicable;


	public String getLengthApplicable() {
		return lengthApplicable;
	}

	public void setLengthApplicable(String lengthApplicable) {
		this.lengthApplicable = lengthApplicable;
	}

	public String getWidthApplicable() {
		return widthApplicable;
	}

	public void setWidthApplicable(String widthApplicable) {
		this.widthApplicable = widthApplicable;
	}

	public String getHeightDepthApplicable() {
		return heightDepthApplicable;
	}

	public void setHeightDepthApplicable(String heightDepthApplicable) {
		this.heightDepthApplicable = heightDepthApplicable;
	}

	public Unit() {
	}

	public Unit(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
