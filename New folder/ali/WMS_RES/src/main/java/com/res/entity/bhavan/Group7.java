package com.res.entity.bhavan;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group7 {

	private String plaster;
	private String floor;
	private String painting;
	private String sanitary;

	public Group7(String plaster, String floor, String painting, String sanitary) {
		super();
		this.plaster = plaster;
		this.floor = floor;
		this.painting = painting;
		this.sanitary = sanitary;
	}

	public Group7() {
	}

	public String getPlaster() {
		return plaster;
	}

	public void setPlaster(String plaster) {
		this.plaster = plaster;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getPainting() {
		return painting;
	}

	public void setPainting(String painting) {
		this.painting = painting;
	}

	public String getSanitary() {
		return sanitary;
	}

	public void setSanitary(String sanitary) {
		this.sanitary = sanitary;
	}

}
