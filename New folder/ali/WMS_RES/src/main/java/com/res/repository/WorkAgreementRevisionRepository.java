package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.WorkAgreement;
import com.res.entity.WorkAgreementRevision;

public interface WorkAgreementRevisionRepository extends JpaRepository <WorkAgreementRevision, Long>, CrudRepository<WorkAgreementRevision, Long> {
	
	public List<WorkAgreementRevision> findByWorkAgreement(WorkAgreement workAgreement);
}
