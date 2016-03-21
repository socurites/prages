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
		HttpClientResponse response = simpleHttpUtil.request("/" + INDEX_NAME);
		if (response.statusCode() == 200) {
			response = simpleHttpUtil.request("DELETE", "/" + INDEX_NAME);
			System.out.println(response.response());
			assertTrue(response.statusCode() == 200);
		}
	}

	@Test
	public void testCreateIndex() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"settings\":"
				+ resourceFileReadUtil.getFileContent("prages/ch1/schema/product_detail_settings.json"));
		sb.append(", " + "\"mappings\":"
				+ resourceFileReadUtil.getFileContent("prages/ch1/schema/product_detail_mappings.json"));
		sb.append("}");
		HttpClientResponse response = simpleHttpUtil.request("POST", "/" + INDEX_NAME + "/" + INDEX_TYPE_NAME + "/",
				sb.toString());
		System.out.println(response.response());
	}

	@Test
	public void testIndex() throws Exception {
		String id = "C011030";
		HttpClientResponse response = simpleHttpUtil.request("PUT", "/" + INDEX_NAME + "/" + INDEX_TYPE_NAME + "/" + id,
				resourceFileReadUtil.getFileContent("prages/ch1/schema/product_index.json"));
		System.out.println(response.response());
		assertTrue(response.statusCode() < 400);
	}

	// @Test
	// public void testBulkIndex() throws Exception {
	// String id = "C011030";
	// HttpClientResponse response = simpleHttpUtil.request("PUT", "/" + INDEX_NAME + "/" + INDEX_TYPE_NAME + "/" + id,
	// resourceFileReadUtil.getFileContent("prages/ch1/schema/product_index.json"));
	// System.out.println(response.response());
	// assertTrue(response.statusCode() < 400);
	// }
}
