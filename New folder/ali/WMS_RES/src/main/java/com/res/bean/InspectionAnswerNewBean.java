package com.res.bean;

import com.res.entity.Bill;
import com.res.entity.Users;
import com.res.entity.Work;

public class InspectionAnswerNewBean {

	private Long inspectionTypeId;
	private Long questionId;
	private Long inspectionId;
	private String answer;
	
	private Long id;
	private String code;
	private String questionText;
	private Bill bill;
	private Users answerBy;
	private String answerByRole;
	private Work work;

	public Long getInspectionTypeId() {
		return inspectionTypeId;
	}

	public void setInspectionTypeId(Long inspectionTypeId) {
		this.inspectionTypeId = inspectionTypeId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
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

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
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

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}
	
	

}
