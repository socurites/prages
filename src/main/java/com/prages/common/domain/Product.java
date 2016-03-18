package com.prages.common.domain;

import java.util.Date;

import com.prages.common.PragEsDomain;


/**
 * 상품 도메인 클래스.
 * 
 * @author socurites
 *
 */
public class Product extends PragEsDomain {
	/** 아이디. */
	protected String id;
	
	/** 상품명. */
	protected String title;
	
	/** 카테고리 코드. */
	protected String categoryCode;
	
	/** 카테고리 명. */
	protected String categoryName;

	/** 가격. */
	protected double price;
	
	/** 등록일시. */
	protected Date logDate;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the categoryCode
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @param categoryCode the categoryCode to set
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the logDate
	 */
	public Date getLogDate() {
		return logDate;
	}

	/**
	 * @param logDate the logDate to set
	 */
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
}
