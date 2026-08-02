package com.res.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.res.bean.ChapterBean;
import com.res.bean.ItemBean;
import com.res.bean.SORBean;
import com.res.bean.StatusBean;
import com.res.bean.UnitBean;
import com.res.bean.YearBean;
import com.res.entity.Item;
import com.res.json.ChapterJson;
import com.res.json.ItemJson;
import com.res.json.SORJson;

public interface SORService {

	SORJson getAllSORs(Pageable pageable, String searchBoxVal, String year);

	String addSOR(SORBean sORBean);

	SORBean fetchSORBean(Long id);

	String updateSOR(SORBean sorBean);

	List<YearBean> fetchYears();

	List<StatusBean> fetchStatus();

	ChapterJson getThisSORChapters(Pageable pageable, Long id);

	String addChapter(ChapterBean chapterBean);

	ChapterBean fetchChapterRBean(Long id);

	String updateChapter(ChapterBean chapterBean);

	ItemJson getThisChapterItem(Pageable pageable, Long chapter);

	List<UnitBean> fetchUnit();

	String addItem(ItemBean itemBean);

	ItemBean fetchItemBean(Long id);

	String updateItem(ItemBean itemBean);

	String deleteItem(Long id, String userId);

	ItemBean fetchSubItemBean(Long id);

	String addSubItem(ItemBean itemBean);

	List<SORBean> fetchAllSOR();

	List<ChapterBean> fetchChaptersBySORId(Long id);

	List<ItemBean> fetchItemsByChapterId(Long id);
	
	public UnitBean fetchUnitById(Long id);

	boolean isSorItemChildExists(Long id);

	boolean isSorChapterItemExists(Long id);
	
	boolean isSorChapterExists(Long id);
	
	String deleteChapter(Long id, String username);

	List<ItemBean> fetchItemsByYearChapterIdItemNoOrName(Long chapterId, String searchText);

}
