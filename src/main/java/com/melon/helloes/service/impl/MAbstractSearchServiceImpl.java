package com.melon.helloes.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.melon.helloes.util.SearchDslResourceReader;

/**
 * 컨텐츠 검색 서비스
 * 
 * @author socurites
 *
 */
public abstract class MAbstractSearchServiceImpl {
	private static final String INDEX_NAME = "song";
	private static final String TYPE_NAME = "detail";
	private static final String MELON_SEARCH_HTTP_URL = "http://localhost:9200";

	protected String doSearch(Map<String, String> paramMap, String dslLocation,
			String searchType) {
		StringBuffer sb = new StringBuffer();

		URL url;
		try {
			
			if ( searchType == null ) {
				url = new URL(MELON_SEARCH_HTTP_URL + "/" + INDEX_NAME + "/" + TYPE_NAME + "/_search");
			} else {
				url = new URL(MELON_SEARCH_HTTP_URL + "/" + INDEX_NAME + "/" + TYPE_NAME + "/_search?search_type=count");
			}
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = null;
			SearchDslResourceReader dslResourceReader = new SearchDslResourceReader();
			input = dslResourceReader.getSearchDsl(dslLocation, paramMap);

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes("UTF-8"));
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream()), "UTF-8"));

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		return sb.toString();
	}
}
