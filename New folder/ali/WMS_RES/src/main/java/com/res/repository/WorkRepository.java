package com.res.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.Contractor;
import com.res.entity.District;
import com.res.entity.Office;
import com.res.entity.Users;
import com.res.entity.Work;
import com.res.entity.WorkEstimation;

public interface WorkRepository
		extends JpaRepository<Work, Long>, CrudRepository<Work, Long>, WorkReportRepositoryCustom {

	Work findByIdAndIsLegacy(Long id, Short isLegacy);

	List<Work> findByWorkNameContainingAndStatus(String workName, String status);

	List<Work> findByWorkNameContainingAndStatusAndSuperintendingEngineerOffice(String workName, String status,
			Office superintendingEngineerOffice);

	List<Work> findByWorkNameContainingAndStatusAndChiefEngineerOffice(String workName, String status,
			Office chiefEngineerOffice);

	List<Work> findByWorkNameContainingAndStatusAndAssistantEngineer(String workName, String status,
			Users assistantEngineer);

	List<Work> findByWorkNameContainingAndStatusAndSubDivisionalOfficer(String workName, String status,
			Users subDivisionalOfficer);

//Rakesh
	List<Work> findByDistrictAndIsLegacy(District district, Short isLegacy);
	// List<Work> findByecutiveEngineerOffice

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and w.assistantEngineer = :assistantEngineer and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	Page<Work> findByAssistantEngineer(Pageable pageable, @Param("assistantEngineer") Users assistantEngineer);

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and w.subDivisionalOfficer = :subDivisionalOfficer and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	Page<Work> findBySubDivisionalOfficer(Pageable pageable, @Param("subDivisionalOfficer") Users subDivisionalOfficer);

	@Query("from Work w left join w.workTender wt   where (w.status is null or w.status!='Deleted') and (w.contractor = :contractor or wt.contractorId=:contractor) and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	Page<Work> findByContractor(Pageable pageable, @Param("contractor") Contractor contractor);

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and w.assistantEngineer = :assistantEngineer and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7)  ) and w.workName like %:search% ")
	Page<Work> findByAssistantEngineerByWorkName(Pageable pageable, @Param("assistantEngineer") Users assistantEngineer,
			@Param("search") String search);

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and w.subDivisionalOfficer = :subDivisionalOfficer and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7)  ) and w.workName like %:search% ")
	Page<Work> findBySubDivisionalOfficerByWorkName(Pageable pageable,
			@Param("subDivisionalOfficer") Users subDivisionalOfficer, @Param("search") String search);

	@Query("from Work w left join w.workTender wt   where (w.status is null or w.status!='Deleted') and (w.contractor = :contractor or wt.contractorId=:contractor) and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) ) and w.workName like %:search% ")
	Page<Work> findByContractorByWorkName(Pageable pageable, @Param("contractor") Contractor contractor,
			@Param("search") String search);

	@Query("select count(*) from Work w  where (w.status is null or w.status!='Deleted') and  w.assistantEngineer = :assistantEngineer and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	long countByAssistantEngineer(@Param("assistantEngineer") Users assistantEngineer);

	@Query("select count(*) from Work w  where (w.status is null or w.status!='Deleted') and  w.subDivisionalOfficer = :subDivisionalOfficer and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	long countBySubDivisionalOfficer(@Param("subDivisionalOfficer") Users subDivisionalOfficer);

	@Query("select count(*) from Work w left join w.workTender wt  where (w.status is null or w.status!='Deleted') and  (w.contractor = :contractor or wt.contractorId=:contractor) and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	long countByContractor(@Param("contractor") Contractor contractor);

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and  w.subEngineer = :subEngineer and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7)  ) and w.workName like %:search% ")
	Page<Work> findBySubEngineerByWorkName(Pageable pageable, @Param("subEngineer") Users subEngineer,
			@Param("search") String search);

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and  w.subEngineer = :subEngineer and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	Page<Work> findBySubEngineer(Pageable pageable, @Param("subEngineer") Users subEngineer);

	@Query("select count(*) from Work w  where (w.status is null or w.status!='Deleted') and  w.subEngineer = :subEngineer and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	long countBySubEngineer(@Param("subEngineer") Users subEngineer);

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and  w.superintendingEngineerOffice = :superintendingEngineerOffice and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5 )  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	Page<Work> findBySuperintendingEngineerOffice(Pageable pageable,
			@Param("superintendingEngineerOffice") Office superintendingEngineerOffice);

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and  w.chiefEngineerOffice = :chiefEngineerOffice and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	Page<Work> findByChiefEngineerOffice(Pageable pageable, @Param("chiefEngineerOffice") Office chiefEngineerOffice);

	@Query("select count(*) from Work w  where (w.status is null or w.status!='Deleted') and  w.executiveEngineerOffice = :executiveEngineerOffice and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )") // and w.workStatusId.id in
																							// (2,4)
	long countWorkListforEE(@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and  w.executiveEngineerOffice = :executiveEngineerOffice and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )") // and w.workStatusId.id in
																							// (2,4)
	Page<Work> fetchWorkListforEE(Pageable pageable, @Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("from Work w  where (w.status is null or w.status!='Deleted') and  w.executiveEngineerOffice = :executiveEngineerOffice and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR "
			+ "  (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7)  ) and w.workName like %:search% ") // and
																															// w.workStatusId.id
																															// in
																															// (2,4)
	Page<Work> fetchWorkListforEEByWorkName(Pageable pageable,
			@Param("executiveEngineerOffice") Office executiveEngineerOffice, @Param("search") String search);

	Page<Work> findByWorkNameContaining(Pageable pageable, String workName);

	@Query("from Work w where w.status = :status")
	Page<Work> findByStatus(Pageable pageable, @Param("status") String status);

	@Query("select count(*) from Work w where w.status = :status")
	long countByStatus(@Param("status") String status);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.executiveEngineerOffice.id = :officeId")
	Page<Work> findByOffice(Pageable pageable, @Param("officeId") Long id);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.executiveEngineerOffice.id = :officeId")
	long countByOffice(@Param("officeId") Long id);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and (:workName is null or w.workName like %:workName%) and w.workTypeId.workTypeId=COALESCE(:workType, w.workTypeId.workTypeId) and (w.workSubTypeId.workSubTypeId is null or w.workSubTypeId.workSubTypeId=COALESCE(:workSubType, w.workSubTypeId.workSubTypeId)) "
			+ "and w.executiveEngineerOffice = :executiveEngineerOffice and w.workRequestStatusId.id>=2 and w.isLegacy=0")
	Page<Work> findPendingWorkEstimationsByExecutiveEngineerOffice(Pageable pageable,
			@Param("workName") String workName, @Param("workType") Long workType,
			@Param("workSubType") Long workSubType, @Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("select wes.work from WorkEstimation wes where wes.id in (select max(we.id) from WorkEstimation we where (:workName is null or we.work.workName like %:workName%) and we.work.workTypeId.workTypeId=COALESCE(:workType, we.work.workTypeId.workTypeId) and (we.work.workSubTypeId.workSubTypeId is null or we.work.workSubTypeId.workSubTypeId=COALESCE(:workSubType, we.work.workSubTypeId.workSubTypeId)) "
			+ "and we.work.superintendingEngineerOffice = :superintendingEngineerOffice and we.work.workRequestStatusId.id>=2 and we.work.isLegacy=0 and (we.work.status is null or we.work.status!='Deleted') and we.status.id>1 group by we.work.id)")
	Page<Work> findPendingWorkEstimationsBySEOffice(Pageable pageable, @Param("workName") String workName,
			@Param("workType") Long workType, @Param("workSubType") Long workSubType,
			@Param("superintendingEngineerOffice") Office superintendingEngineerOffice);

	@Query("select max(we.id) from WorkEstimation we where (:workName is null or we.work.workName like %:workName%) and we.work.workTypeId.workTypeId=COALESCE(:workType, we.work.workTypeId.workTypeId) and (we.work.workSubTypeId.workSubTypeId is null or we.work.workSubTypeId.workSubTypeId=COALESCE(:workSubType, we.work.workSubTypeId.workSubTypeId)) "
			+ "and we.work.superintendingEngineerOffice = :superintendingEngineerOffice and we.work.workRequestStatusId.id>=2 and we.work.isLegacy=0 and (we.work.status is null or we.work.status!='Deleted') and we.status.id>1 group by we.work.id")
	List<Long> findPendingWorkEstimationsBySEOfficeNew(Pageable pageable, @Param("workName") String workName,
			@Param("workType") Long workType, @Param("workSubType") Long workSubType,
			@Param("superintendingEngineerOffice") Office superintendingEngineerOffice);

	@Query("select count(*) from WorkEstimation wes where wes.id in (select max(we.id) from WorkEstimation we where "
			+ "we.work.superintendingEngineerOffice = :superintendingEngineerOffice and we.work.workRequestStatusId.id>=2 and we.work.isLegacy=0 and (we.work.status is null or we.work.status!='Deleted') and we.status.id>1 group by we.work.id)")
	long countPendingWorkEstimationsBySEOffice(
			@Param("superintendingEngineerOffice") Office superintendingEngineerOffice);

	@Query("select max(we.id) from WorkEstimation we where "
			+ "we.work.superintendingEngineerOffice = :superintendingEngineerOffice and we.work.workRequestStatusId.id>=2 and we.work.isLegacy=0 and (we.work.status is null or we.work.status!='Deleted') and we.status.id>1 group by we.work.id)")
	List<Long> countPendingWorkEstimationsBySEOfficeNew(
			@Param("superintendingEngineerOffice") Office superintendingEngineerOffice);

	@Query("select wes.work from WorkEstimation wes where wes.id in (select max(we.id) from WorkEstimation we where (:workName is null or we.work.workName like %:workName%) and we.work.workTypeId.workTypeId=COALESCE(:workType, we.work.workTypeId.workTypeId) and (we.work.workSubTypeId.workSubTypeId is null or we.work.workSubTypeId.workSubTypeId=COALESCE(:workSubType, we.work.workSubTypeId.workSubTypeId)) "
			+ "and we.work.chiefEngineerOffice = :chiefEngineerOffice and we.work.workRequestStatusId.id>=2 and we.work.isLegacy=0 and (we.work.status is null or we.work.status!='Deleted') and we.status.id>1 group by we.work.id)")
	Page<Work> findPendingWorkEstimationsByCEOffice(Pageable pageable, @Param("workName") String workName,
			@Param("workType") Long workType, @Param("workSubType") Long workSubType,
			@Param("chiefEngineerOffice") Office chiefEngineerOffice);

	@Query("select max(we.id) from WorkEstimation we where (:workName is null or we.work.workName like %:workName%) and we.work.workTypeId.workTypeId=COALESCE(:workType, we.work.workTypeId.workTypeId) and (we.work.workSubTypeId.workSubTypeId is null or we.work.workSubTypeId.workSubTypeId=COALESCE(:workSubType, we.work.workSubTypeId.workSubTypeId)) "
			+ "and we.work.chiefEngineerOffice = :chiefEngineerOffice and we.work.workRequestStatusId.id>=2 and we.work.isLegacy=0 and (we.work.status is null or we.work.status!='Deleted') and we.status.id>1 group by we.work.id")
	List<Long> findPendingWorkEstimationsByCEOfficeNew(Pageable pageable, @Param("workName") String workName,
			@Param("workType") Long workType, @Param("workSubType") Long workSubType,
			@Param("chiefEngineerOffice") Office chiefEngineerOffice);

	@Query("select count(*) from WorkEstimation wes where wes.id in (select max(we.id) from WorkEstimation we where "
			+ "we.work.chiefEngineerOffice = :chiefEngineerOffice and we.work.workRequestStatusId.id>=2 and we.work.isLegacy=0 and (we.work.status is null or we.work.status!='Deleted') and we.status.id>1 group by we.work.id)")
	long countPendingWorkEstimationsByCEOffice(@Param("chiefEngineerOffice") Office chiefEngineerOffice);

	@Query("select max(we.id) from WorkEstimation we where "
			+ "we.work.chiefEngineerOffice = :chiefEngineerOffice and we.work.workRequestStatusId.id>=2 and we.work.isLegacy=0 and (we.work.status is null or we.work.status!='Deleted') and we.status.id>1 group by we.work.id")
	List<Long> countPendingWorkEstimationsByCEOfficeNew(@Param("chiefEngineerOffice") Office chiefEngineerOffice);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and (:workName is null or w.workName like %:workName%) and w.workTypeId.workTypeId=COALESCE(:workType, w.workTypeId.workTypeId) and (w.workSubTypeId.workSubTypeId is null or w.workSubTypeId.workSubTypeId=COALESCE(:workSubType, w.workSubTypeId.workSubTypeId)) "
			+ "and w.assistantEngineer = :assistantEngineer and w.workRequestStatusId.id>=2 and w.isLegacy=0")
	Page<Work> findPendingWorkEstimationsByAssistantEngineer(Pageable pageable, @Param("workName") String workName,
			@Param("workType") Long workType, @Param("workSubType") Long workSubType,
			@Param("assistantEngineer") Users assistantEngineer);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and (:workName is null or w.workName like %:workName%) and w.workTypeId.workTypeId=COALESCE(:workType, w.workTypeId.workTypeId) and (w.workSubTypeId.workSubTypeId is null or w.workSubTypeId.workSubTypeId=COALESCE(:workSubType, w.workSubTypeId.workSubTypeId)) "
			+ "and w.subDivisionalOfficer = :subDivisionalOfficer and w.workRequestStatusId.id>=2 and w.isLegacy=0")
	Page<Work> findPendingWorkEstimationsBySubDivisionalOfficer(Pageable pageable, @Param("workName") String workName,
			@Param("workType") Long workType, @Param("workSubType") Long workSubType,
			@Param("subDivisionalOfficer") Users subDivisionalOfficer);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and (:workName is null or w.workName like %:workName%) and w.workTypeId.workTypeId=COALESCE(:workType, w.workTypeId.workTypeId) and (w.workSubTypeId.workSubTypeId is null or w.workSubTypeId.workSubTypeId=COALESCE(:workSubType, w.workSubTypeId.workSubTypeId)) "
			+ "and w.subEngineer = :subEngineer and w.workRequestStatusId.id>=2 and w.isLegacy=0")
	Page<Work> findPendingWorkEstimationsBySubEngineer(Pageable pageable, @Param("workName") String workName,
			@Param("workType") Long workType, @Param("workSubType") Long workSubType,
			@Param("subEngineer") Users subEngineer);
	
	

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>=2 and w.assistantEngineer = :assistantEngineer and w.isLegacy=0")
	long countPendingWorkEstimationsByAssistantEngineer(@Param("assistantEngineer") Users assistantEngineer);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>=2 and w.subDivisionalOfficer = :subDivisionalOfficer and w.isLegacy=0")
	long countPendingWorkEstimationsBySubDivisionalOfficer(@Param("subDivisionalOfficer") Users subDivisionalOfficer);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>=2 and w.subEngineer = :subEngineer and w.isLegacy=0")
	long countPendingWorkEstimationsBySubEngineer(@Param("subEngineer") Users subEngineer);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy")
	Page<Work> findByIsLegacy(Pageable pageable, @Param("isLegacy") Short isLegacy);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy")
	long countByIsLegacy(@Param("isLegacy") Short isLegacy);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.executiveEngineerOffice.id = :officeId ")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeId(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.executiveEngineerOffice.id = :officeId ")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeId2(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = :officeId")
	long countByIsLegacyAndExecutiveEngineerOfficeId(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id = :workRequestStatusId and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId")
	Page<Work> findByIsLegacyAndStatusAndChiefEngineerOfficeId(Pageable pageable, @Param("isLegacy") Short isLegacy,
			@Param("workRequestStatusId") Long id, @Param("officeId") Long officeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id = :workRequestStatusId and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId")
	long countByIsLegacyAndStatusAndChiefEngineerOfficeId(@Param("isLegacy") Short isLegacy,
			@Param("workRequestStatusId") Long id, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.subEngineer.id = :userId and w.executiveEngineerOffice.id = :officeId")
	Page<Work> findByIsLegacyAndSubEngineerUserIdAndOfficeId(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("userId") Long userId, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.subDivisionalOfficer.id = :userId and w.executiveEngineerOffice.id = :officeId")
	Page<Work> findByIsLegacyAndSubDivisionalOfficeUserIdAndOfficeId(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("userId") Long userId, @Param("officeId") Long officeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.subEngineer.id = :userId and w.executiveEngineerOffice.id = :officeId")
	long countByIsLegacyAndSubEngineerUserIdAndOfficeId(@Param("isLegacy") List isLegacy, @Param("userId") Long userId,
			@Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.assistantEngineer.id = :userId and w.executiveEngineerOffice.id = :officeId")
	Page<Work> findByIsLegacyAndAssistantEngineerUserIdAndOfficeId(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("userId") Long userId, @Param("officeId") Long officeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.assistantEngineer.id = :userId and w.executiveEngineerOffice.id = :officeId")
	long countByIsLegacyAndAssistantEngineerUserIdAndOfficeId(@Param("isLegacy") List isLegacy,
			@Param("userId") Long userId, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.subDivisionalOfficer.id = :userId and w.executiveEngineerOffice.id = :officeId")
	Page<Work> findByIsLegacyAndSubDivisionalOfficerUserIdAndOfficeId(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("userId") Long userId, @Param("officeId") Long officeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.subDivisionalOfficer.id = :userId and w.subDivisionalOfficer.id = :officeId")
	long countByIsLegacyAndSubDivisionalOfficerUserIdAndOfficeId(@Param("isLegacy") List isLegacy,
			@Param("userId") Long userId, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) or w.lineDepartmentId.lineDepartmentId is null)"
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id) or w.accountHead.id is null) "
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId) or w.agencyTypeId.agencyTypeId is null)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId) or w.block.blockId is null)"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) or w.gramPanchayat.gramPanchayatId is null)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ " and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null) "
			/*
			 * +
			 * " AND (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear

	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId "
			+ "and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) or w.lineDepartmentId.lineDepartmentId is null)"
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id) or w.accountHead.id is null) "
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId) or w.agencyTypeId.agencyTypeId is null)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId) or w.block.blockId is null)"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) or w.gramPanchayat.gramPanchayatId is null)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ " and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null) "
			/*
			 * +
			 * " AND (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusWithFY(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("officeId") Long officeId,
			@Param("workRequestStatusId") Long id, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear

	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.executiveEngineerOffice.id = :officeId and" + " w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) or w.lineDepartmentId.lineDepartmentId is null)"
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id) or w.accountHead.id is null) "
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId) or w.agencyTypeId.agencyTypeId is null)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId) or w.block.blockId is null)"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) or w.gramPanchayat.gramPanchayatId is null)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null )")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusWOAD(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null )"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate))  AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)" + ")")
	long countByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatus(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workNatureId") Long workNatureId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear

	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null )")
	long countByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusWOAD(@Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusAndWorkStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId "
			+ "and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWithFY(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("officeId") Long officeId,
			@Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.executiveEngineerOffice.id = :officeId "
			+ "and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOADStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOAD(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId "
			+ "and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOADStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("officeId") Long officeId,
			@Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :officeId and"
			+ " w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) or w.lineDepartmentId.lineDepartmentId is null) "
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id) or w.accountHead.id is null) "
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId) or w.agencyTypeId.agencyTypeId is null) "
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId) or w.block.blockId is null) "
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) or w.gramPanchayat.gramPanchayatId is null) "
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null ) "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null )")
	Page<Work> findInPages(Pageable pageable, @Param("isLegacy") List isLegacy, @Param("statusId") Long statusId,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :officeId "
			+ "and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOADStatus2(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("officeId") Long officeId,
			@Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id > :workRequestStatusId  ")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusAndWorkStatusByEstimationByEstimation(
			Pageable pageable, @Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId,
			@Param("workRequestStatusId") Long id);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id > :workRequestStatusId  ")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusAndWorkStatusByEstimationByTechnical(
			Pageable pageable, @Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId,
			@Param("workRequestStatusId") Long id);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = :officeId")
	List<Work> findByIsLegacyAndExecutiveEngineerOfficeId(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id = :workRequestStatusId and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId")
	List<Work> findByIsLegacyAndStatusAndChiefEngineerOfficeId(@Param("isLegacy") Short isLegacy,
			@Param("workRequestStatusId") Long id, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.subEngineer.id = :userId and w.executiveEngineerOffice.id = :officeId")
	List<Work> findByIsLegacyAndSubEngineerUserIdAndOfficeId(@Param("isLegacy") Short isLegacy,
			@Param("userId") Long userId, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.assistantEngineer.id = :userId and w.executiveEngineerOffice.id = :officeId")
	List<Work> findByIsLegacyAndAssistantEngineerUserIdAndOfficeId(@Param("isLegacy") Short isLegacy,
			@Param("userId") Long userId, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.subDivisionalOfficer.id = :userId and w.executiveEngineerOffice.id = :officeId")
	List<Work> findByIsLegacyAndSubDivisionalOfficerUserIdAndOfficeId(@Param("isLegacy") Short isLegacy,
			@Param("userId") Long userId, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id != :workRequestStatusId and w.isLegacy = :isLegacy")
	List<Work> findByIsLegacyAndWorkRequestStatusNotDraft(@Param("workRequestStatusId") Long id,
			@Param("isLegacy") Short isLegacy);

	// nikhil
	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "

			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId ")
	Page<Work> findByIsLegacyAndWorkRequestStatusNotDraftStatus(Pageable pageable, @Param("isLegacy") List isLegacy,

			@Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId);

	// richa
	List<Work> findByIsLegacy(Short isLegacy);

	// nikhil Working
	/*
	 * @Query(value =
	 * "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
	 * + "agencyType.agency_type_name_e, " +
	 * "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, w.physical_stage_as_on_31_march_2018, ag.tentative_completion_date, "
	 * +
	 * "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
	 * +
	 * "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
	 * + "c.technical_sanction_type, b.technical_sanction_no, " +
	 * "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
	 * + "adst.administrative_sanction_amount, ia.issuing_authority_name, " +
	 * "ag.agreement_number, " +
	 * "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
	 * +
	 * "ifnull(w.total_amount_recieved_till_31march2018,0) as total_amount_recieved_till_31march, ifnull(bi.expenditure_amt,0), ifnull(co.contengency_amt,0), we.status as estStatus "
	 * + "FROM work w " +
	 * "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
	 * +
	 * "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
	 * +
	 * "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
	 * +
	 * "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
	 * +
	 * "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
	 * + "LEFT join contractor contractor on  contractor.ID =w.contractor_id " +
	 * "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id " +
	 * "LEFT join mst_district district on  district.ID =w.district_id " +
	 * "LEFT join mst_block block on  block.ID =w.block_id " +
	 * "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id " +
	 * "LEFT join mst_village village on  village.ID =w.village_id " +
	 * "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
	 * + "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
	 * + "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id " +
	 * "LEFT join ( " +
	 * "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
	 * + "from work_estimate a " + "join ( " + "select max(id) as estId " +
	 * "from work_estimate " + "group by work_id) x " +
	 * "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
	 * + "left join mst_work_estimation_status wes on wes.id = we.status " +
	 * "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
	 * + "left join mst_designation des on des.id=b.ts_issuing_authority " +
	 * "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
	 * + "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id " +
	 * "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id " +
	 * "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( " +
	 * "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " +
	 * "FROM bill " + "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( " +
	 * "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
	 * + "FROM contengency_table " +
	 * "GROUP BY work_id) co ON bi.id=co.bill_id where w.work_request_status_id>=2 and w.is_legacy in (:isLegacy)"
	 * , nativeQuery = true) List<Object[]>
	 * findAllWorksByIsLegacy(@Param("isLegacy") List isLegacy);
	 */

	/*
	 * @Query(value =
	 * "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
	 * + "agencyType.agency_type_name_e, " +
	 * "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, w.physical_stage_as_on_31_march_2018, ag.tentative_completion_date, "
	 * +
	 * "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
	 * +
	 * "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
	 * + "c.technical_sanction_type, b.technical_sanction_no, " +
	 * "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
	 * + "adst.administrative_sanction_amount, ia.issuing_authority_name, " +
	 * "ag.agreement_number, " +
	 * "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
	 * +
	 * "ifnull(w.total_amount_recieved_till_31march2018,0) as total_amount_recieved_till_31march, ifnull(bi.expenditure_amt,0), ifnull(co.contengency_amt,0), we.status as estStatus "
	 * + "FROM work w " +
	 * "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
	 * +
	 * "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
	 * +
	 * "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
	 * +
	 * "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
	 * +
	 * "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
	 * + "LEFT join contractor contractor on  contractor.ID =w.contractor_id " +
	 * "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id " +
	 * "LEFT join mst_district district on  district.ID =w.district_id " +
	 * "LEFT join mst_block block on  block.ID =w.block_id " +
	 * "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id " +
	 * "LEFT join mst_village village on  village.ID =w.village_id " +
	 * "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
	 * + "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
	 * + "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id " +
	 * "LEFT join ( " +
	 * "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
	 * + "from work_estimate a " + "join ( " + "select max(id) as estId " +
	 * "from work_estimate " + "group by work_id) x " +
	 * "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
	 * + "left join mst_work_estimation_status wes on wes.id = we.status " +
	 * "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
	 * + "left join mst_designation des on des.id=b.ts_issuing_authority " +
	 * "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
	 * + "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id " +
	 * "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id " +
	 * "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( " +
	 * "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " +
	 * "FROM bill " + "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( " +
	 * "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
	 * + "FROM contengency_table " +
	 * "GROUP BY work_id) co ON bi.id=co.bill_id where w.work_request_status_id>=2 and w.is_legacy in (:isLegacy)"
	 * , nativeQuery = true) List<Object[]>
	 * findAllWorksByIsLegacy(@Param("isLegacy") List isLegacy);
	 */

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, w.physical_stage_as_on_31_march_2018, ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "ifnull(w.total_amount_recieved_till_31march2018,0) as total_amount_recieved_till_31march, ifnull(bi.expenditure_amt,0), ifnull(co.contengency_amt,0), we.status as estStatus ,reqsts.status_name_e as workRequestStatus "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "
			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where w.work_request_status_id>=2 and w.is_legacy = 0  UNION ALL "
			+ "SELECT w.work_name, " + "workType.WORK_TYPE_NAME_E, " + "workSubType.WORK_SUB_TYPE_NAME_E, "
			+ "lineDepartment.line_department_name_e, " + "accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, " + "contractor.name, " + "w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, " + "w.physical_stage_as_on_31_march_2018, "
			+ "ag.tentative_completion_date, " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name, " + "block.block_name, " + "gp.gp_name, " + "village.village_name, "
			+ "w.location_address, " + "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "exeOffice.office_name , " + "asstEngName.name as astName, " + "subEngName.name as subName, "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,null as estapprBy,'Estimation Approved' as status_name_e,null as modified_date, "
			+ "ifnull(w.total_amount_recieved_till_31march2018,0) as total_amount_recieved_till_31march, ifnull(bi.expenditure_amt,0), ifnull(co.contengency_amt,0), 5 as estStatus ,reqsts.status_name_e as workRequestStatus  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "
			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table " + "GROUP BY work_id) co ON bi.id=co.bill_id "
			+ "where w.work_request_status_id>=2 and w.is_legacy = 1 and (w.status is null or w.status!='Deleted')", nativeQuery = true)
	List<Object[]> findAllWorksByIsLegacy();

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, w.physical_stage_as_on_31_march_2018, ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "
			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where w.is_legacy = 0 and w.chief_engineer_office_id = (:ceOfficeId) and w.work_request_status_id>1  UNION ALL "
			+ "SELECT w.work_name, " + "workType.WORK_TYPE_NAME_E, " + "workSubType.WORK_SUB_TYPE_NAME_E, "
			+ "lineDepartment.line_department_name_e, " + "accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, " + "contractor.name, " + "w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, " + "w.physical_stage_as_on_31_march_2018, "
			+ "ag.tentative_completion_date, " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name, " + "block.block_name, " + "gp.gp_name, " + "village.village_name, "
			+ "w.location_address, " + "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "exeOffice.office_name , " + "asstEngName.name as astName, " + "subEngName.name as subName, "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,null as estapprBy,'Estimation Approved' as status_name_e,null as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "
			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table " + "GROUP BY work_id) co ON bi.id=co.bill_id "
			+ "where w.is_legacy = 1 and w.chief_engineer_office_id = (:ceOfficeId) and w.work_request_status_id>1 and (w.status is null or w.status!='Deleted')", nativeQuery = true)
	List<Object[]> findByIsLegacyAndOfficeIdForAll(@Param("ceOfficeId") Long ceOfficeId);

	@Query(value = "DROP TEMPORARY TABLE IF EXISTS work_tech_sanction_temp; "
			+ "CREATE TEMPORARY TABLE work_tech_sanction_temp AS "
			+ "SELECT work_id,id as tecID, technical_sanction_no, technical_sanction_date, technical_sanction_amount, technical_sanction_type_id, ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x " + "on a.id = x.tecID; "
			+ "ALTER TABLE work_tech_sanction_temp ADD INDEX (work_id); "
			+ "DROP TEMPORARY TABLE IF EXISTS work_adm_sanction_temp; "
			+ "CREATE TEMPORARY TABLE work_adm_sanction_temp AS "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x " + "on a.id = x.adminId; "
			+ "ALTER TABLE work_adm_sanction_temp ADD INDEX (work_id); " + "select w.work_name,"
			+ "workType.WORK_TYPE_NAME_E  , " + "workSubType.WORK_SUB_TYPE_NAME_E , "
			+ "lineDepartment.line_department_name_e  , " + "accountHead.account_head_name_e , "
			+ "agencyType.agency_type_name_e, " + "contractor.name , " + "w.total_expenditure_till_31_march_2018 , "
			+ "workStatus.work_status_name_e, " + "w.physical_stage_as_on_31_march_2018 , "
			+ "ag.tentative_completion_date , " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name , " + "block.block_name  , " + "gp.gp_name  , " + "village.village_name , "
			+ "w.location_address, " + "w.work_location_latitude , " + "w.work_location_longitude , "
			+ "exeOffice.office_name, " + "asstEngName.name as astName , " + "subEngName.name as subName , "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name , " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,'' as estapprBy,'Estimation Approved' as status_name_e,'' as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join work_tech_sanction_temp b on w.ID = b.work_id "
			+ "LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join work_adm_sanction_temp adst on  w.ID = adst.work_id "
			+ "LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id"
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "
			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join (  "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table " + "GROUP BY work_id) co ON bi.id=co.bill_id "
			+ "where w.is_legacy in (:isLegacy) and w.work_request_status_id>=2 and (w.status is null or w.status!='Deleted'); "
			+ "DROP TEMPORARY TABLE IF EXISTS work_tech_sanction_temp; "
			+ "DROP TEMPORARY TABLE IF EXISTS work_adm_sanction_temp", nativeQuery = true)
	List<Object[]> findAdminLegacyWorksByIsLegacy(@Param("isLegacy") List isLegacy);

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, physicalStage.physical_stage_name_e , ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,tr.tendered_rate_sign ,old_td.amount_of_contract  as old_pac_amount,old_td.tender_cost as old_tender_cost ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "

			// for old tender and pac amount by Nikhil
			+ "LEFT join (select a.WORK_ID,b.tender_cost,b.amount_of_contract from "
			+ "(SELECT Min(id) as id,WORK_ID FROM work_tender " + " group by work_id " + "HAVING count(1)>1) as a "
			+ "join work_tender as b on a.id=b.id) old_td on w.id=old_td.work_id "

			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = 0 and w.work_request_status_id != 1 and w.executive_engineer_office_id = COALESCE(:eeOfficeId, w.executive_engineer_office_id) and w.chief_engineer_office_id = COALESCE(:ceOfficeId, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:supdtOfficeId, w.superintending_engineer_office_id) "
			+ "UNION ALL " + "SELECT w.work_name, " + "workType.WORK_TYPE_NAME_E, "
			+ "workSubType.WORK_SUB_TYPE_NAME_E, " + "lineDepartment.line_department_name_e, "
			+ "accountHead.account_head_name_e, " + "agencyType.agency_type_name_e, " + "contractor.name, "
			+ "w.total_expenditure_till_31_march_2018, " + "workStatus.work_status_name_e, "
			+ "physicalStage.physical_stage_name_e , " + "ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018, " + "district.district_name, " + "block.block_name, "
			+ "gp.gp_name, " + "village.village_name, " + "w.location_address, " + "w.work_location_latitude, "
			+ "w.work_location_longitude, " + "exeOffice.office_name , " + "asstEngName.name as astName, "
			+ "subEngName.name as subName, " + "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "w.agreement_number  , "
			+ "w.agreement_date,w.tendered_rate_per,w.pac_amount,w.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,null as estapprBy,'Estimation Approved' as status_name_e,null as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,w.tendered_rate_sign ,wl.pac_amount as old_pac_amount,wl.tender_cost as old_tender_cost ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "

			// old tender amt and old pac amt
			+ "left join ( " + "select a.id,b.pac_amount,b.tender_cost " + "from "
			+ "(SELECT min(log_id) as log_id,id FROM work_logging " + "group by id) as a  "
			+ "join work_logging as b on a.log_id=b.log_id " + ") wl on w.id=wl.id "

			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = 1 and w.work_request_status_id != 1 and w.executive_engineer_office_id = COALESCE(:eeOfficeId, w.executive_engineer_office_id) and w.chief_engineer_office_id = COALESCE(:ceOfficeId, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:supdtOfficeId, w.superintending_engineer_office_id)", nativeQuery = true)
	List<Object[]> findAllWorksByIsLegacyAndOfficeEE(@Param("eeOfficeId") Long eeOfficeId,
			@Param("ceOfficeId") Long ceOfficeId, @Param("supdtOfficeId") Long supdtOfficeId);

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, physicalStage.physical_stage_name_e , ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,tr.tendered_rate_sign ,old_td.amount_of_contract  as old_pac_amount,old_td.tender_cost as old_tender_cost ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "

			// for old tender and pac amount by Nikhil
			+ "LEFT join (select a.WORK_ID,b.tender_cost,b.amount_of_contract from "
			+ "(SELECT Min(id) as id,WORK_ID FROM work_tender " + " group by work_id " + "HAVING count(1)>1) as a "
			+ "join work_tender as b on a.id=b.id) old_td on w.id=old_td.work_id "

			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.work_request_status_id>=2  and w.is_legacy = (:isLegacy) and w.executive_engineer_office_id = COALESCE(:eeOfficeId, w.executive_engineer_office_id) and w.chief_engineer_office_id = COALESCE(:ceOfficeId, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:supdtOfficeId, w.superintending_engineer_office_id)", nativeQuery = true)
	List<Object[]> findByIsLegacyAndOfficeId(@Param("isLegacy") Short isLegacy, @Param("eeOfficeId") Long eeOfficeId,
			@Param("ceOfficeId") Long ceOfficeId, @Param("supdtOfficeId") Long supdtOfficeId);

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, physicalStage.physical_stage_name_e , ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,tr.tendered_rate_sign,old_td.amount_of_contract  as old_pac_amount,old_td.tender_cost as old_tender_cost ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "
			// for old tender and pac amount by Nikhil
			+ "LEFT join (select a.WORK_ID,b.tender_cost,b.amount_of_contract from "
			+ "(SELECT Min(id) as id,WORK_ID FROM work_tender " + " group by work_id " + "HAVING count(1)>1) as a "
			+ "join work_tender as b on a.id=b.id) old_td on w.id=old_td.work_id "

			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = (:isLegacy) and w.chief_engineer_office_id = (:ceOfficeId) and w.work_request_status_id>1", nativeQuery = true)
	List<Object[]> findByIsLegacyAndOfficeIdForNewWork(@Param("isLegacy") Short isLegacy,
			@Param("ceOfficeId") Long ceOfficeId);

	@Query(value = "select w.work_name, " + "workType.WORK_TYPE_NAME_E, " + "workSubType.WORK_SUB_TYPE_NAME_E, "
			+ "lineDepartment.line_department_name_e, " + "accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, " + "contractor.name, " + "w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, " + "w.physical_stage_as_on_31_march_2018, "
			+ "ag.tentative_completion_date, " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name, " + "block.block_name, " + "gp.gp_name, " + "village.village_name, "
			+ "w.location_address, " + "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "exeOffice.office_name , " + "asstEngName.name as astName, " + "subEngName.name as subName, "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,'' as estapprBy,'Estimation Approved' as status_name_e,'' as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "
			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.work_request_status_id>=2  and w.is_legacy = (:isLegacy) and w.executive_engineer_office_id = COALESCE(:eeOfficeId, w.executive_engineer_office_id) and w.chief_engineer_office_id = COALESCE(:ceOfficeId, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:supdtOfficeId, w.superintending_engineer_office_id)", nativeQuery = true)
	List<Object[]> findByIsLegacyAndOfficeIdForLegacy(@Param("isLegacy") Short isLegacy,
			@Param("eeOfficeId") Long eeOfficeId, @Param("ceOfficeId") Long ceOfficeId,
			@Param("supdtOfficeId") Long supdtOfficeId);

	@Query(value = "select w.work_name, " + "workType.WORK_TYPE_NAME_E, " + "workSubType.WORK_SUB_TYPE_NAME_E, "
			+ "lineDepartment.line_department_name_e, " + "accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, " + "contractor.name, " + "w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, " + "w.physical_stage_as_on_31_march_2018, "
			+ "ag.tentative_completion_date, " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name, " + "block.block_name, " + "gp.gp_name, " + "village.village_name, "
			+ "w.location_address, " + "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "exeOffice.office_name , " + "asstEngName.name as astName, " + "subEngName.name as subName, "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,'' as estapprBy,'Estimation Approved' as status_name_e,'' as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "
			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy in (:isLegacy)  and w.chief_engineer_office_id = (:ceOfficeId) and w.work_request_status_id>1", nativeQuery = true)
	List<Object[]> findByIsLegacyAndOfficeIdForLegacyWork(@Param("isLegacy") List isLegacy,
			@Param("ceOfficeId") Long ceOfficeId);

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, physicalStage.physical_stage_name_e , ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt,co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,tr.tendered_rate_sign "
			+ ",old_td.amount_of_contract  as old_pac_amount_new_work,old_td.tender_cost as old_tender_cost_new_work ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "

			// for old tender and pac amount by Nikhil
			+ "LEFT join (select a.WORK_ID,b.tender_cost,b.amount_of_contract from "
			+ "(SELECT Min(id) as id,WORK_ID FROM work_tender " + "group by work_id " + "HAVING count(1)>1) as a "
			+ "join work_tender as b on a.id=b.id) old_td on w.id=old_td.work_id "

			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = (:isLegacy) and w.executive_engineer_office_id = COALESCE(:eeOfficeId, w.executive_engineer_office_id) and w.chief_engineer_office_id = COALESCE(:ceOfficeId, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:supdtOfficeId, w.superintending_engineer_office_id)", nativeQuery = true)
	List<Object[]> findByIsLegacyNewWorkAndOfficeIdAllWorks(@Param("isLegacy") Short isLegacy,
			@Param("eeOfficeId") Long eeOfficeId, @Param("ceOfficeId") Long ceOfficeId,
			@Param("supdtOfficeId") Long supdtOfficeId);

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, "
			+ "accountHead.account_head_name_e, agencyType.agency_type_name_e, contractor.name, w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, physicalStage.physical_stage_name_e , ag.tentative_completion_date, w.total_amount_recieved_till_31march2018 ,"
			+ "district.district_name ,block.block_name ,gp.gp_name, village.village_name, w.location_address, w.work_location_latitude, "
			+ "w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, c.technical_sanction_type,"
			+ " b.technical_sanction_no, b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type,"
			+ " adst.administrative_sanction_no, adst.administrative_sanction_date, adst.administrative_sanction_amount, ia.issuing_authority_name,"
			+ " ag.agreement_number, ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,"
			+ "we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date,"
			+ " w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, "
			+ "we.status as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,"
			+ "tr.tendered_rate_sign ,old_td.amount_of_contract  as old_pac_amount,old_td.tender_cost as old_tender_cost ,w.billing_flag,w.max_billing_amount FROM work w "
			+ "LEFT join mst_work_type workType on workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_physical_stage physicalStage on physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on district.ID =w.district_id LEFT join mst_block block on block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on subEngName.ID =w.sub_engineer_id "
			+ "LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id "
			+ "LEFT join ( SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date from work_estimate a join ( select max(id) as estId from work_estimate group by work_id) x on a.id = x.estId) we on w.ID = we.work_id "
			+ "Left join users us on us.email_id = we.estimation_approved_by left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id "
			+ "LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "
			+ "LEFT join (select a.WORK_ID,b.tender_cost,b.amount_of_contract from (SELECT Min(id) as id,WORK_ID "
			+ "FROM work_tender group by work_id HAVING count(1)>1) as a join work_tender as b on a.id=b.id) old_td on w.id=old_td.work_id"
			+ " LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt"
			+ " FROM bill GROUP BY work_id) bi ON w.Id=bi.work_id left join ( SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = (:isLegacy) and w.work_request_status_id>=2", nativeQuery = true)
	List<Object[]> findAdminNewWorksByIsLegacy(@Param("isLegacy") Short isLegacy);

	/*
	 * @Query(value =
	 * "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
	 * + "agencyType.agency_type_name_e, " +
	 * "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, physicalStage.physical_stage_name_e , ag.tentative_completion_date, "
	 * +
	 * "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
	 * +
	 * "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
	 * + "c.technical_sanction_type, b.technical_sanction_no, " +
	 * "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
	 * + "adst.administrative_sanction_amount, ia.issuing_authority_name, " +
	 * "ag.agreement_number, " +
	 * "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
	 * +
	 * "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,tr.tendered_rate_sign "
	 * + "FROM work w " +
	 * "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
	 * +
	 * "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
	 * +
	 * "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
	 * +
	 * "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
	 * +
	 * "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
	 * + "LEFT join contractor contractor on  contractor.ID =w.contractor_id " +
	 * "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id " +
	 * "LEFT join mst_district district on  district.ID =w.district_id " +
	 * "LEFT join mst_block block on  block.ID =w.block_id " +
	 * "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id " +
	 * "LEFT join mst_village village on  village.ID =w.village_id " +
	 * "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
	 * + "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
	 * +
	 * "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
	 * + "LEFT join ( " +
	 * "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
	 * + "from work_estimate a " + "join ( " + "select max(id) as estId " +
	 * "from work_estimate " + "group by work_id) x " +
	 * "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
	 * + "left join mst_work_estimation_status wes on wes.id = we.status " +
	 * "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
	 * + "left join mst_designation des on des.id=b.ts_issuing_authority " +
	 * "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
	 * + "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id " +
	 * "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id " +
	 * "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( " +
	 * "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " +
	 * "FROM bill " + "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( " +
	 * "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
	 * + "FROM contengency_table " +
	 * "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = (:isLegacy) and w.work_request_status_id>=2"
	 * , nativeQuery = true) List<Object[]>
	 * findAdminNewWorksByIsLegacy(@Param("isLegacy") Short isLegacy);
	 */

	@Query(value = "select w.work_name, " + "workType.WORK_TYPE_NAME_E, " + "workSubType.WORK_SUB_TYPE_NAME_E, "
			+ "lineDepartment.line_department_name_e, " + "accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, " + "contractor.name, " + "w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, " + "physicalStage.physical_stage_name_e , "
			+ "ag.tentative_completion_date, " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name, " + "block.block_name, " + "gp.gp_name, " + "village.village_name, "
			+ "w.location_address, " + "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "exeOffice.office_name , " + "asstEngName.name as astName, " + "subEngName.name as subName, "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "w.agreement_number  , "
			+ "w.agreement_date,w.tendered_rate_per,w.pac_amount,w.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,'' as estapprBy,'Estimation Approved' as status_name_e,'' as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,w.tendered_rate_sign ,wl.pac_amount as old_pac_amount,wl.tender_cost as old_tender_cost ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "

			// old tender amt and old pac amt
			+ "left join ( " + "select a.id,b.pac_amount,b.tender_cost " + "from "
			+ "(SELECT min(log_id) as log_id,id FROM work_logging " + "group by id) as a  "
			+ "join work_logging as b on a.log_id=b.log_id " + ") wl on w.id=wl.id "

			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = (:isLegacy) and w.executive_engineer_office_id = COALESCE(:eeOfficeId, w.executive_engineer_office_id) "
			+ "and w.chief_engineer_office_id = COALESCE(:ceOfficeId, w.chief_engineer_office_id) "
			+ "and w.superintending_engineer_office_id = COALESCE(:supdtOfficeId, w.superintending_engineer_office_id)", nativeQuery = true)
	List<Object[]> findByIsLegacyAndOfficeIdAllWorks(@Param("isLegacy") Short isLegacy,
			@Param("eeOfficeId") Long eeOfficeId, @Param("ceOfficeId") Long ceOfficeId,
			@Param("supdtOfficeId") Long supdtOfficeId);

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, physicalStage.physical_stage_name_e , ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,tr.tendered_rate_sign ,old_td.amount_of_contract  as old_pac_amount,old_td.tender_cost as old_tender_cost ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "

			// for old tender and pac amount by Nikhil
			+ "LEFT join (select a.WORK_ID,b.tender_cost,b.amount_of_contract from "
			+ "(SELECT Min(id) as id,WORK_ID FROM work_tender " + " group by work_id " + "HAVING count(1)>1) as a "
			+ "join work_tender as b on a.id=b.id) old_td on w.id=old_td.work_id "

			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = (:isLegacy)  and w.sub_engineer_id = COALESCE(:subEngId, w.sub_engineer_id) and w.assistant_engineer_id = COALESCE(:astEngId, w.assistant_engineer_id)", nativeQuery = true)
	List<Object[]> findByIsLegacyAndSEAndAEUserIdAndOfficeId(@Param("isLegacy") Short isLegacy,
			@Param("subEngId") Long subEngId, @Param("astEngId") Long astEngId);

	@Query(value = "select w.work_name, " + "workType.WORK_TYPE_NAME_E, " + "workSubType.WORK_SUB_TYPE_NAME_E, "
			+ "lineDepartment.line_department_name_e, " + "accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, " + "contractor.name, " + "w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, " + "physicalStage.physical_stage_name_e , "
			+ "ag.tentative_completion_date, " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name, " + "block.block_name, " + "gp.gp_name, " + "village.village_name, "
			+ "w.location_address, " + "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "exeOffice.office_name , " + "asstEngName.name as astName, " + "subEngName.name as subName, "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "w.agreement_number  , "
			+ "w.agreement_date,w.tendered_rate_per,w.pac_amount,w.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,'' as estapprBy,'Estimation Approved' as status_name_e,'' as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,w.tendered_rate_sign ,wl.pac_amount as old_pac_amount,wl.tender_cost as old_tender_cost ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "

			// old tender amt and old pac amt
			+ "left join ( " + "select a.id,b.pac_amount,b.tender_cost " + "from "
			+ "(SELECT min(log_id) as log_id,id FROM work_logging " + "group by id) as a  "
			+ "join work_logging as b on a.log_id=b.log_id " + ") wl on w.id=wl.id "

			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = (:isLegacy)  and w.sub_engineer_id = COALESCE(:subEngId, w.sub_engineer_id) and w.assistant_engineer_id = COALESCE(:astEngId, w.assistant_engineer_id)", nativeQuery = true)
	List<Object[]> findByIsLegacyAndSEAndAEUserIdAndOfficeIdForLegacy(@Param("isLegacy") Short isLegacy,
			@Param("subEngId") Long subEngId, @Param("astEngId") Long astEngId);

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, physicalStage.physical_stage_name_e , ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,tr.tendered_rate_sign"
			+ ",old_td.amount_of_contract  as old_pac_amount_new_work,old_td.tender_cost as old_tender_cost_new_work ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "
			// old tender cost and old pac amt
			+ "LEFT join (select a.WORK_ID,b.tender_cost,b.amount_of_contract from "
			+ "(SELECT Min(id) as id,WORK_ID FROM work_tender " + " group by work_id " + "HAVING count(1)>1) as a "
			+ "join work_tender as b on a.id=b.id) old_td on w.id=old_td.work_id "

			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where w.is_legacy = 0 and w.sub_engineer_id = COALESCE(:subEngId, w.sub_engineer_id) and w.assistant_engineer_id = COALESCE(:astEngId, w.assistant_engineer_id) "

			+ "UNION ALL "

			+ "SELECT w.work_name, " + "workType.WORK_TYPE_NAME_E, " + "workSubType.WORK_SUB_TYPE_NAME_E, "
			+ "lineDepartment.line_department_name_e, " + "accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, " + "contractor.name, " + "w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, " + "physicalStage.physical_stage_name_e , "
			+ "ag.tentative_completion_date, " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name, " + "block.block_name, " + "gp.gp_name, " + "village.village_name, "
			+ "w.location_address, " + "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "exeOffice.office_name , " + "asstEngName.name as astName, " + "subEngName.name as subName, "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "w.agreement_number  , "
			+ "w.agreement_date,w.tendered_rate_per,w.pac_amount,w.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,null as estapprBy,'Estimation Approved' as status_name_e,null as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,w.tendered_rate_sign,wl.pac_amount as old_pac_amount_legacy_work,wl.tender_cost as old_tender_cost_legacy_work ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "

			// old tender cost and old pac amt
			+ "left join ( " + "select a.id,b.pac_amount,b.tender_cost " + "from "
			+ "(SELECT min(log_id) as log_id,id FROM work_logging " + "group by id) as a  "
			+ "join work_logging as b on a.log_id=b.log_id " + ") wl on w.id=wl.id "

			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = 1  and w.sub_engineer_id = COALESCE(:subEngId, w.sub_engineer_id) and w.assistant_engineer_id = COALESCE(:astEngId, w.assistant_engineer_id)", nativeQuery = true)
	List<Object[]> findByIsLegacyAndSEAndAEUserIdAndOfficeIdForAllAEAndSUB(@Param("subEngId") Long subEngId,
			@Param("astEngId") Long astEngId);

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, physicalStage.physical_stage_name_e , ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus ,w.total_expenditure_on_contingency_till_31_march_2018,tr.tendered_rate_sign,old_td.amount_of_contract  as old_pac_amount,old_td.tender_cost as old_tender_cost ,w.billing_flag,w.max_billing_amount  "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id LEFT join mst_physical_stage physicalStage on  physicalStage.physical_stage_id =w.physical_stage_as_on_31_march_2018 "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "

			// for old tender and pac amount by Nikhil
			+ "LEFT join (select a.WORK_ID,b.tender_cost,b.amount_of_contract from "
			+ "(SELECT Min(id) as id,WORK_ID FROM work_tender " + " group by work_id " + "HAVING count(1)>1) as a "
			+ "join work_tender as b on a.id=b.id) old_td on w.id=old_td.work_id "

			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = (:isLegacy) and w.line_department_id = (:lineDeptId)", nativeQuery = true)
	List<Object[]> findWorkListByIsLegacyAndLineDepartmentIdForGP(@Param("isLegacy") Short isLegacy,
			@Param("lineDeptId") Long lineDeptId);

	@Query(value = "select w.work_name, " + "workType.WORK_TYPE_NAME_E, " + "workSubType.WORK_SUB_TYPE_NAME_E, "
			+ "lineDepartment.line_department_name_e, " + "accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, " + "contractor.name, " + "w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, " + "w.physical_stage_as_on_31_march_2018, "
			+ "ag.tentative_completion_date, " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name, " + "block.block_name, " + "gp.gp_name, " + "village.village_name, "
			+ "w.location_address, " + "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "exeOffice.office_name , " + "asstEngName.name as astName, " + "subEngName.name as subName, "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,'' as estapprBy,'Estimation Approved' as status_name_e,'' as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "
			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where (w.status is null or w.status!='Deleted') and w.is_legacy = (:isLegacy) and w.line_department_id = (:lineDeptId)", nativeQuery = true)
	List<Object[]> findWorkListByIsLegacyAndLineDepartmentIdForLegacy(@Param("isLegacy") Short isLegacy,
			@Param("lineDeptId") Long lineDeptId);

	@Query(value = "select w.work_name, workType.WORK_TYPE_NAME_E, workSubType.WORK_SUB_TYPE_NAME_E, lineDepartment.line_department_name_e, accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, "
			+ "contractor.name, w.total_expenditure_till_31_march_2018, workStatus.work_status_name_e, w.physical_stage_as_on_31_march_2018, ag.tentative_completion_date, "
			+ "w.total_amount_recieved_till_31march2018 ,district.district_name ,block.block_name ,gp.gp_name,"
			+ "village.village_name, w.location_address, w.work_location_latitude, w.work_location_longitude, exeOffice.office_name, asstEngName.name as astName, subEngName.name as subName, "
			+ "c.technical_sanction_type, b.technical_sanction_no, "
			+ "b.technical_sanction_date, b.technical_sanction_amount, des.designation, dd.administration_sanction_type, adst.administrative_sanction_no, adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount, ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,we.estimation_type,we.total_amount,us.name as estapprBy,wes.status_name_e,we.modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, we.status as estStatus ,reqsts.status_name_e as workRequestStatus "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as estfId,estimation_approved_by ,status,estimation_type,total_amount,modified_date "
			+ "from work_estimate a " + "join ( " + "select max(id) as estId " + "from work_estimate "
			+ "group by work_id) x "
			+ "on a.id = x.estId) we on  w.ID = we.work_id Left join users us on us.email_id = we.estimation_approved_by "
			+ "left join mst_work_estimation_status wes on wes.id = we.status "
			+ "LEFT join technical_sanction b ON we.estfId=b.work_estimate_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority "
			+ "LEFT join administrative_sanction adst ON b.id=adst.technical_sanction_id left join mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_tender tr ON adst.Id=tr.administrative_sanction_id "
			+ "LEFT join work_agreement ag ON tr.Id=ag.work_tender_id left join ( "
			+ "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table "
			+ "GROUP BY work_id) co ON bi.id=co.bill_id where  w.is_legacy = 0 and w.line_department_id = 17  UNION ALL "
			+ "SELECT w.work_name, " + "workType.WORK_TYPE_NAME_E, " + "workSubType.WORK_SUB_TYPE_NAME_E, "
			+ "lineDepartment.line_department_name_e, " + "accountHead.account_head_name_e, "
			+ "agencyType.agency_type_name_e, " + "contractor.name, " + "w.total_expenditure_till_31_march_2018, "
			+ "workStatus.work_status_name_e, " + "w.physical_stage_as_on_31_march_2018, "
			+ "ag.tentative_completion_date, " + "w.total_amount_recieved_till_31march2018, "
			+ "district.district_name, " + "block.block_name, " + "gp.gp_name, " + "village.village_name, "
			+ "w.location_address, " + "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "exeOffice.office_name , " + "asstEngName.name as astName, " + "subEngName.name as subName, "
			+ "c.technical_sanction_type,b.technical_sanction_no, "
			+ "b.technical_sanction_date,b.technical_sanction_amount, des.designation,dd.administration_sanction_type, adst.administrative_sanction_no,adst.administrative_sanction_date, "
			+ "adst.administrative_sanction_amount,ia.issuing_authority_name, " + "ag.agreement_number, "
			+ "ag.agreement_date,tr.tendered_rate,tr.amount_of_contract,tr.tender_cost,w.remarks,w.work_requisition_no,c.technical_sanction_type as estimation_type ,w.estimated_cost as total_amount,null as estapprBy,'Estimation Approved' as status_name_e,null as modified_date, "
			+ "w.total_amount_recieved_till_31march2018 as total_amount_recieved_till_31march, bi.expenditure_amt, co.contengency_amt, 5 as estStatus ,reqsts.status_name_e as workRequestStatus "
			+ "FROM work w " + "LEFT join mst_work_type workType on  workType.work_type_id =w.work_type_id "
			+ "LEFT join mst_work_sub_type workSubType on  workSubType.work_sub_type_id =w.work_sub_type_id "
			+ "LEFT join mst_line_department lineDepartment on  lineDepartment.line_department_id =w.line_department_id "
			+ "LEFT join mst_account_head accountHead on  accountHead.ID =w.account_head "
			+ "LEFT join mst_agency_type agencyType on  agencyType.agency_type_id =w.agency_type_id "
			+ "LEFT join contractor contractor on  contractor.ID =w.contractor_id "
			+ "LEFT join mst_work_status workStatus on  workStatus.ID =w.work_status_id "
			+ "LEFT join mst_district district on  district.ID =w.district_id "
			+ "LEFT join mst_block block on  block.ID =w.block_id "
			+ "LEFT join mst_gram_panchayat gp on  gp.ID =w.gram_panchayat_id "
			+ "LEFT join mst_village village on  village.ID =w.village_id "
			+ "LEFT join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id "
			+ "LEFT join users asstEngName on  asstEngName.ID =w.assistant_engineer_id "
			+ "LEFT join users subEngName on  subEngName.ID =w.sub_engineer_id LEFT JOIN mst_request_status reqsts on reqsts.id = w.work_request_status_id  "
			+ "LEFT join ( "
			+ "SELECT work_id,id as tecID,technical_sanction_no ,technical_sanction_date,technical_sanction_amount,technical_sanction_type_id,ts_issuing_authority "
			+ "from technical_sanction a " + "join ( " + "select max(id) as tecID " + "from technical_sanction "
			+ "group by work_id) x "
			+ "on a.id = x.tecID) b on  w.ID = b.work_id LEFT JOIN mst_technical_sanction_type c ON c.technical_sanction_type_id=b.technical_sanction_type_id "
			+ "left join mst_designation des on des.id=b.ts_issuing_authority " + "LEFT join ( "
			+ "SELECT work_id,id as adminId,administrative_sanction_no ,administrative_sanction_date,issuing_authority_id,administrative_sanction_amount,administration_sanction_type_id "
			+ "from administrative_sanction a " + "join ( " + "select max(id) as adminId "
			+ "from administrative_sanction " + "group by work_id) x "
			+ "on a.id = x.adminId) adst on  w.ID = adst.work_id LEFT JOIN mst_administration_sanction_type dd on dd.administration_sanction_type_id=adst.administration_sanction_type_id "
			+ "left join mst_issuing_authority ia on ia.id=adst.issuing_authority_id "
			+ "LEFT join work_agreement ag ON w.Id=ag.work_id " + "LEFT join work_tender tr ON w.Id=tr.work_id "
			+ "left join ( " + "SELECT id, work_id, sum(bill_amount_without_deduction) expenditure_amt " + "FROM bill "
			+ "GROUP BY work_id) bi ON w.Id=bi.work_id " + "left join ( "
			+ "SELECT id,bill_id, work_id, IFNULL(sum(Expenditure_amount) , 0) contengency_amt "
			+ "FROM contengency_table " + "GROUP BY work_id) co ON bi.id=co.bill_id "
			+ "where (w.status is null or w.status!='Deleted') and w.is_legacy = 1 and w.line_department_id = 17", nativeQuery = true)
	List<Object[]> findWorkListByIsLegacyAndLineDepartmentIdForAllWorks();

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) or w.workTypeId.workTypeId is null) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) or w.lineDepartmentId.lineDepartmentId is null)"
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id) or w.accountHead.id is null)"
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId) or w.agencyTypeId.agencyTypeId is null)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId) or w.block.blockId is null)"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) or w.gramPanchayat.gramPanchayatId is null)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "
			+ "and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) or w.workTypeId.workTypeId is null) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) or w.lineDepartmentId.lineDepartmentId is null)"
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id) or w.accountHead.id is null)"
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId) or w.agencyTypeId.agencyTypeId is null)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId) or w.block.blockId is null)"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) or w.gramPanchayat.gramPanchayatId is null)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatus2(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) or w.workTypeId.workTypeId is null) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) or w.lineDepartmentId.lineDepartmentId is null)"
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id) or w.accountHead.id is null)"
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId) or w.agencyTypeId.agencyTypeId is null)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId) or w.block.blockId is null)"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) or w.gramPanchayat.gramPanchayatId is null)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusWOAD(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "
			+ "and w.superintendingEngineerOffice.id = :officeId "
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) or w.workTypeId.workTypeId is null) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) or w.lineDepartmentId.lineDepartmentId is null)"
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id) or w.accountHead.id is null)"
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId) or w.agencyTypeId.agencyTypeId is null)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId) or w.block.blockId is null)"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) or w.gramPanchayat.gramPanchayatId is null)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusWOADStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null )"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatus(@Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null )")
	long countByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusWOAD(@Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusAndWorkStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "
			+ "and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusAndWorkStatus2(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOAD(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "
			+ "and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOADStatus(
			Pageable pageable, @Param("isLegacy") List isLegacy, @Param("statusId") Long statusId,
			@Param("exeOfficeId") Long exeOfficeId, @Param("officeId") Long officeId,
			@Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	/*
	 * @Query("select count(*) from Work w where w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
	 * +
	 * "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
	 * +
	 * "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
	 * +
	 * "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
	 * + "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)" +
	 * "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
	 * + "and w.block.blockId=COALESCE( :blockId, w.block.blockId)" +
	 * "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
	 * +
	 * "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
	 * + "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id))" +
	 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate)))"
	 * ) long
	 * countByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusAndWorkStatus(
	 * 
	 * @Param("isLegacy") Short isLegacy,
	 * 
	 * @Param("officeId") Long officeId,
	 * 
	 * @Param("workRequestStatusId") Long id,
	 * 
	 * @Param("workStatusId") Long id2,
	 * 
	 * @Param("workTypeId") Long workTypeId,
	 * 
	 * @Param("workSubTypeId") Long workSubTypeId,
	 * 
	 * @Param("lineDepartmentId") Long lineDepartmentId,
	 * 
	 * @Param("accountHeadId") Long accountHeadId,
	 * 
	 * @Param("executionAgencyId") Long executionAgencyId,
	 * 
	 * @Param("blockId") Long blockId,
	 * 
	 * @Param("gramPanchayatId") Long gramPanchayatId,
	 * 
	 * @Param("villageId") Long villageId,
	 * 
	 * @Param("contractorId") Long contractorId,
	 * 
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate);
	 */

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.chiefEngineerOffice.id = :officeId " + "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "
			+ "and w.chiefEngineerOffice.id = :officeId " + "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatus2(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusWOAD(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "
			+ "and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusWOADStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatus(@Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	long countByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusWOAD(@Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndWorkStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "
			+ "and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndWorkStatus2(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "

			+ "and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOOffice(Pageable pageable,
			@Param("isLegacy") List isLegacy,

			@Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "
			+ "and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOOfficeStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId,
			@Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.chiefEngineerOffice.id = :officeId "
			+ "and w.workRequestStatusId.id != :workRequestStatusId " + "and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOAD(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "
			+ "and w.chiefEngineerOffice.id = :officeId " + "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndWorkStatusWOADStatus(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndWorkStatus(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id, @Param("workStatusId") Long id2,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id != :workRequestStatusId and w.isLegacy in :isLegacy")
	Page<Work> findByIsLegacyAndWorkRequestStatusNotDraft(Pageable pageable, @Param("workRequestStatusId") Long id,
			@Param("isLegacy") List isLegacy);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id != :workRequestStatusId and w.isLegacy in :isLegacy "
			+ "and w.maxBillingAmount is not null and w.finalExpAmountDate is not null")
	Page<Work> findByIsLegacyAndWorkRequestStatusNotDraftNotFinalBillAmountNull(Pageable pageable,
			@Param("workRequestStatusId") Long id, @Param("isLegacy") List isLegacy);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id != :workRequestStatusId and w.isLegacy in :isLegacy "
			+ "and w.maxBillingAmount is not null and w.finalExpAmountDate is not null and w.chiefEngineerOffice.id = :ceOfficeId")
	Page<Work> findByIsLegacyAndWorkRequestStatusNotDraftNotFinalBillAmountNullCEOffice(Pageable pageable,
			@Param("workRequestStatusId") Long id, @Param("isLegacy") List isLegacy,
			@Param("ceOfficeId") Long ceOfficeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id != :workRequestStatusId and w.isLegacy in :isLegacy")
	long countByIsLegacyAndWorkRequestStatusNotDraft(@Param("workRequestStatusId") Long id,
			@Param("isLegacy") List isLegacy);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id != :workRequestStatusId and w.isLegacy in :isLegacy and w.workStatusId.id = :workStatusId")
	Page<Work> findByIsLegacyAndWorkRequestStatusNotDraftAndWorkStatus(Pageable pageable,
			@Param("workRequestStatusId") Long id, @Param("isLegacy") List isLegacy, @Param("workStatusId") Long id2);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.isLegacy in :isLegacy " + "and w.workRequestStatusId.id = :statusId "
			+ "and w.executiveEngineerOffice.id = :exeOfficeId " + "and w.workStatusId.id = :workStatusId")
	Page<Work> findByIsLegacyAndWorkRequestStatusNotDraftAndWorkStatus2(Pageable pageable,
			@Param("workRequestStatusId") Long id, @Param("isLegacy") List isLegacy, @Param("statusId") Long statusId,
			@Param("exeOfficeId") Long exeOfficeId, @Param("workStatusId") Long id2);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id != :workRequestStatusId and w.isLegacy = :isLegacy and w.workStatusId.id = :workStatusId")
	long countByIsLegacyAndWorkRequestStatusNotDraftAndWorkStatus(@Param("workRequestStatusId") Long id,
			@Param("isLegacy") Short isLegacy, @Param("workStatusId") Long id2);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id in :workRequestStatusId and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId")
	Page<Work> findByIsLegacyAndStatusListAndChiefEngineerOfficeId(Pageable pageable, @Param("isLegacy") Short isLegacy,
			@Param("workRequestStatusId") List<Long> idList, @Param("officeId") Long officeId);

	// greater than Work request status id 1
	@Query("from Work w where (w.status is null or w.status!='Deleted') and  w.isLegacy in :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id>1")
	Page<Work> findByIsLegacyAndStatusListAndChiefEngineerOfficeIdForLegacy(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.workRequestStatusId.id in :workRequestStatusIdList and w.executiveEngineerOffice.id = :officeId")
	Page<Work> findByIsLegacyAndWorkRequestStatusAndExecutiveOfficeList(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("workRequestStatusIdList") List<Long> workRequestStatusIdList,
			@Param("officeId") Long officeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.workRequestStatusId.id in :workRequestStatusIdList and w.executiveEngineerOffice.id = :officeId")
	long countByIsLegacyAndWorkRequestStatusAndExecutiveOfficeList(@Param("isLegacy") Short isLegacy,
			@Param("workRequestStatusIdList") List<Long> workRequestStatusIdList, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id in :workRequestStatusIdList and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId and w.district.districtId=:districtId")
	Page<Work> findByIsLegacyAndRequestStatusListAndChiefEngineerOfficeIdAndDistrictId(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("workRequestStatusIdList") List<Long> workRequestStatusIdList,
			@Param("officeId") Long officeId, @Param("districtId") Long districtId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id in :workRequestStatusIdList and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId and w.district.districtId=:districtId")
	long countByIsLegacyAndRequestStatusListAndChiefEngineerOfficeIdAndDistrictId(@Param("isLegacy") Short isLegacy,
			@Param("workRequestStatusIdList") List<Long> workRequestStatusIdList, @Param("officeId") Long officeId,
			@Param("districtId") Long districtId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id in :workRequestStatusId and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId")
	long countByIsLegacyAndStatusListAndChiefEngineerOfficeId(@Param("isLegacy") Short isLegacy,
			@Param("workRequestStatusId") List<Long> idList, @Param("officeId") Long officeId);

	@Query(" from Work w where (w.status is null or w.status!='Deleted') and w.id not in (select distinct(b.work.id) from Bill b) and w.workRequestStatusId.id in :workRequestStatusId and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId")
	Page<Work> findByIsLegacyAndStatusListAndChiefEngineerOfficeIdAndBillNotGenerated(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("workRequestStatusId") List<Long> idList,
			@Param("officeId") Long officeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.id not in (select distinct(b.work.id) from Bill b) and w.workRequestStatusId.id in :workRequestStatusId and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId")
	long countByIsLegacyAndStatusListAndChiefEngineerOfficeIdAndBillNotGenerated(@Param("isLegacy") Short isLegacy,
			@Param("workRequestStatusId") List<Long> idList, @Param("officeId") Long officeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id>1")
	long countByIsLegacyAndStatusListAndChiefEngineerOfficeIdForLegacy(@Param("isLegacy") List isLegacy,

			@Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.district.districtId=:districtId")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndDistrict(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("districtId") Long districtId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.district.districtId=:districtId")
	long countByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndDistrict(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("districtId") Long districtId);

	// richa
	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) "
			+ " and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ " and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ " and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ " and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ " and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ " and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ " and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ " and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ " and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ " and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE(:financialYear, w.financialYear)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)" + ")")

	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndFiltered(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId,
			@Param("workNatureId") Long workNatureId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */
	);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  or w.workStatusId.id is null) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id))"
			/*
			 * +
			 * "and DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE( :fromDate, DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE( :toDate, DATE(w.administrationSanction.administrativeSanctionDate)"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)" + ")")
	long countByIsLegacyAndExecutiveEngineerOfficeIdAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workNatureId") Long workNatureId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy  and "
			+ "w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndLineDepartmentIdAndFiltered(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("financialYear") String financialYear

	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy  and "
			+ "w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			/*
			 * + "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			 */
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndLineDepartmentIdAndFilteredWODistrict(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			/* @Param("districtId") Long districtId, */
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("financialYear") String financialYear

	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndLineDepartmentIdAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("financialYear") String financialYear
	/* @Param("fromDate") Date fromDate,@Param("toDate") Date toDate */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ " and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null) "
			/*
			 * +
			 * " AND (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndLineDepartmentId(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear

	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			/*
			 * + "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			 */
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ " and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null) "
			/*
			 * +
			 * " AND (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndLineDepartmentIdWODistrict(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			/* @Param("districtId") Long districtId, */
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear

	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ " and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null) "
			/*
			 * +
			 * " AND (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			/* + " and w.financialYear = COALESCE( :financialYear, w.financialYear)" */
			+ ")")
	Page<Work> findByIsLegacyAndLineDepartmentIdWOAD(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ " and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * " AND (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			/* + " and w.financialYear = COALESCE( :financialYear, w.financialYear)" */
			+ ")")
	Page<Work> findByIsLegacyAndLineDepartmentIdWoFinYear(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId

	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")
	long countByIsLegacyAndLineDepartmentId(@Param("isLegacy") List isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")
	long countByIsLegacyAndLineDepartmentIdWOAD(@Param("isLegacy") List isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId")
	List<Work> findWorkListByIsLegacyAndLineDepartmentId(@Param("isLegacy") Short isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId  "
			+ "and (w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId )) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)) "
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId))"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and (w.district.districtId=COALESCE( :districtId, w.district.districtId)) "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId))"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId))"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndLineDepartmentIdAndWorkStatus(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workStatusId") Long workStatusId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId  "
			+ "and (w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId )) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)) "
			+ "and (w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId))"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and (w.district.districtId=COALESCE( :districtId, w.district.districtId)) "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId))"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId))"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndLineDepartmentIdAndWorkStatusWOAD(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workStatusId") Long workStatusId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndLineDepartmentIdAndWorkStatus(@Param("isLegacy") Short isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workStatusId") Long workStatusId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy  "
			+ "and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id)"
			+ "and w.executiveEngineerOffice.id=COALESCE( :eeOfficeId, w.executiveEngineerOffice.id)"
			+ "and w.superintendingEngineerOffice.id=COALESCE( :supOfficeId, w.superintendingEngineerOffice.id)"
			+ "and w.chiefEngineerOffice.id=COALESCE( :ceOfficeId, w.chiefEngineerOffice.id)"
			+ " and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and (w.financialYear = COALESCE( :financialYear, w.financialYear) or w.financialYear is null)" + ")")
	Page<Work> findByIsLegacyAndFiltered(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("eeOfficeId") Long eeOfficeId, @Param("supOfficeId") Long supOfficeId,
			@Param("ceOfficeId") Long ceOfficeId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "

			+ " and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndFilteredWOOffice(Pageable pageable, @Param("isLegacy") List isLegacy,

			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("workRequestStatusId") Long workRequestStatusId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.workRequestStatusId.id = :statusId " + "and w.executiveEngineerOffice.id = :exeOfficeId "

			+ " and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findByIsLegacyAndFilteredWOOfficeStatus(Pageable pageable, @Param("isLegacy") List isLegacy,
			@Param("statusId") Long statusId, @Param("exeOfficeId") Long exeOfficeId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("workRequestStatusId") Long workRequestStatusId,

			@Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.id not in (select distinct(b.work.id) from Bill b) and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate)))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)"
			+ " and w.workRequestStatusId.id in :workRequestStatusIdList ")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndFiltered(Pageable pageable, @Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusIdList") List<Long> workRequestStatusIdList,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and  w.workRequestStatusId.id>1 and  w.isLegacy in :isLegacy and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate)))"
			 */
			+ " and (w.financialYear = COALESCE( :financialYear, w.financialYear) or w.financialYear is null)")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndFilteredForLegacy(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId,
			/* @Param("workRequestStatusIdList") List<Long> workRequestStatusIdList, */
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w  where (w.status is null or w.status!='Deleted') and  w.id not in (select distinct(b.work.id) from Bill b) and w.isLegacy = :isLegacy  and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId)  and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate)))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)"
			+ " and w.workRequestStatusId.id in :workRequestStatusIdList")
	long countByIsLegacyAndChiefEngineerOfficeIdAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusIdList") List<Long> workRequestStatusIdList,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w  where  (w.status is null or w.status!='Deleted') and  w.isLegacy in :isLegacy  and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId)  and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate)))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)")
	long countByIsLegacyAndChiefEngineerOfficeIdAndFilteredForLegacy(@Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId,
			/* @Param("workRequestStatusIdList") List<Long> workRequestStatusIdList, */
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy  and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id))"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)" + ")")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeAndFiltered(Pageable pageable, @Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workNatureId") Long workNatureId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("workRequestStatusId") Long workRequestStatusId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId)  and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id))"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)" + ")")
	long countByIsLegacyAndChiefEngineerOfficeAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workNatureId") Long workNatureId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("workRequestStatusId") Long workRequestStatusId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.superintendingEngineerOffice.id = COALESCE( :officeId, w.superintendingEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ " and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndFiltered(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = COALESCE( :officeId, w.superintendingEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id))"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)" + ")")
	long countByIsLegacyAndSuperintendingEngineerOfficeIdAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workNatureId") Long workNatureId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("workRequestStatusId") Long workRequestStatusId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy  and w.superintendingEngineerOffice.id = COALESCE( :officeId, w.superintendingEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)" + ")")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeAndFiltered(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId,
			@Param("workNatureId") Long workNatureId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = COALESCE( :officeId, w.superintendingEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId)  and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			+ "and w.workRequestStatusId.id != :workRequestStatusId ")
	long countByIsLegacyAndSuperintendingEngineerOfficeAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workNatureId") Long workNatureId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("workRequestStatusId") Long workRequestStatusId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.assistantEngineer.id = :userId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndAssistantEngineerUserIdAndFiltered(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("userId") Long userId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.subDivisionalOfficer.id = :userId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndSubDivisionalOfficerUserIdAndFiltered(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("userId") Long userId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)"
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.subDivisionalOfficer.id = :userId "
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndExecutiveEngineerOfficeIdAndSubDivisionalOfficerUserIdAndFiltered(
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId,
			@Param("workNatureId") Long workNatureId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("userId") Long userId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "
			+ "and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)"
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.assistantEngineer.id = :userId "
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndExecutiveEngineerOfficeIdAndAssistantEngineerUserIdAndFiltered(
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId,
			@Param("workNatureId") Long workNatureId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("userId") Long userId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in (:isLegacy) "
			+ "and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) "
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id) "
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId) "
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId) "
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) "
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null ) "
			+ "and w.subEngineer.id = :userId "
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null) "
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)")
	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndSubEngineerUserIdAndFiltered(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("userId") Long userId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.subEngineer.id = :userId " + "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id))"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndExecutiveEngineerOfficeIdAndSubEngineerUserIdAndFiltered(@Param("isLegacy") List isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("userId") Long userId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select w.workRequestStatusId.id as id , w.workRequestStatusId.statusNameE as name, count(*) as count1 from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>1 group by w.workRequestStatusId.id")
	List<Object[]> findStatusWiseWorkListforAdminAndEnC();

	@Query("select w.workRequestStatusId.id as id , w.workRequestStatusId.statusNameE as name, count(*) as count1 from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>1 and w.chiefEngineerOffice.id = :chiefEngineerOfficeId group by w.workRequestStatusId.id")
	List<Object[]> findStatusWiseWorkListforCE(@Param("chiefEngineerOfficeId") Long chiefEngineerOfficeId);

	@Query("select w.workRequestStatusId.id as id , w.workRequestStatusId.statusNameE as name, count(*) as count1 from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>1 and w.superintendingEngineerOffice.id = :superintendingEngineerOfficeId group by w.workRequestStatusId.id")
	List<Object[]> findStatusWiseWorkListforSE(
			@Param("superintendingEngineerOfficeId") Long superintendingEngineerOfficeId);

	@Query("select w.workRequestStatusId.id as id , w.workRequestStatusId.statusNameE as name, count(*) as count1 from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>1 and w.executiveEngineerOffice.id = :executiveEngineerOfficeId group by w.workRequestStatusId.id")
	List<Object[]> findStatusWiseWorkListforEE(@Param("executiveEngineerOfficeId") Long executiveEngineerOfficeId);

	@Query("select w.workRequestStatusId.id as id , w.workRequestStatusId.statusNameE as name, count(*) as count1 from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>1 and w.assistantEngineer.id = :assistantEngineerId group by w.workRequestStatusId.id")
	List<Object[]> findStatusWiseWorkListforAE(@Param("assistantEngineerId") Long assistantEngineerId);

	@Query("select w.workRequestStatusId.id as id , w.workRequestStatusId.statusNameE as name, count(*) as count1 from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>1 and w.subDivisionalOfficer.id = :subDivisionalOfficerId group by w.workRequestStatusId.id")
	List<Object[]> findStatusWiseWorkListforSDO(@Param("subDivisionalOfficerId") Long subDivisionalOfficerId);

	@Query("select w.workRequestStatusId.id as id , w.workRequestStatusId.statusNameE as name, count(*) as count1 from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>1 and w.subEngineer.id = :subEngineerId group by w.workRequestStatusId.id")
	List<Object[]> findStatusWiseWorkListforSubEngineer(@Param("subEngineerId") Long subEngineerId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId")
	List<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatus(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId")
	List<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatus(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId")
	List<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatus(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id != :workRequestStatusId and w.isLegacy = :isLegacy and w.workStatusId.id = :workStatusId")
	List<Work> findByIsLegacyAndWorkRequestStatusNotDraftAndWorkStatus(@Param("workRequestStatusId") Long id,
			@Param("isLegacy") Short isLegacy, @Param("workStatusId") Long id2);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId ")
	List<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndWorkStatus(
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId ")
	List<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndWorkRequestStatusAndWorkStatus(
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId and w.workStatusId.id = :workStatusId ")
	List<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusAndWorkStatus(
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workStatusId") Long id2);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy  and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId")
	List<Work> findByIsLegacyAndFiltered(@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("workRequestStatusId") Long workRequestStatusId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id in :workRequestStatusIdList")
	List<Work> findByIsLegacyAndChiefEngineerOfficeIdAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusIdList") List<Long> workRequestStatusIdList);

	@Query(" from Work w where (w.status is null or w.status!='Deleted') and w.id not in (select distinct(b.work.id) from Bill b) and w.workRequestStatusId.id in :workRequestStatusId and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = :officeId")
	List<Work> findByIsLegacyAndStatusListAndChiefEngineerOfficeIdAndBillNotGenerated(@Param("isLegacy") Short isLegacy,
			@Param("workRequestStatusId") List<Long> idList, @Param("officeId") Long officeId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.contractor.id=COALESCE( :contractorId, w.contractor.id )")
	List<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.subEngineer.id = :userId")
	List<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndSubEngineerUserIdAndFiltered(
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("userId") Long userId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.assistantEngineer.id = :userId")
	List<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndAssistantEngineerUserIdAndFiltered(
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("userId") Long userId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.subDivisionalOfficer.id = :userId")
	List<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndSubDivisionalOfficerUserIdAndFiltered(
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("userId") Long userId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = COALESCE( :officeId, w.superintendingEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ " and w.workRequestStatusId.id != :workRequestStatusId")
	List<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId and w.workStatusId.id = :workStatusId ")
	List<Work> findByIsLegacyAndLineDepartmentIdAndWorkStatus(@Param("isLegacy") Short isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("workStatusId") Long workStatusId);

	@Query("from Work w where w.isLegacy = :isLegacy and w.lineDepartmentId.lineDepartmentId = :lineDepartmentId")
	List<Work> findByIsLegacyAndLineDepartmentId(@Param("isLegacy") Short isLegacy,
			@Param("lineDepartmentId") Long lineDepartmentId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy  and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId")
	List<Work> findByIsLegacyAndLineDepartmentIdAndFiltered(@Param("isLegacy") Short isLegacy,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy  and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "

			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeAndFilteredAndRequisition(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId,
			@Param("workNatureId") Long workNatureId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId)  and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndChiefEngineerOfficeAndFilteredAndRequisition(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")

	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndFilteredAndRequisition(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) and w.workNatureId.workNatureId=COALESCE( :workNatureId, w.workNatureId.workNatureId ) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id)  or w.workStatusId.id is null) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")
	long countByIsLegacyAndExecutiveEngineerOfficeIdAndFilteredAndRequisition(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workNatureId") Long workNatureId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = COALESCE( :officeId, w.superintendingEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId)  and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndSuperintendingEngineerOfficeAndFilteredAndRequisition(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusAndRequisition(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = :officeId and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")
	long countByIsLegacyAndSuperintendingEngineerOfficeIdAndWorkRequestStatusAndRequisition(
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy  and "
			+ "w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			/* + "and w.workRequestStatusId.id != :workRequestStatusId " */
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id))"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findByIsLegacyAndLineDepartmentIdAndFilteredAndRequisition(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId,
			@Param("contractorId") Long contractorId, @Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	long countByIsLegacyAndLineDepartmentIdAndFilteredAndRequisition(@Param("isLegacy") Short isLegacy,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy  and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			+ "and w.workRequestStatusId.id != :workRequestStatusId ")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeAndFilteredAndRequisition(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId,
			@Param("workNatureId") Long workNatureId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId)  and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId")
	long countByIsLegacyAndChiefEngineerOfficeAndFilteredAndRequisition(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy  and w.superintendingEngineerOffice.id = COALESCE( :officeId, w.superintendingEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			+ "and w.workRequestStatusId.id != :workRequestStatusId and w.financialYear = COALESCE( :financialYear, w.financialYear)")
	Page<Work> findByIsLegacyAndSuperintendingEngineerOfficeAndFilteredAndRequisition(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId,
			@Param("workNatureId") Long workNatureId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId,
			@Param("financialYear") String financialYear);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.superintendingEngineerOffice.id = COALESCE( :officeId, w.superintendingEngineerOffice.id) and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null  ) and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId)  and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workRequestStatusId.id != :workRequestStatusId ")
	long countByIsLegacyAndSuperintendingEngineerOfficeAndFilteredAndRequisition(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("workRequestStatusId") Long workRequestStatusId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy  and "
			+ "w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")

	Page<Work> findByIsLegacyAndLineDepartmentIdAndFilteredAndRequisition(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("districtId") Long districtId, @Param("blockId") Long blockId,
			@Param("gramPanchayatId") Long gramPanchayatId, @Param("villageId") Long villageId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy  and "
			+ "w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			/*
			 * + "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			 */
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")

	Page<Work> findByIsLegacyAndLineDepartmentIdAndFilteredAndRequisitionWODistrict(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			/* @Param("districtId") Long districtId, */
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy  and "
			+ "w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			+ "and w.financialYear = COALESCE( :financialYear, w.financialYear)")

	Page<Work> findByIsLegacyAndLineDepartmentIdAndFilteredWithFinancialYear(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("workNatureId") Long workNatureId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("financialYear") String financialYear);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")
	long countByIsLegacyAndLineDepartmentIdAndFilteredAndRequisition(@Param("isLegacy") List isLegacy,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id ) and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")
	Page<Work> findByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndRequisition(Pageable pageable,
			@Param("isLegacy") Short isLegacy, @Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.chiefEngineerOffice.id = COALESCE( :officeId, w.chiefEngineerOffice.id ) and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )")
	long countByIsLegacyAndChiefEngineerOfficeIdAndWorkRequestStatusAndRequisition(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("workRequestStatusId") Long id,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("lineDepartmentId") Long lineDepartmentId, @Param("accountHeadId") Long accountHeadId,
			@Param("executionAgencyId") Long executionAgencyId, @Param("workStatusId") Long workStatusId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId);

//Old working code
	@Query("from Work w where (w.status is null or w.status!='Deleted') and (:workName is null or w.workName like %:workName%) "
			+ "and w.executiveEngineerOffice = :executiveEngineerOffice and w.isLegacy=0 and w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id>=6")
	Page<Work> findPendingWorkAgrrementByExecutiveEngineerOffice(Pageable pageable, @Param("workName") String workName,
			@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	// Rakesh working
	@Query(value = "Select  " + "w.id as workId,    " + "w.work_requisition_no,    " + "w.work_name, "
			+ "w.agency_type_id, " + "mat.agency_type_name_e, " + "wt.tender_cost, " + "wt.contractor_id, " + "c.name, "
			+ "wrs.status_name_e as workRequestStatus, " + "wa.status, " + "wa.agreement_date, "
			+ "wa.tentative_completion_date, " + "wt.parent_id, " + "wt.id as workTenderId, "
			+ "wa.id as workAgreementId," + "mwas.status_name_e " + "from "
			+ "(SELECT max(id) as id FROM work_tender   group by work_id  order by created_date desc  ) innerTable "
			+ "left  join work_tender wt on wt.id=innerTable.id  "
			+ "left join work_agreement wa on wt.id= wa.work_tender_id " + "left join work w on w.id=wt.work_id   "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_workagreeemt_status mwas on wa.status=mwas.id "
			+ "where (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=?1  " +
			// "and w.work_request_status_id>=6 and w.is_legacy=0 " +
			"and w.is_legacy=0 " + "and w.agency_type_id in (1) "
			+ "order by wt.modified_date desc limit ?2,?3", nativeQuery = true)
	List<Object[]> findPendingWorkAgrrementByExecutiveEngineerOfficeQuery(
			@Param("executive_engineer_office_id") Long executive_engineer_office_id, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit);

	// Rakesh
	@Query(value = "Select count(Distinct w.id  ) " + "from "
			+ "(SELECT max(id) as id FROM work_tender   group by work_id  order by created_date desc) innerTable "
			+ "left  join work_tender wt on wt.id=innerTable.id  "
			+ "left join work_agreement wa on wt.id= wa.work_tender_id " + "left join work w on w.id=wt.work_id   "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_workagreeemt_status mwas on wa.status=mwas.id "
			+ "where (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=?1  " +
			// "and w.work_request_status_id>=6 and w.is_legacy=0 " +
			"and w.is_legacy=0 " + "and w.agency_type_id in (1) "
			+ "order by wt.modified_date desc ", nativeQuery = true)
	long findPendingWorkAgrrementByExecutiveEngineerOfficeCount(
			@Param("executive_engineer_office_id") Long executive_engineer_office_id);

	// History
	@Query(value = "Select  " + "w.id as workId,    " + "w.work_requisition_no,    " + "w.work_name, "
			+ "w.agency_type_id, " + "mat.agency_type_name_e, " + "wt.tender_cost, " + "wt.contractor_id, " + "c.name, "
			+ "wrs.status_name_e as workRequestStatus, " + "wa.status, " + "wa.agreement_date, "
			+ "wa.tentative_completion_date, " + "wt.parent_id, " + "wt.id as workTenderId, "
			+ "wa.id as workAgreementId, " + "mwas.status_name_e " + "from "
			+ "(SELECT * FROM work_tender   order by modified_date desc  ) innerTable "
			+ "left  join work_tender wt on wt.id=innerTable.id  "
			+ "left join work_agreement wa on wt.id= wa.work_tender_id "
			+ "left join work w on w.id=innerTable.work_id   "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_workagreeemt_status mwas on wa.status=mwas.id "
			+ "where (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=?1  "
			+ " and w.is_legacy=0 and w.id=?2 and wt.id!=?3 " + "and w.agency_type_id in (1)  "
			+ "order by wt.modified_date desc ", nativeQuery = true)
	List<Object[]> findHistoryWorkAgrrementByExecutiveEngineerOfficeQuery(
			@Param("executive_engineer_office_id") Long executive_engineer_office_id, @Param("workId") Long workId,
			@Param("tenderId") Long tenderId);

	// To get All IDs
	@Query(value = "Select  " + "w.id as workId,    " + "wt.contractor_id, " + "wt.parent_id, "
			+ "wt.id as workTenderId, " + "wa.id as workAgreementId, " + "ads.id as administrative_sanction_id, "
			+ "ts.id as technicalSanctionID, " + "we.id as work_estimate_id " + "from "
			+ "(SELECT * FROM work_tender   order by modified_date desc  ) innerTable "
			+ "left  join work_tender wt on wt.id=innerTable.id  "
			+ "left join work_agreement wa on wt.id= wa.work_tender_id "
			+ "left join work w on w.id=innerTable.work_id   "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_workagreeemt_status mwas on wa.status=mwas.id "
			+ "left join administrative_sanction ads on wt.administrative_sanction_id=ads.id  "
			+ "left join technical_sanction ts on ts.id=ads.technical_sanction_id "
			+ "left join work_estimate we on we.id=ts.work_estimate_id "
			+ "where (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=?1  "
			+ "and w.is_legacy=0 and wt.id=?3 and w.id=?2 " + "and w.agency_type_id in (1) ", nativeQuery = true)
	List<Object[]> findHistoryWorkAgrrementAllIdsQuery(
			@Param("executive_engineer_office_id") Long executive_engineer_office_id, @Param("workId") Long workId,
			@Param("tenderId") Long tenderId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and (:workName is null or w.workName like %:workName%) "
			+ "and w.executiveEngineerOffice = :executiveEngineerOffice and w.workRequestStatusId.id>=11")
	Page<Work> findWorkForPhysicalCCByExecutiveEngineerOffice(Pageable pageable, @Param("workName") String workName,
			@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and (:workName is null or w.workName like %:workName%) "
			+ "and w.executiveEngineerOffice = :executiveEngineerOffice and w.workRequestStatusId.id>=13")
	Page<Work> findWorkForFinancialCCByExecutiveEngineerOffice(Pageable pageable, @Param("workName") String workName,
			@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>=2 and w.executiveEngineerOffice.id = :officeId and w.isLegacy=0")
	long countPendingWorkEstimationsByExecutiveEngineerOffice(@Param("officeId") Long id);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.executiveEngineerOffice = :executiveEngineerOffice and w.workRequestStatusId.id >= 7 ")
	Page<Work> fetchWorkForGeneralInspectionListforEE(Pageable pageable,
			@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.executiveEngineerOffice = :executiveEngineerOffice and w.workRequestStatusId.id >= 7 ")
	long countWorkGeneralInspectionListforEE(@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	
	@Query("from Work w " +
		       "where (w.status is null or w.status != 'Deleted') " +
		       "and w.executiveEngineerOffice = :executiveEngineerOffice " +
		       "and w.workRequestStatusId.id >= 8 " +
		       "order by w.modifiedDate desc")
	Page<Work> fetchWorkInspectionListforEE(Pageable pageable,
			@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.executiveEngineerOffice = :executiveEngineerOffice and w.workRequestStatusId.id >= 8 ")
	long countWorkInspectionListforEE(@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.executiveEngineerOffice = :executiveEngineerOffice and w.workRequestStatusId.id = 8 ")
	List<Work> fetchCCInspectionPendingWorks(@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.executiveEngineerOffice = :executiveEngineerOffice and w.workRequestStatusId.id >= 9 ")
	List<Work> fetchCCInspectionCompletedWorks(@Param("executiveEngineerOffice") Office executiveEngineerOffice);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.executiveEngineerOffice.id = :executiveEngineerOffice and ((w.isLegacy = 1 and w.workStatusId.id in (2,4)) OR (w.isLegacy = 0 and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5) OR (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7))))")
	Page<Work> fetchWorkListForCC(Pageable pageable, @Param("executiveEngineerOffice") Long executiveEngineerOffice);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.executiveEngineerOffice.id = :executiveEngineerOffice and ((w.isLegacy = 1 and w.workStatusId.id in (2,4)) OR (w.isLegacy = 0 and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5) OR (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7))))")
	long countWorkListForCC(@Param("executiveEngineerOffice") Long executiveEngineerOffice);

	// Tender
	@Query("from Work w where (w.status is null or w.status!='Deleted') and  w.workRequestStatusId.id>=5 and w.isLegacy=0 and w.executiveEngineerOffice.id=:officeId and w.agencyTypeId.agencyTypeId in (1) ")
	Page<Work> findWorkTender(Pageable pageable, @Param("officeId") Long officeId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.workRequestStatusId.id>=5 and w.isLegacy=0")
	long countWorkTender();

	// Rakesh working
	/*
	 * @Query(value="Select  " + "w.id as workId,    " +
	 * "w.work_requisition_no,    " + "w.work_name, " + "w.agency_type_id, " +
	 * "mat.agency_type_name_e, " + "wt.tender_cost, " + "wt.contractor_id, " +
	 * "c.name, " + "wrs.status_name_e as workRequestStatus, " + "wt.status, " +
	 * "mts.status_name_e, " + "wt.parent_id " + "from " +
	 * "(SELECT * FROM work_tender   order by modified_date desc  ) innerTable " +
	 * "inner join work_tender wt on wt.id=innerTable.id  " +
	 * "left join work w on w.id=innerTable.work_id   " +
	 * "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  " +
	 * "left join contractor c on wt.contractor_id = c.id  " +
	 * "left join mst_request_status wrs on w.work_request_status_id=wrs.id   " +
	 * "left join mst_tender_status mts on mts.id= wt.status " +
	 * "where   w.executive_engineer_office_id= ?1  " +
	 * "and w.work_request_status_id>=5 and w.is_legacy=0 " +
	 * "and w.agency_type_id in (1) " +
	 * "order by wt.modified_date desc limit ?2,?3 ",nativeQuery=true)
	 */
	@Query(value = "Select  " + "w.id as workId,    " + "w.work_requisition_no,    " + "w.work_name, "
			+ "w.agency_type_id, " + "mat.agency_type_name_e, " + "wt.tender_cost, " + "wt.contractor_id, " + "c.name, "
			+ "wrs.status_name_e as workRequestStatus, " + "wt.status, " + "mts.status_name_e, " + "ads.parent_id,"
			+ "ads.id ,ads.administration_sanction_type_id as AdminTypeId,w.work_request_status_id  " + "from "
			+ "(SELECT max(id) as id FROM administrative_sanction  group by work_id  order by created_date desc ) innerTable "
			+ "inner join administrative_sanction ads on ads.id=innerTable.id  "
			+ "left join work w on w.id=ads.work_id   "
			+ "left join work_tender wt on wt.administrative_sanction_id=ads.id "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_tender_status mts on mts.id= wt.status "
			+ "where  (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id= ?1  " +
//			"and w.work_request_status_id>=5 and w.is_legacy=0 " + 
			"and w.is_legacy=0 " + "and w.agency_type_id in (1)  "
			+ "order by ads.modified_date desc limit ?2,?3 ", nativeQuery = true)
	List<Object[]> findWorkTenderByQuery(@Param("executive_engineer_office_id") Long executive_engineer_office_id,
			@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	// + "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id)
	// limit :offset,:maxLimit ",nativeQuery=true)
	@Query(value = "select w.work_name,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e,w.work_requisition_no,w.id   "
			+ "from work w " + "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted') and w.work_request_status_id=:workReqStatusId and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and case WHEN :exeOfficeId1 is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId1, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId1 is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId1) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)

	List<Object[]> findWorkDataForMultiStatus(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("workReqStatusId") Long workReqStatusId,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("exeOfficeId1") String exeOfficeId1,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workStatusId1") String workStatusId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit);

	// findWorkListTwoForChiefReport supdtOfficeId1

	@Query(value = "select w.work_name,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e,w.work_requisition_no,w.id,w.IS_LEGACY   "
			+ "from work w " + "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted') and w.work_request_status_id <> 1  and w.is_legacy in :totalList  and w.chief_engineer_office_id = COALESCE(:officeId, w.chief_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusLong, w.work_status_id) "
			+ "and case WHEN :exeOfficeId1 is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId1, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId1 is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId1) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "
			+ "and case WHEN :supdtOfficeId1 is null THEN   ifnull(w.superintending_engineer_office_id, 0) = IFNULL(:supdtOfficeId1, ifnull(w.superintending_engineer_office_id, 0)) when :supdtOfficeId1 is not null then FIND_IN_SET(w.superintending_engineer_office_id, :supdtOfficeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)

	List<Object[]> findWorkListTwoForChiefReport(@Param("officeId") Long officeId, @Param("totalList") List totalList,
			@Param("workStatusLong") Long workStatusLong, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("exeOfficeId1") String exeOfficeId1, @Param("supdtOfficeId1") String supdtOfficeId1,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workStatusId1") String workStatusId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit);

	@Query(value = "select w.work_name,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e,w.work_requisition_no,w.id   "
			+ "from work w " + "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted') and w.work_request_status_id <> 1  and w.is_legacy in :totalList  and w.superintending_engineer_office_id = COALESCE(:officeId, w.superintending_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusLong, w.work_status_id) "
			+ "and case WHEN :exeOfficeId1 is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId1, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId1 is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId1) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)

	List<Object[]> findWorkListTwoForSupdtReport(@Param("officeId") Long officeId, @Param("totalList") List totalList,
			@Param("workStatusLong") Long workStatusLong, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("exeOfficeId1") String exeOfficeId1, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workStatusId1") String workStatusId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1,
			@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	// findWorkListTwoForChiefReportcount

	@Query(value = "select count(*)   " + "from work w "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and w.work_request_status_id <> 1 and w.is_legacy in :totalList  and w.chief_engineer_office_id = COALESCE(:officeId, w.chief_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusLong, w.work_status_id) "
			+ "and case WHEN :exeOfficeId1 is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId1, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId1 is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId1) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "
			+ "and case WHEN :supdtOfficeId1 is null THEN   ifnull(w.superintending_engineer_office_id, 0) = IFNULL(:supdtOfficeId1, ifnull(w.superintending_engineer_office_id, 0)) when :supdtOfficeId1 is not null then FIND_IN_SET(w.superintending_engineer_office_id, :supdtOfficeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) ", nativeQuery = true)

	long findWorkListTwoForChiefReportcount(@Param("officeId") Long officeId, @Param("totalList") List totalList,
			@Param("workStatusLong") Long workStatusLong, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("exeOfficeId1") String exeOfficeId1, @Param("supdtOfficeId1") String supdtOfficeId1,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workStatusId1") String workStatusId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1);

	@Query(value = "select count(*)   " + "from work w "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and w.work_request_status_id <> 1 and w.is_legacy in :totalList  and w.superintending_engineer_office_id = COALESCE(:officeId, w.superintending_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusLong, w.work_status_id) "
			+ "and case WHEN :exeOfficeId1 is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId1, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId1 is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId1) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) ", nativeQuery = true)

	long findWorkListTwoForSupdtReportcount(@Param("officeId") Long officeId, @Param("totalList") List totalList,
			@Param("workStatusLong") Long workStatusLong, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("exeOfficeId1") String exeOfficeId1, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workStatusId1") String workStatusId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1);

	@Query(value = "select w.work_name,b.bill_amount_without_deduction,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e ,w.work_requisition_no,w.id,ifnull(d.Expenditure_amount, 0)  "
			+ "from work w " + "left join bill b on b.work_id=w.id "
			+ "left join contengency_table d on b.id = d.bill_id " + "left join payment_table pt on pt.bill_id = b.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=:eeOfficeId and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and pt.cashbook_date between :fromYear and :endYear "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			+ "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)
	List<Object[]> findDataExAgWise(@Param("ceOfficeId1") Long ceOfficeId1, @Param("seOfficeId1") Long seOfficeId1,
			@Param("eeOfficeId") Long eeOfficeId, @Param("fromYear") Date fromYear, @Param("endYear") Date endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workStatusId1") String workStatusId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("workTypeId1") Long workTypeId1, @Param("workSubTypeId1") String workSubTypeId1,
			@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	@Query(value = "select w.work_name,b.bill_amount_without_deduction,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e ,w.work_requisition_no,w.id,Expenditure_amount "
			+ "from work w " + "left join bill b on b.work_id=w.id "
			+ "left join (select bill_id,work_id,Expenditure_amount from contengency_table) d on b.id = d.bill_id "
			+ "left join (select cashbook_date,bill_id from payment_table where (cashbook_date between @StartDate and @Enddate)) pt on pt.bill_id = b.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and b.status='7' and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and cashbook_date between :fromYear and :endYear and "
			+ "case WHEN :exeOfficeId is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			// + "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)
	List<Object[]> findWorkTenderByQuery9(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("exeOfficeId") String exeOfficeId,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workStatusId1") String workStatusId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1,
			@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	@Query(value = "select w.work_name,b.bill_amount_without_deduction,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e ,w.work_requisition_no,w.id,Expenditure_amount "
			+ "from work w " + "left join bill b on b.work_id=w.id "
			+ "left join (select bill_id,work_id,Expenditure_amount from contengency_table) d on b.id = d.bill_id "
			+ "left join (select cashbook_date,bill_id from payment_table where (cashbook_date between @StartDate and @Enddate)) pt on pt.bill_id = b.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and b.status='7' and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and cashbook_date between :fromYear and :endYear "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			// + "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)
	List<Object[]> findWorkTenderByQuery9Total(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("fromYear") String fromYear,
			@Param("endYear") String endYear, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workStatusId1") String workStatusId1,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit);

	@Query(value = "select count(w.id) from work w left join bill b on b.work_id=w.id "
			+ "left join (select bill_id,work_id,Expenditure_amount from contengency_table) d on b.id = d.bill_id "
			+ "left join (select cashbook_date,bill_id from payment_table where (cashbook_date between @StartDate and @Enddate)) pt on pt.bill_id = b.id "
			+ "where "
			+ " (w.status is null or w.status!='Deleted')  and b.status='7' and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and cashbook_date between :fromYear and :endYear and "
			+ "case WHEN :exeOfficeId is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			// + "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) ", nativeQuery = true)
	long findWorkTenderByQuery9count(@Param("ceOfficeId1") Long ceOfficeId1, @Param("seOfficeId1") Long seOfficeId1,
			@Param("exeOfficeId") String exeOfficeId, @Param("fromYear") String fromYear,
			@Param("endYear") String endYear, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workStatusId1") String workStatusId1,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1);

	@Query(value = "select count(w.id) from work w left join bill b on b.work_id=w.id "
			+ "left join (select bill_id,work_id,Expenditure_amount from contengency_table) d on b.id = d.bill_id "
			+ "left join (select cashbook_date,bill_id from payment_table where (cashbook_date between @StartDate and @Enddate)) pt on pt.bill_id = b.id "
			+ "where "
			+ " (w.status is null or w.status!='Deleted')  and b.status='7' and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and cashbook_date between :fromYear and :endYear "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			// + "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) ", nativeQuery = true)
	long findWorkTenderByQuery9countTotal(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("fromYear") String fromYear,
			@Param("endYear") String endYear, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workStatusId1") String workStatusId1,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1);

	@Query(value = "select w.work_name,b.bill_amount_without_deduction,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e,w.work_requisition_no,w.id ,Expenditure_amount  "
			+ "from work w " + "left join bill b on b.work_id=w.id "
			+ "left join (select bill_id,work_id,Expenditure_amount from contengency_table) d on b.id = d.bill_id "
			+ "left join (select cashbook_date,bill_id from payment_table where (cashbook_date between @StartDate and @Enddate)) pt on pt.bill_id = b.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and b.status in (3,4,5,6) and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and b.bill_date between :fromYear and :endYear and "
			+ "case WHEN :exeOfficeId is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			// + "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)
	List<Object[]> findWorkTenderByQuery9pending(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("exeOfficeId") String exeOfficeId,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workStatusId1") String workStatusId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1,
			@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	@Query(value = "select w.work_name,b.bill_amount_without_deduction,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e,w.work_requisition_no,w.id ,Expenditure_amount  "
			+ "from work w " + "left join bill b on b.work_id=w.id "
			+ "left join (select bill_id,work_id,Expenditure_amount from contengency_table) d on b.id = d.bill_id "
			+ "left join (select cashbook_date,bill_id from payment_table where (cashbook_date between @StartDate and @Enddate)) pt on pt.bill_id = b.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and b.status in (3,4,5,6) and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and b.bill_date between :fromYear and :endYear "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			// + "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)
	List<Object[]> findWorkTenderByQuery9pendingTotal(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("fromYear") String fromYear,
			@Param("endYear") String endYear, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workStatusId1") String workStatusId1,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit);

	@Query(value = "select count(w.id) from work w left join bill b on b.work_id=w.id left join payment_table pt on pt.bill_id=b.id where "
			+ " (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=:eeOfficeId  and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and pt.cashbook_date between :fromYear and :endYear and b.status=7 "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			+ "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "
			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) ", nativeQuery = true)
	long findDataExAgitotal(@Param("ceOfficeId1") Long ceOfficeId1, @Param("seOfficeId1") Long seOfficeId1,
			@Param("eeOfficeId") Long eeOfficeId, @Param("fromYear") Date fromYear, @Param("endYear") Date endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workStatusId1") String workStatusId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("workTypeId1") Long workTypeId1, @Param("workSubTypeId1") String workSubTypeId1);

	@Query(value = "select count(w.id) from work w left join bill b on b.work_id=w.id "
			+ "left join (select bill_id,work_id,Expenditure_amount from contengency_table) d on b.id = d.bill_id "
			+ "left join (select cashbook_date,bill_id from payment_table where (cashbook_date between @StartDate and @Enddate)) pt on pt.bill_id = b.id "
			+ "where "
			+ " (w.status is null or w.status!='Deleted') and b.status in (3,4,5,6) and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and b.bill_date between :fromYear and :endYear and "
			+ "case WHEN :exeOfficeId is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			// + "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) ", nativeQuery = true)
	long findWorkTenderByQuery9countpending(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("exeOfficeId") String exeOfficeId,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workStatusId1") String workStatusId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1);

	@Query(value = "select count(w.id) from work w left join bill b on b.work_id=w.id "
			+ "left join (select bill_id,work_id,Expenditure_amount from contengency_table) d on b.id = d.bill_id "
			+ "left join (select cashbook_date,bill_id from payment_table where (cashbook_date between @StartDate and @Enddate)) pt on pt.bill_id = b.id "
			+ "where "
			+ " (w.status is null or w.status!='Deleted') and b.status in (3,4,5,6) and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and b.bill_date between :fromYear and :endYear "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "
			// + "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) ", nativeQuery = true)
	long findWorkTenderByQuery9countpendingTotal(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("fromYear") String fromYear,
			@Param("endYear") String endYear, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workStatusId1") String workStatusId1,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1);

	@Query(value = "select count(w.id) from work w left join bill b on b.work_id=w.id where "
			+ " (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=:eeOfficeId and b.status='7' and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and b.bill_date between :fromYear and :endYear "
			+ "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and (w.work_sub_type_id = COALESCE(:workSubTypeId1, w.work_sub_type_id) or w.work_sub_type_id is null) "
			+ "and w.line_department_id = COALESCE(:workLineDepartmentId1, w.line_department_id) "
			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) "
			+ "and w.district_id = COALESCE(:districtId1, w.district_id) ", nativeQuery = true)
	long findWorkTenderByQuery9countDisplay(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("eeOfficeId") Long eeOfficeId,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear);

	@Query(value = "select count(w.id) from work w left join bill b on b.work_id=w.id where "
			+ " (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=:eeOfficeId  and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and b.bill_date between :fromYear and :endYear "
			+ "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and (w.work_sub_type_id = COALESCE(:workSubTypeId1, w.work_sub_type_id) or w.work_sub_type_id is null) "
			+ "and w.line_department_id = COALESCE(:workLineDepartmentId1, w.line_department_id) "
			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) "
			+ "and w.district_id = COALESCE(:districtId1, w.district_id) ", nativeQuery = true)
	long findDataExAgitotalDisplay(@Param("ceOfficeId1") Long ceOfficeId1, @Param("seOfficeId1") Long seOfficeId1,
			@Param("eeOfficeId") Long eeOfficeId, @Param("fromYear") Date fromYear, @Param("endYear") Date endYear,
			@Param("workTypeId1") Long workTypeId1, @Param("workSubTypeId1") Long workSubTypeId1,
			@Param("workLineDepartmentId1") Long workLineDepartmentId1,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("districtId1") Long districtId1);

	@Query(value = "select count(w.id) from work w left join bill b on b.work_id=w.id where "
			+ " (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=:eeOfficeId and b.status<'7' and b.status>'1' and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and b.bill_date between :fromYear and :endYear "
			+ "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
			+ "and (w.work_sub_type_id = COALESCE(:workSubTypeId1, w.work_sub_type_id) or w.work_sub_type_id is null) "
			+ "and w.line_department_id = COALESCE(:workLineDepartmentId1, w.line_department_id) "
			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) "
			+ "and w.district_id = COALESCE(:districtId1, w.district_id) ", nativeQuery = true)
	long findWorkTenderByQuery9countDisplaypending(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("eeOfficeId") Long eeOfficeId,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear,
			@Param("workTypeId1") Long workTypeId1, @Param("workSubTypeId1") Long workSubTypeId1,
			@Param("workLineDepartmentId1") Long workLineDepartmentId1,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("districtId1") Long districtId1);

	/*
	 * @Query(value="select count(Distinct w.id ) from " +
	 * "(SELECT * FROM work_tender   order by modified_date desc  ) innerTable " +
	 * "inner join work_tender wt on wt.id=innerTable.id  " +
	 * "left join work w on w.id=innerTable.work_id   " +
	 * "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  " +
	 * "left join contractor c on wt.contractor_id = c.id  " +
	 * "left join mst_request_status wrs on w.work_request_status_id=wrs.id   " +
	 * "left join mst_tender_status mts on mts.id= wt.status " +
	 * "where   w.executive_engineer_office_id=?1  " +
	 * "and w.work_request_status_id>=5 and w.is_legacy=0 " +
	 * "and w.agency_type_id in (1) " +
	 * "order by wt.modified_date desc ",nativeQuery=true)
	 */
	@Query(value = "select count(Distinct w.id ) from  (SELECT max(id) as id FROM administrative_sanction  group by work_id  order by created_date desc) innerTable "
			+ "inner join administrative_sanction ads on ads.id=innerTable.id  "
			+ "left join work w on w.id=ads.work_id   "
			+ "left join work_tender wt on wt.administrative_sanction_id=ads.id "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_tender_status mts on mts.id= wt.status "
			+ "where  (w.status is null or w.status!='Deleted') and  w.executive_engineer_office_id=?1  " +
//			"and w.work_request_status_id>=5 and w.is_legacy=0 " + 
			" and w.is_legacy=0 " + "and w.agency_type_id in (1) "
			+ "order by wt.modified_date desc ", nativeQuery = true)
	long findWorkTenderByQueryCount(@Param("officeId") Long officeId);

	// History of tender
	/*
	 * @Query(value="Select  " + "w.id as workId,    " +
	 * "w.work_requisition_no,    " + "w.work_name, " + "w.agency_type_id, " +
	 * "mat.agency_type_name_e, " + "wt.tender_cost, " + "wt.contractor_id, " +
	 * "c.name, " + "wrs.status_name_e as workRequestStatus, " + "wt.status, " +
	 * "mts.status_name_e, " + "ads.parent_id, " + "ads.id " +
	 * "from administrative_sanction ads " +
	 * "left join work w on w.id=ads.work_id   " +
	 * "left join work_tender wt on wt.administrative_sanction_id=ads.id " +
	 * "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  " +
	 * "left join contractor c on wt.contractor_id = c.id  " +
	 * "left join mst_request_status wrs on w.work_request_status_id=wrs.id   " +
	 * "left join mst_tender_status mts on mts.id= wt.status " +
	 * "where   w.executive_engineer_office_id=?1  " + //
	 * "and w.work_request_status_id>=5 and w.is_legacy=0  " + "and w.is_legacy=0  "
	 * + "and w.agency_type_id in (1) and w.id=?2 and ads.id!=?3" +
	 * " order by ads.modified_date desc " + "",nativeQuery=true)
	 */
	@Query(value = "Select  " + "w.id as workId,    " + "w.work_requisition_no,    " + "w.work_name, "
			+ "w.agency_type_id, " + "mat.agency_type_name_e, " + "wt.tender_cost, " + "wt.contractor_id, " + "c.name, "
			+ "wrs.status_name_e as workRequestStatus, " + "wt.status, " + "mts.status_name_e, " + "ads.parent_id, "
			+ "ads.id " + "from work_tender wt " + "inner join work w on w.id=wt.work_id   "
			+ "left join administrative_sanction ads on wt.administrative_sanction_id=ads.id "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_tender_status mts on mts.id= wt.status "
			+ "where  (w.status is null or w.status!='Deleted') and  w.executive_engineer_office_id=?1  " +
//			"and w.work_request_status_id>=5 and w.is_legacy=0  " + 
			"and w.is_legacy=0  " + "and w.agency_type_id in (1) and w.id=?2 and ads.id!=?3"
			+ " order by ads.modified_date desc " + "", nativeQuery = true)
	List<Object[]> findWorkTenderHistoryByQuery(
			@Param("executive_engineer_office_id") Long executive_engineer_office_id, @Param("workId") Long workId,
			@Param("admisintrativeSanctionId") Long admisintrativeSanctionId);

	@Query(value = "Select  " + "w.id as workId," + "w.work_name," + "w.executive_engineer_office_id, "
			+ "mo.office_name," + "mwt.work_type_name_e," + "w.work_requisition_no" + " from work w  "
			+ " left join mst_offices mo on w.executive_engineer_office_id=mo.id "
			+ " left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ " where  (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id in (?1) and w.work_request_status_id>1", nativeQuery = true)
	List<Object[]> findByExecutiveEnginerOfficeIds(
			@Param("executive_engineer_office_id") List<Long> executive_engineer_office_id);

	// SQM webservice Rakesh
	// 1st condition
	@Query(value = "SELECT  " + "distinct w.id, " + "w.work_requisition_no, " + "w.work_name, " + "w.work_type_id, "
			+ "wt.work_type_name_e, " + "w.work_sub_type_id, " + "wst.work_sub_type_name_e, "
			+ "w.line_department_id,  " + "ld.line_department_name_e, " + "w.district_id, " + "d.district_name, "
			+ "w.work_status_id, " + "wrs.status_name_e, " + "w.contractor_id, " + "c.name, "
			+ "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "w.executive_engineer_office_id,exeOffice.office_name, sqma.id as sqmAllocationId, "
			+ "sqma.created_date as sqmAllocationDate " + " FROM  "
			+ " work w   " + "  left join mst_district d on d.id=w.district_id "
			+ "  left join mst_work_type wt on w.work_type_id=wt.work_type_id "
			+ "  left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id "
			+ "  left join mst_request_status wrs on w.work_status_id=wrs.id "
			+ "  left join mst_line_department ld on w.line_department_id=ld.line_department_id "
			+ "  left join contractor c on w.contractor_id = c.id left join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id  "
			+ "  left join sqm_allocation sqma on sqma.work_id=w.id "
			+ " where  (w.status is null or w.status!='Deleted') and w.id in(?1) and w.executive_engineer_office_id in(?2) and sqma.enabled=true and sqma.user_id=(?3)  "
	// + "and w.work_request_status_id<5"
			, nativeQuery = true)
	List<Object[]> findBySqmWorkIdsOrExecutiveEnginerOfficeIds(@Param("workIds") List<Long> workIds,
			@Param("executive_engineer_office_id") List<Long> executive_engineer_office_id,
			@Param("userId") Long userId);

	@Query(value = "SELECT  " + "distinct w.id, " + "w.work_requisition_no, " + "w.work_name, " + "w.work_type_id, "
			+ "wt.work_type_name_e, " + "w.work_sub_type_id, " + "wst.work_sub_type_name_e, "
			+ "w.line_department_id,  " + "ld.line_department_name_e, " + "w.district_id, " + "d.district_name, "
			+ "w.work_status_id, " + "wrs.status_name_e, " + "w.contractor_id, " + "c.name, "
			+ "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "w.executive_engineer_office_id,exeOffice.office_name, sqma.id as sqmAllocationId,sqma.modified_date"
			+ " FROM  " + " work w   " + "  left join mst_district d on d.id=w.district_id "
			+ "  left join mst_work_type wt on w.work_type_id=wt.work_type_id "
			+ "  left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id "
			+ "  left join mst_request_status wrs on w.work_status_id=wrs.id "
			+ "  left join mst_line_department ld on w.line_department_id=ld.line_department_id "
			+ "  left join contractor c on w.contractor_id = c.id left join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id  "
			+ "  left join sqm_allocation sqma on sqma.work_id=w.id "
			+ " where  (w.status is null or w.status!='Deleted') and w.id in(?1) and w.executive_engineer_office_id in(?2) and sqma.enabled=true and sqma.inspection_done=1 and sqma.user_id=(?3)"
	// + "and w.work_request_status_id<5"
			, nativeQuery = true)
	List<Object[]> findBySqmWorkIdsOrExecutiveEnginerOfficeIdsCompleted(@Param("workIds") List<Long> workIds,
			@Param("executive_engineer_office_id") List<Long> executive_engineer_office_id,
			@Param("userId") Long userId);

	// 2nd Condition
	@Query(value = "SELECT  " + "distinct w.id, " + "w.work_requisition_no, " + "w.work_name, " + "w.work_type_id, "
			+ "wt.work_type_name_e, " + "w.work_sub_type_id, " + "wst.work_sub_type_name_e, "
			+ "w.line_department_id,  " + "ld.line_department_name_e, " + "w.district_id, " + "d.district_name, "
			+ "w.work_status_id, " + "wrs.status_name_e, " + "w.contractor_id, " + "c.name, "
			+ "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "w.executive_engineer_office_id,exeOffice.office_name " + " FROM  " + " work w   "
			+ "  left join mst_district d on d.id=w.district_id "
			+ "  left join mst_work_type wt on w.work_type_id=wt.work_type_id "
			+ "  left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id "
			+ "  left join mst_request_status wrs on w.work_status_id=wrs.id "
			+ "  left join mst_line_department ld on w.line_department_id=ld.line_department_id "
			+ "  left join contractor c on w.contractor_id = c.id left join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id  "
			+ " where  (w.status is null or w.status!='Deleted') and w.id in(?1)  "
	// + "and w.work_request_status_id<5"
			, nativeQuery = true)
	List<Object[]> findBySqmWorkIds(@Param("workIds") List<Long> workIds);

	// 3nd Condition
	@Query(value = "SELECT  " + "distinct w.id, " + "w.work_requisition_no, " + "w.work_name, " + "w.work_type_id, "
			+ "wt.work_type_name_e, " + "w.work_sub_type_id, " + "wst.work_sub_type_name_e, "
			+ "w.line_department_id,  " + "ld.line_department_name_e, " + "w.district_id, " + "d.district_name, "
			+ "w.work_status_id, " + "wrs.status_name_e, " + "w.contractor_id, " + "c.name, "
			+ "w.work_location_latitude, " + "w.work_location_longitude, "
			+ "w.executive_engineer_office_id,exeOffice.office_name " + " FROM  " + " work w   "
			+ "  left join mst_district d on d.id=w.district_id "
			+ "  left join mst_work_type wt on w.work_type_id=wt.work_type_id "
			+ "  left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id "
			+ "  left join mst_request_status wrs on w.work_status_id=wrs.id "
			+ "  left join mst_line_department ld on w.line_department_id=ld.line_department_id "
			+ "  left join contractor c on w.contractor_id = c.id left join mst_offices exeOffice on  exeOffice.ID =w.executive_engineer_office_id   "
			+ " where (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id in(?1) "
	// + "and w.work_request_status_id<5"
			, nativeQuery = true)
	List<Object[]> findBySqmOfficeIds(@Param("executive_engineer_office_id") List<Long> executive_engineer_office_id

	);

	// findDataForPendingForInspection
	@Query(value = "select w.work_name,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e,w.work_requisition_no,w.id,c.bill_no,c.bill_type,c.bill_amount_without_deduction,u.name as aeName,uee.name as eeName,d.designation,c.modified_date   "
			+ "from work w left join bill c on w.id = c.work_id " + "left join users uee on c.inspected_by_ee = uee.id "
			+ "left join users u on c.inspected_by = u.id " + "left join mst_designation d on u.designation_id = d.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and c.status=3 and w.work_request_status_id in(7,8)  and c.bill_date between :fromYear and :endYear and  "
			+ "case WHEN :exeOfficeId is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId) end "
			+ "and  w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)

	List<Object[]> findDataForPendingForInspection(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("exeOfficeId") String exeOfficeId,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("fromYear") String fromYear,
			@Param("endYear") String endYear, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit);

	@Query(value = "select w.work_name,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e,w.work_requisition_no,w.id,c.bill_no,c.bill_type,c.bill_amount_without_deduction,u.name as aeName,uee.name as eeName,d.designation,c.modified_date   "
			+ "from work w left join bill c on w.id = c.work_id " + "left join users uee on c.inspected_by_ee = uee.id "
			+ "left join users u on c.inspected_by = u.id " + "left join mst_designation d on u.designation_id = d.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and c.status=3 and w.work_request_status_id in(7,8)  and c.bill_date between :fromYear and :endYear and  "

			+ "w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)

	List<Object[]> findDataForPendingForInspectionTotal(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1,
			@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	// findDataForFinalBillPending
	@Query(value = "select w.work_name,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e,w.work_requisition_no,w.id,c.bill_no,c.bill_type,c.bill_amount_without_deduction,u.name as aeName,uee.name as eeName,d.designation,c.modified_date,c.status,c.inspected_by_ee,c.bill_date   "
			+ "from work w left join bill c on w.id = c.work_id " + "left join users uee on c.inspected_by_ee = uee.id "
			+ "left join users u on c.inspected_by = u.id " + "left join mst_designation d on u.designation_id = d.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and c.status in (4,5,6) and c.bill_type='Final' and c.bill_date between :fromYear and :endYear and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and case WHEN :exeOfficeId is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)

	List<Object[]> findDataForFinalBillPending(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("exeOfficeId") String exeOfficeId,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("fromYear") String fromYear,
			@Param("endYear") String endYear, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit);

	// findDataForFinalBillPending
	@Query(value = "select w.work_name,mwt.work_type_name_e,mwst.work_sub_type_name_e,mld.line_department_name_e"
			+ ",md.district_name,mat.agency_type_name_e,mws.work_status_name_e,w.work_requisition_no,w.id,c.bill_no,c.bill_type,c.bill_amount_without_deduction,u.name as aeName,uee.name as eeName,d.designation,c.modified_date,c.status,c.inspected_by_ee,c.bill_date   "
			+ "from work w left join bill c on w.id = c.work_id " + "left join users uee on c.inspected_by_ee = uee.id "
			+ "left join users u on c.inspected_by = u.id " + "left join mst_designation d on u.designation_id = d.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and c.status in (4,5,6) and c.bill_type='Final' and c.bill_date between :fromYear and :endYear and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)

	List<Object[]> findDataForFinalBillPendingTotal(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1,
			@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	@Query(value = "select count(*) " + "from work w left join bill c on w.id = c.work_id "
			+ "left join users uee on c.inspected_by_ee = uee.id " + "left join users u on c.inspected_by = u.id "
			+ "left join mst_designation d on u.designation_id = d.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and c.status=3 and w.work_request_status_id in(7,8) and c.bill_date between :fromYear and :endYear and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and case WHEN :exeOfficeId is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id)  ", nativeQuery = true)

	long findForPendingForInspectionCount(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("exeOfficeId") String exeOfficeId,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("fromYear") String fromYear,
			@Param("endYear") String endYear, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1);

	@Query(value = "select count(*) " + "from work w left join bill c on w.id = c.work_id "
			+ "left join users uee on c.inspected_by_ee = uee.id " + "left join users u on c.inspected_by = u.id "
			+ "left join mst_designation d on u.designation_id = d.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and c.status=3 and w.work_request_status_id in(7,8) and c.bill_date between :fromYear and :endYear and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id)  ", nativeQuery = true)

	long findForPendingForInspectionCountTotal(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1);

	// findForFinalBillPendingCount

	@Query(value = "select count(*) " + "from work w left join bill c on w.id = c.work_id "
			+ "left join users uee on c.inspected_by_ee = uee.id " + "left join users u on c.inspected_by = u.id "
			+ "left join mst_designation d on u.designation_id = d.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and c.status in (4,5,6) and c.bill_type='Final' and c.bill_date between :fromYear and :endYear and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and case WHEN :exeOfficeId is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id)  ", nativeQuery = true)

	long findForFinalBillPendingCount(@Param("ceOfficeId1") Long ceOfficeId1, @Param("seOfficeId1") Long seOfficeId1,
			@Param("exeOfficeId") String exeOfficeId, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1);

	@Query(value = "select count(*) " + "from work w left join bill c on w.id = c.work_id "
			+ "left join users uee on c.inspected_by_ee = uee.id " + "left join users u on c.inspected_by = u.id "
			+ "left join mst_designation d on u.designation_id = d.id "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted')  and c.status in (4,5,6) and c.bill_type='Final' and c.bill_date between :fromYear and :endYear and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id)  ", nativeQuery = true)

	long findForFinalBillPendingCountTotal(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("fromYear") String fromYear, @Param("endYear") String endYear,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1);

	// + "and w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) "
	@Query(value = "select count(*)   " + "from work w "
			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where "
			+ " (w.status is null or w.status!='Deleted') and w.work_request_status_id=:workReqStatusId and w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "
			+ "and case WHEN :exeOfficeId1 is null THEN   ifnull(w.executive_engineer_office_id, 0) = IFNULL(:exeOfficeId1, ifnull(w.executive_engineer_office_id, 0)) when :exeOfficeId1 is not null then FIND_IN_SET(w.executive_engineer_office_id, :exeOfficeId1) end "
			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workStatusId1 is null THEN   ifnull(w.work_status_id, 0) = IFNULL(:workStatusId1, ifnull(w.work_status_id, 0)) when :workStatusId1 is not null then FIND_IN_SET(w.work_status_id, :workStatusId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id)" + " ", nativeQuery = true)
	long findWorkDataForMultiStatusCount(@Param("ceOfficeId1") Long ceOfficeId1, @Param("seOfficeId1") Long seOfficeId1,
			@Param("workReqStatusId") Long workReqStatusId, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("exeOfficeId1") String exeOfficeId1, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workStatusId1") String workStatusId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1);

	// left join payment_table pt on b.id=pt.bill_id newly added for cashbook date
	// as final bill Paid On and pt.cashbook_date instead of b.bill_date
	@Query(value = "select w.work_requisition_no,w.id,cd.work_completed_on,cd.taken_over_on,cd.physical_cc_issued_on,pt.cashbook_date,pcd.dispatch_date,w.work_name as workName,mwt.work_type_name_e  "
			+ "from work w left join cc_details cd on w.id = cd.work_id " + "left  join bill b on w.id = b.work_id  "
			+ "left join payment_table pt on b.id=pt.bill_id "
			+ "left join physical_cc_dispatch_details pcd on w.id = pcd.work_id "

			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where b.id in (select max(id) from bill where work_id=w.id and status not in (8,9)) "
			+ "and pcd.id in(select max(id) from physical_cc_dispatch_details where work_id=w.id) and "
			+ "w.executive_engineer_office_id=:exeOfficeId and w.work_request_status_id =13 and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)

	List<Object[]> findDataForPhysicalCCDispatch(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("exeOfficeId") Long exeOfficeId,
			@Param("executionAgencyId1") Long executionAgencyId1, @Param("lineDepartmentId1") String lineDepartmentId1,
			@Param("accountHeadId1") String accountHeadId1, @Param("workTypeId1") String workTypeId1,
			@Param("workSubTypeId1") String workSubTypeId1, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit);

	// For Total

	@Query(value = "select w.work_requisition_no,w.id,cd.work_completed_on,cd.taken_over_on,cd.physical_cc_issued_on,pt.cashbook_date,pcd.dispatch_date,w.work_name as workName,mwt.work_type_name_e  "
			+ "from work w left join cc_details cd on w.id = cd.work_id " + "left  join bill b on w.id = b.work_id "
			+ "left join payment_table pt on b.id=pt.bill_id "
			+ "left join physical_cc_dispatch_details pcd on w.id = pcd.work_id "

			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where b.id in (select max(id) from bill where work_id=w.id and status not in (8,9)) "
			+ "and pcd.id in(select max(id) from physical_cc_dispatch_details where work_id=w.id) and "
			+ "w.work_request_status_id =13 and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "
			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "

			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) limit :offset,:maxLimit ", nativeQuery = true)

	List<Object[]> findDataForPhysicalCCDispatchForTotal(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1,
			@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	// findForPhysicalCCDispatchCount

	@Query(value = "select count(*) " + "from work w left join cc_details cd on w.id = cd.work_id "
			+ "left  join bill b on w.id = b.work_id "
			+ "left join physical_cc_dispatch_details pcd on w.id = pcd.work_id "

			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where b.id in (select max(id) from bill where work_id=w.id and status not in (8,9)) "
			+ "and pcd.id in(select max(id) from physical_cc_dispatch_details where work_id=w.id) and "
			+ "w.executive_engineer_office_id=:exeOfficeId and w.work_request_status_id  =13 and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) ", nativeQuery = true)

	long findForPhysicalCCDispatchCount(@Param("ceOfficeId1") Long ceOfficeId1, @Param("seOfficeId1") Long seOfficeId1,
			@Param("exeOfficeId") Long exeOfficeId, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1);
	// for total count

	@Query(value = "select count(*) " + "from work w left join cc_details cd on w.id = cd.work_id "
			+ "left  join bill b on w.id = b.work_id "
			+ "left join physical_cc_dispatch_details pcd on w.id = pcd.work_id "

			+ "left join mst_work_type mwt on mwt.work_type_id=w.work_type_id "
			+ "left join mst_work_sub_type mwst on mwst.work_sub_type_id = w.work_sub_type_id "
			+ "left join mst_line_department mld on mld.line_department_id=w.line_department_id "
			+ "left join mst_district md on md.id=w.district_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id=w.work_status_id where b.id in (select max(id) from bill where work_id=w.id and status not in (8,9)) "
			+ "and pcd.id in(select max(id) from physical_cc_dispatch_details where work_id=w.id) and "
			+ "w.work_request_status_id  =13 and "
			+ " w.chief_engineer_office_id = COALESCE(:ceOfficeId1, w.chief_engineer_office_id) and w.superintending_engineer_office_id = COALESCE(:seOfficeId1, w.superintending_engineer_office_id) "

			+ "and case WHEN :lineDepartmentId1 is null THEN   ifnull(w.line_department_id, 0) = IFNULL(:lineDepartmentId1, ifnull(w.line_department_id, 0)) when :lineDepartmentId1 is not null then FIND_IN_SET(w.line_department_id, :lineDepartmentId1) end "
			+ "and case WHEN :accountHeadId1 is null THEN   ifnull(w.account_head, 0) = IFNULL(:accountHeadId1, ifnull(w.account_head, 0)) when :accountHeadId1 is not null then FIND_IN_SET(w.account_head, :accountHeadId1) end "

			+ "and case WHEN :workTypeId1 is null THEN   ifnull(w.work_type_id, 0) = IFNULL(:workTypeId1, ifnull(w.work_type_id, 0)) when :workTypeId1 is not null then FIND_IN_SET(w.work_type_id, :workTypeId1) end "
			+ "and case WHEN :workSubTypeId1 is null THEN   ifnull(w.work_sub_type_id, 0) = IFNULL(:workSubTypeId1, ifnull(w.work_sub_type_id, 0)) when :workSubTypeId1 is not null then FIND_IN_SET(w.work_sub_type_id, :workSubTypeId1) end "

			+ "and w.agency_type_id = COALESCE(:executionAgencyId1, w.agency_type_id) ", nativeQuery = true)

	long findForPhysicalCCDispatchCountForTotal(@Param("ceOfficeId1") Long ceOfficeId1,
			@Param("seOfficeId1") Long seOfficeId1, @Param("executionAgencyId1") Long executionAgencyId1,
			@Param("lineDepartmentId1") String lineDepartmentId1, @Param("accountHeadId1") String accountHeadId1,
			@Param("workTypeId1") String workTypeId1, @Param("workSubTypeId1") String workSubTypeId1);

	List<Work> findByStatusNotOrStatusIsNull(String status);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.assistantEngineer.id = :userId  and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	List<Work> findWorkByAssociatedAssistantEngineer(@Param("userId") Long userId);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.subDivisionalOfficer.id = :userId  and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	List<Work> findWorkByAssociatedSubDivisionalOfficer(@Param("userId") Long userId);

	@Query("from Work w where (w.status is not null or w.status!='Deleted') and w.executiveEngineerOffice.id = :officeId  and ((w.agencyTypeId.agencyTypeId in (1,2,3) and w.workRequestStatusId.id >= 7) )")
	List<Work> findWorkByAssociatedEE(@Param("officeId") Long officeId);
	
	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.subEngineer.id = :userId  and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	List<Work> findWorkByAssociatedSubEngg(@Param("userId") Long userId);

	@Query("from Work w where (w.status is null or w.status != 'Deleted') and w.superintendingEngineerOffice = :superintendingEngineerOffice and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5) OR (w.agencyTypeId.agencyTypeId in (1)   and w.workRequestStatusId.id >= 7))")
	List<Work> findWorkByAssociatedSupdtEngg( @Param("superintendingEngineerOffice") Office superintendingEngineerOffice);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.chiefEngineerOffice = :chiefEngineerOffice  and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )")
	List<Work> findWorkByAssociatedCE(@Param("chiefEngineerOffice") Office chiefEngineerOffice, @Param("districtId") Long districtId);

	
	/*
	 * @Query("from Work w where (w.status is null or w.status!='Deleted') and w.enCOffice.id = :userId and w.enCOffice = :enCOffice and ((w.agencyTypeId.agencyTypeId in (2,3) and w.workRequestStatusId.id >= 5)  OR (w.agencyTypeId.agencyTypeId in (1) and w.workRequestStatusId.id >= 7) )"
	 * ) List<Work> findWorkByEnCOffice(@Param("userId") Long
	 * userId, @Param("enCOffice") Office enCOffice);
	 */
	 

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy "

			+ "and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)"
			/*
			 * +
			 * "and (DATE(w.administrationSanction.administrativeSanctionDate) BETWEEN COALESCE(:fromDate,DATE(w.administrationSanction.administrativeSanctionDate)) AND COALESCE(:toDate,DATE(w.administrationSanction.administrativeSanctionDate))"
			 */
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")
	Page<Work> findDataForWorkFileInAdminWOAD(Pageable pageable, @Param("isLegacy") List isLegacy,
			/* @Param("officeId") Long officeId, */
			@Param("workRequestStatusId") Long id, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId,
			@Param("financialYear") String financialYear
	/*
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate
	 */);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.workRequestStatusId.id != :workRequestStatusId "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and (w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId) or w.lineDepartmentId.lineDepartmentId is null)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and (w.block.blockId=COALESCE( :blockId, w.block.blockId) or w.block.blockId is null)"
			+ "and (w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId) or w.gramPanchayat.gramPanchayatId is null)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null )"
			+ "and (w.contractor.id=COALESCE( :contractorId, w.contractor.id) or w.contractor.id is null)")
	Page<Work> findDataForWorkFileInAdmin(Pageable pageable, @Param("isLegacy") List isLegacy,
			/* @Param("officeId") Long officeId, */
			@Param("workRequestStatusId") Long id, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("contractorId") Long contractorId);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.agencyTypeId.agencyTypeId=COALESCE(:agencyType, w.agencyTypeId.agencyTypeId)  and w.workRequestStatusId.id>1")
	long countByDataForWorkFileInAdmin(@Param("isLegacy") List isLegacy, @Param("agencyType") Long agencyType

	);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy and w.executiveEngineerOffice.id = COALESCE( :officeId, w.executiveEngineerOffice.id) "
			+ "and w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=COALESCE( :lineDepartmentId, w.lineDepartmentId.lineDepartmentId)"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)  "
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) "
			+ "and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null) "
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			+ " and w.financialYear = COALESCE( :financialYear, w.financialYear)" + ")")

	Page<Work> findByIsLegacyAndExecutiveEngineerOfficeIdAndFilteredAndRequisitionFinYear(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("officeId") Long officeId,
			@Param("workNatureId") Long workNatureId, @Param("workTypeId") Long workTypeId,
			@Param("workSubTypeId") Long workSubTypeId, @Param("lineDepartmentId") Long lineDepartmentId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("financialYear") String financialYear);

	@Query("select count(*) from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy = :isLegacy and w.executiveEngineerOffice.id = :officeId and w.financialYear = COALESCE( :financialYear, w.financialYear)")
	long countByIsLegacyAndExecutiveEngineerOfficeIdWithFy(@Param("isLegacy") Short isLegacy,
			@Param("officeId") Long officeId, @Param("financialYear") String financialYear);

	@Query("from Work w where (w.status is null or w.status!='Deleted') and w.isLegacy in :isLegacy  and "
			+ "w.workTypeId.workTypeId=COALESCE( :workTypeId, w.workTypeId.workTypeId ) "
			+ "and (w.workSubTypeId.workSubTypeId=COALESCE( :workSubTypeId, w.workSubTypeId.workSubTypeId) or w.workSubTypeId.workSubTypeId is null ) "
			+ "and w.lineDepartmentId.lineDepartmentId=17"
			+ "and w.accountHead.id=COALESCE( :accountHeadId, w.accountHead.id)"
			+ "and w.agencyTypeId.agencyTypeId=COALESCE( :executionAgencyId, w.agencyTypeId.agencyTypeId)"
			+ "and (w.workStatusId.id=COALESCE( :workStatusId, w.workStatusId.id) or w.workStatusId.id is null)"
			+ "and w.district.districtId=COALESCE( :districtId, w.district.districtId) and w.block.blockId=COALESCE( :blockId, w.block.blockId)"
			+ "and w.gramPanchayat.gramPanchayatId=COALESCE( :gramPanchayatId, w.gramPanchayat.gramPanchayatId)"
			+ "and (w.village.id=COALESCE( :villageId, w.village.id)  or w.village.id is null ) "
			+ "and w.workNatureId.workNatureId = COALESCE(:workNatureId, w.workNatureId.workNatureId)"
			+ "and w.financialYear = COALESCE( :financialYear, w.financialYear)")

	Page<Work> findByIsLegacyAndLineDepartmentIdAndFilteredAndRequisitionWithFy(Pageable pageable,
			@Param("isLegacy") List isLegacy, @Param("workNatureId") Long workNatureId,
			@Param("workTypeId") Long workTypeId, @Param("workSubTypeId") Long workSubTypeId,
			@Param("accountHeadId") Long accountHeadId, @Param("executionAgencyId") Long executionAgencyId,
			@Param("workStatusId") Long workStatusId, @Param("districtId") Long districtId,
			@Param("blockId") Long blockId, @Param("gramPanchayatId") Long gramPanchayatId,
			@Param("villageId") Long villageId, @Param("financialYear") String financialYear);

	@Query(value = "Select  " + "w.id as workId,    " + "w.work_requisition_no,    " + "w.work_name, "
			+ "w.agency_type_id, " + "mat.agency_type_name_e, " + "wt.tender_cost, " + "wt.contractor_id, " + "c.name, "
			+ "wrs.status_name_e as workRequestStatus, " + "wt.status, " + "mts.status_name_e, " + "ads.parent_id,"
			+ "ads.id ,ads.administration_sanction_type_id as AdminTypeId,w.work_request_status_id " + "from "
			+ "(SELECT max(id) as id FROM administrative_sanction  group by work_id  order by created_date desc ) innerTable "
			+ "inner join administrative_sanction ads on ads.id=innerTable.id  "
			+ "left join work w on w.id=ads.work_id   "
			+ "left join work_tender wt on wt.administrative_sanction_id=ads.id "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_tender_status mts on mts.id= wt.status "
			+ "where  (w.status is null or w.status!='Deleted') and ads.administration_sanction_type_id=2  " +
//				"and w.work_request_status_id>=5 and w.is_legacy=0 " + 
			"and w.is_legacy=0 " + "and w.agency_type_id in (1)  "
			+ "order by ads.modified_date desc limit ?1,?2 ", nativeQuery = true)
	List<Object[]> findWorkTenderByQueryEnc(@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	@Query(value = "select count(Distinct w.id ) from  (SELECT max(id) as id FROM administrative_sanction  group by work_id  order by created_date desc) innerTable "
			+ "inner join administrative_sanction ads on ads.id=innerTable.id  "
			+ "left join work w on w.id=ads.work_id   "
			+ "left join work_tender wt on wt.administrative_sanction_id=ads.id "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_tender_status mts on mts.id= wt.status "
			+ "where  (w.status is null or w.status!='Deleted') and ads.administration_sanction_type_id=2  " +
//				"and w.work_request_status_id>=5 and w.is_legacy=0 " + 
			" and w.is_legacy=0 " + "and w.agency_type_id in (1) "
			+ "order by wt.modified_date desc ", nativeQuery = true)
	long findWorkTenderByQueryCountEE();

	@Query(value = "Select  " + "w.id as workId,    " + "w.work_requisition_no,    " + "w.work_name, "
			+ "w.agency_type_id, " + "mat.agency_type_name_e, " + "wt.tender_cost, " + "wt.contractor_id, " + "c.name, "
			+ "wrs.status_name_e as workRequestStatus, " + "wt.status, " + "mts.status_name_e, " + "ads.parent_id, "
			+ "ads.id " + "from work_tender wt " + "inner join work w on w.id=wt.work_id   "
			+ "left join administrative_sanction ads on wt.administrative_sanction_id=ads.id "
			+ "left join mst_agency_type mat on w.agency_type_id = mat.agency_type_id  "
			+ "left join contractor c on wt.contractor_id = c.id  "
			+ "left join mst_request_status wrs on w.work_request_status_id=wrs.id   "
			+ "left join mst_tender_status mts on mts.id= wt.status "
			+ "where  (w.status is null or w.status!='Deleted') " +
//				"and w.work_request_status_id>=5 and w.is_legacy=0  " + 
			"and w.is_legacy=0  " + "and w.agency_type_id in (1) and w.id=?1 and ads.id!=?2"
			+ " order by ads.modified_date desc " + "", nativeQuery = true)
	List<Object[]> findWorkTenderHistoryByQueryEnc(@Param("workId") Long workId,
			@Param("admisintrativeSanctionId") Long admisintrativeSanctionId);

	Page<Work> findByBillingFlag(Pageable pageable, short s);

	Page<Work> findByBillingFlagAndChiefEngineerOffice(Pageable pageable, short s, Office office);

}
