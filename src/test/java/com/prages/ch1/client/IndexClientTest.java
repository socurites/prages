package com.prages.ch1.client;

import com.prages.base.AbstractBaseClientTest;
import com.prages.base.AbstractBaseHttpTest;
import com.prages.util.HttpClientResponse;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.junit.Test;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class IndexClientTest extends AbstractBaseClientTest {

    @Test
    public void testDeleteIndex() throws Exception {
        DeleteIndexResponse deleteIndexResponse = indicesAdminClient.prepareDelete("priceinfo").get();
        System.out.println("isAcknowledged = " + deleteIndexResponse.isAcknowledged());
    }


    @Test
    public void testCreateIndex() throws Exception {
        CreateIndexResponse createIndexResponse = indicesAdminClient.prepareCreate("priceinfo")
                .setSettings(resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_settings.json"))
                .addMapping("info", resourceFileReadUtil.getFileContent("prages/ch1/schema/price_detail_mappings.json"))
                .get();
        System.out.println("isAcknowledged = " + createIndexResponse.isAcknowledged());
    }

    @Test
    public void testIndex() throws Exception {
    }
}
