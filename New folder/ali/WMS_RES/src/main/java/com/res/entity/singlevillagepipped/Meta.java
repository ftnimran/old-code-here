package com.res.entity.singlevillagepipped;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Meta {
 private String instanceID="";;


 // Getter Methods 

 public Meta() {
	super();
}

public String getInstanceID() {
  return instanceID;
 }

 // Setter Methods 

 public void setInstanceID(String instanceID) {
  this.instanceID = instanceID;
 }
}