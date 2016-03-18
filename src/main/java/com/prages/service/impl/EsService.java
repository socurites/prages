package com.prages.service.impl;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lks21c on 16. 3. 18.
 */
@Service
public class EsService {
	@Autowired
	private Client esClient;

	public SearchResponse search() {
		SearchResponse searchResponse = esClient.prepareSearch("priceinfo").setTypes("info").get();
		return searchResponse;
	}
}
