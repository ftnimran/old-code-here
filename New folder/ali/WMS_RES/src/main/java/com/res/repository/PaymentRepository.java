package com.res.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.res.entity.Bill;
import com.res.entity.PaymentTable;

public interface PaymentRepository extends CrudRepository<PaymentTable, Long> {

	List<PaymentTable> findByBill(Bill bill);	
 

}
