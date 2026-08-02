package com.res.entity.bhavan;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group3 {

	private String neevDesign;
	private String samgriQuality;
	private String workmanship;

	public Group3() {

	}

	public Group3(String neevDesign, String samgriQuality, String workmanship) {
		super();
		this.neevDesign = neevDesign;
		this.samgriQuality = samgriQuality;
		this.workmanship = workmanship;
	}

	public String getNeevDesign() {
		return neevDesign;
	}

	public void setNeevDesign(String neevDesign) {
		this.neevDesign = neevDesign;
	}

	public String getSamgriQuality() {
		return samgriQuality;
	}

	public void setSamgriQuality(String samgriQuality) {
		this.samgriQuality = samgriQuality;
	}

	public String getWorkmanship() {
		return workmanship;
	}

	public void setWorkmanship(String workmanship) {
		this.workmanship = workmanship;
	}

}
