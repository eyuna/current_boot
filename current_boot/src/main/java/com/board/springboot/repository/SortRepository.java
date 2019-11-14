package com.board.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.board.springboot.dao.KeywordSort;

public interface SortRepository extends CrudRepository<KeywordSort, String> {
	
	List<KeywordSort> findByKeywordName(String keyword);
	List<KeywordSort> findByKeywordCodeAndKeywordRef(int keywordCode, int keywordRef);
	List<KeywordSort> findByKeywordRefNull();
	List<KeywordSort> findByKeywordRef(int keywordRef);

}
