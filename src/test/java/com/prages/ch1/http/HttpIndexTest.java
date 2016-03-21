package com.prages.ch1.http;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.prages.base.AbstractBaseHttpTest;
import com.prages.common.util.HttpClientResponse;

/**
 * @author lks21c
 */
public class HttpIndexTest extends AbstractBaseHttpTest {

    @Test
    public void testDeleteIndex() throws Exception {
        HttpClientResponse response = simpleHttpUtil.request("/priceinfo");
        if (response.statusCode() == 200) {
            response = simpleHttpUtil.request("DELETE", "/priceinfo");
            System.out.println(response.response());
            assertTrue(response.statusCode() == 200);
        }
    }

    @Test
    public void testCreateIndex() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"settings\":" + resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_settings.json"));
        sb.append(", " + "\"mappings\":" + resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_mappings.json"));
        sb.append("}");
        HttpClientResponse response = simpleHttpUtil.request("POST", "/priceinfo/info/", sb.toString());
        System.out.println(response.response());
    }

    @Test
    public void testIndex() throws Exception {
		String id = "C011030";
		HttpClientResponse response = simpleHttpUtil.request("PUT", "/priceinfo/info/" + id,
				resourceFileReadUtil.getFileContent("prages/ch1/schema/price_index.json"));
        System.out.println(response.response());
        assertTrue(response.statusCode() < 400);
	}

	@Test
	public void testBulkIndex() throws Exception {
		String id = "C011030";
		HttpClientResponse response = simpleHttpUtil.request("PUT", "/priceinfo/info/" + id,
				resourceFileReadUtil.getFileContent("prages/ch1/schema/price_index.json"));
		System.out.println(response.response());
		assertTrue(response.statusCode() < 400);
    }
}
