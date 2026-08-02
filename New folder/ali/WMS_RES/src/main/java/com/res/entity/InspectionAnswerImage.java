package com.res.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "inspection_answer_image")
public class InspectionAnswerImage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JoinColumn(name = "DOCUMENT_ID", referencedColumnName = "ID")
	@OneToOne
	private DocumentUpload documentUpload;

	@JoinColumn(name = "bill_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Bill bill;

	@JoinColumn(name = "work_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Work work;

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	@JoinColumn(name = "image_by", referencedColumnName = "id")
	@ManyToOne
	private Users imageBy;

	@Column(name = "image_by_role")
	private String imageByRole;

	@Column(name = "remark")
	private String remark;

	@Column(name = "latitude")
	private float latitude;

	@Column(name = "longitude")
	private float longitude;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "image_date_time_random")
	private Date image_date_time_random;

	public Date getImage_date_time_random() {
		return image_date_time_random;
	}

	public void setImage_date_time_random(Date image_date_time_random) {
		this.image_date_time_random = image_date_time_random;
	}

	public void setImageBy(Users imageBy) {
		this.imageBy = imageBy;
	}

	public Users getImageBy() {
		return imageBy;
	}

	public String getImageByRole() {
		return imageByRole;
	}

	public void setImageByRole(String imageByRole) {
		this.imageByRole = imageByRole;
	}

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

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


}
