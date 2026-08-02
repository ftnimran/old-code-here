package com.res.entity;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import com.res.listener.UserRevisionListener;

@SuppressWarnings("serial")
@Entity
@RevisionEntity(UserRevisionListener.class)
public class UserRevEntity extends DefaultRevisionEntity {
	private static final long serialVersionUID = 1L;
	private String username;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
