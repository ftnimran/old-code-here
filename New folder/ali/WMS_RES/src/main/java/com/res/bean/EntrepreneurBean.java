package com.res.bean;

public class EntrepreneurBean{
	
	private Integer index;
	
	private Long id;
	
	private String accountNo;
	
	private String accountType;
	
	private String entrepreneurType;
	
	private String entrepreneurTypeFull;
	
	private String entrepreneurTitle;
	
	private String entrepreneurName;
	
	private String employerTitle;
	
	private String employerName;
	
	private Long parentAccountId;
	
	private EntrepreneurBean parentAccount;
	
	private Long districtId;
	
	private DistrictBean district;
	
	private String startingDate;
	
	private String registrationDate;
	
	private EntrepreneurAddressBean factoryAddress;
	
	private EntrepreneurAddressBean registeredAddress;
	
	private String status;
	
	private String oldAccountNo;
	
	private String entrepreneurDetails;
	
	public EntrepreneurBean() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getEntrepreneurType() {
		return entrepreneurType;
	}

	public void setEntrepreneurType(String entrepreneurType) {
		this.entrepreneurType = entrepreneurType;
	}

	public String getEntrepreneurTitle() {
		return entrepreneurTitle;
	}

	public void setEntrepreneurTitle(String entrepreneurSalutation) {
		this.entrepreneurTitle = entrepreneurSalutation;
	}

	public String getEntrepreneurName() {
		return entrepreneurName;
	}

	public void setEntrepreneurName(String entrepreneurName) {
		this.entrepreneurName = entrepreneurName;
	}

	public String getEmployerTitle() {
		return employerTitle;
	}

	public void setEmployerTitle(String employerSalutation) {
		this.employerTitle = employerSalutation;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public EntrepreneurBean getParentAccount() {
		return parentAccount;
	}

	public void setParentAccount(EntrepreneurBean parentAccount) {
		this.parentAccount = parentAccount;
	}

	public DistrictBean getDistrict() {
		return district;
	}

	public void setDistrict(DistrictBean district) {
		this.district = district;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setFactoryAddress(EntrepreneurAddressBean factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public void setRegisteredAddress(EntrepreneurAddressBean registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public EntrepreneurAddressBean getFactoryAddress() {
		return factoryAddress;
	}

	public EntrepreneurAddressBean getRegisteredAddress() {
		return registeredAddress;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getOldAccountNo() {
		return oldAccountNo;
	}

	public void setOldAccountNo(String oldAccountNo) {
		this.oldAccountNo = oldAccountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(Long parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getEntrepreneurTypeFull() {
		return entrepreneurTypeFull;
	}

	public void setEntrepreneurTypeFull(String entrepreneurTypeFull) {
		this.entrepreneurTypeFull = entrepreneurTypeFull;
	}

	public String getEntrepreneurDetails() {
		return entrepreneurDetails;
	}

	public void setEntrepreneurDetails(String entrepreneurDetails) {
		this.entrepreneurDetails = entrepreneurDetails;
	}
}
