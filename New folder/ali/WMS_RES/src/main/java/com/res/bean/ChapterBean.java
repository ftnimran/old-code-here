package com.res.bean;

public class ChapterBean {

	private Long id;
	 
    private String chapterNumber;
	
	private String chapterName;
	
	private String remarks;
	
	private String numberOfItems;
	
	private SORBean sorBean;
	
	private Integer index;
	
    private String actionPerformedBy;
	
	public String getActionPerformedBy() {
		return actionPerformedBy;
	}

	public void setActionPerformedBy(String actionPerformedBy) {
		this.actionPerformedBy = actionPerformedBy;
	}
	
	public ChapterBean() {
	}

	public ChapterBean(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChapterNumber() {
		return chapterNumber;
	}

	

	public void setChapterNumber(String chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public void setNumberOfItems(String numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public String getNumberOfItems() {
		return numberOfItems;
	}

	public SORBean getSorBean() {
		return sorBean;
	}

	public void setSorBean(SORBean sorBean) {
		this.sorBean = sorBean;
	}


	
}