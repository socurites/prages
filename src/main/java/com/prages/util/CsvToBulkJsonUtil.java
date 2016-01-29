package com.prages.util;

import java.io.*;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class CsvToBulkJsonUtil {

	public static void main(String[] args) {
		run("/extended/es_21/public_data/2015-10-01.csv", "EUC-KR", "output.txt");
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
				String productCode = field[1];
				String categoryName = field[2];
				String categoryCode = field[3];
				String productName = field[4];
				String price = field[5];
				String discountPrice = null;
				String promotionPrice = null;
				if (field.length > 6) {
					discountPrice = field[6];
				}
				if (field.length > 7) {
					promotionPrice = field[7];
				}
				StringBuilder sb = new StringBuilder();
				sb.append("{ \"index\":  { \"_index\": \"priceinfo\", \"_type\": \"info\", \"_id\": \"" + productCode
						+ "\" }}" + "\n");
				sb.append("{\"logDate\": \"" + logDate + "\"," + //
						"\"productCode\": \"" + productCode + "\"," + //
						"\"categoryName\": \"" + categoryName + "\"," + //
						"\"categoryCode\": \"" + categoryCode + "\"," + //
						"\"productName\": \"" + productName + "\"," + //
						"\"price\": \"" + price + "\"," + //
						"\"discountPrice\": \"" + discountPrice + "\"," + //
						"\"promotionPrice\": \"" + promotionPrice + "\"}\n");
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
