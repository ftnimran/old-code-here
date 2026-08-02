package com.res.entity.talab;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group2 {
	private String karyaName;
	private String yojnaName;
	private String prashaskiyaSwikratRashi;
	private String swikratVarsh;
	private String vartmanstithi;

	public Group2() {

	}

	public Group2(String karyaName, String yojnaName,
			String prashaskiyaSwikratRashi, String swikratVarsh,
			String vartmanstithi) {
		super();
		this.karyaName = karyaName;
		this.yojnaName = yojnaName;
		this.prashaskiyaSwikratRashi = prashaskiyaSwikratRashi;
		this.swikratVarsh = swikratVarsh;
		this.vartmanstithi = vartmanstithi;
	}

	public String getKaryaName() {
		return karyaName;
	}

	public void setKaryaName(String karyaName) {
		this.karyaName = karyaName;
	}

	public String getYojnaName() {
		return yojnaName;
	}

	public void setYojnaName(String yojnaName) {
		this.yojnaName = yojnaName;
	}

	public String getPrashaskiyaSwikratRashi() {
		return prashaskiyaSwikratRashi;
	}

	public void setPrashaskiyaSwikratRashi(String prashaskiyaSwikratRashi) {
		this.prashaskiyaSwikratRashi = prashaskiyaSwikratRashi;
	}

	public String getSwikratVarsh() {
		return swikratVarsh;
	}

	public void setSwikratVarsh(String swikratVarsh) {
		this.swikratVarsh = swikratVarsh;
	}

	public String getVartmanstithi() {
		return vartmanstithi;
	}

	public void setVartmanstithi(String vartmanstithi) {
		this.vartmanstithi = vartmanstithi;
	}
}