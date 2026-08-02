package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.InspectionAnswerCC;
import com.res.entity.Work;

public interface InspectionAnswerRepositoryCC  extends JpaRepository<InspectionAnswerCC, Long>, CrudRepository<InspectionAnswerCC, Long> {
    
	public List<InspectionAnswerCC> findByWork(Work work);

	/*public List<Object> findByWorkAndInspectedby(Work work, Users users);*/
	
	/*@Query(value="SELECT ia.id,ia.code,ia.question_text,ia.answer,ia.work_Id,case when cd.modified_by = '' then cd.created_by  else cd.modified_by end  FROM inspection_answer_cc ia inner join cc_details cd on cd.work_id=ia.work_Id "
			+ " where ia.work_Id=:workId and case when cd.modified_by = '' then cd.created_by=:inspectedBy else cd.modified_by=:inspectedBy end ",nativeQuery=true) 
	public List<Object[]> findByWorkAndInspectedby(@Param("workId") Long workId,@Param("inspectedBy") String inspectedBy);*/
	
	@Query(value="SELECT ia.id,ia.code,ia.question_text,ia.answer,ia.work_Id,case when cd.modified_by = '' then cd.created_by  else cd.modified_by end  FROM inspection_answer_cc ia inner join cc_details cd on cd.work_id=ia.work_Id "
			+ " where ia.work_Id=:workId and (cd.modified_by = :inspectedBy or cd.created_by=:inspectedBy or ifnull(ia.answer_by, 0) = IFNULL(:userId, ifnull(ia.answer_by, 0))) ",nativeQuery=true) 
	public List<Object[]> findByWorkAndInspectedby(@Param("workId") Long workId,@Param("inspectedBy") String inspectedBy,@Param("userId") Long userId);
	
	
}
