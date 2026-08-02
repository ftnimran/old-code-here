package com.res.bean;

import java.math.BigDecimal;

public class BillItemLoggingBean {

	private Long logId;
	 
    private Long id;

    private BigDecimal quantityUptodate;
    
    private String unit;

    private String itemOfWork;
    
    private String itemOfWorkNew;
    
    private BigDecimal rate;
    
    private BigDecimal amountUptodate;
    
    private BigDecimal amountPreviousBill;
    
    private BigDecimal quantityPreviousBill;

    private String remark;
    
    private Boolean hasChild;
    
  	private Integer workTemplateId;
    
	private Integer parentId;
    
    public BillItemLoggingBean() {
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

	public BigDecimal getAmountPreviousBill() {
		return amountPreviousBill;
	}

	public void setAmountPreviousBill(BigDecimal amountPreviousBill) {
		this.amountPreviousBill = amountPreviousBill;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getItemOfWorkNew() {
		return itemOfWorkNew;
	}

	public void setItemOfWorkNew(String itemOfWorkNew) {
		this.itemOfWorkNew = itemOfWorkNew;
	}

	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public Integer getWorkTemplateId() {
		return workTemplateId;
	}

	public void setWorkTemplateId(Integer workTemplateId) {
		this.workTemplateId = workTemplateId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public BigDecimal getQuantityPreviousBill() {
		return quantityPreviousBill;
	}

	public void setQuantityPreviousBill(BigDecimal quantityPreviousBill) {
		this.quantityPreviousBill = quantityPreviousBill;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}
}
