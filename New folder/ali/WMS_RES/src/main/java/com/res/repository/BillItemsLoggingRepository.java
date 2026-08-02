package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.Bill;
import com.res.entity.BillItemsLogging;

public interface BillItemsLoggingRepository  extends JpaRepository<BillItemsLogging, Long> , CrudRepository<BillItemsLogging, Long> {
    
	public List<BillItemsLogging> findByBill(Bill bill); 
	
}
