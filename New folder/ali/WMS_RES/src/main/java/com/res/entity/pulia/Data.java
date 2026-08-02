package com.res.entity.pulia;

import javax.xml.bind.annotation.XmlRootElement;

import com.res.entity.sqm.OverallObservation;

@XmlRootElement
public class Data {

	private Group1 Group1;
	private Group2 Group2;
	private Group3 Group3;
	private Group4 Group4;
	private Group5 Group5;
	private Group6 Group6;
	private OverallObservation overallObservation;
	private Group7 Group7;
	private Meta Meta;

	public Data() {
	}

	public Data(com.res.entity.pulia.Meta meta,
			com.res.entity.pulia.Group1 group1,
			com.res.entity.pulia.Group2 group2,
			com.res.entity.pulia.Group3 group3,
			com.res.entity.pulia.Group4 group4,
			com.res.entity.pulia.Group5 group5,
			com.res.entity.pulia.Group6 group6,OverallObservation overallObservation,
			com.res.entity.pulia.Group7 group7) {
		super();
		this.Group1 = group1;
		this.Group2 = group2;
		this.Group3 = group3;
		this.Group4 = group4;
		this.Group5 = group5;
		this.Group6 = group6;
		this.overallObservation = overallObservation;
		this.Group7 = group7;
		this.Meta = meta;
	}

	public Group1 getGroup1() {
		return Group1;
	}

	public void setGroup1(Group1 group1) {
		Group1 = group1;
	}

	public Group2 getGroup2() {
		return Group2;
	}

	public void setGroup2(Group2 group2) {
		Group2 = group2;
	}

	public Group3 getGroup3() {
		return Group3;
	}

	public void setGroup3(Group3 group3) {
		Group3 = group3;
	}

	public Group4 getGroup4() {
		return Group4;
	}

	public void setGroup4(Group4 group4) {
		Group4 = group4;
	}

	public Group5 getGroup5() {
		return Group5;
	}

	public void setGroup5(Group5 group5) {
		Group5 = group5;
	}

	public Meta getMeta() {
		return Meta;
	}

	public void setMeta(Meta meta) {
		Meta = meta;
	}

	public Group6 getGroup6() {
		return Group6;
	}

	public void setGroup6(Group6 group6) {
		Group6 = group6;
	}

	public Group7 getGroup7() {
		return Group7;
	}

	public void setGroup7(Group7 group7) {
		Group7 = group7;
	}

	public OverallObservation getOverallObservation() {
		return overallObservation;
	}

	public void setOverallObservation(OverallObservation overallObservation) {
		this.overallObservation = overallObservation;
	}

}
