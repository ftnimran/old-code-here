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
@Table(name = "bill_items")
public class BillItems extends Auditable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity_uptodate")
    private BigDecimal quantityUptodate;
    
    private String unit;

    @Column(name = "item_of_work")
    private String itemOfWork;
    
    @Column(name = "rate")
    private BigDecimal rate;
    
    @Column(name = "amount_uptodate")
    private BigDecimal amountUptodate;

    
    @Column(name = "amount_previous_bill")
    private BigDecimal amountPreviousBill;
    
    @Column(name = "remark")
    private String remark;
    
    @Column(name = "has_child")
   	private Boolean hasChild;
    
    @Column(name = "WORK_TEMPLATE_ID") 
  	private Integer workTemplateId;
    
    @Column(name = "PARENT_ID") 
	private Integer parentId;
    
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    @ManyToOne
    private Bill bill;

    public BillItems() {
    }

    public BillItems(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getQuantityUptodate() {
        return quantityUptodate;
    }

    public void setQuantityUptodate(BigDecimal quantityUptodate) {
        this.quantityUptodate = quantityUptodate;
    }

    public String getItemOfWork() {
        return itemOfWork;
    }

    public void setItemOfWork(String itemOfWork) {
        this.itemOfWork = itemOfWork;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmountUptodate() {
        return amountUptodate;
    }

    public void setAmountUptodate(BigDecimal amountUptodate) {
        this.amountUptodate = amountUptodate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getAmountPreviousBill() {
		return amountPreviousBill;
	}

	public void setAmountPreviousBill(BigDecimal amountPreviousBill) {
		this.amountPreviousBill = amountPreviousBill;
	}

	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getWorkTemplateId() {
		return workTemplateId;
	}

	public void setWorkTemplateId(Integer workTemplateId) {
		this.workTemplateId = workTemplateId;
	}
}
