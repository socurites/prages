package com.prages.spring;

import static com.prages.conts.SampleIndexConsatant.INDEX_NAME;
import static com.prages.conts.SampleIndexConsatant.INDEX_TYPE_NAME;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.prages.web.config.PragEsConfig;

/**
 * Created by lks21c on 16. 3. 18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PragEsConfig.class }, loader = AnnotationConfigContextLoader.class)
public class SampleSpringTest {
	@Autowired
	private Client esClient;

	@Test
	public void testName() throws Exception {
		SearchResponse searchResponse = esClient.prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE_NAME).get();
		System.out.println(searchResponse.toString());
	}
}
