package com.res.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.DocumentUpload;

public interface DocumentRepository  extends JpaRepository<DocumentUpload, Long> {

	DocumentUpload findByDocumentName(String imageName);
    
	/*Page<Entrepreneur> findByStatusNot(Pageable pageable, String status);
	
	long countByStatusNot(String status);
	
	List<Entrepreneur> findByStatusAndParentAccountIsNull(String status);
	
	@Query("from Entrepreneur e where (e.entrepreneurName like %:entrepreneurName% or e.accountNo like %:accountNo%) and status != :status")
	Page<Entrepreneur> findByEntrepreneurNameContainingOrAccountNoContainingAndStatusNot(Pageable pageable, @Param("entrepreneurName")String entrepreneurName, 
			@Param("accountNo")String accountNo, @Param("status")String status);
	
	@Query("from Entrepreneur e where (e.entrepreneurType like %:entrepreneurType% and e.district.districtName like %:districtName% and e.status like :status%)")
	Page<Entrepreneur> findByEntrepreneurTypeOrDistrictNameOrStatus(Pageable pageable, @Param("entrepreneurType")String entrepreneurType, 
			@Param("districtName")String districtName, @Param("status")String status);
	
	@Query("from Entrepreneur e where (e.entrepreneurName like %:entrepreneurName% or e.accountNo like %:accountNo%) and (e.entrepreneurType like %:entrepreneurType%"
			+ " and e.district.districtName like %:districtName% and e.status like :status%)")
	Page<Entrepreneur> findByEntrepreneurNameContainingOrAccountNoContainingAndEntrepreneurTypeOrDistrictNameOrStatus(Pageable pageable, @Param("entrepreneurName")String entrepreneurName, 
			@Param("accountNo")String accountNo, @Param("entrepreneurType")String entrepreneurType, @Param("districtName")String districtName, @Param("status")String status);

*/
}
