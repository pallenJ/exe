package org.zerock.domain;


import lombok.Data;

@Data
public class CalendarVO {
	private int cno;
	private int cal_year;
	private int cal_month;
	private int cal_day;
	private String cal_date;
	private int cal_holi;
	private String cal_title;
	private String cal_content;
	private String userid;
	private String userauth;
	
	public CalendarVO() {
	}
	
	
}
