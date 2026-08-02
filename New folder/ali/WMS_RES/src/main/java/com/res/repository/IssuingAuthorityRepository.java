package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.IssuingAuthority;

public interface IssuingAuthorityRepository  extends JpaRepository<IssuingAuthority, Long> {
	
	List<IssuingAuthority> findByEnabled(Short isEnabled);
    
}
