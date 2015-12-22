package com.melon.helloes.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MCatUtil {
	public static String cat(String urlString) {
		StringBuffer sb = new StringBuffer();

		URL url;
		try {
			url = new URL(urlString);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			for (String line; (line = reader.readLine()) != null;) {
				sb.append(line);
		    }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
