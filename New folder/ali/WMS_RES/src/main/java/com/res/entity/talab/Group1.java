package com.res.entity.talab;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group1 {

		private String jila;
		private String vikaskhand;
		private String gramPanchayat;
		private String gram;
		private String inspectionDate;
		private String adhikariNameNPost;

		private String latitude;
		private String longitude;
		
		public Group1() {

		}

		public Group1(String jila, String vikaskhand, String gramPanchayat,
				String gram, String inspectionDate, String adhikariNameNPost) {
			super();
			this.jila = jila;
			this.vikaskhand = vikaskhand;
			this.gramPanchayat = gramPanchayat;
			this.gram = gram;
			this.inspectionDate = inspectionDate;
			this.adhikariNameNPost = adhikariNameNPost;
		}

		public String getJila() {
			return jila;
		}

		public void setJila(String jila) {
			this.jila = jila;
		}

		public String getVikaskhand() {
			return vikaskhand;
		}

		public void setVikaskhand(String vikaskhand) {
			this.vikaskhand = vikaskhand;
		}

		public String getGramPanchayat() {
			return gramPanchayat;
		}

		public void setGramPanchayat(String gramPanchayat) {
			this.gramPanchayat = gramPanchayat;
		}

		public String getGram() {
			return gram;
		}

		public void setGram(String gram) {
			this.gram = gram;
		}

		public String getInspectionDate() {
			return inspectionDate;
		}

		public void setInspectionDate(String inspectionDate) {
			this.inspectionDate = inspectionDate;
		}

		public String getAdhikariNameNPost() {
			return adhikariNameNPost;
		}

		public void setAdhikariNameNPost(String adhikariNameNPost) {
			this.adhikariNameNPost = adhikariNameNPost;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

	}
