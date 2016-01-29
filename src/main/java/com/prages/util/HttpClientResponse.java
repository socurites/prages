package com.prages.util;

import java.util.List;
import java.util.Map;

/**
 * forked from : https://github.com/elastic/elasticsearch-river-wikipedia/blob/master/src/test/java/org/elasticsearch/river/wikipedia/helper/HttpClientResponse.java
 *
 * Created by lks21c on 16. 1. 29.
 */
public class HttpClientResponse {
	private final String response;
	private final int errorCode;
	private Map<String, List<String>> headers;
	private final Throwable e;

	public HttpClientResponse(String response, int errorCode, Map<String, List<String>> headers, Throwable e) {
		this.response = response;
		this.errorCode = errorCode;
		this.headers = headers;
		this.e = e;
	}

	public String response() {
		return response;
	}

	public int errorCode() {
		return errorCode;
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