package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//@Audited
@Entity
@Table(name = "work_requisition_id_generation")
public class WorkRequisitionIdGeneration  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
    
    @Column(name = "division_agency")
    private String divisionAgency;
    
    private Integer counter;
   
    
    public WorkRequisitionIdGeneration() {
    }

/*    public WorkRequisitionIdGeneration(Integer id) {
        this.id = id;
    }*/

	public String getDivisionAgency() {
		return divisionAgency;
	}

	public void setDivisionAgency(String divisionAgency) {
		this.divisionAgency = divisionAgency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	
	

	/*public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}*/
	
	
    
    
    

}
