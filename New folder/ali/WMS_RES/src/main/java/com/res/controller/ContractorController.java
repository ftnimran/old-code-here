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
import com.res.json.WorkJson;
import com.res.repository.UserRepository;
import com.res.response.ResponseObject;
import com.res.service.AdminService;
import com.res.service.BudgetService;
import com.res.service.CommonService;
import com.res.service.UserService;
import com.res.util.RESUtil;

@RestController
@RequestMapping("/contractor/*")
public class ContractorController extends BaseController{
	
	public static final Logger logger = LoggerFactory.getLogger(ContractorController.class);
	
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
		ModelAndView modelAndView = new ModelAndView("contractor/contractorHome");
		
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
		ModelAndView modelAndView = new ModelAndView("contractor/dashboard");
		return modelAndView;

	}
	
	@RequestMapping(value = "/searchWorkForBillContractor", method = RequestMethod.GET)
	public ModelAndView searchWorkForBill(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Search Work For Bill page");
		ModelAndView modelAndView = new ModelAndView("contractor/searchWorkForBillContractor");
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchWorksByNameContractor", method = RequestMethod.GET)
	public String fetchWorksByName(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - "
				+ user.getAuthorities() + " - Fetching Work List By Contractor");
		
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
	
	@RequestMapping(value = "/viewBillsContractor", method = RequestMethod.GET)
	public ModelAndView viewBills(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying View Bills page");

		HttpSession httpSession = request.getSession(false);
		String loggedInUserRole = (String) httpSession.getAttribute(RESConstants.LOGGED_IN_USER_ROLE);

		ModelAndView modelAndView = null;

		if (loggedInUserRole.equals(RESConstants.ROLE_CONTRACTOR)) {
			modelAndView = new ModelAndView("contractor/viewBillsContractor");
		}

	

		return modelAndView;
	}
	
	
	

}
