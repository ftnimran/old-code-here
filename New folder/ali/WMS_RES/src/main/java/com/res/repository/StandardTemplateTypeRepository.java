package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.StandardTemplateType;
import com.res.entity.WorkType;

public interface StandardTemplateTypeRepository extends
		JpaRepository<StandardTemplateType, Long> {

	StandardTemplateType findByIdAndEnabled(Long id, Short enabled);

	List<StandardTemplateType> findByWorkTypeWorkTypeIdAndEnabled(Long workTypeId,
			Short enabled);

}