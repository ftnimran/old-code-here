package com.res.entity;

import java.util.List;

public class FileJson {
	
	private String id;
	
	private  String fileString;
	
	 private String role;
		
     private Long userId;
     
     private List<FileDetails> fileNameArray;

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

	public String getFileString() {
		return fileString;
	}

	public void setFileString(String fileString) {
		this.fileString = fileString;
	}

	public List<FileDetails> getFileNameArray() {
		return fileNameArray;
	}

	public void setFileNameArray(List<FileDetails> fileNameArray) {
		this.fileNameArray = fileNameArray;
	}

}
