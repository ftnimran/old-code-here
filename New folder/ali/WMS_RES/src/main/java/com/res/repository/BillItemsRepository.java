package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.Bill;
import com.res.entity.BillItems;

public interface BillItemsRepository  extends JpaRepository<BillItems, Long> {
    
	List<BillItems> findByBill(Bill bill);
	
	void deleteInBatch(Iterable<BillItems> entities);
	
	
}
