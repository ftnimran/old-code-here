package com.res.service;

import java.util.Map;

import com.res.bean.InspectionChecklistBean;
import com.res.bean.InspectionRequestBean;
import com.res.entity.DataJson;

public interface InspectionService {

    InspectionChecklistBean getInspectionChecklist(Integer workTypeId);
    
   // Map<String, String> saveOrUpdateInspection(InspectionRequestBean request);

    public Long saveOrUpdateInspection(InspectionRequestBean data);

}

