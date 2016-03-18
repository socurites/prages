package com.prages.web.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prages.common.domain.Product;
import com.prages.web.service.ProductSearchService;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {
	private static final String INDEX_NAME = "priceinfo";
	private static final String TYPE_NAME = "info";
	
	@Autowired
	private Client client;
	
	
	@Override
	public List<Product> listProduct(String query, String sort, int page, int size) {
		QueryBuilder mainQuery = termQuery("title", query);
		SortBuilder fieldSort = SortBuilders.fieldSort("title").order(SortOrder.DESC);
		SortBuilder scoreSort = SortBuilders.scoreSort();
		
		SearchResponse response = client.prepareSearch(INDEX_NAME)
				.setTypes(TYPE_NAME)
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(mainQuery)
		        .setFrom(page)
		        .setSize(size)
		        .addSort(scoreSort)
		        .addSort(fieldSort)
		        .execute()
		        .actionGet()
        ;
		
		
		SearchHits hits = response.getHits();
		List<Product> list = new ArrayList<Product>();
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		for ( SearchHit hit : hits ) {
			try {
				product = mapper.readValue(hit.getSourceAsString(), Product.class);
				list.add(product);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public List<Product> listAutoComplete(String query, int size) {
		return null;
	}

	@Override
	public List<Product> listProductExt(String query, String cateCode, double fromPrice, double toPrice, String sort, int page, int size) {
		return null;
	}

}
