package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.TechnicalSanctionType;

public interface TechnicalSanctionTypeRepository  extends JpaRepository<TechnicalSanctionType, Long> {
	
	List<TechnicalSanctionType> findByEnabled(Short isEnabled);
    
}
