package com.res.repository;

import java.util.List;

import com.res.bean.DashboardBean;

public interface WorkTypeRepositoryCustom {
	public List<DashboardBean> workTypeWiseDashboard(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId);

	public List<DashboardBean> workSubTypeWiseDashboard(Integer workTypeId,
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId);

	public List<DashboardBean> getDashboardDataForDistrictWiseWorksStatus(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId,
			final Integer subEngId, final Boolean isThematicMap,
			Integer firstResult, Integer pageDisplayLength);

	public List<DashboardBean> getDashboardDataForStatusWisePieChart(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId);

	public List<DashboardBean> getDashboardDataForDistrictWisePieChart(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId);
	
	
	public List<DashboardBean> workTypeWiseLineDeptDashboard(final Integer lineDeptId);
	
	public List<DashboardBean> districtWiseLineDeptPieChart(final Integer lineDeptId);
	
	public List<DashboardBean> districtWiseLineDeptDashboard(final Integer lineDeptId);
	
	public List<DashboardBean> statusWiseLineDeptDashboard(final Integer lineDeptId);
	
	public List<DashboardBean> ceOfficeWiseWorksCount();
	
	public List<DashboardBean> seOfficeWiseWorksCount(final Integer ceOfficeId);
	
	public List<DashboardBean> eeOfficeWiseWorksCount(final Integer seOfficeId);
	
	public List<DashboardBean> getDashboardDataForWorkRequestStatusWiseCount(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId);
	
	//getDashboardDataForWorkRequestStatusWiseCountWithSelection
	public List<DashboardBean> getDashboardDataForWorkRequestStatusWiseCountWithSelection(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId,final String executionAgencyId,final String exeOfficeId,final String lineDepartmentId
			,final String accountHeadId,final String workStatusId,final String workTypeId,final String workSubTypeId);
	//getDashboardPendingForInspectionCountWithSelection
	
	public List<DashboardBean> getDashboardPendingForInspectionCountWithSelection(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId,final String currentFY,final String executionAgencyId,final String exeOfficeId,final String lineDepartmentId
			,final String accountHeadId,final String workTypeId,final String workSubTypeId);
	
	public List<DashboardBean> getDashboardFinalBillPendingCountWithSelection(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId,final String currentFY,final String executionAgencyId,final String exeOfficeId,final String lineDepartmentId
			,final String accountHeadId,final String workTypeId,final String workSubTypeId);
	
	//getDashboardPhysicalCCDispatchCountWithSelection
	
	public List<DashboardBean> getDashboardPhysicalCCDispatchCountWithSelection(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId,final String executionAgencyId,final String lineDepartmentId
			,final String accountHeadId,final String workTypeId,final String workSubTypeId);
	
	public List<DashboardBean> getFYWiseExpenditureList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String fyFrom, final String fyTo);
	
	public List<DashboardBean> getExAgWiseExpenditureList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String fyFrom, final String fyTo,final String executionAgencyId);
	
	public List<DashboardBean> getPaymentWiseExpenditureList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String currentFY,final String executionAgencyId,final String exeOfficeId,final String lineDepartmentId
			,final String accountHeadId,final String workStatusId,final String workTypeId,final String workSubTypeId);
	
	public List<DashboardBean> getExeAgWiseExpenditureList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String financialYearId,final String executionAgencyId,final String exeOfficeId,final String lineDepartmentId
			,final String accountHeadId,final String workStatusId,final String month,final String workTypeId,final String workSubTypeId);
	
	public List<DashboardBean> getEeWiseExpenditureForMonthYearList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year);
	
	public List<DashboardBean> getAccountHeadWiseExpenditureForMonthYearAndEeList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year);
	
	public List<DashboardBean> getLineDeptWiseExpenditureForMonthYearAndEeList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year);
	
	public List<DashboardBean> getWorkTypeWiseExpenditureForMonthYearAndEeList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year);
	
	public List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndWorkTypeList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year, final Integer workType);
	
	public List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndLineDeptList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year, final Integer lineDept);
	
	public List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndAccHeadList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId, final Integer aeId, final Integer subEngId,
			final String month, final String year, final Integer accHead);
	
	
}

