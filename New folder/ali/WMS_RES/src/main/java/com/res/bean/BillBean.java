package com.res.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;


public class BillBean{

	private Integer index;
	
	private String workRequisitionNo;
	
    public String getWorkRequisitionNo() {
		return workRequisitionNo;
	}

	public void setWorkRequisitionNo(String workRequisitionNo) {
		this.workRequisitionNo = workRequisitionNo;
	}

	private Long id;
    
    private String inspectionPhysicalStage;
    
    public String getInspectionPhysicalStage() {
		return inspectionPhysicalStage;
	}

	public void setInspectionPhysicalStage(String inspectionPhysicalStage) {
		this.inspectionPhysicalStage = inspectionPhysicalStage;
	}

	private String billNo;
    
    private String billDate;
    
    private String billType;
    
    private BigDecimal billAmount  = new BigDecimal(0);
    
    private BigDecimal billAmountWithoutDeduction  = new BigDecimal(0);
    
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
    
    private List<BillItemBean> billItems;
    
    private String lastBillNo;
    
    private String lastBillDate;
    
    private Integer lastBillIndex;
    
    private BigDecimal totalAmountPreviousBills = new BigDecimal(0);//all previous bills
    
    private BigDecimal totalAmountPreviousBillsWOgstWOaddOthers;
    
	private BigDecimal totalAmountUpToDate = new BigDecimal(0); 
    
    private BigDecimal totalAmountPreviousBill = new BigDecimal(0);//Only the last bill
    
    private String createdBy;
    
    private BigDecimal gst = new BigDecimal(0);
    
    private BigDecimal addOthers = new BigDecimal(0);
    
    private BigDecimal miscDeposit  = new BigDecimal(0);
    
    private BigDecimal performanceGuarantee  = new BigDecimal(0);
    
    private BigDecimal advancePayments  = new BigDecimal(0);
    
	private UserBean inspectedBy;   
	
	private UserBean inspectedByEE;
	
	private UserBean inspectedBySubEngg;
	
	private UserBean inspectedBySDO;
	
	private boolean inspectionDoneBySubEngg;
	
	private boolean inspectionDoneBySDO;
	
	private boolean manualBill;
	
	private Long inspectedById;
	
	private Long inspectedByIdEE;
	
	private Long inspectedByIdSubEngg;
	
	private Long inspectedByIdSDO;
	
	
	private BigDecimal remainingAmountForPayment  = new BigDecimal(0);

	 private boolean isRevised;
	 private boolean toShow;
	 
	 private boolean contractorBillFlag;
	 private boolean selfBillFlag;
	 
	 //richa 
	 private BigDecimal paidAmount = new BigDecimal(0); 
	 private String paidDate ;
	//
	 
	private Long workStatusId;
	    
	private String workStatus;
	
	private String remark;
	
	private Long workEstimationId;
	
	private boolean inspectionDoneByAe;
	
	private boolean inspectionDoneByEE;
	
	private BigDecimal gstForRemaining;
	
	private BigDecimal addOthersForRemaining;
	
	private BigDecimal billAmountWithoutGstAndDeductions;
	
	private BigDecimal remainingAmountForPaymentWithoutGst;
	
	private UserBean measurementByBill;
	
	private String billRemark;
	
	private String createdByDesignation;
	
   
    private String subeBillRemark;
    
    
    private String eeBillRemark;
    
    private String sdoBillRemark;
    
 
    private String contractorBillRemark;
    
    private String workName;
    
    private String pacAmount;
    
    private BigDecimal finalAsBillingAmount;
    private boolean estimationRevised;
	
	
	
/*	private Date inspectedByDateTime;
	private Date inspectedByDateTimeEE;
	
	private String inspectedByDateTimeString;
	private String inspectedByDateTimeEEString;*/
	
	public BigDecimal getFinalAsBillingAmount() {
		return finalAsBillingAmount;
	}

	public void setFinalAsBillingAmount(BigDecimal finalAsBillingAmount) {
		this.finalAsBillingAmount = finalAsBillingAmount;
	}

	public boolean isEstimationRevised() {
		return estimationRevised;
	}

	public void setEstimationRevised(boolean estimationRevised) {
		this.estimationRevised = estimationRevised;
	}

	private String inspectionUploadedDate;
	
    public BigDecimal getRemainingAmountForPaymentWithoutGst() {
		return remainingAmountForPaymentWithoutGst;
	}

	public void setRemainingAmountForPaymentWithoutGst(
			BigDecimal remainingAmountForPaymentWithoutGst) {
		this.remainingAmountForPaymentWithoutGst = remainingAmountForPaymentWithoutGst;
	}

	public BigDecimal getBillAmountWithoutGstAndDeductions() {
		return billAmountWithoutGstAndDeductions;
	}

	public void setBillAmountWithoutGstAndDeductions(BigDecimal billAmountWithoutGstAndDeductions) {
		this.billAmountWithoutGstAndDeductions = billAmountWithoutGstAndDeductions;
	}

	public BigDecimal getGstForRemaining() {
		return gstForRemaining;
	}

	public void setGstForRemaining(BigDecimal gstForRemaining) {
		this.gstForRemaining = gstForRemaining;
	}

	public BigDecimal getAddOthersForRemaining() {
		return addOthersForRemaining;
	}

	public void setAddOthersForRemaining(BigDecimal addOthersForRemaining) {
		this.addOthersForRemaining = addOthersForRemaining;
	}

	public void setRevised(boolean isRevised) {
		this.isRevised = isRevised;
	}

	public boolean isInspectionDoneByAe() {
		return inspectionDoneByAe;
	}

	public void setInspectionDoneByAe(boolean inspectionDoneByAe) {
		this.inspectionDoneByAe = inspectionDoneByAe;
	}

	public boolean isInspectionDoneByEE() {
		return inspectionDoneByEE;
	}

	public void setInspectionDoneByEE(boolean inspectionDoneByEE) {
		this.inspectionDoneByEE = inspectionDoneByEE;
	}

	public UserBean getInspectedByEE() {
		return inspectedByEE;
	}

	public void setInspectedByEE(UserBean inspectedByEE) {
		this.inspectedByEE = inspectedByEE;
	}

	public BillBean() {
    }

    public BillBean(Long id) {
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

	
	public String getPacAmount() {
		return pacAmount;
	}

	public void setPacAmount(String pacAmount) {
		this.pacAmount = pacAmount;
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

	public List<BillItemBean> getBillItems() {
		return billItems;
	}

	public void setBillItems(List<BillItemBean> billItems) {
		this.billItems = billItems;
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

	public boolean isManualBill() {
		return manualBill;
	}

	public void setManualBill(boolean manualBill) {
		this.manualBill = manualBill;
	}

	public BigDecimal getBillAmountWithoutDeduction() {
		return billAmountWithoutDeduction;
	}

	public void setBillAmountWithoutDeduction(BigDecimal billAmountWithoutDeduction) {
		this.billAmountWithoutDeduction = billAmountWithoutDeduction;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	public Long getWorkStatusId() {
		return workStatusId;
	}

	public void setWorkStatusId(Long workStatusId) {
		this.workStatusId = workStatusId;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getWorkEstimationId() {
		return workEstimationId;
	}

	public void setWorkEstimationId(Long workEstimationId) {
		this.workEstimationId = workEstimationId;
	}
	 public BigDecimal getTotalAmountPreviousBillsWOgstWOaddOthers() {
			return totalAmountPreviousBillsWOgstWOaddOthers;
		}

		public void setTotalAmountPreviousBillsWOgstWOaddOthers(BigDecimal totalAmountPreviousBillsWOgstWOaddOthers) {
			this.totalAmountPreviousBillsWOgstWOaddOthers = totalAmountPreviousBillsWOgstWOaddOthers;
		}

		public UserBean getMeasurementByBill() {
			return measurementByBill;
		}

		public void setMeasurementByBill(UserBean measurementByBill) {
			this.measurementByBill = measurementByBill;
		}

		public Long getInspectedById() {
			return inspectedById;
		}

		public void setInspectedById(Long inspectedById) {
			this.inspectedById = inspectedById;
		}

		public Long getInspectedByIdEE() {
			return inspectedByIdEE;
		}

		public void setInspectedByIdEE(Long inspectedByIdEE) {
			this.inspectedByIdEE = inspectedByIdEE;
		}

		public String getBillRemark() {
			return billRemark;
		}

		public void setBillRemark(String billRemark) {
			this.billRemark = billRemark;
		}

		public String getCreatedByDesignation() {
			return createdByDesignation;
		}

		public void setCreatedByDesignation(String createdByDesignation) {
			this.createdByDesignation = createdByDesignation;
		}

	/*	public Date getInspectedByDateTime() {
			return inspectedByDateTime;
		}

		public void setInspectedByDateTime(Date inspectedByDateTime) {
			this.inspectedByDateTime = inspectedByDateTime;
		}

		public Date getInspectedByDateTimeEE() {
			return inspectedByDateTimeEE;
		}

		public void setInspectedByDateTimeEE(Date inspectedByDateTimeEE) {
			this.inspectedByDateTimeEE = inspectedByDateTimeEE;
		}

		public String getInspectedByDateTimeString() {
			return inspectedByDateTimeString;
		}

		public void setInspectedByDateTimeString(String inspectedByDateTimeString) {
			this.inspectedByDateTimeString = inspectedByDateTimeString;
		}

		public String getInspectedByDateTimeEEString() {
			return inspectedByDateTimeEEString;
		}

		public void setInspectedByDateTimeEEString(String inspectedByDateTimeEEString) {
			this.inspectedByDateTimeEEString = inspectedByDateTimeEEString;
		}*/

		public String getInspectionUploadedDate() {
			return inspectionUploadedDate;
		}

		public void setInspectionUploadedDate(String inspectionUploadedDate) {
			this.inspectionUploadedDate = inspectionUploadedDate;
		}

		public String getSubeBillRemark() {
			return subeBillRemark;
		}

		public void setSubeBillRemark(String subeBillRemark) {
			this.subeBillRemark = subeBillRemark;
		}

		public String getEeBillRemark() {
			return eeBillRemark;
		}

		public void setEeBillRemark(String eeBillRemark) {
			this.eeBillRemark = eeBillRemark;
		}

		public String getContractorBillRemark() {
			return contractorBillRemark;
		}

		public void setContractorBillRemark(String contractorBillRemark) {
			this.contractorBillRemark = contractorBillRemark;
		}

		public boolean isContractorBillFlag() {
			return contractorBillFlag;
		}

		public void setContractorBillFlag(boolean contractorBillFlag) {
			this.contractorBillFlag = contractorBillFlag;
		}

		public boolean isSelfBillFlag() {
			return selfBillFlag;
		}

		public void setSelfBillFlag(boolean selfBillFlag) {
			this.selfBillFlag = selfBillFlag;
		}

		public String getWorkName() {
			return workName;
		}

		public void setWorkName(String workName) {
			this.workName = workName;
		}

		

		public UserBean getInspectedBySDO() {
			return inspectedBySDO;
		}

		public void setInspectedBySDO(UserBean inspectedBySDO) {
			this.inspectedBySDO = inspectedBySDO;
		}


		public Long getInspectedByIdSDO() {
			return inspectedByIdSDO;
		}

		public void setInspectedByIdSDO(Long inspectedByIdSDO) {
			this.inspectedByIdSDO = inspectedByIdSDO;
		}

		public String getSdoBillRemark() {
			return sdoBillRemark;
		}

		public void setSdoBillRemark(String sdoBillRemark) {
			this.sdoBillRemark = sdoBillRemark;
		}

		public UserBean getInspectedBySubEngg() {
			return inspectedBySubEngg;
		}

		public void setInspectedBySubEngg(UserBean inspectedBySubEngg) {
			this.inspectedBySubEngg = inspectedBySubEngg;
		}

		public Long getInspectedByIdSubEngg() {
			return inspectedByIdSubEngg;
		}

		public void setInspectedByIdSubEngg(Long inspectedByIdSubEngg) {
			this.inspectedByIdSubEngg = inspectedByIdSubEngg;
		}

		public boolean isInspectionDoneBySubEngg() {
			return inspectionDoneBySubEngg;
		}

		public void setInspectionDoneBySubEngg(boolean inspectionDoneBySubEngg) {
			this.inspectionDoneBySubEngg = inspectionDoneBySubEngg;
		}

		public boolean isInspectionDoneBySDO() {
			return inspectionDoneBySDO;
		}

		public void setInspectionDoneBySDO(boolean inspectionDoneBySDO) {
			this.inspectionDoneBySDO = inspectionDoneBySDO;
		}
		
		


}
