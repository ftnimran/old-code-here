package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

//@Audited
@Entity
@Table(name = "bill")
public class Bill extends Auditable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
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
    
    @JoinColumn(name = "inspected_by_sub_engg", referencedColumnName = "ID")
	@ManyToOne
	private Users inspectedBySubEngg;
    
    @JoinColumn(name = "inspected_by_sdo", referencedColumnName = "ID")
	@ManyToOne
	private Users inspectedBySDO;
   
    @Column(name = "inspected_by_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inspectedByDatetime;
    
    @Column(name = "inspected_by_ee_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inspectedByEeDatetime;
    
    @Column(name = "inspected_by_sub_engg_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inspectedBySubEnggDatetime;
    
    @Column(name = "inspected_by_sdo_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inspectedBySdoDatetime;
    
    
    public Date getInspectedByDatetime() {
		return inspectedByDatetime;
	}

	public void setInspectedByDatetime(Date inspectedByDatetime) {
		this.inspectedByDatetime = inspectedByDatetime;
	}

	public Date getInspectedByEeDatetime() {
		return inspectedByEeDatetime;
	}

	public void setInspectedByEeDatetime(Date inspectedByEeDatetime) {
		this.inspectedByEeDatetime = inspectedByEeDatetime;
	}

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
	@OneToMany(mappedBy="bill")
	@Cascade(CascadeType.ALL)
	private List<BillItems> billItemsList;*/
    
    @Column(name = "is_revised")
    private boolean isRevised;
    
    @Column(name = "to_show")
    private boolean toShow;
    
    
    @Column(name = "remark")
    private String remark;
    
    @Column(name = "bill_remark")
    private String billRemark;
    
    @Column(name = "sub_engg_remark")
    private String subEnggRemark;
   
    @Column(name = "sdo_remark")
    private String sdoRemark;
    
    @Column(name = "sube_bill_remark")
    private String subeBillRemark;
    
    @Column(name = "ee_bill_remark")
    private String eeBillRemark;
    
    @Column(name = "contractor_bill_remark")
    private String contractorBillRemark;
    
    

    public Bill() {
    }

    public Bill(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

/*	public List<BillItems> getBillItemsList() {
		return billItemsList;
	}

	public void setBillItemsList(List<BillItems> billItemsList) {
		this.billItemsList = billItemsList;
	}
*/
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

	public boolean getIsRevised() {
		return isRevised;
	}

	public void setIsRevised(boolean isRevised) {
		this.isRevised = isRevised;
	}

	public boolean getToShow() {
		return toShow;
	}

	public void setToShow(boolean toShow) {
		this.toShow = toShow;
	}

	public BigDecimal getBillAmountWithoutDeduction() {
		return billAmountWithoutDeduction;
	}

	public void setBillAmountWithoutDeduction(BigDecimal billAmountWithoutDeduction) {
		this.billAmountWithoutDeduction = billAmountWithoutDeduction;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBillRemark() {
		return billRemark;
	}

	public void setBillRemark(String billRemark) {
		this.billRemark = billRemark;
	}

	public String getSubEnggRemark() {
		return subEnggRemark;
	}

	public void setSubEnggRemark(String subEnggRemark) {
		this.subEnggRemark = subEnggRemark;
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

	

	public Users getInspectedBySubEngg() {
		return inspectedBySubEngg;
	}

	public void setInspectedBySubEngg(Users inspectedBySubEngg) {
		this.inspectedBySubEngg = inspectedBySubEngg;
	}

	public Users getInspectedBySDO() {
		return inspectedBySDO;
	}

	public void setInspectedBySDO(Users inspectedBySDO) {
		this.inspectedBySDO = inspectedBySDO;
	}

	public String getSdoRemark() {
		return sdoRemark;
	}

	public void setSdoRemark(String sdoRemark) {
		this.sdoRemark = sdoRemark;
	}


	public Date getInspectedBySubEnggDatetime() {
		return inspectedBySubEnggDatetime;
	}

	public void setInspectedBySubEnggDatetime(Date inspectedBySubEnggDatetime) {
		this.inspectedBySubEnggDatetime = inspectedBySubEnggDatetime;
	}

	public Date getInspectedBySdoDatetime() {
		return inspectedBySdoDatetime;
	}

	public void setInspectedBySdoDatetime(Date inspectedBySdoDatetime) {
		this.inspectedBySdoDatetime = inspectedBySdoDatetime;
	}

	public void setRevised(boolean isRevised) {
		this.isRevised = isRevised;
	}
	
	
	

}