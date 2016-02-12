package com.prages.ch1.http;

import static org.junit.Assert.assertTrue;
import com.prages.base.AbstractBaseHttpTest;
import com.prages.util.HttpClientResponse;
import org.junit.Test;

/**
 * @author lks21c
 *
 */
public class IndexHttpTest extends AbstractBaseHttpTest {

    @Test
    public void testDeleteIndex() throws Exception {
        HttpClientResponse response = simpleHttpUtil.request("DELETE", "/priceinfo");
        System.out.println(response.response());
        assertTrue(response.errorCode() < 400);
    }

    @Test
    public void testCreateIndex() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"settings\":" + resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_settings.json"));
        sb.append(", " + "\"mappings\":" + resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_mappings.json"));
        sb.append("}");
        HttpClientResponse response = simpleHttpUtil.request("POST", "/priceinfo/info", sb.toString());
        System.out.println(response.response());
    }

    @Test
    public void testIndex() throws Exception {
    	String productCode = "C011030";
        HttpClientResponse response = simpleHttpUtil.request("PUT", "/priceinfo/info/" + productCode, resourceFileReadUtil.getFileContent("prages/ch1/schema/price_index.json"));
        System.out.println(response.response());
        assertTrue(response.errorCode() < 400);
    }
}
