package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsAboutLaboratory {
	
	 private String fieldLaboratoryEstablished;
	 private String listEquipmentsAvailable;
	 private String adequateEquipmentsUsed;
	 
	 public ObservationsAboutLaboratory()
	 {
		 
	 }
	 
	 public ObservationsAboutLaboratory(String fieldLaboratoryEstablished, String listEquipmentsAvailable, String adequateEquipmentsUsed) {
			super();
			this.fieldLaboratoryEstablished = fieldLaboratoryEstablished;
			this.listEquipmentsAvailable = listEquipmentsAvailable;
			this.adequateEquipmentsUsed = adequateEquipmentsUsed;
			
			
		}

	public String getFieldLaboratoryEstablished() {
		return fieldLaboratoryEstablished;
	}

	public void setFieldLaboratoryEstablished(String fieldLaboratoryEstablished) {
		this.fieldLaboratoryEstablished = fieldLaboratoryEstablished;
	}

	public String getListEquipmentsAvailable() {
		return listEquipmentsAvailable;
	}

	public void setListEquipmentsAvailable(String listEquipmentsAvailable) {
		this.listEquipmentsAvailable = listEquipmentsAvailable;
	}

	public String getAdequateEquipmentsUsed() {
		return adequateEquipmentsUsed;
	}

	public void setAdequateEquipmentsUsed(String adequateEquipmentsUsed) {
		this.adequateEquipmentsUsed = adequateEquipmentsUsed;
	}

}
