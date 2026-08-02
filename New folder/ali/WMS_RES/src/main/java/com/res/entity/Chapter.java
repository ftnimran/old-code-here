package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "sor_chapter")
public class Chapter extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@NotNull
	@Column(name = "chapter_number")
    private String chapterNumber;
	
    @NotNull
    @Column(name = "chapter_name")
	private String chapterName;
	
	@Column(name = "remarks")
	private String remarks;
	
	@ManyToOne
    @JoinColumn(name = "sor_id", referencedColumnName = "ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private SOR sor;
	
	@Column(name = "enabled")
    private Boolean enabled;

	
    public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public Chapter() {
	}
    

	public Chapter(Long id) {
		this.id=id;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getChapterNumber() {
		return chapterNumber;
	}


	public void setChapterNumber(String chapterNumber) {
		this.chapterNumber = chapterNumber;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public SOR getSor() {
		return sor;
	}


	public void setSor(SOR sor) {
		this.sor = sor;
	}


	public String getChapterName() {
		return chapterName;
	}


	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

}
