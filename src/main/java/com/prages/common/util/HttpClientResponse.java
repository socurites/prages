package com.prages.common.util;

import java.util.List;
import java.util.Map;

/**
 * 
 * forked from : https://github.com/elastic/elasticsearch-river-wikipedia/blob/master/src/test/java/org/elasticsearch/river/wikipedia/helper/HttpClientResponse.java
 * 
 * @author lks21c
 */
public class HttpClientResponse {
	/** Http 응답(JSON 포맷). */
	private final String response;
	/** Http 응답 코드. */
	private final int statusCode;
	/** Http 응답 헤더. */
	private Map<String, List<String>> headers;
	/** 에러. */
	private final Throwable e;

	public HttpClientResponse(String response, int statusCode, Map<String, List<String>> headers, Throwable e) {
		this.response = response;
		this.statusCode = statusCode;
		this.headers = headers;
		this.e = e;
	}

	public String response() {
		return response;
	}

	public int statusCode() {
		return statusCode;
	}

	public Throwable cause() {
		return e;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public String getHeader(String name) {
		if (headers == null) {
			return null;
		}
		List<String> vals = headers.get(name);
		if (vals == null || vals.size() == 0) {
			return null;
		}
		return vals.iterator().next();
	}
}