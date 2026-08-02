package com.res.bean;

import java.util.List;

public class InspectionDetailsBean {
	
	private Long inspectionId; // 0 = NEW, >0 = UPDATE
	    private Long billId;
	    private Long workId;
	    private Long workTypeId;
	    private String workType;
	    private Integer inspectedBy;
	    private String inspectionDate;
	    
	    private Long sqmAllocationId;
	    private Long randomAllocationId;
	    
	    private List<InspectionAnswerNewBean>   answerList;
		
	    public Long getInspectionId() {
			return inspectionId;
		}
		public void setInspectionId(Long inspectionId) {
			this.inspectionId = inspectionId;
		}
		public Long getBillId() {
			return billId;
		}
		public void setBillId(Long billId) {
			this.billId = billId;
		}
		public Long getWorkId() {
			return workId;
		}
		public void setWorkId(Long workId) {
			this.workId = workId;
		}
		public Long getWorkTypeId() {
			return workTypeId;
		}
		public void setWorkTypeId(Long workTypeId) {
			this.workTypeId = workTypeId;
		}
		public String getWorkType() {
			return workType;
		}
		public void setWorkType(String workType) {
			this.workType = workType;
		}
		
		
		public String getInspectionDate() {
			return inspectionDate;
		}
		public void setInspectionDate(String inspectionDate) {
			this.inspectionDate = inspectionDate;
		}
		public Integer getInspectedBy() {
			return inspectedBy;
		}
		public void setInspectedBy(Integer inspectedBy) {
			this.inspectedBy = inspectedBy;
		}
		public List<InspectionAnswerNewBean> getAnswerList() {
			return answerList;
		}
		public void setAnswerList(List<InspectionAnswerNewBean> answerList) {
			this.answerList = answerList;
		}
		public Long getSqmAllocationId() {
			return sqmAllocationId;
		}
		public void setSqmAllocationId(Long sqmAllocationId) {
			this.sqmAllocationId = sqmAllocationId;
		}
		public Long getRandomAllocationId() {
			return randomAllocationId;
		}
		public void setRandomAllocationId(Long randomAllocationId) {
			this.randomAllocationId = randomAllocationId;
		}
		
		
		
	}


