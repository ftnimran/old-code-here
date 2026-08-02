package com.res.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.res.repository.AdministrationSanctionRepository;
import com.res.repository.DesignationRepository;
import com.res.repository.DistrictRepository;
import com.res.repository.DocumentRepository;
import com.res.repository.OfficeTypeRepository;
import com.res.repository.RoleRepository;
import com.res.repository.TechnicalSanctionRepository;
import com.res.repository.UserRepository;
import com.res.repository.WorkRepository;
import com.res.service.CeService;

@Service
public class CeServiceImpl implements CeService {

	public static final Logger logger = LoggerFactory.getLogger(CeServiceImpl.class);
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private DesignationRepository designationRepository;
	
	@Autowired
    private WorkRepository workRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private OfficeTypeRepository officeTypeRepository;
	
	@Autowired
	private TechnicalSanctionRepository technicalSanctionRepository;
	
	@Autowired
	private AdministrationSanctionRepository administrationSanctionRepository;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Value("${document.root}")
	private String documentRootPath;
	
	@Value("${document.work}")
	private String workDocumentPath;
	
	@Value("${document.technical}")
	private String workTechSanctionDocumentPath;
	
	@Value("${document.administration}")
	private String workAdminSanctionDocumentPath;
	
	public String getDocumentRootPath() {
		return documentRootPath;
	}

	public void setDocumentRootPath(String documentRootPath) {
		this.documentRootPath = documentRootPath;
	}

	public String getWorkDocumentPath() {
		return workDocumentPath;
	}

	public void setWorkDocumentPath(String workDocumentPath) {
		this.workDocumentPath = workDocumentPath;
	}

	public String getWorkTechSanctionDocumentPath() {
		return workTechSanctionDocumentPath;
	}

	public void setWorkTechSanctionDocumentPath(String workTechSanctionDocumentPath) {
		this.workTechSanctionDocumentPath = workTechSanctionDocumentPath;
	}

	public String getWorkAdminSanctionDocumentPath() {
		return workAdminSanctionDocumentPath;
	}

	public void setWorkAdminSanctionDocumentPath(
			String workAdminSanctionDocumentPath) {
		this.workAdminSanctionDocumentPath = workAdminSanctionDocumentPath;
	}

	/*@Override
	@Transactional(rollbackFor = Exception.class)
	public String addWork(WorkBean bean) {
		try{
			if(bean!=null){
				if (bean.getAdministrationSanctionFile() != null) {
					DocumentUpload documentUpload = uploadWorkDocument(
							"AsDocs", bean.getAdministrationSanctionFile(),
							bean.getDocumentDesc(),
							RESConstants.ADMINISTRATION_SANCTION_DOC);
					documentRepository.save(documentUpload);
//					legalDao.saveOrUpdate(legalDocumentUpload);
//					legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//							legalDocumentUpload.getDocumentId()));
				}
				
				Work entity = new Work();
				TechnicalSanction technicalSanctionEntity = new TechnicalSanction();
				AdministrationSanction administrationSanctionEntity = new AdministrationSanction();
				if (bean.getAdministrationSanctionBean().getAdministrationSanctionFile() != null) {
					DocumentUpload documentUpload = RESUtil.uploadAsWorkDocument(documentRootPath+workAdminSanctionDocumentPath,
							"blank", bean.getAdministrationSanctionBean().getAdministrationSanctionFile(),
							null,
							"blank");
					documentRepository.save(documentUpload);
					administrationSanctionEntity.setDocumentUpload(documentUpload);
//					entity.setAdministrationSanctionFile(documentUpload);
//					legalDao.saveOrUpdate(legalDocumentUpload);
//					legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//							legalDocumentUpload.getDocumentId()));
				}
				
				if (bean.getAgreementCopyFile() != null) {
					DocumentUpload documentUpload = RESUtil.uploadAgreementDocument(documentRootPath+workDocumentPath,
							"blank", bean.getAgreementCopyFile(),
							null,
							"blank");
					documentRepository.save(documentUpload);
					entity.setAgreementCopy(documentUpload);
//					legalDao.saveOrUpdate(legalDocumentUpload);
//					legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//							legalDocumentUpload.getDocumentId()));
				}
				
				if (bean.getTechnicalSanctionBean().getLatestDrawingCopyFile() != null) {
					DocumentUpload documentUpload = RESUtil.uploadDrawingDocument(documentRootPath+workTechSanctionDocumentPath,
							"blank", bean.getTechnicalSanctionBean().getLatestDrawingCopyFile(),
							null,
							"blank");
					documentRepository.save(documentUpload);
					technicalSanctionEntity.setDocumentUploadDrawing(documentUpload);
//					legalDao.saveOrUpdate(legalDocumentUpload);
//					legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//							legalDocumentUpload.getDocumentId()));
				}
				
				
				
				if (bean.getTechnicalSanctionBean().getEstimateFile() != null) {
					DocumentUpload documentUpload = RESUtil.uploadEstimationDocument(documentRootPath+workTechSanctionDocumentPath,
							"blank", bean.getTechnicalSanctionBean().getEstimateFile(),
							null,
							"blank");
					documentRepository.save(documentUpload);
					technicalSanctionEntity.setDocumentUploadEstimate(documentUpload);
//					legalDao.saveOrUpdate(legalDocumentUpload);
//					legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//							legalDocumentUpload.getDocumentId()));
				}
				
				if (bean.getTechnicalSanctionBean().getTechnicalSanctionFile() != null) {
					DocumentUpload documentUpload = RESUtil.uploadTsWorkDocument(documentRootPath+workTechSanctionDocumentPath,
							"blank", bean.getTechnicalSanctionBean().getTechnicalSanctionFile(),
							null,
							"blank");
					documentRepository.save(documentUpload);
					technicalSanctionEntity.setDocumentUploadTechnical(documentUpload);
//					legalDao.saveOrUpdate(legalDocumentUpload);
//					legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//							legalDocumentUpload.getDocumentId()));
				}
				
			
				convertWorkBeanToEntity(entity, bean);
				workRepository.save(entity);
				
				convertWorkBeanToTechnicalSanctionEntity(technicalSanctionEntity,entity,bean);
				technicalSanctionRepository.save(technicalSanctionEntity);
				
				convertWorkBeanToAdministrationSanctionEntity(administrationSanctionEntity,entity,bean);
				administrationSanctionRepository.save(administrationSanctionEntity);
			}
			return null;
		}catch (Exception e) {
			logger.error(e.getMessage());
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	



	private Work convertWorkBeanToEntity(Work entity, WorkBean bean) throws RESBusinessException {
		
		
		
		entity.setWorkName(bean.getWorkName());
		entity.setWorkTypeId(new WorkType(bean.getWorkTypeBean().getWorkTypeId()));
		entity.setWorkSubTypeId(new WorkSubType(bean.getWorkSubTypeBean().getWorkSubTypeId()));
		entity.setLineDepartmentId(new LineDepartment(bean.getLineDepartmentBean().getLineDepartmentId()));
		entity.setAccountHead(bean.getAccountHead());
		entity.setAgencyTypeId(new AgencyType(bean.getAgencyTypeBean().getAgencyTypeId()));
		if(bean.getAgencyTypeBean().getAgencyTypeId()==1){
			entity.setContractor(new Contractor(bean.getContractorBean().getId()));
		}
		if(bean.getAgencyTypeBean().getAgencyTypeId()==2){
			if(bean.getAgencyName()!=null)
			entity.setAgencyName(bean.getAgencyName());
		}
		entity.setEstimatedCost(new BigDecimal(bean.getEstimatedCostString()));
		entity.setTotalCost(new BigDecimal(bean.getTotalCostString()));
		entity.setTotalExpenditureTill31March2018(new BigDecimal(bean.getTotalExpenditureTill31March2018String()));
		if(bean.getPhysicalStageTypeBean()!=null)
		entity.setPhysicalStageType(new PhysicalStageType(bean.getPhysicalStageTypeBean().getPhysicalStageId()));
		entity.setTentativeCompletionDate(RESUtil.convertStringToDate(bean.getTentativeCompletionDateString()));
		entity.setTotalAmountRecievedTill31march2018(new BigDecimal(bean.getTotalAmountRecievedTill31March2018String()));
		entity.setAgreementDate(RESUtil.convertStringToDate(bean.getAgreementDateString()));
		entity.setTenderedRateSign(bean.getTenderedRateSign());
		entity.setTenderedRatePer(bean.getTenderedRatePer());
		entity.setDistrict(new District(bean.getDistrict().getDistrictId()));
		entity.setBlock(new Block(bean.getBlock().getBlockId()));
		if(bean.getGramPanchayatBean()!=null)
		entity.setGramPanchayat(new GramPanchayat(bean.getGramPanchayatBean().getGramPanchayatId()));
		if(bean.getVillageBean()!=null)
		entity.setVillage(new Village(bean.getVillageBean().getVillageId()));
		entity.setWorkLocationLatitude(bean.getWorkLocationLatitude());
		entity.setWorkLocationLongitude(bean.getWorkLocationLongitude());
		if(bean.getLocationGeometery()!=null && !bean.getLocationGeometery().isEmpty())
		entity.setLocationGeometery(bean.getLocationGeometery());
		entity.setExecutiveEngineerOffice(new Office(bean.getUserBean().getOfficeBean().getId()));
		entity.setLocationAddress(bean.getLocationAddress());
		if(bean.getClientIp()!=null)
		entity.setClientIp(bean.getClientIp());
		if(bean.getAssistantEngineer()!=null)
		entity.setAssistantEngineer(new Users(bean.getAssistantEngineer().getId()));
		if(bean.getSubEngineer()!=null)
		entity.setSubEngineer(new Users(bean.getSubEngineer().getId()));
		if(bean.getWorkStatusBean()!=null)
		entity.setWorkStatusId(new WorkStatus(bean.getWorkStatusBean().getWorkStatusid()));
		entity.setStatus(RESConstants.STATUS_ACTIVE);
		
		entity.setTenderedRateSign(bean.getTenderedRateSign());
		entity.setTenderedRatePer(bean.getTenderedRatePer()!=null ? bean.getTenderedRatePer() : BigDecimal.ZERO);
		
		return entity;
	}
	
	private TechnicalSanction convertWorkBeanToTechnicalSanctionEntity(
			TechnicalSanction technicalSanctionEntity, Work entity,
			WorkBean bean) throws RESBusinessException {
		technicalSanctionEntity.setTechnicalSanctionType(new TechnicalSanctionType(bean.getTechnicalSanctionBean().getTechnicalSanctionTypeBean().getTechnicalSanctionTypeId()));
		technicalSanctionEntity.setTechnicalSanctionNo(bean.getTechnicalSanctionBean().getTechnicalSanctionNo());
		technicalSanctionEntity.setTechnicalSanctionDate(RESUtil.convertStringToDate(bean.getTechnicalSanctionBean().getTechnicalSanctionDate()));
		technicalSanctionEntity.setWork(entity);
		technicalSanctionEntity.setStatus(RESConstants.STATUS_ACTIVE);
		technicalSanctionEntity.setTechnicalSanctionAmount(new BigDecimal(bean.getEstimatedCostString()));
		return technicalSanctionEntity;
		
	}
	

	private void convertWorkBeanToAdministrationSanctionEntity(
			AdministrationSanction administrationSanctionEntity, Work entity,
			WorkBean bean) throws RESBusinessException {
		administrationSanctionEntity.setAdministrationSanctionType(new AdministrationSanctionType(bean.getAdministrationSanctionBean().getAdministrationSanctionTypeBean().getAdministrationSanctionTypeId()));
		administrationSanctionEntity.setAdministrativeSanctionNo(bean.getAdministrationSanctionBean().getAdministrationSanctionNo());
		administrationSanctionEntity.setAdministrativeSanctionDate(RESUtil.convertStringToDate(bean.getAdministrationSanctionBean().getAdministrationSanctionDate()));
		if(entity.getAgencyTypeId().getAgencyTypeId()==1){
			administrationSanctionEntity.setIssuingAuthority(new IssuingAuthority(bean.getLineDepartmentBean().getLineDepartmentId()));
		}
		if(entity.getAgencyTypeId().getAgencyTypeId()==2){
			administrationSanctionEntity.setIssuingAuthority(new IssuingAuthority(bean.getAdministrationSanctionBean().getIssuingAuthorityBean().getIssuingAuthorityId()));
		}		
		administrationSanctionEntity.setWork(entity);
		administrationSanctionEntity.setAdministrativeSanctionAmount(new BigDecimal(bean.getTotalCostString()));
		administrationSanctionEntity.setStatus(RESConstants.STATUS_ACTIVE);
	}

	@Override
	public WorkJson getAllWorks(Pageable pageable) {
		WorkJson workJson = null;
		String[] statusArr = null;
		if(StringUtils.isEmpty(status)){
			statusArr = new String[]{RESConstants.STATUS_ACTIVE, RESConstants.STATUS_INACTIVE, RESConstants.STATUS_PENDING};
		}else{
			statusArr = new String[]{status};
		}
		try{
			Page<Work> works = null;
			
			if(!StringUtils.isEmpty(searchBoxVal) && (!StringUtils.isEmpty(designation) || !StringUtils.isEmpty(status)))

				users = userRepository.findByNameContainingOrEmailIdContainingAndDesignationAndStatusInAndIsOICNotNull(pageable, searchBoxVal, searchBoxVal, 
						designation, statusArr);
			else if(!StringUtils.isEmpty(searchBoxVal))
				users = userRepository.findByNameContainingOrEmailIdContainingAndStatusNotAndIsOICNotNull(pageable, searchBoxVal, searchBoxVal, RESConstants.STATUS_DELETED);
			else if(!StringUtils.isEmpty(designation) || !StringUtils.isEmpty(status))
				users = userRepository.findByDesignationAndStatusInAndIsOICNotNull(pageable, designation, statusArr);
			else
				users = userRepository.findByStatusNotAndIsOICNotNull(pageable, RESConstants.STATUS_DELETED);
			
			works = workRepository.findAll(pageable);
			
			if(works!=null){
				List<Work> entityList = works.getContent();
				List<WorkBean> beanList = new ArrayList<>();
				if(entityList!= null && !entityList.isEmpty()){
					
					int index = pageable.getPageNumber()*pageable.getPageSize();
					for(Work work : entityList){
						
						WorkBean bean = convertWorkEntityToBean(work);
						bean.setIndex(++index);
						beanList.add(bean);
					}
				}
				workJson = new WorkJson();
				workJson.setiTotalDisplayRecords(works.getTotalElements());
//				workJson.setiTotalRecords(workRepository.countByStatusNotAndIsOICNotNull(RESConstants.STATUS_DELETED));
				workJson.setiTotalRecords(workRepository.count());
				workJson.setAaData(beanList);
			}
			return workJson;
		}
		catch (Exception e) {
			logger.error("An exception occurred.", e);
			return workJson;
		}
	}
	
	private WorkBean convertWorkEntityToBean(Work entity) {
		WorkBean bean = new WorkBean();
		if(entity!=null){
			bean.setWorkId(entity.getId());
			bean.setWorkName(entity.getWorkName());
			bean.setWorkTypeId(entity.getWorkTypeId().getWorkTypeId());
			bean.setWorkSubTypeId(entity.getWorkSubTypeId().getWorkSubTypeId());
			bean.setLineDepartmentId(entity.getLineDepartmentId().getLineDepartmentId());
//			bean.setWorkTypeBean(convertWorkTypeEntityToBean(entity.getWorkTypeId()));
//			bean.setWorkSubTypeBean(convertWorkSubTypeEntityToBean(entity.getWorkSubTypeId()));
//			bean.setLineDepartmentBean(convertLineDepartmentEntityToBean(entity.getLineDepartmentId()));
			bean.setEstimatedCostString(String.valueOf(entity.getEstimatedCost()));
			bean.setTotalCostString(String.valueOf(entity.getTotalCost()));
			bean.setAccountHead(entity.getAccountHead());
			bean.setAgencyTypeId(entity.getAgencyTypeId().getAgencyTypeId());
//			bean.setAgencyTypeBean(convertAgencyTypeEntityToBean(entity.getAgencyTypeId()));
			bean.setAgencyName(entity.getAgencyName());
			bean.setTotalExpenditureTill31March2018String(String.valueOf(entity.getTotalExpenditureTill31March2018()));
			bean.setPhysicalStageId(entity.getPhysicalStageType().getPhysicalStageId());
			bean.setAgreementDateString(RESUtil.convertDateToString(entity.getAgreementDate()));
			bean.setTenderedRateSign(entity.getTenderedRateSign());
			bean.setTenderedRatePer(entity.getTenderedRatePer());
			
			if(entity.getAssistantEngineer()!=null)
			bean.setAssistantEngineerId(entity.getAssistantEngineer().getId());
//			bean.setSubEngineerId(entity.getSubEngineer().getId());
			if(entity.getExecutiveEngineerOffice()!=null)
			{
			bean.setExecutiveEngineerOfficeId(entity.getExecutiveEngineerOffice().getId());
			bean.setExecutiveEngineerOfficeName(entity.getExecutiveEngineerOffice().getOfficeName());
			}
//			bean.setPhysicalStageTypeBean(convertPhysicalStageTypeEntityToBean(entity.getPhysicalStageType()));
//			bean.setExecuticeEngineerOffice(convertOfficeEntityToBean(entity.getExecutiveEngineerOffice()));
//			bean.setAssistantEngineer(convertUserEntityToBean(entity.getAssistantEngineer()));
//			bean.setSubEngineer(convertUserEntityToBean(entity.getSubEngineer()));
			if(entity.getDistrict()!=null)
			bean.setDistrictId(entity.getDistrict().getDistrictId());
			bean.setBlockId(entity.getBlock().getBlockId());
//			bean.setDistrict(convertDistrictEntityToBean(entity.getDistrict()));
//			bean.setBlock(convertBlockEntityToBean(entity.getBlock()));
			bean.setLocationGeometery(entity.getLocationGeometery());
			bean.setWorkLocationLatitude(entity.getWorkLocationLatitude());
			bean.setWorkLocationLongitude(entity.getWorkLocationLongitude());
			bean.setClientIp(entity.getClientIp());
			bean.setApprovedBy(entity.getApprovedBy());
			bean.setStatus(entity.getStatus());
			
			bean.setTenderedRateSign(entity.getTenderedRateSign());
			bean.setTenderedRatePer(entity.getTenderedRatePer());
		}
		return bean;
	
	}





	private WorkBean convertWorkEntityToBean(Work entity,TechnicalSanction entityTechnical,AdministrationSanction entityAdministration) {
		WorkBean bean = new WorkBean();
		if(entity!=null){
			bean.setWorkId(entity.getId());
			bean.setWorkName(entity.getWorkName());
			bean.setWorkTypeId(entity.getWorkTypeId().getWorkTypeId());
			bean.setWorkSubTypeId(entity.getWorkSubTypeId().getWorkSubTypeId());
			bean.setLineDepartmentId(entity.getLineDepartmentId().getLineDepartmentId());
			bean.setWorkStatusId(entity.getWorkStatusId().getId());
//			bean.setWorkTypeBean(convertWorkTypeEntityToBean(entity.getWorkTypeId()));
//			bean.setWorkSubTypeBean(convertWorkSubTypeEntityToBean(entity.getWorkSubTypeId()));
//			bean.setLineDepartmentBean(convertLineDepartmentEntityToBean(entity.getLineDepartmentId()));
			bean.setEstimatedCostString(String.valueOf(entity.getEstimatedCost()));
			bean.setTotalCostString(String.valueOf(entity.getTotalCost()));			
			bean.setAccountHead(entity.getAccountHead());
			bean.setAgencyTypeId(entity.getAgencyTypeId().getAgencyTypeId());
//			bean.setAgencyTypeBean(convertAgencyTypeEntityToBean(entity.getAgencyTypeId()));
			bean.setAgencyName(entity.getAgencyName());
			if(entity.getContractor()!=null)
			bean.setContractorId(entity.getContractor().getId());
			bean.setTotalExpenditureTill31March2018String(String.valueOf(entity.getTotalExpenditureTill31March2018()));
			if(entity.getPhysicalStageType()!=null)
			bean.setPhysicalStageId(entity.getPhysicalStageType().getPhysicalStageId());
			bean.setTentativeCompletionDateString(RESUtil.convertDateToString(entity.getTentativeCompletionDate()));
			bean.setTotalAmountRecievedTill31March2018String(String.valueOf(entity.getTotalAmountRecievedTill31march2018()));			
			
			bean.setAgreementDateString(RESUtil.convertDateToString(entity.getAgreementDate()));
			bean.setTenderedRateSign(entity.getTenderedRateSign());
			bean.setTenderedRatePer(entity.getTenderedRatePer());
			if(entity.getAssistantEngineer()!=null)
			{
			bean.setAssistantEngineerId(entity.getAssistantEngineer().getId());
			} else {
				bean.setAssistantEngineerId(null);
			}
			if(entity.getSubEngineer()!=null)
			{
			bean.setSubEngineerId(entity.getSubEngineer().getId());
			} else {
				bean.setSubEngineerId(null);
			}
//			bean.setSubEngineerId(entity.getSubEngineer().getId());
			if(entity.getExecutiveEngineerOffice()!=null)
			{
			bean.setExecutiveEngineerOfficeId(entity.getExecutiveEngineerOffice().getId());
			bean.setExecutiveEngineerOfficeName(entity.getExecutiveEngineerOffice().getOfficeName());
			}
//			bean.setPhysicalStageTypeBean(convertPhysicalStageTypeEntityToBean(entity.getPhysicalStageType()));
//			bean.setExecuticeEngineerOffice(convertOfficeEntityToBean(entity.getExecutiveEngineerOffice()));
//			bean.setAssistantEngineer(convertUserEntityToBean(entity.getAssistantEngineer()));
//			bean.setSubEngineer(convertUserEntityToBean(entity.getSubEngineer()));
			bean.setDistrictId(entity.getDistrict().getDistrictId());
			bean.setDistrictName(entity.getDistrict().getDistrictName());
			bean.setBlockId(entity.getBlock().getBlockId());
			if(entity.getGramPanchayat()!=null)
			bean.setGramPanchayatId(entity.getGramPanchayat().getGramPanchayatId());
			if(entity.getVillage()!=null)
			bean.setVillageId(entity.getVillage().getId());
//			bean.setDistrict(convertDistrictEntityToBean(entity.getDistrict()));
//			bean.setBlock(convertBlockEntityToBean(entity.getBlock()));
			bean.setLocationAddress(entity.getLocationAddress());
			bean.setLocationGeometery(entity.getLocationGeometery());
			bean.setWorkLocationLatitude(entity.getWorkLocationLatitude());
			bean.setWorkLocationLongitude(entity.getWorkLocationLongitude());
			bean.setClientIp(entity.getClientIp());
			bean.setApprovedBy(entity.getApprovedBy());
			bean.setStatus(entity.getStatus());
			if(entityTechnical!=null)
			{
			bean.setTechnicalSanctionTypeId(entityTechnical.getTechnicalSanctionType().getTechnicalSanctionTypeId());
			bean.setTechnicalSanctionNo(entityTechnical.getTechnicalSanctionNo());
			bean.setTechnicalSanctionDate(RESUtil.convertDateToString(entityTechnical.getTechnicalSanctionDate()));
			}
			if(entityAdministration!=null)
			{
			bean.setAdministrationSanctionTypeId(entityAdministration.getAdministrationSanctionType().getAdministrationSanctionTypeId());
			bean.setAdministrationSanctionNo(entityAdministration.getAdministrativeSanctionNo());
			bean.setAdministrationSanctionDate(RESUtil.convertDateToString(entityAdministration.getAdministrativeSanctionDate()));
			if(entityAdministration.getIssuingAuthority()!=null)
			bean.setIssuingAuthorityId(entityAdministration.getIssuingAuthority().getIssuingAuthorityId());
			}
		}
		return bean;
	}

	private OfficeBean convertOfficeEntityToBean(Office entity) {
		OfficeBean bean = new OfficeBean();
		if(entity!=null){
			bean.setId(entity.getId());
			bean.setOfficeName(entity.getOfficeName());
			bean.setOfficeNameH(entity.getOfficeNameH());
			bean.setEnabled(entity.getEnabled());
			bean.setOfficeType(convertOfficeTypeEntityToBean(entity.getOfficeType()));
			bean.setOic(convertUserEntityToBean(entity.getOic()));
			bean.setParentOffice(convertOfficeEntityToBean(entity.getParentOffice()));
			bean.setDistrict(convertDistrictEntityToBean(districtRepository.findByDistrictCodeAndEnabled(entity.getDistrictCode(),(short) 1)));
			bean.setDivision(convertDivisionEntityToBean(entity.getDivision()));
		}
		return bean;
	}
	
	private BlockBean convertBlockEntityToBean(Block entity) {


		BlockBean bean = new BlockBean();
		if(entity!=null){
			bean.setBlockId(entity.getBlockId());
			bean.setBlockName(entity.getBlockName());
			bean.setBlockNameH(entity.getBlockNameH());
			bean.setEnabled(entity.getEnabled());
			bean.setDistrictCode(entity.getDistrictCode());
			bean.setBlockCode(entity.getBlockCode());
			
		}
		return bean;
	
		
	}
	
	private DivisionBean convertDivisionEntityToBean(Division entity){

		DivisionBean bean = new DivisionBean();
		if(entity!=null){
			bean.setId(entity.getId());
			bean.setDivisionName(entity.getDivisionName());
			bean.setDivisionNameH(entity.getDivisionNameH());
			bean.setEnabled(entity.getEnabled());
		}
		return bean;
	}
	
	private DistrictBean convertDistrictEntityToBean(District entity){

		DistrictBean bean = new DistrictBean();
		if(entity!=null){
			bean.setDistrictId(entity.getDistrictId());
			bean.setDistrictCode(entity.getDistrictCode());
			bean.setDistrictName(entity.getDistrictName());
			bean.setDistrictNameH(entity.getDistrictNameH());
			bean.setEnabled(entity.getEnabled());
		}
		return bean;
	}
	
	private UserBean convertUserEntityToBean(Users entity) {

		UserBean bean = new UserBean();
		if(entity!=null){
			bean.setId(entity.getId());
			bean.setName(entity.getName());
		}
		return bean;
	
	}


	private OfficeTypeBean convertOfficeTypeEntityToBean(OfficeType entity) {
		OfficeTypeBean bean = new OfficeTypeBean();
		if(entity!=null){
			bean.setId(entity.getId());
			bean.setOfficeType(entity.getOfficeType());
			bean.setOfficeTypeH(entity.getOfficeTypeH());
			bean.setEnabled(entity.getEnabled());
		}
		return bean;
	}





	private PhysicalStageTypeBean convertPhysicalStageTypeEntityToBean(
			PhysicalStageType entity) {
		PhysicalStageTypeBean bean = new PhysicalStageTypeBean();
		if(entity!=null){
			bean.setPhysicalStageId(entity.getPhysicalStageId());
			bean.setPhysicalStageNameE(entity.getPhysicalStageNameE());
			bean.setPhysicalStageNameH(entity.getPhysicalStageNameH());
			bean.setEnabled(entity.getEnabled());
		}
		return bean;
	}

	private AgencyTypeBean convertAgencyTypeEntityToBean(AgencyType entity) {
		AgencyTypeBean bean = new AgencyTypeBean();
		if(entity!=null){
			bean.setAgencyTypeId(entity.getAgencyTypeId());
			bean.setAgencyTypeNameE(entity.getAgencyTypeNameE());
			bean.setAgencyTypeNameH(entity.getAgencyTypeNameH());
			bean.setEnabled(entity.getEnabled());
		}
		return bean;
	}

	private LineDepartmentBean convertLineDepartmentEntityToBean(
			LineDepartment entity) {
		LineDepartmentBean bean = new LineDepartmentBean();
		if(entity!=null){
			bean.setLineDepartmentId(bean.getLineDepartmentId());
			bean.setLineDepartmentNameE(bean.getLineDepartmentNameE());
			bean.setLineDepartmentNameH(bean.getLineDepartmentNameH());
			bean.setEnabled(bean.getEnabled());
		}
		return bean;
	}





	private WorkSubTypeBean convertWorkSubTypeEntityToBean(
			WorkSubType entity) {
		WorkSubTypeBean bean = new WorkSubTypeBean();
		
		if(entity!=null){
			bean.setWorkSubTypeId(entity.getWorkSubTypeId());
			bean.setWorkSubTypeNameE(entity.getWorkSubTypeNameE());
			bean.setWorkSubTypeNameH(entity.getWorkSubTypeNameH());
			bean.setEnabled(entity.getEnabled());
			bean.setWorkType(new WorkTypeBean(entity.getWorkType().getWorkTypeId()));
		}
		
		return bean;
	}





	private WorkTypeBean convertWorkTypeEntityToBean(WorkType entity) {
		WorkTypeBean bean = new WorkTypeBean();
		if(entity!=null){
			bean.setWorkTypeId(entity.getWorkTypeId());
			bean.setWorkTypeNameE(entity.getWorkTypeNameE());
			bean.setWorkTypeNameH(entity.getWorkTypeNameH());
			bean.setEnabled(entity.getEnabled());
		}
		return bean;
	}





	@Override
	public String deleteWork(Long id) {
		try{
			Work entity = workRepository.findOne(id);
			if(entity!=null){
				entity.setStatus(RESConstants.STATUS_DELETED);
				workRepository.save(entity);
			}
			TechnicalSanction entity2 = technicalSanctionRepository.findByWork(entity);
			if(entity2!=null){
				entity2.setStatus(RESConstants.STATUS_DELETED);
				technicalSanctionRepository.save(entity2);
			}
			AdministrationSanction entity3 = administrationSanctionRepository.findByWork(entity);
			if(entity3!=null){
				entity3.setStatus(RESConstants.STATUS_DELETED);
				administrationSanctionRepository.save(entity3);
			}
			return null;
		}
		catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_DELETING_DATA;
		}
	}


	@Override
	public WorkBean fetchWorkDetails(Long id) {
		try{
			Work entity = workRepository.findOne(id);
			TechnicalSanction entityTechnical = technicalSanctionRepository.findByWork(entity);
			AdministrationSanction entityAdministration = administrationSanctionRepository.findByWork(entity);
			return convertWorkEntityToBean(entity,entityTechnical,entityAdministration);
		}
		catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	@Override
	public String editWork(WorkBean workBean) {

		
		try{	
			Work entity = workRepository.findOne(workBean.getWorkId());
			TechnicalSanction technicalSanctionEntity = technicalSanctionRepository.findByWork(entity);
			AdministrationSanction administrationSanctionEntity = administrationSanctionRepository.findByWork(entity);
			
			
			if (workBean.getAdministrationSanctionFile() != null) {
				DocumentUpload documentUpload = RESUtil.uploadAsWorkDocument(documentRootPath+workAdminSanctionDocumentPath,
						"blank", workBean.getAdministrationSanctionFile(),
						null,
						"blank");
				documentRepository.save(documentUpload);
				administrationSanctionEntity.setDocumentUpload(documentUpload);
//				entity.setAdministrationSanctionFile(documentUpload);
//				legalDao.saveOrUpdate(legalDocumentUpload);
//				legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//						legalDocumentUpload.getDocumentId()));
			}
			
			if (workBean.getAgreementCopyFile() != null) {
				DocumentUpload documentUpload = RESUtil.uploadAgreementDocument(documentRootPath+workDocumentPath,
						"blank", workBean.getAgreementCopyFile(),
						null,
						"blank");
				documentRepository.save(documentUpload);
				entity.setAgreementCopy(documentUpload);
//				legalDao.saveOrUpdate(legalDocumentUpload);
//				legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//						legalDocumentUpload.getDocumentId()));
			}
			
			if (workBean.getLatestDrawingCopyFile() != null) {
				DocumentUpload documentUpload = RESUtil.uploadDrawingDocument(documentRootPath+workTechSanctionDocumentPath,
						"blank", workBean.getLatestDrawingCopyFile(),
						null,
						"blank");
				documentRepository.save(documentUpload);
				technicalSanctionEntity.setDocumentUploadDrawing(documentUpload);
//				legalDao.saveOrUpdate(legalDocumentUpload);
//				legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//						legalDocumentUpload.getDocumentId()));
			}
			
			
			
			if (workBean.getEstimateFile() != null) {
				DocumentUpload documentUpload = RESUtil.uploadEstimationDocument(documentRootPath+workTechSanctionDocumentPath,
						"blank", workBean.getEstimateFile(),
						null,
						"blank");
				documentRepository.save(documentUpload);
				technicalSanctionEntity.setDocumentUploadEstimate(documentUpload);
//				legalDao.saveOrUpdate(legalDocumentUpload);
//				legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//						legalDocumentUpload.getDocumentId()));
			}
			
			if (workBean.getTechnicalSanctionFile() != null) {
				DocumentUpload documentUpload = RESUtil.uploadTsWorkDocument(documentRootPath+workTechSanctionDocumentPath,
						"blank", workBean.getTechnicalSanctionFile(),
						null,
						"blank");
				documentRepository.save(documentUpload);
				technicalSanctionEntity.setDocumentUploadTechnical(documentUpload);
//				legalDao.saveOrUpdate(legalDocumentUpload);
//				legalCaseHearing.setLegalDocumentUpload(new LegalDocumentUpload(
//						legalDocumentUpload.getDocumentId()));
			}
				convertWorkBeanToEntityDuringEdit(entity, workBean);
				workRepository.save(entity);
				
				convertWorkBeanToTechnicalSanctionEntityDuringEdit(technicalSanctionEntity,workBean);
				technicalSanctionRepository.save(technicalSanctionEntity);
				
				convertWorkBeanToAdministrationSanctionEntityDuringEdit(administrationSanctionEntity,workBean);
				administrationSanctionRepository.save(administrationSanctionEntity);
		
			
			return null;
			
		}catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	
	}
	
	private void convertWorkBeanToEntityDuringEdit(Work entity,
			WorkBean workBean) throws RESBusinessException {
		
		entity.setWorkName(workBean.getWorkName());
		entity.setWorkTypeId(new WorkType(workBean.getWorkTypeId()));
		entity.setWorkSubTypeId(new WorkSubType(workBean.getWorkSubTypeId()));
		entity.setLineDepartmentId(new LineDepartment(workBean.getLineDepartmentId()));
		entity.setAccountHead(workBean.getAccountHead());
		entity.setAgencyTypeId(new AgencyType(workBean.getAgencyTypeId()));
		
		if(workBean.getAgencyTypeId()==1){
			entity.setContractor(new Contractor(workBean.getContractorId()));
		}
		if(workBean.getAgencyTypeId()==2){
			if(workBean.getAgencyName()!=null)
			entity.setAgencyName(workBean.getAgencyName());
		}
		
		entity.setEstimatedCost(new BigDecimal(workBean.getEstimatedCostString()));
		entity.setTotalCost(new BigDecimal(workBean.getTotalCostString()));
		
		entity.setTotalExpenditureTill31March2018(new BigDecimal(workBean.getTotalExpenditureTill31March2018String()));
		
		entity.setWorkStatusId(new WorkStatus(workBean.getWorkStatusId()));
		
		if(workBean.getPhysicalStageId()!=null)
		entity.setPhysicalStageType(new PhysicalStageType(workBean.getPhysicalStageId()));
		entity.setTentativeCompletionDate(RESUtil.convertStringToDate(workBean.getTentativeCompletionDateString()));		
		entity.setTotalAmountRecievedTill31march2018(new BigDecimal(workBean.getTotalAmountRecievedTill31March2018String()));		
		entity.setAgreementDate(RESUtil.convertStringToDate(workBean.getAgreementDateString()));		
		entity.setTenderedRateSign(workBean.getTenderedRateSign());		
		entity.setTenderedRatePer(workBean.getTenderedRatePer());				
		
		entity.setDistrict(new District(workBean.getDistrictId()));
		entity.setBlock(new Block(workBean.getBlockId()));
		if(workBean.getGramPanchayatId()!=null)
		entity.setGramPanchayat(new GramPanchayat(workBean.getGramPanchayatId()));
		if(workBean.getVillageId()!=null)
		entity.setVillage(new Village(workBean.getVillageId()));
		entity.setLocationAddress(workBean.getLocationAddress());
		entity.setWorkLocationLatitude(workBean.getWorkLocationLatitude());
		entity.setWorkLocationLongitude(workBean.getWorkLocationLongitude());
		if(workBean.getLocationGeometery()!=null && !workBean.getLocationGeometery().isEmpty())
		entity.setLocationGeometery(workBean.getLocationGeometery());
		
		if(workBean.getClientIp()!=null)
			entity.setClientIp(workBean.getClientIp());
		
		entity.setExecutiveEngineerOffice(new Office(workBean.getExecutiveEngineerOfficeId()));
		if(workBean.getAssistantEngineerId()!=null)
		entity.setAssistantEngineer(new Users(workBean.getAssistantEngineerId()));
		if(workBean.getSubEngineerId()!=null)
		entity.setSubEngineer(new Users(workBean.getSubEngineerId()));
		
		entity.setStatus(RESConstants.STATUS_ACTIVE);
		
		
	}

	private void convertWorkBeanToTechnicalSanctionEntityDuringEdit(
			TechnicalSanction technicalSanctionEntity, 
			WorkBean workBean) throws RESBusinessException {
		technicalSanctionEntity.setTechnicalSanctionType(new TechnicalSanctionType(workBean.getTechnicalSanctionTypeId()));
		technicalSanctionEntity.setTechnicalSanctionNo(workBean.getTechnicalSanctionNo());
		technicalSanctionEntity.setTechnicalSanctionDate(RESUtil.convertStringToDate(workBean.getTechnicalSanctionDate()));
		technicalSanctionEntity.setTechnicalSanctionAmount(new BigDecimal(workBean.getEstimatedCostString()));
	}
	
	private void convertWorkBeanToAdministrationSanctionEntityDuringEdit(
			AdministrationSanction administrationSanctionEntity, 
			WorkBean workBean) throws RESBusinessException {
		administrationSanctionEntity.setAdministrationSanctionType(new AdministrationSanctionType(workBean.getAdministrationSanctionTypeId()));
		administrationSanctionEntity.setAdministrativeSanctionNo(workBean.getAdministrationSanctionNo());
		administrationSanctionEntity.setAdministrativeSanctionDate(RESUtil.convertStringToDate(workBean.getAdministrationSanctionDate()));
		if(workBean.getIssuingAuthorityId()!=null)
		administrationSanctionEntity.setIssuingAuthority(new IssuingAuthority(workBean.getIssuingAuthorityId()));
		administrationSanctionEntity.setAdministrativeSanctionAmount(new BigDecimal(workBean.getTotalCostString()));
	}
*/
	
	
	
	/*@SuppressWarnings("unused")
	private DocumentUpload uploadWorkDocument(String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {

		String fileName = RESUtil.saveFile(getWorkDocumentPath(),
				String.valueOf(workid), document);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
//		documentUpload.setDocumentType(new MasterLegalDocumentType(documentType));
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}
	
	@SuppressWarnings("unused")
	private DocumentUpload uploadAsWorkDocument(String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {

		String fileName = RESUtil.saveFile(getWorkAdminSanctionDocumentPath(),
				String.valueOf(workid), document);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
//		documentUpload.setDocumentType(new MasterLegalDocumentType(documentType));
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}
	
	@SuppressWarnings("unused")
	private DocumentUpload uploadTsWorkDocument(String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {

		String fileName = RESUtil.saveFile(getWorkTechSanctionDocumentPath(),
				String.valueOf(workid), document);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
//		documentUpload.setDocumentType(new MasterLegalDocumentType(documentType));
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}*/
}
