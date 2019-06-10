package org.zerock.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.zerock.domain.CalendarVO;
import org.zerock.domain.DayInfo;

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
