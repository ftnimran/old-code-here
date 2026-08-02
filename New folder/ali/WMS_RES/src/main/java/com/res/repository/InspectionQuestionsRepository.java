package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.InspectionAnswer;

public interface InspectionQuestionsRepository extends JpaRepository<InspectionAnswer, Integer> {

	@Query(
		    value = "SELECT " +
		            "wt.work_type_id, wt.work_type_name_e, " +
		            "it.id AS inspection_type_id, it.inspection_type, " +
		            "m.id AS question_id, m.inspection_name, m.Data_Type, m.Data_values " +
		            "FROM mst_work_type wt " +
		            "JOIN tbl_work_inspection_type_mapping m " +
		            "ON wt.work_type_id = m.work_type_id " +
		            "JOIN mst_inspection_type it " +
		            "ON it.id = m.inspection_type_id " +
		            "WHERE wt.enabled = 1 " +
		            "AND it.enabled = 1 " +
		            "AND wt.work_type_id = :workTypeId " +
		            "ORDER BY  m.id asc",
		    nativeQuery = true
		)
		List<Object[]> fetchInspectionChecklist(
		        @Param("workTypeId") Integer workTypeId
		);
		
		@Query(
			    value = "SELECT m.id AS question_id, m.inspection_name " +  // <-- space added
			            "FROM tbl_work_inspection_type_mapping m " +
			            "WHERE m.id = :questionId",
			    nativeQuery = true
			)
			Object[] fetchQuestionsById(@Param("questionId") Long questionId);

}

