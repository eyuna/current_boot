package com.board.springboot.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.springboot.dao.HibTest;
import com.board.springboot.dao.KeywordSort;
import com.board.springboot.dao.StoreInfoVO;
import com.board.springboot.service.KeywordService;
import com.board.springboot.service.KeywordServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/keyword")
public class KeywordController {
	
	@Autowired
	KeywordService keywordService;
	private static final Logger logger = LoggerFactory.getLogger(KeywordController.class);

	@RequestMapping(value="", method = RequestMethod.GET)
	public List<HibTest> getKeywordList() {
		logger.info("keyword list controller");
		return keywordService.selectKeywordList();
	}
	
	@RequestMapping(value="/specific", method = RequestMethod.GET)
	public List<StoreInfoVO> getSpecificKeywordList(@RequestParam(value="keyword") String keyword) {
		logger.info("specific controller");
		logger.info("---keywordName: "+ keyword);	
		return keywordService.selectSpecificKeywordList(keyword);
	}
	
	@RequestMapping(value="/minit", method = RequestMethod.GET)
	public List<KeywordSort> getMajorInit() {
		logger.info("major init controller");
		return keywordService.selectMajorInit();
	}
	
	@RequestMapping(value="/sinit", method = RequestMethod.GET)
	public List<KeywordSort> getSmallCategory(@RequestParam(value="major") int keywordRef) {
		logger.info("small init controller");
		return keywordService.selectSmallInit(keywordRef);
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public List<StoreInfoVO> getStoreInfo(@RequestParam(value="code") int keywordCode) {
		logger.info("search controller");
		return keywordService.selectInfoByCode(keywordCode);
	}
	
	@RequestMapping(value="/major", method = RequestMethod.GET)
	public List<StoreInfoVO> getMajorCategory(@RequestParam(value="major") String major) {
		logger.info("major controller");
		return keywordService.selectSpecificKeywordList(major);
	}
	
	
}
