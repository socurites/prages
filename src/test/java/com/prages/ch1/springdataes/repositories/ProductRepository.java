package com.prages.ch1.springdataes.repositories;

import com.prages.ch1.springdataes.vo.ProductInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * @author lks21c
 */
public interface ProductRepository extends ElasticsearchCrudRepository<ProductInfo, String> {
}
