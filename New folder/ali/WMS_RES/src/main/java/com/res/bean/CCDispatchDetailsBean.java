package com.res.bean;

import org.springframework.web.multipart.MultipartFile;

public class CCDispatchDetailsBean  {
	
	private Long Id;
	 
	private Long workId;

	private String dispatchNumber;

	private String dispatchDate;
	
	private String remarks;

	private MultipartFile file;
	
	private Long documentId;
	
	private String documentName;
	
	private Short status;
	
	private UserBean issuedBy;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getDispatchNumber() {
		return dispatchNumber;
	}

	public void setDispatchNumber(String dispatchNumber) {
		this.dispatchNumber = dispatchNumber;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public UserBean getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(UserBean issuedBy) {
		this.issuedBy = issuedBy;
	}

	
	
	
	
	
}
