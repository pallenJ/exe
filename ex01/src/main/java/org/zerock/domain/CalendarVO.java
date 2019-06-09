package org.zerock.domain;

import java.util.List;

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
	public CalendarVO(int cal_year, int cal_month, int cal_day, String cal_date, int cal_holi, String cal_title,
			String cal_content) {
		super();
		this.cal_year = cal_year;
		this.cal_month = cal_month;
		this.cal_day = cal_day;
		this.cal_date = cal_date;
		this.cal_holi = cal_holi;
		this.cal_title = cal_title;
		this.cal_content = cal_content;
	}
	public CalendarVO() {
	}
	
	
}
