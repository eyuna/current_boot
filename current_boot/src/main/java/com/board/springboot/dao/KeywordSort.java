package com.board.springboot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keyword_sort")
public class KeywordSort {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="keyword_name")
	private String keywordName;
	@Column(name="keyword_code")
	private int keywordCode;
	@Column(name="keyword_ref", nullable=true)
	private Integer keywordRef;
	
	public String getKeywordName() {
		return keywordName;
	}
	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}
	public int getKeywordCode() {
		return keywordCode;
	}
	public void setKeywordCode(int keywordCode) {
		this.keywordCode = keywordCode;
	}
	public Integer getKeywordRef() {
		return keywordRef;
	}
	public void setKeywordRef(Integer keywordRef) {
		this.keywordRef = keywordRef;
	}

}
