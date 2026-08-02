package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author COE-13
 */
@Entity
@Table(name = "work_template")
public class WorkTemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;

	@JoinColumn(name = "WORK_TYPE_ID", referencedColumnName = "WORK_TYPE_ID")
	@ManyToOne
	private WorkType workType;

	@JoinColumn(name = "STANDARD_TEMPLATE_TYPE_ID", referencedColumnName = "ID")
	@ManyToOne
	private StandardTemplateType standardTemplateType;

	@Size(max = 45)
	@Column(name = "sor_item_no")
	private String sorItemNo;

	@Size(max = 45)
	@Column(name = "sor_type")
	private String sorType;

	@Size(max = 1000)
	@Column(name = "item_desc")
	private String itemDesc;

	@Column(name = "measure_length")
	private Boolean measureLength;

	@Column(name = "measure_width")
	private Boolean measureWidth;

	@Column(name = "measure_height_depth")
	private Boolean measureHeightDepth;

	@Size(max = 45)
	@Column(name = "unit")
	private String unit;

	@Column(name = "labour_component")
	private Boolean labourComponent;

	@Column(name = "sequence_no")
	private Float sequenceNo;

	@Column(name = "has_child")
	private Boolean hasChild;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "group")
	private Boolean group;

	@Size(max = 45)
	@Column(name = "rate")
	private String rate;

	@Column(name = "template_type")
	private Short templateType;

	@OneToMany(mappedBy = "parentItem")
	private Collection<WorkTemplate> childItems;

	@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
	@ManyToOne
	private WorkTemplate parentItem;

	@Column(name = "length")
	private BigDecimal length;

	@Column(name = "width")
	private BigDecimal width;

	@Column(name = "height_depth")
	private BigDecimal heightDepth;

	@Column(name = "nos")
	private BigDecimal no;

	@Column(name = "quantity")
	private BigDecimal quantity;

	public Short getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Short templateType) {
		this.templateType = templateType;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public WorkTemplate() {
	}

	public WorkTemplate(Long id) {
		this.id = id;
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

	public String getSorType() {
		return sorType;
	}

	public void setSorType(String sorType) {
		this.sorType = sorType;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Boolean getMeasureLength() {
		return measureLength;
	}

	public void setMeasureLength(Boolean measureLength) {
		this.measureLength = measureLength;
	}

	public Boolean getMeasureWidth() {
		return measureWidth;
	}

	public void setMeasureWidth(Boolean measureWidth) {
		this.measureWidth = measureWidth;
	}

	public Boolean getMeasureHeightDepth() {
		return measureHeightDepth;
	}

	public void setMeasureHeightDepth(Boolean measureHeightDepth) {
		this.measureHeightDepth = measureHeightDepth;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean getLabourComponent() {
		return labourComponent;
	}

	public void setLabourComponent(Boolean labourComponent) {
		this.labourComponent = labourComponent;
	}

	public Float getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Float sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<WorkTemplate> getChildItems() {
		return childItems;
	}

	public void setChildItems(Collection<WorkTemplate> childItems) {
		this.childItems = childItems;
	}

	public WorkTemplate getParentItem() {
		return parentItem;
	}

	public void setParentItem(WorkTemplate parentItem) {
		this.parentItem = parentItem;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public StandardTemplateType getStandardTemplateType() {
		return standardTemplateType;
	}

	public void setStandardTemplateType(
			StandardTemplateType standardTemplateType) {
		this.standardTemplateType = standardTemplateType;
	}

	public Boolean getGroup() {
		return group;
	}

	public void setGroup(Boolean group) {
		this.group = group;
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

	public BigDecimal getNo() {
		return no;
	}

	public void setNo(BigDecimal no) {
		this.no = no;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
