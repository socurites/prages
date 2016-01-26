package com.melon.helloes.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.melon.helloes.domain.MToken;
import com.melon.helloes.domain.TermCount;
import com.melon.helloes.runner.MMecabKoPosTagger;
import com.melon.helloes.service.MAnalysisService;

/**
 * 컨텐츠 검색 서비스
 * 
 * @author socurites
 *
 */
@Service
public class MAnalysisServiceImpl extends MAbstractSearchServiceImpl implements MAnalysisService {
	public List<MToken> morphemeIntroAnalyze(String line) {
		MMecabKoPosTagger tagger = new MMecabKoPosTagger();
		List<MToken> tokens = tagger.analyzeLine(line);

		return tokens;
	}

	@Override
	public TreeSet<TermCount> morphemeIntroAnalyzeLyrics(String artistId) {
		int searchDepth = 1000;

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("artistId", artistId);
		paramMap.put("size", searchDepth + "");

		String resultInfo = doSearch(paramMap, "helloes/search_dsl/common/get_artist_lyric_list.dsl", null);

		JsonElement root = new JsonParser().parse(resultInfo);
		JsonElement jsonElement = null;
		String lyric = null;
		MMecabKoPosTagger tagger = new MMecabKoPosTagger();
		List<TreeSet<TermCount>> nounMaps = new ArrayList<TreeSet<TermCount>>();
		TreeSet<TermCount> nounMap = null;
		if (root.getAsJsonObject().get("hits").getAsJsonObject().get("total").getAsInt() > 0) {
			for (int i = 0; i < root.getAsJsonObject().get("hits").getAsJsonObject().get("hits").getAsJsonArray()
					.size(); i++) {
				jsonElement = root.getAsJsonObject().get("hits").getAsJsonObject().get("hits").getAsJsonArray().get(i)
						.getAsJsonObject().get("fields").getAsJsonObject().get("lyric_analz");
				lyric = jsonElement.getAsString();

				if (lyric != null && !lyric.isEmpty()) {
					nounMap = tagger.getNounMap(lyric);
					nounMaps.add(nounMap);
				}
			}

		}

		return tagger.getFilteredNounMap(nounMaps);
	}

	public static void main(String[] args) {
		MAnalysisServiceImpl serviceImpl = new MAnalysisServiceImpl();

		// String line = "너는 내 취향저격 내 취향저격 말하지 않아도 느낌이 와";
		// List<MToken> tokens = serviceImpl.morphemeIntroAnalyze(line);
		// System.out.println(tokens);

		TreeSet<TermCount> result = serviceImpl.morphemeIntroAnalyzeLyrics("198094");
		System.out.println(result);

	}

}
