package com.res.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.Work;
import com.res.entity.WorkAgreement;
import com.res.entity.WorkTender;

public interface WorkAgreementRepository extends JpaRepository <WorkAgreement, Long>, CrudRepository<WorkAgreement, Long> {
	
	public WorkAgreement findById(Long id);
	public WorkAgreement findByWork(Work work);
	
	public List<WorkAgreement> findByWorkOrderByCreatedDateDesc(Work work);
	
	@Query(value="select tentative_completion_date from work_agreement where status=2 and work_id=:workId order by created_date DESC limit 1",nativeQuery=true)
	public Date fetchTentativeCompletionDateByWorkId(@Param("workId") Long workId);
	
	public WorkAgreement findByWorkTender(WorkTender workTender);
	
	@Query("from WorkAgreement t where t.work = :work and (t.work.status is null or t.work.status!='Deleted') order by created_date DESC")
	public List<WorkAgreement> findAllWorkAgreementByWork(@Param("work") Work work);
}
