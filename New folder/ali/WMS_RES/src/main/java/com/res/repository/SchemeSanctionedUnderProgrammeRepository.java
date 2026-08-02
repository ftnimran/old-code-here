package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.SchemeSanctionedUnderProgramme;

public interface SchemeSanctionedUnderProgrammeRepository  extends JpaRepository<SchemeSanctionedUnderProgramme, Long> {
    
	
	List<SchemeSanctionedUnderProgramme> findByEnabled( Short isEnabled);
	
/*	@Query("select count(*) from SchemeSanctionedUnderProgramme b where b.enabled = :isEnabled "
			+ "")
	long findByEnabledCount( Short isEnabled);*/
	
	
	@Query("select count(*) from SchemeSanctionedUnderProgramme b where b.enabled = :enabled")
	long findByEnabledCount(@Param("enabled") Short enabled);
	
//	List<com.nrega.entity.SchemeSanctionedUnderProgramme> findByEnabledAndAgencyTypeAgencyTypeId(Short isEnabled, Long agencyTypeId);
	
	@Query(value="select    c.id, c.name  from quality_inspection a "
			+ "left join work b on a.work_id = b.id "
			+ "left join mst_scheme_sanctioned_under_programme c "
			+ "on b.scheme_sanctioned_under_programme_id = c.id group by 1",nativeQuery=true)
	List<Object[]> fetchschemeForQualityGradingReport();
	//List<District> findByDivisionAndEnabled(Division state, Short isEnabled);
	/*
	List<District> findByEnabledOrderByDistrictNameAsc(Short isEnabled);
	
	District findByDistrictCodeAndEnabled(String districtCode, Short isEnabled);
	List<District> findAll();*/

	
	
/*	@Query("from SchemeSanctionedUnderProgramme b where b.enabled = :enabled and  b.status=COALESCE(:statusName, b.status) "
			+ "")
	Page<SchemeSanctionedUnderProgramme> findByEnabledAndStatusName(Pageable pageable, @Param("enabled") Short enabled
			, @Param("statusName") String statusName);*/
	
	@Query("from SchemeSanctionedUnderProgramme w where w.enabled = :enabled and w.status=COALESCE(:statusName, w.status)")
	Page<SchemeSanctionedUnderProgramme> findByEnabledAndStatusName(Pageable pageable,  @Param("enabled") Short enabled,@Param("statusName") String statusName);
	
	@Query(value="select    c.id, c.name  from quality_inspection a "
			+ "left join work b on a.work_id = b.id "
			+ "left join mst_scheme_sanctioned_under_programme c "
			+ "on b.scheme_sanctioned_under_programme_id = c.id where c.agencyTypeId = :agencyTypeId group by 1",nativeQuery=true)
	List<Object[]> fetchschemeForQualityGradReports(@Param("agencyTypeId") Long agencyTypeId);
}
