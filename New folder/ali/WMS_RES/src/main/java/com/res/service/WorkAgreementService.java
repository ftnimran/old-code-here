package com.res.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.res.bean.FinancialStageTypeBean;
import com.res.bean.WorkAgreementBean;
import com.res.bean.WorkAgreementRevisionBean;
import com.res.json.WorkAgreementJson;


public interface WorkAgreementService { 

	public WorkAgreementJson fetchWorkAgreementList(Pageable pageable,
			String searchBoxVal, String loggedInUserRole, String username);
	public WorkAgreementJson fetchWorkAgreementList(Pageable pageable,
			String searchBoxVal, String loggedInUserRole, String username,Long workId,Long tenderId);
	
	public String addWorkAgreement(WorkAgreementBean workAgreementBean);
	
	public String addRevisedWorkAgreement(WorkAgreementBean workAgreementBean);
	
	public String updateWorkAgreement(WorkAgreementBean workAgreementBean);

	public WorkAgreementBean fetchWorkAgreementDetails(Long workId, boolean milestoneRequired);
	public WorkAgreementBean fetchWorkAgreementDetailsByTenderId(Long id, boolean milestoneRequired);
	
	
	public List<FinancialStageTypeBean> fetchFinancialMileStone();	
	
	public List<WorkAgreementRevisionBean> fetchMilestoneRevisionByWorkAgreementId(Long workAgreementId);
	
	public WorkAgreementRevisionBean fetchMilestoneRevisionByWorkAgreementRevisionId(Long revisionId);
	
} 
