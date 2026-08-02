package com.res.entity.pulia;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group2 {

		private String sthalChayan;
		private String hydrolicDesign;
		private String samgriQuality;
		private String workmanship;
		private String foundationDesign;
		private String samgriTest;
		
		public Group2() {

		}

		public Group2(String sthalChayan, String hydrolicDesign, String samgriQuality,
				String workmanship, String foundationDesign, String samgriTest) {
			super();
			this.sthalChayan = sthalChayan;
			this.hydrolicDesign = hydrolicDesign;
			this.samgriQuality = samgriQuality;
			this.workmanship = workmanship;
			this.foundationDesign = foundationDesign;
			this.samgriTest = samgriTest;
		}

		public String getSthalChayan() {
			return sthalChayan;
		}

		public void setSthalChayan(String sthalChayan) {
			this.sthalChayan = sthalChayan;
		}

		public String getHydrolicDesign() {
			return hydrolicDesign;
		}

		public void setHydrolicDesign(String hydrolicDesign) {
			this.hydrolicDesign = hydrolicDesign;
		}

		public String getSamgriQuality() {
			return samgriQuality;
		}

		public void setSamgriQuality(String samgriQuality) {
			this.samgriQuality = samgriQuality;
		}

		public String getWorkmanship() {
			return workmanship;
		}

		public void setWorkmanship(String workmanship) {
			this.workmanship = workmanship;
		}

		public String getFoundationDesign() {
			return foundationDesign;
		}

		public void setFoundationDesign(String foundationDesign) {
			this.foundationDesign = foundationDesign;
		}

		public String getSamgriTest() {
			return samgriTest;
		}

		public void setSamgriTest(String samgriTest) {
			this.samgriTest = samgriTest;
		}

		
		

	}
