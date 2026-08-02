package com.res.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.MasterInspectionQuestion;

public interface MasterInspectionQuestionRepository extends JpaRepository<MasterInspectionQuestion, Long>, CrudRepository<MasterInspectionQuestion, Long> {

	
	
}
