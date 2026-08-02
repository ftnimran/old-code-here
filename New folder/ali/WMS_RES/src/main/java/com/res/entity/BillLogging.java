package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Audited
@Entity
@Table(name = "bill_logging")
public class BillLogging extends Auditable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;
    
    @JoinColumn(name = "id", referencedColumnName = "id")
    @OneToOne
    private Bill bill;
    
    @Column(name = "bill_no")
    private String billNo;
    
    @Column(name = "bill_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDate;
    
    @Column(name = "bill_type")
    private String billType;
    
    @Column(name = "bill_amount")
    private BigDecimal billAmount;
    
    @Column(name = "bill_amount_without_deduction")
    private BigDecimal billAmountWithoutDeduction;
    
    @Column(name = "mb_no")
    private String mbNo;
    
    @Column(name = "mb_page_no")
    private String mbPageNo;
    
    @JoinColumn(name = "measurement_by", referencedColumnName = "ID")
   	@ManyToOne
   	private Users measurementBy;
    
    @Column(name = "security_deposit")
    private BigDecimal securityDeposit;
    
    @Column(name = "income_tax")
    private BigDecimal incomeTax;
    
    @Column(name = "upkar")
    private BigDecimal upkar;
    
    @Column(name = "royalty")
    private BigDecimal royalty;
    
    @Column(name = "other")
    private BigDecimal other;
    
    @Column(name = "cost_of_bill_form")
    private BigDecimal costOfBillForm;
    
    @JoinColumn(name = "status", referencedColumnName = "status_id")
	@ManyToOne
	private MasterBillStatus status;
    
    @Column(name = "bill_index")
    private Integer billIndex;
    
    @Column(name = "gst")
    private BigDecimal gst;
    
    @Column(name = "Add_others")
    private BigDecimal addOthers;
    
    @Column(name = "Misc_Deposit")
    private BigDecimal miscDeposit;
    
    @Column(name = "Performance_Guarantee ")
    private BigDecimal performanceGuarantee ;
    
    @Column(name = "Advance_Payments")
    private BigDecimal advancePayments;
    
    @JoinColumn(name = "inspected_By", referencedColumnName = "ID")
	@ManyToOne
	private Users inspectedBy;
    
    @JoinColumn(name = "inspected_by_ee", referencedColumnName = "ID")
   	@ManyToOne
   	private Users inspectedByEE;
    
    public Users getInspectedByEE() {
		return inspectedByEE;
	}


	public void setInspectedByEE(Users inspectedByEE) {
		this.inspectedByEE = inspectedByEE;
	}


	@JoinColumn(name = "WORK_ID", referencedColumnName = "ID")
	@OneToOne
	private Work work;
    
 /*   @LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="billLogging")
	@Cascade(CascadeType.ALL)
	private List<BillItemsLogging> billItemsLoggingList;
    */
    public BillLogging() {
    }


    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
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

    public Integer getBillIndex() {
		return billIndex;
	}

    public void setBillIndex(Integer billIndex) {
        this.billIndex = billIndex;
    }

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
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

	public MasterBillStatus getStatus() {
		return status;
	}

	public void setStatus(MasterBillStatus status) {
		this.status = status;
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

	public Users getInspectedBy() {
		return inspectedBy;
	}

	public void setInspectedBy(Users inspectedBy) {
		this.inspectedBy = inspectedBy;
	}


	public Users getMeasurementBy() {
		return measurementBy;
	}

	public void setMeasurementBy(Users measurementBy) {
		this.measurementBy = measurementBy;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

/*	public List<BillItemsLogging> getBillItemsLoggingList() {
		return billItemsLoggingList;
	}

	public void setBillItemsLoggingList(List<BillItemsLogging> billItemsLoggingList) {
		this.billItemsLoggingList = billItemsLoggingList;
	}
*/
 

	public Bill getBill() {
		return bill;
	}


	public void setBill(Bill bill) {
		this.bill = bill;
	}


	public BigDecimal getBillAmountWithoutDeduction() {
		return billAmountWithoutDeduction;
	}


	public void setBillAmountWithoutDeduction(BigDecimal billAmountWithoutDeduction) {
		this.billAmountWithoutDeduction = billAmountWithoutDeduction;
	}

}