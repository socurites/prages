package com.prages.web.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.prages.common.domain.Product;
import com.prages.web.config.PragEsConfig;
import com.prages.web.service.ProductSearchService;

/**
 * @author socurites
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PragEsConfig.class, ProductSearchServiceImpl.class }, loader = AnnotationConfigContextLoader.class)
public class ProductSearchServiceImplTest {
	@Autowired
	private ProductSearchService productSearchSearvice;
	
	@Test
	public void listProduct() {
		String query = "jacket";
		String sort = "";
		int page = 0;
		int size = 10;
		
		List<Product> products = productSearchSearvice.listProduct(query, sort, page, size);
		assertNotNull(products);
		System.out.println(products);
	}
	
}
