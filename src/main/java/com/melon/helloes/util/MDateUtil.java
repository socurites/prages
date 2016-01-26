package com.melon.helloes.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MDateUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public static Date parseIssueDate(String dateStr) {
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String formatIssueDate(Date date) {
		return sdf.format(date);
	}
}
