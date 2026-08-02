package com.res.json;

import java.util.List;

import com.res.bean.WorkEstimationBean;

public class WorkEstimationJson {

	long iTotalRecords;

	long iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<WorkEstimationBean> aaData;

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

	public List<WorkEstimationBean> getAaData() {
		return aaData;
	}

	public void setAaData(List<WorkEstimationBean> aaData) {
		this.aaData = aaData;
	}

}
