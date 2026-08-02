package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.AccountHead;

public interface AccountHeadRepository extends CrudRepository<AccountHead, Long> {

	List<AccountHead> findByEnabled(Short isEnabled);
	
	
	Page<AccountHead> findByEnabled(Pageable pageable, Short isEnabled);
	
	
/*	@Query("from Users u where (u.name like %:name% or u.emailId like %:emailId%) and status != :status")
	Page<Users> findByNameContainingOrEmailIdContainingAndStatusNotAndIsOICNotNull(Pageable pageable, @Param("name")String name, 
			@Param("emailId")String emailId, @Param("status")String status);*/

}
