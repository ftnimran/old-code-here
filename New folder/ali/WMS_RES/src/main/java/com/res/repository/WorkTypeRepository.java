package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.Users;
import com.res.entity.WorkType;

public interface WorkTypeRepository extends CrudRepository<WorkType, Long>,
		WorkTypeRepositoryCustom {

	List<WorkType> findByEnabled(Short isEnabled);
	
	@Query("from Users u where (u.name like %:name% or u.emailId like %:emailId%) and status != :status")
	Page<Users> findByNameContainingOrEmailIdContainingAndStatusNotAndIsOICNotNull(Pageable pageable, @Param("name")String name, 
			@Param("emailId")String emailId, @Param("status")String status);

}
