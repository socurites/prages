package com.melon.helloes.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.melon.helloes.service.MSearchService;

@RestController
@RequestMapping("/overview")
public class OverviewController {
	@Resource
	private MSearchService searchService;
	
	@RequestMapping("/intro")
	public ModelAndView introView() {
		return new ModelAndView("site.overview.intro");
	}
	
	@RequestMapping("/search")
	public ModelAndView searchView() {
		return new ModelAndView("site.overview.search");
	}
	
	@RequestMapping(value="/intro/search", produces = "application/json; charset=utf8")
	public String search(@RequestParam("term") String term) throws UnsupportedEncodingException {
		return searchService.overviewIntroSearch(URLDecoder.decode(term, "UTF-8"));
	}
	
	@RequestMapping(value="/search/prefix_artist_aggs", produces = "application/json; charset=utf8")
	public String prefixArtistAggs(@RequestParam("term") String term, @RequestParam("size") int size) throws UnsupportedEncodingException {
		return searchService.overViewPrefixArtistAggs(URLDecoder.decode(term, "UTF-8"), size);
	}
	
	@RequestMapping(value="/search/artist_detail_aggs", produces = "application/json; charset=utf8")
	public String artistDetailAggs(@RequestParam("artistId") String artistId) throws UnsupportedEncodingException {
		return searchService.overViewArtisDetailtAggs(URLDecoder.decode(artistId, "UTF-8"));
	}
}
