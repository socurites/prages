package com.melon.helloes.service;

public interface MSearchService {
	String overviewIntroSearch(String term);
	
	String overViewPrefixArtistAggs(String term, int size);
	
	String overViewArtisDetailtAggs(String artistId);
	
	String morphemePrefixArtistAggsWithSynonym(String term, int size);
}
