package org.zerock.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		PageDTO pdto = new PageDTO(service.count(), cri);
		log.info("total:"+pdto.getTotal());
		log.info("start:"+pdto.getStartPage());
		log.info("end:"+pdto.getEndPage());
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker",new PageDTO(service.count(), cri));
	}

 	@GetMapping("/register")
	public void register() {

 	}

 	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register post............................................");
		service.register(board);
		log.info("register post............................................");
		rttr.addFlashAttribute("result", "register");
		return "redirect:/board/list";
	}

 	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("board", service.get(bno));
	}

 	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify post............................");
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "modify");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
 		return "redirect:/board/list";
	}

 	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "remove");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
 		return "redirect:/board/list";
	}

 }