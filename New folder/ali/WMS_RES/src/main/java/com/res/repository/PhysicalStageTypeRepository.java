package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.PhysicalStageType;
import com.res.entity.WorkType;

public interface PhysicalStageTypeRepository  extends JpaRepository<PhysicalStageType, Long> {
	
	List<PhysicalStageType> findByEnabled(Short isEnabled);
    
	List<PhysicalStageType> findByWorkType(WorkType workType);
}
