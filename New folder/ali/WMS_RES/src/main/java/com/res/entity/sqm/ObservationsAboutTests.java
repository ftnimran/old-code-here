package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsAboutTests {
	
	private String itemOfWorkExecuted;
	private String quantity;
	private String nameOfTest;
	private String noOfTestsRequired;
	private String noOfTestsConductedByEE;
	
	public ObservationsAboutTests() {
		
	}
	
	public ObservationsAboutTests(String itemOfWorkExecuted, String quantity, String nameOfTest,
			String noOfTestsRequired, String noOfTestsConductedByEE) {
		super();
		this.itemOfWorkExecuted = itemOfWorkExecuted;
		this.quantity = quantity;
		this.nameOfTest = nameOfTest;
		this.noOfTestsRequired = noOfTestsRequired;
		this.noOfTestsConductedByEE = noOfTestsConductedByEE;
		
	}

	public String getItemOfWorkExecuted() {
		return itemOfWorkExecuted;
	}

	public void setItemOfWorkExecuted(String itemOfWorkExecuted) {
		this.itemOfWorkExecuted = itemOfWorkExecuted;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getNameOfTest() {
		return nameOfTest;
	}

	public void setNameOfTest(String nameOfTest) {
		this.nameOfTest = nameOfTest;
	}

	public String getNoOfTestsRequired() {
		return noOfTestsRequired;
	}

	public void setNoOfTestsRequired(String noOfTestsRequired) {
		this.noOfTestsRequired = noOfTestsRequired;
	}

	public String getNoOfTestsConductedByEE() {
		return noOfTestsConductedByEE;
	}

	public void setNoOfTestsConductedByEE(String noOfTestsConductedByEE) {
		this.noOfTestsConductedByEE = noOfTestsConductedByEE;
	}

}
