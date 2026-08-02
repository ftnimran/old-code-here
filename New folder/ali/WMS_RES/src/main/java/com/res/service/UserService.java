package com.res.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.res.bean.ChangePasswordBean;
import com.res.bean.RoleBean;
import com.res.bean.UserBean;
import com.res.entity.Users;
import com.res.exception.RESBusinessException;
import com.res.json.UserJson;

public interface UserService {

	Users findByUserName(String userName);
	
	Users findByEmailId(String emaildId);
	
	void changePassword(ChangePasswordBean changePassword, String userName)
			throws RESBusinessException;

	void resetPassword(String username) throws RESBusinessException;

	List<RoleBean> fetchRoles();

	UserBean fetchUserDetails(Long id);
	
	UserBean fetchSqmUserDetails(Long id);
	
	boolean checkSqmUserDetailByEmailId(String emailId);

	String editUser(UserBean bean, String websiteURL);
//Rakesh
	String addUser(UserBean bean, String websiteURL);
	String updateSqmUser(UserBean bean, String websiteURL);
	
	String deleteUser(Long id);
	
	UserBean fetchUserDetailsByUserName(String userName);

	UserJson getAllUsers(Pageable pageable, String searchBoxVal, String designation, String status, Long officeId);
	UserJson getAllSqmUsers(Pageable pageable, String searchBoxVal, String designation, String status, Long officeId);

	String verifyEmail(Long id, String verificationStr);

	String registerUser(UserBean signUpBean, String verifyServiceUrl) throws RESBusinessException;
	
	public UserBean convertUserEntityToBean(Users user);

	public boolean checkIsOICByOfficeId(Long officeId);
	
	public UserJson getAllSqmUsersForSe(Pageable pageable, String searchBoxVal,
			String designation, String status, Long officeId);

	UserJson getAllInspUsers(Pageable pageable, String searchBoxVal, String designation, String status, Long officeId);
	
	UserBean fetchInspUserDetails(Long id);

	String updateOfficerInsp(UserBean userBean, String websiteURL);
	
	
}
