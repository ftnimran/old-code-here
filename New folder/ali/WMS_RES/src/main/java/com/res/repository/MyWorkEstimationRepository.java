/*package com.res.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.WorkEstimation;



public interface MyWorkEstimationRepository extends
JpaRepository<WorkEstimation, Long> {


	
	
	
	
	@Query(value="SELECT    " + 
			"			w.id as workId,  " + 
			"			innerTable.id as workEstimateId,  " + 
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
			"			innerTable.parent_id,  " + 
			"			innerTable.estimation_type,  " + 
			"			innerTable.total_amount,  " + 
			"			innerTable.status,  " + 
			"			ts.technical_sanction_status,  " + 
			"			mts.status_name_e,  " + 
			"			innerTable.estimation_approved_by,  " + 
			"			w.executive_engineer_office_id, " + 
			"			w.superintending_engineer_office_id,  " + 
			"			w.chief_engineer_office_id,  " + 
			"			mts.id  as techincalStatusId, +" + 
			"		    tst.technical_sanction_type_id,  " + 
			"			tst.technical_sanction_type, " + 
			"            usr_role.role_code" + 
			"           " + 
			"			FROM (SELECT * FROM work_estimate where status=5 and enabled=1 order by modified_date desc) innerTable  " + 
			"			inner join work_estimate we1 on we1.id=innerTable.id   " + 
			"			inner join work w on w.id=innerTable.work_id   " + 
			"			 left join mst_district d on d.id=w.district_id  " + 
			"			 left join mst_work_type wt on w.work_type_id=wt.work_type_id  " + 
			"			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id  " + 
			"			 left join mst_request_status wrs on w.work_request_status_id=wrs.id  " + 
			"			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"			 left join technical_sanction ts on innerTable.work_id=ts.work_id  " + 
			"			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id " + 
			"			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"            left join users usr on  innerTable.estimation_approved_by=usr.username " + 
			"            left join user_role usr_role on usr.id=usr_role.id " + 
			"			 where we1.status=?1 and we1.enabled= ?2  " + 
			"            and w.executive_engineer_office_id=?3 " + 
			//"             and innerTable.estimation_approved_by='aseemsuper@gmail.com' " + 
			"            and  usr_role.role_code='ROLE_EE' "
		  //  "   ORDER BY w.id "+
			//"   group by innerTable.work_id  order by   \n#pageable\n"
			//"   group by innerTable.work_id  order by w.id desc #pageable"
			
//			+ "  ORDER BY w.id ?\n#pageable\n"
			, nativeQuery=true)
	List<Object[]> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryEE(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("executive_engineer_office_id") Long executive_engineer_office_id
			//,@Param("pageable") Pageable pageable
			);
	Page<WorkEstimation> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryEE(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("executive_engineer_office_id") Long executive_engineer_office_id
			//,@Param("pageable") Pageable pageable
			, Pageable pageable
			);
	
	@Query(value="SELECT    " + 
			"			w.id as workId,  " + 
			"			innerTable.id as workEstimateId,  " + 
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
			"			innerTable.parent_id,  " + 
			"			innerTable.estimation_type,  " + 
			"			innerTable.total_amount,  " + 
			"			innerTable.status,  " + 
			"			ts.technical_sanction_status,  " + 
			"			mts.status_name_e,  " + 
			"			innerTable.estimation_approved_by,  " + 
			"			w.executive_engineer_office_id, " + 
			"			w.superintending_engineer_office_id,  " + 
			"			w.chief_engineer_office_id,  " + 
			"			mts.id  as techincalStatusId, +" + 
			"		    tst.technical_sanction_type_id,  " + 
			"			tst.technical_sanction_type, " + 
			"            usr_role.role_code" + 
			"           " + 
			"			FROM (SELECT * FROM work_estimate where status=5 and enabled=1 order by modified_date desc) innerTable  " + 
			"			inner join work_estimate we1 on we1.id=innerTable.id   " + 
			"			inner join work w on w.id=innerTable.work_id   " + 
			"			 left join mst_district d on d.id=w.district_id  " + 
			"			 left join mst_work_type wt on w.work_type_id=wt.work_type_id  " + 
			"			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id  " + 
			"			 left join mst_request_status wrs on w.work_request_status_id=wrs.id  " + 
			"			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"			 left join technical_sanction ts on innerTable.work_id=ts.work_id  " + 
			"			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id " + 
			"			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"            left join users usr on  innerTable.estimation_approved_by=usr.username " + 
			"            left join user_role usr_role on usr.id=usr_role.id " + 
			"			 where we1.status=?1 and we1.enabled= ?2  " + 
			"            and w.superintending_engineer_office_id=?3 " + 
			//"             and innerTable.estimation_approved_by='aseemsuper@gmail.com' " + 
			"             and  usr_role.role_code='ROLE_SUPDT_ENGG' "+
			"   group by innerTable.work_id  order by w.id desc", nativeQuery=true)
	List<Object[]> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQuerySE(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("superintending_engineer_office_id") Long superintending_engineer_office_id);
	
	@Query(value="SELECT    " + 
			"			w.id as workId,  " + 
			"			innerTable.id as workEstimateId,  " + 
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
			"			innerTable.parent_id,  " + 
			"			innerTable.estimation_type,  " + 
			"			innerTable.total_amount,  " + 
			"			innerTable.status,  " + 
			"			ts.technical_sanction_status,  " + 
			"			mts.status_name_e,  " + 
			"			innerTable.estimation_approved_by,  " + 
			"			w.executive_engineer_office_id, " + 
			"			w.superintending_engineer_office_id,  " + 
			"			w.chief_engineer_office_id,  " + 
			"			mts.id  as techincalStatusId, +" + 
			"		    tst.technical_sanction_type_id,  " + 
			"			tst.technical_sanction_type, " + 
			"            usr_role.role_code" + 
			"           " + 
			"			FROM (SELECT * FROM work_estimate where status=5 and enabled=1 order by modified_date desc) innerTable  " + 
			"			inner join work_estimate we1 on we1.id=innerTable.id   " + 
			"			inner join work w on w.id=innerTable.work_id   " + 
			"			 left join mst_district d on d.id=w.district_id  " + 
			"			 left join mst_work_type wt on w.work_type_id=wt.work_type_id  " + 
			"			 left join mst_work_sub_type wst on w.work_sub_type_id=wst.work_sub_type_id  " + 
			"			 left join mst_request_status wrs on w.work_request_status_id=wrs.id  " + 
			"			 left join mst_line_department ld on w.line_department_id=ld.line_department_id  " + 
			"			 left join technical_sanction ts on innerTable.work_id=ts.work_id  " + 
			"			 left join mst_technical_status mts on ts.technical_sanction_status=mts.id " + 
			"			 left join mst_technical_sanction_type tst on ts.technical_sanction_type_id=tst.technical_sanction_type_id  " + 
			"            left join users usr on  innerTable.estimation_approved_by=usr.username " + 
			"            left join user_role usr_role on usr.id=usr_role.id " + 
			"			 where we1.status=?1 and we1.enabled= ?2  " + 
			"            and w.chief_engineer_office_id=?3 " + 
			//"             and innerTable.estimation_approved_by='aseemsuper@gmail.com' " + 
			"             and  usr_role.role_code='ROLE_CE' "+
			"   group by innerTable.work_id  order by w.id desc", nativeQuery=true)
	List<Object[]> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryCE(
			@Param("estimationStatusId") Long estimationStatusId, 
			@Param("enabled") Boolean enabled,
			@Param("chief_engineer_office_id") Long chief_engineer_office_id);
	
	
	
	
	
}
*/