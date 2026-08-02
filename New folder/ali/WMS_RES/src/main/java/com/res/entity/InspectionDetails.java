package com.res.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "inspection_details_new")
public class InspectionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inspection_id")
    private Long id;

    @Column(name = "bill_id")
    private Long billId;

    @Column(name = "work_id", nullable = false)
    private Long workId;

    @Column(name = "work_type_id")
    private Long workTypeId;

    @Column(name = "work_type")
    private String workType;

    @Column(name = "inspected_by")
    private String inspectedBy;

    @Column(name = "inspection_date")
    private Date inspectionDate;
    
    @Column(name = "random_allocation_id", nullable = false)
    private Long randomAllocationId;
    
    @Column(name = "sqm_allocation_id", nullable = false)
    private Long sqmAllocationId;
    
    @Column(name = "general_inspection_done")
    private Short generalInspectionDone;
   

    @Column(name = "created_on", insertable = false, updatable = false)
    private Date createdOn;
    
    
    

	public InspectionDetails() {
		super();
	}

	

	
	public InspectionDetails(Long inspectionId) {
		// TODO Auto-generated constructor stub
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
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

	public String getInspectedBy() {
		return inspectedBy;
	}

	public void setInspectedBy(String inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	

	 /* ================= CHILD MAPPING ================= */

    public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}



	@OneToMany(
        mappedBy = "inspection",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<InspectionAnswersNew> answers = new ArrayList<>();

    /* ================= HELPER METHODS ================= */

    public void addAnswer(InspectionAnswersNew answer) {
        answers.add(answer);
        answer.setInspection(this); // IMPORTANT
    }

    public void removeAnswer(InspectionAnswersNew answer) {
        answers.remove(answer);
        answer.setInspection(null);
    }

    public List<InspectionAnswersNew> getAnswers() {
        return answers;
    }

    public void setAnswers(List<InspectionAnswersNew> answers) {
        this.answers = answers;
    }



	public Long getRandomAllocationId() {
		return randomAllocationId;
	}



	public void setRandomAllocationId(Long randomAllocationId) {
		this.randomAllocationId = randomAllocationId;
	}



	public Long getSqmAllocationId() {
		return sqmAllocationId;
	}



	public void setSqmAllocationId(Long sqmAllocationId) {
		this.sqmAllocationId = sqmAllocationId;
	}



	public Short getGeneralInspectionDone() {
		return generalInspectionDone;
	}



	public void setGeneralInspectionDone(Short generalInspectionDone) {
		this.generalInspectionDone = generalInspectionDone;
	}
	
	

    
}

