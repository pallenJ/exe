package org.zerock.service;


import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.zerock.domain.CalendarVO;

public interface CalendarService {

CalendarVO get(int cno);	
List<CalendarVO> listByDay(String date);
void register(CalendarVO vo);
Map<String,List<CalendarVO>> listByMonth(int year, int month,int startweek);
Map<String,List<CalendarVO>> listByMonth(String ym,int startweek);

List<List<LocalDate>> getLocalArr(int year, int month, int startweek);
List<String> generateWeekList(int week);

boolean modify(CalendarVO vo);
boolean remove(int cno);
Map<String, List<CalendarVO>> listByMonth(int year, int month, int startweek, String userid);
}
