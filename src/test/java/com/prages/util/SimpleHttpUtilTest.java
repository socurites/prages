package com.prages.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.prages.common.util.HttpClientResponse;
import com.prages.common.util.SimpleHttpUtil;

public class SimpleHttpUtilTest {

	@Test
	public void test() throws MalformedURLException {
		SimpleHttpUtil simpleHttpUtil = new SimpleHttpUtil(new URL("http://localhost:9200"));
        HttpClientResponse httpClientResponse = simpleHttpUtil.request("/priceinfo/info/_search");
        System.out.println(httpClientResponse.response());
    }

}
