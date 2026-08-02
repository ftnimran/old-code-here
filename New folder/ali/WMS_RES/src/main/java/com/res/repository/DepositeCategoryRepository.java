package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.res.entity.DepositeCategory;

public interface DepositeCategoryRepository extends JpaRepository<DepositeCategory, Long>{
	
	List<DepositeCategory> findByEnabled(Short isEnabled);

}
