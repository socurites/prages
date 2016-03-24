package com.prages.index.service;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Bulk Index Service
 *
 * @author lks21c
 */
@Service
public class BulkIndexService {
	@Autowired
	private Client client;

	private static final int MAX_BULK_ITEM_SIZE = 500;

	public void runBulkIndex(String indexName, String typeName, String bulkTxtPath) {
		BulkRequestBuilder bulkBuilder = client.prepareBulk();

		// String bulkAction = resourceFileReadUtil.getFileContent("prages/publicdata/sample2.txt");
		// bulkBuilder.add(bulkAction.getBytes(Charsets.UTF_8), 0, bulkAction.getBytes(Charsets.UTF_8).length);
		// BulkResponse bulkResponse = bulkBuilder.get();

	}
}
