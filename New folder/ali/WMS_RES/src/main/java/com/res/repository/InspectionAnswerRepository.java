package com.res.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.Bill;
import com.res.entity.InspectionAnswer;
import com.res.entity.InspectionAnswersNew;
import com.res.entity.Users;
import com.res.entity.Work;

public interface InspectionAnswerRepository  extends JpaRepository<InspectionAnswer, Long>, CrudRepository<InspectionAnswer, Long> {
    
	public List<InspectionAnswer> findByBill(Bill bill);
	
	@Query("from InspectionAnswer ia where ia.bill= :bill and (ia.answerByRole is null or ia.answerByRole in ('ROLE_AE', 'ROLE_SUB_ENGG', 'ROLE_SDO'))")
	public List<InspectionAnswer> findInspectionAnswersForAEOrSubEnggByBill(@Param("bill") Bill bill);
	
	@Query("from InspectionAnswer ia where ia.bill= :bill and ia.answerByRole = 'ROLE_EE'")
	public List<InspectionAnswer> findInspectionAnswersForEEByBill(@Param("bill") Bill bill);

	public List<InspectionAnswer> findByBillAndAnswerByRole(Bill bill, String role);

	public List<InspectionAnswer> findByBillAndAnswerBy(Bill bill, Users users);

	@Query("from InspectionAnswer ia where ia.answerBy= :answer_by and ia.work = :work")
	public List<InspectionAnswer> findDistinctRandomInspectionDateTime(@Param("answer_by")Users users, @Param("work")Work work); 
	
	@Query("from InspectionAnswer ia where ia.answerBy= :answer_by and ia.work = :work and ia.randomInspectionDate_time = :time")
	public List<InspectionAnswer> findAllRandomInspectionDateTime(@Param("answer_by")Users users, @Param("work")Work work, @Param("time")Timestamp time); 


	public List<InspectionAnswer> findDistinctByWorkAndAnswerBy(Work work,Users users);
	
	@Query("from InspectionAnswer ia where ia.work= :work and ia.answerBy = :answer_by  group by random_inspection_date_time order by id desc")
	public List<InspectionAnswer> findByWorkAndAnswerBy(@Param("work")Work work,@Param("answer_by")Users users);

	@Query("from InspectionAnswer ia where ia.answerBy= :answer_by and ia.work = :work and code = 'PhysicalStage' and ia.randomInspectionDate_time = :time")
	public List<InspectionAnswer> findByAnswerByAndWorkWithPhysicalStage( @Param("answer_by")Users users, @Param("work")Work work, @Param("time")Timestamp time);
	

	@Query("from InspectionAnswer ia where ia.answerBy= :answer_by and ia.work = :work and ia.randomInspectionDate_time = :time")
	public List<InspectionAnswer> findByAnswerByAndWorkAndTime( @Param("answer_by")Users users, @Param("work")Work work, @Param("time") Date time);

	@Query("from InspectionAnswer ia where ia.id= :id")
	public InspectionAnswer findById( @Param("id")Long id);

	public List<InspectionAnswer> findByBillAndCode(Bill billEntity, String code); 
	
	
	
	/*
	 * @Query("from InspectionAnswer ia where ia.bill= :bill and (ia.answerByRole is null or ia.answerByRole in ('ROLE_AE', 'ROLE_SUB_ENGG', 'ROLE_SDO'))"
	 * ) public List<InspectionAnswersNew>
	 * findInspectionAnswersForAEOrSubEnggByBill_new(@Param("bill") Bill bill);
	 * 
	 * @Query("from InspectionAnswer ia where ia.bill= :bill and ia.answerByRole = 'ROLE_EE'"
	 * ) public List<InspectionAnswersNew>
	 * findInspectionAnswersForEEByBill_new(@Param("bill") Bill bill);
	 * 
	 * public List<InspectionAnswersNew> findByBillAndAnswerByRole_new(Bill bill,
	 * String role);
	 * 
	 * public List<InspectionAnswersNew> findByBillAndAnswerBy_new(Bill bill, Users
	 * users);
	 * 
	 * @Query("from InspectionAnswer ia where ia.answerBy= :answer_by and ia.work = :work"
	 * ) public List<InspectionAnswersNew>
	 * findDistinctRandomInspectionDateTime_new(@Param("answer_by")Users
	 * users, @Param("work")Work work);
	 * 
	 * @Query("from InspectionAnswer ia where ia.answerBy= :answer_by and ia.work = :work and ia.randomInspectionDate_time = :time"
	 * ) public List<InspectionAnswersNew>
	 * findAllRandomInspectionDateTime_new(@Param("answer_by")Users
	 * users, @Param("work")Work work, @Param("time")Timestamp time);
	 */


 
	
}
