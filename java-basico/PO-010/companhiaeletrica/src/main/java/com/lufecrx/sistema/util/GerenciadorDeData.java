package com.lufecrx.sistema.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GerenciadorDeData {

    public static String calendarParaString(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        return sdf.format(calendar.getTime());
    }
    	
    public static Calendar stringParaCalendar(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		Date date = sdf.parse(dateString);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
        return calendar;
    }
    
}
