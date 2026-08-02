package com.res.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "inspection_answer")
public class InspectionAnswer implements Serializable {

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

	@JoinColumn(name = "bill_id", referencedColumnName = "id") 
	@ManyToOne(fetch = FetchType.LAZY)
	private Bill bill;
	
	@JoinColumn(name = "answer_by", referencedColumnName = "id") 
	@ManyToOne
	private Users answerBy;
	
	@Column(name = "answer_by_role")
	private String answerByRole;
	
	@JoinColumn(name = "work_id", referencedColumnName = "id") 
	@ManyToOne(fetch = FetchType.LAZY)
	private Work work;
	
	
	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "random_inspection_date_time")
	private Date randomInspectionDate_time;
	
	

	public Date getRandomInspectionDate_time() {
		return randomInspectionDate_time;
	}

	public void setRandomInspectionDate_time(Date randomInspectionDate_time) {
		this.randomInspectionDate_time = randomInspectionDate_time;
	}

	public String getAnswerByRole() {
		return answerByRole;
	}

	public void setAnswerByRole(String answerByRole) {
		this.answerByRole = answerByRole;
	}

	public Users getAnswerBy() {
		return answerBy;
	}

	public void setAnswerBy(Users answerBy) {
		this.answerBy = answerBy;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	
}
