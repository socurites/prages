package com.prages.ch1.springdataes.repositories;

import com.prages.ch1.springdataes.vo.PriceInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * @author lks21c
 */
public interface ProductRepository extends ElasticsearchCrudRepository<PriceInfo, String> {

    List<PriceInfo> findByCategoryName(String categoryName);

}
