package com.melon.helloes.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.melon.helloes.domain.MToken;
import com.melon.helloes.domain.TermCount;
import com.melon.helloes.service.MAnalysisService;
import com.melon.helloes.service.MSearchService;

@RestController
@RequestMapping("/morpheme")
public class MorphemeController {
	@Resource
	private MAnalysisService analysisService;
	
	@Resource
	private MSearchService searchService;
	
	@RequestMapping("/intro")
	public ModelAndView introView() {
		return new ModelAndView("site.morpheme.intro");
	}
	
	@RequestMapping("/synonym")
	public ModelAndView synonymView() {
		return new ModelAndView("site.morpheme.synonym");
	}
	
	@RequestMapping(value="/intro/analyze", produces = "application/json; charset=utf8")
	public String analyze(@RequestParam("line") String line) throws UnsupportedEncodingException {
		List<MToken> tokens = analysisService.morphemeIntroAnalyze(URLDecoder.decode(line, "UTF-8"));
		
		Gson gson = new Gson();
		
		return gson.toJson(tokens);
	}
	
	@RequestMapping(value="/intro/analyze_artist", produces = "application/json; charset=utf8")
	public String analyzeLyrics(@RequestParam("artistId") String artistId) throws UnsupportedEncodingException {
		TreeSet<TermCount> termCounts = analysisService.morphemeIntroAnalyzeLyrics(URLDecoder.decode(artistId, "UTF-8"));
		
		Gson gson = new Gson();
		
		return gson.toJson(termCounts);
	}
	
	@RequestMapping(value="/search/prefix_artist_aggs", produces = "application/json; charset=utf8")
	public String prefixArtistAggs(@RequestParam("term") String term, @RequestParam("size") int size) throws UnsupportedEncodingException {
		return searchService.morphemePrefixArtistAggsWithSynonym(URLDecoder.decode(term, "UTF-8"), size);
	}
}
