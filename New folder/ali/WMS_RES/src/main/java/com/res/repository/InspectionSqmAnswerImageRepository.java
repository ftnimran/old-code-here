package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.Bill;
import com.res.entity.InspectionAnswerImageNew;
import com.res.entity.InspectionSqmAnswerImage;
import com.res.entity.SqmAllocation;
import com.res.entity.Work;

public interface InspectionSqmAnswerImageRepository extends JpaRepository<InspectionSqmAnswerImage, Long>, CrudRepository<InspectionSqmAnswerImage, Long> {
	
	
	@Query("from InspectionSqmAnswerImage ia where ia.sqmAllocationId= :sqmAllocationId  and ia.answerByRole = 'ROLE_SQM'")
	public List<InspectionSqmAnswerImage> findInspectionAnswersImageForSqmByByWork(@Param("sqmAllocationId") SqmAllocation sqmAllocationId);

	/*public List<InspectionSqmAnswerImage> findDocumentId(Long workId, Long inspectedBy, Long sqmId);*/
	
	/*@Query("from InspectionSqmAnswerImage ia where ia.work.id= :workId and ia.answerBy.id=COALESCE(:inspectedBy, ia.answerBy.id) and ia.sqmAllocationId.id=COALESCE(:sqmId, ia.sqmAllocationId.id)")
	public List<InspectionSqmAnswerImage> findDocumentId(@Param("workId") Long workId, @Param("inspectedBy") Long inspectedBy, @Param("sqmId") Long sqmId);*/
	
	@Query("from InspectionSqmAnswerImage ia where ia.sqmAllocationId.id=COALESCE(:sqmId, ia.sqmAllocationId.id)")
	public List<InspectionSqmAnswerImage> findDocumentId(@Param("sqmId") Long sqmId);
	
	@Query("from InspectionSqmAnswerImage ia where ia.sqmAllocationId= :sqmAllocationId  and ia.answerByRole!= 'ROLE_SQM'")
	public List<InspectionSqmAnswerImage> findInspectionAnswersImageForOfficerByByWork(@Param("sqmAllocationId") SqmAllocation sqmAllocationId);
	
	
	

	
	
	

	

}
