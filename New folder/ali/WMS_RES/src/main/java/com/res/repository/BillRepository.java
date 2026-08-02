package com.res.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.Bill;
import com.res.entity.Contractor;
import com.res.entity.Office;
import com.res.entity.Users;
import com.res.entity.Work;

public interface BillRepository  extends JpaRepository<Bill, Long>, CrudRepository<Bill, Long> {
    
	
	/*Page<Bill> findByStatusNot(Pageable pageable, String status);
	
	long countByStatusNotAndWorkExecutiveEngineerOffice(MasterBillStatus status, Office executiveEngineerOffice);
	
	long countByStatusNotAndWorkAssistantEngineer(MasterBillStatus status, Users assistantEngineer);
	
	long countByStatusNotAndWorkSubEngineer(MasterBillStatus status, Users subEngineer);*/
	
	/*@Query("from Bill b where (b.billNo like %:billNo% or b.work.workName like %:workName% ) and (DATE(b.billDate) between :billDateFrom and :billDateTo) "
			+ " and b.status = :status")
	Page<Bill> findByBillNoContainingOrWorkNameContainingAndBillDateBetweenAndStatus(Pageable pageable, 
			@Param("billNo")String billNo, @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")String status);*/
	
	@Query("from Bill b where ((:billNo is null or b.billNo like %:billNo%) or (:workName is null or b.work.workName like %:workName% )) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.executiveEngineerOffice = :executiveEngineerOffice and b.toShow = 1")
	Page<Bill> findBillsByExecutiveEngineerOffice(Pageable pageable, 
			@Param("billNo")String billNo, @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("executiveEngineerOffice")Office executiveEngineerOffice);
	
	/*@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1)  OR (b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId not in (1, 8)) and b.toShow = 1")
	Page<Bill> findBillsEE(Pageable pageable, @Param("createdBy") String createdBy,  @Param("executiveEngineerOffice") Office executiveEngineerOffice);
	*/
	/*@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status = COALESCE(:status, b.status))  OR  ((b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.executiveEngineerOffice = :executiveEngineerOffice) and b.status.statusId not in (1, 8) and b.toShow = 1")
	Page<Bill> findBillsByExecutiveEngineerOfficeForEE(Pageable pageable, @Param("createdBy") String createdBy, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("executiveEngineerOffice")Office executiveEngineerOffice);*/
	
	
	/*@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status = COALESCE(:status, b.status) and (b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))))  OR  ((b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.executiveEngineerOffice = :executiveEngineerOffice)  and b.toShow = 1")
	Page<Bill> findBillsByExecutiveEngineerOfficeForEE(Pageable pageable, @Param("createdBy") String createdBy, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("executiveEngineerOffice")Office executiveEngineerOffice);*/
	
	@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status = COALESCE(:status, b.status) and (b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))))  OR  ((b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId!=1)  and b.toShow = 1")
	Page<Bill> findBillsByExecutiveEngineerOfficeForEE(Pageable pageable, @Param("createdBy") String createdBy, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("executiveEngineerOffice")Office executiveEngineerOffice);
	
	@Query("from Bill b where (b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	Page<Bill> findBillsByExecutiveEngineerOfficeForEEPayment(Pageable pageable, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("executiveEngineerOffice")Office executiveEngineerOffice);
	
	
	@Query("from Bill b where ((:billNo is null or b.billNo like %:billNo%) or (:workName is null or b.work.workName like %:workName% )) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.assistantEngineer = :assistantEngineer and b.toShow = 1")
	Page<Bill> findBillsByAssistantEngineer(Pageable pageable, 
			@Param("billNo")String billNo, @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("assistantEngineer") Users assistantEngineer);
	
	@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status = COALESCE(:status, b.status) and (b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))))  OR ((b.work.workName like %:workName% or :workName is null) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.assistantEngineer = :assistantEngineer and b.status.statusId!=1) and b.toShow = 1")
	Page<Bill> findBillsByAssistantEngineerForAE(Pageable pageable,@Param("createdBy") String createdBy, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("assistantEngineer")Users assistantEngineer);
	
	@Query("from Bill b where ((:billNo is null or b.billNo like %:billNo%) or (:workName is null or b.work.workName like %:workName% )) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.subDivisionalOfficer = :subDivisionalOfficer and b.toShow = 1")
	Page<Bill> findBillsBySubDivisionalOfficer(Pageable pageable, 
			@Param("billNo")String billNo, @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("subDivisionalOfficer") Users subDivisionalOfficer);
	
	@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status = COALESCE(:status, b.status) and (b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))))  OR ((b.work.workName like %:workName% or :workName is null) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.subDivisionalOfficer = :subDivisionalOfficer and b.status.statusId!=1) and b.toShow = 1")
	Page<Bill> findBillsBySubDivisionalOfficerForSDO(Pageable pageable,@Param("createdBy") String createdBy, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("subDivisionalOfficer")Users subDivisionalOfficer);
	
	//findBillsByContractorForSearch
	
	@Query("from Bill b left join b.work.workTender wt where (b.createdBy= :createdBy and b.toShow = 1 and b.status = COALESCE(:status, b.status) and (b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))))  OR ((b.work.workName like %:workName% or :workName is null) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and (b.work.contractor = :contractor or wt.contractorId=:contractor) and b.status.statusId!=1) and b.toShow = 1")
	Page<Bill> findBillsByContractorForSearch(Pageable pageable,@Param("createdBy") String createdBy, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("contractor")Contractor contractor);

	
	@Query("from Bill b where (b.work.workName like %:workName% or :workName is null) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.assistantEngineer = :assistantEngineer and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and  b.toShow = 1")
	Page<Bill> findBillsByAssistantEngineerForAEForPayment(Pageable pageable, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("assistantEngineer") Users assistantEngineer);
	
	@Query("from Bill b where (b.work.workName like %:workName% or :workName is null) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.subDivisionalOfficer = :subDivisionalOfficer and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and  b.toShow = 1")
	Page<Bill> findBillsBySubDivisionalOfficerForSDOForPayment(Pageable pageable, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("subDivisionalOfficer") Users subDivisionalOfficer);

	
	@Query("from Bill b where ((:billNo is null or b.billNo like %:billNo%) or (:workName is null or b.work.workName like %:workName% )) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.subEngineer = :subEngineer and b.toShow = 1")
	Page<Bill> findBillsBySubEngineer(Pageable pageable, 
			@Param("billNo")String billNo, @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("subEngineer") Users subEngineer);
	
	
	
/*	@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status = COALESCE(:status, b.status) and (b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate)))) OR ((b.work.workName like %:workName% or :workName is null) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.subEngineer = :subEngineer and b.status.statusId!=1) and b.toShow = 1")
	Page<Bill> findBillsBySubEngineerForSUBE(Pageable pageable,@Param("createdBy") String createdBy,  
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("subEngineer") Users subEngineer);
	*/
	
	@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status = COALESCE(:status, b.status) and (b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate)))) OR ((b.work.workName like %:workName% or :workName is null) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.subEngineer = :subEngineer and b.status.statusId!=1) OR ((b.work.workName like %:workName% or :workName is null) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) and b.status = COALESCE(:status, b.status) and b.measurementBy = :subEngineer and b.status.statusId!=1) and b.toShow = 1")
	Page<Bill> findBillsBySubEngineerForSUBE(Pageable pageable,@Param("createdBy") String createdBy,  
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("subEngineer") Users subEngineer);
	
	
	@Query("from Bill b where (b.work.workName like %:workName% or :workName is null) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and  b.work.subEngineer = :subEngineer and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	Page<Bill> findBillsBySubEngineerForSUBEForPayment(Pageable pageable, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("subEngineer") Users subEngineer);
	
	//richa
	
	@Query("from Bill b where ((:billNo is null or b.billNo like %:billNo%) or (:workName is null or b.work.workName like %:workName% )) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status.statusId >= :status and b.work.executiveEngineerOffice = :executiveEngineerOffice and b.toShow = 1")
	Page<Bill> findBillsByExecutiveEngineerOffice1(Pageable pageable, 
			@Param("billNo")String billNo, @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("executiveEngineerOffice")Office executiveEngineerOffice);
	
	@Query("from Bill b where b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	Page<Bill> findPaymentBillsEE(Pageable pageable , @Param("executiveEngineerOffice") Office executiveEngineerOffice);
	
	@Query("select count(*) from Bill b where b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	long countPaymentBillsEE(@Param("executiveEngineerOffice") Office executiveEngineerOffice);
	
	@Query("select count(*) from Bill b where b.work.assistantEngineer = :assistantEngineer and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	long countPaymentBillsAE(@Param("assistantEngineer") Users assistantEngineer);
	
	@Query("from Bill b where b.work.assistantEngineer = :assistantEngineer and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	Page<Bill> findPaymentBillsAE(Pageable pageable, @Param("assistantEngineer") Users assistantEngineer);
	
	@Query("select count(*) from Bill b where b.work.subDivisionalOfficer = :subDivisionalOfficer and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	long countPaymentBillsSDO(@Param("subDivisionalOfficer") Users subDivisionalOfficer);
	
	@Query("from Bill b where b.work.subDivisionalOfficer = :subDivisionalOfficer and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	Page<Bill> findPaymentBillsSDO(Pageable pageable, @Param("subDivisionalOfficer") Users subDivisionalOfficer);
	
	
	@Query("from Bill b where b.work.subEngineer = :subEngineer and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	Page<Bill> findPaymentBillsSubEng(Pageable pageable, @Param("subEngineer") Users subEngineer);
	
	@Query("select count(*) from Bill b where b.work.subEngineer = :subEngineer and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	long countPaymentBillsSubEng(@Param("subEngineer") Users subEngineer);
	
	
	/*@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1)  OR (b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId not in (1, 8)) and b.toShow = 1")
	Page<Bill> findBillsEE(Pageable pageable, @Param("createdBy") String createdBy,  @Param("executiveEngineerOffice") Office executiveEngineerOffice);*/
	
	/*@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1)  OR (b.work.executiveEngineerOffice = :executiveEngineerOffice) and b.toShow = 1")
	Page<Bill> findBillsEE(Pageable pageable, @Param("createdBy") String createdBy,  @Param("executiveEngineerOffice") Office executiveEngineerOffice);*/
	
	@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR (b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId!=1 and b.status.statusId!=8) and b.toShow = 1 and b.status.statusId!=8 ")
	Page<Bill> findBillsEE(Pageable pageable, @Param("createdBy") String createdBy,  @Param("executiveEngineerOffice") Office executiveEngineerOffice);
	
	
	@Query("select count(*) from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR (b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId!=1 and b.status.statusId!=8 ) and b.toShow = 1 and b.status.statusId!=8 ")
	long countBillsEE(@Param("createdBy") String createdBy,  @Param("executiveEngineerOffice") Office executiveEngineerOffice);
	
	/*@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1)  OR (b.work.assistantEngineer = :assistantEngineer and b.status.statusId not in (1, 8)) and b.toShow = 1")
	Page<Bill> findBillsAE(Pageable pageable, @Param("createdBy") String createdBy,  @Param("assistantEngineer") Users assistantEngineer);*/
	
	@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR (b.work.assistantEngineer = :assistantEngineer and b.status.statusId!=1 and b.status.statusId!=8) and b.toShow = 1 and b.status.statusId!=8")
	Page<Bill> findBillsAE(Pageable pageable, @Param("createdBy") String createdBy,  @Param("assistantEngineer") Users assistantEngineer);
	
	@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR (b.work.subDivisionalOfficer = :subDivisionalOfficer and b.status.statusId!=1 and b.status.statusId!=8) and b.toShow = 1 and b.status.statusId!=8")
	Page<Bill> findBillsSDO(Pageable pageable, @Param("createdBy") String createdBy,  @Param("subDivisionalOfficer") Users subDivisionalOfficer);
	
	@Query("from Bill b left join b.work.workTender wt where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR ((b.work.contractor = :contractor or wt.contractorId=:contractor) and b.status.statusId!=1 and b.status.statusId!=8) and b.toShow = 1 and b.status.statusId!=8")
	Page<Bill> findBillsContractor(Pageable pageable, @Param("createdBy") String createdBy,  @Param("contractor") Contractor contractor);
	
	@Query("select count(*) from Bill b left join b.work.workTender wt where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR ((b.work.contractor = :contractor or wt.contractorId=:contractor) and b.status.statusId!=1 and b.status.statusId!=8) and b.toShow = 1 and b.status.statusId!=8")
	long countBillsContractor(@Param("createdBy") String createdBy,  @Param("contractor") Contractor contractor);
	
	@Query("select count(*) from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR (b.work.assistantEngineer = :assistantEngineer and b.status.statusId!=1 and b.status.statusId!=8) and b.toShow = 1 and b.status.statusId!=8")
	long countBillsAE(@Param("createdBy") String createdBy,  @Param("assistantEngineer") Users assistantEngineer);
	
	@Query("select count(*) from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR (b.work.subDivisionalOfficer = :subDivisionalOfficer and b.status.statusId!=1 and b.status.statusId!=8) and b.toShow = 1 and b.status.statusId!=8")
	long countBillsSDO(@Param("createdBy") String createdBy,  @Param("subDivisionalOfficer") Users subDivisionalOfficer);
	
	/*@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1)  OR (b.work.subEngineer = :subEngineer and b.status.statusId not in (1, 8))   and b.toShow = 1")
	Page<Bill> findBillsSubEng(Pageable pageable, @Param("createdBy") String createdBy,  @Param("subEngineer") Users subEngineer );*/
	
	
	@Query("from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR (b.work.subEngineer = :subEngineer and b.status.statusId!=1 and b.status.statusId!=8) or (b.measurementBy = :subEngineer and b.status.statusId!=1 and b.status.statusId!=8)    and b.toShow = 1 and b.status.statusId!=8")
	Page<Bill> findBillsSubEng(Pageable pageable, @Param("createdBy") String createdBy,  @Param("subEngineer") Users subEngineer );
	
	/*@Query("select count(*) from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR (b.work.subEngineer = :subEngineer and b.status.statusId!=1 and b.status.statusId!=8)   and b.toShow = 1 and b.status.statusId!=8")
	long countBillsSubEng(@Param("createdBy") String createdBy,  @Param("subEngineer") Users subEngineer );*/
	
	//new 
	
	@Query("select count(*) from Bill b where (b.createdBy= :createdBy and b.toShow = 1 and b.status.statusId!=8)  OR (b.work.subEngineer = :subEngineer and b.status.statusId!=1 and b.status.statusId!=8) OR (b.measurementBy = :subEngineer and b.status.statusId!=1 and b.status.statusId!=8)   and b.toShow = 1 and b.status.statusId!=8")
	long countBillsSubEng(@Param("createdBy") String createdBy,  @Param("subEngineer") Users subEngineer );
	
	
	@Query("from Bill b where " +
		       "(b.inspectedBy = :inspectedBy " +
		       " or b.inspectedBySubEngg = :inspectedBy " +
		       " or (b.inspectedBySDO = :inspectedBy and b.toShow = true)) " +
		       " OR (b.work.executiveEngineerOffice = :executiveEngineerOffice " +
		       " and b.status.statusId >= 3 and b.toShow = true) " +
		       " OR (b.inspectedByEE = :inspectedBy and b.toShow = true) " +
		       " order by b.modifiedDate desc")
	Page<Bill> findInspectionBillsEE(Pageable pageable, @Param("inspectedBy") Users inspectedBy,  @Param("executiveEngineerOffice") Office executiveEngineerOffice);
	
	@Query("select count(*) from Bill b where (b.inspectedBy= :inspectedBy and b.toShow = 1)  OR (b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId >=3 and b.toShow = 1) OR (b.inspectedByEE= :inspectedBy and b.toShow = 1)")
	long countInspectionBillsEE(@Param("inspectedBy") Users inspectedBy,  @Param("executiveEngineerOffice") Office executiveEngineerOffice);
	
	@Query("from Bill b where (b.inspectedBy= :inspectedBy and b.toShow = 1 and b.status = COALESCE(:status, b.status) and (b.work.workName like %:workName% or :workName is null ) "
			+ "and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))))  OR  ((b.work.workName like %:workName% or :workName is null ) "
			+ "and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) "
			+ " and b.status = COALESCE(:status, b.status) and b.work.executiveEngineerOffice = :executiveEngineerOffice) and b.status.statusId >=3 and b.toShow = 1) or (b.inspectedByEE= :inspectedBy and b.toShow = 1 and b.status = COALESCE(:status, b.status) and (b.work.workName like %:workName% or :workName is null ) and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))))")
	Page<Bill> findInspectionBillsByExecutiveEngineerOfficeForEE(Pageable pageable,  @Param("inspectedBy") Users inspectedBy, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status, @Param("executiveEngineerOffice")Office executiveEngineerOffice);
	
	
	@Query("from Bill b where  b.inspectedBy= :inspectedBy and b.status.statusId >= 3 and b.status.statusId not in (8) and b.toShow = 1 and (b.work.workName like %:workName% or :workName is null and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) and b.status = COALESCE(:status, b.status))")
	Page<Bill> findInspectionBillsAEWithFilter(Pageable pageable,  @Param("inspectedBy") Users inspectedBy, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status );
	
	@Query("from Bill b where  b.inspectedBy= :inspectedBy and b.status.statusId >= 3 and b.status.statusId not in (8) and b.toShow = 1 and (b.work.workName like %:workName% or :workName is null and (DATE(b.billDate) between COALESCE(:billDateFrom, DATE(b.billDate)) and COALESCE(:billDateTo, DATE(b.billDate))) and b.status = COALESCE(:status, b.status))")
	Page<Bill> findInspectionBillsSubEWithFilter(Pageable pageable,  @Param("inspectedBy") Users inspectedBy, 
			 @Param("workName")String workName, @Param("billDateFrom")Date billDateFrom, @Param("billDateTo")Date billDateTo, 
			@Param("status")Long status );
	
	
	@Query("from Bill b where  b.inspectedBy= :inspectedBy and b.status.statusId >= 3 and b.status.statusId not in (8) and b.toShow = 1")
	Page<Bill> findInspectionBillsAE(Pageable pageable, @Param("inspectedBy") Users inspectedBy );
	
	
	@Query("select count(*) from Bill b where  b.inspectedBy = :inspectedBy and b.status.statusId >= 3 and b.status.statusId not in (8) and b.toShow = 1")
	long countInspectionBillsAE(@Param("inspectedBy") Users inspectedBy );
	
	@Query("from Bill b where  b.inspectedBySubEngg = :inspectedBy and b.status.statusId  >=3 and b.status.statusId not in (8) and b.toShow = 1")
	Page<Bill> findInspectionBillsSubEng(Pageable pageable, @Param("inspectedBy") Users inspectedBy );
	
	@Query("select count(*) from Bill b where  b.inspectedBySubEngg = :inspectedBy and b.status.statusId  >=3 and b.status.statusId not in (8) and b.toShow = 1")
	long countInspectionBillsSubEng(@Param("inspectedBy") Users inspectedBy );
	
	@Query("from Bill b where  b.inspectedBySDO = :inspectedBy and b.status.statusId  >=3 and b.status.statusId not in (8) and b.toShow = 1")
	Page<Bill> findInspectionBillsSDO(Pageable pageable, @Param("inspectedBy") Users inspectedBy );
	
	@Query("select count(*) from Bill b where  b.inspectedBySDO = :inspectedBy and b.status.statusId  >=3 and b.status.statusId not in (8) and b.toShow = 1")
	long countInspectionBillsSDO(@Param("inspectedBy") Users inspectedBy );
	
	
	List<Bill> findByWorkAndStatusStatusIdNotIn(Work work, Long status);
	
	List<Bill> findByWorkAndStatusStatusIdNotIn(Work work, List<Long> status);
	
	List<Bill> findByStatusStatusIdNotIn(List<Long> status);
	
	List<Bill> findByWorkAndBillTypeAndStatusStatusIdNotIn(Work work, String billType, List<Long> status);
	
	long countByWorkAndBillTypeAndStatusStatusIdNotIn(Work work, String billType, Long status);
	
	List<Bill> findByWorkAndBillTypeAndStatusStatusId(Work work, String billType, Long status);
	
	List<Bill> findByWorkAndBillTypeAndStatusStatusIdIn(Work work, String billType, List<Long> status);
	
	List<Bill> findByWork(Work work);
	
	//Not required
	//List<Bill> findByWorkAndToShow(Work work, boolean  toshow);
	
	List<Bill> findByWorkAndToShowAndStatusStatusIdNotIn(Work work, boolean  toshow, Long status);
	
	@Query("from Bill b where b.inspectedBy= :inspectedBy and b.status.statusId in (3) and b.inspectedByDatetime is null and b.toShow = 1")
	List<Bill> fetchInspectionPendingBillsForRES(@Param("inspectedBy") Users inspectedBy);
	
	@Query("from Bill b where b.inspectedByEE= :inspectedByEE and b.status.statusId in (3) and b.billType = :billType and b.inspectedByEeDatetime is null and b.toShow = 1")
	List<Bill> fetchInspectionPendingBillsForRESEe(@Param("inspectedByEE") Users inspectedByEE ,  @Param("billType") String billType);
	@Query("from Bill b where b.inspectedByEE= :inspectedByEE and b.inspectedByEE.isOIC = 1 and b.billType = :billType and b.status.statusId in (3) and b.inspectedByEeDatetime is null and b.toShow = 1")
	List<Bill> fetchInspectionPendingBillsForRESEeOIC(@Param("inspectedByEE") Users inspectedByEE , @Param("billType") String billType);
	
	@Query("from Bill b where b.inspectedBySubEngg= :inspectedBySubEngg  and b.billType = :billType and b.status.statusId in (3) and b.inspectedBySubEnggDatetime is null and b.toShow = 1")
	List<Bill> fetchInspectionPendingBillsForRESSubEngg(@Param("inspectedBySubEngg") Users inspectedBySubEngg,  @Param("billType")String billType);
	@Query("from Bill b where b.inspectedBySubEngg= :inspectedBySubEngg and b.inspectedBySubEngg.isOIC = 1 and b.billType = :billType and b.status.statusId in (3) and b.inspectedBySubEnggDatetime is null and b.toShow = 1")
	List<Bill> fetchInspectionBillsForRESSubEnggOIC(@Param("inspectedBySubEngg") Users inspectedBySubEngg, @Param("billType") String billType);
	
	@Query("from Bill b where b.inspectedBySDO= :inspectedBySDO and b.status.statusId in (3) and b.billType = :billType and b.inspectedBySdoDatetime is null and b.toShow = 1")
	List<Bill> fetchInspectionPendingBillsForRESSdo(@Param("inspectedBySDO") Users inspectedBySDO, @Param("billType") String billType);
	@Query("from Bill b where b.inspectedBySDO= :inspectedBySDO and b.inspectedBySDO.isOIC = 1 and b.billType = :billType and  b.status.statusId in (3) and b.inspectedBySdoDatetime is null and b.toShow = 1")
	List<Bill> fetchInspectionPendingBillsForRESSdoOIC(@Param("inspectedBySDO") Users inspectedBySDO , @Param("billType") String billType);
	
	@Query("from Bill b where  b.inspectedBy= :inspectedBy and   b.inspectedByDatetime is not null and  b.status.statusId not in (8,9)  and b.toShow = 1")
	List<Bill> fetchInspectionCompletedBillsForRES(@Param("inspectedBy") Users inspectedBy );
	
	@Query("from Bill b where  b.inspectedByEE= :inspectedByEE and b.inspectedByEeDatetime is not null and b.status.statusId not in (8,9) and b.toShow = 1")
	List<Bill> fetchInspectionCompletedBillsForRESEe(@Param("inspectedByEE") Users inspectedByEE );
	
	@Query("from Bill b where b.work.agencyTypeId.agencyTypeId = :agencyTypeId and  b.work.workTypeId.workTypeId = :workTypeId and b.work.subEngineer= :subEngineer and b.status.statusId in (4) and b.status.statusId not in (8,9) and b.toShow = 1")
	List<Bill> fetchInspectionPendingBillsForGP( @Param("agencyTypeId") Long agencyTypeId, @Param("workTypeId") Long workTypeId, @Param("subEngineer") Users subEngineer);
	
	@Query("from Bill b where b.work.agencyTypeId.agencyTypeId = :agencyTypeId and b.work.subEngineer= :subEngineer and (b.status.statusId >= 5 and b.status.statusId not in (8,9)) and b.toShow = 1")
	List<Bill> fetchInspectionCompletedBillsForGP( @Param("agencyTypeId") Long agencyTypeId,   @Param("subEngineer") Users subEngineer);

	List<Bill> findByWorkAndToShowAndStatusStatusIdNotInAndStatusStatusIdNotIn(Work work, boolean b,
			Long statusFinalBillRejectedId, Long statusDeletedId);
		//Rakesh Working
		@Query("from Bill b where b.measurementBy= :measurementBy and b.status.statusId in (11) and b.inspectedByDatetime is null and b.toShow = 1")
		List<Bill> fetchInspectionPendingBillsForRESSubEng(@Param("measurementBy") Users measurementBy);
	

		List<Bill> findByWorkAndStatusStatusIdOrderByBillDateDesc(Work entity, Long statusPaymentCompletedId);
		
		List<Bill> findByWorkAndStatusStatusIdNotInOrderByBillDateDesc(Work work, List<Long> status);
//		@Query("from Bill b where  b.status = :statusPaymentCompletedId order by STR_TO_DATE(progress_entry_date,'%d-%m-%Y')  desc")
//		List<Bill> findByWorkAndStatusStatusIdOrderByBillDateDesc(@Param("statusPaymentCompletedId") Long statusPaymentCompletedId);

}
