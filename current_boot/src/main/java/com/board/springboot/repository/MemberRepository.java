package com.board.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.board.springboot.dao.MemberVO;

public interface MemberRepository extends CrudRepository<MemberVO, String>{
	
}
