package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseCourseObservationsQoM {
	
	private String itemObservations;
    private String layerThicknessObservations;
    private String thicknessisadequate;
    private String confirmstoSpecifications;
    private String workmanshipAsPerNorms;
    
    
public BaseCourseObservationsQoM() {
		
	}
	
	
	public BaseCourseObservationsQoM(String itemObservations, String layerThicknessObservations, String thicknessisadequate,
			String confirmstoSpecifications,String workmanshipAsPerNorms) {
		super();
		this.itemObservations = itemObservations;
		this.layerThicknessObservations = layerThicknessObservations;
		this.thicknessisadequate = thicknessisadequate;
		this.confirmstoSpecifications = confirmstoSpecifications;
		this.workmanshipAsPerNorms = workmanshipAsPerNorms;
		
		
	}


	public String getItemObservations() {
		return itemObservations;
	}


	public void setItemObservations(String itemObservations) {
		this.itemObservations = itemObservations;
	}


	

	public String getThicknessisadequate() {
		return thicknessisadequate;
	}


	public void setThicknessisadequate(String thicknessisadequate) {
		this.thicknessisadequate = thicknessisadequate;
	}


	public String getConfirmstoSpecifications() {
		return confirmstoSpecifications;
	}


	public void setConfirmstoSpecifications(String confirmstoSpecifications) {
		this.confirmstoSpecifications = confirmstoSpecifications;
	}


	public String getWorkmanshipAsPerNorms() {
		return workmanshipAsPerNorms;
	}


	public void setWorkmanshipAsPerNorms(String workmanshipAsPerNorms) {
		this.workmanshipAsPerNorms = workmanshipAsPerNorms;
	}


	public String getLayerThicknessObservations() {
		return layerThicknessObservations;
	}


	public void setLayerThicknessObservations(String layerThicknessObservations) {
		this.layerThicknessObservations = layerThicknessObservations;
	}

}
