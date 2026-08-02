package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.InspectionSqmAnswerFile;
import com.res.entity.InspectionSqmAnswerImage;
import com.res.entity.SqmAllocation;
import com.res.entity.Work;

public interface InspectionSqmAnswerFileRepository extends JpaRepository<InspectionSqmAnswerFile, Long>, CrudRepository<InspectionSqmAnswerFile, Long> {

	/*List<InspectionSqmAnswerFile> findInspectionAnswersFileForSqmByByWork(Work work);*/
	
	@Query("from InspectionSqmAnswerFile ia where ia.sqmAllocationId= :sqmAllocationId  and ia.answerByRole = 'ROLE_SQM'")
	public List<InspectionSqmAnswerFile> findInspectionAnswersFileForSqmByByWork(@Param("sqmAllocationId") SqmAllocation sqmAllocationId);

	/*public List<InspectionSqmAnswerFile> findDocumentId(Long workId, Long inspectedBy, Long sqmId);*/
	
	/*@Query("from InspectionSqmAnswerFile ia where ia.work.id= :workId and ia.answerBy.id=COALESCE(:inspectedBy, ia.answerBy.id) and ia.sqmAllocationId.id=COALESCE(:sqmId, ia.sqmAllocationId.id)")
	public List<InspectionSqmAnswerFile> findDocumentId(@Param("workId") Long workId, @Param("inspectedBy") Long inspectedBy, @Param("sqmId") Long sqmId);*/
	
	@Query("from InspectionSqmAnswerFile ia where ia.sqmAllocationId.id=COALESCE(:sqmId, ia.sqmAllocationId.id)")
	public List<InspectionSqmAnswerFile> findDocumentId(@Param("sqmId") Long sqmId);
	
	@Query("from InspectionSqmAnswerFile ia where ia.sqmAllocationId= :sqmAllocationId  and ia.answerByRole!= 'ROLE_SQM'")
	public List<InspectionSqmAnswerFile> findInspectionAnswersFileForOfficerByByWork(@Param("sqmAllocationId") SqmAllocation sqmAllocationId);

}
