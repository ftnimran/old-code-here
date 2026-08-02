package com.res.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.res.bean.CCDetailsBean;
import com.res.bean.CCDispatchDetailsBean;
import com.res.bean.WorkBean;
import com.res.constants.RESConstants;
import com.res.entity.Bill;
import com.res.entity.CCDetails;
import com.res.entity.DocumentUpload;
import com.res.entity.FinancialCCDispatchDetails;
import com.res.entity.PhysicalCCDispatchDetails;
import com.res.entity.RequestStatus;
import com.res.entity.Users;
import com.res.entity.Work;
import com.res.exception.RESBusinessException;
import com.res.json.WorkJson;
import com.res.repository.BillRepository;
import com.res.repository.CCDetailsRepository;
import com.res.repository.DocumentRepository;
import com.res.repository.FinancialCCDispatchRepository;
import com.res.repository.PhysicalCCDispatchRepository;
import com.res.repository.UserRepository;
import com.res.repository.WorkRepository;
import com.res.service.WorkCCService;
import com.res.util.RESUtil;

@Service
public class WorkCCServiceImpl implements WorkCCService {

	public static final Logger logger = LoggerFactory
			.getLogger(WorkAgreementServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkRepository workRepository;
	
	@Autowired
	private CCDetailsRepository ccDetailsRepository;	
	
	@Autowired
	private PhysicalCCDispatchRepository physicalCCDispatchRepository;	
	
	@Autowired
	private FinancialCCDispatchRepository financialCCDispatchRepository;	
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Value("${document.root}")
	private String documentRootPath;	
	
	@Value("${document.ccFilePath}")
	private String ccFilePath;	
	
	public String initiateCCSubmit(CCDetailsBean ccDetailsBean) {		
		try {
			CCDetails ccDetails = new CCDetails();
			ccDetails.setWorkCompletedOn(RESUtil.convertStringToDate(ccDetailsBean.getWorkCompletedOn()));
			ccDetails.setUsedMBNo(ccDetailsBean.getUsedMBNo());
			ccDetails.setTakenOverOn(RESUtil.convertStringToDate(ccDetailsBean.getTakenOverOn()));
			ccDetails.setPageNo(ccDetailsBean.getPageNo());
			ccDetails.setMbNo(ccDetailsBean.getMbNo());
			ccDetails.setDate(RESUtil.convertStringToDate(ccDetailsBean.getDate()));			
			ccDetails.setWork(new Work(ccDetailsBean.getWorkId()));
			ccDetailsRepository.save(ccDetails);
			
			Work work  = workRepository.findOne(ccDetailsBean.getWorkId());
			
			List<Bill> billList = billRepository.findByWorkAndBillTypeAndStatusStatusIdIn(new Work(ccDetailsBean.getWorkId()), "Final", Arrays.asList(RESConstants.STATUS_FWD_FOR_PAYMENT_ID, RESConstants.STATUS_CONTENGENCY_COMPLETED_ID, RESConstants.STATUS_PAYMENT_COMPLETED_ID));
			
			if(billList != null) {
				if(billList.size() == 1) {
					work.setWorkRequestStatusId(new RequestStatus(RESConstants.REQUEST_STATUS_Final_Inspecion_Completed_ID));
				}else{
					work.setWorkRequestStatusId(new RequestStatus(RESConstants.REQUEST_STATUS_Initiated_CC_Fwd_for_Final_Inspection_ID));
				}
			}
			
			workRepository.save(work);
			} catch (Exception e) {
				logger.error("An exception occurred.", e);
				return null;
		}
		return null;
	}
	
	@Override
	public WorkJson fetchWorkPhysicalCCList(Pageable pageable,
			String searchBoxVal, String loggedInUserRole, String username) {
				
		WorkJson workJson = null;

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
						.findWorkForPhysicalCCByExecutiveEngineerOffice(
								pageable, searchBoxVal, entity.getOffice());

				count = workRepository
						.countPendingWorkEstimationsByExecutiveEngineerOffice(entity
								.getOffice().getId());
			} 

			if (work != null) {
				List<Work> entityList = work.getContent();
				List<WorkBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber()* pageable.getPageSize();
					
					for (Work element : entityList) {

						WorkBean bean = convertWorkEntityToBean(element);
						bean.setIndex(++index);
						beanList.add(bean);
					}
					
				}
				workJson = new WorkJson();
				workJson.setiTotalDisplayRecords(work
						.getTotalElements());
				workJson.setiTotalRecords(work
						.getTotalElements());
				workJson.setAaData(beanList);
			}
			return workJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return workJson;
		}
	}
	
	@Override
	public WorkJson fetchWorkFinancialCCList(Pageable pageable,
			String searchBoxVal, String loggedInUserRole, String username) {
				
		WorkJson workJson = null;

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
						.findWorkForFinancialCCByExecutiveEngineerOffice(
								pageable, searchBoxVal, entity.getOffice());

				count = workRepository
						.countPendingWorkEstimationsByExecutiveEngineerOffice(entity
								.getOffice().getId());
			} 

			if (work != null) {
				List<Work> entityList = work.getContent();
				List<WorkBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber()* pageable.getPageSize();
					
					for (Work element : entityList) {

						WorkBean bean = convertWorkEntityToBean(element);
						
						List<Bill> billList = billRepository.findByWorkAndBillTypeAndStatusStatusId(element, "Final",  RESConstants.STATUS_PAYMENT_COMPLETED_ID);
						
						if(billList != null) {
							if(billList.size() == 1) {
								bean.setShowIssueFinancialCC(true);
							}
						}
						//If there is single running bill for which payment is not done, Dont issue Financial CC
						List<Bill> rbillList = billRepository.findByWorkAndBillTypeAndStatusStatusIdNotIn(element, "Running",  Arrays.asList(RESConstants.STATUS_FINAL_BILL_REJECTED_ID, RESConstants.STATUS_DELETED_ID, RESConstants.STATUS_PAYMENT_COMPLETED_ID));
						
						if(rbillList != null) {
							if(rbillList.size() >= 1) {
								bean.setShowIssueFinancialCC(false);
							}
						}
					
						bean.setIndex(++index);
						beanList.add(bean);
					}
					
				}
				workJson = new WorkJson();
				workJson.setiTotalDisplayRecords(work
						.getTotalElements());
				workJson.setiTotalRecords(work
						.getTotalElements());
				workJson.setAaData(beanList);
			}
			return workJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return workJson;
		}
	}
	
	
	private WorkBean convertWorkEntityToBean(Work entity) {
		WorkBean bean = new WorkBean();
		if (entity != null) {
			bean.setWorkRequisitionNo(entity.getWorkRequisitionNo());
			bean.setIsLegacy(entity.getIsLegacy());
			bean.setWorkId(entity.getId());
			bean.setWorkName(entity.getWorkName());
			bean.setWorkTypeId(entity.getWorkTypeId().getWorkTypeId());
			bean.setWorkTypeName(entity.getWorkTypeId().getWorkTypeNameE());
			if (entity.getWorkSubTypeId() != null)
				bean.setWorkSubTypeId(entity.getWorkSubTypeId().getWorkSubTypeId());			
			if (entity.getWorkStatusId() != null) {
				bean.setWorkStatusId(entity.getWorkStatusId().getId());
				bean.setWorkStatus(entity.getWorkStatusId().getWorkStatusNameE());
			}
			if (entity.getWorkRequestStatusId() != null) {
				bean.setWorkRequestStatusId(entity.getWorkRequestStatusId().getId());
				bean.setWorkRequestStatus(entity.getWorkRequestStatusId().getStatusNameE());
			}
			
			bean.setExecutionAgency(entity.getAgencyTypeId().getAgencyTypeNameE());
			
			PhysicalCCDispatchDetails physicalCCDispatchDetails = physicalCCDispatchRepository.findByWork(entity);
			bean.setPhysicalCCDispatchStatus((physicalCCDispatchDetails!=null)?physicalCCDispatchDetails.getStatus():(short)0);
			
			FinancialCCDispatchDetails financialCCDispatchDetails = financialCCDispatchRepository.findByWork(entity);
			bean.setFinancialCCDispatchStatus((financialCCDispatchDetails!=null)?financialCCDispatchDetails.getStatus():(short)0);
			
			//CCDetails ccDetails = ccDetailsRepository.findByWork(entity);
			CCDetails ccDetails = 
					ccDetailsRepository.findByWorkOrderByCreatedDateDesc(entity).size() > 0 ? ccDetailsRepository.findByWorkOrderByCreatedDateDesc(entity).get(0) : null;
			if (ccDetails!=null) {
				bean.setCcInspectionSubmittedOn(RESUtil.convertDateToString(ccDetails.getCcInspectionSubmittedOn()));
			}	
		}
		
		return bean;
	}
	
	public String addPhysicalCCDispatchDetails(CCDispatchDetailsBean ccDispatchDetailsBean) {
		try {
			PhysicalCCDispatchDetails physicalCCDispatchDetails;
			if (ccDispatchDetailsBean.getId()==null) {
				physicalCCDispatchDetails = new PhysicalCCDispatchDetails();
			} else {
				physicalCCDispatchDetails = physicalCCDispatchRepository.findOne(ccDispatchDetailsBean.getId());
			}
			convertPhysicalCCDispatchDetailsBeanToEntity(physicalCCDispatchDetails, ccDispatchDetailsBean);
			physicalCCDispatchRepository.save(physicalCCDispatchDetails);

			if (ccDispatchDetailsBean.getStatus()==2) {
				Work work = workRepository.findOne(ccDispatchDetailsBean.getWorkId());				
				work.setWorkRequestStatusId(new RequestStatus(RESConstants.REQUEST_STATUS_Physical_CC_Disptached_ID));
				workRepository.save(work);
			}
			
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		return null;
	}
	
	public String addFinancialCCDispatchDetails(CCDispatchDetailsBean ccDispatchDetailsBean) {
		try {
			FinancialCCDispatchDetails financialCCDispatchDetails;
			if (ccDispatchDetailsBean.getId()==null) {
				financialCCDispatchDetails = new FinancialCCDispatchDetails();
			} else {
				financialCCDispatchDetails = financialCCDispatchRepository.findOne(ccDispatchDetailsBean.getId());
			}			
			convertFinancialCCDispatchDetailsBeanToEntity(financialCCDispatchDetails, ccDispatchDetailsBean);
			financialCCDispatchRepository.save(financialCCDispatchDetails);

			if (ccDispatchDetailsBean.getStatus()==2) {
				Work work = workRepository.findOne(ccDispatchDetailsBean.getWorkId());				
				work.setWorkRequestStatusId(new RequestStatus(RESConstants.REQUEST_STATUS_Final_CC_Disptached_ID));
				workRepository.save(work);
			}
			
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		return null;
	}
	
	private void convertPhysicalCCDispatchDetailsBeanToEntity(PhysicalCCDispatchDetails physicalCCDispatchDetails, CCDispatchDetailsBean ccDispatchDetailsBean)
			throws RESBusinessException {
		if (ccDispatchDetailsBean.getId()!=null) {
			physicalCCDispatchDetails.setId(ccDispatchDetailsBean.getId());
		}		
		physicalCCDispatchDetails.setWork(new Work(ccDispatchDetailsBean.getWorkId()));		
		physicalCCDispatchDetails.setDispatchNumber(ccDispatchDetailsBean.getDispatchNumber());
		physicalCCDispatchDetails.setDispatchDate(RESUtil.convertStringToDate(ccDispatchDetailsBean.getDispatchDate()));		
		physicalCCDispatchDetails.setRemarks(ccDispatchDetailsBean.getRemarks());
		physicalCCDispatchDetails.setStatus(ccDispatchDetailsBean.getStatus());		
		if (ccDispatchDetailsBean.getFile()!=null) {
			DocumentUpload documentUpload = RESUtil
					.uploadCCDocument(documentRootPath
							+ ccFilePath, "blank",
							ccDispatchDetailsBean.getFile(), null,
							RESConstants.PHYSICAL_CC_FILE);
			documentRepository.save(documentUpload);
			physicalCCDispatchDetails.setCcFile(documentUpload);
		}
	}
	
	private void convertFinancialCCDispatchDetailsBeanToEntity(FinancialCCDispatchDetails financialCCDispatchDetails, CCDispatchDetailsBean ccDispatchDetailsBean)
			throws RESBusinessException {
		if (ccDispatchDetailsBean.getId()!=null) {
			financialCCDispatchDetails.setId(ccDispatchDetailsBean.getId());
		}
		financialCCDispatchDetails.setWork(new Work(ccDispatchDetailsBean.getWorkId()));		
		financialCCDispatchDetails.setDispatchNumber(ccDispatchDetailsBean.getDispatchNumber());
		financialCCDispatchDetails.setDispatchDate(RESUtil.convertStringToDate(ccDispatchDetailsBean.getDispatchDate()));		
		financialCCDispatchDetails.setRemarks(ccDispatchDetailsBean.getRemarks());
		financialCCDispatchDetails.setStatus(ccDispatchDetailsBean.getStatus());		
		if (ccDispatchDetailsBean.getFile()!=null) {
			DocumentUpload documentUpload = RESUtil
					.uploadCCDocument(documentRootPath
							+ ccFilePath, "blank",
							ccDispatchDetailsBean.getFile(), null,
							RESConstants.FINANCIAL_CC_FILE);
			documentRepository.save(documentUpload);
			financialCCDispatchDetails.setCcFile(documentUpload);
		}
	}
	
	@Override
	public CCDispatchDetailsBean fetchPhysicalCCDispatchDetailsByWorkId(Long workid) {
		try {
			PhysicalCCDispatchDetails physicalCCDispatchDetails = physicalCCDispatchRepository.findByWork(new Work(workid));
			if (physicalCCDispatchDetails!=null) {
				return convertPhysicalCCDispatchDetailsEntityToBean(physicalCCDispatchDetails);
			} else {
				return null;
			}
			
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}
	
	@Override
	public CCDispatchDetailsBean fetchFinancialCCDispatchDetailsByWorkId(Long workid) {
		try {
			FinancialCCDispatchDetails financialCCDispatchDetails = financialCCDispatchRepository.findByWork(new Work(workid));
			if (financialCCDispatchDetails!=null) {
				return convertFinancialCCDispatchDetailsEntityToBean(financialCCDispatchDetails);
			} else {
				return null;
			}
			
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}
	
	private CCDispatchDetailsBean convertPhysicalCCDispatchDetailsEntityToBean(PhysicalCCDispatchDetails physicalCCDispatchDetails) {
		CCDispatchDetailsBean ccDispatchDetailsBean = new CCDispatchDetailsBean();
		ccDispatchDetailsBean.setId(physicalCCDispatchDetails.getId());
		ccDispatchDetailsBean.setWorkId(physicalCCDispatchDetails.getWork().getId());
		ccDispatchDetailsBean.setDispatchNumber(physicalCCDispatchDetails.getDispatchNumber());
		ccDispatchDetailsBean.setDispatchDate(RESUtil.convertDateToString(physicalCCDispatchDetails.getDispatchDate()));		
		ccDispatchDetailsBean.setRemarks(physicalCCDispatchDetails.getRemarks());
		ccDispatchDetailsBean.setStatus(physicalCCDispatchDetails.getStatus());
		if (physicalCCDispatchDetails.getCcFile()!=null) {
			ccDispatchDetailsBean.setDocumentId(physicalCCDispatchDetails.getCcFile().getDocumentId());
			ccDispatchDetailsBean.setDocumentName(physicalCCDispatchDetails.getCcFile().getDocumentName());
		}
		return ccDispatchDetailsBean;
	}
	
	private CCDispatchDetailsBean convertFinancialCCDispatchDetailsEntityToBean(FinancialCCDispatchDetails financialCCDispatchDetails) {
		CCDispatchDetailsBean ccDispatchDetailsBean = new CCDispatchDetailsBean();
		ccDispatchDetailsBean.setId(financialCCDispatchDetails.getId());
		ccDispatchDetailsBean.setWorkId(financialCCDispatchDetails.getWork().getId());
		ccDispatchDetailsBean.setDispatchNumber(financialCCDispatchDetails.getDispatchNumber());
		ccDispatchDetailsBean.setDispatchDate(RESUtil.convertDateToString(financialCCDispatchDetails.getDispatchDate()));		
		ccDispatchDetailsBean.setRemarks(financialCCDispatchDetails.getRemarks());
		ccDispatchDetailsBean.setStatus(financialCCDispatchDetails.getStatus());
		if (financialCCDispatchDetails.getCcFile()!=null) {
			ccDispatchDetailsBean.setDocumentId(financialCCDispatchDetails.getCcFile().getDocumentId());
			ccDispatchDetailsBean.setDocumentName(financialCCDispatchDetails.getCcFile().getDocumentName());
		}
		return ccDispatchDetailsBean;
	}
	
	
	@Override
	public String issuePhysicalCCForWorkId(Long workid) {
		try {
			Work work = workRepository.findOne(workid);
			
			//CCDetails ccDetails = ccDetailsRepository.findByWork(work);
			CCDetails ccDetails = 
					ccDetailsRepository.findByWorkOrderByCreatedDateDesc(work).size() > 0 ? ccDetailsRepository.findByWorkOrderByCreatedDateDesc(work).get(0) : null;
			if(ccDetails!=null) {
				ccDetails.setPhysicalCCIssuedOn(new Date());
				ccDetailsRepository.save(ccDetails);
				
				work.setWorkRequestStatusId(new RequestStatus(RESConstants.REQUEST_STATUS_Physical_CC_Issued_ID));
				workRepository.save(work);
			}
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		return null;
	}
	
	@Override
	public String issueFinancialCCForWorkId(Long workid) {
		try {
			Work work = workRepository.findOne(workid);
			
			//CCDetails ccDetails = ccDetailsRepository.findByWork(work);
			CCDetails ccDetails = 
					ccDetailsRepository.findByWorkOrderByCreatedDateDesc(work).size() > 0 ? ccDetailsRepository.findByWorkOrderByCreatedDateDesc(work).get(0) : null;
			if(ccDetails!=null) {
				ccDetails.setFinancialCCIssuedOn(new Date());
				ccDetailsRepository.save(ccDetails);
				
				work.setWorkRequestStatusId(new RequestStatus(RESConstants.REQUEST_STATUS_Final_CC_Issued_ID));
				workRepository.save(work);
			}
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		return null;
	}
}
