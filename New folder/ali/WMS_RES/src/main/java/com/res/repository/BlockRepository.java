package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.Block;

public interface BlockRepository  extends JpaRepository<Block, Long> {

	List<Block> findByDistrictCodeAndEnabled(String districtCode, short isEnabled);

	Block findByBlockCode(String blockCode);
    
//	List<Block> findByDistrictAndEnabled(District district, Short isEnabled);
}
