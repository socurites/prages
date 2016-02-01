package com.prages.base;

import com.prages.util.ResourceFileReadUtil;
import com.prages.util.SimpleHttpUtil;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.net.InetAddress;

/**
 * Created by hydra01 on 16. 2. 1.
 */
public class AbstractBaseClientTest {
    protected static Client client;

    protected static IndicesAdminClient indicesAdminClient;

    protected ResourceFileReadUtil resourceFileReadUtil = new ResourceFileReadUtil();

    @BeforeClass
    public static void setUp() throws Exception {
        client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        indicesAdminClient = client.admin().indices();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        client.close();
    }
}
