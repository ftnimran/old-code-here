package com.res.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.util.StringUtils;

import com.res.bean.UserBean;
import com.res.bean.WorkBean;
import com.res.constants.RESConstants;
import com.res.entity.Users;
import com.res.response.ResponseObject;
import com.res.service.AdminService;
import com.res.service.CommonService;
import com.res.service.EeService;
import com.res.service.UserService;
import com.res.util.RESUtil;

@RestController
@RequestMapping("/dirgp/*")
public class DirGpController extends BaseController{
	
	public static final Logger logger = LoggerFactory.getLogger(DirGpController.class);
	
	private User user;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private EeService eeService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView viewHome(HttpServletRequest request, Model model) {
		
		Locale locale = (Locale) request.getSession().getAttribute(
				SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		
		String language = request.getParameter("lang");
		
		if (null == locale) {
			request.getSession().setAttribute(
					SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					new Locale("en"));
		} else if (!StringUtils.isEmpty(language)
				&& language.equals(RESConstants.LOCALE_HI)) {

			request.getSession().setAttribute(
					SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					new Locale("hi"));
		} else if (!StringUtils.isEmpty(language)
				&& language.equals(RESConstants.LOCALE_EN)) {
			request.getSession().setAttribute(
					SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					new Locale("en"));
		}

		Locale updatedLocale = (Locale) request.getSession().getAttribute(
				SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		model.addAttribute("language", updatedLocale.toString());

		user = RESUtil.getUserDetail();
		ModelAndView modelAndView = new ModelAndView("dirgp/dirgpHome");
		
		if(user!=null){
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying home page");
			//Users userEntity = userService.findByUserName(user.getUsername());
			modelAndView.addObject("loggedInUserName", user.getUsername());
			
			/*HttpSession httpSession = request.getSession(false);
			
			String roleName = (String)httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);*/
			/*Set<Role> roles = userEntity.getRoles();
			if(roles!= null && !roles.isEmpty()){
				List<Role> roleList = new ArrayList<Role>(roles);
				roleName = roleList.get(0).getRoleName();
			}*/
			UserBean userBean = fetchLoggedInUserDetails(request);
			modelAndView.addObject("roleName", userBean.getLoggedInUserRole());
			modelAndView.addObject("isOIC", userBean.getIsOICString());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView viewDashboard(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying dashboard");
		ModelAndView modelAndView = new ModelAndView("dirgp/dashboard");
		return modelAndView;

	}
	

	
	/*@RequestMapping(value = "/fetchRatesList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchRatesList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Rates List");
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		
		Sort sort = null;
		if(sColName!=null){
			if(StringUtils.equals("asc", sSortDir)){
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			}else{
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		}else{
			sort = new Sort(new Sort.Order(Direction.ASC, "effectiveFrom"));//default sorting
		}
		
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

	RatesJson ratesJson = adminService.getAllRates(pageable, searchParameter);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(ratesJson);

		return json;
	}
	
	@RequestMapping(value = "/addRatesForm", method = RequestMethod.GET)
	public ModelAndView viewAddRatesForm(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Add Rates Form");
		ModelAndView modelAndView = new ModelAndView("admin/addRatesForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addRates", method = RequestMethod.POST)
	public ResponseObject addRates(@RequestBody RatesBean ratesBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Rates data");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = adminService.addRates(ratesBean);
		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Rates added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Rates added successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/editRatesForm/{id}", method = RequestMethod.GET)
	public ModelAndView viewEditRatesForm(
			@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Rates Form");
		ModelAndView modelAndView = new ModelAndView("admin/editRatesForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "fetchRatesDetails/{id}", method = RequestMethod.GET)
	public RatesBean fetchRatesDetails(@PathVariable Long id,
			HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Rates data");
		return adminService.fetchRatesDetails(id);
	}
	
	@RequestMapping(value = "/editRates", method = RequestMethod.POST)
	public ResponseObject editRates(@RequestBody RatesBean ratesBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating Rates data");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = adminService.editRates(ratesBean);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Rates updated successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Rates updated successfully!");
		}
		return response;
	}*/
	
	/*@RequestMapping(value = "/deleteRates/{id}", method = RequestMethod.GET)
	public ResponseObject deleteRates(
			@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Deleting Rates");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = adminService.deleteRates(id);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Rates deleted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Rates deleted successfully!");
		}
		return response;
	}*/
	
	@RequestMapping(value = "/addLegacyDataMapping", method = RequestMethod.GET)
	public ModelAndView addLegacyDataMapping(HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Add Legacy Data Form");
		
		
		ModelAndView modelAndView = new ModelAndView("ee/addLegacyDataForm");
		Users userEntity = userService.findByUserName(user.getUsername());
		modelAndView.addObject("officeId", userEntity.getOffice().getId());
		modelAndView.addObject("officeName", userEntity.getOffice().getOfficeName());
		return modelAndView;
	}
	
	/*@RequestMapping(value = "/manageLegacyDataMapping", method = RequestMethod.GET)
	public ModelAndView manageLegacyDataMapping(HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		boolean hasRole = false;
		for (GrantedAuthority authority : user.getAuthorities()) {
		     hasRole = authority.getAuthority().equals(RESConstants.ROLE_EE);
		     if (hasRole) {
		    	 ModelAndView modelAndView = new ModelAndView(
		 				"ee/manageLegacyData");
		     }
		     hasRole = authority.getAuthority().equals(RESConstants.ROLE_ADMIN);
		     if (hasRole) {
		    	 ModelAndView modelAndView = new ModelAndView(
		 				"ee/manageLegacyData");
		     }
		  }
		HttpSession httpSession = request.getSession(false);
		String role = (String)httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		ModelAndView modelAndView = null;
		
		if(role.equals(RESConstants.ROLE_EE)){
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Manage Legacy Data page");			
			 modelAndView = new ModelAndView(
					"ee/manageLegacyData");
		}
		
		if(role.equals(RESConstants.ROLE_ADMIN)){
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying WorkWise Report");			
			 modelAndView = new ModelAndView(
					"common/viewWorkReport");
		}
		return modelAndView;
	}*/
		
	/*@RequestMapping(value = "/addWork", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addWork(
			WorkBean workBean,
			HttpServletRequest request) throws Exception {
		
		String remoteIpAddr = request.getHeader("X-Forwarded-For");
		if (remoteIpAddr == null || "".equals(remoteIpAddr)) {
			remoteIpAddr = request.getRemoteAddr();
		}
		workBean.setClientIp(remoteIpAddr);

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Legacy data");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = eeService.addWork(workBean);
		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Work added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work added successfully!");
		}
		return response;
	}*/
	

	
	@RequestMapping(value = "/deleteWork/{id}", method = RequestMethod.GET)
	public ResponseObject deleteWork(
			@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Deleting Work");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = eeService.deleteWork(id);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Work deleted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work deleted successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/editLegacyDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewEditUserForm(
			@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Legacy Form");
		ModelAndView modelAndView = new ModelAndView("ee/editLegacyDataForm");
		return modelAndView;
	}
	
	/*@RequestMapping(value = "fetchWorkDetails/{id}", method = RequestMethod.GET)
	public WorkBean fetchWorkDetails(@PathVariable Long id,
			HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Work data");
		return eeService.fetchWorkDetails(id);
	}*/
	
	/*@RequestMapping(value = "/editWork", method = RequestMethod.POST)
	public ResponseObject editWork(@RequestBody WorkBean workBean,
			HttpServletRequest request) throws Exception {
		
		String remoteIpAddr = request.getHeader("X-Forwarded-For");
		if (remoteIpAddr == null || "".equals(remoteIpAddr)) {
			remoteIpAddr = request.getRemoteAddr();
		}
		workBean.setClientIp(remoteIpAddr);
		

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating Work data");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = eeService.editWork(workBean);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Work updated successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work updated successfully!");
		}
		return response;
	}*/
	
	@RequestMapping(value = "/editWork", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject editWork(WorkBean workBean,
			HttpServletRequest request) throws Exception {
		
		String remoteIpAddr = request.getHeader("X-Forwarded-For");
		if (remoteIpAddr == null || "".equals(remoteIpAddr)) {
			remoteIpAddr = request.getRemoteAddr();
		}
		workBean.setClientIp(remoteIpAddr);
		

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating Work data");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = eeService.editWork(workBean);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Work updated successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work updated successfully!");
		}
		return response;
	}
	
	
	
	/*@RequestMapping(value = "/viewEeReportsMapping", method = RequestMethod.GET)
	public ModelAndView viewEeReports(HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Executive Offices Report of Work");
		ModelAndView modelAndView = new ModelAndView(
				"common/viewExecutiveWorkReport");
		return modelAndView;
	}*/
	
	/*@RequestMapping(value = "/fetchExecutiveOfficeWorkReport", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchExecutiveOfficeWorkReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Executive Office Work Report");
		
		String searchBoxVal = request.getParameter("searchBoxVal");
		String designation = request.getParameter("designation");
		String status = request.getParameter("status");
		
		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		// Fetch the page number from client
		Integer pageNumber = 0;

		// Fetch search parameter
		//String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));
		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
		}
		Sort sort = null;
		if(sColName!=null){
			if(StringUtils.equals("asc", sSortDir)){
				sort = new Sort(new Sort.Order(Direction.ASC, sColName));
			}else{
				sort = new Sort(new Sort.Order(Direction.DESC, sColName));
			}
		}else{
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));//default sorting
		}
		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);

//		UserJson userJson = userService.getAllUsers(pageable, searchBoxVal, designation, status);
		
		ExecutiveWorkJson workJson = eeService.getAllExecutiveWorks(pageable);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workJson);
		return json;
	}*/
	
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
	
	@RequestMapping(value = "/addRequisitionWork", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject addRequisitionWork(
			WorkBean workBean, @RequestPart(value = "kmlFile", required = false) MultipartFile kmlFile,
			HttpServletRequest request) throws Exception {
		
		String remoteIpAddr = request.getHeader("X-Forwarded-For");
		if (remoteIpAddr == null || "".equals(remoteIpAddr)) {
			remoteIpAddr = request.getRemoteAddr();
		}
		workBean.setClientIp(remoteIpAddr);

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Adding Requisition Work data");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = eeService.addRequisitionWork(workBean);
		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			if(workBean.getWorkRequestStatusId()==2){
			response.setSuccessMessage("Work Requisition Submitted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work Requisition Submitted successfully!");
			} else {
				response.setSuccessMessage("Work Requisition Saved as Draft successfully!");
				logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work Requisition Saved as Draft successfully!");
			}
		}
		return response;
	}
	
	
	
	@RequestMapping(value = "/editRequisitionWork", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject editRequisitionWork(WorkBean workBean, @RequestPart(value = "kmlFile", required = false) MultipartFile kmlFile,
			HttpServletRequest request) throws Exception {
		
		String remoteIpAddr = request.getHeader("X-Forwarded-For");
		if (remoteIpAddr == null || "".equals(remoteIpAddr)) {
			remoteIpAddr = request.getRemoteAddr();
		}
		workBean.setClientIp(remoteIpAddr);
		

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Updating Work data");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = eeService.editRequisitionWork(workBean);

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{

			if(workBean.getWorkRequestStatusId()==2){
			response.setSuccessMessage("Work Requisition Submitted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work Requisition Submitted successfully!");
			} else {
				response.setSuccessMessage("Work Requisition Saved as Draft successfully!");
				logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work Requisition Saved as Draft successfully!");
			}
			
			/*response.setSuccessMessage("Work updated successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Work Requisition updated successfully!");*/
		}
		return response;
	}
	

	/*@RequestMapping(value = "/viewLegacyDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewLegacyDataForm(
			@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying View Legacy Form");
		ModelAndView modelAndView = new ModelAndView("dirgp/viewLegacyDataForm");
		return modelAndView;
	}*/
	

	@RequestMapping(value = "/editWorkRequisitionDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewEditWorkRequisitionForm(
			@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Work Requisition Form");
		ModelAndView modelAndView = new ModelAndView("dirgp/viewWorkRequisitionDataForm");
		return modelAndView;
	}
	
	
	
}
