package com.res.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.res.bean.ForgotPasswordBean;
import com.res.entity.Users;
import com.res.service.UserService;

@Component
public class ForgotPasswordValidator implements Validator{

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ForgotPasswordBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ForgotPasswordBean forgotpasswordBean = (ForgotPasswordBean) target;

		/*if(!StringUtils.isEmpty(forgotpasswordBean.getEmailId())) {
			EmailValidator  emailValidator = EmailValidator.getInstance();

			if(!emailValidator.isValid(forgotpasswordBean.getEmailId())) {
			errors.rejectValue("emailId", "Pattern.forgotPasswordBean.emailId");	
			}

		}*/

		if(!StringUtils.isEmpty(forgotpasswordBean.getEmailId())) {

			Users user = userService.findByEmailId(forgotpasswordBean.getEmailId());

			if (user == null) {
				errors.rejectValue("emailId", "Pattern.forgotPasswordBean.emailId");
			}
		}
	}
}
