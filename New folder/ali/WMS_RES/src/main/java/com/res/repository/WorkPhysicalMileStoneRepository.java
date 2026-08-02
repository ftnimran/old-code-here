package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.WorkAgreement;
import com.res.entity.WorkAgreementRevision;
import com.res.entity.WorkPhysicalMileStone;


public interface WorkPhysicalMileStoneRepository extends JpaRepository<WorkPhysicalMileStone, Long>, CrudRepository<WorkPhysicalMileStone, Long>{
	
	public List<WorkPhysicalMileStone> findByWorkAgreementAndWorkAgreementRevisionIsNull(WorkAgreement workAgreement);
	
	@Query("from WorkPhysicalMileStone w where (w.workAgreement.work.status is null or w.workAgreement.work.status!='Deleted') and w.workAgreementRevision is null")
	public List<WorkPhysicalMileStone> fetchPhyMileByWorkAgreementRevisionIsNull();
	
	public List<WorkPhysicalMileStone> findByWorkAgreementRevision(WorkAgreementRevision workAgreementRevision);

	public List<WorkPhysicalMileStone> findByWorkAgreement(WorkAgreement parentWorkAgreement);

}
