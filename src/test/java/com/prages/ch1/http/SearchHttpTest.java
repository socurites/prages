package com.prages.ch1.http;

import java.net.MalformedURLException;

import com.prages.base.AbstractBaseHttpTest;
import org.junit.Test;

import com.prages.util.HttpClientResponse;

/**
 * @author lks21c
 *
 */
public class SearchHttpTest extends AbstractBaseHttpTest {

    @Test
    public void testSearch() throws MalformedURLException {
        HttpClientResponse httpClientResponse = simpleHttpUtil.request("/priceinfo/info/_search");
        System.out.println(httpClientResponse.response());
    }

    @Test
    public void testSearchWithQueryString() throws Exception {

    }

    @Test
    public void testSearchWithQueryDsl() throws Exception {
        String content = resourceFileReadUtil.getFileContent("prages/ch1/schema/search_dsl.json");
        HttpClientResponse httpClientResponse = simpleHttpUtil.request("GET", "/priceinfo/info/_search", content);
        System.out.println(httpClientResponse.response());
    }
}
