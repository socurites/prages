package com.prages.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.google.common.base.Charsets;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.io.Streams;

/**
 * Forked from : https://github.com/elastic/elasticsearch-river-wikipedia/blob/master/src/test/java/org/elasticsearch/river/wikipedia/helper/HttpClient.java
 *
 * Created by lks21c on 16. 1. 29.
 */
public class SimpleHttpUtil {

    /**
     * ES의 기본 url주소와 포트를 지정한다.
     * 자주 사용되어 반복되기 때문에 메서드 입력시 마다 지정하지 않고 생성자를 통해 설정한다.
     */
	private static URL baseUrl;

	/**
	 * 파라미터를 지정하지 않으면 편의상 baseUrl을 http://localhost:9200으로 설정한다.
     *
	 * @throws MalformedURLException
     */
	public SimpleHttpUtil() throws MalformedURLException {
		new SimpleHttpUtil(new URL("http://localhost:9200"));
	}

	public SimpleHttpUtil(URL baseUrl) {
		this.baseUrl = baseUrl;
	}

	public HttpClientResponse request(String path) {
		return request("GET", path, null, null);
	}

	public HttpClientResponse request(String method, String path) {
		return request(method, path, null, null);
	}

	public HttpClientResponse request(String method, String path, String payload) {
		return request(method, path, null, payload);
	}

	public HttpClientResponse request(String method, String path, Map<String, String> headers, String payload) {
		URL url = null;
		try {
			url = new URL(baseUrl, path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod(method);
			if (headers != null) {
				for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
					urlConnection.setRequestProperty(headerEntry.getKey(), headerEntry.getValue());
				}
			}

			if (payload != null) {
				urlConnection.setDoOutput(true);
				urlConnection.setRequestProperty("Content-Type", "application/json");
				urlConnection.setRequestProperty("Accept", "application/json");
				OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream(),
						StandardCharsets.UTF_8);
				osw.write(payload);
				osw.flush();
				osw.close();
			}

			urlConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int errorCode = -1;
		Map<String, List<String>> respHeaders = null;
		try {
			errorCode = urlConnection.getResponseCode();
			respHeaders = urlConnection.getHeaderFields();
			InputStream inputStream = urlConnection.getInputStream();
			String body = null;
			try {
				body = Streams.copyToString(new InputStreamReader(inputStream, Charsets.UTF_8));
			} catch (IOException e1) {
				throw new ElasticsearchException("problem reading error stream", e1);
			}
			return new HttpClientResponse(body, errorCode, respHeaders, null);
		} catch (IOException e) {
			InputStream errStream = urlConnection.getErrorStream();
			String body = null;
			if (errStream != null) {
				try {
					body = Streams.copyToString(new InputStreamReader(errStream, Charsets.UTF_8));
				} catch (IOException e1) {
					throw new ElasticsearchException("problem reading error stream", e1);
				}
			}
			return new HttpClientResponse(body, errorCode, respHeaders, e);
		} finally {
			urlConnection.disconnect();
		}
	}
}
