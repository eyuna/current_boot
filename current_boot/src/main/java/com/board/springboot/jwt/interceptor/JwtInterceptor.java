package com.board.springboot.jwt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.board.springboot.error.UnauthorizedException;
import com.board.springboot.jwt.JwtService;

public class JwtInterceptor implements HandlerInterceptor{
	private static final String HEADER_AUTH = "Authorization";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String token = request.getHeader(HEADER_AUTH);

		if(token != null && jwtService.isUsable(token)){
			return true;
		}else{
			throw new UnauthorizedException();
		}

	}
}