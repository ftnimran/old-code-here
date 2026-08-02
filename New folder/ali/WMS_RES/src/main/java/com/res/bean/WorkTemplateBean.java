package com.res.bean;

import java.util.List;

/**
 *
 * @author COE-13
 */
public class WorkTemplateBean {

	private Long id;

	private String sorItemNo;

	private String sorType;

	private String itemDesc;

	private Boolean measureLength;

	private Boolean measureWidth;

	private Boolean measureHeightDepth;

	private String unit;

	private Boolean labourComponent;

	private Float sequenceNo;

	private Boolean hasChild;

	private Boolean enabled;

	private Boolean group;

	private WorkTemplateBean parentItem;

	private List<WorkTemplateBean> childItems;

	private Long workTypeId;

	private Long standardTemplateTypeId;

	private String no;

	private String length;

	private String width;

	private String heightDepth;

	private String quantity;

	private String rate;
	private String labourComponentValue;
	private String amount;
	private String rateLabour;
	private String amountMaterial;
	private String amoountMachinery;
	private String amountLooseningSoil;
	private String amountExcavation;

	private boolean readOnly;

	private Integer serialNo;

	private Integer cumulativeChildsCount;
	
	private Integer childsCount;

	private boolean isNew;

	private boolean isRateReadOnly;

	private boolean isUnitReadOnly;

	private boolean isSorItemNoReadOnly;

	private boolean isDescReadOnly;
	
	private boolean isLeafNode;

	private Integer index;
	private Integer parentIndex;
	private Short templateType;
	
	public Short getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Short templateType) {
		this.templateType = templateType;
	}

	public Integer getChildsCount() {
		return childsCount;
	}

	public void setChildsCount(Integer childsCount) {
		this.childsCount = childsCount;
	}

	private Long parentId;
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	private boolean lastElement;

	public boolean isLastElement() {
		return lastElement;
	}

	public void setLastElement(boolean lastElement) {
		this.lastElement = lastElement;
	}

	public boolean isLeafNode() {
		return isLeafNode;
	}

	public void setLeafNode(boolean isLeafNode) {
		this.isLeafNode = isLeafNode;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(Integer parentIndex) {
		this.parentIndex = parentIndex;
	}

	public boolean isSorItemNoReadOnly() {
		return isSorItemNoReadOnly;
	}

	public void setSorItemNoReadOnly(boolean isSorItemNoReadOnly) {
		this.isSorItemNoReadOnly = isSorItemNoReadOnly;
	}

	public boolean isDescReadOnly() {
		return isDescReadOnly;
	}

	public void setDescReadOnly(boolean isDescReadOnly) {
		this.isDescReadOnly = isDescReadOnly;
	}

	public boolean isRateReadOnly() {
		return isRateReadOnly;
	}

	public void setRateReadOnly(boolean isRateReadOnly) {
		this.isRateReadOnly = isRateReadOnly;
	}

	public boolean isUnitReadOnly() {
		return isUnitReadOnly;
	}

	public void setUnitReadOnly(boolean isUnitReadOnly) {
		this.isUnitReadOnly = isUnitReadOnly;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public Integer getCumulativeChildsCount() {
		return cumulativeChildsCount;
	}

	public void setCumulativeChildsCount(Integer cumulativeChildsCount) {
		this.cumulativeChildsCount = cumulativeChildsCount;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getRateLabour() {
		return rateLabour;
	}

	public void setRateLabour(String rateLabour) {
		this.rateLabour = rateLabour;
	}

	public String getAmountMaterial() {
		return amountMaterial;
	}

	public void setAmountMaterial(String amountMaterial) {
		this.amountMaterial = amountMaterial;
	}

	public String getAmoountMachinery() {
		return amoountMachinery;
	}

	public void setAmoountMachinery(String amoountMachinery) {
		this.amoountMachinery = amoountMachinery;
	}

	public String getAmountLooseningSoil() {
		return amountLooseningSoil;
	}

	public void setAmountLooseningSoil(String amountLooseningSoil) {
		this.amountLooseningSoil = amountLooseningSoil;
	}

	public String getAmountExcavation() {
		return amountExcavation;
	}

	public void setAmountExcavation(String amountExcavation) {
		this.amountExcavation = amountExcavation;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getLabourComponentValue() {
		return labourComponentValue;
	}

	public void setLabourComponentValue(String labourComponentValue) {
		this.labourComponentValue = labourComponentValue;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeightDepth() {
		return heightDepth;
	}

	public void setHeightDepth(String heightDepth) {
		this.heightDepth = heightDepth;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public WorkTemplateBean() {
	}

	public WorkTemplateBean(Long id) {
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

	public WorkTemplateBean getParentItem() {
		return parentItem;
	}

	public void setParentItem(WorkTemplateBean parentItem) {
		this.parentItem = parentItem;
	}

	public List<WorkTemplateBean> getChildItems() {
		return childItems;
	}

	public void setChildItems(List<WorkTemplateBean> childItems) {
		this.childItems = childItems;
	}

	public Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}

	
	public Long getStandardTemplateTypeId() {
		return standardTemplateTypeId;
	}

	public void setStandardTemplateTypeId(Long standardTemplateTypeId) {
		this.standardTemplateTypeId = standardTemplateTypeId;
	}

	public Boolean getGroup() {
		return group;
	}

	public void setGroup(Boolean group) {
		this.group = group;
	}

	public boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

}
