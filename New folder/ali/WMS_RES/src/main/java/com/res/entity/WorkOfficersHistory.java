package com.res.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "work_officer_history")
public class WorkOfficersHistory extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "ASSISTANT_ENGINEER_ID", referencedColumnName = "ID")
	@ManyToOne
	private Users assistantEngineer;

	@JoinColumn(name = "SUB_ENGINEER_ID", referencedColumnName = "ID")
	@ManyToOne
	private Users subEngineer;
	
	@JoinColumn(name = "SUB_DIVISIONAL_OFFICER_ID", referencedColumnName = "ID")
	@ManyToOne
	private Users subDivisionalOfficer;

	
	@JoinColumn(name = "WORK_ID", referencedColumnName = "ID")
	@ManyToOne
	private Work work;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getAssistantEngineer() {
		return assistantEngineer;
	}

	public void setAssistantEngineer(Users assistantEngineer) {
		this.assistantEngineer = assistantEngineer;
	}

	public Users getSubEngineer() {
		return subEngineer;
	}

	public void setSubEngineer(Users subEngineer) {
		this.subEngineer = subEngineer;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public Users getSubDivisionalOfficer() {
		return subDivisionalOfficer;
	}

	public void setSubDivisionalOfficer(Users subDivisionalOfficer) {
		this.subDivisionalOfficer = subDivisionalOfficer;
	}
	
	
  
}