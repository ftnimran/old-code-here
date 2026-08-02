package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//@Audited
@Entity
@Table(name = "bill_inspection_item")
public class BillInspectionItems extends Auditable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "template_item_id") 
  	private Integer workTemplateId;
    
    @Column(name = "unit")
    private String unit;
    
    @Column(name = "quantity_uptodate")
    private Integer quantityUptodate;
    
    //actual_quantity
    @Column(name = "actual_quantity")
    private Integer actualQuantity;
    
   

    @Column(name = "item_of_work")
    private String itemOfWork;
    
    @Column(name = "grading")
    private String grading;
    
    @Column(name = "remarks")
    private String remarks;
    
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    @ManyToOne
    private Bill bill;
    
    @JoinColumn(name = "work_id", referencedColumnName = "id")
    @ManyToOne
    private Work  work;
    
    @JoinColumn(name = "bill_inspection_id", referencedColumnName = "id")
    @ManyToOne
    private BillInspection  billInspection;
    
    @JoinColumn(name = "bill_item_id", referencedColumnName = "id")
    @ManyToOne
    private BillItems  billItems;
    
    

	 @Column(name = "created_by")
	   
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

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public BillInspection getBillInspection() {
		return billInspection;
	}

	public void setBillInspection(BillInspection billInspection) {
		this.billInspection = billInspection;
	}

	public BillItems getBillItems() {
		return billItems;
	}

	public void setBillItems(BillItems billItems) {
		this.billItems = billItems;
	}
   
}
