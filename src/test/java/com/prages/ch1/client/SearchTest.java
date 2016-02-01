package com.prages.ch1.client;

import com.prages.util.HttpClientResponse;
import com.prages.util.ResourceFileReadUtil;
import com.prages.util.SimpleHttpUtil;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class SearchTest {
    private SimpleHttpUtil simpleHttpUtil;

    ResourceFileReadUtil resourceFileReadUtil = new ResourceFileReadUtil();

    @Before
    public void setUp() throws Exception {
        simpleHttpUtil = new SimpleHttpUtil();
    }

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
