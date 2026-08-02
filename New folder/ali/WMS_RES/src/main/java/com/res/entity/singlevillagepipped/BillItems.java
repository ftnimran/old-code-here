/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.entity.singlevillagepipped;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BillItems {
 private String 	workTemplateId;
 private String unit;
 private String quantityUptodate;
 private String actualQuantity;
 
public BillItems() {
	//super();
	// TODO Auto-generated constructor stub
}

public String getWorkTemplateId() {
	return workTemplateId;
}

public void setWorkTemplateId(String workTemplateId) {
	this.workTemplateId = workTemplateId;
}

private String itemOfWork;
 private String grading;
 private String remarks;


 // Getter Methods 

 public String getUnit() {
  return unit;
 }

 public String getQuantityUptodate() {
  return quantityUptodate;
 }

 public String getItemOfWork() {
  return itemOfWork;
 }

 public String getGrading() {
  return grading;
 }

 public String getRemarks() {
  return remarks;
 }

 // Setter Methods 

 public void setUnit(String unit) {
  this.unit = unit;
 }

 public void setQuantityUptodate(String quantityUptodate) {
  this.quantityUptodate = quantityUptodate;
 }

 public void setItemOfWork(String itemOfWork) {
  this.itemOfWork = itemOfWork;
 }

 public void setGrading(String grading) {
  this.grading = grading;
 }

 public void setRemarks(String remarks) {
  this.remarks = remarks;
 }

public String getActualQuantity() {
	return actualQuantity;
}

public void setActualQuantity(String actualQuantity) {
	this.actualQuantity = actualQuantity;
}

public BillItems(String unit, String quantityUptodate, String actualQuantity, String itemOfWork, String grading,
		String remarks,String 	workTemplateId) {
	super();
	this.unit = unit;
	this.quantityUptodate = quantityUptodate;
	this.actualQuantity = actualQuantity;
	this.itemOfWork = itemOfWork;
	this.grading = grading;
	this.remarks = remarks;
	this.workTemplateId = workTemplateId;
}
 
}