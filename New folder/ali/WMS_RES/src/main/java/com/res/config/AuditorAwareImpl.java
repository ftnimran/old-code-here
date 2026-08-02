package com.res.config;

import org.springframework.data.domain.AuditorAware;

import com.res.util.RESUtil;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		
		String username = null;
		
		if(RESUtil.getUserDetail()!=null)
			username = RESUtil.getUserDetail().getUsername();
		else
			username = "";
		
		return username;
	}

}
