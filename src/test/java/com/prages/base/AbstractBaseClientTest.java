package com.prages.base;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.prages.common.env.PragEsConstants;
import com.prages.common.util.ResourceFileReadUtil;

/**
 * Created by hydra01 on 16. 2. 1.
 */
public class AbstractBaseClientTest {
    protected static Client client;
    protected static IndicesAdminClient indicesAdminClient;
    protected ResourceFileReadUtil resourceFileReadUtil = new ResourceFileReadUtil();

    @BeforeClass
    public static void setUp() throws Exception {
    	Settings settings = Settings.settingsBuilder()
    	        .put("cluster.name", PragEsConstants.ES_CLUSTER_NAME).build();
    	
        client = TransportClient.builder()
        		.settings(settings)
        		.build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(PragEsConstants.ES_HOST), PragEsConstants.ES_CLIENT_PORT));
        indicesAdminClient = client.admin().indices();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        client.close();
    }
}
