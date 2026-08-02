
package com.res.entity.Khel;

import javax.xml.bind.annotation.XmlRootElement;

import com.res.entity.sadak.Group1;
import com.res.entity.sadak.Group2;



@XmlRootElement
public class Data {
	
	private Group1 group1;
	private Group2 group2;
	private Meta meta;
	private InspectionDetails inspectionDetails;
	private String isWorkingAccordingToFixedLagat;
    private String suggestion;
    private String overallGrading;
    private ImageAndFiles imageAndFiles;
	
public Data() {
		
	}
	
	
	public Data( Group1 group1,Group2 group2,Meta meta,InspectionDetails inspectionDetails,String isWorkingAccordingToFixedLagat, String suggestion, String overallGrading
			,ImageAndFiles imageAndFiles) {
		super();
		this.group1 = group1;
		this.group2 = group2;
		this.meta =  meta;
		this.inspectionDetails = inspectionDetails;
		this.isWorkingAccordingToFixedLagat = isWorkingAccordingToFixedLagat;
		this.suggestion = suggestion;
		this.overallGrading = overallGrading;
		this.imageAndFiles = imageAndFiles;
	
		
	}


	public Group1 getGroup1() {
		return group1;
	}


	public void setGroup1(Group1 group1) {
		this.group1 = group1;
	}


	public Group2 getGroup2() {
		return group2;
	}


	public void setGroup2(Group2 group2) {
		this.group2 = group2;
	}


	public InspectionDetails getInspectionDetails() {
		return inspectionDetails;
	}


	public void setInspectionDetails(InspectionDetails inspectionDetails) {
		this.inspectionDetails = inspectionDetails;
	}


	public String getIsWorkingAccordingToFixedLagat() {
		return isWorkingAccordingToFixedLagat;
	}


	public void setIsWorkingAccordingToFixedLagat(String isWorkingAccordingToFixedLagat) {
		this.isWorkingAccordingToFixedLagat = isWorkingAccordingToFixedLagat;
	}


	public String getSuggestion() {
		return suggestion;
	}


	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}


	public String getOverallGrading() {
		return overallGrading;
	}


	public void setOverallGrading(String overallGrading) {
		this.overallGrading = overallGrading;
	}


	public Meta getMeta() {
		return meta;
	}


	public void setMeta(Meta meta) {
		this.meta = meta;
	}


	public ImageAndFiles getImageAndFiles() {
		return imageAndFiles;
	}


	public void setImageAndFiles(ImageAndFiles imageAndFiles) {
		this.imageAndFiles = imageAndFiles;
	}
	

}
