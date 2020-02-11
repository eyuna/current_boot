package com.board.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.board.springboot.repository.MemberRepository;

/* 실제 인증 과정 처리 */
public class CustomUserDetailsService implements UserDetailsService{
	
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;//반환할 타입이 Member와 맞지 않는다.
	}
}
