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
@Table(name = "bill_items_logging")
public class BillItemsLogging extends Auditable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

   /* @JoinColumn(name = "id", referencedColumnName = "id")
    @OneToOne
    private BillItems billItems;*/
    
    @Column(name = "id")
    private Long id;  //bill_item_id
    
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
    
    @JoinColumn(name = "bill_log_id", referencedColumnName = "log_id")
    @ManyToOne
    private BillLogging billLogging;
    
    
    public BillItemsLogging() {
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

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public BillLogging getBillLogging() {
		return billLogging;
	}

	public void setBillLogging(BillLogging billLogging) {
		this.billLogging = billLogging;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 
}