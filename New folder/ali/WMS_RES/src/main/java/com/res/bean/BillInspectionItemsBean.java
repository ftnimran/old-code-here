package com.res.bean;


public class BillInspectionItemsBean {

  
 
    private Integer id;

  	private Integer workTemplateId;
    

    private String unit;
    
   
    private Integer quantityUptodate;
    
 
    private Integer actualQuantity;
    
   

  
    private String itemOfWork;
    
   
    private String grading;
    
   
    private String remarks;
    
  
    private BillBean bill;
    
  
    private WorkBean  work;
    
    
    private BillInspectionBean  billInspection;

	
	   
	    private String createdBy;
	 
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkTemplateId() {
		return workTemplateId;
	}

	public void setWorkTemplateId(Integer workTemplateId) {
		this.workTemplateId = workTemplateId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getQuantityUptodate() {
		return quantityUptodate;
	}

	public void setQuantityUptodate(Integer quantityUptodate) {
		this.quantityUptodate = quantityUptodate;
	}

	public Integer getActualQuantity() {
		return actualQuantity;
	}

	public void setActualQuantity(Integer actualQuantity) {
		this.actualQuantity = actualQuantity;
	}

	public String getItemOfWork() {
		return itemOfWork;
	}

	public void setItemOfWork(String itemOfWork) {
		this.itemOfWork = itemOfWork;
	}

	public String getGrading() {
		return grading;
	}

	public void setGrading(String grading) {
		this.grading = grading;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BillBean getBill() {
		return bill;
	}

	public void setBill(BillBean bill) {
		this.bill = bill;
	}

	public WorkBean getWork() {
		return work;
	}

	public void setWork(WorkBean work) {
		this.work = work;
	}

	public BillInspectionBean getBillInspection() {
		return billInspection;
	}

	public void setBillInspection(BillInspectionBean billInspection) {
		this.billInspection = billInspection;
	}

	
   
}
