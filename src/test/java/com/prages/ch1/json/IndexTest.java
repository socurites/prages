package com.prages.ch1.json;

import com.prages.base.AbstractBaseTest;
import com.prages.util.HttpClientResponse;
import org.junit.Test;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class IndexTest extends AbstractBaseTest {

    @Test
    public void testDeleteIndex() throws Exception {
        HttpClientResponse response = simpleHttpUtil.request("DELETE", "/priceinfo");
        System.out.println(response.response());
    }

    @Test
    public void testCreateIndex() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"settings\":" + resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_settings.json"));
        sb.append(", " + "\"mappings:\"" + resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_mappings.json"));
        sb.append("}");
        HttpClientResponse response = simpleHttpUtil.request("POST", sb.toString());
        System.out.println(response.response());
    }

    @Test
    public void testIndex() throws Exception {
        HttpClientResponse response = simpleHttpUtil.request("PUT", resourceFileReadUtil.getFileContent("prages/ch1/schema/price_index.json"));
        System.out.println(response.response());
    }
}
