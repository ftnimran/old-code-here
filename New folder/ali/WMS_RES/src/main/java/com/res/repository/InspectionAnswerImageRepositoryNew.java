package com.res.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.bean.InspectionAnswerImageBean;
import com.res.entity.Bill;
import com.res.entity.InspectionAnswerImage;
import com.res.entity.InspectionAnswerImageNew;
import com.res.entity.InspectionDetails;
import com.res.entity.Users;
import com.res.entity.Work;

public interface InspectionAnswerImageRepositoryNew  extends JpaRepository<InspectionAnswerImageNew, Long>, CrudRepository<InspectionAnswerImageNew, Long> {
    
	public List<InspectionAnswerImageNew> findByBill(Bill bill);
	
	@Query(
			  "from InspectionAnswerImageNew ia " +
			  "where ia.bill = :bill " +
			  "and (ia.imageByRole is null " +
			  "     or ia.imageByRole in ('ROLE_AE', 'ROLE_SUB_ENGG', 'ROLE_SDO', 'ROLE_EE'))"
			)
	public List<InspectionAnswerImageNew> findInspectionAnswersImageForAEOrSubEnggorEEorSDOByBill(@Param("bill") Bill bill);
	
	@Query(
			  "from InspectionAnswerImageNew ia " +
			  "where ia.inspection.id = :inspectionId " +
			  "and (ia.imageByRole is null " +
			  "     or ia.imageByRole in ('ROLE_AE', 'ROLE_SUB_ENGG', 'ROLE_SDO', 'ROLE_EE', 'ROLE_SQM'))"
			)
	public List<InspectionAnswerImageNew> findInspectionAnswersImageForAEOrSubEnggorEEorSDOByInspectionId(@Param("inspectionId") Long inspectionId);
	

	
	@Query("from InspectionAnswerImage ia where ia.bill= :bill and ia.imageByRole = 'ROLE_EE'")
	public List<InspectionAnswerImage> findInspectionAnswersImageForEEByBill(@Param("bill") Bill bill);
	
	
	/*
	 * @Query("from InspectionAnswerImageNew ia where ia.bill= :bill and (ia.imageByRole is null or ia.imageByRole in ('ROLE_AE', 'ROLE_SUB_ENGG', 'ROLE_SDO'))"
	 * ) public List<InspectionAnswerImageNew>
	 * findInspectionAnswersImageNewForAEOrSubEnggByBill(@Param("bill") Bill bill);
	 */
	@Query("from InspectionAnswerImageNew ia where ia.bill= :bill and ia.imageByRole = 'ROLE_EE'")
	public List<InspectionAnswerImageNew> findInspectionAnswerImageNewForEEByBill(@Param("bill") Bill bill);

	

	@Query("from InspectionAnswerImageNew ia where ia.inspection.id = :inspectionId")
	List<InspectionAnswerImageNew> findByInspectionId(@Param("inspectionId") Long inspectionId);


	/*public List<Object[]> findByStatusIdAndEnabledNotOrderByModifiedDateDescByQueryEE(long l, boolean b,
			Long loggedInOfficeId, int offset, int maxLimit);
	*/
	
	
/*	@Query("from Work w where (w.status is null or w.status!='Deleted') and (:workName is null or w.workName like %:workName%) and w.workTypeId.workTypeId=COALESCE(:workType, w.workTypeId.workTypeId) and (w.workSubTypeId.workSubTypeId is null or w.workSubTypeId.workSubTypeId=COALESCE(:workSubType, w.workSubTypeId.workSubTypeId)) "
			+ "and w.executiveEngineerOffice = :executiveEngineerOffice and w.workRequestStatusId.id>=2 and w.isLegacy=0")
	Page<Work> findPendingWorkEstimationsByExecutiveEngineerOffice(
			Pageable pageable, @Param("workName") String workName,
			@Param("workType") Long workType,
			@Param("workSubType") Long workSubType,
			@Param("executiveEngineerOffice") Office executiveEngineerOffice);*/
	@Query("from InspectionAnswerImageNew ia where ia.bill.id= :billId and ia.imageBy.id=COALESCE(:inspectedBy, ia.imageBy.id)")
	public List<InspectionAnswerImageNew> findDocumentId(@Param("billId") Long billId, @Param("inspectedBy") Long inspectedBy);

	@Query("from InspectionAnswerImageNew ia where ia.imageBy= :image_by and ia.work = :work and ia.image_date_time_random = :image_date_time_random")
	public List<InspectionAnswerImageNew> findByUserIdWorkAndTime(@Param("image_by")Users users, @Param("work")Work work, @Param("image_date_time_random") Date time);

	
 
	
	
	
	
	
}
