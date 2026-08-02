package com.res.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import com.res.bean.UserBean;
import com.res.service.UserService;
import com.res.util.RESUtil;

public class BaseController {

	@Autowired
	private UserService userService;

	public UserBean fetchLoggedInUserDetails(HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			return userService.fetchUserDetailsByUserName(user.getUsername());
		} else {
			return null;
		}
	}

}
