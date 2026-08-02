package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.Bill;
import com.res.entity.Office;
import com.res.entity.TechnicalSanction;
import com.res.entity.TechnicalSanctionType;
import com.res.entity.TechnicalStatus;
import com.res.entity.Work;
import com.res.entity.WorkEstimation;

public interface TechnicalSanctionRepository  extends JpaRepository<TechnicalSanction, Long> {
	TechnicalSanction findById(Long id);
    
	@Query("from TechnicalSanction t where t.work = :work")
	TechnicalSanction findByWork(@Param("work") Work work);
	
	@Query("from TechnicalSanction t where t.work.id = :workId order by created_date DESC")
 	List<TechnicalSanction> findAllTSByWorkId(@Param("workId") Long workId);
	
	@Query("from TechnicalSanction t where t.work = :work order by created_date DESC")
 	List<TechnicalSanction> findAllTSByWork(@Param("work") Work work);
	
	Page<TechnicalSanction> findByTechnicalStatus(Pageable pageable, TechnicalStatus status);
	
	Page<TechnicalSanction> findByTechnicalStatusAndWorkExecutiveEngineerOfficeId(Pageable pageable, TechnicalStatus status, Long Id);
	
	//Rakesh Working
	@Query(value="Select  " + 
			"w.id as workId,    " + 
			"w.work_requisition_no,    " + 
			"w.work_name,    " + 
			"w.work_type_id,    " + 
			"wt.work_type_name_e,    " + 
			"w.work_sub_type_id,    " + 
			"wst.work_sub_type_name_e,    " + 
			"w.line_department_id,     " + 
			"ld.line_department_name_e,    " + 
			"w.district_id,   " + 
			"d.district_name, "+
			"wrs.status_name_e as workRequestStatus, " + 
//			"#ads.administrative_sanction_amount as technicalSanctionAmount, " + 
			"we1.technical_sanction_amount, " + 
			"ads.administration_sanction_type_id, " + 
			"madst.administration_sanction_type, " + 
			"mts.id  as techincalStatusId,      " + 
//			"#tst.technical_sanction_type_id,      " + 
//			"#tst.technical_sanction_type, " + 
			"ads.administrative_sanction_status,  " + 

		    "wrs.id as workRequestStatusId,"+
		   
		    "we1.id as technicalSanctionId, " + 
		//    " ads.parent_id " + we1.parent_id
	        " we1.parent_id,w.agency_type_id  " + 
			"from " + 
			"(SELECT max(id) as id FROM technical_sanction group by work_id  order by created_date desc  ) innerTable " + 
			"inner join technical_sanction we1 on we1.id=innerTable.id  " + 
			"left join work w on w.id=we1.work_id   " + 
			" left join mst_district d on d.id=w.district_id    " + 
			"			 			 left join mst_work_type wt on w.work_type_id=wt.work_type_id    " + 
			"			 			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id    " + 
			"			 			 left join mst_request_status wrs on w.work_request_status_id=wrs.id    " + 
			"			 			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"                        left join administrative_sanction ads on ads.technical_sanction_id=we1.id " + 
			"                        left join mst_administration_sanction_type madst on ads.administration_sanction_type_id=madst.administration_sanction_type_id " + 
			"						 left join mst_technical_status mts on we1.technical_sanction_status=mts.id   " + 
			"                        left join mst_technical_sanction_type tst on we1.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"                         " + 
//			"                       #  where w.id=18478 " + 
			"                        where (w.status is null or w.status!='Deleted') and mts.id=?1  and w.executive_engineer_office_id=?2    " + 
			"			             " + 
			" order by w.id desc limit ?3 ,?4",nativeQuery=true)
	List<Object[]> findByTechnicalStatusAndWorkExecutiveEngineerOfficeIdByQuery(@Param("status") Long status, @Param("Id")Long Id,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit);
	
	@Query("from Bill b where b.work.executiveEngineerOffice = :executiveEngineerOffice and b.status.statusId >= 4 and b.status.statusId not in (8, 9) and b.toShow = 1")
	Page<Bill> findPaymentBillsEE(Pageable pageable , @Param("executiveEngineerOffice") Office executiveEngineerOffice);
	
	long countByTechnicalStatus(TechnicalStatus status);

	long countByTechnicalStatusAndWorkExecutiveEngineerOfficeId(TechnicalStatus status, Long Id);
	
	@Query("from TechnicalSanction t where t.workEstimation = :workEstimationId")
	TechnicalSanction findByWorkEstimateId(@Param("workEstimationId") WorkEstimation workEstimationId);

	

	//Count Queries
	@Query(value="select count(distinct w.id) " + 
			"                        " + 
			"from " + 
			"(SELECT max(id) as id FROM technical_sanction group by work_id  order by created_date desc  ) innerTable " + 
			"inner join technical_sanction we1 on we1.id=innerTable.id  " + 
			"left join work w on w.id=we1.work_id   " + 
			" left join mst_district d on d.id=w.district_id    " + 
			"			 			 left join mst_work_type wt on w.work_type_id=wt.work_type_id    " + 
			"			 			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id    " + 
			"			 			 left join mst_request_status wrs on w.work_request_status_id=wrs.id    " + 
			"			 			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"                         left join administrative_sanction ads on ads.technical_sanction_id=we1.id " + 
			"                         left join mst_administration_sanction_type madst on ads.administration_sanction_type_id=madst.administration_sanction_type_id " + 
			"						 left join mst_technical_status mts on we1.technical_sanction_status=mts.id   " + 
			"                         left join mst_technical_sanction_type tst on we1.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"                         " + 
//			"                       #  where w.id=18478 " + 
			"                        where (w.status is null or w.status!='Deleted') and mts.id=?1  and w.executive_engineer_office_id=?2 ",nativeQuery=true)
	long findByTechnicalStatusAndWorkExecutiveEngineerOfficeIdByQueryCount(@Param("status") Long status, @Param("Id")Long Id);
	
	//Rakesh Working
	@Query(value="Select  " + 
			"w.id as workId,    " + 
			"w.work_requisition_no,    " + 
			"w.work_name,    " + 
			"w.work_type_id,    " + 
			"wt.work_type_name_e,    " + 
			"w.work_sub_type_id,    " + 
			"wst.work_sub_type_name_e,    " + 
			"w.line_department_id,     " + 
			"ld.line_department_name_e,    " + 
			"w.district_id,   " + 
			"d.district_name, "+
			"wrs.status_name_e as workRequestStatus, " + 
//			"#ads.administrative_sanction_amount as technicalSanctionAmount, " + 
			"we1.technical_sanction_amount, " + 
			"ads.administration_sanction_type_id, " + 
			"madst.administration_sanction_type, " + 
			"mts.id  as techincalStatusId,      " + 
//			"#tst.technical_sanction_type_id,      " + 
//			"#tst.technical_sanction_type, " + 
			"ads.administrative_sanction_status,  " + 

		    "wrs.id as workRequestStatusId,"+
		   
		    "we1.id as technicalSanctionId " + 
			"from " + 
			"(SELECT *  FROM technical_sanction   order by created_date desc) innerTable " + 
			"inner join technical_sanction we1 on we1.id=innerTable.id  " + 
			"left join work w on w.id=we1.work_id   " + 
			" left join mst_district d on d.id=w.district_id    " + 
			"			 			 left join mst_work_type wt on w.work_type_id=wt.work_type_id    " + 
			"			 			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id    " + 
			"			 			 left join mst_request_status wrs on w.work_request_status_id=wrs.id    " + 
			"			 			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"                        left join administrative_sanction ads on ads.technical_sanction_id=we1.id " + 
			"                        left join mst_administration_sanction_type madst on ads.administration_sanction_type_id=madst.administration_sanction_type_id " + 
			"						 left join mst_technical_status mts on we1.technical_sanction_status=mts.id   " + 
			"                        left join mst_technical_sanction_type tst on we1.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"                         " + 
//			"                       #  where w.id=18478 " + ads.work_id
//			"                        where mts.id=?1  and w.executive_engineer_office_id=?2   and w.id=?3  and innerTable.id and NOT (innerTable.id = ?4) " + 
"                                  where (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=?1   and ads.work_id=?2   and we1.id != ?3 " + 
			"			             "  
			//"group by innerTable.work_id order by w.id desc limit ?3 ,?4"
			,nativeQuery=true)
		List<Object[]> findHistoryWorkAdminstrativeSanctionByQuery(
				//@Param("status")Long status, 
				 @Param("executive_engineer_office_id")Long executive_engineer_office_id,
				 @Param("workId")Long workId,
				 @Param("tId")Long tId
				 );
		
		List<TechnicalSanction> findByWorkAndTechnicalStatusAndTechnicalSanctionTypeOrderByModifiedDateDesc(Work work, TechnicalStatus status, TechnicalSanctionType type);

	 
}
