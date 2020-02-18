package com.board.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.springboot.dao.AuthenticationRequest;
import com.board.springboot.dao.MemberAuthVO;
import com.board.springboot.dao.MemberVO;
import com.board.springboot.jwt.JwtService;
import com.board.springboot.service.MemberService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	@Autowired 
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public void insertMember(@RequestParam("uname") String uname
			, @RequestParam("upw") String upw
			, @RequestParam("uemail") String uemail) {
		logger.info("member default controller");
		memberService.insertMember(uname, upw, uemail);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public MemberAuthVO loginController(
              @RequestBody AuthenticationRequest authenticationRequest,
              HttpSession session
              ) {
		logger.info("login controller");
        String uemail = authenticationRequest.getUemail();
        String password = authenticationRequest.getUpw();
         
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(uemail, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                  SecurityContextHolder.getContext());
        
        MemberVO member = memberService.selectMemberByEmail(uemail);
        MemberAuthVO res = new MemberAuthVO();
        res.setUserIdx(member.getIdx());
        res.setAuthority(member.getMAuth().get(0).getAuthority());
        res.setToken(session.getId());
        return res;
    }
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
    public String signin(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response){
		
		String uemail = authenticationRequest.getUemail();
//        String password = authenticationRequest.getUpw();
        
        String token = jwtService.createJwt(uemail);
        response.setHeader("Authorization", token);
//        result.setData(loginMember);
        return "== 토큰 발급 완료 " + uemail + "==";
    }
	
	@RequestMapping(value="/tkcheck", method=RequestMethod.GET)
    public boolean authToken(HttpServletRequest res) throws Exception {
        String jwt = res.getParameter("jwt");

        if(jwt == null) {
            return false;
        }else {
            return jwtService.isUsable(jwt);
        }
    }
}
