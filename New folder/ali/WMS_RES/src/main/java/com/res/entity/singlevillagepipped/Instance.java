package com.res.entity.singlevillagepipped;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mapit06
 */
@XmlRootElement
public class Instance {
 Data DataObject;


 // Getter Methods 

 public Instance() {
	super();
}

public Data getData() {
  return DataObject;
 }

 // Setter Methods 

 public void setData(Data dataObject) {
  this.DataObject = dataObject;
 }
}