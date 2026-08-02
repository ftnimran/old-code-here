package com.res.entity.pulia;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group3 {

	private String samgriQuality1;
	private String pipeCulvert;
	private String pipeCushion;

	private String fillingCompaction;
	private String slaveCulvert;
	private String samgriTest1;

	private String rccWorkQuality;
	private String reinforcementQuality;
	private String rccSamgriQuality;

	public Group3() {

	}

	public Group3(String samgriQuality1, String pipeCulvert,
			String pipeCushion, String fillingCompaction, String slaveCulvert,
			String samgriTest1, String rccWorkQuality,
			String reinforcementQuality, String rccSamgriQuality) {
		super();
		this.samgriQuality1 = samgriQuality1;
		this.pipeCulvert = pipeCulvert;
		this.pipeCushion = pipeCushion;
		this.fillingCompaction = fillingCompaction;
		this.slaveCulvert = slaveCulvert;
		this.samgriTest1 = samgriTest1;
		this.rccWorkQuality = rccWorkQuality;
		this.reinforcementQuality = reinforcementQuality;
		this.rccSamgriQuality = rccSamgriQuality;
	}
	
	public String getSamgriQuality1() {
		return samgriQuality1;
	}

	public void setSamgriQuality1(String samgriQuality1) {
		this.samgriQuality1 = samgriQuality1;
	}

	public String getPipeCulvert() {
		return pipeCulvert;
	}

	public void setPipeCulvert(String pipeCulvert) {
		this.pipeCulvert = pipeCulvert;
	}

	public String getPipeCushion() {
		return pipeCushion;
	}

	public void setPipeCushion(String pipeCushion) {
		this.pipeCushion = pipeCushion;
	}

	public String getFillingCompaction() {
		return fillingCompaction;
	}

	public void setFillingCompaction(String fillingCompaction) {
		this.fillingCompaction = fillingCompaction;
	}

	public String getSlaveCulvert() {
		return slaveCulvert;
	}

	public void setSlaveCulvert(String slaveCulvert) {
		this.slaveCulvert = slaveCulvert;
	}

	public String getSamgriTest1() {
		return samgriTest1;
	}

	public void setSamgriTest1(String samgriTest1) {
		this.samgriTest1 = samgriTest1;
	}

	public String getRccWorkQuality() {
		return rccWorkQuality;
	}

	public void setRccWorkQuality(String rccWorkQuality) {
		this.rccWorkQuality = rccWorkQuality;
	}

	public String getReinforcementQuality() {
		return reinforcementQuality;
	}

	public void setReinforcementQuality(String reinforcementQuality) {
		this.reinforcementQuality = reinforcementQuality;
	}

	public String getRccSamgriQuality() {
		return rccSamgriQuality;
	}

	public void setRccSamgriQuality(String rccSamgriQuality) {
		this.rccSamgriQuality = rccSamgriQuality;
	}

	

}
