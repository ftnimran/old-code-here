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

@Entity
@Table(name = "inspection_answer_image_cc")
public class InspectionAnswerImageCC implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JoinColumn(name = "DOCUMENT_ID", referencedColumnName = "ID")
	@OneToOne
	private DocumentUpload documentUpload;
	
	@JoinColumn(name = "work_Id", referencedColumnName = "id") 
	@ManyToOne(fetch = FetchType.LAZY)
	private Work work;
	
	@JoinColumn(name = "image_by", referencedColumnName = "id") 
	@ManyToOne
	private Users imageBy;
	
	@Column(name = "image_by_role")
	private String imageByRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DocumentUpload getDocumentUpload() {
		return documentUpload;
	}

	public void setDocumentUpload(DocumentUpload documentUpload) {
		this.documentUpload = documentUpload;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public Users getImageBy() {
		return imageBy;
	}

	public void setImageBy(Users imageBy) {
		this.imageBy = imageBy;
	}

	public String getImageByRole() {
		return imageByRole;
	}

	public void setImageByRole(String imageByRole) {
		this.imageByRole = imageByRole;
	}
 

	 

}
