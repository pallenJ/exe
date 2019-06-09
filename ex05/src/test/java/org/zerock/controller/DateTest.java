package org.zerock.controller;

import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR)+"/"+cal.get(Calendar.DATE));
	}
}
