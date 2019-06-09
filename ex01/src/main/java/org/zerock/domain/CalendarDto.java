package org.zerock.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

@Data
public class CalendarDto {
	 private String summary;
	    private String startDate;
	    private String startTime;
	    private String endDate;
	    private String endTime;
	    private String description;
	    private String eventId;
	    private String calendarId;
	    
	    {
	        description = "";
	    }
	    
	    public CalendarDto() {}
	    
	    public Date getStartDateTime() throws ParseException {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm");
	        return format.parse(startDate+startTime);
	    }
	    public Date getEndDateTime() throws ParseException {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm");
	        return format.parse(endDate+endTime);
	    }
	    
	 
	    @Override
	    public String toString() {
	        return "GoogleCalendarDto [summary=" + summary + ", startDate=" + startDate + ", startTime=" + startTime
	                + ", endDate=" + endDate + ", endTime=" + endTime + ", description=" + description + ", eventId="
	                + eventId + ", calendarId=" + calendarId + "]";
	    }
	
	
}
