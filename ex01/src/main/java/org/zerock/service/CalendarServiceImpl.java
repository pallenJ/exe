package org.zerock.service;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.calculate.CalendarDTO;
import org.zerock.domain.CalendarVO;
import org.zerock.mapper.CalendarMapper;

import lombok.Setter;

@Service
public class CalendarServiceImpl implements CalendarService{
	
	@Setter(onMethod_ = @Autowired)
	private CalendarMapper mapper;
	
	@Override
	public CalendarVO get(int cno) {
		return mapper.get(cno);
	}
	
	@Override
	public List<CalendarVO> listByDay(String date) {
		return mapper.getByDay(date);
	}
	
	@Override
	public Map<String,List<CalendarVO>> listByMonth(int year, int month,int startweek) {
		List<LocalDate> monthLlist=CalendarDTO.makeDateTimeList(year, month, startweek);
		return CalendarDTO.change(mapper.getByMonth(monthLlist.get(0).toString(), monthLlist.get(monthLlist.size()-1).toString()));
	}
	@Override
	public Map<String,List<CalendarVO>> listByMonth(String ym,int startweek) {
		return listByMonth(Integer.parseInt(ym.substring(0,4)), Integer.parseInt(ym.substring(4,6)),startweek);
	}
	
	@Override
	public void register(CalendarVO vo) {
		mapper.insert(vo);
	}
	
	@Override
	public boolean modify(CalendarVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo)>0;
	}

	@Override
	public boolean remove(int cno) {
		// TODO Auto-generated method stub
		return mapper.delete(cno)>0;
	}

	
	@Override
	public List<List<LocalDate>> getLocalArr(int year, int month,int startweek){
		return CalendarDTO.getLocalArr(year, month, startweek);
	}
	@Override
	public List<String> generateWeekList(int week) {
		return CalendarDTO.generateWeekList(week);
	}

	
	
}
