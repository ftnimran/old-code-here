package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.InspectionAnswersNew;
import com.res.entity.InspectionSqmAnswer;

public interface InspectionAnswerRepositoryNew
		extends JpaRepository<InspectionAnswersNew, Long>, CrudRepository<InspectionAnswersNew, Long> {

	//public List<InspectionAnswersNew> findByBill(Bill bill);
	

	public List<InspectionAnswersNew> findByInspection(com.res.entity.InspectionDetails inspection); 
	
	//public	List<InspectionAnswersNew> findBySqmAllocationIdIdAndCodeOrderByCreatedDateDesc(Long id, String code);

	
	

}
