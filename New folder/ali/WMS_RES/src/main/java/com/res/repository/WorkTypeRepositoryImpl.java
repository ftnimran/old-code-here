package com.res.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.res.bean.DashboardBean;

public class WorkTypeRepositoryImpl implements WorkTypeRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<DashboardBean> workTypeWiseDashboard(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("workTypeWiseDashboard")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
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
				dashboardBean.setCount5(Integer.parseInt((String) result[6]));
				list.add(dashboardBean);
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
	public List<DashboardBean> workSubTypeWiseDashboard(Integer workTypeId,
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("workSubTypeWiseDashboard")
					.registerStoredProcedureParameter("workTypeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("workTypeId", workTypeId);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
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
				dashboardBean.setCount5(Integer.parseInt((String) result[6]));
				list.add(dashboardBean);
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
	public List<DashboardBean> getDashboardDataForDistrictWiseWorksStatus(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId,
			final Integer subEngId, final Boolean isThematicMap,
			final Integer firstResult, final Integer pageDisplayLength) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = null;
			if (null == isThematicMap || !isThematicMap) {
				lQuery = (StoredProcedureQuery) em
						.createStoredProcedureQuery("districtWiseWorkDashboard");
			} else {
				lQuery = (StoredProcedureQuery) em
						.createStoredProcedureQuery("districtWiseWorkDashboardForThematicMap");

			}
			lQuery.registerStoredProcedureParameter("ceOfficeId",
					Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN);
			if (null != isThematicMap && isThematicMap) {
				lQuery.registerStoredProcedureParameter("pageNumber",
						Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("pageDisplayLength",
								Integer.class, ParameterMode.IN);
			}

			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);

			if (null != isThematicMap && isThematicMap) {
				lQuery.setParameter("pageNumber", firstResult);
				lQuery.setParameter("pageDisplayLength", pageDisplayLength);
			}

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			int i=0;
			for (Object[] result : results) {
				++i;
				dashboardBean = new DashboardBean();
				dashboardBean.setNumbering(i);
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				dashboardBean.setCount1(Integer.parseInt((String) result[2]));
				dashboardBean.setCount2(Integer.parseInt((String) result[3]));
				dashboardBean.setCount3(Integer.parseInt((String) result[4]));
				dashboardBean.setCount4(Integer.parseInt((String) result[5]));
				dashboardBean.setCount5(Integer.parseInt((String) result[6]));
				dashboardBean.setCode((String) result[7]);
				list.add(dashboardBean);
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
	public List<DashboardBean> getDashboardDataForStatusWisePieChart(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {

			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("workStatusWiseDashboard")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi = (BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> getDashboardDataForDistrictWisePieChart(
			final Integer ceOfficeId, final Integer seOfficeId,
			final Integer eeOfficeId, final Integer aeId, final Integer subEngId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {

			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery(
							"districtWiseWorkDashboardForPieChart")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi = (BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> workTypeWiseLineDeptDashboard(final Integer lineDeptId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("workTypeWiseByLineDeptDashboard")
					.registerStoredProcedureParameter("lineDeptId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("lineDeptId", lineDeptId);

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
				dashboardBean.setCount5(Integer.parseInt((String) result[6]));
				list.add(dashboardBean);
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
	public List<DashboardBean> districtWiseLineDeptPieChart(final Integer lineDeptId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("districtWiseWorkByLineDeptDashboardForPieChart")
					.registerStoredProcedureParameter("lineDeptId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("lineDeptId", lineDeptId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi = (BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> districtWiseLineDeptDashboard(final Integer lineDeptId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("districtWiseWorkByLineDeptDashboard")
					.registerStoredProcedureParameter("lineDeptId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("lineDeptId", lineDeptId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			int i=0;
			for (Object[] result : results) {
				++i;
				dashboardBean = new DashboardBean();
				dashboardBean.setNumbering(i);
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				dashboardBean.setCount1(Integer.parseInt((String) result[2]));
				dashboardBean.setCount2(Integer.parseInt((String) result[3]));
				dashboardBean.setCount3(Integer.parseInt((String) result[4]));
				dashboardBean.setCount4(Integer.parseInt((String) result[5]));
				dashboardBean.setCount5(Integer.parseInt((String) result[6]));
				dashboardBean.setCode((String) result[7]);
				
				list.add(dashboardBean);
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
	public List<DashboardBean> statusWiseLineDeptDashboard(final Integer lineDeptId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("workStatusWiseByLineDeptDashboard")
					.registerStoredProcedureParameter("lineDeptId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("lineDeptId", lineDeptId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi = (BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> ceOfficeWiseWorksCount() {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("CEOfficeWiseWorksCount");

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi = (BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> seOfficeWiseWorksCount(final Integer ceOfficeId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("SEOfficeWiseWorksCount")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi = (BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> eeOfficeWiseWorksCount(final Integer seOfficeId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("EEOfficeWiseWorksCount")
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN);
			lQuery.setParameter("seOfficeId", seOfficeId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi = (BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> getDashboardDataForWorkRequestStatusWiseCount(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("WorkRequestStatusWiseCount")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDeptId",
									Integer.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			int i=1;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setCount(i++);
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi=(BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	//getDashboardDataForWorkRequestStatusWiseCountWithSelection
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DashboardBean> getDashboardDataForWorkRequestStatusWiseCountWithSelection(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId,String executionAgencyId,String exeOfficeId,String lineDepartmentId,String accountHeadId
			,String workStatusId,String workTypeId,String workSubTypeId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		
		Integer execAgencyId=null;
		String wrkTypeId=null;
		String distId=null;
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
		
		/*if(!"".equals(workTypeId)) {
			wrkTypeId = Integer.parseInt(workTypeId);
			}
			else
			{
				wrkTypeId=-1;
			}*/
		
		  if("".equals(workTypeId)) {
				
			   wrkTypeId="0";
			}
			else {
				wrkTypeId=workTypeId;
			}
		
		if("".equals(exeOfficeId)) {
			
			distId="0";
		}
		else {
			distId=exeOfficeId;
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
		
		
		
		
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("WorkRequestStatusWiseCountWithSelection")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					
							.registerStoredProcedureParameter("execAgencyId",
									Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("distId",
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
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			
			lQuery.setParameter("execAgencyId", execAgencyId);
			lQuery.setParameter("distId", distId);
			lQuery.setParameter("lineDepartId", lineDepartId);
			lQuery.setParameter("accHeadId", accHeadId);
			lQuery.setParameter("wStatusId", wStatusId);
			lQuery.setParameter("wrkTypeId", wrkTypeId);
			lQuery.setParameter("wrkSubTypeId", wrkSubTypeId);
			

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			int i=1;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setCount(i++);
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi=(BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> getDashboardPendingForInspectionCountWithSelection(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId,String currentFY,String executionAgencyId,String exeOfficeId,String lineDepartmentId,String accountHeadId
			,String workTypeId,String workSubTypeId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		
		Integer execAgencyId=null;
		String wrkTypeId=null;
		String distId=null;
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
		
		/*if(!"".equals(workTypeId)) {
			wrkTypeId = Integer.parseInt(workTypeId);
			}
			else
			{
				wrkTypeId=-1;
			}*/
		
		  if("".equals(workTypeId)) {
				
			   wrkTypeId="0";
			}
			else {
				wrkTypeId=workTypeId;
			}
		
		
			
			/*distId="0";*/
		
		
        if("".equals(lineDepartmentId)) {
			
        	lineDepartId="0";
		}
		else {
			lineDepartId=lineDepartmentId;
		}
        
       if("".equals(exeOfficeId)) {
			
			distId="0";
		}
		else {
			distId=exeOfficeId;
		}
        
        if("".equals(accountHeadId)) {
			
        	accHeadId="0";
		}
		else {
			accHeadId=accountHeadId;
		}
      
			
    	   /*wStatusId="0";*/
		
		
       
       if("".equals(workSubTypeId)) {
			
    	   wrkSubTypeId="0";
		}
		else {
			wrkSubTypeId=workSubTypeId;
		}
		
		
		
		
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("PendingForInspectionCountWithSelection")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("currentFY",
							String.class, ParameterMode.IN)
					
							.registerStoredProcedureParameter("execAgencyId",
									Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("distId",
									String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDepartId",
									String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("accHeadId",
									String.class, ParameterMode.IN)
							/*.registerStoredProcedureParameter("wStatusId",
									String.class, ParameterMode.IN)*/
							.registerStoredProcedureParameter("wrkTypeId",
									String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("wrkSubTypeId",
									String.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			
			lQuery.setParameter("currentFY", currentFY);
			lQuery.setParameter("execAgencyId", execAgencyId);
		    lQuery.setParameter("distId", distId);
			lQuery.setParameter("lineDepartId", lineDepartId);
			lQuery.setParameter("accHeadId", accHeadId);
			/*lQuery.setParameter("wStatusId", wStatusId);*/
			lQuery.setParameter("wrkTypeId", wrkTypeId);
			lQuery.setParameter("wrkSubTypeId", wrkSubTypeId);
			

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			int i=1;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setCount(i++);
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi=(BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				dashboardBean.setStartYear((String) result[3]);
				dashboardBean.setEndYear((String) result[4]);
				list.add(dashboardBean);
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
	public List<DashboardBean> getDashboardFinalBillPendingCountWithSelection(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId,String currentFY,String executionAgencyId,String exeOfficeId,String lineDepartmentId,String accountHeadId
			,String workTypeId,String workSubTypeId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		
		Integer execAgencyId=null;
		String wrkTypeId=null;
		String distId=null;
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
		
		/*if(!"".equals(workTypeId)) {
			wrkTypeId = Integer.parseInt(workTypeId);
			}
			else
			{
				wrkTypeId=-1;
			}*/
		
		   if("".equals(workTypeId)) {
				
			   wrkTypeId="0";
			}
			else {
				wrkTypeId=workTypeId;
			}
		
		
			
			/*distId="0";*/
		   
		   if("".equals(exeOfficeId)) {
				
				distId="0";
			}
			else {
				distId=exeOfficeId;
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
      
			
    	   /*wStatusId="0";*/
		
		
       
       if("".equals(workSubTypeId)) {
			
    	   wrkSubTypeId="0";
		}
		else {
			wrkSubTypeId=workSubTypeId;
		}
		
		
		
		
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("FinalBillPendingCountWithSelection")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("currentFY",
							String.class, ParameterMode.IN)
					
							.registerStoredProcedureParameter("execAgencyId",
									Integer.class, ParameterMode.IN)
							
							.registerStoredProcedureParameter("distId",
									String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDepartId",
									String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("accHeadId",
									String.class, ParameterMode.IN)
							/*.registerStoredProcedureParameter("wStatusId",
									String.class, ParameterMode.IN)*/
							.registerStoredProcedureParameter("wrkTypeId",
									String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("wrkSubTypeId",
									String.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("currentFY", currentFY);
			
			lQuery.setParameter("execAgencyId", execAgencyId);
			lQuery.setParameter("distId", distId);
			lQuery.setParameter("lineDepartId", lineDepartId);
			lQuery.setParameter("accHeadId", accHeadId);
			/*lQuery.setParameter("wStatusId", wStatusId);*/
			lQuery.setParameter("wrkTypeId", wrkTypeId);
			lQuery.setParameter("wrkSubTypeId", wrkSubTypeId);
			

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			int i=1;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setCount(i++);
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi=(BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				
				/*BigInteger bii=(BigInteger) result[3];
				dashboardBean.setCount2(bii.intValue());*/
				
			
				BigDecimal b2=(BigDecimal) result[3];
				b2=b2.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setCount2(b2.intValue());
				dashboardBean.setStartYear((String) result[4]);
				dashboardBean.setEndYear((String) result[5]);
				
				list.add(dashboardBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	//getDashboardPhysicalCCDispatchCountWithSelection
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DashboardBean> getDashboardPhysicalCCDispatchCountWithSelection(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId,String executionAgencyId,String lineDepartmentId,String accountHeadId
			,String workTypeId,String workSubTypeId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		
		Integer execAgencyId=null;
		String wrkTypeId=null;
		String distId=null;
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
		
	/*	if(!"".equals(workTypeId)) {
			wrkTypeId = Integer.parseInt(workTypeId);
			}
			else
			{
				wrkTypeId=-1;
			}*/
		  if("".equals(workTypeId)) {
				
			   wrkTypeId="0";
			}
			else {
				wrkTypeId=workTypeId;
			}
		
		
			
			/*distId="0";*/
		
		
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
      
			
    	   /*wStatusId="0";*/
		
		
       
       if("".equals(workSubTypeId)) {
			
    	   wrkSubTypeId="0";
		}
		else {
			wrkSubTypeId=workSubTypeId;
		}
		
		
		
		
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("PhysicalCCDispatchCountWithSelection")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					
							.registerStoredProcedureParameter("execAgencyId",
									Integer.class, ParameterMode.IN)
							/*.registerStoredProcedureParameter("distId",
									String.class, ParameterMode.IN)*/
							.registerStoredProcedureParameter("lineDepartId",
									String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("accHeadId",
									String.class, ParameterMode.IN)
							/*.registerStoredProcedureParameter("wStatusId",
									String.class, ParameterMode.IN)*/
							.registerStoredProcedureParameter("wrkTypeId",
									String.class, ParameterMode.IN)
							.registerStoredProcedureParameter("wrkSubTypeId",
									String.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			
			lQuery.setParameter("execAgencyId", execAgencyId);
			/*lQuery.setParameter("distId", distId);*/
			lQuery.setParameter("lineDepartId", lineDepartId);
			lQuery.setParameter("accHeadId", accHeadId);
			/*lQuery.setParameter("wStatusId", wStatusId);*/
			lQuery.setParameter("wrkTypeId", wrkTypeId);
			lQuery.setParameter("wrkSubTypeId", wrkSubTypeId);
			

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			int i=1;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setCount(i++);
				dashboardBean.setId((Integer) result[0]);
				dashboardBean.setName((String) result[1]);
				BigInteger bi=(BigInteger) result[2];
				dashboardBean.setCount1(bi.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> getFYWiseExpenditureList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, String fyFrom, String fyTo) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureFinancialYearWise")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDeptId",
									Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("fyFrom",
									String.class, ParameterMode.IN)
								.registerStoredProcedureParameter("fyTo",
										String.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);
			lQuery.setParameter("fyFrom", fyFrom);
			lQuery.setParameter("fyTo", fyTo);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			int i=1;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setCount(i++);
				dashboardBean.setName((String) result[0]);
				BigDecimal bi=(BigDecimal) result[1];
				bi=bi.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setCount1(bi.intValue());
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> getExAgWiseExpenditureList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, String fyFrom, String fyTo,String executionAgencyId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		String execAgencyId=null;
		
		if(!"".equals(executionAgencyId)){
			execAgencyId = executionAgencyId;
		}
		if("".equals(executionAgencyId)) {
			 execAgencyId = "0";
		}
		
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureExAgWiseYearWise")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDeptId",
									Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("fyFrom",
									String.class, ParameterMode.IN)
								.registerStoredProcedureParameter("fyTo",
										String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("execAgencyId",
					Integer.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);
			lQuery.setParameter("fyFrom", fyFrom);
			lQuery.setParameter("fyTo", fyTo);
			lQuery.setParameter("execAgencyId", execAgencyId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setName((String) result[0]);
				BigDecimal bi=(BigDecimal) result[1];
				bi=bi.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setCount1(bi.intValue());
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				list.add(dashboardBean);
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
	public List<DashboardBean> getPaymentWiseExpenditureList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, String currentFY,String executionAgencyId,String exeOfficeId,String lineDepartmentId,String accountHeadId
			,String workStatusId,String workTypeId,String workSubTypeId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		Integer execAgencyId=null;
		String wrkTypeId=null;
		String distId=null;
		String lineDepartId=null;
		String accHeadId=null;
		String wStatusId=null;
		String wrkSubTypeId = null;
		/* if(!"".equals(workStatusId)) {
			 //need to be convert as List Of String
		Set<Integer> set11 = Stream.of(workStatusId.split(",")).map(Integer::parseInt).collect(Collectors.toSet());
		//If you want all values
		 List<Integer> erg = Arrays.asList(workStatusId.replace(" ", "").split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
		System.out.println(erg);
		 }*/
		if(!"".equals(executionAgencyId)) {
		 execAgencyId = Integer.parseInt(executionAgencyId);
		}
		else
		{
			execAgencyId=-1;
		}
		
	/*	if(!"".equals(workTypeId)) {
			wrkTypeId = Integer.parseInt(workTypeId);
			}
			else
			{
				wrkTypeId=-1;
			}*/
		
		  if("".equals(workTypeId)) {
				
			   wrkTypeId="0";
			}
			else {
				wrkTypeId=workTypeId;
			}
		
		if("".equals(exeOfficeId)) {
			
			distId="0";
		}
		else {
			distId=exeOfficeId;
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
		
		
		
		
	
		
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditurePaymentWiseYearWise")
					/*.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDeptId",
									Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("fyFrom",
									String.class, ParameterMode.IN)*/
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
								.registerStoredProcedureParameter("currentFY",
										String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("execAgencyId",
					Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("distId",
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
			/*lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);*/
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("currentFY", currentFY);
			
			lQuery.setParameter("execAgencyId", execAgencyId);
			lQuery.setParameter("distId", distId);
			lQuery.setParameter("lineDepartId", lineDepartId);
			lQuery.setParameter("accHeadId", accHeadId);
			lQuery.setParameter("wStatusId", wStatusId);
			lQuery.setParameter("wrkTypeId", wrkTypeId);
			lQuery.setParameter("wrkSubTypeId", wrkSubTypeId);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			int i=0;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				dashboardBean.setCount(i++);
				dashboardBean.setName((String) result[0]);
				/*BigDecimal bi=(BigDecimal) result[2];
				bi.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setCount1(bi.intValue());
				Double b2=(Double) result[3]; 
				dashboardBean.setCount2(b2.intValue());
				list.add(dashboardBean);*/
				
				
				
				
				
				
				
				dashboardBean.setName((String) result[0]);
				dashboardBean.setEeOfficeId((Integer)result[1]);
				/*BigDecimal bi=(BigDecimal) result[2];*/
				Double dd=(Double) result[2];
				BigDecimal bi = BigDecimal.valueOf(dd);
				bi=bi.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setCount1(bi.intValue());
				/*Double b2=(Double) result[2];*/ 
				BigDecimal b2=(BigDecimal) result[3];
				b2=b2.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setCount2(b2.intValue());
				dashboardBean.setStartYear((String) result[4]);
				dashboardBean.setEndYear((String) result[5]);
				list.add(dashboardBean);
				
				
				
				
				
				
				
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
	public List<DashboardBean> getExeAgWiseExpenditureList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, String financialYearId,String executionAgencyId,final String exeOfficeId,final String lineDepartmentId
			,final String accountHeadId,final String workStatusId,final String month,final String workTypeId,final String workSubTypeId) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		Integer execAgencyId=null;
		Integer exe = Integer.parseInt(executionAgencyId);
		Integer wrkTypeId=null;
		Integer wTypeId = Integer.parseInt(workTypeId);
		
		if(exe==0){
			execAgencyId = -1;
		}
		else {
			execAgencyId = exe;
		}
		
		if(wTypeId==0){
			wrkTypeId = -1;
		}
		else {
			wrkTypeId = wTypeId;
		}
		
		// TODO Auto-generated method stub
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureExeAgWiseYearWise")
					/*.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDeptId",
									Integer.class, ParameterMode.IN)*/
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("financialYearId",
									String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("execAgencyId",
					Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("exeOfficeId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("lineDepartmentId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("accountHeadId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("workStatusId",
					String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("wrkTypeId",
					Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("workSubTypeId",
					String.class, ParameterMode.IN);
			
								/*.registerStoredProcedureParameter("fyTo",
										String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("execAgencyId",
					//Integer.class, ParameterMode.IN);*/
		/*	lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);*/
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("financialYearId", financialYearId);
			lQuery.setParameter("execAgencyId", execAgencyId);
			lQuery.setParameter("exeOfficeId", exeOfficeId);
			lQuery.setParameter("lineDepartmentId", lineDepartmentId);
			lQuery.setParameter("accountHeadId", accountHeadId);
			lQuery.setParameter("workStatusId", workStatusId);
			lQuery.setParameter("wrkTypeId", wrkTypeId);
			lQuery.setParameter("workSubTypeId", workSubTypeId);
			/*lQuery.setParameter("fyTo", fyTo);
			lQuery.setParameter("execAgencyId", execAgencyId);*/

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			
			Long finalAprilAmount= new Long(0);
			Long finalAprilAmountCont= new Long(0);
			
			Long finalMayAmount= new Long(0);
			Long finalMayAmountCont= new Long(0);
			
			Long finalJunAmount= new Long(0);
			Long finalJunAmountCont= new Long(0);
			
			Long finalJulyAmount= new Long(0);
			Long finalJulyAmountCont= new Long(0);
			
			Long finalAugAmount= new Long(0);
			Long finalAugAmountCont= new Long(0);
			
			Long finalSeptAmount= new Long(0);
			Long finalSeptAmountCont= new Long(0);
			
			Long finalOctAmount= new Long(0);
			Long finalOctAmountCont= new Long(0);
			
			Long finalNovAmount= new Long(0);
			Long finalNovAmountCont= new Long(0);
			
			Long finalDecAmount= new Long(0);
			Long finalDecAmountCont= new Long(0);
			
			Long finalJanAmount= new Long(0);
			Long finalJanAmountCont= new Long(0);
			
			Long finalFebAmount= new Long(0);
			Long finalFebAmountCont= new Long(0);
			
			Long finalMarAmount= new Long(0);
			Long finalMarAmountCont= new Long(0);
			
			Long aprilTotalAmount = new Long(0);Long mayTotalAmount = new Long(0);Long juneTotalAmount = new Long(0);Long julyTotalAmount = new Long(0);
			Long augustTotalAmount = new Long(0);Long septemberTotalAmount = new Long(0);Long octoberTotalAmount = new Long(0);Long novemberTotalAmount = new Long(0);
			Long decemberTotalAmount = new Long(0);Long januaryTotalAmount = new Long(0);Long febuaryTotalAmount = new Long(0);Long marchTotalAmount = new Long(0);
			
			Long finalAprTotalAmount = new Long(0);
			Long finalMayTotalAmount = new Long(0);
			Long finalJunTotalAmount = new Long(0);
			Long finalJulTotalAmount = new Long(0);
			Long finalAugTotalAmount = new Long(0);
			Long finalSepTotalAmount = new Long(0);
			Long finalOctTotalAmount = new Long(0);
			Long finalNovTotalAmount = new Long(0);
			Long finalDecTotalAmount = new Long(0);
			Long finalJanTotalAmount = new Long(0);
			Long finalFebTotalAmount = new Long(0);
			Long finalMarTotalAmount = new Long(0);
			
			Long finalAllTotalAmount = new Long(0);
			Long finalAllTotalAmountPayment = new Long(0);
			Long finalAllTotalAmountContg = new Long(0);
			
			
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				if(month.equals("0")) {
				dashboardBean.setName((String) result[0]);
				dashboardBean.setEeOfficeId((Integer)result[1]);
				
				/*dashboardBean.setAprilWorksAmt( result[2].toString());*/
				
				BigDecimal aprilWorks=(BigDecimal) result[2];
				aprilWorks=aprilWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setAprilWorksAmt(aprilWorks.longValue());
				
				
				//finalAprilAmount=finalAprilAmount+((BigDecimal) result[2]);
				//finalAprilAmount.add((BigDecimal) result[2]);
				
				
				Long aprilWorksLong = aprilWorks.longValue();
				finalAprilAmount = finalAprilAmount + aprilWorksLong ;
				
				/*dashboardBean.setAprilContegencyAmt(result[3].toString());*/
				
				Double aprilCont = (Double) result[3];
				BigDecimal aprilContBig = BigDecimal.valueOf(aprilCont);
				aprilContBig=aprilContBig.setScale(0, RoundingMode.HALF_UP);
				Long aprilContLong = aprilCont.longValue();
				finalAprilAmountCont = finalAprilAmountCont + aprilContLong ;
				dashboardBean.setAprilContegencyAmt(aprilContBig.longValue());
				
				
				//april both total
				
				aprilTotalAmount=aprilWorksLong + aprilContLong;
				
				/*dashboardBean.setAprilTotalAmount(dashboardBean.getAprilWorksAmt());*/
				
				
				/*dashboardBean.setMayWorksAmt(result[4].toString());*/
				
				
				BigDecimal mayWorks = (BigDecimal) result[4];
				mayWorks=mayWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setMayWorksAmt(mayWorks.longValue());
				Long mayWorksLong = mayWorks.longValue();
				finalMayAmount = finalMayAmount + mayWorksLong ;
				
				/*dashboardBean.setMayContegencyAmt(result[5].toString());*/
				
				Double mayCont = (Double) result[5];
				BigDecimal mayContBig = BigDecimal.valueOf(mayCont);
				mayContBig=mayContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setMayContegencyAmt(mayContBig.longValue());
				Long mayContLong = mayCont.longValue();
				finalMayAmountCont = finalMayAmountCont + mayContLong ;
				
				
				// may both total
				
				mayTotalAmount = mayWorksLong+mayContLong;
				
				/*dashboardBean.setJuneWorksAmt(result[6].toString());*/
				
				BigDecimal juneWorks = (BigDecimal) result[6];
				juneWorks=juneWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setJuneWorksAmt(juneWorks.longValue());
				Long juneWorksLong = juneWorks.longValue();
				finalJunAmount = finalJunAmount + juneWorksLong ;
				
			
				
				/*dashboardBean.setJuneContegencyAmt(result[7].toString());*/
				
				Double junCont = (Double) result[7];
				BigDecimal junContBig = BigDecimal.valueOf(junCont);
				junContBig=junContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setJuneContegencyAmt(junContBig.longValue());
				Long junContLong = junCont.longValue();
				finalJunAmountCont = finalJunAmountCont + junContLong ;
				
				//june both total
				
				juneTotalAmount = juneWorksLong+junContLong;
				
				
				/*dashboardBean.setJulyWorksAmt(result[8].toString());*/
				
				BigDecimal julyWorks = (BigDecimal) result[8];
				julyWorks=julyWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setJulyWorksAmt(julyWorks.longValue());
				Long julyWorksLong = julyWorks.longValue();
				finalJulyAmount = finalJulyAmount + julyWorksLong ;
				
				/*dashboardBean.setJulyContegencyAmt(result[9].toString());*/
				
				Double julyCont = (Double) result[9];
				BigDecimal julyContBig = BigDecimal.valueOf(julyCont);
				julyContBig=julyContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setJulyContegencyAmt(julyContBig.longValue());
				Long julyContLong = julyCont.longValue();
				finalJulyAmountCont = finalJulyAmountCont + julyContLong ;
				
				//july both total
				julyTotalAmount = julyWorksLong+julyContLong;
				
				
				/*dashboardBean.setAugWorksAmt(result[10].toString());*/
				
				BigDecimal augWorks = (BigDecimal) result[10];
				augWorks=augWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setAugWorksAmt(augWorks.longValue());
				Long augWorksLong = augWorks.longValue();
				finalAugAmount = finalAugAmount + augWorksLong ;
				
				/*dashboardBean.setAugContegencyAmt(result[11].toString());*/
				
				Double augCont = (Double) result[11];
				BigDecimal augContBig = BigDecimal.valueOf(augCont);
				augContBig=augContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setAugContegencyAmt(augContBig.longValue());
				Long augContLong = augCont.longValue();
				finalAugAmountCont = finalAugAmountCont + augContLong ;
				
				//aug both total
				augustTotalAmount = augWorksLong+augContLong;
				
				/*dashboardBean.setSepWorksAmt(result[12].toString());*/
				
				BigDecimal sepWorks = (BigDecimal) result[12];
				sepWorks=sepWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setSepWorksAmt(sepWorks.longValue());
				Long sepWorksLong = sepWorks.longValue();
				finalSeptAmount = finalSeptAmount + sepWorksLong ;
				
				/*dashboardBean.setSepContegencyAmt(result[13].toString());*/
				
				Double sepCont = (Double) result[13];
				BigDecimal sepContBig = BigDecimal.valueOf(sepCont);
				sepContBig=sepContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setSepContegencyAmt(sepContBig.longValue());
				Long sepContLong = sepCont.longValue();
				finalSeptAmountCont = finalSeptAmountCont + sepContLong ;
				
				//sept both total
				septemberTotalAmount=sepWorksLong + sepContLong;
				
				
				/*dashboardBean.setOctWorksAmt(result[14].toString());*/
				
				BigDecimal octWorks = (BigDecimal) result[14];
				octWorks=octWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setOctWorksAmt(octWorks.longValue());
				Long octWorksLong = octWorks.longValue();
				finalOctAmount = finalOctAmount + octWorksLong ;
				
				/*dashboardBean.setOctContegencyAmt(result[15].toString());*/
				
				Double octCont = (Double) result[15];
				BigDecimal octContBig = BigDecimal.valueOf(octCont);
				octContBig=octContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setOctContegencyAmt(octContBig.longValue());
				Long octContLong = octCont.longValue();
				finalOctAmountCont = finalOctAmountCont + octContLong ;
				
				//oct both total
				
				octoberTotalAmount = octWorksLong+octContLong;
				
				/*dashboardBean.setNovWorksAmt(result[16].toString());*/
				
				BigDecimal novWorks = (BigDecimal) result[16];
				novWorks=novWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setNovWorksAmt(novWorks.longValue());
				Long novWorksLong = novWorks.longValue();
				finalNovAmount = finalNovAmount + novWorksLong ;
				
				/*dashboardBean.setNovContegencyAmt(result[17].toString());*/
				
				Double novCont = (Double) result[17];
				BigDecimal novContBig = BigDecimal.valueOf(novCont);
				novContBig=novContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setNovContegencyAmt(novContBig.longValue());
				Long novContLong = novCont.longValue();
				finalNovAmountCont = finalNovAmountCont + novContLong ;
				
				//nov both total
				
				novemberTotalAmount = novWorksLong+novContLong;
				
				/*dashboardBean.setDecWorksAmt(result[18].toString());*/
				
				BigDecimal decWorks = (BigDecimal) result[18];
				decWorks=decWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setDecWorksAmt(decWorks.longValue());
				Long decWorksLong = decWorks.longValue();
				finalDecAmount = finalDecAmount + decWorksLong ;
				
				/*dashboardBean.setDecContegencyAmt(result[19].toString());*/
				
				Double decCont = (Double) result[19];
				BigDecimal decContBig = BigDecimal.valueOf(decCont);
				decContBig=decContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setDecContegencyAmt(decContBig.longValue());
				Long decContLong = decCont.longValue();
				finalDecAmountCont = finalDecAmountCont + decContLong ;
				
				//dec both total
				decemberTotalAmount = decWorksLong+decContLong;
				
				/*dashboardBean.setJanWorksAmt(result[20].toString());*/
				
				BigDecimal janWorks = (BigDecimal) result[20];
				janWorks=janWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setJanWorksAmt(janWorks.longValue());
				Long janWorksLong = janWorks.longValue();
				finalJanAmount = finalJanAmount + janWorksLong ;
				
				/*dashboardBean.setJanContegencyAmt(result[21].toString());*/
				
				Double janCont = (Double) result[21];
				BigDecimal janContBig = BigDecimal.valueOf(janCont);
				janContBig=janContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setJanContegencyAmt(janContBig.longValue());
				Long janContLong = janCont.longValue();
				finalJanAmountCont = finalJanAmountCont + janContLong ;
				
				//jan both total
				januaryTotalAmount = janWorksLong+janContLong;
				
				
				
				
				/*dashboardBean.setFebWorksAmt(result[22].toString());*/
				
				BigDecimal febWorks = (BigDecimal) result[22];
				febWorks=febWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setFebWorksAmt(febWorks.longValue());
				Long febWorksLong = febWorks.longValue();
				finalFebAmount = finalFebAmount + febWorksLong ;
				
				/*dashboardBean.setFebContegencyAmt(result[23].toString());*/
				
				Double febCont = (Double) result[23];
				BigDecimal febContBig = BigDecimal.valueOf(febCont);
				febContBig=febContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setFebContegencyAmt(febContBig.longValue());
				Long febContLong = febCont.longValue();
				finalFebAmountCont = finalFebAmountCont + febContLong ;
				
				//feb both total
				febuaryTotalAmount = febWorksLong+febContLong;
				
				/*dashboardBean.setMarWorksAmt(result[24].toString());*/
				
				BigDecimal marWorks = (BigDecimal) result[24];
				marWorks=marWorks.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setMarWorksAmt(marWorks.longValue());
				Long marWorksLong = marWorks.longValue();
				finalMarAmount = finalMarAmount + marWorksLong ;
				
				/*dashboardBean.setMarContegencyAmt(result[25].toString());*/
				
				Double marCont = (Double) result[25];
				BigDecimal marContBig = BigDecimal.valueOf(marCont);
				marContBig=marContBig.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setMarContegencyAmt(marContBig.longValue());

				Long marContLong = marCont.longValue();
				finalMarAmountCont = finalMarAmountCont + marContLong ;
				
				//march both total
				marchTotalAmount = marWorksLong+marContLong;
				
				//set both total
				
				dashboardBean.setAprilTotalAmount(aprilTotalAmount);
				dashboardBean.setMayTotalAmount(mayTotalAmount);
				dashboardBean.setJuneTotalAmount(juneTotalAmount);
				dashboardBean.setJulyTotalAmount(julyTotalAmount);
				dashboardBean.setAugustTotalAmount(augustTotalAmount);
				dashboardBean.setSeptemberTotalAmount(septemberTotalAmount);
				dashboardBean.setOctoberTotalAmount(octoberTotalAmount);
				dashboardBean.setNovemberTotalAmount(novemberTotalAmount);
				dashboardBean.setDecemberTotalAmount(decemberTotalAmount);
				dashboardBean.setJanuaryTotalAmount(januaryTotalAmount);
				dashboardBean.setFebuaryTotalAmount(febuaryTotalAmount);
				dashboardBean.setMarchTotalAmount(marchTotalAmount);
				
				
				
				dashboardBean.setMonth(month);
				}
				else {
					if(month.contains("January")) {
						Double janCont = (Double) result[21];
						BigDecimal janContBig = BigDecimal.valueOf(janCont);
						janContBig=janContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setJanContegencyAmt(janContBig.longValue());
						Long janContLong = janCont.longValue();
						finalJanAmountCont = finalJanAmountCont + janContLong ;	
						
						BigDecimal janWorks = (BigDecimal) result[20];
						janWorks=janWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setJanWorksAmt(janWorks.longValue());
						Long janWorksLong = janWorks.longValue();
						finalJanAmount = finalJanAmount + janWorksLong ;
						//jan both total
						januaryTotalAmount = janWorksLong+janContLong;
						dashboardBean.setJanuaryTotalAmount(januaryTotalAmount);
						
						
						
					dashboardBean.setName((String) result[0]);
				/*	dashboardBean.setJanWorksAmt(result[20].toString());
					dashboardBean.setJanContegencyAmt(result[21].toString());*/
					dashboardBean.setEeOfficeId((Integer)result[1]);
					dashboardBean.setMonth(month);
						
					}
					if(month.contains("February")) {
						
						BigDecimal febWorks = (BigDecimal) result[22];
						febWorks=febWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setFebWorksAmt(febWorks.longValue());
						Long febWorksLong = febWorks.longValue();
						finalFebAmount = finalFebAmount + febWorksLong ;
						
						
						
						Double febCont = (Double) result[23];
						BigDecimal febContBig = BigDecimal.valueOf(febCont);
						febContBig=febContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setFebContegencyAmt(febContBig.longValue());
						Long febContLong = febCont.longValue();
						finalFebAmountCont = finalFebAmountCont + febContLong ;
						
						//feb both total
						febuaryTotalAmount = febWorksLong+febContLong;
						
						dashboardBean.setFebuaryTotalAmount(febuaryTotalAmount);
						
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setFebWorksAmt(result[22].toString());
						dashboardBean.setFebContegencyAmt(result[23].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("March")) {
						
						BigDecimal marWorks = (BigDecimal) result[24];
						marWorks=marWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setMarWorksAmt(marWorks.longValue());
						Long marWorksLong = marWorks.longValue();
						finalMarAmount = finalMarAmount + marWorksLong ;
						
						
						Double marCont = (Double) result[25];
						BigDecimal marContBig = BigDecimal.valueOf(marCont);
						marContBig=marContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setMarContegencyAmt(marContBig.longValue());
						Long marContLong = marCont.longValue();
						finalMarAmountCont = finalMarAmountCont + marContLong ;
						//march both total
						marchTotalAmount = marWorksLong+marContLong;
						dashboardBean.setMarchTotalAmount(marchTotalAmount);
						
						
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setMarWorksAmt(result[24].toString());
						dashboardBean.setMarContegencyAmt(result[25].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("APRIL")) {
						
						BigDecimal aprilWorks = (BigDecimal) result[2];
						aprilWorks=aprilWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setAprilWorksAmt(aprilWorks.longValue());
						Long aprilWorksLong = aprilWorks.longValue();
						finalAprilAmount = finalAprilAmount + aprilWorksLong ;
						
						
						
						Double aprilCont = (Double) result[3];
						BigDecimal aprilContBig = BigDecimal.valueOf(aprilCont);
						aprilContBig=aprilContBig.setScale(0, RoundingMode.HALF_UP);
						Long aprilContLong = aprilCont.longValue();
						dashboardBean.setAprilContegencyAmt(aprilContBig.longValue());
						finalAprilAmountCont = finalAprilAmountCont + aprilContLong ;
						//april both total
						aprilTotalAmount=aprilWorksLong + aprilContLong;
						dashboardBean.setAprilTotalAmount(aprilTotalAmount);
						
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setAprilWorksAmt( result[2].toString());
						dashboardBean.setAprilContegencyAmt(result[3].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("May")) {
						
						BigDecimal mayWorks = (BigDecimal) result[4];
						mayWorks=mayWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setMayWorksAmt(mayWorks.longValue());
						Long mayWorksLong = mayWorks.longValue();
						finalMayAmount = finalMayAmount + mayWorksLong ;
						
						
						
						Double mayCont = (Double) result[5];
						BigDecimal mayContBig = BigDecimal.valueOf(mayCont);
						mayContBig=mayContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setMayContegencyAmt(mayContBig.longValue());
						Long mayContLong = mayCont.longValue();
						finalMayAmountCont = finalMayAmountCont + mayContLong ;
						
						// may both total
						
						mayTotalAmount = mayWorksLong+mayContLong;
						dashboardBean.setMayTotalAmount(mayTotalAmount);
						
						
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setMayWorksAmt(result[4].toString());
						dashboardBean.setMayContegencyAmt(result[5].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("June")) {
						

						BigDecimal juneWorks = (BigDecimal) result[6];
						juneWorks=juneWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setJuneWorksAmt(juneWorks.longValue());
						Long juneWorksLong = juneWorks.longValue();
						finalJunAmount = finalJunAmount + juneWorksLong ;
						
					
						
						
						
						Double junCont = (Double) result[7];
						BigDecimal junContBig = BigDecimal.valueOf(junCont);
						junContBig=junContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setJuneContegencyAmt(junContBig.longValue());
						Long junContLong = junCont.longValue();
						
						finalJunAmountCont = finalJunAmountCont + junContLong ;
						
						//june both total
						
						juneTotalAmount = juneWorksLong+junContLong;
						
						dashboardBean.setJuneTotalAmount(juneTotalAmount);
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setJuneWorksAmt(result[6].toString());
						dashboardBean.setJuneContegencyAmt(result[7].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("July")) {
						
						BigDecimal julyWorks = (BigDecimal) result[8];
						julyWorks=julyWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setJulyWorksAmt(julyWorks.longValue());
						Long julyWorksLong = julyWorks.longValue();
						finalJulyAmount = finalJulyAmount + julyWorksLong ;
						
					
						
						Double julyCont = (Double) result[9];
						BigDecimal julyContBig = BigDecimal.valueOf(julyCont);
						julyContBig=julyContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setJulyContegencyAmt(julyContBig.longValue());
						Long julyContLong = julyCont.longValue();
						finalJulyAmountCont = finalJulyAmountCont + julyContLong ;
						
						//july both total
						julyTotalAmount = julyWorksLong+julyContLong;
						
						dashboardBean.setJulyTotalAmount(julyTotalAmount);
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setJulyWorksAmt(result[8].toString());
						dashboardBean.setJulyContegencyAmt(result[9].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("August")) {
						
						BigDecimal augWorks = (BigDecimal) result[10];
						augWorks=augWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setAugWorksAmt(augWorks.longValue());
						Long augWorksLong = augWorks.longValue();
						finalAugAmount = finalAugAmount + augWorksLong ;
						
						
						
						Double augCont = (Double) result[11];
						BigDecimal augContBig = BigDecimal.valueOf(augCont);
						augContBig=augContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setAugContegencyAmt(augContBig.longValue());
						Long augContLong = augCont.longValue();
						finalAugAmountCont = finalAugAmountCont + augContLong ;
						
						//aug both total
						augustTotalAmount = augWorksLong+augContLong;
						
						dashboardBean.setAugustTotalAmount(augustTotalAmount);
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setAugWorksAmt(result[10].toString());
						dashboardBean.setAugContegencyAmt(result[11].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("September")) {
						
						BigDecimal sepWorks = (BigDecimal) result[12];
						sepWorks=sepWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setSepWorksAmt(sepWorks.longValue());
						Long sepWorksLong = sepWorks.longValue();
						finalSeptAmount = finalSeptAmount + sepWorksLong ;
						
						
						
						Double sepCont = (Double) result[13];
						BigDecimal sepContBig = BigDecimal.valueOf(sepCont);
						sepContBig=sepContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setSepContegencyAmt(sepContBig.longValue());
						Long sepContLong = sepCont.longValue();
						finalSeptAmountCont = finalSeptAmountCont + sepContLong ;
						
						//sept both total
						septemberTotalAmount=sepWorksLong + sepContLong;
						
						
						dashboardBean.setSeptemberTotalAmount(septemberTotalAmount);
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setSepWorksAmt(result[12].toString());
						dashboardBean.setSepContegencyAmt(result[13].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("October")) {
						
						
						BigDecimal octWorks = (BigDecimal) result[14];
						octWorks=octWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setOctWorksAmt(octWorks.longValue());
						Long octWorksLong = octWorks.longValue();
						finalOctAmount = finalOctAmount + octWorksLong ;
						
						
						
						Double octCont = (Double) result[15];
						BigDecimal octContBig = BigDecimal.valueOf(octCont);
						octContBig=octContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setOctContegencyAmt(octContBig.longValue());
						Long octContLong = octCont.longValue();
						finalOctAmountCont = finalOctAmountCont + octContLong ;
						
						//oct both total
						
						octoberTotalAmount = octWorksLong+octContLong;
						
						dashboardBean.setOctoberTotalAmount(octoberTotalAmount);
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setOctWorksAmt(result[14].toString());
						dashboardBean.setOctContegencyAmt(result[15].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("November")) {
						BigDecimal novWorks = (BigDecimal) result[16];
						novWorks=novWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setNovWorksAmt(novWorks.longValue());
						Long novWorksLong = novWorks.longValue();
						finalNovAmount = finalNovAmount + novWorksLong ;
						
						
						
						Double novCont = (Double) result[17];
						BigDecimal novContBig = BigDecimal.valueOf(novCont);
						novContBig=novContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setNovContegencyAmt(novContBig.longValue());
						Long novContLong = novCont.longValue();
						finalNovAmountCont = finalNovAmountCont + novContLong ;
						
						//nov both total
						
						novemberTotalAmount = novWorksLong+novContLong;
						
						dashboardBean.setNovemberTotalAmount(novemberTotalAmount);
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setNovWorksAmt(result[16].toString());
						dashboardBean.setNovContegencyAmt(result[17].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					if(month.contains("December")) {
						
						BigDecimal decWorks = (BigDecimal) result[18];
						decWorks=decWorks.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setDecWorksAmt(decWorks.longValue());
						Long decWorksLong = decWorks.longValue();
						finalDecAmount = finalDecAmount + decWorksLong ;
						
						
						
						Double decCont = (Double) result[19];
						BigDecimal decContBig = BigDecimal.valueOf(decCont);
						decContBig=decContBig.setScale(0, RoundingMode.HALF_UP);
						dashboardBean.setDecContegencyAmt(decContBig.longValue());
						Long decContLong = decCont.longValue();
						finalDecAmountCont = finalDecAmountCont + decContLong ;
						
						//dec both total
						decemberTotalAmount = decWorksLong+decContLong;
						
						dashboardBean.setDecemberTotalAmount(decemberTotalAmount);
						dashboardBean.setName((String) result[0]);
						/*dashboardBean.setDecWorksAmt(result[18].toString());
						dashboardBean.setDecContegencyAmt(result[19].toString());*/
						dashboardBean.setEeOfficeId((Integer)result[1]);
						dashboardBean.setMonth(month);
							
						}
					
				}
				//dashboardBean.setAprilTotal(finalAprilAmount.toString());
				
				
				/*BigDecimal bi=(BigDecimal) result[1];
				bi.setScale(0, RoundingMode.HALF_UP);
				dashboardBean.setCount1(bi.intValue());
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				dashboardBean.setEeOfficeName((String) result[3]);*/
				list.add(dashboardBean);
				
			}
			/*System.err.println(finalAprilAmount);*/
			if(!list.isEmpty()) {
			if(month.equals("0")) {
			
			list.get(0).setAprilTotal(finalAprilAmount);
			list.get(0).setAprilTotalCont(finalAprilAmountCont);
			finalAprTotalAmount = finalAprilAmount+finalAprilAmountCont;
			list.get(0).setFinalAprTotalAmount(finalAprTotalAmount.toString());
			
			System.err.println(finalAprTotalAmount);
			
			list.get(0).setJanTotal(finalJanAmount);
			list.get(0).setJanTotalCont(finalJanAmountCont);
			finalJanTotalAmount = finalJanAmount+finalJanAmountCont;
			list.get(0).setFinalJanTotalAmount(finalJanTotalAmount.toString());
			
			
			list.get(0).setFebTotal(finalFebAmount);
			list.get(0).setFebTotalCont(finalFebAmountCont);
			finalFebTotalAmount = finalFebAmount+finalFebAmountCont;
			list.get(0).setFinalFebTotalAmount(finalFebTotalAmount.toString());
			
			list.get(0).setMarTotal(finalMarAmount);
			list.get(0).setMarTotalCont(finalMarAmountCont);
			finalMarTotalAmount = finalMarAmount+finalMarAmountCont;
			list.get(0).setFinalMarTotalAmount(finalMarTotalAmount.toString());
			
			list.get(0).setMayTotal(finalMayAmount);
			list.get(0).setMayTotalCont(finalMayAmountCont);
			finalMayTotalAmount = finalMayAmount+finalMayAmountCont;
			list.get(0).setFinalMayTotalAmount(finalMayTotalAmount.toString());
			
			list.get(0).setJunTotal(finalJunAmount);
			list.get(0).setJunTotalCont(finalJunAmountCont);
			finalJunTotalAmount = finalJunAmount+finalJunAmountCont;
			list.get(0).setFinalJunTotalAmount(finalJunTotalAmount.toString());
			
			list.get(0).setJulTotal(finalJulyAmount);
			list.get(0).setJulTotalCont(finalJulyAmountCont);
			finalJulTotalAmount = finalJulyAmount+finalJulyAmountCont;
			list.get(0).setFinalJulTotalAmount(finalJulTotalAmount.toString());
			
			list.get(0).setAugTotal(finalAugAmount);
			list.get(0).setAugTotalCont(finalAugAmountCont);
			finalAugTotalAmount = finalAugAmount+finalAugAmountCont;
			list.get(0).setFinalAugTotalAmount(finalAugTotalAmount.toString());
			
			list.get(0).setSepTotal(finalSeptAmount);
			list.get(0).setSepTotalCont(finalSeptAmountCont);
			finalSepTotalAmount = finalSeptAmount+finalSeptAmountCont;
			list.get(0).setFinalSepTotalAmount(finalSepTotalAmount.toString());
			
			list.get(0).setOctTotal(finalOctAmount);
			list.get(0).setOctTotalCont(finalOctAmountCont);
			finalOctTotalAmount = finalOctAmount+finalOctAmountCont;
			list.get(0).setFinalOctTotalAmount(finalOctTotalAmount.toString());
			
			list.get(0).setNovTotal(finalNovAmount);
			list.get(0).setNovTotalCont(finalNovAmountCont);
			finalNovTotalAmount = finalNovAmount+finalNovAmountCont;
			list.get(0).setFinalNovTotalAmount(finalNovTotalAmount.toString());
			
			list.get(0).setDecTotal(finalDecAmount);
			list.get(0).setDecTotalCont(finalDecAmountCont);
			finalDecTotalAmount = finalDecAmount+finalDecAmountCont;
			list.get(0).setFinalDecTotalAmount(finalDecTotalAmount.toString());
			
			finalAllTotalAmount = finalDecTotalAmount+finalNovTotalAmount+finalOctTotalAmount+finalSepTotalAmount+finalAugTotalAmount+finalJulTotalAmount+finalJunTotalAmount
					+finalMayTotalAmount+finalMarTotalAmount+finalFebTotalAmount+finalJanTotalAmount+finalAprTotalAmount;
			
			finalAllTotalAmountPayment = finalDecAmount + finalNovAmount + finalOctAmount + finalSeptAmount + finalAugAmount + finalJulyAmount + finalJunAmount
					+finalMayAmount + finalMarAmount + finalFebAmount + finalJanAmount + finalAprilAmount;
					
			list.get(0).setFinalAllTotalAmount(finalAllTotalAmount.toString());
			list.get(0).setFinalAllTotalAmountPayment(finalAllTotalAmountPayment.toString());
			
			finalAllTotalAmountContg = finalDecAmountCont+finalNovAmountCont+finalOctAmountCont+finalSeptAmountCont+finalAugAmountCont+finalJulyAmountCont+finalJunAmountCont
					+finalMayAmountCont+finalMarAmountCont+finalFebAmountCont+finalJanAmountCont+finalAprilAmountCont;
			
			list.get(0).setFinalAllTotalAmountContg(finalAllTotalAmountContg.toString());
			
			
			// for both total
			/*list.get(0).setAprilTotalAmount(aprilTotalAmount.toString());
			list.get(0).setMayTotalAmount(mayTotalAmount.toString());
			list.get(0).setJuneTotalAmount(juneTotalAmount.toString());
			list.get(0).setJulyTotalAmount(julyTotalAmount.toString());
			list.get(0).setAugustTotalAmount(augustTotalAmount.toString());
			list.get(0).setSeptemberTotalAmount(septemberTotalAmount.toString());
			list.get(0).setOctoberTotalAmount(octoberTotalAmount.toString());
			list.get(0).setNovemberTotalAmount(novemberTotalAmount.toString());
			list.get(0).setDecemberTotalAmount(decemberTotalAmount.toString());
			list.get(0).setJanuaryTotalAmount(januaryTotalAmount.toString());
			list.get(0).setFebuaryTotalAmount(febuaryTotalAmount.toString());
			list.get(0).setMarchTotalAmount(marchTotalAmount.toString());*/
			
			}
			else {
				
				if(month.contains("January")) {
					
					list.get(0).setJanTotal(finalJanAmount);
					list.get(0).setJanTotalCont(finalJanAmountCont);
					finalJanTotalAmount = finalJanAmount+finalJanAmountCont;
					list.get(0).setFinalJanTotalAmount(finalJanTotalAmount.toString());
				}
				
                if(month.contains("February")) {
					
                	list.get(0).setFebTotal(finalFebAmount);
        			list.get(0).setFebTotalCont(finalFebAmountCont);
        			finalFebTotalAmount = finalFebAmount+finalFebAmountCont;
        			list.get(0).setFinalFebTotalAmount(finalFebTotalAmount.toString());
				}
               if(month.contains("March")) {
					
            	   list.get(0).setMayTotal(finalMayAmount);
       			list.get(0).setMayTotalCont(finalMayAmountCont);
       			finalMarTotalAmount = finalMarAmount+finalMarAmountCont;
    			list.get(0).setFinalMarTotalAmount(finalMarTotalAmount.toString());
				}
               
               if(month.contains("APRIL")) {
					
            	   list.get(0).setAprilTotal(finalAprilAmount);
       			list.get(0).setAprilTotalCont(finalAprilAmountCont);
       			finalAprTotalAmount = finalAprilAmount+finalAprilAmountCont;
    			list.get(0).setFinalAprTotalAmount(finalAprTotalAmount.toString());
				}
               
               if(month.contains("May")) {
					
            	   list.get(0).setMayTotal(finalMayAmount);
       			list.get(0).setMayTotalCont(finalMayAmountCont);
       			finalMayTotalAmount = finalMayAmount+finalMayAmountCont;
    			list.get(0).setFinalMayTotalAmount(finalMayTotalAmount.toString());
				}
               
               if(month.contains("June")) {
					
            	   list.get(0).setJulTotal(finalJulyAmount);
       			list.get(0).setJulTotalCont(finalJulyAmountCont);
       			finalJunTotalAmount = finalJunAmount+finalJunAmountCont;
    			list.get(0).setFinalJunTotalAmount(finalJunTotalAmount.toString());
       			
				}
               
               if(month.contains("July")) {
					
            	   list.get(0).setJulTotal(finalJulyAmount);
       			list.get(0).setJulTotalCont(finalJulyAmountCont);
       			finalJulTotalAmount = finalJulyAmount+finalJulyAmountCont;
    			list.get(0).setFinalJulTotalAmount(finalJulTotalAmount.toString());
				}
               
               if(month.contains("August")) {
					
            	   list.get(0).setAugTotal(finalAugAmount);
       			list.get(0).setAugTotalCont(finalAugAmountCont);
       			finalAugTotalAmount = finalAugAmount+finalAugAmountCont;
    			list.get(0).setFinalAugTotalAmount(finalAugTotalAmount.toString());
				}
               
               if(month.contains("September")) {
					
            	   list.get(0).setSepTotal(finalSeptAmount);
       			list.get(0).setSepTotalCont(finalSeptAmountCont);
       			finalSepTotalAmount = finalSeptAmount+finalSeptAmountCont;
    			list.get(0).setFinalSepTotalAmount(finalSepTotalAmount.toString());
       			
				}
               
               if(month.contains("October")) {
					
            		list.get(0).setOctTotal(finalOctAmount);
        			list.get(0).setOctTotalCont(finalOctAmountCont);
        			finalOctTotalAmount = finalOctAmount+finalOctAmountCont;
        			list.get(0).setFinalOctTotalAmount(finalOctTotalAmount.toString());
        			
				}
               
               if(month.contains("November")) {
					
            	   list.get(0).setNovTotal(finalNovAmount);
       			list.get(0).setNovTotalCont(finalNovAmountCont);
       			finalNovTotalAmount = finalNovAmount+finalNovAmountCont;
    			list.get(0).setFinalNovTotalAmount(finalNovTotalAmount.toString());
       			
				}
               
               if(month.contains("December")) {
					
            	   list.get(0).setDecTotal(finalDecAmount);
       			list.get(0).setDecTotalCont(finalDecAmountCont);
       			finalDecTotalAmount = finalDecAmount+finalDecAmountCont;
    			list.get(0).setFinalDecTotalAmount(finalDecTotalAmount.toString());
				}
               finalAllTotalAmount = finalDecTotalAmount+finalNovTotalAmount+finalOctTotalAmount+finalSepTotalAmount+finalAugTotalAmount+finalJulTotalAmount+finalJunTotalAmount
   					+finalMayTotalAmount+finalMarTotalAmount+finalFebTotalAmount+finalJanTotalAmount+finalAprTotalAmount;
               
               list.get(0).setFinalAllTotalAmount(finalAllTotalAmount.toString());
               
           	
               finalAllTotalAmountPayment = finalDecAmount + finalNovAmount + finalOctAmount + finalSeptAmount + finalAugAmount + finalJulyAmount + finalJunAmount
					+finalMayAmount + finalMarAmount + finalFebAmount + finalJanAmount + finalAprilAmount;
					
			
			list.get(0).setFinalAllTotalAmountPayment(finalAllTotalAmountPayment.toString());
			
			finalAllTotalAmountContg = finalDecAmountCont+finalNovAmountCont+finalOctAmountCont+finalSeptAmountCont+finalAugAmountCont+finalJulyAmountCont+finalJunAmountCont
					+finalMayAmountCont+finalMarAmountCont+finalFebAmountCont+finalJanAmountCont+finalAprilAmountCont;
			
			list.get(0).setFinalAllTotalAmountContg(finalAllTotalAmountContg.toString());
 
				
			}
		}
			
			
			
			
			
			/*System.err.println(finalAprilAmount);*/
			//dashboardBean.setAprilTotal(finalAprilAmount.toString());
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DashboardBean> getEeWiseExpenditureForMonthYearList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, String month, String year) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureEeWiseForMonthYear")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDeptId",
									Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("vmonth",
									String.class, ParameterMode.IN)
								.registerStoredProcedureParameter("vyear",
										String.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);
			lQuery.setParameter("vmonth", month);
			lQuery.setParameter("vyear", year);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				
				dashboardBean.setName((String) result[0]);
				
				BigDecimal bi=(BigDecimal) result[1];
				//bi = bi.setScale(0, BigDecimal.ROUND_HALF_UP);
				dashboardBean.setCount1(bi.intValue());
				
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				
				dashboardBean.setId((Integer)result[3]);
				list.add(dashboardBean);
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
	public List<DashboardBean> getAccountHeadWiseExpenditureForMonthYearAndEeList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, String month, String year) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureAccountHeadWiseForEeAndMonthYear")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDeptId",
									Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("vmonth",
									String.class, ParameterMode.IN)
								.registerStoredProcedureParameter("vyear",
										String.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);
			lQuery.setParameter("vmonth", month);
			lQuery.setParameter("vyear", year);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				
				dashboardBean.setName((String) result[0]);
				
				BigDecimal bi=(BigDecimal) result[1];
				dashboardBean.setCount1(bi.intValue());
				
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				
				dashboardBean.setId((Integer)result[3]);
				list.add(dashboardBean);
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
	public List<DashboardBean> getLineDeptWiseExpenditureForMonthYearAndEeList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, String month, String year) {
		List<DashboardBean> list = new LinkedList<DashboardBean>();
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureLineDeptWiseForEeAndMonthYear")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDeptId",
									Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("vmonth",
									String.class, ParameterMode.IN)
								.registerStoredProcedureParameter("vyear",
										String.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);
			lQuery.setParameter("vmonth", month);
			lQuery.setParameter("vyear", year);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				
				dashboardBean.setName((String) result[0]);
				
				BigDecimal bi=(BigDecimal) result[1];
				dashboardBean.setCount1(bi.intValue());
				
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				
				dashboardBean.setId((Integer)result[3]);
				list.add(dashboardBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	public List<DashboardBean> getWorkTypeWiseExpenditureForMonthYearAndEeList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year) {

		List<DashboardBean> list = new LinkedList<DashboardBean>();
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureWorkTypeWiseForEeAndMonthYear")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", Integer.class,
							ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("lineDeptId",
									Integer.class, ParameterMode.IN)
							.registerStoredProcedureParameter("vmonth",
									String.class, ParameterMode.IN)
								.registerStoredProcedureParameter("vyear",
										String.class, ParameterMode.IN);
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);
			lQuery.setParameter("vmonth", month);
			lQuery.setParameter("vyear", year);

			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				
				dashboardBean.setName((String) result[0]);
				
				BigDecimal bi=(BigDecimal) result[1];
				dashboardBean.setCount1(bi.intValue());
				
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				
				dashboardBean.setId((Integer)result[3]);
				list.add(dashboardBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	
	}
	
	
	public List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndWorkTypeList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId, final Integer aeId, final Integer subEngId, 
			final Integer lineDeptId, final String month, final String year, final Integer workType) {

		List<DashboardBean> list = new LinkedList<DashboardBean>();
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureWorkWiseForEeAndMonthYearAndWorkType")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", 
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("lineDeptId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("vmonth",
							String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("vyear",
							String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("workType", 
							Integer.class, ParameterMode.IN);
			
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);
			lQuery.setParameter("vmonth", month);
			lQuery.setParameter("vyear", year);
			lQuery.setParameter("workType", workType);
			
			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				
				dashboardBean.setName((String) result[0]);
				
				BigDecimal bi=(BigDecimal) result[1];
				dashboardBean.setCount1(bi.intValue());
				
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				
				dashboardBean.setId((Integer)result[3]);
				list.add(dashboardBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	public List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndLineDeptList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId,
			final Integer aeId, final Integer subEngId, final Integer lineDeptId, final String month, final String year, final Integer lineDept) {

		List<DashboardBean> list = new LinkedList<DashboardBean>();
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureWorkWiseForEeAndMonthYearAndLineDept")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", 
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("lineDeptId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("vmonth",
							String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("vyear",
							String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("lineDept", 
							Integer.class, ParameterMode.IN);
			
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("lineDeptId", lineDeptId);
			lQuery.setParameter("vmonth", month);
			lQuery.setParameter("vyear", year);
			lQuery.setParameter("lineDept", lineDept);
			
			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				
				dashboardBean.setName((String) result[0]);
				
				BigDecimal bi=(BigDecimal) result[1];
				dashboardBean.setCount1(bi.intValue());
				
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				
				dashboardBean.setId((Integer)result[3]);
				list.add(dashboardBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
	
	public List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndAccHeadList(final Integer ceOfficeId,
			final Integer seOfficeId, final Integer eeOfficeId, final Integer aeId, final Integer subEngId, 
			final String month, final String year, final Integer accHead) {

		List<DashboardBean> list = new LinkedList<DashboardBean>();
		try {
			StoredProcedureQuery lQuery = (StoredProcedureQuery) em
					.createStoredProcedureQuery("ExpenditureWorkWiseForEeAndMonthYearAndAccountHead")
					.registerStoredProcedureParameter("ceOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("seOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("eeOfficeId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("aeId", 
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("subEngId",
							Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("vmonth",
							String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("vyear",
							String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("accHead", 
							Integer.class, ParameterMode.IN);
			
			lQuery.setParameter("ceOfficeId", ceOfficeId);
			lQuery.setParameter("seOfficeId", seOfficeId);
			lQuery.setParameter("eeOfficeId", eeOfficeId);
			lQuery.setParameter("aeId", aeId);
			lQuery.setParameter("subEngId", subEngId);
			lQuery.setParameter("vmonth", month);
			lQuery.setParameter("vyear", year);
			lQuery.setParameter("accHead", accHead);
			
			List<Object[]> results = lQuery.getResultList();
			DashboardBean dashboardBean = null;
			for (Object[] result : results) {
				dashboardBean = new DashboardBean();
				
				dashboardBean.setName((String) result[0]);
				
				BigDecimal bi=(BigDecimal) result[1];
				dashboardBean.setCount1(bi.intValue());
				
				Double b2=(Double) result[2]; 
				dashboardBean.setCount2(b2.intValue());
				
				dashboardBean.setId((Integer)result[3]);
				list.add(dashboardBean);
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return list;
	}
}
