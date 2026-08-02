package com.res.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MST_BLOCK_NEW")
public class Block implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "ID")
	private Long blockId;

	@Column(name = "DISTRICT_CODE")
	private String districtCode;
	
	@Column(name = "BLOCK_CODE")
	private String blockCode;
	
	@Column(name = "BLOCK_NAME")
	private String blockName;

	@Column(name = "BLOCK_NAME_H")
	private String blockNameH;
	
	@Column(name = "ENABLED")
	private Short enabled;
	
	@Column(name = "type")
	private String type;

	/*@JoinColumn(name = "DISTRICT_ID", referencedColumnName = "DISTRICT_ID")
	@ManyToOne
	private District district;*/

	public Block() {
	}

	public Block(Long blockId) {
		this.blockId = blockId;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getBlockNameH() {
		return blockNameH;
	}

	public void setBlockNameH(String blockNameH) {
		this.blockNameH = blockNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
