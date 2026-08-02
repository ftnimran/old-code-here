package com.res.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.res.bean.ChapterBean;
import com.res.bean.ItemBean;
import com.res.bean.SORBean;
import com.res.bean.StatusBean;
import com.res.bean.UnitBean;
import com.res.bean.UserBean;
import com.res.bean.YearBean;
import com.res.constants.RESConstants;
import com.res.json.ChapterJson;
import com.res.json.ItemJson;
import com.res.json.SORJson;
import com.res.response.ResponseObject;
import com.res.service.AdminService;
import com.res.service.SORService;
import com.res.service.UserService;
import com.res.util.RESUtil;

@RestController
@RequestMapping(value = { "/admin/*", "/adminView/*" })
@Scope("session")
public class AdminController extends BaseController {
	
	public static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private User user;
	
	
	@Autowired
	private SORService sorService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
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
		UserBean userDetail =  userService.fetchUserDetailsByUserName(user.getUsername());
		String loggedInUserRole = userDetail.getLoggedInUserRole();
		ModelAndView modelAndView=null;
		
		if(loggedInUserRole.equals("ROLE_ADMIN_VIEW")) {
		 modelAndView = new ModelAndView("admin/adminViewHome");
		}else {
			modelAndView = new ModelAndView("admin/adminHome");
		}
		
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
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		return modelAndView;

	}
		
	@RequestMapping(value = "/editLegacyDataMapping/{id}", method = RequestMethod.GET)
	public ModelAndView viewEditUserForm(
			@PathVariable String id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Legacy Form");
		ModelAndView modelAndView = new ModelAndView("admin/editLegacyDataForm");
		return modelAndView;
	}
	/*BOF: SOR listing Implementation */
	@RequestMapping(value = "/manageSORs", method = RequestMethod.GET)
	public ModelAndView sorConfiguration(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Manage SOR page");
		ModelAndView modelAndView = new ModelAndView("admin/manageSORs");
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchSORList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchSORList(HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching SOR List");

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
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);
		String searchBoxVal = request.getParameter("searchBoxVal");
		String year = request.getParameter("year");
		SORJson sorJson = sorService.getAllSORs(pageable, searchBoxVal, year);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(sorJson);

		return json;
	}
	
	@RequestMapping(value = "/addSORForm", method = RequestMethod.GET)
	public ModelAndView addSORForm() {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add New SOR Form");

		return new ModelAndView("admin/addSORForm");

	}
	
	@RequestMapping(value = "/addSOR", method = RequestMethod.POST)
	public ResponseObject addSOR(@RequestBody SORBean sorBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Calling addSOR");
		ResponseObject response = new ResponseObject();

		String errorMsg = sorService.addSOR(sorBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Schedule of rate added successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Schedule of rat added successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/editSORForm/{id}", method = RequestMethod.GET)
	public ModelAndView viewEditSORForm(@PathVariable String id, HttpServletRequest request) {
		 request.getSession().setAttribute("sorId",id);

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Schedule of rate Form");
		ModelAndView modelAndView = new ModelAndView("admin/editSORForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "fetchSORById/{id}", method = RequestMethod.GET)
	public SORBean fetchSORBean(@PathVariable Long id, HttpServletRequest request) {
		   request.getSession().setAttribute("sorId",id);
		return sorService.fetchSORBean(id);
	}
	
	
	
	
	@RequestMapping(value = "/editSOR", method = RequestMethod.POST)
	public ResponseObject editSOR(@RequestBody SORBean sorBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Calling editSOR");
		ResponseObject response = new ResponseObject();

		String errorMsg = sorService.updateSOR(sorBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("SOR Edited successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - SOR Edited successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "fetchYears", method = RequestMethod.GET)
	public List<YearBean> fetchYears(HttpServletRequest request) {
		
		return sorService.fetchYears();
	}
	
	@RequestMapping(value = "fetchStatus", method = RequestMethod.GET)
	public List<StatusBean> fetchStatus(HttpServletRequest request) {
		
		return sorService.fetchStatus();
	}
	/*EOF: SOR Implementation */
	
	/*BOF: Chapter Implementation */
	@RequestMapping(value = "/fetchThisSORChapterList/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchThisSORChapterList(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Chapter List");

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
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);
		ChapterJson chapterJson = sorService.getThisSORChapters(pageable, id);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(chapterJson);

		return json;
	}
	
	@RequestMapping(value = "/addChapterForm/{id}", method = RequestMethod.GET)
	public ModelAndView addChapterForm(@PathVariable Long id, HttpServletRequest request) {
		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
				+ " - Displaying Add New Chapter Form");
		
		return new ModelAndView("admin/addChapterForm");

	}
	
	@RequestMapping(value = "/addChapter", method = RequestMethod.POST)
	public ResponseObject addChapter(@RequestBody ChapterBean chapterBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Calling addChapter");
		ResponseObject response = new ResponseObject();
		
		Long sorid=(Long) request.getSession().getAttribute("sorId");

		chapterBean.setSorBean(new SORBean(sorid));
		
		String errorMsg = sorService.addChapter(chapterBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Chapter added successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Chapter added successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/editChapterForm/{id}/{chapter}", method = RequestMethod.GET)
	public ModelAndView viewEditChapterForm(@PathVariable String id, @PathVariable String chapter, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info(
				"User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Displaying Edit Chapter Form");
		ModelAndView modelAndView = new ModelAndView("admin/editChapterForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchChapterById/{id}/{chapter}", method = RequestMethod.GET)
	public ChapterBean fetchChapter(@PathVariable Long id, @PathVariable String chapter, HttpServletRequest request) {
		   request.getSession().setAttribute("sorId",id);
		return sorService.fetchChapterRBean(Long.parseLong(chapter));
	}
	
	@RequestMapping(value = "/editChapter", method = RequestMethod.POST)
	public ResponseObject updateChapter(@RequestBody ChapterBean chapterBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Calling updateChapter");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = sorService.updateChapter(chapterBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Chapter updated successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Chapter updated successfully!");
		}
		return response;
	}
	
	/*EOF: Chapter Implementation editChapter */
	/*BOF: Item Implementation */
	@RequestMapping(value = "/fetchThisChapterItemList/{id}/{chapter}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String fetchThisChapterItemList(@PathVariable Long id, @PathVariable Long chapter, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching Chapter List");

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
			sort = new Sort(new Sort.Order(Direction.DESC, "id"));// default
																	// sorting
		}

		Pageable pageable = new PageRequest(pageNumber, pageDisplayLength, sort);
		ItemJson itemJson = sorService.getThisChapterItem(pageable, chapter);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(itemJson);

		return json;
	}
	
	@RequestMapping(value = "fetchUnit", method = RequestMethod.GET)
	public List<UnitBean> fetchUnit(HttpServletRequest request) {
		
		return sorService.fetchUnit();
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ResponseObject addItem(@RequestBody ItemBean itemBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Calling addItem");
		request.getParameter("chapterBean.id");
		request.getParameter("chapterId");
		ResponseObject response = new ResponseObject();   
		String errorMsg = sorService.addItem(itemBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Item added successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Item added successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/fetchItemById/{id}", method = RequestMethod.GET)
	public ItemBean fetchItemById(@PathVariable Long id, HttpServletRequest request) {
		return sorService.fetchItemBean(id);
	}
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	public ResponseObject updateItem(@RequestBody ItemBean itemBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Calling updateItem");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = sorService.updateItem(itemBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Item updated successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Item updated successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
	public ResponseObject deleteItem(@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Deleting Item");
		ResponseObject response = new ResponseObject();

		String errorMsg = sorService.deleteItem(id, user.getUsername());

		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Item deleted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Item deleted successfully!");
		}
		return response;
	}
	
	
	@RequestMapping(value = "/fetchSubItemById/{id}", method = RequestMethod.GET)
	public ItemBean fetchSubItemById(@PathVariable Long id, HttpServletRequest request) {
		return sorService.fetchSubItemBean(id);
	}
	
	@RequestMapping(value = "/addSubItem", method = RequestMethod.POST)
	public ResponseObject addSubItem(@RequestBody ItemBean itemBean, HttpServletRequest request)
			throws Exception {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Calling addItem");
		request.getParameter("chapterBean.id");
		request.getParameter("chapterId");
		ResponseObject response = new ResponseObject();   
		String errorMsg = sorService.addSubItem(itemBean);
		if (errorMsg != null) {
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		} else {
			response.setSuccessMessage("Item added successfully !");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Item added successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "fetchUnitById/{id}", method = RequestMethod.GET)
	public UnitBean fetchUnitById(@PathVariable Long id, HttpServletRequest request) {
		
		return sorService.fetchUnitById(id);
	}
	
	@RequestMapping(value = "/deleteChapter/{id}", method = RequestMethod.GET)
	public ResponseObject deleteChapter(
			@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Deleting Chapter");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = sorService.deleteChapter(id, user.getUsername());

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Chapter deleted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Chapter deleted successfully!");
		}
		return response;
	}
	
	@RequestMapping(value = "isSorItemChildExists/{id}", method = RequestMethod.GET)
	public boolean isSorItemChildExists(HttpServletRequest request, @PathVariable Long id) {
		
		return sorService.isSorItemChildExists(id);
	}
	
	@RequestMapping(value = "isSorChapterItemExists/{id}", method = RequestMethod.GET)
	public boolean isSorChapterItemExists(HttpServletRequest request, @PathVariable Long id) {
		
		return sorService.isSorChapterItemExists(id);
	}
	
	@RequestMapping(value = "isSorChapterExists/{id}", method = RequestMethod.GET)
	public boolean isSorChapterExists(HttpServletRequest request, @PathVariable Long id) {
		
		return sorService.isSorChapterExists(id);
	}
	
	@RequestMapping(value = "/deleteContractor/{id}", method = RequestMethod.GET)
	public ResponseObject deleteContractor(
			@PathVariable Long id, HttpServletRequest request) {

		user = RESUtil.getUserDetail();
		logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Deleting Contractor");
		ResponseObject response = new ResponseObject();
		
		String errorMsg = adminService.deleteContractor(id, user.getUsername());

		if(errorMsg!=null){
			response.setErrorMessage(errorMsg);
			logger.error("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - " + errorMsg);
		}else{
			response.setSuccessMessage("Contractor deleted successfully!");
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Contractor deleted successfully!");
		}
		return response;
	}
	
	
	
	/*EOF: Item Implementation  */
}
