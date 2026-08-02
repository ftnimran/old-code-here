package com.res.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.res.bean.BudgetAllotmentBean;
import com.res.bean.BudgetAllotmentEEOfficeBean;
import com.res.bean.BudgetRequestBean;
import com.res.bean.UserBean;
import com.res.constants.RESConstants;
import com.res.json.BudgetAllotmentEEOfficeJson;
import com.res.json.BudgetAllotmentJson;
import com.res.json.BudgetRequestDetailJson;
import com.res.json.BudgetRequestJson;
import com.res.repository.UserRepository;
import com.res.response.ResponseObject;
import com.res.service.AdminService;
import com.res.service.BudgetService;
import com.res.service.CommonService;
import com.res.service.UserService;
import com.res.util.RESUtil;

@RestController
@RequestMapping("/ao/*")
public class AOController extends BaseController{
	
	public static final Logger logger = LoggerFactory.getLogger(AOController.class);
	
	private User user;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BudgetService budgetService;
	
	@Autowired
	private UserRepository userRepository;
	
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
		ModelAndView modelAndView = new ModelAndView("accountOfficer/aoHome");
		
		if(user!=null){
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying home page");
			//Users userEntity = userService.findByUserName(user.getUsername());
			modelAndView.addObject("loggedInUserName", user.getUsername());
			
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
		ModelAndView modelAndView = new ModelAndView("accountOfficer/dashboard");
		return modelAndView;

	}
	
	@RequestMapping(value = "/budgetRequestApprovalList", method = RequestMethod.GET)
	public ModelAndView budgetRequestApprovalList(HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting budgetRequestApproval ");

		ModelAndView modelAndView = new ModelAndView("enc/budgetRequestApprovalList");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/budgetRequestApproval/{id}", method = RequestMethod.GET)
	public ModelAndView budgetRequestApproval(@PathVariable Long id,   HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting budgetRequestApproval ");

		ModelAndView modelAndView = new ModelAndView("enc/budgetRequestApproval");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchBudgetRequestApprovalList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBudgetRequestApprovalList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Budget Request List");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
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

		String loggedInUserRole = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BudgetRequestJson budgetRequestJson = budgetService.fetchBudgetRequestList(pageable, searchBoxVal, loggedInUserRole, user.getUsername());
				
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(budgetRequestJson);

		return json;
	}	
	
	@RequestMapping(value = "/fetchBudgetRequestApproval/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBudgetRequestApproval(@PathVariable Long id , HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Budget Request List");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
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

		String loggedInUserRole = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BudgetRequestDetailJson budgetRequestDetailJson = budgetService.fetchBudgetRequestDetailList(pageable, id, loggedInUserRole, user.getUsername());
				
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(budgetRequestDetailJson);

		return json;
	}
	
	@RequestMapping(value = "/saveBudgetRequestApproval", method = RequestMethod.POST)
	public ResponseObject saveBudgetRequest(@RequestBody BudgetRequestBean budgetRequestBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Adding Bill data");
		ResponseObject response = new ResponseObject();

		String errorMsg = budgetService.saveBudgetRequest(budgetRequestBean);
		
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Bill added successfully!");
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Bill added successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/saveEditBudgetRequest", method = RequestMethod.POST)
	public ResponseObject saveEditBudgetRequest(@RequestBody BudgetRequestBean budgetRequestBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - saveEditBudgetRequest is called");
		ResponseObject response = new ResponseObject();

		String errorMsg = budgetService.saveEditBudgetRequest(budgetRequestBean);
		
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - " + errorMsg);
		} else {
			
			if(budgetRequestBean.getStatusId() == 5) {
				response.setSuccessMessage("Budget Request Approved !");
				
				logger.info("User - " + user.getUsername() + ", Role - "
						+ user.getAuthorities() + " - Budget Request Approved!");
			} else if(budgetRequestBean.getStatusId() == 4) {
				response.setSuccessMessage("Budget Request Rejected !");
				logger.info("User - " + user.getUsername() + ", Role - "
						+ user.getAuthorities() + " - Budget Request Rejected!");
				
			} else if(budgetRequestBean.getStatusId() == 3) {
				response.setSuccessMessage("Budget Request Draft Saved  !");
				logger.info("User - " + user.getUsername() + ", Role - "
						+ user.getAuthorities() + " - Budget Request Draft Saved !");
			} else {
				response.setSuccessMessage("Budget Edited successfully!");
				logger.info("User - " + user.getUsername() + ", Role - "
						+ user.getAuthorities() + " - Budget Edited successfully!");
			}
			
		}
		return response;
	}
	
	
	@RequestMapping(value = "/viewBudgetRequest/{id}", method = RequestMethod.GET)
	public ModelAndView viewBudgetRequest(@PathVariable Long id , HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting Budget Request  ");

		ModelAndView modelAndView = new ModelAndView("ee/viewBudgetRequest");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		return modelAndView;
	}
	@RequestMapping(value = "/viewBudgetRequestAllotement/{id}", method = RequestMethod.GET)
	public ModelAndView viewBudgetRequestAllotement(@PathVariable Long id , HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting Budget Request  ");

		ModelAndView modelAndView = new ModelAndView("ee/viewBudgetRequestAllotement");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewBudgetRequestAllotementAllAccHead/{accountHeadId}/{accountHead}", method = RequestMethod.GET)
	public ModelAndView viewBudgetRequestAllotementAllAccHead(@PathVariable Long accountHeadId ,@PathVariable String accountHead,HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting Budget Request  ");

		ModelAndView modelAndView = new ModelAndView("ee/viewBudgetRequestAllotementAllAccHead");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchBudgetRequestDetailList/{budgetRequestId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBudgetRequestDetailList(@PathVariable Long budgetRequestId,   HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Budget Request Detail List");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
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

		String loggedInUserRole = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BudgetRequestDetailJson budgetRequestJson = budgetService.fetchBudgetRequestDetailList(pageable, budgetRequestId, loggedInUserRole, user.getUsername());
				
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(budgetRequestJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchBudgetRequestAllotementList/{budgetAllotmentId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBudgetRequestAllotementList(@PathVariable Long budgetAllotmentId,   HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Budget Allotment Detail List");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
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

		String loggedInUserRole = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BudgetAllotmentEEOfficeJson budgetAllotmentEEOfficeJson = budgetService.fetchBudgetRequestAllotementList(pageable, budgetAllotmentId, loggedInUserRole, user.getUsername());
				
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(budgetAllotmentEEOfficeJson);

		return json;
	}
	
	
	@RequestMapping(value = "/fetchBudgetAllotmentListAllAccHead/{accountHeadId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBudgetAllotmentListAllAccHead(@PathVariable Long accountHeadId,   HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Budget Allotment Detail List");

		String searchBoxVal = request.getParameter("searchBoxVal");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);

		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
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

		String loggedInUserRole = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BudgetAllotmentJson budgetRequestJson= budgetService.fetchBudgetAllotmentListAllAccHead(pageable, accountHeadId, loggedInUserRole, user.getUsername());
				
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(budgetRequestJson);

		return json;
	}
	

	@RequestMapping(value = "/editBudgetRequestApproval/{id}", method = RequestMethod.GET)
	public ModelAndView editBudgetRequest(@PathVariable Long id , HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting Edit Budget Request ");

		ModelAndView modelAndView = new ModelAndView("enc/editBudgetApprovalRequest");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		return modelAndView;
	}

	@RequestMapping(value = "/budgetRequestAllotmentList", method = RequestMethod.GET)
	public ModelAndView budgetRequestAllotmentList(HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting budget RequestAllotment List ");

		ModelAndView modelAndView = new ModelAndView("enc/budgetRequestAllotmentList");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/budgetRequestAllotmentListAccHeadWise", method = RequestMethod.GET)
	public ModelAndView budgetRequestAllotmentListAccHeadWise(HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting budget RequestAllotment List Account Head Wise ");

		ModelAndView modelAndView = new ModelAndView("enc/budgetRequestAllotmentListAccHeadWise");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/getBudgetAllotmentList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBudgetRequestList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Budget Request List");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		String accountHeadId= request.getParameter("accountHeadId");

		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
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

		String loggedInUserRole = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BudgetAllotmentJson budgetRequestJson = budgetService.fetchBudgetAllotmentList(pageable, loggedInUserRole, user.getUsername(),accountHeadId);
				
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(budgetRequestJson);

		return json;
	}
	
	//getBudgetAllotmentListAccHeadWise
	
	@RequestMapping(value = "/getBudgetAllotmentListAccHeadWise", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchBudgetRequestListAccHeadWise(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Budget Request List Account Head Wise");

		String sSortCol = request.getParameter("iSortCol_0");
		String sSortDir = request.getParameter("sSortDir_0");
		String sColName = request.getParameter("mDataProp_" + sSortCol);
		String accountHeadId= request.getParameter("accountHeadId");

		Integer pageNumber = 0;

		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength);
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

		String loggedInUserRole = (String) httpSession
				.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		BudgetAllotmentJson budgetRequestJson = budgetService.fetchBudgetAllotmentListAccHeadWise(pageable, loggedInUserRole, user.getUsername(),accountHeadId);
				
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(budgetRequestJson);

		return json;
	}
	
	
	@RequestMapping(value = "/addnewAllotment", method = RequestMethod.GET)
	public ModelAndView addnewAllotment(HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting Budget Request List  ");

		ModelAndView modelAndView = new ModelAndView("enc/addnewAllotment");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		modelAndView.addObject("workStatus", "total");
		modelAndView.addObject("isLegacy", -1);
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/editAllotment/{id}", method = RequestMethod.GET)
	public ModelAndView editAllotment(@PathVariable Long id , HttpServletRequest request) {
		
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - getting Edit Allotment ");

		ModelAndView modelAndView = new ModelAndView("enc/editAllotment");
		
		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);
		
		return modelAndView;
	}
	

	@RequestMapping(value = "/saveBudgetAllotment", method = RequestMethod.POST)
	public ResponseObject saveAllotment(@RequestBody BudgetAllotmentBean budgetAllotmentBean,
			HttpServletRequest request) throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - saveBudgetAllotment");
		ResponseObject response = new ResponseObject();

		String errorMsg = budgetService.saveBudgetAllotment(budgetAllotmentBean);
		
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Budget Allotted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " -  Budget Allotted successfully!");
		}
		return response;
	}


	@RequestMapping(value = "/fetchBudgetRequest/{budgetRequestId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public BudgetRequestBean fetchBudgetRequest(@PathVariable Long budgetRequestId,   HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchBudgetRequest is called");
		BudgetRequestBean budgetRequestBean  = 	budgetService.fetchBudgetRequest(budgetRequestId);
		return budgetRequestBean;
	}
	
	@RequestMapping(value = "/fetchBudgetAllotmentEEOffice/{budgetRequestId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<BudgetAllotmentEEOfficeBean> fetchBudgetAllotmentEEOffice(@PathVariable Long budgetRequestId,   HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchBudgetAllotmentEEOffice is called");
		
		List<BudgetAllotmentEEOfficeBean> list = budgetService.fetchBudgetAllotmentEEOffice(budgetRequestId);
		return list ;
	}
	
	@RequestMapping(value = "/fetchBudgetAllotment/{budgetAllotmentId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public BudgetAllotmentBean fetchBudgetAllotment(@PathVariable Long budgetAllotmentId,   HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - fetchBudgetAllotment is called");
		BudgetAllotmentBean budgetAllotmentBean  = 	budgetService.fetchBudgetAllotment(budgetAllotmentId);
		return budgetAllotmentBean;
	}
	

}
