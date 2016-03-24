package com.prages.ch1.client;

import static com.prages.base.SampleIndexConsatant.*;

import java.net.MalformedURLException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.junit.Test;

import com.prages.base.AbstractBaseClientTest;
import com.prages.common.util.ResourceFileReadUtil;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class ClientSearchTest extends AbstractBaseClientTest {

	@Test
	public void testSearch() throws MalformedURLException {
		// 색인
		String id = "C011030";
		IndexResponse indexResponse = client().prepareIndex(INDEX_NAME, INDEX_TYPE_NAME).setId(id)
				.setSource(ResourceFileReadUtil.getFileContent("prages/ch1/schema/product_index.json")).get();
		assertTrue(indexResponse.getVersion() > 0);
		System.out.println("Document version = " + indexResponse.getVersion());

		// Refresh
		client().admin().indices().prepareRefresh(INDEX_NAME).get();

		// 검색, 조건없음
		SearchResponse searchResponse = client().prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE_NAME).get();
		System.out.println(searchResponse.toString());
		assertTrue(searchResponse.getHits().totalHits() > 0);
	}

	@Test
	public void testSearchWithQueryDsl() throws Exception {
		// 색인
		String id = "C011030";
		IndexResponse indexResponse = client().prepareIndex(INDEX_NAME, INDEX_TYPE_NAME).setId(id)
				.setSource(ResourceFileReadUtil.getFileContent("prages/ch1/schema/product_index.json")).get();
		assertTrue(indexResponse.getVersion() > 0);
		System.out.println("Document version = " + indexResponse.getVersion());

		// Refresh
		client().admin().indices().prepareRefresh(INDEX_NAME).get();

		// Search
		String source = ResourceFileReadUtil.getFileContent("prages/ch1/search_dsl/search_dsl.dsl");
		SearchResponse searchResponse = client().prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE_NAME).setSource(source)
				.get();
		assertTrue(searchResponse.getHits().getHits().length > 0);
		System.out.println(searchResponse.toString());

	}
}
