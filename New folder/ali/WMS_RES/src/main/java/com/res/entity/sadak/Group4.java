package com.res.entity.sadak;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group4 {

	private String samagreeQuality1;
	private String lengthBreadth1;
	private String extraBreadth1;
    private String compectionWork1;
    private String samagreeTest1;
    private String testResult1;
    private String cbrValue ;
    
    
    public Group4() {}
    
	public Group4(String samagreeQuality1, String lengthBreadth1,
			String extraBreadth1, String compectionWork1, String samagreeTest1,
			String testResult1, String cbrValue) {
		super();
		this.samagreeQuality1 = samagreeQuality1;
		this.lengthBreadth1 = lengthBreadth1;
		this.extraBreadth1 = extraBreadth1;
		this.compectionWork1 = compectionWork1;
		this.samagreeTest1 = samagreeTest1;
		this.testResult1 = testResult1;
		this.cbrValue = cbrValue;
	}
	public String getSamagreeQuality1() {
		return samagreeQuality1;
	}
	public void setSamagreeQuality1(String samagreeQuality1) {
		this.samagreeQuality1 = samagreeQuality1;
	}
	public String getLengthBreadth1() {
		return lengthBreadth1;
	}
	public void setLengthBreadth1(String lengthBreadth1) {
		this.lengthBreadth1 = lengthBreadth1;
	}
	public String getExtraBreadth1() {
		return extraBreadth1;
	}
	public void setExtraBreadth1(String extraBreadth1) {
		this.extraBreadth1 = extraBreadth1;
	}
	public String getCompectionWork1() {
		return compectionWork1;
	}
	public void setCompectionWork1(String compectionWork1) {
		this.compectionWork1 = compectionWork1;
	}
	public String getSamagreeTest1() {
		return samagreeTest1;
	}
	public void setSamagreeTest1(String samagreeTest1) {
		this.samagreeTest1 = samagreeTest1;
	}
	public String getTestResult1() {
		return testResult1;
	}
	public void setTestResult1(String testResult1) {
		this.testResult1 = testResult1;
	}
	public String getCbrValue() {
		return cbrValue;
	}
	public void setCbrValue(String cbrValue) {
		this.cbrValue = cbrValue;
	}

	 
}
