package com.res.entity.talab;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group4 {
	
	private String khudai;
    private String paddal;
    private String bolder;
    private String hurting;
    private String lengthBreadth;
    private String hurtingWork;
    
    public Group4() {}
	
    public Group4(String khudai, String paddal, String bolder, String hurting,
			String lengthBreadth, String hurtingWork) {
		super();
		this.khudai = khudai;
		this.paddal = paddal;
		this.bolder = bolder;
		this.hurting = hurting;
		this.lengthBreadth = lengthBreadth;
		this.hurtingWork = hurtingWork;
	}
	public String getKhudai() {
		return khudai;
	}
	public void setKhudai(String khudai) {
		this.khudai = khudai;
	}
	public String getPaddal() {
		return paddal;
	}
	public void setPaddal(String paddal) {
		this.paddal = paddal;
	}
	public String getBolder() {
		return bolder;
	}
	public void setBolder(String bolder) {
		this.bolder = bolder;
	}
	public String getHurting() {
		return hurting;
	}
	public void setHurting(String hurting) {
		this.hurting = hurting;
	}
	public String getLengthBreadth() {
		return lengthBreadth;
	}
	public void setLengthBreadth(String lengthBreadth) {
		this.lengthBreadth = lengthBreadth;
	}
	public String getHurtingWork() {
		return hurtingWork;
	}
	public void setHurtingWork(String hurtingWork) {
		this.hurtingWork = hurtingWork;
	}
    
    
    
}
