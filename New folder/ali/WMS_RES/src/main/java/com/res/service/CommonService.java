package com.res.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.res.bean.AccountHeadBean;
import com.res.bean.AdministrationSanctionBean;
import com.res.bean.AdministrationSanctionTypeBean;
import com.res.bean.AgencyTypeBean;
import com.res.bean.BankBean;
import com.res.bean.BillBean;
import com.res.bean.BillDataInspectionBean;
import com.res.bean.BillLoggingBean;
import com.res.bean.BlockBean;
import com.res.bean.CCDetailsBean;
import com.res.bean.CCDispatchDetailsBean;
import com.res.bean.ContengencyBean;
import com.res.bean.ContractorBean;
import com.res.bean.DeductionBean;
import com.res.bean.DepositeCategoryBean;
import com.res.bean.DepositeTypeBean;
import com.res.bean.DesignationBean;
import com.res.bean.DistrictBean;
import com.res.bean.FinancialYearBean;
import com.res.bean.GramPanchayatBean;
import com.res.bean.InspectionAnswerBean;
import com.res.bean.InspectionAnswerImageBean;
import com.res.bean.InspectionAnswerNewBean;
import com.res.bean.InspectionCompletedWorkBean;
import com.res.bean.InspectionCompletedWorkBeanNew;
import com.res.bean.InspectionDetailsBean;
import com.res.bean.InspectionSqmAnswerBean;
import com.res.bean.IssuingAuthorityBean;
import com.res.bean.LineDepartmentBean;
import com.res.bean.OfficeBean;
import com.res.bean.OfficeTypeBean;
import com.res.bean.PaymentBean;
import com.res.bean.PhysicalStageTypeBean;
import com.res.bean.SchemeSanctionedUnderProgrammeBean;
import com.res.bean.SqmAllocationBean;
import com.res.bean.StandardTemplateTypeBean;
import com.res.bean.StateBean;
import com.res.bean.TechnicalSanctionBean;
import com.res.bean.TechnicalSanctionTypeBean;
import com.res.bean.UserBean;
import com.res.bean.VillageBean;
import com.res.bean.WorkBean;
import com.res.bean.WorkBean2;
import com.res.bean.WorkDataInspectionBean;
import com.res.bean.WorkEstimationBean;
import com.res.bean.WorkFinancialMileStoneBean;
import com.res.bean.WorkLoggingBean;
import com.res.bean.WorkNatureBean;
import com.res.bean.WorkPhysicalMileStoneBean;
import com.res.bean.WorkStatusBean;
import com.res.bean.WorkSubTypeBean;
import com.res.bean.WorkTemplateBean;
import com.res.bean.WorkTenderBean;
import com.res.bean.WorkTypeBean;
import com.res.entity.Block;
import com.res.entity.DataJson;
import com.res.entity.District;
import com.res.entity.FileJson;
import com.res.entity.FileSqmJson;
import com.res.entity.ImageJson;
import com.res.entity.InspectionAnswer;
import com.res.entity.InspectionAnswerCC;
import com.res.entity.InspectionAnswerImage;
import com.res.entity.InspectionAnswerImageNew;
import com.res.entity.InspectionAnswersNew;
import com.res.entity.InspectionDetails;
import com.res.entity.InspectionAnswerImageCC;
import com.res.entity.InspectionSqmAnswerFile;
import com.res.entity.InspectionSqmAnswerImage;
import com.res.entity.RandomDataJson;
import com.res.entity.Users;
import com.res.entity.Work;
import com.res.exception.RESBusinessException;
import com.res.json.AccountHeadJson;
import com.res.json.BankJson;
import com.res.json.BillJson;
import com.res.json.ContractorJson;
import com.res.json.ExecutiveWorkJson;
import com.res.json.InspectionSqmAnswerJson;
import com.res.json.LineDepartmentJson;
import com.res.json.SqmAllocationJson;
import com.res.json.TechnicalSanctionJson;
import com.res.json.WorkDistrictJson;
import com.res.json.WorkEstimationJson;
import com.res.json.WorkJson;
import com.res.json.WorkLoggingJson;
import com.res.json.WorkTenderJson;

public interface CommonService {

	List<DistrictBean> fetchDistrictsByState(Long stateId);
	List<DepositeCategoryBean> fetchDepositeCategory();
	List<DepositeTypeBean> fetchDepositeType();
	List<BankBean> fetchBankName();
//Rakesh
	List<DistrictBean> fetchDistricts();
	Long fetchStateIdOfMP();

	List<StateBean> fetchStates();

	List<BankBean> fetchBanks();

	List<DesignationBean> fetchDesignations();
	String editWorkRevise(WorkBean workBean);

	List<OfficeTypeBean> fetchOfficeTypes();

	List<OfficeBean> fetchOfficesByOfficeType(Long officeTypeId);
	//Rakesh
	List<OfficeBean> fetchOfficesByOfficeTypeAndSqmUser(Long officeTypeId,Long sqmUserId);

	List<BlockBean> fetchBlocksByDistrict(Long districtId);

	List<WorkTypeBean> fetchWorkType();
	

	List<WorkSubTypeBean> fetchWorkSubTypeByWorkTypeId(Long workTypeId);

	List<LineDepartmentBean> fetchLineDepartment();

	List<UserBean> fetchAssistantEngineerByOfficeId(Long officeId);
	
	List<UserBean> fetchSubdivisionalOfficersByOfficeId(Long officeId);
	
	//fetchUsersForOfficeIdAndDesg
	List<UserBean> fetchUsersForOfficeIdAndDesg(Long officeId);

	List<DistrictBean> fetchMPDistricts();
	

	List<BlockBean> fetchBlocksByDistrictNew(Long districtId);

	List<AgencyTypeBean> fetchAgencyType();

	List<PhysicalStageTypeBean> fetchPhysicalStageType();

	List<TechnicalSanctionTypeBean> fetchTechnicalSanctionType();

	List<AdministrationSanctionTypeBean> fetchAdministrationSanctionType();

	List<IssuingAuthorityBean> fetchIssuingAuthority();

	List<GramPanchayatBean> fetchGramPanchayatByBlockCode(Long blockId);

	List<VillageBean> fetchVillageByGramPanchayatCode(Long gramPanchayatId);

	List<WorkStatusBean> fetchWorkStatus();

	List<PhysicalStageTypeBean> fetchPhysicalStageByWorkTypeId(Long workTypeId);

	List<UserBean> fetchSubEngineerByOfficeId(Long officeId);
	
	public List<UserBean> fetchExecutiveEngineerByOfficeId(Long officeId);
	
	public List<UserBean> fetchSubDivisionalOfficerByOfficeId(Long officeId);

	List<ContractorBean> fetchContractors();

	List<ContractorBean> fetchContractorsByName(String name);

	WorkBean fetchWorkDetails(Long id);
	
	WorkLoggingBean fetchWorkDetailsHistoryLegacy(Long workLoggingId);
	//Rakesh 
	WorkBean fetchWorkDetailsByTender(Long id,Long tenderId,Long officeId);

	TechnicalSanctionBean fetchTechnicalDetailsByWorkId(Long id);

	public List<WorkBean> fetchWorkDetailsList();

	String fetchDownloadFileName(Long documentId);

	BillBean fetchLastBillDetails(Long id);
	
	List<BillBean> fetchBillListForWork(Long workId);

	/** CR-RESOWMS/CR/4-2
	 * EE can make 10 % extra payment of the total tender cost. 
	 * It should be less or equal to AS.
	 * @param billBean
	 * @param loggedInUserRole
	 * @return responseMessage
	 * @throws Exception
	 */
	String addBill(BillBean bean, String loggedInUserRole);

	String addWorkEstimation(WorkEstimationBean bean);

	String editSaveBill(BillBean bean, String role);

	ExecutiveWorkJson getAllExecutiveWorks(Pageable pageable, String role,
			UserBean userBean, Short isLegacy);

	/*ExecutiveWorkJson getAllSuperintendingWorks(Pageable pageable, String role,
			UserBean userBean,Short isLegacy);*/
	
	ExecutiveWorkJson getAllSuperintendingWorks(Pageable pageable, String role,
			UserBean userBean,Short isLegacy, String executionAgencyId,String exeOfficeId, String lineDepartmentId
			,String accountHeadId,String workStatusId,String workTypeId,String workSubTypeId);

	ExecutiveWorkJson getAllChiefWorks(Pageable pageable, String role,
			UserBean userBean,Short isLegacy, String executionAgencyId,String exeOfficeId,String supdtOfficeId, String lineDepartmentId
			,String accountHeadId,String workStatusId,String workTypeId,String workSubTypeId);

	WorkJson getAllWorks(Pageable pageable, String role, Long officeId,
			Long userId, String workStatus, Long districtId,  Long workNatureId, Long workTypeId,
			Long workSubTypeId, Long lineDepartmentId, Long accountHeadId,
			Long executionAgencyId, Long workStatusId, Long blockId,
			Long gramPanchayatId, Long villageId, Long contractorId,  String financialYear ,Long eeOfficeId,Long supOfficeId,Long ceOfficeId,Short isLegacy);

	WorkJson getAllWorksForAll(Pageable pageable, String role, Long officeId,
			Long userId, String workStatus, Long districtId, Long workNatureId,Long workTypeId,
			Long workSubTypeId, Long lineDepartmentId, Long accountHeadId,
			Long executionAgencyId, Long workStatusId, Long blockId,
			Long gramPanchayatId, Long villageId, Long contractorId,String financialYear ,Long eeOfficeId,Long supOfficeId,Long ceOfficeId,Short isLegacy);
	
	WorkJson getAllWorksWOOffice(Pageable pageable, String role,
			Long userId, String workStatus, Long districtId, Long workTypeId,
			Long workSubTypeId, Long lineDepartmentId, Long accountHeadId,
			Long executionAgencyId, Long workStatusId, Long blockId,
			Long gramPanchayatId, Long villageId, Long contractorId, String financialYear,Long eeOfficeId,Long supOfficeId,Long ceOfficeId,Short isLegacy);
	
	
	
	
	
	WorkJson getAllWorksTwo(Pageable pageable, String role, Long officeId,
			Long userId, String workStatus, Long districtId,  Long workNatureId, Long workTypeId,
			Long workSubTypeId, Long lineDepartmentId, Long accountHeadId,
			Long executionAgencyId, Long workStatusId, Long blockId,
			Long gramPanchayatId, Long villageId, Long contractorId,  String financialYear ,Long eeOfficeId,Long supOfficeId,Long ceOfficeId,Short isLegacy);
	
	WorkJson getAllWorksTwoStatus(Pageable pageable, String role, Long statusId,Long exeOfficeId, Long officeId, Long userId,
			String workStatus, Long districtId,  Long workNatureId, Long workTypeId, Long workSubTypeId, Long lineDepartmentId,
			Long accountHeadId, Long executionAgencyId, Long workStatusId, Long blockId, Long gramPanchayatId,
			Long villageId, Long contractorId, String financialYear , Long eeOfficeId, Long supOfficeId,
			Long ceOfficeId, Short isLegacy);
	
	
	/*List<WorkBean> getAllWorksByName(String searchBoxVal,
			String loggedInUserRole, String username);
	*/
	
	WorkJson getAllWorksByNameP(Pageable pageable,String searchBoxVal,
			String loggedInUserRole, String username);

	BillJson getAllBills(Pageable pageable, String searchBoxVal,
			String billDateFrom, String billDateTo, String status,
			String loggedInUserRole, String username);

	BillJson getAllBills1(Pageable pageable, String searchBoxVal,
			String billDateFrom, String billDateTo, String status,
			String loggedInUserRole, String username);

	WorkEstimationJson getAllPendingWorkEstimations(Pageable pageable,
			String searchBoxVal, Long workType, Long workSubType,
			String loggedInUserRole, String username);	
	WorkEstimationJson fetchHistoryWorkEstimations(Pageable pageable,Long workId,Long estimationId,
			String searchBoxVal, 
			String loggedInUserRole, String username);
	
	WorkLoggingJson fetchHistoryWorkLegacy(Pageable pageable,Long workId,
			
			String loggedInUserRole, String username);
	
	
	List<WorkEstimationBean> fetchWorkEstimationsHistoryForWork(Long workId);

	String deleteBill(Long billId);

	BillBean fetchBillDetails(Long id);

	BillBean fetchBillDetailsforEdit(Long id);
	
	BillBean fetchBillDetailsforPrint(Long id);
	
	public BillLoggingBean fetchOriginalBillDetails(Long id);

	List<DesignationBean> fetchTSIssuingAuthorityFromDesignationTable();

	String unlockWork(Long workId);

	List<WorkTemplateBean> fetchWorkTemplateItems(Long workTypeId,
			Long workSubTypeId);

	List<WorkTemplateBean> fetchWorkTemplateItemsForEstimation(Long workTypeId,
			Long standardTemplateTypeId, Short templateType);

	/*WorkJson getAllRequisitionWorks(Pageable pageable, String role,
			Long officeId, Long districtId, Long workTypeId,
			Long workSubTypeId, Long lineDepartmentId, Long accountHeadId,
			Long executionAgencyId, Long blockId, Long gramPanchayatId,
			Long villageId, Long contractorId,Date fromDate,Date toDate);*/
	
	
	WorkJson getAllRequisitionWorks(Pageable pageable, String role,
			Long officeId, Long districtId,  Long workNatureId, Long workTypeId,
			Long workSubTypeId, Long lineDepartmentId, Long accountHeadId,
			Long executionAgencyId, Long blockId, Long gramPanchayatId,
			Long villageId, Long contractorId,String financialYear);

	WorkJson getAllTechnicalSanctionWorks(Pageable pageable, String role,
			Long officeId);

	public WorkBean fetchWorkDetailsByBillId(Long id);

	WorkEstimationBean fetchWorkEstimationDetailsById(Long workId);
	
	WorkEstimationBean fetchWorkEstimationDetailsByIdH(Long estimationId);
	public Map<String, String> saveInspectionAnwserForBhavan(DataJson data);

	public Map<String, String> saveInspectionAnwserForPulia(DataJson data);

	public Map<String, String> saveInspectionAnwserForSadak(DataJson data);

	public Map<String, String> saveInspectionAnwserForTalab(DataJson data);

	public Map<String, String> saveInspectionAnwserForBhavanCC(DataJson data);

	public Map<String, String> saveInspectionAnwserForPuliaCC(DataJson data);

	public Map<String, String> saveInspectionAnwserForSadakCC(DataJson data);

	public Map<String, String> saveInspectionAnwserForTalabCC(DataJson data);
	
	
	public void updateBillStatus(Long billId, Long status);

	List<AccountHeadBean> fetchAccountHead();

	WorkJson getAllAdministrationSanctionWorks(Pageable pageable, String role,
			Long officeId);

	public List<WorkBean> getWorkList(Short isLegacy, String role,
			Long officeId, Long userId);
	
	public List<Object[]> getWorkListForObjArr(Short isLegacy, String role,
			Long officeId, Long userId);

	String savePaymentData(PaymentBean paymentBean);

	List<BillDataInspectionBean> fetchInspectionPendingBillsForGP(
			Long agencyType, Long workTypeId, String username);

	List<BillDataInspectionBean> fetchInspectionCompletedBillsForGP(
			Long agencyType, String username);

	public List<BillDataInspectionBean> fetchInspectionCompletedBillsForRES(
			String username, Long billStatus);

	public List<BillDataInspectionBean> fetchInspectionPendingBillsForRES(
			String username, Long billStatus);
	
	public List<BillDataInspectionBean> fetchInspectionRunningBillsForRES(
			String username, Long billStatus);
	
	public List<BillDataInspectionBean> fetchInspectionFinalBillsForRES(
			String username, Long billStatus);
	
	public List<BillDataInspectionBean> fetchRandomInspectionPendingWorks(Long userId);

	//Rakesh
	public List<WorkDataInspectionBean> fetchInspectionPendingWorksForSQM(Long userId);
	//fetchInspectionCompletedWorksForSQM by nikhil
	
	public List<WorkDataInspectionBean> fetchInspectionCompletedWorksForSQM(Long userId);
	

	void editWorkRequestStatusById(WorkBean workBean);

	public PaymentBean fetchPaymentData(Long billId);

	public ContengencyBean fetchContengecyData(Long billId);

	public List<ContengencyBean> fetchPrevContengecyData(Long billId);

	public String addAdminSanction(WorkBean workBean);

	String saveContengencyData(ContengencyBean contengencyBean);

	AdministrationSanctionBean fetchAdministrativeDetailsByWorkId(Long id);

	public void saveImage(ImageJson imageJson) throws RESBusinessException;
	public void saveImageSqm(ImageJson imageJson) throws RESBusinessException;

	public void saveImageCC(ImageJson imageJson) throws RESBusinessException;
	
	public void saveFile(FileJson fileJson) throws RESBusinessException;
	
	public void saveFileSqm(FileSqmJson fileJson) throws RESBusinessException;

	public void saveFileCC(FileJson fileJson) throws RESBusinessException;
	
	String addTechnicalSanction(WorkBean workBean);

	public BillBean fetchPrevBillDetailsByBillId(Long billId);

	List<AgencyTypeBean> fetchGramPanchayat();

	public void updateWorkRequisitionScript();

	TechnicalSanctionJson getAllWorksByAdmin(Pageable pageable, String role, Long officeId, Long userId, String workStatus,
			Long districtId , Long workTypeId, Long workSubTypeId , Long lineDepartmentId
			, Long accountHeadId, Long executionAgencyId, Long workStatusId,Long blockId
			,Long gramPanchayatId,Long villageId);
//Rakesh
	TechnicalSanctionJson getAllWorksByAdminHistory(Pageable pageable, String role, Long officeId, Long userId, String workStatus,
			Long districtId , Long workTypeId, Long workSubTypeId , Long lineDepartmentId
			, Long accountHeadId, Long executionAgencyId, Long workStatusId,Long blockId
			,Long gramPanchayatId,Long villageId,Long workId,Long technicalSanctionId);
	
	WorkEstimationJson getAllWorksByTechnical(Pageable pageable, String role, Long officeId, Long userId, String workStatus,
			Long districtId , Long workTypeId, Long workSubTypeId , Long lineDepartmentId
			, Long accountHeadId, Long executionAgencyId, Long workStatusId,Long blockId
			,Long gramPanchayatId,Long villageId,String loggedInUserName);


     public WorkDistrictJson getAllStatusWiseWorks(String role, UserBean userBean);

    
	public void updateWorkRequisitionScriptForNonLegacy();
	public void updateFinancialYearForWorkForNonLegacyData() throws RESBusinessException;
	
	public void updateFinancialYearForWorkForLegacyData() throws RESBusinessException;
	
	public List<Work> getWorkListForExcel(Short isLegacy, String role,
			Long officeId, Long userId);
	
	public List<BillBean> fetchInspectionBillsByWork(Long workId);
	
	public BillJson fetchInspectionBills(Pageable pageable, String searchBoxVal,
			String billDateFrom, String billDateTo, String status,
			String loggedInUserRole, String username);
	
	public List<InspectionAnswer> fetchInspectionAnswerByBillId(Long billId);
	
	public List<InspectionAnswer> fetchInspectionAnswerEEByBillId(Long billId);
	
	
	public List<InspectionSqmAnswerBean> fetchInspectionAnswerSqmByWorkId(Long workId);

	public List<InspectionAnswerImage> fetchInspectionAnswerImageByBillId(Long billId);
	
	public List<InspectionAnswerImage> fetchInspectionAnswerImageEEByBillId(Long billId);

	
	public List<WorkBean> getWorkListTest(Short isLegacy, String role, Long officeId,
			Long userId, String workStatus, Long districtId, Long workTypeId,
			Long workSubTypeId, Long lineDepartmentId, Long accountHeadId,
			Long executionAgencyId, Long workStatusId, Long blockId,
			Long gramPanchayatId, Long villageId, Long contractorId);
	
	public List<FinancialYearBean> fetchFinancialYear();

	/** CR-RESOWMS/CR/1-2
	 * Office Mgmt.-Create New Office and shift the works.
	 * @param request
	 * @return List<OfficeBean>
	 */
	List<OfficeBean> fetchOffices();

	void changeTechnicalSanctionStatus(String estimationId, Users user);

	WorkBean fetchWorkDetailsForPrintTs(Long id, Long estimationId);
	
	public String addTender(WorkTenderBean tenderBean, String role);
	public String editTender(WorkTenderBean tenderBean);
	
	/*public AdministrationSanctionJson getAllWorkTender(Pageable pageable,
			String searchBoxVal, Long workType, Long workSubType,
			String loggedInUserRole, String username);*/
	
	
	
	
	//Rakesh
	public WorkJson getAllWorkTenderHistory(Pageable pageable,
			String searchBoxVal, Long workType, Long workSubType,
			String loggedInUserRole, String username,Long workId,Long administrationSanctionId);
	public WorkTenderJson getAllWorkTenderList(Pageable pageable,
			String searchBoxVal, Long workType, Long workSubType,
			String loggedInUserRole, String username);
	public WorkTenderBean fetchWorkTenderDetailsByWorkId(Long id);
	
	public WorkTenderBean fetchWorkTenderDetailsByTenderId(Long id);
	
	/*WorkEstimationJson getAllWorksByTechnicalTwo(Pageable pageable, String role, Long officeId, Long userId, String workStatus,
			Long districtId , Long workTypeId, Long workSubTypeId , Long lineDepartmentId
			, Long accountHeadId, Long executionAgencyId, Long workStatusId,Long blockId
			,Long gramPanchayatId,Long villageId,String loggedInUserName);
	*/
	
	public WorkEstimationJson getAllWorksByTechnicalTwo(Pageable pageable, String role, Long officeId,
			 String loggedInUserName) ;
	
	WorkBean fetchWorkDetailsInEstimation(Long id,Long estimationId);
	
	WorkBean fetchWorkDetailsInEstimationByParentId(Long id,Long estimationId,Long parentId);
	
	WorkBean fetchWorkDetailsInTechnical(Long id,Long technicalSanctionId);
	//Rakesh
	WorkBean fetchWorkDetailsInTechnical(Long id,Long technicalSanctionId,Long parentId);
	
	public List<StandardTemplateTypeBean> fetchStandardTemplateTypesByWorkTypeId(Long workTypeId);
	
	public WorkBean fetchCCInspectionsByWork(Long workId);
	
	public CCDetailsBean fetchInitiatedCCByWork(Long workId);
	
	public CCDispatchDetailsBean fetchPhysicalCCByWork(Long workId);
	
	public CCDispatchDetailsBean fetchFinancialCCByWork(Long workId);
	
	public WorkJson fetchCCInspectionList(Pageable pageable, String searchBoxVal, String loggedInUserRole, String username);
	
	public List<BillDataInspectionBean> fetchCCInspectionPendingWorks(String username, Long status, String role);
	//fetchCCInspectionCompletedWorks
	public List<BillDataInspectionBean> fetchCCInspectionCompletedWorks(String username, Long status);
	
	public List<InspectionAnswerCC> fetchInspectionAswerByWorkId(Long WorkId);

	public List<InspectionAnswerImageCC> fetchInspectionAswerImageCCByWorkId(Long workId);
	
	public WorkJson fetchGeneralInspectionList(Pageable pageable, String searchBoxVal, String loggedInUserRole, String username);
	
	
	public void updateWorkStatus(Long workId, Long status);
	
	public void updateCCDetails(Long workId);
	
	public void updateCCDetails(Long workId, boolean approve) ;
	
	public void updateCCDetails(CCDetailsBean CCDetailsBean, boolean approve) ;
	
	public WorkEstimationBean fetchWorkDetailsH(Long estimationId);
	
	
	public WorkJson fetchWorkListForCC(Pageable pageable, String role, Long officeId,
			Long userId) throws RESBusinessException;
	
	public DeductionBean fetchBillDeduction(Long workId);
	public AccountHeadJson fetchAccountHead(Pageable pageable);
	public LineDepartmentJson fetchLineDepartment(Pageable pageable);
	public String addAccountHead(AccountHeadBean accountHeadBean);
	public String addLineDepartment(LineDepartmentBean lineDepartmentBean);
	public AccountHeadBean FetchAccountHeadBean(Long id);
	public LineDepartmentBean fetchLineDepartmentById(Long id);
	
	public ContractorBean fetchContractorBean(Long id);
	public String addContractor(ContractorBean contractorBean);
	public String editContractor(ContractorBean contractorBean);
	public ContractorJson fetchContractorList(Pageable pageable);
	
	public void rejectBill(BillBean billBean, String role);

	ExecutiveWorkJson getAllExecutiveWorksStatus( Pageable pageable ,Long statusId, String role, UserBean userBean,
			Short isLegacy);
	public WorkBean fetchFullDetailsForWork(Long id);
	
	public Long getIdFromFinancialYearName(final String fyName);
	WorkJson getAllWorksWOOfficeStatus(Pageable pageable, String role, Long statusId, Long exeOfficeId, Long id,
			String workStatus, Long districtId, Long workTypeId, Long workSubTypeId, Long lineDepartmentId,
			Long accountHeadId, Long executionAgencyId, Long workStatusId, Long blockId, Long gramPanchayatId,
			Long villageId, Long contractorId, String financialYear, Long eeOfficeId, Long supOfficeId,
			Long ceOfficeId, Short isLegacy);
	
	public List<TechnicalSanctionBean> fetchTechnicalDetailsListByWorkId(Long id) ;
	public List<AdministrationSanctionBean> fetchAdministrativeDetailsListByWorkId(Long id);
	public BankJson getAllBanks(Pageable pageable, String searchParameter);
	public BankBean fetchBankDetails(Long bankId);
	public String editBank(BankBean bankBean);
	public String addBank(BankBean bean);
	public String updateOfficer(WorkBean workBean);

	TechnicalSanctionBean findByWorkId(Long workid);

	//Rakesh Working
	public WorkEstimationJson fetchHistoryWorkEstimationsByWorkIdAndEstimationId(Pageable pageable, String loggedInrole, Long loggedInOfficeId, String loggedInUserName,	Long workId,
			Long workEstimationId);
	
	List<DistrictBean> fetchAllWorkByDisticts(List<DistrictBean> districtBeans);
	List<WorkBean> fetchAllWorkByEEOfficeId(String eeOfficeId);
	 List<WorkBean> fetchAllWorkByUserIdInSqmEdit(Long userId);
	 List<WorkBean> fetchAllWorkByUserIdInOfficersEdit(Long userId);
	/*WorkEstimationJson fetchHistoryWorkEstimations(Pageable pageable, Long workId, String searchBoxVal, String role,
			String username);*/
	public WorkBean fetchWorkDetailsByWorkId(Long id);
	public Map<String, String> saveSqmInspectionAnwserForSadak(DataJson dataJson);
	//saveSqmInspectionAnwserForBhavan
	
	public Map<String, String> saveSqmInspectionAnwserForBhavan(DataJson dataJson);
	
	public Map<String, String> saveSqmInspectionAnwserForSarovar(DataJson dataJson);
	InspectionSqmAnswerJson getAllWorksBySqmInspection(Pageable pageable, String role, String exeOfficeId, String workStatusId, String workTypeId, String sqmId, String grading, Long officeId,
			 String loggedInUserName);
	
	InspectionSqmAnswerJson getAllWorksByOfficerInspection(Pageable pageable, String role, String exeOfficeId, String workStatusId, String workTypeId, String sqmId, String grading, Long officeId,
			 String loggedInUserName);
	public List<OfficeBean> fetchOfficesByParentOfficeId(Long parentOfficeId);
	
	public List<OfficeBean> fetchSubDivisionalOfficesByParentOfficeId(Long parentOfficeId);
	
	
	public List<OfficeBean> fetchOfficesBySeOfficeIdAndSqmUser(Long seOfficeId,Long sqmUserId);
	List<InspectionSqmAnswerImage> fetchInspectionAnswerImageByWorkId(Long workId);
	List<InspectionSqmAnswerFile> fetchInspectionAnswerFileByWorkId(Long workId);
	SqmAllocationJson getAllWorksBySqmInspectionByWorkId(Pageable pageable, String role, Long officeId, String username,
			Long workId);
	public List<SqmAllocationBean> getAllSqmAllocationsByWorkId(Long workId);
	String getFinancialYearById(Long financialYearId);
	public WorkJson getDataForPaymentWise(Pageable pageable, String searchBoxVal,Integer ceOfficeId,Integer seOfficeId, String billStatus, String eeOfficeId,
			String fromYear, String endYear,String lineDepartmentId,String accountHeadId,String workStatusId,Long executionAgencyId,String workTypeId,String workSubTypeId);
	
	public WorkJson getAllWorkTender(Pageable pageable,
			String searchBoxVal, Long workType, Long workSubType,
			String loggedInUserRole, String username);
	List<OfficeBean> fetchExeOffices();
	public WorkJson getDataExAgWise(Pageable pageable, String searchBoxVal,Integer ceOfficeId,Integer seOfficeId, Long eeOfficeId, String parameter1,
			String lineDepartmentId,String accountHeadId,String workStatusId,Long executionAgencyId,Long workTypeId,String workSubTypeId
			);
	List<OfficeBean> fetchExeOfficesForOffices(Integer office, String loggedInUserRole);
	List<OfficeBean> fetchSupdtOffices(Integer office, String loggedInUserRole);
	public WorkJson getDataForMultiselectStatusWise(Pageable pageable, String searchBoxVal,Integer ceOfficeId,Integer seOfficeId, Long workReqStatusId,
			Long executionAgencyId, String exeOfficeId,String lineDepartmentId,String accountHeadId,String workStatusId,String workTypeId,String workSubTypeId);
	public WorkJson getDataForPendingForInspection(Pageable pageable, String searchBoxVal, Integer ceOfficeId, Integer seOfficeId,
			String exeOfficeId, Long executionAgencyId,String fromYear, String endYear, String lineDepartmentId, String accountHeadId, String workTypeId,
			String workSubTypeId);
	
	public WorkJson getDataForFinalBillPending(Pageable pageable, String searchBoxVal, Integer ceOfficeId, Integer seOfficeId,
			String exeOfficeId, Long executionAgencyId,String fromYear, String endYear, String lineDepartmentId, String accountHeadId, String workTypeId,
			String workSubTypeId);
	
	//getDataForPhysicalCCDispatch
	
	public WorkJson getDataForPhysicalCCDispatch(Pageable pageable, String searchBoxVal, Integer ceOfficeId, Integer seOfficeId,
			Long exeOfficeId, Long executionAgencyId, String lineDepartmentId, String accountHeadId, String workTypeId,
			String workSubTypeId);
	public Map<String, String> saveInspectionAnwserForKhel(DataJson data);
	public Map<String, String> saveSqmInspectionAnwserForPulia(DataJson dataJson);
	public Map<String, String> saveSqmInspectionAnwserForKhel(DataJson dataJson);
	/*List<UserBean> fetchNameOfSqm();
	*/
	List<UserBean> fetchNameOfSqm();
	List<UserBean> fetchNameOfOfficers();
	String changeFlag(Long id, BigDecimal adminAmount);
	String changeFlagToZ(Long id);
	
	//allowSubEngToPrepareBill
	/*** CR-RESOWMS/CR/4-3
	 * EE can make 10 % extra payment of the total tender cost. 
	 * It should be less or equal to AS.
	 * @param id
	 * @return String
	 **/
	String allowSubEngToPrepareBill(Long id);
	public String updateAeSubNAme(BillBean billBean);
	public String updateAeEeSubNAme(BillBean billBean);
	public String updateSeSdoName(BillBean billBean);
	String updateSeSdoEeName(BillBean billBean); //updateBillStatusRemarks
	public String updateBillStatusRemarks(BillBean billBean, String userId);
	//deleteBillRemarks
	public String deleteBillRemarks(BillBean billBean, String userId);
	List<OfficeBean> fetchEEofficesBySupdtOfficeIds(String supdtOfficeId);
	List<OfficeBean> fetchEEofficesBySupdtOfficeIdsCeEnc(String supdtOfficeId);
	List<OfficeBean> fetchEEofficesBySupdtOfficeIdsCeEncChecked(String supdtOfficeId,Long userId);
	//fetchWorkSubTypeByWorkTypeIds
	
	List<WorkSubTypeBean> fetchWorkSubTypeByWorkTypeIds(String workTypeIds);
	
	//fetchWorkSubTypeByWorkTypeIds
	public WorkJson fetchWorkListTwoForChiefReport(Pageable pageable, String searchBoxVal, Long officeId, String workStatus,
			Short isLegacy, Long executionAgencyId, String exeOfficeId, String supdtOfficeId,String lineDepartmentId, String accountHeadId,
			String workStatusId, String workTypeId, String workSubTypeId);
	
	public WorkJson fetchWorkListTwoForSupdtReport(Pageable pageable, String searchBoxVal, Long officeId, String workStatus,
			Short isLegacy, Long executionAgencyId, String exeOfficeId,String lineDepartmentId, String accountHeadId,
			String workStatusId, String workTypeId, String workSubTypeId);
	List<WorkBean2> fetchAllWorks();
	InspectionCompletedWorkBean fetchInspectionCompletedBills(Long billId, Long inspectedBy);
	//fetchInspectionCompletedWorksCC by nikhil
	InspectionCompletedWorkBean fetchInspectionCompletedWorksCC(Long workId, String inspectedBy);
	InspectionCompletedWorkBean fetchInspectionCompletedSqm(Long sqmId);
	List<DesignationBean> fetchDesignationsType();
	List<UserBean> fetchUsersByDesig(Long desigId);
	String addOfficerInsp(UserBean userBean, String websiteURL);
	List<WorkBean> fetchAllWorkByOfficersEEOfficeId(String officeIdList);
	SqmAllocationJson getAllWorksByOfficerInspectionByWorkId(Pageable pageable, String role, Long id, String username,
			Long workId);
	
	public List<InspectionSqmAnswerBean> fetchInspectionAnswerOfficerByWorkId(Long workId);
	List<InspectionSqmAnswerImage> fetchInspectionAnswerOfficerImageByWorkId(Long workId);
	List<InspectionSqmAnswerFile> fetchInspectionAnswerOfficerFileByWorkId(Long workId);
//	Long 
	Long saveSingleVillagePipedWaterSupplyScheme(DataJson dataJson);


	String addBillContractor(BillBean billBean);
	String editSaveBillContractor(BillBean billBean, String loggedInUserRole);
	public void addContractorsAsUsers();

	/*List<Object[]> addContractorsAsUsers();*/


	  List<BillDataInspectionBean> fetchRandomInspection(Long userId);
	 
	  List<BillDataInspectionBean> fetchGeneralInspection(Long userId);
	  
	List<InspectionAnswerBean> fetchAlreadyDoneRandomInspectionList(Long userId,Long workId);
 
	 
	Map<String, String> saveRandomInspectionAnwser(RandomDataJson randomDataJson);
 
	InspectionCompletedWorkBean fetchInspectionCompletedBills(Long billId, Long inspectedBy, boolean isWorkId,
			Long inspectionAnswerId);
	
	public List<InspectionAnswer> fetchInspectionAnswerEEByWorkId(Long workId);
	
	public List<InspectionAnswerImage> fetchInspectionAnswerImageEEByWorkId(Long billId);
	List<WorkPhysicalMileStoneBean> fetchPhysicalMilestonesByWorkId(Long workId);
	List<WorkFinancialMileStoneBean> fetchFinancialMilestonesByWorkId(Long workId);
	public WorkJson getExpAmountHistory(Pageable pageable, String searchParameter, String role, Long officeId);
	public WorkJson fetchChangeHistory(Pageable pageable, String searchParameter, String role, Long id);
	List<WorkNatureBean> fetchWorkNature();
	DistrictBean getDisctrictDetailByLgdDistrictCode(String lgdDistrictCode);
	List<District> getAllDistricts(Long districtId);
	Block getBlocks(UserBean userBean);
	WorkBean2 fetchWorkDetailsByBillIdMobile(Long id);
	List<SchemeSanctionedUnderProgrammeBean> fetchSchemeSanctionedUnderProgrammes();
	//List<OfficeBean> fetchSubDivisionalOfficesByParentOfficeId(Long parentOfficeId, String loggedInUserRole);
	InspectionCompletedWorkBeanNew fetchInspectionCompletedBillsNew(Long inspectionId, Long inspectedBy);
	
	// New mobile app 
	//fetch inspection for new bills
	List<InspectionAnswerNewBean> fetchInspectionAnswerByBillIdNew(Long billId);
	
	//public List<BillBean> fetchInspectionBillsByWork(Long workId);
	
	//public BillJson fetchInspectionBills(Pageable pageable, String searchBoxVal,
	//		String billDateFrom, String billDateTo, String status,
	//		String loggedInUserRole, String username);
	
//	public List<InspectionAnswersNew> fetchInspectionAnswerByBillId_new(Long billId);
	
	//public List<InspectionSqmAnswerBean> fetchInspectionAnswerSqmByWorkId(Long workId);

	public List<InspectionAnswerImageBean> fetchInspectionAnswerImageByBillIdNew(Long billId);
	
	
	List<InspectionAnswerNewBean> fetchInspectionAnswerByInspectionIdNewSQM(InspectionDetails inspection);
	
	List<InspectionAnswerImageBean> fetchInspectionAnswerImageByInspectionId(Long inspectionId);
	
	SqmAllocationJson getAllWorksByGeneralInspectionByWorkId(Pageable pageable, String loggedInrole,
			Long loggedInOfficeId, String loggedInUserName, Long workId);
	
	
	

	
} 

