package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeneralObsSQM {
	private String generalObsSQMDeficiency;
	private String generalObsSQMWorkCompletedDelay;
	private String generalObsSQMWorkCompletedSanctionedCost;
	private String generalObsSQMWorkCompletedActionTaken;
	private String generalObsSQMWorkCompletedObservationsMade;
	
	public GeneralObsSQM() {
		
	}
	
	public GeneralObsSQM(String generalObsSQMDeficiency, String generalObsSQMWorkCompletedDelay, String generalObsSQMWorkCompletedSanctionedCost,
			String generalObsSQMWorkCompletedActionTaken, String generalObsSQMWorkCompletedObservationsMade) {
		super();
		this.generalObsSQMDeficiency = generalObsSQMDeficiency;
		this.generalObsSQMWorkCompletedDelay = generalObsSQMWorkCompletedDelay;
		this.generalObsSQMWorkCompletedSanctionedCost = generalObsSQMWorkCompletedSanctionedCost;
		this.generalObsSQMWorkCompletedActionTaken = generalObsSQMWorkCompletedActionTaken;
		this.generalObsSQMWorkCompletedObservationsMade = generalObsSQMWorkCompletedObservationsMade;
		
	}

	public String getGeneralObsSQMDeficiency() {
		return generalObsSQMDeficiency;
	}

	public void setGeneralObsSQMDeficiency(String generalObsSQMDeficiency) {
		this.generalObsSQMDeficiency = generalObsSQMDeficiency;
	}

	public String getGeneralObsSQMWorkCompletedDelay() {
		return generalObsSQMWorkCompletedDelay;
	}

	public void setGeneralObsSQMWorkCompletedDelay(String generalObsSQMWorkCompletedDelay) {
		this.generalObsSQMWorkCompletedDelay = generalObsSQMWorkCompletedDelay;
	}

	public String getGeneralObsSQMWorkCompletedSanctionedCost() {
		return generalObsSQMWorkCompletedSanctionedCost;
	}

	public void setGeneralObsSQMWorkCompletedSanctionedCost(String generalObsSQMWorkCompletedSanctionedCost) {
		this.generalObsSQMWorkCompletedSanctionedCost = generalObsSQMWorkCompletedSanctionedCost;
	}

	public String getGeneralObsSQMWorkCompletedActionTaken() {
		return generalObsSQMWorkCompletedActionTaken;
	}

	public void setGeneralObsSQMWorkCompletedActionTaken(String generalObsSQMWorkCompletedActionTaken) {
		this.generalObsSQMWorkCompletedActionTaken = generalObsSQMWorkCompletedActionTaken;
	}

	public String getGeneralObsSQMWorkCompletedObservationsMade() {
		return generalObsSQMWorkCompletedObservationsMade;
	}

	public void setGeneralObsSQMWorkCompletedObservationsMade(String generalObsSQMWorkCompletedObservationsMade) {
		this.generalObsSQMWorkCompletedObservationsMade = generalObsSQMWorkCompletedObservationsMade;
	}

}
