package com.res.bean;

public class ItemBean {
	private Long id;
	 
    private String itemNumber;
	
    private String subItemNumber;
	private String description;
	
	private UnitBean unit;
	
	public ItemBean(UnitBean unit) {
		this.unit = unit;
	}

	public ItemBean() {
	}

	private Double rate;

	private ChapterBean chapter;
	
	private Long chapterId;

	private Integer index;

	private String lengthApplicable;
	
	private String widthApplicable;
	
	private String heightApplicable;
	
	private Long parentId;
	
	private String isParent;

	private String parentItemNumber;
	
	private String parentDesc;
	
    private String actionPerformedBy;
    
    private String sorType;
	
	public String getSorType() {
		return sorType;
	}

	public void setSorType(String sorType) {
		this.sorType = sorType;
	}

	public String getActionPerformedBy() {
		return actionPerformedBy;
	}

	public void setActionPerformedBy(String actionPerformedBy) {
		this.actionPerformedBy = actionPerformedBy;
	}
	
	public String getParentDesc() {
		return parentDesc;
	}

	public void setParentDesc(String parentDesc) {
		this.parentDesc = parentDesc;
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public UnitBean getUnit() {
		return unit;
	}

	public void setUnit(UnitBean unit) {
		this.unit = unit;
	}

	public ChapterBean getChapter() {
		return chapter;
	}

	public void setChapter(ChapterBean chapter) {
		this.chapter = chapter;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getSubItemNumber() {
		return subItemNumber;
	}

	public void setSubItemNumber(String subItemNumber) {
		this.subItemNumber = subItemNumber;
	}

	public String getParentItemNumber() {
		return parentItemNumber;
	}

	public void setParentItemNumber(String parentItemNumber) {
		this.parentItemNumber = parentItemNumber;
	}
	
}
