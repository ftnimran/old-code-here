package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the mst_village database table.
 * 
 */
@Entity
@Table(name="village_master_utf8")
public class Village implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//private String bhucode;

	@Column(name="block_code")
	private String blockCode;

	private Short enabled;

	@Column(name="gp_code")
	private String gpCode;

	@Column(name="tehsil_code")
	private String tehsilCode;

	@Column(name="village_code")
	private String villageCode;

	@Column(name="village_name")
	private String villageName;

	@Column(name="village_name_h")
	private String villageNameH;

	@Column(name="villagecode_census")
	private String villagecodeCensus;

	@Column(name="villagename_census")
	private String villagenameCensus;
	
	/*
	 * @Column(name="LATITUDE") private BigDecimal latitude;
	 * 
	 * @Column(name="LONGITUDE") private BigDecimal longitude;
	 */
	
	@Column(name = "district_code")
	@JoinColumn(name = "district_code", referencedColumnName = "district_code")
	private String districtCode;

	
	

	public Village() {
	}
	
	public Village(Long id) {
		this.id=id;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
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

	public String getGpCode() {
		return this.gpCode;
	}

	public void setGpCode(String gpCode) {
		this.gpCode = gpCode;
	}

	public String getTehsilCode() {
		return this.tehsilCode;
	}

	public void setTehsilCode(String tehsilCode) {
		this.tehsilCode = tehsilCode;
	}

	public String getVillageCode() {
		return this.villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageName() {
		return this.villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getVillageNameH() {
		return this.villageNameH;
	}

	public void setVillageNameH(String villageNameH) {
		this.villageNameH = villageNameH;
	}

	public String getVillagecodeCensus() {
		return this.villagecodeCensus;
	}

	public void setVillagecodeCensus(String villagecodeCensus) {
		this.villagecodeCensus = villagecodeCensus;
	}

	public String getVillagenameCensus() {
		return this.villagenameCensus;
	}

	public void setVillagenameCensus(String villagenameCensus) {
		this.villagenameCensus = villagenameCensus;
	}

	public Short getEnabled() {
		return enabled;
	}

	
	
}