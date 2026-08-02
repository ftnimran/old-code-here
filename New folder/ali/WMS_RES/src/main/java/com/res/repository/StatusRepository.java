package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.Status;

public interface StatusRepository 	extends JpaRepository<Status, Long> {
		
		List<Status> findByOrderById();
		
		@Query("from Year d where d.id in :tsList")
		List<Status> findByTechnicalSanctionList(@Param("tsList") List<Long> tsList);

}
