package org.zerock.calendar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class SampleCal {
	public static void main(String[] args) {
		/*
		 * ArrayList<Integer> sample = new ArrayList<Integer>(); DateTime dateTime = new
		 * DateTime(2019, 5, 1, 0, 0, 0, 0); DateTimeFormatter fmt =
		 * DateTimeFormat.forPattern("yyyy-MM-dd").withLocale(Locale.US);
		 * ArrayList<String> listDay = makeMonthDayList(2019, 5);
		 * System.out.println(listDay.subList(0, 7));
		 * System.out.println(listDay.subList(7, 14));
		 * System.out.println(listDay.subList(14, 21));
		 * System.out.println(listDay.subList(21, 28));
		 * System.out.println(listDay.subList(28, 35));
		 * System.out.println(listDay.subList(35, listDay.size()));
		 */
		//printCalendar(listDay);
	}

	
	private static List<String> convertFormat(List<String> weekRow, String type) {
		ArrayList<String> viewRow = new ArrayList<String>();
		Iterator<String> it = weekRow.iterator();
		while (it.hasNext()) {
			String tmp = it.next();
			if (type.equals("D"))
				viewRow.add(tmp.substring(8, 10));
		}
		return viewRow;
	}

	private static ArrayList<String> makeMonthDayList(int year, int month) {
		ArrayList<String> listDay = new ArrayList<String>();
		DateTime dateTime = new DateTime(2019, 5, 1, 0, 0, 0, 0);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		int weekOfDay = dateTime.getDayOfWeek()+2;
		dateTime = dateTime.plusDays(-weekOfDay);
		listDay.add(fmt.print(dateTime));
		while (fmt.print(dateTime).compareTo("2019-05-31") != 0) {
			dateTime = dateTime.plusDays(1);
			listDay.add(fmt.print(dateTime));
		}
		weekOfDay = dateTime.getDayOfWeek();
		for (int i = 0; i < 6 - weekOfDay; i++) {
			dateTime = dateTime.plusDays(1);
			listDay.add(fmt.print(dateTime));
		}
		return listDay;
	}
}