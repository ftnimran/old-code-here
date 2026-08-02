package com.res.entity.singlevillagepipped;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Data {
	OverallGradingAndRemarks overallGradingAndRemarks;
 public Data(Meta metaObject, WorkDetails workDetailsObject,  String _id,ImageAndFiles imageAndFiles,OverallGradingAndRemarks overallGradingAndRemarks) {
		super();
		MetaObject = metaObject;
		WorkDetailsObject = workDetailsObject;
		
		this._id = _id;
		this.imageAndFiles = imageAndFiles;
		this.overallGradingAndRemarks=overallGradingAndRemarks;
	}
 
 

 Meta MetaObject;
 WorkDetails WorkDetailsObject;
// Components ComponentsObject;
 Bill bill;
 List<BillItems> billItems;
  ImageAndFiles imageAndFiles;
 
 public ImageAndFiles getImageAndFiles() {
	return imageAndFiles;
}

public OverallGradingAndRemarks getOverallGradingAndRemarks() {
	return overallGradingAndRemarks;
}

public void setOverallGradingAndRemarks(OverallGradingAndRemarks overallGradingAndRemarks) {
	this.overallGradingAndRemarks = overallGradingAndRemarks;
}

public void setImageAndFiles(ImageAndFiles imageAndFiles) {
	this.imageAndFiles = imageAndFiles;
}

public Data() {
	super();
}

private String _id;


 // Getter Methods 

 public Meta getMeta() {
  return MetaObject;
 }

 public WorkDetails getWorkDetails() {
  return WorkDetailsObject;
 }


 public Meta getMetaObject() {
	return MetaObject;
}

public void setMetaObject(Meta metaObject) {
	MetaObject = metaObject;
}

public WorkDetails getWorkDetailsObject() {
	return WorkDetailsObject;
}

public void setWorkDetailsObject(WorkDetails workDetailsObject) {
	WorkDetailsObject = workDetailsObject;
}



public Bill getBill() {
	return bill;
}

public void setBill(Bill bill) {
	this.bill = bill;
}



public List<BillItems> getBillItems() {
	return billItems;
}

public void setBillItems(List<BillItems> billItems) {
	this.billItems = billItems;
}

public String get_id() {
  return _id;
 }

 // Setter Methods 

 public void setMeta(Meta metaObject) {
  this.MetaObject = metaObject;
 }

 public void setWorkDetails(WorkDetails workDetailsObject) {
  this.WorkDetailsObject = workDetailsObject;
 }

 

 public void set_id(String _id) {
  this._id = _id;
 }
}