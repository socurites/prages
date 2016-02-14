package com.prages.ch1.springdataes;

import com.prages.base.AbstractBaseHttpTest;
import com.prages.ch1.springdataes.config.SpringDataEsConfig;
import com.prages.common.util.HttpClientResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;

/**
 * @author lks21c
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataEsConfig.class)
public class SpringDataEsSearchTest {

    @Test
    public void testSearch() throws MalformedURLException {

    }

    @Test
    public void testSearchWithQueryString() throws Exception {

    }

    @Test
    public void testSearchWithQueryDsl() throws Exception {
    }
}
