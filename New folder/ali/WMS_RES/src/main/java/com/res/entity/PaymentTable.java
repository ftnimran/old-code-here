package com.res.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

//@Audited
@Entity
@Table(name = "Payment_Table")
public class PaymentTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    @ManyToOne
    private Bill bill;
    
    @Column(name = "cashbook_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cashbookDate;
    
    @Column(name = "cashbook_voucher_no")
    private String cashbookVoucherNo;
    
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
 
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
    public PaymentTable() {
    }

    public PaymentTable(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCashbookDate() {
		return cashbookDate;
	}

	public void setCashbookDate(Date cashbookDate) {
		this.cashbookDate = cashbookDate;
	}

	public String getCashbookVoucherNo() {
		return cashbookVoucherNo;
	}

	public void setCashbookVoucherNo(String cashbookVoucherNo) {
		this.cashbookVoucherNo = cashbookVoucherNo;
	}

    
}