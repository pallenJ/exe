package org.zerock.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CalendarVO;
import org.zerock.domain.DayInfo;
import org.zerock.mapper.CalendarMapper;
import org.zerock.security.MemberTest;
import org.zerock.service.CalendarService;
import org.zerock.service.ReadCSV;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
	,"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j
public class CalendarTest {
	
	@Setter(onMethod_ = @Autowired)
	private CalendarMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private CalendarService service;
	
	@Test
	public void test() {
		/*
		 * int year = 2019, month=5; List<CalendarVO> list = mapper.get(year, month);
		 * int fweek = list.get(0).getCal_week(); log.info(year+"-"+month);
		 * log.info("============"); String temp = ""; while (--fweek>0) {
		 * System.out.print("|  ");; }
		 * 
		 * for (CalendarVO c: list) { System.out.print("|"+String.format("%02d",
		 * c.getCal_day())); if(c.getCal_week()==7) { System.out.print("\n"); } }
		 */
		/*
		 * int year = 2019, month=5; List<CalendarVO> list = mapper.get(year, month);
		 * int splitTemp = 7 - list.get(0).getCal_week()+1; List<List<CalendarVO>>
		 * calList = new ArrayList<>();
		 * 
		 * int weekCal = 0; int maxday = list.size(); while (true) { List<CalendarVO>
		 * temp = list.subList(weekCal, splitTemp);
		 * 
		 * calList.add(list.subList(weekCal, splitTemp));
		 * 
		 * for (CalendarVO calendarVO : temp) {
		 * System.out.print("|"+calendarVO.getCal_day()); } System.out.println();
		 * if(maxday == splitTemp) break; weekCal+= weekCal==0?splitTemp:7; splitTemp
		 * +=7; splitTemp = splitTemp<= maxday?splitTemp:maxday;
		 * 
		 * }
		 */
		/*
		 * int year = 2019, month=5; List<List<String>> list =
		 * service.getWeekArrays(year, month);
		 * 
		 * for (List<String> temp : list) { log.info(temp); }
		 */
		/*
		 * List<List<CalendarVO>> list = service.getWeekArrays(2019, 6); for
		 * (List<CalendarVO> week : list) { for (CalendarVO cal : week) {
		 * System.out.print(cal.getCal_day()+"|"); } System.out.println(); }
		 */
		
		/*
		 * List<List<LocalDate>> list = service.getLocalArr(2019, 5, 5);
		 * 
		 * for (List<LocalDate> week : list) { for (LocalDate date : week) {
		 * System.out.print(date+"|"); } System.out.println(); }
		 */
		
		/*
		 * ReadCSV readCSV = new ReadCSV("201905.csv", "\t"); readCSV.loadFile();
		 * HashMap<String, ArrayList<DayInfo>> hashDayInfo = readCSV.toHashData();
		 * 
		 * Iterator <String> it = hashDayInfo.keySet().iterator(); while(it.hasNext()) {
		 * String key = it.next(); ArrayList<DayInfo> listDayInfo =
		 * hashDayInfo.get(key); for(DayInfo dayInfo : listDayInfo)
		 * System.out.println(key +" " + dayInfo.getFlagHoliday() +" "+
		 * dayInfo.getDayTitle() ); }
		 */
			
			//log.info(mapper.getByMonth("2019-05-05", "2019-06-30").get(0));
		
		/*
		 * CalendarVO vo = new CalendarVO(); vo.setCal_year(2019); vo.setCal_month(1);
		 * vo.setCal_day(1); vo.setCal_holi(1); vo.setCal_date("2019-01-01");
		 * vo.setCal_title("a"); vo.setCal_content("a"); mapper.insert(vo);
		 * mapper.insert(vo); mapper.insert(vo); mapper.insert(vo);
		 */
		 //log.info(mapper.deleteDay("2019-01-01"));
		//log.info(mapper.deleteMulti("2019-05-11", "2019-06-04"));
		//log.info(mapper.delete(4));
		CalendarVO vo = mapper.get(11);
		log.info(vo);
		/*
		 * List<List<String>>asdf= new ArrayList<List<String>>(); List<String> list =
		 * service.makeMonthDayList(2019, 5); asdf.add(list.subList(0, 7));
		 * log.info(asdf);
		 */
	}
	
}
