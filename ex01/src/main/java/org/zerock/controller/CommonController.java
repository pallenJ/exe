package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommonController {
	@GetMapping("accessError")
	public void accessDenied(Authentication auth , Model model) {
		log.info("access denied : "+auth);
		model.addAttribute("msg","Access Denied");
	}
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		log.error("error:"+error);
		log.error("logout:"+logout);
		
		if(error!=null) {model.addAttribute("error","Login Error Check Your Account");}
		if(logout!=null) {model.addAttribute("logout","Logout!!");}
		
	}
	@GetMapping("/customLogout")
	public void logoutGet() {
		log.info("custom logout get");
		
	}
	
	@PostMapping("/customLogout")
	public void logoutPost() {
		log.info("custom logout post");
		
	}
	@GetMapping("/customRegister")
	public void memberRegister() {}
	
}
