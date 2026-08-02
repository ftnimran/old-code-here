package com.res.entity.talab;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group5 {

	private String casing;
	private String casingWorkmanShip;
	private String bottom;
	private String pitchingWorkQuality;
	private String wasteWear;
	private String testDone;

	public Group5() {
	}

	public Group5(String casing, String casingWorkmanShip, String bottom,
			String pitchingWorkQuality, String wasteWear, String testDone) {
		super();
		this.casing = casing;
		this.casingWorkmanShip = casingWorkmanShip;
		this.bottom = bottom;
		this.pitchingWorkQuality = pitchingWorkQuality;
		this.wasteWear = wasteWear;
		this.testDone = testDone;
	}

	public String getCasing() {
		return casing;
	}

	public void setCasing(String casing) {
		this.casing = casing;
	}

	public String getCasingWorkmanShip() {
		return casingWorkmanShip;
	}

	public void setCasingWorkmanShip(String casingWorkmanShip) {
		this.casingWorkmanShip = casingWorkmanShip;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

	public String getPitchingWorkQuality() {
		return pitchingWorkQuality;
	}

	public void setPitchingWorkQuality(String pitchingWorkQuality) {
		this.pitchingWorkQuality = pitchingWorkQuality;
	}

	public String getWasteWear() {
		return wasteWear;
	}

	public void setWasteWear(String wasteWear) {
		this.wasteWear = wasteWear;
	}

	public String getTestDone() {
		return testDone;
	}

	public void setTestDone(String testDone) {
		this.testDone = testDone;
	}

}
