package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.State;

public interface StateRepository  extends JpaRepository<State, Long> {
	
	State findByStateName(String stateName);
	
	List<State> findByEnabled(Short isEnabled);
    
}
