package com.board.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.board.springboot.dao.HibTest;
import com.board.springboot.dao.KeywordSort;
import com.board.springboot.dao.StoreInfoVO;
import com.board.springboot.repository.KeywordRepository;
import com.board.springboot.repository.SortRepository;
import com.board.springboot.repository.StoreRepository;

@Service
public class KeywordServiceImpl implements KeywordService {

	@Autowired
	KeywordRepository keywordRepository;
	@Autowired
	SortRepository sortRepository;
	@Autowired
	StoreRepository storeRepository;
	private static final Logger logger = LoggerFactory.getLogger(KeywordServiceImpl.class);
	
	@Override
	public List<HibTest> selectKeywordList() {
		return keywordRepository.findAll();
	}
	
	@Override
	public List<KeywordSort> selectMajorInit() { 
		return sortRepository.findByKeywordRefNull();
	}
	
	@Override
	public List<KeywordSort> selectSmallInit(int keywordRef) { 
		return sortRepository.findByKeywordRef(keywordRef);
	}
	
	@Override
	public List<StoreInfoVO> selectInfoByCode(int keywordCode) { 
		return storeRepository.findByKeywordCode(keywordCode);
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<StoreInfoVO> selectSpecificKeywordList(String keyword) {
		logger.info("specific service");
		
//		if(detail!=null) {
//		}
		
		List<KeywordSort> tmp = sortRepository.findByKeywordName(keyword);
		int keywordCode = tmp.get(0).getKeywordCode();
		Integer keywordRef = tmp.get(0).getKeywordRef(); 
		
		logger.info(keywordCode + "---"+ keywordRef);
		
		return storeRepository.findByKeywordCode(keywordCode);
	}

}
