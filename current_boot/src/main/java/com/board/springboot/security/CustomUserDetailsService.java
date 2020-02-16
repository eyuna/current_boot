package com.board.springboot.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.board.springboot.dao.MemberVO;
import com.board.springboot.repository.MemberRepository;

/* 실제 인증 과정 처리 */
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String uemail) throws UsernameNotFoundException {
		
		MemberVO member = memberRepository.findByUemail(uemail);
		if(member == null) {
			throw new UsernameNotFoundException(uemail);
		}
		return new SecurityMember(member);
	}
	

}
