package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="budget_allotment_ee_office")
public class BudgetAllotmentEEOffice extends Auditable implements Serializable {


	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private String amount;


    @JoinColumn(name = "ACCOUNT_HEAD", referencedColumnName = "ID")
	@OneToOne
	private AccountHead accountHead;
    
    @JoinColumn(name = "mst_offices_id", referencedColumnName = "id")
    @ManyToOne
    private Office office;
    
    @JoinColumn(name = "budget_allotment_Id", referencedColumnName = "id")
    @ManyToOne
    private BudgetAllotment budgetAllotment;
    
    @Column(name = "lapsed")
    private boolean lapsed;
    
    public BudgetAllotmentEEOffice(Long id) {
		super();
		this.id = id;
	}
    
    public BudgetAllotmentEEOffice(String amount, AccountHead accountHead,
			Office office) {
		super();
		this.amount = amount;
		this.accountHead = accountHead;
		this.office = office;
	}

	public BudgetAllotmentEEOffice() {
	
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public BudgetAllotment getBudgetAllotment() {
		return budgetAllotment;
	}

	public void setBudgetAllotment(BudgetAllotment budgetAllotment) {
		this.budgetAllotment = budgetAllotment;
	}

	public AccountHead getAccountHead() {
		return accountHead;
	}

	public void setAccountHead(AccountHead accountHead) {
		this.accountHead = accountHead;
	}

	public boolean isLapsed() {
		return lapsed;
	}

	public void setLapsed(boolean lapsed) {
		this.lapsed = lapsed;
	}

}