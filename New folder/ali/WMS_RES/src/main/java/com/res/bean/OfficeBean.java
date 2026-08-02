package com.res.bean;

public class OfficeBean{
	
	private Long id;
	
	private Long exeOfficeId;

	private String officeName;

	private String officeNameH;
	
	private OfficeTypeBean officeType;
	
	private OfficeBean parentOffice;
	
	private UserBean oic;
	
	private DivisionBean division;
	
	private DistrictBean district;
	
	private Short enabled;
	
	private String districtCode;
	
	private Long chiefEngineerOfficeId;
	
	private Long sdoOfficeId;
	
	private Long sdoOfficeName;
	
	private String officeAddress;
	//Rakesh
	private Integer isSqmChecked;

	public Integer getIsSqmChecked() {
		return isSqmChecked;
	}

	public void setIsSqmChecked(Integer isSqmChecked) {
		this.isSqmChecked = isSqmChecked;
	}

	public OfficeBean() {
	}
	
	public OfficeBean(Long id) {
		this.id=id;
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

	public OfficeBean getParentOffice() {
		return parentOffice;
	}

	public void setParentOffice(OfficeBean parentOffice) {
		this.parentOffice = parentOffice;
	}

	public DivisionBean getDivision() {
		return division;
	}

	public void setDivision(DivisionBean division) {
		this.division = division;
	}

	public OfficeTypeBean getOfficeType() {
		return officeType;
	}

	public void setOfficeType(OfficeTypeBean officeType) {
		this.officeType = officeType;
	}

	public UserBean getOic() {
		return oic;
	}

	public void setOic(UserBean oic) {
		this.oic = oic;
	}

	public DistrictBean getDistrict() {
		return district;
	}

	public void setDistrict(DistrictBean district) {
		this.district = district;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public Long getChiefEngineerOfficeId() {
		return chiefEngineerOfficeId;
	}

	public void setChiefEngineerOfficeId(Long chiefEngineerOfficeId) {
		this.chiefEngineerOfficeId = chiefEngineerOfficeId;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Long getExeOfficeId() {
		return exeOfficeId;
	}

	public void setExeOfficeId(Long exeOfficeId) {
		this.exeOfficeId = exeOfficeId;
	}

	public Long getSdoOfficeId() {
		return sdoOfficeId;
	}

	public void setSdoOfficeId(Long sdoOfficeId) {
		this.sdoOfficeId = sdoOfficeId;
	}

	public Long getSdoOfficeName() {
		return sdoOfficeName;
	}

	public void setSdoOfficeName(Long sdoOfficeName) {
		this.sdoOfficeName = sdoOfficeName;
	}
	
	
	
}
