package org.zerock.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.CalendarVO;
import org.zerock.domain.CustomUser;
import org.zerock.domain.DayInfo;
import org.zerock.mapper.CalendarMapper;
import org.zerock.security.CustomUserDetailsService;
import org.zerock.service.CalendarService;
import org.zerock.service.MemberService;
import org.zerock.service.ReadCSV;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/calendar/*")
public class CalendarController {
	
	@Setter(onMethod_ = @Autowired)
	private CalendarService service;
	@Setter(onMethod_ = @Autowired)
	private CustomUserDetailsService memberservice;
	
	@GetMapping("/googleCalPage")
	public void googleCal() {
		
	}
	
	@GetMapping("/main")
	@PreAuthorize(value="isAuthenticated()") 
	public void calPage( HttpServletRequest request,Model model,Principal principal) {
		
		String userid = principal.getName();
		CustomUser mem = (CustomUser) memberservice.loadUserByUsername(userid);
		
		String userauth = mem.getMember().getAuthList().get(0).getAuth();
		
		log.info("calPage");
		log.info(request.getParameter("ym"));
		log.info(request.getParameter("f"));
		log.info("username:"+userid);
		log.info("userauth:"+userauth);
		
		
		DateTime now = DateTime.now();
		
		int year = now.getYear(), month = now.getMonthOfYear(),week=1;
		
		try {
			year  = Integer.parseInt(request.getParameter("ym").substring(0,4));
			month = Integer.parseInt(request.getParameter("ym").substring(4,6));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			week  = Integer.parseInt(request.getParameter("f"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		log.info(year+"/"+month);
		
		//List<List<CalendarVO>> calList = service.getWeekArrays(year, month);
		List<List<LocalDate>> calList = service.getLocalArr(year, month, week);
		
		
		/*
		 * ReadCSV readCSV = new ReadCSV("C:\\upload\\tmp\\201905.csv", "\t");
		 * readCSV.loadFile(); HashMap<String, ArrayList<DayInfo>> hashDayInfo =
		 * readCSV.toHashData();
		 */
		  Map<String,List<CalendarVO>> hashDayInfo = 
				(userauth.equals("ROLE_ADMIN"))?
					service.listByMonth(year, month,week):
						service.listByMonth(year, month,week,principal.getName());
		  
		
			  
		 
		model.addAttribute("calList",calList);
		model.addAttribute("year",year);
		model.addAttribute("month",month);
		model.addAttribute("weekList",service.generateWeekList(week));
		model.addAttribute("schedule",hashDayInfo);
		
		
		
	}
	@GetMapping("/get")
	public void get(@RequestParam("cno") int cno,Model model) {
		model.addAttribute("schedule", service.get(cno));
	}
	
	@GetMapping("/register")
	public void register(String date,HttpServletRequest request) {
		try {
			request.setAttribute("date", date);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@PostMapping("/register")
	public String register(CalendarVO vo  , RedirectAttributes rttr) {
		
		log.info(vo.getCal_title());
		service.register(vo);
		
		return "redirect:/calendar/main";
	}
	
	@GetMapping("/modify")
	public void modify(int cno,Model model) {
		model.addAttribute("cal",service.get(cno));
	}
	@PostMapping("/modify")
	public String modify(CalendarVO vo,Model model) {
		return "redirect:/calendar/main";
	}
	@PostMapping("/remove")
	public String remove(int cno) {
		log.info("modify success:"+service.remove(cno));
		return "redirect:/calendar/main";
	}
	
	@GetMapping("/{date}")
	@ResponseBody
	public List<CalendarVO> getCalList(@PathVariable("date") String date){
		log.info(service.listByDay(date));
		return service.listByDay(date);
	}
	
}
