package it.lorenzo.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.lorenzo.app.utils.Utils;

public class MainTest {

	public static void main(String[] args) {
		Date date = Utils.getCurrentDateOfRegister();
		Utils.addDays(date, 7);
	}

}
