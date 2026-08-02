package com.res.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the mst_gram_panchayat database table.
 * 
 */
@Entity
@Table(name="mst_gram_panchayat_new")
public class GramPanchayat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long gramPanchayatId;

	@Column(name="block_code")
	private String blockCode;

	@Column(name="district_code")
	private String districtCode;

	@Column(name="gp_code")
	private String gpCode;

	@Column(name="gp_name")
	private String gpName;

	@Column(name="gp_name_h")
	private String gpNameH;

	@Column(name="tehsil_code")
	private String tehsilCode;
		
	@Column(name = "ENABLED")
	private Short enabled;

	public GramPanchayat() {
	}
	
	public GramPanchayat(Long gramPanchayatId) {
		this.gramPanchayatId=gramPanchayatId;
	}
		
	public Long getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(Long gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}

	public String getBlockCode() {
		return this.blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public String getGpCode() {
		return this.gpCode;
	}

	public void setGpCode(String gpCode) {
		this.gpCode = gpCode;
	}

	public String getGpName() {
		return this.gpName;
	}

	public void setGpName(String gpName) {
		this.gpName = gpName;
	}

	public String getGpNameH() {
		return this.gpNameH;
	}

	public void setGpNameH(String gpNameH) {
		this.gpNameH = gpNameH;
	}

	public String getTehsilCode() {
		return this.tehsilCode;
	}

	public void setTehsilCode(String tehsilCode) {
		this.tehsilCode = tehsilCode;
	}

}