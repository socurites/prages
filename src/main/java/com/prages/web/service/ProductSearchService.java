package com.prages.web.service;

import java.util.List;

import com.prages.common.domain.Product;

public interface ProductSearchService {
	/**
	 * 통합 검색 & 초성 검색용
	 * query에 매칭되는 상품을 검색.
	 * 
	 * @param query		검색어
	 * @param sort		정렬순서
	 * @param page		페이지번호
	 * @param size		검색건수
	 * @return
	 */
	List<Product> listProduct(String query, String sort, int page, int size);
	
	/**
	 * 자동완성 검색용.
	 * query로 시작하는 상품을 검색.
	 * 
	 * @param query		검색어	
	 * @param size		검색건수
	 * @return
	 */
	List<Product> listAutoComplete(String query, int size);
	
	/**
	 * 확장 검색용.
	 * 조건에 매칭되는 상품을 검색.
	 * 
	 * @param query			검색어
	 * @param cateCode		카테고리 코드
	 * @param fromPrice		시작가격
	 * @param toPrice		끝 가격
	 * @param sort			정렬순서
	 * @param page			페이지번호
	 * @param size			검색건수
	 * @return
	 */
	List<Product> listProductExt(String query, String cateCode, double fromPrice, double toPrice, String sort, int page, int size);
}
