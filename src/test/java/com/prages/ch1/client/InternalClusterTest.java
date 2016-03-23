package com.prages.ch1.client;

import com.google.common.base.Charsets;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import static org.elasticsearch.test.StreamsUtils.copyToStringFromClasspath;
import static org.junit.Assert.assertTrue;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class InternalClusterTest extends ESIntegTestCase {

    @Test
    public void testCreateIndex() throws Exception {
        String bulkAction = copyToStringFromClasspath("/org/elasticsearch/action/bulk/bulk-log.json");
        BulkRequestBuilder bulkBuilder = client().prepareBulk();
        bulkBuilder.add(bulkAction.getBytes(Charsets.UTF_8), 0, bulkAction.length(), null, null);
        bulkBuilder.get();
        assertBusy(new Runnable() {
            @Override
            public void run() {
                GetMappingsResponse mappingsResponse = client().admin().indices().prepareGetMappings().get();
                assertTrue(mappingsResponse.getMappings().containsKey("logstash-2014.03.30"));
                assertTrue(mappingsResponse.getMappings().get("logstash-2014.03.30").containsKey("logs"));
            }
        });
    }
}
