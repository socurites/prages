package com.melon.helloes.domain;

public class Synonym {
	private String term;
	private String synonym;
	
	public Synonym() {}
	
	public Synonym(String term, String synonym) {
		this.term = term;
		this.synonym = synonym;
	}
	
	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return the synonym
	 */
	public String getSynonym() {
		return synonym;
	}
	/**
	 * @param synonym the synonym to set
	 */
	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}
}
