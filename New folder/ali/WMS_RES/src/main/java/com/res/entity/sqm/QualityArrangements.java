package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QualityArrangements {
	 private ObservationsAboutLaboratory observationsAboutLaboratory;
	 private ObservationsAboutTests observationsAboutTests;
	 private MandatoryTestsGrading mandatoryTestsGrading;
	 private String mandatoryTestsConducted;
	 private String qcRegisterPart1Maintained;
	 private String qcRegisterPart2Maintained;
	 
	
	
	 
    public QualityArrangements() {
		 
	 }
    
    
    public QualityArrangements(ObservationsAboutLaboratory observationsAboutLaboratory,ObservationsAboutTests observationsAboutTests,MandatoryTestsGrading mandatoryTestsGrading,String mandatoryTestsConducted, String qcRegisterPart1Maintained, String qcRegisterPart2Maintained
    		) {
		super();
		this.mandatoryTestsConducted = mandatoryTestsConducted;
		this.qcRegisterPart1Maintained = qcRegisterPart1Maintained;
		this.qcRegisterPart2Maintained = qcRegisterPart2Maintained;
		
		this.mandatoryTestsGrading = mandatoryTestsGrading;
		this.observationsAboutLaboratory = observationsAboutLaboratory;
		this.observationsAboutTests = observationsAboutTests;
		
	}




	 
	 public String getMandatoryTestsConducted() {
		return mandatoryTestsConducted;
	}

	public void setMandatoryTestsConducted(String mandatoryTestsConducted) {
		this.mandatoryTestsConducted = mandatoryTestsConducted;
	}

	public String getQcRegisterPart1Maintained() {
		return qcRegisterPart1Maintained;
	}

	public void setQcRegisterPart1Maintained(String qcRegisterPart1Maintained) {
		this.qcRegisterPart1Maintained = qcRegisterPart1Maintained;
	}

	public String getQcRegisterPart2Maintained() {
		return qcRegisterPart2Maintained;
	}

	public void setQcRegisterPart2Maintained(String qcRegisterPart2Maintained) {
		this.qcRegisterPart2Maintained = qcRegisterPart2Maintained;
	}

	

	

	public MandatoryTestsGrading getMandatoryTestsGrading() {
		return mandatoryTestsGrading;
	}


	public void setMandatoryTestsGrading(MandatoryTestsGrading mandatoryTestsGrading) {
		this.mandatoryTestsGrading = mandatoryTestsGrading;
	}


	public ObservationsAboutLaboratory getObservationsAboutLaboratory() {
		return observationsAboutLaboratory;
	}

	public void setObservationsAboutLaboratory(ObservationsAboutLaboratory observationsAboutLaboratory) {
		this.observationsAboutLaboratory = observationsAboutLaboratory;
	}

	public ObservationsAboutTests getObservationsAboutTests() {
		return observationsAboutTests;
	}

	public void setObservationsAboutTests(ObservationsAboutTests observationsAboutTests) {
		this.observationsAboutTests = observationsAboutTests;
	}

	
	 
	 
	 

}
