package com.res.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.res.bean.DashboardBean;
import com.res.bean.UserBean;
import com.res.constants.RESConstants;
import com.res.json.WorkDistrictJson;
import com.res.service.DashboardService;

@Controller
public class DashboardController extends BaseController {

	@Autowired
	private DashboardService dashboardService;

	@RequestMapping(value = "/workTypeWiseStatus", method = RequestMethod.GET)
	@ResponseBody
	public String getDashboardDataForDepartmentWiseRequestsStatus(
			@RequestParam(value = "loggedInUserRole", required = false) String loggedInRole,
			@RequestParam(value = "officeId", required = false) Integer officeId,
			@RequestParam(value = "userId", required = false) Integer userId, HttpServletRequest request) {

		List<DashboardBean> result = null;

		UserBean userDetail = fetchLoggedInUserDetails(request);
		if (null == userDetail) {
			if (null == loggedInRole) {
				result = dashboardService.getDashboardDataWorkTypeWise(-1, -1, -1, -1, -1);
			} else {
				result = getDashboardDataForWorkTypeWise(officeId, userId, loggedInRole);
			}
		} else {
			String loggedInUserRole = userDetail.getLoggedInUserRole();
			Integer office = null;
			if (null != userDetail.getOfficeId()) {
				office = userDetail.getOfficeId().intValue();
			}
			Integer user = null;
			if (null != userDetail.getId()) {
				user = userDetail.getId().intValue();
			}
			result = getDashboardDataForWorkTypeWise(office, user, loggedInUserRole);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(result);
		return json2;
	}

	private List<DashboardBean> getDashboardDataForWorkTypeWise(Integer officeId, Integer userId,
			String loggedInUserRole) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getDashboardDataWorkTypeWise(-1, -1, officeId, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getDashboardDataWorkTypeWise(-1, -1, -1, userId, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getDashboardDataWorkTypeWise(-1, -1, -1, -1, userId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// SubE
			// login
			result = dashboardService.getDashboardDataWorkTypeWise(-1, -1, -1, -1, userId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getDashboardDataWorkTypeWise(-1, officeId, -1, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getDashboardDataWorkTypeWise(officeId, -1, -1, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getDashboardDataWorkTypeWiseLineDept(17);
		} else {
			result = dashboardService.getDashboardDataWorkTypeWise(-1, -1, -1, -1, -1);
		}
		return result;
	}

	@RequestMapping(value = "/workSubTypeWiseStatus", method = RequestMethod.GET)
	@ResponseBody
	public String getDashboardDataForWorkSubTypeWise(
			@RequestParam(value = "workTypeId", required = true) Integer workTypeId, HttpServletRequest request) {
		List<DashboardBean> result = null;

		UserBean userDetail = fetchLoggedInUserDetails(request);
		if (null == userDetail) {
			result = dashboardService.getDashboardDataWorkSubTypeWise(workTypeId, -1, -1, -1, -1, -1);
		} else {
			String loggedInUserRole = userDetail.getLoggedInUserRole();

			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
				result = dashboardService.getDashboardDataWorkSubTypeWise(workTypeId, -1, -1,
						userDetail.getOfficeId().intValue(), -1, -1);
			} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																		// login
				result = dashboardService.getDashboardDataWorkSubTypeWise(workTypeId, -1, -1, -1,
						userDetail.getId().intValue(), -1);
			} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																				// login
				result = dashboardService.getDashboardDataWorkSubTypeWise(workTypeId, -1, -1, -1, -1,
						userDetail.getId().intValue());
			} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// SubE
				// login
				result = dashboardService.getDashboardDataWorkSubTypeWise(workTypeId, -1, -1, -1, -1,
						userDetail.getId().intValue());
			} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																				// login{
				result = dashboardService.getDashboardDataWorkSubTypeWise(workTypeId, -1,
						userDetail.getOfficeId().intValue(), -1, -1, -1);
			} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																		// login{
				result = dashboardService.getDashboardDataWorkSubTypeWise(workTypeId,
						userDetail.getOfficeId().intValue(), -1, -1, -1, -1);
			} else {
				result = dashboardService.getDashboardDataWorkSubTypeWise(workTypeId, -1, -1, -1, -1, -1);
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(result);
		return json2;
	}

	@RequestMapping(value = "/districtWiseWorkStatus", method = RequestMethod.GET)
	@ResponseBody
	public String getDashboardDataForDistrictWiseWorksStatus(
			@RequestParam(value = "isThematicMap", required = false) Boolean isThematicMap,
			@RequestParam(value = "loggedInUserRole", required = false) String loggedInRole,
			@RequestParam(value = "officeId", required = false) Integer officeId,
			@RequestParam(value = "userId", required = false) Integer userId, HttpServletRequest request) {
		List<DashboardBean> result = null;

		UserBean userDetail = fetchLoggedInUserDetails(request);
		if (null == userDetail) {
			if (null == loggedInRole) {
				result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1, -1, isThematicMap,
						-1, -1);
			} else {
				result = getDashboardDataForDistrictWiseWorkStatus(isThematicMap, officeId, userId, loggedInRole);
			}
		} else {
			String loggedInUserRole = userDetail.getLoggedInUserRole();
			Integer office = null;
			if (null != userDetail.getOfficeId()) {
				office = userDetail.getOfficeId().intValue();
			}
			Integer user = null;
			if (null != userDetail.getId()) {
				user = userDetail.getId().intValue();
			}

			result = getDashboardDataForDistrictWiseWorkStatus(isThematicMap, office, user, loggedInUserRole);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(result);
		return json2;
	}

	private List<DashboardBean> getDashboardDataForDistrictWiseWorkStatus(Boolean isThematicMap, Integer officeId,
			Integer userId, String loggedInUserRole) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, officeId, -1, -1,
					isThematicMap, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, userId, -1, isThematicMap,
					-1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1, userId, isThematicMap,
					-1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// SubE
			// login
			result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1, userId, isThematicMap,
					-1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, officeId, -1, -1, -1,
					isThematicMap, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(officeId, -1, -1, -1, -1,
					isThematicMap, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getDashboardDataWorkStatusWiseLineDept(17);
		} else {
			result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1, -1, isThematicMap, -1,
					-1);
		}
		return result;
	}

	@RequestMapping(value = "/districtWiseWorkStatusForTable", method = RequestMethod.GET)
	@ResponseBody
	public String getDashboardTableDataForDistrictWiseWorksStatus(
			@RequestParam(value = "isThematicMap", required = false) Boolean isThematicMap,
			final HttpServletRequest request) {
		List<DashboardBean> result = null;
		Integer pageNumber = 0;
		Integer pageDisplayLength = 10;
		Integer displayStart = 0;
		String searchParameter = null;
		int firstResult = 0;
		int totalResult = 0;

		if (null != request.getParameter("iDisplayLength")) {
			// Fetch Page display length
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		}

		if (null != request.getParameter("iDisplayStart")) {
			// Fetch displayStart
			displayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		}

		pageNumber = (displayStart / pageDisplayLength) + 1;

		firstResult = (pageNumber - 1) * pageDisplayLength;

		UserBean userDetail = fetchLoggedInUserDetails(request);

		if (null == userDetail) {
			totalResult = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1, -1, isThematicMap, -1, -1).size();
			result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1, -1, isThematicMap,
					firstResult, pageDisplayLength);
		} else {
			String loggedInUserRole = userDetail.getLoggedInUserRole();
			if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
				result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1,
						userDetail.getOfficeId().intValue(), -1, -1, isThematicMap, firstResult, pageDisplayLength);
				totalResult = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1,
						userDetail.getOfficeId().intValue(), -1, -1, isThematicMap, -1, -1).size();
			} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
				totalResult = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1,
						userDetail.getId().intValue(), -1, isThematicMap, -1, -1).size(); // login
				result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1,
						userDetail.getId().intValue(), -1, isThematicMap, firstResult, pageDisplayLength);
			} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
				totalResult = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1,
						userDetail.getId().intValue(), isThematicMap, -1, -1).size(); // login
				result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1,
						userDetail.getId().intValue(), isThematicMap, firstResult, pageDisplayLength);
			} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// SubE
				totalResult = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1,
						userDetail.getId().intValue(), isThematicMap, -1, -1).size(); // login
				result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1,
						userDetail.getId().intValue(), isThematicMap, firstResult, pageDisplayLength);
			} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
				totalResult = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1,
						userDetail.getOfficeId().intValue(), -1, -1, -1, isThematicMap, -1, -1).size(); // login{
				result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, userDetail.getId().intValue(),
						-1, -1, -1, isThematicMap, firstResult, pageDisplayLength);
			} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
				totalResult = dashboardService.getDashboardDataForDistrictWiseWorksStatus(
						userDetail.getOfficeId().intValue(), -1, -1, -1, -1, isThematicMap, -1, -1).size(); // login{
				result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(
						userDetail.getOfficeId().intValue(), -1, -1, -1, -1, isThematicMap, firstResult,
						pageDisplayLength);
			} else {
				totalResult = dashboardService
						.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1, -1, isThematicMap, -1, -1).size();
				result = dashboardService.getDashboardDataForDistrictWiseWorksStatus(-1, -1, -1, -1, -1, isThematicMap,
						firstResult, pageDisplayLength);
			}
		}

		WorkDistrictJson workDistrictJson = new WorkDistrictJson();
		workDistrictJson.setAaData(result);
		workDistrictJson.setiTotalDisplayRecords(totalResult);
		workDistrictJson.setiTotalRecords(totalResult);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(workDistrictJson);
		return json2;
	}

	@RequestMapping(value = "/statusWiseWorkStatus", method = RequestMethod.GET)
	@ResponseBody
	public String getDashboardDataForStatusWisePieChart(
			@RequestParam(value = "loggedInUserRole", required = false) String loggedInRole,
			@RequestParam(value = "officeId", required = false) Integer officeId,
			@RequestParam(value = "userId", required = false) Integer userId, HttpServletRequest request) {
		List<DashboardBean> result = null;

		UserBean userDetail = fetchLoggedInUserDetails(request);
		if (null == userDetail) {
			if (null == loggedInRole) {
				result = dashboardService.getDashboardDataForStatusWisePieChart(-1, -1, -1, -1, -1);
			} else {
				result = getDashboardDataForStatusWisePieChart(officeId, userId, loggedInRole);
			}
		} else {
			String loggedInUserRole = userDetail.getLoggedInUserRole();
			Integer office = null;
			if (null != userDetail.getOfficeId()) {
				office = userDetail.getOfficeId().intValue();
			}
			Integer user = null;
			if (null != userDetail.getId()) {
				user = userDetail.getId().intValue();
			}
			result = getDashboardDataForStatusWisePieChart(office, user, loggedInUserRole);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(result);
		return json2;
	}

	private List<DashboardBean> getDashboardDataForStatusWisePieChart(Integer officeId, Integer userId,
			String loggedInUserRole) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getDashboardDataForStatusWisePieChart(-1, -1, officeId, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getDashboardDataForStatusWisePieChart(-1, -1, -1, userId, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getDashboardDataForStatusWisePieChart(-1, -1, -1, -1, userId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// SubE
			// login
			result = dashboardService.getDashboardDataForStatusWisePieChart(-1, -1, -1, -1, userId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getDashboardDataForStatusWisePieChart(-1, officeId, -1, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getDashboardDataForStatusWisePieChart(officeId, -1, -1, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// dir gp
			// login{
			result = dashboardService.getDashboardDataWorkStatusWiseLineDept(17);
		} else {
			result = dashboardService.getDashboardDataForStatusWisePieChart(-1, -1, -1, -1, -1);
		}
		return result;
	}

	@RequestMapping(value = "/districtWisePieChart", method = RequestMethod.GET)
	@ResponseBody
	public String getDashboardDataDistrictWisePieChart(
			@RequestParam(value = "loggedInUserRole", required = false) String loggedInRole,
			@RequestParam(value = "officeId", required = false) Integer officeId,
			@RequestParam(value = "userId", required = false) Integer userId, HttpServletRequest request) {
		List<DashboardBean> result = null;

		UserBean userDetail = fetchLoggedInUserDetails(request);
		if (null == userDetail) {
			if (null == loggedInRole) {
				result = dashboardService.getDashboardDataForDistrictWisePieChart(-1, -1, -1, -1, -1);
			} else {
				result = getDashboardDataForDistrictWisePieChart(officeId, userId, loggedInRole);
			}
		} else {
			String loggedInUserRole = userDetail.getLoggedInUserRole();
			Integer office = null;
			if (null != userDetail.getOfficeId()) {
				office = userDetail.getOfficeId().intValue();
			}
			Integer user = null;
			if (null != userDetail.getId()) {
				user = userDetail.getId().intValue();
			}
			result = getDashboardDataForDistrictWisePieChart(office, user, loggedInUserRole);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(result);
		return json2;
	}

	private List<DashboardBean> getDashboardDataForDistrictWisePieChart(Integer officeId, Integer userId,
			String loggedInUserRole) {
		List<DashboardBean> result;
		if (loggedInUserRole.equals(RESConstants.ROLE_EE)) {// EE login
			result = dashboardService.getDashboardDataForDistrictWisePieChart(-1, -1, officeId, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_AE)) {// AE
																	// login
			result = dashboardService.getDashboardDataForDistrictWisePieChart(-1, -1, -1, officeId, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUB_ENGG)) {// SubE
																			// login
			result = dashboardService.getDashboardDataForDistrictWisePieChart(-1, -1, -1, -1, officeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// SDO
			// login
			result = dashboardService.getDashboardDataForDistrictWisePieChart(-1, -1, -1, -1, officeId);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
																			// login{
			result = dashboardService.getDashboardDataForDistrictWisePieChart(-1, officeId, -1, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_CE)) {// CE
																	// login{
			result = dashboardService.getDashboardDataForDistrictWisePieChart(officeId, -1, -1, -1, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_DIR_GP)) {// CE
			// login{
			result = dashboardService.getDashboardDataDistrictWiseLineDeptPieChart(17);
		} else {
			result = dashboardService.getDashboardDataForDistrictWisePieChart(-1, -1, -1, -1, -1);
		}
		return result;
	}

	@RequestMapping(value = "/ceOfficeWiseWorksCount", method = RequestMethod.GET)
	@ResponseBody
	public String ceOfficeWiseWorksCount(HttpServletRequest request) {

		List<DashboardBean> result = dashboardService.ceOfficeWiseWorksCount();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(result);
		return json2;
	}

	@RequestMapping(value = "/seOfficeWiseWorksCount", method = RequestMethod.GET)
	@ResponseBody
	public String seOfficeWiseWorksCount(@RequestParam(value = "ceOfficeId", required = true) Integer ceOfficeId,
			HttpServletRequest request) {
		List<DashboardBean> result = null;
		UserBean userDetail = fetchLoggedInUserDetails(request);
		if (null == ceOfficeId && null != userDetail) {
			String loggedInUserRole = userDetail.getLoggedInUserRole();
			Integer office = null;
			if (null != userDetail.getOfficeId() && RESConstants.ROLE_CE.equals(loggedInUserRole)) {
				office = userDetail.getOfficeId().intValue();
			}
			result = dashboardService.seOfficeWiseWorksCount(office);
		} else {
			result = dashboardService.seOfficeWiseWorksCount(ceOfficeId);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(result);
		return json2;
	}

	@RequestMapping(value = "/eeOfficeWiseWorksCount", method = RequestMethod.GET)
	@ResponseBody
	public String eeOfficeWiseWorksCount(@RequestParam(value = "seOfficeId", required = true) Integer seOfficeId,
			HttpServletRequest request) {
		List<DashboardBean> result = null;
		UserBean userDetail = fetchLoggedInUserDetails(request);
		if (null == seOfficeId && null != userDetail) {
			String loggedInUserRole = userDetail.getLoggedInUserRole();
			Integer office = null;
			if (null != userDetail.getOfficeId() && RESConstants.ROLE_SUPDT_ENGG.equals(loggedInUserRole)) {
				office = userDetail.getOfficeId().intValue();
			}
			result = dashboardService.eeOfficeWiseWorksCount(office);
		} else {
			result = dashboardService.eeOfficeWiseWorksCount(seOfficeId);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(result);
		return json2;
	}

	@RequestMapping(value = "/workRequestStatusWiseCount", method = RequestMethod.GET)
	@ResponseBody
	public String WorkRequestStatusWiseCount(
			@RequestParam(value = "loggedInUserRole", required = false) String loggedInRole,
			@RequestParam(value = "officeId", required = false) Integer officeId,
			@RequestParam(value = "userId", required = false) Integer userId, HttpServletRequest request) {

		List<DashboardBean> result = null;

		UserBean userDetail = fetchLoggedInUserDetails(request);
		if (null == userDetail) {
			if (null == loggedInRole) {
				result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(-1, -1, -1, -1, -1, -1);
			} else {
				result = getDashboardDataForWorkRequestStatusWiseCount(officeId, userId, loggedInRole);
			}
		} else {
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
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(result);
		return json2;
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
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SDO)) {// Sdo
			// login
			result = dashboardService.getDashboardDataForWorkRequestStatusWiseCount(-1, -1, -1, -1, userId, -1);
		} else if (loggedInUserRole.equals(RESConstants.ROLE_SUPDT_ENGG)) {// SUPDTE
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

}
