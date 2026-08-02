package com.res.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;
import com.res.bean.AuthRequest;
import com.res.bean.AuthResponseBean;
import com.res.bean.UserBean;
import com.res.exception.RESBusinessException;
import com.res.service.UserService;
import com.res.util.SHAHashingUtil;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String viewLogin(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "timeout", required = false) String timeout,
			@RequestParam(value = "resetPassword", required = false) String resetPassword,
			@RequestParam(value = "register", required = false) String register,
			@RequestParam(value = "alreadyVerified", required = false) String alreadyVerified,
			@RequestParam(value = "verificationSuccess", required = false) String verificationSuccess,
			@RequestParam(value = "verificationError", required = false) String verificationError,
			HttpServletRequest request, Model model) {
		if (error != null)
			// model.addAttribute("error",
			// "Your username and password is invalid.");
			model.addAttribute("error",
					getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));

		if (logout != null)
			model.addAttribute("message",
					"You've been logged out successfully.");

		if (timeout != null) {
			model.addAttribute("error", "Session expired. Please login again.");
		}
		if (resetPassword != null) {
			model.addAttribute("message",
					"New password has been sent to your registered Email Id and Mobile No.!");
		}
		if (register != null) {
			model.addAttribute(
					"message",
					"Sign up was successful. Please verify your email by following instructions sent to you in an email.");
		}
		if (alreadyVerified != null) {
			model.addAttribute("error", "Email Already Verified.");
		}
		if (verificationSuccess != null) {
			model.addAttribute(
					"message",
					"Email Verification Successful. Once Admin approves your account, You will get email notification.");
		}
		if (verificationError != null) {
			model.addAttribute("error", "Invalid URL.");
		}

		return "index";
	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession()
				.getAttribute(key);

		String error = "";

		if (exception instanceof AuthenticationServiceException) {
			error = exception.getMessage();
		} else if (exception instanceof BadCredentialsException) {
			error = "Invalid user name or password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = exception.getMessage();
		}

		return error;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView viewErrorPage(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("error/403");
		return modelAndView;

	}

	@PostMapping("/authenticateUserByMobileApp")
	@ResponseBody
	public ResponseEntity<AuthResponseBean> authenticateUserByMobileApp(
	        @RequestBody AuthRequest authRequest) {

		AuthResponseBean responseBean = new AuthResponseBean();
		responseBean.setUserId(authRequest.getUsername());
		
		

		UserBean user = userService.fetchUserDetailsByUserName(authRequest.getUsername());

		if (null != user && null != user.getId()) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			 try {
				StringBuffer encodedPassword = SHAHashingUtil.encryptPassword(authRequest.getPassword());
			
			if (passwordEncoder.matches(encodedPassword, user.getPassword())) {
				responseBean.setStatusCode(200L);
				responseBean.setStatusDesc("Success");
				responseBean.setLoggedInUserRole(user.getLoggedInUserRole());
				if (null != user.getOfficeId()) {
					responseBean.setOfficeId(user.getOfficeId().intValue());
				}
				if (null != user.getId()) {
					responseBean.setUserIdInt(user.getId().intValue());
				}
				if (null != user.getIsOIC()) {
				responseBean.setIsOIC(user.getIsOIC());
				}
				
				// Success
				/*
				 * responseBean.setStatusCode(200L); responseBean.setStatusDesc("Success");
				 * responseBean.setLoggedInUserRole(user.getLoggedInUserRole());
				 * responseBean.setOfficeId(user.getOfficeId() != null ?
				 * user.getOfficeId().intValue() : null); responseBean.setUserIdInt(user.getId()
				 * != null ? user.getId().intValue() : null);
				 */
			    
				
			} else {
				responseBean.setStatusCode(302L);
				responseBean.setStatusDesc("Invalid Password");
				}
			 } catch (RESBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} else {
			responseBean.setStatusCode(301L);
			responseBean.setStatusDesc("Invalid User Id");
		}

	    return ResponseEntity.ok(responseBean);
	}

	
	@RequestMapping(value = "/contactUs", method = RequestMethod.GET)
	public ModelAndView contactUs(HttpServletRequest request) {
	
	
		ModelAndView modelAndView = new ModelAndView("contactUs");
		return modelAndView;
	}
}
