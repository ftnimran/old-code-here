package com.res.bean;

import java.util.List;

import com.res.entity.InspectionAnswerImageNew;

public class BillInspectionBean {

	public BillInspectionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillInspectionBean(Long id) {
		super();
		this.id = id;
	}

	private Long id;

	private BillBean billBean;

	private WorkBean workBean;

	public BillBean getBillBean() {
		return billBean;
	}

	public void setBillBean(BillBean billBean) {
		this.billBean = billBean;
	}

	public WorkBean getWorkBean() {
		return workBean;
	}

	public void setWorkBean(WorkBean workBean) {
		this.workBean = workBean;
	}

	public Integer getExecutionAgencyId() {
		return executionAgencyId;
	}

	public void setExecutionAgencyId(Integer executionAgencyId) {
		this.executionAgencyId = executionAgencyId;
	}

	public String getOverallGrading() {
		return overallGrading;
	}

	public void setOverallGrading(String overallGrading) {
		this.overallGrading = overallGrading;
	}

	public String getOverallRemarks() {
		return overallRemarks;
	}

	public void setOverallRemarks(String overallRemarks) {
		this.overallRemarks = overallRemarks;
	}

	public String getInspectedByRole() {
		return inspectedByRole;
	}

	public void setInspectedByRole(String inspectedByRole) {
		this.inspectedByRole = inspectedByRole;
	}

	private Integer executionAgencyId;

	private String overallGrading;

	private String overallRemarks;

	/*
	 * private DocumentUpload imageStrDoc1;
	 * 
	 * @JoinColumn(name="image_str_doc_2",referencedColumnName="id")
	 * 
	 * @ManyToOne private DocumentUpload imageStrDoc2;
	 * 
	 * @JoinColumn(name="image_str_doc_3",referencedColumnName="id")
	 * 
	 * @ManyToOne private DocumentUpload imageStrDoc3;
	 * 
	 * @JoinColumn(name="image_str_doc_4",referencedColumnName="id")
	 * 
	 * @ManyToOne private DocumentUpload imageStrDoc4;
	 * 
	 * @JoinColumn(name="image_str_doc_5",referencedColumnName="id")
	 * 
	 * @ManyToOne private DocumentUpload imageStrDoc5;
	 * 
	 * @JoinColumn(name="file_str_doc",referencedColumnName="id")
	 * 
	 * @ManyToOne private DocumentUpload fileStrDoc;
	 */

	String inspectedByRole;

	private String createdBy;
	List<BillInspectionItemsBean> billInspectionItemsBean; 
	
	//new column
	List<InspectionAnswerImageNew> inspectionAnswerImages;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<BillInspectionItemsBean> getBillInspectionItemsBean() {
		return billInspectionItemsBean;
	}

	public void setBillInspectionItemsBean(List<BillInspectionItemsBean> billInspectionItemsBean) {
		this.billInspectionItemsBean = billInspectionItemsBean;
	}



	public List<InspectionAnswerImageNew> getInspectionAnswerImages() {
		return inspectionAnswerImages;
	}

	public void setInspectionAnswerImages(List<InspectionAnswerImageNew> inspectionAnswerImages) {
		this.inspectionAnswerImages = inspectionAnswerImages;
	}

	

}