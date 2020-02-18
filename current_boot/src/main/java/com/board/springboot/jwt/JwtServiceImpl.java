package com.board.springboot.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.board.springboot.controller.MemberController;
import com.board.springboot.dao.MemberVO;
import com.board.springboot.error.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	
	private static final String SALT =  "ySecret";
	private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
	 
	@Override
	public String createJwt(String uemail) {
		String jwt = "";
		try {
			jwt = Jwts.builder()
					.setHeaderParam("typ", "JWT")
					.setExpiration(new Date(System.currentTimeMillis() + (1000*60*60*24)))
					.setSubject("user")
					.claim("email", uemail)
					.signWith(
							SignatureAlgorithm.HS256,
							SALT.getBytes("UTF-8")
					)
					.compact();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};
		return jwt;
	}
	
	private byte[] generateKey(){
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}else{
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}
		
		return key;
	}
	
	@Override
	public boolean isUsable(String jwt) {
		try{
			Claims claims = Jwts.parser()
					  .setSigningKey(this.generateKey())
					  .parseClaimsJws(jwt).getBody();
			
			logger.info("expireTime :" + claims.getExpiration());
            logger.info("Email :" + claims.get("email"));
			return true;
			
		}catch (ExpiredJwtException exception) {
            logger.info("토큰 만료");
            return false;
        } catch (JwtException exception) {
            logger.info("토큰 변조");
            return false;
        }
	}
	
	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("Authorization");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
						 .setSigningKey(SALT.getBytes("UTF-8"))
						 .parseClaimsJws(jwt);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
		return value;
	}
}
