package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.AgencyType;

public interface AgencyTypeRepository  extends JpaRepository<AgencyType, Long> {
	
	List<AgencyType> findByEnabled(Short isEnabled);
	
	List<AgencyType> findByAgencyTypeIdAndEnabled(Long agencyTypeId,Short isEnabled);
    
}
