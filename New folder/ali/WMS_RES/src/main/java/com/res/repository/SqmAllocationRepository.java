package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.BudgetRequestDetail;
import com.res.entity.SqmAllocation;
import com.res.entity.Users;
import com.res.entity.Work;

public interface SqmAllocationRepository  extends JpaRepository<SqmAllocation, Long> {
	
	//List<OfficeType> findByEnabled(Short isEnabled);
public	List<SqmAllocation> findByUsers(Users users);
public	List<SqmAllocation> findByUsersAndEnabled(Users users, Short s);
//@Query("select s.officeId from #{#SqmAllocation} s")
//List<Long> getAllOfficeIds();
//public	List<SqmAllocation> findDistinctByOfficeIdAndUsers(Users users);

public List<SqmAllocation> findByWorkAndUsers(Work work, Users users);

public List<SqmAllocation> findByUsersAndInspectionDoneAndEnabled(Users users, Short s, Short enabled);

public	SqmAllocation findById(Long id);

public	SqmAllocation findByIdAndInspectionDone(Long id, short inspectionDone);

Page<SqmAllocation> findByWorkAndInspectionDone(Pageable pageable, Work work, short inspectionDone);

public List<SqmAllocation> findAllocationByWorkAndInspectionDone(Work work, short inspectionDone);
//public List<SqmAllocation> findD();

@Query(value="select u.id,u.username,u.name from users u left join user_role ur on u.id = ur.id where ur.role_code = 'ROLE_SQM'",nativeQuery=true) 
public List<Object[]> findDistinctUsersForName();



@Query(value="select ur.id, ur.name, ur.username, ur.email_id, ur.mobile_no, ur.status, d.designation "
		+ "from sqm_allocation u left join users ur on u.user_id=ur.id "
		+ "left join mst_designation d on ur.designation_id=d.id "
		+ "where ur.designation_id in (1,2,3,4,5,9) and ur.status!='Deleted' group by ur.id order by ur.created_date desc limit ?1,?2 ;",nativeQuery=true)
public List<Object[]> findByInspRole(@Param("offset") int offset,
		@Param("maxLimit") int maxLimit);

@Query(value="select count(*) from( select count(u.id) from sqm_allocation u left join users ur on u.user_id=ur.id left join mst_designation d on ur.designation_id=d.id where ur.designation_id in (1,2,3,4,5,9) and ur.status!='Deleted' group by ur.id) a ",nativeQuery=true)
public long findByInspRoleCount();
public List<SqmAllocation> findDistinctByUsers(Users users);
public List<SqmAllocation> findAllocationByWorkAndInspectionDoneAndOfficeTypeNotNull(Work work, short s);
public List<SqmAllocation> findAllocationByWorkAndInspectionDoneAndOfficeTypeIsNull(Work work, short s);
public Page<SqmAllocation> findByWorkAndInspectionDoneAndOfficeTypeIsNull(Pageable pageable, Work work, short s);
public Page<SqmAllocation> findByWorkAndInspectionDoneAndOfficeTypeNotNull(Pageable pageable, Work work, short s);
    
}
