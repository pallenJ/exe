package org.zerock.calculate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.zerock.domain.CalendarVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class CalendarDTO{


	
	public static List<List<LocalDate>> getLocalArr(int year, int month,int startweek){
		List<List<LocalDate>> result = new ArrayList<>();
		List<LocalDate> tmp   = makeDateTimeList(year, month, startweek);
		int last = tmp.size()>=42?6:5;
		
		for (int i = 0; i < last; i++) { 
			result.add(tmp.subList(7*i, 7*(i+1))) ; 
		}
		
		
		
		return result;
		
	}
	
	

	public static List<LocalDate> makeDateTimeList(int year, int month,int startweek) {
	   ArrayList<LocalDate> listDay = new ArrayList<>();
		
	    LocalDate date = new LocalDate(year, month, 1);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		
		LocalDate lastDay = date.dayOfMonth().withMaximumValue();
		
		int weekOfDay = date.getDayOfWeek()-startweek+1;
		if(weekOfDay<0) weekOfDay+=7;
		date = date.plusDays(-weekOfDay);

		listDay.add(date);
		
		while (fmt.print(date).compareTo(fmt.print(lastDay)) != 0) {
			date = date.plusDays(1);
			listDay.add(date);
		}
		log.debug(listDay.size());
		weekOfDay = date.getDayOfWeek();
		int lastweek = lastDay.getDayOfWeek();//달의 마지막날 요일 구하는 메소드
		
		
		int end = (6+lastweek-weekOfDay)%7;
		//if(end == 0) end = 7;
		for (int i = 0; i < end; i++) {
			date = date.plusDays(1);
			listDay.add(date);
		}
		log.info(listDay.size());
		return listDay;

	}
	
	public static Map<String,List<CalendarVO>> change(List<CalendarVO> calList){
		Map<String,List<CalendarVO>> rs = new HashMap<>();
		for (CalendarVO cal: calList) {
			Set<String> keySet = rs.keySet();
			String key = cal.getCal_date();
			List<CalendarVO> clist = new ArrayList<>();
			if(keySet.contains(key)) {
				clist = rs.get(key);
			}
			clist.add(cal);
			rs.put(key, clist);
		
		}
		return rs;
	}
	
	
	public static List<String> generateWeekList(int week) {
		// TODO Auto-generated method stub
		List<String> weekList = new ArrayList<String>();
		
		String[] weeksString = {"토","일","월","화","수","목","금"};
		week %= weeksString.length;
		
		for (int i = week; i < weeksString.length+week; i++) {
			weekList.add(weeksString[i%(weeksString.length)]);
		}
		
		return weekList;
	}

	
}
