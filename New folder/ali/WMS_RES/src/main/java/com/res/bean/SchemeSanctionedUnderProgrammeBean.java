package com.res.bean;




public class SchemeSanctionedUnderProgrammeBean  {
	
	
	
	private Long id;
	
	
	private String name;
	
	
	private String nameHi;

	
	private Short enabled;
	
	private Integer index;
	
	private String status;
	
	private Long agencyTypeId;
	
	private String agencyName;
	  private String shortName;
	  

	public String getShortName() {
		return shortName;
	}


	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


	public SchemeSanctionedUnderProgrammeBean(Long id){
		this.id=id;
		
	}
	

	public SchemeSanctionedUnderProgrammeBean() {
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNameHi() {
		return nameHi;
	}


	public void setNameHi(String nameHi) {
		this.nameHi = nameHi;
	}


	public Short getEnabled() {
		return enabled;
	}


	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}


	public Integer getIndex() {
		return index;
	}


	public void setIndex(Integer index) {
		this.index = index;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Long getAgencyTypeId() {
		return agencyTypeId;
	}


	public void setAgencyTypeId(Long agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
	}


	public String getAgencyName() {
		return agencyName;
	}


	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}




	
}
