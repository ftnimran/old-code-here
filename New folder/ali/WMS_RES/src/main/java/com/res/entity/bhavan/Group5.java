package com.res.entity.bhavan;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group5 {

	private String samgriQuality2;
	private String workmanship2;
	private String lintel;
	private String door;

	public Group5() {
	}

	public Group5(String samgriQuality2, String workmanship2, String lintel,
			String door) {
		super();
		this.samgriQuality2 = samgriQuality2;
		this.workmanship2 = workmanship2;
		this.lintel = lintel;
		this.door = door;
	}

	public String getSamgriQuality2() {
		return samgriQuality2;
	}

	public void setSamgriQuality2(String samgriQuality2) {
		this.samgriQuality2 = samgriQuality2;
	}

	public String getWorkmanship2() {
		return workmanship2;
	}

	public void setWorkmanship2(String workmanship2) {
		this.workmanship2 = workmanship2;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getLintel() {
		return lintel;
	}

	public void setLintel(String lintel) {
		this.lintel = lintel;
	}

}
