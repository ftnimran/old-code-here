package com.res.entity.pulia;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group7 {

	private String imageStr1;
	private String imageStr2;
	private String imageStr3;
	private String imageStr4;
	private String imageStr5;
	private String fileStr;
	
	
	public Group7(String imageStr1, String imageStr2, String imageStr3,
			String imageStr4, String imageStr5, String fileStr) {
		super();
		this.imageStr1 = imageStr1;
		this.imageStr2 = imageStr2;
		this.imageStr3 = imageStr3;
		this.imageStr4 = imageStr4;
		this.imageStr5 = imageStr5;
		this.fileStr = fileStr;
	}
	
	public Group7() {
		
	}
	public String getImageStr1() {
		return imageStr1;
	}

	public void setImageStr1(String imageStr1) {
		this.imageStr1 = imageStr1;
	}

	public String getImageStr2() {
		return imageStr2;
	}

	public void setImageStr2(String imageStr2) {
		this.imageStr2 = imageStr2;
	}

	public String getImageStr3() {
		return imageStr3;
	}

	public void setImageStr3(String imageStr3) {
		this.imageStr3 = imageStr3;
	}

	public String getImageStr4() {
		return imageStr4;
	}

	public void setImageStr4(String imageStr4) {
		this.imageStr4 = imageStr4;
	}

	public String getImageStr5() {
		return imageStr5;
	}

	public void setImageStr5(String imageStr5) {
		this.imageStr5 = imageStr5;
	}
	public String getFileStr() {
		return fileStr;
	}
	public void setFileStr(String fileStr) {
		this.fileStr = fileStr;
	}

	 

}
