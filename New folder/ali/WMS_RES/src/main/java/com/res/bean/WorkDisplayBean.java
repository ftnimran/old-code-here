package com.res.bean;

public class WorkDisplayBean{
	
	private WorkBean workBean;

	private TechnicalSanctionBean technicalSanctionBean;
	
	private AdministrationSanctionBean administrationSanctionBean;
	
	public WorkDisplayBean() {
	}

	public WorkBean getWorkBean() {
		return workBean;
	}

	public void setWorkBean(WorkBean workBean) {
		this.workBean = workBean;
	}

	public TechnicalSanctionBean getTechnicalSanctionBean() {
		return technicalSanctionBean;
	}

	public void setTechnicalSanctionBean(TechnicalSanctionBean technicalSanctionBean) {
		this.technicalSanctionBean = technicalSanctionBean;
	}

	public AdministrationSanctionBean getAdministrationSanctionBean() {
		return administrationSanctionBean;
	}

	public void setAdministrationSanctionBean(
			AdministrationSanctionBean administrationSanctionBean) {
		this.administrationSanctionBean = administrationSanctionBean;
	}
	
}
