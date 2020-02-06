package com.board.springboot.service;

import java.util.List;

import com.board.springboot.dao.HibTest;
import com.board.springboot.dao.KeywordSort;
import com.board.springboot.dao.StoreInfoVO;

public interface KeywordService {
	
	public List<HibTest> selectKeywordList();
	public List<StoreInfoVO> selectSpecificKeywordList(String keyword);
	public List<KeywordSort> selectMajorInit();
	public List<KeywordSort> selectSmallInit(int keywordRef);
	public List<StoreInfoVO> selectInfoByCode(int keywordCode);
	public List<StoreInfoVO> selectStoreByWord(String word);
}
