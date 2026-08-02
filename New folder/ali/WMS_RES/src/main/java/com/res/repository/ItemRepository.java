package com.res.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.bean.ItemBean;
import com.res.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query("from Item i where i.chapter.id=:chapter and (i.status!='Deleted' OR i.status IS NULL) order by i.parentId2, i.id ASC")
	Page<Item> findItemsByChapterId(Pageable pageable, @Param("chapter") Long chapter);

	@Query("select count(*) from Item i where i.chapter.id=:chapter and (i.status!='Deleted' OR i.status IS NULL)  ")
	long countItemsByChapterId(@Param("chapter") Long chapter);
	
	@Query("from Item i where i.chapter.id=:id and (i.status!='Deleted' OR i.status IS NULL) order by i.id ASC")
	List<Item> findByChapterId(@Param("id") Long id);
	
	@Query("from Item i where i.id=:id")
	Item findByItemId(@Param("id") Long id);
	
	@Query("from Item i where i.chapter.id=:chapter and (i.status!='Deleted' OR i.status IS NULL) and i.item.id IS NULL order by i.id ASC")
	Page<Item> findParentItemsByChapterId(Pageable pageable, @Param("chapter") Long chapter);
	
	@Query("select count(*) from Item i where i.item.id=:id and (i.status!='Deleted' OR i.status IS NULL)")
	long countChildItemsByParentId(@Param("id") Long id);
	
	
	@Transactional
	@Modifying
	@Query("update Item i set i.status='Deleted', i.modifiedBy=:deletedBy, i.modifiedDate=now() where i.chapter.id=:id and (i.status!='Deleted' OR i.status IS NULL)")
	void deleteItemsByChapterId(@Param("id") Long id, @Param("deletedBy") String deletedBy);
	
	@Query("from Item i where i.item.id=:id and (i.status!='Deleted' OR i.status IS NULL)")
	List<Item> findItemsByParentId(@Param("id") Long id);

	 @Query("SELECT s FROM Item s WHERE s.chapter.id = :chapterId AND " +
	           "(LOWER(s.itemNumber) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
	           "LOWER(s.description) LIKE LOWER(CONCAT('%', :searchText, '%')))")
	    List<Item> findByChapterIdAndItemNoOrName(@Param("chapterId") Long chapterId,
	                                                 @Param("searchText") String searchText);
	
}
