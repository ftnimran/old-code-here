package com.res.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.res.bean.BillBean;
import com.res.bean.InspectionAnswerNewBean;
import com.res.bean.InspectionChecklistBean;
import com.res.bean.InspectionDetailsBean;
import com.res.bean.InspectionQuestionsBean;
import com.res.bean.InspectionRequestBean;
import com.res.bean.InspectionTypeBean;
import com.res.bean.UserBean;
import com.res.constants.RESConstants;
import com.res.entity.Bill;
import com.res.entity.DataJson;
import com.res.entity.InspectionAnswersNew;
import com.res.entity.InspectionDetails;
import com.res.entity.InspectionSqmAnswer;
import com.res.entity.MasterBillStatus;
import com.res.entity.PhysicalStageType;
import com.res.entity.RequestStatus;
import com.res.entity.SqmAllocation;
import com.res.entity.Users;
import com.res.entity.Work;
import com.res.exception.RESBusinessException;
import com.res.repository.BillItemsRepository;
import com.res.repository.BillRepository;
import com.res.repository.InspectionDetailsRepository;
import com.res.repository.InspectionQuestionsRepository;
import com.res.repository.UserRepository;
import com.res.repository.WorkRepository;
import com.res.service.InspectionService;
import com.res.service.UserService;
import com.res.util.RESUtil;

@Service
public class InspectionServiceImpl implements InspectionService {

	@Autowired
	private InspectionQuestionsRepository repository;

	@Autowired
	private WorkRepository workRepository;

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private BillItemsRepository billItemsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public InspectionChecklistBean getInspectionChecklist(Integer workTypeId) {

		List<Object[]> rows = repository.fetchInspectionChecklist(workTypeId);

		InspectionChecklistBean response = new InspectionChecklistBean();
		Map<Integer, InspectionTypeBean> inspectionMap = new LinkedHashMap<>();

		for (Object[] r : rows) {

			response.setWorkTypeId((Integer) r[0]);
			response.setWorkType((String) r[1]);

			Integer inspectionTypeId = (Integer) r[2];

			InspectionTypeBean inspectionType = inspectionMap.computeIfAbsent(inspectionTypeId, id -> {
				InspectionTypeBean dto = new InspectionTypeBean();
				dto.setInspectionTypeId(id);
				dto.setInspectionType((String) r[3]);
				return dto;
			});

			InspectionQuestionsBean question = new InspectionQuestionsBean();
			question.setId((Integer) r[4]);
			question.setInspectionName((String) r[5]);
			question.setDataType((String) r[6]);
			if ((String) r[7] != null) {
				question.setDataValues(Arrays.asList(((String) r[7]).split(",")));
			} else {
				question.setDataValues(Arrays.asList(""));
			}

			inspectionType.getQuestions().add(question);
		}

		response.setInspectionTypes(new ArrayList<>(inspectionMap.values()));

		return response;
	}

	@Autowired
	private InspectionDetailsRepository inspectionDetailsRepo;

	@Override
	public Long saveOrUpdateInspection(InspectionRequestBean datajson) {

		Map<String, String> responseMap = new HashMap<String, String>();
		try {
			Long billId = 0L;
			Long workId = 0L;
			Long sqmAllocationId = datajson.getInspectionDetail().getSqmAllocationId();
			Long randomAllocationId = datajson.getInspectionDetail().getRandomAllocationId();
			

			Users u = userRepository.findOne(new Long(datajson.getInspectionDetail().getInspectedBy()));
			UserBean user = userService.fetchUserDetailsByUserName(u.getUsername());
			boolean isRandomInspection = false;
			boolean isGeneralInspection = false;
			Bill bill = billRepository.findOne(datajson.getInspectionDetail().getBillId());
			if (null != bill && !datajson.getInspectionDetail().getBillId().equals(datajson.getInspectionDetail().getWorkId())) {
				
				if( (RESConstants.ROLE_SDO.equals(user.getLoggedInUserRole())
						||RESConstants.ROLE_SUB_ENGG.equals(user.getLoggedInUserRole())
						||RESConstants.ROLE_EE.equals(user.getLoggedInUserRole()) && ((user.getIsOIC() == 0 
						&& bill.getBillType().equals(RESConstants.BILL_TYPE_RUNNING))
								||(user.getIsOIC() == 1 
						&& bill.getBillType().equals(RESConstants.BILL_TYPE_FINAL)))))
				billId = Long.valueOf(datajson.getInspectionDetail().getBillId());
			}else if(randomAllocationId!=null){
				isRandomInspection = true;
				workId = Long.valueOf(datajson.getInspectionDetail().getWorkId());
			}else if(sqmAllocationId!=null){
				
				workId = Long.valueOf(datajson.getInspectionDetail().getWorkId());
			}else{
				isGeneralInspection=true;
				workId = Long.valueOf(datajson.getInspectionDetail().getWorkId());
			}
			
			
			InspectionDetailsBean headerDTO = datajson.getInspectionDetail();

	    	InspectionDetails inspection = new InspectionDetails();
	    	
	    	

	        /* ---------- CREATE or UPDATE ---------- */
	        if (headerDTO.getInspectionId() != null && headerDTO.getInspectionId() > 0) {
	        	if(headerDTO.getInspectionId()!=null)
	            inspection = inspectionDetailsRepo.findOne(headerDTO.getInspectionId());
	          //  inspection.getAnswers().clear(); // orphanRemoval = true
	        } else {
	            inspection = new InspectionDetails();
	          //  inspection.setInspectionId(generateInspectionId());
	        }

	        /* ---------- MAP HEADER ---------- */
	        if(headerDTO.getBillId().equals(headerDTO.getWorkId())) {
	        	inspection.setWorkId(headerDTO.getWorkId());
	        }else {
	        	inspection.setWorkId(headerDTO.getWorkId());
	        	inspection.setBillId(headerDTO.getBillId());
	        }
	        inspection.setWorkTypeId(headerDTO.getWorkTypeId());
	        inspection.setWorkType(headerDTO.getWorkType());
	        inspection.setInspectedBy(u.getUsername());
	        if(sqmAllocationId!=null) {
	        	 inspection.setSqmAllocationId(sqmAllocationId);
	    	}
	        if(randomAllocationId!=null) {
	        	 inspection.setRandomAllocationId(randomAllocationId);
	    	}
	        if(isGeneralInspection) {
	        	inspection.setGeneralInspectionDone((short) 1);
	        }
	        try {
				inspection.setInspectionDate(RESUtil.convertStringToDate_yyyy_mm_dd(headerDTO.getInspectionDate()));
			} catch (RESBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        /* ---------- MAP CHILD ANSWERS ---------- */
	        for (InspectionAnswerNewBean dto : datajson.getInspectionAnswersList()) {

	        	
	            InspectionAnswersNew answer = new InspectionAnswersNew();
	           // answer.setAnswerId(generateAnswerId());
	            answer.setInspectionTypeId(dto.getInspectionTypeId());
	            answer.setQuestionId(dto.getQuestionId());
	            answer.setAnswer(dto.getAnswer());

	            inspection.addAnswer(answer);
	        }

	       inspectionDetailsRepo.save(inspection);
	       

			if (bill!=null && !isRandomInspection && !isGeneralInspection) {
				updateBillStatusForRunningAndFinalBill(billId, workId,  RESConstants.STATUS_PHYSICAL_INSPECTION_COMPLETED_ID,
						user.getLoggedInUserRole());
				// updateWorkPhysicalStage(billId, group8.getPhysicalStage(),
				
				// group8.getCompletedLength());
				return inspection.getId();
			} else if (isRandomInspection || isGeneralInspection) {
				updateBillStatusForRunningAndFinalBill(billId, workId,  RESConstants.STATUS_PHYSICAL_INSPECTION_COMPLETED_ID,
						user.getLoggedInUserRole());
				// updateWorkPhysicalStage(billId, group8.getPhysicalStage(),
				
				// group8.getCompletedLength());
				return inspection.getId();
			} else if (sqmAllocationId !=null|| randomAllocationId!=null) {
				updateBillStatusForRunningAndFinalBill(billId, workId,  RESConstants.STATUS_PHYSICAL_INSPECTION_COMPLETED_ID,
						user.getLoggedInUserRole());
				// updateWorkPhysicalStage(billId, group8.getPhysicalStage(),
				
				// group8.getCompletedLength());
				return inspection.getId();
			} else{
				// updateWorkPhysicalStageViaWorkId(workId, group8.getPhysicalStage(),
				// group8.getCompletedLength());
				updateBillStatusForRunningAndFinalBill(billId, workId,  RESConstants.REQUEST_STATUS_Final_Inspecion_Completed_ID,
						user.getLoggedInUserRole());
				return inspection.getId();
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public void updateBillStatus(Long billId, Long status) {

		Bill bill = billRepository.findOne(billId);
		bill.setStatus(new MasterBillStatus(status));
		billRepository.saveAndFlush(bill);
		if (RESConstants.STATUS_FWD_FOR_PAYMENT_ID.equals(status)
				&& bill.getBillType().equals(RESConstants.BILL_TYPE_FINAL)) {
			Work workEntity = workRepository.findOne(bill.getWork().getId());
			if (workEntity.getWorkRequestStatusId().getId()
					.equals(RESConstants.REQUEST_STATUS_Initiated_CC_Fwd_for_Final_Inspection_ID)) {
				workEntity.setWorkRequestStatusId(
						new RequestStatus(RESConstants.REQUEST_STATUS_Final_Inspecion_Completed_ID));
				workRepository.saveAndFlush(workEntity);
			}
		}
	}

	
	@Transactional
	public void updateBillStatusForRunningAndFinalBill(Long billId, Long workId, Long status, String role) {
		try {
	    Bill bill = null;
	    Work work = null;

	    if (billId != null && billId != 0) {
	        bill = billRepository.findOne(billId);;
	    } else if (workId != null) {
	        work = workRepository.findOne(workId);
	    }

	    // ================= BILL FLOW =================
	    if (bill != null) {

	        if (RESConstants.BILL_TYPE_RUNNING.equals(bill.getBillType()) ||
	            RESConstants.BILL_TYPE_FINAL.equals(bill.getBillType())) {

	            if (RESConstants.ROLE_SUB_ENGG.equals(role)) {
	                bill.setInspectedBySubEnggDatetime(new Date());
	            } else if (RESConstants.ROLE_SDO.equals(role)) {
	                bill.setInspectedBySdoDatetime(new Date());
	            } else if (RESConstants.ROLE_EE.equals(role)) {
	                bill.setInspectedByEeDatetime(new Date());
	            }

	            if (status != null) {
	                bill.setStatus(new MasterBillStatus(status));
	            }
	        }

	        billRepository.saveAndFlush(bill);
	    }

	    // ================= WORK FLOW =================
	    else if (work != null && work.getWorkRequestStatusId().getId()
				.equals(RESConstants.REQUEST_STATUS_Initiated_CC_Fwd_for_Final_Inspection_ID)) {

	        if (RESConstants.ROLE_EE.equals(role)) {

	        	List<Bill> billList =billRepository.findByWorkAndBillTypeAndStatusStatusIdIn(new
						  Work(work.getId()), "Final",
						  Arrays.asList(RESConstants.STATUS_FWD_FOR_PAYMENT_ID,
						  RESConstants.STATUS_CONTENGENCY_COMPLETED_ID,
						  RESConstants.STATUS_PAYMENT_COMPLETED_ID,
						  RESConstants.STATUS_PHYSICAL_INSPECTION_COMPLETED_ID, 
						  RESConstants.REQUEST_STATUS_Initiated_CC_Fwd_for_Final_Inspection_ID));

	            if (billList != null && billList.size() == 1) {

	                for (Bill billEntity : billList) {
	                    billEntity.setInspectedByEeDatetime(new Date());

	                    if (status != null) {
	                        billEntity.setStatus(new MasterBillStatus(status));
	                    }

	                    billRepository.save(billEntity);
	                }

	                work.setWorkRequestStatusId(
	                        new RequestStatus(
	                                RESConstants.REQUEST_STATUS_Final_Inspecion_Completed_ID
	                        )
	                );

	            } else {
	                work.setWorkRequestStatusId(
	                        new RequestStatus(
	                                RESConstants.REQUEST_STATUS_Initiated_CC_Fwd_for_Final_Inspection_ID
	                        )
	                );
	            }

	            workRepository.save(work);
	        }
	      }
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void rejectBill(BillBean billBean, String role) {
		Bill bill = billRepository.findOne(billBean.getId());
		if (role.equals("ROLE_SUB_ENGG")) {
			bill.setSubEnggRemark(billBean.getRemark());
		} else {
			bill.setRemark(billBean.getRemark());
		}
		bill.setStatus(new MasterBillStatus(RESConstants.STATUS_FINAL_BILL_REJECTED_ID));
		billRepository.saveAndFlush(bill);
	}

	private void updateWorkPhysicalStage(Long billId, String physicalStage) {
		if (null != physicalStage && !("").equals(physicalStage)) {

			Bill billEntity = billRepository.findOne(billId);
			Work workEntity = workRepository.findOne(billEntity.getWork().getId());
			workEntity.setPhysicalStageType(new PhysicalStageType(Long.parseLong(physicalStage)));
			if (billEntity.getBillType().equals(RESConstants.BILL_TYPE_FINAL)
					&& billEntity.getStatus().getStatusId().equals(RESConstants.STATUS_PHYSICAL_INSPECTION_COMPLETED_ID)
					&& workEntity.getWorkRequestStatusId().getId()
							.equals(RESConstants.REQUEST_STATUS_Initiated_CC_Fwd_for_Final_Inspection_ID)) {
				workEntity.setWorkRequestStatusId(
						new RequestStatus(RESConstants.REQUEST_STATUS_Final_Inspecion_Completed_ID));
			}
			workRepository.saveAndFlush(workEntity);
		}
	}

	private void updateWorkPhysicalStageViaWorkId(Long workId, String physicalStage) {
		if (null != physicalStage && !("").equals(physicalStage)) {

			Work workEntity = workRepository.findOne(workId);
			workEntity.setPhysicalStageType(new PhysicalStageType(Long.parseLong(physicalStage)));
			workRepository.saveAndFlush(workEntity);
		}
	}

	private void updateWorkPhysicalStage(Long billId, String physicalStage, String completedLength) {
		if (null != physicalStage && !("").equals(physicalStage)) {

			Bill billEntity = billRepository.findOne(billId);
			Work workEntity = workRepository.findOne(billEntity.getWork().getId());

			workEntity.setPhysicalStageType(new PhysicalStageType(Long.parseLong(physicalStage)));

			if (completedLength != null) {
				workEntity.setCompleteddistance(new BigDecimal(completedLength));
			}

			workRepository.saveAndFlush(workEntity);
		}
	}

	private void updateWorkPhysicalStageViaWorkId(Long workId, String physicalStage, String completedLength) {
		if (null != physicalStage && !("").equals(physicalStage)) {

			Work workEntity = workRepository.findOne(workId);

			workEntity.setPhysicalStageType(new PhysicalStageType(Long.parseLong(physicalStage)));

			if (completedLength != null) {
				workEntity.setCompleteddistance(new BigDecimal(completedLength));
			}

			workRepository.saveAndFlush(workEntity);
		}
	}

}