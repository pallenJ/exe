package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.CalendarVO;

public interface CalendarMapper {
	
	public CalendarVO get(int cno);
	public List<CalendarVO> getByDay(String date);
	public CalendarVO getByUserDay(String userid);
	public List<CalendarVO> getByMonth(@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	public void insert(@Param("newSc")CalendarVO newSc);
	
	public int delete(int cno);
	public int deleteDay(String date);
	public int deleteMulti(@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	public int update(@Param("upSc")CalendarVO upSc);
	
}
