package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.AdministrationSanctionType;

public interface AdministrationSanctionTypeRepository  extends JpaRepository<AdministrationSanctionType, Long> {
	
	List<AdministrationSanctionType> findByEnabled(Short isEnabled);
    
}
