package br.com.utility;

import java.util.Calendar;
import java.util.Date;

public class DataUtils {

	public static Date toDateOnly(Date date){
		// ignora informação de horas
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
		// ignora informação de horas
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date);

		//		calendar.clear(Calendar.HOUR);
		//		calendar.clear(Calendar.HOUR_OF_DAY);
		//		calendar.clear(Calendar.MINUTE);
		//		calendar.clear(Calendar.SECOND);
		//		calendar.clear(Calendar.MILLISECOND);

		return calendar.getTime();
	}

	public static java.sql.Date getSQLDate(Object dateObj) throws Exception {

//		SimpleDateFormat sdf = null;
		try {

//			sdf = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date utilDate = (Date) dateObj;//sdf.parse((String) dateObj);
			if (utilDate == null) {
				return null;
			} else {
				return new java.sql.Date(utilDate.getTime());
			}

		} catch(Exception e) {
			throw e;
		}

	}

}
