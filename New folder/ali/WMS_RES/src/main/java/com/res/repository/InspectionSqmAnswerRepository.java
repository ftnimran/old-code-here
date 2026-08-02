package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.InspectionDetails;
import com.res.entity.InspectionSqmAnswer;
import com.res.entity.SqmAllocation;
import com.res.entity.Users;
import com.res.entity.Work;

public interface InspectionSqmAnswerRepository  extends JpaRepository<InspectionSqmAnswer, Long>, CrudRepository<InspectionSqmAnswer, Long> {

	
	@Query(value =
	        "SELECT " +
	        " w.work_name AS WorkName, " +                 // 0
	        " w.work_requisition_no, " +                   // 1
	        " COALESCE(mwt.WORK_TYPE_NAME_E, '') AS workTypeName, " + // 2
	        " COALESCE(us.name, '') AS inspectedBy, " +    // 3
	        " ias.work_id, " +                             // 4
	        " COALESCE(md.district_name, '') AS districtName, " + // 5
	        " COALESCE(ld.line_department_name_e, '') AS line_department_name_e, " + // 6
	        " '' AS dummyColumn, " +                       // 7 (to maintain index alignment)
	        " COALESCE(mb.block_name, '') AS block_name, " + // 8
	        " COALESCE(mat.agency_type_name_e, '') AS agency_type_name_e, " + // 9
	        " COALESCE(mws.work_status_name_e, '') AS work_status_name_e " + // 10

	        "FROM inspection_details_new ias " +

	        "INNER JOIN work w ON w.id = ias.work_id " +
	        "INNER JOIN sqm_allocation sqma ON sqma.id = ias.sqm_allocation_id " +

	        "LEFT JOIN users us ON us.username = ias.inspected_by " +
	        "LEFT JOIN user_role usr ON usr.id = us.id " +
	        "LEFT JOIN mst_line_department ld ON ld.line_department_id = w.line_department_id " +
	        "LEFT JOIN mst_work_type mwt ON mwt.work_type_id = w.work_type_id " +
	        "LEFT JOIN mst_district md ON md.id = w.district_id " +
	        "LEFT JOIN mst_block mb ON mb.id = w.block_id " +
	        "LEFT JOIN mst_agency_type mat ON mat.agency_type_id = w.agency_type_id " +
	        "LEFT JOIN mst_work_status mws ON mws.id = w.work_status_id " +

	        "WHERE (w.status IS NULL OR w.status != 'Deleted') " +
	        "AND sqma.inspection_done = 1 " +
	        "AND COALESCE(usr.role_code, '') = 'ROLE_SQM' " +
	        "AND w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " +
	        "AND w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " +
	        "AND w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) " +
	        "AND ias.inspected_by = COALESCE(:sqmId1, ias.inspected_by) " +
	        "AND COALESCE(ias.work_type, '') = COALESCE(:grading1, COALESCE(ias.work_type, '')) " +
	        "AND w.chief_engineer_office_id = :chief_engineer_office_id " +
	        "GROUP BY w.id " +
	        "LIMIT :maxLimit OFFSET :offset",
	        nativeQuery = true)
	List<Object[]> findSqmInspectionListBasedOnWork(
			@Param("chief_engineer_office_id") Long chiefEngineerOfficeId,
	        @Param("exeOfficeId1") Long exeOfficeId1,
	        @Param("workStatusId1") Long workStatusId1,
	        @Param("workTypeId1") Long workTypeId1,
	        @Param("sqmId1") String sqmId1,
	        @Param("grading1") String grading1,
	        @Param("offset") int offset,
	        @Param("maxLimit") int maxLimit
	);
	
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and ias.answer_by_role='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) "
			
			+ "and w.superintending_engineer_office_id=:supt_engineer_office_id and sqma.inspection_done=1 group by ias.work_id limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForSupdt(
			@Param("supt_engineer_office_id") Long supt_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	@Query(value =
	        "SELECT " +
	        " w.work_name AS WorkName, " +                 // 0
	        " w.work_requisition_no, " +                   // 1
	        " COALESCE(mwt.WORK_TYPE_NAME_E, '') AS workTypeName, " + // 2
	        " COALESCE(us.name, '') AS inspectedBy, " +    // 3
	        " ias.work_id, " +                             // 4
	        " COALESCE(md.district_name, '') AS districtName, " + // 5
	        " COALESCE(ld.line_department_name_e, '') AS line_department_name_e, " + // 6
	        " '' AS dummyColumn, " +                       // 7 (to maintain index alignment)
	        " COALESCE(mb.block_name, '') AS block_name, " + // 8
	        " COALESCE(mat.agency_type_name_e, '') AS agency_type_name_e, " + // 9
	        " COALESCE(mws.work_status_name_e, '') AS work_status_name_e " + // 10

	        "FROM inspection_details_new ias " +

	        "INNER JOIN work w ON w.id = ias.work_id " +
	        "INNER JOIN sqm_allocation sqma ON sqma.id = ias.sqm_allocation_id " +

	        "LEFT JOIN users us ON us.username = ias.inspected_by " +
	        "LEFT JOIN user_role usr ON usr.id = us.id " +
	        "LEFT JOIN mst_line_department ld ON ld.line_department_id = w.line_department_id " +
	        "LEFT JOIN mst_work_type mwt ON mwt.work_type_id = w.work_type_id " +
	        "LEFT JOIN mst_district md ON md.id = w.district_id " +
	        "LEFT JOIN mst_block mb ON mb.id = w.block_id " +
	        "LEFT JOIN mst_agency_type mat ON mat.agency_type_id = w.agency_type_id " +
	        "LEFT JOIN mst_work_status mws ON mws.id = w.work_status_id " +

	        
	        "WHERE (w.status IS NULL OR w.status != 'Deleted') " +
	        "AND sqma.inspection_done = 1 " +
	        "AND COALESCE(usr.role_code, '') = 'ROLE_SQM' " +
	        "AND w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " +
	        "AND w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " +
	        "AND w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) " +
	        "AND ias.inspected_by = COALESCE(:sqmId1, ias.inspected_by) " +
	        "AND COALESCE(ias.work_type, '') = COALESCE(:grading1, COALESCE(ias.work_type, '')) " +
	        "and w.executive_engineer_office_id =:executive_engineer_office_id "+
	        "GROUP BY w.id " +
	        "LIMIT :maxLimit OFFSET :offset", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForEE(
			@Param("executive_engineer_office_id") Long executive_engineer_office_id,
			@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") String sqmId1,@Param("grading1") String grading1, @Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
		
	@Query(value =
	        "SELECT " +
	        " w.work_name AS WorkName, " +                 // 0
	        " w.work_requisition_no, " +                   // 1
	        " COALESCE(mwt.WORK_TYPE_NAME_E, '') AS workTypeName, " + // 2
	        " COALESCE(us.name, '') AS inspectedBy, " +    // 3
	        " ias.work_id, " +                             // 4
	        " COALESCE(md.district_name, '') AS districtName, " + // 5
	        " COALESCE(ld.line_department_name_e, '') AS line_department_name_e, " + // 6
	        " '' AS dummyColumn, " +                       // 7 (to maintain index alignment)
	        " COALESCE(mb.block_name, '') AS block_name, " + // 8
	        " COALESCE(mat.agency_type_name_e, '') AS agency_type_name_e, " + // 9
	        " COALESCE(mws.work_status_name_e, '') AS work_status_name_e " + // 10

	        "FROM inspection_details_new ias " +

	        "INNER JOIN work w ON w.id = ias.work_id " +
	        "INNER JOIN sqm_allocation sqma ON sqma.id = ias.sqm_allocation_id " +

	        "LEFT JOIN users us ON us.username = ias.inspected_by " +
	        "LEFT JOIN user_role usr ON usr.id = us.id " +
	        "LEFT JOIN mst_line_department ld ON ld.line_department_id = w.line_department_id " +
	        "LEFT JOIN mst_work_type mwt ON mwt.work_type_id = w.work_type_id " +
	        "LEFT JOIN mst_district md ON md.id = w.district_id " +
	        "LEFT JOIN mst_block mb ON mb.id = w.block_id " +
	        "LEFT JOIN mst_agency_type mat ON mat.agency_type_id = w.agency_type_id " +
	        "LEFT JOIN mst_work_status mws ON mws.id = w.work_status_id " +

	        "WHERE (w.status IS NULL OR w.status != 'Deleted') " +
	        "AND sqma.inspection_done = 1 " +
	        "AND COALESCE(usr.role_code, '') = 'ROLE_SQM' " +
	        "AND w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " +
	        "AND w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " +
	        "AND w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) " +
	        "AND ias.inspected_by = COALESCE(:sqmId1, ias.inspected_by) " +
	        "AND COALESCE(ias.work_type, '') = COALESCE(:grading1, COALESCE(ias.work_type, '')) " +

	        "GROUP BY w.id " +
	        "LIMIT :maxLimit OFFSET :offset",
	        nativeQuery = true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdmin(
	        @Param("exeOfficeId1") Long exeOfficeId1,
	        @Param("workStatusId1") Long workStatusId1,
	        @Param("workTypeId1") Long workTypeId1,
	        @Param("sqmId1") String sqmId1,
	        @Param("grading1") String grading1,
	        @Param("offset") int offset,
	        @Param("maxLimit") int maxLimit
	);

	
	@Query(value="Select w.work_name as WorkName,"
			+ "w.work_requisition_no,"
			+ "mwt.WORK_TYPE_NAME_E as workTypeName,"
			+ "us.name as inspectedBy,"
			+ "ias.work_id,"
			+ "d.district_name,"
			+ "ld.line_department_name_e,"
			+ "md.district_name as districtName,"
			+ "mb.block_name,"
			+ "mat.agency_type_name_e,"
			+ "mws.work_status_name_e FROM inspection_details_new ias "
			+ "inner join work w on w.id = ias.work_id "
			+ "inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id "
			+ "LEFT JOIN users us ON us.username = ias.inspected_by " 
			+ "LEFT JOIN user_role usr ON usr.id = us.id " 
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id "
			+ "left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id "
			+ "left join mst_block mb on mb.id = w.block_id "
			+ "left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id "
			+ "left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role!='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "AND ias.inspected_by = COALESCE(:sqmId1, ias.inspected_by)"
			+ "AND COALESCE(ias.work_type, '') = COALESCE(:grading1, COALESCE(ias.work_type, '')) group by ias.work_id limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findOfficerInspectionListBasedOnWorkForAdmin2(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") String sqmId1,@Param("grading1") String grading1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	@Query(value =
	        "SELECT " +
	        " w.work_name AS WorkName, " +                 // 0
	        " w.work_requisition_no, " +                   // 1
	        " COALESCE(mwt.WORK_TYPE_NAME_E, '') AS workTypeName, " + // 2
	        " COALESCE(us.name, '') AS inspectedBy, " +    // 3
	        " ias.work_id, " +                             // 4
	        " COALESCE(md.district_name, '') AS districtName, " + // 5
	        " COALESCE(ld.line_department_name_e, '') AS line_department_name_e, " + // 6
	        " '' AS dummyColumn, " +                       // 7 (to maintain index alignment)
	        " COALESCE(mb.block_name, '') AS block_name, " + // 8
	        " COALESCE(mat.agency_type_name_e, '') AS agency_type_name_e, " + // 9
	        " COALESCE(mws.work_status_name_e, '') AS work_status_name_e " + // 10

	        "FROM inspection_details_new ias " +

	        "INNER JOIN work w ON w.id = ias.work_id " +
	        "INNER JOIN sqm_allocation sqma ON sqma.id = ias.random_allocation_id " +

	        "LEFT JOIN users us ON us.username = ias.inspected_by " +
	        "LEFT JOIN user_role usr ON usr.id = us.id " +
	        "LEFT JOIN mst_line_department ld ON ld.line_department_id = w.line_department_id " +
	        "LEFT JOIN mst_work_type mwt ON mwt.work_type_id = w.work_type_id " +
	        "LEFT JOIN mst_district md ON md.id = w.district_id " +
	        "LEFT JOIN mst_block mb ON mb.id = w.block_id " +
	        "LEFT JOIN mst_agency_type mat ON mat.agency_type_id = w.agency_type_id " +
	        "LEFT JOIN mst_work_status mws ON mws.id = w.work_status_id " +

	        "WHERE (w.status IS NULL OR w.status != 'Deleted') " +
	        "AND sqma.inspection_done = 1 " +
	        "AND COALESCE(usr.role_code, '') != 'ROLE_SQM' " +
	        "AND w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " +
	        "AND w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " +
	        "AND w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) " +
	        "AND ias.inspected_by = COALESCE(:sqmId1, ias.inspected_by) " +
	        "AND COALESCE(ias.work_type, '') = COALESCE(:grading1, COALESCE(ias.work_type, '')) " +

	        "GROUP BY w.id " +
	        "LIMIT :maxLimit OFFSET :offset",
	        nativeQuery = true)
	List<Object[]> findOfficerInspectionListBasedOnWorkForAdmin(
	        @Param("exeOfficeId1") Long exeOfficeId1,
	        @Param("workStatusId1") Long workStatusId1,
	        @Param("workTypeId1") Long workTypeId1,
	        @Param("sqmId1") String sqmId1,
	        @Param("grading1") String grading1,
	        @Param("offset") int offset,
	        @Param("maxLimit") int maxLimit
	);
	
	@Query(value="SELECT \r\n"
			+ "    w.work_name AS WorkName,\r\n"
			+ "    w.work_requisition_no,\r\n"
			+ "    mwt.WORK_TYPE_NAME_E AS workTypeName,\r\n"
			+ "    us.name AS inspectedBy,\r\n"
			+ "    ias.work_id,\r\n"
			+ "    md.district_name AS districtName,\r\n"
			+ "    ld.line_department_name_e,\r\n"
			+ "    mb.block_name,\r\n"
			+ "    mat.agency_type_name_e,\r\n"
			+ "    mws.work_status_name_e\r\n"
			+ "\r\n"
			+ "FROM inspection_details_new ias\r\n"
			+ "\r\n"
			+ "INNER JOIN work w \r\n"
			+ "    ON w.id = ias.work_id\r\n"
			+ "\r\n"
			+ "INNER JOIN sqm_allocation sqma \r\n"
			+ "    ON sqma.id = ias.sqm_allocation_id\r\n"
			+ "\r\n"
			+ "LEFT JOIN users us \r\n"
			+ "    ON us.username = ias.inspected_by \r\n"
			+ "    \r\n"
			+ "    LEFT JOIN user_role usr \r\n"
			+ "    ON usr.id = us.id\r\n"
			+ "\r\n"
			+ "LEFT JOIN mst_line_department ld \r\n"
			+ "    ON ld.line_department_id = w.line_department_id\r\n"
			+ "\r\n"
			+ "LEFT JOIN mst_work_type mwt \r\n"
			+ "    ON mwt.work_type_id = w.work_type_id\r\n"
			+ "\r\n"
			+ "LEFT JOIN mst_district md \r\n"
			+ "    ON md.id = w.district_id\r\n"
			+ "\r\n"
			+ "LEFT JOIN mst_block mb \r\n"
			+ "    ON mb.id = w.block_id\r\n"
			+ "\r\n"
			+ "LEFT JOIN mst_agency_type mat \r\n"
			+ "    ON mat.agency_type_id = w.agency_type_id\r\n"
			+ "\r\n"
			+ "LEFT JOIN mst_work_status mws \r\n"
			+ "    ON mws.id = w.work_status_id\r\n"
			+ "\r\n"
			+ "WHERE \r\n"
			+ "    (w.status IS NULL OR w.status != 'Deleted')\r\n"
			+ "    AND sqma.inspection_done = 1\r\n"
			+ "    AND usr.role_code != 'ROLE_SQM'\r\n"
			+ "\r\n"
			+ "    AND w.executive_engineer_office_id = \r\n"
			+ "        COALESCE(:exeOfficeId1, w.executive_engineer_office_id)\r\n"
			+ "\r\n"
			+ "    AND w.work_status_id = \r\n"
			+ "        COALESCE(:workStatusId1, w.work_status_id)\r\n"
			+ "\r\n"
			+ "    AND w.work_type_id = \r\n"
			+ "        COALESCE(:workTypeId1, w.work_type_id)\r\n"
			+ "\r\n"
			+ "    AND ias.inspected_by = \r\n"
			+ "        COALESCE(:sqmId1, ias.inspected_by)"
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminWithQues(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role!='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findOfficerInspectionListBasedOnWorkForAdminWithQues(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	//findSqmInspectionListBasedOnWorkForAdminWithQuesCE
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.chief_engineer_office_id=:chief_engineer_office_id "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminWithQuesCE(@Param("chief_engineer_office_id") Long chief_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.superintending_engineer_office_id=:supt_engineer_office_id "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminWithQuesSupdt(@Param("supt_engineer_office_id") Long supt_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.executive_engineer_office_id=:executive_engineer_office_id "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminWithQuesEEE(@Param("executive_engineer_office_id") Long executive_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	//countIdisplaySqmInspectionListBasedOnWorkForAdminWithQues
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.chief_engineer_office_id=:chief_engineer_office_id "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id ", nativeQuery=true)
	List<Object[]> countIdisplaySqmInspectionListBasedOnWorkForAdminWithQuesCE(@Param("chief_engineer_office_id") Long chief_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1
			);
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.superintending_engineer_office_id=:supt_engineer_office_id "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id ", nativeQuery=true)
	List<Object[]> countIdisplaySqmInspectionListBasedOnWorkForAdminWithQuesSupdt(@Param("supt_engineer_office_id") Long supt_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1
			);
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.executive_engineer_office_id=:executive_engineer_office_id "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id ", nativeQuery=true)
	List<Object[]> countIdisplaySqmInspectionListBasedOnWorkForAdminWithQuesEEE(@Param("executive_engineer_office_id") Long executive_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1
			);
	//countIdisplaySqmInspectionListBasedOnWorkForAdminWithQuesCE
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id ", nativeQuery=true)
	List<Object[]> countIdisplaySqmInspectionListBasedOnWorkForAdminWithQues(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1
			);
	
	@Query(value="Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name,"
			+ "ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role!='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) "
			+ "and ifnull(ias.answer, '') = IFNULL(:grading1, ifnull(ias.answer, '')) and ias.question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') group by ias.work_id ", nativeQuery=true)
	List<Object[]> countIdisplayOfficerInspectionListBasedOnWorkForAdminWithQues(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1
			);
	
	//not applicable
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminNotApplicable(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role!='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findOfficerInspectionListBasedOnWorkForAdminNotApplicable(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.chief_engineer_office_id=:chief_engineer_office_id " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminNotApplicableCE(@Param("chief_engineer_office_id") Long chief_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) and w.superintending_engineer_office_id=:supt_engineer_office_id " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminNotApplicableSupdt(@Param("supt_engineer_office_id") Long supt_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) and w.executive_engineer_office_id=:executive_engineer_office_id " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null limit :offset,:maxLimit", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminNotApplicableEEE(@Param("executive_engineer_office_id") Long executive_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit
			);
	
	//findSqmInspectionListBasedOnWorkForAdminNotApplicableDisplaysize
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null ", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminNotApplicableDisplaysize(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1
			);
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role!='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null ", nativeQuery=true)
	List<Object[]> findOfficerInspectionListBasedOnWorkForAdminNotApplicableDisplaysize(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1
			);
	
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.chief_engineer_office_id=:chief_engineer_office_id " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null ", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminNotApplicableDisplaysizeCE(@Param("chief_engineer_office_id") Long chief_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1
			);
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.superintending_engineer_office_id=:supt_engineer_office_id " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null ", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminNotApplicableDisplaysizeSupdt(@Param("supt_engineer_office_id") Long supt_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1
			);
	
	
	@Query(value="select x.*, y.work_id as yworkId from ( "
			+ " " +
			"Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name," + 
			"			ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias " + 
			"			inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by " + 
			"			left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id " + 
			"			left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id " + 
			"			left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id " + 
			"			where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' " + 
			"			and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " + 
			"			and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " + 
			"			and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) " + 
			"			and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) and w.executive_engineer_office_id=:executive_engineer_office_id " + 
			"			" + 
			"            group by ias.work_id " + 
			") x " + 
			"" + 
			"LEFT JOIN (" + 
			"SELECT work_id FROM res_owms_prod.inspection_sqm_answer " + 
			"where question_text in ('Overall Observation - Grading','6.	Over all grading (S/SRI/U)') " + 
			"group by work_id " + 
			") y on x.work_id = y.work_id " + 
			"where y.work_id is null ", nativeQuery=true)
	List<Object[]> findSqmInspectionListBasedOnWorkForAdminNotApplicableDisplaysizeEEE(@Param("executive_engineer_office_id") Long executive_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1
			);
	
	
	@Query(value="select count(*) from ( Select w.work_name as WorkName,w.work_requisition_no,mwt.WORK_TYPE_NAME_E as workTypeName,us.name as inspectedBy,ias.work_id,d.district_name, ld.line_department_name_e,md.district_name as districtName,mb.block_name,mat.agency_type_name_e,mws.work_status_name_e,ias.question_text,ias.answer,ias.sqm_allocation_id,sqma.work_id as workId FROM inspection_sqm_answer ias inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) group by ias.sqm_allocation_id ) x LEFT JOIN ( SELECT work_id FROM res_owms_prod.inspection_sqm_answer where question_text in ('Overall Observation - Grading','6. Over all grading (S/SRI/U)') group by work_id ) y on x.work_id = y.work_id where y.work_id is null", nativeQuery=true)
	List<Object[]> countIdisplaySqmInspectionListBasedOnWorkForAdminNotApplicable(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1);
	
	
	
	
	
	
	
	
	
/*	@Query(value="Select count(*) FROM inspection_sqm_answer ias "
			+ "inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) "
			+ "and ias.answer = COALESCE(:grading1, ias.answer)", nativeQuery=true)
	long countIdisplaySqmInspectionListBasedOnWorkForAdmin(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1
			);*/
	
	@Query(value="Select count(ias.id) " + 
			"from " + 
			"(SELECT id as id FROM inspection_sqm_answer where ifnull(answer, '') = IFNULL(:grading1, ifnull(answer, '')) group by work_id) innerTable " 
			+ "left  join inspection_sqm_answer ias on ias.id=innerTable.id inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) "
			+ " ", nativeQuery=true)
		
	long countIdisplaySqmInspectionListBasedOnWorkForAdmin(@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1
			);
	
	
	@Query(value =
	        "SELECT COUNT(DISTINCT w.id) " +
	        "FROM inspection_details_new ias " +

	        "INNER JOIN work w ON w.id = ias.work_id " +
	        "INNER JOIN sqm_allocation sqma ON sqma.id = ias.random_allocation_id " +

	        "LEFT JOIN users us ON us.username = ias.inspected_by " +
	        "LEFT JOIN user_role usr ON usr.id = us.id " +

	        "WHERE (w.status IS NULL OR w.status != 'Deleted') " +
	        "AND sqma.inspection_done = 1 " +
	        "AND COALESCE(usr.role_code, '') != 'ROLE_SQM' " +
	        "AND w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " +
	        "AND w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " +
	        "AND w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) " +
	        "AND ias.inspected_by = COALESCE(:sqmId1, ias.inspected_by) " +
	        "AND COALESCE(ias.work_type, '') = COALESCE(:grading1, COALESCE(ias.work_type, ''))",
	        nativeQuery = true)
	Long countIdisplayOfficerInspectionListBasedOnWorkForAdmin(
	        @Param("exeOfficeId1") Long exeOfficeId1,
	        @Param("workStatusId1") Long workStatusId1,
	        @Param("workTypeId1") Long workTypeId1,
	        @Param("sqmId1") String sqmId1,
	        @Param("grading1") String grading1
	);
	
	//findSqmInspectionListBasedOnWorkForAdminNotApplicableDisplaysizeCE
	

	@Query(value =
	        "SELECT COUNT(DISTINCT w.id) " +
	        "FROM inspection_details_new ias " +

	        "INNER JOIN work w ON w.id = ias.work_id " +
	        "INNER JOIN sqm_allocation sqma ON sqma.id = ias.sqm_allocation_id " +

	        "LEFT JOIN users us ON us.username = ias.inspected_by " +
	        "LEFT JOIN user_role usr ON usr.id = us.id " +

	        "WHERE (w.status IS NULL OR w.status != 'Deleted') " +
	        "AND sqma.inspection_done = 1 " +
	        "AND COALESCE(usr.role_code, '') = 'ROLE_SQM' " +
	        "AND w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) " +
	        "AND w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) " +
	        "AND w.work_type_id = COALESCE(:workTypeId1, w.work_type_id) " +
	        "AND ias.inspected_by = COALESCE(:sqmId1, ias.inspected_by) " +
	        "AND COALESCE(ias.work_type, '') = COALESCE(:grading1, COALESCE(ias.work_type, '')) " +
	        "AND w.chief_engineer_office_id = :chief_engineer_office_id",
	        nativeQuery = true)
	Long countIdisplaySqmInspectionListBasedOnWork(
	        @Param("chief_engineer_office_id") Long chiefEngineerOfficeId,
	        @Param("exeOfficeId1") Long exeOfficeId1,
	        @Param("workStatusId1") Long workStatusId1,
	        @Param("workTypeId1") Long workTypeId1,
	        @Param("sqmId1") String sqmId1,
	        @Param("grading1") String grading1
	);
	
	@Query(value="Select count(ias.id) " + 
			"from " + 
			"(SELECT id as id FROM inspection_sqm_answer where ifnull(answer, '') = IFNULL(:grading1, ifnull(answer, '')) group by work_id) innerTable " 
			+ "left  join inspection_sqm_answer ias on ias.id=innerTable.id inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' and w.superintending_engineer_office_id=:supt_engineer_office_id "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) "
			+ " ", nativeQuery=true)
	long countIdisplaySqmInspectionListBasedOnWorkForSupdt(@Param("supt_engineer_office_id") Long supt_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1
			);
	
	@Query(value="Select count(ias.id) " + 
			"from " + 
			"(SELECT id as id FROM inspection_sqm_answer where ifnull(answer, '') = IFNULL(:grading1, ifnull(answer, '')) group by work_id) innerTable " 
			+ "left  join inspection_sqm_answer ias on ias.id=innerTable.id inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by "
			+ "left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id "
			+ "left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id "
			+ "left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id "
			+ "where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' and w.executive_engineer_office_id=:executive_engineer_office_id "
			+ "and w.executive_engineer_office_id = COALESCE(:exeOfficeId1, w.executive_engineer_office_id) "
			+ "and w.work_status_id = COALESCE(:workStatusId1, w.work_status_id) "
			+ "and w.work_type_Id = COALESCE(:workTypeId1, w.work_type_Id) "
			+ "and ias.answer_by = COALESCE(:sqmId1, ias.answer_by) "
			+ " ", nativeQuery=true)
	long countIdisplaySqmInspectionListBasedOnWorkForEE(@Param("executive_engineer_office_id") Long executive_engineer_office_id,@Param("exeOfficeId1") Long exeOfficeId1,@Param("workStatusId1") Long workStatusId1,@Param("workTypeId1") Long workTypeId1,
			@Param("sqmId1") Long sqmId1,@Param("grading1") String grading1
			);
	
	//countIdisplaySqmInspectionListBasedOnWorkForSupdt

	/*List<InspectionSqmAnswer> findInspectionAnswersForSqmByWorkId(Work work);*/
	
	/*long countIdisplaySqmInspectionListBasedOnWork(Long loggedInOfficeId, Long exeOfficeId1, Long workStatusId1,
			Long workTypeId1, Long sqmId1, String grading1);*/
	
	@Query("from InspectionSqmAnswer ia where ia.sqmAllocationId= :sqmAllocationId and ia.answerByRole = 'ROLE_SQM' and ia.parentId is null")
	public List<InspectionSqmAnswer> findInspectionAnswersForSqmBySqmAllocationId(@Param("sqmAllocationId") SqmAllocation sqmAllocationId);

	String findByQuestionText(String string);

	@Query(value =
	        "SELECT " +
	        " w.work_name AS WorkName, " +                 // 0
	        " w.work_requisition_no, " +                   // 1
	        " COALESCE(mwt.WORK_TYPE_NAME_E, '') AS workTypeName, " + // 2
	        " COALESCE(us.name, '') AS inspectedBy, " +    // 3
	        " ias.work_id, " +                             // 4
	        " COALESCE(md.district_name, '') AS districtName, " + // 5
	        " COALESCE(ld.line_department_name_e, '') AS line_department_name_e, " + // 6
	        " '' AS dummyColumn, " +                       // 7 (to maintain index alignment)
	        " COALESCE(mb.block_name, '') AS block_name, " + // 8
	        " COALESCE(mat.agency_type_name_e, '') AS agency_type_name_e, " + // 9
	        " COALESCE(mws.work_status_name_e, '') AS work_status_name_e " + // 10

	        "FROM inspection_details_new ias " +

	        "INNER JOIN work w ON w.id = ias.work_id " +
	        "INNER JOIN sqm_allocation sqma ON sqma.id = ias.sqm_allocation_id " +

	        "LEFT JOIN users us ON us.username = ias.inspected_by " +
	        "LEFT JOIN user_role usr ON usr.id = us.id " +
	        "LEFT JOIN mst_line_department ld ON ld.line_department_id = w.line_department_id " +
	        "LEFT JOIN mst_work_type mwt ON mwt.work_type_id = w.work_type_id " +
	        "LEFT JOIN mst_district md ON md.id = w.district_id " +
	        "LEFT JOIN mst_block mb ON mb.id = w.block_id " +
	        "LEFT JOIN mst_agency_type mat ON mat.agency_type_id = w.agency_type_id " +
	        "LEFT JOIN mst_work_status mws ON mws.id = w.work_status_id " +

	        "WHERE (w.status IS NULL OR w.status != 'Deleted') " +
	        "AND sqma.inspection_done = 1 " +
	        "AND COALESCE(usr.role_code, '') = 'ROLE_SQM' " +
	        "AND w.chief_engineer_office_id=?1 " +
	        "GROUP BY w.id ", nativeQuery=true)
	List<Object[]> countSqmInspectionListBasedOnWork(Long loggedInOfficeId);

	@Query(value="Select w.id, w.work_name FROM inspection_sqm_answer ias inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id where (w.status is null or w.status!='Deleted') and w.superintending_engineer_office_id=?1 and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' group by ias.work_id", nativeQuery=true)
	List<Object[]> countSqmInspectionListBasedOnWorkForSupdt(Long loggedInOfficeId);

	@Query(value="Select w.id, w.work_name FROM inspection_details_new ias inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.username=ias.inspected_by  LEFT JOIN user_role usr \r\n"
			+ "    ON usr.id = us.id left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and usr.role_code='ROLE_SQM' group by ias.work_id", nativeQuery=true)
	List<Object[]> countSqmInspectionListBasedOnWorkForAdmin();
	
	@Query(value="Select w.id, w.work_name FROM inspection_sqm_answer ias inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id where (w.status is null or w.status!='Deleted') and w.executive_engineer_office_id=?1 and sqma.inspection_done=1 and ias.answer_by_role='ROLE_SQM' group by ias.work_id", nativeQuery=true)
	List<Object[]> countSqmInspectionListBasedOnWorkForEE(Long loggedInOfficeId);
	
	public	List<InspectionSqmAnswer> findBySqmAllocationIdIdAndCodeOrderByCreatedDateDesc(Long id, String code);

	List<InspectionSqmAnswer> findByWork(Work work);

	List<InspectionSqmAnswer> findBySqmAllocationId(SqmAllocation sqmAllocation);

	
	
	List<InspectionSqmAnswer> findByWorkAndAnswerByAndSqmAllocationId(Work work, Users users,
			SqmAllocation sqmAllocation);
	
	/*
	 * @Query(
	 * value="Select w.id, w.work_name FROM inspection_sqm_answer ias inner join work w on w.id = ias.work_id inner join sqm_allocation sqma on sqma.id = ias.sqm_allocation_id left join users us on us.id=ias.answer_by left join mst_line_department ld on ld.line_department_id=w.line_department_id left join mst_work_type mwt on mwt.work_type_Id=w.work_type_id left join mst_district md on md.id = w.district_id left join mst_block mb on mb.id = w.block_id left join mst_agency_type mat on mat.agency_type_id = w.agency_type_id left join mst_work_status mws on mws.id = w.work_status_id left join mst_district d on d.id=w.district_id where (w.status is null or w.status!='Deleted') and sqma.inspection_done=1 and ias.answer_by_role!='ROLE_SQM' group by ias.work_id"
	 * , nativeQuery=true) List<Object[]>
	 * countOfficerInspectionListBasedOnWorkForAdmin();
	 */
	
	@Query(value =
	        "SELECT w.id, COUNT(w.id) " +
	        "FROM inspection_details_new ias " +
	        "INNER JOIN work w ON w.id = ias.work_id " +
	        "INNER JOIN sqm_allocation sqma ON sqma.id = ias.random_allocation_id " +
	        "LEFT JOIN users us ON us.username = ias.inspected_by " +
	        "LEFT JOIN user_role usr ON usr.id = us.id " +
	        "WHERE (w.status IS NULL OR w.status != 'Deleted') " +
	        "AND sqma.inspection_done = 1 " +
	        "AND (usr.role_code IS NULL OR usr.role_code <> 'ROLE_SQM') " +
	        "GROUP BY w.id",
	        nativeQuery = true)
	List<Object[]> countOfficerInspectionListBasedOnWorkForAdmin();

	@Query("from InspectionSqmAnswer ia where ia.sqmAllocationId= :sqmAllocationId and ia.answerByRole!= 'ROLE_SQM' and ia.parentId is null")
	public List<InspectionSqmAnswer> findInspectionAnswersForOfficerBySqmAllocationId(@Param("sqmAllocationId") SqmAllocation sqmAllocationId);

	
    
	
	
	
}
