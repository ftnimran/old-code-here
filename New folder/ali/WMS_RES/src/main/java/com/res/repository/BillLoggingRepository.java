package com.res.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.Bill;
import com.res.entity.BillLogging;
import com.res.entity.MasterBillStatus;

public interface BillLoggingRepository  extends JpaRepository<BillLogging, Long>, CrudRepository<BillLogging, Long> {
    
	public BillLogging findByBillAndStatus(Bill bill, MasterBillStatus status); 
	 
	
}
