package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SurplusingArrangements {
	
	
	private String surplusingItem;
	private String whetherHydraulicDesignisDone;
	private String qualityofthematerialisacceptable;
	private String qualityOfworkmanshipIsAcceptable;
	
public SurplusingArrangements() {
		
	}
	
	
	public SurplusingArrangements(String surplusingItem, String whetherHydraulicDesignisDone, String qualityofthematerialisacceptable,
			String properlyPreparedAvailable) {
		super();
		this.surplusingItem = surplusingItem;
		this.whetherHydraulicDesignisDone = whetherHydraulicDesignisDone;
		this.qualityofthematerialisacceptable = qualityofthematerialisacceptable;
	}


	public String getSurplusingItem() {
		return surplusingItem;
	}


	public void setSurplusingItem(String surplusingItem) {
		this.surplusingItem = surplusingItem;
	}


	public String getWhetherHydraulicDesignisDone() {
		return whetherHydraulicDesignisDone;
	}


	public void setWhetherHydraulicDesignisDone(String whetherHydraulicDesignisDone) {
		this.whetherHydraulicDesignisDone = whetherHydraulicDesignisDone;
	}


	public String getQualityofthematerialisacceptable() {
		return qualityofthematerialisacceptable;
	}


	public void setQualityofthematerialisacceptable(String qualityofthematerialisacceptable) {
		this.qualityofthematerialisacceptable = qualityofthematerialisacceptable;
	}


	public String getQualityOfworkmanshipIsAcceptable() {
		return qualityOfworkmanshipIsAcceptable;
	}


	public void setQualityOfworkmanshipIsAcceptable(String qualityOfworkmanshipIsAcceptable) {
		this.qualityOfworkmanshipIsAcceptable = qualityOfworkmanshipIsAcceptable;
	}

}
