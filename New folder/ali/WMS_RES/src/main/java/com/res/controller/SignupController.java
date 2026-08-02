package com.res.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.res.bean.DesignationBean;
import com.res.bean.OfficeBean;
import com.res.bean.OfficeTypeBean;
import com.res.bean.UserBean;
import com.res.constants.RESConstants;
import com.res.exception.RESBusinessException;
import com.res.response.ResponseObject;
import com.res.service.CommonService;
import com.res.service.UserService;

@Controller
public class SignupController{

	public static final Logger logger = LoggerFactory.getLogger(SignupController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CommonService commonService;
	
	@Value("${applicationDeploymentServerName}")
	private String applicationDeploymentServerName;

	/*@Autowired
	ForgotPasswordValidator forgotPasswordValidator;*/

	@RequestMapping(value = "registrationForm", method = RequestMethod.GET)
	public ModelAndView viewSignUpForm(HttpServletRequest request, Model model, HttpServletResponse response) {

		logger.info("Displaying Sign up page");
		
		//Cache Control
		String headerValue = CacheControl.maxAge(10, TimeUnit.SECONDS).getHeaderValue();
		response.addHeader("Cache-Control", headerValue);
		
		ModelAndView modelAndView = new ModelAndView("signup");
		return modelAndView;
		
	}

	@RequestMapping(value = "doSignUp", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject doSignUp(@RequestBody UserBean signUpBean,
			HttpServletRequest httpServletRequest)
					throws RESBusinessException{

		logger.info("Signing Up");
		
		ResponseObject response = new ResponseObject();
		
		String captchaText = signUpBean.getCaptchaText();
		//String captchaText = request.getParameter("captchaText");
		HttpSession session = httpServletRequest.getSession();

		String captcha = (String) session
				.getAttribute(RESConstants.CAPTCHA_SIGNUP);

		if (captcha == null
				|| (captcha != null && !captcha.equals(captchaText))) {

			logger.error("Wrong Captcha Text!");
			response.setErrorMessage("Wrong Captcha Text!");
			return response;
			//model.addAttribute("error", "Wrong Captcha Text!");
			//return "signup";
		} 
		/*forgotPasswordValidator.validate(forgotPasswordBean, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("forgotPasswordBean", forgotPasswordBean);
			return "forgotpassword";
		}

		userService.resetPassword(
				forgotPasswordBean.getEmailId());*/

		//return "redirect:/login?resetPassword";
		
		/*String url = httpServletRequest.getRequestURL().toString();
		
		String verifyServiceUrl = url.replaceAll("doSignUp", RESConstants.VERIFY_EMAIL_SERVICE_NAME);*/
		
		String contextPath = "";
		if (!StringUtils.isEmpty(httpServletRequest.getContextPath())) {
			contextPath = httpServletRequest.getContextPath();
		}

		String fullPath = httpServletRequest.getScheme() + "://"
				+ getApplicationDeploymentServerName() + ":"
				+ httpServletRequest.getServerPort() + contextPath;
		
		String verifyServiceUrl = fullPath + "/" + RESConstants.VERIFY_EMAIL_SERVICE_NAME;
		String errorMsg = userService.registerUser(signUpBean, verifyServiceUrl);
		if(errorMsg!=null){
			logger.error(errorMsg);
			response.setErrorMessage(errorMsg);
			return response;
		}
		logger.info("Email - " + signUpBean.getEmailId() +" successfully registered in the system.");
		response.setSuccessMessage("You are successfully registered in the system.");
		return response;
	}
	
	@RequestMapping(value = "fetchDesignations", method = RequestMethod.GET)
	@ResponseBody
	public List<DesignationBean> fetchDesignations(HttpServletRequest request) {
		return commonService.fetchDesignations();
	}

	@RequestMapping(value = "fetchOfficeTypes", method = RequestMethod.GET)
	@ResponseBody
	public List<OfficeTypeBean> fetchOfficeTypes(HttpServletRequest request) {
		return commonService.fetchOfficeTypes();
	}

	@RequestMapping(value = "fetchOfficesByOfficeType/{officeTypeId}", method = RequestMethod.GET)
	@ResponseBody
	public List<OfficeBean> fetchOfficesByOfficeType(HttpServletRequest request, @PathVariable Long officeTypeId) {
		return commonService.fetchOfficesByOfficeType(officeTypeId);
	}
	
	@RequestMapping(value = "fetchSubDivionOfficesByOfficeId/{officeId}", method = RequestMethod.GET)
	@ResponseBody
	public List<OfficeBean> fetchSubDivionOfficesByOfficeId(HttpServletRequest request, @PathVariable Long officeId) {
		return commonService.fetchSubDivisionalOfficesByParentOfficeId(officeId);
	}
	
	
	@RequestMapping(value = "verifyEmail", method = RequestMethod.GET)
	public String verifyEmail(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id
			,@RequestParam(value = "verificationStr", required = true) String verificationStr) {
		
		String status = userService.verifyEmail(id, verificationStr);
		
		if(status!=null){
			if(status.equals("Already Verified")){
				return "redirect:/login?alreadyVerified";
			}
			return "redirect:/login?verificationSuccess";
		}
		else
			return "redirect:/login?verificationError";
	}

	public String getApplicationDeploymentServerName() {
		return applicationDeploymentServerName;
	}

	public void setApplicationDeploymentServerName(String applicationDeploymentServerName) {
		this.applicationDeploymentServerName = applicationDeploymentServerName;
	}
}