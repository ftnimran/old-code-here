package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.InspectionAnswerImageCC;
import com.res.entity.Work;

public interface InspectionAnswerImageRepositoryCC  extends JpaRepository<InspectionAnswerImageCC, Long>, CrudRepository<InspectionAnswerImageCC, Long> {
    
	public List<InspectionAnswerImageCC> findByWork(Work work);

	/*public List<Object[]> findDocumentId(Long workid, String inspectedBy);*/
	
	
/*	@Query(value="SELECT dud.document_name,dud.id FROM inspection_answer_image_cc ia inner join cc_details cd on cd.work_id=ia.work_Id left join document_upload_details dud on dud.id=document_id where ia.work_Id=:workId and case when cd.modified_by='' "
			+ " then cd.created_by=:inspectedBy else cd.modified_by=:inspectedBy end ",nativeQuery=true) 
	public List<Object[]> findDocumentId(@Param("workId") Long workId,@Param("inspectedBy") String inspectedBy);*/
	
	@Query(value="SELECT dud.document_name,dud.id FROM inspection_answer_image_cc ia inner join cc_details cd on cd.work_id=ia.work_Id left join document_upload_details dud on dud.id=document_id where ia.work_Id=:workId and (cd.modified_by = :inspectedBy or cd.created_by=:inspectedBy or ifnull(ia.image_by, 0) = IFNULL(:userId, ifnull(ia.image_by, 0))) ",nativeQuery=true) 
	public List<Object[]> findDocumentId(@Param("workId") Long workId,@Param("inspectedBy") String inspectedBy,@Param("userId") Long userId);
	
	
}
