package com.res.entity.pulia;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Meta {
	String instanceID;
	
	public Meta() {
	}
	public String getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	
}