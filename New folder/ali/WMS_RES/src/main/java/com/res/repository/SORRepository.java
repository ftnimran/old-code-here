package com.res.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.SOR;

public interface SORRepository extends JpaRepository<SOR, Long> {

	
	@Query("from SOR s  where (s.referenceName like %:searchBoxVal% or s.year.year like %:year% ) and  s.status.statusName='Active' order by s.dateOfAdoption Desc")
	Page<SOR> findAllSORs(Pageable pageable, @Param("searchBoxVal")String searchBoxVal, @Param("year") String year);

	@Query("select count(*) from SOR s where s.status.statusName='Active'")
	long countAllSORs();
	
	@Query("from SOR s  where  s.status.statusName='Active' order by s.dateOfAdoption Desc")
	Page<SOR> findAllSORs(Pageable pageable);
	

}
