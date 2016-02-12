package com.prages.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.google.common.base.Charsets;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.io.Streams;

/**
 * 
 * Forked from : https://github.com/elastic/elasticsearch-river-wikipedia/blob/master/src/test/java/org/elasticsearch/river/wikipedia/helper/HttpClient.java
 * 
 * @author lks21c
 */
public class SimpleHttpUtil {
	private static final String DEFAULT_BASE_URL = "http://localhost:9200";
	private static final String REQUEST_CONTENT_TYPE = "application/json";
	private static final String REQUEST_ACCEPT_TYPE = "application/json";
	private static final Charset DEFAULT_REQUEST_CHARSET = StandardCharsets.UTF_8;
	private static final Charset DEFAULT_RESPONSE_CHARSET = Charsets.UTF_8;

    /**
     * 접속할 ES 노드의 url주소와 포트.
     * 자주 사용되어 반복되기 때문에 생성자를 통해 설정한다.
     */
	private static URL baseUrl;

	/**
	 * 기본 생성자
     *
	 * @throws MalformedURLException
     */
	public SimpleHttpUtil() throws MalformedURLException {
		this(new URL(SimpleHttpUtil.DEFAULT_BASE_URL));
	}

	/**
	 * 생성자
	 * 
	 * @param baseUrl 	접속할 ES 노드의 url주소와 포트
	 */
	public SimpleHttpUtil(URL baseUrl) {
		SimpleHttpUtil.baseUrl = baseUrl;
	}

	/**
	 * Http 요청하여 Http 클라이언트 객체를 리턴.
	 * 
	 * @param method 	GET/POST 등 요청 방
	 * @param path		인덱스/타입 경로
	 * @param headers	Http 요청 헤더 설정 정
	 * @param payload	전송할 명령 본문(JSON 포맷)
	 * @return
	 */
	public HttpClientResponse request(String method, String path, Map<String, String> headers, String payload) {
		URL url = getPathUrl(path);

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
				urlConnection.setRequestProperty("Content-Type", SimpleHttpUtil.REQUEST_CONTENT_TYPE);
				urlConnection.setRequestProperty("Accept", SimpleHttpUtil.REQUEST_ACCEPT_TYPE);
				OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream(), SimpleHttpUtil.DEFAULT_REQUEST_CHARSET);
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
				body = Streams.copyToString(new InputStreamReader(inputStream, SimpleHttpUtil.DEFAULT_RESPONSE_CHARSET));
			} catch (IOException e1) {
				throw new ElasticsearchException("problem reading error stream", e1);
			}
			return new HttpClientResponse(body, errorCode, respHeaders, null);
		} catch (IOException e) {
			InputStream errStream = urlConnection.getErrorStream();
			String body = null;
			if (errStream != null) {
				try {
					body = Streams.copyToString(new InputStreamReader(errStream, SimpleHttpUtil.DEFAULT_RESPONSE_CHARSET));
				} catch (IOException e1) {
					throw new ElasticsearchException("problem reading error stream", e1);
				}
			}
			return new HttpClientResponse(body, errorCode, respHeaders, e);
		} finally {
			urlConnection.disconnect();
		}
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
	
	/**
	 * path까지 포함한 URL 객체 생성.
	 * 
	 * @param path	인덱스/타입 경로.
	 * @return
	 */
	private URL getPathUrl(String path) {
		URL url = null;
		try {
			url = new URL(SimpleHttpUtil.baseUrl, path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
}
