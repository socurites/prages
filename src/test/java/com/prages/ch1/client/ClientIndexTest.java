package com.prages.ch1.client;

import static com.prages.conts.SampleIndexConsatant.INDEX_NAME;
import static com.prages.conts.SampleIndexConsatant.INDEX_TYPE_NAME;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.prages.base.AbstractBaseClientTest;
import com.prages.common.util.ResourceFileReadUtil;

/**
 * @author lks21c
 */
public class ClientIndexTest extends AbstractBaseClientTest {

	@Test
	public void testDeleteIndex() throws Exception {
		// 인덱스 생성
		createIndex();

		// 인덱스 삭제
		DeleteIndexResponse deleteIndexResponse = client().admin().indices().prepareDelete(INDEX_NAME).get();
		assertTrue(deleteIndexResponse.isAcknowledged());
		System.out.println("Is deleteIndexResponse acknowledged? = " + deleteIndexResponse.isAcknowledged());

		// 인덱스 생성 확인
		boolean isIndexExists = isIndexExists();
		assertFalse(isIndexExists);
		System.out.println("Is index existsed? = " + isIndexExists);
	}

	@Test
	public void testCreateIndex() throws Exception {
		// 인덱스 생성
		createIndex();

		// 인덱스 생성 확인
		boolean isIndexExists = isIndexExists();
		assertTrue(isIndexExists);
		System.out.println("Is index existsed? = " + isIndexExists);
	}

	@Test
	public void testIndex() throws Exception {
		// 색인
		String id = "C011030";
		IndexResponse indexResponse = client().prepareIndex(INDEX_NAME, INDEX_TYPE_NAME).setId(id)
				.setSource(ResourceFileReadUtil.getFileContent("prages/ch1/schema/product_index.json")).get();
		assertTrue(indexResponse.getVersion() > 0);
		System.out.println("Document version = " + indexResponse.getVersion());

		// 문서가 색인되었는지 확인
		checkDocumentExists(id);
	}

	@Test
	public void testBulkIndex() throws Exception {
		// 인덱스 생성
		createIndex();

		// 벌크 색인
		BulkRequestBuilder bulkBuilder = client().prepareBulk();
		String bulkAction = ResourceFileReadUtil.getFileContent("prages/publicdata/bulk_index_sample.txt");
		bulkBuilder.add(bulkAction.getBytes(Charsets.UTF_8), 0, bulkAction.getBytes(Charsets.UTF_8).length);
		BulkResponse bulkResponse = bulkBuilder.get();

		System.out.println(bulkResponse.buildFailureMessage());

		assertTrue(bulkResponse.getItems().length > 0);
		assertFalse(bulkResponse.hasFailures());
		System.out.println("version = " + bulkResponse.getItems()[0].getVersion());
		System.out.println("id = " + bulkResponse.getItems()[0].getItemId());

		System.out.println("Does bulkResponse have failures? = " + bulkResponse.hasFailures());

		// Refresh
		client().admin().indices().prepareRefresh(INDEX_NAME).get();

		// 검색, 조건없음
		SearchResponse searchResponse = client().prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE_NAME).get();
		System.out.println(searchResponse.toString());
		assertTrue(searchResponse.getHits().totalHits() > 0);
	}

	private void createIndex() {
		CreateIndexResponse createIndexResponse = client().admin().indices().prepareCreate(INDEX_NAME)
				.setSettings(ResourceFileReadUtil.getFileContent("prages/ch1/schema/product_detail_settings.json"))
				.addMapping(INDEX_TYPE_NAME,
						ResourceFileReadUtil.getFileContent("prages/ch1/schema/product_detail_mappings.json"))
				.get();
		assertTrue(createIndexResponse.isAcknowledged());
		System.out.println("Is createIndexResponse acknowledged? = " + createIndexResponse.isAcknowledged());
	}

	private boolean isIndexExists() {
		IndicesExistsResponse indicesExistsResponse = client().admin().indices().prepareExists(INDEX_NAME).get();
		return indicesExistsResponse.isExists();
	}

	private void checkDocumentExists(String id) {
		GetResponse getResponse = client().prepareGet(INDEX_NAME, INDEX_TYPE_NAME, id).get();
		assertTrue("Document isn't existed.", getResponse.isExists());
		assertEquals(id, getResponse.getId());
		System.out.println("Is Document exists? = " + getResponse.isExists());
		System.out.println("Document Source =  " + getResponse.getSourceAsString());
	}
}
