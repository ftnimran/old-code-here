package com.res.repository;

import java.util.List;

import com.res.bean.ExecutiveWorkReportBean;

public interface WorkReportRepositoryCustom {
	List<ExecutiveWorkReportBean> executiveOfficeWiseWorkReport(final Integer eeOfficeId, Short isLegacy);
	List<ExecutiveWorkReportBean> executiveOfficeWiseWorkReportStatus(Long statusId,final Integer eeOfficeId, Short isLegacy);
	List<ExecutiveWorkReportBean> superintendingOfficeWiseWorkReportStatus(Long statusId,final Integer supdteOfficeId, Short isLegacy);
	
	/*List<ExecutiveWorkReportBean> executiveOfficeWiseWorkReport(final Integer eeOfficeId, List<Short> isLegacy);*/
	
	List<ExecutiveWorkReportBean> chiefOfficeWiseWorkReport(final Integer ceOfficeId, Short isLegacy,final String executionAgencyId,final String districtId,
			final String supdtOfficeId,final String lineDepartmentId, final String accountHeadId,final String workStatusId,final String workTypeId,final String workSubTypeId);
	List<ExecutiveWorkReportBean> superintendingOfficeWiseWorkReport(final Integer supdteOfficeId, Short isLegacy,final String executionAgencyId,final String districtId,
			final String lineDepartmentId, final String accountHeadId,final String workStatusId,final String workTypeId,final String workSubTypeId);
	List<ExecutiveWorkReportBean> panchayatiRajDepartmentWiseWorkReport(Short isLegacy);
	List<ExecutiveWorkReportBean> chiefOfficeWiseWorkReportStatus(Long statusId, Integer ceOfficeId, Short isLegacy);
	List<ExecutiveWorkReportBean> adminStatusWorkReport(Long statusId, Integer eeOfficeId, Short isLegacy);
	List<Object[]> findAdminLegacyWorksByIsLegacyForAdmin(Short isLegacy);
	List<Object[]> findAllWorksByIsLegacyForAdmin();
	List<Object[]> findByIsLegacyAndOfficeIdForLegacyWorkForCE(final Integer ceOfficeId, Short isLegacy);
	List<Object[]> findByIsLegacyAndOfficeIdForAllForCE(final Integer ceOfficeId);
	List<Object[]> findWorkListByIsLegacyAndLineDepartmentIdForLegacyGP(Short isLegacy);
	List<Object[]> findWorkListByIsLegacyAndLineDepartmentIdForAllWorksGP();
	List<Object[]> findByIsLegacyAndOfficeIdForLegacyForSupdt(final Integer supdtOfficeId, Short isLegacy);
	List<Object[]> findAllWorksByIsLegacyAndOfficeForAllWorksSupdt(final Integer supdtOfficeId);
	
}
