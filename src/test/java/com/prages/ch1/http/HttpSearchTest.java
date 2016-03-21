package com.prages.ch1.http;

import java.net.MalformedURLException;

import org.junit.Test;

import com.prages.base.AbstractBaseHttpTest;
import com.prages.common.util.HttpClientResponse;

/**
 * @author lks21c
 *
 */
public class HttpSearchTest extends AbstractBaseHttpTest {

	@Test
	public void testSearch() throws MalformedURLException {
		HttpClientResponse httpClientResponse = simpleHttpUtil
				.request("/" + INDEX_NAME + "/" + INDEX_TYPE_NAME + "/" + "_search");
		System.out.println(httpClientResponse.statusCode());
		System.out.println(httpClientResponse.response());
	}

	@Test
	public void testSearchWithQueryString() throws Exception {

	}

	@Test
	public void testSearchWithQueryDsl() throws Exception {
		String payload = resourceFileReadUtil.getFileContent("prages/ch1/search_dsl/search_dsl.dsl");
		HttpClientResponse httpClientResponse = simpleHttpUtil.request("GET",
				"/" + INDEX_NAME + "/" + INDEX_TYPE_NAME + "/" + "_search", payload);
		System.out.println(httpClientResponse.response());
	}
}
