package com.res.entity.sadak;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group5 {

	private String samagreeQuality2;
	private String lengthBreadth2;
	private String extraBreadth2;
	private String compectionWork2;
	private String samagreeTest2;
	private String testResult2;
	private String cbr1;

	public Group5() {
	}

	public Group5(String samagreeQuality2, String lengthBreadth2,
			String extraBreadth2, String compectionWork2, String samagreeTest2,
			String testResult2, String cbr1) {
		super();
		this.samagreeQuality2 = samagreeQuality2;
		this.lengthBreadth2 = lengthBreadth2;
		this.extraBreadth2 = extraBreadth2;
		this.compectionWork2 = compectionWork2;
		this.samagreeTest2 = samagreeTest2;
		this.testResult2 = testResult2;
		this.cbr1 = cbr1;
	}

	public String getSamagreeQuality2() {
		return samagreeQuality2;
	}

	public void setSamagreeQuality2(String samagreeQuality2) {
		this.samagreeQuality2 = samagreeQuality2;
	}

	public String getLengthBreadth2() {
		return lengthBreadth2;
	}

	public void setLengthBreadth2(String lengthBreadth2) {
		this.lengthBreadth2 = lengthBreadth2;
	}

	public String getExtraBreadth2() {
		return extraBreadth2;
	}

	public void setExtraBreadth2(String extraBreadth2) {
		this.extraBreadth2 = extraBreadth2;
	}

	public String getCompectionWork2() {
		return compectionWork2;
	}

	public void setCompectionWork2(String compectionWork2) {
		this.compectionWork2 = compectionWork2;
	}

	public String getSamagreeTest2() {
		return samagreeTest2;
	}

	public void setSamagreeTest2(String samagreeTest2) {
		this.samagreeTest2 = samagreeTest2;
	}

	public String getTestResult2() {
		return testResult2;
	}

	public void setTestResult2(String testResult2) {
		this.testResult2 = testResult2;
	}

	public String getCbr1() {
		return cbr1;
	}

	public void setCbr1(String cbr1) {
		this.cbr1 = cbr1;
	}

}
