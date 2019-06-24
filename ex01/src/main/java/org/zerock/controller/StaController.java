package org.zerock.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.domain.TableDTO;
import org.zerock.mapper.UserDataMapper;
import org.zerock.service.sta.StaService;

import lombok.Getter;
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
	
	@Setter
	@Getter
	private List<Map<String,Object>>forPoi;
	
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
		  if(ftnum==10) {
			  table = new TableDTO(tableName, TableDTO.YMD,TableDTO.H);
			  multi = true;
			//---
		  }else if(ftnum<10&&tableName!=null&&!tableName.equals("")) { 
			  table = new TableDTO(tableName, ftnum); 
		  }else if(ftnum==100) {
			  table = new TableDTO(tableName, TableDTO.YMD);
			  table.setWrite(true);
		  } 
		  
		  } catch (Exception e) { }
		  	@SuppressWarnings("unchecked")
			List<Map<String,Object>>list = (List<Map<String,Object>>)(staService.getStatistics(multi, table));
		  	
		  	Set<String> titles = multi?new TreeSet<String>(list.get(0).keySet()):list.get(0).keySet();
		  	
		  	model.addAttribute("datas", list);
		    model.addAttribute("titles", titles);
		    
		    setForPoi(list);
	    	}
	
	@GetMapping("poiTest")
	public void poitest(HttpServletResponse response/* ,@RequestParam("datas") List<?> datas */,String tableName,int ftNum) throws Exception{
		staService.poiMaker(response,getForPoi(),tableName+"_data",ftNum==10);
		
	}
	

	
}//---

/*
 * @SuppressWarnings("unchecked") List<Map<String,Object>> staList =
 * (List<Map<String,Object>>)staService.getStatisticsMulti(table);
 */
/*
 * Map<String,Map<String,Object>> staList =
 * staService.getStatisticsMulti(table); Iterator<String> itr =
 * staList.keySet().iterator(); itr.hasNext(); Set<String> titles = new
 * TreeSet<>(staList.get(itr.next()).keySet());
 * 
 * model.addAttribute("datas", staList); model.addAttribute("titles", titles);
 * return;
 */