package com.prages.ch1.springdataes;

import com.prages.ch1.springdataes.config.SpringDataEsConfig;
import com.prages.ch1.springdataes.repositories.ProductRepository;
import com.prages.ch1.springdataes.vo.PriceInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSearch() throws MalformedURLException {
        Page<PriceInfo> priceInfoList = (Page<PriceInfo>) productRepository.findAll();
        System.out.println(priceInfoList.getTotalPages());
        assertNotEquals(0, priceInfoList.getTotalPages());
        List<PriceInfo> list = priceInfoList.getContent();
        assertNotEquals(0, list.size());
        System.out.println(list.get(0).getProductName());
    }

    @Test
    public void testSearchWithCategoryName() throws Exception {
        List<PriceInfo> priceInfoList = productRepository.findByCategoryName("174456322");
        assertTrue(priceInfoList.size() > 0);
    }

    @Test
    public void testSearchWithId() throws Exception {
        PriceInfo priceInfo = productRepository.findOne("C011030");
        System.out.println(priceInfo.getProductName());
    }
}
