package com.melon.helloes.domain;

public class TermCount implements Comparable<TermCount> {
	private String term;
	private int count;

	public TermCount() {

	}

	public TermCount(String term, int count) {
		this.term = term;
		this.count = count;
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
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(TermCount other) {
		return -(this.count - other.count);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else {
			if (this.term.equals(((TermCount) other).term)) {
				return true;
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.term + ":" + this.count;
	}

}
