package com.res.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "inspection_sqm_answer")
public class InspectionSqmAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "question_text")
	private String questionText;
	
	@Column(name = "answer")
	private String answer;

	@JoinColumn(name = "work_id", referencedColumnName = "id") 
	@ManyToOne(fetch = FetchType.LAZY)
	private Work work;
	
	@JoinColumn(name = "answer_by", referencedColumnName = "id") 
	@ManyToOne
	private Users answerBy;
	
	@Column(name = "answer_by_role")
	private String answerByRole;
	
	@Column(name = "group_code")
	private Short group;
	
	@OneToMany(mappedBy = "parentId")
	private Collection<InspectionSqmAnswer> childItems;
	
	@JoinColumn(name = "parent_id", referencedColumnName = "id") 
	@ManyToOne(fetch = FetchType.LAZY)
	private InspectionSqmAnswer parentId;
	
	@Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
	
	@JoinColumn(name = "sqm_allocation_id", referencedColumnName = "id") 
	@ManyToOne
	private SqmAllocation sqmAllocationId;
	
	public SqmAllocation getSqmAllocationId() {
		return sqmAllocationId;
	}

	public void setSqmAllocationId(SqmAllocation sqmAllocationId) {
		this.sqmAllocationId = sqmAllocationId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public InspectionSqmAnswer() {
		super();
	}

	public InspectionSqmAnswer(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public Users getAnswerBy() {
		return answerBy;
	}

	public void setAnswerBy(Users answerBy) {
		this.answerBy = answerBy;
	}

	public String getAnswerByRole() {
		return answerByRole;
	}

	public void setAnswerByRole(String answerByRole) {
		this.answerByRole = answerByRole;
	}

	public Short getGroup() {
		return group;
	}

	public void setGroup(Short group) {
		this.group = group;
	}

	public InspectionSqmAnswer getParentId() {
		return parentId;
	}

	public void setParentId(InspectionSqmAnswer parentId) {
		this.parentId = parentId;
	}

	public Collection<InspectionSqmAnswer> getChildItems() {
		return childItems;
	}

	public void setChildItems(Collection<InspectionSqmAnswer> childItems) {
		this.childItems = childItems;
	}

	

	


	
}
