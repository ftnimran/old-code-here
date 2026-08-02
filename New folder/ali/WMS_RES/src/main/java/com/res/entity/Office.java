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

/**
 * CR-RESOWMS/CR/2-1 New Office Addition-Add New Office
 */
//@Audited
@Entity
@Table(name = "MST_OFFICES")
public class Office implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	private Long id;

	@Column(name = "OFFICE_NAME")
	private String officeName;

	@Column(name = "OFFICE_NAME_H")
	private String officeNameH;

	@JoinColumn(name = "OFFICE_TYPE_ID", referencedColumnName = "ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private OfficeType officeType;

	@JoinColumn(name = "PARENT_OFFICE_ID", referencedColumnName = "ID")
	@ManyToOne
	private Office parentOffice;

	@JoinColumn(name = "OIC_ID", referencedColumnName = "ID")
	@ManyToOne
	private Users oic;

	@JoinColumn(name = "DIVISION_ID", referencedColumnName = "ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Division division;

	/*
	 * @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
	 * 
	 * @ManyToOne private District district;
	 */
	@Column(name = "DISTRICT_CODE")
	private String districtCode;

	@Column(name = "ENABLED")
	private Short enabled;

	@Column(name = "office_address")
	private String officeAddress;

	// @JoinColumn(name = "block_code", referencedColumnName = "block_code")
	// @ManyToOne
	@Column(name = "block_code")
	private Long blockCode;

	@JoinColumn(name = "block_id", referencedColumnName = "id")
	@ManyToOne
	private Block blockId;

	@Column(name = "gp_id")
	private Long gramPanchayatId;

	public Office() {
	}

	public Office(Long id) {
		this.id = id;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficeNameH() {
		return officeNameH;
	}

	public void setOfficeNameH(String officeNameH) {
		this.officeNameH = officeNameH;
	}

	public OfficeType getOfficeType() {
		return officeType;
	}

	public void setOfficeType(OfficeType officeType) {
		this.officeType = officeType;
	}

	public Office getParentOffice() {
		return parentOffice;
	}

	public void setParentOffice(Office parentOffice) {
		this.parentOffice = parentOffice;
	}

	public Users getOic() {
		return oic;
	}

	public void setOic(Users oic) {
		this.oic = oic;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Long getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(Long blockCode) {
		this.blockCode = blockCode;
	}

	public Block getBlockId() {
		return blockId;
	}

	public void setBlockId(Block blockId) {
		this.blockId = blockId;
	}

	public Long getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(Long gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}
	
	
}


