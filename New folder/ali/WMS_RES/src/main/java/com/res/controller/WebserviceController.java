package com.res.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import com.res.bean.BillBean;
import com.res.bean.BillDataInspectionBean;
import com.res.bean.BillItemBean;
import com.res.bean.ChangePasswordBean;
import com.res.bean.InspectionAnswerBean;
import com.res.bean.InspectionChecklistBean;
import com.res.bean.InspectionCompletedWorkBean;
import com.res.bean.InspectionCompletedWorkBeanNew;
import com.res.bean.InspectionRequestBean;
import com.res.bean.UserBean;
import com.res.bean.UserProfileBean;
import com.res.bean.WorkBean;
import com.res.bean.WorkBean2;
import com.res.bean.WorkDataInspectionBean;
import com.res.bean.WorkFinancialMileStoneBean;
import com.res.bean.WorkPhysicalMileStoneBean;
import com.res.bean.WorkTypeBean;
import com.res.constants.RESConstants;
import com.res.entity.DataJson;
import com.res.entity.DocumentUpload;
import com.res.entity.FileJson;
import com.res.entity.ImageJson;
import com.res.entity.InspectionDetails;
import com.res.entity.InspectionSqmAnswer;
import com.res.entity.RandomDataJson;
import com.res.entity.SqmAllocation;
import com.res.entity.Users;
import com.res.entity.bhavan.Data;
import com.res.entity.bhavan.Group1;
import com.res.entity.bhavan.Group10;
import com.res.entity.bhavan.Group2;
import com.res.entity.bhavan.Group3;
import com.res.entity.bhavan.Group4;
import com.res.entity.bhavan.Group5;
import com.res.entity.bhavan.Group6;
import com.res.entity.bhavan.Group7;
import com.res.entity.bhavan.Group8;
import com.res.entity.bhavan.Group9;
import com.res.entity.bhavan.Meta;
import com.res.entity.singlevillagepipped.Bill;
import com.res.entity.singlevillagepipped.BillItems;
import com.res.entity.singlevillagepipped.OverallGradingAndRemarks;
import com.res.entity.singlevillagepipped.WorkDetails;
import com.res.entity.sqm.OverallObservation;
import com.res.exception.RESBusinessException;
import com.res.repository.DocumentRepository;
import com.res.repository.InspectionDetailsRepository;
import com.res.repository.InspectionSqmAnswerRepository;
import com.res.repository.SqmAllocationRepository;
import com.res.repository.UserRepository;
import com.res.response.ResponseObject;
import com.res.service.CommonService;
import com.res.service.InspectionService;
import com.res.service.UserService;
import com.res.util.RESUtil;

@RestController
@RequestMapping(value = { "/ws/*", "/ae/*" })
public class WebserviceController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	// documentRepository
	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private InspectionService inspectionService;

	@Autowired
	private SqmAllocationRepository sqmAllocationRepository;

	@Autowired
	private InspectionDetailsRepository inspectionDetailsRepository;
	
	@Autowired
	private SqmAllocationRepository allocationRepository;
	
	@Autowired
	private InspectionDetailsRepository inspectionDetailsRepo;

	User user;

	public static final Logger logger = LoggerFactory.getLogger(WebserviceController.class);

	@RequestMapping(value = "/changePasswordMobileApp", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseObject changePasswordMobileApp(@RequestBody ChangePasswordBean changePasswordBean)
			throws IOException, RESBusinessException {

		ResponseObject response = new ResponseObject();

		Users userEntity = userService.findByUserName(changePasswordBean.getUserName());

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (!StringUtils.isEmpty(changePasswordBean.getCurrentPassword())
				&& !passwordEncoder.matches(changePasswordBean.getCurrentPassword(), userEntity.getPassword())) {

			response.setErrorMessage("Current password is not valid.");

			return response;
		}

		if ((!StringUtils.isEmpty(changePasswordBean.getPassword())
				&& !StringUtils.isEmpty(changePasswordBean.getConfirmPassword()))
				&& (!changePasswordBean.getPassword().equals(changePasswordBean.getConfirmPassword()))) {

			response.setErrorMessage("New password and confirm password not matched.");
			return response;
		}

		changePasswordBean.setPassword(passwordEncoder.encode(changePasswordBean.getPassword()));
		userService.changePassword(changePasswordBean, changePasswordBean.getUserName());
		response.setSuccessMessage("You have successfully changed the password.");
		return response;

	}

	@RequestMapping(value = "/resetPasswordMobileApp", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseObject resetPasswordMobileApp(@RequestBody ChangePasswordBean changePasswordBean)
			throws IOException, RESBusinessException {

		ResponseObject response = new ResponseObject();

		if (!StringUtils.isEmpty(changePasswordBean.getUserName())) {

			Users user = userService.findByEmailId(changePasswordBean.getUserName());

			if (user == null) {
				response.setErrorMessage("Invalid User Id.");
				return response;
			}
		}

		userService.resetPassword(changePasswordBean.getUserName());
		response.setSuccessMessage("Password reset successfull. New Password sent on registered Email Id & Mobile No.");
		return response;

	}

	@RequestMapping(value = "/fetchAllWorks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<WorkBean2> fetchAllWorks(HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Fetching All works data");
		}

		List<WorkBean2> workBeanList = commonService.fetchAllWorks();

		return workBeanList;
	}

	// for Running Inspection
	@RequestMapping(value = "/fetchRunningInspectionPendingBills/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BillDataInspectionBean>> fetchInspectionPendingBills(@PathVariable Long userId,
			HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		}
		Users u = userRepository.findByIdAndStatusNot(userId, RESConstants.STATUS_DELETED);
		if (u == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Long pathUser = userId == null ? null : userId;
		Long dbUser = u.getId() == null ? null : u.getId();
		if (!Objects.equals(pathUser, dbUser)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		List<BillDataInspectionBean> billBeanList = commonService.fetchInspectionRunningBillsForRES(u.getUsername(),
				RESConstants.STATUS_FWD_FOR_INSPECTION_ID);

		return ResponseEntity.ok(billBeanList);
	}

	// for final Inspection
	@RequestMapping(value = "/fetchFinalInspectionPendingBills/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BillDataInspectionBean>> fetchFinalInspectionPendingBills(@PathVariable Long userId,
			HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		}
		Users u = userRepository.findByIdAndStatusNot(userId, RESConstants.STATUS_DELETED);
		if (u == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Long pathUser = userId == null ? null : userId;
		Long dbUser = u.getId() == null ? null : u.getId();
		if (!Objects.equals(pathUser, dbUser)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		List<BillDataInspectionBean> billBeanList = commonService.fetchInspectionFinalBillsForRES(u.getUsername(),
				RESConstants.STATUS_FWD_FOR_INSPECTION_ID);

		return ResponseEntity.ok(billBeanList);
	}

	/// CC Inspection
	@RequestMapping(value = "/fetchCCInspectionPendingWorks/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BillDataInspectionBean>> fetchCCInspectionPendingWorks(@PathVariable Long userId,
			HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Fetching  CCInspectionPendingWorks");
		}

		Users u = userRepository.findByIdAndStatusNot(userId, RESConstants.STATUS_DELETED);
		UserBean userRole = userService.fetchUserDetailsByUserName(u.getUsername());

		if (u == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Long pathUser = userId == null ? null : userId;
		Long dbUser = u.getId() == null ? null : u.getId();
		if (!Objects.equals(pathUser, dbUser)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		List<BillDataInspectionBean> billBeanList = commonService.fetchCCInspectionPendingWorks(u.getUsername(),
				RESConstants.REQUEST_STATUS_Initiated_CC_Fwd_for_Final_Inspection_ID, userRole.getLoggedInUserRole());
		return ResponseEntity.ok(billBeanList);
	}

	@RequestMapping(value = "/fetchCCInspectionCompletedWorks/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BillDataInspectionBean>> fetchCCInspectionCompletedWorks(@PathVariable Long userId,
			HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Fetching  CCInspectionCompletedWorks");
		}

		Users u = userRepository.findByIdAndStatusNot(userId, RESConstants.STATUS_DELETED);
		if (u == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Long pathUser = userId == null ? null : userId;
		Long dbUser = u.getId() == null ? null : u.getId();
		if (!Objects.equals(pathUser, dbUser)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		List<BillDataInspectionBean> billBeanList = commonService.fetchCCInspectionCompletedWorks(u.getUsername(),
				RESConstants.REQUEST_STATUS_Final_Inspecion_Completed_ID);

		return ResponseEntity.ok(billBeanList);
	}

	@RequestMapping(value = "/fetchRandomInspection/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BillDataInspectionBean>> fetchRandomInspection(@PathVariable Long userId,
			HttpServletRequest request) {

		/*
		 * User loggedInUser = RESUtil.getUserDetail();
		 * 
		 * if (loggedInUser == null) { return
		 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }
		 */
		/*
		 * logger.info("User - {}, Role - {} - Fetching User data",
		 * loggedInUser.getUsername(), loggedInUser.getAuthorities());
		 */

		Users u = userRepository.findByIdAndStatusNot(userId, RESConstants.STATUS_DELETED);
		// compare path userId with logged-in username

		if (u == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Long pathUser = userId == null ? null : userId;
		Long dbUser = u.getId() == null ? null : u.getId();
		if (!Objects.equals(pathUser, dbUser)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		List<BillDataInspectionBean> billBeanList = commonService.fetchRandomInspectionPendingWorks(userId);

		/*
		 * List<BillDataInspectionBean> billBeanList =
		 * commonService.fetchRandomInspection(u.getId());
		 */

		return ResponseEntity.ok(billBeanList);
	}

	// for General inspection
	@RequestMapping(value = "/fetchGeneralInspection/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BillDataInspectionBean>> fetchGeneralInspection(@PathVariable Long userId,
			HttpServletRequest request) {

		/*
		 * User loggedInUser = RESUtil.getUserDetail();
		 * 
		 * if (loggedInUser == null) { return
		 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }
		 */
		/*
		 * logger.info("User - {}, Role - {} - Fetching User data",
		 * loggedInUser.getUsername(), loggedInUser.getAuthorities());
		 */

		Users u = userRepository.findByIdAndStatusNot(userId, RESConstants.STATUS_DELETED);
		// compare path userId with logged-in username

		if (u == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Long pathUser = userId == null ? null : userId;
		Long dbUser = u.getId() == null ? null : u.getId();
		if (!Objects.equals(pathUser, dbUser)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		List<BillDataInspectionBean> billBeanList = commonService.fetchGeneralInspection(u.getId());

		return ResponseEntity.ok(billBeanList);
	}

	// Jaydevi
	@RequestMapping(value = "/fetchAlreadyDoneRandomInspectionList/{userId}/{workId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<InspectionAnswerBean> fetchAlreadyDoneRandomInspectionList(@PathVariable String userId,
			@PathVariable String workId, HttpServletRequest request) {

		List<InspectionAnswerBean> inspectionAnswerBeanList = commonService
				.fetchAlreadyDoneRandomInspectionList(Long.parseLong(userId), Long.parseLong(workId));

		return inspectionAnswerBeanList;
	}

	@RequestMapping(value = "/submitRandomInspectionAsCompleteAnswer", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, String> submitRandomInspectionAsCompleteAnswer(
			@RequestBody RandomDataJson dataJson, HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		map = commonService.saveRandomInspectionAnwser(dataJson);

		return map;

	}

	// billing inspection Question Answers API By Nikhil
	@RequestMapping(value = "/fetchUploadedInspectionAnswers/{billId}/{inspectedBy}/{isWorkId}/{inspectionAnswerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody InspectionCompletedWorkBean fetchInspectionPendingBills(@PathVariable Long billId,
			@PathVariable Long inspectedBy, @PathVariable(value = "isWorkId", required = false) boolean isWorkId,
			@PathVariable(value = "inspectionAnswerId", required = false) Long inspectionAnswerId,
			HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Fetching Inspection Data data");
		}

		InspectionCompletedWorkBean billBeanList = commonService.fetchInspectionCompletedBills(billId, inspectedBy,
				isWorkId, inspectionAnswerId);

		return billBeanList;
	}

	// cc inspection Question Answers API By Nikhil
	@RequestMapping(value = "/fetchUploadedInspectionAnswersCC/{inspectedBy}/{workId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody InspectionCompletedWorkBean fetchInspectionPendingWorksCC(@PathVariable String inspectedBy,
			@PathVariable Long workId, HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Fetching Inspection Data data");
		}

		InspectionCompletedWorkBean billBeanList = commonService.fetchInspectionCompletedWorksCC(workId, inspectedBy);

		return billBeanList;
	}

	@RequestMapping(value = "/downloadDocumentImage/{imageName}", method = RequestMethod.GET)
	public void downloadDocument(@PathVariable String imageName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DocumentUpload doc = documentRepository.findByDocumentName(imageName + ".jpg");

		String fileName = commonService.fetchDownloadFileName(doc.getDocumentId());
		InputStream is = null;
		OutputStream os = null;
		try {
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
		} finally {
			if (null != os) {
				os.flush();
				os.close();
			}
			if (null != is)
				is.close();
		}

	}

	@RequestMapping(value = "/downloadDocumentImages/{imageName}", method = RequestMethod.GET)
	public void downloadDocuments(@PathVariable String imageName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DocumentUpload doc = documentRepository.findByDocumentName(imageName + ".jpg");

		String fileName = commonService.fetchDownloadFileName(doc.getDocumentId());

		FileInputStream fin = null;
		try {
			if (fileName != null) {
				File file = new File(fileName);
				fin = new FileInputStream(file);
				PrintWriter p = response.getWriter();
				response.setContentType("image/jpeg");
				int i = 0;
				while (i != -1) {
					i = fin.read();
					p.write(i);
				}
			}
		} finally {
			if (fin != null)
				fin.close();
		}

	}

	// completed inspection API
	@RequestMapping(value = "/fetchInspectionCompletedRESBills/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BillDataInspectionBean> fetchInspectionCompletedRESBills(@PathVariable String userId,
			HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		}

		List<BillDataInspectionBean> billBeanList = commonService.fetchInspectionCompletedBillsForRES(userId,
				RESConstants.STATUS_PHYSICAL_INSPECTION_COMPLETED_ID);

		return billBeanList;
	}

	@RequestMapping(value = "/fetchInspectionPendingBillsForGP/{workTypeId}/{userId:.+}", method = RequestMethod.GET)
	public @ResponseBody List<BillDataInspectionBean> fetchInspectionPendingBillsForGP(@PathVariable Long workTypeId,
			@PathVariable String userId, HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		}

		Long agencyType = 2L;

		List<BillDataInspectionBean> billBeanList = commonService.fetchInspectionPendingBillsForGP(agencyType,
				workTypeId, userId);

		return billBeanList;
	}

	@RequestMapping(value = "/fetchInspectionCompletedGPBills/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BillDataInspectionBean> fetchInspectionCompletedGPBills(@PathVariable String userId,
			HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities() + " - Fetching User data");
		}

		Long agencyType = 2L;

		List<BillDataInspectionBean> billBeanList = commonService.fetchInspectionCompletedBillsForGP(agencyType,
				userId);

		return billBeanList;
	}

	@RequestMapping(value = "/getWorkTypeList", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<WorkTypeBean> fetchWorkType() {

		List<WorkTypeBean> list = commonService.fetchWorkType();

		return list;
	}

	// In Use
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> imageUpload(@RequestBody ImageJson imageJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();
		try {
			commonService.saveImage(imageJson);

		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Image in Inspection Data.." + e.getStackTrace());
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Image saved successfully !");

		logger.info("Image Data saved..");

		return map;
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> fileUpload(@RequestBody FileJson fileJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();
		try {
			commonService.saveFile(fileJson);

		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving File in Inspection Data.." + e.getStackTrace());
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "File uploaded successfully !");

		logger.info("File saved..");

		return map;
	}

	@RequestMapping(value = "/userprofile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewUserProfileForm(@RequestParam String username, HttpServletRequest request) {

		UserBean user = userService.fetchUserDetailsByUserName(username);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}

		logger.info("User - " + user.getUsername() + " - Displaying viewUserProfileForm page");

		// Create a safe response DTO (so password is not returned)
		UserProfileBean resp = new UserProfileBean();
		resp.setUserId(user.getId());
		resp.setName(user.getName());
		resp.setUsername(user.getUsername());
		resp.setEmailId(user.getEmailId());
		if (user.getOfficeBean() != null && user.getOfficeBean().getOfficeName() != null)
			resp.setOfficeName(user.getOfficeBean().getOfficeName());
		resp.setLoggedInUserRole(user.getLoggedInUserRole());
		resp.setMobileNo(user.getMobileNo());
		resp.setDesignationName(user.getDesignationBean().getDesignation());

		return ResponseEntity.ok(resp);
	}

	@RequestMapping(value = "fetchWorkDetailsByBillIdForInspection/{id}/{userId}", method = RequestMethod.GET)
	public WorkBean2 fetchWorkDetailsByBillIdForInspection(@PathVariable Long id, @PathVariable Long userId,
			HttpServletRequest request) {

		user = RESUtil.getUserDetail();

		Users u = userRepository.findByIdAndStatusNot(userId, RESConstants.STATUS_DELETED);

		UserBean userDeatils = userService.fetchUserDetailsByUserName(u.getUsername());
		if (user != null) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Fetching Work data by Bill Id");
		}
		// WorkBean workbean = commonService.fetchWorkDetailsByBillId(id);
		// workBean2.setUserBean(userService.fetchUserDetailsByUserName(userDeatils.getUsername()));
		WorkBean2 workBean2 = commonService.fetchWorkDetailsByBillIdMobile(id);
		workBean2.setLoggedInUserName(userDeatils.getUsername());
		return workBean2;
	}

	@RequestMapping(value = "/saveChecklist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> saveChecklist(@RequestBody Map<String, Object> req) {

		Map<String, Object> map = new HashMap<>();

		try {
			Long workTypeId = Long.valueOf(req.get("workTypeId").toString());

			DataJson dataJson = new DataJson();
			dataJson.setWorkId(req.get("workId").toString());
			dataJson.setUserId(Long.valueOf(req.get("userId").toString()));
			dataJson.setRole(req.get("role").toString());
			dataJson.setXml(req.get("xml").toString()); // XML required

			Map<String, String> response = null;

			switch (workTypeId.intValue()) {
			case 1:
				response = commonService.saveInspectionAnwserForBhavan(dataJson);
				break;
			case 2:
				response = commonService.saveInspectionAnwserForPulia(dataJson);
				break;
			case 3:
				response = commonService.saveInspectionAnwserForSadak(dataJson);
				break;
			case 4:
				response = commonService.saveInspectionAnwserForKhel(dataJson);
				break;
			case 5:
				response = commonService.saveInspectionAnwserForTalab(dataJson);
				break;
			default:
				map.put("code", "400");
				map.put("message", "Unknown workTypeId: " + workTypeId);
				return map;
			}

			map.put("code", "200");
			map.put("message", "Checklist Saved Successfully");
			map.put("details", response);

		} catch (Exception e) {
			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
		}

		return map;
	}

	@RequestMapping(value = "/checklist/{workTypeId}", method = RequestMethod.GET)
	public ResponseEntity<InspectionChecklistBean> getChecklist(@PathVariable Integer workTypeId) {

		return ResponseEntity.ok(inspectionService.getInspectionChecklist(workTypeId));
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public Map<String, Object> saveInspection(@RequestBody InspectionRequestBean request) {

		Map<String, Object> response = new HashMap<>();
		
		
		
		List<InspectionDetails> isa = null;
		InspectionDetails inspectionAlredyDone = null;
		if (request.getInspectionDetail().getSqmAllocationId() != null) {
			 isa = inspectionDetailsRepository.findBySqmAllocationId(request.getInspectionDetail().getSqmAllocationId());
			 SqmAllocation sqmAllocation = allocationRepository.findByIdAndInspectionDone(request.getInspectionDetail().getSqmAllocationId(),(short) 0);
			 if(sqmAllocation!=null)
			 inspectionAlredyDone = inspectionDetailsRepo.findByWorkIdAndSqmAllocationId(sqmAllocation.getWork().getId(), request.getInspectionDetail().getSqmAllocationId());
			}
		
		
		  if (null != isa && isa.size() > 0 && inspectionAlredyDone!=null) {
			  response.put("status", "FAILURE");
			  response.put("inspectionId", null);
		  
			  response.put("code", "201");
			  response.put("message", "FAILURE");
			  response.put("DetailMessage", "Inspection Already Done!");
		  
			  return  response;
		  
		  }
		 

		Long inspectionId = inspectionService.saveOrUpdateInspection(request);
		if (inspectionId != null && (request.getInspectionDetail().getSqmAllocationId() != null|| request.getInspectionDetail().getRandomAllocationId() != null)) {
			SqmAllocation sqmAllocation =null;
			if(request.getInspectionDetail().getSqmAllocationId()!=null)
			sqmAllocation = sqmAllocationRepository
					.findById(request.getInspectionDetail().getSqmAllocationId());
			else if(request.getInspectionDetail().getRandomAllocationId()!=null)
				sqmAllocation = sqmAllocationRepository
				.findById(request.getInspectionDetail().getRandomAllocationId());
			if(sqmAllocation!=null) {
			User user = RESUtil.getUserDetail();
			sqmAllocation.setInspectionDone((short) 1);
			sqmAllocation.setModifiedBy(sqmAllocation.getUsers().getUsername());
			sqmAllocation.setModifiedDate(new Date());
			sqmAllocationRepository.save(sqmAllocation);
			}

		} 

		if (inspectionId != null) {
			response.put("status", "SUCCESS");
			response.put("inspectionId", inspectionId);
		} else {
			response.put("status", "Error");
		}
		return response;
	}

	// Generic method for all Templates
	@RequestMapping(value = "/getTemplate/{fileName:.+}", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<InputStreamResource> getBhavanTemplate(@PathVariable String fileName) throws IOException {

		ClassPathResource pdfFile = new ClassPathResource("downloads/" + fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/xml"));
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Content-Disposition", "filename=" + fileName);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		headers.setContentLength(pdfFile.contentLength());
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
				new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);

		logger.info(fileName + " Sent..");
		return response;

	}

	/// Bhavan
	// CC Inspection

	@RequestMapping(value = "/getBhavanInstanceCC.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody Data getBhavanInstanceCC(@RequestParam Long id, @RequestParam Long userId,
			@RequestParam String roleId) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetails(id);

		Data data = getBhavanInstanceData(workBean, userId, roleId);

		return data;
	}

	// Inspection Bill
	@RequestMapping(value = "/getBhavanInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody Data getBhavanInstance(@RequestParam Long id, @RequestParam Long userId,
			@RequestParam String roleId, @RequestParam(value = "isWorkId", required = false) boolean isWorkId)
			throws Exception {

		System.err.println(userId);
		System.err.println(roleId);

		WorkBean workBean = isWorkId ? commonService.fetchWorkDetailsByWorkId(id)
				: commonService.fetchWorkDetailsByBillId(id);

		Data data = getBhavanInstanceData(workBean, userId, roleId);

		return data;
	}

	private Data getBhavanInstanceData(WorkBean workBean, Long userId, String roleId) {

		Group1 group1 = new Group1();
		group1.setGram(workBean.getVillageBean().getVillageName());
		group1.setGramPanchayat(workBean.getGramPanchayatName());
		group1.setVikaskhand(workBean.getBlockName());

		group1.setJila(workBean.getDistrictName());
		Users u = userRepository.findOne(userId);
		String name = u.getName();
		String post = u.getDesignation().getDesignation();
		String adhikariNameNPost = name + "-" + post;
		/*
		 * if(roleId.equals("ROLE_AE")) {
		 * 
		 * } if (workBean.getAssistantEngineer() != null) {
		 * group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); } else {
		 * group1.setAdhikariNameNPost(""); }
		 */
		group1.setAdhikariNameNPost(adhikariNameNPost);
		group1.setInspectionDate("");

		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());

		Group2 group2 = new Group2();
		group2.setKaryaName(workBean.getWorkName());
		group2.setYojnaName(workBean.getAccountHead());
		group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());
		group2.setVartmanstithi("");
		group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());

		Meta meta = new Meta();
		meta.setInstanceID("build_Bhavan_1542879302");

		Group3 group3 = new Group3("", "", "");
		Group4 group4 = new Group4("", "", "");
		Group5 group5 = new Group5("", "", "", "");
		Group6 group6 = new Group6("", "", "", "", "", "");
		Group7 group7 = new Group7("", "", "", "");
		Group8 group8 = new Group8("", "");
		Group9 group9 = new Group9("", "", "", "", "", "");
		OverallObservation overallObservation = new OverallObservation("", "");
		Group10 group10 = new Group10("", "", "", "", "", ""); // Image n File

		Data data = new Data(meta, group1, group2, group3, group4, group5, group6, group7, group8, group9,
				overallObservation, group10);

		return data;
	}

	@RequestMapping(value = "/saveBhavanAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveBhavanAnswers(@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		try {

			logger.info(dataJson + "");
			map = commonService.saveInspectionAnwserForBhavan(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			return map;
		}

		/*
		 * map.put("code", "200"); map.put("message", "SUCCESS");
		 * map.put("DetailMessage", "Bhavan Inspection Data saved");
		 */

		logger.info("Bhavan Inspection Data saved..");

		return map;

	}

	// Pulia

	// CC Inspection
	@RequestMapping(value = "/getPuliaInstanceCC.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.pulia.Data getPuliaInstanceCC(@RequestParam Long id, @RequestParam Long userId,
			@RequestParam String roleId) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetails(id);
		com.res.entity.pulia.Data data = getPuliaInstanceData(workBean, userId, roleId);

		return data;
	}

	// Inspectoin Bill
	@RequestMapping(value = "/getPuliaInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.pulia.Data getPuliaInstance(@RequestParam Long id, @RequestParam Long userId,
			@RequestParam String roleId, @RequestParam(value = "isWorkId", required = false) boolean isWorkId)
			throws Exception {

		WorkBean workBean = isWorkId ? commonService.fetchWorkDetailsByWorkId(id)
				: commonService.fetchWorkDetailsByBillId(id);

		com.res.entity.pulia.Data data = getPuliaInstanceData(workBean, userId, roleId);

		return data;
	}

	private com.res.entity.pulia.Data getPuliaInstanceData(WorkBean workBean, Long userId, String roleId) {
		com.res.entity.pulia.Group1 group1 = new com.res.entity.pulia.Group1();
		group1.setKaryaName(workBean.getWorkName());
		group1.setVartmanstithi("");
		group1.setSwikratVarsh(workBean.getAdministrationSanctionDate());
		group1.setYojnaName(workBean.getAccountHead());
		group1.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());
		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());

		com.res.entity.pulia.Group6 group6 = new com.res.entity.pulia.Group6();
		group6.setGram(workBean.getVillageBean().getVillageName());
		group6.setGramPanchayat(workBean.getGramPanchayatName());
		group6.setVikaskhand(workBean.getBlockName());

		group6.setJila(workBean.getDistrictName());
		/*
		 * if (workBean.getAssistantEngineer() != null) {
		 * group6.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); } else {
		 * group6.setAdhikariNameNPost(""); }
		 */

		Users u = userRepository.findOne(userId);
		String name = u.getName();
		String post = u.getDesignation().getDesignation();
		String adhikariNameNPost = name + "-" + post;
		/*
		 * if(roleId.equals("ROLE_AE")) {
		 * 
		 * } if (workBean.getAssistantEngineer() != null) {
		 * group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); } else {
		 * group1.setAdhikariNameNPost(""); }
		 */
		group6.setAdhikariNameNPost(adhikariNameNPost);

		group6.setInspectionDate("");

		com.res.entity.pulia.Meta meta = new com.res.entity.pulia.Meta();
		meta.setInstanceID("build_pulia_1539913442");

		com.res.entity.pulia.Group2 group2 = new com.res.entity.pulia.Group2("", "", "", "", "", "");

		com.res.entity.pulia.Group3 group3 = new com.res.entity.pulia.Group3("", "", "", "", "", "", "", "", "");

		com.res.entity.pulia.Group4 group4 = new com.res.entity.pulia.Group4("", "", "", "");

		com.res.entity.pulia.Group5 group5 = new com.res.entity.pulia.Group5("", "", "", "", "", "");
		OverallObservation overallObservation = new OverallObservation("", "");

		com.res.entity.pulia.Group7 group7 = new com.res.entity.pulia.Group7("", "", "", "", "", ""); // Image n File

		com.res.entity.pulia.Data data = new com.res.entity.pulia.Data(meta, group1, group2, group3, group4, group5,
				group6, overallObservation, group7);

		return data;
	}

	@RequestMapping(value = "/savePuliaAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> savePuliaAnswers(@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		try {
			map = commonService.saveInspectionAnwserForPulia(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());

			logger.error("Error in saving Pulia Inspection Data.." + e.getStackTrace());

			return map;
		}

		/*
		 * map.put("code", "200"); map.put("message", "SUCCESS");
		 * map.put("DetailMessage", "Pulia Inspection Data saved");
		 */

		logger.info("Pulia Inspection Data saved..");

		return map;

	}

	// CC Inspection

	@RequestMapping(value = "/getSadakInstanceCC.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.sadak.Data getSadakInstanceCC(@RequestParam Long id, @RequestParam Long userId,
			@RequestParam String roleId) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetails(id);

		com.res.entity.sadak.Data data = getSadakInstanceData(workBean, userId, roleId);

		return data;
	}

	// Inspection bill
	@RequestMapping(value = "/getSadakInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.sadak.Data getSadakInstance(@RequestParam Long id, @RequestParam Long userId,
			@RequestParam String roleId, @RequestParam(value = "isWorkId", required = false) boolean isWorkId)
			throws Exception {

		WorkBean workBean = isWorkId ? commonService.fetchWorkDetailsByWorkId(id)
				: commonService.fetchWorkDetailsByBillId(id);

		com.res.entity.sadak.Data data = getSadakInstanceData(workBean, userId, roleId);

		return data;
	}

	private com.res.entity.sadak.Data getSadakInstanceData(WorkBean workBean, Long userId, String roleId) {
		com.res.entity.sadak.Group1 group1 = new com.res.entity.sadak.Group1();
		group1.setGram(workBean.getVillageBean().getVillageName());
		group1.setGramPanchayat(workBean.getGramPanchayatName());
		group1.setVikaskhand(workBean.getBlockName());
		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());

		group1.setJila(workBean.getDistrictName());
		/*
		 * if (workBean.getAssistantEngineer() != null) {
		 * group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); } else {
		 * group1.setAdhikariNameNPost(""); }
		 */

		Users u = userRepository.findOne(userId);
		String name = u.getName();
		String post = u.getDesignation().getDesignation();
		String adhikariNameNPost = name + "-" + post;
		/*
		 * if(roleId.equals("ROLE_AE")) {
		 * 
		 * } if (workBean.getAssistantEngineer() != null) {
		 * group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); } else {
		 * group1.setAdhikariNameNPost(""); }
		 */
		group1.setAdhikariNameNPost(adhikariNameNPost);

		group1.setInspectionDate("");

		com.res.entity.sadak.Group2 group2 = new com.res.entity.sadak.Group2();
		group2.setKaryaName(workBean.getWorkName());
		group2.setVartmanstithi("");
		group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());
		group2.setYojnaName(workBean.getAccountHead());
		group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());

		com.res.entity.sadak.Meta meta = new com.res.entity.sadak.Meta();
		meta.setInstanceID("build_sadak_1529918112");

		com.res.entity.sadak.Group3 group3 = new com.res.entity.sadak.Group3("", "", "", "", "", "", "", "", "");

		com.res.entity.sadak.Group4 group4 = new com.res.entity.sadak.Group4("", "", "", "", "", "", "");

		com.res.entity.sadak.Group5 group5 = new com.res.entity.sadak.Group5("", "", "", "", "", "", "");

		com.res.entity.sadak.Group6 group6 = new com.res.entity.sadak.Group6("");

		com.res.entity.sadak.Group7 group7 = new com.res.entity.sadak.Group7("");

		com.res.entity.sadak.Group8 group8 = new com.res.entity.sadak.Group8("", "", "", "", "", "", "");

		OverallObservation overallObservation = new OverallObservation("", "");

		com.res.entity.sadak.Group9 group9 = new com.res.entity.sadak.Group9("", "", "", "", "", "");

		com.res.entity.sadak.Data data = new com.res.entity.sadak.Data(meta, group1, group2, group3, group4, group5,
				group6, group7, group8, overallObservation, group9);
		return data;

	}

	@RequestMapping(value = "/saveSadakAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveSadakAnswers(@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		try {
			map = commonService.saveInspectionAnwserForSadak(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			e.printStackTrace();
			logger.error("Error in saving Sadak Inspection Data.." + e.getStackTrace());
			return map;
		}

		/*
		 * map.put("code", "200"); map.put("message", "SUCCESS");
		 * map.put("DetailMessage", "Sadak Inspection Data saved");
		 */

		logger.info("Sadak Inspection Data saved..");

		return map;

	}

	@RequestMapping(value = "/getKhelInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.Khel.Data getKhelInstance(@RequestParam Long id, @RequestParam Long userId,
			@RequestParam String roleId, @RequestParam(value = "isWorkId", required = false) boolean isWorkId)
			throws Exception {

		WorkBean workBean = isWorkId ? commonService.fetchWorkDetailsByWorkId(id)
				: commonService.fetchWorkDetailsByBillId(id);

		com.res.entity.Khel.Data data = getKhelInstanceData(workBean, userId, roleId);

		return data;
	}

	private com.res.entity.Khel.Data getKhelInstanceData(WorkBean workBean, Long userId, String roleId) {
		com.res.entity.sadak.Group1 group1 = new com.res.entity.sadak.Group1();
		group1.setGram(workBean.getVillageBean().getVillageName());
		group1.setGramPanchayat(workBean.getGramPanchayatName());
		group1.setVikaskhand(workBean.getBlockName());
		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());

		group1.setJila(workBean.getDistrictName());
		/*
		 * if (workBean.getAssistantEngineer() != null) {
		 * group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); } else {
		 * group1.setAdhikariNameNPost(""); }
		 */

		Users u = userRepository.findOne(userId);
		String name = u.getName();
		String post = u.getDesignation().getDesignation();
		String adhikariNameNPost = name + "-" + post;
		/*
		 * if(roleId.equals("ROLE_AE")) {
		 * 
		 * } if (workBean.getAssistantEngineer() != null) {
		 * group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); } else {
		 * group1.setAdhikariNameNPost(""); }
		 */
		group1.setAdhikariNameNPost(adhikariNameNPost);

		group1.setInspectionDate("");
		/* group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); */

		com.res.entity.sadak.Group2 group2 = new com.res.entity.sadak.Group2();
		group2.setKaryaName(workBean.getWorkName());
		group2.setVartmanstithi("");
		group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());
		group2.setYojnaName(workBean.getAccountHead());
		group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());

		com.res.entity.Khel.Meta meta = new com.res.entity.Khel.Meta();
		meta.setInstanceID("build_KhelMaidan_1561110928");

		com.res.entity.Khel.InspectionDetails inspectionDetails = new com.res.entity.Khel.InspectionDetails("", "", "",
				"", "", "", "", "", "");
		com.res.entity.Khel.ImageAndFiles imageAndFiles = new com.res.entity.Khel.ImageAndFiles("", "", "", "", "", "");

		com.res.entity.Khel.Data data = new com.res.entity.Khel.Data(group1, group2, meta, inspectionDetails, "", "",
				"", imageAndFiles);
		return data;

	}

	@RequestMapping(value = "/saveKhelAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveKhelAnswers(@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		try {
			map = commonService.saveInspectionAnwserForKhel(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Khel Maidan Inspection Data.." + e.getStackTrace());
			return map;
		}

		/*
		 * map.put("code", "200"); map.put("message", "SUCCESS");
		 * map.put("DetailMessage", "Sadak Inspection Data saved");
		 */

		logger.info("Khel Maidan Inspection Data saved..");

		return map;

	}

	// Talab

	// CC Inspection
	@RequestMapping(value = "/getTalabInstanceCC.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.talab.Data getTalabInstanceCC(@RequestParam Long id, @RequestParam Long userId,
			@RequestParam String roleId) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetails(id);
		com.res.entity.talab.Data data = getTalabInstanceData(workBean, userId, roleId);

		return data;
	}

	// Inspection bill
	@RequestMapping(value = "/getTalabInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.talab.Data getTalabInstance(@RequestParam Long id, @RequestParam Long userId,
			@RequestParam String roleId, @RequestParam(value = "isWorkId", required = false) boolean isWorkId)
			throws Exception {

		WorkBean workBean = isWorkId ? commonService.fetchWorkDetailsByWorkId(id)
				: commonService.fetchWorkDetailsByBillId(id);

		com.res.entity.talab.Data data = getTalabInstanceData(workBean, userId, roleId);

		return data;
	}

	private com.res.entity.talab.Data getTalabInstanceData(WorkBean workBean, Long userId, String roleId) {

		com.res.entity.talab.Group1 group1 = new com.res.entity.talab.Group1();
		group1.setGram(workBean.getVillageBean().getVillageName());
		group1.setGramPanchayat(workBean.getGramPanchayatName());
		group1.setVikaskhand(workBean.getBlockName());

		/*
		 * group1.setJila(workBean.getDistrictName()); if
		 * (workBean.getAssistantEngineer() != null) {
		 * group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); } else {
		 * group1.setAdhikariNameNPost(""); }
		 */

		Users u = userRepository.findOne(userId);
		String name = u.getName();
		String post = u.getDesignation().getDesignation();
		String adhikariNameNPost = name + "-" + post;
		/*
		 * if(roleId.equals("ROLE_AE")) {
		 * 
		 * } if (workBean.getAssistantEngineer() != null) {
		 * group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost()); } else {
		 * group1.setAdhikariNameNPost(""); }
		 */
		group1.setAdhikariNameNPost(adhikariNameNPost);

		group1.setInspectionDate("");

		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());

		com.res.entity.talab.Group2 group2 = new com.res.entity.talab.Group2();
		group2.setKaryaName(workBean.getWorkName());

		group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());
		group2.setYojnaName(workBean.getAccountHead());
		group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());

		group2.setVartmanstithi("");

		com.res.entity.talab.Meta meta = new com.res.entity.talab.Meta();
		meta.setInstanceID("build_talab_1529923456");

		com.res.entity.talab.Group3 group3 = new com.res.entity.talab.Group3("", "", "", "");

		com.res.entity.talab.Group4 group4 = new com.res.entity.talab.Group4("", "", "", "", "", "");

		com.res.entity.talab.Group5 group5 = new com.res.entity.talab.Group5("", "", "", "", "", "");

		com.res.entity.talab.Group6 group6 = new com.res.entity.talab.Group6("", "", "", "", "", "");

		OverallObservation overallObservation = new OverallObservation("", "");

		com.res.entity.talab.Group7 group7 = new com.res.entity.talab.Group7("", "", "", "", "", "");

		com.res.entity.talab.Data data = new com.res.entity.talab.Data(meta, group1, group2, group3, group4, group5,
				group6, overallObservation, group7);

		return data;
	}

	@RequestMapping(value = "/saveTalabAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveTalabAnswers(@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		try {
			map = commonService.saveInspectionAnwserForTalab(dataJson);

		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Talab Inspection Data.." + e.getStackTrace());
			return map;
		}

		/*
		 * map.put("code", "200"); map.put("message", "SUCCESS");
		 * map.put("DetailMessage", "Sadak Inspection Data saved");
		 */

		logger.info("Talab Inspection Data saved..");

		return map;

	}

	@RequestMapping(value = "/saveBhavanAnswersCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveBhavanAnswersCC(@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		logger.info("Incoming Request for Bhavan :" + dataJson + "");

		try {
			map = commonService.saveInspectionAnwserForBhavanCC(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Bhavan InspectionCC Data.." + e.getStackTrace());
			return map;
		}
		/*
		 * map.put("code", "200"); map.put("message", "SUCCESS");
		 * map.put("DetailMessage", "Bhavan Inspection Data saved");
		 */

		logger.info("Bhavan Inspection Data saved..");

		return map;

	}

	@RequestMapping(value = "/savePuliaAnswersCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> savePuliaAnswersCC(@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		logger.info("Incoming Request for Pulia :" + dataJson + "");
		try {
			map = commonService.saveInspectionAnwserForPuliaCC(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Pulia InspectionCC Data.." + e.getStackTrace());
			return map;
		}
		/*
		 * map.put("code", "200"); map.put("message", "SUCCESS");
		 * map.put("DetailMessage", "Pulia Inspection Data saved");
		 */

		logger.info("Pulia Inspection Data saved..");

		return map;
	}

	@RequestMapping(value = "/saveSadakAnswersCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveSadakAnswersCC(@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		logger.info("Incoming Request for Sadak :" + dataJson + "");

		try {
			map = commonService.saveInspectionAnwserForSadakCC(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Sadak InspectionCC Data.." + e.getStackTrace());
			return map;
		}
		/*
		 * map.put("code", "200"); map.put("message", "SUCCESS");
		 * map.put("DetailMessage", "Sadak Inspection Data saved");
		 */

		logger.info("Sadak InspectionCC Data saved..");

		return map;

	}

	@RequestMapping(value = "/saveTalabAnswersCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveTalabAnswersCC(@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		logger.info("Incoming Request for Talab :" + dataJson + "");
		try {
			map = commonService.saveInspectionAnwserForTalabCC(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Talab InspectionCC Data.." + e.getStackTrace());
			return map;
		}

		/*
		 * map.put("code", "200"); map.put("message", "SUCCESS");
		 * map.put("DetailMessage", "Sadak Inspection Data saved");
		 */

		logger.info("Talab Inspection Data saved..");
		return map;

	}

	@RequestMapping(value = "/fileUploadCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> fileUploadCC(@RequestBody FileJson fileJson) throws Exception {

		Map<String, String> map = new HashMap<String, String>();

		try {

			commonService.saveFileCC(fileJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving File in InspectionCC Data.." + e.getStackTrace());
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "File uploaded successfully !");

		logger.info("File saved..");

		return map;
	}

	// in Use
	@RequestMapping(value = "/imageUploadCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> imageUploadCCC(@RequestBody ImageJson imageJson) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		try {

			commonService.saveImageCC(imageJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Image in InspectionCC Data.." + e.getStackTrace());
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Image saved successfully !");

		logger.info("Image Data saved..");

		return map;
	}

	/*
	 * @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR ,
	 * reason="Error in saving Inspection Data")
	 * 
	 * @ExceptionHandler(Exception.class) public void exceptionHandler(Exception e)
	 * { logger.error("Error in saving Inspection Data.."); e.printStackTrace(); }
	 */

	///////////////////

	/////////////////// This is one time activity

	@RequestMapping(value = "/updateWorkRequisitionScriptDone", method = RequestMethod.GET)

	public void updateWorkRequisitionScript() throws Exception {

		commonService.updateWorkRequisitionScript();

		System.out.println("Completed ......updateWorkRequisitionScript");
	}

	/////////////////// This is one time activity

	@RequestMapping(value = "/updateWorkRequisitionScriptForNonLegacy", method = RequestMethod.GET)

	public void updateWorkRequisitionScriptForNonLegacy() throws Exception {

		commonService.updateWorkRequisitionScriptForNonLegacy();

		System.out.println("Completed ......updateWorkRequisitionScriptForNonLegacy");
	}

	@RequestMapping(value = "/updateFinancialYearForWorkForNonLegacyData", method = RequestMethod.GET)

	public void updateFinancialYearForWork() throws Exception {
		long start = System.currentTimeMillis();
		try {
			commonService.updateFinancialYearForWorkForNonLegacyData();
		} catch (Exception e) {
			e.printStackTrace();

			long elapsedTimeMillis = System.currentTimeMillis() - start;
			// Get elapsed time in minutes
			float elapsedTimeMin = elapsedTimeMillis / (60 * 1000F);
			System.out.println(
					"in exception controller - updateFinancialYearForWorkForNonLegacyData.........." + elapsedTimeMin);

		}

		System.out.println("Completed controller ......updateFinancialYearForWorkForNonLegacyData");
	}

	@RequestMapping(value = "/updateFinancialYearForWorkForLegacyData", method = RequestMethod.GET)

	public void updateFinancialYearForWorkForLegacyData() throws Exception {
		long start = System.currentTimeMillis();
		try {
			commonService.updateFinancialYearForWorkForLegacyData();
		} catch (Exception e) {
			e.printStackTrace();

			long elapsedTimeMillis = System.currentTimeMillis() - start;
			// Get elapsed time in minutes
			float elapsedTimeMin = elapsedTimeMillis / (60 * 1000F);
			System.out.println(
					"in exception controller - updateFinancialYearForWorkForLegacyData.........." + elapsedTimeMin);

		}

		System.out.println("Completed controller ......updateFinancialYearForWorkForLegacyData");
	}

	// Rakesh Code for Webservices

	// new code for single villagePiped water supply
	@RequestMapping(value = "/getResBillInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.singlevillagepipped.Data getSingleVillagePipedWaterSupplySchemeInstance(

			@RequestParam Long id) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetailsByBillId(id);

		com.res.entity.singlevillagepipped.Data data = getVillagePipedWaterSupplySchemeInstanceData(workBean, id);

		return data;
		// return category_1;
	}

	private com.res.entity.singlevillagepipped.Data getVillagePipedWaterSupplySchemeInstanceData(WorkBean workBean,
			Long billId) {

		com.res.entity.singlevillagepipped.Meta meta = new com.res.entity.singlevillagepipped.Meta();
		meta.setInstanceID("build_Verify-Bill-_1577872864");

		WorkDetails workDetails = new WorkDetails(workBean.getWorkName() + "", workBean.getExecutionAgency(),
				workBean.getWorkTypeName() + "", workBean.getWorkSubTypeName(), workBean.getWorkId().toString());

		// to fill all components records

		com.res.entity.singlevillagepipped.Data data = null;
		try {

			com.res.entity.singlevillagepipped.ImageAndFiles imagesAndFiles = new com.res.entity.singlevillagepipped.ImageAndFiles(
					"", "", "", "", "", "");
			com.res.entity.singlevillagepipped.OverallGradingAndRemarks overallGradingAndRemarks = new OverallGradingAndRemarks(
					"", "");
			data = new com.res.entity.singlevillagepipped.Data(meta, workDetails, workBean.getWorkId() + "",
					imagesAndFiles, overallGradingAndRemarks);
//			DynaBean myBean = new LazyDynaBean();
//			myBean.set("myProperty", "myValue");

			BillBean bean = commonService.fetchBillDetails(billId);
			data.setBill(new Bill(bean.getBillNo(), bean.getBillType(), bean.getId() + ""));

			List<BillItems> billItems = new ArrayList<BillItems>();
			for (BillItemBean itemBean : bean.getBillItems()) {

				if (null != itemBean.getQuantityUptodate() && itemBean.getQuantityUptodate().intValue() > 0)
					billItems.add(new BillItems(itemBean.getUnit(), itemBean.getQuantityUptodate().intValue() + "",
							itemBean.getQuantityUptodate().intValue() + "",
							itemBean.getItemOfWork() + "\n #id: " + itemBean.getId(), "", "",
							itemBean.getWorkTemplateId() + ""));
			}
			/*
			 * billItems.add(new BillItems("KG", "12", "Jahaha hhahah ahajaj", "", ""));
			 * billItems.add(new BillItems("KG", "13", "Jahah ahhahah ahajaj", "", ""));
			 * billItems.add(new BillItems("KG", "14", "Jahahahha hah ahajaj", "", ""));
			 * billItems.add(new BillItems("KG", "15", "Jahahah hahah ahajaj", "", ""));
			 * billItems.add(new BillItems("Liter", "69999", "Mkk kfdfd", "", ""));
			 * data.setBillItems(billItems);
			 */
			data.setOverallGradingAndRemarks(overallGradingAndRemarks);
			data.setBillItems(billItems);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;

	}

	@RequestMapping(value = "/saveResBillAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveSingleVillagePipedWaterSupplyScheme(@RequestBody DataJson dataJson)
			throws IOException {

		Map<String, String> map = new HashMap<String, String>();
		Long billInspectionId = null;
		// System.err.println(dataJson.getXml());
		try {
			billInspectionId = commonService.saveSingleVillagePipedWaterSupplyScheme(dataJson);
			if (billInspectionId == 0L) {
				map.put("billInspectionId", billInspectionId + "");
				map.put("code", "201");
				map.put("message", "Inspection Already Done");
				map.put("DetailMessage", "Inspection Already Completed , you can not do multiple times ");

				logger.info("Single Village PipedWater Supply Scheme Inspection Data saved..");
				return map;
			}
			// System.err.println("billInspectionId="+billInspectionId);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error(
					"Error in saving Single Village PipedWater Supply Scheme Inspection Data.." + e.getStackTrace());
			return map;
		}
		map.put("billInspectionId", billInspectionId + "");
		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Single Village PipedWater Supply Scheme Inspection Data saved");

		logger.info("Single Village PipedWater Supply Scheme Inspection Data saved..");

		return map;

	}

	/*
	 * @RequestMapping(value = "/downloadDocumentImages/{imageName}", method =
	 * RequestMethod.GET) public void downloadDocuments(@PathVariable String
	 * imageName, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception { DocumentUpload
	 * doc=documentRepository.findByDocumentName(imageName+".jpg");
	 * 
	 * try { //List<DocumentUpload>
	 * doc=documentRepository.findByDocumentName(imageName+".jpg"); //DocumentUpload
	 * doc=documentRepository.findOne(93L); //String fileName =
	 * commonService.fetchDownloadFileName(doc.get(0).getDocumentId()); String
	 * fileName = commonService.fetchDownloadFileName(doc.getDocumentId());
	 * 
	 * if (fileName != null) { File file = new File(fileName); FileInputStream fin =
	 * new FileInputStream(file); PrintWriter p = response.getWriter();
	 * response.setContentType("image/jpeg"); int i = 0; while (i != -1) { i =
	 * fin.read(); p.write(i); } fin.close(); } }catch (Exception e) { // TODO:
	 * handle exception e.printStackTrace(); } }
	 */
	/*
	 * @RequestMapping(value = "/fetchBillInspectionData/{id}/{inspectedBy}", method
	 * = RequestMethod.GET)
	 * 
	 * @ResponseBody public BillInspectionBean fetchBillInspectionData(@PathVariable
	 * Long id,
	 * 
	 * @PathVariable String inspectedBy) {
	 * 
	 * 
	 * List<BillInspectionBean> list =
	 * commonService.fetchBillInspectionByBillId(id,inspectedBy); //
	 * System.err.println("list s==>"+list.get(0).getInspectionComponentsBeans().
	 * size()); //
	 * System.err.println("list s==>"+list.get(0).getBillInspectionItemsBean().size(
	 * ));
	 * 
	 * return list.get(0); }
	 */

	/*
	 * @RequestMapping(value = "/image/{imageName}")
	 * 
	 * @ResponseBody public byte[] getImage(@PathVariable(value = "imageName")
	 * String imageName) throws IOException { // createPizzaImagesDirIfNeeded(); //
	 * File serverFile = new File(PIZZA_IMAGES_DIR_ABSOLUTE_PATH + imageName +
	 * ".jpg");
	 * 
	 * return Files.readAllBytes(serverFile.toPath()); }
	 */

	/*
	 * @RequestMapping(value = "/findAllContractor", method = RequestMethod.GET,
	 * produces = MediaType.APPLICATION_JSON_VALUE) public @ResponseBody
	 * List<Object[]> findAllContractor(HttpServletRequest request) {
	 * 
	 * User user = RESUtil.getUserDetail(); if (null != user) {
	 * logger.info("User - " + user.getUsername() + ", Role - " +
	 * user.getAuthorities() + " - Fetching All works data"); }
	 * 
	 * List<Object[]> workBeanList = commonService.addContractorsAsUsers(); return
	 * workBeanList;
	 * 
	 * 
	 * }
	 */

	////////// this is 1 time activity

	@RequestMapping(value = "/updateContractorsAsUsers", method = RequestMethod.GET)

	public void addContractorsAsUsers() throws Exception {

		commonService.addContractorsAsUsers();

		System.out.println("Completed ......addContractorsAsUsers");
	}

	@RequestMapping(value = { "/fetchAllBillsForAllWorks",
			"/fetchAllBillsByWorkId/{workId}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BillBean> fetchAllBillsByWorkId(@PathVariable(required = false) Long workId,
			HttpServletRequest request) {

		List<BillBean> billBeanList = commonService.fetchBillListForWork(workId);

		return billBeanList;
	}

	@RequestMapping(value = { "/fetchPhysicalMilestonesForAllWorks",
			"/fetchPhysicalMilestonesByWorkId/{workId}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<WorkPhysicalMileStoneBean> fetchPhysicalMilestonesByWorkId(
			@PathVariable(required = false) Long workId, HttpServletRequest request) {

		List<WorkPhysicalMileStoneBean> beanList = commonService.fetchPhysicalMilestonesByWorkId(workId);

		return beanList;
	}

	@RequestMapping(value = { "/fetchFinancialMilestonesForAllWorks",
			"/fetchFinancialMilestonesByWorkId/{workId}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<WorkFinancialMileStoneBean> fetchFinancialMilestonesByWorkId(
			@PathVariable(required = false) Long workId, HttpServletRequest request) {

		List<WorkFinancialMileStoneBean> beanList = commonService.fetchFinancialMilestonesByWorkId(workId);

		return beanList;
	}

	@RequestMapping(value = "/fetchUploadedInspectionAnswersNew/{inspectionId}/{inspectedBy}/{isWorkId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody InspectionCompletedWorkBeanNew fetchInspectionPendingBillsNew(@PathVariable Long inspectionId,
			@PathVariable Long inspectedBy, @PathVariable(value = "isWorkId", required = false) boolean isWorkId,
			HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - " + user.getAuthorities()
					+ " - Fetching Inspection Data data");
		}

		InspectionCompletedWorkBeanNew billBeanList = commonService.fetchInspectionCompletedBillsNew(inspectionId,
				inspectedBy);

		return billBeanList;
	}

}
