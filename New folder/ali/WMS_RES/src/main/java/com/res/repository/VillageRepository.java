package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.Village;

public interface VillageRepository extends JpaRepository<Village, Long> {
	
	List<Village> findByGpCodeAndEnabled(String gpCode, short isEnabled);

	Village findByVillageCode(String string);

}
