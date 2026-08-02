 
package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_workagreeemt_status")
public class MasterWorkAgreementStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long statusId;

    @Column(name = "STATUS_NAME_E")
    private String statusNameE;

    @Column(name = "STATUS_NAME_H")
    private String statusNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public MasterWorkAgreementStatus() {
    }
    
	public MasterWorkAgreementStatus(Long statusId) {
		this.statusId=statusId;
	}

	public String getStatusNameE() {
		return statusNameE;
	}

	public void setStatusNameE(String statusNameE) {
		this.statusNameE = statusNameE;
	}

	public String getStatusNameH() {
		return statusNameH;
	}

	public void setStatusNameH(String statusNameH) {
		this.statusNameH = statusNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
    
}
