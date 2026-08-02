package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.AdministrationSanction;
import com.res.entity.AdministrativeStatus;
import com.res.entity.TechnicalSanction;
import com.res.entity.Work;

public interface AdministrationSanctionRepository  extends JpaRepository<AdministrationSanction, Long> {
	
/*	@Query("from AdministrationSanction a where a.work = :work")
	AdministrationSanction findByWork(@Param("work") Work work);*/
	AdministrationSanction findById(Long id);
	@Query("from AdministrationSanction t where t.work = :work order by created_date DESC")
 	List<AdministrationSanction> findAllASByWork(@Param("work") Work work);
	
	@Query("from AdministrationSanction w where  w.work.workRequestStatusId.id>=5 and w.work.isLegacy=0 and w.work.executiveEngineerOffice.id=:officeId"
			+ " and w.work.agencyTypeId.agencyTypeId in (1) ")
	Page<AdministrationSanction> findWorkTender(Pageable pageable, @Param("officeId") Long officeId);
	
	
	/*@Query("select count(*) from AdministrationSanction w where w.work.workRequestStatusId.id>=5 and w.work.isLegacy=0")
	long countWorkTender();	*/
	
    
	/*Page<Entrepreneur> findByStatusNot(Pageable pageable, String status);
	
	long countByStatusNot(String status);
	
	List<Entrepreneur> findByStatusAndParentAccountIsNull(String status);
	
	@Query("from Entrepreneur e where (e.entrepreneurName like %:entrepreneurName% or e.accountNo like %:accountNo%) and status != :status")
	Page<Entrepreneur> findByEntrepreneurNameContainingOrAccountNoContainingAndStatusNot(Pageable pageable, @Param("entrepreneurName")String entrepreneurName, 
			@Param("accountNo")String accountNo, @Param("status")String status);
	
	@Query("from Entrepreneur e where (e.entrepreneurType like %:entrepreneurType% and e.district.districtName like %:districtName% and e.status like :status%)")
	Page<Entrepreneur> findByEntrepreneurTypeOrDistrictNameOrStatus(Pageable pageable, @Param("entrepreneurType")String entrepreneurType, 
			@Param("districtName")String districtName, @Param("status")String status);
	
	@Query("from Entrepreneur e where (e.entrepreneurName like %:entrepreneurName% or e.accountNo like %:accountNo%) and (e.entrepreneurType like %:entrepreneurType%"
			+ " and e.district.districtName like %:districtName% and e.status like :status%)")
	Page<Entrepreneur> findByEntrepreneurNameContainingOrAccountNoContainingAndEntrepreneurTypeOrDistrictNameOrStatus(Pageable pageable, @Param("entrepreneurName")String entrepreneurName, 
			@Param("accountNo")String accountNo, @Param("entrepreneurType")String entrepreneurType, @Param("districtName")String districtName, @Param("status")String status);

*/
	
	@Query("from AdministrationSanction a where a.technicalSanction = :technicalSanctionId")
	AdministrationSanction findByTechnicalSanctionId(@Param("technicalSanctionId") TechnicalSanction technicalSanctionId);
	
	@Query("from AdministrationSanction a where a.administrationSanctionType.administrationSanctionTypeId = :id and a.work.financialYear='AS Pending' and a.work.isLegacy=0")
 	List<AdministrationSanction> findByAdministrationSanctionTypeAdministrationSanctionTypeIdForNonLegacyData(@Param("id") Long id);
	
	@Query("from AdministrationSanction a where a.work.financialYear='AS Pending' and a.work.isLegacy=1")
 	List<AdministrationSanction> findByAdministrationSanctionTypeAdministrationSanctionTypeIdForLegacyData();
	
	
	public List<AdministrationSanction> findByWorkIdOrderByCreatedDateDesc(Long id);
	
	public List<AdministrationSanction> findByWorkAndAdministrativeStatusOrderByCreatedDateDesc(Work work, AdministrativeStatus status);
	

}
