package com.res.entity.pulia;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group4 {

	private String protection;
	private String wheelGuard;
	private String cautionBoard;
	private String approaches;

	public Group4(String protection, String wheelGuard, String cautionBoard,
			String approaches) {
		super();
		this.protection = protection;
		this.wheelGuard = wheelGuard;
		this.cautionBoard = cautionBoard;
		this.approaches = approaches;
	}

	public Group4() {
	}

	public String getProtection() {
		return protection;
	}

	public void setProtection(String protection) {
		this.protection = protection;
	}

	public String getWheelGuard() {
		return wheelGuard;
	}

	public void setWheelGuard(String wheelGuard) {
		this.wheelGuard = wheelGuard;
	}

	public String getCautionBoard() {
		return cautionBoard;
	}

	public void setCautionBoard(String cautionBoard) {
		this.cautionBoard = cautionBoard;
	}

	public String getApproaches() {
		return approaches;
	}

	public void setApproaches(String approaches) {
		this.approaches = approaches;
	}

}
