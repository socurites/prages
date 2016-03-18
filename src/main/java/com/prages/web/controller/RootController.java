package com.prages.web.controller;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lks21c on 16. 3. 14.
 */
@RestController
public class RootController {

	@Autowired
	private Client esClient;

	@RequestMapping("")
	public String index() {
		return "Hello World";
	}

	@RequestMapping("search")
	public String search() {
		SearchResponse searchResponse = esClient.prepareSearch("priceinfo").setTypes("info").get();
		return searchResponse.toString();
	}
}
