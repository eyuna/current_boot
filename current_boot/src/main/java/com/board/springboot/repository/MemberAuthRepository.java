package com.board.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.board.springboot.dao.MemberAuthVO;

public interface MemberAuthRepository extends CrudRepository<MemberAuthVO, String> {

}
