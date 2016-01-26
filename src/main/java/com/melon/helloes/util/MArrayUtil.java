package com.melon.helloes.util;

public class MArrayUtil {
	public static String concat(String[] arr) {
		String result = "";
		for (int i = 0; i < arr.length; i++) {
			result += arr[i];

			if (i != arr.length - 1) {
				result += ",";
			}
		}

		return result;
	}
}
