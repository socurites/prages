package com.prages.ch1.springdataes.repositories;

import com.prages.ch1.springdataes.vo.PriceInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * @author lks21c
 */
public interface ProductRepository extends ElasticsearchCrudRepository<PriceInfo, String> {
}
