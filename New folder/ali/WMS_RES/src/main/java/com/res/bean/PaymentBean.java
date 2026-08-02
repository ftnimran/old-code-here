package com.res.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long billId;

	private String cashbookDate;

	private String cashbookVoucherNo;

	private Date createdDate;

	private String createdBy;

	List<PaymentDetailBean> paymentDetailBeanList = new ArrayList<PaymentDetailBean>();
	
	public PaymentBean() {
	}

	public PaymentBean(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getCashbookDate() {
		return cashbookDate;
	}

	public void setCashbookDate(String cashbookDate) {
		this.cashbookDate = cashbookDate;
	}

	public String getCashbookVoucherNo() {
		return cashbookVoucherNo;
	}

	public void setCashbookVoucherNo(String cashbookVoucherNo) {
		this.cashbookVoucherNo = cashbookVoucherNo;
	}

	public List<PaymentDetailBean> getPaymentDetailBeanList() {
		return paymentDetailBeanList;
	}

	public void setPaymentDetailBeanList(
			List<PaymentDetailBean> paymentDetailBeanList) {
		this.paymentDetailBeanList = paymentDetailBeanList;
	}

}