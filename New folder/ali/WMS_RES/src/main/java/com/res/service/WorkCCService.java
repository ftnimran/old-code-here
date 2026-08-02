package com.res.service;

import org.springframework.data.domain.Pageable;

import com.res.bean.CCDetailsBean;
import com.res.bean.CCDispatchDetailsBean;
import com.res.json.WorkJson;

public interface WorkCCService { 

	public String initiateCCSubmit(CCDetailsBean ccDetailsBean);
	
	public WorkJson fetchWorkPhysicalCCList(Pageable pageable,
			String searchBoxVal, String loggedInUserRole, String username);
	
	public WorkJson fetchWorkFinancialCCList(Pageable pageable,
			String searchBoxVal, String loggedInUserRole, String username);	
	
	public String addPhysicalCCDispatchDetails(CCDispatchDetailsBean ccDispatchDetailsBean);
	
	public String addFinancialCCDispatchDetails(CCDispatchDetailsBean ccDispatchDetailsBean);
	
	public CCDispatchDetailsBean fetchPhysicalCCDispatchDetailsByWorkId(Long workid);
	
	public CCDispatchDetailsBean fetchFinancialCCDispatchDetailsByWorkId(Long workid);	
	
	public String issuePhysicalCCForWorkId(Long workid);
	
	public String issueFinancialCCForWorkId(Long workid);
} 
