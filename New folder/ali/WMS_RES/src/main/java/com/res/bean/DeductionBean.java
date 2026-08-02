package com.res.bean;

import java.math.BigDecimal;

public class DeductionBean {

	private BigDecimal totalSecurityDeposit = new BigDecimal(0);

	private BigDecimal totalIncomeTax = new BigDecimal(0);

	private BigDecimal totalUpkar = new BigDecimal(0);

	private BigDecimal totalRoyalty = new BigDecimal(0);

	private BigDecimal totalCostOfBillForm = new BigDecimal(0);
	
	private BigDecimal totalMiscDeposit = new BigDecimal(0);

	private BigDecimal totalPerformanceGuarantee = new BigDecimal(0);

	private BigDecimal totalAdvancePayments = new BigDecimal(0);
	private BigDecimal totalOther = new BigDecimal(0);
	public BigDecimal getTotalSecurityDeposit() {
		return totalSecurityDeposit;
	}
	public void setTotalSecurityDeposit(BigDecimal totalSecurityDeposit) {
		this.totalSecurityDeposit = totalSecurityDeposit;
	}
	public BigDecimal getTotalIncomeTax() {
		return totalIncomeTax;
	}
	public void setTotalIncomeTax(BigDecimal totalIncomeTax) {
		this.totalIncomeTax = totalIncomeTax;
	}
	public BigDecimal getTotalUpkar() {
		return totalUpkar;
	}
	public void setTotalUpkar(BigDecimal totalUpkar) {
		this.totalUpkar = totalUpkar;
	}
	public BigDecimal getTotalRoyalty() {
		return totalRoyalty;
	}
	public void setTotalRoyalty(BigDecimal totalRoyalty) {
		this.totalRoyalty = totalRoyalty;
	}
	public BigDecimal getTotalCostOfBillForm() {
		return totalCostOfBillForm;
	}
	public void setTotalCostOfBillForm(BigDecimal totalCostOfBillForm) {
		this.totalCostOfBillForm = totalCostOfBillForm;
	}
	public BigDecimal getTotalMiscDeposit() {
		return totalMiscDeposit;
	}
	public void setTotalMiscDeposit(BigDecimal totalMiscDeposit) {
		this.totalMiscDeposit = totalMiscDeposit;
	}
	public BigDecimal getTotalPerformanceGuarantee() {
		return totalPerformanceGuarantee;
	}
	public void setTotalPerformanceGuarantee(BigDecimal totalPerformanceGuarantee) {
		this.totalPerformanceGuarantee = totalPerformanceGuarantee;
	}
	public BigDecimal getTotalAdvancePayments() {
		return totalAdvancePayments;
	}
	public void setTotalAdvancePayments(BigDecimal totalAdvancePayments) {
		this.totalAdvancePayments = totalAdvancePayments;
	}
	public BigDecimal getTotalOther() {
		return totalOther;
	}
	public void setTotalOther(BigDecimal totalOther) {
		this.totalOther = totalOther;
	}
	
}
