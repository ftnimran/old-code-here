package com.res.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.res.bean.DashboardBean;
import com.res.bean.UserBean;
import com.res.constants.RESConstants;
import com.res.json.WorkDistrictJson;
import com.res.response.ResponseObject;
import com.res.service.CommonService;
import com.res.service.DashboardService;
import com.res.service.UserService;
import com.res.util.RESUtil;

import antlr.StringUtils;

@RestController
@RequestMapping(value = { "/admin/*", "/ee/*", "/ce/*", "/supdte/*", "/enc/*",
		"/ae/*", "/sube/*","/dirgp/*","/adminView/*","/ao/*" })
public class ReportController {
	
	public static final Logger logger = LoggerFactory
			.getLogger(ReportController.class);
	
	private User user;

	@Autowired
	private UserService userService;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private DashboardService dashboardService;
	
	
	@RequestMapping(value = "/viewFYWiseExpenditureReport", method = RequestMethod.GET)
	public ModelAndView viewFYWiseExpenditureReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewFYWiseExpenditureReport");

		ModelAndView modelAndView = new ModelAndView("common/report/viewFYWiseExpenditureReport"); 
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewExAgWiseExpenditureReport", method = RequestMethod.GET)
	public ModelAndView viewExAgWiseExpenditureReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewFYWiseExpenditureReport");

		ModelAndView modelAndView = new ModelAndView("common/report/viewExAgWiseExpenditureReport"); 
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewPaymentWiseExpenditureReport", method = RequestMethod.GET)
	public ModelAndView viewPaymentWiseExpenditureReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewPaymentWiseExpenditureReport");

		ModelAndView modelAndView = new ModelAndView("common/report/viewPaymentWiseExpenditureReport"); 
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewExeAgWiseExpenditureReport", method = RequestMethod.GET)
	public ModelAndView viewExeAgWiseExpenditureReport(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewFYWiseExpenditureReport");

		ModelAndView modelAndView = new ModelAndView("common/report/viewExeAgWiseExpenditureReport"); 
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewExeAgWiseExpenditureReportPayment", method = RequestMethod.GET)
	public ModelAndView viewExeAgWiseExpenditureReportPayment(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewExeAgWiseExpenditureReportPayment");

		ModelAndView modelAndView = new ModelAndView("common/report/viewExeAgWiseExpenditureReportPayment"); 
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewExeAgWiseExpenditureReportContg", method = RequestMethod.GET)
	public ModelAndView viewExeAgWiseExpenditureReportContgt(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewExeAgWiseExpenditureReportContg");

		ModelAndView modelAndView = new ModelAndView("common/report/viewExeAgWiseExpenditureReportContg"); 
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchFYWiseExpenditureList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchFYWiseExpenditureList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchFYWiseExpenditureList");

		List<DashboardBean> result = null;
			
		String fyFrom=null;
		String fyTo=null;
		if(null!= request.getParameter("financialYearId") && request.getParameter("financialYearId").length()>0){
			String[] strArr=request.getParameter("financialYearId").split("-");
			fyFrom=strArr[0];
			fyTo=strArr[1];
		}else{
			String currentFY=RESUtil.getCurrentFinancialFullYear();
			String[] strArr=currentFY.split("-");
			fyFrom="1/April/"+strArr[0];
			fyTo="31/March/"+strArr[1];
		}
		
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
		
		result = getFYWiseExpenditureList(office, user,
				loggedInUserRole, fyFrom, fyTo);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	@RequestMapping(value = "/fetchExAgWiseExpenditureList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchExAgWiseExpenditureList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchFYWiseExpenditureList");
		String executionAgencyId = request.getParameter("executionAgencyId");
		String districtId= request.getParameter("districtId");
		/*System.err.println("request.getParameter(\"districtId\")="+request.getParameter("districtId"));*/
		
		List<Long> list = null;
		if(!districtId.equals("")) {
		 list = Stream.of(districtId.split(","))
		        .map(Long::parseLong)
		        .collect(Collectors.toList());
		}
		
		String workTypeId= request.getParameter("workTypeId");
		
		String financialYearId = request.getParameter("financialYearId");
		
		
		System.err.println(list);
		

		List<DashboardBean> result = null;
			
		String fyFrom=null;
		String fyTo=null;
		if(null!= request.getParameter("financialYearId") && request.getParameter("financialYearId").length()>0){
			String[] strArr=request.getParameter("financialYearId").split("-");
			fyFrom=strArr[0];
			fyTo=strArr[1];
		}else{
			String currentFY=RESUtil.getCurrentFinancialFullYear();
			String[] strArr=currentFY.split("-");
			fyFrom="1/April/"+strArr[0];
			fyTo="31/March/"+strArr[1];
		}
		
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
		
		result = getExAgWiseExpenditureList(office, user,
				loggedInUserRole, fyFrom, fyTo,executionAgencyId,list);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchPaymentWiseExpenditureList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchPaymentWiseExpenditureList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchFYWiseExpenditureList");
		String executionAgencyId = request.getParameter("agencyTypeId");
		String exeOfficeId= request.getParameter("exeOfficeId");
		String lineDepartmentId= request.getParameter("lineDepartmentId");
		String accountHeadId= request.getParameter("accountHeadId");
		String workStatusId= request.getParameter("workStatusId");
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
		
		result = getPaymentWiseExpenditureList(office, user,
				loggedInUserRole, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	private List<DashboardBean> getPaymentWiseExpenditureList(Integer officeId, Integer userId, String loggedInUserRole, String currentFY, String executionAgencyId,String exeOfficeId, String lineDepartmentId
			,String accountHeadId,String workStatusId,String workTypeId,String workSubTypeId) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getPaymentWiseExpenditureList(-1, -1,
					officeId, -1, -1, -1, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getPaymentWiseExpenditureList(-1, -1, -1,
					userId, -1, -1, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// AE
			// login
		result = dashboardService.getPaymentWiseExpenditureList(-1, -1, -1,
		userId, -1, -1, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getPaymentWiseExpenditureList(-1, -1, -1,
					-1, userId, -1, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getPaymentWiseExpenditureList(-1,
					officeId, -1, -1, -1, -1, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getPaymentWiseExpenditureList(officeId,
					-1, -1, -1, -1, -1, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getPaymentWiseExpenditureList(-1, -1, -1,
					-1, -1, 17, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		} else {
			result = dashboardService.getPaymentWiseExpenditureList(-1, -1, -1,
					-1, -1, -1, currentFY,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,workTypeId,workSubTypeId);
		}
		return result;
	}
	
	
	
	
	
	@RequestMapping(value = "/fetchExeAgWiseExpenditureList/{financialYearId}/{exeOfficeId}/{executionAgencyId}/{lineDepartmentId}/{accountHeadId}/{workStatusId}/{month}/{workTypeId}/{workSubTypeId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<DashboardBean> fetchExAgWiseExpenditureList(@PathVariable String financialYearId,
			@PathVariable String exeOfficeId,
			@PathVariable String executionAgencyId,@PathVariable String lineDepartmentId,@PathVariable String accountHeadId,@PathVariable String workStatusId,
			@PathVariable String month,@PathVariable String workTypeId,@PathVariable String workSubTypeId,HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchFYWiseExpenditureList");
		
		/*System.err.println("request.getParameter(\"districtId\")="+request.getParameter("districtId"));*/
		String fyFrom=null;
		String fyTo=null;
		
		List<Long> list = null;
		/*if(districtId!="") {
		 list = Stream.of(districtId.split(","))
		        .map(Long::parseLong)
		        .collect(Collectors.toList());
		}
		*/
		/*String workTypeId= request.getParameter("workTypeId");*/
		
		
		
			
			
		
		

		List<DashboardBean> result = null;
			
		
		
		
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
		
		result = getExeAgWiseExpenditureList(office, user,
				loggedInUserRole, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return result;
	}
	
	
	private List<DashboardBean> getExeAgWiseExpenditureList(Integer officeId, Integer userId, String loggedInUserRole, String financialYearId, String executionAgencyId, String exeOfficeId,String lineDepartmentId
			,String accountHeadId,String workStatusId,String month,String workTypeId,String workSubTypeId) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getExeAgWiseExpenditureList(-1, -1,
					officeId, -1, -1, -1, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getExeAgWiseExpenditureList(-1, -1, -1,
					userId, -1, -1, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// AE
			// login
		result = dashboardService.getExeAgWiseExpenditureList(-1, -1, -1,
		userId, -1, -1, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																					// login
			result = dashboardService.getExeAgWiseExpenditureList(-1, -1, -1,
					-1, userId, -1, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getExeAgWiseExpenditureList(-1,
					officeId, -1, -1, -1, -1, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getExeAgWiseExpenditureList(officeId,
					-1, -1, -1, -1, -1, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getExeAgWiseExpenditureList(-1, -1, -1,
					-1, -1, 17, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
		} else {
			result = dashboardService.getExeAgWiseExpenditureList(-1, -1, -1,
					-1, -1, -1, financialYearId,executionAgencyId,exeOfficeId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId);
		}
		return result;
	}
	
	
	private List<DashboardBean> getExAgWiseExpenditureList(Integer officeId, Integer userId, String loggedInUserRole, String fyFrom, String fyTo, String executionAgencyId, List<Long> list) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getExAgWiseExpenditureList(-1, -1,
					officeId, -1, -1, -1, fyFrom, fyTo,executionAgencyId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getExAgWiseExpenditureList(-1, -1, -1,
					userId, -1, -1, fyFrom, fyTo,executionAgencyId);
		}  else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// AE
			// login
		result = dashboardService.getExAgWiseExpenditureList(-1, -1, -1,
		userId, -1, -1, fyFrom, fyTo,executionAgencyId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getExAgWiseExpenditureList(-1, -1, -1,
					-1, userId, -1, fyFrom, fyTo,executionAgencyId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getExAgWiseExpenditureList(-1,
					officeId, -1, -1, -1, -1, fyFrom, fyTo,executionAgencyId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getExAgWiseExpenditureList(officeId,
					-1, -1, -1, -1, -1, fyFrom, fyTo,executionAgencyId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getExAgWiseExpenditureList(-1, -1, -1,
					-1, -1, 17, fyFrom, fyTo,executionAgencyId);
		} else {
			result = dashboardService.getExAgWiseExpenditureList(-1, -1, -1,
					-1, -1, -1, fyFrom, fyTo,executionAgencyId);
		}
		return result;
	}
	
	
	private List<DashboardBean> getFYWiseExpenditureList(
			Integer officeId, Integer userId, String loggedInUserRole, String fyFrom, String fyTo) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getFYWiseExpenditureList(-1, -1,
					officeId, -1, -1, -1, fyFrom, fyTo);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getFYWiseExpenditureList(-1, -1, -1,
					userId, -1, -1, fyFrom, fyTo);
		}else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// AE
			// login
		result = dashboardService.getFYWiseExpenditureList(-1, -1, -1,
		userId, -1, -1, fyFrom, fyTo);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getFYWiseExpenditureList(-1, -1, -1,
					-1, userId, -1, fyFrom, fyTo);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getFYWiseExpenditureList(-1,
					officeId, -1, -1, -1, -1, fyFrom, fyTo);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getFYWiseExpenditureList(officeId,
					-1, -1, -1, -1, -1, fyFrom, fyTo);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getFYWiseExpenditureList(-1, -1, -1,
					-1, -1, 17, fyFrom, fyTo);
		} else {
			result = dashboardService.getFYWiseExpenditureList(-1, -1, -1,
					-1, -1, -1, fyFrom, fyTo);
		}
		return result;
	}
	
	@RequestMapping(value = "getCurrentFinancialYear", method = RequestMethod.GET)
	public ResponseObject getCurrentFinancialYear(HttpServletRequest request) {
		ResponseObject responseObject=new ResponseObject();
		responseObject.setNumber(RESUtil.getCurrentFinancialFullYear());
		return responseObject;
	}
	
	@RequestMapping(value = "getIdFromFinancialYearName", method = RequestMethod.GET)
	public Long getIdFromFinancialYear(@RequestParam(value = "fyName", required = true) String fyName,
			HttpServletRequest request) {
		String[] strArr=fyName.split("-");
 		String fullFYName="1/April/"+strArr[0]+"-31/March/"+strArr[1];
		return commonService.getIdFromFinancialYearName(fullFYName);
	}

	//////  
	@RequestMapping(value = "/viewEeWiseExpenditureReport/{monthYear}", method = RequestMethod.GET)
	public ModelAndView viewEeReportsMappingStatus(@PathVariable (value= "monthYear", required = false) String monthYear,
			  HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewEeWiseExpenditureReport");

		ModelAndView modelAndView = new ModelAndView("common/report/viewEeWiseExpenditureReport"); 
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchEeWiseExpenditureList/{monthYear}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchEeWiseExpenditureList(@PathVariable (value= "monthYear", required = false)  String monthYear  , HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchEeWiseExpenditureList");

		List<DashboardBean> result = null;
			
		String month = null;
		String year = null;
		
		if (null != monthYear && monthYear.length() > 0){
			String[] strArr = monthYear.split(",");
			month= strArr[0];
			year = strArr[1];
		}
		
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
		
		result = getEeWiseExpenditureForMonthYearList(office, user, loggedInUserRole, month, year);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	private List<DashboardBean> getEeWiseExpenditureForMonthYearList(
			Integer officeId, Integer userId, String loggedInUserRole, String month, String year) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getEeWiseExpenditureForMonthYearList(-1, -1,
					officeId, -1, -1, -1, month, year);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE  login
			result = dashboardService.getEeWiseExpenditureForMonthYearList(-1, -1, -1,
					userId, -1, -1, month, year);
		}  else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// AE  login
			result = dashboardService.getEeWiseExpenditureForMonthYearList(-1, -1, -1,
					userId, -1, -1, month, year);
		}else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE login
			result = dashboardService.getEeWiseExpenditureForMonthYearList(-1, -1, -1,
					-1, userId, -1, month, year);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE login 
			result = dashboardService.getEeWiseExpenditureForMonthYearList(-1,
					officeId, -1, -1, -1, -1, month, year);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE  login 
			result = dashboardService.getEeWiseExpenditureForMonthYearList(officeId,
					-1, -1, -1, -1, -1, month, year);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE login 
			result = dashboardService.getEeWiseExpenditureForMonthYearList(-1, -1, -1,
					-1, -1, 17, month, year);
		} else {
			result = dashboardService.getEeWiseExpenditureForMonthYearList(-1, -1, -1,
					-1, -1, -1, month, year);
		}
		return result;
	}
	
	/////
	@RequestMapping(value = "/viewAccountHeadWiseExpenditureReport/{monthYear}/{eeId}", method = RequestMethod.GET)
	public ModelAndView viewAccoutHeadWiseExpenditureReport(@PathVariable (value= "monthYear", required = false) String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, 
			  HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewAccountHeadWiseExpenditureReport");

		ModelAndView modelAndView = new ModelAndView("common/report/viewAccountWiseExpenditureReport"); 
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchAccountHeadWiseExpenditureList/{monthYear}/{eeId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchAccountHeadWiseExpenditureList(@PathVariable (value= "monthYear", required = false)  String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchAccountHeadWiseExpenditureList");

		List<DashboardBean> result = null;
			
		String month = null;
		String year = null;
		
		if (null != monthYear && monthYear.length() > 0){
			String[] strArr = monthYear.split(",");
			month= strArr[0];
			year = strArr[1];
		}
		
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
		
		result = getAccountHeadWiseExpenditureForMonthYearAndEeList(office, user, loggedInUserRole, month, year, eeId);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	private List<DashboardBean> getAccountHeadWiseExpenditureForMonthYearAndEeList(
			Integer officeId, Integer userId, String loggedInUserRole, String month, String year, Integer eeId) {
		List<DashboardBean>  result = dashboardService.getAccountHeadWiseExpenditureForMonthYearAndEeList(-1, -1, eeId,
				-1, -1, -1, month, year);
		return result;
	}
	
	////
	@RequestMapping(value = "/viewLineDeptWiseExpenditureReport/{monthYear}/{eeId}", method = RequestMethod.GET)
	public ModelAndView viewLineDeptWiseExpenditureReport(@PathVariable (value= "monthYear", required = false) String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, 
			  HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewLineDeptWiseExpenditureReport");
		ModelAndView modelAndView = new ModelAndView("common/report/viewLineDeptWiseExpenditureReport");
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchLineDeptWiseExpenditureList/{monthYear}/{eeId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchLineDeptWiseExpenditureList(@PathVariable (value= "monthYear", required = false)  String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchLineDeptWiseExpenditureList");

		List<DashboardBean> result = null;
			
		String month = null;
		String year = null;
		
		if (null != monthYear && monthYear.length() > 0){
			String[] strArr = monthYear.split(",");
			month= strArr[0];
			year = strArr[1];
		}
		
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
		
		result = getLineDeptWiseExpenditureForMonthYearAndEeList(office, user, loggedInUserRole, month, year, eeId);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	private List<DashboardBean> getLineDeptWiseExpenditureForMonthYearAndEeList(
			Integer officeId, Integer userId, String loggedInUserRole, String month, String year, Integer eeId) {
		List<DashboardBean> result  = dashboardService.getLineDeptWiseExpenditureForMonthYearAndEeList(-1, -1, eeId,
				-1, -1, -1, month, year);
		return result;
	}
	
	///
	@RequestMapping(value = "/viewWorkTypeWiseExpenditureReport/{monthYear}/{eeId}", method = RequestMethod.GET)
	public ModelAndView viewWorkTypeWiseExpenditureReport(@PathVariable (value= "monthYear", required = false) String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, 
			  HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewWorkTypeWiseExpenditureReport");

		ModelAndView modelAndView = new ModelAndView("common/report/viewWorkTypeWiseExpenditureReport");
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchWorkTypeExpenditureList/{monthYear}/{eeId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkTypeExpenditureList(@PathVariable (value= "monthYear", required = false)  String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchWorkTypeExpenditureList");

		List<DashboardBean> result = null;
			
		String month = null;
		String year = null;
		
		if (null != monthYear && monthYear.length() > 0){
			String[] strArr = monthYear.split(",");
			month= strArr[0];
			year = strArr[1];
		}
		
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
		
		result = getWorkTypeWiseExpenditureForMonthYearAndEeList(office, user, loggedInUserRole, month, year, eeId);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	private List<DashboardBean> getWorkTypeWiseExpenditureForMonthYearAndEeList(
			Integer officeId, Integer userId, String loggedInUserRole, String month, String year, Integer eeId) {
		List<DashboardBean> result  = dashboardService.getWorkTypeWiseExpenditureForMonthYearAndEeList(-1, -1, eeId,
				-1, -1, -1, month, year);
		return result;
	}
	
	///////
	@RequestMapping(value = "/viewWorkWiseExpenditureReportByWorkType/{monthYear}/{eeId}/{workType}", method = RequestMethod.GET)
	public ModelAndView viewWorkWiseExpenditureReportByWorkType(@PathVariable (value= "monthYear", required = false) String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, 
			@PathVariable (value= "workType", required = false) Integer workType, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewWorkWiseExpenditureReportByWorkType");

		ModelAndView modelAndView = new ModelAndView("common/report/viewWorkWiseByWorkTypeExpenditureReport");
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchWorkExpenditureByWorkTypeList/{monthYear}/{eeId}/{workType}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkExpenditureByWorkTypeList(@PathVariable (value= "monthYear", required = false)  String monthYear,
			@PathVariable (value= "eeId", required = false) Integer eeId, 
			@PathVariable (value= "workType", required = false) Integer workType, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchWorkExpenditureByWorkTypeList");

		List<DashboardBean> result = null;
			
		String month = null;
		String year = null;
		
		if (null != monthYear && monthYear.length() > 0){
			String[] strArr = monthYear.split(",");
			month= strArr[0];
			year = strArr[1];
		}
		
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
		
		result = getWorkWiseExpenditureForMonthYearAndEeAndWorkTypeList(office, user, loggedInUserRole, month, year, eeId, workType);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	private List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndWorkTypeList(
			Integer officeId, Integer userId, String loggedInUserRole, String month, String year, Integer eeId, Integer workType) {
		List<DashboardBean> result  = dashboardService.getWorkWiseExpenditureForMonthYearAndEeAndWorkTypeList(-1, -1, eeId,
				-1, -1, -1, month, year, workType);
		return result;
	}
	
	////////
	@RequestMapping(value = "/viewWorkWiseExpenditureReportByLineDept/{monthYear}/{eeId}/{workType}", method = RequestMethod.GET)
	public ModelAndView viewWorkWiseExpenditureReportByLineDeptamhappy
	(@PathVariable (value= "monthYear", required = false) String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, 
			@PathVariable (value= "workType", required = false) Integer workType, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewWorkWiseExpenditureReportByLineDept");

		ModelAndView modelAndView = new ModelAndView("common/report/viewWorkWiseByLineDeptExpenditureReport");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchWorkExpenditureByLineDeptList/{monthYear}/{eeId}/{lineDept}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkExpenditureByLineDeptList(@PathVariable (value= "monthYear", required = false)  String monthYear,
			@PathVariable (value= "eeId", required = false) Integer eeId, 
			@PathVariable (value= "lineDept", required = false) Integer lineDept, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchWorkExpenditureByLineDeptList");

		List<DashboardBean> result = null;
			
		String month = null;
		String year = null;
		
		if (null != monthYear && monthYear.length() > 0){
			String[] strArr = monthYear.split(",");
			month= strArr[0];
			year = strArr[1];
		}
		
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
		
		result = getWorkWiseExpenditureForMonthYearAndEeAndLineDeptList(office, user, loggedInUserRole, month, year, eeId, lineDept);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	private List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndLineDeptList(
			Integer officeId, Integer userId, String loggedInUserRole, String month, String year, Integer eeId, Integer lineDept) {
		List<DashboardBean> result;
		
		result = dashboardService.getWorkWiseExpenditureForMonthYearAndEeAndLineDeptList(-1, -1, eeId,
				-1, -1, -1, month, year, lineDept);
		return result;
	}
	
////////
	@RequestMapping(value = "/viewWorkWiseExpenditureReportByAccHead/{monthYear}/{eeId}/{accHead}", method = RequestMethod.GET)
	public ModelAndView viewWorkWiseExpenditureReportByAccHead
	(@PathVariable (value= "monthYear", required = false) String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, 
			@PathVariable (value= "accHead", required = false) Integer accHead, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewWorkWiseExpenditureReportByAccHead");

		ModelAndView modelAndView = new ModelAndView("common/report/viewWorkWiseByAccountHeadExpenditureReport");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchWorkExpenditureByAccHeadList/{monthYear}/{eeId}/{accHead}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchWorkExpenditureByAccHeadList(@PathVariable (value= "monthYear", required = false)  String monthYear,
			@PathVariable (value= "eeId", required = false) Integer eeId, 
			@PathVariable (value= "accHead", required = false) Integer accHead, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching fetchWorkExpenditureByAccHeadList");

		List<DashboardBean> result = null;
			
		String month = null;
		String year = null;
		
		if (null != monthYear && monthYear.length() > 0){
			String[] strArr = monthYear.split(",");
			month= strArr[0];
			year = strArr[1];
		}
	 
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
		
		result = getWorkWiseExpenditureForMonthYearAndEeAndAccountHeadList(office, user, loggedInUserRole, month, year, eeId, accHead);
		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(result.size());
		workDistrictJson.setiTotalRecords(result.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(workDistrictJson);

		return json;
	}
	
	private List<DashboardBean> getWorkWiseExpenditureForMonthYearAndEeAndAccountHeadList(
			Integer officeId, Integer userId, String loggedInUserRole, String month, String year, Integer eeId, Integer accHead) {
		List<DashboardBean> result;
		
		result = dashboardService.getWorkWiseExpenditureForMonthYearAndEeAndAccHeadList(-1, -1, eeId,
				-1, -1,  month, year, accHead);
		return result;
	}
	
	@RequestMapping(value = "/viewThreeTabExpenditureReport/{monthYear}/{eeId}", method = RequestMethod.GET)
	public ModelAndView viewThreeTabExpenditureReport(@PathVariable (value= "monthYear", required = false) String monthYear,
			@PathVariable (value= "eeId", required = false)  Integer eeId, 
			  HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Displaying viewThreeTabExpenditureReport");

		ModelAndView modelAndView = new ModelAndView("common/report/viewThreeTabExpenditureReport"); 
		return modelAndView;
	}
	
}
