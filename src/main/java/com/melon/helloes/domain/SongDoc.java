package com.melon.helloes.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.melon.helloes.util.MDateUtil;
import com.melon.helloes.util.MLanguageUtil;

public class SongDoc {
	private String songId;

	private String title;

	private String albumName;

	private String artistId;

	private String artistName;

	private Date issueDate;

	private String labelName;

	private String lyric;

	private List<String> genres = new ArrayList<String>();
	private List<String> lyricists = new ArrayList<String>();
	private List<String> composers = new ArrayList<String>();
	private List<String> arrangers = new ArrayList<String>();
	
	private String actType;
	
	private String albumImagePath;
	private String artistImagePath;

	/**
	 * @return the songId
	 */
	public String getSongId() {
		return songId;
	}

	/**
	 * @param songId
	 *            the songId to set
	 */
	public void setSongId(String songId) {
		this.songId = songId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the albumName
	 */
	public String getAlbumName() {
		return albumName;
	}

	/**
	 * @param albumName
	 *            the albumName to set
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	/**
	 * @return the artistId
	 */
	public String getArtistId() {
		return artistId;
	}

	/**
	 * @param artistId
	 *            the artistId to set
	 */
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	/**
	 * @return the artistName
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * @param artistName
	 *            the artistName to set
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	/**
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate
	 *            the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * @param labelName
	 *            the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * @return the lyric
	 */
	public String getLyric() {
		return lyric;
	}

	/**
	 * @param lyric
	 *            the lyric to set
	 */
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	/**
	 * @return the genres
	 */
	public List<String> getGenres() {
		return genres;
	}

	/**
	 * @param genres
	 *            the genres to set
	 */
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	/**
	 * @return the lyricists
	 */
	public List<String> getLyricists() {
		return lyricists;
	}

	/**
	 * @param lyricists
	 *            the lyricists to set
	 */
	public void setLyricists(List<String> lyricists) {
		this.lyricists = lyricists;
	}

	/**
	 * @return the composers
	 */
	public List<String> getComposers() {
		return composers;
	}

	/**
	 * @param composers
	 *            the composers to set
	 */
	public void setComposers(List<String> composers) {
		this.composers = composers;
	}

	/**
	 * @return the arrangers
	 */
	public List<String> getArrangers() {
		return arrangers;
	}

	/**
	 * @param arrangers
	 *            the arrangers to set
	 */
	public void setArrangers(List<String> arrangers) {
		this.arrangers = arrangers;
	}
	
	/**
	 * @return the actType
	 */
	public String getActType() {
		return actType;
	}

	/**
	 * @param actType the actType to set
	 */
	public void setActType(String actType) {
		this.actType = actType;
	}

	/**
	 * @return the albumImagePath
	 */
	public String getAlbumImagePath() {
		return albumImagePath;
	}

	/**
	 * @param albumImagePath the albumImagePath to set
	 */
	public void setAlbumImagePath(String albumImagePath) {
		this.albumImagePath = albumImagePath;
	}

	/**
	 * @return the artistImagePath
	 */
	public String getArtistImagePath() {
		return artistImagePath;
	}

	/**
	 * @param artistImagePath the artistImagePath to set
	 */
	public void setArtistImagePath(String artistImagePath) {
		this.artistImagePath = artistImagePath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result =  "\"songId\" : \"" + this.songId + "\", \n"
			 + "\"title_analz\" : \"" + escapeString(this.title) + "\", \n"
			 + "\"title_token\" : \"" + escapeString(this.title) + "\", \n"
			 + "\"title_token_eng\" : \"" + MLanguageUtil.convertKo2Eng(escapeString(this.title)) + "\", \n"
			 + "\"album_name_analz\" : \"" + escapeString(this.albumName) + "\", \n"
			 + "\"album_name_token\" : \"" + escapeString(this.albumName) + "\", \n"
			 + "\"album_name_token_eng\" : \"" + MLanguageUtil.convertKo2Eng(escapeString(this.albumName)) + "\", \n"
			 + "\"artist_id\" : \"" + this.artistId + "\", \n"
			 + "\"artist_name_analz\" : \"" + escapeString(this.artistName) + "\", \n"
			 + "\"artist_name_token\" : \"" + escapeString(this.artistName) + "\", \n"
			 + "\"artist_name_token_eng\" : \"" + MLanguageUtil.convertKo2Eng(escapeString(this.artistName)) + "\", \n"
			 + "\"label_name_analz\" : \"" + escapeString(this.labelName) + "\", \n"
			 + "\"label_name_token\" : \"" + escapeString(this.labelName) + "\", \n"
			 + "\"label_name_token_eng\" : \"" + MLanguageUtil.convertKo2Eng(escapeString(this.labelName)) + "\", \n"
			 + "\"issue_date\" : \"" + MDateUtil.formatIssueDate(this.issueDate) + "\", \n"
			 + "\"act_type\" : \"" + this.actType + "\", \n"
			 + "\"album_image_path\" : \"" + this.albumImagePath + "\", \n"
			 + "\"artist_image_path\" : \"" + this.artistImagePath + "\", \n"
			 + "\"genres\" : " + formatList(this.genres) + ", \n"
			 + "\"lyricists\" : " + formatList(this.lyricists) + ", \n"
			 + "\"composers\" : " + formatList(this.composers) + ", \n"
			 + "\"arrangers\" : " + formatList(this.arrangers) + ", \n"
			 + "\"lyric_analz\" : \"" + escapeString(this.lyric) + "\", \n"
			 + "\"lyric_token\" : \"" + escapeString(this.lyric) + "\""
			 ;
		
		return result;
	}
	
	private String escapeString(String str) {
		return str.replaceAll("'", "\\u0027")
				.replaceAll("\"", "\\u0022");
	}
	
	public String formatStringForBulk() {
		return this.toString().replaceAll("\r\n", " ").replaceAll("\n", " ");
	}
	
	private String formatList(List<String> list) {
		String result = "[";
		for ( int i = 0; i < list.size(); i++ ) {
			result += "\"" + escapeString(list.get(i)) + "\"";
			
			if ( i != list.size() -1 ) {
				result += ",";
			}
		}
		
		result += "]";
		
		return result;
	}
	
}
