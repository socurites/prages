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
		IndicesExistsResponse indicesExistsResponse = indicesAdminClient.prepareExists(INDEX_NAME).get();
		if (indicesExistsResponse.isExists()) {
			DeleteIndexResponse deleteIndexResponse = indicesAdminClient.prepareDelete(INDEX_NAME).get();
			System.out.println("isAcknowledged = " + deleteIndexResponse.isAcknowledged());
			assertTrue(deleteIndexResponse.isAcknowledged());
		}
	}

	@Test
	public void testCreateIndex() throws Exception {
		IndicesExistsResponse indicesExistsResponse = indicesAdminClient.prepareExists(INDEX_NAME).get();
		if (!indicesExistsResponse.isExists()) {
			CreateIndexResponse createIndexResponse = indicesAdminClient.prepareCreate(INDEX_NAME)
					.setSettings(resourceFileReadUtil.getFileContent("prages/ch1/schema/product_detail_settings.json"))
					.addMapping(INDEX_TYPE_NAME,
							resourceFileReadUtil.getFileContent("prages/ch1/schema/product_detail_mappings.json"))
					.get();
			System.out.println("isAcknowledged = " + createIndexResponse.isAcknowledged());
			assertTrue(createIndexResponse.isAcknowledged());
		}
	}

	@Test
	public void testIndex() throws Exception {
		String id = "C011030";
		IndexResponse indexResponse = client.prepareIndex(INDEX_NAME, INDEX_TYPE_NAME).setId(id)
				.setSource(resourceFileReadUtil.getFileContent("prages/ch1/schema/product_index.json")).get();
		System.out.println(indexResponse.getVersion());
		assertTrue(indexResponse.getVersion() > 0);
	}
}
