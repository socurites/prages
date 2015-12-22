package com.melon.helloes.service;

import java.util.List;
import java.util.TreeSet;

import com.melon.helloes.domain.MToken;
import com.melon.helloes.domain.TermCount;

public interface MAnalysisService {
	List<MToken> morphemeIntroAnalyze(String line);

	TreeSet<TermCount> morphemeIntroAnalyzeLyrics(String artistId);
}
