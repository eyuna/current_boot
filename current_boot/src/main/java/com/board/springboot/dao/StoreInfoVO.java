package com.board.springboot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.querydsl.core.annotations.QueryProjection;

@Entity
@Table(name = "store_info")
public class StoreInfoVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idx")
	private int idx;
	@Column(name="store_name")
	private String storeName;
	@Column(name="adr_dong")
	private String adrDong;
	@Column(name="adr_full")
	private String adrFull;
	@Column(name="keyword_code")
	private Integer keywordCode;

	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAdrDong() {
		return adrDong;
	}
	public void setAdrDong(String adrDong) {
		this.adrDong = adrDong;
	}
	public String getAdrFull() {
		return adrFull;
	}
	public void setAdrFull(String adrFull) {
		this.adrFull = adrFull;
	}
	public Integer getKeywordCode() {
		return keywordCode;
	}
	public void setKeywordCode(Integer keywordCode) {
		this.keywordCode = keywordCode;
	}
	

}
