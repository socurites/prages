package com.prages.index.config;

import com.prages.common.env.PragEsConstants;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 배치 실행에 필요한 기 본설정
 *
 * @author lks21c
 */
public class BatchConfig {

    protected static final int MAX_BULK_ITEM_SIZE = 500;

    public Client client() throws UnknownHostException {
        Settings settings = Settings.settingsBuilder().put("cluster.name", PragEsConstants.ES_CLUSTER_NAME).build();
        return TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(
                InetAddress.getByName(PragEsConstants.ES_HOST), PragEsConstants.ES_CLIENT_PORT));
    }
}
