package com.melon.helloes.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.melon.helloes.service.MSearchService;
import com.melon.helloes.util.MLanguageUtil;
import com.melon.helloes.util.SynonymDicUtil;

/**
 * 컨텐츠 검색 서비스
 * 
 * @author socurites
 *
 */
@Service
public class MSearchServiceImpl extends MAbstractSearchServiceImpl implements MSearchService {
	public String overviewIntroSearch(String term) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("term", term);

		return doSearch(paramMap, "helloes/search_dsl/overview/intro.dsl", null);
	}

	@Override
	public String overViewPrefixArtistAggs(String term, int size) {
		term = MLanguageUtil.convertKo2Eng(term);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("term", term);
		paramMap.put("size", size + "");

		String result = doSearch(paramMap, "helloes/search_dsl/overview/prefix_artist_aggs.dsl", "count");

		return result;
	}

	@Override
	public String overViewArtisDetailtAggs(String artistId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("artistId", artistId);

		String resultAgg = doSearch(paramMap, "helloes/search_dsl/overview/artist_detail_aggs.dsl", "count");
		String resultInfo = doSearch(paramMap, "helloes/search_dsl/common/get_artist_info.dsl", null);

		return "{ \"aggs\": " + resultAgg + ", \"info\": " + resultInfo + "}";
	}

	@Override
	public String morphemePrefixArtistAggsWithSynonym(String term, int size) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("term", MLanguageUtil.convertKo2Eng(term));
		paramMap.put("size", size + "");

		if (SynonymDicUtil.hasSynonym(term)) {
			paramMap.put("synonym", MLanguageUtil.convertKo2Eng(SynonymDicUtil.getTerm(term)));
		}

		String result = doSearch(paramMap, "helloes/search_dsl/morpheme/prefix_artist_aggs_with_synonym.dsl", "count");

		return result;
	}

	public static void main(String[] args) {
		MSearchServiceImpl searchService = new MSearchServiceImpl();

		String json = searchService.overViewArtisDetailtAggs("198094");

		System.out.println(json);
	}

}
