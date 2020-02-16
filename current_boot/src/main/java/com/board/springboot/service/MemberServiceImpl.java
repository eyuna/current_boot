package com.board.springboot.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.springboot.controller.MemberController;
import com.board.springboot.dao.MemberAuthVO;
import com.board.springboot.dao.MemberVO;
import com.board.springboot.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public void insertMember(String uname, String upw, String uemail) {
		logger.info("email: " + uemail);
		MemberVO member = new MemberVO();
		MemberAuthVO mauth = new MemberAuthVO();
		member.setUemail(uemail);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setUpw(passwordEncoder.encode(upw));
		member.setUname(uname);
		
		mauth.setAuthority("ROLE_USER");
		member.setMAuth(Arrays.asList(mauth));
		MemberVO returnedMember = memberRepository.save(member);
		
		logger.info("Returned Account ID is " + returnedMember.getUname());

	}

	@Override
	public MemberVO selectStoreByEmail(String uemail) {
		return memberRepository.findByUemail(uemail);
	}	
	
	
	
}
