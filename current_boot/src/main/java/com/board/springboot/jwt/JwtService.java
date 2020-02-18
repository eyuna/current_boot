package com.board.springboot.jwt;

import java.util.Map;

public interface JwtService {
	public String createJwt(String uemail);
	boolean isUsable(String jwt);
	Map<String, Object> get(String key);

}
