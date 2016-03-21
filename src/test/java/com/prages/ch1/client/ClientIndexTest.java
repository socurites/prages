package com.prages.ch1.client;

import com.prages.base.AbstractBaseClientTest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
        System.out.println("version = " + indexResponse.getVersion());
        assertTrue(indexResponse.getVersion() > 0);
    }

    @Test
    public void testBulkIndex() throws Exception {
        String sampleText = resourceFileReadUtil.getFileContent("prages/publicdata/sample.txt");
        System.out.println(sampleText);
        BulkResponse bulkdResponse = client.prepareBulk().add(sampleText.getBytes(), 0, sampleText.length()).get();
        System.out.println(bulkdResponse.toString());
    }
}
