package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
	List<Notification> findByStatus(String status);

}
