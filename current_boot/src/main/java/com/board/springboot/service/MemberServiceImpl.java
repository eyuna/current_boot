package com.board.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.springboot.controller.MemberController;
import com.board.springboot.dao.MemberVO;
import com.board.springboot.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public void insertMember(String uid, String upw, String uemail) {
		MemberVO member = new MemberVO();
		member.setUid(uid);
		member.setUpw(upw);
		member.setUemail(uemail);
		MemberVO returnedMember = memberRepository.save(member);
		
		logger.info("Returned Account ID is " + returnedMember.getUid());

	}	
}
