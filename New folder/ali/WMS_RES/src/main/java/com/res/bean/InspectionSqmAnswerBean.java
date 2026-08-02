package com.res.bean;

import java.util.Date;
import java.util.List;

import com.res.entity.InspectionSqmAnswer;
import com.res.entity.Users;
import com.res.entity.Work;

public class InspectionSqmAnswerBean {
	
	


	private Long id;
	private Long InspectionSqmId;


	private String code;

	
	private String questionText;
	
	
	private String answer;

	
	private Work work;
	
	private Long answeredBy;
	
	
	
	
	private Users answerBy;
	
	
	private String answerByRole;
	
	
	private Short group;
	
	
	private InspectionSqmAnswer parentId;
	

    private Date createdDate;
    
    private Long workId;
    
    private String workName;
    
    private String workRequisitionNo;
    
    private Long workTypeId;
    
    private String workTypeName;
    private String name;
    
    private String inspectedByname;
    
    private Integer index;
    private String districtName;
    private String blockName;
    private String executionAgency;
    private String workStatus;
    private String lineDeptName;
    
    private boolean lastElement;
    private Integer serialNo;
    private Long isaparentId;
    private Integer parentIndex;
    private boolean isLeafNode;
    private List<InspectionSqmAnswerBean> childItems;
    private Integer childsCount;
    private Integer cumulativeChildsCount;
    
    


	public Long getInspectionSqmId() {
		return InspectionSqmId;
	}


	public void setInspectionSqmId(Long inspectionSqmId) {
		InspectionSqmId = inspectionSqmId;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getQuestionText() {
		return questionText;
	}


	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public Work getWork() {
		return work;
	}


	public void setWork(Work work) {
		this.work = work;
	}


	public Users getAnswerBy() {
		return answerBy;
	}


	public void setAnswerBy(Users answerBy) {
		this.answerBy = answerBy;
	}


	public String getAnswerByRole() {
		return answerByRole;
	}


	public void setAnswerByRole(String answerByRole) {
		this.answerByRole = answerByRole;
	}


	public Short getGroup() {
		return group;
	}


	public void setGroup(Short group) {
		this.group = group;
	}


	public InspectionSqmAnswer getParentId() {
		return parentId;
	}


	public void setParentId(InspectionSqmAnswer parentId) {
		this.parentId = parentId;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Long getWorkId() {
		return workId;
	}


	public void setWorkId(Long workId) {
		this.workId = workId;
	}


	public String getWorkName() {
		return workName;
	}


	public void setWorkName(String workName) {
		this.workName = workName;
	}


	public String getWorkRequisitionNo() {
		return workRequisitionNo;
	}


	public void setWorkRequisitionNo(String workRequisitionNo) {
		this.workRequisitionNo = workRequisitionNo;
	}


	public Long getWorkTypeId() {
		return workTypeId;
	}


	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}


	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getWorkTypeName() {
		return workTypeName;
	}


	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}


	public Integer getIndex() {
		return index;
	}


	public void setIndex(Integer index) {
		this.index = index;
	}


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public String getInspectedByname() {
		return inspectedByname;
	}


	public void setInspectedByname(String inspectedByname) {
		this.inspectedByname = inspectedByname;
	}


	public String getLineDeptName() {
		return lineDeptName;
	}


	public void setLineDeptName(String lineDeptName) {
		this.lineDeptName = lineDeptName;
	}


	public String getBlockName() {
		return blockName;
	}


	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}


	public String getExecutionAgency() {
		return executionAgency;
	}


	public void setExecutionAgency(String executionAgency) {
		this.executionAgency = executionAgency;
	}


	public String getWorkStatus() {
		return workStatus;
	}


	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public boolean isLastElement() {
		return lastElement;
	}


	public void setLastElement(boolean lastElement) {
		this.lastElement = lastElement;
	}


	public Integer getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}


	public Long getIsaparentId() {
		return isaparentId;
	}


	public void setIsaparentId(Long isaparentId) {
		this.isaparentId = isaparentId;
	}


	public Integer getParentIndex() {
		return parentIndex;
	}


	public void setParentIndex(Integer parentIndex) {
		this.parentIndex = parentIndex;
	}


	public boolean isLeafNode() {
		return isLeafNode;
	}


	public void setLeafNode(boolean isLeafNode) {
		this.isLeafNode = isLeafNode;
	}


	public List<InspectionSqmAnswerBean> getChildItems() {
		return childItems;
	}


	public void setChildItems(List<InspectionSqmAnswerBean> childItems) {
		this.childItems = childItems;
	}


	public Integer getChildsCount() {
		return childsCount;
	}


	public void setChildsCount(Integer childsCount) {
		this.childsCount = childsCount;
	}


	public Integer getCumulativeChildsCount() {
		return cumulativeChildsCount;
	}


	public void setCumulativeChildsCount(Integer cumulativeChildsCount) {
		this.cumulativeChildsCount = cumulativeChildsCount;
	}


	public Long getAnsweredBy() {
		return answeredBy;
	}


	public void setAnsweredBy(Long answeredBy) {
		this.answeredBy = answeredBy;
	}


	
	
	

	

	


	


}
