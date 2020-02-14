package com.board.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.springboot.service.MemberService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping(value="", method = RequestMethod.GET)
	public void insertMember(@RequestParam("uname") String uname
			, @RequestParam("upw") String upw
			, @RequestParam("uemail") String uemail) {
		logger.info("member default controller");
		memberService.insertMember(uname, upw, uemail);
	}

}
