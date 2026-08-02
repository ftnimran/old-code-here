package com.res.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.res.entity.PaymentDetail;
import com.res.entity.PaymentTable;

public interface PaymentDetailRepository extends CrudRepository<PaymentDetail, Long> {

	List<PaymentDetail> findByPaymentTable(PaymentTable paymentTable);	
 

}
