package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.OfficeType;

public interface OfficeTypeRepository  extends JpaRepository<OfficeType, Long> {
	
	List<OfficeType> findByEnabled(Short isEnabled);
    
}
