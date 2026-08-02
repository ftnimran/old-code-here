package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.Designation;
import com.res.entity.Office;
import com.res.entity.Users;
import com.res.entity.WorkType;

public interface UserRepository  extends JpaRepository<Users, Long> {
    
	Users findByUsername(String username);
	
	Users findByEmailId(String emailId);
	
	Users findByEmailIdAndStatusNot(String emailId, String status);
	
	Users findByIdAndVerificationRandomString(Long id, String verificationRandomString);
	
	Users findByUsernameAndStatus(String username, String status);
	
	Users findByUsernameAndStatusNot(String username, String status);
	//Rakesh
	//@Query(value = "select count(u.emailId)>0 FROM Users u where  u.emailId=:emailId")
	//Boolean checkUserExist(@Param("emailId")String emailId);
	@Query(value = "from Users u  where  u.emailId=:emailId")
	List<Users> checkUserExist(@Param("emailId")String emailId);
	
	
	/*Page<Users> findByUsernameContainingAndStatusNot(Pageable pageable, String username, String status);*/
	
	@Query("from Users u where (u.name like %:name% or u.emailId like %:emailId%) and status not in :status and isOIC is not null"
			+ " and u.office = COALESCE(:office, u.office) and u.isOIC in (:isOIC)")
	Page<Users> findByNameContainingOrEmailIdContainingAndStatusNotInAndIsOICNotNullAndOffice(Pageable pageable, @Param("name")String name, 
			@Param("emailId")String emailId, @Param("status")String[] status, @Param("office")Office office, @Param("isOIC")List<Short> isOIC);
	
	@Query("from Users u where (u.designation.designation like %:designation% and u.status in :status) and isOIC is not null"
			+ " and u.office = COALESCE(:office, u.office) and u.isOIC in (:isOIC)")
	Page<Users> findByDesignationAndStatusInAndIsOICNotNullAndOffice(Pageable pageable,
			@Param("designation")String designation, @Param("status")String[] status, @Param("office")Office office
			, @Param("isOIC")List<Short> isOIC);
	
	@Query("from Users u where (u.name like %:name% or u.emailId like %:emailId%) and (u.designation.designation like %:designation% and u.status in :status)"
			+ "  and isOIC is not null and u.office = COALESCE(:office, u.office) and u.isOIC in (:isOIC)")
	Page<Users> findByNameContainingOrEmailIdContainingAndDesignationAndStatusInAndIsOICNotNullAndOffice(Pageable pageable, 
			@Param("name")String name, 
			@Param("emailId")String emailId, @Param("designation")String designation, @Param("status")String[] status, @Param("office")Office office
			, @Param("isOIC")List<Short> isOIC);
	
	@Query("from Users u where status not in :status and isOIC is not null"
			+ " and u.office = COALESCE(:office, u.office) and u.isOIC in (:isOIC)")
	Page<Users> findByStatusNotInAndIsOICNotNullAndOffice(Pageable pageable, @Param("status")String[] status, @Param("office")Office office
			, @Param("isOIC")List<Short> isOIC);
	
	@Query("select count(*) from Users u where status not in :status and isOIC is not null"
			+ " and u.office = COALESCE(:office, u.office) and u.isOIC in (:isOIC)")
	long countByStatusNotInAndIsOICNotNullAndOffice( @Param("status")String[] status, @Param("office")Office office
			, @Param("isOIC")List<Short> isOIC);
	
	@Query("from Users u where u.office = :office and u.designation = :designation and u.status = :status order by u.name Asc, u.isOIC Desc")
	List<Users> findByOfficeAndDesignation(@Param("office") Office office,@Param("designation") Designation designation, @Param("status") String status);
	
	//findByOfficeAndDesignations
	
	@Query("from Users u where u.office = :office and u.designation in (1,2,3,4,5,9) and u.status = :status order by u.name Asc, u.isOIC Desc")
	List<Users> findByOfficeAndDesignations(@Param("office") Office office, @Param("status") String status);
	
	Users findByOfficeIdAndIsOICAndStatus(Long officeId, Short isOIC, String status);
	//Rakesh
	@Query(value="select u.id, " + 
			"u.name, " + 
			"       u.username, " + 
			"       u.email_id, " + 
			"       u.mobile_no, " + 
			"       u.status, " + 
			"       ur.role_code " + 
			"       from users u  " + 
			"       left join user_role ur on u.id=ur.id  " + 
			"       where ur.role_code='ROLE_SQM'  and u.status!='Deleted' order by u.created_date desc limit ?1,?2 ;",nativeQuery=true)
	
	public List<Object[]> findBySqmRole(@Param("offset") int offset,
			@Param("maxLimit") int maxLimit);
	
		@Query(value="select u.id, " + 
				"u.name, " + 
				"       u.username, " + 
				"       u.email_id, " + 
				"       u.mobile_no, " + 
				"       u.status, " + 
				"       ur.role_code " + 
				"       from users u  " + 
				"       left join user_role ur on u.id=ur.id  " + 
				"       inner join sqm_allocation sqma on sqma.user_id=u.id  " + 
				"       where ur.role_code='ROLE_SQM' and u.status!='Deleted' and sqma.office_id in (?3) group by u.id order by u.created_date desc limit ?1,?2 ;",nativeQuery=true)
		
		public List<Object[]> findBySqmRoleForSe(@Param("offset") int offset,
				@Param("maxLimit") int maxLimit, @Param("eeOficeIdList") List<Long> eeOficeIdList);
	
	@Query(value="select count(u.id)  " + 
			"        from users u  " + 
			"       left join user_role ur on u.id=ur.id  " + 
			"       where ur.role_code='ROLE_SQM'  and u.status!='Deleted'",nativeQuery=true)
	public long findBySqmRoleCount();
	
	@Query(value="select count(distinct u.id)  " + 
			"        from users u  " + 
			"       left join user_role ur on u.id=ur.id  " + 
			"       inner join sqm_allocation sqma on sqma.user_id=u.id  " +
			"       where ur.role_code='ROLE_SQM'  and u.status!='Deleted' and sqma.office_id in (?1)",nativeQuery=true)
	public long findBySqmRoleCountForSe(@Param("eeOficeIdList") List<Long> eeOficeIdList);
	
//Rakesh 
	@Query(value="select u.id, " + 
			//"u.name, " + 
			"       u.username, " + 
			"       u.email_id " + 
		
		
		
			"       from users u  " + 
		
			"       where  u.email_id=?1 and u.status!='Deleted'",nativeQuery=true)
	
	public List<Object[]> findBySqmEmailId(@Param("email_id") String email_id);

	Users findByOfficeAndIsOICAndStatusNot(Office office, short s, String string);

	
	//30-10-2019

	Users findByIdAndStatusNot(Long username, String statusDeleted);

	
	Users findByOfficeAndIsOICAndStatus(Office office, short s, String string);

	List<Users> findByDesignationAndStatus(Designation designation, String statusActive);

	/*List<Object[]> findOfficersNameAndDesigForInsp();*/
	
	@Query(value="select u.id,u.username,u.name,md.designation from users u left join mst_designation md on u.designation_id=md.id where u.designation_id in (1,2,3) and u.status='Active' ",nativeQuery=true) 
	public List<Object[]> findOfficersNameAndDesigForInsp();

	List<Users> findByStatus(String statusActive);

}
