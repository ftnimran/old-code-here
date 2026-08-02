package com.res.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.PhysicalCCDispatchDetails;
import com.res.entity.Work;
import com.res.entity.WorkAgreement;

public interface PhysicalCCDispatchRepository extends JpaRepository <PhysicalCCDispatchDetails, Long>, CrudRepository<PhysicalCCDispatchDetails, Long> {
	
	public PhysicalCCDispatchDetails findByWork(Work work);	
}
