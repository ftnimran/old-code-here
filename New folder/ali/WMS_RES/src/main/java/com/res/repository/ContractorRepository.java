package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {

	List<Contractor> findByEnabled(Short isEnabled);

	Page<Contractor> findByEnabled(Pageable pageable, Short isEnabled);
	
	@Query("from Contractor c where c.enabled=:enabled and c.name like %:name%")
	List<Contractor> findByEnabledAndName(@Param("enabled") Short enabled,
			@Param("name") String name);

	/*List<Object[]> findAllContractor();*/
	
    @Query(value = "select c.id, c.name, c.father_name, c.address, c.registration_number, c.contractor_id, c.pan, c.gstin, c.email_id, c.contact_no "
    		+ "from work w left join contractor c on w.contractor_id=c.id where w.contractor_id is not null and c.user_status=0 "
    		+ "UNION DISTINCT "
    		+ "select c.id,c.name, c.father_name, c.address, c.registration_number, c.contractor_id, c.pan, c.gstin, c.email_id, c.contact_no "
    		+ "from work_tender w left join contractor c on w.contractor_id=c.id where w.contractor_id is not null and c.user_status=0", nativeQuery = true)
	List<Object[]> findAllContractor();

}
