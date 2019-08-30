package com.example.asn.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.asn.domain.RecordDTO;
import com.example.asn.domain.RecordCSVDTO;
import com.example.asn.domain.RecordCSVDTO.LecEnum;
import com.example.asn.service.CalCSVService;
import com.example.asn.service.CalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/records/*")
@Controller
public class RecordController {

	@Resource
	CalService calService;
	
	  @Resource CalCSVService calCSVService;
	 

	@GetMapping("record")
	public void record(HttpServletRequest request, Model model) {

		String srt;
		String order;
		try {
			srt = request.getParameter("srt");
			if(srt == null) throw new Exception();
		} catch (Exception e) {
			srt = "d";
		}
		
		try {
			order = request.getParameter("order");
			if(order == null) throw new Exception();
		} catch (Exception e) {
			order = "DESC";
		}
		
		log.info("srt::{}" , srt);
		recordCalCus(srt,order, model);

	}

	private void recordCalCus(String srt,String order, Model model) {

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
		List<String> lecList = new ArrayList<>(Arrays.asList(rdto.getLectures()));
		lecList.add("sta_total");
		List<String> stdList = rdto.stdSortList(kvalue);
		if("kemt".contains(srt)&&!order.equals("ASC"))
		Collections.reverse(stdList);
		log.info("lectures cnt :: {}",rdto.getLectures().length);
		model.addAttribute("stdList", stdList);
		model.addAttribute("rData", rdto);
		model.addAttribute("lecList", lecList);

	}
	
	@GetMapping("csvRecord")
	public void csvRecord(HttpServletRequest request, Model model) throws IOException {

		String srt;

		try {
			srt = request.getParameter("srt");
			if(srt == null) throw new Exception();
		} catch (Exception e) {
			srt = LecEnum.re.name();
		}
		
		log.info("srt::" + srt);
		recordCalCSV(changeSRT(srt), model);

	}
	
	private void recordCalCSV(int code, Model model) throws IOException {
		RecordCSVDTO csvDto = calCSVService.getDto();
		csvDto.sort(code);
		model.addAttribute("rdto",csvDto);
	}
	private int changeSRT(String srt) {
		switch (srt) {
		case "k":
			return LecEnum.korean.ordinal();

		case "e":
			return LecEnum.english.ordinal();

		case "m":
			return LecEnum.math.ordinal();

		case "t":
			return LecEnum.total.ordinal();

		default:
			return LecEnum.re.ordinal();
		}
	}

}
