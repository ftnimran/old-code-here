package com.res.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sor")
public class SOR extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Column(name = "REFERENCE_NAME")
    @NotNull
    private String referenceName;

    @NotNull
	@JoinColumn(name = "YEAR_ID", referencedColumnName = "ID")
	@OneToOne
    private Year year;
    
    @NotNull
    @Column(name = "DATE_OF_ADOPTION")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dateOfAdoption;

    @NotNull
	@JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
	@OneToOne
	private Status status;
	
	@Column(name = "REMARKS")
	private String remarks;

	
    public SOR() {
	}
    

	public SOR(Long id) {
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDateOfAdoption() {
		return dateOfAdoption;
	}

	public void setDateOfAdoption(Date dateOfAdoption) {
		this.dateOfAdoption = dateOfAdoption;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the year
	 */
	public Year getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Year year) {
		this.year = year;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}
