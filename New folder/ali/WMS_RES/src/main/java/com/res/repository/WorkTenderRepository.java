package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.bean.StateBean;
import com.res.entity.AdministrationSanction;
import com.res.entity.Work;
import com.res.entity.WorkTender;

public interface WorkTenderRepository extends JpaRepository<WorkTender, Long> {	
	
	public WorkTender findByWorkId(Long id);
	
	public List<WorkTender> findByWorkIdOrderByCreatedDateDesc(Long id);
	
	public WorkTender findByWork(Work work);
	
	@Query("select count(*) from WorkTender w where (w.work.status is null or w.work.status!='Deleted') and w.id>=1 and w.work.isLegacy=0")
	long countWorkTenderList();

	WorkTender findByAdministrationSanction(AdministrationSanction administrationSanction);	
	public WorkTender findById(Long id);

	
	
	@Query("from WorkTender t where t.work = :work order by created_date DESC")
 	List<WorkTender> findAllTenderByWork(@Param("work") Work work);

	public List<WorkTender> findByWorkIdOrderByCreatedDate(Long id);
	

}
