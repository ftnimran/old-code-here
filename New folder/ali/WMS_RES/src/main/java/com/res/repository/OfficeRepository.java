package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.Office;
import com.res.entity.OfficeType;

public interface OfficeRepository  extends JpaRepository<Office, Long> {
	
	List<Office> findByEnabled(Short isEnabled);
    
	List<Office> findByOfficeType(OfficeType officeType);
	
	Office findById(Long id);
	
	List<Office> findByParentOfficeNotNull();
	
	List<Office> findByParentOfficeAndEnabled(Office parentOffice, Short enabled);

	List<Office> findByOfficeTypeAndEnabled(OfficeType officeType, short s);

	List<Office> findByOfficeTypeAndParentOfficeAndEnabled(OfficeType officeType, Office office, short s);

	List<Office> findByParentOfficeIn(List<Office> list1);

	/*List<Office> findByIdAndEnabled(Long officeId, short s);*/

	List<Office> findByIdAndEnabled(Long officeId, short s);

	List<Office> findBlockIdById(Long officeId);
	
	
	
	
}
