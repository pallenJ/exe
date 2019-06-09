package org.zerock.domain;

public class DayInfo
{
	String dayTitle;
	String flagHoliday;

	public DayInfo(String dayTitle, String flagHoliday)
	{
		this.dayTitle = dayTitle;
		this.flagHoliday = flagHoliday;
	}

	public String toString()
	{
		return this.dayTitle + "\t" + this.flagHoliday;
	}

	public String getDayTitle()
	{
		return dayTitle;
	}

	public String getFlagHoliday()
	{
		return flagHoliday;
	}
}