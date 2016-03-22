package com.prages.common.util;

import java.io.*;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class CsvToBulkJsonUtil {

	public void run(String inputFilePath, String inputFileEncoding, String date) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line;
		String cvsSplitBy = ",";
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			reader = new BufferedReader(
new InputStreamReader(
					new FileInputStream(classLoader.getResource(inputFilePath + date + ".csv").getFile()),
					inputFileEncoding));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(date + ".txt")));
			while ((line = reader.readLine()) != null) {
				String[] field = line.split(cvsSplitBy);
				String logDate = field[0];
				String id = field[1];
				String categoryName = field[2];
				String categoryCode = field[3];
				String title = field[4];
				String price = field[5];
				StringBuilder sb = new StringBuilder();
				// sb.append("{ \"index\": { \"_index\": \"product_" + date + "\", \"_type\": \"info\", \"_id\": \""
				// + id + "\" }}" + "\n");
				sb.append("{\"logDate\": \"" + logDate + "\"," + //
						"\"id\": \"" + id + "\"," + //
						"\"categoryName\": \"" + categoryName + "\"," + //
						"\"categoryCode\": \"" + categoryCode + "\"," + //
						"\"title\": \"" + title + "\"," + //
						"\"price\": \"" + price + "\"" + //
						"}\n");
				writer.write(sb.toString());
				System.out.println(sb.toString() + "생성");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
