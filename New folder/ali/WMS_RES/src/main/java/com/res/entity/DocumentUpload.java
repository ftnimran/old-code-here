/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aditya Singhai
 */
@Entity
@Table(name = "DOCUMENT_UPLOAD_DETAILS")
public class DocumentUpload implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long documentId;
    
    @Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "document_content")
	private Blob documentContent;
    
    @Column(name = "document_name")
	private String documentName;
    
	@Column(name = "document_type")
	private String documentType;
	
	@Column(name = "document_desc")
	private String documentDesc;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    @Column(name = "document_upload_path")
	private String documentUploadPath;
    
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
    
	@PrePersist
	protected void onCreate() {
		this.createdDate = new Date();
	}
    
    public DocumentUpload() {
    }

    public DocumentUpload(Long documentId) {
        this.documentId = documentId;
    }
    
    public DocumentUpload(Blob documentContent, String documentName,
			String documentType, Short enabled) {
		super();
		this.documentContent = documentContent;
		this.documentName = documentName;
		this.documentType = documentType;
		this.enabled = enabled;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Blob getDocumentContent() {
		return documentContent;
	}

	public void setDocumentContent(Blob documentContent) {
		this.documentContent = documentContent;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public String getDocumentUploadPath() {
		return documentUploadPath;
	}

	public void setDocumentUploadPath(String documentUploadPath) {
		this.documentUploadPath = documentUploadPath;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDocumentDesc() {
		return documentDesc;
	}

	public void setDocumentDesc(String documentDesc) {
		this.documentDesc = documentDesc;
	}
	
}
