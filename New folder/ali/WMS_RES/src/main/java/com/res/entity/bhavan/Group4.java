package com.res.entity.bhavan;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group4 {
	public Group4(String samgriQuality1, String workmanship1, String design) {
		super();
		this.samgriQuality1 = samgriQuality1;
		this.workmanship1 = workmanship1;
		this.design = design;
	}

	private String samgriQuality1;
	private String workmanship1;
	private String design;

	public Group4() {
	}

	public String getSamgriQuality1() {
		return samgriQuality1;
	}

	public void setSamgriQuality1(String samgriQuality1) {
		this.samgriQuality1 = samgriQuality1;
	}

	public String getWorkmanship1() {
		return workmanship1;
	}

	public void setWorkmanship1(String workmanship1) {
		this.workmanship1 = workmanship1;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}
	 
}
