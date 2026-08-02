package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.District;

public interface DistrictRepository  extends JpaRepository<District, Long> {
    
//	List<District> findByStateAndEnabled(State state, Short isEnabled);
	
	List<District> findByEnabledOrderByDistrictNameAsc(Short isEnabled);
	
	District findByDistrictCodeAndEnabled(String districtCode, Short isEnabled);
	List<District> findAll();

	District findByLgdDistrictCode(String lgdDistrictCode);

	List<District> findAllByDistrictId(Long districtId);
	
	District findByDistrictName(String districtName);
}
