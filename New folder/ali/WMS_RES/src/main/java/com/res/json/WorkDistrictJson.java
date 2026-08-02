package com.res.json;

import java.util.List;

import com.res.bean.DashboardBean;

public class WorkDistrictJson {

	long iTotalRecords;

	long iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<DashboardBean> aaData;

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

	public List<DashboardBean> getAaData() {
		return aaData;
	}

	public void setAaData(List<DashboardBean> aaData) {
		this.aaData = aaData;
	}

}
