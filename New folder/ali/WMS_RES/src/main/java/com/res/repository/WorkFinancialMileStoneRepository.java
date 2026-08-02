package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.WorkAgreement;
import com.res.entity.WorkAgreementRevision;
import com.res.entity.WorkFinancialMileStone;
import com.res.entity.WorkPhysicalMileStone;

public interface WorkFinancialMileStoneRepository extends JpaRepository<WorkFinancialMileStone, Long>, CrudRepository<WorkFinancialMileStone, Long>{
	
	public List<WorkFinancialMileStone> findByWorkAgreementAndWorkAgreementRevisionIsNull(WorkAgreement workAgreement);
	
	@Query("from WorkFinancialMileStone w where (w.workAgreement.work.status is null or w.workAgreement.work.status!='Deleted') and w.workAgreementRevision is null")
	public List<WorkFinancialMileStone> fetchFinMileByWorkAgreementRevisionIsNull();
	
	public List<WorkFinancialMileStone> findByWorkAgreementRevision(WorkAgreementRevision workAgreementRevision);

	public List<WorkFinancialMileStone> findByWorkAgreement(WorkAgreement parentWorkAgreement);
}
