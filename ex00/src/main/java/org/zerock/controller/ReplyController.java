package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.ReplyVO;
import org.zerock.service.BoardService;
import org.zerock.service.ReplyService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController//restcon 으로 작성, restlet로 테스트
@RequestMapping("/replies/*")
public class ReplyController {
	
	@Setter(onMethod_ = { @Autowired })
	private ReplyService service;
	
	@PostMapping(value = "/new", consumes = "application/json",produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		log.info("rVO:"+vo);
		int insertCount = service.register(vo);
		log.info("Ins cnt:"+insertCount);
		return insertCount == 1?
				new ResponseEntity<>("success",HttpStatus.OK):
					 new ResponseEntity<>("success",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
