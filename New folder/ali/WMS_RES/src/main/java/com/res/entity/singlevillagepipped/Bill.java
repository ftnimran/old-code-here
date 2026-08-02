/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.entity.singlevillagepipped;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rakesh
 */
@XmlRootElement
public class Bill {
 public Bill(String billNo, String billType, String id) {
		super();
		this.billNo = billNo;
		this.billType = billType;
		this.id=id;
	}

private String id;
private String billNo;
 public Bill() {
	super();
	// TODO Auto-generated constructor stub
}


private String billType;


 // Getter Methods 

 public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getBillNo() {
  return billNo;
 }

 public String getBillType() {
  return billType;
 }

 // Setter Methods 

 public void setBillNo(String billNo) {
  this.billNo = billNo;
 }

 public void setBillType(String billType) {
  this.billType = billType;
 }
}