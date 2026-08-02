package com.res.bean;

public class InspectionAnswerImageBean {


	    private Long id;
	    private String imagePath;
	    private String imageByRole;

	    public InspectionAnswerImageBean(Long id, String imagePath, String imageByRole) {
	        this.id = id;
	        this.imagePath = imagePath;
	        this.imageByRole = imageByRole;
	    }

		public InspectionAnswerImageBean() {
			// TODO Auto-generated constructor stub
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public String getImageByRole() {
			return imageByRole;
		}

		public void setImageByRole(String imageByRole) {
			this.imageByRole = imageByRole;
		}
	    
	    
	    
	    
	    
	}

