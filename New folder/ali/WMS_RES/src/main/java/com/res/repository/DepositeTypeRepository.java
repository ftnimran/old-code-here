package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.res.entity.DepositeType;

public interface DepositeTypeRepository extends JpaRepository<DepositeType, Long> {
	
	List<DepositeType> findByEnabled(Short isEnabled);

}
