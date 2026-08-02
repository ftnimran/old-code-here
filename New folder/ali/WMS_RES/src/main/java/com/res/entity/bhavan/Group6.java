package com.res.entity.bhavan;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group6 {

	private String samgriQuality3;
	private String workmanship3;
	private String lintel1;
	private String roofwork;
	private String roofSlope;
	private String roofWorkDesign;

	public Group6() {
	}

	public Group6(String samgriQuality3, String workmanship3, String lintel1,
			String roofwork, String roofSlope, String roofWorkDesign) {
		super();
		this.samgriQuality3 = samgriQuality3;
		this.workmanship3 = workmanship3;
		this.lintel1 = lintel1;
		this.roofwork = roofwork;
		this.roofSlope = roofSlope;
		this.roofWorkDesign = roofWorkDesign;
	}

	public String getSamgriQuality3() {
		return samgriQuality3;
	}

	public void setSamgriQuality3(String samgriQuality3) {
		this.samgriQuality3 = samgriQuality3;
	}

	public String getWorkmanship3() {
		return workmanship3;
	}

	public void setWorkmanship3(String workmanship3) {
		this.workmanship3 = workmanship3;
	}

	public String getLintel1() {
		return lintel1;
	}

	public void setLintel1(String lintel1) {
		this.lintel1 = lintel1;
	}

	public String getRoofwork() {
		return roofwork;
	}

	public void setRoofwork(String roofwork) {
		this.roofwork = roofwork;
	}

	public String getRoofSlope() {
		return roofSlope;
	}

	public void setRoofSlope(String roofSlope) {
		this.roofSlope = roofSlope;
	}

	public String getRoofWorkDesign() {
		return roofWorkDesign;
	}

	public void setRoofWorkDesign(String roofWorkDesign) {
		this.roofWorkDesign = roofWorkDesign;
	}

}
