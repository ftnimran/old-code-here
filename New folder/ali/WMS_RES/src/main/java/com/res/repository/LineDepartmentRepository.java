package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.LineDepartment;

public interface LineDepartmentRepository  extends JpaRepository<LineDepartment, Long> {
	
	List<LineDepartment> findByEnabled(Short isEnabled);

	Page<LineDepartment> findByEnabled(Pageable pageable, Short isEnabled);
    
}
