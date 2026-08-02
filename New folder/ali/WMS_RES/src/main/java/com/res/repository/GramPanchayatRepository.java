package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.GramPanchayat;

public interface GramPanchayatRepository  extends JpaRepository<GramPanchayat, Long> {

//	List<Block> findByDistrictCodeAndEnabled(String districtCode, short isEnabled);

	List<GramPanchayat> findByBlockCodeAndEnabled(String blockCode, short isEnabled);
    
	List<GramPanchayat> findByGpCode(String gpCode);
//	List<Block> findByDistrictAndEnabled(District district, Short isEnabled);


}
