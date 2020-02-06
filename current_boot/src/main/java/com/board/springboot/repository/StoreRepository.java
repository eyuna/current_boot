package com.board.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.board.springboot.dao.StoreInfoVO;


public interface StoreRepository extends CrudRepository<StoreInfoVO, String>{
	List<StoreInfoVO> findByKeywordCode(int keywordCode);
	List<StoreInfoVO> findByStoreNameLike(String word);
}
