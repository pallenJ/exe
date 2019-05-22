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
	public void list(HttpServletRequest request, Model model) {
		model.addAttribute("list", service.getList());
		/*
		 * PageDTO pdto = new PageDTO(123, cri); pdto.setStartPage((pageNum-1)*10);
		 */
	
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
	public String modify(BoardVO board,  RedirectAttributes rttr) {
		log.info("modify post............................");
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "modify");
		}

 		return "redirect:/board/list";
	}

 	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "remove");
		}
 		return "redirect:/board/list";
	}

 }