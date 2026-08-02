package com.res.entity.sadak;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group7 {

	private String surveyLevel;
	  
	public Group7( ) { }
		
	public Group7(String surveyLevel) {
		super();
		this.surveyLevel = surveyLevel;
	}

	public String getSurveyLevel() {
		return surveyLevel;
	}

	public void setSurveyLevel(String surveyLevel) {
		this.surveyLevel = surveyLevel;
	}
}
