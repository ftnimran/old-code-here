package com.res.entity.sadak;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group3 {

	private String noticeBoard;
	private String ogl;
	private String tvm;
	private String samagreeQuality;
	private String lengthBreadth;
	private String extraBreadth;
	private String compectionWork;
	private String samagreeTest;
	private String testResult;
	
	public Group3() {}
	
	public Group3(String noticeBoard, String ogl, String tvm,
			String samagreeQuality, String lengthBreadth, String extraBreadth,
			String compectionWork, String samagreeTest, String testResult) {
		super();
		this.noticeBoard = noticeBoard;
		this.ogl = ogl;
		this.tvm = tvm;
		this.samagreeQuality = samagreeQuality;
		this.lengthBreadth = lengthBreadth;
		this.extraBreadth = extraBreadth;
		this.compectionWork = compectionWork;
		this.samagreeTest = samagreeTest;
		this.testResult = testResult;
	}
	public String getNoticeBoard() {
		return noticeBoard;
	}
	public void setNoticeBoard(String noticeBoard) {
		this.noticeBoard = noticeBoard;
	}
	public String getOgl() {
		return ogl;
	}
	public void setOgl(String ogl) {
		this.ogl = ogl;
	}
	public String getTvm() {
		return tvm;
	}
	public void setTvm(String tvm) {
		this.tvm = tvm;
	}
	public String getSamagreeQuality() {
		return samagreeQuality;
	}
	public void setSamagreeQuality(String samagreeQuality) {
		this.samagreeQuality = samagreeQuality;
	}
	public String getLengthBreadth() {
		return lengthBreadth;
	}
	public void setLengthBreadth(String lengthBreadth) {
		this.lengthBreadth = lengthBreadth;
	}
	public String getExtraBreadth() {
		return extraBreadth;
	}
	public void setExtraBreadth(String extraBreadth) {
		this.extraBreadth = extraBreadth;
	}
	public String getCompectionWork() {
		return compectionWork;
	}
	public void setCompectionWork(String compectionWork) {
		this.compectionWork = compectionWork;
	}
	public String getSamagreeTest() {
		return samagreeTest;
	}
	public void setSamagreeTest(String samagreeTest) {
		this.samagreeTest = samagreeTest;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	 
}
