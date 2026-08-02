package com.res.entity.talab;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group3 {

	private String sthalChayan;
	private String hydrolicSurvey;
	private String vanBhumi;
	private String karyaUddeshya;

	public Group3() {
	}

	public Group3(String sthalChayan, String hydrolicSurvey, String vanBhumi,
			String karyaUddeshya) {
		super();
		this.sthalChayan = sthalChayan;
		this.hydrolicSurvey = hydrolicSurvey;
		this.vanBhumi = vanBhumi;
		this.karyaUddeshya = karyaUddeshya;
	}

	public String getSthalChayan() {
		return sthalChayan;
	}

	public void setSthalChayan(String sthalChayan) {
		this.sthalChayan = sthalChayan;
	}

	public String getHydrolicSurvey() {
		return hydrolicSurvey;
	}

	public void setHydrolicSurvey(String hydrolicSurvey) {
		this.hydrolicSurvey = hydrolicSurvey;
	}

	public String getVanBhumi() {
		return vanBhumi;
	}

	public void setVanBhumi(String vanBhumi) {
		this.vanBhumi = vanBhumi;
	}

	public String getKaryaUddeshya() {
		return karyaUddeshya;
	}

	public void setKaryaUddeshya(String karyaUddeshya) {
		this.karyaUddeshya = karyaUddeshya;
	}

}
