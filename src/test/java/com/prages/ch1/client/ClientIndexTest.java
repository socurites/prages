package com.prages.ch1.client;

import static org.junit.Assert.assertTrue;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.junit.Test;

import com.prages.base.AbstractBaseClientTest;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class ClientIndexTest extends AbstractBaseClientTest {

	@Test
	public void testDeleteIndex() throws Exception {
		IndicesExistsResponse indicesExistsResponse = indicesAdminClient.prepareExists("priceinfo").get();
		if (indicesExistsResponse.isExists()) {
			DeleteIndexResponse deleteIndexResponse = indicesAdminClient.prepareDelete("priceinfo").get();
			System.out.println("isAcknowledged = " + deleteIndexResponse.isAcknowledged());
			assertTrue(deleteIndexResponse.isAcknowledged());
		}
	}

	@Test
	public void testCreateIndex() throws Exception {
		IndicesExistsResponse indicesExistsResponse = indicesAdminClient.prepareExists("priceinfo").get();
		if (!indicesExistsResponse.isExists()) {
			CreateIndexResponse createIndexResponse = indicesAdminClient.prepareCreate("priceinfo")
					.setSettings(resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_settings.json"))
					.addMapping("info",
							resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_mappings.json"))
					.get();
			System.out.println("isAcknowledged = " + createIndexResponse.isAcknowledged());
			assertTrue(createIndexResponse.isAcknowledged());
		}
	}

	@Test
	public void testIndex() throws Exception {
		String id = "C011030";
		IndexResponse indexResponse = client.prepareIndex("priceinfo", "info").setId(id)
				.setSource(resourceFileReadUtil.getFileContent("prages/ch1/schema/price_index.json")).get();
		System.out.println(indexResponse.getVersion());
		assertTrue(indexResponse.getVersion() > 0);
	}
}
