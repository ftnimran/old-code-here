package com.res.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.res.bean.BudgetAllotmentBean;
import com.res.bean.BudgetAllotmentEEOfficeBean;
import com.res.bean.BudgetRequestBean;
import com.res.bean.BudgetRequestDetailBean;
import com.res.bean.OfficeBean;
import com.res.bean.WorkBean;
import com.res.constants.RESConstants;
import com.res.entity.AccountHead;
import com.res.entity.BudgetAllotment;
import com.res.entity.BudgetAllotmentEEOffice;
import com.res.entity.BudgetRequest;
import com.res.entity.BudgetRequestDetail;
import com.res.entity.BudgetRequestNoGeneration;
import com.res.entity.MasterBudgetStatus;
import com.res.entity.Office;
import com.res.entity.Users;
import com.res.entity.Work;
import com.res.exception.RESBusinessException;
import com.res.json.BudgetAllotmentEEOfficeJson;
import com.res.json.BudgetAllotmentJson;
import com.res.json.BudgetRequestDetailJson;
import com.res.json.BudgetRequestJson;
import com.res.repository.BudgetAllotmentEEOfficeRepository;
import com.res.repository.BudgetAllotmentRepository;
import com.res.repository.BudgetRequestDetailRepository;
import com.res.repository.BudgetRequestNoGenerationRepository;
import com.res.repository.BudgetRequestRepository;
import com.res.repository.UserRepository;
import com.res.repository.WorkRepository;
import com.res.service.BudgetService;
import com.res.service.CommonService;
import com.res.util.RESUtil;
@Service
public class BudgetServiceImpl implements BudgetService {

	public static final Logger logger = LoggerFactory.getLogger(BudgetServiceImpl.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkRepository workRepository;
	
	@Autowired
	private BudgetRequestRepository budgetRequestRepository;
	
	@Autowired
	private BudgetRequestDetailRepository budgetRequestDetailRepository;
	
	
	@Autowired
	private BudgetAllotmentRepository budgetAllotmentRepository;
	
	@Autowired
	private BudgetAllotmentEEOfficeRepository  budgetAllotmentEEOfficeRepository;
	
	@Autowired
	private BudgetRequestNoGenerationRepository budgetRequestNoGenerationRepository;
	
	
	
	@Override
	public BudgetRequestJson fetchBudgetRequestList(Pageable pageable, String searchBoxVal, String loggedInUserRole, String username) {

		BudgetRequestJson budgetRequestJson = null;

		if (searchBoxVal!= null && searchBoxVal.isEmpty())
			searchBoxVal = null; //If not null and empty then null is assigned - Explanation
		
		try {
			 
			Page<BudgetRequest> budgetRequestList = null;

			Users entity = userRepository.findByUsernameAndStatus(username, RESConstants.STATUS_ACTIVE);
			
			String officeName = entity.getOffice().getOfficeName();
			
			long count = 0;
			//If condition was assigning the same thing is not null and null. Explanation
			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) { 
				budgetRequestList = budgetRequestRepository.findAll(pageable);
				count = budgetRequestList.getTotalElements();
			}
			
			else if (loggedInUserRole.equals(RESConstants.ROLE_EnC) || loggedInUserRole.equals(RESConstants.ROLE_ACC_OFFICER)) {
				 budgetRequestList = budgetRequestRepository.findByStatus(pageable, 1L);
					count = budgetRequestList.getTotalElements();
			}
			
			List<BudgetRequestBean> beanList = new ArrayList<>();

			if (budgetRequestList != null) {
				List<BudgetRequest> entityList = budgetRequestList.getContent();
				if (entityList != null && !entityList.isEmpty()) {

					int index = 1;  //pageable.getPageNumber() * pageable.getPageSize();
					for (BudgetRequest element : entityList) {
						

						BudgetRequestBean bean = convertBudgetRequestEntityToBean(element);
						bean.setIndex(index ++);
						Collection<BudgetRequestDetail> budgetRequestDetails = element.getBudgetRequestDetails();
						
						if(null!=budgetRequestDetails) {
							//newly added
							BigDecimal existingSurrAmt = new BigDecimal(0);
							for(BudgetRequestDetail budgetRequestDetail : budgetRequestDetails) {
								bean.setAccountHeadNameE(budgetRequestDetail.getWork().getAccountHead().getAccountHeadNameE());
								// newly added
								if(budgetRequestDetail.getSurrenderAmount()!=null) {
									existingSurrAmt = budgetRequestDetail.getSurrenderAmount().add(existingSurrAmt);
								}
								bean.setSurrendedAmount(existingSurrAmt);
								//till here
								//commented because it will not add existingSurrAmt
								/*break;*/
							}
						}
						beanList.add(bean);
					}
				}

				budgetRequestJson = new BudgetRequestJson();
				budgetRequestJson.setiTotalDisplayRecords(budgetRequestList.getTotalElements());
				budgetRequestJson.setiTotalRecords(count);
				budgetRequestJson.setAaData(beanList);
			}
			return budgetRequestJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return budgetRequestJson;
		}
	}
	
	public BudgetRequestDetailJson fetchBudgetRequestDetailList(Pageable pageable, Long budgetRequestId, String loggedInUserRole, String username) {
		
		BudgetRequestDetailJson budgetRequestDetailJson = null;
		try {
			long count = 0;
			Page<BudgetRequestDetail> budgetRequestDetailList = null;
			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) { 
				budgetRequestDetailList = budgetRequestDetailRepository.findByBudgetRequest(pageable, new BudgetRequest(budgetRequestId));
				count = budgetRequestDetailList.getTotalElements();
			}
			
			 else if (loggedInUserRole.equals(RESConstants.ROLE_EnC) || loggedInUserRole.equals(RESConstants.ROLE_ACC_OFFICER)) {
				 budgetRequestDetailList = budgetRequestDetailRepository.findByBudgetRequest(pageable, new BudgetRequest(budgetRequestId));
					count = budgetRequestDetailList.getTotalElements();
			 }
			List<BudgetRequestDetailBean> beanList = new ArrayList<>();

			if (budgetRequestDetailList != null) {
				List<BudgetRequestDetail> entityList = budgetRequestDetailList.getContent();
				if (entityList != null && !entityList.isEmpty()) {

					int index = 1;  //pageable.getPageNumber() * pageable.getPageSize();
					BigDecimal existingSurrAmt = new BigDecimal(0);
					for (BudgetRequestDetail element : entityList) {
						/*if(element.getSurrenderAmount()!=null) {
							existingSurrAmt = element.getSurrenderAmount().add(existingSurrAmt);
						}*/

						BudgetRequestDetailBean bean = convertBudgetRequestDetailEntityToBean(element);
						/*bean.setSurrendedAmount(existingSurrAmt);*/
						bean.setIndex(index ++);
						beanList.add(bean);
					}
				}

				budgetRequestDetailJson = new BudgetRequestDetailJson();
				budgetRequestDetailJson.setiTotalDisplayRecords(budgetRequestDetailList.getTotalElements());
				budgetRequestDetailJson.setiTotalRecords(count);
				budgetRequestDetailJson.setAaData(beanList);
			}
			return budgetRequestDetailJson;
		}
			catch (Exception e) {
				logger.error("An exception occurred.", e);
				return budgetRequestDetailJson;
			}
		}
	
public BudgetAllotmentEEOfficeJson fetchBudgetRequestAllotementList(Pageable pageable, Long budgetAllotmentId, String loggedInUserRole, String username) {
		
	BudgetAllotmentEEOfficeJson budgetRequestDetailJson = null;
		try {
			long count = 0;
			Page<BudgetAllotmentEEOffice> budgetRequestDetailList = null;
			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) { 
				budgetRequestDetailList = budgetAllotmentEEOfficeRepository.findByBudgetAllotment(pageable, new BudgetAllotment(budgetAllotmentId));
				count = budgetRequestDetailList.getTotalElements();
			}
			
			 else if (loggedInUserRole.equals(RESConstants.ROLE_EnC) || loggedInUserRole.equals(RESConstants.ROLE_ACC_OFFICER)) {
				 budgetRequestDetailList = budgetAllotmentEEOfficeRepository.findByBudgetAllotment(pageable, new BudgetAllotment(budgetAllotmentId));
					count = budgetRequestDetailList.getTotalElements();
			 }
			List<BudgetAllotmentEEOfficeBean> beanList = new ArrayList<>();

			if (budgetRequestDetailList != null) {
				List<BudgetAllotmentEEOffice> entityList = budgetRequestDetailList.getContent();
				if (entityList != null && !entityList.isEmpty()) {

					int index = 1;  //pageable.getPageNumber() * pageable.getPageSize();
					for (BudgetAllotmentEEOffice element : entityList) {

						BudgetAllotmentEEOfficeBean bean = convertBudgetAllotmentEEOfficeEntityToBean(element);
						bean.setIndex(index ++);
						beanList.add(bean);
					}
				}

				budgetRequestDetailJson = new BudgetAllotmentEEOfficeJson();
				budgetRequestDetailJson.setiTotalDisplayRecords(budgetRequestDetailList.getTotalElements());
				budgetRequestDetailJson.setiTotalRecords(count);
				budgetRequestDetailJson.setAaData(beanList);
			}
			return budgetRequestDetailJson;
		}
			catch (Exception e) {
				logger.error("An exception occurred.", e);
				return budgetRequestDetailJson;
			}
		}
	@Transactional
	@Override
	public String saveBudgetRequest(BudgetRequestBean budgetRequestBean) {
		
		try {
			
			List<BudgetRequestDetailBean> budgetRequestDetailBeanList = budgetRequestBean.getBudgetRequestDetailBeanList();
			BudgetRequestDetailBean firstBean=budgetRequestDetailBeanList.get(0);
			String accountHeadName=firstBean.getAccountHeadNameE();
			for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				
				if(!bean.getAccountHeadNameE().equalsIgnoreCase(accountHeadName) ) {
					return "Account Head should be same for selected Works.";
				}
				
				if(bean.getRemainingAmountTotal().compareTo(bean.getRequestedAmount()) <0) {
					return "Requested Amount can not be greater than Remaing amount";
				}
				
				Work work  = workRepository.findOne(bean.getWorkId());
				if (work.getWorkRequestStatusId().getId() >= 14) {
					return "Budget Requested Can not be generated for " + work.getWorkRequisitionNo() + " . Since its Final CC is Generated / Issued.";
				}
			}
		
			budgetRequestBean.setNoOfWork(Long.valueOf(budgetRequestDetailBeanList.size()));
			
			BigDecimal requestedAmount = new BigDecimal(0);

			for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				requestedAmount = requestedAmount.add(bean.getRequestedAmount());
			}
			
			budgetRequestBean.setRequestedAmount(requestedAmount);
			
			BudgetRequest budgetRequest = new BudgetRequest();
			
			convertBudgetRequestBeanToEntity(budgetRequest, budgetRequestBean);

			budgetRequest.setRequestDate(new Date());
			
			budgetRequestRepository.save(budgetRequest);
			
			List<BudgetRequestDetail> budgetRequestDetailList = new ArrayList<BudgetRequestDetail>();
			
			BudgetRequestDetail entity = null;
			BudgetRequestDetail budgetRequestDetail = null;
			for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				
				budgetRequestDetail = new BudgetRequestDetail();
				entity = convertBudgetRequestDetailBeanToEntity(budgetRequestDetail, bean);
				entity.setBudgetRequest(budgetRequest);
				budgetRequestDetailList.add(entity);
			}
			
			budgetRequestDetailRepository.save(budgetRequestDetailList);
			
			return null;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		
	}
	
	@Override
	@Transactional
	public String saveEditBudgetRequest(BudgetRequestBean budgetRequestBean) {
		
		try {
			//In case rejected, just update status - no validation required.
			if(budgetRequestBean.getStatusId() == 4 ) {
				BudgetRequest budgetRequest = budgetRequestRepository.findOne(budgetRequestBean.getId());
				budgetRequest.setStatus(new MasterBudgetStatus(budgetRequestBean.getStatusId()));
				budgetRequest.setRemark(budgetRequestBean.getRemark());
				budgetRequestRepository.save(budgetRequest);
				return null;
			}
			
			List<BudgetRequestDetailBean> budgetRequestDetailBeanList = budgetRequestBean.getBudgetRequestDetailBeanList();
			
			for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				
				if(bean.getRemainingAmountTotal().compareTo(bean.getRequestedAmount()) <0) {
					return "Requested Amount can not be greater than Remaing amount";
				}
				
				if(budgetRequestBean.getStatusId() == 3 || budgetRequestBean.getStatusId() == 5 ) //save as draft or approved
				{
					if(bean.getRequestedAmount().compareTo(bean.getApprovedAmount()) <0) {
						return "Approved Amount can not be greater than Requested amount";
					}	
				}
				
				Work work = workRepository.findOne(bean.getWorkId());
				if (work.getWorkRequestStatusId().getId() >= 14) {
					return "Budget Requested Can not be Approved for " + work.getWorkRequisitionNo() + " . Since its Final CC is Generated / Issued.";
				}
				
			}
			
			if(budgetRequestBean.getStatusId() >= 2 && budgetRequestBean.getStatusId() != 4) { //submitted after that Enc Flow
				for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
					Work work = workRepository.findOne(bean.getWorkId());
					Long AccHeadId  = work.getAccountHead().getId();
					Long officeId = work.getExecutiveEngineerOffice().getId();
					
					int allottedAmountTotal = 0;
					List<BudgetAllotmentEEOffice> list = budgetAllotmentEEOfficeRepository.findByaccountHeadIdAndOfficeIdAndLapsed(AccHeadId, officeId, false);
					for(BudgetAllotmentEEOffice budgetAllotmentEEOffice :  list ) {
						allottedAmountTotal = allottedAmountTotal + Integer.parseInt(budgetAllotmentEEOffice.getAmount());
					}
					
					if(bean.getApprovedAmount().intValue() > allottedAmountTotal) {
						return "Approved Amount can not be greater than Allotted amount for Office : " +  work.getExecutiveEngineerOffice().getOfficeName() + " , under Account Head : " + work.getAccountHead().getAccountHeadNameE();
					}
					
					else {
						if(budgetRequestBean.getStatusId() == 5) {  //In case of approval only deduct from allotted amount
							int newAmount = 0;
							int approved = bean.getApprovedAmount().intValue();
							
							for(BudgetAllotmentEEOffice budgetAllotmentEEOffice :  list ) {
								
								if(Integer.parseInt(budgetAllotmentEEOffice.getAmount()) >= approved) {
									newAmount = Integer.parseInt(budgetAllotmentEEOffice.getAmount()) - approved;
									budgetAllotmentEEOffice.setAmount(newAmount+"");
									budgetAllotmentEEOfficeRepository.save(budgetAllotmentEEOffice);
									break;
								}
								
								if(Integer.parseInt(budgetAllotmentEEOffice.getAmount()) < approved) {
									approved =  approved - Integer.parseInt(budgetAllotmentEEOffice.getAmount()) ;
									budgetAllotmentEEOffice.setAmount(0+"");
									budgetAllotmentEEOfficeRepository.save(budgetAllotmentEEOffice);
								}
								
							}
						}
					}
				}
			}
			/////////////////////////////////////
			
			budgetRequestBean.setNoOfWork(Long.valueOf(budgetRequestDetailBeanList.size()));
			
			BigDecimal requestedAmount = new BigDecimal(0);
			BigDecimal approvedAmount = new BigDecimal(0);

			for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				requestedAmount = requestedAmount.add(bean.getRequestedAmount());
				if(bean.getApprovedAmount() != null) {
					approvedAmount = approvedAmount.add(bean.getApprovedAmount());	
				}
			}
			
			budgetRequestBean.setRequestedAmount(requestedAmount);
			
			budgetRequestBean.setApprovedAmount(approvedAmount);
			
			
			BudgetRequest budgetRequest = budgetRequestRepository.findOne(budgetRequestBean.getId());
			
			convertBudgetRequestBeanToEntity(budgetRequest, budgetRequestBean);
			
			budgetRequest.setModifiedDate(new Date());
			
			budgetRequestRepository.save(budgetRequest);
			
			List<BudgetRequestDetail> budgetRequestDetailList= new ArrayList<BudgetRequestDetail>();			
			
			for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				BudgetRequestDetail budgetRequestDetail = budgetRequestDetailRepository.findOne(bean.getId());
				
				budgetRequestDetail.setApprovedAmount(bean.getApprovedAmount());
				budgetRequestDetail.setRequestedAmount(bean.getRequestedAmount());
				
				budgetRequestDetailList.add(budgetRequestDetail);
				
			}
				
			budgetRequestDetailRepository.save(budgetRequestDetailList);
			
		if(budgetRequestBean.getStatusId()!=3)	{
         List<BudgetAllotment> budgetAllotmentList= new ArrayList<BudgetAllotment>();	
			
			BudgetAllotment budgetAllotment = new BudgetAllotment();
			BigDecimal approvedAmountFinal=new BigDecimal(0);
			for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				
				Work work = workRepository.findOne(bean.getWorkId());
				
				if(bean.getApprovedAmount()!=null) {
				BigDecimal surrenderAmount = bean.getApprovedAmount();
				approvedAmountFinal = approvedAmountFinal.add(surrenderAmount);
				
				}
				budgetAllotment.setAccountHead(work.getAccountHead());
				
				
				budgetAllotmentList.add(budgetAllotment);
				
			}
			
			budgetAllotment.setReceivedOn(new Date());
			budgetAllotment.setStatusId(new Long(2));
			BigDecimal myAmount = approvedAmountFinal.negate();
			/*BigDecimal myAmount = approvedAmountFinal;*/
			budgetAllotment.setAmount(myAmount);
				
			budgetAllotmentRepository.save(budgetAllotment);
		}
			
			
			
			
			
			return null;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		
	}
	
	//saveSurrenderRequest by nikhil
	
	@Override
	@Transactional
	public String saveSurrenderRequest(BudgetRequestBean budgetRequestBean) {
		
		try {
			
			
			// this validation is for Total Surrender Amount can not be greater than Approved Amount
			List<BudgetRequestDetailBean> budgetRequestDetailBeanList = budgetRequestBean.getBudgetRequestDetailBeanList();
			
			for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				BudgetRequestDetail budgetRequestDetail = budgetRequestDetailRepository.findOne(bean.getId());
				BigDecimal existingSurrAmt = new BigDecimal(0);
				if(budgetRequestDetail.getSurrenderAmount()!=null) {
					existingSurrAmt = budgetRequestDetail.getSurrenderAmount();
				}
				if(bean.getApprovedAmount()!=null && bean.getSurrendedAmount()!=null) {
				if(bean.getApprovedAmount().compareTo(bean.getSurrendedAmount().add(existingSurrAmt)) <0) {
					return "Total Surrender Amount can not be greater than Approved Amount";
					
				}
			  }
				
			}
			
			//add total surrender amounts in budget Allotment table
			
			budgetRequestBean.setNoOfWork(Long.valueOf(budgetRequestDetailBeanList.size()));
			
			BigDecimal requestedAmount = new BigDecimal(0);
			BigDecimal approvedAmount = new BigDecimal(0);
			
			List<BudgetAllotment> budgetAllotmentList= new ArrayList<BudgetAllotment>();	
			
			BudgetAllotment budgetAllotment = new BudgetAllotment();
			BigDecimal surrenderAmountFinal=new BigDecimal(0);
			for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				
				Work work = workRepository.findOne(bean.getWorkId());
				
				if(bean.getSurrendedAmount()!=null) {
				BigDecimal surrenderAmount = bean.getSurrendedAmount();
				surrenderAmountFinal = surrenderAmountFinal.add(surrenderAmount);
				
				}
				budgetAllotment.setAccountHead(work.getAccountHead());
				budgetAllotmentList.add(budgetAllotment);
				
			}
			
			budgetAllotment.setReceivedOn(new Date());
			budgetAllotment.setStatusId(new Long(3));
			/*BigDecimal myAmount = surrenderAmountFinal.negate();*/
			BigDecimal myAmount = surrenderAmountFinal;
			budgetAllotment.setAmount(myAmount);
				
			budgetAllotmentRepository.save(budgetAllotment);
			
			//add  surrender amounts in budget request Details table
			
			List budgetRequestDetailList= new ArrayList();
         for(BudgetRequestDetailBean bean : budgetRequestDetailBeanList) {
				
				Work work = workRepository.findOne(bean.getWorkId());
				BudgetRequestDetail budgetRequestDetail = budgetRequestDetailRepository.findOne(bean.getId());
				BigDecimal existingAmt = new BigDecimal(0);
				if(budgetRequestDetail.getSurrenderAmount()!=null) {
				 existingAmt = budgetRequestDetail.getSurrenderAmount();
				}
				
				if(bean.getSurrendedAmount()!=null) {
					
						BigDecimal surrenderAmount  = existingAmt.add(bean.getSurrendedAmount());

				budgetRequestDetail.setSurrenderAmount(surrenderAmount);
				}
			
				budgetRequestDetailList.add(budgetRequestDetail);
				
			}
				
			budgetRequestDetailRepository.save(budgetRequestDetailList);
			//
			
			return null;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
		
	}
	
	private BudgetRequestBean convertBudgetRequestEntityToBean(BudgetRequest budgetRequest) {
		BudgetRequestBean budgetRequestBean = new BudgetRequestBean();
		
		budgetRequestBean.setId(budgetRequest.getId());
		budgetRequestBean.setNoOfWork(budgetRequest.getNoOfWork());
		budgetRequestBean.setRequestNumber(budgetRequest.getRequestNumber());
		budgetRequestBean.setRequestedAmount(budgetRequest.getRequestedAmount());
		budgetRequestBean.setApprovedAmount(budgetRequest.getApprovedAmount());
		budgetRequestBean.setRequestDate(RESUtil.convertDateToString(budgetRequest.getRequestDate()));
		budgetRequestBean.setStatus(budgetRequest.getStatus().getStatusNameE());
		budgetRequestBean.setStatusId(budgetRequest.getStatus().getStatusId());
		if(budgetRequest.getOffice()!= null) {
		budgetRequestBean.setOfficeBeanId(budgetRequest.getOffice().getId());
		budgetRequestBean.setOfficeName(budgetRequest.getOffice().getOfficeName());
		}
		
		budgetRequestBean.setRemark(budgetRequest.getRemark());
		
		return budgetRequestBean;
	}
	
	private BudgetRequest convertBudgetRequestBeanToEntity(BudgetRequest budgetRequest, BudgetRequestBean budgetRequestBean)  {
		
		//Dont update in edit.
		if(budgetRequest.getRequestNumber() == null ) {
		
			String financialYear = RESUtil.getFinancialYear();
		    String officeName = budgetRequestBean.getOfficeName();
		    String office = officeName.substring(officeName.lastIndexOf(",") + 1);
		    String  generatedString = financialYear+"/" + office ;

		    BudgetRequestNoGeneration budgetRequestNoGeneration = budgetRequestNoGenerationRepository.findByOfficeAndFinancialYear(office, financialYear);
			    
		    if (budgetRequestNoGeneration == null) {
				generatedString = generatedString + "/1";
				persistBudgetRequestNoGeneration(financialYear, office, 1);
			} else {
				int newCount = budgetRequestNoGeneration.getCounter() + 1;
				budgetRequestNoGeneration.setCounter(newCount);
				budgetRequestNoGenerationRepository.save(budgetRequestNoGeneration);
				generatedString = generatedString + "/" + newCount;
			}

			budgetRequest.setRequestNumber(generatedString);
		}
		
		budgetRequest.setNoOfWork(budgetRequestBean.getNoOfWork());
		budgetRequest.setRequestedAmount(budgetRequestBean.getRequestedAmount());
		budgetRequest.setApprovedAmount(budgetRequestBean.getApprovedAmount());
		budgetRequest.setStatus(new MasterBudgetStatus(budgetRequestBean.getStatusId()));	
		budgetRequest.setRemark(budgetRequestBean.getRemark());
		
		if(budgetRequestBean.getOfficeBeanId() != null) {
			budgetRequest.setOffice(new Office(budgetRequestBean.getOfficeBeanId()));	
		}
		return budgetRequest;
	}
	
	private void persistBudgetRequestNoGeneration(String financialYear, String office, int i) {

		BudgetRequestNoGeneration workLegacyIdGeneration = new BudgetRequestNoGeneration();
		workLegacyIdGeneration.setFinancialYear(financialYear);
		workLegacyIdGeneration.setOffice(office);
		workLegacyIdGeneration.setCounter(i);
		budgetRequestNoGenerationRepository.save(workLegacyIdGeneration);
	}
	
	private BudgetRequestDetailBean convertBudgetRequestDetailEntityToBean(BudgetRequestDetail budgetRequestDetail) {
		
		BudgetRequestDetailBean budgetRequestDetailBean = new BudgetRequestDetailBean();
		
		budgetRequestDetailBean.setId(budgetRequestDetail.getId());
		if(budgetRequestDetail.getBudgetRequest() != null) {
			budgetRequestDetailBean.setBudgetRequestId(budgetRequestDetail.getBudgetRequest().getId());	
		}
		
		budgetRequestDetailBean.setApprovedAmount(budgetRequestDetail.getApprovedAmount());
		
		budgetRequestDetailBean.setRequestedAmount(budgetRequestDetail.getRequestedAmount());
		WorkBean workBean = commonService.fetchWorkDetails(budgetRequestDetail.getWork().getId());
				
		budgetRequestDetailBean.setWorkBean(workBean);
		budgetRequestDetailBean.setWorkId(workBean.getWorkId());
		budgetRequestDetailBean.setAccountHeadNameE(workBean.getAccountHeadName());
		budgetRequestDetailBean.setAccountHeadId(workBean.getAccountHeadId());
		if(budgetRequestDetail.getSurrenderAmount()!=null) {
			budgetRequestDetailBean.setSurrendedAmount(budgetRequestDetail.getSurrenderAmount());
		}
		
		
		return budgetRequestDetailBean;
	}
	
	private BudgetAllotmentEEOfficeBean convertBudgetAllotmentEEOfficeEntityToBean(BudgetAllotmentEEOffice budgetAllotmentEEOffice) {
		
		BudgetAllotmentEEOfficeBean budgetAllotmentEEOfficeBean = new BudgetAllotmentEEOfficeBean();
		
		budgetAllotmentEEOfficeBean.setId(budgetAllotmentEEOffice.getId());
		budgetAllotmentEEOfficeBean.setAmount(budgetAllotmentEEOffice.getAmount());
		budgetAllotmentEEOfficeBean.setOfficeName(budgetAllotmentEEOffice.getOffice().getOfficeName());
		budgetAllotmentEEOfficeBean.setOfficeBeanId(budgetAllotmentEEOffice.getOffice().getId());		
		budgetAllotmentEEOfficeBean.setAccountHead(budgetAllotmentEEOffice.getAccountHead().getAccountHeadNameE());
		budgetAllotmentEEOfficeBean.setAccountHeadId(budgetAllotmentEEOffice.getAccountHead().getId());
		budgetAllotmentEEOfficeBean.setReceivedOn(budgetAllotmentEEOffice.getBudgetAllotment().getReceivedOn());
		budgetAllotmentEEOfficeBean.setEeOfficeAmountReceivedOn(RESUtil.convertDateToString(budgetAllotmentEEOffice.getCreatedDate()));
		budgetAllotmentEEOfficeBean.setReceivedAmount(budgetAllotmentEEOffice.getBudgetAllotment().getAmount().toString());
		
		return budgetAllotmentEEOfficeBean;
	}
	
	private BudgetRequestDetail convertBudgetRequestDetailBeanToEntity(BudgetRequestDetail budgetRequestDetail, BudgetRequestDetailBean budgetRequestDetailBean) {
		
		budgetRequestDetail.setId(budgetRequestDetailBean.getId());
		budgetRequestDetail.setWork(new Work(budgetRequestDetailBean.getWorkId()));
		budgetRequestDetail.setRequestedAmount(budgetRequestDetailBean.getRequestedAmount());
		budgetRequestDetail.setApprovedAmount(budgetRequestDetailBean.getApprovedAmount());
		
		return budgetRequestDetail;
	}

	
public BudgetAllotmentJson fetchBudgetAllotmentList(Pageable pageable, String loggedInUserRole, String username,String accountHeadId) {
		
	BudgetAllotmentJson budgetRequestDetailJson = null;
	Long accountHeadId1=null;
		try {
			if(!accountHeadId.equals("")) {
				accountHeadId1 = Long.parseLong(accountHeadId);
			}
			
			
			long count = 0;
			Page<BudgetAllotment> budgetRequestDetailList = null;
			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) { 
				budgetRequestDetailList = budgetAllotmentRepository.findAll(pageable);
				count = budgetRequestDetailList.getTotalElements();
			}
			
			 else if (loggedInUserRole.equals(RESConstants.ROLE_EnC) || loggedInUserRole.equals(RESConstants.ROLE_ACC_OFFICER)) {
				 budgetRequestDetailList = budgetAllotmentRepository.findByAccountHead(pageable,accountHeadId1);
					count = budgetRequestDetailList.getTotalElements();
			 }
			List<BudgetAllotmentBean> beanList = new ArrayList<>();

			if (budgetRequestDetailList != null) {
				List<BudgetAllotment> entityList = budgetRequestDetailList.getContent();
				if (entityList != null && !entityList.isEmpty()) {

					int index = 1;  //pageable.getPageNumber() * pageable.getPageSize();
					BudgetAllotmentBean  bean = null;
					for (BudgetAllotment element : entityList) {
						bean = new BudgetAllotmentBean();
						convertBudgetAllotmentEntityToBean(element, bean);
						bean.setIndex(index ++);
						beanList.add(bean);
					}
				}

				budgetRequestDetailJson = new BudgetAllotmentJson();
				budgetRequestDetailJson.setiTotalDisplayRecords(budgetRequestDetailList.getTotalElements());
				budgetRequestDetailJson.setiTotalRecords(count);
				budgetRequestDetailJson.setAaData(beanList);
			}
			return budgetRequestDetailJson;
		}
			catch (Exception e) {
				logger.error("An exception occurred.", e);
				return budgetRequestDetailJson;
			}
		}



public BudgetAllotmentJson fetchBudgetAllotmentListAllAccHead(Pageable pageable, Long accountHeadId,
		String loggedInUserRole, String username) {
	
	BudgetAllotmentJson budgetRequestDetailJson = null;
	
		try {
			
			
			
			long count = 0;
			Page<BudgetAllotment> budgetRequestDetailList = null;
			/*if (loggedInUserRole.equals(RESConstants.ROLE_EE)) { 
				budgetRequestDetailList = budgetAllotmentRepository.findAll(pageable);
				count = budgetRequestDetailList.getTotalElements();
			}*/
			
			 if (loggedInUserRole.equals(RESConstants.ROLE_EnC) || loggedInUserRole.equals(RESConstants.ROLE_ACC_OFFICER)) {
				 budgetRequestDetailList = budgetAllotmentRepository.findByAccountHead(pageable,accountHeadId);
					count = budgetRequestDetailList.getTotalElements();
			 }
			List<BudgetAllotmentBean> beanList = new ArrayList<>();

			if (budgetRequestDetailList != null) {
				List<BudgetAllotment> entityList = budgetRequestDetailList.getContent();
				if (entityList != null && !entityList.isEmpty()) {

					int index = 1;  //pageable.getPageNumber() * pageable.getPageSize();
					BudgetAllotmentBean  bean = null;
					for (BudgetAllotment element : entityList) {
						bean = new BudgetAllotmentBean();
						convertBudgetAllotmentEntityToBean(element, bean);
						bean.setIndex(index ++);
						beanList.add(bean);
					}
				}

				budgetRequestDetailJson = new BudgetAllotmentJson();
				budgetRequestDetailJson.setiTotalDisplayRecords(budgetRequestDetailList.getTotalElements());
				budgetRequestDetailJson.setiTotalRecords(count);
				budgetRequestDetailJson.setAaData(beanList);
			}
			return budgetRequestDetailJson;
		}
			catch (Exception e) {
				logger.error("An exception occurred.", e);
				return budgetRequestDetailJson;
			}
		}



//fetchBudgetAllotmentListAccHeadWise

public BudgetAllotmentJson fetchBudgetAllotmentListAccHeadWise(Pageable pageable, String loggedInUserRole, String username,String accountHeadId) {
	
	BudgetAllotmentJson budgetRequestDetailJson = null;
	List<Object[]> entityList1=null;
	
	int maxLimit=(pageable.getPageSize()*pageable.getPageNumber())==0?pageable.getPageSize():(pageable.getPageSize()*pageable.getPageNumber());
	Long accountHeadId1=null;
		try {
			if(!accountHeadId.equals("")) {
				accountHeadId1 = Long.parseLong(accountHeadId);
			}
			
			
			long count = 0;
			Page<BudgetAllotment> budgetRequestDetailList = null;
			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) { 
				budgetRequestDetailList = budgetAllotmentRepository.findAll(pageable);
				count = budgetRequestDetailList.getTotalElements();
			}
			
			 else if (loggedInUserRole.equals(RESConstants.ROLE_EnC) || loggedInUserRole.equals(RESConstants.ROLE_ACC_OFFICER)) {
				 entityList1 = budgetAllotmentRepository.findByAccountHeadGroupBy(accountHeadId1,pageable.getOffset(),maxLimit);
				 //entityList=	workRepository.findWorkTenderByQuery( userEntity.getOffice().getId(),pageable.getOffset(),maxLimit);
					count = entityList1.size();
			 }
			List<BudgetAllotmentBean> beanList = new ArrayList<>();
			
			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) { 

			if (budgetRequestDetailList != null) {
				List<BudgetAllotment> entityList = budgetRequestDetailList.getContent();
				if (entityList != null && !entityList.isEmpty()) {
					int index = pageable.getPageNumber()
							* pageable.getPageSize();

					  //pageable.getPageNumber() * pageable.getPageSize();
					BudgetAllotmentBean  bean = null;
					for (BudgetAllotment element : entityList) {
						bean = new BudgetAllotmentBean();
						convertBudgetAllotmentEntityToBean(element, bean);
						bean.setIndex(index ++);
						beanList.add(bean);
					}
				}

				budgetRequestDetailJson = new BudgetAllotmentJson();
				budgetRequestDetailJson.setiTotalDisplayRecords(budgetRequestDetailList.getTotalElements());
				budgetRequestDetailJson.setiTotalRecords(count);
				budgetRequestDetailJson.setAaData(beanList);
			}
			}
			
			
			
			 if (loggedInUserRole.equals(RESConstants.ROLE_EnC) || loggedInUserRole.equals(RESConstants.ROLE_ACC_OFFICER)) {

				
					///////
					if (entityList1 != null && !entityList1.isEmpty()) {
						int index = pageable.getPageNumber()
								* pageable.getPageSize();
						for (Object[] objArr : entityList1) {
							
//							AdministrationSanctionBean bean = convertAdministrationSanctionEntityToTenderBean(administrationSanctionEntity);
//						/	WorkBean bean1 = convertWorkEntityToBean(administrationSanctionEntity);
						
							BudgetAllotmentBean bean=new BudgetAllotmentBean();
						bean.setAccountHeadId(Long.parseLong(objArr[0].toString()));
						//bean.setWorkRequisitionDate(RESUtil.convertDateToString(entity.getCreatedDate()));			
						//bean.setIsLegacy(entity.getIsLegacy());
						bean.setAccountHead(objArr[1].toString());
						Double dd = ((Double)objArr[2]);
						BigDecimal big = BigDecimal.valueOf(dd);
						bean.setAmount(big);
						//bean.setWorkTypeId(Long.parseLong(objArr[3].toString()));
						bean.setReceivedOn(objArr[3].toString());
						bean.setIndex(++index);
						
						
							beanList.add(bean);
						}
					}
              /////
					budgetRequestDetailJson = new BudgetAllotmentJson();
					budgetRequestDetailJson.setiTotalDisplayRecords(budgetAllotmentRepository.findByAccountHeadGroupByTotalDispRecord(accountHeadId1).size());
					budgetRequestDetailJson.setiTotalRecords(budgetAllotmentRepository.findByAccountHeadGroupByTotalRecord().size());
					//findByAccountHeadGroupByTotalDispRecord
					budgetRequestDetailJson.setAaData(beanList);
				
				}
			
			
			return budgetRequestDetailJson;
		}
			catch (Exception e) {
				logger.error("An exception occurred.", e);
				return budgetRequestDetailJson;
			}
		}
	

@Override
@Transactional
	public String saveBudgetAllotment(BudgetAllotmentBean budgetAllotmentBean) {
		
		try {
			
			int total = 0;
			int existingAmount=0;
			if(budgetAllotmentBean.getId()!=null) {
			List<BudgetAllotmentEEOffice> budgetAllotmentEEOfficeList1 = budgetAllotmentEEOfficeRepository.findByBudgetAllotment(new BudgetAllotment(budgetAllotmentBean.getId()));
			for(BudgetAllotmentEEOffice budgetAllotmentEEOffice1 : budgetAllotmentEEOfficeList1) {
				if(budgetAllotmentEEOffice1.getAmount()!=null) {
					existingAmount = Integer.parseInt(budgetAllotmentEEOffice1.getAmount())+existingAmount;
				}
				
			}
			}
			
			List<BudgetAllotmentEEOfficeBean> budgetRequestDetailBeanList = budgetAllotmentBean.getBudgetAllotmentEEOfficeList();
			
			for( BudgetAllotmentEEOfficeBean budgetAllotmentEEOfficeBean : budgetRequestDetailBeanList) {
				/*BudgetAllotmentEEOffice budgetAllotmentEEOffice = budgetAllotmentEEOfficeRepository.findOne(budgetAllotmentBean.getId());*/
				/*BigDecimal existingAmt = new BigDecimal(0);*/
				/*if(budgetAllotmentEEOffice.getAmount()!=null) {
					existingAmount = Integer.parseInt(budgetAllotmentEEOffice.getAmount())+existingAmount;
				}*/
				if(budgetAllotmentEEOfficeBean.getAmount() != null && !("").equals(budgetAllotmentEEOfficeBean.getAmount())) {
					total = Integer.parseInt(budgetAllotmentEEOfficeBean.getAmount())+ total;
				}
			}
			
			if(total+existingAmount > budgetAllotmentBean.getAmount().intValue()) {
				return "Total cannot be Greater than amount received";
			}
			
			/*if(total < budgetAllotmentBean.getAmount().intValue()) {
				return "Total cannot be Smaller than amount received";
			}*/
			
			BudgetAllotment budgetAllotment=null;
			if(null!= budgetAllotmentBean.getId()) {
				budgetAllotment=budgetAllotmentRepository.findOne(budgetAllotmentBean.getId());
				budgetAllotment.setModifiedDate(new Date());
			}else {
				budgetAllotment  = new BudgetAllotment();
				convertBudgetAllotmentBeanToEntity(budgetAllotment, budgetAllotmentBean);
				budgetAllotment.setStatusId(new Long(1));
			}
			budgetAllotmentRepository.save(budgetAllotment);
			
			
			
			
			List<BudgetAllotmentEEOffice> budgetAllotmentEEOfficeList = new ArrayList<BudgetAllotmentEEOffice>();
			BudgetAllotmentEEOffice budgetAllotmentEEOffice = null;
			
			for( BudgetAllotmentEEOfficeBean budgetAllotmentEEOfficeBean : budgetRequestDetailBeanList) {
				
				if(budgetAllotmentEEOfficeBean.getAmount() != null) {
						budgetAllotmentEEOffice = new BudgetAllotmentEEOffice();
						convertBudgetAllotmentEEOfficeBeanToEntity(budgetAllotmentEEOffice, budgetAllotmentEEOfficeBean);
						budgetAllotmentEEOffice.setBudgetAllotment(budgetAllotment);
						budgetAllotmentEEOffice.setAccountHead(new AccountHead(budgetAllotmentBean.getAccountHeadId()));
					
					budgetAllotmentEEOfficeList.add(budgetAllotmentEEOffice);
				}
				
			}
			budgetAllotmentEEOfficeRepository.save(budgetAllotmentEEOfficeList);
			return null;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	private BudgetAllotment convertBudgetAllotmentBeanToEntity(BudgetAllotment budgetAllotment, BudgetAllotmentBean budgetAllotmentBean)  {
		
		budgetAllotment.setAccountHead(new AccountHead(budgetAllotmentBean.getAccountHeadId()));
		budgetAllotment.setAmount(budgetAllotmentBean.getAmount());
		budgetAllotment.setCreatedDate(new Date());
		/*budgetAllotment.setStatusId(new Long(1));*/
		
		try {
			if(budgetAllotmentBean.getReceivedOn() != null) {
				budgetAllotment.setReceivedOn(RESUtil.convertStringToDate(budgetAllotmentBean.getReceivedOn()));	
			}
		} catch (RESBusinessException e) {
			e.printStackTrace();
		}
		return budgetAllotment;
	}
	
	private BudgetAllotmentEEOffice convertBudgetAllotmentEEOfficeBeanToEntity(BudgetAllotmentEEOffice budgetAllotmentEEOffice, BudgetAllotmentEEOfficeBean budgetAllotmentEEOfficeBean)  {
			
		budgetAllotmentEEOffice.setAmount(budgetAllotmentEEOfficeBean.getAmount());
		budgetAllotmentEEOffice.setOffice(new Office(budgetAllotmentEEOfficeBean.getOfficeBeanId()));
		budgetAllotmentEEOffice.setCreatedDate(new Date());

		return budgetAllotmentEEOffice;
	}
	
	
	private BudgetAllotment convertBudgetAllotmentEntityToBean(BudgetAllotment budgetAllotment, BudgetAllotmentBean budgetAllotmentBean)  {
		budgetAllotmentBean.setId(budgetAllotment.getId());
		
		budgetAllotmentBean.setAccountHead(budgetAllotment.getAccountHead().getAccountHeadNameE());
		budgetAllotmentBean.setAccountHeadId(budgetAllotment.getAccountHead().getId());
		budgetAllotmentBean.setAmount(budgetAllotment.getAmount());
		budgetAllotmentBean.setReceivedOn(RESUtil.convertDateToString(budgetAllotment.getReceivedOn()));	
			 
		return budgetAllotment;
	}
	
	@Override
	public BudgetRequestBean fetchBudgetRequest(Long budgetRequestId) {
		BudgetRequest budgetRequest  = budgetRequestRepository.findOne(budgetRequestId);
		return convertBudgetRequestEntityToBean(budgetRequest);
	}
	
	@Override
	public List<BudgetAllotmentEEOfficeBean> fetchBudgetAllotmentEEOffice(Long budgetRequestId) {
		
		BudgetRequest budgetRequest = budgetRequestRepository.findOne(budgetRequestId);
		Long officeId = budgetRequest.getOffice().getId();
		
		List<BudgetAllotmentEEOffice> list = budgetAllotmentEEOfficeRepository.findByOfficeIdAndLapsed(officeId, false);
		
		List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeBeanList = new ArrayList<BudgetAllotmentEEOfficeBean>();
		
		for(BudgetAllotmentEEOffice budgetAllotmentEEOffice: list) {
			BudgetAllotmentEEOfficeBean bean = convertBudgetAllotmentEEOfficeEntityToBean(budgetAllotmentEEOffice);
			budgetAllotmentEEOfficeBeanList.add(bean);
		}
		
		return budgetAllotmentEEOfficeBeanList ;
	}
	
	
	public Map get(List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeBeanList) {
		Map map = new HashMap ();
		for(BudgetAllotmentEEOfficeBean bean :  budgetAllotmentEEOfficeBeanList) {
		}
		
		return map;
	}

	
	/*public BudgetAllotmentBean fetchBudgetAllotment(Long budgetAllotmentId) {
		BudgetAllotment budgetAllotment = budgetAllotmentRepository.findOne(budgetAllotmentId);
		BudgetAllotmentBean budgetAllotmentBean=new BudgetAllotmentBean();
		convertBudgetAllotmentEntityToBean(budgetAllotment, budgetAllotmentBean);
		List<BudgetAllotmentEEOffice> findByBudgetAllotment = budgetAllotmentEEOfficeRepository.findByBudgetAllotment(budgetAllotment);
		List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeBeans=null;
		List<OfficeBean> fetchOfficesByOfficeType = commonService.fetchOfficesByOfficeType(RESConstants.EE_OFFICE_TYPE_ID);
		if(null!= fetchOfficesByOfficeType) {
			budgetAllotmentEEOfficeBeans=new LinkedList<BudgetAllotmentEEOfficeBean>();
			for(OfficeBean officeBean : fetchOfficesByOfficeType) {
				BudgetAllotmentEEOfficeBean budgetAllotmentEEOfficeBean=new BudgetAllotmentEEOfficeBean();
				budgetAllotmentEEOfficeBean.setOfficeBeanId(officeBean.getId());
				budgetAllotmentEEOfficeBean.setOfficeName(officeBean.getOfficeName());
				
				if(null!= findByBudgetAllotment) {
					innerloop:
					for(BudgetAllotmentEEOffice budgetAllotmentEEOffice : findByBudgetAllotment) {
						if(budgetAllotmentEEOffice.getOffice().getId().equals(officeBean.getId())) {
							budgetAllotmentEEOfficeBean.setId(budgetAllotmentEEOffice.getId());
							budgetAllotmentEEOfficeBean.setAmount(budgetAllotmentEEOffice.getAmount());
							budgetAllotmentEEOfficeBean.setBudgetAllotmentId(budgetAllotmentEEOffice.getBudgetAllotment().getId());
							budgetAllotmentEEOfficeBean.setEeOfficeAmountReceivedOn(RESUtil.convertDateToString(budgetAllotmentEEOffice.getCreatedDate()));
							break innerloop;
						}
					}
				}
				budgetAllotmentEEOfficeBeans.add(budgetAllotmentEEOfficeBean);
			}
			budgetAllotmentBean.setBudgetAllotmentEEOfficeList(budgetAllotmentEEOfficeBeans);
		}
		return budgetAllotmentBean;
	}  */
	
	@Override
	public BudgetAllotmentBean fetchBudgetAllotment(Long budgetAllotmentId) {
		BudgetAllotment budgetAllotment = budgetAllotmentRepository.findOne(budgetAllotmentId);
		BudgetAllotmentBean budgetAllotmentBean=new BudgetAllotmentBean();
		convertBudgetAllotmentEntityToBean(budgetAllotment, budgetAllotmentBean);
		List<BudgetAllotmentEEOffice> findByBudgetAllotment = budgetAllotmentEEOfficeRepository.findByBudgetAllotment(budgetAllotment);
		List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeBeans=null;
		List<OfficeBean> fetchOfficesByOfficeType = commonService.fetchOfficesByOfficeType(RESConstants.EE_OFFICE_TYPE_ID);
		BudgetAllotmentEEOfficeBean budgetAllotmentEEOfficeBean=new BudgetAllotmentEEOfficeBean();
		Map<Long,String> myStr=new HashMap();
		budgetAllotmentEEOfficeBeans = functionForGetOfficeWiseAmountDate(findByBudgetAllotment, budgetAllotmentEEOfficeBeans,
				fetchOfficesByOfficeType, budgetAllotmentEEOfficeBean, myStr);
	     //for (String name : budgetAllotmentEEOfficeBeans.get.keySet())  
	       //     System.out.println("key: " + name);  
		if(null!= fetchOfficesByOfficeType) {
			/*budgetAllotmentEEOfficeBeans=new LinkedList<BudgetAllotmentEEOfficeBean>();*/
			for(OfficeBean officeBean : fetchOfficesByOfficeType) {
				BudgetAllotmentEEOfficeBean budgetAllotmentEEOfficeBeanFinal=new BudgetAllotmentEEOfficeBean();
				if(officeBean.getId()==null)
					continue;
				budgetAllotmentEEOfficeBeanFinal.setOfficeBeanId(officeBean.getId());
				budgetAllotmentEEOfficeBeanFinal.setOfficeName(officeBean.getOfficeName());
				
				if(null!= findByBudgetAllotment) {
					innerloop:
					for(BudgetAllotmentEEOffice budgetAllotmentEEOffice : findByBudgetAllotment) {
						if(budgetAllotmentEEOffice.getOffice().getId().equals(officeBean.getId())) {
							budgetAllotmentEEOfficeBeanFinal.setId(budgetAllotmentEEOffice.getId());
							
							budgetAllotmentEEOfficeBeanFinal.setBudgetAllotmentId(budgetAllotmentEEOffice.getBudgetAllotment().getId());
							
							break innerloop;
						}
					}
				}
					budgetAllotmentEEOfficeBeans.add(budgetAllotmentEEOfficeBeanFinal);
				
			}
			budgetAllotmentBean.setBudgetAllotmentEEOfficeList(budgetAllotmentEEOfficeBeans);
		}
		
		ListIterator<BudgetAllotmentEEOfficeBean> iter = budgetAllotmentEEOfficeBeans.listIterator();
		while(iter.hasNext()){
			BudgetAllotmentEEOfficeBean officeBean = (BudgetAllotmentEEOfficeBean)iter.next();
			if(officeBean.getOfficeBeanId()==null)
				iter.remove();
			//for(BudgetAllotmentEEOfficeBean officeBean : budgetAllotmentEEOfficeBeans) {
			//officeBean.getOfficeBeanId()==null
			if(officeBean.getIdlist()!=null) {
			Map<Long, String> myMAP=officeBean.getIdlist();
			for(Map.Entry<Long, String> entry : myMAP.entrySet()) {
				List<BudgetAllotmentEEOfficeBean> newlist = budgetAllotmentBean.getBudgetAllotmentEEOfficeList();
				for (BudgetAllotmentEEOfficeBean object : newlist) {
					if(entry.getKey().equals(object.getOfficeBeanId())) {
						if(null!=entry.getValue()&& !entry.getValue().equals("")) {
							object.setFomatedStringForOffices(entry.getValue());
						}
						
					}
				}
			}
			}
			
		}
		
		
		
		
			budgetAllotmentBean.setBudgetAllotmentEEOfficeList(budgetAllotmentEEOfficeBeans);
		
		return budgetAllotmentBean;
	}

	private List<BudgetAllotmentEEOfficeBean> functionForGetOfficeWiseAmountDate(List<BudgetAllotmentEEOffice> findByBudgetAllotment,
			List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeBeans, List<OfficeBean> fetchOfficesByOfficeType,
			BudgetAllotmentEEOfficeBean budgetAllotmentEEOfficeBean, Map<Long, String> myStr) {
		if(null!= fetchOfficesByOfficeType) {
			budgetAllotmentEEOfficeBeans=new LinkedList<BudgetAllotmentEEOfficeBean>();
			for(OfficeBean officeBean : fetchOfficesByOfficeType) {
				if(null!= findByBudgetAllotment) {
					String a="";
					for (int j = 0; j < findByBudgetAllotment.size(); j++) {
						if(officeBean.getId().equals(findByBudgetAllotment.get(j).getOffice().getId())) {
							 a+=""+findByBudgetAllotment.get(j).getAmount()+"-"+RESUtil.convertDateToString(findByBudgetAllotment.get(j).getCreatedDate())+"  ,";
						}
					}
					if (a.endsWith(",")) {
						  a = a.substring(0, a.length() - 1);
						
					}
					myStr.put(officeBean.getId(),a);
					
					
					}
				}
			budgetAllotmentEEOfficeBean.setIdlist(myStr);
			//budgetAllotmentEEOfficeBean.setAmountAndDate(myStr);
			budgetAllotmentEEOfficeBeans.add(budgetAllotmentEEOfficeBean);
				
			}
		return budgetAllotmentEEOfficeBeans;
	}
	
}