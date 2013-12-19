package br.com.utility;

import java.util.Calendar;
import java.util.Date;

public class DataUtils {

	public static Date toDateOnly(Date date){
		// ignora informa��o de horas
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date);

		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);

		return calendar.getTime();
	}

	public static Date toCalendarDate(Date date){
		// ignora informa��o de horas
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date);

		//		calendar.clear(Calendar.HOUR);
		//		calendar.clear(Calendar.HOUR_OF_DAY);
		//		calendar.clear(Calendar.MINUTE);
		//		calendar.clear(Calendar.SECOND);
		//		calendar.clear(Calendar.MILLISECOND);

		return calendar.getTime();
	}

}
