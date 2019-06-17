package org.zerock.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.domain.TableDTO;
import org.zerock.mapper.UserDataMapper;
import org.zerock.service.sta.StaService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/sta/*")
public class StaController {

	@Setter(onMethod_ = @Autowired)
	StaService staService;
	@Setter
	private TableDTO tableDTO = new TableDTO(StaService.BOARD_10);

	@GetMapping
	public void main(HttpServletRequest request, Criteria cri, Model model) {
		// int cnt = staService.count(tableDTO,cri);
		PageDTO pdto = new PageDTO(2000000, cri);
		log.info("=================================");
		// log.info("total:"+cnt);
		log.info("start:" + pdto.getStartPage());
		log.info("end:" + pdto.getEndPage());
		model.addAttribute("list", staService.getList(tableDTO, cri));
		model.addAttribute("pageMaker", pdto);

	}

	@GetMapping("staPage")
	public void stapage(HttpServletRequest request,Model model) {

			boolean multi = false;
		  TableDTO table = new TableDTO(); try { 
			  log.info("1");
		  String tableName = (String)request.getParameter("tableName"); 
		  
		  int ftnum = Integer.parseInt(request.getParameter("ftNum"));
		  
		  log.info(tableName+"/"+ftnum);
		  
		  if(ftnum<10&&tableName!=null&&!tableName.equals("")) { 
			  table = new TableDTO(tableName, ftnum); 
		  }else if(ftnum==10) {
			  table = new TableDTO(tableName, TableDTO.YMD,TableDTO.H);
			  multi = true;
		  }else if(ftnum==100) {
			  table = new TableDTO(tableName, TableDTO.YMD);
			  table.setWrite(true);
		  } 
		  
		  } catch (Exception e) { }
		
		model.addAttribute("datas", staService.getStatistics(multi, table));

	    	}

}