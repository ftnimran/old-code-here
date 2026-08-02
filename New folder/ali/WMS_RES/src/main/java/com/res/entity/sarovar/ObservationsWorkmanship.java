package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsWorkmanship {
	
	
	
	private String itemofWorkmanship;
	private String observationsWorkmanshipMdd;
	private String observationsWorkmanshipFieldContent;
    private String fieldDensityM;
    private String dryDensityM;
    private String compactionAdequateM;
    
public ObservationsWorkmanship() {
		
	}
	
	public ObservationsWorkmanship(String itemofWorkmanship, String observationsWorkmanshipMdd, String observationsWorkmanshipFieldContent,
			String fieldDensityM, String dryDensityM,String compactionAdequateM) {
		super();
		this.itemofWorkmanship = itemofWorkmanship;
		this.observationsWorkmanshipMdd = observationsWorkmanshipMdd;
		this.observationsWorkmanshipFieldContent = observationsWorkmanshipFieldContent;
		this.fieldDensityM = fieldDensityM;
		this.dryDensityM = dryDensityM;
		this.compactionAdequateM = compactionAdequateM;
		
	}

	public String getItemofWorkmanship() {
		return itemofWorkmanship;
	}

	public void setItemofWorkmanship(String itemofWorkmanship) {
		this.itemofWorkmanship = itemofWorkmanship;
	}

	public String getObservationsWorkmanshipMdd() {
		return observationsWorkmanshipMdd;
	}

	public void setObservationsWorkmanshipMdd(String observationsWorkmanshipMdd) {
		this.observationsWorkmanshipMdd = observationsWorkmanshipMdd;
	}

	public String getObservationsWorkmanshipFieldContent() {
		return observationsWorkmanshipFieldContent;
	}

	public void setObservationsWorkmanshipFieldContent(String observationsWorkmanshipFieldContent) {
		this.observationsWorkmanshipFieldContent = observationsWorkmanshipFieldContent;
	}

	public String getFieldDensityM() {
		return fieldDensityM;
	}

	public void setFieldDensityM(String fieldDensityM) {
		this.fieldDensityM = fieldDensityM;
	}

	public String getDryDensityM() {
		return dryDensityM;
	}

	public void setDryDensityM(String dryDensityM) {
		this.dryDensityM = dryDensityM;
	}

	public String getCompactionAdequateM() {
		return compactionAdequateM;
	}

	public void setCompactionAdequateM(String compactionAdequateM) {
		this.compactionAdequateM = compactionAdequateM;
	}
	
	
	
	
    
   
}
