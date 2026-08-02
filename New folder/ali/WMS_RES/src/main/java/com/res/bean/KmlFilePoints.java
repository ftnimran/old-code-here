package com.res.bean;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class KmlFilePoints {
 
	private MultipartFile KmlFile;
	
	
	private Double Lattitude;
	
	private Double Longitude;

	List<String> gpCodeList;
	private Long projectId;
	
	
	
	
	
	

	
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public List<String> getGpCodeList() {
		return gpCodeList;
	}

	public void setGpCodeList(List<String> gpCodeList) {
		this.gpCodeList = gpCodeList;
	}

	private String  LocationName;
	
	
	public String getLocationName() {
		return LocationName;
	}

	public void setLocationName(String locationName) {
		LocationName = locationName;
	}

	public Double getLattitude() {
		return Lattitude;
	}

	public void setLattitude(Double lattitude) {
		Lattitude = lattitude;
	}

	public Double getLongitude() {
		return Longitude;
	}

	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}

	public MultipartFile getKmlFile() {
		return KmlFile;
	}

	public void setKmlFile(MultipartFile kmlFile) {
		KmlFile = kmlFile;
	}
	
	
	
	
	
}
