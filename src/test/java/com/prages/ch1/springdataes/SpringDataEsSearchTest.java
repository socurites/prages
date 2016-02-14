package com.prages.ch1.springdataes;

import com.prages.ch1.springdataes.config.SpringDataEsConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;

/**
 * @author lks21c
 */

/**
 * TODO: 현재는 스프링 데이터가 ES 2.0을 제대로 지원하지 못한다.
 * 이는 spring data "hopper" release에서 패치될 예정이며 3월 18일 GA예정이다.
 * https://jira.spring.io/browse/DATAES-211
 * https://github.com/spring-projects/spring-data-commons/wiki/Release-Train-Hopper
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
