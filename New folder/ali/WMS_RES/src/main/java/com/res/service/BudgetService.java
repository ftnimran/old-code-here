package com.res.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.res.bean.BudgetAllotmentBean;
import com.res.bean.BudgetAllotmentEEOfficeBean;
import com.res.bean.BudgetRequestBean;
import com.res.json.BudgetAllotmentEEOfficeJson;
import com.res.json.BudgetAllotmentJson;
import com.res.json.BudgetRequestDetailJson;
import com.res.json.BudgetRequestJson;

public interface BudgetService {

	public BudgetRequestJson fetchBudgetRequestList(Pageable pageable, String searchBoxVal, String loggedInUserRole, String username);
	
	public BudgetRequestDetailJson fetchBudgetRequestDetailList(Pageable pageable, Long id, String loggedInUserRole, String username);
	
	public String saveBudgetRequest(BudgetRequestBean budgetRequestBean);
	
	public String saveEditBudgetRequest(BudgetRequestBean budgetRequestBean);
	
	public BudgetAllotmentJson fetchBudgetAllotmentList(Pageable pageable, String loggedInUserRole, String username, String accountHeadId);
	
	public String saveBudgetAllotment(BudgetAllotmentBean budgetAllotmentBean);

	public BudgetAllotmentEEOfficeJson fetchBudgetRequestAllotementList(Pageable pageable, Long id,
			String loggedInUserRole, String username);

	public BudgetRequestBean fetchBudgetRequest(Long budgetRequestId);

	public List<BudgetAllotmentEEOfficeBean> fetchBudgetAllotmentEEOffice(Long budgetRequestId);
	
	public BudgetAllotmentBean fetchBudgetAllotment(Long budgetAllotmentId);

	public BudgetAllotmentJson fetchBudgetAllotmentListAccHeadWise(Pageable pageable, String loggedInUserRole,
			String username, String accountHeadId);

	public BudgetAllotmentJson fetchBudgetAllotmentListAllAccHead(Pageable pageable, Long accountHeadId,
			String loggedInUserRole, String username);

	public String saveSurrenderRequest(BudgetRequestBean budgetRequestBean);
}
