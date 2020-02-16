package com.board.springboot.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.board.springboot.dao.MemberAuthVO;
import com.board.springboot.dao.MemberVO;

import antlr.MakeGrammar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityMember extends User {
	
	public SecurityMember(MemberVO member) { 
		super(member.getUemail(), member.getUpw(), authorities(member)); 
		} 
	private static Collection<? extends GrantedAuthority> authorities(MemberVO member) { 
		List<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(member.getMAuth().get(0).getAuthority()));
//		if (member.isAdmin()) { 
//			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
//		} 
//		else { 
//			authorities.add(new SimpleGrantedAuthority("ROLE_USER")); 
//		} 
		return authorities; 
		}

}
