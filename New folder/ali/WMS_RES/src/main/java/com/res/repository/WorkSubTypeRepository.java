package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.WorkSubType;
import com.res.entity.WorkType;

public interface WorkSubTypeRepository  extends JpaRepository<WorkSubType, Long> {
	
	List<WorkSubType> findByEnabled(Short isEnabled);
    
	List<WorkSubType> findByWorkType(WorkType workType);

	List<WorkSubType> findByWorkTypeAndEnabled(WorkType workType, short s);
}
