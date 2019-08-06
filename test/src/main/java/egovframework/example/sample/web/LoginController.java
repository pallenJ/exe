package egovframework.example.sample.web;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.sample.service.LoginService;
import egovframework.example.sample.service.impl.SampleDAO;
import egovframework.example.sample.service.impl.TestDAO;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
//@RequestMapping("/member/*")
public class LoginController{
	@Resource(name = "loginService")
	LoginService loginService;
	
	@Resource(name = "sampleDAO")
	SampleDAO sampleDAO;
	

	
	@GetMapping("/login.do")
	public String login() {
		
		log.info( "test:"+loginService.test());
		return "sample/login";
	}
	
	
	
	@PostMapping("login/{id}.do")
	public String sampleMyPage(@PathVariable("id") String id,String pw, Model model) {
		model.addAttribute("grade",loginService.login(id, pw)?"admin":"normal");
		return "sample/loginResult";
	}
	
}
