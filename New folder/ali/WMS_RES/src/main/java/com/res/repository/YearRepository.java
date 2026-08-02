package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.Year;

public interface YearRepository  extends JpaRepository<Year, Long> {
	
	List<Year> findByOrderById();
	
	@Query("from Year d where d.id in :tsList")
	List<Year> findByTechnicalSanctionList(@Param("tsList") List<Long> tsList);

	
    
}
