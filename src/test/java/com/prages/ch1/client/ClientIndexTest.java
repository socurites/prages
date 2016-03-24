package com.prages.ch1.client;

import static org.junit.Assert.assertTrue;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.prages.base.AbstractBaseClientTest;
import com.prages.common.util.ResourceFileReadUtil;

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
					.setSettings(ResourceFileReadUtil.getFileContent("prages/ch1/schema/product_detail_settings.json"))
					.addMapping(INDEX_TYPE_NAME,
							ResourceFileReadUtil.getFileContent("prages/ch1/schema/product_detail_mappings.json"))
					.get();
			System.out.println("isAcknowledged = " + createIndexResponse.isAcknowledged());
			assertTrue(createIndexResponse.isAcknowledged());
		}
	}

	@Test
	public void testIndex() throws Exception {
		String id = "C011030";
		IndexResponse indexResponse = client.prepareIndex(INDEX_NAME, INDEX_TYPE_NAME).setId(id)
				.setSource(ResourceFileReadUtil.getFileContent("prages/ch1/schema/product_index.json")).get();
		System.out.println("version = " + indexResponse.getVersion());
		assertTrue(indexResponse.getVersion() > 0);
	}

	@Test
	public void testBulkIndex() throws Exception {
		BulkRequestBuilder bulkBuilder = client.prepareBulk();
		bulkBuilder.add(client.prepareIndex(INDEX_NAME, INDEX_TYPE_NAME).setId("L012010")
				.setSource(ResourceFileReadUtil.getFileContent("prages/publicdata/sample.txt")));
		BulkResponse bulkResponse = bulkBuilder.get();
		assertTrue(bulkResponse.getItems().length > 0);
		assertTrue(!bulkResponse.hasFailures());
		System.out.println("version = " + bulkResponse.getItems()[0].getVersion());
	}

	@Test
	public void testBulkIndex2() throws Exception {
		BulkRequestBuilder bulkBuilder = client.prepareBulk();

		String bulkAction = ResourceFileReadUtil.getFileContent("prages/publicdata/sample2.txt");
		bulkBuilder.add(bulkAction.getBytes(Charsets.UTF_8), 0, bulkAction.getBytes(Charsets.UTF_8).length);
		BulkResponse bulkResponse = bulkBuilder.get();

		assertTrue(bulkResponse.getItems().length > 0);
		System.out.println("fail message = " + bulkResponse.buildFailureMessage());
		assertTrue(!bulkResponse.hasFailures());
		System.out.println("version = " + bulkResponse.getItems()[0].getVersion());
	}
}
