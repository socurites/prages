package com.prages.ch1.client;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.elasticsearch.action.search.SearchResponse;
import org.junit.Test;

import com.prages.base.AbstractBaseClientTest;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class ClientSearchTest extends AbstractBaseClientTest {

	@Test
	public void testSearch() throws MalformedURLException {
		SearchResponse searchResponse = client.prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE_NAME).get();
		System.out.println(searchResponse.toString());
		assertTrue(searchResponse.status().getStatus() == 200);
	}

	@Test
	public void testSearchWithQueryDsl() throws Exception {
		String source = resourceFileReadUtil.getFileContent("prages/ch1/search_dsl/search_dsl.dsl");
		SearchResponse searchResponse = client.prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE_NAME).setSource(source)
				.get();
		System.out.println(searchResponse.toString());
		assertTrue(searchResponse.status().getStatus() == 200);

	}
}
