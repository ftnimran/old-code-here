package com.res.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.res.bean.ForgotPasswordBean;
import com.res.constants.RESConstants;
import com.res.exception.RESBusinessException;
import com.res.service.UserService;
import com.res.validator.ForgotPasswordValidator;

@Controller
public class ForgotPasswordController{

	public static final Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ForgotPasswordValidator forgotPasswordValidator;

	@RequestMapping(value = "forgotpassword", method = RequestMethod.GET)
	public String viewForgotPassword(HttpServletRequest request, Model model) {
		
		logger.info("Displaying Forgot password page");
		model.addAttribute("forgotPasswordBean", new ForgotPasswordBean());
		return "forgotpassword";

	}

	@RequestMapping(value = "resetpassword", method = RequestMethod.POST)
	public String resetPassword(@Valid ForgotPasswordBean forgotPasswordBean,
			BindingResult bindingResult, Model model, HttpServletRequest request)
			throws RESBusinessException{
		
		logger.info("Resetting password");
		
		String captchaText = request.getParameter("captchaText");
		HttpSession session = request.getSession();

		String captcha = (String) session
				.getAttribute(RESConstants.CAPTCHA_RESET);

		if (captcha == null
				|| (captcha != null && !captcha.equals(captchaText))) {
			
			logger.error("Wrong Captcha Text!");
			model.addAttribute("error", "Wrong Captcha Text!");
			return "forgotpassword";
		} 
		forgotPasswordValidator.validate(forgotPasswordBean, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("forgotPasswordBean", forgotPasswordBean);
			return "forgotpassword";
		}

		userService.resetPassword(
				forgotPasswordBean.getEmailId());
		
		return "redirect:/login?resetPassword";
	}

}
