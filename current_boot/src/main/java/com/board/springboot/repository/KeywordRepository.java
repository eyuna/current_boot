package com.board.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.board.springboot.dao.HibTest;

public interface KeywordRepository extends CrudRepository<HibTest, String>{
	
	List<HibTest> findAll();
}
