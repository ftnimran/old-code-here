package com.res.bean;

public class UnitBean {
	
	public UnitBean() {
	}

	private Long id;

    private String unit;
    
    private String lengthApplicable;
    
    private String widthApplicable;
    
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public UnitBean(Long id, String unit) {
		super();
		this.id = id;
		this.unit = unit;
	}

	public UnitBean(Long id) {
		super();
		this.id = id;
	}

	public UnitBean(String unit) {
		super();
		this.unit = unit;
	}

}
