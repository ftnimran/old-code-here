package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.TechnicalSanction;
import com.res.entity.Work;
import com.res.entity.WorkEstimation;
import com.res.entity.WorkLogging;
import com.res.entity.WorkTender;

public interface WorkLoggingRepository extends JpaRepository<WorkLogging, Long> {

	Page<WorkLogging> findById(Pageable pageable,Long workId);

	WorkLogging findByLogId(Long workLoggingId);

	public List<WorkLogging> findByIdOrderByCreatedDate(Long id);
	


	
}
