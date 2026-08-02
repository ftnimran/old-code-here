package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inspection_answer_CC")
public class InspectionAnswerCC implements Serializable {

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

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
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

}
