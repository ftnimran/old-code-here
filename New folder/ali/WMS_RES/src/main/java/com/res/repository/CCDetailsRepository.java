package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.CCDetails;
import com.res.entity.Work;

public interface CCDetailsRepository extends JpaRepository <CCDetails, Long>, CrudRepository<CCDetails, Long> {
	
	public CCDetails findByWork(Work work);
	
	public List<CCDetails> findByWorkOrderByCreatedDateDesc(Work work);
	
	public CCDetails findByWorkAndApproved(Work work, Boolean approved);
	
	@Query(value="select work_completed_on, physical_cc_issued_on, financial_cc_issued_on from cc_details where approved=1 and work_id=:workId order by created_date DESC limit 1",nativeQuery=true)
	public Object[] fetchColumnsByWorkId(@Param("workId") Long workId);
}
