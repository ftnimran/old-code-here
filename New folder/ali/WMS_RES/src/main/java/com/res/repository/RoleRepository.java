package com.res.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.Role;

public interface RoleRepository  extends JpaRepository<Role, String> {
    
}
