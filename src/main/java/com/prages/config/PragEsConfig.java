package com.prages.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.prages.common.env.PragEsConstants;

/**
 * Created by lks21c on 16. 3. 14.
 */
@Configuration
public class PragEsConfig {

	@Bean
	public Client esClient() throws UnknownHostException {
		Settings settings = Settings.settingsBuilder().put("cluster.name", PragEsConstants.ES_CLUSTER_NAME).build();
		return TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(
				InetAddress.getByName(PragEsConstants.ES_HOST), PragEsConstants.ES_CLIENT_PORT));
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "WEB-INF/tiles/tiles.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
}
