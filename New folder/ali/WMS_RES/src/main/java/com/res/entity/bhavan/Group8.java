package com.res.entity.bhavan;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group8 {

	private String conduitPipe;
	private String fan;

	public Group8(String conduitPipe, String fan) {
		super();
		this.conduitPipe = conduitPipe;
		this.fan = fan;
	}

	public Group8() {
	}

	public String getConduitPipe() {
		return conduitPipe;
	}

	public void setConduitPipe(String conduitPipe) {
		this.conduitPipe = conduitPipe;
	}

	public String getFan() {
		return fan;
	}

	public void setFan(String fan) {
		this.fan = fan;
	}

}
