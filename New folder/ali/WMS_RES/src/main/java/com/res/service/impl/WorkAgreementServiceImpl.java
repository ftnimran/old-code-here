package com.res.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.res.bean.FinancialStageTypeBean;
import com.res.bean.PhysicalStageTypeBean;
import com.res.bean.WorkAgreementBean;
import com.res.bean.WorkAgreementRevisionBean;
import com.res.bean.WorkFinancialMileStoneBean;
import com.res.bean.WorkPhysicalMileStoneBean;
import com.res.constants.RESConstants;
import com.res.entity.FinancialStageType;
import com.res.entity.MasterWorkAgreementStatus;
import com.res.entity.PhysicalStageType;
import com.res.entity.RequestStatus;
import com.res.entity.Users;
import com.res.entity.Work;
import com.res.entity.WorkAgreement;
import com.res.entity.WorkAgreementRevision;
import com.res.entity.WorkFinancialMileStone;
import com.res.entity.WorkPhysicalMileStone;
import com.res.entity.WorkStatus;
import com.res.entity.WorkTender;
import com.res.exception.RESBusinessException;
import com.res.json.WorkAgreementJson;
import com.res.repository.FinancialStageTypeRepository;
import com.res.repository.PhysicalStageTypeRepository;
import com.res.repository.UserRepository;
import com.res.repository.WorkAgreementRepository;
import com.res.repository.WorkAgreementRevisionRepository;
import com.res.repository.WorkFinancialMileStoneRepository;
import com.res.repository.WorkPhysicalMileStoneRepository;
import com.res.repository.WorkRepository;
import com.res.repository.WorkTenderRepository;
import com.res.service.CommonService;
import com.res.service.WorkAgreementService;
import com.res.util.RESUtil;

@Service
public class WorkAgreementServiceImpl implements WorkAgreementService {

	public static final Logger logger = LoggerFactory
			.getLogger(WorkAgreementServiceImpl.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkRepository workRepository;
	
	@Autowired
	private WorkTenderRepository workTenderRepository;
	
	@Autowired
	private WorkAgreementRepository workAgreementRepository;
	
	@Autowired
	private WorkAgreementRevisionRepository workAgreementRevisionRepository;
	
	@Autowired
	private WorkPhysicalMileStoneRepository workPhysicalMileStoneRepository;
	
	@Autowired
	private WorkFinancialMileStoneRepository workFinancialMileStoneRepository;
	
	@Autowired
	private FinancialStageTypeRepository financialStageTypeRepository;
	
	@Autowired
	private PhysicalStageTypeRepository physicalStageTypeRepository;
	//Rakesh working
	@Override
	public WorkAgreementJson fetchWorkAgreementList(Pageable pageable,
			String searchBoxVal, String loggedInUserRole, String username) {
		
		
		WorkAgreementJson workAgreementJson = null;

		if (StringUtils.isEmpty(searchBoxVal)) {
			searchBoxVal = null;
		}

		try {

		//	Page<Work> work = null;
			
			Users entity = userRepository.findByUsernameAndStatus(username,
					RESConstants.STATUS_ACTIVE);
			long count = 0;
			int maxLimit=(pageable.getPageSize()*pageable.getPageNumber())==0?pageable.getPageSize():(pageable.getPageSize()*pageable.getPageNumber());
			List<Object[]> entityList = null;
			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
				entityList = workRepository
						.findPendingWorkAgrrementByExecutiveEngineerOfficeQuery(entity.getOffice().getId(), pageable.getOffset(), maxLimit);
								

				count = workRepository
						.findPendingWorkAgrrementByExecutiveEngineerOfficeCount(entity.getOffice().getId());
			} 

			//if (work != null) {
				
				List<WorkAgreementBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber()* pageable.getPageSize();
					
					for (Object[] objArr : entityList) {

//						WorkAgreementBean bean = convertWorkEntityToAgreementBean(element);
				WorkAgreementBean bean=new WorkAgreementBean();
				bean.setWorkId(Long.parseLong(objArr[0].toString()));
				bean.setWorkName((String)objArr[2]);
				bean.setWorkRequisitionNo((String)objArr[1]);
				bean.setWorkStatus((String)objArr[8]);
				bean.setExecutionAgency((String)objArr[4]);
				
				//WorkTender workTender = workTenderRepository.findByWorkId(work.getId());
				//WorkTender workTender = (workTenderRepository.findByWorkIdOrderByCreatedDateDesc(work.getId()).size()>0?workTenderRepository.findByWorkIdOrderByCreatedDateDesc(work.getId()).get(0):null);
				if (objArr[5]!=null) {
					bean.setTenderCost((BigDecimal) objArr[5]);
					bean.setContractorId(Long.parseLong(objArr[6].toString()));
					bean.setContractorName((String) objArr[7]);
				}
				if(objArr[13]!=null)
				bean.setTenderId(Long.parseLong(objArr[13].toString()));
				
				//WorkAgreement workAgreement = workAgreementRepository.findByWorkTender(workTender);
				if (objArr[10]!=null) {
					bean.setAgreementDate(RESUtil.convertDateToStringWithFormat((Date) objArr[10], RESConstants.DATE_FORMAT));
				//	bean.setWrittenOrderDate(RESUtil.convertDateToStringWithFormat(workAgreement.getWritten_order_date(), RESConstants.DATE_FORMAT));
					bean.setTentativeCompletionDate(RESUtil.convertDateToStringWithFormat((Date) objArr[11], RESConstants.DATE_FORMAT));
					
				}
				if (objArr[9]!=null)
				bean.setWorkAgreementStatusId(Long.parseLong(objArr[9].toString()));
				if(objArr[15]!=null)
				bean.setWorkAgreementStatus((String) objArr[15]);
				if (objArr[14]!=null)
				bean.setWorkAgreementId(Long.parseLong(objArr[14].toString()));
				if (objArr[12]!=null)
				bean.setParentId(Long.parseLong(objArr[12].toString()));
				
						bean.setIndex(++index);
						beanList.add(bean);
					}
					
				}
				workAgreementJson = new WorkAgreementJson();
				workAgreementJson.setiTotalDisplayRecords(count);
				workAgreementJson.setiTotalRecords(count);
				workAgreementJson.setAaData(beanList);
			//}
			return workAgreementJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return workAgreementJson;
		}
	}
	//History
	@Override
	public WorkAgreementJson fetchWorkAgreementList(Pageable pageable,
			String searchBoxVal, String loggedInUserRole, String username,Long workId,Long tenderId) {
		
		
		WorkAgreementJson workAgreementJson = null;

		if (StringUtils.isEmpty(searchBoxVal)) {
			searchBoxVal = null;
		}

		try {

		//	Page<Work> work = null;
			
			Users entity = userRepository.findByUsernameAndStatus(username,
					RESConstants.STATUS_ACTIVE);
			long count = 0;
			int maxLimit=(pageable.getPageSize()*pageable.getPageNumber())==0?pageable.getPageSize():(pageable.getPageSize()*pageable.getPageNumber());
			List<Object[]> entityList = null;
			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
				/*entityList = workRepository
						.findPendingWorkAgrrementByExecutiveEngineerOfficeQuery(entity.getOffice().getId(), pageable.getOffset(), maxLimit);*/
				entityList = workRepository
						.findHistoryWorkAgrrementByExecutiveEngineerOfficeQuery(entity.getOffice().getId(), workId, tenderId);
						//(entity.getOffice().getId(), pageable.getOffset(), maxLimit);
								

				count =entityList.size();
			} 

			//if (work != null) {
				
				List<WorkAgreementBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber()* pageable.getPageSize();
					
					for (Object[] objArr : entityList) {

//						WorkAgreementBean bean = convertWorkEntityToAgreementBean(element);
				WorkAgreementBean bean=new WorkAgreementBean();
				bean.setWorkId(Long.parseLong(objArr[0].toString()));
				bean.setWorkName((String)objArr[2]);
				bean.setWorkRequisitionNo((String)objArr[1]);
				bean.setWorkStatus((String)objArr[8]);
				bean.setExecutionAgency((String)objArr[4]);
				
				//WorkTender workTender = workTenderRepository.findByWorkId(work.getId());
				//WorkTender workTender = (workTenderRepository.findByWorkIdOrderByCreatedDateDesc(work.getId()).size()>0?workTenderRepository.findByWorkIdOrderByCreatedDateDesc(work.getId()).get(0):null);
				if (objArr[5]!=null) {
					bean.setTenderCost((BigDecimal) objArr[5]);
					bean.setContractorId(Long.parseLong(objArr[6].toString()));
					bean.setContractorName((String) objArr[7]);
				}
				if(objArr[13]!=null)
				bean.setTenderId(Long.parseLong(objArr[13].toString()));
				
				//WorkAgreement workAgreement = workAgreementRepository.findByWorkTender(workTender);
				if (objArr[10]!=null) {
					bean.setAgreementDate(RESUtil.convertDateToStringWithFormat((Date) objArr[10], RESConstants.DATE_FORMAT));
				//	bean.setWrittenOrderDate(RESUtil.convertDateToStringWithFormat(workAgreement.getWritten_order_date(), RESConstants.DATE_FORMAT));
					bean.setTentativeCompletionDate(RESUtil.convertDateToStringWithFormat((Date) objArr[11], RESConstants.DATE_FORMAT));
					
				}
				if (objArr[9]!=null)
				bean.setWorkAgreementStatusId(Long.parseLong(objArr[9].toString()));
				if(objArr[15]!=null)
				bean.setWorkAgreementStatus((String) objArr[15]);
				if (objArr[14]!=null)
				bean.setWorkAgreementId(Long.parseLong(objArr[14].toString()));
				if (objArr[12]!=null)
				bean.setParentId(Long.parseLong(objArr[12].toString()));
				
						bean.setIndex(++index);
						beanList.add(bean);
					}
					
				}
				workAgreementJson = new WorkAgreementJson();
				workAgreementJson.setiTotalDisplayRecords(count);
				workAgreementJson.setiTotalRecords(count);
				workAgreementJson.setAaData(beanList);
			//}
			return workAgreementJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return workAgreementJson;
		}
	}
	//Old Code
	/*@Override
	public WorkAgreementJson fetchWorkAgreementList(Pageable pageable,
			String searchBoxVal, String loggedInUserRole, String username) {
		
		
		WorkAgreementJson workAgreementJson = null;

		if (StringUtils.isEmpty(searchBoxVal)) {
			searchBoxVal = null;
		}

		try {

			Page<Work> work = null;

			Users entity = userRepository.findByUsernameAndStatus(username,
					RESConstants.STATUS_ACTIVE);
			long count = 0;

			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
				work = workRepository
						.findPendingWorkAgrrementByExecutiveEngineerOffice(
								pageable, searchBoxVal, entity.getOffice());

				count = workRepository
						.countPendingWorkEstimationsByExecutiveEngineerOffice(entity
								.getOffice().getId());
			} 

			if (work != null) {
				List<Work> entityList = work.getContent();
				List<WorkAgreementBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber()* pageable.getPageSize();
					
					for (Work element : entityList) {

						WorkAgreementBean bean = convertWorkEntityToAgreementBean(element);
						bean.setIndex(++index);
						beanList.add(bean);
					}
					
				}
				workAgreementJson = new WorkAgreementJson();
				workAgreementJson.setiTotalDisplayRecords(work
						.getTotalElements());
				workAgreementJson.setiTotalRecords(work
						.getTotalElements());
				workAgreementJson.setAaData(beanList);
			}
			return workAgreementJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return workAgreementJson;
		}
	}*/
	
	
	
	private WorkAgreementBean convertWorkEntityToAgreementBean(Work work) {
		WorkAgreementBean workAgreementBean = new WorkAgreementBean();
		
		workAgreementBean.setWorkId(work.getId());
		workAgreementBean.setWorkName(work.getWorkName());
		workAgreementBean.setWorkRequisitionNo(work.getWorkRequisitionNo());
		workAgreementBean.setWorkStatus(work.getWorkRequestStatusId().getStatusNameE());
		workAgreementBean.setExecutionAgency(work.getAgencyTypeId().getAgencyTypeNameE());
		
		//WorkTender workTender = workTenderRepository.findByWorkId(work.getId());
		WorkTender workTender = (workTenderRepository.findByWorkIdOrderByCreatedDateDesc(work.getId()).size()>0?workTenderRepository.findByWorkIdOrderByCreatedDateDesc(work.getId()).get(0):null);
		if (workTender!=null) {
			workAgreementBean.setTenderCost(workTender.getTenderCost());
			workAgreementBean.setContractorId(workTender.getContractorId().getId());
			workAgreementBean.setContractorName(workTender.getContractorId().getName());
		}
		
		WorkAgreement workAgreement = workAgreementRepository.findByWorkTender(workTender);
		if (workAgreement!=null) {
			workAgreementBean.setAgreementDate(RESUtil.convertDateToStringWithFormat(workAgreement.getAgreementDate(), RESConstants.DATE_FORMAT));
			workAgreementBean.setWrittenOrderDate(RESUtil.convertDateToStringWithFormat(workAgreement.getWritten_order_date(), RESConstants.DATE_FORMAT));
			workAgreementBean.setTentativeCompletionDate(RESUtil.convertDateToStringWithFormat(workAgreement.getTentativeCompletionDate(), RESConstants.DATE_FORMAT));
			workAgreementBean.setWorkAgreementStatusId(workAgreement.getStatus().getStatusId());
			workAgreementBean.setWorkAgreementStatus(workAgreement.getStatus().getStatusNameE());
			workAgreementBean.setWorkAgreementId(workAgreement.getId());
		}
		
		return workAgreementBean;
	}
	
	public String updateWorkAgreement(WorkAgreementBean workAgreementBean) {
		try {
			WorkAgreement workAgreement = workAgreementRepository.findById(workAgreementBean.getWorkAgreementId());
			
			workAgreement.setId(workAgreementBean.getWorkAgreementId());
			convertWorkAgreementBeanToEntity(workAgreement, workAgreementBean);
			
			workAgreementRepository.save(workAgreement);

			List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList = workAgreementBean.getWorkFinancialMileStoneBeanList();
			if (workFinancialMileStoneBeanList!=null) {
				for (WorkFinancialMileStoneBean workFinancialMileStoneBean : workFinancialMileStoneBeanList) {
					WorkFinancialMileStone workFinancialMileStone = new WorkFinancialMileStone();
					workFinancialMileStone.setId(workFinancialMileStoneBean.getId());
					convertWorkFinancialMileStoneBeanToEntity(workFinancialMileStone, workFinancialMileStoneBean);
					workFinancialMileStone.setWorkAgreement(workAgreement);
					workFinancialMileStoneRepository.save(workFinancialMileStone);
				}
			}
			
			List<WorkPhysicalMileStoneBean> workPhysicalMileStoneBeanList = workAgreementBean.getWorkPhysicalMileStoneBeanList();
			if (workPhysicalMileStoneBeanList!=null) {
				for (WorkPhysicalMileStoneBean workPhysicalMileStoneBean : workPhysicalMileStoneBeanList) {
					if(workPhysicalMileStoneBean.getCompletionDate() !=null ) {
						WorkPhysicalMileStone workPhysicalMileStone = new WorkPhysicalMileStone();
						convertWorkPhysicalMileStoneBeanToEntity(workPhysicalMileStone, workPhysicalMileStoneBean);
						workPhysicalMileStone.setWorkAgreement(workAgreement);
						workPhysicalMileStoneRepository.save(workPhysicalMileStone);
					}
					
				}
			}

			if (workAgreementBean.getWorkAgreementStatusId()==2) {
				Work work = workRepository.findOne(workAgreementBean.getWorkId());
				work.setWorkStatusId(new WorkStatus(RESConstants.STATUS_IN_PROGRESS_ID));
				work.setWorkRequestStatusId(new RequestStatus(RESConstants.REQUEST_STATUS_WORK_AGREEMENT_DONE_FWD_FOR_BILLING_INSPECTION_ID));
				workRepository.save(work);
			}	
			
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		return null;
	}
	
	public String addWorkAgreement(WorkAgreementBean workAgreementBean) {
		try {
			WorkAgreement workAgreement = new WorkAgreement();
			convertWorkAgreementBeanToEntity(workAgreement, workAgreementBean);
			WorkAgreement parentWorkAgreement = 
			workAgreementRepository.findByWorkOrderByCreatedDateDesc(new Work(workAgreementBean.getWorkId())).size() > 0 ? workAgreementRepository.findByWorkOrderByCreatedDateDesc(new Work(workAgreementBean.getWorkId())).get(0) : null;
			workAgreement.setParentId(parentWorkAgreement);
			workAgreementRepository.save(workAgreement);

			List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList = workAgreementBean.getWorkFinancialMileStoneBeanList();
			if (workFinancialMileStoneBeanList!=null) {
				for (WorkFinancialMileStoneBean workFinancialMileStoneBean : workFinancialMileStoneBeanList) {
					WorkFinancialMileStone workFinancialMileStone = new WorkFinancialMileStone();
					convertWorkFinancialMileStoneBeanToEntity(workFinancialMileStone, workFinancialMileStoneBean);
					workFinancialMileStone.setWorkAgreement(workAgreement);
					workFinancialMileStoneRepository.save(workFinancialMileStone);
				}
			}
			
			List<WorkPhysicalMileStoneBean> workPhysicalMileStoneBeanList = workAgreementBean.getWorkPhysicalMileStoneBeanList();
			if (workPhysicalMileStoneBeanList!=null) {
				for (WorkPhysicalMileStoneBean workPhysicalMileStoneBean : workPhysicalMileStoneBeanList) {
					WorkPhysicalMileStone workPhysicalMileStone = new WorkPhysicalMileStone();
					convertWorkPhysicalMileStoneBeanToEntity(workPhysicalMileStone, workPhysicalMileStoneBean);
					workPhysicalMileStone.setWorkAgreement(workAgreement);
					workPhysicalMileStoneRepository.save(workPhysicalMileStone);
				}
			}

			if (workAgreementBean.getWorkAgreementStatusId()==2) {
				Work work = workRepository.findOne(workAgreementBean.getWorkId());
				work.setWorkStatusId(new WorkStatus(RESConstants.STATUS_IN_PROGRESS_ID));
				work.setWorkRequestStatusId(new RequestStatus(RESConstants.REQUEST_STATUS_WORK_AGREEMENT_DONE_FWD_FOR_BILLING_INSPECTION_ID));
				workRepository.save(work);
			}	
			
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		return null;
	}
	
	public String addRevisedWorkAgreement(WorkAgreementBean workAgreementBean) {
		try {
			WorkAgreementRevision workAgreementRevision = new WorkAgreementRevision();
			workAgreementRevision.setRevisedOn(RESUtil.convertStringToDate(workAgreementBean.getRevisedOn()));
			workAgreementRevision.setWorkAgreement(new WorkAgreement(workAgreementBean.getWorkAgreementId()));
			workAgreementRevisionRepository.save(workAgreementRevision);
			
			List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList = workAgreementBean.getWorkFinancialMileStoneBeanList();
			if (workFinancialMileStoneBeanList!=null) {
				for (WorkFinancialMileStoneBean workFinancialMileStoneBean : workFinancialMileStoneBeanList) {
					WorkFinancialMileStone workFinancialMileStone = new WorkFinancialMileStone();
					convertWorkFinancialMileStoneBeanToEntity(workFinancialMileStone, workFinancialMileStoneBean);
					workFinancialMileStone.setWorkAgreement(new WorkAgreement(workAgreementBean.getWorkAgreementId()));
					workFinancialMileStone.setWorkAgreementRevision(workAgreementRevision);
					workFinancialMileStoneRepository.save(workFinancialMileStone);
				}
			}
			
			List<WorkPhysicalMileStoneBean> workPhysicalMileStoneBeanList = workAgreementBean.getWorkPhysicalMileStoneBeanList();
			if (workPhysicalMileStoneBeanList!=null) {
				for (WorkPhysicalMileStoneBean workPhysicalMileStoneBean : workPhysicalMileStoneBeanList) {
					WorkPhysicalMileStone workPhysicalMileStone = new WorkPhysicalMileStone();
					convertWorkPhysicalMileStoneBeanToEntity(workPhysicalMileStone, workPhysicalMileStoneBean);
					workPhysicalMileStone.setWorkAgreement(new WorkAgreement(workAgreementBean.getWorkAgreementId()));
					workPhysicalMileStone.setWorkAgreementRevision(workAgreementRevision);
					workPhysicalMileStoneRepository.save(workPhysicalMileStone);
				}
			}

			/*if (workAgreementBean.getWorkAgreementStatusId()==2) {
				Work work = workRepository.findOne(workAgreementBean.getWorkId());
				work.setWorkStatusId(new WorkStatus(RESConstants.STATUS_IN_PROGRESS_ID));
				work.setWorkRequestStatusId(new RequestStatus(RESConstants.STATUS_FWD_FOR_BILLING_ID));
				workRepository.save(work);
			}*/	
			
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		return null;
	}
	
	private void convertWorkAgreementBeanToEntity(WorkAgreement workAgreement, WorkAgreementBean workAgreementBean)
			throws RESBusinessException {
		workAgreement.setAgreementDate(RESUtil.convertStringToDate(workAgreementBean.getAgreementDate()));
		workAgreement.setAgreementNumber(workAgreementBean.getAgreementNumber());
		workAgreement.setTentativeCompletionDate(RESUtil.convertStringToDate(workAgreementBean.getTentativeCompletionDate()));
		workAgreement.setWritten_order_date(RESUtil.convertStringToDate(workAgreementBean.getWrittenOrderDate()));
		
		workAgreement.setWork(new Work(workAgreementBean.getWorkId()));
		workAgreement.setWorkTender(new WorkTender(workAgreementBean.getTenderId()));
		workAgreement.setStatus(new MasterWorkAgreementStatus(workAgreementBean.getWorkAgreementStatusId()));
	}
	
	private void convertWorkFinancialMileStoneBeanToEntity(WorkFinancialMileStone workFinancialMileStone, WorkFinancialMileStoneBean workFinancialMileStoneBean)
			throws RESBusinessException {
		workFinancialMileStone.setFinancialStage(new FinancialStageType(workFinancialMileStoneBean.getFinancialStageId()));
		workFinancialMileStone.setCompletionDate(RESUtil.convertStringToDate(workFinancialMileStoneBean.getCompletionDate()));
		workFinancialMileStone.setCumulative(workFinancialMileStoneBean.getCumulative());
	}
	
	private void convertWorkPhysicalMileStoneBeanToEntity(WorkPhysicalMileStone workPhysicalMileStone, WorkPhysicalMileStoneBean workPhysicalMileStoneBean)
			throws RESBusinessException {
		
		
			workPhysicalMileStone.setId(workPhysicalMileStoneBean.getId());
			workPhysicalMileStone.setPhysicalStage(new PhysicalStageType(workPhysicalMileStoneBean.getPhysicalStageId()));
			if(workPhysicalMileStoneBean.getCompletionDate() != null) {				
				workPhysicalMileStone.setCompletionDate(RESUtil.convertStringToDate(workPhysicalMileStoneBean.getCompletionDate()));
			}
				
	}	
	
	public WorkAgreementBean fetchWorkAgreementDetails(Long workId, boolean milestoneRequired) {
		
		//WorkAgreement workAgreement = workAgreementRepository.findByWork(new Work(workId));
		WorkAgreement workAgreement = 
				workAgreementRepository.findByWorkOrderByCreatedDateDesc(new Work(workId)).size() > 0 ? workAgreementRepository.findByWorkOrderByCreatedDateDesc(new Work(workId)).get(0) : null;
		WorkAgreementBean workAgreementBean = convertWorkAgreementEntityToAgreementBean(workAgreement);		
		
		if(milestoneRequired) {
			
			List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList = new ArrayList<WorkFinancialMileStoneBean>();		
			List<WorkFinancialMileStone> workFinancialMileStoneList = workFinancialMileStoneRepository.findByWorkAgreementAndWorkAgreementRevisionIsNull(workAgreement);		
			for(WorkFinancialMileStone d: workFinancialMileStoneList)	{
				WorkFinancialMileStoneBean workFinancialMileStoneBean = convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(d);
				workFinancialMileStoneBeanList.add(workFinancialMileStoneBean);
			}
			
			List<WorkPhysicalMileStoneBean>  workPhysicalMileStoneBeanList = new ArrayList<WorkPhysicalMileStoneBean>();		
			List<WorkPhysicalMileStone>  workPhysicalMileStoneList = workPhysicalMileStoneRepository.findByWorkAgreementAndWorkAgreementRevisionIsNull(workAgreement);		
			for(WorkPhysicalMileStone d: workPhysicalMileStoneList)	{
				WorkPhysicalMileStoneBean workPhysicalMileStoneBean = convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(d);
				workPhysicalMileStoneBeanList.add(workPhysicalMileStoneBean);
			}
			if(null!=workAgreementBean) {
				workAgreementBean.setWorkPhysicalMileStoneBeanList(workPhysicalMileStoneBeanList);
				workAgreementBean.setWorkFinancialMileStoneBeanList(workFinancialMileStoneBeanList);
		    }
		}
		return workAgreementBean;
	}
	
	//Rakesh
@Override
public WorkAgreementBean fetchWorkAgreementDetailsByTenderId(Long tenderId, boolean milestoneRequired) {
		
		//WorkAgreement workAgreement = workAgreementRepository.findByWork(new Work(workId));
		WorkAgreement workAgreement = 
				workAgreementRepository.findByWorkTender(new WorkTender(tenderId));
		WorkAgreementBean workAgreementBean = convertWorkAgreementEntityToAgreementBean(workAgreement);		
		
		if(milestoneRequired) {
			
			List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList = new ArrayList<WorkFinancialMileStoneBean>();		
			List<WorkFinancialMileStone> workFinancialMileStoneList = workFinancialMileStoneRepository.findByWorkAgreementAndWorkAgreementRevisionIsNull(workAgreement);		
			for(WorkFinancialMileStone d: workFinancialMileStoneList)	{
				WorkFinancialMileStoneBean workFinancialMileStoneBean = convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(d);
				workFinancialMileStoneBeanList.add(workFinancialMileStoneBean);
			}
			
			List<WorkPhysicalMileStoneBean>  workPhysicalMileStoneBeanList = new ArrayList<WorkPhysicalMileStoneBean>();		
			List<WorkPhysicalMileStone>  workPhysicalMileStoneList = workPhysicalMileStoneRepository.findByWorkAgreementAndWorkAgreementRevisionIsNull(workAgreement);		
			for(WorkPhysicalMileStone d: workPhysicalMileStoneList)	{
				WorkPhysicalMileStoneBean workPhysicalMileStoneBean = convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(d);
				workPhysicalMileStoneBeanList.add(workPhysicalMileStoneBean);
			}
			if(null!=workAgreementBean) {
				workAgreementBean.setWorkPhysicalMileStoneBeanList(workPhysicalMileStoneBeanList);
				workAgreementBean.setWorkFinancialMileStoneBeanList(workFinancialMileStoneBeanList);
			}
		}
		return workAgreementBean;
	}
//Rakesh
public WorkAgreementBean fetchWorkAgreementDetailsNew(Long workId, boolean milestoneRequired,Long workAgreemrntId) {
		
		//WorkAgreement workAgreement = workAgreementRepository.findByWork(new Work(workId));
//		/*WorkAgreement workAgreement = 
//				workAgreementRepository.findByWorkOrderByCreatedDateDesc(new Work(workId)).size() > 0 ? workAgreementRepository.findByWorkOrderByCreatedDateDesc(new Work(workId)).get(0) : null;*/
	WorkAgreement workAgreement = 
			workAgreementRepository.findById(workAgreemrntId);
	WorkAgreementBean workAgreementBean = convertWorkAgreementEntityToAgreementBean(workAgreement);		
		
		if(milestoneRequired) {
			
			List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList = new ArrayList<WorkFinancialMileStoneBean>();		
			List<WorkFinancialMileStone> workFinancialMileStoneList = workFinancialMileStoneRepository.findByWorkAgreementAndWorkAgreementRevisionIsNull(workAgreement);		
			for(WorkFinancialMileStone d: workFinancialMileStoneList)	{
				WorkFinancialMileStoneBean workFinancialMileStoneBean = convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(d);
				workFinancialMileStoneBeanList.add(workFinancialMileStoneBean);
			}
			
			List<WorkPhysicalMileStoneBean>  workPhysicalMileStoneBeanList = new ArrayList<WorkPhysicalMileStoneBean>();		
			List<WorkPhysicalMileStone>  workPhysicalMileStoneList = workPhysicalMileStoneRepository.findByWorkAgreementAndWorkAgreementRevisionIsNull(workAgreement);		
			for(WorkPhysicalMileStone d: workPhysicalMileStoneList)	{
				WorkPhysicalMileStoneBean workPhysicalMileStoneBean = convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(d);
				workPhysicalMileStoneBeanList.add(workPhysicalMileStoneBean);
			}
			if(null!=workAgreementBean) {
				workAgreementBean.setWorkPhysicalMileStoneBeanList(workPhysicalMileStoneBeanList);
				workAgreementBean.setWorkFinancialMileStoneBeanList(workFinancialMileStoneBeanList);
			}
		}
		
		
		return workAgreementBean;
	}

	private WorkAgreementBean convertWorkAgreementEntityToAgreementBean(
			WorkAgreement entity) {
			
		WorkAgreementBean workAgreementBean = null;
		if(entity != null) {
			workAgreementBean = new WorkAgreementBean();
			workAgreementBean.setWorkAgreementId(entity.getId());
			workAgreementBean.setAgreementDate(RESUtil.convertDateToString(entity.getAgreementDate()));
			workAgreementBean.setAgreementNumber(entity.getAgreementNumber());
			workAgreementBean.setTentativeCompletionDate(RESUtil.convertDateToString(entity.getTentativeCompletionDate()));
			workAgreementBean.setWrittenOrderDate(RESUtil.convertDateToString(entity.getWritten_order_date()));
			
			workAgreementBean.setWorkAgreementStatusId(entity.getStatus().getStatusId());
			workAgreementBean.setWorkAgreementStatus(entity.getStatus().getStatusNameE());
			
			if (entity.getWork()!=null) {workAgreementBean.setWorkId(entity.getWork().getId());}
		}
		
		return workAgreementBean;
	}
	
	
	private WorkAgreement convertWorkAgreementBeanToAgreementEntity(WorkAgreementBean bean) throws RESBusinessException {			
		WorkAgreement entity = new WorkAgreement();
		
		entity.setAgreementDate(RESUtil.convertStringToDate(bean.getAgreementDate()));
		
		entity.setAgreementNumber(bean.getAgreementNumber());
		
		entity.setStatus(new MasterWorkAgreementStatus(bean.getWorkAgreementStatusId()));
		
		return entity;
	}
	
	private WorkPhysicalMileStoneBean convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(WorkPhysicalMileStone entity) {
			
		WorkPhysicalMileStoneBean workPhysicalMileStoneBean = new WorkPhysicalMileStoneBean();
		workPhysicalMileStoneBean.setId(entity.getId());
		workPhysicalMileStoneBean.setPhysicalStageId(entity.getPhysicalStage().getPhysicalStageId());
		workPhysicalMileStoneBean.setPhysicalStageName(entity.getPhysicalStage().getPhysicalStageNameE());
		workPhysicalMileStoneBean.setCompletionDate(RESUtil.convertDateToStringWithFormat(entity.getCompletionDate(), RESConstants.DATE_FORMAT));
		workPhysicalMileStoneBean.setWorkAgreementId(entity.getWorkAgreement().getId());
		
		return workPhysicalMileStoneBean;
	}
	
	
	private WorkFinancialMileStoneBean convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(
			WorkFinancialMileStone entity) {
			
		WorkFinancialMileStoneBean workFinancialMileStoneBean = new WorkFinancialMileStoneBean();
		workFinancialMileStoneBean.setId(entity.getId());
		workFinancialMileStoneBean.setFinancialStageId(entity.getFinancialStage().getFinancialStageId());
		workFinancialMileStoneBean.setFinancialStageName(entity.getFinancialStage().getFinancialStageNameE());		
		workFinancialMileStoneBean.setCompletionDate(RESUtil.convertDateToStringWithFormat(entity.getCompletionDate(), RESConstants.DATE_FORMAT));
		workFinancialMileStoneBean.setCumulative(entity.getCumulative());
		workFinancialMileStoneBean.setWorkAgreementId(entity.getWorkAgreement().getId());
		
		return workFinancialMileStoneBean;
	}
	
	
	public List<FinancialStageTypeBean> fetchFinancialMileStone() {
		List<FinancialStageType> list = financialStageTypeRepository.findAll();
		
		List<FinancialStageTypeBean> financialStageTypeBeanList = new ArrayList<FinancialStageTypeBean>();
		
		for(FinancialStageType financialStageType : list) {
			FinancialStageTypeBean financialStageTypeBean = new FinancialStageTypeBean();
			
			financialStageTypeBean.setFinancialStageId(financialStageType.getFinancialStageId());
			financialStageTypeBean.setFinancialStageNameE(financialStageType.getFinancialStageNameE());
			financialStageTypeBean.setFinancialStageNameH(financialStageType.getFinancialStageNameH());
			financialStageTypeBean.setOrder(financialStageType.getOrder());
			
			financialStageTypeBeanList.add(financialStageTypeBean);
		}		
		return financialStageTypeBeanList;		
	}
	
	public List<WorkAgreementRevisionBean> fetchMilestoneRevisionByWorkAgreementId(Long workAgreementId) {
		List<WorkAgreementRevision> workAgreementRevisionList = workAgreementRevisionRepository.findByWorkAgreement(new WorkAgreement(workAgreementId));
		
		List<WorkAgreementRevisionBean> workAgreementRevisionBeanList = new ArrayList<WorkAgreementRevisionBean>();
		
		for(WorkAgreementRevision workAgreementRevision : workAgreementRevisionList) {
			WorkAgreementRevisionBean workAgreementRevisionBean = new WorkAgreementRevisionBean();
			
			workAgreementRevisionBean.setWorkAgreementRevisionId(workAgreementRevision.getId());
			workAgreementRevisionBean.setRevisedOn(RESUtil.convertDateToStringWithFormat(workAgreementRevision.getRevisedOn(), RESConstants.DATE_FORMAT));
			workAgreementRevisionBean.setAgreementWorkId(workAgreementRevision.getWorkAgreement().getId());
			
			workAgreementRevisionBeanList.add(workAgreementRevisionBean);
		}		
		return workAgreementRevisionBeanList;		
	}
	
	public WorkAgreementRevisionBean fetchMilestoneRevisionByWorkAgreementRevisionId(Long revisionId) {
		WorkAgreementRevision workAgreementRevision = workAgreementRevisionRepository.findOne(revisionId);
		
		WorkAgreementRevisionBean workAgreementRevisionBean = new WorkAgreementRevisionBean();		
		workAgreementRevisionBean.setWorkAgreementRevisionId(workAgreementRevision.getId());
		workAgreementRevisionBean.setRevisedOn(RESUtil.convertDateToStringWithFormat(workAgreementRevision.getRevisedOn(), RESConstants.DATE_FORMAT));
		workAgreementRevisionBean.setAgreementWorkId(workAgreementRevision.getWorkAgreement().getId());
			
		List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList = new ArrayList<WorkFinancialMileStoneBean>();		
		List<WorkFinancialMileStone> workFinancialMileStoneList = workFinancialMileStoneRepository.findByWorkAgreementRevision(workAgreementRevision);		
		for(WorkFinancialMileStone d: workFinancialMileStoneList)	{
			WorkFinancialMileStoneBean workFinancialMileStoneBean = convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(d);
			workFinancialMileStoneBeanList.add(workFinancialMileStoneBean);
		}
		
		List<WorkPhysicalMileStoneBean>  workPhysicalMileStoneBeanList = new ArrayList<WorkPhysicalMileStoneBean>();		
		List<WorkPhysicalMileStone>  workPhysicalMileStoneList = workPhysicalMileStoneRepository.findByWorkAgreementRevision(workAgreementRevision);		
		for(WorkPhysicalMileStone d: workPhysicalMileStoneList)	{
			WorkPhysicalMileStoneBean workPhysicalMileStoneBean = convertWorkPhysicalMileStoneEntityToWorkPhysicalMileStoneBean(d);
			workPhysicalMileStoneBeanList.add(workPhysicalMileStoneBean);
		}
		
		workAgreementRevisionBean.setWorkPhysicalMileStoneBeanList(workPhysicalMileStoneBeanList);
		workAgreementRevisionBean.setWorkFinancialMileStoneBeanList(workFinancialMileStoneBeanList);
			
		return workAgreementRevisionBean;		
	}
	
}
