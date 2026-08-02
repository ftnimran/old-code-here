package com.res.json;

import java.util.List;

import com.res.bean.WorkTenderBean;


public class WorkTenderJson {
	
	
	long iTotalRecords;
	
	long iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<WorkTenderBean> aaData;

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<WorkTenderBean> getAaData() {
		return aaData;
	}

	public void setAaData(List<WorkTenderBean> aaData) {
		this.aaData = aaData;
	}

	
	

}
