package com.example.asn.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.asn.domain.RecordDTO;
import com.example.asn.service.CalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/records/*")
@Controller
public class RecordConroller {

	@Resource
	CalService calService;

	@GetMapping("record")
	public void record(HttpServletRequest request, Model model) {

		String srt;

		try {
			srt = request.getParameter("srt");
			if(srt == null) throw new Exception();
		} catch (Exception e) {
			srt = "d";
		}
		
		log.info("srt::" + srt);
		recordCalCus(srt, model);

	}

	private void recordCalCus(String srt, Model model) {

		RecordDTO rdto = calService.recordMap();

		int kvalue;
		switch (srt) {
		case "k":
			kvalue = RecordDTO.K;
			break;

		case "e":
			kvalue = RecordDTO.E;
			break;

		case "m":
			kvalue = RecordDTO.M;
			break;

		case "t":
			kvalue = RecordDTO.T;
			break;

		default:
			kvalue = RecordDTO.D;
			break;
		}

		List<String> stdList = rdto.stdSortList(kvalue);

		model.addAttribute("stdList", stdList);
		model.addAttribute("rData", rdto);

	}

}
