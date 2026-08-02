package com.res.service;

import java.util.List;

import com.res.bean.BlockBean;
import com.res.bean.GramPanchayatBean;
import com.res.bean.KmlFilePoints;
import com.res.bean.VillageBean;
import com.res.bean.WorkBean;


public interface EeService {

	String addWork(WorkBean workBean);

//	WorkJson getAllWorks(Pageable pageable);
	
	String deleteWork(Long id);
	
	//WorkBean fetchWorkDetails(Long id);

	/** CR-RESOWMS/CR/1-1
	 * Work Transfer Module-Transfer Work to Other Office
	 * @param workBean
	 * @return String
	 */
	String editWork(WorkBean workBean);
	
	/*String editWorkRevise(WorkBean workBean);*/
	
	String addRequisitionWork(WorkBean workBean);
	
	String editRequisitionWork(WorkBean workBean);

	List<KmlFilePoints> processKmlFile(KmlFilePoints bean);

	VillageBean fetchVillageByVCode(Long long1);

	List<GramPanchayatBean> fetchGramPanchayatByGPCode(Long gpCode);

	GramPanchayatBean fetchLgdGpCode(Long gpId);

	BlockBean fetchblockCode(Long blockId);
	
}
