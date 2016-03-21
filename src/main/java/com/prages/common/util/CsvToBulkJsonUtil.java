package com.prages.common.util;

import java.io.*;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class CsvToBulkJsonUtil {

	public static void main(String[] args) {
		run("/extended/es_21/public_data/2015-10-01.csv", "EUC-KR", "2015-10-01.txt");
		run("/extended/es_21/public_data/2015-10-02.csv", "EUC-KR", "2015-10-02.txt");
		run("/extended/es_21/public_data/2015-10-03.csv", "EUC-KR", "2015-10-03.txt");
		run("/extended/es_21/public_data/2015-10-04.csv", "EUC-KR", "2015-10-04.txt");
		run("/extended/es_21/public_data/2015-10-05.csv", "EUC-KR", "2015-10-05.txt");
	}

	private static void run(String inputFilePath, String inputFileEncoding, String outputFilePath) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line;
		String cvsSplitBy = ",";
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), inputFileEncoding));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath)));
			while ((line = reader.readLine()) != null) {
				String[] field = line.split(cvsSplitBy);
				String logDate = field[0];
				String id = field[1];
				String categoryName = field[2];
				String categoryCode = field[3];
				String title = field[4];
				String price = field[5];
				StringBuilder sb = new StringBuilder();
				sb.append("{ \"index\":  { \"_index\": \"priceinfo\", \"_type\": \"info\", \"_id\": \"" + id
						+ "\" }}" + "\n");
				sb.append("{\"logDate\": \"" + logDate + "\"," + //
						"\"id\": \"" + id + "\"," + //
						"\"categoryName\": \"" + categoryName + "\"," + //
						"\"categoryCode\": \"" + categoryCode + "\"," + //
						"\"title\": \"" + title + "\"," + //
						"\"price\": \"" + price + "\"" + //
						"\"}\n");
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
