package it.lorenzo.app.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static Date getCurrentDateOfRegister() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); // 2016/11/16 12:08:43
		return date;
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date getDateTimestamp(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(date));
		return date;
	}

	public static Date convertStringToDateTimestamp(String inputDate) {
		Date date = null;
		DateFormat dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			date = dateFormat.parse(inputDate);
		} catch (Exception e) {
			System.out.println("Errore nel parsing da stringa a data.");
		}
		return date;
	}

}
