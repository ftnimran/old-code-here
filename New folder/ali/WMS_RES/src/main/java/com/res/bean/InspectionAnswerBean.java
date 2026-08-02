package com.res.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.res.entity.Bill;
import com.res.entity.Users;

public class InspectionAnswerBean {
	
	

	private Long id;


	private String code;


	private String questionText;


	private String answer;


	private Long billId;
	
	private Long workId;


	private Long answerBy;
	
	private String answerByName;


	private String answerByRole;
	
	private Short group;
	
	private String random_inspection_date_time;


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


	public Long getBillId() {
		return billId;
	}


	public void setBillId(Long billId) {
		this.billId = billId;
	}


	public Long getAnswerBy() {
		return answerBy;
	}


	public void setAnswerBy(Long answerBy) {
		this.answerBy = answerBy;
	}


	public String getAnswerByRole() {
		return answerByRole;
	}


	public void setAnswerByRole(String answerByRole) {
		this.answerByRole = answerByRole;
	}


	public Long getWorkId() {
		return workId;
	}


	public void setWorkId(Long workId) {
		this.workId = workId;
	}


	public String getAnswerByName() {
		return answerByName;
	}


	public void setAnswerByName(String answerByName) {
		this.answerByName = answerByName;
	}


	public Short getGroup() {
		return group;
	}


	public void setGroup(Short group) {
		this.group = group;
	}


	public String getRandom_inspection_date_time() {
		return random_inspection_date_time;
	}


	public void setRandom_inspection_date_time(String random_inspection_date_time) {
		this.random_inspection_date_time = random_inspection_date_time;
	}

}
