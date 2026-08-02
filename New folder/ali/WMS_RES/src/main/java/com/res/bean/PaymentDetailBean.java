package com.res.bean;

import java.math.BigDecimal;

public class PaymentDetailBean    {

    private Long id;

    private Long paymentTableId;
    
    private String paymentMode;
    
    private String instrumentNo;
    
    private Long bankId;
    
    private String bankName;
    
    private String instrumentDate;
    
    private BigDecimal amount;
    
    public PaymentDetailBean() {
    }

    public PaymentDetailBean(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getInstrumentNo() {
		return instrumentNo;
	}

	public void setInstrumentNo(String instrumentNo) {
		this.instrumentNo = instrumentNo;
	}

	public String getInstrumentDate() {
		return instrumentDate;
	}

	public void setInstrumentDate(String instrumentDate) {
		this.instrumentDate = instrumentDate;
	}


	public Long getPaymentTableId() {
		return paymentTableId;
	}

	public void setPaymentTableId(Long paymentTableId) {
		this.paymentTableId = paymentTableId;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	 

}
