package com.res.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.WorkOfficersHistory;

public interface WorkOfficersHistoryRepository extends JpaRepository<WorkOfficersHistory, Long>, CrudRepository<WorkOfficersHistory, Long> {

}
