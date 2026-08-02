package com.res.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.res.bean.ExecutiveWorkReportBean;
import com.res.entity.LineDepartment;
import com.res.entity.Office;

public class WorkRepositoryImpl implements WorkReportRepositoryCustom {

	
	@Autowired
	LineDepartmentRepository lineDepartmentRepository;
	@PersistenceContext
	private EntityManager em;
	
/*	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> executiveOfficeWiseWorkReport() {

		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("workCountCompletedIncompletedByExecutiveOfficesReport");
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setTotalWorkCount(((BigInteger)result[2]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setIncompletedWorkCount(((BigDecimal)result[4]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> chiefOfficeWiseWorkReport() {

		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("workCountCompletedIncompletedByChiefOfficesReport");
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setTotalWorkCount(((BigInteger)result[2]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setIncompletedWorkCount(((BigDecimal)result[4]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> superintendingOfficeWiseWorkReport() {

		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("workCountCompletedIncompletedBySuperintendingOfficesReport");
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setTotalWorkCount(((BigInteger)result[2]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setIncompletedWorkCount(((BigDecimal)result[4]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> executiveOfficeWiseWorkReport(final Integer eeOfficeId, Short isLegacy) {
		
		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("workCountCompletedIncompletedByExecutiveOfficesReport");
			lQuery.registerStoredProcedureParameter("eeOfficeId",Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);			
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeId((Integer)result[0]);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setTotalWorkCount(((BigDecimal)result[2]).intValue());
				reportBean.setNotStartedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setInProgressWorkCount(((BigDecimal)result[4]).intValue());
				reportBean.setOnHoldWorkCount(((BigDecimal)result[5]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[6]).intValue());
				reportBean.setCancelWorkCount(((BigDecimal)result[7]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAdminLegacyWorksByIsLegacyForAdmin(Short isLegacy) {
		
		    // For Admin All Legacy Data procedure
		
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("legacyDataForAdminInExcel");
			
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);			
			
			lQuery.setParameter("isLegacy", (int) isLegacy);
			
			List<Object[]> results = lQuery.getResultList();
			
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findWorkListByIsLegacyAndLineDepartmentIdForLegacyGP(Short isLegacy) {
		
		// For DirGP Legacy Data came from this procedure
		
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("legacyAndLineDepartmentIdForLegacyGPWork");
			
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);			
			
			lQuery.setParameter("isLegacy", (int) isLegacy);
			
			List<Object[]> results = lQuery.getResultList();
			
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByIsLegacyAndOfficeIdForLegacyWorkForCE(final Integer ceOfficeId, Short isLegacy) {
		
		
		// For CE Legacy Data came based on ceOfficeId
		
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("legacyAndOfficeIdForLegacyWorkForCE");
			
			lQuery.registerStoredProcedureParameter("ceOfficeId",Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);			
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			
			List<Object[]> results = lQuery.getResultList();
			
			
		return results;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByIsLegacyAndOfficeIdForLegacyForSupdt(final Integer supdtOfficeId, Short isLegacy) {
		    //For Supdt Legacy Data came based on supdtOfficeId
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("legacyAndOfficeIdForLegacyWorkForSupdt");
			
			lQuery.registerStoredProcedureParameter("supdtOfficeId",Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);			
			lQuery.setParameter("supdtOfficeId", supdtOfficeId);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			
			List<Object[]> results = lQuery.getResultList();
		return results;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByIsLegacyAndOfficeIdForAllForCE(final Integer ceOfficeId) {
		// For CE All Data came based on ceOfficeId
		
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("legacyAndOfficeIdForAllWorkForCE");
			
			lQuery.registerStoredProcedureParameter("ceOfficeId",Integer.class, ParameterMode.IN);
						
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			
			List<Object[]> results = lQuery.getResultList();
			
		return results;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAllWorksByIsLegacyAndOfficeForAllWorksSupdt(final Integer supdtOfficeId) {
		// For Supdt All Data came based on supdtOfficeId
		
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("findAllWorksByOfficeForAllWorksSupdtE");
			
			lQuery.registerStoredProcedureParameter("supdtOfficeId",Integer.class, ParameterMode.IN);
						
			lQuery.setParameter("supdtOfficeId", supdtOfficeId);
			
			List<Object[]> results = lQuery.getResultList();
			
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override	
public List<Object[]> findAllWorksByIsLegacyForAdmin() {
		
		// For Admin All Data came from procedure
		
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("allDataForAdminInExcel");
			List<Object[]> results = lQuery.getResultList();
			
		return results;
	}


@SuppressWarnings("unchecked")
@Override	
public List<Object[]> findWorkListByIsLegacyAndLineDepartmentIdForAllWorksGP() {
	
	
	// For DirGP All Data came from this procedure
	
		StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("listByIsLegacyAndLineDepartmentIdForAllWorksGPProc");
		List<Object[]> results = lQuery.getResultList();
		
		
	return results;
}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> executiveOfficeWiseWorkReportStatus(Long statusId,final Integer eeOfficeId, Short isLegacy) {
		
		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("workCountCompletedIncompletedByExecutiveOfficesReportStatus");
			lQuery.registerStoredProcedureParameter("eeOfficeId",Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("statusId",Long.class, ParameterMode.IN);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			lQuery.setParameter("statusId", (long) statusId);
			
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeId((Integer)result[0]);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setTotalWorkCount(((BigDecimal)result[2]).intValue());
				reportBean.setNotStartedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setInProgressWorkCount(((BigDecimal)result[4]).intValue());
				reportBean.setOnHoldWorkCount(((BigDecimal)result[5]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[6]).intValue());
				reportBean.setCancelWorkCount(((BigDecimal)result[7]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> adminStatusWorkReport(Long statusId,final Integer eeOfficeId, Short isLegacy) {
		
		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em.createStoredProcedureQuery("workCountCompletedIncompletedByExecutiveOfficesReportStatus");
			lQuery.registerStoredProcedureParameter("eeOfficeId",Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("statusId",Long.class, ParameterMode.IN);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			lQuery.setParameter("statusId", (long) statusId);
			
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeId((Integer)result[0]);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setTotalWorkCount(((BigDecimal)result[2]).intValue());
				reportBean.setNotStartedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setInProgressWorkCount(((BigDecimal)result[4]).intValue());
				reportBean.setOnHoldWorkCount(((BigDecimal)result[5]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[6]).intValue());
				reportBean.setCancelWorkCount(((BigDecimal)result[7]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> superintendingOfficeWiseWorkReportStatus(Long statusId,final Integer supdteOfficeId, Short isLegacy) {

		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("workCountCompletedIncompletedBySuperintendingOfficesReportStatus")
					.registerStoredProcedureParameter("supdteOfficeId",
					Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("statusId",Long.class, ParameterMode.IN);
			lQuery.setParameter("supdteOfficeId", supdteOfficeId);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			lQuery.setParameter("statusId", (long) statusId);
	
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setOfficeId((Integer)result[0]);
				reportBean.setTotalWorkCount(((BigDecimal)result[2]).intValue());
				reportBean.setNotStartedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setInProgressWorkCount(((BigDecimal)result[4]).intValue());
				reportBean.setOnHoldWorkCount(((BigDecimal)result[5]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[6]).intValue());
				reportBean.setCancelWorkCount(((BigDecimal)result[7]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> chiefOfficeWiseWorkReportStatus(Long statusId,final Integer ceOfficeId, Short isLegacy) {

		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) 
					em
					.createStoredProcedureQuery("workCountCompletedIncompletedByChiefOfficesReportStatus")
					.registerStoredProcedureParameter("ceOfficeId",
					Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);
			lQuery.registerStoredProcedureParameter("statusId",Long.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			lQuery.setParameter("statusId", (long) statusId);
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeId((Integer)result[0]);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setTotalWorkCount(((BigDecimal)result[2]).intValue());
				reportBean.setNotStartedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setInProgressWorkCount(((BigDecimal)result[4]).intValue());
				reportBean.setOnHoldWorkCount(((BigDecimal)result[5]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[6]).intValue());
				reportBean.setCancelWorkCount(((BigDecimal)result[7]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> chiefOfficeWiseWorkReport(final Integer ceOfficeId, Short isLegacy,String executionAgencyId,String exeOfficeId,String supdtOfficeId,String lineDepartmentId,String accountHeadId
			,String workStatusId,String workTypeId,String workSubTypeId) {

		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		
		Integer execAgencyId=null;
		String wrkTypeId=null;
		String distId=null;
		String supdtOfficeIds=null;
		String lineDepartId=null;
		String accHeadId=null;
		String wStatusId=null;
		String wrkSubTypeId = null;
		
		if(!"".equals(executionAgencyId)) {
		 execAgencyId = Integer.parseInt(executionAgencyId);
		}
		else
		{
			execAgencyId=-1;
		}
		
	
		
		if("".equals(exeOfficeId)) {
			
			distId="0";
		}
		else {
			distId=exeOfficeId;
		}
       if("".equals(supdtOfficeId)) {
			
    	   supdtOfficeIds="0";
		}
		else {
			supdtOfficeIds=supdtOfficeId;
		}
        if("".equals(lineDepartmentId)) {
			
        	lineDepartId="0";
		}
		else {
			lineDepartId=lineDepartmentId;
		}
        if("".equals(accountHeadId)) {
			
        	accHeadId="0";
		}
		else {
			accHeadId=accountHeadId;
		}
       if("".equals(workStatusId)) {
			
    	   wStatusId="0";
		}
		else {
			wStatusId=workStatusId;
		}
       
       if("".equals(workSubTypeId)) {
			
    	   wrkSubTypeId="0";
		}
		else {
			wrkSubTypeId=workSubTypeId;
		}
       
       if("".equals(workTypeId)) {
			
    	   wrkTypeId="0";
		}
		else {
			wrkTypeId=workTypeId;
		}
		
		
		
		
		
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) 
					em
					.createStoredProcedureQuery("workCountCompletedIncompletedByChiefOfficesReport")
					.registerStoredProcedureParameter("ceOfficeId",
					Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN)
					//from
			.registerStoredProcedureParameter("execAgencyId",
					Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("distId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("supdtOfficeIds",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("lineDepartId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("accHeadId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("wStatusId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("wrkTypeId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("wrkSubTypeId",
					String.class, ParameterMode.IN);
			//here
			
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			//from
			lQuery.setParameter("execAgencyId", execAgencyId);
			lQuery.setParameter("distId", distId);
			lQuery.setParameter("supdtOfficeIds", supdtOfficeIds);
			lQuery.setParameter("lineDepartId", lineDepartId);
			lQuery.setParameter("accHeadId", accHeadId);
			lQuery.setParameter("wStatusId", wStatusId);
			lQuery.setParameter("wrkTypeId", wrkTypeId);
			lQuery.setParameter("wrkSubTypeId", wrkSubTypeId);
            //here
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeId((Integer)result[0]);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setTotalWorkCount(((BigDecimal)result[2]).intValue());
				reportBean.setNotStartedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setInProgressWorkCount(((BigDecimal)result[4]).intValue());
				reportBean.setOnHoldWorkCount(((BigDecimal)result[5]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[6]).intValue());
				reportBean.setCancelWorkCount(((BigDecimal)result[7]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> superintendingOfficeWiseWorkReport(final Integer supdteOfficeId, Short isLegacy,String executionAgencyId,String exeOfficeId,String lineDepartmentId,String accountHeadId
			,String workStatusId,String workTypeId,String workSubTypeId) {

		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		
		Integer execAgencyId=null;
		String wrkTypeId=null;
		String distId=null;
		/*String supdtOfficeIds=null;*/
		String lineDepartId=null;
		String accHeadId=null;
		String wStatusId=null;
		String wrkSubTypeId = null;
		
		if(!"".equals(executionAgencyId)) {
		 execAgencyId = Integer.parseInt(executionAgencyId);
		}
		else
		{
			execAgencyId=-1;
		}
		
	
		
		if("".equals(exeOfficeId)) {
			
			distId="0";
		}
		else {
			distId=exeOfficeId;
		}
      /* if("".equals(supdtOfficeId)) {
			
    	   supdtOfficeIds="0";
		}
		else {
			supdtOfficeIds=supdtOfficeId;
		}*/
        if("".equals(lineDepartmentId)) {
			
        	lineDepartId="0";
		}
		else {
			lineDepartId=lineDepartmentId;
		}
        if("".equals(accountHeadId)) {
			
        	accHeadId="0";
		}
		else {
			accHeadId=accountHeadId;
		}
       if("".equals(workStatusId)) {
			
    	   wStatusId="0";
		}
		else {
			wStatusId=workStatusId;
		}
       
       if("".equals(workSubTypeId)) {
			
    	   wrkSubTypeId="0";
		}
		else {
			wrkSubTypeId=workSubTypeId;
		}
       
       if("".equals(workTypeId)) {
			
    	   wrkTypeId="0";
		}
		else {
			wrkTypeId=workTypeId;
		}
		
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("workCountCompletedIncompletedBySuperintendingOfficesReport")
					.registerStoredProcedureParameter("supdteOfficeId",
					Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("execAgencyId",
					Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("distId",
					String.class, ParameterMode.IN)
			/*.registerStoredProcedureParameter("supdtOfficeIds",
					String.class, ParameterMode.IN)*/
			.registerStoredProcedureParameter("lineDepartId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("accHeadId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("wStatusId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("wrkTypeId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("wrkSubTypeId",
					String.class, ParameterMode.IN);
			
			lQuery.setParameter("supdteOfficeId", supdteOfficeId);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			//from
			lQuery.setParameter("execAgencyId", execAgencyId);
			lQuery.setParameter("distId", distId);
			/*lQuery.setParameter("supdtOfficeIds", supdtOfficeIds);*/
			lQuery.setParameter("lineDepartId", lineDepartId);
			lQuery.setParameter("accHeadId", accHeadId);
			lQuery.setParameter("wStatusId", wStatusId);
			lQuery.setParameter("wrkTypeId", wrkTypeId);
			lQuery.setParameter("wrkSubTypeId", wrkSubTypeId);
            //here
	
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setOfficeName((String) result[1]);
				reportBean.setOfficeId((Integer)result[0]);
				reportBean.setTotalWorkCount(((BigDecimal)result[2]).intValue());
				reportBean.setNotStartedWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setInProgressWorkCount(((BigDecimal)result[4]).intValue());
				reportBean.setOnHoldWorkCount(((BigDecimal)result[5]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[6]).intValue());
				reportBean.setCancelWorkCount(((BigDecimal)result[7]).intValue());
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	

	/*@SuppressWarnings("unchecked")
	@Override
	public List<DashboardBean> getDashboardDataForDistritWiseWorksStatus(
			Integer districtId, Integer aeId, Integer subEngId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("districtWiseWorkDashboard")
					.registerStoredProcedureParameter("districtId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("districtId", districtId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				dashboardBean.setCount1(Integer.parseInt((String) result[2]));
				dashboardBean.setCount2(Integer.parseInt((String) result[3]));
				dashboardBean.setCount3(Integer.parseInt((String) result[4]));
				dashboardBean.setCount4(Integer.parseInt((String) result[5]));
				list.add(dashboardBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveWorkReportBean> panchayatiRajDepartmentWiseWorkReport(Short isLegacy) {
		
		List<ExecutiveWorkReportBean> list = new LinkedList<ExecutiveWorkReportBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = 
					(StoredProcedureQuery) 
					em
					.createStoredProcedureQuery("workCountCompletedIncompletedByGramPanchayatReport");
			lQuery.registerStoredProcedureParameter("isLegacy",Integer.class, ParameterMode.IN);
			lQuery.setParameter("isLegacy", (int) isLegacy);
			lQuery.registerStoredProcedureParameter("lineDepartmentId",Integer.class, ParameterMode.IN);
			lQuery.setParameter("lineDepartmentId", 17);
			
			List<Object[]> results = lQuery.getResultList();
			ExecutiveWorkReportBean reportBean = null;
			int i=0;
			for (Object[] result : results) {
				reportBean = new ExecutiveWorkReportBean();
//				reportBean.setId((Integer) result[0]);
				reportBean.setId(++i);
				reportBean.setTotalWorkCount(((BigDecimal)result[0]).intValue());
				reportBean.setNotStartedWorkCount(((BigDecimal)result[1]).intValue());
				reportBean.setInProgressWorkCount(((BigDecimal)result[2]).intValue());
				reportBean.setOnHoldWorkCount(((BigDecimal)result[3]).intValue());
				reportBean.setCompletedWorkCount(((BigDecimal)result[4]).intValue());
				reportBean.setCancelWorkCount(((BigDecimal)result[5]).intValue());
				reportBean.setOfficeId((Integer)result[6]);
				
				int id = (Integer)result[6];
				
				LineDepartment lineDepartment=lineDepartmentRepository.findOne((long) id);
				reportBean.setOfficeName(lineDepartment.getLineDepartmentNameE());
				
				
				
				
				list.add(reportBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}

	
	
	

}
