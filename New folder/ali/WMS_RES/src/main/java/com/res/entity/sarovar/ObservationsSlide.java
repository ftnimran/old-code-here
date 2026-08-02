package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsSlide {
	
	
    
    private String observationsSlideLocation;
	private String observationsSlideSlopesSatisfactory;
	private String observationsSlideProfileSatisfactory;
	
	 public ObservationsSlide() {
	    	
	    }
	    
	    public ObservationsSlide(String observationsSlideLocation, String observationsSlideSlopesSatisfactory, String observationsSlideProfileSatisfactory) {
			super();
			this.observationsSlideLocation = observationsSlideLocation;
			this.observationsSlideSlopesSatisfactory = observationsSlideSlopesSatisfactory;
			this.observationsSlideProfileSatisfactory = observationsSlideProfileSatisfactory;
			
			
		}

		public String getObservationsSlideLocation() {
			return observationsSlideLocation;
		}

		public void setObservationsSlideLocation(String observationsSlideLocation) {
			this.observationsSlideLocation = observationsSlideLocation;
		}

		public String getObservationsSlideSlopesSatisfactory() {
			return observationsSlideSlopesSatisfactory;
		}

		public void setObservationsSlideSlopesSatisfactory(String observationsSlideSlopesSatisfactory) {
			this.observationsSlideSlopesSatisfactory = observationsSlideSlopesSatisfactory;
		}

		public String getObservationsSlideProfileSatisfactory() {
			return observationsSlideProfileSatisfactory;
		}

		public void setObservationsSlideProfileSatisfactory(String observationsSlideProfileSatisfactory) {
			this.observationsSlideProfileSatisfactory = observationsSlideProfileSatisfactory;
		}

}
