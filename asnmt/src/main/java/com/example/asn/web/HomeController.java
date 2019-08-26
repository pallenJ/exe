package com.example.asn.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.asn.service.CalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Resource
	CalService calService;
	
	@GetMapping("/")
	public String home() {
		calService.test();
		return "home";
	}
}
