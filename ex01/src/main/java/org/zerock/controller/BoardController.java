package org.zerock.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@GetMapping("/list")
	public void list(HttpServletRequest request,Criteria cri, Model model) {
		PageDTO pdto = new PageDTO(service.count(cri), cri);
		log.info("=================================");
		log.info("total:"+service.count(cri));
		log.info("start:"+pdto.getStartPage());
		log.info("end:"+pdto.getEndPage());
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker",pdto);
	}

 	@GetMapping("/register")
 	@PreAuthorize("isAuthenticated()")
	public void register() {

 	}
 
	
 	@PostMapping("/register")
 	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {
		service.register(board);
		
		System.out.println("제목 -----------------------"+board.getTitle());
		rttr.addFlashAttribute("result", "register");
		return "redirect:/board/list";
	}

 	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model) {
 		log.info(bno);
 		//log.info("rcnt:"+service.replyCnt(bno));
		model.addAttribute("board", service.get(bno));
	}
 	
 	@PreAuthorize("principal.username == #writer")
 	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify post............................");
		//log.info("pageNum:" + cri.getPageNum());
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "modify");
		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
 		return "redirect:/board/list";
	}
 	
 	@PreAuthorize("principal.username == #writer")
 	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
 		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "remove");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/board/list";
	}
 	
	
	  @GetMapping("/rcnt")
	  @ResponseBody public int replyCnt(long bno) {
		  return service.replyCnt(bno);
	  }
	 
 	
 }