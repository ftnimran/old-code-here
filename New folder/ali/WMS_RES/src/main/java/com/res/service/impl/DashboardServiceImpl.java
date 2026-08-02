package com.res.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.res.bean.DashboardBean;
import com.res.repository.WorkTypeRepository;
import com.res.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private WorkTypeRepository workTypeRepository;

	public List<DashboardBean> getDashboardDataWorkTypeWise(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId) {
		return workTypeRepository.workTypeWiseDashboard(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId);
	}

	public List<DashboardBean> getDashboardDataWorkSubTypeWise(
			final Integer workTypeId, final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId) {
		return workTypeRepository.workSubTypeWiseDashboard(workTypeId,
				ceOfficeId, seOfficeId, eeOfficeId, aeId, subEngId);
	}

	public List<DashboardBean> getDashboardDataForDistrictWiseWorksStatus(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId,
			final Integer subEngId, final Boolean isThematicMap,
			final Integer firstResult, final Integer pageDisplayLength) {
		return workTypeRepository.getDashboardDataForDistrictWiseWorksStatus(
				ceOfficeId, seOfficeId, eeOfficeId, aeId, subEngId,
				isThematicMap, firstResult, pageDisplayLength);
	}

	public List<DashboardBean> getDashboardDataForStatusWisePieChart(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId) {
		return workTypeRepository.getDashboardDataForStatusWisePieChart(
				ceOfficeId, seOfficeId, eeOfficeId, aeId, subEngId);
	}

	public List<DashboardBean> getDashboardDataForDistrictWisePieChart(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId) {
		return workTypeRepository.getDashboardDataForDistrictWisePieChart(
				ceOfficeId, seOfficeId, eeOfficeId, aeId, subEngId);
	}
	
	public List<DashboardBean> getDashboardDataWorkTypeWiseLineDept(
			final Integer lineDeptId) {
		return workTypeRepository.workTypeWiseLineDeptDashboard(lineDeptId);
	}
	public List<DashboardBean> getDashboardDataWorkStatusWiseLineDept(
			final Integer lineDeptId) {
		return workTypeRepository.statusWiseLineDeptDashboard(lineDeptId);
	}
	public List<DashboardBean> getDashboardDataDistrictWiseLineDeptPieChart(
			final Integer lineDeptId) {
		return workTypeRepository.districtWiseLineDeptPieChart(lineDeptId);
	}
	
	public List<DashboardBean> ceOfficeWiseWorksCount() {
		return workTypeRepository.ceOfficeWiseWorksCount();
	}
	
	public List<DashboardBean> seOfficeWiseWorksCount(final Integer ceOfficeId) {
		return workTypeRepository.seOfficeWiseWorksCount(ceOfficeId);
	}
	
	public List<DashboardBean> eeOfficeWiseWorksCount(final Integer seOfficeId) {
		return workTypeRepository.eeOfficeWiseWorksCount(seOfficeId);
	}
	
	public List<DashboardBean> getDashboardDataForWorkRequestStatusWiseCount(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId) {
		return workTypeRepository.getDashboardDataForWorkRequestStatusWiseCount(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId);
	}
	//getDashboardDataForWorkRequestStatusWiseCountWithSelection
	
	public List<DashboardBean> getDashboardDataForWorkRequestStatusWiseCountWithSelection(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId,final String executionAgencyId,final String exeOfficeId
			,final String lineDepartmentId,final String accountHeadId,final String workStatusId,final String workTypeId,final String workSubTypeId) {
		return workTypeRepository.getDashboardDataForWorkRequestStatusWiseCountWithSelection(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
	}
	
	public List<DashboardBean> getDashboardPendingForInspectionCountWithSelection(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId,final String currentFY,final String executionAgencyId
			,final String exeOfficeId,final String lineDepartmentId,final String accountHeadId,final String workTypeId,final String workSubTypeId) {
		return workTypeRepository.getDashboardPendingForInspectionCountWithSelection(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
	}
	
	public List<DashboardBean> getDashboardFinalBillPendingCountWithSelection(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId,String currentFY,final String executionAgencyId
			,final String exeOfficeId,final String lineDepartmentId,final String accountHeadId,final String workTypeId,final String workSubTypeId) {
		return workTypeRepository.getDashboardFinalBillPendingCountWithSelection(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
	}
	
	//getDashboardPhysicalCCDispatchCountWithSelection
	
	public List<DashboardBean> getDashboardPhysicalCCDispatchCountWithSelection(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId,final String executionAgencyId
			,final String lineDepartmentId,final String accountHeadId,final String workTypeId,final String workSubTypeId) {
		return workTypeRepository.getDashboardPhysicalCCDispatchCountWithSelection(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
	}
	
	public List<DashboardBean> getFYWiseExpenditureList(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String fyFrom, final String fyTo) {
		return workTypeRepository.getFYWiseExpenditureList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, fyFrom, fyTo);
	}
	
	public List<DashboardBean> getExAgWiseExpenditureList(final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String fyFrom, final String fyTo,final String executionAgencyId
			) {
		return workTypeRepository.getExAgWiseExpenditureList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, fyFrom, fyTo,executionAgencyId);
	}
	
	public List<DashboardBean> getPaymentWiseExpenditureList(final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String currentFY,final String executionAgencyId,final String exeOfficeId
			,final String lineDepartmentId,final String accountHeadId,final String workStatusId,final String workTypeId,final String workSubTypeId) {
		return workTypeRepository.getPaymentWiseExpenditureList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
	}
	
	public List<DashboardBean> getExeAgWiseExpenditureList(final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String financialYearId,final String executionAgencyId,final String exeOfficeId
			,final String lineDepartmentId,final String accountHeadId,final String workStatusId,final String month,final String workTypeId,final String workSubTypeId) {
		return workTypeRepository.getExeAgWiseExpenditureList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
	}
	
	public List<DashboardBean> getEeWiseExpenditureForMonthYearList(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year) {
		return workTypeRepository.getEeWiseExpenditureForMonthYearList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, month, year);
	}

	public List<DashboardBean> getAccountHeadWiseExpenditureForMonthYearAndEeList(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year) {
		return workTypeRepository.getAccountHeadWiseExpenditureForMonthYearAndEeList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, month, year);
	}
	
	
	public List<DashboardBean> getLineDeptWiseExpenditureForMonthYearAndEeList(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year) {
		return workTypeRepository.getLineDeptWiseExpenditureForMonthYearAndEeList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, month, year);
	}
	
	
	public List<DashboardBean> getWorkTypeWiseExpenditureForMonthYearAndEeList(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year) {
		return workTypeRepository.getWorkTypeWiseExpenditureForMonthYearAndEeList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, month, year);
	}
	
	
	public List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndWorkTypeList(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year, final Integer workType) {
		return workTypeRepository.getWorkWiseExpenditureForMonthYearAndEeAndWorkTypeList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, month, year, workType);
	}

	public List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndLineDeptList(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year, final Integer lineDept) {
		return workTypeRepository.getWorkWiseExpenditureForMonthYearAndEeAndLineDeptList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId, lineDeptId, month, year, lineDept);
	}
	public List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndAccHeadList(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId, final String month, final String year, final Integer accHead) {
		return workTypeRepository.getWorkWiseExpenditureForMonthYearAndEeAndAccHeadList(ceOfficeId, seOfficeId,
				eeOfficeId, aeId, subEngId,  month, year, accHead);
	}
	
}
