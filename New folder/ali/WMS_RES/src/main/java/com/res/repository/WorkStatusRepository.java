package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.WorkStatus;

public interface WorkStatusRepository  extends JpaRepository<WorkStatus, Long> {
	
	List<WorkStatus> findByEnabled(Short isEnabled);
    
}
