package com.res.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mst_village database table.
 * 
 */
@Entity
@Table(name="mst_village_new")
@NamedQuery(name="MstVillage.findAll", query="SELECT m FROM MstVillage m")
public class MstVillage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String bhucode;

	@Column(name="block_code")
	private String blockCode;

	@Column(name="district_code")
	private String districtCode;

	private byte enabled;

	@Column(name="gp_code")
	private String gpCode;

	private String halkaname;

	private String halkano;

	private String ricode;

	private String riname;

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

	public MstVillage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBhucode() {
		return this.bhucode;
	}

	public void setBhucode(String bhucode) {
		this.bhucode = bhucode;
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

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getGpCode() {
		return this.gpCode;
	}

	public void setGpCode(String gpCode) {
		this.gpCode = gpCode;
	}

	public String getHalkaname() {
		return this.halkaname;
	}

	public void setHalkaname(String halkaname) {
		this.halkaname = halkaname;
	}

	public String getHalkano() {
		return this.halkano;
	}

	public void setHalkano(String halkano) {
		this.halkano = halkano;
	}

	public String getRicode() {
		return this.ricode;
	}

	public void setRicode(String ricode) {
		this.ricode = ricode;
	}

	public String getRiname() {
		return this.riname;
	}

	public void setRiname(String riname) {
		this.riname = riname;
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

}