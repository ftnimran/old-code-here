package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpStreamGrading {
	
	private String upStreamGrad;
    private String upStreamReasons;
    
public UpStreamGrading() {
		
	}
	
	
	public UpStreamGrading(String upStreamGrad,String upStreamReasons) {
		super();
		this.upStreamGrad = upStreamGrad;
		this.upStreamReasons = upStreamReasons;
	}


	public String getUpStreamGrad() {
		return upStreamGrad;
	}


	public void setUpStreamGrad(String upStreamGrad) {
		this.upStreamGrad = upStreamGrad;
	}


	public String getUpStreamReasons() {
		return upStreamReasons;
	}


	public void setUpStreamReasons(String upStreamReasons) {
		this.upStreamReasons = upStreamReasons;
	}

}
