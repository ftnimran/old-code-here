package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group11 {

	private String fileStr1;
	private String fileStr2;
	private String fileStr3;
	
	
	
	
	public Group11(String fileStr1, String fileStr2, String fileStr3
			) {
		super();
		this.fileStr1 = fileStr1;
		this.fileStr2 = fileStr2;
		this.fileStr3 = fileStr3;
		
		
	}
	
	public Group11() {
		
	}

	public String getFileStr1() {
		return fileStr1;
	}

	public void setFileStr1(String fileStr1) {
		this.fileStr1 = fileStr1;
	}

	public String getFileStr2() {
		return fileStr2;
	}

	public void setFileStr2(String fileStr2) {
		this.fileStr2 = fileStr2;
	}

	public String getFileStr3() {
		return fileStr3;
	}

	public void setFileStr3(String fileStr3) {
		this.fileStr3 = fileStr3;
	}
	

	
	



}
