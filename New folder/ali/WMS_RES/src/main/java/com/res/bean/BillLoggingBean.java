package com.res.bean;

import java.math.BigDecimal;
import java.util.List;


public class BillLoggingBean{

	private Integer logId;
	
	private Integer index;
	
    private Long id;
    
    private String billNo;
    
    private String billDate;
    
    private String billType;
    
    private BigDecimal billAmount;
    
    private BigDecimal billAmountWithoutDeduction;
    
    private String mbNo;
    
    private String mbPageNo;
    
    private String measurementBy;
    
    private Long measurementById;
    
    private BigDecimal securityDeposit = new BigDecimal(0);
    
    private BigDecimal incomeTax = new BigDecimal(0);
    
    private BigDecimal upkar = new BigDecimal(0);
    
    private BigDecimal royalty = new BigDecimal(0);
    
    private BigDecimal other = new BigDecimal(0);
    
    private BigDecimal costOfBillForm = new BigDecimal(0);
    
    private Long prevStatusId;
    
    private String prevstatus;
    
    private Long statusId;
    
    private String status;
    
    private Integer billIndex;
    
    private Long workId;
    
    private WorkBean work;
    
    private List<BillItemLoggingBean> billItems;
    
    private String lastBillNo;
    
    private String lastBillDate;
    
    private Integer lastBillIndex;
    
    private BigDecimal totalAmountPreviousBills;//all previous bills
    
    private BigDecimal totalAmountUpToDate;
    
    private BigDecimal totalAmountPreviousBill;//Only the last bill
    
    private String createdBy;
    
    private BigDecimal gst = new BigDecimal(0);
    
    private BigDecimal addOthers = new BigDecimal(0);
    
    private BigDecimal miscDeposit  = new BigDecimal(0);
    
    private BigDecimal performanceGuarantee  = new BigDecimal(0);
    
    private BigDecimal advancePayments  = new BigDecimal(0);
    
	private UserBean inspectedBy;
	
	private UserBean measurementByBill;
	
	private UserBean inspectedByEE;
	
	public UserBean getInspectedByEE() {
		return inspectedByEE;
	}

	public void setInspectedByEE(UserBean inspectedByEE) {
		this.inspectedByEE = inspectedByEE;
	}

	private BigDecimal remainingAmountForPayment  = new BigDecimal(0);

	 private boolean isRevised;
	 private boolean toShow;
	 
	
    public BillLoggingBean() {
    }

    public BillLoggingBean(Long id) {
    	this.id= id;
    }
    
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public String getMbNo() {
        return mbNo;
    }

    public void setMbNo(String mbNo) {
        this.mbNo = mbNo;
    }

    public String getMbPageNo() {
        return mbPageNo;
    }

    public void setMbPageNo(String mbPageNo) {
        this.mbPageNo = mbPageNo;
    }

    public String getMeasurementBy() {
        return measurementBy;
    }

    public void setMeasurementBy(String measurementBy) {
        this.measurementBy = measurementBy;
    }

    public BigDecimal getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(BigDecimal securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getUpkar() {
        return upkar;
    }

    public void setUpkar(BigDecimal upkar) {
        this.upkar = upkar;
    }

    public BigDecimal getRoyalty() {
        return royalty;
    }

    public void setRoyalty(BigDecimal royalty) {
        this.royalty = royalty;
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    public BigDecimal getCostOfBillForm() {
        return costOfBillForm;
    }

    public void setCostOfBillForm(BigDecimal costOfBillForm) {
        this.costOfBillForm = costOfBillForm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBillIndex() {
        return billIndex;
    }

    public void setBillIndex(Integer billIndex) {
        this.billIndex = billIndex;
    }

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public WorkBean getWork() {
		return work;
	}

	public void setWork(WorkBean workBean) {
		this.work = workBean;
	}

	public String getLastBillNo() {
		return lastBillNo;
	}

	public void setLastBillNo(String lastBillNo) {
		this.lastBillNo = lastBillNo;
	}

	public Integer getLastBillIndex() {
		return lastBillIndex;
	}

	public void setLastBillIndex(Integer lastBillIndex) {
		this.lastBillIndex = lastBillIndex;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public BigDecimal getTotalAmountPreviousBills() {
		return totalAmountPreviousBills;
	}

	public void setTotalAmountPreviousBills(BigDecimal totalAmountPreviousBills) {
		this.totalAmountPreviousBills = totalAmountPreviousBills;
	}

	public BigDecimal getTotalAmountUpToDate() {
		return totalAmountUpToDate;
	}

	public void setTotalAmountUpToDate(BigDecimal totalAmountUpToDate) {
		this.totalAmountUpToDate = totalAmountUpToDate;
	}

	public BigDecimal getTotalAmountPreviousBill() {
		return totalAmountPreviousBill;
	}

	public void setTotalAmountPreviousBill(BigDecimal totalAmountPreviousBill) {
		this.totalAmountPreviousBill = totalAmountPreviousBill;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getGst() {
		return gst;
	}

	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}

	public BigDecimal getAddOthers() {
		return addOthers;
	}

	public void setAddOthers(BigDecimal addOthers) {
		this.addOthers = addOthers;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public BigDecimal getMiscDeposit() {
		return miscDeposit;
	}

	public void setMiscDeposit(BigDecimal miscDeposit) {
		this.miscDeposit = miscDeposit;
	}

	public BigDecimal getPerformanceGuarantee() {
		return performanceGuarantee;
	}

	public void setPerformanceGuarantee(BigDecimal performanceGuarantee) {
		this.performanceGuarantee = performanceGuarantee;
	}

	public BigDecimal getAdvancePayments() {
		return advancePayments;
	}

	public void setAdvancePayments(BigDecimal advancePayments) {
		this.advancePayments = advancePayments;
	}

	public Long getPrevStatusId() {
		return prevStatusId;
	}

	public void setPrevStatusId(Long prevStatusId) {
		this.prevStatusId = prevStatusId;
	}

	public String getPrevstatus() {
		return prevstatus;
	}

	public void setPrevstatus(String prevstatus) {
		this.prevstatus = prevstatus;
	}

	public UserBean getInspectedBy() {
		return inspectedBy;
	}

	public void setInspectedBy(UserBean inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	public Long getMeasurementById() {
		return measurementById;
	}

	public void setMeasurementById(Long measurementById) {
		this.measurementById = measurementById;
	}

	public BigDecimal getRemainingAmountForPayment() {
		return remainingAmountForPayment;
	}

	public void setRemainingAmountForPayment(BigDecimal remainingAmountForPayment) {
		this.remainingAmountForPayment = remainingAmountForPayment;
	}

	public boolean getIsRevised() {
		return isRevised;
	}

	public void setIsRevised(boolean isRevised) {
		this.isRevised = isRevised;
	}

	public String getLastBillDate() {
		return lastBillDate;
	}

	public void setLastBillDate(String lastBillDate) {
		this.lastBillDate = lastBillDate;
	}

	public boolean getToShow() {
		return toShow;
	}

	public void setToShow(boolean toShow) {
		this.toShow = toShow;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public void setBillItems(List<BillItemLoggingBean> billItems) {
		this.billItems = billItems;
	}

	public List<BillItemLoggingBean> getBillItems() {
		return billItems;
	}

	public BigDecimal getBillAmountWithoutDeduction() {
		return billAmountWithoutDeduction;
	}

	public void setBillAmountWithoutDeduction(BigDecimal billAmountWithoutDeduction) {
		this.billAmountWithoutDeduction = billAmountWithoutDeduction;
	}

	public UserBean getMeasurementByBill() {
		return measurementByBill;
	}

	public void setMeasurementByBill(UserBean measurementByBill) {
		this.measurementByBill = measurementByBill;
	}
	 
}
