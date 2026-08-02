package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Audited
@Entity
@Table(name = "work_estimation_items")
public class WorkEstimationItems extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "sor_item_no")
	private String sorItemNo;

	@Column(name = "item_desc")
	private String itemDesc;

	@Column(name = "no")
	private BigDecimal no;

	@Column(name = "length")
	private BigDecimal length;

	@Column(name = "width")
	private BigDecimal width;

	@Column(name = "height_depth")
	private BigDecimal heightDepth;

	@Column(name = "quantity")
	private BigDecimal quantity;

	@Column(name = "unit")
	private String unit;

	@Column(name = "rate")
	private BigDecimal rate;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "rate_labour")
	private BigDecimal rateLabour;

	@Column(name = "amount_labour")
	private BigDecimal amountLabour;

	@Column(name = "amount_material")
	private BigDecimal amountMaterial;

	@Column(name = "amoount_machinery")
	private BigDecimal amoountMachinery;

	@Column(name = "amount_loosening_soil")
	private BigDecimal amountLooseningSoil;

	@Column(name = "amount_excavation")
	private BigDecimal amountExcavation;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "has_child")
	private Boolean hasChild;
	
	@Column(name = "is_group")
	private Boolean group;

	@JoinColumn(name = "work_estimation_id", referencedColumnName = "id")
	@OneToOne
	private WorkEstimation workEstimation;

	public Boolean getGroup() {
		return group;
	}

	public void setGroup(Boolean group) {
		this.group = group;
	}

	public WorkEstimationItems() {
	}

	public WorkEstimationItems(Long id) {
		this.id = id;
	}

	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSorItemNo() {
		return sorItemNo;
	}

	public void setSorItemNo(String sorItemNo) {
		this.sorItemNo = sorItemNo;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public BigDecimal getNo() {
		return no;
	}

	public void setNo(BigDecimal no) {
		this.no = no;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeightDepth() {
		return heightDepth;
	}

	public void setHeightDepth(BigDecimal heightDepth) {
		this.heightDepth = heightDepth;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRateLabour() {
		return rateLabour;
	}

	public void setRateLabour(BigDecimal rateLabour) {
		this.rateLabour = rateLabour;
	}

	public BigDecimal getAmountLabour() {
		return amountLabour;
	}

	public void setAmountLabour(BigDecimal amountLabour) {
		this.amountLabour = amountLabour;
	}

	public BigDecimal getAmountMaterial() {
		return amountMaterial;
	}

	public void setAmountMaterial(BigDecimal amountMaterial) {
		this.amountMaterial = amountMaterial;
	}

	public BigDecimal getAmoountMachinery() {
		return amoountMachinery;
	}

	public void setAmoountMachinery(BigDecimal amoountMachinery) {
		this.amoountMachinery = amoountMachinery;
	}

	public BigDecimal getAmountLooseningSoil() {
		return amountLooseningSoil;
	}

	public void setAmountLooseningSoil(BigDecimal amountLooseningSoil) {
		this.amountLooseningSoil = amountLooseningSoil;
	}

	public BigDecimal getAmountExcavation() {
		return amountExcavation;
	}

	public void setAmountExcavation(BigDecimal amountExcavation) {
		this.amountExcavation = amountExcavation;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public WorkEstimation getWorkEstimation() {
		return workEstimation;
	}

	public void setWorkEstimation(WorkEstimation workEstimation) {
		this.workEstimation = workEstimation;
	}

}
