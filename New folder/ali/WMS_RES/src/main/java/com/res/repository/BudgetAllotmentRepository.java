package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.AccountHead;
import com.res.entity.BudgetAllotment;
import com.res.entity.Office;
import com.res.entity.Work;

public interface BudgetAllotmentRepository  extends JpaRepository<BudgetAllotment, Long>, CrudRepository<BudgetAllotment, Long> {

/*	Page<BudgetAllotment> findByAccountHead(Pageable pageable, AccountHead accountHead);*/
	
	
	
	@Query("from BudgetAllotment w where  w.accountHead=COALESCE(:accountHead, w.accountHead)")
	Page<BudgetAllotment> findByAccountHead(
			Pageable pageable, @Param("accountHead") Long accountHead
			);
	
	
	/*@Query("Select sum(amount),accountHead.id,id from BudgetAllotment w where  w.accountHead=COALESCE(:accountHead, w.accountHead) group by w.accountHead.id,w.id ")
	Page<BudgetAllotment> findByAccountHeadGroupBy(
			Pageable pageable, @Param("accountHead") Long accountHead
			);*/
	
	@Query(value="SELECT Account_head,account_head_name_e,sum(amount),Received_On,ba.created_date,ba.modified_date,ba.created_by,ba.modified_by FROM res_owms_prod.budget_allotment ba left join mst_account_head ah on ah.id = ba.Account_head where Account_head = COALESCE(:accountHead, Account_head)  group by Account_head limit :offset,:maxLimit ",nativeQuery=true)
	List<Object[]> findByAccountHeadGroupBy( @Param("accountHead") Long accountHead,@Param("offset") int offset,
			@Param("maxLimit") int maxLimit);
	
	
	@Query(value="SELECT Account_head,account_head_name_e,sum(amount),Received_On,ba.created_date,ba.modified_date,ba.created_by,ba.modified_by FROM res_owms_prod.budget_allotment ba left join mst_account_head ah on ah.id = ba.Account_head group by Account_head ",nativeQuery=true)
	List<Object[]> findByAccountHeadGroupByTotalRecord();
	
	@Query(value="SELECT Account_head,account_head_name_e,sum(amount),Received_On,ba.created_date,ba.modified_date,ba.created_by,ba.modified_by FROM res_owms_prod.budget_allotment ba left join mst_account_head ah on ah.id = ba.Account_head where Account_head = COALESCE(:accountHead, Account_head)  group by Account_head ",nativeQuery=true)
	List<Object[]> findByAccountHeadGroupByTotalDispRecord( @Param("accountHead") Long accountHead);
	
}
