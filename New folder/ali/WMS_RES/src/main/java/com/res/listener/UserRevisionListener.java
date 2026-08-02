package com.res.listener;

import org.hibernate.envers.RevisionListener;

import com.res.entity.UserRevEntity;
import com.res.util.RESUtil;

public class UserRevisionListener implements RevisionListener {
	
    @Override
    public void newRevision(Object revisionEntity) {
    	
        UserRevEntity revEntity = (UserRevEntity) revisionEntity;
        
        String username = null;
		
		if(RESUtil.getUserDetail()!=null)
			username = RESUtil.getUserDetail().getUsername();
		else
			username = "";
		
        revEntity.setUsername(username);
    }
}
