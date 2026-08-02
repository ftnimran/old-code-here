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
@Table(name = "MST_DISTRICT_NEW")
public class District implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "ID")
	private Long districtId;
	
	@Column(name = "DISTRICT_CODE")
	private String districtCode;

	@Column(name = "DISTRICT_NAME")
	private String districtName;

	@Column(name = "DISTRICT_NAME_H")
	private String districtNameH;
	
	@Column(name = "ENABLED")
	private Short enabled;

	/*@JoinColumn(name = "STATE_ID", referencedColumnName = "STATE_ID")
	@ManyToOne
	private State state;*/
	
	@JoinColumn(name = "DIVISION_ID", referencedColumnName = "ID")
	@ManyToOne
	private Division division;
	
	@Column(name = "lgd_dist_code")
	private String districtGisCode;

	@Column(name = "census_dist_cd")
	private String lgdDistrictCode;
	
	@Column(name = "dep_district_code") 
	private String depDistrictCode;
	
	@Column(name = "state_id")
	private Long stateId;
	
	@Column(name = "short_name")
	private String shortName;
	

	public District() {
	}

	public District(Long districtId) {
		this.districtId = districtId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictNameH() {
		return districtNameH;
	}

	public void setDistrictNameH(String districtNameH) {
		this.districtNameH = districtNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public String getDistrictGisCode() {
		return districtGisCode;
	}

	public void setDistrictGisCode(String districtGisCode) {
		this.districtGisCode = districtGisCode;
	}

	public String getLgdDistrictCode() {
		return lgdDistrictCode;
	}

	public void setLgdDistrictCode(String lgdDistrictCode) {
		this.lgdDistrictCode = lgdDistrictCode;
	}

	public String getDepDistrictCode() {
		return depDistrictCode;
	}

	public void setDepDistrictCode(String depDistrictCode) {
		this.depDistrictCode = depDistrictCode;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
