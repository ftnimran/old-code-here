/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class StandardTemplateTypeBean {

    private Long id;

    private String typeDesc;
    
    private String typeDescH;

    private Short enabled;
    
    public StandardTemplateTypeBean() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getTypeDescH() {
		return typeDescH;
	}

	public void setTypeDescH(String typeDescH) {
		this.typeDescH = typeDescH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
}
