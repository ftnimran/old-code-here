package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.res.entity.SqmAllocation;
import com.res.entity.SqmAllocationHistory;
import com.res.entity.Users;

public interface SqmAllocationHistoryRepository  extends JpaRepository<SqmAllocationHistory, Long> {
	
	//List<OfficeType> findByEnabled(Short isEnabled);
//public	List<SqmAllocation> findByUsers(Users users);
//@Query("select s.officeId from #{#SqmAllocation} s")
//List<Long> getAllOfficeIds();
//public	List<SqmAllocation> findDistinctByOfficeIdAndUsers(Users users);
    
}
