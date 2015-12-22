package com.melon.helloes.domain;

import java.util.HashSet;
import java.util.Set;

public class NetworkItem {
	private String name;
	private String artistName;
	private String type;
	private Set<String> dependArtists = new HashSet<String>();
	private Set<String> depends = new HashSet<String>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the depends
	 */
	public Set<String> getDepends() {
		return depends;
	}

	/**
	 * @param depends
	 *            the depends to set
	 */
	public void setDepends(Set<String> depends) {
		this.depends = depends;
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
		this.name = artistName.replaceAll(" ", "");
	}
	
	public void addDependArtist(String name) {
		this.dependArtists.add(name);
		this.depends.add(name.replaceAll(" ", ""));
		
	}

	/**
	 * @return the dependArtists
	 */
	public Set<String> getDependArtists() {
		return dependArtists;
	}

	/**
	 * @param dependArtists the dependArtists to set
	 */
	public void setDependArtists(Set<String> dependArtists) {
		this.dependArtists = dependArtists;
	}
}
