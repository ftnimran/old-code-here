package com.res.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.res.entity.WorkNature;

public interface WorkNatureRepository extends CrudRepository<WorkNature, Long> {

List<WorkNature> findByEnabled(Short isEnabled);


}
