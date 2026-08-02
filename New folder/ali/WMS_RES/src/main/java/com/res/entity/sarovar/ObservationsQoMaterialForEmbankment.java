package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsQoMaterialForEmbankment {
	
	private String itomOfWork;
    private String observationsQoMEmbankmentVisual;
    private String observationsQoMEmbankmentAcceptable;
    
    
public ObservationsQoMaterialForEmbankment() {
		
	}
	
	
	public ObservationsQoMaterialForEmbankment(String itomOfWork, String observationsQoMEmbankmentVisual,String observationsQoMEmbankmentAcceptable) {
		super();
		this.itomOfWork = itomOfWork;
		this.observationsQoMEmbankmentVisual = observationsQoMEmbankmentVisual;
		this.observationsQoMEmbankmentAcceptable = observationsQoMEmbankmentAcceptable;
		
		
	}


	public String getItomOfWork() {
		return itomOfWork;
	}


	public void setItomOfWork(String itomOfWork) {
		this.itomOfWork = itomOfWork;
	}


	public String getObservationsQoMEmbankmentVisual() {
		return observationsQoMEmbankmentVisual;
	}


	public void setObservationsQoMEmbankmentVisual(String observationsQoMEmbankmentVisual) {
		this.observationsQoMEmbankmentVisual = observationsQoMEmbankmentVisual;
	}


	public String getObservationsQoMEmbankmentAcceptable() {
		return observationsQoMEmbankmentAcceptable;
	}


	public void setObservationsQoMEmbankmentAcceptable(String observationsQoMEmbankmentAcceptable) {
		this.observationsQoMEmbankmentAcceptable = observationsQoMEmbankmentAcceptable;
	}

}
