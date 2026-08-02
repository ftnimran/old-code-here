/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "sqm_allocation_history")
public class SqmAllocationHistory  implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	/*@JoinColumn(name = "district_id", referencedColumnName = "ID")
	@ManyToOne
	private District district;*/
    //
    @Column(name = "office_id")
    private Long officeId;
    
    
    @JoinColumn(name = "work_id", referencedColumnName = "ID")
   	@ManyToOne
   	private Work work;
    
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
   	@ManyToOne
   	private Users users;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
   
 
    
    public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

 
   
    
    @Column(name = "edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
     private Date editDate;
    
    public SqmAllocationHistory() {
    }

    public SqmAllocationHistory(Long id) {
        this.id = id;
    }

	public Long getId() {
		return id;
	}

	/*public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}*/

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "created_date", nullable = false, updatable = false)
    //@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
 
    @Column(name = "modified_date")
    //@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(name = "created_by")
   //@CreatedBy
    private String createdBy;
 
    @Column(name = "modified_by")
    private String modifiedBy;
    
    @Column(name = "inspection_done")
    private Short inspectionDone;

	public Short getInspectionDone() {
		return inspectionDone;
	}

	public void setInspectionDone(Short inspectionDone) {
		this.inspectionDone = inspectionDone;
	}

	
	
}
