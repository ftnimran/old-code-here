package com.res.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.res.entity.Bill;
import com.res.entity.ContengencyTable;
import com.res.entity.Work;

public interface ContengencyRepository  extends CrudRepository<ContengencyTable, Long> {

	List<ContengencyTable> findByBill(Bill bill);	

	List<ContengencyTable> findByWork(Work work);
 

}
