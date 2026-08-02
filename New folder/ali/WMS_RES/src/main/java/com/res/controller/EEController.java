package com.res.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.res.bean.BlockBean;
import com.res.bean.BudgetRequestBean;
import com.res.bean.GramPanchayatBean;
import com.res.bean.KmlFilePoints;
import com.res.bean.LatLngForestResponse;
import com.res.bean.SchemeSanctionedUnderProgrammeBean;
import com.res.bean.UserBean;
import com.res.bean.VillageBean;
import com.res.bean.WorkBean;
import com.res.constants.RESConstants;
import com.res.entity.Users;
import com.res.json.BudgetRequestDetailJson;
import com.res.json.BudgetRequestJson;
import com.res.repository.UserRepository;
import com.res.response.ResponseObject;
import com.res.service.AdminService;
import com.res.service.BudgetService;
import com.res.service.CommonService;
import com.res.service.EeService;
import com.res.service.UserService;
import com.res.util.RESUtil;

/**
 * @author Admin This class is used in EE Login. Functionalities which contains
 *         Legacy Data Mgmt., Estimation, Tender, Work Agreement. Budget Mgmt.
 *         Module
 */
@RestController
@RequestMapping("/ee/*")
public class EEController extends BaseController {

	public static final Logger logger = LoggerFactory.getLogger(EEController.class);

	private User user;

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private EeService eeService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private BudgetService budgetService;

	@Autowired
	private UserRepository userRepository;

	RestTemplate restTemplate = new RestTemplate();

	/**
	 * @param request
	 * @param model
	 * @return ModelAndView Home Page Controller method for EE.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView viewHome(HttpServletRequest request, Model model) {

		Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);

		String language = request.getParameter("lang");

		if (null == locale) {
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en"));
		} else if (!StringUtils.isEmpty(language) && language.equals(RESConstants.LOCALE_HI)) {

			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("hi"));
		} else if (!StringUtils.isEmpty(language) && language.equals(RESConstants.LOCALE_EN)) {
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en"));
		}

		Locale updatedLocale = (Locale) request.getSession()
				.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		model.addAttribute("language", updatedLocale.toString());

		user = RESUtil.getUserDetail();
		ModelAndView modelAndView = new ModelAndView("ee/eeHome");

		if (user != null) {
			logger.info(
					"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying home page");
			// Users userEntity = userService.findByUserName(user.getUsername());
			modelAndView.addObject("loggedInUserName", user.getUsername());

			/*
			 * HttpSession httpSession = request.getSession(false);
			 * 
			 * String roleName =
			 * (String)httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
			 */
			/*
			 * Set<Role> roles = userEntity.getRoles(); if(roles!= null &&
			 * !roles.isEmpty()){ List<Role> roleList = new ArrayList<Role>(roles); roleName
			 * = roleList.get(0).getRoleName(); }
			 */
			UserBean userBean = fetchLoggedInUserDetails(request);
			modelAndView.addObject("roleName", userBean.getLoggedInUserRole());
			modelAndView.addObject("isOIC", userBean.getIsOICString());
		}
		return modelAndView;
	}

	/**
	 * @param request
	 * @return ModelAndView Dashboard Page for EE
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView viewDashboard(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying dashboard");
		ModelAndView modelAndView = new ModelAndView("ee/dashboard");
		return modelAndView;

	}

	/*
	 * @RequestMapping(value = "/fetchRatesList", method = RequestMethod.GET,
	 * produces = "application/json;charset=UTF-8") public String
	 * fetchRatesList(HttpServletRequest request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Fetching Rates List"); String
	 * sSortCol = request.getParameter("iSortCol_0"); String sSortDir =
	 * request.getParameter("sSortDir_0"); String sColName =
	 * request.getParameter("mDataProp_" + sSortCol);
	 * 
	 * // Fetch the page number from client Integer pageNumber = 0;
	 * 
	 * // Fetch search parameter String searchParameter =
	 * request.getParameter("sSearch");
	 * 
	 * // Fetch Page display length Integer pageDisplayLength =
	 * Integer.valueOf(request .getParameter("iDisplayLength"));
	 * 
	 * if (null != request.getParameter("iDisplayStart")) { pageNumber = (Integer
	 * .valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength); }
	 * 
	 * Sort sort = null; if(sColName!=null){ if(StringUtils.equals("asc",
	 * sSortDir)){ sort = new Sort(new Sort.Order(Direction.ASC, sColName)); }else{
	 * sort = new Sort(new Sort.Order(Direction.DESC, sColName)); } }else{ sort =
	 * new Sort(new Sort.Order(Direction.ASC, "effectiveFrom"));//default sorting }
	 * 
	 * Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);
	 * 
	 * RatesJson ratesJson = adminService.getAllRates(pageable, searchParameter);
	 * 
	 * Gson gson = new GsonBuilder().setPrettyPrinting().create(); String json =
	 * gson.toJson(ratesJson);
	 * 
	 * return json; }
	 * 
	 * @RequestMapping(value = "/addRatesForm", method = RequestMethod.GET) public
	 * ModelAndView viewAddRatesForm(HttpServletRequest request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Displaying Add Rates Form");
	 * ModelAndView modelAndView = new ModelAndView("admin/addRatesForm"); return
	 * modelAndView; }
	 * 
	 * @RequestMapping(value = "/addRates", method = RequestMethod.POST) public
	 * ResponseObject addRates(@RequestBody RatesBean ratesBean, HttpServletRequest
	 * request) throws Exception {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Adding Rates data"); ResponseObject
	 * response = new ResponseObject();
	 * 
	 * String errorMsg = adminService.addRates(ratesBean); if(errorMsg!=null){
	 * response.setErrorMessage(errorMsg); logger.error("User - " +
	 * user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
	 * }else{ response.setSuccessMessage("Rates added successfully!");
	 * logger.info("User - " + user.getUsername() + ", Role - " +
	 * user.getAuthorities() + " - Rates added successfully!"); } return response; }
	 * 
	 * @RequestMapping(value = "/editRatesForm/{id}", method = RequestMethod.GET)
	 * public ModelAndView viewEditRatesForm(
	 * 
	 * @PathVariable String id, HttpServletRequest request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Displaying Edit Rates Form");
	 * ModelAndView modelAndView = new ModelAndView("admin/editRatesForm"); return
	 * modelAndView; }
	 * 
	 * @RequestMapping(value = "fetchRatesDetails/{id}", method = RequestMethod.GET)
	 * public RatesBean fetchRatesDetails(@PathVariable Long id, HttpServletRequest
	 * request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Fetching Rates data"); return
	 * adminService.fetchRatesDetails(id); }
	 * 
	 * @RequestMapping(value = "/editRates", method = RequestMethod.POST) public
	 * ResponseObject editRates(@RequestBody RatesBean ratesBean, HttpServletRequest
	 * request) throws Exception {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Updating Rates data");
	 * ResponseObject response = new ResponseObject();
	 * 
	 * String errorMsg = adminService.editRates(ratesBean);
	 * 
	 * if(errorMsg!=null){ response.setErrorMessage(errorMsg);
	 * logger.error("User - " + user.getUsername() + ", Role - " +
	 * user.getAuthorities() + " - " + errorMsg); }else{
	 * response.setSuccessMessage("Rates updated successfully!");
	 * logger.info("User - " + user.getUsername() + ", Role - " +
	 * user.getAuthorities() + " - Rates updated successfully!"); } return response;
	 * }
	 */

	/*
	 * @RequestMapping(value = "/deleteRates/{id}", method = RequestMethod.GET)
	 * public ResponseObject deleteRates(
	 * 
	 * @PathVariable Long id, HttpServletRequest request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Deleting Rates"); ResponseObject
	 * response = new ResponseObject();
	 * 
	 * String errorMsg = adminService.deleteRates(id);
	 * 
	 * if(errorMsg!=null){ response.setErrorMessage(errorMsg);
	 * logger.error("User - " + user.getUsername() + ", Role - " +
	 * user.getAuthorities() + " - " + errorMsg); }else{
	 * response.setSuccessMessage("Rates deleted successfully!");
	 * logger.info("User - " + user.getUsername() + ", Role - " +
	 * user.getAuthorities() + " - Rates deleted successfully!"); } return response;
	 * }
	 */

	/**
	 * @param request
	 * @return ModelAndView Controller method for Legacy Data.
	 */
	@RequestMapping(value = "/addLegacyDataMapping", method = RequestMethod.GET)
	public ModelAndView addLegacyDataMapping(HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add Legacy Data Form");

		ModelAndView modelAndView = new ModelAndView("ee/addLegacyDataForm");
		Users userEntity = userService.findByUserName(user.getUsername());
		modelAndView.addObject("officeId", userEntity.getOffice().getId());
		modelAndView.addObject("officeName", userEntity.getOffice().getOfficeName());
		return modelAndView;
	}

	/*
	 * @RequestMapping(value = "/manageLegacyDataMapping", method =
	 * RequestMethod.GET) public ModelAndView
	 * manageLegacyDataMapping(HttpServletRequest request) {
	 * 
	 * user = RESUtil.getUserDetail(); boolean hasRole = false; for
	 * (GrantedAuthority authority : user.getAuthorities()) { hasRole =
	 * authority.getAuthority().equals(RESConstants.ROLE_EE); if (hasRole) {
	 * ModelAndView modelAndView = new ModelAndView( "ee/manageLegacyData"); }
	 * hasRole = authority.getAuthority().equals(RESConstants.ROLE_ADMIN); if
	 * (hasRole) { ModelAndView modelAndView = new ModelAndView(
	 * "ee/manageLegacyData"); } } HttpSession httpSession =
	 * request.getSession(false); String role =
	 * (String)httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
	 * ModelAndView modelAndView = null;
	 * 
	 * if(role.equals(RESConstants.ROLE_EE)){ logger.info("User - " +
	 * user.getUsername() + ", Role - " + user.getAuthorities() +
	 * " - Displaying Manage Legacy Data page"); modelAndView = new ModelAndView(
	 * "ee/manageLegacyData"); }
	 * 
	 * if(role.equals(RESConstants.ROLE_ADMIN)){ logger.info("User - " +
	 * user.getUsername() + ", Role - " + user.getAuthorities() +
	 * " - Displaying WorkWise Report"); modelAndView = new ModelAndView(
	 * "common/viewWorkReport"); } return modelAndView; }
	 */

	/*
	 * @RequestMapping(value = "/addWork", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public ResponseObject addWork( WorkBean workBean,
	 * HttpServletRequest request) throws Exception {
	 * 
	 * String remoteIpAddr = request.getHeader("X-Forwarded-For"); if (remoteIpAddr
	 * == null || "".equals(remoteIpAddr)) { remoteIpAddr = request.getRemoteAddr();
	 * } workBean.setClientIp(remoteIpAddr);
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Adding Legacy data");
	 * ResponseObject response = new ResponseObject();
	 * 
	 * String errorMsg = eeService.addWork(workBean); if(errorMsg!=null){
	 * response.setErrorMessage(errorMsg); logger.error("User - " +
	 * user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
	 * }else{ response.setSuccessMessage("Work added successfully!");
	 * logger.info("User - " + user.getUsername() + ", Role - " +
	 * user.getAuthorities() + " - Work added successfully!"); } return response; }
	 */

	/**
	 * @param workLoggingId
	 * @param request
	 * @return ModelAndView Controller method- View History Page for legacy data
	 */
	@RequestMapping(value = "/viewHLegacyData/{workLoggingId}", method = RequestMethod.GET)
	public ModelAndView viewHLegacyData(@PathVariable Long workLoggingId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying View work History Legacy Form");
		ModelAndView modelAndView = null;

		modelAndView = new ModelAndView("ee/viewHLegacyData");

		return modelAndView;
	}

	/*
	 * @RequestMapping(value = "fetchWorkDetailsHistorylegacy/{workLoggingId}",
	 * method = RequestMethod.GET) public WorkBean
	 * fetchWorkDetailsHistorylegacy(@PathVariable Long workLoggingId,
	 * HttpServletRequest request) {
	 * 
	 * return null; }
	 */

	/**
	 * @param id
	 * @param request
	 * @return ResponseObject Method for Deleting the Work
	 */
	@RequestMapping(value = "/deleteWork/{id}", method = RequestMethod.GET)
	public ResponseObject deleteWork(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Deleting Work");
		ResponseObject response = new ResponseObject();

		String errorMsg = eeService.deleteWork(id);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Work deleted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Work deleted successfully!");
		}
		return response;
	}

	/**
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editLegacyDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewEditUserForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit Legacy Form");
		ModelAndView modelAndView = new ModelAndView("ee/editLegacyDataForm");
		return modelAndView;
	}

	@RequestMapping(value = "/editLegacyEEDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView editEELegacyDataForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit EE Legacy Form");
		ModelAndView modelAndView = new ModelAndView("ee/editEELegacyDataForm");
		return modelAndView;
	}

	/*
	 * @RequestMapping(value = "/viewLegacyDataMapping/{id}", method =
	 * RequestMethod.GET) public ModelAndView viewUserForm(
	 * 
	 * @PathVariable String id, HttpServletRequest request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Displaying Edit Legacy Form");
	 * ModelAndView modelAndView = new ModelAndView("ee/viewLegacyDataForm"); return
	 * modelAndView; }
	 */

	/*
	 * @RequestMapping(value = "fetchWorkDetails/{id}", method = RequestMethod.GET)
	 * public WorkBean fetchWorkDetails(@PathVariable Long id, HttpServletRequest
	 * request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Fetching Work data"); return
	 * eeService.fetchWorkDetails(id); }
	 */

	/*
	 * @RequestMapping(value = "/editWork", method = RequestMethod.POST) public
	 * ResponseObject editWork(@RequestBody WorkBean workBean, HttpServletRequest
	 * request) throws Exception {
	 * 
	 * String remoteIpAddr = request.getHeader("X-Forwarded-For"); if (remoteIpAddr
	 * == null || "".equals(remoteIpAddr)) { remoteIpAddr = request.getRemoteAddr();
	 * } workBean.setClientIp(remoteIpAddr);
	 * 
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() + " - Updating Work data");
	 * ResponseObject response = new ResponseObject();
	 * 
	 * String errorMsg = eeService.editWork(workBean);
	 * 
	 * if(errorMsg!=null){ response.setErrorMessage(errorMsg);
	 * logger.error("User - " + user.getUsername() + ", Role - " +
	 * user.getAuthorities() + " - " + errorMsg); }else{
	 * response.setSuccessMessage("Work updated successfully!");
	 * logger.info("User - " + user.getUsername() + ", Role - " +
	 * user.getAuthorities() + " - Work updated successfully!"); } return response;
	 * }
	 */

	/**
	 * CR-RESOWMS/CR/1-1 Work Transfer Module-Transfer Work to Other Office
	 * 
	 * @param workBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editWork", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject editWork(WorkBean workBean, HttpServletRequest request) throws Exception {

		String remoteIpAddr = request.getHeader("X-Forwarded-For");
		if (remoteIpAddr == null || "".equals(remoteIpAddr)) {
			remoteIpAddr = request.getRemoteAddr();
		}
		workBean.setClientIp(remoteIpAddr);

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating Work data");
		ResponseObject response = new ResponseObject();

		String errorMsg = eeService.editWork(workBean);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Work updated successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Work updated successfully!");
		}
		return response;
	}

	/*
	 * @RequestMapping(value = "/viewEeReportsMapping", method = RequestMethod.GET)
	 * public ModelAndView viewEeReports(HttpServletRequest request) { user =
	 * RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() +
	 * " - Displaying Executive Offices Report of Work"); ModelAndView modelAndView
	 * = new ModelAndView( "common/viewExecutiveWorkReport"); return modelAndView; }
	 */

	/*
	 * @RequestMapping(value = "/fetchExecutiveOfficeWorkReport", method =
	 * RequestMethod.GET, produces = "application/json;charset=UTF-8") public String
	 * fetchExecutiveOfficeWorkReport(HttpServletRequest request) {
	 * 
	 * user = RESUtil.getUserDetail(); logger.info("User - " + user.getUsername() +
	 * ", Role - " + user.getAuthorities() +
	 * " - Fetching Executive Office Work Report");
	 * 
	 * String searchBoxVal = request.getParameter("searchBoxVal"); String
	 * designation = request.getParameter("designation"); String status =
	 * request.getParameter("status");
	 * 
	 * String sSortCol = request.getParameter("iSortCol_0"); String sSortDir =
	 * request.getParameter("sSortDir_0"); String sColName =
	 * request.getParameter("mDataProp_" + sSortCol);
	 * 
	 * // Fetch the page number from client Integer pageNumber = 0;
	 * 
	 * // Fetch search parameter //String searchParameter =
	 * request.getParameter("sSearch");
	 * 
	 * // Fetch Page display length Integer pageDisplayLength =
	 * Integer.valueOf(request .getParameter("iDisplayLength")); if (null !=
	 * request.getParameter("iDisplayStart")) { pageNumber = (Integer
	 * .valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength); } Sort
	 * sort = null; if(sColName!=null){ if(StringUtils.equals("asc", sSortDir)){
	 * sort = new Sort(new Sort.Order(Direction.ASC, sColName)); }else{ sort = new
	 * Sort(new Sort.Order(Direction.DESC, sColName)); } }else{ sort = new Sort(new
	 * Sort.Order(Direction.DESC, "id"));//default sorting } Pageable pageable = new
	 * PageRequest(pageNumber, pageDisplayLength, sort);
	 * 
	 * // UserJson userJson = userService.getAllUsers(pageable, searchBoxVal,
	 * designation, status);
	 * 
	 * ExecutiveWorkJson workJson = eeService.getAllExecutiveWorks(pageable); Gson
	 * gson = new GsonBuilder().setPrettyPrinting().create(); String json =
	 * gson.toJson(workJson); return json; }
	 */


	/**
	 * @param workBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/addWorkRequisitionDataMapping", method = RequestMethod.GET)
	public ModelAndView addWorkRequisitionDataMapping(HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Add Work Requisition Data Form");
		
		
		ModelAndView modelAndView = new ModelAndView("ee/addWorkRequisitionDataForm");
		Users userEntity = userService.findByUserName(user.getUsername());
		modelAndView.addObject("officeId", userEntity.getOffice().getId());
		modelAndView.addObject("officeName", userEntity.getOffice().getOfficeName());
		return modelAndView;
	}
	
	/**
	 * @param workBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRequisitionWork", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addRequisitionWork(
			WorkBean workBean,
			HttpServletRequest request) throws Exception {

		String remoteIpAddr = request.getHeader("X-Forwarded-For");
	    if (remoteIpAddr == null || "".equals(remoteIpAddr)) {
	        remoteIpAddr = request.getRemoteAddr();
	    }
	    workBean.setClientIp(remoteIpAddr);

	    String isKmlFile = request.getParameter("isKmlFile");
	    if (isKmlFile != null && !isKmlFile.isEmpty()) {
	        workBean.setKmlFileUpload(true);
	    }

	   
	    user = RESUtil.getUserDetail();
	    logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Requisition Work data");

	    ResponseObject response = new ResponseObject();

	    String errorMsg = eeService.addRequisitionWork(workBean);
	    if (errorMsg != null) {
	        response.setErrorMessage(errorMsg);
	        logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
	    } else {
	        if (workBean.getWorkRequestStatusId() == 2) {
	            response.setSuccessMessage("Work Requisition Submitted successfully!");
	        } else {
	            response.setSuccessMessage("Work Requisition Saved as Draft successfully!");
	        }
	    }
	    return response;
	}
	
	/**
	 * CR-RESOWMS/CR/4-5 In work Requisition and Legacy data the option of PAC
	 * should be freezed once it is been filled by the EE. So remove the option of
	 * editing the Pac from E-in-C login.
	 * 
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editWorkRequisitionDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewEditWorkRequisitionForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit Work Requisition Form");
		ModelAndView modelAndView = new ModelAndView("ee/editWorkRequisitionDataForm");
		return modelAndView;
	}

	@RequestMapping(value = "/editEEWorkRequisitionDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewEditEEWorkRequisitionForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying EE Edit Work Requisition Form");
		ModelAndView modelAndView = new ModelAndView("ee/editEEWorkRequisitionDataForm");
		return modelAndView;
	}

	@RequestMapping(value = "/viewWorkRequisitionDataMappingForReq/{id}", method = RequestMethod.GET)
	public ModelAndView viewWorkRequisitionForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit Work Requisition Form");
		ModelAndView modelAndView = new ModelAndView("ee/viewWorkRequisitionDataFormForReq");
		return modelAndView;
	}

	/**
	 * @param workBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editRequisitionWork", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject editRequisitionWork(
			WorkBean workBean,
			HttpServletRequest request) throws Exception {
		String remoteIpAddr = request.getHeader("X-Forwarded-For");
		if (remoteIpAddr == null || "".equals(remoteIpAddr)) {
			remoteIpAddr = request.getRemoteAddr();
		}
		
		workBean.setClientIp(remoteIpAddr);
		
		long kmlfileid = workBean.getKmlFileId();
		String isKmlFile = request.getParameter("isKmlFile");

		if (isKmlFile != null && !isKmlFile.trim().isEmpty() && kmlfileid == 0) {
		    workBean.setKmlFileUpload(true);
		}
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating Work data");
		ResponseObject response = new ResponseObject();

		String errorMsg = eeService.editRequisitionWork(workBean);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {

			if (workBean.getWorkRequestStatusId() == 2) {
				response.setSuccessMessage("Work Requisition Submitted successfully!");
				logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
						+ " - Work Requisition Submitted successfully!");
			} else {
				response.setSuccessMessage("Work Requisition Saved as Draft successfully!");
				logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
						+ " - Work Requisition Saved as Draft successfully!");
			}

			/*
			 * response.setSuccessMessage("Work updated successfully!");
			 * logger.info("User - " + user.getUsername() + ", Role - " +
			 * user.getAuthorities() + " - Work Requisition updated successfully!");
			 */
		}
		return response;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/budgetRequestList", method = RequestMethod.GET)
	public ModelAndView budgetRequest(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - getting Budget Request List  ");

		ModelAndView modelAndView = new ModelAndView("ee/budgetRequestList");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		return modelAndView;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fetchBudgetRequestList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBudgetRequestList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Budget Request List");

		String searchBoxVal = request.getParameter("searchBoxVal");

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
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default sorting

		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BudgetRequestJson budgetRequestJson = budgetService.fetchBudgetRequestList(pageable, searchBoxVal,
				loggedInUserRole, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(budgetRequestJson);

		return json;
	}

	/**
	 * @param budgetRequestId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fetchBudgetRequestDetailList/{budgetRequestId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBudgetRequestDetailList(@PathVariable Long budgetRequestId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Fetching Budget Request Detail List");

		String searchBoxVal = request.getParameter("searchBoxVal");

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
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default sorting

		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

		HttpSession httpSession = request.getSession(false);

		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BudgetRequestDetailJson budgetRequestJson = budgetService.fetchBudgetRequestDetailList(pageable,
				budgetRequestId, loggedInUserRole, user.getUsername());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(budgetRequestJson);

		return json;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addBudgetRequest", method = RequestMethod.GET)
	public ModelAndView addBudgetRequest(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - getting Budget Request List  ");

		ModelAndView modelAndView = new ModelAndView("ee/addBudgetRequest");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		modelAndView.addObject("workStatus", "total");
		modelAndView.addObject("isLegacy", -1);

		return modelAndView;

	}

	/**
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editBudgetRequest/{id}", method = RequestMethod.GET)
	public ModelAndView editBudgetRequest(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - getting Budget Request ");

		ModelAndView modelAndView = new ModelAndView("ee/editBudgetRequest");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		return modelAndView;
	}

	@RequestMapping(value = "/viewBudgetRequest/{id}", method = RequestMethod.GET)
	public ModelAndView viewBudgetRequest(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - getting Budget Request  ");

		ModelAndView modelAndView = new ModelAndView("ee/viewBudgetRequest");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		return modelAndView;
	}

	@RequestMapping(value = "/addBudgetSurrender/{id}", method = RequestMethod.GET)
	public ModelAndView addBudgetSurrender(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - getting Budget Request  ");

		ModelAndView modelAndView = new ModelAndView("ee/addBudgetSurrender");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		return modelAndView;
	}

	/**
	 * @param budgetRequestBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveBudgetRequest", method = RequestMethod.POST)
	public ResponseObject saveBudgetRequest(@RequestBody BudgetRequestBean budgetRequestBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - saveBudgetRequest is called ");
		ResponseObject response = new ResponseObject();

		Users entity = userRepository.findByUsernameAndStatus(user.getUsername(), RESConstants.STATUS_ACTIVE);

		Long officeId = entity.getOffice().getId();

		budgetRequestBean.setOfficeBeanId(officeId);
		budgetRequestBean.setOfficeName(entity.getOffice().getOfficeName());

		String errorMsg = budgetService.saveBudgetRequest(budgetRequestBean);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Budget added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Budget added successfully!");
		}
		return response;
	}

	/**
	 * @param budgetRequestBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveEditBudgetRequest", method = RequestMethod.POST)
	public ResponseObject saveEditBudgetRequest(@RequestBody BudgetRequestBean budgetRequestBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - saveEditBudgetRequest is called");
		ResponseObject response = new ResponseObject();

		Users entity = userRepository.findByUsernameAndStatus(user.getUsername(), RESConstants.STATUS_ACTIVE);
		Long officeId = entity.getOffice().getId();

		budgetRequestBean.setOfficeBeanId(officeId);
		budgetRequestBean.setOfficeName(entity.getOffice().getOfficeName());

		String errorMsg = budgetService.saveEditBudgetRequest(budgetRequestBean);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {

			if (budgetRequestBean.getStatusId() == 1) {
				response.setSuccessMessage("Budget Request Saved !");
				logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
						+ " - Budget Request Saved!");
			} else if (budgetRequestBean.getStatusId() == 2) {
				response.setSuccessMessage("Budget Request Submitted !");
				logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
						+ " - Budget Request Submitted!");
			} else {
				response.setSuccessMessage("Budget Edited successfully!");
				logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
						+ " - Budget Edited successfully!");
			}

		}
		return response;
	}

	/**
	 * @param budgetRequestBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveSurrenderRequest", method = RequestMethod.POST)
	public ResponseObject saveSurrenderRequest(@RequestBody BudgetRequestBean budgetRequestBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - saveSurrenderRequest is called");
		ResponseObject response = new ResponseObject();

		Users entity = userRepository.findByUsernameAndStatus(user.getUsername(), RESConstants.STATUS_ACTIVE);
		Long officeId = entity.getOffice().getId();

		budgetRequestBean.setOfficeBeanId(officeId);
		budgetRequestBean.setOfficeName(entity.getOffice().getOfficeName());

		String errorMsg = budgetService.saveSurrenderRequest(budgetRequestBean);

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Surrender Request Added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Surrender Request Added successfully!");
		}

		return response;
	}

	/**
	 * @param budgetRequestId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fetchBudgetRequest/{budgetRequestId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public BudgetRequestBean fetchBudgetRequest(@PathVariable Long budgetRequestId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - saveEditBudgetRequest is called");
		BudgetRequestBean budgetRequestBean = budgetService.fetchBudgetRequest(budgetRequestId);
		return budgetRequestBean;
	}

	@RequestMapping(value = "/reviseLegacyDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView reviseEditUserForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit Legacy Form");
		ModelAndView modelAndView = new ModelAndView("ee/reviseLegacyDataForm");
		return modelAndView;
	}

	/**
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/historyLegacyDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView historyEditUserForm(@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Edit Legacy Form");
		ModelAndView modelAndView = new ModelAndView("ee/historyLegacyDataForm");
		return modelAndView;
	}

	@PostMapping(value = "processKmlFile", consumes = { "multipart/form-data" })
	public List<KmlFilePoints> ProcessKmlFile(KmlFilePoints Bean, HttpServletRequest request) {
		logger.info("Processing Kml File");

		return eeService.processKmlFile(Bean);

	}
	
	
	@PostMapping("/fetchGeojson/{districtCode}")
	public ResponseEntity<String> fetchGeojson(@PathVariable String districtCode) throws IOException {
		String apiUrl = "https://geoportal.mp.gov.in/AdminRestService/RestServiceImpl.svc/getAdminGeojsonUsingCode";
		String requestBody = String.format("{\"AdminUnitType\": \"District\", \"AdminUnitCode\": \"%s\"}",
				districtCode);

		// Set up the HTTP request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		// Call the external API
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

		// Check if the request was successful (status code 2xx)
		/*
		 * if (response.getStatusCode().is2xxSuccessful()) { // Get the JSON object from
		 * the response body String jsonResponse = response.getBody();
		 * 
		 * // Now you can parse the JSON string using an ObjectMapper or any other JSON
		 * parsing library ObjectMapper objectMapper = new ObjectMapper(); try {
		 * JsonNode jsonObject = objectMapper.readTree(jsonResponse);
		 * 
		 * // Now you have the JSON object and can further process it as needed
		 * System.out.println("JSON Object: " + jsonObject);
		 * 
		 * // Example: Accessing a specific field in the JSON object String adminCode =
		 * jsonObject.get("data").get(0).get("admingeojsoninfo").get("features").get(0).
		 * get("properties").get("admincode").asText();
		 * System.out.println("Admin Code: " + adminCode); } catch
		 * (JsonProcessingException e) { e.printStackTrace(); // Handle JSON parsing
		 * errors } } else { // Handle unsuccessful response (e.g., error handling)
		 * System.err.println("Failed to retrieve data. Status code: " +
		 * response.getStatusCode()); }
		 */
		return response;

	}

	@RequestMapping(value = "fetchblockCode/{blockId}", method = RequestMethod.GET)
	public BlockBean fetchblockCode(@PathVariable Long blockId, HttpServletRequest request) {
		System.out.println("WorkcController");
		return eeService.fetchblockCode(blockId);
	}

	@RequestMapping(value = "fetchBlockGeojson/{blockCode}", method = RequestMethod.GET)
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

	@RequestMapping(value = "fetchLgdGpCode/{GpId}", method = RequestMethod.GET)
	public GramPanchayatBean fetchLgdGpCode(@PathVariable Long GpId, HttpServletRequest request) {
		System.out.println("WorkcController");
		return eeService.fetchLgdGpCode(GpId);
	}

	@PostMapping("fetchGpGeojson/{lgdGpCode}")
	public ResponseEntity<String> fetchGpGeojson(
	        @PathVariable("lgdGpCode") String lgdGpCode) {

	    String apiUrl =
	        "https://geoportal.mp.gov.in/AdminRestService/RestServiceImpl.svc/getAdminGeojsonUsingCode";

	    String requestBody = "{"
	            + "\"AdminUnitType\":\"GramPanchayat\","
	            + "\"AdminUnitCode\":\"" + lgdGpCode + "\""
	            + "}";

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	    // WCF services often fail without this
	    headers.set("User-Agent", "SpringBoot");

	    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

	    try {
	        return restTemplate.exchange(
	                apiUrl,
	                HttpMethod.POST,
	                requestEntity,
	                String.class
	        );
	    } catch (HttpStatusCodeException ex) {
	        // Return real WCF error to UI (very important for debugging)
	        return ResponseEntity
	                .status(ex.getStatusCode())
	                .body(ex.getResponseBodyAsString());
	    }
	}


	@RequestMapping(value = "fetchVillageCode/{VillageId}", method = RequestMethod.GET)
	public VillageBean fetchVillageCode(@PathVariable Long VillageId, HttpServletRequest request) {
		System.out.println("WorkcController");
		return eeService.fetchVillageByVCode(VillageId);
	}

	@PostMapping("fetchVillageGeojson/{villageCode}")
	public ResponseEntity<String> fetchVillageGeojson(
	        @PathVariable("villageCode") String villageCode) {

	    String apiUrl =
	        "https://geoportal.mp.gov.in/AdminRestService/RestServiceImpl.svc/getAdminGeojsonUsingCode";

	    // Exact payload expected by WCF
	    String requestBody = "{"
	            + "\"AdminUnitType\":\"Village\","
	            + "\"AdminUnitCode\":\"" + villageCode + "\""
	            + "}";

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    headers.set("User-Agent", "SpringBoot");

	    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

	    try {
	        return restTemplate.exchange(
	                apiUrl,
	                HttpMethod.POST,
	                entity,
	                String.class
	        );
	    } catch (HttpStatusCodeException ex) {
	        // IMPORTANT: return actual error from WCF
	        return ResponseEntity
	                .status(ex.getStatusCode())
	                .body(ex.getResponseBodyAsString());
	    }
	}
	
	

	@PostMapping("getLatLng/{templat}/{templong}/")
	public ResponseEntity<String> getLatLng(@PathVariable BigDecimal  templat, @PathVariable BigDecimal  templong)
			throws IOException {
		System.out.printf("templat: %.6f, templong: %.6f%n", templat, templong);
		String apiUrl = "https://geoportal.mp.gov.in/adminunitservice/RestServiceImpl.svc/getAdminUnitsByLatLon";
		String requestBody = String.format("{\"lat\": \"%s\", \"lon\": \"%s\"}", templat, templong);

		// Set up the HTTP request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		// Call the external API
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
		// System.out.println("response"+response.getBody());

		return response;

	}

	@PostMapping("getLatLngforest/{templat}/{templong}")
	public ResponseEntity<LatLngForestResponse> getLatLngforestarea(@PathVariable double templat,
			@PathVariable double templong) throws IOException {
		String apiUrl = "https://geoportal.mp.gov.in/MPGeo_API/api/RangeBoundary/getRangeBoundaryByLatLng";
		String requestBody = String.format("{\"lat\": \"%s\", \"lng\": \"%s\"}", templat, templong);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		// Map the response directly to LatLngForestResponse
		ResponseEntity<LatLngForestResponse> response = restTemplate.postForEntity(apiUrl, requestEntity,
				LatLngForestResponse.class);

		return ResponseEntity.ok(response.getBody());

	}

	 @RequestMapping(value = "fetchGramPanchayatByGPCode/{gpCode}", method = RequestMethod.GET)
		public List<GramPanchayatBean> fetchGramPanchayatByGPCode(@PathVariable String gpCode, HttpServletRequest request) {
			return eeService.fetchGramPanchayatByGPCode(Long.parseLong(gpCode));
		}
	    @RequestMapping(value = "fetchVillageByVCode/{vCode}", method = RequestMethod.GET)
	  		public VillageBean fetchVillageByVCode(@PathVariable String vCode, HttpServletRequest request) {
	  			return eeService.fetchVillageByVCode(Long.parseLong(vCode));
	    }
	    
	    @RequestMapping(value = "/downloadUploadDocument/{documentId}", method = RequestMethod.GET)
		public void downloadUploadDocument(@PathVariable Long documentId, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			System.out.println("  ");
			String fileName = commonService.fetchDownloadFileName(documentId);
			  if (fileName != null) {
		          File file = new File(fileName);
		          if (file.exists()) {
		              InputStream is = null;
		              OutputStream os = null;
		              try {
		                  is = new FileInputStream(file);
		                  os = response.getOutputStream();

		                  // Set response headers
		                  response.setContentType("application/octet-stream");
		                  response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

		                  // Read and write file content
		                  byte[] buffer = new byte[1024];
		                  int len;
		                  while ((len = is.read(buffer)) != -1) {
		                      os.write(buffer, 0, len);
		                  }
		                  os.flush();
		              } catch (IOException e) {
		                  logger.error("Error while downloading document with ID: " + documentId, e);
		                  response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to download file.");
		              } finally {
		                  if (os != null) {
		                      try {
		                          os.close();
		                      } catch (IOException e) {
		                          logger.warn("Failed to close output stream", e);
		                      }
		                  }
		                  if (is != null) {
		                      try {
		                          is.close();
		                      } catch (IOException e) {
		                          logger.warn("Failed to close input stream", e);
		                      }
		                  }
		              }
		          } else {
		              response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
		          }
		      } else {
		          response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid document ID or file name.");
		      }
		  }
	    
	    
		@RequestMapping(value = "fetchSchemes", method = RequestMethod.GET)
		public List<SchemeSanctionedUnderProgrammeBean> fetchSchemeSanctionedUnderProgrammes(HttpServletRequest request) {
			return commonService.fetchSchemeSanctionedUnderProgrammes();
		}
		
		@RequestMapping(value = "fetchSubdivisionalOfficersByOfficeId/{officeId}", method = RequestMethod.GET)
		@ResponseBody
		public List<UserBean> fetchSubDivionOfficersByOfficeId(HttpServletRequest request, @PathVariable Long officeId) {
			return commonService.fetchSubdivisionalOfficersByOfficeId(officeId);
		}

}
