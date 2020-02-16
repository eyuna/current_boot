package com.board.springboot.service;

import java.util.List;

import com.board.springboot.dao.MemberVO;
import com.board.springboot.dao.StoreInfoVO;

public interface MemberService {

	public void insertMember(String uname, String upw, String uemail);
	public MemberVO selectStoreByEmail(String uemail);
}
