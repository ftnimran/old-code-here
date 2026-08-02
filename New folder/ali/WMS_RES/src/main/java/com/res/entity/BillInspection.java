package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="bill_inspection")
public class BillInspection extends Auditable implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public BillInspection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillInspection(Long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@JoinColumn(name = "bill_id", referencedColumnName = "ID")
	@ManyToOne
	private Bill bill;
	
	@JoinColumn(name="work_id",referencedColumnName="id")
	@ManyToOne
	private Work work;
	
	@Column(name="execution_agency_id")
	private Integer executionAgencyId;
	
	@Column(name="overall_grading")
	private String overallGrading;
	
	@Column(name="overall_remarks")
	private String overallRemarks;
	
	@JoinColumn(name="image_str_doc_1",referencedColumnName="id")
	@ManyToOne
	private DocumentUpload imageStrDoc1;
	
	@JoinColumn(name="image_str_doc_2",referencedColumnName="id")
	@ManyToOne
	private DocumentUpload imageStrDoc2;
	
	@JoinColumn(name="image_str_doc_3",referencedColumnName="id")
	@ManyToOne
	private DocumentUpload imageStrDoc3;
	
	@JoinColumn(name="image_str_doc_4",referencedColumnName="id")
	@ManyToOne
	private DocumentUpload imageStrDoc4;
	
	@JoinColumn(name="image_str_doc_5",referencedColumnName="id")
	@ManyToOne
	private DocumentUpload imageStrDoc5;
	
	@JoinColumn(name="file_str_doc",referencedColumnName="id")
	@ManyToOne
	private DocumentUpload fileStrDoc;
	
	@Column(name="inspected_by_role")
	String inspectedByRole;


	 @Column(name = "created_by")
	   
	    private String createdBy;
	 
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

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
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

	public DocumentUpload getImageStrDoc1() {
		return imageStrDoc1;
	}

	public void setImageStrDoc1(DocumentUpload imageStrDoc1) {
		this.imageStrDoc1 = imageStrDoc1;
	}

	public DocumentUpload getImageStrDoc2() {
		return imageStrDoc2;
	}

	public void setImageStrDoc2(DocumentUpload imageStrDoc2) {
		this.imageStrDoc2 = imageStrDoc2;
	}

	public DocumentUpload getImageStrDoc3() {
		return imageStrDoc3;
	}

	public void setImageStrDoc3(DocumentUpload imageStrDoc3) {
		this.imageStrDoc3 = imageStrDoc3;
	}

	public DocumentUpload getImageStrDoc4() {
		return imageStrDoc4;
	}

	public void setImageStrDoc4(DocumentUpload imageStrDoc4) {
		this.imageStrDoc4 = imageStrDoc4;
	}

	public DocumentUpload getImageStrDoc5() {
		return imageStrDoc5;
	}

	public void setImageStrDoc5(DocumentUpload imageStrDoc5) {
		this.imageStrDoc5 = imageStrDoc5;
	}

	public DocumentUpload getFileStrDoc() {
		return fileStrDoc;
	}

	public void setFileStrDoc(DocumentUpload fileStrDoc) {
		this.fileStrDoc = fileStrDoc;
	}

	public String getInspectedByRole() {
		return inspectedByRole;
	}

	public void setInspectedByRole(String inspectedByRole) {
		this.inspectedByRole = inspectedByRole;
	}
	
	
	
	
	
	
	
	
}