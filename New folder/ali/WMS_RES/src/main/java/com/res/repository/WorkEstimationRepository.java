package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.RequestStatus;
import com.res.entity.TechnicalSanctionType;
import com.res.entity.Work;
import com.res.entity.WorkEstimation;
import com.res.entity.WorkEstimationStatus;

public interface WorkEstimationRepository extends
		JpaRepository<WorkEstimation, Long> {

	List<WorkEstimation> findByWorkAndEnabledOrderByModifiedDateDesc(Work work,
			Boolean enabled);
	
	/*List<WorkEstimation> findByWorkIdAndEnabledANdOrderByModifiedDateDesc(Long workId,
			Boolean enabled);*/
	List<WorkEstimation> findByWorkIdAndEnabledAndIdNotOrderByModifiedDateDesc(Long workId,
			Boolean enabled,Long estimationId);
	
	Page<WorkEstimation> findByWorkWorkRequestStatusIdAndEnabledOrderByModifiedDateDesc(Pageable pageable, RequestStatus status, Boolean enabled);
	
	

	
	/*//richa
	Page<WorkEstimation> findByStatusIdAndEnabledOrderByModifiedDateDesc(Pageable pageable,Long estimationStatusId,  Boolean enabled);
	
	*/
	
	//Rakesh
	//findDistinctPeopleByAddress
		//Page<WorkEstimation> findDistinctWorkAndStatusIdAndEnabled(Pageable pageable,Long estimationStatusId,  Boolean enabled);
	/*//Old Working code
	@Query("from WorkEstimation we where we.enabled = :enabled and we.status.id = :estimationStatusId GROUP BY  we.work ")
//	Page<WorkEstimation> findByStatusIdAndEnabledOrderByModifiedDateDesc(Pageable pageable,
	Page<WorkEstimation> findByStatusIdAndEnabledNotOrderByModifiedDateDesc(Pageable pageable,
			@Param("estimationStatusId")Long estimationStatusId, 
			@Param("enabled")Boolean enabled); // does not have duplicated parents
*/	
	
	//new code with query by Rakesh
	
	//Select innerTable.* from
//	(SELECT * FROM res_owms_prod.work_estimate  order by modified_date desc) innerTable
//	inner join res_owms_prod.work_estimate we1 on we1.id=innerTable.id group by innerTable.work_id;
	//Page<WorkEstimation> findByStatusIdAndEnabledAndGroupByWorkOrderByModifiedDateDesc(Pageable pageable,Long estimationStatusId,  Boolean enabled);
	//@Query("select innerTable from WorkEstimation (select FROM  WorkEstimation we where we.enabled = :enabled and we.status.id = :estimationStatusId order by we.modifiedDate desc) as innerTable inner join WorkEstimation we1 on we1.id=innerTable.id  GROUP BY  innerTable.work.id ")
	//	+ "where we.enabled = :enabled and we.status.id = :estimationStatusId GROUP BY  we.work ")
//	Page<WorkEstimation> findByStatusIdAndEnabledOrderByModifiedDateDesc(Pageable pageable,
	
	/*@Query(value="Select innerTable.* from " + 
			"(SELECT * FROM work_estimate where status=?1 and enabled=?2 order by modified_date desc) innerTable " + 
			"inner join work_estimate we1 on we1.id=innerTable.id " + 
			"where we1.status= ?1 and we1.enabled= ?2 " + 
			"group by innerTable.work_id;", nativeQuery=true)
	List<WorkEstimation> findByStatusIdAndEnabledNotOrderByModifiedDateDesc(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled);*/
	
	@Query(value="Select innerTable.* from " + 
			"(SELECT * FROM work_estimate we inner join work w on w.id=we.work_id where we.status=?1 and we.enabled=?2 and (w.status is null or w.status!='Deleted') order by we.modified_date desc) innerTable " + 
			"inner join work_estimate we1 on we1.id=innerTable.id " + 
			"where we1.status= ?1 and we1.enabled= ?2 " + 
			"group by innerTable.work_id ", nativeQuery=true)
	List<WorkEstimation> findByStatusIdAndEnabledNotOrderByModifiedDateDesc(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled);
	
	
	
	
	@Query(value="SELECT    " + 
			"			w.id as workId,  " + 
			"			we1.id as workEstimateId,  " + 
			"			w.work_requisition_no,  " + 
			"			w.work_name,  " + 
			"			w.work_type_id,  " + 
			"			wt.work_type_name_e,  " + 
			"			w.work_sub_type_id,  " + 
			"			wst.work_sub_type_name_e,  " + 
			"			w.line_department_id,   " + 
			"			ld.line_department_name_e,  " + 
			"			w.district_id,  " + 
			"			d.district_name,  " + 
			"			w.work_status_id,  " + 
			"			wrs.status_name_e as workRequestStatus,  " + 
			"			we1.parent_id,  " + 
			"			we1.estimation_type,  " + 
			"			we1.total_amount,  " + 
			"			we1.status,  " + 
			"			ts.technical_sanction_status,  " + 
			"			mts.status_name_e,  " + 
			"			we1.estimation_approved_by,  " + 
			"			w.executive_engineer_office_id, " + 
			"			w.superintending_engineer_office_id,  " + 
			"			w.chief_engineer_office_id,  " + 
			"			mts.id  as techincalStatusId, +" + 
			"		    tst.technical_sanction_type_id,  " + 
			"			tst.technical_sanction_type, " + 
			"            usr_role.role_code" + 
			"           " + 
			"			FROM (SELECT max(id) as id FROM work_estimate where status=5 and enabled=1 group by work_id order by created_date desc) innerTable  " + 
			"			inner join work_estimate we1 on we1.id=innerTable.id   " + 
			"			inner join work w on w.id=we1.work_id   " + 
			"			 left join mst_district d on d.id=w.district_id  " + 
			"			 left join mst_work_type wt on w.work_type_id=wt.work_type_id  " + 
			"			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id  " + 
			"			 left join mst_request_status wrs on w.work_request_status_id=wrs.id  " + 
			"			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"			 left join technical_sanction ts on we1.id=ts.work_estimate_id  " + 
			"			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id " + 
			"			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"            left join users usr on  we1.estimation_approved_by=usr.username " + 
			"            left join user_role usr_role on usr.id=usr_role.id " + 
			"			 where we1.status=?1 and we1.enabled= ?2  " + 
			"            and w.executive_engineer_office_id=?3 " + 
			"            and (w.status is null or w.status!='Deleted') " + 
			"             and  usr_role.role_code='ROLE_EE' " + 
			"   order by w.id desc Limit ?4, ?5", nativeQuery=true)
	List<Object[]> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryEE(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("executive_engineer_office_id") Long executive_engineer_office_id,
			@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	@Query(value="SELECT    " + 
			"			w.id as workId,  " + 
			"			we1.id as workEstimateId,  " + 
			"			w.work_requisition_no,  " + 
			"			w.work_name,  " + 
			"			w.work_type_id,  " + 
			"			wt.work_type_name_e,  " + 
			"			w.work_sub_type_id,  " + 
			"			wst.work_sub_type_name_e,  " + 
			"			w.line_department_id,   " + 
			"			ld.line_department_name_e,  " + 
			"			w.district_id,  " + 
			"			d.district_name,  " + 
			"			w.work_status_id,  " + 
			"			wrs.status_name_e as workRequestStatus,  " + 
			"			we1.parent_id,  " + 
			"			we1.estimation_type,  " + 
			"			we1.total_amount,  " + 
			"			we1.status,  " + 
			"			ts.technical_sanction_status,  " + 
			"			mts.status_name_e,  " + 
			"			we1.estimation_approved_by,  " + 
			"			w.executive_engineer_office_id, " + 
			"			w.superintending_engineer_office_id,  " + 
			"			w.chief_engineer_office_id,  " + 
			"			mts.id  as techincalStatusId, +" + 
			"		    tst.technical_sanction_type_id,  " + 
			"			tst.technical_sanction_type, " + 
			"            usr_role.role_code" + 
			"           " + 
			"			FROM (SELECT max(id) as id FROM work_estimate where status=5 and enabled=1 group by work_id order by created_date desc) innerTable  " + 
			"			inner join work_estimate we1 on we1.id=innerTable.id   " + 
			"			inner join work w on w.id=we1.work_id   " + 
			"			 left join mst_district d on d.id=w.district_id  " + 
			"			 left join mst_work_type wt on w.work_type_id=wt.work_type_id  " + 
			"			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id  " + 
			"			 left join mst_request_status wrs on w.work_request_status_id=wrs.id  " + 
			"			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"			 left join technical_sanction ts on we1.id=ts.work_estimate_id  " + 
			"			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id " + 
			"			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"            left join users usr on  we1.estimation_approved_by=usr.username " + 
			"            left join user_role usr_role on usr.id=usr_role.id " + 
			"			 where we1.status=?1 and we1.enabled= ?2  " + 
			"            and w.superintending_engineer_office_id=?3 " +
			"            and (w.status is null or w.status!='Deleted') " + 
			//"             and innerTable.estimation_approved_by='aseemsuper@gmail.com' " + 
			"             and  usr_role.role_code='ROLE_SUPDT_ENGG' "+
			"   order by w.id desc Limit ?4, ?5", nativeQuery=true)
	List<Object[]> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQuerySE(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("superintending_engineer_office_id") Long superintending_engineer_office_id,
			@Param("offset") int offset,
			@Param("maxLimit") int maxLimit);
	
	@Query(value="SELECT    " + 
			"			w.id as workId,  " + 
			"			we1.id as workEstimateId,  " + 
			"			w.work_requisition_no,  " + 
			"			w.work_name,  " + 
			"			w.work_type_id,  " + 
			"			wt.work_type_name_e,  " + 
			"			w.work_sub_type_id,  " + 
			"			wst.work_sub_type_name_e,  " + 
			"			w.line_department_id,   " + 
			"			ld.line_department_name_e,  " + 
			"			w.district_id,  " + 
			"			d.district_name,  " + 
			"			w.work_status_id,  " + 
			"			wrs.status_name_e as workRequestStatus,  " + 
			"			we1.parent_id,  " + 
			"			we1.estimation_type,  " + 
			"			we1.total_amount,  " + 
			"			we1.status,  " + 
			"			ts.technical_sanction_status,  " + 
			"			mts.status_name_e,  " + 
			"			we1.estimation_approved_by,  " + 
			"			w.executive_engineer_office_id, " + 
			"			w.superintending_engineer_office_id,  " + 
			"			w.chief_engineer_office_id,  " + 
			"			mts.id  as techincalStatusId, +" + 
			"		    tst.technical_sanction_type_id,  " + 
			"			tst.technical_sanction_type, " + 
			"            usr_role.role_code" + 
			"           " + 
			"			  FROM (SELECT max(id) as id FROM work_estimate where status=5 and enabled=1 group by work_id order by created_date desc) innerTable  " + 
			"			inner join work_estimate we1 on we1.id=innerTable.id   " + 
			"			inner join work w on w.id=we1.work_id   " + 
			"			 left join mst_district d on d.id=w.district_id  " + 
			"			 left join mst_work_type wt on w.work_type_id=wt.work_type_id  " + 
			"			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id  " + 
			"			 left join mst_request_status wrs on w.work_request_status_id=wrs.id  " + 
			"			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"			 left join technical_sanction ts on we1.id=ts.work_estimate_id  " + 
			"			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id " + 
			"			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"            left join users usr on  we1.estimation_approved_by=usr.username " + 
			"            left join user_role usr_role on usr.id=usr_role.id " + 
			"			 where we1.status=?1 and we1.enabled= ?2  " + 
			"            and w.chief_engineer_office_id=?3 " + 
			"            and (w.status is null or w.status!='Deleted') " + 
			//"             and innerTable.estimation_approved_by='aseemsuper@gmail.com' " + 
			"             and  usr_role.role_code='ROLE_CE' "+
			"   order by w.id desc Limit ?4, ?5", nativeQuery=true)
	List<Object[]> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryCE(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("chief_engineer_office_id") Long chief_engineer_office_id,
			@Param("offset") int offset,
			@Param("maxLimit") int maxLimit);
	
	@Query(value="SELECT    " + 
			"			w.id as workId,  " + 
			"			we1.id as workEstimateId,  " + 
			"			w.work_requisition_no,  " + 
			"			w.work_name,  " + 
			"			w.work_type_id,  " + 
			"			wt.work_type_name_e,  " + 
			"			w.work_sub_type_id,  " + 
			"			wst.work_sub_type_name_e,  " + 
			"			w.line_department_id,   " + 
			"			ld.line_department_name_e,  " + 
			"			w.district_id,  " + 
			"			d.district_name,  " + 
			"			w.work_status_id,  " + 
			"			wrs.status_name_e as workRequestStatus,  " + 
			"			we1.parent_id,  " + 
			"			we1.estimation_type,  " + 
			"			we1.total_amount,  " + 
			"			we1.status,  " + 
			"			ts.technical_sanction_status,  " + 
			"			mts.status_name_e,  " + 
			"			we1.estimation_approved_by,  " + 
			"			w.executive_engineer_office_id, " + 
			"			w.superintending_engineer_office_id,  " + 
			"			w.chief_engineer_office_id,  " + 
			"			mts.id  as techincalStatusId, +" + 
			"		    tst.technical_sanction_type_id,  " + 
			"			tst.technical_sanction_type, " + 
			"            usr_role.role_code" + 
			"           " + 
			"			FROM (SELECT max(id) as id FROM work_estimate where status=5 and enabled=1 group by work_id order by created_date desc) innerTable  " + 
			"			inner join work_estimate we1 on we1.id=innerTable.id   " + 
			"			inner join work w on w.id=we1.work_id   " + 
			"			 left join mst_district d on d.id=w.district_id  " + 
			"			 left join mst_work_type wt on w.work_type_id=wt.work_type_id  " + 
			"			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id  " + 
			"			 left join mst_request_status wrs on w.work_request_status_id=wrs.id  " + 
			"			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"			 left join technical_sanction ts on we1.id=ts.work_estimate_id  " + 
			"			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id " + 
			"			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"            left join users usr on  we1.estimation_approved_by=usr.username " + 
			"            left join user_role usr_role on usr.id=usr_role.id " + 
			"			 where we1.status=?1 and we1.enabled= ?2  " + 
			"            and (w.status is null or w.status!='Deleted') " + 
			"            and usr.username=?3 " + 
			//"             and innerTable.estimation_approved_by='aseemsuper@gmail.com' " + 
			"             and  usr_role.role_code='ROLE_AE' "+
			"   order by w.id desc Limit ?4, ?5", nativeQuery=true)
	List<Object[]> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryAE(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("loggedInUserName") String loggedInUserName,
			@Param("offset") int offset,
			@Param("maxLimit") int maxLimit);
	
	/*@Query(value="Select innerTable.* from " + 
			"(SELECT * FROM work_estimate  order by modified_date desc) innerTable " + 
			"inner join work_estimate we1 on we1.id=innerTable.id " + 
			"where we1.status=:enabled and we1.enabled=:estimationStatusId " + 
			"group by innerTable.work_id;",nativeQuery=true)
	Collection<WorkEstimation> findByStatusIdAndEnabledNotOrderByModifiedDateDesc(
			@Param("estimationStatusId")Long estimationStatusId, 
			@Param("enabled")Boolean enabled);*/
	
	
	/*@Query(value="SELECT * FROM work_estimate",nativeQuery=true)
	Collection<WorkEstimation> findByStatusIdAndEnabledNotOrderByModifiedDateDesc(
			Long estimationStatusId, 
			Boolean enabled);*/
	/*@Query(value="Select innerTable.* from " + 
			"(SELECT * FROM work_estimate  order by modified_date desc) innerTable " + 
			"inner join work_estimate we1 on we1.id=innerTable.id " + 
			"where we1.status=5 and we1.enabled=1 " + 
			"group by innerTable.work_id;",nativeQuery=true)
	List<WorkEstimation> findByStatusIdAndEnabledNotOrderByModifiedDateDesc(
			Long estimationStatusId, 
			Boolean enabled);*/
	//
	
	// Query for finding list of Technical Sanction in different logins.
	Page<WorkEstimation> findByStatusIdAndEnabledAndModifiedByOrderByModifiedDateDesc(Pageable pageable,Long estimationStatusId,  Boolean enabled, String modifiedBy);
	
	@Query("from WorkEstimation we where we.enabled = :enabled and we.status.id in :statusIds and we.work.executiveEngineerOffice.id = :officeId and (we.work.status is null or we.work.status!='Deleted') order by we.modifiedDate desc")
	Page<WorkEstimation> findByStatusIdsAndEnabledAndExecutiveEngineerOfficeId(Pageable pageable, @Param("statusIds") List<Long> statusIds, @Param("enabled") Boolean enabled, @Param("officeId") Long officeId);

	@Query("select count(*) from WorkEstimation we where we.enabled = :enabled and we.status.id in :statusIds and we.work.executiveEngineerOffice.id = :officeId and (we.work.status is null or we.work.status!='Deleted')")
	long countByStatusIdsAndEnabledAndExecutiveEngineerOfficeId(@Param("statusIds") List<Long> statusIds, @Param("enabled") Boolean enabled, @Param("officeId") Long officeId);
	
	// Query for counting list of Technical Sanction in different logins.
	@Query("select count(*) from WorkEstimation we where we.enabled = :enabled and we.modifiedBy = :modifiedBy and we.status.id = :status and (we.work.status is null or we.work.status!='Deleted')")
	long countByEnabledAndModifiedByAndStatusOrderByModifiedDateDesc(@Param("enabled") Boolean enabled,@Param("modifiedBy") String modifiedBy,@Param("status") Long status);
	
	// Query for finding list of Technical Sanction in different logins with Filters.
		@Query("from WorkEstimation we where we.enabled = :enabled and we.modifiedBy = :modifiedBy and we.status.id = :status and (we.work.status is null or we.work.status!='Deleted')")
		Page<WorkEstimation> findByStatusIdAndModifiedByAndFiltered(Pageable pageable,
				@Param("status") Long estimationStatusId,
				@Param("enabled") Boolean enabled,
				@Param("modifiedBy") String modifiedBy);
		
	// Query for counting list of Technical Sanction in different logins with Filters.
	@Query("select count(*) from WorkEstimation we where we.enabled = :enabled and we.modifiedBy = :modifiedBy and we.status.id = :status and (we.work.status is null or we.work.status!='Deleted')")
	long countByStatusIdAndModifiedByAndFiltered(@Param("enabled") Boolean enabled,@Param("modifiedBy") String modifiedBy,@Param("status") Long status);

	List<WorkEstimation> findByWork(Work work);

	

	List<WorkEstimation> findByIdAndEnabledOrderByModifiedDateDesc(Long estimationId, boolean b);

	WorkEstimation findById(Long estimationId);
	
	// Rakesh working code to get the History of Technical sanction page
	@Query(value=" SELECT     " + 
			"			 			w.id as workId,   " + 
			"			 			innerTable.id as workEstimationId,   " + 
			"			 			w.work_requisition_no,   " + 
			"			 			w.work_name,   " + 
			"			 			w.work_type_id,   " + 
			"			 			wt.work_type_name_e,   " + 
			"			 			w.work_sub_type_id,   " + 
			"			 			wst.work_sub_type_name_e,   " + 
			"			 			w.line_department_id,    " + 
			"			 			ld.line_department_name_e,   " + 
			"			 			w.district_id,   " + 
			"			 			d.district_name,   " + 
			"			 			w.work_status_id,   " + 
			"			 			wrs.status_name_e as workRequestStatus,   " + 
			"			 			innerTable.parent_id,   " + 
			"			 			innerTable.estimation_type,   " + 
			"			 			innerTable.total_amount,   " + 
			"			 			innerTable.status,   " + 
			"			 			ts.technical_sanction_status,   " + 
			"			 			mts.status_name_e,   " + 
			"			 			innerTable.estimation_approved_by,   " + 
			"			 			w.executive_engineer_office_id,  " + 
			"			 			w.superintending_engineer_office_id,   " + 
			"			 			w.chief_engineer_office_id,   " + 
			"			 			mts.id  as techincalStatusId,   " + 
			"			 		    tst.technical_sanction_type_id,   " + 
			"			 			tst.technical_sanction_type,"
			+ "                     ts.technical_sanction_amount  " + 
			"						FROM work_estimate innerTable   " + 
			"						inner join work w on w.id=innerTable.work_id    " + 
			"			 			left join mst_district d on d.id=w.district_id   " + 
			"			 			left join mst_work_type wt on w.work_type_id=wt.work_type_id   " + 
			"			 			left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id   " + 
			"			 			left join mst_request_status wrs on w.work_request_status_id=wrs.id   " + 
			"			 			left join mst_line_department ld on w.line_department_id=ld.line_department_id   " + 
			"			 			left join technical_sanction ts on innerTable.id=ts.work_estimate_id   " + 
			"			 			left join mst_technical_status mts on ts.technical_sanction_status=mts.id  " + 
			"			 			left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id   " + 
			"						where innerTable.status=?1 and innerTable.enabled= ?2   " + 
			"			            and w.executive_engineer_office_id=?3 " + 
			"                       and (w.status is null or w.status!='Deleted') " +
			"                       and w.id=?4 and innerTable.id and NOT (innerTable.id = ?5) order by innerTable.id desc", nativeQuery=true)
	List<Object[]> findHistoryOfTechnicalSaction(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("executive_engineer_office_id") Long executive_engineer_office_id,
			@Param("workId") Long workId, 
			@Param("workEstimationId") Long workEstimationId 
			
			);
	
	@Query(value=" SELECT     " + 
			"			 			w.id as workId,   " + 
			"			 			innerTable.id as workEstimationId,   " + 
			"			 			w.work_requisition_no,   " + 
			"			 			w.work_name,   " + 
			"			 			w.work_type_id,   " + 
			"			 			wt.work_type_name_e,   " + 
			"			 			w.work_sub_type_id,   " + 
			"			 			wst.work_sub_type_name_e,   " + 
			"			 			w.line_department_id,    " + 
			"			 			ld.line_department_name_e,   " + 
			"			 			w.district_id,   " + 
			"			 			d.district_name,   " + 
			"			 			w.work_status_id,   " + 
			"			 			wrs.status_name_e as workRequestStatus,   " + 
			"			 			innerTable.parent_id,   " + 
			"			 			innerTable.estimation_type,   " + 
			"			 			innerTable.total_amount,   " + 
			"			 			innerTable.status,   " + 
			"			 			ts.technical_sanction_status,   " + 
			"			 			mts.status_name_e,   " + 
			"			 			innerTable.estimation_approved_by,   " + 
			"			 			w.executive_engineer_office_id,  " + 
			"			 			w.superintending_engineer_office_id,   " + 
			"			 			w.chief_engineer_office_id,   " + 
			"			 			mts.id  as techincalStatusId,   " + 
			"			 		    tst.technical_sanction_type_id,   " + 
			"			 			tst.technical_sanction_type,"
			+ "                     ts.technical_sanction_amount  " + 
			"						FROM work_estimate innerTable   " + 
			"						inner join work w on w.id=innerTable.work_id    " + 
			"			 			left join mst_district d on d.id=w.district_id   " + 
			"			 			left join mst_work_type wt on w.work_type_id=wt.work_type_id   " + 
			"			 			left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id   " + 
			"			 			left join mst_request_status wrs on w.work_request_status_id=wrs.id   " + 
			"			 			left join mst_line_department ld on w.line_department_id=ld.line_department_id   " + 
			"			 			left join technical_sanction ts on innerTable.id=ts.work_estimate_id   " + 
			"			 			left join mst_technical_status mts on ts.technical_sanction_status=mts.id  " + 
			"			 			left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id   " + 
			"						where innerTable.status=?1 and innerTable.enabled= ?2   " + 
			"			            and w.superintending_engineer_office_id=?3 " + 
			"                       and (w.status is null or w.status!='Deleted') " +
			"                       and w.id=?4 and innerTable.id and NOT (innerTable.id = ?5) order by innerTable.id desc", nativeQuery=true)
	List<Object[]> findHistoryOfTechnicalSactionSe(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("se_office_id") Long se_office_id,
			@Param("workId") Long workId, 
			@Param("workEstimationId") Long workEstimationId 
			
			);
	
	@Query(value=" SELECT     " + 
			"			 			w.id as workId,   " + 
			"			 			innerTable.id as workEstimationId,   " + 
			"			 			w.work_requisition_no,   " + 
			"			 			w.work_name,   " + 
			"			 			w.work_type_id,   " + 
			"			 			wt.work_type_name_e,   " + 
			"			 			w.work_sub_type_id,   " + 
			"			 			wst.work_sub_type_name_e,   " + 
			"			 			w.line_department_id,    " + 
			"			 			ld.line_department_name_e,   " + 
			"			 			w.district_id,   " + 
			"			 			d.district_name,   " + 
			"			 			w.work_status_id,   " + 
			"			 			wrs.status_name_e as workRequestStatus,   " + 
			"			 			innerTable.parent_id,   " + 
			"			 			innerTable.estimation_type,   " + 
			"			 			innerTable.total_amount,   " + 
			"			 			innerTable.status,   " + 
			"			 			ts.technical_sanction_status,   " + 
			"			 			mts.status_name_e,   " + 
			"			 			innerTable.estimation_approved_by,   " + 
			"			 			w.executive_engineer_office_id,  " + 
			"			 			w.superintending_engineer_office_id,   " + 
			"			 			w.chief_engineer_office_id,   " + 
			"			 			mts.id  as techincalStatusId,   " + 
			"			 		    tst.technical_sanction_type_id,   " + 
			"			 			tst.technical_sanction_type,"
			+ "                     ts.technical_sanction_amount  " + 
			"						FROM work_estimate innerTable   " + 
			"						inner join work w on w.id=innerTable.work_id    " + 
			"			 			left join mst_district d on d.id=w.district_id   " + 
			"			 			left join mst_work_type wt on w.work_type_id=wt.work_type_id   " + 
			"			 			left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id   " + 
			"			 			left join mst_request_status wrs on w.work_request_status_id=wrs.id   " + 
			"			 			left join mst_line_department ld on w.line_department_id=ld.line_department_id   " + 
			"			 			left join technical_sanction ts on innerTable.id=ts.work_estimate_id   " + 
			"			 			left join mst_technical_status mts on ts.technical_sanction_status=mts.id  " + 
			"			 			left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id   " + 
			"						where innerTable.status=?1 and innerTable.enabled= ?2   " + 
			"			            and w.chief_engineer_office_id=?3 " + 
			"                       and (w.status is null or w.status!='Deleted') " +
			"                       and w.id=?4 and innerTable.id and NOT (innerTable.id = ?5) order by innerTable.id desc", nativeQuery=true)
	List<Object[]> findHistoryOfTechnicalSactionCe(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("ce_office_id") Long ce_office_id,
			@Param("workId") Long workId, 
			@Param("workEstimationId") Long workEstimationId 
			
			);
	
	//Count Queries Rakesh
	@Query(value="Select count(DISTINCT  w.id) from " + 
			"(SELECT * FROM work_estimate where status=?1 and enabled=?2 order by modified_date desc) innerTable      " + 
			"			 			inner join work_estimate we1 on we1.id=innerTable.id       " + 
			"			 			inner join work w on w.id=innerTable.work_id       " + 
			"			 			 left join mst_district d on d.id=w.district_id      " + 
			"			 			 left join mst_work_type wt on w.work_type_id=wt.work_type_id      " + 
			"			 			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id      " + 
			"			 			 left join mst_request_status wrs on w.work_request_status_id=wrs.id      " + 
			"			 			 left join mst_line_department ld on w.line_department_id=ld.line_department_id      " + 
			"			 			 left join technical_sanction ts on innerTable.id=ts.work_estimate_id      " + 
			"			 			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id     " + 
			"			 			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id      " + 
			"			             left join users usr on  innerTable.estimation_approved_by=usr.username     " + 
			"			             left join user_role usr_role on usr.id=usr_role.id     " + 
			"			 			 where we1.status=?1 and we1.enabled= ?2    " + 
			"			             and w.executive_engineer_office_id=?3    " + 
			"                       and (w.status is null or w.status!='Deleted') " +
			"			             and  usr_role.role_code='ROLE_EE'  ", nativeQuery=true)
	long findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryEECount(@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("executive_engineer_office_id") Long executive_engineer_office_id);
	
	@Query(value="Select count(DISTINCT  w.id) from " + 
			"(SELECT * FROM work_estimate where status=?1 and enabled=?2 order by modified_date desc) innerTable      " + 
			"			 			inner join work_estimate we1 on we1.id=innerTable.id       " + 
			"			 			inner join work w on w.id=innerTable.work_id       " + 
			"			 			 left join mst_district d on d.id=w.district_id      " + 
			"			 			 left join mst_work_type wt on w.work_type_id=wt.work_type_id      " + 
			"			 			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id      " + 
			"			 			 left join mst_request_status wrs on w.work_request_status_id=wrs.id      " + 
			"			 			 left join mst_line_department ld on w.line_department_id=ld.line_department_id      " + 
			"			 			 left join technical_sanction ts on innerTable.id=ts.work_estimate_id      " + 
			"			 			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id     " + 
			"			 			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id      " + 
			"			             left join users usr on  innerTable.estimation_approved_by=usr.username     " + 
			"			             left join user_role usr_role on usr.id=usr_role.id     " + 
			"			 			 where we1.status=?1 and we1.enabled= ?2    " + 
			"                       and (w.status is null or w.status!='Deleted') " +
			"			             and usr.username=?3    " + 
		
			"			             and  usr_role.role_code='ROLE_AE'  ", nativeQuery=true)
	long findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryAECount(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("loggedInUser") String loggedInUser);
	
	//"            and w.superintending_engineer_office_id=?3 " + 
	//"             and innerTable.estimation_approved_by='aseemsuper@gmail.com' " + 
//	"             and  usr_role.role_code='ROLE_SUPDT_ENGG' "+
	/*@Query(value="Select count(DISTINCT  w.id) from " + 
			"(SELECT * FROM work_estimate where status=?1 and enabled=?2 order by modified_date desc) innerTable      " + 
			"			 			inner join work_estimate we1 on we1.id=innerTable.id       " + 
			"			 			inner join work w on w.id=innerTable.work_id       " + 
			"			 			 left join mst_district d on d.id=w.district_id      " + 
			"			 			 left join mst_work_type wt on w.work_type_id=wt.work_type_id      " + 
			"			 			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id      " + 
			"			 			 left join mst_request_status wrs on w.work_request_status_id=wrs.id      " + 
			"			 			 left join mst_line_department ld on w.line_department_id=ld.line_department_id      " + 
			"			 			 left join technical_sanction ts on innerTable.id=ts.work_estimate_id      " + 
			"			 			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id     " + 
			"			 			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id      " + 
			"			             left join users usr on  innerTable.estimation_approved_by=usr.username     " + 
			"			             left join user_role usr_role on usr.id=usr_role.id     " + 
			"			 			 where  w.superintending_engineer_office_id=?3    " + 
		
			"			             and  usr_role.role_code='ROLE_SUPDT_ENGG'  ", nativeQuery=true)
	List<Object[]> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQuerySECount(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("superintending_engineer_office_id") Long superintending_engineer_office_id);*/
	//
	
	
	@Query(value="Select count(DISTINCT  w.id) from " + 
			"(SELECT * FROM work_estimate where status=?1 and enabled=?2 order by modified_date desc) innerTable      " + 
			"			 			inner join work_estimate we1 on we1.id=innerTable.id       " + 
			"			 			inner join work w on w.id=innerTable.work_id       " + 
			"			 			 left join mst_district d on d.id=w.district_id      " + 
			"			 			 left join mst_work_type wt on w.work_type_id=wt.work_type_id      " + 
			"			 			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id      " + 
			"			 			 left join mst_request_status wrs on w.work_request_status_id=wrs.id      " + 
			"			 			 left join mst_line_department ld on w.line_department_id=ld.line_department_id      " + 
			"			 			 left join technical_sanction ts on innerTable.id=ts.work_estimate_id      " + 
			"			 			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id     " + 
			"			 			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id      " + 
			"			             left join users usr on  innerTable.estimation_approved_by=usr.username     " + 
			"			             left join user_role usr_role on usr.id=usr_role.id     " + 
			"			 			 where  w.superintending_engineer_office_id=?3    " + 
			"                        and (w.status is null or w.status!='Deleted') " +
		
			"			             and  usr_role.role_code='ROLE_SUPDT_ENGG'  ", nativeQuery=true)
	long findByStatusIdAndEnabledNotOrderByModifiedDateDescByQuerySECount(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("superintending_engineer_office_id") Long superintending_engineer_office_id);
	
	@Query(value="Select count(DISTINCT  w.id) from " + 
			"(SELECT * FROM work_estimate where status=?1 and enabled=?2 order by modified_date desc) innerTable      " + 
			"			 			inner join work_estimate we1 on we1.id=innerTable.id       " + 
			"			 			inner join work w on w.id=innerTable.work_id       " + 
			"			 			 left join mst_district d on d.id=w.district_id      " + 
			"			 			 left join mst_work_type wt on w.work_type_id=wt.work_type_id      " + 
			"			 			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id      " + 
			"			 			 left join mst_request_status wrs on w.work_request_status_id=wrs.id      " + 
			"			 			 left join mst_line_department ld on w.line_department_id=ld.line_department_id      " + 
			"			 			 left join technical_sanction ts on innerTable.id=ts.work_estimate_id      " + 
			"			 			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id     " + 
			"			 			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id      " + 
			"			             left join users usr on  innerTable.estimation_approved_by=usr.username     " + 
			"			             left join user_role usr_role on usr.id=usr_role.id     " + 
			"			 			 where we1.status=?1 and we1.enabled= ?2    " + 
			"			             and w.chief_engineer_office_id=?3    " + 
			"                        and (w.status is null or w.status!='Deleted') " +
		
			"			             and  usr_role.role_code='ROLE_CE'  ", nativeQuery=true)
	long findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryCECount(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("chief_engineer_office_id") Long chief_engineer_office_id);

	@Query("from WorkEstimation t where t.work = :work and t.enabled = :enabled order by created_date DESC")
 	List<WorkEstimation> findAllEstByWork(@Param("work") Work work,@Param("enabled") Boolean enabled);
	
	List<WorkEstimation> findByWorkAndStatusAndEstimationTypeOrderByModifiedDateDesc(Work work, WorkEstimationStatus status, String type);
	
	 @Query("SELECT w FROM WorkEstimation w WHERE w.id = :parentId")
	    WorkEstimation findByParentId(@Param("parentId") Long parentId);
	 
	 @Query(value = "SELECT * FROM work_estimate WHERE work_id = :workId ORDER BY id DESC LIMIT 1", nativeQuery = true)
	 WorkEstimation findLatestByWorkId(@Param("workId") Long workId);


	
}
