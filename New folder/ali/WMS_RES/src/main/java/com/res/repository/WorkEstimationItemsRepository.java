package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.WorkEstimation;
import com.res.entity.WorkEstimationItems;

public interface WorkEstimationItemsRepository extends
		JpaRepository<WorkEstimationItems, Integer> {

	List<WorkEstimationItems> findByWorkEstimationAndEnabled(
			WorkEstimation workEstimation, Boolean enabled);

	
}
