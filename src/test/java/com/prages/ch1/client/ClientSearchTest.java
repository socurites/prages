package com.prages.ch1.client;

import com.prages.base.AbstractBaseClientTest;
import org.elasticsearch.action.search.SearchResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prages.common.util.HttpClientResponse;
import com.prages.common.util.ResourceFileReadUtil;
import com.prages.common.util.SimpleHttpUtil;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class ClientSearchTest extends AbstractBaseClientTest {

    @Test
    public void testSearch() throws MalformedURLException {
        SearchResponse searchResponse = client.prepareSearch("priceinfo").setTypes("info").get();
        System.out.println(searchResponse.toString());
        assertTrue(searchResponse.status().getStatus() == 200);
    }

    @Test
    public void testSearchWithQueryDsl() throws Exception {
        String source = resourceFileReadUtil.getFileContent("prages/ch1/schema/search_dsl.json");
        SearchResponse searchResponse = client.prepareSearch("priceinfo").setTypes("info").setSource(source).get();
        System.out.println(searchResponse.toString());
        assertTrue(searchResponse.status().getStatus() == 200);

    }
}
