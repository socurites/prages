package com.melon.helloes.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;

public class SearchDslResourceReader {
	public String getSearchDsl(String inputPath, Map<String, String> paramMap) {
		StringBuffer sb = new StringBuffer();

		ClassLoader cl = getClass().getClassLoader();
		File file = new File(cl.getResource(inputPath).getFile());

		try (Scanner scanner = new Scanner(file)) {
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				sb.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		String result = sb.toString();
		
		Set<String> keySet = paramMap.keySet();
		for ( String key : keySet ) {
			result = result.replaceAll("#" + key, Matcher.quoteReplacement(paramMap.get(key)));
		}
		
		System.out.println(result);
		
		return result;
	}
	
	public static void main(String[] args) {
//		String inputPath = "search_dsl/search_nickname_r.dsl";
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("keyword", "하늘");
//		paramMap.put("offset", "5");
//		paramMap.put("pageSize", "30");
//		
//		String searchDsl = (new SearchDslResourceReader()).getSearchDsl(inputPath, paramMap);
//		
//		System.out.println(searchDsl);
		
		System.out.println("$".replaceAll("\\$", "\\\\$"));
	}
}
