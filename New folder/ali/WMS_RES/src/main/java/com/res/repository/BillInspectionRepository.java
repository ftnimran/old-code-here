package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.Bill;
import com.res.entity.BillInspection;

import com.res.entity.Year;

public interface BillInspectionRepository  extends JpaRepository<BillInspection, Long> {
	
	/*List<Year> findByOrderById();
	
	@Query("from Year d where d.id in :tsList")
	List<Year> findByTechnicalSanctionList(@Param("tsList") List<Long> tsList);*/
	List<BillInspection> findByBillAndInspectedByRoleAndCreatedBy(Bill bill,String inspectedBy,String CreatedBy );

	List<BillInspection> findByBillAndCreatedBy(Bill bill,String CreatedBy );
    
}
