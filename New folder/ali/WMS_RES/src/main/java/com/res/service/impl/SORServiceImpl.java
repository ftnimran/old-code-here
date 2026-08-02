package com.res.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.res.bean.ChapterBean;
import com.res.bean.ItemBean;
import com.res.bean.SORBean;
import com.res.bean.StatusBean;
import com.res.bean.UnitBean;
import com.res.bean.YearBean;
import com.res.constants.RESConstants;
import com.res.entity.Chapter;
import com.res.entity.Contractor;
import com.res.entity.Item;
import com.res.entity.SOR;
import com.res.entity.Status;
import com.res.entity.Unit;
import com.res.entity.Year;
import com.res.exception.RESBusinessException;
import com.res.json.ChapterJson;
import com.res.json.ItemJson;
import com.res.json.SORJson;
import com.res.repository.ChapterRepository;
import com.res.repository.ItemRepository;
import com.res.repository.SORRepository;
import com.res.repository.StatusRepository;
import com.res.repository.UnitRepository;
import com.res.repository.YearRepository;
import com.res.service.SORService;
import com.res.util.RESUtil;

@Service
public class SORServiceImpl implements SORService {

	public static final Logger logger = LoggerFactory.getLogger(SORServiceImpl.class);

	@Autowired
	SORRepository sorRepository;
	
	@Autowired
	YearRepository yearRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	ChapterRepository chapterRepository;

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	UnitRepository unitRepository;

	@Override
	public SORJson getAllSORs(Pageable pageable, String searchBoxVal, String year) {
		SORJson sorJson = null;
		try {
			Page<SOR> work = null;
			long count = 0;
			if (!StringUtils.isEmpty(searchBoxVal)) {
				work = sorRepository.findAllSORs(pageable,searchBoxVal, searchBoxVal);
			}
			else {
				work = sorRepository.findAllSORs(pageable);
			}
			count = sorRepository.countAllSORs();
			if (work != null) {
				List<SOR> entityList = work.getContent();
				List<SORBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber()
							* pageable.getPageSize();
					for (SOR element : entityList) {

						SORBean bean = convertSOREntityToBean(element);
						
						bean.setIndex(++index);
						beanList.add(bean);
					}
				}
				sorJson = new SORJson();
				sorJson.setiTotalDisplayRecords(work
						.getTotalElements());
				sorJson.setiTotalRecords(count);
				sorJson.setAaData(beanList);
			}

			return sorJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return sorJson;
		}
	}

	public SORBean convertSOREntityToBean(SOR sor) {
		SORBean sorBean = new SORBean(); 
		sorBean.setId(sor.getId());
		sorBean.setReferenceName(sor.getReferenceName());
		sorBean.setStatus(sor.getStatus());
		sorBean.setYear(convertYearntityToBean(sor.getYear()));
		sorBean.setDateOfAdoption(RESUtil.convertDateToString(sor.getDateOfAdoption()) ) ;
		sorBean.setRemarks(sor.getRemarks());
		return sorBean;
	}

	@Override
	public String addSOR(SORBean sORBean) {
		try {
			SOR entity = new SOR();
			entity = convertSORBeanToEntityADD(entity, sORBean);
			entity.setCreatedDate(new Date());
			entity.setCreatedBy(sORBean.getActionPerformedBy());
			sorRepository.save(entity);
			return null;
		}catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}
	private SOR convertSORBeanToEntityADD(SOR entity, SORBean sORBean) throws RESBusinessException {
		entity.setDateOfAdoption(RESUtil.convertStringToDate(sORBean.getDateOfAdoption()));
		entity.setReferenceName(sORBean.getReferenceName());
		entity.setStatus(sORBean.getStatus());
		entity.setYear(new Year(Long.parseLong(sORBean.getYear().getYear())));	
		entity.setRemarks(sORBean.getRemarks());
		return entity;
	}
	
	private SOR convertSORBeanToEntity(SOR entity, SORBean sORBean) throws RESBusinessException {
		entity.setDateOfAdoption(RESUtil.convertStringToDate(sORBean.getDateOfAdoption()));
		entity.setReferenceName(sORBean.getReferenceName());
		entity.setStatus(sORBean.getStatus());
		entity.setYear(convertYearBeanToEntity(sORBean.getYear()));	
		entity.setRemarks(sORBean.getRemarks());
		return entity;
	}

	@Override
	public SORBean fetchSORBean(Long id) {
		SOR  entity = sorRepository.findOne(id);
		SORBean sorBean = convertSOREntityToBean(entity);
		sorBean.getYear().setYear(sorBean.getYear().getYear().substring(0, 4));
		return sorBean;
	}

	@Override
	public String updateSOR(SORBean sorBean) {
		try {
			SOR entity = new SOR();
			entity = convertSORBeanToEntity(entity, sorBean);
			entity.setId(sorBean.getId());
			entity.setModifiedDate(new Date());
			entity.setModifiedBy(sorBean.getActionPerformedBy());
			sorRepository.save(entity);
			return null;
		}catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	@Override
	public List<YearBean> fetchYears() {
		try {

			List<Year> list = yearRepository.findByOrderById();

			List<YearBean> beanList = new ArrayList<>();
			for (Year year : list) {
				beanList.add(convertYearntityToBean(year));
			}

			return beanList;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}
	private YearBean convertYearntityToBean(Year entity) {
		YearBean bean = new YearBean();
		if (entity != null) {
			bean.setId(entity.getId());
			bean.setYear(entity.getYear().substring(0, 4));
			bean.setEnabled(entity.getEnabled());
		}
		return bean;
	}
	
	private Year convertYearBeanToEntity(YearBean bean) {
		Year entity = new Year();
		entity.setId(bean.getId());
		entity.setYear(bean.getYear().substring(0, 4));
		entity.setEnabled(bean.getEnabled());
		return entity;
	}

	@Override
	public List<StatusBean> fetchStatus() {
		try {
			List<Status> list = statusRepository.findByOrderById();

			List<StatusBean> beanList = new ArrayList<>();
			for (Status status : list) {
				beanList.add(convertStatusEntityToBean(status));
			}

			return beanList;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	private StatusBean convertStatusEntityToBean(Status entity) {
		StatusBean bean = new StatusBean();
		if (entity != null) {
			bean.setId(entity.getId());
			bean.setStatusName(entity.getStatusName());
		}
		return bean;
	}
	@Override
	public ChapterJson getThisSORChapters(Pageable pageable, Long id) {
		ChapterJson sorJson = null;
		try {
				int maxLimit=(pageable.getPageSize());
				List<Object[]> entityList = chapterRepository.findAllChaptersBySORId(id,pageable.getOffset(),maxLimit);
			if (entityList != null) {
				List<ChapterBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber()
							* pageable.getPageSize();
					for (Object[] element : entityList) {
						ChapterBean chapterBean = new ChapterBean();
						populateChapterBeanFromObject(element, chapterBean);
						chapterBean.setIndex(++index);
						beanList.add(chapterBean);
						
					}
				}
				sorJson = new ChapterJson();
				
				sorJson.setiTotalDisplayRecords(chapterRepository.countChaptersBySORId(id));
				sorJson.setiTotalRecords(chapterRepository.countChaptersBySORId(id));
				sorJson.setAaData(beanList);
			}

			return sorJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return sorJson;
		}
	}
	private void populateChapterBeanFromObject(Object[] obj, ChapterBean chapterBean) {
		Integer intVal = (Integer) obj[0];
		chapterBean.setId(intVal.longValue());
		chapterBean.setChapterNumber((String) obj[1]);
		chapterBean.setChapterName(((String) obj[2]));
		chapterBean.setNumberOfItems(""+obj[3]);
		Integer intVal2 = (Integer) obj[4];
		chapterBean.setSorBean(new SORBean(intVal2.longValue()));
	}

	
	@Override
	public String addChapter(ChapterBean chapterBean) {
		try {
			Chapter entity = new Chapter();
			entity = convertChapterBeanToEntity(entity, chapterBean);
			entity.setCreatedDate(new Date());
			entity.setCreatedBy(chapterBean.getActionPerformedBy());
			chapterRepository.save(entity);
			return null;
		}catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}
	private Chapter convertChapterBeanToEntity(Chapter entity, ChapterBean chapterBean) {
		entity.setChapterName(chapterBean.getChapterName());
		entity.setChapterNumber(chapterBean.getChapterNumber());
		entity.setRemarks(chapterBean.getRemarks());	
		entity.setSor(new SOR(chapterBean.getSorBean().getId()));
		return entity;
	}

	@Override
	public ChapterBean fetchChapterRBean(Long id) {
		Chapter  entity = chapterRepository.findOne(id);
		return convertChapterEntityToBean(entity);
	}
	private ChapterBean convertChapterEntityToBean(Chapter entity) {
		ChapterBean chapterBean = new ChapterBean(); 
		chapterBean.setId(entity.getId());
		chapterBean.setChapterNumber(entity.getChapterNumber());
		chapterBean.setChapterName(entity.getChapterName());
		chapterBean.setRemarks(entity.getRemarks());
		chapterBean.setSorBean(convertSOREntityToBean(entity.getSor()));
		return chapterBean;
	}
	
	@Override
	public String updateChapter(ChapterBean chapterBean) {
		try {
			Chapter entity = new Chapter();
			entity = convertChapterBeanToEntity(entity, chapterBean);
			entity.setEnabled(true);
			entity.setId(chapterBean.getId());
			entity.setModifiedDate(new Date());
			entity.setModifiedBy(chapterBean.getActionPerformedBy());
			chapterRepository.save(entity);
			return null;
		}catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	/*@Override
	public ItemJson getThisChapterItem(Pageable pageable, Long chapter) {

		ItemJson sorJson = null;
		try {
			Page<Item> work = null;
			long count = 0;
			work = itemRepository.findItemsByChapterId(pageable, chapter);
			count = itemRepository.countItemsByChapterId(chapter);
			if (work != null) {
				List<Item> entityList = work.getContent();
				List<ItemBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber()
							* pageable.getPageSize();
					for (Item element : entityList) {
						
						if(beanList.size()<=pageable.getPageSize() && !beanList.contains(new ItemBean(element.getId()))) {
							index = setChildItemsInBeanList(beanList, index, element);
							
							if(null!= element.getItemList() && !element.getItemList().isEmpty() && beanList.size()<=pageable.getPageSize() && !beanList.contains(new ItemBean(element.getId()))) {
								for(Item element1 : element.getItemList()) {
									if(beanList.size()<=pageable.getPageSize() && !beanList.contains(new ItemBean(element.getId()))) {
										index=setChildItemsInBeanList(beanList, index, element1);
										
										if(null!= element1.getItemList() && !element1.getItemList().isEmpty() && beanList.size()<=pageable.getPageSize() && !beanList.contains(new ItemBean(element.getId()))) {
											for(Item element2 : element1.getItemList()) {
												if(beanList.size()<=pageable.getPageSize() && !beanList.contains(new ItemBean(element.getId()))) {
													index=setChildItemsInBeanList(beanList, index, element2);
													
													if(null!= element2.getItemList() && !element2.getItemList().isEmpty() && beanList.size()<=pageable.getPageSize() && !beanList.contains(new ItemBean(element.getId()))) {
														for(Item element3 : element1.getItemList()) {
															if(beanList.size()<=pageable.getPageSize() && !beanList.contains(new ItemBean(element.getId()))) {
																index=setChildItemsInBeanList(beanList, index, element3);
															}
														}
													}	
												}
											}
										}
									}
								}
							}
						}
					}
				}
				//beanList=beanList.subList(pageable.getPageNumber(), pageable.getPageSize());
				sorJson = new ItemJson();
				sorJson.setiTotalDisplayRecords(count);
				sorJson.setiTotalRecords(count);
				sorJson.setAaData(beanList);
			}

			return sorJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return sorJson;
		}
	
	}*/

	/*private int setChildItemsInBeanList(List<ItemBean> beanList, int index, Item element) {
		ItemBean bean = convertItemEntityToBean(element);
		
		bean.setIndex(++index);
		
		
		beanList.add(bean);
		return index;
	}*/
	
	@Override
	public ItemJson getThisChapterItem(Pageable pageable, Long chapter) {

		ItemJson sorJson = null;
		try {
			Page<Item> work = null;
			long count = 0;
			work = itemRepository.findItemsByChapterId(pageable, chapter);
			count = itemRepository.countItemsByChapterId(chapter);
			if (work != null) {
				List<Item> entityList = work.getContent();
				List<ItemBean> beanList = new ArrayList<>();
				if (entityList != null && !entityList.isEmpty()) {

					int index = pageable.getPageNumber()
							* pageable.getPageSize();
					for (Item element : entityList) {

						ItemBean bean = convertItemEntityToBean(element);
						
						bean.setIndex(++index);
						beanList.add(bean);
					}
				}
				sorJson = new ItemJson();
				sorJson.setiTotalDisplayRecords(work
						.getTotalElements());
				sorJson.setiTotalRecords(count);
				sorJson.setAaData(beanList);
			}

			return sorJson;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return sorJson;
		}
	
	}
	
	public ItemBean convertItemEntityToBean(Item item) {
		ItemBean itemBean = new ItemBean(); 
		itemBean.setId(item.getId());
		itemBean.setDescription(item.getDescription());
		itemBean.setItemNumber(item.getItemNumber());
		if(null!= item.getIsParent() && item.getIsParent().equals("N")) {
			itemBean.setUnit(convertUnitEntityToBean(item.getUnit()));
			itemBean.setRate(item.getRate()) ;
			if(null!= item.getUnit()) {
				itemBean.setHeightApplicable(item.getUnit().getHeightDepthApplicable());
				itemBean.setWidthApplicable(item.getUnit().getWidthApplicable());
				itemBean.setLengthApplicable(item.getUnit().getLengthApplicable());
			}
			
		}
		itemBean.setChapter(convertChapterEntityToBean(item.getChapter()));
		
		itemBean.setIsParent(item.getIsParent());
		if(item.getItem()!=null&&null!=item.getItem().getItemNumber()) {
			itemBean.setParentItemNumber(null==item.getItem()?"":item.getItem().getItemNumber());
		}
		if(item.getItem()!=null&&null!=item.getItem().getId()) {
			itemBean.setParentId(item.getItem().getId());
		}
		if(item.getItem()!=null) {
			itemBean.setParentDesc(item.getItem().getDescription());
		}
		itemBean.setSorType("USOR");
		
		return itemBean;
	}

	@Override
	public List<UnitBean> fetchUnit() {
		try {

			List<Unit> list = unitRepository.findAll();

			List<UnitBean> beanList = new ArrayList<>();
			for (Unit unit : list) {
				beanList.add(convertUnitEntityToBean(unit));
			}
			return beanList;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}
	
	@Override
	public UnitBean fetchUnitById(Long id) {
		try {

			Unit unit = unitRepository.findOne(id);

				UnitBean unitBean=convertUnitEntityToBean(unit);
			return unitBean;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}
	
	private UnitBean convertUnitEntityToBean(Unit entity) {
		UnitBean unitBean = new UnitBean();
		if (entity != null) {
			unitBean.setId(entity.getId());
			unitBean.setUnit(entity.getUnit());
			unitBean.setLengthApplicable(entity.getLengthApplicable());
			unitBean.setWidthApplicable(entity.getWidthApplicable());
			unitBean.setHeightDepthApplicable(entity.getHeightDepthApplicable());
		}
		return unitBean;
	}

	@Override
	public String addItem(ItemBean itemBean) {
		try {
			Item entity = new Item();
			entity = convertItemBeanToEntity(entity, itemBean);
			entity.setCreatedDate(new Date());
			entity.setCreatedBy(itemBean.getActionPerformedBy());
			Item updatedItem=itemRepository.save(entity);
			if(null==itemBean.getParentId()) {
				updatedItem.setParentId2(updatedItem.getId());
			}else {
				Item rootItem=findRootItem(itemBean.getParentId());
				if(null!=rootItem) {
					updatedItem.setParentId2(rootItem.getId());
				}
			}
			itemRepository.save(updatedItem);
			return null;
		}catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}
	
	private Item findRootItem(Long itemId) {
		Item item=null;
		if(null!= itemId) {
			item=itemRepository.findOne(itemId);
		}
		if(null!= item && null!= item.getItem()) {
			if(null!=item.getItem().getItem()) {
				if(null!=item.getItem().getItem().getItem()) {
					if(null==item.getItem().getItem().getItem().getItem()) {
						return item.getItem().getItem().getItem();
					}else {
						return item.getItem().getItem().getItem();
					}
				}else {
					return item.getItem().getItem();
				}
			}else {
				return item.getItem();
			}
		}else {
			return item;
		}
	}
	
	
	private Item convertItemBeanToEntity(Item entity, ItemBean itemBean){
		//entity.setItemNumber(null==itemBean.getSubItemNumber()?itemBean.getItemNumber():itemBean.getItemNumber()+"("+itemBean.getSubItemNumber()+")");
		entity.setItemNumber(null==itemBean.getSubItemNumber()?itemBean.getItemNumber():itemBean.getSubItemNumber());
		entity.setRate(itemBean.getRate());
		if(null!= itemBean.getUnit() && null!=itemBean.getUnit().getId()) {
			entity.setUnit(new Unit(itemBean.getUnit().getId()));
			entity.setLengthApplicable(itemBean.getLengthApplicable());
			entity.setHeightApplicable(itemBean.getHeightApplicable());
			entity.setWidthApplicable(itemBean.getWidthApplicable());
		}
		
		entity.setDescription(itemBean.getDescription());
		entity.setChapter(new Chapter(itemBean.getChapter().getId()));
		if(null!=itemBean.getParentId()) {
			entity.setItem(new Item(itemBean.getParentId()));
		}
			
		entity.setIsParent(itemBean.getIsParent());
		return entity;
	}

	@Override
	public ItemBean fetchItemBean(Long id) {
		Item  entity = itemRepository.findByItemId(id);
		return convertItemEntityToBean(entity);
	}

	@Override
	public ItemBean fetchSubItemBean(Long id) {
		Item  entity = itemRepository.findByItemId(id);
		return convertSubItemEntityToBean(entity);
	}
	private ItemBean convertSubItemEntityToBean(Item entity) {
		ItemBean itemBean = new ItemBean(); 
		itemBean.setId(null);
		itemBean.setDescription("");
		itemBean.setItemNumber(entity.getItemNumber());
		if(null!= entity.getIsParent() && entity.getIsParent().equals("N")) {
			itemBean.setUnit(convertUnitEntityToBean(entity.getUnit()));
			itemBean.setRate(entity.getRate());
			itemBean.setHeightApplicable(entity.getHeightApplicable());
			itemBean.setWidthApplicable(entity.getWidthApplicable());
			itemBean.setLengthApplicable(entity.getLengthApplicable());
		}
		itemBean.setChapter(convertChapterEntityToBean(entity.getChapter()));
		
		itemBean.setIsParent(entity.getIsParent());
		if(null!= entity.getId()) {
			itemBean.setParentId(entity.getId());
		}
		
		return itemBean;
}

	@Override
	public String updateItem(ItemBean itemBean) {
		try {
			Item entity = new Item();
			entity = convertItemBeanToEntity(entity, itemBean);
			entity.setId(itemBean.getId());
			//itemRepository.save(entity);
			
			entity.setModifiedDate(new Date());
			entity.setModifiedBy(itemBean.getActionPerformedBy());
			
			Item updatedItem=itemRepository.save(entity);
			if(null==itemBean.getParentId()) {
				updatedItem.setParentId2(updatedItem.getId());
			}else {
				Item rootItem=findRootItem(itemBean.getParentId());
				if(null!=rootItem) {
				updatedItem.setParentId2(rootItem.getId());
				}
			}
			itemRepository.save(updatedItem);
			
			return null;
		}catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}

	}

	/*@Override
	public String deleteItem(Long id) {

		try {
			Item entity = itemRepository.findOne(id);
			if (entity != null && entity.getIsParent().equals("N")) {
				entity.setStatus(RESConstants.STATUS_DELETED);
				itemRepository.save(entity);
			}else {
				return "Parent Item can not be deleted.";	
			}
			return null;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_DELETING_DATA;
		}
	}*/
	
	@Override
	public String deleteItem(Long id, String userId) {

		try {
			Item entity = itemRepository.findOne(id);
			if (entity != null) {
				entity.setStatus(RESConstants.STATUS_DELETED);
				entity.setModifiedBy(userId);
				entity.setModifiedDate(new Date());
				itemRepository.save(entity);
				
				List<Item> childItemList =itemRepository.findItemsByParentId(id);
				if(null!= childItemList && !childItemList.isEmpty()) {
					for(Item childItem : childItemList) {
						childItem.setStatus(RESConstants.STATUS_DELETED);
						childItem.setModifiedBy(userId);
						childItem.setModifiedDate(new Date());
						itemRepository.save(childItem);
						
						List<Item> childItemList1 =itemRepository.findItemsByParentId(childItem.getId());
						
						if(null!= childItemList1 && !childItemList1.isEmpty()) {
							for(Item childItem1 : childItemList1) {
								childItem1.setStatus(RESConstants.STATUS_DELETED);
								childItem1.setModifiedBy(userId);
								childItem1.setModifiedDate(new Date());
								itemRepository.save(childItem1);
								
								List<Item> childItemList2 =itemRepository.findItemsByParentId(childItem1.getId());
								
								if(null!= childItemList2 && !childItemList2.isEmpty()) {
									for(Item childItem2 : childItemList1) {
										childItem2.setStatus(RESConstants.STATUS_DELETED);
										childItem2.setModifiedBy(userId);
										childItem2.setModifiedDate(new Date());
										itemRepository.save(childItem2);
									}
								}
							}
						}
					}
				}
			}
			return null;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_DELETING_DATA;
		}
	}

	@Override
	public String addSubItem(ItemBean itemBean) {
		try {
			Item entity = new Item();
			entity = convertItemBeanToEntity(entity, itemBean);
			
			//itemRepository.save(entity);
			
			entity.setModifiedDate(new Date());
			entity.setModifiedBy(itemBean.getActionPerformedBy());
			
			Item updatedItem=itemRepository.save(entity);
			if(null==itemBean.getParentId()) {
				updatedItem.setParentId2(updatedItem.getId());
			}else {
				Item rootItem=findRootItem(itemBean.getParentId());
				if(null!=rootItem) {
				updatedItem.setParentId2(rootItem.getId());
				}
			}
			itemRepository.save(updatedItem);
			
			return null;
		}catch (Exception e) {
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_SAVING_DATA;
		}
	}

	@Override
	public List<SORBean> fetchAllSOR() {
		try {
			List<SOR> list = sorRepository.findAll();
			List<SORBean> beanList = new ArrayList<>();
			for (SOR sor : list) {
				if(sor.getStatus().getStatusName().equalsIgnoreCase("Active")) {
					beanList.add(convertSOREntityToBean(sor));
				}
			}
			return beanList;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	@Override
	public List<ChapterBean> fetchChaptersBySORId(Long id) {
		try {
			List<Chapter> list = chapterRepository.findBySor_id(id);
			List<ChapterBean> beanList = new ArrayList<>();
			for (Chapter chapter : list) {
				beanList.add(convertChapterEntityToBean(chapter));
			}
			return beanList;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	@Override
	public List<ItemBean> fetchItemsByChapterId(Long id) {
		try {
			List<Item> list = itemRepository.findByChapterId(id);
			List<ItemBean> beanList = new ArrayList<>();
			for (Item item : list) {
				if(null!=item.getStatus()) {
					if(!item.getStatus().equalsIgnoreCase("deleted")) {
						beanList.add(convertItemEntityToBean(item));
					}
				}else {
					beanList.add(convertItemEntityToBean(item));
				}
			}
			return beanList;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		}
	}

	@Override
	public boolean isSorItemChildExists(Long id) {
		Long itemCount=itemRepository.countChildItemsByParentId(id);
		if(itemCount>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isSorChapterItemExists(Long id) {
		Long itemCount=itemRepository.countItemsByChapterId(id);
		if(itemCount>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isSorChapterExists(Long id) {
		Long itemCount=chapterRepository.countChaptersBySORId(id);
		if(itemCount>0) {
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String deleteChapter(Long id, String deletedBy) {

		try {
			   itemRepository.deleteItemsByChapterId(id, deletedBy);
			   
			   Chapter entity=chapterRepository.findOne(id);
			
				entity.setModifiedBy(deletedBy);
				entity.setModifiedDate(new Date());
				entity.setEnabled(false);
				chapterRepository.save(entity);
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("An exception occurred.", e);
			return RESConstants.ERROR_DELETING_DATA;
		}
	}
	
	@Override
    public List<ItemBean> fetchItemsByYearChapterIdItemNoOrName(Long chapterId, String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            return Collections.emptyList();
        }
        try {
        	
			List<Item> list = itemRepository.findByChapterIdAndItemNoOrName(chapterId, searchText.trim());
			List<ItemBean> beanList = new ArrayList<>();
			for (Item item : list) {
				if(null!=item.getStatus()) {
					if(!item.getStatus().equalsIgnoreCase("deleted")) {
						beanList.add(convertItemEntityToBean(item));
					}
				}else {
					beanList.add(convertItemEntityToBean(item));
				}
			}
			return beanList;
		} catch (Exception e) {
			logger.error("An exception occurred.", e);
			return null;
		} 
    }
}
