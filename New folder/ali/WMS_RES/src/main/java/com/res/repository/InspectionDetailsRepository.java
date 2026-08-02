package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.res.entity.InspectionDetails;
import com.res.entity.Work;

@Repository
public interface InspectionDetailsRepository extends JpaRepository<InspectionDetails, Long> {

	InspectionDetails findByBillId(Long billId);
	
	InspectionDetails findByWorkId(Long workId);
	
	List<InspectionDetails> findByWorkIdAndGeneralInspectionDone(Long workId, short s);
	

	InspectionDetails findByIdAndGeneralInspectionDone(Long inspectionId, short s);
	
	InspectionDetails findById(Long inspectionId);
	
	InspectionDetails findByWorkIdAndSqmAllocationId(Long workId, Long sqmAllocationId);
	
	InspectionDetails findByWorkIdAndRandomAllocationId(Long workId, Long randomAllocationId);
	
	
	
	List<InspectionDetails> findByRandomAllocationId(Long randomAllocationId);
	List<InspectionDetails> findBySqmAllocationId(Long sqmAllocationId);

	Page<InspectionDetails> findByWorkIdAndGeneralInspectionDone(
	        Long workId,
	        Short generalInspectionDone,
	        Pageable pageable);
}

