package com.res.bean;

import java.util.List;

public class DistrictBean {
	
	private Long districtId;

	private String districtCode;
	
	private String lgdDistrictCode;
	
	private String districtName;

	private String districtNameH;
	
	private Short enabled;

	private StateBean state;
	//new code for list of work bean
	List<WorkBean> workBeans;
	
	private Integer count;
	

	public List<WorkBean> getWorkBeans() {
		return workBeans;
	}

	public void setWorkBeans(List<WorkBean> workBeans) {
		this.workBeans = workBeans;
	}

	public DistrictBean() {
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictNameH() {
		return districtNameH;
	}

	public void setDistrictNameH(String districtNameH) {
		this.districtNameH = districtNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public StateBean getState() {
		return state;
	}

	public void setState(StateBean state) {
		this.state = state;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getLgdDistrictCode() {
		return lgdDistrictCode;
	}

	public void setLgdDistrictCode(String lgdDistrictCode) {
		this.lgdDistrictCode = lgdDistrictCode;
	}

	
	
}
