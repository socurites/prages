package com.melon.helloes.runner;

import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.melon.helloes.domain.ArtistDoc;
import com.melon.helloes.domain.SongDoc;
import com.melon.helloes.util.MCatUtil;
import com.melon.helloes.util.MDateUtil;
import com.melon.helloes.util.MJsonUtil;

/**
 * 컨텐츠 크롤링 서비스
 * 
 * @author socurites
 *
 */
public class MCrawler {
	/**
	 * songDetailUrl 웹페이지를 크롤링한 후, 특정 정보를 Map 형태로 반환.
	 * 
	 * @param songDetailUrl
	 * @return
	 */
	public SongDoc crawlSongDetail(String songDetailUrl) {
		String jsonString = MCatUtil.cat(songDetailUrl);
		
		
		JsonElement root = new JsonParser().parse(jsonString);
		
		if ( !root.getAsJsonObject().get("lyricEntity").isJsonNull() && !root.getAsJsonObject().get("lyricEntity").getAsJsonObject().get("LYRIC").isJsonNull() ) {
		
			JsonObject songInfoObj = root.getAsJsonObject().get("songInfo").getAsJsonObject();
			JsonObject lyricEntityObj = root.getAsJsonObject().get("lyricEntity").getAsJsonObject();
			JsonArray lyricistArray = nullToEmptyJsonArray(root.getAsJsonObject().get("lystSongRoleNameBasket"));
			JsonArray composerArray = nullToEmptyJsonArray(root.getAsJsonObject().get("cmpsrSongRoleNameBasket"));
			JsonArray arrangerArray = nullToEmptyJsonArray(root.getAsJsonObject().get("arngrSongRoleNameBasket"));
			
			
			String songId = songInfoObj.get("SONGID").getAsString();
			String title = songInfoObj.get("SONGNAMEWAP").getAsString();
			String albumName = songInfoObj.get("ALBUMNAMEWAP").getAsString();
			String artistId = songInfoObj.get("ARTISTIDBASKET").getAsString().split("\r\n")[0];
			String artistName = songInfoObj.get("ARTISTNAMEBASKET").getAsString().split("\r\n")[0];
			List<String> genres = getGeneresAsList(songInfoObj.get("SONGGNRBASKET").getAsString().split("")[0].split("=")[1]);
			String issueDate = nullToBlank(songInfoObj.get("ISSUEDATE"));
			String labelName = nullToBlank(songInfoObj.get("PLANCNPY"));
			String lyric = lyricEntityObj.get("LYRIC").getAsString().replaceAll("<BR>", " ").replaceAll("<br>", " ").replaceAll("<br/>", " ").replaceAll("</br>", " ").replaceAll("\t", " ");
			List<String> lyricists = MJsonUtil.getJsonArrayAsList(lyricistArray);
			List<String> composers = MJsonUtil.getJsonArrayAsList(composerArray);
			List<String> arrangers = MJsonUtil.getJsonArrayAsList(arrangerArray);
			
			String albumImagePath = songInfoObj.get("ALBUMIMGPATH").getAsString();
			
			
			SongDoc doc = new SongDoc();
			doc.setSongId(songId);
			doc.setTitle(title);
			doc.setAlbumName(albumName);
			doc.setArtistId(artistId);
			doc.setArtistName(artistName);
			doc.setIssueDate(MDateUtil.parseIssueDate(issueDate));
			doc.setLabelName(labelName);
			doc.setLyric(lyric);
			doc.setGenres(genres);
			doc.setLyricists(lyricists);
			doc.setComposers(composers);
			doc.setArrangers(arrangers);
			
			doc.setAlbumImagePath(albumImagePath);
			
			if ( !artistId.equals("2727") ) {
				ArtistDoc artistDoc = this.crawlArtistDetail("http://www.melon.com/artist/timeline.json?artistId=" + artistId);
				
				doc.setActType(artistDoc.getActType());
				doc.setArtistImagePath(artistDoc.getArtistImagePath());
			}
		
			return doc;
		} else {
			return null;
		}
	}
	
	public ArtistDoc crawlArtistDetail(String artistDetailUrl) {
		String jsonString = MCatUtil.cat(artistDetailUrl);
		
		
		JsonElement root = new JsonParser().parse(jsonString);
		
		JsonObject songInfoObj = root.getAsJsonObject().get("artistInfo").getAsJsonObject();
		
		String actType = nullToBlank(songInfoObj.get("ACTTYPENAME"));
		String artistImagePath = nullToBlank(songInfoObj.get("ARTISTIMGPATH"));
		
		ArtistDoc doc = new ArtistDoc();
		doc.setActType(actType);
		doc.setArtistImagePath(artistImagePath);
		
		return doc;
	}
	
	private List<String> getGeneresAsList(String genres) {
		String[] tokens = genres.split(" / ");

		return Arrays.asList(tokens);
	}
	
	private String nullToBlank(JsonElement el) {
		if ( el.isJsonNull() ) {
			return "";
		} else {
			return el.getAsString();
		}
	}
	
	private JsonArray nullToEmptyJsonArray(JsonElement el) {
		if ( el.isJsonArray() ) {
			return el.getAsJsonArray();
		} else {
			return new JsonArray();
		}
	}
	
	public static void main(String[] args) {
		MCrawler crawler = new MCrawler();
		SongDoc doc = crawler.crawlSongDetail("http://www.melon.com/song/detail.json?songId=3053257");
		
		System.out.println(doc.formatStringForBulk());
	}
}
