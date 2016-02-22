package com.prages.ch1.springdataes.config;

import com.prages.common.env.PragEsConstants;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lks21c
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com/prages/ch1/springdataes/repositories")
public class SpringDataEsConfig {

    @Bean
    public Client client() throws UnknownHostException {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", PragEsConstants.ES_CLUSTER_NAME).build();

        Client client = TransportClient.builder()
                .settings(settings)
                .build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(PragEsConstants.ES_HOST), PragEsConstants.ES_CLIENT_PORT));
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }
}
