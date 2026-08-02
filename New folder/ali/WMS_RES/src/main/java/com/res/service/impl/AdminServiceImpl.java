package com.res.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.res.bean.WorkBean;
import com.res.constants.RESConstants;
import com.res.entity.AccountHead;
import com.res.entity.AdministrationSanction;
import com.res.entity.Contractor;
import com.res.entity.LineDepartment;
import com.res.entity.TechnicalSanction;
import com.res.entity.Work;
import com.res.repository.AccountHeadRepository;
import com.res.repository.AdministrationSanctionRepository;
import com.res.repository.ContractorRepository;
import com.res.repository.LineDepartmentRepository;
import com.res.repository.TechnicalSanctionRepository;
import com.res.repository.WorkRepository;
import com.res.service.AdminService;
import com.res.util.RESUtil;


@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private WorkRepository workRepository;
	
	@Autowired
	private TechnicalSanctionRepository technicalSanctionRepository;

	@Autowired
	private AdministrationSanctionRepository administrationSanctionRepository;

	@Autowired
	private AccountHeadRepository accountHeadRepository;
	
	@Autowired
	LineDepartmentRepository lineDepartmentRepository;
	
	@Autowired
	ContractorRepository contractorRepository;

	public static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public WorkBean fetchWorkDetails(Long id) {
		try {
			Work entity = workRepository.findOne(id);
			TechnicalSanction entityTechnical = technicalSanctionRepository.findByWork(entity);
			/*AdministrationSanction entityAdministration = administrationSanctionRepository.findByWork(entity);*/
			
			AdministrationSanction entityAdministration = administrationSanctionRepository.findAllASByWork(entity).get(0);
			
			return convertWorkEntityToBean(entity, entityTechnical, entityAdministration);
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}
	
	private WorkBean convertWorkEntityToBean(Work entity,
			TechnicalSanction entityTechnical,
			AdministrationSanction entityAdministration) {
		WorkBean bean = new WorkBean();
		if (entity != null) {
			bean.setWorkId(entity.getId());
			bean.setWorkName(entity.getWorkName());
			bean.setWorkTypeId(entity.getWorkTypeId().getWorkTypeId());
			bean.setWorkSubTypeId(entity.getWorkSubTypeId().getWorkSubTypeId());
			bean.setLineDepartmentId(entity.getLineDepartmentId()
					.getLineDepartmentId());
			bean.setWorkStatusId(entity.getWorkStatusId().getId());
			// bean.setWorkTypeBean(convertWorkTypeEntityToBean(entity.getWorkTypeId()));
			// bean.setWorkSubTypeBean(convertWorkSubTypeEntityToBean(entity.getWorkSubTypeId()));
			// bean.setLineDepartmentBean(convertLineDepartmentEntityToBean(entity.getLineDepartmentId()));
			bean.setEstimatedCostString(String.valueOf(entity
					.getEstimatedCost()));
			bean.setTotalCostString(String.valueOf(entity.getTotalCost()));
			if (entity.getAccountHead() != null) {
				bean.setAccountHeadId(entity.getAccountHead().getId());
				bean.setAccountHeadName(entity.getAccountHead()
						.getAccountHeadNameE());
			}
			bean.setAgencyTypeId(entity.getAgencyTypeId().getAgencyTypeId());
			// bean.setAgencyTypeBean(convertAgencyTypeEntityToBean(entity.getAgencyTypeId()));
			bean.setAgencyName(entity.getAgencyName());
			if (entity.getContractor() != null)
				bean.setContractorId(entity.getContractor().getId());
			bean.setTotalExpenditureTill31March2018String(String.valueOf(entity
					.getTotalExpenditureTill31March2018()));
			if (entity.getPhysicalStageType() != null)
				bean.setPhysicalStageId(entity.getPhysicalStageType()
						.getPhysicalStageId());
			bean.setTentativeCompletionDateString(RESUtil
					.convertDateToString(entity.getTentativeCompletionDate()));
			bean.setTotalAmountRecievedTill31March2018String(String
					.valueOf(entity.getTotalAmountRecievedTill31march2018()));

			bean.setAgreementDateString(RESUtil.convertDateToString(entity
					.getAgreementDate()));
			bean.setTenderedRateSign(entity.getTenderedRateSign());
			bean.setTenderedRatePer(entity.getTenderedRatePer());
			if (entity.getAssistantEngineer() != null) {
				bean.setAssistantEngineerId(entity.getAssistantEngineer()
						.getId());
			} else {
				bean.setAssistantEngineerId(null);
			}
			if (entity.getSubDivisionalOfficer() != null) {
				bean.setSubDivisionOfficerId(entity.getSubDivisionalOfficer().getId());
			} else {
				bean.setSubDivisionOfficerId(null);
			}
			if (entity.getSubEngineer() != null) {
				bean.setSubEngineerId(entity.getSubEngineer().getId());
			} else {
				bean.setSubEngineerId(null);
			}
			// bean.setSubEngineerId(entity.getSubEngineer().getId());
			if (entity.getExecutiveEngineerOffice() != null) {
				bean.setExecutiveEngineerOfficeId(entity
						.getExecutiveEngineerOffice().getId());
				bean.setExecutiveEngineerOfficeName(entity
						.getExecutiveEngineerOffice().getOfficeName());
			}
			// bean.setPhysicalStageTypeBean(convertPhysicalStageTypeEntityToBean(entity.getPhysicalStageType()));
			// bean.setExecuticeEngineerOffice(convertOfficeEntityToBean(entity.getExecutiveEngineerOffice()));
			// bean.setAssistantEngineer(convertUserEntityToBean(entity.getAssistantEngineer()));
			// bean.setSubEngineer(convertUserEntityToBean(entity.getSubEngineer()));
			bean.setDistrictId(entity.getDistrict().getDistrictId());
			bean.setDistrictName(entity.getDistrict().getDistrictName());
			bean.setBlockId(entity.getBlock().getBlockId());
			if (entity.getGramPanchayat() != null)
				bean.setGramPanchayatId(entity.getGramPanchayat()
						.getGramPanchayatId());
			if (entity.getVillage() != null)
				bean.setVillageId(entity.getVillage().getId());
			// bean.setDistrict(convertDistrictEntityToBean(entity.getDistrict()));
			// bean.setBlock(convertBlockEntityToBean(entity.getBlock()));
			bean.setLocationAddress(entity.getLocationAddress());
			bean.setLocationGeometery(entity.getLocationGeometery());
			bean.setWorkLocationLatitude(entity.getWorkLocationLatitude());
			bean.setWorkLocationLongitude(entity.getWorkLocationLongitude());
			bean.setClientIp(entity.getClientIp());
			bean.setApprovedBy(entity.getApprovedBy());
			bean.setStatus(entity.getStatus());
			if (entityTechnical != null) {
				bean.setTechnicalSanctionTypeId(entityTechnical
						.getTechnicalSanctionType()
						.getTechnicalSanctionTypeId());
				bean.setTechnicalSanctionNo(entityTechnical
						.getTechnicalSanctionNo());
				bean.setTechnicalSanctionDate(RESUtil
						.convertDateToString(entityTechnical
								.getTechnicalSanctionDate()));
			}
			if (entityAdministration != null) {
				bean.setAdministrationSanctionTypeId(entityAdministration
						.getAdministrationSanctionType()
						.getAdministrationSanctionTypeId());
				bean.setAdministrationSanctionNo(entityAdministration
						.getAdministrativeSanctionNo());
				bean.setAdministrationSanctionDate(RESUtil
						.convertDateToString(entityAdministration
								.getAdministrativeSanctionDate()));
				if (entityAdministration.getIssuingAuthority() != null)
					bean.setIssuingAuthorityId(entityAdministration
							.getIssuingAuthority().getIssuingAuthorityId());
			}
		}
		return bean;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String deleteAccountHead(Long accountHeadId, String deletedBy) {

		try {
			AccountHead entity = accountHeadRepository.findOne(accountHeadId);
			
				entity.setModifiedBy(deletedBy);
				entity.setModifiedDate(new Date());
				entity.setEnabled((short)0);
				accountHeadRepository.save(entity);
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_DELETING_DATA;
		}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String deleteLineDept(Long id, String deletedBy) {

		try {
			LineDepartment entity = lineDepartmentRepository.findOne(id);
			
				entity.setModifiedBy(deletedBy);
				entity.setModifiedDate(new Date());
				entity.setEnabled((short)0);
				lineDepartmentRepository.save(entity);
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_DELETING_DATA;
		}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String deleteContractor(Long id, String deletedBy) {

		try {
			Contractor entity = contractorRepository.findOne(id);
			
				entity.setModifiedBy(deletedBy);
				entity.setModifiedDate(new Date());
				entity.setEnabled((short)0);
				contractorRepository.save(entity);
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_DELETING_DATA;
		}
	}
}
