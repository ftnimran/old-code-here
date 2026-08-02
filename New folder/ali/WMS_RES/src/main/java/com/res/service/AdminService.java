package com.res.service;

import com.res.bean.WorkBean;

public interface AdminService {

	WorkBean fetchWorkDetails(Long id);
	
    String deleteAccountHead(Long id, String username);
	
	String deleteLineDept(Long id, String username);
	
	String deleteContractor(Long id, String username);
	
	
}
