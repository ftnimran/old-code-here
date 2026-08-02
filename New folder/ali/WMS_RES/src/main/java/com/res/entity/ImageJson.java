package com.res.entity;

import java.util.List;

public class ImageJson {
	
	private String id;
	
	private String workId;
	
    private String role;
    private Long inspectionId;
    
    private String saveInspectionTime;
	
	private Long userId;
	private Long remark;
	
		//	private List<String>  imageArray;
	

	/*private List<Map<String,String>>  imageNameArray;*/
	
	private Long sqmAllocationId;
	
	private	List<ImageDetails> imageNameArray;
	/*private ImageDetails imageDetails;*/
	
	/*private String imageName;*/

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Long getSqmAllocationId() {
		return sqmAllocationId;
	}

	public void setSqmAllocationId(Long sqmAllocationId) {
		this.sqmAllocationId = sqmAllocationId;
	}

	public List<ImageDetails> getImageNameArray() {
		return imageNameArray;
	}

	public void setImageNameArray(List<ImageDetails> imageNameArray) {
		this.imageNameArray = imageNameArray;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getSaveInspectionTime() {
		return saveInspectionTime;
	}

	public void setSaveInspectionTime(String saveInspectionTime) {
		this.saveInspectionTime = saveInspectionTime;
	}

	public Long getRemark() {
		return remark;
	}

	public void setRemark(Long remark) {
		this.remark = remark;
	}

	public Long getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}


	

	/*public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public List<Map<String, String>> getImageNameArray() {
		return imageNameArray;
	}

	public void setImageNameArray(List<Map<String, String>> imageNameArray) {
		this.imageNameArray = imageNameArray;
	}*/
 
}
