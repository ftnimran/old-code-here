package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.StandardTemplateType;
import com.res.entity.WorkSubType;
import com.res.entity.WorkTemplate;
import com.res.entity.WorkType;

public interface WorkTemplateRepository extends
		JpaRepository<WorkTemplate, Long> {

	List<WorkTemplate> findByEnabled(Boolean isEnabled);

	List<WorkTemplate> findByWorkTypeAndEnabled(WorkType workType,
			Boolean isEnabled);

	List<WorkTemplate> findByWorkTypeAndParentItemAndEnabled(WorkType workType,
			Boolean hasChild, Boolean isEnabled);
	
	List<WorkTemplate> findByWorkTypeAndStandardTemplateTypeAndTemplateTypeAndParentItemAndEnabled(WorkType workType, 
			StandardTemplateType standardTemplateType, Short templateType,
			Boolean parentItem, Boolean enabled);
}