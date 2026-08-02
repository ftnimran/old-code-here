package com.res.entity;

import java.util.List;

public class FileSqmJson {
	
    private String id;
	
    private String role;
	
	private Long userId;
	
	private List<String>  fileArray;
	
	private Long sqmAllocationId;
	
	private	List<FileDetails> fileNameArray;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public List<String> getFileArray() {
		return fileArray;
	}

	public void setFileArray(List<String> fileArray) {
		this.fileArray = fileArray;
	}

	public Long getSqmAllocationId() {
		return sqmAllocationId;
	}

	public void setSqmAllocationId(Long sqmAllocationId) {
		this.sqmAllocationId = sqmAllocationId;
	}

	public List<FileDetails> getFileNameArray() {
		return fileNameArray;
	}

	public void setFileNameArray(List<FileDetails> fileNameArray) {
		this.fileNameArray = fileNameArray;
	}
	
	

}
