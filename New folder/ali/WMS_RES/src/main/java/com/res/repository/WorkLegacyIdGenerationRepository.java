package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.res.entity.Users;
import com.res.entity.WorkLegacyIdGeneration;

@Transactional(readOnly = true)
public interface WorkLegacyIdGenerationRepository extends JpaRepository<WorkLegacyIdGeneration, Long>{

//	List<WorkLegacyIdGeneration> findByDivisionAgency(String divisionAgency);
	
	/*@Modifying
	@Transactional
    @Query("delete from WorkLegacyIdGeneration w where w.id = (select a.i from (select max(b.id) i  from WorkLegacyIdGeneration  b  where b.divisionAgency = :divisionAgency)  a)")
	void deleteLegacyGenerationWorkByDivisionAgencyAndMaxCount(@Param("divisionAgency") String divisionAgency);*/

	//delete from `work_legacy_id_generation`  where id = (
	//select i from (select max(b.id) as i  from `work_legacy_id_generation` as b  where b.division_agency = "LD_Division Bhopal_GP") as a
	//);
	
	@Query("select max(w.id) from WorkLegacyIdGeneration w where w.divisionAgency = :divisionAgency")
	Long fetchMaxIdByDivisionAgency(@Param("divisionAgency") String divisionAgency);
	
	long deleteById(Long id);
	
	
	WorkLegacyIdGeneration findByDivisionAgency(String divisionAgency);
}
