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
@Table(name = "payment_detail")
public class PaymentDetail  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "payment_table_id", referencedColumnName = "id")
    @ManyToOne
    private PaymentTable paymentTable;
    
    @Column(name = "payment_mode")
    private String paymentMode;
    
    @Column(name = "Instrument_no")
    private String InstrumentNo;
    
    @JoinColumn(name = "bank_id", referencedColumnName = "bank_id")
	@ManyToOne
	private Bank bankId;
    
    @Column(name = "Instrument_date")
    private String InstrumentDate;
    
    @Column(name = "amount")
    private BigDecimal amount;
    
    
    public PaymentDetail() {
    }

    public PaymentDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public PaymentTable getPaymentTable() {
		return paymentTable;
	}

	public void setPaymentTable(PaymentTable paymentTable) {
		this.paymentTable = paymentTable;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getInstrumentNo() {
		return InstrumentNo;
	}

	public void setInstrumentNo(String instrumentNo) {
		InstrumentNo = instrumentNo;
	}

	public String getInstrumentDate() {
		return InstrumentDate;
	}

	public void setInstrumentDate(String instrumentDate) {
		InstrumentDate = instrumentDate;
	}

	public Bank getBankId() {
		return bankId;
	}

	public void setBankId(Bank bankId) {
		this.bankId = bankId;
	}

}
