package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.Chapter;


public interface ChapterRepository  extends JpaRepository<Chapter, Long>{
	
	 @Query(value="SELECT c.id, c.chapter_number, c.chapter_name, (SELECT COUNT(i.id) FROM sor_item i WHERE chapter_id=c.id and (i.status!='deleted' OR i.status IS NULL)), c.sor_id FROM sor_chapter c where c.sor_id =?1  "
	 		+ "order by c.id asc limit ?2,?3 ", nativeQuery=true) 
	 List<Object[]> findAllChaptersBySORId(@Param("id") Long id,@Param("offset") int offset, @Param("maxLimit") int maxLimit);

	
	@Query("select count(*) from Chapter c where c.sor.id=:id")
	long countChaptersBySORId(@Param("id") Long id);


	List<Chapter> findBySor_id(Long id);


}
