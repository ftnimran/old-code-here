package com.res.entity.Khel;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class InspectionDetails {
	 private String currentStatus;
	 private String isPlaceAppropriate;
	 private String isQualityOfMaterialAcceptable;
	 private String isCompletingOfWorkAcceptable;
	 private String isCPTKaryaManakAnusar;
	 private String isUrinalAndSittingArrangementAccordingToDirection;
	 private String ifWorkCompletedThenPlayActivityIsHappened;
	 private String isSizeOfPlayGroundAccordingToDirsction;
	 private String physicalStage;
	 
	 public InspectionDetails() {
		 
	 }
	 
	 public InspectionDetails( String currentStatus,String isPlaceAppropriate,String isQualityOfMaterialAcceptable,String isCompletingOfWorkAcceptable, String isCPTKaryaManakAnusar, String isUrinalAndSittingArrangementAccordingToDirection,
			 String ifWorkCompletedThenPlayActivityIsHappened, String isSizeOfPlayGroundAccordingToDirsction,String physicalStage) {
			super();
			this.currentStatus = currentStatus;
			this.isPlaceAppropriate = isPlaceAppropriate;
			this.isQualityOfMaterialAcceptable = isQualityOfMaterialAcceptable;
			this.isCompletingOfWorkAcceptable = isCompletingOfWorkAcceptable;
			this.isCPTKaryaManakAnusar = isCPTKaryaManakAnusar;
			this.isUrinalAndSittingArrangementAccordingToDirection = isUrinalAndSittingArrangementAccordingToDirection;
			this.ifWorkCompletedThenPlayActivityIsHappened = ifWorkCompletedThenPlayActivityIsHappened;
			this.isSizeOfPlayGroundAccordingToDirsction = isSizeOfPlayGroundAccordingToDirsction;
			this.physicalStage = physicalStage;
		
		}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getIsPlaceAppropriate() {
		return isPlaceAppropriate;
	}

	public void setIsPlaceAppropriate(String isPlaceAppropriate) {
		this.isPlaceAppropriate = isPlaceAppropriate;
	}

	public String getIsQualityOfMaterialAcceptable() {
		return isQualityOfMaterialAcceptable;
	}

	public void setIsQualityOfMaterialAcceptable(String isQualityOfMaterialAcceptable) {
		this.isQualityOfMaterialAcceptable = isQualityOfMaterialAcceptable;
	}

	public String getIsCompletingOfWorkAcceptable() {
		return isCompletingOfWorkAcceptable;
	}

	public void setIsCompletingOfWorkAcceptable(String isCompletingOfWorkAcceptable) {
		this.isCompletingOfWorkAcceptable = isCompletingOfWorkAcceptable;
	}

	public String getIsCPTKaryaManakAnusar() {
		return isCPTKaryaManakAnusar;
	}

	public void setIsCPTKaryaManakAnusar(String isCPTKaryaManakAnusar) {
		this.isCPTKaryaManakAnusar = isCPTKaryaManakAnusar;
	}

	public String getIsUrinalAndSittingArrangementAccordingToDirection() {
		return isUrinalAndSittingArrangementAccordingToDirection;
	}

	public void setIsUrinalAndSittingArrangementAccordingToDirection(
			String isUrinalAndSittingArrangementAccordingToDirection) {
		this.isUrinalAndSittingArrangementAccordingToDirection = isUrinalAndSittingArrangementAccordingToDirection;
	}

	public String getIfWorkCompletedThenPlayActivityIsHappened() {
		return ifWorkCompletedThenPlayActivityIsHappened;
	}

	public void setIfWorkCompletedThenPlayActivityIsHappened(String ifWorkCompletedThenPlayActivityIsHappened) {
		this.ifWorkCompletedThenPlayActivityIsHappened = ifWorkCompletedThenPlayActivityIsHappened;
	}

	public String getIsSizeOfPlayGroundAccordingToDirsction() {
		return isSizeOfPlayGroundAccordingToDirsction;
	}

	public void setIsSizeOfPlayGroundAccordingToDirsction(String isSizeOfPlayGroundAccordingToDirsction) {
		this.isSizeOfPlayGroundAccordingToDirsction = isSizeOfPlayGroundAccordingToDirsction;
	}

	public String getPhysicalStage() {
		return physicalStage;
	}

	public void setPhysicalStage(String physicalStage) {
		this.physicalStage = physicalStage;
	}
	 
	 
	 
	 
	

}
