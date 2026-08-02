package com.res.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.res.bean.AccountHeadBean;
import com.res.bean.AdministrationSanctionBean;
import com.res.bean.AdministrationSanctionTypeBean;
import com.res.bean.AgencyTypeBean;
import com.res.bean.BankBean;
import com.res.bean.BillBean;
import com.res.bean.BillLoggingBean;
import com.res.bean.BlockBean;
import com.res.bean.CCDetailsBean;
import com.res.bean.CCDispatchDetailsBean;
import com.res.bean.ChangePasswordBean;
import com.res.bean.ChapterBean;
import com.res.bean.ContengencyBean;
import com.res.bean.ContractorBean;
import com.res.bean.DashboardBean;
import com.res.bean.DeductionBean;
import com.res.bean.DepositeCategoryBean;
import com.res.bean.DepositeTypeBean;
import com.res.bean.DesignationBean;
import com.res.bean.DistrictBean;
import com.res.bean.FinancialStageTypeBean;
import com.res.bean.FinancialYearBean;
import com.res.bean.GramPanchayatBean;
import com.res.bean.InspectionAnswerImageBean;
import com.res.bean.InspectionAnswerNewBean;
import com.res.bean.InspectionSqmAnswerBean;
import com.res.bean.IssuingAuthorityBean;
import com.res.bean.ItemBean;
import com.res.bean.LineDepartmentBean;
import com.res.bean.OfficeBean;
import com.res.bean.OfficeTypeBean;
import com.res.bean.PaymentBean;
import com.res.bean.PhysicalStageTypeBean;
import com.res.bean.RoleBean;
import com.res.bean.SORBean;
import com.res.bean.SqmAllocationBean;
import com.res.bean.StandardTemplateTypeBean;
import com.res.bean.StateBean;
import com.res.bean.TechnicalSanctionBean;
import com.res.bean.TechnicalSanctionTypeBean;
import com.res.bean.UserBean;
import com.res.bean.VillageBean;
import com.res.bean.WorkAgreementBean;
import com.res.bean.WorkAgreementRevisionBean;
import com.res.bean.WorkBean;
import com.res.bean.WorkEstimationBean;
import com.res.bean.WorkFileBean;
import com.res.bean.WorkLoggingBean;
import com.res.bean.WorkNatureBean;
import com.res.bean.WorkStatusBean;
import com.res.bean.WorkSubTypeBean;
import com.res.bean.WorkTemplateBean;
import com.res.bean.WorkTenderBean;
import com.res.bean.WorkTypeBean;
import com.res.constants.RESConstants;
import com.res.entity.Block;
import com.res.entity.District;
import com.res.entity.InspectionAnswer;
import com.res.entity.InspectionAnswerCC;
import com.res.entity.InspectionAnswerImage;
import com.res.entity.InspectionAnswerImageNew;
import com.res.entity.InspectionAnswersNew;
import com.res.entity.InspectionDetails;
import com.res.entity.InspectionAnswerImageCC;
import com.res.entity.InspectionSqmAnswerFile;
import com.res.entity.InspectionSqmAnswerImage;
import com.res.entity.SqmAllocation;
import com.res.entity.Users;
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
import com.res.json.UserJson;
import com.res.json.WorkAgreementJson;
import com.res.json.WorkDistrictJson;
import com.res.json.WorkEstimationJson;
import com.res.json.WorkJson;
import com.res.json.WorkLoggingJson;
import com.res.json.WorkTenderJson;
import com.res.repository.InspectionDetailsRepository;
import com.res.repository.SqmAllocationRepository;
import com.res.response.ResponseObject;
import com.res.service.CommonService;
import com.res.service.DashboardService;
import com.res.service.SORService;
import com.res.service.UserService;
import com.res.service.WorkAgreementService;
import com.res.service.impl.WorkCCServiceImpl;
import com.res.util.RESUtil;

/**
 * @author Admin
 * Rest Controller for Mentioned users Type i.e. Admin, EE, CE, SE, AE SubE, DirGP
 */
@RestController
@RequestMapping(value = { "/admin/*", "/adminView/*","/ee/*", "/ce/*", "/supdte/*", "/enc/*", "/ae/*", "/sube/*","/sdo/*", "/dirgp/*",
		"/ao/*", "/contractor/*" })
public class CommonController {

	public static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	private User user;

	@Autowired
	private UserService userService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private WorkAgreementService workAgreementService;

	@Autowired
	private WorkCCServiceImpl workCCServiceImpl;
	
	@Autowired
	private SORService sorService;
	
	@Autowired
	private InspectionDetailsRepository inspectionDetailsRepository;
	
	@Autowired
	private InspectionDetailsRepository inspectionDetailsRepo;
	
	@Autowired
	private SqmAllocationRepository allocationRepository;
	

	@Value("${applicationDeploymentServerName}")
	private String applicationDeploymentServerName;

	RestTemplate restTemplate = new RestTemplate();
	/**
	 * @param request
	 * @return ModelAndView
	 * Change password method.
	 */
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public ModelAndView viewChangePasswordForm(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Change password page");

		ModelAndView modelAndView = new ModelAndView("common/changepassword");
		return modelAndView;

	}

	@RequestMapping(value = "/dochangepassword", method = RequestMethod.POST)
	public ResponseObject changePassword(@RequestBody ChangePasswordBean changePassword, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Changing password");

		ResponseObject response = new ResponseObject();

		User user = RESUtil.getUserDetail();

		Users userEntity = userService.findByUserName(user.getUsername());

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (!StringUtils.isEmpty(changePassword.getCurrentPassword())
				&& !passwordEncoder.matches(changePassword.getCurrentPassword(), userEntity.getPassword())) {

			response.setErrorMessage("Current password is not valid.");
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Current password is not valid.");
			return response;
		}

		if ((!StringUtils.isEmpty(changePassword.getPassword())
				&& !StringUtils.isEmpty(changePassword.getConfirmPassword()))
				&& (!changePassword.getPassword().equals(changePassword.getConfirmPassword()))) {

			response.setErrorMessage("New password and confirm password not matched.");
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - New password and confirm password not matched.");
			return response;
		}

		changePassword.setPassword(passwordEncoder.encode(changePassword.getPassword()));
		userService.changePassword(changePassword, user.getUsername());
		response.setSuccessMessage("You have successfully changed the password.");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - You have successfully changed the password.");
		return response;

	}
	
	
	 // Java Spring Controller for checking password expiry and sending response
 	@RequestMapping(value = "/verifyUserPasswordExpiry", method = RequestMethod.GET)
 	@ResponseBody
 	public ResponseEntity<ResponseObject> verifyUserPasswordExpiry() {
 	    ResponseObject response = new ResponseObject(); 
 	   user = RESUtil.getUserDetail();
 	  //  User user = getUserDetail();
 		Users userInfo = userService.findByUserName(user.getUsername());
 	 //   Users userInfo = commonService.findUserById(user.getUsername());
 		  String role = SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();   // e.g. ROLE_ADMIN, ROLE_USER

 	    // Check if the user has no password or last password updated date is null
 	    if (userInfo == null || userInfo.getLastPasswordUpdatedOn() == null) {
 	        response.setSuccessMessage("Your password has expired. Please Update your Password");
 	       response.setRole(role);
 	        return new ResponseEntity<>(response, HttpStatus.OK);
 	    }
 	    
 	    // Get the last password update date
 	    Date lastPasswordUpdated = userInfo.getLastPasswordUpdatedOn();
 	    
 	    // Set expiration period (e.g., 31 days)
 	    int expirationPeriod = 31; // Password expires after 31 days
 	    Calendar calendar = Calendar.getInstance();
 	    calendar.setTime(lastPasswordUpdated);
 	    calendar.add(Calendar.DAY_OF_YEAR, expirationPeriod); // Add expiration period to the last updated date
 	    Date passwordExpiryDate = calendar.getTime();
 	    
 	    // Get the current date
 	    Date currentDate = new Date();
 	    
 	    // Calculate the difference in days between the current date and the password expiry date
 	    long diffInMillis = passwordExpiryDate.getTime() - currentDate.getTime();
 	    long remainingDays = diffInMillis / (1000 * 60 * 60 * 24); // Convert milliseconds to days
 	    
 	    // Determine the response message based on password expiration
 	    if (remainingDays <= 0) {
 	    	 response.setRole(role);
 	        response.setSuccessMessage("Your password has expired. Please Update your Password");
 	    } else {
 	        if (remainingDays <= 3) {
 	        	 response.setRole(role);
 	            response.setSuccessMessage("Your password will expire in " + remainingDays + " days. Do you want to update the password?");
 	        } else {
 	        	 response.setRole(role);
 	            // Password is still valid and not close to expiration
 	            response.setSuccessMessage("Your password is still valid.");
 	            return new ResponseEntity<>(response, HttpStatus.OK); // Return 200 OK for valid password
 	        }
 	    }
 	    
 	    // Return the response with the appropriate success message
 	    return new ResponseEntity<>(response, HttpStatus.OK);
 	}

	@RequestMapping(value = "/validateCurrentPassword", method = RequestMethod.POST)
	public ResponseObject validateCurrentPassword(@RequestBody ChangePasswordBean currentpassword) {

	    ResponseObject response = new ResponseObject();

	    User user = RESUtil.getUserDetail();
	    Users userEntity = userService.findByUserName(user.getUsername());

	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	    // Wrong current password
	    if (!passwordEncoder.matches(currentpassword.getCurrentPassword(), userEntity.getPassword())) {
	        response.setErrorMessage("INVALID_CURRENT_PASSWORD");
	        return response;
	    }

	    //  Correct current password
	    response.setSuccessMessage("VALID_CURRENT_PASSWORD");
	    return response;
	}
	
	@RequestMapping(value = "/userchangepassword", method = RequestMethod.GET)
	public ModelAndView viewUserChangePasswordForm(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Change password page");

		ModelAndView modelAndView = new ModelAndView("common/userchangepassword");
		return modelAndView;

	}
	

	// commented for security audit
	/*
	 * @RequestMapping(value = "/logout", method = RequestMethod.GET) public String
	 * logoutPage(HttpServletRequest request, HttpServletResponse response) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Logout");
	 * 
	 * Authentication auth = SecurityContextHolder.getContext()
	 * .getAuthentication(); if (auth != null) { new
	 * SecurityContextLogoutHandler().logout(request, response, auth); } return
	 * "redirect:/login?logout"; }
	 */

	@RequestMapping(value = "fetchDesignations", method = RequestMethod.GET)
	public List<DesignationBean> fetchDesignations(HttpServletRequest request) {
		return commonService.fetchDesignations();
	}
	
	@RequestMapping(value = "fetchDesignationsType", method = RequestMethod.GET)
	public List<DesignationBean> fetchDesignationsType(HttpServletRequest request) {
		return commonService.fetchDesignationsType();
	}

	@RequestMapping(value = "fetchOfficeTypes", method = RequestMethod.GET)
	public List<OfficeTypeBean> fetchOfficeTypes(HttpServletRequest request) {
		return commonService.fetchOfficeTypes();
	}

	@RequestMapping(value = "fetchOfficesByOfficeType/{officeTypeId}", method = RequestMethod.GET)
	public List<OfficeBean> fetchOfficesByOfficeType(HttpServletRequest request, @PathVariable Long officeTypeId) {
		return commonService.fetchOfficesByOfficeType(officeTypeId);
	}
	//Rakesh
	@RequestMapping(value = "fetchOfficesByOfficeTypeAndSqmUser/{officeTypeId}/{id}", method = RequestMethod.GET)
	public List<OfficeBean> fetchOfficesByOfficeTypeAndSqmUser(HttpServletRequest request, @PathVariable Long officeTypeId, @PathVariable Long id) {
		return commonService.fetchOfficesByOfficeTypeAndSqmUser(officeTypeId,id);
	}
	
   @RequestMapping(value = "fetchOfficesBySeOfficeIdAndSqmUser/{id}", method = RequestMethod.GET)
   public List<OfficeBean> fetchOfficesBySeOfficeIdAndSqmUser(HttpServletRequest request, @PathVariable Long id) {
	   UserBean userDetail = fetchLoggedInUserDetails(request);

		Long officeId = userDetail.getOfficeId();
			return commonService.fetchOfficesBySeOfficeIdAndSqmUser(officeId,id);
	}

	@RequestMapping(value = "fetchStates", method = RequestMethod.GET)
	public List<StateBean> fetchStates(HttpServletRequest request) {
		return commonService.fetchStates();
	}

	@RequestMapping(value = "fetchDistrictsByState/{stateId}", method = RequestMethod.GET)
	public List<DistrictBean> fetchDistrictsByState(@PathVariable Long stateId, HttpServletRequest request) {
		return commonService.fetchDistrictsByState(stateId);
	}

	@RequestMapping(value = "fetchDistricts", method = RequestMethod.GET)
	public List<DistrictBean> fetchDistricts(HttpServletRequest request) {
		return commonService.fetchDistricts();
	}

	@RequestMapping(value = "fetchDistrictsOfMP", method = RequestMethod.GET)
	public List<DistrictBean> fetchDistrictsOfMP(HttpServletRequest request) {
		return commonService.fetchDistrictsByState(commonService.fetchStateIdOfMP());
	}

	@RequestMapping(value = "fetchStateIdOfMP", method = RequestMethod.GET)
	public Long fetchStateIdOfMP(HttpServletRequest request) {
		return commonService.fetchStateIdOfMP();
	}

	@RequestMapping(value = "fetchBlocksByDistrict/{districtId}", method = RequestMethod.GET)
	public List<BlockBean> fetchBlocksByDistrict(@PathVariable Long districtId, HttpServletRequest request) {
		return commonService.fetchBlocksByDistrict(districtId);
	}
	
	@RequestMapping(value = "/viewLegacyDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewUserForm(
			@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Legacy Form");
		ModelAndView modelAndView = new ModelAndView("ee/viewLegacyDataForm");
		return modelAndView;
	}

	@RequestMapping(value = "fetchWorkType", method = RequestMethod.GET)
	public List<WorkTypeBean> fetchWorkType(HttpServletRequest request) {
		return commonService.fetchWorkType();
	}
	@RequestMapping(value = "fetchWorkNature", method = RequestMethod.GET)
	public List<WorkNatureBean> fetchWorkNature(HttpServletRequest request) {
		return commonService.fetchWorkNature();
	}
	
	@RequestMapping(value = "fetchNameOfSqm", method = RequestMethod.GET)
	public List<UserBean> fetchNameOfSqm(HttpServletRequest request) {
		return commonService.fetchNameOfSqm();
	}
	
	@RequestMapping(value = "fetchNameOfOfficers", method = RequestMethod.GET)
	public List<UserBean> fetchNameOfOfficers(HttpServletRequest request) {
		return commonService.fetchNameOfOfficers();
	}

	@RequestMapping(value = "fetchWorkSubTypeByWorkTypeId/{workTypeId}", method = RequestMethod.GET)
	public List<WorkSubTypeBean> fetchWorkSubTypeByWorkTypeId(@PathVariable Long workTypeId,
			HttpServletRequest request) {
		return commonService.fetchWorkSubTypeByWorkTypeId(workTypeId);
	}

	@RequestMapping(value = "fetchLineDepartment", method = RequestMethod.GET)
	public List<LineDepartmentBean> fetchLineDepartment(HttpServletRequest request) {
		return commonService.fetchLineDepartment();
	}

	@RequestMapping(value = "fetchUserDetailsFromLoggedInUserName", method = RequestMethod.GET)
	public WorkBean fetchUserDetails(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		WorkBean bean = new WorkBean();
		bean.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		return bean;
	}

	@RequestMapping(value = "fetchLoggedInUserDetails", method = RequestMethod.GET)
	public UserBean fetchLoggedInUserDetails(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
			return userService.fetchUserDetailsByUserName(user.getUsername());
		} else {
			return null;
		}
	}

	@RequestMapping(value = "fetchEngineersByOfficeId/{officeId}", method = RequestMethod.GET)
	public List<UserBean> fetchEngineersByOfficeId(@PathVariable Long officeId, HttpServletRequest request) {

		List<UserBean> list1 = commonService.fetchSubEngineerByOfficeId(officeId);
		List<UserBean> list2 = commonService.fetchAssistantEngineerByOfficeId(officeId);
		List<UserBean> list3 = commonService.fetchExecutiveEngineerByOfficeId(officeId);
		List<UserBean> list4 = commonService.fetchSubDivisionalOfficerByOfficeId(officeId);
		list1.addAll(list2);
		list1.addAll(list3);
		list1.addAll(list4);
		return list1;
	}

	@RequestMapping(value = "fetchAeAndSubEngByOfficeId/{officeId}", method = RequestMethod.GET)
	public List<UserBean> fetchAeAndSubEngByOfficeId(@PathVariable Long officeId, HttpServletRequest request) {
		// need Only Assistant Engineer List Instead Of Both For Inspected By For Bill @Nikhil
		List<UserBean> list1 = commonService.fetchAssistantEngineerByOfficeId(officeId);
		/*	List<UserBean> list2 = commonService.fetchSubEngineerByOfficeId(officeId);

		list1.addAll(list2);*/
		return list1;
	}
	
	@RequestMapping(value = "fetchSdoAndSubEngByOfficeId/{officeId}", method = RequestMethod.GET)
	public List<UserBean> fetchSdoAndSubEngByOfficeId(@PathVariable Long officeId, HttpServletRequest request) {
		// need Only Assistant Engineer List Instead Of Both For Inspected By For Bill @Nikhil
		List<UserBean> list1 = commonService.fetchSubdivisionalOfficersByOfficeId(officeId);
		/*	List<UserBean> list2 = commonService.fetchSubEngineerByOfficeId(officeId);

		list1.addAll(list2);*/
		return list1;
	}

	@RequestMapping(value = "fetchSubEngAndAeByOfficeId/{officeId}", method = RequestMethod.GET)
	public List<UserBean> fetchSubEngAndAeByOfficeId(@PathVariable Long officeId, HttpServletRequest request) {
		// need Only Sub Engineer List Instead Of Both For Measured By For Bill @Nikhil
		List<UserBean> list1 = commonService.fetchSubEngineerByOfficeId(officeId);
		/*List<UserBean> list2 = commonService.fetchAssistantEngineerByOfficeId(officeId);*/

		/*list1.addAll(list2);*/
		return list1;
	}

	@RequestMapping(value = "fetchExecutiveEngineersByOfficeId/{officeId}", method = RequestMethod.GET)
	public List<UserBean> fetchExecutiveEngineersByOfficeId(@PathVariable Long officeId, HttpServletRequest request) {
		// that List is for When Bill is Final then inspected by EE For Bill @Nikhil
		List<UserBean> list1 = commonService.fetchExecutiveEngineerByOfficeId(officeId);
		return list1;
	}

	@RequestMapping(value = "fetchAssistantEngineerByOfficeId/{officeId}", method = RequestMethod.GET)
	public List<UserBean> fetchAssistantEngineerByOfficeId(@PathVariable Long officeId, HttpServletRequest request) {
		return commonService.fetchAssistantEngineerByOfficeId(officeId);
	}
	
	@RequestMapping(value = "fetchSubDivisionOfficerByOfficeId/{officeId}", method = RequestMethod.GET)
	public List<UserBean> fetchSubDivisionOfficerByOfficeId(@PathVariable Long officeId, HttpServletRequest request) {
		return commonService.fetchSubdivisionalOfficersByOfficeId(officeId);
	}
	

	
	@RequestMapping(value = "fetchUsersForOfficeIdAndDesg/{officeId}", method = RequestMethod.GET)
	public List<UserBean> fetchUsersForOfficeIdAndDesg(@PathVariable Long officeId, HttpServletRequest request) {
		return commonService.fetchUsersForOfficeIdAndDesg(officeId);
	}

	@RequestMapping(value = "fetchDistrictsOfMPNew", method = RequestMethod.GET)
	public List<DistrictBean> fetchDistrictsOfMPNew(HttpServletRequest request) {
		return commonService.fetchMPDistricts();
	}
	@RequestMapping(value = "fetchExeOffices", method = RequestMethod.GET)
	public List<OfficeBean> fetchfetchExeOffices(HttpServletRequest request) {
		return commonService.fetchExeOffices();
	}
	
	@RequestMapping(value = "fetchExeOfficesForOffices", method = RequestMethod.GET)
	public List<OfficeBean> fetchfetchExeOfficesSupdt(HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		UserBean userDetail =  userService.fetchUserDetailsByUserName(user.getUsername());
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		return commonService.fetchExeOfficesForOffices(office,loggedInUserRole);
	}
	
	@RequestMapping(value = "fetchSupdtOffices", method = RequestMethod.GET)
	public List<OfficeBean> fetchSupdtOffices(HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		UserBean userDetail =  userService.fetchUserDetailsByUserName(user.getUsername());
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		return commonService.fetchSupdtOffices(office,loggedInUserRole);
	}

	@RequestMapping(value = "fetchBlocksByDistrictNew/{districtId}", method = RequestMethod.GET)
	public List<BlockBean> fetchBlocksByDistrictNew(@PathVariable Long districtId, HttpServletRequest request) {
		return commonService.fetchBlocksByDistrictNew(districtId);
	}

	@RequestMapping(value = "fetchAgencyType", method = RequestMethod.GET)
	public List<AgencyTypeBean> fetchAgencyType(HttpServletRequest request) {
		return commonService.fetchAgencyType();
	}

	@RequestMapping(value = "fetchPhysicalStageType", method = RequestMethod.GET)
	public List<PhysicalStageTypeBean> fetchPhysicalStageType(HttpServletRequest request) {
		return commonService.fetchPhysicalStageType();
	}

	@RequestMapping(value = "fetchTechnicalSanctionType", method = RequestMethod.GET)
	public List<TechnicalSanctionTypeBean> fetchTechnicalSanctionType(HttpServletRequest request) {
		return commonService.fetchTechnicalSanctionType();
	}

	@RequestMapping(value = "fetchAdministrationSanctionType", method = RequestMethod.GET)
	public List<AdministrationSanctionTypeBean> fetchAdministrationSanctionType(HttpServletRequest request) {
		return commonService.fetchAdministrationSanctionType();
	}

	@RequestMapping(value = "fetchIssuingAuthority", method = RequestMethod.GET)
	public List<IssuingAuthorityBean> fetchIssuingAuthority(HttpServletRequest request) {
		return commonService.fetchIssuingAuthority();
	}

	@RequestMapping(value = "fetchGramPanchayatByBlockCode/{blockId}", method = RequestMethod.GET)
	public List<GramPanchayatBean> fetchGramPanchayatByBlockCode(@PathVariable Long blockId,
			HttpServletRequest request) {
		return commonService.fetchGramPanchayatByBlockCode(blockId);
	}

	@RequestMapping(value = "fetchVillageByGramPanchayatCode/{gramPanchayatId}", method = RequestMethod.GET)
	public List<VillageBean> fetchVillageByGramPanchayatCode(@PathVariable Long gramPanchayatId,
			HttpServletRequest request) {
		return commonService.fetchVillageByGramPanchayatCode(gramPanchayatId);
	}

	@RequestMapping(value = "fetchWorkStatusType", method = RequestMethod.GET)
	public List<WorkStatusBean> fetchWorkStatusType(HttpServletRequest request) {
		return commonService.fetchWorkStatus();
	}

	@RequestMapping(value = "fetchPhysicalStageByWorkTypeId/{workTypeId}", method = RequestMethod.GET)
	public List<PhysicalStageTypeBean> fetchPhysicalStageByWorkTypeId(@PathVariable Long workTypeId,
			HttpServletRequest request) {
		return commonService.fetchPhysicalStageByWorkTypeId(workTypeId);
	}

	@RequestMapping(value = "fetchSubEngineerByOfficeId/{officeId}", method = RequestMethod.GET)
	public List<UserBean> fetchSubEngineerByOfficeId(@PathVariable Long officeId, HttpServletRequest request) {
		return commonService.fetchSubEngineerByOfficeId(officeId);
	}

	@RequestMapping(value = "fetchContractors", method = RequestMethod.GET)
	public List<ContractorBean> fetchContractors(HttpServletRequest request) {
		return commonService.fetchContractors();
	}

	@RequestMapping(value = "/manageusers", method = RequestMethod.GET)
	public ModelAndView manageUsersView(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Manage Users page");
		ModelAndView modelAndView = new ModelAndView("common/manageUsers");
		return modelAndView;
	}
//Rakesh
	@RequestMapping(value = "/manageSqmUsers", method = RequestMethod.GET)
	public ModelAndView manageSqmUsers(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Manage Users page");
		ModelAndView modelAndView = new ModelAndView("common/manageSqmUsers");
		return modelAndView;
	}
	
	@RequestMapping(value = "/manageInspUsers", method = RequestMethod.GET)
	public ModelAndView manageInspUsers(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Manage Users page");
		ModelAndView modelAndView = new ModelAndView("common/manageInspUsers");
		return modelAndView;
	}
	

	@RequestMapping(value = "/fetchUserList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchUserList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		String designation = request.getParameter("designation");
		String status = request.getParameter("status");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		UserBean userDetail = fetchLoggedInUserDetails(request);

		Long officeId = userDetail.getOfficeId();

		UserJson userJson = userService.getAllUsers(pageable, searchBoxVal, designation, status, officeId);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(userJson);

		return json;
	}
//Rakesh
	@RequestMapping(value = "/fetchSqmUserList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchSqmUserList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		String designation = request.getParameter("designation");
		String status = request.getParameter("status");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		UserBean userDetail = fetchLoggedInUserDetails(request);

		Long officeId = userDetail.getOfficeId();

		UserJson userJson = userService.getAllSqmUsers(pageable, searchBoxVal, designation, status, officeId);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(userJson);

		return json;
	}
	
	@RequestMapping(value = "fetchRoles", method = RequestMethod.GET)
	public List<RoleBean> fetchRoles(HttpServletRequest request) {
		return userService.fetchRoles();
	}

	@RequestMapping(value = "/editUserForm/{id}", method = RequestMethod.GET)
	public ModelAndView viewEditUserForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit User Form");
		ModelAndView modelAndView = new ModelAndView("common/editUserForm");
		return modelAndView;
	}
	//Rakesh
	@RequestMapping(value = "/editSqmUserForm/{id}", method = RequestMethod.GET)
	public ModelAndView editSqmUserForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit User Form");
		ModelAndView modelAndView = new ModelAndView("common/editSqmUserForm");
		return modelAndView;
	}

	//Rakesh
	@RequestMapping(value = "/addSqmUserForm", method = RequestMethod.GET)
	public ModelAndView addSqmUserForm(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit User Form");
		ModelAndView modelAndView = new ModelAndView("common/addSqmUserForm");
		return modelAndView;
	}
	@RequestMapping(value = "/addOffInspForm", method = RequestMethod.GET)
	public ModelAndView addOffInspForm(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Officer Inspection Form");
		ModelAndView modelAndView = new ModelAndView("common/addOffInspForm");
		return modelAndView;
	}
	
	//Submit Sqm User form
		@RequestMapping(value = "/addUser", method = RequestMethod.POST)
		public ResponseObject addUser(@RequestBody UserBean userBean, HttpServletRequest request) throws Exception {

			user = RESUtil.getUserDetail();
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating User data");
			ResponseObject response = new ResponseObject();
			userBean.setModifiedBy(user.getAuthorities().toString());

			String contextPath = "";
			if (!StringUtils.isEmpty(request.getContextPath())) {
				contextPath = request.getContextPath();
			}

			String websiteURL = request.getScheme() + "://" + getApplicationDeploymentServerName() + ":"
					+ request.getServerPort() + contextPath;

			String errorMsg = userService.addUser(userBean, websiteURL);

			if (errorMsg != null) {
				response.setErrorMessage(errorMsg);
				logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
			} else {
				response.setSuccessMessage("SQM User saved successfully!");
				logger.info("SQM User - " + user.getUsername() + ", Role - " + user.getAuthorities()
						+ " -SQM User saved successfully!");
			}
			return response;
		}
	//submit officer inspection
	@RequestMapping(value = "/addOfficerInsp", method = RequestMethod.POST)
	public ResponseObject addOfficerInsp(@RequestBody UserBean userBean, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating User data");
		ResponseObject response = new ResponseObject();
		userBean.setModifiedBy(user.getAuthorities().toString());

		String contextPath = "";
		if (!StringUtils.isEmpty(request.getContextPath())) {
			contextPath = request.getContextPath();
		}

		String websiteURL = request.getScheme() + "://" + getApplicationDeploymentServerName() + ":"
				+ request.getServerPort() + contextPath;

		String errorMsg = commonService.addOfficerInsp(userBean, websiteURL);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("SQM User saved successfully!");
			logger.info("SQM User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " -SQM User saved successfully!");
		}
		return response;
	}
	
	//Update Officer Insp
			@RequestMapping(value = "/updateOfficerInsp", method = RequestMethod.POST)
			public ResponseObject updateOfficerInsp(@RequestBody UserBean userBean, HttpServletRequest request) throws Exception {

				user = RESUtil.getUserDetail();
				logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating User data");
				ResponseObject response = new ResponseObject();
				userBean.setModifiedBy(user.getAuthorities().toString());

		
				String contextPath = "";
				if (!StringUtils.isEmpty(request.getContextPath())) {
					contextPath = request.getContextPath();
				}

				String websiteURL = request.getScheme() + "://" + getApplicationDeploymentServerName() + ":"
						+ request.getServerPort() + contextPath;

				String errorMsg = userService.updateOfficerInsp(userBean, websiteURL);

				if (errorMsg != null) {
					response.setErrorMessage(errorMsg);
					logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
				} else {
					response.setSuccessMessage("SQM User updated successfully!");
					logger.info("SQM User - " + user.getUsername() + ", Role - " + user.getAuthorities()
							+ " -SQM User updated successfully!");
				}
				return response;
			}
	
	//Update Sqm User form
		@RequestMapping(value = "/updateSqmUser", method = RequestMethod.POST)
		public ResponseObject updateSqmUser(@RequestBody UserBean userBean, HttpServletRequest request) throws Exception {

			user = RESUtil.getUserDetail();
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating User data");
			ResponseObject response = new ResponseObject();
			userBean.setModifiedBy(user.getAuthorities().toString());

	
			String contextPath = "";
			if (!StringUtils.isEmpty(request.getContextPath())) {
				contextPath = request.getContextPath();
			}

			String websiteURL = request.getScheme() + "://" + getApplicationDeploymentServerName() + ":"
					+ request.getServerPort() + contextPath;

			String errorMsg = userService.updateSqmUser(userBean, websiteURL);

			if (errorMsg != null) {
				response.setErrorMessage(errorMsg);
				logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
			} else {
				response.setSuccessMessage("SQM User updated successfully!");
				logger.info("SQM User - " + user.getUsername() + ", Role - " + user.getAuthorities()
						+ " -SQM User updated successfully!");
			}
			return response;
		}
	@RequestMapping(value = "fetchUserDetails/{id}", method = RequestMethod.GET)
	public UserBean fetchUserDetails(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		return userService.fetchUserDetails(id);
	}

	//Rakesh
	@RequestMapping(value = "fetchSqmUserDetails/{id}", method = RequestMethod.GET)
	public UserBean fetchSqmUserDetails(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		return userService.fetchSqmUserDetails(id);
	}
	//Rakesh
	@RequestMapping(value = "checkSqmUserDetailsByEmailId/{email:.+}", method = RequestMethod.GET)
	public boolean checkSqmUserDetailsByEmailId(@PathVariable String email, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		return userService.checkSqmUserDetailByEmailId(email);
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public ResponseObject editUser(@RequestBody UserBean userBean, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating User data");
		ResponseObject response = new ResponseObject();

		String contextPath = "";
		if (!StringUtils.isEmpty(request.getContextPath())) {
			contextPath = request.getContextPath();
		}

		String websiteURL = request.getScheme() + "://" + getApplicationDeploymentServerName() + ":"
				+ request.getServerPort() + contextPath;

		String errorMsg = userService.editUser(userBean, websiteURL);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("User updated successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - User updated successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/checkIsOICByOfficeId/{officeId}", method = RequestMethod.GET)
	public boolean checkIsOICByOfficeId(@PathVariable Long officeId, HttpServletRequest request) throws Exception {

		boolean isOIC = userService.checkIsOICByOfficeId(officeId);

		return isOIC;
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public ResponseObject deleteUser(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Deleting User");
		ResponseObject response = new ResponseObject();

		String errorMsg = userService.deleteUser(id);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("User deleted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - User deleted successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/viewBills", method = RequestMethod.GET)
	public ModelAndView viewBills(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying View Bills page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {
			modelAndView = new ModelAndView("common/viewBillsEE");
		}

		else if (loggedInUserRole.equals(RESConstants.ROLE_AE)|| loggedInUserRole.equals(RESConstants.ROLE_SDO)) {
			modelAndView = new ModelAndView("common/viewBills");
		}
		else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {
			modelAndView = new ModelAndView("common/viewBillsSub");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/viewPayments", method = RequestMethod.GET)
	public ModelAndView viewPayments(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying View Payments page");
		ModelAndView modelAndView = new ModelAndView("common/viewPayments");
		return modelAndView;
	}

	@RequestMapping(value = "/fetchBills1", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBills1(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bills 1");

		String searchBoxVal = request.getParameter("searchBoxVal");
		String billDateFrom = request.getParameter("billDateFrom");
		String billDateTo = request.getParameter("billDateTo");
		String status = request.getParameter("status");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BillJson billJson = commonService.getAllBills1(pageable, searchBoxVal, billDateFrom, billDateTo, status, role,
				user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(billJson);

		return json;
	}
	
	
	
	/*@RequestMapping(value = "fetchWorksByName", method = RequestMethod.GET)
	public List<WorkBean> fetchWorksByName(
			@RequestParam(value = "searchBoxVal", required = true) String searchBoxVal,
			HttpServletRequest request) {

<<<<<<< .mine
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Work List By Name");

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		return commonService.getAllWorksByName(searchBoxVal, role,
				user.getUsername());
	}*/

	@RequestMapping(value = "/fetchWorksByName", method = RequestMethod.GET)
	public String fetchWorksByName(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Work List By Name");
		
		String searchBoxVal = request.getParameter("searchBoxVal");
		
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength);

	
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		
		
		
		WorkJson workJson =  commonService.getAllWorksByNameP(pageable,searchBoxVal, role,
				user.getUsername());
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	

	@RequestMapping(value = "/fetchBills", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBills(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bills");

		String searchBoxVal = request.getParameter("searchBoxVal");
		String billDateFrom = request.getParameter("billDateFrom");
		String billDateTo = request.getParameter("billDateTo");
		String status = request.getParameter("status");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BillJson billJson = commonService.getAllBills(pageable, searchBoxVal, billDateFrom, billDateTo, status, role,
				user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(billJson);

		return json;
	}
	
	@RequestMapping(value = "/fetchBillsContractor", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBillsContractor(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bills");

		String searchBoxVal = request.getParameter("searchBoxVal");
		String billDateFrom = request.getParameter("billDateFrom");
		String billDateTo = request.getParameter("billDateTo");
		String status = request.getParameter("status");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BillJson billJson = commonService.getAllBills(pageable, searchBoxVal, billDateFrom, billDateTo, status, role,
				user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(billJson);

		return json;
	}

	@RequestMapping(value = "/fetchInspectionBills", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchInspectionBills(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bills");

		String searchBoxVal = request.getParameter("searchBoxVal");
		String billDateFrom = request.getParameter("billDateFrom");
		String billDateTo = request.getParameter("billDateTo");
		String status = request.getParameter("status");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BillJson billJson = commonService.fetchInspectionBills(pageable, searchBoxVal, billDateFrom, billDateTo, status,
				role, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(billJson);

		return json;
	}

	@RequestMapping(value = "/searchWorkForBill", method = RequestMethod.GET)
	public ModelAndView searchWorkForBill(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Search Work For Bill page");
		ModelAndView modelAndView = new ModelAndView("common/searchWorkForBill");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		modelAndView.addObject("loggedInUserRole", loggedInUserRole);
		
		return modelAndView;
	}


	

	/*@RequestMapping(value = "fetchWorksByName", method = RequestMethod.GET)
	public List<WorkBean> fetchWorksByName(@RequestParam(value = "searchBoxVal", required = true) String searchBoxVal,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Work List By Name");

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		return commonService.getAllWorksByName(searchBoxVal, role,
				user.getUsername());
	


		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List By Name");

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		return commonService.getAllWorksByName(searchBoxVal, role, user.getUsername());
	}*/
	
	


	@RequestMapping(value = "/addNewBill/{id}", method = RequestMethod.GET)
	public ModelAndView addNewBill(@PathVariable Long id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add New Bill Form");

		ModelAndView modelAndView = null;
		WorkBean bean = commonService.fetchWorkDetails(id);

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {

			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/addNewBillFormEE");
			} else {
				modelAndView = new ModelAndView("common/addNewBillForEstimationEE");
			}

		}

		else if (loggedInUserRole.equals(RESConstants.ROLE_AE) 
				|| loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)||
				loggedInUserRole.equals(RESConstants.ROLE_SDO)) {

			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/addNewBillForm");
			} else {
				modelAndView = new ModelAndView("common/addNewBillForEstimation");
			}
		}
		
		else if (loggedInUserRole.equals(RESConstants.ROLE_CONTRACTOR) ) {

			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/addNewBillFormContractor");
			} else {
				modelAndView = new ModelAndView("common/addNewBillForEstimationContractor");
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "fetchWorkDetails/{id}", method = RequestMethod.GET)
	public WorkBean fetchWorkDetails(@PathVariable Long id, HttpServletRequest request) {
try {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id");
		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkBean workDetails = commonService.fetchWorkDetails(id);

		workDetails.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		Users users = userService.findByUserName(user.getUsername());

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		workDetails.setLoggedInUserRole(role);
			
		if (role.equals(RESConstants.ROLE_EE)) {

			String anukramNo = null;
			anukramNo = "कार्यपालन यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String anukramNo = null;
			anukramNo = "मुख्य अभियंता, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_AE) ) {

			String anukramNo = null;
			anukramNo = "Assistant Engineer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SDO)) {

			String anukramNo = null;
			anukramNo = "Sub-Divisional Officer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String anukramNo = null;
			anukramNo = "अधीक्षण यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_EE)) {

			String yantriName = null;
			yantriName = "कार्यपालन यंत्री ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String yantriName = null;
			yantriName = "मुख्य अभियंता ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String yantriName = null;
			yantriName = "अधीक्षण यंत्री ";
			workDetails.setYantriName(yantriName);

		}

		return workDetails;
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	@RequestMapping(value = "fetchWorkDetailsHistoryLegacy/{workLoggingId}", method = RequestMethod.GET)
	public WorkLoggingBean fetchWorkDetailsHistoryLegacy(@PathVariable Long workLoggingId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id");
		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkLoggingBean workDetails = commonService.fetchWorkDetailsHistoryLegacy(workLoggingId);

		workDetails.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		Users users = userService.findByUserName(user.getUsername());

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		workDetails.setLoggedInUserRole(role);

		if (role.equals(RESConstants.ROLE_EE)) {

			String anukramNo = null;
			anukramNo = "कार्यपालन यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String anukramNo = null;
			anukramNo = "मुख्य अभियंता, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_AE)) {

			String anukramNo = null;
			anukramNo = "Assistant Engineer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SDO)) {

			String anukramNo = null;
			anukramNo = "Sub-Divisional Officer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String anukramNo = null;
			anukramNo = "अधीक्षण यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_EE)) {

			String yantriName = null;
			yantriName = "कार्यपालन यंत्री ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String yantriName = null;
			yantriName = "मुख्य अभियंता ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String yantriName = null;
			yantriName = "अधीक्षण यंत्री ";
			workDetails.setYantriName(yantriName);

		}

		return workDetails;
	}
	
	

	// Rakesh working
	@RequestMapping(value = "fetchWorkDetailsByTender/{id}/{tenderId}", method = RequestMethod.GET)
	public WorkBean fetchWorkDetailsByTender(@PathVariable Long id, @PathVariable Long tenderId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id");
		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		Users users = userService.findByUserName(user.getUsername());
		WorkBean workDetails = commonService.fetchWorkDetailsByTender(id, tenderId, users.getOffice().getId());
		
		workDetails.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		workDetails.setLoggedInUserRole(role);

		if (role.equals(RESConstants.ROLE_EE)) {

			String anukramNo = null;
			anukramNo = "कार्यपालन यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String anukramNo = null;
			anukramNo = "मुख्य अभियंता, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_AE)) {

			String anukramNo = null;
			anukramNo = "Assistant Engineer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SDO)) {

			String anukramNo = null;
			anukramNo = "Sub-Divisional Officer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String anukramNo = null;
			anukramNo = "अधीक्षण यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_EE)) {

			String yantriName = null;
			yantriName = "कार्यपालन यंत्री ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String yantriName = null;
			yantriName = "मुख्य अभियंता ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String yantriName = null;
			yantriName = "अधीक्षण यंत्री ";
			workDetails.setYantriName(yantriName);

		}

		return workDetails;
	}

	// Rakesh
	@RequestMapping(value = "/fetchWorkDetailsDistrics", method = RequestMethod.POST)
	public List<DistrictBean> fetchWorkDetailsDistrics(@RequestBody List<DistrictBean> districtBeans,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - fwdForPayment called ");
		ResponseObject response = new ResponseObject();
		for (Iterator iterator = districtBeans.iterator(); iterator.hasNext();) {
			DistrictBean districtBean = (DistrictBean) iterator.next();
//			

		}
		// commonService.fetchAllWorkByDisticts(districtBeans);
		List<DistrictBean> list=	commonService.fetchAllWorkByDisticts(districtBeans);
		//response.setSuccessMessage("Bill Rejected!");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - list "+list);

		return  list;

	}

	//Rakesh
	@RequestMapping(value = "/fetchWorkDetailsByEEOfficeId", method = RequestMethod.GET)
	@ResponseBody
	public List<WorkBean> fetchWorkDetailsByEEOfficeId(@RequestParam(name = "officeIdList") String officeIdList,
			HttpServletRequest request) {
         String[] arr=officeIdList.split(",");
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - fwdForPayment called ");
		//ResponseObject response = new ResponseObject();
		for (String id:arr) {
			//DistrictBean districtBean = (DistrictBean) iterator.next();
//			

		}
		List<WorkBean> list=	 commonService.fetchAllWorkByEEOfficeId(officeIdList);
		System.err.println(list.size());
	//	List<DistrictBean> list=	commonService.fetchAllWorkByDisticts(districtBeans);
		//response.setSuccessMessage("Bill Rejected!");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - list "+list);

		return  list;

	}
	//in edit code
	@RequestMapping(value = "/fetchWorkDetailsByUserIdInSqmEdit", method = RequestMethod.GET)
	@ResponseBody
	public List<WorkBean> fetchWorkDetailsByUserIdInSqmEdit(@RequestParam(name = "userId") Long userId,
			HttpServletRequest request) {
       //  String[] arr=officeIdList.split(",");
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - fwdForPayment called ");
		//ResponseObject response = new ResponseObject();
		
		List<WorkBean> list=	 commonService.fetchAllWorkByUserIdInSqmEdit(userId);
	//	List<DistrictBean> list=	commonService.fetchAllWorkByDisticts(districtBeans);
		//response.setSuccessMessage("Bill Rejected!");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - list "+list);

		return  list;

	}
	
	@RequestMapping(value = "/fetchWorkDetailsByUserIdInOfficersEdit", method = RequestMethod.GET)
	@ResponseBody
	public List<WorkBean> fetchWorkDetailsByUserIdInOfficersEdit(@RequestParam(name = "userId") Long userId,
			HttpServletRequest request) {
       //  String[] arr=officeIdList.split(",");
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - fwdForPayment called ");
		//ResponseObject response = new ResponseObject();
		
		List<WorkBean> list=	 commonService.fetchAllWorkByUserIdInOfficersEdit(userId);
	//	List<DistrictBean> list=	commonService.fetchAllWorkByDisticts(districtBeans);
		//response.setSuccessMessage("Bill Rejected!");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - list "+list);

		return  list;

	}
//	Richa working on 
	@RequestMapping(value = "fetchFullDetailsForWork/{id}", method = RequestMethod.GET)
	public WorkBean fetchFullDetailsForWork(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id");
		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkBean workDetails = commonService.fetchFullDetailsForWork(id);

		workDetails.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		Users users = userService.findByUserName(user.getUsername());

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		workDetails.setLoggedInUserRole(role);

		return workDetails;
	}

	@RequestMapping(value = "fetchWorkDetailsH/{estimationId}", method = RequestMethod.GET)
	public WorkEstimationBean fetchWorkDetailsH(@PathVariable Long estimationId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id");
		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkEstimationBean workDetails = commonService.fetchWorkDetailsH(estimationId);

		Users users = userService.findByUserName(user.getUsername());

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

//		String des = users.getDesignation().getDesignation();

//		fetchWorkDetails.setLoggedInUserRole(loggedInUserRole);
		return workDetails;
	}

	@RequestMapping(value = "fetchTechnicalDetailsByWorkId/{id}", method = RequestMethod.GET)
	public TechnicalSanctionBean fetchTechnicalDetails(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Technical data by Work Id");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		TechnicalSanctionBean technicalSanctionBean = commonService.fetchTechnicalDetailsByWorkId(id);
		return technicalSanctionBean;
	}

	// nikhil
	@RequestMapping(value = "fetchAdministrativeDetailsByWorkId/{id}", method = RequestMethod.GET)
	public AdministrationSanctionBean fetchAdministrativeDetailsByWorkId(@PathVariable Long id,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Technical data by Work Id");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		AdministrationSanctionBean administrationSanctionBean = commonService.fetchAdministrativeDetailsByWorkId(id);
		return administrationSanctionBean;
	}

	@RequestMapping(value = "fetchWorkDetailsByBillId/{id}", method = RequestMethod.GET)
	public WorkBean fetchWorkDetailsByBillId(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Bill Id");

		WorkBean workbean = commonService.fetchWorkDetailsByBillId(id);
		workbean.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		return workbean;
	}

	@RequestMapping(value = "fetchWorkTemplateItems/{workTypeId}/{workSubTypeId}", method = RequestMethod.GET)
	public List<WorkTemplateBean> fetchWorkTemplateItems(@PathVariable Long workTypeId,
			@PathVariable Long workSubTypeId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work Template Items");
		return commonService.fetchWorkTemplateItems(workTypeId, workSubTypeId);
	}

	@RequestMapping(value = "fetchWorkTemplateItemsForEstimation/{workTypeId}/{templateType}", method = RequestMethod.GET)
	public List<WorkTemplateBean> fetchWorkTemplateItemsForEstimation(@PathVariable Long workTypeId,
			@PathVariable Short templateType,
			@RequestParam(value = "standardTemplateTypeId", required = false) Long standardTemplateTypeId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work Template Items");
		return commonService.fetchWorkTemplateItemsForEstimation(workTypeId, standardTemplateTypeId, templateType);
	}

	@RequestMapping(value = "fetchWorkEstimationDetailsById/{workId}", method = RequestMethod.GET)
	public WorkEstimationBean fetchWorkEstimationDetailsById(@PathVariable Long workId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching fetchWorkEstimationDetailsById");
		return commonService.fetchWorkEstimationDetailsById(workId);
	}

	@RequestMapping(value = "fetchWorkEstimationDetailsByIdH/{estimationId}", method = RequestMethod.GET)
	public WorkEstimationBean fetchWorkEstimationDetailsByIdH(@PathVariable Long estimationId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching fetchWorkEstimationDetailsById");
		return commonService.fetchWorkEstimationDetailsByIdH(estimationId);
	}

	@RequestMapping(value = "fetchLastBillDetails/{id}", method = RequestMethod.GET)
	public BillBean fetchLastBillDetails(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Last Bill data");
		return commonService.fetchLastBillDetails(id);
	}

	@RequestMapping(value = "fetchPrevBillDetailsByBillId/{billId}", method = RequestMethod.GET)
	public BillBean fetchPrevBillDetailsByBillId(@PathVariable Long billId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Last Bill data");
		return commonService.fetchPrevBillDetailsByBillId(billId);
	}

	/** CR-RESOWMS/CR/4-2,3
	 * EE can make 10 % extra payment of the total tender cost. 
	 * It should be less or equal to AS.
	 * @param billBean
	 * @param request
	 * @return ResponseObject
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveBillData", method = RequestMethod.POST)
	public ResponseObject saveBillData(@RequestBody BillBean billBean, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Bill data");
		ResponseObject response = new ResponseObject();
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		String errorMsg = commonService.addBill(billBean,loggedInUserRole);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Bill added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Bill added successfully!");
		}
		return response;
	}
	
	
	@RequestMapping(value = "/saveBillDataContractor", method = RequestMethod.POST)
	public ResponseObject saveBillDataContractor(@RequestBody BillBean billBean, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Bill data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.addBillContractor(billBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Bill added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Bill added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/saveWorkEstimationData", method = RequestMethod.POST)
	public ResponseObject saveWorkEstimationData(@RequestBody WorkEstimationBean workEstimationBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Adding Work Estimation data");
		ResponseObject response = new ResponseObject();
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		workEstimationBean.setLoggedInUserRole(loggedInUserRole);

		String errorMsg = commonService.addWorkEstimation(workEstimationBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Work Estimation added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Work Estimation added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/editSaveBillData", method = RequestMethod.POST)
	public ResponseObject editSaveBillData(@RequestBody BillBean billBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Edited Bill data");
		ResponseObject response = new ResponseObject();

		billBean.setCreatedBy(user.getUsername());

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		String errorMsg = commonService.editSaveBill(billBean, loggedInUserRole);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Bill edited successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Edited Bill added successfully!");
		}
		return response;
	}
	
	
	@RequestMapping(value = "/editSaveBillDataContractor", method = RequestMethod.POST)
	public ResponseObject editSaveBillDataContractor(@RequestBody BillBean billBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Edited Bill data");
		ResponseObject response = new ResponseObject();

		billBean.setCreatedBy(user.getUsername());

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		String errorMsg = commonService.editSaveBillContractor(billBean, loggedInUserRole);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Bill edited successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Edited Bill added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/deleteBill/{id}", method = RequestMethod.GET)
	public ResponseObject deleteBill(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Deleting Bill");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.deleteBill(id);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Bill deleted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Bill deleted successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/editBill/{id}", method = RequestMethod.GET)
	public ModelAndView editBill(@PathVariable Long id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Bill Form");

		ModelAndView modelAndView = null;

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkBean bean = commonService.fetchWorkDetailsByBillId(id);

		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {

			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/editBillFormEE");
			} else {
				modelAndView = new ModelAndView("common/editBillFormEstimationEE");
			}
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE) || loggedInUserRole.equals(RESConstants.ROLE_SDO)) {

			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/editBillForm");
			} else {
				modelAndView = new ModelAndView("common/editBillFormEstimation");
			}
		}
		
		else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)|| loggedInUserRole.equals(RESConstants.ROLE_SDO)) {

			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/editBillFormSubE");
			} else {
				modelAndView = new ModelAndView("common/editBillFormEstimationSubE");
			}
		}
		
		else if (loggedInUserRole.equals(RESConstants.ROLE_CONTRACTOR)) {

			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/editBillFormContractor");
			} else {
				modelAndView = new ModelAndView("common/editBillFormEstimationContractor");
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/reviseBill/{id}", method = RequestMethod.GET)
	public ModelAndView reviseBill(@PathVariable Long id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - reviseBill Bill Form");

		ModelAndView modelAndView = null;

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkBean bean = commonService.fetchWorkDetailsByBillId(id);

		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {

			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/reviseBillFormEE");
			} else {
				modelAndView = new ModelAndView("common/reviseBillFormEstimationEE");
			}

		}

		return modelAndView;
	}

	@RequestMapping(value = "/fwdForPayment/{id}", method = RequestMethod.POST)
	public ResponseObject fwdForPayment(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - fwdForPayment called ");
		ResponseObject response = new ResponseObject();

		commonService.updateBillStatus(id, RESConstants.STATUS_FWD_FOR_PAYMENT_ID);

		response.setSuccessMessage("Bill forwarded For Payment successfully!");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Bill forwarded For Payment successfully!");

		return response;

	}
	@RequestMapping(value = "/revertToPhysicalInspectionCompleted/{id}", method = RequestMethod.POST)
	public ResponseObject revertToPhysicalInspectionCompleted(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - revertToPhysicalInspectionCompleted called ");
		ResponseObject response = new ResponseObject();

		commonService.updateBillStatus(id, RESConstants.STATUS_PHYSICAL_INSPECTION_COMPLETED_ID);

		response.setSuccessMessage("Reverted Bill Back To Physical Inspection Completed successfully!");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Reverted Bill Back To Physical Inspection Completed  successfully!");

		return response;

	}

	@RequestMapping(value = "/rejectBill", method = RequestMethod.POST)
	public ResponseObject rejectBill(@RequestBody BillBean billBean, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - fwdForPayment called ");
		ResponseObject response = new ResponseObject();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		commonService.rejectBill(billBean,role);

		response.setSuccessMessage("Bill Rejected!");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Bill Rejected!");

		return response;

	}

	@RequestMapping(value = "/viewBill/{id}", method = RequestMethod.GET)
	public ModelAndView viewBill(@PathVariable Long id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Bill Form");

		WorkBean bean = commonService.fetchWorkDetailsByBillId(id);
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;
		
		if (loggedInUserRole.equals(RESConstants.ROLE_CONTRACTOR) ) {
			


			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/viewBillFormContractor");
			} else {
				modelAndView = new ModelAndView("common/viewBillFormEstimationContractor");
			}
		

			
		}
		else {

			if (bean.getIsLegacy() == 1) {
				modelAndView = new ModelAndView("common/viewBillForm");
			} else {
				modelAndView = new ModelAndView("common/viewBillFormEstimation");
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/viewContingency/{id}", method = RequestMethod.GET)
	public ModelAndView viewContingency(@PathVariable String id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Bill Form");

		ModelAndView modelAndView = new ModelAndView("common/viewContingencyForm");
		return modelAndView;
	}

	@RequestMapping(value = "/viewPayment/{id}", method = RequestMethod.GET)
	public ModelAndView viewPayment(@PathVariable String id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Bill Form");

		ModelAndView modelAndView = new ModelAndView("common/viewPaymentForm");
		return modelAndView;
	}

	@RequestMapping(value = "/billPaymentForm/{id}", method = RequestMethod.GET)
	public ModelAndView billPaymentForm(@PathVariable String id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Bill Form");

		ModelAndView modelAndView = new ModelAndView("common/billPaymentForm");
		return modelAndView;
	}

	@RequestMapping(value = "/contingencyForm/{id}", method = RequestMethod.GET)
	public ModelAndView contingencyForm(@PathVariable String id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Bill Form");

		ModelAndView modelAndView = new ModelAndView("common/contingencyForm");
		return modelAndView;
	}

	@RequestMapping(value = "/savePaymentData", method = RequestMethod.POST)
	public ResponseObject savePaymentData(@RequestBody PaymentBean paymentBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Saving payment data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.savePaymentData(paymentBean);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Payment Data saved successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Payment Data saved successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/saveContengencyData", method = RequestMethod.POST)
	public ResponseObject saveContengencyData(@RequestBody ContengencyBean contengencyBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Saving Contengency data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.saveContengencyData(contengencyBean);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Contengency Data saved successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Contengency Data saved successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/printBill/{id}", method = RequestMethod.GET)
	public ModelAndView printBill(@PathVariable Long id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Bill Form");

		WorkBean bean = commonService.fetchWorkDetailsByBillId(id);

		ModelAndView modelAndView = new ModelAndView("common/printBillForm");
		return modelAndView;
	}

	@RequestMapping(value = "/printOriginalBill/{id}", method = RequestMethod.GET)
	public ModelAndView printOriginalBill(@PathVariable String id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Bill Form");

		ModelAndView modelAndView = new ModelAndView("common/printOriginalBillForm");
		return modelAndView;
	}

	@RequestMapping(value = "fetchOriginalBillDetails/{id}", method = RequestMethod.GET)
	public BillLoggingBean fetchOriginalBillDetails(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");

		return commonService.fetchOriginalBillDetails(id);
	}

	@RequestMapping(value = "fetchBillDetails/{id}", method = RequestMethod.GET)
	public BillBean fetchBillDetails(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");
		return commonService.fetchBillDetails(id);
	}

	@RequestMapping(value = "fetchBillListForWork/{workId}", method = RequestMethod.GET)
	public List<BillBean> fetchBillListForWork(@PathVariable Long workId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");
		return commonService.fetchBillListForWork(workId);
	}

	/** CR-RESOWMS/CR/3-1
	 * District filter is not available in WorkFile in admin and admin_view ids, 
	 * and other filters like block, village etc-Create New Office and shift the works.
	 * @param workId
	 * * @param filter
	 * @return List<WorkFileBean>
	 */
	@RequestMapping(value = "fetchFileListForWork/{workId}/{filter}", method = RequestMethod.GET)
	public List<WorkFileBean> fetchFileListForWork(@PathVariable Long workId, @PathVariable String filter,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work File data");

		List<WorkFileBean> workFileBeanList = new ArrayList<WorkFileBean>();
		WorkFileBean workFileBean = new WorkFileBean();

		if (filter.equals("All") || filter.equals("Work Requisition")) {
			WorkBean workDetails = commonService.fetchWorkDetails(workId);
			if (workDetails != null) {
				workFileBean = new WorkFileBean();
				workFileBean.setDate(workDetails.getWorkRequisitionDate());
				workFileBean.setObject(workDetails);
				workFileBean.setObjectType("Work Requisition");
				workFileBeanList.add(workFileBean);
			}
		}

		if (filter.equals("All") || filter.equals("Work Estimation")) {
			for (WorkEstimationBean workEstimationBean : commonService.fetchWorkEstimationsHistoryForWork(workId)) {
				if (workEstimationBean != null) {
					workFileBean = new WorkFileBean();
					workFileBean.setDate(workEstimationBean.getCreatedDateString());
					workFileBean.setObject(workEstimationBean);
					workFileBean.setObjectType("Work Estimation");
					workFileBeanList.add(workFileBean);
				}
			}
		}

		if (filter.equals("All") || filter.equals("Technical Sanction")) {
			for (TechnicalSanctionBean technicalSanctionBean : commonService
					.fetchTechnicalDetailsListByWorkId(workId)) {
				if (technicalSanctionBean != null) {
					workFileBean = new WorkFileBean();
					workFileBean.setDate(technicalSanctionBean.getTechnicalSanctionDate());
					workFileBean.setObject(technicalSanctionBean);
					workFileBean.setObjectType("Technical Sanction");
					workFileBeanList.add(workFileBean);
				}
			}

		}

		if (filter.equals("All") || filter.equals("Administrative Sanction")) {
			for (AdministrationSanctionBean administrationSanctionBean : commonService
					.fetchAdministrativeDetailsListByWorkId(workId)) {
				if (administrationSanctionBean != null) {
					workFileBean = new WorkFileBean();
					workFileBean.setDate(administrationSanctionBean.getAdministrationSanctionDate());
					workFileBean.setObject(administrationSanctionBean);
					workFileBean.setObjectType("Administrative Sanction");
					workFileBeanList.add(workFileBean);
				}
			}
		}

		if (filter.equals("All") || filter.equals("Work Tender and Agreement")) {
			WorkAgreementBean workAgreementBean = workAgreementService.fetchWorkAgreementDetails(workId, false);
			if (workAgreementBean != null) {
				workFileBean = new WorkFileBean();
				workFileBean.setDate(workAgreementBean.getAgreementDate());
				workFileBean.setObject(workAgreementBean);
				workFileBean.setObjectType("Work Agreement");
				workFileBeanList.add(workFileBean);
			}

			WorkTenderBean workTenderBean = commonService.fetchWorkTenderDetailsByWorkId(workId);
			if (workTenderBean != null) {
				workFileBean = new WorkFileBean();
				workFileBean.setDate(workTenderBean.getTenderOpeningDate());
				workFileBean.setObject(workTenderBean);
				workFileBean.setObjectType("Work Tender");
				workFileBeanList.add(workFileBean);
			}
		}

		if (filter.equals("All") || filter.equals("Bills")) {
			for (BillBean billBean : commonService.fetchBillListForWork(workId)) {
				if (billBean != null) {
					workFileBean = new WorkFileBean();
					workFileBean.setDate(billBean.getBillDate());
					workFileBean.setObject(billBean);
					workFileBean.setObjectType("Bills");
					workFileBeanList.add(workFileBean);
				}
			}
		}

		if (filter.equals("All") || filter.equals("Physical Inspection")) {
			for (BillBean billBean : commonService.fetchInspectionBillsByWork(workId)) {
				if (billBean != null) {
					workFileBean = new WorkFileBean();
					workFileBean.setDate(billBean.getBillDate());
					workFileBean.setObject(billBean);
					workFileBean.setObjectType("Physical Inspection");
					workFileBeanList.add(workFileBean);
				}
			}
		}

		if (filter.equals("All") || filter.equals("Final Inspection")) {
			WorkBean workBean = commonService.fetchCCInspectionsByWork(workId);
			if (workBean != null) {
				workFileBean = new WorkFileBean();
				workFileBean.setDate(workBean.getCcInspectionSubmittedOn());
				workFileBean.setObject(workBean);
				workFileBean.setObjectType("Final Inspection");
				workFileBeanList.add(workFileBean);
			}
		}

		if (filter.equals("All") || filter.equals("Completion Certificate Initiated")) {
			CCDetailsBean ccDetailsBean = commonService.fetchInitiatedCCByWork(workId);
			if (ccDetailsBean != null) {
				workFileBean = new WorkFileBean();
				workFileBean.setDate(ccDetailsBean.getCcInitiatedOn());
				workFileBean.setObject(ccDetailsBean);
				workFileBean.setObjectType("Completion Certificate Initiated");
				workFileBeanList.add(workFileBean);
			}
		}

		if (filter.equals("All") || filter.equals("Physical Completion Certificate")) {
			CCDispatchDetailsBean ccDispatchDetailsBean = commonService.fetchPhysicalCCByWork(workId);
			if (ccDispatchDetailsBean != null) {
				workFileBean = new WorkFileBean();
				workFileBean.setDate(ccDispatchDetailsBean.getDispatchDate());
				workFileBean.setObject(ccDispatchDetailsBean);
				workFileBean.setObjectType("Physical Completion Certificate");
				workFileBeanList.add(workFileBean);
			}
		}

		if (filter.equals("All") || filter.equals("Financial Completion Certificate")) {
			CCDispatchDetailsBean ccDispatchDetailsBean = commonService.fetchFinancialCCByWork(workId);
			if (ccDispatchDetailsBean != null) {
				workFileBean = new WorkFileBean();
				workFileBean.setDate(ccDispatchDetailsBean.getDispatchDate());
				workFileBean.setObject(ccDispatchDetailsBean);
				workFileBean.setObjectType("Financial Completion Certificate");
				workFileBeanList.add(workFileBean);
			}
		}

		return workFileBeanList;
	}

	@RequestMapping(value = "fetchBillDeduction/{workId}", method = RequestMethod.GET)
	public DeductionBean fetchBillDeduction(@PathVariable Long workId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");
		return commonService.fetchBillDeduction(workId);
	}

	@RequestMapping(value = "fetchBillDetailsForEdit/{id}", method = RequestMethod.GET)
	public BillBean fetchBillDetailsForEdit(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");

		return commonService.fetchBillDetailsforEdit(id);
	}

	@RequestMapping(value = "fetchBillDetailsForPrint/{id}", method = RequestMethod.GET)
	public BillBean fetchBillDetailsForPrint(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Bill data for print");

		return commonService.fetchBillDetailsforPrint(id);
	}

	@RequestMapping(value = "/viewEeReportsMapping", method = RequestMethod.GET)
	public ModelAndView viewEeReports(HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Executive Offices Report of Work");

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		ModelAndView modelAndView = null;
		if (role.equals(RESConstants.ROLE_EE)) {
			modelAndView = new ModelAndView("common/viewExecutiveWorkReport");
		}

		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			modelAndView = new ModelAndView("common/viewExecutiveWorkReportSuperintending");
		}

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			modelAndView = new ModelAndView("common/viewExecutiveWorkReportAdmin");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/viewEeReportsMappingStatus/{id}", method = RequestMethod.GET)
	public ModelAndView viewEeReportsStatus(@PathVariable Long id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Executive Offices Report of Work");
		
		

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		ModelAndView modelAndView = null;
		if (role.equals(RESConstants.ROLE_EE)) {
			modelAndView = new ModelAndView("common/viewExecutiveWorkReportStatus");
		}

		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			modelAndView = new ModelAndView("common/viewSupdteWorkReportStatus");
		}
		if (role.equals(RESConstants.ROLE_CE)) {
			modelAndView = new ModelAndView("common/viewChiefWorkReportStatus");
		}

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			modelAndView = new ModelAndView("common/viewWorkReportAdminStatus");
		}
		if(null!=modelAndView)
		modelAndView.addObject("id", id);

		return modelAndView;
	}

	@RequestMapping(value = "/fetchExecutiveOfficeWorkReport", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchExecutiveOfficeWorkReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Executive Office Work Report");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		Short isLegacy = Short.parseShort(request.getParameter("isLegacy"));

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = fetchLoggedInUserDetails(request);
		ExecutiveWorkJson workJson = commonService.getAllExecutiveWorks(pageable, role, userBean, isLegacy);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);
		return json;
	}

	@RequestMapping(value = "/viewSupdteReportsMapping", method = RequestMethod.GET)
	public ModelAndView viewSupdteReports(HttpServletRequest request) {
		/*
		 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
		 * ", Role - " + user.getAuthorities() +
		 * " - Displaying Superintending Offices Report of Work");
		 * 
		 * ModelAndView modelAndView = new ModelAndView( "common/viewSupdteWorkReport");
		 * 
		 * return modelAndView;
		 */

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Superintending Offices Report of Work");

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		ModelAndView modelAndView = null;
		/*if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			modelAndView = new ModelAndView("common/viewSupdteWorkReport");
		}*/
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			modelAndView = new ModelAndView("common/viewSupdteWorkReportAdmin");
		}
		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			modelAndView = new ModelAndView("common/viewSupdteWorkReportAdmin");
		}

		return modelAndView;

	}

	@RequestMapping(value = "/fetchSuperintendingOfficeWorkReport", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchSuperintendingOfficeWorkReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Superintending Office Work Report");
		
		String executionAgencyId = request.getParameter("agencyTypeId");
		String exeOfficeId= request.getParameter("exeOfficeId");
		/*String supdtOfficeId= request.getParameter("supdtOfficeId");*/
		String lineDepartmentId= request.getParameter("lineDepartmentId");
		String accountHeadId= request.getParameter("accountHeadId");
		String workStatusId= request.getParameter("workStatusId");
		/*System.err.println("request.getParameter(\"districtId\")="+request.getParameter("districtId"));*/
		
		
		
		String workTypeId= request.getParameter("workTypeId");
		String workSubTypeId = request.getParameter("workSubTypeId");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		Short isLegacy = Short.parseShort(request.getParameter("isLegacy"));

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = fetchLoggedInUserDetails(request);

		ExecutiveWorkJson workJson = commonService.getAllSuperintendingWorks(pageable, role, userBean, isLegacy,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);
		return json;
	}

	// nikhil sup status
	@RequestMapping(value = "/fetchSuperintendingOfficeWorkReportStatus/{statusId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchSuperintendingOfficeWorkReportStatus(@PathVariable Long statusId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Superintending Office status wise Work Report");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		Short isLegacy = Short.parseShort(request.getParameter("isLegacy"));

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = fetchLoggedInUserDetails(request);

		ExecutiveWorkJson workJson = commonService.getAllExecutiveWorksStatus(pageable, statusId, role, userBean,
				isLegacy);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);
		return json;
	}

	// nikhil ee status
	@RequestMapping(value = "/fetchExecutiveOfficeWorkReportStatus/{statusId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchExecutiveOfficeWorkReportStatus(@PathVariable Long statusId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Executive Office Work Report");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		Short isLegacy = Short.parseShort(request.getParameter("isLegacy"));

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = fetchLoggedInUserDetails(request);
		ExecutiveWorkJson workJson = commonService.getAllExecutiveWorksStatus(pageable, statusId, role, userBean,
				isLegacy);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);
		return json;
	}

	// nikhil admin status
	@RequestMapping(value = "/fetchAdminWorkReportStatus/{statusId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchAdminWorkReportStatus(@PathVariable Long statusId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Executive Office Work Report");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		Short isLegacy = Short.parseShort(request.getParameter("isLegacy"));

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = fetchLoggedInUserDetails(request);
		ExecutiveWorkJson workJson = commonService.getAllExecutiveWorksStatus(pageable, statusId, role, userBean,
				isLegacy);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);
		return json;
	}

	// nikhil ce status
	@RequestMapping(value = "/fetchChiefOfficeWorkReportStatus/{statusId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchChiefOfficeWorkReportStatus(@PathVariable Long statusId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Executive Office Work Report");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		Short isLegacy = Short.parseShort(request.getParameter("isLegacy"));

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = fetchLoggedInUserDetails(request);
		ExecutiveWorkJson workJson = commonService.getAllExecutiveWorksStatus(pageable, statusId, role, userBean,
				isLegacy);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);
		return json;
	}

	@RequestMapping(value = "/viewCeReportsMapping", method = RequestMethod.GET)
	public ModelAndView viewCeReports(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Chief Offices Report of Work");

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		ModelAndView modelAndView = null;
		if (role.equals(RESConstants.ROLE_CE)) {
			modelAndView = new ModelAndView("common/viewChiefWorkReportAdmin");
		}
		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			modelAndView = new ModelAndView("common/viewChiefWorkReportAdmin");
		}

		return modelAndView;

	}
	
	@RequestMapping(value = "/viewCEWorksForLegacy", method = RequestMethod.GET)
	public ModelAndView viewCEWorksForLegacy(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities()
				+ " - Displaying Chief Offices Report of Work");

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		ModelAndView modelAndView = null;
		if (role.equals(RESConstants.ROLE_CE)) {
			modelAndView = new ModelAndView("ce/manageLagacyDataTotalListForChief");
		}
		if (role.equals(RESConstants.ROLE_EnC)) {
			modelAndView = new ModelAndView("ce/manageLagacyDataTotalListForEnC");
		}
		

		return modelAndView;

	}
	
	@RequestMapping(value = "/viewCEWorksForNewData", method = RequestMethod.GET)
	public ModelAndView viewCEWorksForNewData(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities()
				+ " - Displaying Chief Offices Report of Work");

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		ModelAndView modelAndView = null;
		if (role.equals(RESConstants.ROLE_CE)) {
			modelAndView = new ModelAndView("ce/manageNewDataTotalListForChief");
		}
		

		return modelAndView;

	}
	
	@RequestMapping(value = "/viewCEWorksForAllData", method = RequestMethod.GET)
	public ModelAndView viewCEWorksForAllData(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities()
				+ " - Displaying Chief Offices Report of Work");

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		ModelAndView modelAndView = null;
		if (role.equals(RESConstants.ROLE_CE)) {
			modelAndView = new ModelAndView("ce/manageAllDataTotalListForChief");
		}
		

		return modelAndView;

	}


	@RequestMapping(value = "/fetchChiefOfficeWorkReport", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchChiefOfficeWorkReport(HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Chief Office Work Report");
		
		
		String executionAgencyId = request.getParameter("agencyTypeId");
		String exeOfficeId= request.getParameter("exeOfficeId");
		String supdtOfficeId= request.getParameter("supdtOfficeId");
		String lineDepartmentId= request.getParameter("lineDepartmentId");
		String accountHeadId= request.getParameter("accountHeadId");
		String workStatusId= request.getParameter("workStatusId");
		/*System.err.println("request.getParameter(\"districtId\")="+request.getParameter("districtId"));*/
		
		
		
		String workTypeId= request.getParameter("workTypeId");
		String workSubTypeId = request.getParameter("workSubTypeId");
		
		/*String financialYearId = request.getParameter("financialYearId");*/
		
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		Short isLegacy = Short.parseShort(request.getParameter("isLegacy"));
		Integer pageNumber = 0;
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = fetchLoggedInUserDetails(request);
		
		/*String currentFY=null;
		if(null== request.getParameter("financialYearId")){
			currentFY=RESUtil.getCurrentFinancialFullYear();
		
		}else
		{
			 currentFY = request.getParameter("financialYearId");
		}*/

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);
		ExecutiveWorkJson workJson = commonService.getAllChiefWorks(pageable, role, userBean, isLegacy,executionAgencyId,exeOfficeId,supdtOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);
		return json;
	}

	@RequestMapping(value = "/fetchWorkListTwo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListTwo(HttpServletRequest request) throws RESBusinessException {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		String workStatus = (request.getParameter("workStatus") != null) ? request.getParameter("workStatus")
				: "inProgress";
		Short isLegacy = (request.getParameter("isLegacy") != null) ? Short.parseShort(request.getParameter("isLegacy"))
				: 0;

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");

		HashMap<String, Long> hmap = new HashMap<String, Long>();
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workContractorId")))
			hmap.put(RESConstants.CONTRACTOR, Long.parseLong(request.getParameter("workContractorId")));
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("financialYearId")))
			hmap.put(RESConstants.FINANCIAL_YEAR, Long.parseLong(request.getParameter("financialYearId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("officeId")))
			hmap.put(RESConstants.OFFICE, Long.parseLong(request.getParameter("officeId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);
		Long contractorId = hmap.get(RESConstants.CONTRACTOR);
		Long financialYearId = hmap.get(RESConstants.FINANCIAL_YEAR);
		Long officeIdCheck = hmap.get(RESConstants.OFFICE);

		Long eeOfficeId = null;
		Long supOfficeId = null;
		Long ceOfficeId = null;

		if (officeIdCheck != null) {
			if (officeIdCheck >= 2 && officeIdCheck <= 4) {
				ceOfficeId = officeIdCheck;
			} else if (officeIdCheck >= 5 && officeIdCheck <= 14) {
				supOfficeId = officeIdCheck;
			} else {
				eeOfficeId = officeIdCheck;
			}
		}

		String finyearString = null;

		if (financialYearId != null) {
			finyearString = RESUtil.finanicalYearMap.get(financialYearId);
		}

		/*
		 * Date fromDate = null; Date toDate = null; if(financialYearId!=null) {
		 * if(financialYearId==1) { fromDate=RESUtil.convertStringToDate("01/04/2010");
		 * toDate=RESUtil.convertStringToDate("31/03/2011"); } else
		 * if(financialYearId==2) { fromDate=RESUtil.convertStringToDate("01/04/2011");
		 * toDate=RESUtil.convertStringToDate("31/03/2012"); } else
		 * if(financialYearId==3) { fromDate=RESUtil.convertStringToDate("01/04/2012");
		 * toDate=RESUtil.convertStringToDate("31/03/2013"); } else
		 * if(financialYearId==4) { fromDate=RESUtil.convertStringToDate("01/04/2013");
		 * toDate=RESUtil.convertStringToDate("31/03/2014"); } else
		 * if(financialYearId==5) { fromDate=RESUtil.convertStringToDate("01/04/2014");
		 * toDate=RESUtil.convertStringToDate("31/03/2015"); } else
		 * if(financialYearId==6) { fromDate=RESUtil.convertStringToDate("01/04/2015");
		 * toDate=RESUtil.convertStringToDate("31/03/2016"); } else
		 * if(financialYearId==7) { fromDate=RESUtil.convertStringToDate("01/04/2016");
		 * toDate=RESUtil.convertStringToDate("31/03/2017"); } else
		 * if(financialYearId==8) { fromDate=RESUtil.convertStringToDate("01/04/2017");
		 * toDate=RESUtil.convertStringToDate("31/03/2018"); } else
		 * if(financialYearId==9) { fromDate=RESUtil.convertStringToDate("01/04/2018");
		 * toDate=RESUtil.convertStringToDate("31/03/2019"); } else
		 * if(financialYearId==10) { fromDate=RESUtil.convertStringToDate("01/04/2019");
		 * toDate=RESUtil.convertStringToDate("31/03/2020"); } }
		 */

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			} else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		/*
		 * if (sColName != null) { if (StringUtils.equals("asc", sSortDir)) { sort = new
		 * Sort(new Sort.Order(Direction.ASC, sColName)); } else { sort = new Sort(new
		 * Sort.Order(Direction.DESC, sColName)); } } else { sort = new Sort(new
		 * Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));// default // sorting
		 * }
		 */

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		Long officeId = null;
		if (null != userBean.getOfficeBean()) {
			officeId = userBean.getOfficeBean().getId();
		}
		if (null != request.getParameter("officeId")) {
			officeId = Long.parseLong(request.getParameter("officeId"));
		}

		WorkJson workJson = commonService.getAllWorksTwo(pageable, role, officeId, userBean.getId(), workStatus,
				districtId, workNatureId,workTypeId, workSubTypeId, lineDepartmentId, accountHeadId, executionAgencyId, workStatusId,
				blockId, gramPanchayatId, villageId, contractorId, finyearString, eeOfficeId, supOfficeId, ceOfficeId,
				isLegacy);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "/fetchWorkListTwoStatus/{statusId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListTwoStatus(@PathVariable Long statusId, HttpServletRequest request)
			throws RESBusinessException {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work Status Report");

		String workStatus = (request.getParameter("workStatus") != null) ? request.getParameter("workStatus")
				: "inProgress";
		Short isLegacy = (request.getParameter("isLegacy") != null) ? Short.parseShort(request.getParameter("isLegacy"))
				: 0;

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");

		HashMap<String, Long> hmap = new HashMap<String, Long>();
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workContractorId")))
			hmap.put(RESConstants.CONTRACTOR, Long.parseLong(request.getParameter("workContractorId")));
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("financialYearId")))
			hmap.put(RESConstants.FINANCIAL_YEAR, Long.parseLong(request.getParameter("financialYearId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("officeId")))
			hmap.put(RESConstants.OFFICE, Long.parseLong(request.getParameter("officeId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);
		Long contractorId = hmap.get(RESConstants.CONTRACTOR);
		Long financialYearId = hmap.get(RESConstants.FINANCIAL_YEAR);
		Long officeIdCheck = hmap.get(RESConstants.OFFICE);

		Long eeOfficeId = null;
		Long supOfficeId = null;
		Long ceOfficeId = null;

		if (officeIdCheck != null) {
			if (officeIdCheck >= 2 && officeIdCheck <= 4) {
				ceOfficeId = officeIdCheck;
			} else if (officeIdCheck >= 5 && officeIdCheck <= 14) {
				supOfficeId = officeIdCheck;
			} else {
				eeOfficeId = officeIdCheck;
			}
		}

		/*
		 * Date fromDate = null; Date toDate = null; if(financialYearId!=null) {
		 * if(financialYearId==1) { fromDate=RESUtil.convertStringToDate("01/04/2010");
		 * toDate=RESUtil.convertStringToDate("31/03/2011"); } else
		 * if(financialYearId==2) { fromDate=RESUtil.convertStringToDate("01/04/2011");
		 * toDate=RESUtil.convertStringToDate("31/03/2012"); } else
		 * if(financialYearId==3) { fromDate=RESUtil.convertStringToDate("01/04/2012");
		 * toDate=RESUtil.convertStringToDate("31/03/2013"); } else
		 * if(financialYearId==4) { fromDate=RESUtil.convertStringToDate("01/04/2013");
		 * toDate=RESUtil.convertStringToDate("31/03/2014"); } else
		 * if(financialYearId==5) { fromDate=RESUtil.convertStringToDate("01/04/2014");
		 * toDate=RESUtil.convertStringToDate("31/03/2015"); } else
		 * if(financialYearId==6) { fromDate=RESUtil.convertStringToDate("01/04/2015");
		 * toDate=RESUtil.convertStringToDate("31/03/2016"); } else
		 * if(financialYearId==7) { fromDate=RESUtil.convertStringToDate("01/04/2016");
		 * toDate=RESUtil.convertStringToDate("31/03/2017"); } else
		 * if(financialYearId==8) { fromDate=RESUtil.convertStringToDate("01/04/2017");
		 * toDate=RESUtil.convertStringToDate("31/03/2018"); } else
		 * if(financialYearId==9) { fromDate=RESUtil.convertStringToDate("01/04/2018");
		 * toDate=RESUtil.convertStringToDate("31/03/2019"); } else
		 * if(financialYearId==10) { fromDate=RESUtil.convertStringToDate("01/04/2019");
		 * toDate=RESUtil.convertStringToDate("31/03/2020"); } }
		 */

		String finyearString = null;

		if (financialYearId != null) {
			finyearString = RESUtil.finanicalYearMap.get(financialYearId);
		}

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		Integer pageNumber = 0;

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			} else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		Long officeId = null;
		if (null != userBean.getOfficeBean()) {
			officeId = userBean.getOfficeBean().getId();
		}
		if (null != request.getParameter("officeId")) {
			officeId = Long.parseLong(request.getParameter("officeId"));
		}

		WorkJson workJson = commonService.getAllWorksTwoStatus(pageable, role, statusId, null, officeId,
				userBean.getId(), workStatus, districtId,  workNatureId,workTypeId, workSubTypeId, lineDepartmentId, accountHeadId,
				executionAgencyId, workStatusId, blockId, gramPanchayatId, villageId, contractorId, finyearString,
				eeOfficeId, supOfficeId, ceOfficeId, isLegacy);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "/fetchWorkListTwoStatus2/{statusId}/{exeOfficeId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListTwoStatus2(@PathVariable Long statusId, @PathVariable Long exeOfficeId,
			HttpServletRequest request) throws RESBusinessException {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work Status Report");

		String workStatus = (request.getParameter("workStatus") != null) ? request.getParameter("workStatus")
				: "inProgress";
		Short isLegacy = (request.getParameter("isLegacy") != null) ? Short.parseShort(request.getParameter("isLegacy"))
				: 0;

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");

		HashMap<String, Long> hmap = new HashMap<String, Long>();
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workContractorId")))
			hmap.put(RESConstants.CONTRACTOR, Long.parseLong(request.getParameter("workContractorId")));
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("financialYearId")))
			hmap.put(RESConstants.FINANCIAL_YEAR, Long.parseLong(request.getParameter("financialYearId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("officeId")))
			hmap.put(RESConstants.OFFICE, Long.parseLong(request.getParameter("officeId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);
		Long contractorId = hmap.get(RESConstants.CONTRACTOR);
		Long financialYearId = hmap.get(RESConstants.FINANCIAL_YEAR);
		Long officeIdCheck = hmap.get(RESConstants.OFFICE);

		Long eeOfficeId = null;
		Long supOfficeId = null;
		Long ceOfficeId = null;

		if (officeIdCheck != null) {
			if (officeIdCheck >= 2 && officeIdCheck <= 4) {
				ceOfficeId = officeIdCheck;
			} else if (officeIdCheck >= 5 && officeIdCheck <= 14) {
				supOfficeId = officeIdCheck;
			} else {
				eeOfficeId = officeIdCheck;
			}
		}

		String finyearString = null;

		if (financialYearId != null) {
			finyearString = RESUtil.finanicalYearMap.get(financialYearId);
		}

		/*
		 * Date fromDate = null; Date toDate = null; if(financialYearId!=null) {
		 * if(financialYearId==1) { fromDate=RESUtil.convertStringToDate("01/04/2010");
		 * toDate=RESUtil.convertStringToDate("31/03/2011"); } else
		 * if(financialYearId==2) { fromDate=RESUtil.convertStringToDate("01/04/2011");
		 * toDate=RESUtil.convertStringToDate("31/03/2012"); } else
		 * if(financialYearId==3) { fromDate=RESUtil.convertStringToDate("01/04/2012");
		 * toDate=RESUtil.convertStringToDate("31/03/2013"); } else
		 * if(financialYearId==4) { fromDate=RESUtil.convertStringToDate("01/04/2013");
		 * toDate=RESUtil.convertStringToDate("31/03/2014"); } else
		 * if(financialYearId==5) { fromDate=RESUtil.convertStringToDate("01/04/2014");
		 * toDate=RESUtil.convertStringToDate("31/03/2015"); } else
		 * if(financialYearId==6) { fromDate=RESUtil.convertStringToDate("01/04/2015");
		 * toDate=RESUtil.convertStringToDate("31/03/2016"); } else
		 * if(financialYearId==7) { fromDate=RESUtil.convertStringToDate("01/04/2016");
		 * toDate=RESUtil.convertStringToDate("31/03/2017"); } else
		 * if(financialYearId==8) { fromDate=RESUtil.convertStringToDate("01/04/2017");
		 * toDate=RESUtil.convertStringToDate("31/03/2018"); } else
		 * if(financialYearId==9) { fromDate=RESUtil.convertStringToDate("01/04/2018");
		 * toDate=RESUtil.convertStringToDate("31/03/2019"); } else
		 * if(financialYearId==10) { fromDate=RESUtil.convertStringToDate("01/04/2019");
		 * toDate=RESUtil.convertStringToDate("31/03/2020"); } }
		 */

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			} else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		/*
		 * if (sColName != null) { if (StringUtils.equals("asc", sSortDir)) { sort = new
		 * Sort(new Sort.Order(Direction.ASC, sColName)); } else { sort = new Sort(new
		 * Sort.Order(Direction.DESC, sColName)); } } else { sort = new Sort(new
		 * Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));// default // sorting
		 * }
		 */

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		Long officeId = null;
		if (null != userBean.getOfficeBean()) {
			officeId = userBean.getOfficeBean().getId();
		}
		if (null != request.getParameter("officeId")) {
			officeId = Long.parseLong(request.getParameter("officeId"));
		}

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			WorkJson workJson = commonService.getAllWorksWOOfficeStatus(pageable, role, statusId, exeOfficeId,
					userBean.getId(), workStatus, districtId, workTypeId, workSubTypeId, lineDepartmentId,
					accountHeadId, executionAgencyId, workStatusId, blockId, gramPanchayatId, villageId, contractorId,
					finyearString, eeOfficeId, supOfficeId, ceOfficeId, isLegacy);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(workJson);
			return json;
		} else {

			WorkJson workJson = commonService.getAllWorksTwoStatus(pageable, role, statusId, exeOfficeId, officeId,
					userBean.getId(), workStatus, districtId,  workNatureId,workTypeId, workSubTypeId, lineDepartmentId,
					accountHeadId, executionAgencyId, workStatusId, blockId, gramPanchayatId, villageId, contractorId,
					finyearString, eeOfficeId, supOfficeId, ceOfficeId, isLegacy);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(workJson);

			return json;
		}
	}

	@RequestMapping(value = "/fetchWorkList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkList(HttpServletRequest request) throws RESBusinessException {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		Short isLegacy = (request.getParameter("isLegacy") != null) ? Short.parseShort(request.getParameter("isLegacy"))
				: 0;
		String workStatus = (request.getParameter("workStatus") != null) ? request.getParameter("workStatus")
				: "inProgress";

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");

		HashMap<String, Long> hmap = new HashMap<String, Long>();
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workContractorId")))
			hmap.put(RESConstants.CONTRACTOR, Long.parseLong(request.getParameter("workContractorId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("financialYearId")))
			hmap.put(RESConstants.FINANCIAL_YEAR, Long.parseLong(request.getParameter("financialYearId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("officeId")))
			hmap.put(RESConstants.OFFICE, Long.parseLong(request.getParameter("officeId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);
		Long contractorId = hmap.get(RESConstants.CONTRACTOR);
		Long financialYearId = hmap.get(RESConstants.FINANCIAL_YEAR);
		Long officeIdCheck = hmap.get(RESConstants.OFFICE);

		Long eeOfficeId = null;
		Long supOfficeId = null;
		Long ceOfficeId = null;

		if (officeIdCheck != null) {
			if (officeIdCheck >= 2 && officeIdCheck <= 4) {
				ceOfficeId = officeIdCheck;
			} else if (officeIdCheck >= 5 && officeIdCheck <= 14) {
				supOfficeId = officeIdCheck;
			} else {
				eeOfficeId = officeIdCheck;
			}
		}

		String finyearString = null;

		if (financialYearId != null) {
			finyearString = RESUtil.finanicalYearMap.get(financialYearId);
		}

		/*
		 * Date fromDate = null; Date toDate = null; if(financialYearId!=null) {
		 * if(financialYearId==1) {
		 * 
		 * fromDate=RESUtil.convertStringToDate("01/04/2010");
		 * toDate=RESUtil.convertStringToDate("31/03/2011");
		 * 
		 * }else if(financialYearId==2){
		 * fromDate=RESUtil.convertStringToDate("01/04/2011");
		 * toDate=RESUtil.convertStringToDate("31/03/2012");
		 * 
		 * }else if(financialYearId==3){
		 * fromDate=RESUtil.convertStringToDate("01/04/2012");
		 * toDate=RESUtil.convertStringToDate("31/03/2013");
		 * 
		 * }else if(financialYearId==4){
		 * fromDate=RESUtil.convertStringToDate("01/04/2013");
		 * toDate=RESUtil.convertStringToDate("31/03/2014");
		 * 
		 * }else if(financialYearId==5){
		 * fromDate=RESUtil.convertStringToDate("01/04/2014");
		 * toDate=RESUtil.convertStringToDate("31/03/2015");
		 * 
		 * }else if(financialYearId==6){
		 * fromDate=RESUtil.convertStringToDate("01/04/2015");
		 * toDate=RESUtil.convertStringToDate("31/03/2016");
		 * 
		 * }else if(financialYearId==7){
		 * fromDate=RESUtil.convertStringToDate("01/04/2016");
		 * toDate=RESUtil.convertStringToDate("31/03/2017");
		 * 
		 * }else if(financialYearId==8){
		 * 
		 * fromDate=RESUtil.convertStringToDate("01/04/2017");
		 * toDate=RESUtil.convertStringToDate("31/03/2018");
		 * 
		 * }else if(financialYearId==9){
		 * fromDate=RESUtil.convertStringToDate("01/04/2018");
		 * toDate=RESUtil.convertStringToDate("31/03/2019");
		 * 
		 * }else if(financialYearId==10){
		 * fromDate=RESUtil.convertStringToDate("01/04/2019");
		 * toDate=RESUtil.convertStringToDate("31/03/2020");
		 * 
		 * } }
		 */

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}
			// nikhil

			else if (sColName.equals("workRequisitionNo")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequisitionNo"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequisitionNo"));
				}
			}

			else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

			else if (sColName.equals("workRequestStatusId")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequestStatusId"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequestStatusId"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		/*
		 * if (sColName != null) { if (StringUtils.equals("asc", sSortDir)) { sort = new
		 * Sort(new Sort.Order(Direction.ASC, sColName)); } else { sort = new Sort(new
		 * Sort.Order(Direction.DESC, sColName)); } } else { sort = new Sort(new
		 * Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));// default // sorting
		 * }
		 */

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());
		if (role.equals(RESConstants.ROLE_ADMIN)) {
			WorkJson workJson = commonService.getAllWorksWOOffice(pageable, role, userBean.getId(), workStatus,
					districtId, workTypeId, workSubTypeId, lineDepartmentId, accountHeadId, executionAgencyId,
					workStatusId, blockId, gramPanchayatId, villageId, contractorId, finyearString, eeOfficeId,
					supOfficeId, ceOfficeId, isLegacy);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(workJson);
			return json;
		} else {
			WorkJson workJson = commonService.getAllWorks(pageable, role, userBean.getOfficeBean().getId(),
					userBean.getId(), workStatus, districtId, workNatureId, workTypeId, workSubTypeId, lineDepartmentId,
					accountHeadId, executionAgencyId, workStatusId, blockId, gramPanchayatId, villageId, contractorId,
					finyearString, eeOfficeId, supOfficeId, ceOfficeId, isLegacy);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(workJson);
			return json;

		}

	}

	@RequestMapping(value = "/fetchWorkListWOStatus", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListWOStatus(HttpServletRequest request, String workStatus) throws RESBusinessException {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		Short isLegacy = (request.getParameter("isLegacy") != null) ? Short.parseShort(request.getParameter("isLegacy"))
				: 0;

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");

		HashMap<String, Long> hmap = new HashMap<String, Long>();
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workContractorId")))
			hmap.put(RESConstants.CONTRACTOR, Long.parseLong(request.getParameter("workContractorId")));
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("financialYearId")))
			hmap.put(RESConstants.FINANCIAL_YEAR, Long.parseLong(request.getParameter("financialYearId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("officeId")))
			hmap.put(RESConstants.OFFICE, Long.parseLong(request.getParameter("officeId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);
		Long contractorId = hmap.get(RESConstants.CONTRACTOR);
		Long financialYearId = hmap.get(RESConstants.FINANCIAL_YEAR);
		Long officeIdCheck = hmap.get(RESConstants.OFFICE);

		Long eeOfficeId = null;
		Long supOfficeId = null;
		Long ceOfficeId = null;

		if (officeIdCheck != null) {
			if (officeIdCheck >= 2 && officeIdCheck <= 4) {
				ceOfficeId = officeIdCheck;
			} else if (officeIdCheck >= 5 && officeIdCheck <= 14) {
				supOfficeId = officeIdCheck;
			} else {
				eeOfficeId = officeIdCheck;
			}
		}

		String finyearString = null;

		if (financialYearId != null) {
			finyearString = RESUtil.finanicalYearMap.get(financialYearId);
		}

		/*
		 * Date fromDate = null; Date toDate = null; if(financialYearId!=null) {
		 * if(financialYearId==1) {
		 * 
		 * fromDate=RESUtil.convertStringToDate("01/04/2010");
		 * toDate=RESUtil.convertStringToDate("31/03/2011");
		 * 
		 * }else if(financialYearId==2){
		 * fromDate=RESUtil.convertStringToDate("01/04/2011");
		 * toDate=RESUtil.convertStringToDate("31/03/2012");
		 * 
		 * }else if(financialYearId==3){
		 * fromDate=RESUtil.convertStringToDate("01/04/2012");
		 * toDate=RESUtil.convertStringToDate("31/03/2013");
		 * 
		 * }else if(financialYearId==4){
		 * fromDate=RESUtil.convertStringToDate("01/04/2013");
		 * toDate=RESUtil.convertStringToDate("31/03/2014");
		 * 
		 * }else if(financialYearId==5){
		 * fromDate=RESUtil.convertStringToDate("01/04/2014");
		 * toDate=RESUtil.convertStringToDate("31/03/2015");
		 * 
		 * }else if(financialYearId==6){
		 * fromDate=RESUtil.convertStringToDate("01/04/2015");
		 * toDate=RESUtil.convertStringToDate("31/03/2016");
		 * 
		 * }else if(financialYearId==7){
		 * fromDate=RESUtil.convertStringToDate("01/04/2016");
		 * toDate=RESUtil.convertStringToDate("31/03/2017");
		 * 
		 * }else if(financialYearId==8){
		 * 
		 * fromDate=RESUtil.convertStringToDate("01/04/2017");
		 * toDate=RESUtil.convertStringToDate("31/03/2018");
		 * 
		 * }else if(financialYearId==9){
		 * fromDate=RESUtil.convertStringToDate("01/04/2018");
		 * toDate=RESUtil.convertStringToDate("31/03/2019");
		 * 
		 * }else if(financialYearId==10){
		 * fromDate=RESUtil.convertStringToDate("01/04/2019");
		 * toDate=RESUtil.convertStringToDate("31/03/2020");
		 * 
		 * } }
		 */

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}
			// nikhil

			else if (sColName.equals("workRequisitionNo")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequisitionNo"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequisitionNo"));
				}
			}

			else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

			else if (sColName.equals("workRequestStatusId")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequestStatusId"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequestStatusId"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());
		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			WorkJson workJson = commonService.getAllWorksWOOffice(pageable, role, userBean.getId(), workStatus,
					districtId, workTypeId, workSubTypeId, lineDepartmentId, accountHeadId, executionAgencyId,
					workStatusId, blockId, gramPanchayatId, villageId, contractorId, finyearString, eeOfficeId,
					supOfficeId, ceOfficeId, isLegacy);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(workJson);
			return json;
		} else {
			WorkJson workJson = commonService.getAllWorks(pageable, role, 38L,
					userBean.getId(), workStatus, districtId,  workNatureId,workTypeId, workSubTypeId, lineDepartmentId,
					accountHeadId, executionAgencyId, workStatusId, blockId, gramPanchayatId, villageId, contractorId,
					finyearString, eeOfficeId, supOfficeId, ceOfficeId, isLegacy);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(workJson);
			return json;

		}

	}
	
	
	@RequestMapping(value = "fetchWorkListWOStatusForHistory/{id}", method = RequestMethod.GET)
	public WorkTenderBean fetchWorkListWOStatusForHistory(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Technical data by Work Id");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		WorkTenderBean workTenderBean = commonService.fetchWorkTenderDetailsByWorkId(id);
		return workTenderBean;
	}

	@RequestMapping(value = "/editWorkRevise", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject editWorkRevise(WorkBean workBean,
			HttpServletRequest request) throws Exception {
		
		String remoteIpAddr = request.getHeader("X-Forwarded-For");
		if (remoteIpAddr == null || "".equals(remoteIpAddr)) {
			remoteIpAddr = request.getRemoteAddr();
		}
		workBean.setClientIp(remoteIpAddr);
		

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating Work data");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = commonService.editWorkRevise(workBean);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Work updated successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work updated successfully!");
		}
		return response;
	}
	

	@RequestMapping(value = "/fetchWorkListForAll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListForAll(HttpServletRequest request) throws RESBusinessException {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		Short isLegacy = (request.getParameter("isLegacy") != null) ? Short.parseShort(request.getParameter("isLegacy"))
				: 0;
		String workStatus = (request.getParameter("workStatus") != null) ? request.getParameter("workStatus")
				: "inProgress";

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");

		HashMap<String, Long> hmap = new HashMap<String, Long>();
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workContractorId")))
			hmap.put(RESConstants.CONTRACTOR, Long.parseLong(request.getParameter("workContractorId")));
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("financialYearId")))
			hmap.put(RESConstants.FINANCIAL_YEAR, Long.parseLong(request.getParameter("financialYearId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("officeId")))
			hmap.put(RESConstants.OFFICE, Long.parseLong(request.getParameter("officeId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);
		Long contractorId = hmap.get(RESConstants.CONTRACTOR);
		Long financialYearId = hmap.get(RESConstants.FINANCIAL_YEAR);
		Long officeIdCheck = hmap.get(RESConstants.OFFICE);

		Long eeOfficeId = null;
		Long supOfficeId = null;
		Long ceOfficeId = null;

		if (officeIdCheck != null) {
			if (officeIdCheck >= 2 && officeIdCheck <= 4) {
				ceOfficeId = officeIdCheck;
			} else if (officeIdCheck >= 5 && officeIdCheck <= 14) {
				supOfficeId = officeIdCheck;
			} else {
				eeOfficeId = officeIdCheck;
			}
		}

		String finyearString = null;

		if (financialYearId != null) {
			finyearString = RESUtil.finanicalYearMap.get(financialYearId);
		}

		/*
		 * Date fromDate = null; Date toDate = null; if(financialYearId!=null) {
		 * if(financialYearId==1) {
		 * 
		 * fromDate=RESUtil.convertStringToDate("01/04/2010");
		 * toDate=RESUtil.convertStringToDate("31/03/2011");
		 * 
		 * }else if(financialYearId==2){
		 * fromDate=RESUtil.convertStringToDate("01/04/2011");
		 * toDate=RESUtil.convertStringToDate("31/03/2012");
		 * 
		 * }else if(financialYearId==3){
		 * fromDate=RESUtil.convertStringToDate("01/04/2012");
		 * toDate=RESUtil.convertStringToDate("31/03/2013");
		 * 
		 * }else if(financialYearId==4){
		 * fromDate=RESUtil.convertStringToDate("01/04/2013");
		 * toDate=RESUtil.convertStringToDate("31/03/2014");
		 * 
		 * }else if(financialYearId==5){
		 * fromDate=RESUtil.convertStringToDate("01/04/2014");
		 * toDate=RESUtil.convertStringToDate("31/03/2015");
		 * 
		 * }else if(financialYearId==6){
		 * fromDate=RESUtil.convertStringToDate("01/04/2015");
		 * toDate=RESUtil.convertStringToDate("31/03/2016");
		 * 
		 * }else if(financialYearId==7){
		 * fromDate=RESUtil.convertStringToDate("01/04/2016");
		 * toDate=RESUtil.convertStringToDate("31/03/2017");
		 * 
		 * }else if(financialYearId==8){
		 * 
		 * fromDate=RESUtil.convertStringToDate("01/04/2017");
		 * toDate=RESUtil.convertStringToDate("31/03/2018");
		 * 
		 * }else if(financialYearId==9){
		 * fromDate=RESUtil.convertStringToDate("01/04/2018");
		 * toDate=RESUtil.convertStringToDate("31/03/2019");
		 * 
		 * }else if(financialYearId==10){
		 * fromDate=RESUtil.convertStringToDate("01/04/2019");
		 * toDate=RESUtil.convertStringToDate("31/03/2020");
		 * 
		 * } }
		 */

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}

			else if (sColName.equals("workRequisitionNo")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequisitionNo"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequisitionNo"));
				}
			}

			else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

			else if (sColName.equals("workRequestStatusId")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequestStatusId"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequestStatusId"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		WorkJson workJson = commonService.getAllWorksForAll(pageable, role, userBean.getOfficeBean().getId(),
				userBean.getId(), workStatus, districtId,  workNatureId, workTypeId, workSubTypeId, lineDepartmentId, accountHeadId,
				executionAgencyId, workStatusId, blockId, gramPanchayatId, villageId, contractorId, finyearString,
				eeOfficeId, supOfficeId, ceOfficeId, isLegacy);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);
		return json;

	}

	@RequestMapping(value = "/fetchWorkListByAdmin", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListByAdmin(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");

		HashMap<String, Long> hmap = new HashMap<String, Long>();
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}

			else if (sColName.equals("workRequisitionNo")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequisitionNo"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequisitionNo"));
				}
			}

			else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

			else if (sColName.equals("workRequestStatusId")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequestStatusId"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequestStatusId"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());
		TechnicalSanctionJson workJson = commonService.getAllWorksByAdmin(pageable, role,
				userBean.getOfficeBean().getId(), userBean.getId(), null, districtId, workTypeId, workSubTypeId,
				lineDepartmentId, accountHeadId, executionAgencyId, workStatusId, blockId, gramPanchayatId, villageId);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "/fetchWorkListByTechnical", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListByTechnical(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");
		HashMap<String, Long> hmap = new HashMap<String, Long>();
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}
			// nikhil

			else if (sColName.equals("workRequisitionNo")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequisitionNo"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequisitionNo"));
				}
			}

			else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

			else if (sColName.equals("workRequestStatusId")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequestStatusId"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequestStatusId"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());
		WorkEstimationJson workJson = commonService.getAllWorksByTechnical(pageable, role,
				userBean.getOfficeBean().getId(), userBean.getId(), null, districtId, workTypeId, workSubTypeId,
				lineDepartmentId, accountHeadId, executionAgencyId, workStatusId, blockId, gramPanchayatId, villageId,
				role);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	

	@RequestMapping(value = "/manageLegacyDataMapping", method = RequestMethod.GET)
	public ModelAndView manageLegacyDataMapping(HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("common/viewWorkReport");
		}

		if (role.equals(RESConstants.ROLE_EE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ee/manageLegacyData");
		}

		if (role.equals(RESConstants.ROLE_SUB_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("sube/manageLegacyData");
		}
		if (role.equals(RESConstants.ROLE_SDO)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("sdo/manageLegacyData");
		}

		if (role.equals(RESConstants.ROLE_AE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ae/manageLegacyData");
		}
		
		if (role.equals(RESConstants.ROLE_SDO)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("sdo/manageLegacyData");
		}
		/*if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ce/lockedWorkList");
		}*/
		
		if (role.equals(RESConstants.ROLE_EnC)) {
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying WorkWise Report");
		modelAndView = new ModelAndView("ce/lockedWorkList");
	   }

		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("supdte/lockedWorkList");
		}

		if (role.equals(RESConstants.ROLE_DIR_GP)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("dirgp/manageLegacyData");
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/manageLegacyDataMappingForAdmin", method = RequestMethod.GET)
	public ModelAndView manageLegacyDataMappingAdmin(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		 
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("common/viewWorkReportForLegacyAdmin");
		}

		

		if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ce/lockedWorkList");
		}

		return modelAndView;
	}
	
	
	@RequestMapping(value = "/manageNewDataMappingForAdmin", method = RequestMethod.GET)
	public ModelAndView manageNewDataMappingAdmin(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		 
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("common/viewWorkReportForNewAdmin");
		}

		

		if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ce/lockedWorkList");
		}

		return modelAndView;
	}

	public String getApplicationDeploymentServerName() {
		return applicationDeploymentServerName;
	}

	public void setApplicationDeploymentServerName(String applicationDeploymentServerName) {
		this.applicationDeploymentServerName = applicationDeploymentServerName;
	}

	@RequestMapping(value = "fetchTSIssuingAuthorityFromDesignationTable", method = RequestMethod.GET)
	public List<DesignationBean> fetchTSIssuingAuthorityFromDesignationTable(HttpServletRequest request) {
		return commonService.fetchTSIssuingAuthorityFromDesignationTable();
	}

	@RequestMapping(value = "/viewWorkLegacyDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewWorkForm(@PathVariable String id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying View Legacy Work Form");
			modelAndView = new ModelAndView("ce/viewWorkForm");
		}

		else if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying View Legacy Work Form");
			modelAndView = new ModelAndView("supdte/viewWorkForm");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/unlockWork/{id}", method = RequestMethod.GET)
	public ResponseObject unlockWork(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Unlock Work");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.unlockWork(id);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Work Unlocked successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Work Unlocked successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/downloadDocument/{documentId}", method = RequestMethod.GET)
	public void downloadDocument(@PathVariable Long documentId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		InputStream is=null;
		OutputStream os=null;
		try{
			String fileName = commonService.fetchDownloadFileName(documentId);

			if (fileName != null) {
				File file = new File(fileName);
				is = new FileInputStream(file);

				// MIME type of the file
				response.setContentType("application/octet-stream");
				// Response header
				response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
				// Read from the file and write into the response
				os = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		finally {
			if(null!=os) {
				os.flush();
			    os.close();
			}
			if(null!=is) {
				is.close();
			}
		}
	}

	@RequestMapping(value = "/viewPendingWorkEstimations", method = RequestMethod.GET)
	public ModelAndView viewPendingWorkEstimation(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying View Bills page");
		ModelAndView modelAndView = new ModelAndView("common/viewPendingWorkEstimation");
		return modelAndView;
	}

	@RequestMapping(value = "/viewHistoryWorkEstimation/{id}/{estimationId}", method = RequestMethod.GET)
	public ModelAndView viewHistoryWorkEstimation(@PathVariable Integer id, @PathVariable Integer estimationId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying View work Estimation Form");
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("common/viewHistoryWorkEstimation");
		return modelAndView;
	}

//Rakesh working
//	templateUrl: function(params){ return 'viewHistoryWorkAdminstrativeSanctionRouteMapping/' +params.workId+'/'+ params.technicalSanctionId; },
	@RequestMapping(value = "/viewHistoryWorkAdminstrativeSanctionRouteMapping/{id}/{technicalSanctionId}", method = RequestMethod.GET)
	public ModelAndView viewHistoryWorkAdminstrativeSanction(@PathVariable Integer id,
			@PathVariable Integer technicalSanctionId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying View work Estimation Form");
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("ee/viewHistoryWorkAdminstrativeSanction");
		return modelAndView;
	}

	@RequestMapping(value = "/fetchPendingWorkEstimations", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchPendingWorkEstimation(HttpServletRequest request) {

		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Pending Work Estimations");

		String searchBoxVal = request.getParameter("searchBoxVal");
		Long workType = null;
		Long workSubType = null;
		if (null != request.getParameter("workType")) {
			workType = Long.parseLong(request.getParameter("workType"));
		}
		if (null != request.getParameter("workSubType")) {
			workSubType = Long.parseLong(request.getParameter("workSubType"));
		}

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkEstimationJson workEstimationJson = commonService.getAllPendingWorkEstimations(pageable, searchBoxVal,
				workType, workSubType, role, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workEstimationJson);

		return json;
	}

	@RequestMapping(value = "/fetchHistoryWorkEstimations/{workId}/{estimationId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchHistoryWorkEstimations(@PathVariable Long workId, @PathVariable Long estimationId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Pending Work Estimations");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkEstimationJson workEstimationJson = commonService.fetchHistoryWorkEstimations(pageable, workId,
				estimationId, searchBoxVal, role, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workEstimationJson);

		return json;
	}
	
	/*** CR-RESOWMS/CR/4-8
	 *Report for the same can be downloaded and history of the changes made by 
	 *E-in-C and chief engineer should be managed at both the level.
	 * @param workId
	 * @param request
	 * @return String
	 **/
	@RequestMapping(value = "/fetchHistoryWorkLegacy/{workId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchHistoryWorkEstimations(@PathVariable Long workId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Pending Work Estimations");

		/*String searchBoxVal = request.getParameter("searchBoxVal");*/

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkLoggingJson workEstimationJson = commonService.fetchHistoryWorkLegacy(pageable, workId,
				 role, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workEstimationJson);

		return json;
	}
	
	@RequestMapping(value = "/fetchWorkListBySqmInspectionByWorkId/{workId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListBySqmInspectionByWorkId(@PathVariable Long workId,HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */
		
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}
			// nikhil

			
			

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		SqmAllocationJson workJson = commonService.getAllWorksBySqmInspectionByWorkId(pageable, role,
				userBean.getOfficeBean().getId(), user.getUsername(),workId);

		// request.setAttribute("iTotal", workJson.getAaData().size());
//	/	workJson.setiTotalDisplayRecords(5);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	//had to Work
	@RequestMapping(value = "/fetchWorkListByOfficerInspectionByWorkId/{workId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListByOfficerInspectionByWorkId(@PathVariable Long workId,HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Officer Inspction List");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */
		
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}
			// nikhil

			
			

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		SqmAllocationJson workJson = commonService.getAllWorksByOfficerInspectionByWorkId(pageable, role,
				userBean.getOfficeBean().getId(), user.getUsername(),workId);

		// request.setAttribute("iTotal", workJson.getAaData().size());
//	/	workJson.setiTotalDisplayRecords(5);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

//Rakesh Working
	@RequestMapping(value = "/fetchAllTechnicalWorksHistoryNew/{workId}/{workEstimationId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchAllTechnicalWorksHistory(@PathVariable Long workId, @PathVariable Long workEstimationId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Pending Work Estimations");

		// String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		// WorkEstimationJson workJson =
		// commonService.getAllWorksByTechnicalTwo(pageable, role,
		// userBean.getOfficeBean().getId(), user.getUsername());

		// WorkEstimationJson workEstimationJson=null;
		/*
		 * WorkEstimationJson workEstimationJson = commonService
		 * .fetchHistoryWorkEstimations(pageable, workId,estimationId, searchBoxVal,
		 * role, user.getUsername());
		 */

		WorkEstimationJson workEstimationJson = commonService.fetchHistoryWorkEstimationsByWorkIdAndEstimationId(
				pageable, role, userBean.getOfficeBean().getId(), user.getUsername(), workId, workEstimationId);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workEstimationJson);

		return json;
	}

	@RequestMapping(value = "/addWorkEstimation/{workTypeId}/{id}", method = RequestMethod.GET)
	public ModelAndView addWorkEstimation(@PathVariable Integer workTypeId, @PathVariable Integer id,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add work Estimation Form");
		ModelAndView modelAndView = new ModelAndView("common/addWorkEstimation");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		modelAndView.addObject("loggedInUserRole", loggedInUserRole);

		return modelAndView;
	}

	/** CR-RESOWMS/CR/4-1
	 * Revised estimation Process- User can change the items in revised 
	 * estimation and forward it for the TS and Then for billing.
	 * @param estimationType
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/reviseWorkEstimation/{estimationType}/{id}", method = RequestMethod.GET)
	public ModelAndView reviseWorkEstimation(@PathVariable String estimationType, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Revise work Estimation Form");

		ModelAndView modelAndView = new ModelAndView("common/reviseWorkEstimation");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		modelAndView.addObject("loggedInUserRole", loggedInUserRole);

		return modelAndView;
	}

	@RequestMapping(value = "/viewWorkEstimation/{workTypeId}/{id}", method = RequestMethod.GET)
	public ModelAndView viewWorkEstimation(@PathVariable Integer workTypeId, @PathVariable Integer id,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying View work Estimation Form");
		ModelAndView modelAndView = new ModelAndView("common/viewWorkEstimation");

		return modelAndView;
	}

	@RequestMapping(value = "/viewHEstimation/{estimationId}/{id}", method = RequestMethod.GET)
	public ModelAndView viewHEstimation(@PathVariable Integer estimationId, @PathVariable Integer id,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying View work Estimation Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("common/viewHEstimation");

		return modelAndView;
	}
	
	

	@RequestMapping(value = "/manageWorkRequisitionDataMapping", method = RequestMethod.GET)
	public ModelAndView manageWorkRequisitionDataMapping(HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_EE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Work Requisition Wise Report");
			modelAndView = new ModelAndView("ee/workRequisitionList");
		}

		if (role.equals(RESConstants.ROLE_SUB_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Work Requisition Wise Report");
			modelAndView = new ModelAndView("sube/workRequisitionList");
		}
		
		if (role.equals(RESConstants.ROLE_SDO)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Work Requisition Wise Report");
			modelAndView = new ModelAndView("sdo/workRequisitionList");
		}

		if (role.equals(RESConstants.ROLE_AE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Work Requisition Wise Report");
			modelAndView = new ModelAndView("ae/workRequisitionList");
		}

		/*if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Work Requisition Wise Report");
			modelAndView = new ModelAndView("ce/lockedWorkRequisitionList");
		}*/
		
		if (role.equals(RESConstants.ROLE_EnC)) {
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Work Requisition Wise Report");
		modelAndView = new ModelAndView("ce/lockedWorkRequisitionList");
	   }

		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Work Requisition Wise Report");
			modelAndView = new ModelAndView("supdte/lockedWorkRequisitionList");
		}

		if (role.equals(RESConstants.ROLE_DIR_GP)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Work Requisition Wise Report");
			modelAndView = new ModelAndView("dirgp/workRequisitionList");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/fetchWorkRequisitionList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkRequisitionList(HttpServletRequest request) throws RESBusinessException {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");
		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}

		HashMap<String, Long> hmap = new HashMap<String, Long>();
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workContractorId")))
			hmap.put(RESConstants.CONTRACTOR, Long.parseLong(request.getParameter("workContractorId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("financialYearId")))
			hmap.put(RESConstants.FINANCIAL_YEAR, Long.parseLong(request.getParameter("financialYearId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);
		Long contractorId = hmap.get(RESConstants.CONTRACTOR);
		Long financialYearId = hmap.get(RESConstants.FINANCIAL_YEAR);

		String finyearString = null;

		if (financialYearId != null) {
			finyearString = RESUtil.finanicalYearMap.get(financialYearId);
		}

		/*
		 * Date fromDate = null; Date toDate = null; if(financialYearId!=null) {
		 * 
		 * if(financialYearId==1) { fromDate=RESUtil.convertStringToDate("01/04/2010");
		 * toDate=RESUtil.convertStringToDate("31/03/2011");
		 * 
		 * }else if(financialYearId==2){
		 * fromDate=RESUtil.convertStringToDate("01/04/2011");
		 * toDate=RESUtil.convertStringToDate("31/03/2012");
		 * 
		 * }else if(financialYearId==3){
		 * fromDate=RESUtil.convertStringToDate("01/04/2012");
		 * toDate=RESUtil.convertStringToDate("31/03/2013");
		 * 
		 * }else if(financialYearId==4){
		 * fromDate=RESUtil.convertStringToDate("01/04/2013");
		 * toDate=RESUtil.convertStringToDate("31/03/2014");
		 * 
		 * }else if(financialYearId==5){
		 * fromDate=RESUtil.convertStringToDate("01/04/2014");
		 * toDate=RESUtil.convertStringToDate("31/03/2015");
		 * 
		 * }else if(financialYearId==6){
		 * fromDate=RESUtil.convertStringToDate("01/04/2015");
		 * toDate=RESUtil.convertStringToDate("31/03/2016");
		 * 
		 * }else if(financialYearId==7){
		 * fromDate=RESUtil.convertStringToDate("01/04/2016");
		 * toDate=RESUtil.convertStringToDate("31/03/2017");
		 * 
		 * }else if(financialYearId==8){
		 * 
		 * fromDate=RESUtil.convertStringToDate("01/04/2017");
		 * toDate=RESUtil.convertStringToDate("31/03/2018");
		 * 
		 * }else if(financialYearId==9){
		 * fromDate=RESUtil.convertStringToDate("01/04/2018");
		 * toDate=RESUtil.convertStringToDate("31/03/2019");
		 * 
		 * }else if(financialYearId==10){
		 * fromDate=RESUtil.convertStringToDate("01/04/2019");
		 * toDate=RESUtil.convertStringToDate("31/03/2020");
		 * 
		 * } }
		 */

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		WorkJson workJson = commonService.getAllRequisitionWorks(pageable, role, userBean.getOfficeBean().getId(),
				districtId,workNatureId, workTypeId, workSubTypeId, lineDepartmentId, accountHeadId, executionAgencyId, blockId,
				gramPanchayatId, villageId, contractorId, finyearString);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "/viewWorkRequisitionDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewWorkRequisitionForm(@PathVariable String id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();

		ModelAndView modelAndView = null;

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying View Requisition Work Form");
			modelAndView = new ModelAndView("ce/viewWorkRequisitionDataForm");
		}
		
		if (role.equals(RESConstants.ROLE_EnC)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying View Requisition Work Form");
			modelAndView = new ModelAndView("ce/viewWorkRequisitionDataForm");
		}

		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying View Requisition Work Form");
			modelAndView = new ModelAndView("supdte/viewWorkRequisitionDataForm");
		}

		/*
		 * ModelAndView modelAndView = new ModelAndView(
		 * "ce/viewWorkRequisitionDataForm");
		 */
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/viewWorkRequisitionDataMappingView/{id}", method = RequestMethod.GET)
	public ModelAndView viewWorkRequisitionFormView(@PathVariable String id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();

		ModelAndView modelAndView = null;

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying View Requisition Work Form");
			modelAndView = new ModelAndView("ce/viewWorkRequisitionDataFormView");
		

		

		/*
		 * ModelAndView modelAndView = new ModelAndView(
		 * "ce/viewWorkRequisitionDataForm");
		 */
		return modelAndView;
	}

	@RequestMapping(value = "/manageTechnicalSanctionDataMapping", method = RequestMethod.GET)
	public ModelAndView manageTechnicalSanctionDataMapping(HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

//		if (role.equals(RESConstants.ROLE_EE)) {
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Technical Sanction Work");
		modelAndView = new ModelAndView("common/workTechnicalSanctionList");
//		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/manageSqmInspection", method = RequestMethod.GET)
	public ModelAndView manageSqmInspectionData(HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

//		if (role.equals(RESConstants.ROLE_EE)) {
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Inspection For Sqm");
		modelAndView = new ModelAndView("common/manageSqmInspection");
//		}

		return modelAndView;
	}
	
	//manageSqmInspection
	
	@RequestMapping(value = "/manageOfficerInspection", method = RequestMethod.GET)
	public ModelAndView manageOfficerInspectionData(HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

//		if (role.equals(RESConstants.ROLE_EE)) {
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Inspection For Officer's");
		modelAndView = new ModelAndView("common/manageOfficerInspection");
//		}

		return modelAndView;
	}

	@RequestMapping(value = "/manageRandomInspection", method = RequestMethod.GET)
	public ModelAndView manageRandomInspectionData(HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

//		if (role.equals(RESConstants.ROLE_EE)) {
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Random Inspection");
		modelAndView = new ModelAndView("common/manageRandomlnspection");
//		}

		return modelAndView;
	}
//Rakesh working
	/*
	 * .when('/viewHistoryWorkTechnicalSectionRoute/:workId', { templateUrl:
	 * function(params){ return 'viewHistoryWorkTechnicalSectionMapping/'
	 * +params.workId; }, controller : 'CommonController' })
	 */
	@RequestMapping(value = "/viewHistoryWorkTechnicalSectionMapping/{workId}/{workEstimationId}", method = RequestMethod.GET)
	public ModelAndView viewHistoryWorkTechnicalSectionMapping(@PathVariable Long workId,
			@PathVariable Long workEstimationId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
//		request.setAttribute("workId", workId);
//		request.setAttribute("workEstimationId", workEstimationId);

		ModelAndView modelAndView = null;

//		if (role.equals(RESConstants.ROLE_EE)) {
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Technical Sanction Work");
		modelAndView = new ModelAndView("common/viewHistoryWorkTechnicalSection");
//		}

		return modelAndView;
	}

	@RequestMapping(value = "/fetchTechnicalSanctionList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchTechnicalSanctionList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Technical Sanction List");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());
		WorkJson workJson = commonService.getAllTechnicalSanctionWorks(pageable, role,
				userBean.getOfficeBean().getId());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "/manageAdministrationSanctionDataMapping", method = RequestMethod.GET)
	public ModelAndView manageAdministrationSanctionDataMapping(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		/*
		 * boolean hasRole = false; for (GrantedAuthority authority :
		 * user.getAuthorities()) { hasRole =
		 * authority.getAuthority().equals(RESConstants.ROLE_EE); if (hasRole) {
		 * ModelAndView modelAndView = new ModelAndView( "ee/manageLegacyData"); }
		 * hasRole = authority.getAuthority().equals(RESConstants.ROLE_ADMIN); if
		 * (hasRole) { ModelAndView modelAndView = new ModelAndView(
		 * "ee/manageLegacyData"); } }
		 */
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_EE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Administration Sanction Work");
			modelAndView = new ModelAndView("ee/workAdministrationSanctionList");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/fetchAdministrationSanctionList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchAdministrationSanctionList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Administration Sanction List");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());
		WorkJson workJson = commonService.getAllAdministrationSanctionWorks(pageable, role,
				userBean.getOfficeBean().getId());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "fetchAccountHead", method = RequestMethod.GET)
	public List<AccountHeadBean> fetchAccountHead(HttpServletRequest request) {
		return commonService.fetchAccountHead();
	}

	@RequestMapping(value = "fetchContractors/{name}", method = RequestMethod.GET)
	public List<ContractorBean> fetchContractorsByName(HttpServletRequest request, @PathVariable String name) {
		return commonService.fetchContractorsByName(name);
	}

	/*
	 * @RequestMapping(value = "/workListExportToExcel/{isLegacy}", method =
	 * RequestMethod.GET) public void workListExportToExcel(@PathVariable Short
	 * isLegacy, HttpServletRequest request, HttpServletResponse response) throws
	 * ParseException {
	 * 
	 * user = RESUtil.getUserDetail(); HttpSession httpSession =
	 * request.getSession(false); String role = (String) httpSession
	 * .getAttribute(RESConstants.LOGGED_IN_USER_ROLE); UserBean userBean =
	 * userService.fetchUserDetailsByUserName(user .getUsername());
	 * 
	 * List<WorkBean> workDetailsList = commonService.getWorkList(isLegacy, role,
	 * userBean.getOfficeBean().getId(), userBean.getId());
	 * 
	 * try { String fileName = "Work_List.xlsx"; ClassPathResource pdfFile = new
	 * ClassPathResource(fileName); InputStream excelFile =
	 * pdfFile.getInputStream(); XSSFWorkbook workbook = new
	 * XSSFWorkbook(excelFile); XSSFSheet sheet1 = workbook.getSheetAt(0);
	 * 
	 * createxlssheet(workDetailsList, workbook, sheet1);
	 * 
	 * ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	 * 
	 * workbook.write(outStream);
	 * 
	 * byte[] outArray = outStream.toByteArray();
	 * 
	 * response.setContentLength(outArray.length);
	 * 
	 * response.setContentType("application/octet-stream");
	 * 
	 * String headerKey = "Content-Disposition"; String headerValue =
	 * String.format("attachment; filename=\"%s\"", fileName);
	 * response.setHeader(headerKey, headerValue); OutputStream outStream1 =
	 * response.getOutputStream(); outStream1.write(outArray); outStream1.flush();
	 * workbook.close();
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
	 * e) { e.printStackTrace(); } }
	 */

	@RequestMapping(value = "/workListExportToExcel/{isLegacy}", method = RequestMethod.GET)
	public void workListExportToExcelForObjArr(@PathVariable Short isLegacy, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		List<Object[]> workDetailsList = commonService.getWorkListForObjArr(isLegacy, role,
				userBean.getOfficeBean().getId(), userBean.getId());

		try {
			String fileName = "Work_List.xlsx";
			ClassPathResource pdfFile = new ClassPathResource(fileName);
			InputStream excelFile = pdfFile.getInputStream();
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet sheet1 = workbook.getSheetAt(0);

			createxlssheetFromArray(workDetailsList, workbook, sheet1);

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();

			workbook.write(outStream);

			byte[] outArray = outStream.toByteArray();

			response.setContentLength(outArray.length);

			response.setContentType("application/octet-stream");

			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
			response.setHeader(headerKey, headerValue);
			OutputStream outStream1 = response.getOutputStream();
			outStream1.write(outArray);
			outStream1.flush();
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/workListExportToExcelTest/{isLegacy}", method = RequestMethod.GET)
	public void workListExportToExcelTest(@PathVariable Short isLegacy, @RequestParam String workTypeId,
			@RequestParam String workSubTypeId, @RequestParam String workLineDepartmentId,
			@RequestParam String accountHeadId, @RequestParam String executionAgencyId,
			@RequestParam String workStatusId, @RequestParam String blockId, @RequestParam String gramPanchayatId,
			@RequestParam String villageId, @RequestParam String workContractorId, @RequestParam String districtId,
			HttpServletRequest request, HttpServletResponse response) throws ParseException {

		Long workTypeIdLong = null;
		Long workSubTypeIdLong = null;
		Long workLineDepartmentIdLong = null;
		Long accountHeadIdLong = null;
		Long executionAgencyIdLong = null;
		Long workStatusIdLong = null;
		Long blockIdLong = null;
		Long gramPanchayatIdLong = null;
		Long villageIdLong = null;
		Long workContractorIdLong = null;
		Long districtIdLong = null;

		if (workTypeId != null && !"null".equals(workTypeId)) {
			workTypeIdLong = Long.valueOf(workTypeId);
		}

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		List<WorkBean> workDetailsList = null;

		workDetailsList = commonService.getWorkListTest(isLegacy, role, userBean.getOfficeBean().getId(),
				userBean.getId(), null, districtIdLong, workTypeIdLong, workSubTypeIdLong, workLineDepartmentIdLong,
				accountHeadIdLong, executionAgencyIdLong, workStatusIdLong, blockIdLong, gramPanchayatIdLong,
				villageIdLong, workContractorIdLong);

		try {
			String fileName = "Work_List.xlsx";
			ClassPathResource pdfFile = new ClassPathResource(fileName);
			InputStream excelFile = pdfFile.getInputStream();
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet sheet1 = workbook.getSheetAt(0);

			createxlssheet(workDetailsList, workbook, sheet1);

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();

			workbook.write(outStream);

			byte[] outArray = outStream.toByteArray();

			response.setContentLength(outArray.length);

			response.setContentType("application/octet-stream");

			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
			response.setHeader(headerKey, headerValue);
			OutputStream outStream1 = response.getOutputStream();
			outStream1.write(outArray);
			outStream1.flush();
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createxlssheet(List<WorkBean> workDetailsList, XSSFWorkbook workbook, XSSFSheet sheet1)
			throws ParseException {
		int snoCounter = 1, rowCounter = 6;

		XSSFCell cell = null;
		CellStyle cellStyleDate = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd-mm-yyyyy"));
		SimpleDateFormat dateFormat = new SimpleDateFormat(RESConstants.DATE_FORMAT);

		if (null != workDetailsList) {
			for (WorkBean workBean : workDetailsList) {

				XSSFRow row = sheet1.createRow(rowCounter);

				row.createCell(0).setCellValue(String.valueOf(snoCounter));
				row.createCell(1).setCellValue(workBean.getWorkName());
				row.createCell(2).setCellValue(workBean.getWorkTypeName());

				if (workBean.getWorkSubTypeBean() != null) {
					row.createCell(3).setCellValue(workBean.getWorkSubTypeBean().getWorkSubTypeNameE());
				} else {
					row.createCell(3).setCellValue("");
				}

				if (workBean.getLineDepartmentBean() != null) {
					row.createCell(4).setCellValue(workBean.getLineDepartmentBean().getLineDepartmentNameE());
				} else {
					row.createCell(4).setCellValue("");
				}

				row.createCell(5).setCellValue(workBean.getAccountHeadName());

				row.createCell(6).setCellValue(workBean.getAgencyTypeBean().getAgencyTypeNameE());

				row.createCell(7).setCellValue(workBean.getContractorBean().getName());

				cell = row.createCell(8);
				cell.setCellType(CellType.NUMERIC);
				if (workBean.getTotalExpenditureTill31March2018String() != null) {
					cell.setCellValue(Integer.valueOf(workBean.getTotalExpenditureTill31March2018String()));
				} else {
					cell.setCellValue("-");
				}

				if (workBean.getWorkStatus() != null) {
					row.createCell(9).setCellValue(workBean.getWorkStatus());
				} else {
					row.createCell(9).setCellValue("-");
				}

				row.createCell(10).setCellValue(workBean.getPhysicalStageType());

				// row.createCell(11).setCellValue(workBean.getTentativeCompletionDateString());

				Date d3 = null;
				if (workBean.getTentativeCompletionDateString() != null) {
					d3 = dateFormat.parse(workBean.getTentativeCompletionDateString());
				}
				cell = row.createCell(11);
				cell.setCellValue(d3);
				cell.setCellStyle(cellStyleDate);

				cell = row.createCell(12);
				cell.setCellType(CellType.NUMERIC);
				if (workBean.getTotalExpenditureTill31March2018String() != null) {
					cell.setCellValue(Integer.valueOf(workBean.getTotalExpenditureTill31March2018String()));
				} else {
					cell.setCellValue("-");
				}

				row.createCell(13).setCellValue(workBean.getDistrictName());
				row.createCell(14).setCellValue(workBean.getBlockName());
				row.createCell(15).setCellValue(workBean.getGramPanchayatName());
				row.createCell(16).setCellValue(workBean.getVillageBean().getVillageName());
				row.createCell(17).setCellValue(workBean.getLocationAddress());

				cell = row.createCell(18);
				cell.setCellType(CellType.NUMERIC);
				if (workBean.getWorkLocationLatitude() != null) {
					cell.setCellValue(Double.valueOf(workBean.getWorkLocationLatitude()));
				} else {
					cell.setCellValue("");
				}

				cell = row.createCell(19);
				cell.setCellType(CellType.NUMERIC);

				if (workBean.getWorkLocationLongitude() != null) {
					cell.setCellValue(Double.valueOf(workBean.getWorkLocationLongitude()));
				} else {
					cell.setCellValue("");
				}

				row.createCell(20).setCellValue(workBean.getExecutiveEngineerOfficeName());
				row.createCell(21).setCellValue(workBean.getAssistantEngineerName());
				row.createCell(22).setCellValue(workBean.getSubEngineerName());
				row.createCell(21).setCellValue(workBean.getSubDivisionOfficerName());

				row.createCell(23).setCellValue(workBean.getTechnicalSanctionType());

				row.createCell(24).setCellValue(workBean.getTechnicalSanctionNo());

				Date d = null;
				if (workBean.getTechnicalSanctionDate() != null) {
					d = dateFormat.parse(workBean.getTechnicalSanctionDate());
				}
				cell = row.createCell(25);
				cell.setCellValue(d);
				cell.setCellStyle(cellStyleDate);

				cell = row.createCell(26);
				cell.setCellType(CellType.NUMERIC);

				if (workBean.getEstimatedCostString() != null) {
					cell.setCellValue(Integer.valueOf(workBean.getEstimatedCostString()));
				} else {
					cell.setCellValue("");
				}

				row.createCell(27).setCellValue(workBean.getTsAuthorityName());

				row.createCell(28).setCellValue(workBean.getAdministrationSanctionType());

				row.createCell(29).setCellValue(workBean.getAdministrationSanctionNo());

				Date d1 = null;
				if (workBean.getAdministrationSanctionDate() != null) {
					d1 = dateFormat.parse(workBean.getAdministrationSanctionDate());
				}
				cell = row.createCell(30);
				cell.setCellValue(d1);
				cell.setCellStyle(cellStyleDate);

				cell = row.createCell(31);
				cell.setCellType(CellType.NUMERIC);
				if (workBean.getTotalCostString() != null) {
					cell.setCellValue(Integer.valueOf(workBean.getTotalCostString()));
				} else {
					cell.setCellValue("-");
				}

				row.createCell(32).setCellValue(workBean.getAsAuthorityName());

				row.createCell(33).setCellValue(workBean.getAgreementNumber());

				// row.createCell(34).setCellValue(workBean.getAgreementDateString());

				Date d5 = null;
				if (workBean.getAgreementDateString() != null) {
					d5 = dateFormat.parse(workBean.getAgreementDateString());
				}
				cell = row.createCell(34);
				cell.setCellValue(d5);
				cell.setCellStyle(cellStyleDate);

				if (workBean.getTenderedRatePer() != null) {

					if (workBean.getTenderedRateSign() != null) {
						if (workBean.getTenderedRateSign().equals("+")) {
							row.createCell(35).setCellValue(workBean.getTenderedRatePer().toString() + " above");
						}
						if (workBean.getTenderedRateSign().equals("-")) {
							row.createCell(35).setCellValue(workBean.getTenderedRatePer().toString() + " below");
						}
					} else {
						row.createCell(35).setCellValue("");
					}

				} else {
					row.createCell(35).setCellValue("");
				}

				cell = row.createCell(36);
				cell.setCellType(CellType.NUMERIC);

				if (workBean.getPacAmount() != null) {
					cell.setCellValue(workBean.getPacAmount().intValue());

				} else {
					cell.setCellValue("");
				}

				cell = row.createCell(37);
				cell.setCellType(CellType.NUMERIC);

				if (workBean.getTenderCost() != null) {
					cell.setCellValue(workBean.getTenderCost().intValue());
				} else {
					cell.setCellValue("");
				}

				row.createCell(38).setCellValue(workBean.getRemarks());

				if (workBean.getWorkRequisitionNo() != null) {
					row.createCell(39).setCellValue(workBean.getWorkRequisitionNo());
				} else {
					row.createCell(39).setCellValue("");
				}

				snoCounter++;
				rowCounter++;
			}
		}

	}

	//nikhil add data on excel
	private void createxlssheetFromArray(List<Object[]> workDetailsListArr, XSSFWorkbook workbook, XSSFSheet sheet1) throws ParseException {
		int snoCounter = 1, rowCounter = 6;
		
		XSSFCell cell = null;
		CellStyle cellStyleDate = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd-mm-yyyyy"));
		SimpleDateFormat dateFormat = new SimpleDateFormat(RESConstants.DATE_FORMAT);
		
		//set the data from List of Object Array in to excel
		for (Object[] workBean : workDetailsListArr) {

			XSSFRow row = sheet1.createRow(rowCounter);

			row.createCell(0).setCellValue(String.valueOf(snoCounter));
			row.createCell(1).setCellValue((String)workBean[0]);
			row.createCell(2).setCellValue((String)workBean[1]);
			row.createCell(3).setCellValue((String)workBean[2]);
			row.createCell(4).setCellValue((String)workBean[3]);
			row.createCell(5).setCellValue((String)workBean[4]);
			row.createCell(6).setCellValue((String)workBean[5]);
			row.createCell(7).setCellValue((String)workBean[6]);
			if(workBean[7]!=null) {
			row.createCell(8).setCellValue(workBean[7].toString());
			}
			// shall treat 8 as 9
			row.createCell(9).setCellValue((String)workBean[8]);
			if(workBean[9]!=null) {
			row.createCell(10).setCellValue(workBean[9].toString());
			}
			//Stipulated Date Of Completion
			if(workBean[10]!=null) {
			row.createCell(11).setCellValue(RESUtil.convertDateToString((Date) workBean[10]));
			}
			if(workBean[11]!=null) {
			row.createCell(12).setCellValue((String)workBean[11].toString());
			}
			row.createCell(13).setCellValue((String)workBean[12].toString());
			row.createCell(14).setCellValue((String)workBean[13].toString());
			row.createCell(15).setCellValue((String)workBean[14].toString());
			if(workBean[15]!=null) {
			row.createCell(16).setCellValue((String)workBean[15].toString());
			}
			if(workBean[16]!=null) {
				row.createCell(17).setCellValue((String)workBean[16].toString());
			}
			if(workBean[17]!=null) {
				row.createCell(18).setCellValue((String)workBean[17].toString());
			}
			if(workBean[18]!=null) {
				row.createCell(19).setCellValue((String)workBean[18].toString());
			}
			if(workBean[19]!=null) {
				row.createCell(20).setCellValue((String)workBean[19].toString());
			}
			if(workBean[20]!=null) {
				row.createCell(21).setCellValue((String)workBean[20].toString());
			}
			if(workBean[21]!=null) {
				row.createCell(22).setCellValue((String)workBean[21].toString());
			}
			if(workBean[22]!=null) {
				row.createCell(23).setCellValue((String)workBean[22].toString());
			}
			if(workBean[23]!=null) {
				row.createCell(24).setCellValue((String)workBean[23].toString());
			}
			
			//Technical Sanction Date
			if(workBean[24]!=null) {
				row.createCell(25).setCellValue(RESUtil.convertDateToString((Date)workBean[24]));
			}
			
			// Technical Sanction Amount ===Estimated Cost As Per Technical Sanction (in Rs.)
			BigDecimal Estimated_Cost_As_Per_Technical_Sanction = (BigDecimal)workBean[25];

			if(Estimated_Cost_As_Per_Technical_Sanction!=null) {
				row.createCell(26).setCellValue(Estimated_Cost_As_Per_Technical_Sanction.doubleValue());
			}
			if(workBean[26]!=null) {
				row.createCell(27).setCellValue((String)workBean[26].toString());
			}
			if(workBean[27]!=null) {
				row.createCell(28).setCellValue((String)workBean[27].toString());
			}
			if(workBean[28]!=null) {
				row.createCell(29).setCellValue((String)workBean[28].toString());
			}
			//Administrative Sanction Date
			if(workBean[29]!=null) {
				row.createCell(30).setCellValue(RESUtil.convertDateToString((Date)workBean[29]));
			}
			// Admin Sanction Amount ===Approved Cost As Per Administrative Sanction (in Rs.)
			BigDecimal Approved_Cost_As_Per_Administrative_Sanction = (BigDecimal)workBean[30];

			if(Approved_Cost_As_Per_Administrative_Sanction!=null) {
				row.createCell(31).setCellValue(Approved_Cost_As_Per_Administrative_Sanction.doubleValue());
			}
			if(workBean[31]!=null) {
				row.createCell(32).setCellValue((String)workBean[31].toString());
			}
			if(workBean[32]!=null) {
				row.createCell(33).setCellValue((String)workBean[32].toString());
			}
			
			//Agreement Date
			if(workBean[33]!=null) {
				row.createCell(34).setCellValue(RESUtil.convertDateToString((Date)workBean[33]));
			}
			//tender rate(tender sign) by nikhil
			if(workBean[34]!=null) {
				row.createCell(35).setCellValue((String)workBean[34].toString()+"("+(String)workBean[50]+")");
			}
			
			// PAC Amount
			BigDecimal PAC_Amount = (BigDecimal)workBean[35];
			if(PAC_Amount!=null) {
				row.createCell(36).setCellValue(PAC_Amount.doubleValue());
			}
			
			//tender cost
			BigDecimal Tender_Cost = (BigDecimal)workBean[36];
			if(Tender_Cost!=null) {
				row.createCell(37).setCellValue(Tender_Cost.doubleValue());
			}
			if(workBean[37]!=null) {
				row.createCell(38).setCellValue((String)workBean[37].toString());
			}
			if(workBean[38]!=null) {
				row.createCell(39).setCellValue((String)workBean[38].toString());
			}
			if(workBean[39]!=null) {
				row.createCell(40).setCellValue((String)workBean[39].toString());
			}
			
			//Estimation Total Amount
			BigDecimal Estimation_Total_Amount = (BigDecimal)workBean[40];
			if(Estimation_Total_Amount!=null) {
				row.createCell(41).setCellValue(Estimation_Total_Amount.doubleValue());
			}
			if(workBean[41]!=null) {
				row.createCell(42).setCellValue((String)workBean[41].toString());
			}
			if(workBean[42]!=null) {
				row.createCell(43).setCellValue((String)workBean[42].toString());
			}
			
			//Estimation Date
			if(workBean[43]!=null && workBean[47].toString().equals("5")) {
				if(workBean[43] instanceof Date) {
				row.createCell(44).setCellValue(RESUtil.convertDateToString((Date)workBean[43]));
				}
				else {
					row.createCell(44).setCellValue((String)workBean[43].toString());
				}
				
			}
			
			
			/*BigDecimal total_amt_31 = (BigDecimal)workBean[44];*/
			BigDecimal total_amt_31 = (BigDecimal)workBean[7];
			BigDecimal exp_amt = (BigDecimal)workBean[45];
		
			//contegency amount is in Double and total amount and expendeture amount is in BigDecimal
			
			Double cont_amt = (Double)workBean[46];
			
			if(total_amt_31 == null)
			{
				row.createCell(45).setCellValue(" ");
				total_amt_31 = BigDecimal.ZERO;
			}else
			{
			row.createCell(45).setCellValue(total_amt_31.doubleValue());
			}
			
			//46
			if(exp_amt == null)
			{
				row.createCell(47).setCellValue(" ");
				exp_amt = BigDecimal.ZERO;
				
			}
			else {
			row.createCell(47).setCellValue(exp_amt.doubleValue());
			}
			if(cont_amt==null)
			{
				row.createCell(48).setCellValue(" ");
				cont_amt = (Double)0.0;
			}
			row.createCell(48).setCellValue(cont_amt);
			
			/*row.createCell(48).setCellValue(exp_amt.doubleValue() + cont_amt);*/
			
			BigDecimal total_exp_on_cont= (BigDecimal)workBean[49];
			
			if(total_exp_on_cont == null)
			{
				
				total_exp_on_cont = BigDecimal.ZERO;
			}
			
	BigDecimal total_expenditure_till_31_march_2018= (BigDecimal)workBean[7];
			
			if(total_expenditure_till_31_march_2018 == null)
			{
				
				total_expenditure_till_31_march_2018 = BigDecimal.ZERO;
			}
			row.createCell(49).setCellValue((total_expenditure_till_31_march_2018.add(exp_amt)).doubleValue());
			row.createCell(50).setCellValue(total_exp_on_cont.doubleValue() + cont_amt);
			row.createCell(51).setCellValue(total_exp_on_cont.doubleValue() + cont_amt + total_expenditure_till_31_march_2018.doubleValue() + exp_amt.doubleValue());
			
			row.createCell(46).setCellValue(total_exp_on_cont.doubleValue());
			
			
			
			/*Integer workid  = (Integer) workBean[23];
			if(workid != null) {
			
			TechnicalSanctionBean technicalSanctionBean = commonService.findByWorkId(workid.longValue());
			
			
			if(technicalSanctionBean.getTechnicalSanctionTypeBean().getTechnicalSanctionType()!=null && technicalSanctionBean!=null && technicalSanctionBean.getTechnicalSanctionTypeBean()!=null) {
			
			row.createCell(25).setCellValue(technicalSanctionBean.getTechnicalSanctionTypeBean().getTechnicalSanctionType());
			}
			
			
			System.out.println(technicalSanctionBean.getTechnicalSanctionTypeBean().getTechnicalSanctionType());
			}*/
			
			if(workBean[48]!=null) {
				row.createCell(52).setCellValue((String)workBean[48].toString());
			}
			
			//[51]
			
			//Old Pac And Old Tender By Nikhil
			
			if(workBean[51]!=null) {
				row.createCell(53).setCellValue((String)workBean[51].toString());
			}
			
			if(workBean[52]!=null) {
				row.createCell(54).setCellValue((String)workBean[52].toString());
			}
			
			//Billing Flag And Max Bill Amount
			
			if(workBean[53]!=null) {
				row.createCell(55).setCellValue((String)workBean[53].toString());
			}
			
	/*		if(workBean[53]!=null) {
				row.createCell(55).setCellValue((Byte)workBean[53]);
			}*/
			
			if(workBean[54]!=null) {
				row.createCell(56).setCellValue((String)workBean[54].toString());
			}
			

			snoCounter++;
			rowCounter++;
		}
	}

//working on report
	@RequestMapping(value = "/manageLegacyDataMappingDuringReport/{workStatus}/{isLegacy}", method = RequestMethod.GET)
	public ModelAndView manageLegacyDataMappingDuringReport(@PathVariable String workStatus,
			@PathVariable Short isLegacy, HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_ADMIN)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("common/viewWorkReportForReport");
		}else if (role.equals(RESConstants.ROLE_EE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report in Executive Engineer Login");
			modelAndView = new ModelAndView("ee/manageLegacyDataTotalList");
		}else if (role.equals(RESConstants.ROLE_SUB_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("sube/manageLegacyData");
		}else if (role.equals(RESConstants.ROLE_SDO)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
			+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("sdo/manageLegacyData");
		}else if (role.equals(RESConstants.ROLE_AE)) {
					logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ae/manageLegacyData");
		}else if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ce/manageLegacyDataTotalList");
		}else if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report in Superintending Engineer Login");
			modelAndView = new ModelAndView("supdte/manageLegacyDataTotalList");
		}else if (role.equals(RESConstants.ROLE_DIR_GP)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report in Director Gram Panchayat Login");
			modelAndView = new ModelAndView("dirgp/viewPanchayatiRajWorkDataTotalList");
		}
		if(null!= modelAndView) {
			modelAndView.addObject("workStatus", workStatus);
			modelAndView.addObject("isLegacy", isLegacy);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/manageLegacyDataRouteDuringReportStatus/{workStatus}/{id}/{isLegacy}", method = RequestMethod.GET)
	public ModelAndView manageLegacyDataRouteDuringReportStatus(@PathVariable String workStatus, @PathVariable Long id,
			@PathVariable Short isLegacy, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_ADMIN)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("common/viewWorkReportForReportAdminStatus");
		}

		if (role.equals(RESConstants.ROLE_EE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report in Executive Engineer Login");
			modelAndView = new ModelAndView("ee/manageLegacyDataTotalListStatus");
		}

		if (role.equals(RESConstants.ROLE_SUB_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("sube/manageLegacyData");
		}
		if (role.equals(RESConstants.ROLE_SDO)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("sdo/manageLegacyData");
		}

		if (role.equals(RESConstants.ROLE_AE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ae/manageLegacyData");
		}

		if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ce/manageLegacyDataTotalListStatus");
		}

		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report in Superintending Engineer Login");
			modelAndView = new ModelAndView("supdte/manageLegacyDataTotalListStatus");
		}

		if (role.equals(RESConstants.ROLE_DIR_GP)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report in Director Gram Panchayat Login");
			modelAndView = new ModelAndView("dirgp/viewPanchayatiRajWorkDataTotalList");
		}

		if(null!=modelAndView) {
			modelAndView.addObject("workStatus", workStatus);
			modelAndView.addObject("isLegacy", isLegacy);
		}
		return modelAndView;
	}

	// nikhil workimg on report

	@RequestMapping(value = "/manageLegacyDataRouteDuringReportStatusSupdt/{workStatus}/{officeId}/{id}/{isLegacy}", method = RequestMethod.GET)
	public ModelAndView manageLegacyDataRouteDuringReportStatusSupdt(@PathVariable String workStatus,
			@PathVariable Long officeId, @PathVariable Long id, @PathVariable Short isLegacy,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("common/viewWorkReportForReportAdminStatus");
		}

		if (role.equals(RESConstants.ROLE_EE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report in Executive Engineer Login");
			modelAndView = new ModelAndView("ee/manageLegacyDataTotalListStatus");
		}

		if (role.equals(RESConstants.ROLE_SUB_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("sube/manageLegacyData");
		}
		if (role.equals(RESConstants.ROLE_SDO)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("sdo/manageLegacyData");
		}

		if (role.equals(RESConstants.ROLE_AE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ae/manageLegacyData");
		}

		if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("ce/manageLegacyDataTotalListStatus");
		}

		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report in Superintending Engineer Login");
			modelAndView = new ModelAndView("supdte/manageLegacyDataTotalListStatus");
		}

		if (role.equals(RESConstants.ROLE_DIR_GP)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report in Director Gram Panchayat Login");
			modelAndView = new ModelAndView("dirgp/viewPanchayatiRajWorkDataTotalList");
		}
		if (null != modelAndView) {
			modelAndView.addObject("workStatus", workStatus);
			modelAndView.addObject("isLegacy", isLegacy);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/manageLegacyDataMappingDuringReportAdmin/{workStatus}/{isLegacy}/{officeId}", method = RequestMethod.GET)
	public ModelAndView manageLegacyDataMappingDuringReportAdmin(@PathVariable String workStatus,
			@PathVariable Long officeId, @PathVariable Short isLegacy, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("admin/manageLegacyDataTotalList");
		}
		
		if (null != modelAndView) {
			modelAndView.addObject("workStatus", workStatus);
			modelAndView.addObject("officeId", officeId);
			modelAndView.addObject("isLegacy", isLegacy);	
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/manageListLegacyDataMappingDuringReportAdmin/{workStatus}/{isLegacy}/{officeId}/{executionAgencyId}/{exeOfficeId}/{supdtOfficeId}/{lineDepartmentId}/{accountHeadId}/{workStatusId}/{workTypeId}/{workSubTypeId}", method = RequestMethod.GET)
	public ModelAndView manageListLegacyDataMappingDuringReportAdmin(@PathVariable String workStatus,
			@PathVariable Long officeId, @PathVariable Short isLegacy,
			@PathVariable Integer executionAgencyId,@PathVariable String exeOfficeId,@PathVariable String supdtOfficeId,@PathVariable String lineDepartmentId,@PathVariable String accountHeadId,@PathVariable String workStatusId,
			@PathVariable String workTypeId,@PathVariable String workSubTypeId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("admin/manageListLegacyDataTotalList");
		}
		
		if (role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("admin/manageListLegacyDataTotalList");
		}
		
		if (null != modelAndView) {
			modelAndView.addObject("workStatus", workStatus);
			modelAndView.addObject("officeId", officeId);
			modelAndView.addObject("isLegacy", isLegacy);
			modelAndView.addObject("executionAgencyId", executionAgencyId);
			modelAndView.addObject("exeOfficeId", exeOfficeId);
			modelAndView.addObject("supdtOfficeId", supdtOfficeId);
			modelAndView.addObject("lineDepartmentId", lineDepartmentId);
			modelAndView.addObject("accountHeadId", accountHeadId);
			modelAndView.addObject("workStatusId", workStatusId);
			modelAndView.addObject("workTypeId", workTypeId);
			modelAndView.addObject("workSubTypeId", workSubTypeId);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/manageDataForSupdtStatusWiseReports/{workStatus}/{isLegacy}/{officeId}/{executionAgencyId}/{exeOfficeId}/{lineDepartmentId}/{accountHeadId}/{workStatusId}/{workTypeId}/{workSubTypeId}", method = RequestMethod.GET)
	public ModelAndView manageListLegacyDataMappingDuringReportAdmin(@PathVariable String workStatus,
			@PathVariable Long officeId, @PathVariable Short isLegacy,
			@PathVariable Integer executionAgencyId,@PathVariable String exeOfficeId,@PathVariable String lineDepartmentId,@PathVariable String accountHeadId,@PathVariable String workStatusId,
			@PathVariable String workTypeId,@PathVariable String workSubTypeId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_ADMIN) || role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_ADMIN_VIEW)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("admin/manageListLegacyDataTotalListSupdt");
		}
		
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("admin/manageListLegacyDataTotalListSupdt");
		}
		
		if (null != modelAndView) {
			modelAndView.addObject("workStatus", workStatus);
			modelAndView.addObject("officeId", officeId);
			modelAndView.addObject("isLegacy", isLegacy);
			modelAndView.addObject("executionAgencyId", executionAgencyId);
			modelAndView.addObject("exeOfficeId", exeOfficeId);
			/*modelAndView.addObject("supdtOfficeId", supdtOfficeId);*/
			modelAndView.addObject("lineDepartmentId", lineDepartmentId);
			modelAndView.addObject("accountHeadId", accountHeadId);
			modelAndView.addObject("workStatusId", workStatusId);
			modelAndView.addObject("workTypeId", workTypeId);
			modelAndView.addObject("workSubTypeId", workSubTypeId);
		}
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/manageDataForPaymentWiseReport/{billStatus}/{eeOfficeId}/{fromYear}/{endYear}/{lineDepartmentId}/{accountHeadId}/{workStatusId}/{executionAgencyId}/{workTypeId}/{workSubTypeId}", method = RequestMethod.GET)
	public ModelAndView manageDataForPaymentWiseReport(@PathVariable String billStatus,
			@PathVariable String eeOfficeId, @PathVariable String fromYear,@PathVariable String endYear,@PathVariable String lineDepartmentId,
			@PathVariable String accountHeadId,@PathVariable String workStatusId,@PathVariable Integer executionAgencyId,@PathVariable String workTypeId,@PathVariable String workSubTypeId ,HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

	
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Payment Wise Report");
			modelAndView = new ModelAndView("admin/manageDataForPaymentWiseReport");
		

		modelAndView.addObject("billStatus", billStatus);
		modelAndView.addObject("eeOfficeId", eeOfficeId);
		modelAndView.addObject("fromYear", fromYear);
		modelAndView.addObject("endYear", endYear);
		modelAndView.addObject("lineDepartmentId", lineDepartmentId);
		modelAndView.addObject("accountHeadId", accountHeadId);
		modelAndView.addObject("workStatusId", workStatusId);
		modelAndView.addObject("executionAgencyId", executionAgencyId);
		modelAndView.addObject("workTypeId", workTypeId);
		modelAndView.addObject("workSubTypeId", workSubTypeId);
		

		return modelAndView;
	}
	@RequestMapping(value = "/manageDataForMultiStatusReport/{workReqStatusId}/{executionAgencyId}/{exeOfficeId}/{lineDepartmentId}/{accountHeadId}/{workStatusId}/{workTypeId}/{workSubTypeId}", method = RequestMethod.GET)
	public ModelAndView manageDataForMultiSelectReport(@PathVariable Integer workReqStatusId,
			@PathVariable Integer executionAgencyId,@PathVariable String exeOfficeId,@PathVariable String lineDepartmentId,@PathVariable String accountHeadId,@PathVariable String workStatusId,
			@PathVariable String workTypeId,@PathVariable String workSubTypeId,HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

	
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Payment Wise Report");
			modelAndView = new ModelAndView("admin/manageDataForMultiStatusReport");
		

		modelAndView.addObject("workReqStatusId", workReqStatusId);
		modelAndView.addObject("executionAgencyId", executionAgencyId);
		modelAndView.addObject("exeOfficeId", exeOfficeId);
		modelAndView.addObject("lineDepartmentId", lineDepartmentId);
		modelAndView.addObject("accountHeadId", accountHeadId);
		modelAndView.addObject("workStatusId", workStatusId);
		modelAndView.addObject("workTypeId", workTypeId);
		modelAndView.addObject("workSubTypeId", workSubTypeId);
		
		return modelAndView;
	}
	//id/:agencyTypeId/:fromYear/:endYear/:lineDepartmentId/:accountHeadId/:workTypeId/:workSubTypeId/:name
	@RequestMapping(value = "/manageDataPendingForInspectionReport/{exeOfficeId}/{executionAgencyId}/{fromYear}/{endYear}/{lineDepartmentId}/{accountHeadId}/{workTypeId}/{workSubTypeId}/{name}", method = RequestMethod.GET)
	public ModelAndView manageDataPendingForInspectionReport(@PathVariable String exeOfficeId,
			@PathVariable Integer executionAgencyId,@PathVariable String fromYear,@PathVariable String endYear,@PathVariable String lineDepartmentId,@PathVariable String accountHeadId,
			@PathVariable String workTypeId,@PathVariable String workSubTypeId,String name,HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

	
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Payment Wise Report");
			modelAndView = new ModelAndView("admin/manageDataPendingForInspectionReport");
		

		modelAndView.addObject("exeOfficeId", exeOfficeId);
		modelAndView.addObject("executionAgencyId", executionAgencyId);
		/*modelAndView.addObject("exeOfficeId", exeOfficeId);*/
		modelAndView.addObject("fromYear", fromYear);
		modelAndView.addObject("endYear", endYear);
		modelAndView.addObject("lineDepartmentId", lineDepartmentId);
		modelAndView.addObject("accountHeadId", accountHeadId);
		/*modelAndView.addObject("workStatusId", workStatusId);*/
		modelAndView.addObject("workTypeId", workTypeId);
		modelAndView.addObject("workSubTypeId", workSubTypeId);
		/*modelAndView.addObject("name", name);*/
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/manageDataFinalBillPendingReport/{exeOfficeId}/{executionAgencyId}/{fromYear}/{endYear}/{lineDepartmentId}/{accountHeadId}/{workTypeId}/{workSubTypeId}/{name}", method = RequestMethod.GET)
	public ModelAndView manageDataFinalBillPendingReport(@PathVariable String exeOfficeId,
			@PathVariable Integer executionAgencyId,@PathVariable String fromYear,@PathVariable String endYear,@PathVariable String lineDepartmentId,@PathVariable String accountHeadId,
			@PathVariable String workTypeId,@PathVariable String workSubTypeId,String name,HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

	
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Payment Wise Report");
			modelAndView = new ModelAndView("admin/manageDataFinalBillPendingReport");
		

		modelAndView.addObject("exeOfficeId", exeOfficeId);
		modelAndView.addObject("executionAgencyId", executionAgencyId);
		/*modelAndView.addObject("exeOfficeId", exeOfficeId);*/
		modelAndView.addObject("fromYear", fromYear);
		modelAndView.addObject("endYear", endYear);
		modelAndView.addObject("lineDepartmentId", lineDepartmentId);
		modelAndView.addObject("accountHeadId", accountHeadId);
		/*modelAndView.addObject("workStatusId", workStatusId);*/
		modelAndView.addObject("workTypeId", workTypeId);
		modelAndView.addObject("workSubTypeId", workSubTypeId);
		/*modelAndView.addObject("name", name);*/
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/manageDataPhysicalCCDispatcheReport/{exeOfficeId}/{executionAgencyId}/{lineDepartmentId}/{accountHeadId}/{workTypeId}/{workSubTypeId}/{name}", method = RequestMethod.GET)
	public ModelAndView manageDataPhysicalCCDispatcheReport(@PathVariable Integer exeOfficeId,
			@PathVariable Integer executionAgencyId,@PathVariable String lineDepartmentId,@PathVariable String accountHeadId,
			@PathVariable String workTypeId,@PathVariable String workSubTypeId,String name,HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

	
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying Payment Wise Report");
			modelAndView = new ModelAndView("admin/manageDataPhysicalCCDispatcheReport");
		

		modelAndView.addObject("exeOfficeId", exeOfficeId);
		modelAndView.addObject("executionAgencyId", executionAgencyId);
		/*modelAndView.addObject("exeOfficeId", exeOfficeId);*/
		modelAndView.addObject("lineDepartmentId", lineDepartmentId);
		modelAndView.addObject("accountHeadId", accountHeadId);
		/*modelAndView.addObject("workStatusId", workStatusId);*/
		modelAndView.addObject("workTypeId", workTypeId);
		modelAndView.addObject("workSubTypeId", workSubTypeId);
		/*modelAndView.addObject("name", name);*/
		
		
		return modelAndView;
	}
	
	
	
	
	@RequestMapping(value = "/viewExeAgWiseExpenditureReportDetails/{parameter1}/{eeOfficeId}/{name}/{lineDepartmentId}/{accountHeadId}/{workStatusId}/{executionAgencyId}/{workTypeId}/{workSubTypeId}", method = RequestMethod.GET)
	public ModelAndView viewExeAgWiseExpenditureReportDetails(@PathVariable String parameter1,@PathVariable Integer eeOfficeId,@PathVariable String name,@PathVariable String lineDepartmentId,
			@PathVariable String accountHeadId,@PathVariable String workStatusId,@PathVariable Integer executionAgencyId,@PathVariable Integer workTypeId,@PathVariable String workSubTypeId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Displaying WorkWise Report");
			modelAndView = new ModelAndView("admin/viewExeAgWiseExpenditureReportDetails");
		

		modelAndView.addObject("parameter1", parameter1);
		modelAndView.addObject("eeOfficeId", eeOfficeId);
		modelAndView.addObject("name", name);
		modelAndView.addObject("lineDepartmentId", lineDepartmentId);
		modelAndView.addObject("accountHeadId", accountHeadId);
		modelAndView.addObject("workStatusId", workStatusId);
		modelAndView.addObject("executionAgencyId", executionAgencyId);
		modelAndView.addObject("workTypeId", workTypeId);
		modelAndView.addObject("workSubTypeId", workSubTypeId);
		

		return modelAndView;
	}

	@RequestMapping(value = "/generateTs/{estimationId}", method = RequestMethod.GET)
	public ModelAndView generateTs(@PathVariable String estimationId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Generating Technical Sanction , with Print Option ");

		Users userEntity = userService.findByUserName(user.getUsername());
		commonService.changeTechnicalSanctionStatus(estimationId, userEntity);

		ModelAndView modelAndView = new ModelAndView("common/workTechnicalSanctionList");
		return modelAndView;

	}

	@RequestMapping(value = "/dispatchTs/{workId}/{estimationId}", method = RequestMethod.GET)
	public ModelAndView dispatchTs(@PathVariable String workId, @PathVariable String estimationId,
			HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Dispatch TS Form");

		ModelAndView modelAndView = new ModelAndView("common/dispatchTsForm");
		return modelAndView;
	}

	// Rakesh Working
	@RequestMapping(value = "/dispatchTsByParent/{workId}/{estimationId}/{parentId}", method = RequestMethod.GET)
	public ModelAndView dispatchTsByParent(@PathVariable String workId, @PathVariable String estimationId,
			@PathVariable String parentId, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Dispatch TS Form");

		ModelAndView modelAndView = new ModelAndView("common/dispatchTsForm");
		return modelAndView;
	}

	@RequestMapping(value = "/editTechnicalSanction/{workId}/{estimationId}", method = RequestMethod.GET)
	public ModelAndView editTechnicalSanction(@PathVariable String workId, @PathVariable String estimationId,
			HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit Dispatch TS Form");

		ModelAndView modelAndView = new ModelAndView("common/editTechnicalSanction");
		return modelAndView;
	}

	// Rakesh working
	@RequestMapping(value = "/viewTechnicalSanction/{workId}/{estimationId}", method = RequestMethod.GET)
	public ModelAndView viewTechnicalSanction(@PathVariable String workId, @PathVariable String estimationId,
			HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit Dispatch TS Form");

		ModelAndView modelAndView = new ModelAndView("common/viewTechnicalSanction");
		return modelAndView;
	}

	@RequestMapping(value = "fetchPaymentData/{billId}", method = RequestMethod.GET)
	public PaymentBean fetchPaymentData(@PathVariable Long billId, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");
		return commonService.fetchPaymentData(billId);
	}

	@RequestMapping(value = "/addAdminSanction/{workId}/{technicalSanctionId}", method = RequestMethod.GET)
	public ModelAndView addAdminSanction(@PathVariable String workId, @PathVariable String technicalSanctionId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add Admin Sanction Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/addAdminSection");

		return modelAndView;
	}

//Rakesh 
	@RequestMapping(value = "/addAdminSanctionByParent/{workId}/{technicalSanctionId}/{parentId}", method = RequestMethod.GET)
	public ModelAndView addAdminSanctionByParent(@PathVariable String workId, @PathVariable String technicalSanctionId,
			@PathVariable String parentId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add Admin Sanction Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/addAdminSection");

		return modelAndView;
	}

	@RequestMapping(value = "fetchContengecyData/{billId}", method = RequestMethod.GET)
	public ContengencyBean fetchContengecyData(@PathVariable Long billId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");
		return commonService.fetchContengecyData(billId);
	}

	@RequestMapping(value = "/viewAdminSection/{workTypeId}/{id}", method = RequestMethod.GET)
	public ModelAndView viewAdminSection(@PathVariable Integer workTypeId, @PathVariable Integer id,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add Admin Section Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/viewAdminSection");

		return modelAndView;
	}

	@RequestMapping(value = "/administrationSanctionHindiForm/{workTypeId}/{id}", method = RequestMethod.GET)
	public ModelAndView administrationSanctionHindiForm(@PathVariable Integer workTypeId, @PathVariable Integer id,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add Admin Section Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/administrationSanctionHindiForm");

		return modelAndView;
	}

	@RequestMapping(value = "/editAdminSection/{workTypeId}/{id}", method = RequestMethod.GET)
	public ModelAndView editAdminSection(@PathVariable Integer workTypeId, @PathVariable Integer id,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add Admin Section Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/editAdminSection");

		return modelAndView;
	}

	@RequestMapping(value = "/addTechnicalSanction", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addTechnicalSanction(WorkBean workBean, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Adding Technical Sanction data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.addTechnicalSanction(workBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Technical Sanction added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Technical Sanction added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/viewTs/{id}", method = RequestMethod.GET)
	public ModelAndView viewTs(@PathVariable String id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Dispatch TS Form");

		ModelAndView modelAndView = new ModelAndView("ee/viewTsForm");
		return modelAndView;
	}

	@RequestMapping(value = "fetchWorkTenderDetailsByWorkId/{id}", method = RequestMethod.GET)
	public WorkTenderBean fetchWorkTenderDetailsByWorkId(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Technical data by Work Id");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		WorkTenderBean workTenderBean = commonService.fetchWorkTenderDetailsByWorkId(id);
		return workTenderBean;
	}

	@RequestMapping(value = "fetchWorkTenderDetailsByTenderId/{id}", method = RequestMethod.GET)
	public WorkTenderBean fetchWorkTenderDetailsByTenderId(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Technical data by Work Id");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		WorkTenderBean workTenderBean = commonService.fetchWorkTenderDetailsByTenderId(id);
		return workTenderBean;
	}

	@RequestMapping(value = "/unlockMultipleWork", method = RequestMethod.POST)
	public ResponseObject unlockMultipleWork(@RequestParam(value = "workids", required = true) String workids,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Unlock Work");
		ResponseObject response = new ResponseObject();

		String[] workidArr = workids.split(",");

		String errorMsg = null;

		for (String workid : workidArr) {

			long id = Long.parseLong(workid);
			errorMsg = commonService.unlockWork(id);
		}

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Work Unlocked successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Work Unlocked successfully!");
		}
		return response;
	}

	@RequestMapping(value = "fetchGramPanchayat", method = RequestMethod.GET)
	public List<AgencyTypeBean> fetchGramPanchayat(HttpServletRequest request) {
		return commonService.fetchGramPanchayat();
	}

	@RequestMapping(value = "/viewPanchayatiRajReportsMapping", method = RequestMethod.GET)
	public ModelAndView viewPanchayatiRajReportsMapping(HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Gram Panchayat Offices Report of Work");

		ModelAndView modelAndView = new ModelAndView("dirgp/viewPanchayatiRajWorkReport");

		return modelAndView;
	}

	@RequestMapping(value = "/inspectionList", method = RequestMethod.GET)
	public ModelAndView inspectionList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Inspection List page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {
			modelAndView = new ModelAndView("common/inspectionListEE");
		}

		else if (loggedInUserRole.equals(RESConstants.ROLE_AE) 
				|| loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)
				|| loggedInUserRole.equals(RESConstants.ROLE_SDO)) {
			modelAndView = new ModelAndView("common/inspectionList");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cc/ccInspectionList", method = RequestMethod.GET)
	public ModelAndView ccInspectionList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying CC Inspection List page");

		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("ee/cc/ccInspectionList");

		return modelAndView;
	}
	
	@RequestMapping(value = "/generalInspectionList", method = RequestMethod.GET)
	public ModelAndView generalInspectionList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying General Inspection List page");

		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("common/generalInspectionList");

		return modelAndView;
	}
	

	@RequestMapping(value = "/viewInspection/{id}", method = RequestMethod.GET)
	public ModelAndView viewInspection(HttpServletRequest request, @PathVariable Long id) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Inspection List page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("common/viewInspection");

		return modelAndView;
	}
	
	@RequestMapping(value = "/viewGeneralInspection/{id}", method = RequestMethod.GET)
	public ModelAndView viewGeneralInspection(HttpServletRequest request, @PathVariable Long id) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Inspection List page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("common/viewGeneralInspection");

		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/viewInspectionForSqm/{id}/{inspectionId}", method = RequestMethod.GET)
	public ModelAndView viewInspectionForSqm(HttpServletRequest request, @PathVariable Long id, @PathVariable Long inspectionId) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Sqm Inspection List  page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("common/viewInspectionForSqm");

		return modelAndView;
	}
	
	@RequestMapping(value = "/viewInspectionForOfficer/{id}/{inspectionId}", method = RequestMethod.GET)
	public ModelAndView viewInspectionForOfficer(HttpServletRequest request, @PathVariable Long id,  @PathVariable Long inspectionId) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Officer Inspection List  page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("common/viewInspectionForOfficer");

		return modelAndView;
	}
	
	@RequestMapping(value = "/viewInspectionForSqmByWorkId/{workId}", method = RequestMethod.GET)
	public ModelAndView viewInspectionForSqmByWorkId(HttpServletRequest request, @PathVariable Long workId) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Sqm Inspection List  page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("common/viewInspectionForSqmByWorkId");

		return modelAndView;
	}
	
	@RequestMapping(value = "/viewInspectionForOfficerByWorkId/{workId}", method = RequestMethod.GET)
	public ModelAndView viewInspectionForOfficerByWorkId(HttpServletRequest request, @PathVariable Long workId) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Sqm Inspection List  page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("common/viewInspectionForOfficerByWorkId");

		return modelAndView;
	}
	
	@RequestMapping(value="/viewInspectionEE/{id}" , method=RequestMethod.GET)
	public ModelAndView viewInspectionEE(HttpServletRequest request, @PathVariable Long id) {
	
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying Inspection List page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("common/viewInspectionEE");

		return modelAndView;
	}
	@RequestMapping(value="/fetchInspectionData/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerNewBean> fetchInspectionData(@PathVariable Long id) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionData called");
		 List<InspectionAnswerNewBean> list = commonService.fetchInspectionAnswerByBillIdNew(id);
		return list;
	}
	@RequestMapping(value="/fetchInspectionDataSQM/{sqmAllocationId}/{inspectionId}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerNewBean> fetchInspectionDataSqm(@PathVariable Long sqmAllocationId, @PathVariable Long inspectionId) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionData called");
		List<InspectionDetails> isa = null;
		if (sqmAllocationId != null) {
			isa = inspectionDetailsRepository.findBySqmAllocationId(sqmAllocationId);
		}
		if (null != isa && isa.size() > 0) {
		InspectionDetails inspection = inspectionDetailsRepo.findById(inspectionId);
		if(inspection!=null) {
		 List<InspectionAnswerNewBean> list = commonService.fetchInspectionAnswerByInspectionIdNewSQM(inspection);
		  return list;
		  }
		}
		return null;
	}
	
	@RequestMapping(value="/fetchGeneralInspectionDataNew/{inspectionId}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerNewBean> fetchGeneralInspectionDataNew( @PathVariable Long inspectionId) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionData called");
		InspectionDetails inspection = inspectionDetailsRepository.findByIdAndGeneralInspectionDone(inspectionId, (short) 1);
		if(inspection!=null) {
		 List<InspectionAnswerNewBean> list = commonService.fetchInspectionAnswerByInspectionIdNewSQM(inspection);
		  return list;
		  }
		return null;
	}
	
	
	
	@RequestMapping(value="/fetchInspectionDataForOfficerNew/{randomAllocationId}/{inspectionId}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerNewBean> fetchInspectionDataForOfficerNew(@PathVariable Long randomAllocationId, @PathVariable Long inspectionId) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionData called");
		List<InspectionDetails> isa = null;
		if (randomAllocationId != null) {
			isa = inspectionDetailsRepository.findByRandomAllocationId(randomAllocationId);
		}
		if (null != isa && isa.size() > 0) {
		InspectionDetails inspection = inspectionDetailsRepo.findById(inspectionId);
		if(inspection!=null) {
		 List<InspectionAnswerNewBean> list = commonService.fetchInspectionAnswerByInspectionIdNewSQM(inspection);
		  return list;
		  }
		}
		return null;
		
	}
	@RequestMapping(value="/fetchInspectionDataForEE/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerNewBean> fetchInspectionDataForEE(@PathVariable Long id) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionData called");
		 List<InspectionAnswerNewBean> list = commonService.fetchInspectionAnswerByBillIdNew(id);
		return list;
	}
	
	@RequestMapping(value="/fetchInspectionDataForSqm/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionSqmAnswerBean> fetchInspectionDataForSqm(@PathVariable Long id) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionData called");
		 return commonService.fetchInspectionAnswerSqmByWorkId(id);
		
	}
	
	@RequestMapping(value="/fetchInspectionDataForOfficer/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionSqmAnswerBean> fetchInspectionDataForOfficer(@PathVariable Long id) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionData called");
		 return commonService.fetchInspectionAnswerOfficerByWorkId(id);
		
	}
	

	@RequestMapping(value = "fetchFinancialYear", method = RequestMethod.GET)
	public List<FinancialYearBean> fetchFinancialYear(HttpServletRequest request) {
		return commonService.fetchFinancialYear();
	}

	/** CR-RESOWMS/CR/1-2
	 * Office Mgmt.-Create New Office and shift the works.
	 * @param request
	 * @return List<OfficeBean>
	 */
	@RequestMapping(value = "fetchOffices", method = RequestMethod.GET)
	public List<OfficeBean> fetchOffices(HttpServletRequest request) {
		return commonService.fetchOffices();
	}

	@RequestMapping(value="/fetchInspectionImages/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerImageBean> fetchInspectionImages(@PathVariable Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionImages called");
		
		 List<InspectionAnswerImageBean> list = commonService.fetchInspectionAnswerImageByBillIdNew(id);
		return list;
	}
	
	@RequestMapping(value="/fetchGeneralInspectionImages/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerImageBean> fetchGeneralInspectionImages(@PathVariable Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionImages called");
		
		 List<InspectionAnswerImageBean> list = commonService.fetchInspectionAnswerImageByInspectionId(id);
		return list;
	}
	
	
	@RequestMapping(value="/fetchInspectionImagesSqm/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionSqmAnswerImage> fetchInspectionImagesSqm(@PathVariable Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionImages called");
		
		 List<InspectionSqmAnswerImage> list = commonService.fetchInspectionAnswerImageByWorkId(id);
		return list;
	}
	
	
	@RequestMapping(value="/fetchInspectionImagesSqmNew/{sqmAllocationId}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerImageBean> fetchInspectionImagesSqmNew(@PathVariable Long sqmAllocationId) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionImages called");
		List<InspectionDetails> isa = null;
		if (sqmAllocationId != null) {
			isa = inspectionDetailsRepository.findBySqmAllocationId(sqmAllocationId);
		}
		
		if (null != isa && isa.size() > 0) {
			
		SqmAllocation sqmAllocation = allocationRepository.findById(sqmAllocationId);
		InspectionDetails inspection = inspectionDetailsRepo.findByWorkIdAndSqmAllocationId(sqmAllocation.getWork().getId(), sqmAllocationId);
		
		 List<InspectionAnswerImageBean> list = commonService.fetchInspectionAnswerImageByInspectionId(inspection.getId());
		return list;
	  }
		return null;
	}
	
	@RequestMapping(value="/fetchInspectionImagesByInspectionId/{inspectionId}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerImageBean> fetchInspectionImagesByInspectionId(@PathVariable Long inspectionId) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionImages called");
		
		 List<InspectionAnswerImageBean> list = commonService.fetchInspectionAnswerImageByInspectionId(inspectionId);
		return list;
	  }
	
	
	@RequestMapping(value="/fetchInspectionImagesOfficerNew/{randomAllocationId}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerImageBean> fetchInspectionImagesOfficerNew(@PathVariable Long randomAllocationId) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionImages called");
		List<InspectionDetails> isa = null;
		if (randomAllocationId != null) {
			isa = inspectionDetailsRepository.findByRandomAllocationId(randomAllocationId);
		}
		
		if (null != isa && isa.size() > 0) {
			
		SqmAllocation sqmAllocation = allocationRepository.findById(randomAllocationId);
		InspectionDetails inspection = inspectionDetailsRepo.findByWorkIdAndRandomAllocationId(sqmAllocation.getWork().getId(), randomAllocationId);
		
		 List<InspectionAnswerImageBean> list = commonService.fetchInspectionAnswerImageByInspectionId(inspection.getId());
		return list;
	  }
		return null;
	}
	
	
	@RequestMapping(value="/fetchInspectionImagesOfficer/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionSqmAnswerImage> fetchInspectionImagesOfficer(@PathVariable Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionImages called");
		
		 List<InspectionSqmAnswerImage> list = commonService.fetchInspectionAnswerOfficerImageByWorkId(id);
		return list;
	}
	
	@RequestMapping(value="/fetchInspectionFilesSqm/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionSqmAnswerFile> fetchInspectionFilesSqm(@PathVariable Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionImages called");
		
		 List<InspectionSqmAnswerFile> list = commonService.fetchInspectionAnswerFileByWorkId(id);
		return list;
	}
	
	@RequestMapping(value="/fetchInspectionFilesOfficer/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionSqmAnswerFile> fetchInspectionFilesOfficer(@PathVariable Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionFiles called");
		
		 List<InspectionSqmAnswerFile> list = commonService.fetchInspectionAnswerOfficerFileByWorkId(id);
		return list;
	}
	
	@RequestMapping(value="/fetchInspectionImagesEE/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerImageBean> fetchInspectionImagesEE(@PathVariable Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchInspectionImages called");
		
		List<InspectionAnswerImageBean> list = commonService.fetchInspectionAnswerImageByBillIdNew(id);
		return list;
	}

	@RequestMapping(value = "/manageTenderDataRoute", method = RequestMethod.GET)
	public ModelAndView manageTenderDataRoute(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " -  manageTenderDataRoute  called");
		ModelAndView modelAndView = new ModelAndView("common/manageTenderDataRoute");
		return modelAndView;
	}
	@RequestMapping(value = "/manageTenderDataRouteForEnc", method = RequestMethod.GET)
	public ModelAndView manageTenderDataRouteForEnc(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " -  manageTenderDataRoute  called");
		ModelAndView modelAndView = new ModelAndView("common/manageTenderDataRouteForEnc");
		return modelAndView;
	}

	// Rakesh
	@RequestMapping(value = "/manageHistoryTenderDataRoute/{id}/{administrationSanctionId}", method = RequestMethod.GET)
	public ModelAndView manageTenderHistoryDataRoute(@PathVariable Long id, @PathVariable Long administrationSanctionId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " -  manageTenderHistoryDataRoute  called");
		ModelAndView modelAndView = new ModelAndView("common/manageHistoryTenderDataRoute");
		return modelAndView;
	}

	@RequestMapping(value = "/editTenderDataRoute", method = RequestMethod.GET)
	public ModelAndView editTenderDataRoute(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " -  editTenderDataRoute  called");
		ModelAndView modelAndView = new ModelAndView("common/editTenderDataRoute");
		return modelAndView;
	}

	@RequestMapping(value = "/editTender/{id}/{administrationSanctionId}", method = RequestMethod.GET)
	public ModelAndView editTender(@PathVariable Integer id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add Admin Section Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/editTender");

		return modelAndView;
	}

	@RequestMapping(value = "/editTender", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject editTender(@RequestBody WorkTenderBean workTenderBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Tender data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.editTender(workTenderBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Tender Updated successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Technical Sanction added successfully!");
		}
		return response;

	}

	// old
	/*
	 * @RequestMapping(value = "/fetchWorkTender", method = RequestMethod.GET,
	 * produces = "application/json;charset=UTF-8") public String
	 * fetchWorkTender(HttpServletRequest request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Fetching Work Tender List");
	 * 
	 * String searchBoxVal = request.getParameter("searchBoxVal"); Long workType =
	 * null; Long workSubType = null; if (null != request.getParameter("workType"))
	 * { workType = Long.parseLong(request.getParameter("workType")); } if (null !=
	 * request.getParameter("workSubType")) { workSubType =
	 * Long.parseLong(request.getParameter("workSubType")); }
	 * 
	 * String sSortCol = request.getParameter("iSortCol_0"); String sSortDir =
	 * request.getParameter("sSortDir_0"); String sColName =
	 * request.getParameter("mDataProp_" + sSortCol);
	 * 
	 * // Fetch the page number from client Integer pageNumber = 0;
	 * 
	 * Integer pageDisplayLength = Integer.valueOf(request
	 * .getParameter("iDisplayLength"));
	 * 
	 * if (null != request.getParameter("iDisplayStart")) { pageNumber = (Integer
	 * .valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength); }
	 * 
	 * Sort sort = null; if (sColName != null) { if (StringUtils.equals("asc",
	 * sSortDir)) { sort = new Sort(new Sort.Order(Direction.ASC, sColName)); } else
	 * { sort = new Sort(new Sort.Order(Direction.DESC, sColName)); } } else { sort
	 * = new Sort(new Sort.Order(Direction.DESC, "id"));// default // sorting }
	 * 
	 * Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);
	 * 
	 * HttpSession httpSession = request.getSession(false);
	 * 
	 * String role = (String) httpSession
	 * .getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
	 * 
	 * AdministrationSanctionJson adminSanctionJson = commonService
	 * .getAllWorkTender(pageable, searchBoxVal, workType, workSubType, role,
	 * user.getUsername());
	 * 
	 * Gson gson = new GsonBuilder().setPrettyPrinting().create(); String json =
	 * gson.toJson(adminSanctionJson);
	 * 
	 * return json; }
	 */

	// new richa

	@RequestMapping(value = "/fetchWorkTender", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkTender(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work Tender List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		Long workType = null;
		Long workSubType = null;
		if (null != request.getParameter("workType")) {
			workType = Long.parseLong(request.getParameter("workType"));
		}
		if (null != request.getParameter("workSubType")) {
			workSubType = Long.parseLong(request.getParameter("workSubType"));
		}

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkJson workJson = commonService.getAllWorkTender(pageable, searchBoxVal, workType, workSubType, role,
				user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	@RequestMapping(value = "/fetchDataForPaymentWise", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchDataForPaymentWise(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work Tender List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		/*Long workTypeId = null;*/
		Long workSubType = null;
		/*Long eeOfficeId=null;*/
		Long executionAgencyId=null;
		
	
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		
		String billStatus = request.getParameter("billStatus");
		String eeOfficeId = request.getParameter("eeOfficeId");
		/*eeOfficeId = Long.parseLong(request.getParameter("eeOfficeId"));*/
		String fromYear = request.getParameter("fromYear");
		String endYear = request.getParameter("endYear");
	
		/*eeOfficeId = Long.parseLong(request.getParameter("eeOfficeId"));*/
		//String fromYear = request.getParameter("fromYear");
		//String endYear = request.getParameter("endYear");
		
		/*String parameter1 = request.getParameter("parameter1");*/
		/*String lineDeptStr = request.getParameter("lineDepartmentId");*/
		String lineDepartmentId = request.getParameter("lineDepartmentId");;
		
		
		
		/*String accountHeadIdstr = request.getParameter("accountHeadId");*/
		String accountHeadId = request.getParameter("accountHeadId");

		
		/*String workStatusIdstr = request.getParameter("workStatusId");*/
		String workStatusId = request.getParameter("workStatusId");
	
		
		executionAgencyId = Long.parseLong(request.getParameter("executionAgencyId"));
		/* workTypeId = Long.parseLong(request.getParameter("workTypeId"));*/
		String workTypeId = request.getParameter("workTypeId");
		
		/*String workSubTypeIdstr = request.getParameter("workSubTypeId");*/
		String workSubTypeId = request.getParameter("workSubTypeId");
		workSubTypeId = workSubTypeId.replace('"', ' ');
		workSubTypeId = workSubTypeId.replace('[', ' ');
		workSubTypeId = workSubTypeId.replace(']', ' ');
		workSubTypeId = workSubTypeId.replace(']', ' ');
		workSubTypeId = workSubTypeId.replaceAll(" , ", ",");
		workSubTypeId = workSubTypeId.trim();
	
		
		

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// defaultR
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		WorkJson workJson=null;
         
		if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {
		 workJson = commonService.getDataForPaymentWise(pageable, searchBoxVal,office,-1, billStatus, eeOfficeId, fromYear,endYear
				 ,lineDepartmentId,accountHeadId,workStatusId,executionAgencyId,workTypeId,workSubTypeId
				 );
		}
		 else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			 
			 workJson = commonService.getDataForPaymentWise(pageable, searchBoxVal,-1,office, billStatus, eeOfficeId, fromYear,endYear
					 ,lineDepartmentId,accountHeadId,workStatusId,executionAgencyId,workTypeId,workSubTypeId
						);
			 
		 }
		 else {
			 workJson = commonService.getDataForPaymentWise(pageable, searchBoxVal,-1,-1, billStatus, eeOfficeId, fromYear,endYear
					 ,lineDepartmentId,accountHeadId,workStatusId,executionAgencyId,workTypeId,workSubTypeId
						);
			 
		 }

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchDataForMultiselectStatusWise", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchDataForMultiselectStatusWise(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work Tender List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		/*Long workTypeId = null;*/
		Long workReqStatusId=null;
		Long executionAgencyId=null;
	

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		
		
		executionAgencyId = Long.parseLong(request.getParameter("executionAgencyId"));
		
		
		workReqStatusId = Long.parseLong(request.getParameter("workReqStatusId"));
	    String exeOfficeId = request.getParameter("exeOfficeId");
	    String lineDepartmentId = request.getParameter("lineDepartmentId");
	    String accountHeadId = request.getParameter("accountHeadId");
	    String workStatusId = request.getParameter("workStatusId");
	    /*workTypeId = Long.parseLong(request.getParameter("workTypeId"));*/
	    String workTypeId = request.getParameter("workTypeId");
	    String workSubTypeId = request.getParameter("workSubTypeId");
	    
	    
	    
	   /* List<Long> exeOfficelist = Stream.of(exeOfficeId.split(","))
	            .map(Long::parseLong)
	            .collect(Collectors.toList());*/
		
	
		
		

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// defaultR
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		WorkJson workJson=null;
		
		if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {

		 workJson = commonService.getDataForMultiselectStatusWise(pageable, searchBoxVal,office,-1,
				workReqStatusId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		}
	 else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {
		workJson = commonService.getDataForMultiselectStatusWise(pageable, searchBoxVal,-1,office,
					workReqStatusId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
	 }
	 else {
		 workJson = commonService.getDataForMultiselectStatusWise(pageable, searchBoxVal,-1,-1,
					workReqStatusId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
	 }

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchWorkListTwoForChiefReport", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListTwoForChiefReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work Tender List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		/*Long workTypeId = null;*/
		Long workReqStatusId=null;
		Long executionAgencyId=null;
	

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		
		
		executionAgencyId = Long.parseLong(request.getParameter("executionAgencyId"));
		
		
		/*workReqStatusId = Long.parseLong(request.getParameter("workReqStatusId"));*/
	    String exeOfficeId = request.getParameter("exeOfficeId");
	    String supdtOfficeId=request.getParameter("supdtOfficeId");
	    String lineDepartmentId = request.getParameter("lineDepartmentId");
	    String accountHeadId = request.getParameter("accountHeadId");
	    String workStatusId = request.getParameter("workStatusId");
	    /*workTypeId = Long.parseLong(request.getParameter("workTypeId"));*/
	    String workSubTypeId = request.getParameter("workSubTypeId");
	    String workTypeId = request.getParameter("workTypeId");
	    String workStatus=request.getParameter("workStatus");
		/*String officeId=request.getParameter("officeId");*/
		Short isLegacy = (request.getParameter("isLegacy") != null) ? Short.parseShort(request.getParameter("isLegacy"))
				: 0;
	    
	    
	    
	    
	   /* List<Long> exeOfficelist = Stream.of(exeOfficeId.split(","))
	            .map(Long::parseLong)
	            .collect(Collectors.toList());*/
		
	
		
		

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// defaultR
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		Long officeId = null;
		if (null != userBean.getOfficeBean()) {
			officeId = userBean.getOfficeBean().getId();
		}
		if (null != request.getParameter("officeId")) {
			officeId = Long.parseLong(request.getParameter("officeId"));
		}
		
		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		WorkJson workJson=null;
		
		
		 workJson = commonService.fetchWorkListTwoForChiefReport(pageable, searchBoxVal,officeId,workStatus,isLegacy
					,executionAgencyId,exeOfficeId,supdtOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchWorkListTwoForSupdtReport", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListTwoForSupdtReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work Tender List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		/*Long workTypeId = null;*/
		Long workReqStatusId=null;
		Long executionAgencyId=null;
	

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		
		
		executionAgencyId = Long.parseLong(request.getParameter("executionAgencyId"));
		
		
		/*workReqStatusId = Long.parseLong(request.getParameter("workReqStatusId"));*/
	    String exeOfficeId = request.getParameter("exeOfficeId");
	    /*String supdtOfficeId=request.getParameter("supdtOfficeId");*/
	    String lineDepartmentId = request.getParameter("lineDepartmentId");
	    String accountHeadId = request.getParameter("accountHeadId");
	    String workStatusId = request.getParameter("workStatusId");
	    /*workTypeId = Long.parseLong(request.getParameter("workTypeId"));*/
	    String workSubTypeId = request.getParameter("workSubTypeId");
	    String workTypeId = request.getParameter("workTypeId");
	    String workStatus=request.getParameter("workStatus");
		/*String officeId=request.getParameter("officeId");*/
		Short isLegacy = (request.getParameter("isLegacy") != null) ? Short.parseShort(request.getParameter("isLegacy"))
				: 0;
	    
	    
	    
	    
	   /* List<Long> exeOfficelist = Stream.of(exeOfficeId.split(","))
	            .map(Long::parseLong)
	            .collect(Collectors.toList());*/
		
	
		
		

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// defaultR
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		Long officeId = null;
		if (null != userBean.getOfficeBean()) {
			officeId = userBean.getOfficeBean().getId();
		}
		if (null != request.getParameter("officeId")) {
			officeId = Long.parseLong(request.getParameter("officeId"));
		}
		
		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		WorkJson workJson=null;
	
		 workJson = commonService.fetchWorkListTwoForSupdtReport(pageable, searchBoxVal,officeId,workStatus,isLegacy
					,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);


		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchDataForPendingForInspection", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchDataForPendingForInspection(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Pending For Inspection List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		/*Long workTypeId = null;*/
		/*Long exeOfficeId=null;*/
		Long executionAgencyId=null;
	

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		
		
		executionAgencyId = Long.parseLong(request.getParameter("executionAgencyId"));
		
		
		/*exeOfficeId = Long.parseLong(request.getParameter("exeOfficeId"));*/
	  String exeOfficeId = request.getParameter("exeOfficeId");
		
		String fromYear = request.getParameter("fromYear");
		String endYear = request.getParameter("endYear");
	    String lineDepartmentId = request.getParameter("lineDepartmentId");
	    String accountHeadId = request.getParameter("accountHeadId");
	    /*String workStatusId = request.getParameter("workStatusId");*/
	    /*workTypeId = Long.parseLong(request.getParameter("workTypeId"));*/
	    String workTypeId = request.getParameter("workTypeId");
	    String workSubTypeId = request.getParameter("workSubTypeId");
	    
	    
	    
	   /* List<Long> exeOfficelist = Stream.of(exeOfficeId.split(","))
	            .map(Long::parseLong)
	            .collect(Collectors.toList());*/
		
	
		
		

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// defaultR
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		WorkJson workJson=null;
		
		if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {

		 workJson = commonService.getDataForPendingForInspection(pageable, searchBoxVal,office,-1,
				exeOfficeId,executionAgencyId,fromYear,endYear,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		}
	 else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {
		workJson = commonService.getDataForPendingForInspection(pageable, searchBoxVal,-1,office,
				exeOfficeId,executionAgencyId,fromYear,endYear,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
	 }
	 else {
		 workJson = commonService.getDataForPendingForInspection(pageable, searchBoxVal,-1,-1,
				 exeOfficeId,executionAgencyId,fromYear,endYear,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
	 }

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	/*** CR-RESOWMS/CR/4-10
	 * Pending bill reports should have option of Bill pending at & pending from date.
	 * @param request
	 * @return WorkJson as String
	 **/
	@RequestMapping(value = "/fetchDataForFinalBillPending", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchDataForFinalBillPending(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Pending For Inspection List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		/*Long workTypeId = null;*/
		/*Long exeOfficeId=null;*/
		Long executionAgencyId=null;
	

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		
		
		executionAgencyId = Long.parseLong(request.getParameter("executionAgencyId"));
		
		
		/*exeOfficeId = Long.parseLong(request.getParameter("exeOfficeId"));*/
		
		String fromYear = request.getParameter("fromYear");
		String endYear = request.getParameter("endYear");
	    String exeOfficeId = request.getParameter("exeOfficeId");
	    String lineDepartmentId = request.getParameter("lineDepartmentId");
	    String accountHeadId = request.getParameter("accountHeadId");
	    /*String workStatusId = request.getParameter("workStatusId");*/
	    /*workTypeId = Long.parseLong(request.getParameter("workTypeId"));*/
	    String workTypeId = request.getParameter("workTypeId");
	    String workSubTypeId = request.getParameter("workSubTypeId");
	    
	    
	    
	   /* List<Long> exeOfficelist = Stream.of(exeOfficeId.split(","))
	            .map(Long::parseLong)
	            .collect(Collectors.toList());*/
		
	
		
		

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// defaultR
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		WorkJson workJson=null;
		
		if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {

		 workJson = commonService.getDataForFinalBillPending(pageable, searchBoxVal,office,-1,
				exeOfficeId,executionAgencyId,fromYear,endYear,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		}
	 else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {
		workJson = commonService.getDataForFinalBillPending(pageable, searchBoxVal,-1,office,
				exeOfficeId,executionAgencyId,fromYear,endYear,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
	 }
	 else {
		 workJson = commonService.getDataForFinalBillPending(pageable, searchBoxVal,-1,-1,
				 exeOfficeId,executionAgencyId,fromYear,endYear,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
	 }

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchDataForPhysicalCCDispatch", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchDataForPhysicalCCDispatch(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Physical CC Dispatch List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		/*Long workTypeId = null;*/
		Long exeOfficeId=null;
		Long executionAgencyId=null;
	

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		executionAgencyId = Long.parseLong(request.getParameter("executionAgencyId"));
		exeOfficeId = Long.parseLong(request.getParameter("exeOfficeId"));
	    String lineDepartmentId = request.getParameter("lineDepartmentId");
	    String accountHeadId = request.getParameter("accountHeadId");
	    /*workTypeId = Long.parseLong(request.getParameter("workTypeId"));*/
	    String workTypeId = request.getParameter("workTypeId");
	    String workSubTypeId = request.getParameter("workSubTypeId");

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// defaultR
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		WorkJson workJson=null;
		
		if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {

		 workJson = commonService.getDataForPhysicalCCDispatch(pageable, searchBoxVal,office,-1,
				exeOfficeId,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		}
	 else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {
		workJson = commonService.getDataForPhysicalCCDispatch(pageable, searchBoxVal,-1,office,
				exeOfficeId,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
	 }
	 else {
		 workJson = commonService.getDataForPhysicalCCDispatch(pageable, searchBoxVal,-1,-1,
				 exeOfficeId,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
	 }

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	
	
	@RequestMapping(value = "/fetchStatusWiseWorkListSelection", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchStatusWiseWorkListSelection(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching fetchStatusWiseWorkListWithSelection");
		
		String executionAgencyId = request.getParameter("agencyTypeId");
		String exeOfficeId= request.getParameter("exeOfficeId");
		String lineDepartmentId= request.getParameter("lineDepartmentId");
		String accountHeadId= request.getParameter("accountHeadId");
		String workStatusId= request.getParameter("workStatusId");
		/*System.err.println("request.getParameter(\"districtId\")="+request.getParameter("districtId"));*/
		String workTypeId= request.getParameter("workTypeId");
		String workSubTypeId = request.getParameter("workSubTypeId");
		

		List<DashboardBean> result = null;

		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}

		result = getDashboardDataForWorkRequestStatusWiseCountWithSelection(office, user, loggedInUserRole,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	@RequestMapping(value = "/fetchPhysicalCCDispatchListSelection", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchPhysicalCCDispatchListSelection(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching fetchPhysicalCCDispatchListSelection");
		
		String executionAgencyId = request.getParameter("agencyTypeId");
		/*String exeOfficeId= request.getParameter("exeOfficeId");*/
		String lineDepartmentId= request.getParameter("lineDepartmentId");
		String accountHeadId= request.getParameter("accountHeadId");
		/*String workStatusId= request.getParameter("workStatusId");*/
		/*System.err.println("request.getParameter(\"districtId\")="+request.getParameter("districtId"));*/
		String workTypeId= request.getParameter("workTypeId");
		String workSubTypeId = request.getParameter("workSubTypeId");
		

		List<DashboardBean> result = null;

		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}

		result = getDashboardPhysicalCCDispatchCountWithSelection(office, user, loggedInUserRole,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	private List<DashboardBean> getDashboardPhysicalCCDispatchCountWithSelection(Integer officeId, Integer userId,
			String loggedInUserRole, String executionAgencyId, String lineDepartmentId
			,String accountHeadId,String workTypeId,String workSubTypeId) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getDashboardPhysicalCCDispatchCountWithSelection(-1, -1, officeId, -1, -1, -1,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getDashboardPhysicalCCDispatchCountWithSelection(-1, -1, -1, userId, -1, -1,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		}else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// AE
			// login
			result = dashboardService.getDashboardPhysicalCCDispatchCountWithSelection(-1, -1, -1, userId, -1, -1,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																						// login
			result = dashboardService.getDashboardPhysicalCCDispatchCountWithSelection(-1, -1, -1, -1, userId, -1,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getDashboardPhysicalCCDispatchCountWithSelection(-1, officeId, -1, -1, -1, -1,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getDashboardPhysicalCCDispatchCountWithSelection(officeId, -1, -1, -1, -1, -1,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getDashboardPhysicalCCDispatchCountWithSelection(-1, -1, -1, -1, -1, 17,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else {
			result = dashboardService.getDashboardPhysicalCCDispatchCountWithSelection(-1, -1, -1, -1, -1, -1,executionAgencyId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		}
		return result;
	}
	
	
	
	@RequestMapping(value = "/fetchPendingForInspectionListSelection", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchPendingForInspectionListSelection(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching fetchPendingForInspectionListSelection");
		
		String executionAgencyId = request.getParameter("agencyTypeId");
		String exeOfficeId= request.getParameter("exeOfficeId");
		/*String exeOfficeId= request.getParameter("exeOfficeId");*/
		String lineDepartmentId= request.getParameter("lineDepartmentId");
		String accountHeadId= request.getParameter("accountHeadId");
		/*String workStatusId= request.getParameter("workStatusId");*/
		/*System.err.println("request.getParameter(\"districtId\")="+request.getParameter("districtId"));*/
		String workTypeId= request.getParameter("workTypeId");
		String workSubTypeId = request.getParameter("workSubTypeId");
		String financialYearId = request.getParameter("financialYearId");
		

		List<DashboardBean> result = null;
		
		String currentFY=null;
		if(null== request.getParameter("financialYearId")){
			currentFY=RESUtil.getCurrentFinancialFullYear();
		
		}else
		{
			 currentFY = request.getParameter("financialYearId");
		}

		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}

		result = getDashboardPendingForInspectionCountWithSelection(office, user, loggedInUserRole,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchFinalBillPendingListSelection", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchFinalBillPendingListSelection(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching fetchFinalBillPendingListSelection");
		
		String executionAgencyId = request.getParameter("agencyTypeId");
		String exeOfficeId= request.getParameter("exeOfficeId");
		String lineDepartmentId= request.getParameter("lineDepartmentId");
		String accountHeadId= request.getParameter("accountHeadId");
		/*String workStatusId= request.getParameter("workStatusId");*/
		/*System.err.println("request.getParameter(\"districtId\")="+request.getParameter("districtId"));*/
		String workTypeId= request.getParameter("workTypeId");
		String workSubTypeId = request.getParameter("workSubTypeId");
		

		List<DashboardBean> result = null;
		
		String currentFY=null;
		if(null== request.getParameter("financialYearId")){
			currentFY=RESUtil.getCurrentFinancialFullYear();
		
		}else
		{
			 currentFY = request.getParameter("financialYearId");
		}

		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}

		result = getDashboardFinalBillPendingCountWithSelection(office, user, loggedInUserRole,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	
	private List<DashboardBean> getDashboardPendingForInspectionCountWithSelection(Integer officeId, Integer userId,
			String loggedInUserRole,String currentFY, String executionAgencyId,String exeOfficeId, String lineDepartmentId
			,String accountHeadId,String workTypeId,String workSubTypeId) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getDashboardPendingForInspectionCountWithSelection(-1, -1, officeId, -1, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getDashboardPendingForInspectionCountWithSelection(-1, -1, -1, userId, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// AE
			// login
			result = dashboardService.getDashboardPendingForInspectionCountWithSelection(-1, -1, -1, userId, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
			} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																						// login
			result = dashboardService.getDashboardPendingForInspectionCountWithSelection(-1, -1, -1, -1, userId, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																					// login{
			result = dashboardService.getDashboardPendingForInspectionCountWithSelection(-1, officeId, -1, -1, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getDashboardPendingForInspectionCountWithSelection(officeId, -1, -1, -1, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getDashboardPendingForInspectionCountWithSelection(-1, -1, -1, -1, -1, 17,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else {
			result = dashboardService.getDashboardPendingForInspectionCountWithSelection(-1, -1, -1, -1, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		}
		return result;
	}
	
	private List<DashboardBean> getDashboardFinalBillPendingCountWithSelection(Integer officeId, Integer userId,
			String loggedInUserRole,String currentFY, String executionAgencyId,String exeOfficeId, String lineDepartmentId
			,String accountHeadId,String workTypeId,String workSubTypeId) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getDashboardFinalBillPendingCountWithSelection(-1, -1, officeId, -1, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getDashboardFinalBillPendingCountWithSelection(-1, -1, -1, userId, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// SDO
			// login
		result = dashboardService.getDashboardFinalBillPendingCountWithSelection(-1, -1, -1, userId, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getDashboardFinalBillPendingCountWithSelection(-1, -1, -1, -1, userId, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		}  else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getDashboardFinalBillPendingCountWithSelection(-1, officeId, -1, -1, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getDashboardFinalBillPendingCountWithSelection(officeId, -1, -1, -1, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getDashboardFinalBillPendingCountWithSelection(-1, -1, -1, -1, -1, 17,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		} else {
			result = dashboardService.getDashboardFinalBillPendingCountWithSelection(-1, -1, -1, -1, -1, -1,currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workTypeId,workSubTypeId);
		}
		return result;
	}
	
	private List<DashboardBean> getDashboardDataForWorkRequestStatusWiseCountWithSelection(Integer officeId, Integer userId,
			String loggedInUserRole, String executionAgencyId,String exeOfficeId, String lineDepartmentId
			,String accountHeadId,String workStatusId,String workTypeId,String workSubTypeId) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCountWithSelection(-1, -1, officeId, -1, -1, -1,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCountWithSelection(-1, -1, -1, userId, -1, -1,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		}else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// AE
			// login
		result = dashboardService.getDashboardDataForWorkRequestStatusWiseCountWithSelection(-1, -1, -1, userId, -1, -1,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCountWithSelection(-1, -1, -1, -1, userId, -1,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCountWithSelection(-1, officeId, -1, -1, -1, -1,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCountWithSelection(officeId, -1, -1, -1, -1, -1,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCountWithSelection(-1, -1, -1, -1, -1, 17,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else {
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCountWithSelection(-1, -1, -1, -1, -1, -1,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/fetchDataExAgWise", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchDataExAgWise(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work Tender List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		
		Long workTypeId = null;
		
		Long executionAgencyId=null;
		
		
		
		Long eeOfficeId=null;
		

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		
		//String billStatus = request.getParameter("billStatus");
		//String eeOfficeId = request.getParameter("eeOfficeId");
		eeOfficeId = Long.parseLong(request.getParameter("eeOfficeId"));
		//String fromYear = request.getParameter("fromYear");
		//String endYear = request.getParameter("endYear");
		
		String parameter1 = request.getParameter("parameter1");
		/*String lineDeptStr = request.getParameter("lineDepartmentId");*/
		String lineDepartmentId = request.getParameter("lineDepartmentId");;
//		lineDepartmentId = lineDepartmentId.replace('"', ' ');
//		lineDepartmentId = lineDepartmentId.replace('[', ' ');
//		lineDepartmentId = lineDepartmentId.replace(']', ' ');
//		lineDepartmentId = lineDepartmentId.replace(']', ' ');
//		lineDepartmentId = lineDepartmentId.replaceAll(" , ", ",");
//		lineDepartmentId = lineDepartmentId.trim();
		System.out.println(lineDepartmentId);
		
		
		
		/*String accountHeadIdstr = request.getParameter("accountHeadId");*/
		String accountHeadId = request.getParameter("accountHeadId");
		accountHeadId = accountHeadId.replace('"', ' ');
		accountHeadId = accountHeadId.replace('[', ' ');
		accountHeadId = accountHeadId.replace(']', ' ');
		accountHeadId = accountHeadId.replace(']', ' ');
		accountHeadId = accountHeadId.replaceAll(" , ", ",");
		accountHeadId = accountHeadId.trim();
		
		/*String workStatusIdstr = request.getParameter("workStatusId");*/
		String workStatusId = request.getParameter("workStatusId");
		workStatusId = workStatusId.replace('"', ' ');
		workStatusId = workStatusId.replace('[', ' ');
		workStatusId = workStatusId.replace(']', ' ');
		workStatusId = workStatusId.replace(']', ' ');
		workStatusId = workStatusId.replaceAll(" , ", ",");
		workStatusId = workStatusId.trim();
		
		executionAgencyId = Long.parseLong(request.getParameter("executionAgencyId"));
		 workTypeId = Long.parseLong(request.getParameter("workTypeId"));
		
		/*String workSubTypeIdstr = request.getParameter("workSubTypeId");*/
		String workSubTypeId = request.getParameter("workSubTypeId");
		workSubTypeId = workSubTypeId.replace('"', ' ');
		workSubTypeId = workSubTypeId.replace('[', ' ');
		workSubTypeId = workSubTypeId.replace(']', ' ');
		workSubTypeId = workSubTypeId.replace(']', ' ');
		workSubTypeId = workSubTypeId.replaceAll(" , ", ",");
		workSubTypeId = workSubTypeId.trim();
		
		
		
		
      /*		String workTypeId = request.getParameter("workTypeId");
		String workSubTypeId = request.getParameter("workSubTypeId");
		String workLineDepartmentId = request.getParameter("workLineDepartmentId");
		String executionAgencyId = request.getParameter("executionAgencyId");
		String districtId = request.getParameter("districtId");*/
		
	
		
		

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// defaultR
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}
		WorkJson workJson=null;
		
		if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {

		workJson = commonService.getDataExAgWise(pageable, searchBoxVal,office,-1, eeOfficeId, parameter1,lineDepartmentId,accountHeadId,workStatusId,executionAgencyId,workTypeId,workSubTypeId
				);
		}
		else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {
			workJson = commonService.getDataExAgWise(pageable, searchBoxVal,-1,office, eeOfficeId, parameter1,lineDepartmentId,accountHeadId,workStatusId,executionAgencyId,workTypeId,workSubTypeId
					);
			
		}
		 else {
			 workJson = commonService.getDataExAgWise(pageable, searchBoxVal,-1,-1, eeOfficeId, parameter1,lineDepartmentId,accountHeadId,workStatusId,executionAgencyId,workTypeId,workSubTypeId
						);
			 
			
			 
		 }

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	
	
	

	// Rakesh
	@RequestMapping(value = "/fetchWorkTenderHistory/{id}/{administrationSanctionId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkTenderHistory(@PathVariable Long id, @PathVariable Long administrationSanctionId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work Tender List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		Long workType = null;
		Long workSubType = null;
		if (null != request.getParameter("workType")) {
			workType = Long.parseLong(request.getParameter("workType"));
		}
		if (null != request.getParameter("workSubType")) {
			workSubType = Long.parseLong(request.getParameter("workSubType"));
		}

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkJson workJson = commonService.getAllWorkTenderHistory(pageable, searchBoxVal, workType, workSubType, role,
				user.getUsername(), id, administrationSanctionId);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	/** CR-RESOWMS/CR/4-4
	 * Tender Page needs to be freeze. No fields can be edited once it is submitted.
	 * @param id
	 * @param administrationSanctionId
	 * @param loggedInUserRole
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/addTender/{id}/{administrationSanctionId}", method = RequestMethod.GET)
	public ModelAndView addTender(@PathVariable Integer id, @PathVariable Integer administrationSanctionId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add Admin Section Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/addTender");

		return modelAndView;
	}

	@RequestMapping(value = "/addTender", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addTender(@RequestBody WorkTenderBean workTenderBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Adding Technical Sanction data");
		ResponseObject response = new ResponseObject();
		
		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		String errorMsg = commonService.addTender(workTenderBean,role);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Tender added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Tender added successfully!");
		}
		response.setRole(role);
		return response;

	}

	@RequestMapping(value = "/viewTender/{id}/{administrationSanctionId}", method = RequestMethod.GET)
	public ModelAndView viewTender(@PathVariable Integer id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add Admin Section Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/viewTender");

		return modelAndView;
	}

	@RequestMapping(value = "fetchDepositeCategory", method = RequestMethod.GET)
	public List<DepositeCategoryBean> fetchDepositeCategory(HttpServletRequest request) {
		return commonService.fetchDepositeCategory();
	}

	@RequestMapping(value = "/fetchWorkTenderList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkTenderList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Pending Work Estimations");

		String searchBoxVal = request.getParameter("searchBoxVal");
		Long workType = null;
		Long workSubType = null;
		if (null != request.getParameter("workType")) {
			workType = Long.parseLong(request.getParameter("workType"));
		}
		if (null != request.getParameter("workSubType")) {
			workSubType = Long.parseLong(request.getParameter("workSubType"));
		}

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkTenderJson worktenderjson = commonService.getAllWorkTenderList(pageable, searchBoxVal, workType,
				workSubType, role, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(worktenderjson);

		return json;
	}

	@RequestMapping(value = "fetchDepositeType", method = RequestMethod.GET)
	public List<DepositeTypeBean> fetchDepositeType(HttpServletRequest request) {
		return commonService.fetchDepositeType();
	}

	@RequestMapping(value = "fetchBankName", method = RequestMethod.GET)
	public List<BankBean> fetchBankName(HttpServletRequest request) {
		return commonService.fetchBankName();
	}

	@RequestMapping(value = "/printTs/{estimationId}", method = RequestMethod.GET)
	public ModelAndView printTs(@PathVariable String estimationId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Printing Technical Sanction Form  ");

		ModelAndView modelAndView = new ModelAndView("common/technical_sanction_print");
		return modelAndView;

	}

	@RequestMapping(value = "/workAgreementList", method = RequestMethod.GET)
	public ModelAndView workAgreementList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Work Agreement List page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {
			modelAndView = new ModelAndView("common/workAgreementListEE");
		}

		return modelAndView;
	}

	// Rakesh
	@RequestMapping(value = "/workAgreementHistoryList/{workId}/{tenderId}", method = RequestMethod.GET)
	public ModelAndView workAgreementList(HttpServletRequest request, @PathVariable Long workId,
			@PathVariable Long tenderId) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Work Agreement List page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {
			modelAndView = new ModelAndView("common/workAgreementHistoryListEE");
		}

		return modelAndView;
	}

	// Rakesh
	@RequestMapping(value = "/fetchWorkAgreementHistoryList/{workId}/{tenderId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkAgreementHistoryList(HttpServletRequest request, @PathVariable Long workId,
			@PathVariable Long tenderId) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work Agreement List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		Long workType = null;
		Long workSubType = null;
		if (null != request.getParameter("workType")) {
			workType = Long.parseLong(request.getParameter("workType"));
		}
		if (null != request.getParameter("workSubType")) {
			workSubType = Long.parseLong(request.getParameter("workSubType"));
		}

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkAgreementJson workAgreementJson = workAgreementService.fetchWorkAgreementList(pageable, searchBoxVal, role,
				user.getUsername(), workId, tenderId);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workAgreementJson);

		return json;
	}

	@RequestMapping(value = "/fetchWorkAgreementList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkAgreementList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work Agreement List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		Long workType = null;
		Long workSubType = null;
		if (null != request.getParameter("workType")) {
			workType = Long.parseLong(request.getParameter("workType"));
		}
		if (null != request.getParameter("workSubType")) {
			workSubType = Long.parseLong(request.getParameter("workSubType"));
		}

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkAgreementJson workAgreementJson = workAgreementService.fetchWorkAgreementList(pageable, searchBoxVal, role,
				user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workAgreementJson);

		return json;
	}

	@RequestMapping(value = "/addWorkAgreement/{id}", method = RequestMethod.GET)
	public ModelAndView addWorkAgreement(@PathVariable Integer id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add work Agreement Form");

		ModelAndView modelAndView = new ModelAndView("common/addWorkAgreement");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		modelAndView.addObject("loggedInUserRole", loggedInUserRole);

		return modelAndView;
	}

	@RequestMapping(value = "/addWorkAgreement", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addWorkAgreement(@RequestBody WorkAgreementBean workAgreementBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Work Agreement data");
		ResponseObject response = new ResponseObject();

		String errorMsg = workAgreementService.addWorkAgreement(workAgreementBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Work Agreement added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Work Agreement added successfully!");
		}
		return response;

	}

	@RequestMapping(value = "/addRevisedWorkAgreement", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addRevisedWorkAgreement(@RequestBody WorkAgreementBean workAgreementBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Adding Work Agreement Revision data");
		ResponseObject response = new ResponseObject();

		String errorMsg = workAgreementService.addRevisedWorkAgreement(workAgreementBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Work Agreement Revision added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Work Agreement Revision added successfully!");
		}
		return response;

	}

	@RequestMapping(value = "/updateWorkAgreement", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject updateWorkAgreement(@RequestBody WorkAgreementBean workAgreementBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Work Agreement data");
		ResponseObject response = new ResponseObject();

		String errorMsg = workAgreementService.updateWorkAgreement(workAgreementBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Work Agreement added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Work Agreement added successfully!");
		}
		return response;

	}

	@RequestMapping(value = "/saveWorkAgreementData", method = RequestMethod.POST)
	public ResponseObject saveWorkAgreementData(@RequestBody WorkAgreementBean workAgreementBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Work Agreement data");
		ResponseObject response = new ResponseObject();
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		String errorMsg = workAgreementService.addWorkAgreement(workAgreementBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Work Estimation added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Work Estimation added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/editWorkAgreement/{id}", method = RequestMethod.GET)
	public ModelAndView editWorkAgreement(@PathVariable Integer id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add work Agreement Form");

		ModelAndView modelAndView = new ModelAndView("common/editWorkAgreement");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		modelAndView.addObject("loggedInUserRole", loggedInUserRole);

		return modelAndView;
	}

	@RequestMapping(value = "/printTsTwo/{workId}/{workEstimationId}", method = RequestMethod.GET)
	public ModelAndView printTsTwo(@PathVariable String workId, @PathVariable String workEstimationId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Printing Technical Sanction Form  ");

		ModelAndView modelAndView = new ModelAndView("common/technical_sanction_print_two");
		return modelAndView;

	}

	@RequestMapping(value = "fetchWorkDetailsForPrintTs/{id}/{workEstimationId}", method = RequestMethod.GET)
	public WorkBean fetchWorkDetails(@PathVariable Long id, @PathVariable Long workEstimationId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id");
		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkBean workDetails = commonService.fetchWorkDetailsForPrintTs(id, workEstimationId);

		workDetails.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		Users users = userService.findByUserName(user.getUsername());

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		workDetails.setLoggedInUserRole(role);

		if (role.equals(RESConstants.ROLE_EE)) {

			String anukramNo = null;
			anukramNo = "कार्यपालन यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String anukramNo = null;
			anukramNo = "मुख्य अभियंता, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_AE)) {

			String anukramNo = null;
			anukramNo = "Assistant Engineer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SDO)) {

			String anukramNo = null;
			anukramNo = "Sub-divisional Officer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);
		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String anukramNo = null;
			anukramNo = "अधीक्षण यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_EE)) {

			String yantriName = null;
			yantriName = "कार्यपालन यंत्री ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String yantriName = null;
			yantriName = "मुख्य अभियंता ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String yantriName = null;
			yantriName = "अधीक्षण यंत्री ";
			workDetails.setYantriName(yantriName);

		}

		return workDetails;
	}

	@RequestMapping(value = "/saveEditWorkAgreement/{id}", method = RequestMethod.GET)
	public ModelAndView saveEditWorkAgreement(@RequestBody WorkAgreementBean workAgreementBean,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add work Agreement Form");

		ModelAndView modelAndView = new ModelAndView("common/editWorkAgreement");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		modelAndView.addObject("loggedInUserRole", loggedInUserRole);

		return modelAndView;
	}

	@RequestMapping(value = "/viewWorkAgreement/{id}", method = RequestMethod.GET)
	public ModelAndView viewWorkAgreement(@PathVariable Integer id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add work Agreement Form");

		ModelAndView modelAndView = new ModelAndView("common/viewWorkAgreement");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		modelAndView.addObject("loggedInUserRole", loggedInUserRole);

		return modelAndView;
	}

	// Rakesh
	@RequestMapping(value = "/viewWorkAgreementByTenderId/{workId}/{tenderId}", method = RequestMethod.GET)
	public ModelAndView viewWorkAgreementByTenderId(@PathVariable Long workId, @PathVariable Long tenderId,
			HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add work Agreement Form");

		ModelAndView modelAndView = new ModelAndView("common/viewWorkAgreementByTender");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		modelAndView.addObject("loggedInUserRole", loggedInUserRole);

		return modelAndView;
	}

	@RequestMapping(value = "fetchWorkAgreementDetails/{id}", method = RequestMethod.GET)
	public WorkAgreementBean fetchWorkAgreementDetails(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");
		return workAgreementService.fetchWorkAgreementDetails(id, true);
	}

	@RequestMapping(value = "fetchWorkAgreementDetailsByTenderId/{id}", method = RequestMethod.GET)
	public WorkAgreementBean fetchWorkAgreementDetailsByTenderId(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");
		return workAgreementService.fetchWorkAgreementDetailsByTenderId(id, true);
	}

	@RequestMapping(value = "fetchMilestoneRevisionHistory/{workAgreementId}", method = RequestMethod.GET)
	public List<WorkAgreementRevisionBean> fetchMilestoneRevisionHistory(@PathVariable Long workAgreementId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");
		return workAgreementService.fetchMilestoneRevisionByWorkAgreementId(workAgreementId);
	}

	@RequestMapping(value = "fetchWorkAgreementRevisionDetails/{revisionId}", method = RequestMethod.GET)
	public WorkAgreementRevisionBean fetchWorkAgreementRevisionDetails(@PathVariable Long revisionId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bill data");
		return workAgreementService.fetchMilestoneRevisionByWorkAgreementRevisionId(revisionId);
	}

	@RequestMapping(value = "/printTs/{workId}/{workEstimationId}", method = RequestMethod.GET)
	public ModelAndView printTs(@PathVariable String workId, @PathVariable String workEstimationId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Printing Technical Sanction Form  ");
		/*
		 * WorkBean workBean = new WorkBean(); workBean.setWorkId(Long.parseLong(id));
		 * 
		 * commonService.editWorkRequestStatusById(workBean);
		 */

//		commonService.changeTechnicalSanctionStatus(estimationId);

		ModelAndView modelAndView = new ModelAndView("common/technical_sanction_print");
		return modelAndView;

	}

	@RequestMapping(value = "/fetchAllTechnicalWorks", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchAllTechnicalWorks(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */
		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");
		HashMap<String, Long> hmap = new HashMap<String, Long>();
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}
			// nikhil

			else if (sColName.equals("workRequisitionNo")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequisitionNo"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequisitionNo"));
				}
			}

			else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

			else if (sColName.equals("workRequestStatusId")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequestStatusId"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequestStatusId"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		WorkEstimationJson workJson = commonService.getAllWorksByTechnicalTwo(pageable, role,
				userBean.getOfficeBean().getId(), user.getUsername());

		// request.setAttribute("iTotal", workJson.getAaData().size());
//	/	workJson.setiTotalDisplayRecords(5);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchWorkListBySqmInspection", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListBySqmInspection(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */
		
		
		
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		String exeOfficeId = request.getParameter("exeOfficeId");
		String workStatusId = request.getParameter("workStatusId");
		String workTypeId = request.getParameter("workTypeId");
		String sqmId = request.getParameter("sqmId");
		String grading = request.getParameter("grading");
		

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}
			// nikhil

			
			

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		InspectionSqmAnswerJson workJson = commonService.getAllWorksBySqmInspection(pageable, role, exeOfficeId, workStatusId,workTypeId,sqmId,grading,
				userBean.getOfficeBean().getId(), user.getUsername());

		// request.setAttribute("iTotal", workJson.getAaData().size());
//	/	workJson.setiTotalDisplayRecords(5);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	//OFFICER INSPECTION BY nikhil
	@RequestMapping(value = "/fetchWorkListByOfficerInspection", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListByOfficerInspection(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Officer Inspection List");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */
		
		
		
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		
		String exeOfficeId = request.getParameter("exeOfficeId");
		String workStatusId = request.getParameter("workStatusId");
		String workTypeId = request.getParameter("workTypeId");
		String sqmId = request.getParameter("sqmId");
		String grading = request.getParameter("grading");
		

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}
			// nikhil

			
			

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		InspectionSqmAnswerJson workJson = commonService.getAllWorksByOfficerInspection(pageable, role,exeOfficeId,workStatusId,workTypeId,sqmId,grading,
				userBean.getOfficeBean().getId(), user.getUsername());

		// request.setAttribute("iTotal", workJson.getAaData().size());
//	/	workJson.setiTotalDisplayRecords(5);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "/editAdminSanction/{workId}/{technicalSanctionId}", method = RequestMethod.GET)
	public ModelAndView editAdminSanction(@PathVariable String workId, @PathVariable String technicalSanctionId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit Admin Sanction Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/editAdminSanction");

		return modelAndView;
	}

	@RequestMapping(value = "/addAdminSanction", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addAdminSanction(WorkBean workBean, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Admin Sanction data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.addAdminSanction(workBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Admin Sanction Added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Admin sanction added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "fetchWorkDetailsByEstimationAndParent/{id}/{estimationId}/{parentId}", method = RequestMethod.GET)
	public WorkBean fetchWorkDetailsByEstimationAndParent(@PathVariable Long id, @PathVariable Long estimationId,
			@PathVariable Long parentId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id ");

		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkBean workDetails = commonService.fetchWorkDetailsInEstimationByParentId(id, estimationId, parentId);

		workDetails.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		Users users = userService.findByUserName(user.getUsername());

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		workDetails.setLoggedInUserRole(role);

		if (role.equals(RESConstants.ROLE_EE)) {

			String anukramNo = null;
			anukramNo = "कार्यपालन यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);
		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String anukramNo = null;
			anukramNo = "मुख्य अभियंता, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_AE)) {

			String anukramNo = null;
			anukramNo = "Assistant Engineer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SDO)) {

			String anukramNo = null;
			anukramNo = "Sub-divisional Officer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);
		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String anukramNo = null;
			anukramNo = "अधीक्षण यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_EE)) {

			String yantriName = null;
			yantriName = "कार्यपालन यंत्री ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String yantriName = null;
			yantriName = "मुख्य अभियंता ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String yantriName = null;
			yantriName = "अधीक्षण यंत्री ";
			workDetails.setYantriName(yantriName);

		}

		return workDetails;
	}

	// Rakesh Working
	@RequestMapping(value = "fetchWorkDetailsByEstimation/{id}/{estimationId}", method = RequestMethod.GET)
	public WorkBean fetchWorkDetailsInEstimation(@PathVariable Long id, @PathVariable Long estimationId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id");
		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkBean workDetails = commonService.fetchWorkDetailsInEstimation(id, estimationId);

		workDetails.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		Users users = userService.findByUserName(user.getUsername());

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		workDetails.setLoggedInUserRole(role);

		if (role.equals(RESConstants.ROLE_EE)) {

			String anukramNo = null;
			anukramNo = "कार्यपालन यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);
		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String anukramNo = null;
			anukramNo = "मुख्य अभियंता, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_AE)) {

			String anukramNo = null;
			anukramNo = "Assistant Engineer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SDO)) {

			String anukramNo = null;
			anukramNo = "Sub-divisional Officer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);
		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String anukramNo = null;
			anukramNo = "अधीक्षण यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_EE)) {

			String yantriName = null;
			yantriName = "कार्यपालन यंत्री ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String yantriName = null;
			yantriName = "मुख्य अभियंता ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String yantriName = null;
			yantriName = "अधीक्षण यंत्री ";
			workDetails.setYantriName(yantriName);

		}

		return workDetails;
	}

	@RequestMapping(value = "fetchWorkDetailsByTechnical/{id}/{technicalSanctionId}", method = RequestMethod.GET)
	public WorkBean fetchWorkDetailsInTechnical(@PathVariable Long id, @PathVariable Long technicalSanctionId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id");
		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkBean workDetails = commonService.fetchWorkDetailsInTechnical(id, technicalSanctionId);

		workDetails.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		Users users = userService.findByUserName(user.getUsername());

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		workDetails.setLoggedInUserRole(role);

		if (role.equals(RESConstants.ROLE_EE)) {

			String anukramNo = null;
			anukramNo = "कार्यपालन यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String anukramNo = null;
			anukramNo = "मुख्य अभियंता, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_AE)) {

			String anukramNo = null;
			anukramNo = "Assistant Engineer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SDO)) {

			String anukramNo = null;
			anukramNo = "Sub-divisional Officer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);
		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String anukramNo = null;
			anukramNo = "अधीक्षण यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_EE)) {

			String yantriName = null;
			yantriName = "कार्यपालन यंत्री ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String yantriName = null;
			yantriName = "मुख्य अभियंता ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String yantriName = null;
			yantriName = "अधीक्षण यंत्री ";
			workDetails.setYantriName(yantriName);

		}

		return workDetails;
	}

	@RequestMapping(value = "fetchFinancialMileStone", method = RequestMethod.GET)
	public List<FinancialStageTypeBean> fetchFinancialMileStone(HttpServletRequest request) {
		return workAgreementService.fetchFinancialMileStone();
	}

	/** CR-RESOWMS/CR/3-1
	 * District filter is not available in WorkFile in admin and admin_view ids, 
	 * and other filters like block, village etc-Create New Office and shift the works.
	 * @param workId
	 * * @param filter
	 * @return List<WorkFileBean>
	 */
	@RequestMapping(value = "/workFile", method = RequestMethod.GET)
	public ModelAndView workFile(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying work list for Completion Certificate");
		ModelAndView modelAndView = new ModelAndView("common/viewWorkFile");
		return modelAndView;
	}

	@RequestMapping(value = "/cc/workList", method = RequestMethod.GET)
	public ModelAndView ccWorkList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying work list for Completion Certificate");
		ModelAndView modelAndView = new ModelAndView("ee/cc/workList");
		return modelAndView;
	}

	@RequestMapping(value = "/cc/workDetails/{workId}", method = RequestMethod.GET)
	public ModelAndView ccWorkDetails(@PathVariable Long workId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying work Details for Completion Certificate");
		ModelAndView modelAndView = new ModelAndView("ee/cc/workDetails");
		return modelAndView;
	}

	@RequestMapping(value = "/cc/initiateCC/{workId}", method = RequestMethod.GET)
	public ModelAndView ccInitiateCC(@PathVariable Long workId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying work Details for Completion Certificate");
		ModelAndView modelAndView = new ModelAndView("ee/cc/initiateCC");
		return modelAndView;
	}

	@RequestMapping(value = "/cc/physicalCCList", method = RequestMethod.GET)
	public ModelAndView ccPhysicalCCList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying work Details for Physical Completion Certificate");
		ModelAndView modelAndView = new ModelAndView("ee/cc/physicalCCList");
		return modelAndView;
	}

	@RequestMapping(value = "/cc/financialCCList", method = RequestMethod.GET)
	public ModelAndView ccFinancialCCList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying work Details for Financial Completion Certificate");
		ModelAndView modelAndView = new ModelAndView("ee/cc/financialCCList");
		return modelAndView;
	}

	@RequestMapping(value = "/fetchPhysicalCCList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchPhysicalCCList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work for Physical CC List");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkJson workJson = workCCServiceImpl.fetchWorkPhysicalCCList(pageable, searchBoxVal, role, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "/fetchFinancialCCList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchFinancialCCList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work for Financial CC List");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkJson workJson = workCCServiceImpl.fetchWorkFinancialCCList(pageable, searchBoxVal, role,
				user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "fetchStandardTemplateTypesByWorkTypeId", method = RequestMethod.GET)
	public List<StandardTemplateTypeBean> fetchStandardTemplateTypesByWorkTypeId(
			@RequestParam(value = "workTypeId", required = true) Long workTypeId, HttpServletRequest request) {
		return commonService.fetchStandardTemplateTypesByWorkTypeId(workTypeId);
	}

	@RequestMapping(value = "/addPhysicalCCDispatchDetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addPhysicalCCDispatchDetails(CCDispatchDetailsBean ccDispatchDetailsBean,
			HttpServletRequest request) throws Exception {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Adding Physical CC Dispatch Details data");
		ResponseObject response = new ResponseObject();

		String errorMsg = workCCServiceImpl.addPhysicalCCDispatchDetails(ccDispatchDetailsBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Physical CC Dispatch Details added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Physical CC Dispatch Details added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/addFinancialCCDispatchDetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addFinancialCCDispatchDetails(CCDispatchDetailsBean ccDispatchDetailsBean,
			HttpServletRequest request) throws Exception {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Adding Financial CC Dispatch Details data");
		ResponseObject response = new ResponseObject();

		String errorMsg = workCCServiceImpl.addFinancialCCDispatchDetails(ccDispatchDetailsBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Financial CC Dispatch Details added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Financial CC Dispatch Details added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "fetchPhysicalCCDispatchDetails/{workid}", method = RequestMethod.GET)
	public CCDispatchDetailsBean fetchPhysicalCCDispatchDetails(@PathVariable Long workid, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Physical CC Dispatch data by Work Id");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		CCDispatchDetailsBean ccDispatchDetailsBean = workCCServiceImpl.fetchPhysicalCCDispatchDetailsByWorkId(workid);
		return ccDispatchDetailsBean;
	}

	@RequestMapping(value = "fetchFinancialCCDispatchDetails/{workid}", method = RequestMethod.GET)
	public CCDispatchDetailsBean fetchFinancialCCDispatchDetails(@PathVariable Long workid,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Financial CC Dispatch data by Work Id");
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		CCDispatchDetailsBean ccDispatchDetailsBean = workCCServiceImpl.fetchFinancialCCDispatchDetailsByWorkId(workid);
		return ccDispatchDetailsBean;
	}

	@RequestMapping(value = "/issuePhysicalCC", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject issuePhysicalCC(@RequestBody Long workId, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Issuing Physical CC");
		ResponseObject response = new ResponseObject();

		String errorMsg = workCCServiceImpl.issuePhysicalCCForWorkId(workId);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Physical CC Issued successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Physical CC Issued successfully!");
		}
		return response;

	}

	@RequestMapping(value = "/issueFinancialCC", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject issueFinancialCC(@RequestBody Long workId, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Issuing Financial CC");
		ResponseObject response = new ResponseObject();

		String errorMsg = workCCServiceImpl.issueFinancialCCForWorkId(workId);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Financial CC Issued successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Financial CC Issued successfully!");
		}
		return response;

	}

	@RequestMapping(value = "/cc/printPhysicalCC/{workId}", method = RequestMethod.GET)
	public ModelAndView printPhysicalCC(@PathVariable Long workId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Printing Physical CC");
		ModelAndView modelAndView = new ModelAndView("ee/cc/printPhysicalCC");
		return modelAndView;
	}

	@RequestMapping(value = "/cc/printFinancialCC/{workId}", method = RequestMethod.GET)
	public ModelAndView printFinancialCC(@PathVariable Long workId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Printing Financial CC");
		ModelAndView modelAndView = new ModelAndView("ee/cc/printFinancialCC");
		return modelAndView;
	}

	/// richa
	@RequestMapping(value = "/fetchCCInspectionList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchCCInspectionList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bills");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkJson workJson = commonService.fetchCCInspectionList(pageable, searchBoxVal, role, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	@RequestMapping(value = "/viewGeneralInspectionByWorkId/{workId}", method = RequestMethod.GET)
	public ModelAndView viewGeneralInspectionByWorkId(HttpServletRequest request, @PathVariable Long workId) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying General Inspection List  page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("common/viewGeneralInspectionByWorkId");

		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchWorkListGeneralInspectionByWorkId/{workId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListGeneralInspectionByWorkId(@PathVariable Long workId,HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Officer Inspction List");

		/*
		 * String searchBoxVal = request.getParameter("searchBoxVal"); String
		 * designation = request.getParameter("designation"); String status =
		 * request.getParameter("status");
		 */
		
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}
			// nikhil

			
			

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		SqmAllocationJson workJson = commonService.getAllWorksByGeneralInspectionByWorkId(pageable, role,
				userBean.getOfficeBean().getId(), user.getUsername(),workId);

		// request.setAttribute("iTotal", workJson.getAaData().size());
//	/	workJson.setiTotalDisplayRecords(5);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	

	@RequestMapping(value = "/fetchGeneralInspectionList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchGeneralInspectionList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Bills");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkJson workJson = commonService.fetchGeneralInspectionList(pageable, searchBoxVal, role, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	@RequestMapping(value = "/cc/initiateCCSubmit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject initiateCCSubmit(@RequestBody CCDetailsBean ccDetailsBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying work Details for Completion Certificate");

		ResponseObject response = new ResponseObject();

		String errorMsg = workCCServiceImpl.initiateCCSubmit(ccDetailsBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("CC Initiated successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - CC Initiated successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/viewInspectionCC/{id}", method = RequestMethod.GET)
	public ModelAndView viewInspectionCC(HttpServletRequest request, @PathVariable Long id) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Inspection List page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/cc/viewInspectionCC");

		return modelAndView;
	}

	@RequestMapping(value = "/fetchInspectionCCData/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerNewBean> fetchInspectionCCData(@PathVariable Long id) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - fetchInspectionData called");
		List<InspectionAnswerNewBean> list = commonService.fetchInspectionAnswerByBillIdNew(id);
		return list;
	}
	
	@RequestMapping(value = "/fetchInspectionCCDataFromFinalBill/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswer> fetchInspectionCCDataFromFinalBill(@PathVariable Long id) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - fetchInspectionCCDataFromFinalBill called");
		List<InspectionAnswer> list = commonService.fetchInspectionAnswerEEByWorkId(id);
		return list;
	}
	

	@RequestMapping(value = "/fetchInspectionCCImages/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerImageBean> fetchInspectionCCImages(@PathVariable Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - fetchInspectionImages called");

		List<InspectionAnswerImageBean> list = commonService.fetchInspectionAnswerImageByBillIdNew(id);
		return list;
	}
	
	@RequestMapping(value = "/fetchInspectionCCImagesFromFinalBill/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<InspectionAnswerImage> fetchInspectionCCImagesFromFinalBill(@PathVariable Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - fetchInspectionCCImagesFromFinalBill called");

		List<InspectionAnswerImage> list = commonService.fetchInspectionAnswerImageEEByWorkId(id);
		return list;
	}

	@RequestMapping(value = "/fwdForPhysicalCC/{id}", method = RequestMethod.POST)
	public ResponseObject fwdForPhysicalCC(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - fwdForPhysicalCC called ");
		ResponseObject response = new ResponseObject();

		commonService.updateWorkStatus(id, RESConstants.REQUEST_STATUS_Fwd_for_Physical_CC_ID);
		commonService.updateCCDetails(id, true);

		response.setSuccessMessage("Forwarded Physical CC successfully!");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Forwarded Physical CC successfully!");

		return response;

	}

	@RequestMapping(value = "/CCReject", method = RequestMethod.POST)
	public ResponseObject ccReject(@RequestBody CCDetailsBean ccDetailsBean, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - ccReject called ");
		ResponseObject response = new ResponseObject();

		commonService.updateWorkStatus(ccDetailsBean.getWorkId(), RESConstants.REQUEST_STATUS_CC_Rejected_ID);
		commonService.updateCCDetails(ccDetailsBean, false);

		response.setSuccessMessage("Certificate Completion Rejected !");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Certificate Completion Rejected!");

		return response;

	}

	@RequestMapping(value = "/viewStatusWiseWorkReport", method = RequestMethod.GET)
	public ModelAndView viewStatusWiseWorkReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying viewStatusWiseWorkReport");

		ModelAndView modelAndView = new ModelAndView("common/viewStatusWiseWorkReport");
		;

		return modelAndView;
	}
	
	@RequestMapping(value = "/viewStatusWiseWorkReportWithSelection", method = RequestMethod.GET)
	public ModelAndView viewStatusWiseWorkReportWithSelection(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying viewStatusWiseWorkReport");

		ModelAndView modelAndView = new ModelAndView("common/viewStatusWiseWorkReportWithSelection");
		;

		return modelAndView;
	}
	
	@RequestMapping(value = "/viewPendingForInspectionReport", method = RequestMethod.GET)
	public ModelAndView viewPendingForInspectionReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying viewPendingForInspectionReport");

		ModelAndView modelAndView = new ModelAndView("common/viewPendingForInspectionReport");
		;

		return modelAndView;
	}
	
	@RequestMapping(value = "/viewFinalBillPendingReport", method = RequestMethod.GET)
	public ModelAndView viewFinalBillPendingnReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying viewPendingForInspectionReport");

		ModelAndView modelAndView = new ModelAndView("common/viewFinalBillPendingReport");
		;

		return modelAndView;
	}
	
	@RequestMapping(value = "/viewPhysicalCCDispatchReport", method = RequestMethod.GET)
	public ModelAndView viewPhysicalCCDispatchReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying viewPhysicalCCDispatchReport");

		ModelAndView modelAndView = new ModelAndView("common/viewPhysicalCCDispatchReport");
		;

		return modelAndView;
	}

	/*
	 * @RequestMapping(value = "/viewEstimationStatusWiseWorkReport", method =
	 * RequestMethod.GET) public ModelAndView
	 * viewEstimationStatusWiseWorkReport(HttpServletRequest request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() +
	 * " - Displaying viewEstimationStatusWiseWorkReport");
	 * 
	 * ModelAndView modelAndView = new
	 * ModelAndView("common/viewEstimationStatusWiseWorkReport");;
	 * 
	 * return modelAndView; }
	 */

	@RequestMapping(value = "/fetchStatusWiseWorkList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchStatusWiseWorkList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching fetchStatusWiseWorkList");

		List<DashboardBean> result = null;

		UserBean userDetail = fetchLoggedInUserDetails(request);
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		Integer office = null;
		if (null != userDetail.getOfficeId()) {
			office = userDetail.getOfficeId().intValue();
		}
		Integer user = null;
		if (null != userDetail.getId()) {
			user = userDetail.getId().intValue();
		}

		result = getDashboardDataForWorkRequestStatusWiseCount(office, user, loggedInUserRole);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	

	private List<DashboardBean> getDashboardDataForWorkRequestStatusWiseCount(Integer officeId, Integer userId,
			String loggedInUserRole) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(-1, -1, officeId, -1, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(-1, -1, -1, userId, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(-1, -1, -1, -1, userId, -1);
		}
		else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// SubE
			// login
		result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(-1, -1, -1, -1, userId, -1);
		}else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(-1, officeId, -1, -1, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(officeId, -1, -1, -1, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(-1, -1, -1, -1, -1, 17);
		} else {
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(-1, -1, -1, -1, -1, -1);
		}
		return result;
	}
	
	/*** CR-RESOWMS/CR/4-9
	 * Show these details on click of view all works details. AS amount, 
	 * Contract Amount and Expenditure amount.
	 * @param request
	 * @return ModelAndView
	 **/
	@RequestMapping(value = "/viewAllWorks", method = RequestMethod.GET)
	public ModelAndView addBudgetRequest(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - getting View All Work List  ");

		ModelAndView modelAndView = new ModelAndView("common/viewAllWorks");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		modelAndView.addObject("workStatus", "total");
		modelAndView.addObject("isLegacy", -1);
		modelAndView.addObject("loggedInUserRole", loggedInUserRole);

		return modelAndView;

	}
	
	/*** CR-RESOWMS/CR/4-9
	 * Show these details on click of view all works details. AS amount, 
	 * Contract Amount and Expenditure amount.
	 * @param request
	 * @return ModelAndView
	 **/
	@RequestMapping(value = "/viewAllWorksForSupdt", method = RequestMethod.GET)
	public ModelAndView viewAllWorksForSupdt(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - getting View All Work List  ");

		ModelAndView modelAndView = new ModelAndView("common/viewAllWorksForSupdt");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		modelAndView.addObject("workStatus", "total");
		modelAndView.addObject("isLegacy", -1);

		return modelAndView;

	}
	
	/*** CR-RESOWMS/CR/4-9
	 * Show these details on click of view all works details. AS amount, 
	 * Contract Amount and Expenditure amount.
	 * @param request
	 * @return ModelAndView
	 **/
	@RequestMapping(value = "/viewAllWorksForSubAe", method = RequestMethod.GET)
	public ModelAndView viewAllWorksForSubAe(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - getting View All Work List  ");

		ModelAndView modelAndView = new ModelAndView("common/viewAllWorksForSubAe");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		modelAndView.addObject("workStatus", "total");
		modelAndView.addObject("isLegacy", -1);

		return modelAndView;

	}

	@RequestMapping(value = "/fetchWorkListForCC", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListForCC(HttpServletRequest request) throws RESBusinessException {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default sorting
		}
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		Long officeId = null;
		if (null != userBean.getOfficeBean()) {
			officeId = userBean.getOfficeBean().getId();
		}
		if (null != request.getParameter("officeId")) {
			officeId = Long.parseLong(request.getParameter("officeId"));
		}

		WorkJson workJson = commonService.fetchWorkListForCC(pageable, role, officeId, userBean.getId());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;

	}

	@RequestMapping(value = "/manageAccountHead", method = RequestMethod.GET)
	public ModelAndView manageAccountHead(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Manage Users page");
		ModelAndView modelAndView = new ModelAndView("common/manageAccountHead");
		return modelAndView;
	}

	@RequestMapping(value = "/manageLineDepartment", method = RequestMethod.GET)
	public ModelAndView manageLineDepartment(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Manage Users page");
		ModelAndView modelAndView = new ModelAndView("common/manageLineDepartment");
		return modelAndView;
	}

	@RequestMapping(value = "/fetchAccountHeadList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchAccountHeadList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User List");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		AccountHeadJson accountHeadJson = commonService.fetchAccountHead(pageable);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(accountHeadJson);

		return json;
	}

	@RequestMapping(value = "/fetchLineDepartmentList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchLineDepartmentList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User List");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId"));// default sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		LineDepartmentJson lineDepartmentJson = commonService.fetchLineDepartment(pageable);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(lineDepartmentJson);

		return json;
	}

	@RequestMapping(value = "/addAccountHead", method = RequestMethod.POST)
	public ResponseObject addAccountHead(@RequestBody AccountHeadBean accountHeadBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Bill data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.addAccountHead(accountHeadBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Account Head added successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Account Head added successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/updateAeName", method = RequestMethod.POST)
	public ResponseObject updateAeName(@RequestBody BillBean billBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Inspected User Updated data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.updateAeSubNAme(billBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Inspected User Updated successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Inspected User Updated successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/updateAeEeName", method = RequestMethod.POST)
	public ResponseObject updateAeEeName(@RequestBody BillBean billBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Inspected User Updated data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.updateAeEeSubNAme(billBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Inspected User Updated successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Inspected User Updatedsuccessfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/updateSESDOName", method = RequestMethod.POST)
	public ResponseObject updateSESDOName(@RequestBody BillBean billBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Inspected User Updated data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.updateSeSdoName(billBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Inspected User Updated successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Inspected User Updatedsuccessfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/updateSESDOEEName", method = RequestMethod.POST)
	public ResponseObject updateSESDOEEName(@RequestBody BillBean billBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Inspected User Updated data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.updateSeSdoEeName(billBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Inspected User Updated successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Inspected User Updatedsuccessfully!");
		}
		return response;
	}
	
	
	@RequestMapping(value = "/updateBillStatusRemarks", method = RequestMethod.POST)
	public ResponseObject updateBillStatusRemarks(@RequestBody BillBean billBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Inspected Bill Status");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.updateBillStatusRemarks(billBean,user.getUsername());
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Reject And Revise Bill successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Reject And Revise Bill successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/deleteBillRemarks", method = RequestMethod.POST)
	public ResponseObject deleteBillRemarks(@RequestBody BillBean billBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - delete Bill");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.deleteBillRemarks(billBean,user.getUsername());
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Delete Bill successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Delete Bill successfully!");
		}
		return response;
	}


	@RequestMapping(value = "/addLineDepartment", method = RequestMethod.POST)
	public ResponseObject addLineDepartment(@RequestBody LineDepartmentBean lineDepartmentBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Bill data");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.addLineDepartment(lineDepartmentBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Line Department   added successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Line Department  added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "fetchAccountHeadById/{id}", method = RequestMethod.GET)
	public AccountHeadBean FetchAccountHeadBean(@PathVariable Long id, HttpServletRequest request) {
		return commonService.FetchAccountHeadBean(id);
	}

	@RequestMapping(value = "fetchLineDepartmentById/{id}", method = RequestMethod.GET)
	public LineDepartmentBean fetchLineDepartmentById(@PathVariable Long id, HttpServletRequest request) {
		return commonService.fetchLineDepartmentById(id);
	}

	@RequestMapping(value = "/manageContractor", method = RequestMethod.GET)
	public ModelAndView manageContractor(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Manage Contractor page");
		ModelAndView modelAndView = new ModelAndView("common/manageContractor");
		return modelAndView;
	}

	@RequestMapping(value = "/fetchContractorList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchContractorList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User List");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		ContractorJson contractorJson = commonService.fetchContractorList(pageable);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(contractorJson);

		return json;
	}

	@RequestMapping(value = "/addContractorForm", method = RequestMethod.GET)
	public ModelAndView addContractorForm() {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add New Bill Form");

		ModelAndView modelAndView = new ModelAndView("common/addContractorForm");

		return modelAndView;
	}

	@RequestMapping(value = "/addContractor", method = RequestMethod.POST)
	public ResponseObject addContractor(@RequestBody ContractorBean contractorBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Calling addContractor");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.addContractor(contractorBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Contractor added successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Contractor added successfully!");
		}
		return response;
	}

	@RequestMapping(value = "/editContractorForm/{id}", method = RequestMethod.GET)
	public ModelAndView editContractorForm(@PathVariable(value = "id") Long id) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit addContractor");

		ModelAndView modelAndView = new ModelAndView("common/editContractorForm");

		return modelAndView;
	}

	@RequestMapping(value = "/editContractor", method = RequestMethod.POST)
	public ResponseObject editContractor(@RequestBody ContractorBean contractorBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Calling editContractor");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.addContractor(contractorBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Contractor Edited successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Contractor Edited successfully!");
		}
		return response;
	}

	@RequestMapping(value = "fetchContractorById/{id}", method = RequestMethod.GET)
	public ContractorBean fetchContractorBean(@PathVariable Long id, HttpServletRequest request) {
		return commonService.fetchContractorBean(id);
	}

	@RequestMapping(value = "/manageBank", method = RequestMethod.GET)
	public ModelAndView manageBank(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Manage Bank page");
		ModelAndView modelAndView = new ModelAndView("common/manageallbanks");
		return modelAndView;
	}

	@RequestMapping(value = "/addBankForm", method = RequestMethod.GET)
	public ModelAndView viewAddBankForm(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - {}, Role - {} - Displaying Add Entrepreneur Form", user.getUsername(),
				user.getAuthorities());
		ModelAndView modelAndView = new ModelAndView("common/addBankForm");
		return modelAndView;

	}

	// richa
	@RequestMapping(value = "/addBank", method = RequestMethod.POST)
	public ResponseObject addBank(@RequestBody BankBean bankBean, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - {}, Role - {} - Adding Bank data", user.getUsername(), user.getAuthorities());
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.addBank(bankBean);
		if (errorMsg == null) {
			response.setSuccessMessage("Bank added successfully !");
		} else {
			response.setErrorMessage(errorMsg);
		}
		return response;
	}

	@RequestMapping(value = "/fetchBankList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBankList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - {}, Role - {} - Fetching Bank List", user.getUsername(), user.getAuthorities());
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.ASC, "bankName"));// default sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		BankJson ratesJson = commonService.getAllBanks(pageable, searchParameter);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(ratesJson);

		return json;
	}
	
	@RequestMapping(value = "/fetchExpAmountHistory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchExpAmountHistory(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - {}, Role - {} - Fetching Bank List", user.getUsername(), user.getAuthorities());
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.ASC, "workName"));// default sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);
		
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		WorkJson workJson = commonService.getExpAmountHistory(pageable, searchParameter,role,userBean.getOfficeBean().getId());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchChangeHistory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchChangeHistory(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - {}, Role - {} - Fetching Bank List", user.getUsername(), user.getAuthorities());
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.ASC, "workName"));// default sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);
		
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());

		WorkJson workJson = commonService.fetchChangeHistory(pageable, searchParameter,role,userBean.getOfficeBean().getId());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}
	

	@RequestMapping(value = "/editBankForm/{bankId}", method = RequestMethod.GET)
	public ModelAndView editBankForm(@PathVariable String bankId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - {}, Role - {} - Displaying Edit Bank Form", user.getUsername(), user.getAuthorities());
		ModelAndView modelAndView = new ModelAndView("common/editBankForm");
		return modelAndView;
	}

	@RequestMapping(value = "fetchBankDetails/{bankId}", method = RequestMethod.GET)
	public BankBean fetchBankDetails(@PathVariable Long bankId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - {}, Role - {} - Fetching Bank data", user.getUsername(), user.getAuthorities());
		return commonService.fetchBankDetails(bankId);
	}

	@RequestMapping(value = "/editBank", method = RequestMethod.POST)
	public ResponseObject editBank(@RequestBody BankBean bankBean, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - {}, Role - {} - Editing Bank data", user.getUsername(), user.getAuthorities());
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.editBank(bankBean);
		if (errorMsg == null) {
			response.setSuccessMessage("Bank updated successfully !");
		} else {
			response.setErrorMessage(errorMsg);
		}
		return response;
	}

	@RequestMapping(value = "/updateOfficer", method = RequestMethod.POST)
	public ResponseObject updateOfficer(@RequestBody WorkBean workBean, HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - updateOfficer called...");
		ResponseObject response = new ResponseObject();

		String errorMsg = commonService.updateOfficer(workBean);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Officer Updated successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Officer Updated successfully!");
		}
		return response;
	}

	// Rakesh History
	@RequestMapping(value = "/fetchWorkListByAdminHistory/{workId}/{technicalSanctionId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkListByAdminHistory(@PathVariable Long workId, @PathVariable Long technicalSanctionId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work List");

		Long districtId = null;
		String District = request.getParameter("selectionDropdownVal");

		
		HashMap<String, Long> hmap = new HashMap<String, Long>();
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workNatureId")))
			hmap.put(RESConstants.WORK_NATURE, Long.parseLong(request.getParameter("workNatureId")));
		
		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workTypeId")))
			hmap.put(RESConstants.WORK_TYPE, Long.parseLong(request.getParameter("workTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workSubTypeId")))
			hmap.put(RESConstants.WORK_SUB_TYPE, Long.parseLong(request.getParameter("workSubTypeId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workLineDepartmentId")))
			hmap.put(RESConstants.LINE_DEPARTMENT, Long.parseLong(request.getParameter("workLineDepartmentId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("accountHeadId")))
			hmap.put(RESConstants.ACCOUNT_HEAD, Long.parseLong(request.getParameter("accountHeadId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("executionAgencyId")))
			hmap.put(RESConstants.EXECUTION_AGENCY, Long.parseLong(request.getParameter("executionAgencyId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("workStatusId")))
			hmap.put(RESConstants.WORK_STATUS, Long.parseLong(request.getParameter("workStatusId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("blockId")))
			hmap.put(RESConstants.BLOCK, Long.parseLong(request.getParameter("blockId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("gramPanchayatId")))
			hmap.put(RESConstants.GRAM_PANCHAYAT, Long.parseLong(request.getParameter("gramPanchayatId")));

		if (!StringUtils.isEmptyOrWhitespace(request.getParameter("villageId")))
			hmap.put(RESConstants.VILLAGE, Long.parseLong(request.getParameter("villageId")));

		Long workNatureId = hmap.get(RESConstants.WORK_NATURE);
		Long workTypeId = hmap.get(RESConstants.WORK_TYPE);
		Long workSubTypeId = hmap.get(RESConstants.WORK_SUB_TYPE);
		Long lineDepartmentId = hmap.get(RESConstants.LINE_DEPARTMENT);
		Long accountHeadId = hmap.get(RESConstants.ACCOUNT_HEAD);
		Long executionAgencyId = hmap.get(RESConstants.EXECUTION_AGENCY);
		Long workStatusId = hmap.get(RESConstants.WORK_STATUS);
		Long blockId = hmap.get(RESConstants.BLOCK);
		Long gramPanchayatId = hmap.get(RESConstants.GRAM_PANCHAYAT);
		Long villageId = hmap.get(RESConstants.VILLAGE);

		if (!StringUtils.isEmptyOrWhitespace(District)) {
			districtId = Long.parseLong(District);
		}
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {

			if (sColName.equals("workName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, sColName));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, sColName));
				}
			}

			else if (sColName.equals("workRequisitionNo")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequisitionNo"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequisitionNo"));
				}
			}

			else if (sColName.equals("workTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workTypeNameE"));
				}
			}

			else if (sColName.equals("workSubTypeName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workSubTypeId.workSubTypeNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workSubTypeId.workSubTypeNameE"));
				}
			}

			else if (sColName.equals("lineDepartmentName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "lineDepartmentId.lineDepartmentNameE"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "lineDepartmentId.lineDepartmentNameE"));
				}
			}

			else if (sColName.equals("districtName")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "district.districtName"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "district.districtName"));
				}
			}

			else if (sColName.equals("workRequestStatusId")) {
				if (StringUtils.equals("asc", sSortDir)) {
					sort = new Sort(new Sort.Order(Direction.ASC, "workTypeId.workRequestStatusId"));
				} else {
					sort = new Sort(new Sort.Order(Direction.DESC, "workTypeId.workRequestStatusId"));
				}
			}

		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		// UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
		// designation, status);
		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		UserBean userBean = userService.fetchUserDetailsByUserName(user.getUsername());
		TechnicalSanctionJson workJson = commonService.getAllWorksByAdminHistory(pageable, role,
				userBean.getOfficeBean().getId(), userBean.getId(), null, districtId, workTypeId, workSubTypeId,
				lineDepartmentId, accountHeadId, executionAgencyId, workStatusId, blockId, gramPanchayatId, villageId,
				workId, technicalSanctionId);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);

		return json;
	}

	// Rakesh
	@RequestMapping(value = "fetchWorkDetailsByTechnicalAndParent/{id}/{technicalSanctionId}/{parentId}", method = RequestMethod.GET)
	public WorkBean fetchWorkDetailsByTechnicalAndParent(@PathVariable Long id, @PathVariable Long technicalSanctionId,
			@PathVariable Long parentId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Work data by Work Id");
		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		WorkBean workDetails = commonService.fetchWorkDetailsInTechnical(id, technicalSanctionId, parentId);

		workDetails.setUserBean(userService.fetchUserDetailsByUserName(user.getUsername()));
		Users users = userService.findByUserName(user.getUsername());

		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		workDetails.setLoggedInUserRole(role);

		if (role.equals(RESConstants.ROLE_EE)) {

			String anukramNo = null;
			anukramNo = "कार्यपालन यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String anukramNo = null;
			anukramNo = "मुख्य अभियंता, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_AE)) {

			String anukramNo = null;
			anukramNo = "Assistant Engineer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_SDO)) {

			String anukramNo = null;
			anukramNo = "Sub-divisional Officer, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);
		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String anukramNo = null;
			anukramNo = "अधीक्षण यंत्री, " + workDetails.getDistrictName();
			workDetails.setAnukramNo(anukramNo);

		}
		if (role.equals(RESConstants.ROLE_EE)) {

			String yantriName = null;
			yantriName = "कार्यपालन यंत्री ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_CE)) {

			String yantriName = null;
			yantriName = "मुख्य अभियंता ";
			workDetails.setYantriName(yantriName);

		}
		if (role.equals(RESConstants.ROLE_SUPDT_ENGG)) {

			String yantriName = null;
			yantriName = "अधीक्षण यंत्री ";
			workDetails.setYantriName(yantriName);

		}

		return workDetails;
	}
	
	@RequestMapping(value = "/getAllSqmAllocationsByWorkId/{workId}", method = RequestMethod.GET)
	@ResponseBody
	public List<SqmAllocationBean> getAllSqmAllocationsByWorkId(@PathVariable Long workId) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - getAllSqmAllocationsByWorkId called");

		List<SqmAllocationBean> list = commonService.getAllSqmAllocationsByWorkId(workId);
		return list;
	}
	
	
	@RequestMapping(value = "fetchAllSOR", method = RequestMethod.GET)
	public List<SORBean> fetchAllSOR(HttpServletRequest request) {
		
		return sorService.fetchAllSOR();
	}
	
	@RequestMapping(value = "/fetchChaptersBySORId/{id}", method = RequestMethod.GET)
	public List<ChapterBean> fetchItemById(@PathVariable Long id, HttpServletRequest request) {
		return sorService.fetchChaptersBySORId(id);
	}
	
	@RequestMapping(value = "/fetchItemsByChapterId/{id}", method = RequestMethod.GET)
	public List<ItemBean> fetchItemsByChapterId(@PathVariable Long id, HttpServletRequest request) {
		return sorService.fetchItemsByChapterId(id);
	}
	
	

	    @GetMapping("/fetchItemsByYearChapterIdItemNoOrName/{chapterId}/{searchText}")
	    public ResponseEntity<List<ItemBean>> fetchItemsByYearChapterIdItemNoOrName(
	            @PathVariable("chapterId") Long chapterId,
	            @PathVariable("searchText") String searchText) {

	        // Call repository method to perform search
	        List<ItemBean> items = sorService.fetchItemsByYearChapterIdItemNoOrName(chapterId, searchText);

	        return ResponseEntity.ok(items);
	    }
	
	@RequestMapping(value = "/changeFlag/{id}/{adminAmount}", method = RequestMethod.GET)
	public ResponseObject changeFlag(
			@PathVariable Long id,@PathVariable BigDecimal adminAmount, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Changing Work Flag");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = commonService.changeFlag(id,adminAmount);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Final Expendture Added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Final Expendture Added successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/changeFlagToZ/{id}", method = RequestMethod.GET)
	public ResponseObject changeFlagToZ(
			@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Changing Work Flag to zero ");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = commonService.changeFlagToZ(id);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Billing Flag Change successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Billing Flag Change successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "fetchEEofficesBySupdtOfficeIds/{supdtOfficeId}", method = RequestMethod.GET)
	public List<OfficeBean> fetchEEofficesBySupdtOfficeIds(@PathVariable String supdtOfficeId,
			HttpServletRequest request) {
		return commonService.fetchEEofficesBySupdtOfficeIds(supdtOfficeId);
	}
	
	@RequestMapping(value = "fetchEEofficesBySupdtOfficeIdsCeEnc/{supdtOfficeId}", method = RequestMethod.GET)
	public List<OfficeBean> fetchEEofficesBySupdtOfficeIdsCeEnc(@PathVariable String supdtOfficeId,
			HttpServletRequest request) {
		return commonService.fetchEEofficesBySupdtOfficeIdsCeEnc(supdtOfficeId);
	}
	@RequestMapping(value = "fetchEEofficesBySupdtOfficeIdsCeEncChecked/{supdtOfficeId}/{userId}", method = RequestMethod.GET)
	public List<OfficeBean> fetchEEofficesBySupdtOfficeIdsCeEncChecked(@PathVariable String supdtOfficeId,@PathVariable Long userId,
			HttpServletRequest request) {
		return commonService.fetchEEofficesBySupdtOfficeIdsCeEncChecked(supdtOfficeId,userId);
	}
	
	@RequestMapping(value = "fetchWorkSubTypeByWorkTypeIds/{workTypeId}", method = RequestMethod.GET)
	public List<WorkSubTypeBean> fetchWorkSubTypeByWorkTypeIds(@PathVariable String workTypeId,
			HttpServletRequest request) {
		return commonService.fetchWorkSubTypeByWorkTypeIds(workTypeId);
	}
	
	@RequestMapping(value = "loadUsersByDesig/{desigId}", method = RequestMethod.GET)
	public List<UserBean> fetchUsersByDesig(@PathVariable Long desigId,
			HttpServletRequest request) {
		return commonService.fetchUsersByDesig(desigId);
	}
	
	@RequestMapping(value = "/fetchInspUserList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchInspUserList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User List");

		String searchBoxVal = request.getParameter("searchBoxVal");
		String designation = request.getParameter("designation");
		String status = request.getParameter("status");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		// String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}

		Sort sort = null;
		if (sColName != null) {
			if (StringUtils.equals("asc", sSortDir)) {
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			} else {
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		} else {
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		UserBean userDetail = fetchLoggedInUserDetails(request);

		Long officeId = userDetail.getOfficeId();

		UserJson userJson = userService.getAllInspUsers(pageable, searchBoxVal, designation, status, officeId);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(userJson);

		return json;
	}
	
	@RequestMapping(value = "/editInspUserForm/{id}", method = RequestMethod.GET)
	public ModelAndView editInspUserForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit User Form");
		ModelAndView modelAndView = new ModelAndView("common/editInspUserForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "fetchInspUserDetails/{id}", method = RequestMethod.GET)
	public UserBean fetchInspUserDetails(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		return userService.fetchInspUserDetails(id);
	}
	
	@RequestMapping(value = "/fetchWorkDetailsByOfficersEEOfficeId", method = RequestMethod.GET)
	@ResponseBody
	public List<WorkBean> fetchWorkDetailsByOfficersEEOfficeId(@RequestParam(name = "officeIdList") String officeIdList,
			HttpServletRequest request) {
         String[] arr=officeIdList.split(",");
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Find Officers Inspection details called ");
		//ResponseObject response = new ResponseObject();
		for (String id:arr) {
			//DistrictBean districtBean = (DistrictBean) iterator.next();
//			

		}
		List<WorkBean> list=	 commonService.fetchAllWorkByOfficersEEOfficeId(officeIdList);
	//	List<DistrictBean> list=	commonService.fetchAllWorkByDisticts(districtBeans);
		//response.setSuccessMessage("Bill Rejected!");
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - list "+list);

		return  list;

	}
	
	
	@RequestMapping(value = "/manageLegacyDataMappingForENC", method = RequestMethod.GET)
	public ModelAndView manageLegacyDataMappingForENC(HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_EnC)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
			+ " - Displaying WorkWise Report For ENC");
			modelAndView = new ModelAndView("common/viewWorkReportForENC");
		}


		return modelAndView;
	}
	
	@RequestMapping(value = "/manageExpAmountHistory", method = RequestMethod.GET)
	public ModelAndView manageExpAmountHistory(HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
			+ " - Displaying Exp Amount History");
			modelAndView = new ModelAndView("common/manageExpAmountHistory");
		}


		return modelAndView;
	}
	
	@RequestMapping(value = "/manageChangesHistory", method = RequestMethod.GET)
	public ModelAndView manageChangesHistory(HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		HttpSession httpSession = request.getSession(false);
		String role = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (role.equals(RESConstants.ROLE_EnC) || role.equals(RESConstants.ROLE_CE)) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
			+ " - Displaying Exp Amount History");
			modelAndView = new ModelAndView("common/manageChangesHistory");
		}


		return modelAndView;
	}
	
	/** CR-RESOWMS/CR/4-3
	 * EE can make 10 % extra payment of the total tender cost. 
	 * It should be less or equal to AS.
	 * @param id
	 * @param request
	 * @return responseMessage
	 */
	@RequestMapping(value = "/allowSubEngToPrepareBill/{id}", method = RequestMethod.GET)
	public ResponseObject allowSubEngToPrepareBill(
			@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Changing Work Flag to zero ");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = commonService.allowSubEngToPrepareBill(id);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Allow Sub Eng To Prepare Extra Amount Of Bill successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Billing Flag Change successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "getDisctrictDetailByLgdDistrictCode/{id}", method = RequestMethod.GET)
	public DistrictBean getDisctrictDetailByLgdDistrictCode(@PathVariable String id, HttpServletRequest request) {
		return commonService.getDisctrictDetailByLgdDistrictCode(id);
	}

	@RequestMapping(value = "/fetchDistrictOfLoggedInUser", method = RequestMethod.GET)
	public List<District> fetchDistrictOfLoggedInUser(HttpServletRequest request) {
		try {
			user = RESUtil.getUserDetail();
			UserBean userBean = fetchLoggedInUserDetails(request);
			logger.info("User - {}, Role - {} - Fetching Work data", user.getUsername(), user.getAuthorities());

			return commonService.getAllDistricts(userBean.getDistrictBean().getDistrictId());
		} catch (Exception e) {
			return null;
		}

	}

	@RequestMapping(value = "/fetchBlockOfLoggedInUser", method = RequestMethod.GET)
	public Block fetchBlockOfLoggedInUser(HttpServletRequest request) {
		try {
			user = RESUtil.getUserDetail();
			UserBean userBean = fetchLoggedInUserDetails(request);
			logger.info("User - {}, Role - {} - Fetching Work data", user.getUsername(), user.getAuthorities());
			//System.out.println("userBean.getBlockId()" + userBean.getBlockId());
			return commonService.getBlocks(userBean);
		} catch (Exception e) {
			return null;
		}

	}
	

	@PostMapping("fetchBlockGeojson/{blockCode}")
	public ResponseEntity<String> fetchBlockGeojson(@PathVariable String blockCode) throws IOException {
		String apiUrl = "https://geoportal.mp.gov.in/AdminRestService/RestServiceImpl.svc/getAdminGeojsonUsingCode";
		String requestBody = String.format("{\"AdminUnitType\": \"Block\", \"AdminUnitCode\": \"%s\"}", blockCode);

		// Set up the HTTP request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		// Call the external API
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
		// System.out.println("response"+response.getBody());

		return response;

	}
	
	
	
}