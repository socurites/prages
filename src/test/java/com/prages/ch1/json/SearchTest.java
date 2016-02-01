package com.prages.ch1.json;

import java.net.MalformedURLException;

import com.prages.base.AbstractBaseTest;
import org.junit.Before;
import org.junit.Test;

import com.prages.util.HttpClientResponse;
import com.prages.util.ResourceFileReadUtil;
import com.prages.util.SimpleHttpUtil;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class SearchTest extends AbstractBaseTest {

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
