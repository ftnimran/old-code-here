package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "sor_item")
public class Item extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Column(name = "item_number")
    private String itemNumber;
	
    
    @Column(name = "description")
	private String description;
    
    @JoinColumn(name = "item_unit", referencedColumnName = "ID")
   	@ManyToOne
	private Unit unit;
	
	@Column(name = "rate")
	private Double rate;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chapter_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Chapter chapter;
	
	@Column(name = "length_applicable")
	private String lengthApplicable;
	
	@Column(name = "width_applicable")
	private String widthApplicable;
	
	@Column(name = "height_applicable")
	private String heightApplicable;
	
	@ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Item item;
	
	
	@Column(name = "status")
	private String status;
	
	
	@Column(name = "is_parent")
	private String isParent;
	
	@Column(name = "parent_id2")
	private Long parentId2;

    public Long getParentId2() {
		return parentId2;
	}

	public void setParentId2(Long parentId2) {
		this.parentId2 = parentId2;
	}

    public Item() {
	}
    
	public Item(Long id) {
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public String getLengthApplicable() {
		return lengthApplicable;
	}

	public void setLengthApplicable(String lengthApplicable) {
		this.lengthApplicable = lengthApplicable;
	}

	public String getWidthApplicable() {
		return widthApplicable;
	}

	public void setWidthApplicable(String widthApplicable) {
		this.widthApplicable = widthApplicable;
	}

	public String getHeightApplicable() {
		return heightApplicable;
	}

	public void setHeightApplicable(String heightApplicable) {
		this.heightApplicable = heightApplicable;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
