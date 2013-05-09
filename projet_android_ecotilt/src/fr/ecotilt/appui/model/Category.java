package fr.ecotilt.appui.model;

import java.io.Serializable;

public class Category implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 8799696442923745502L;

	private long		categoryId;

	private String		categoryName;

	private String		description;

	public Category() {
	}

	public Category(String categoryName, String description) {
		this.categoryName = categoryName;
		this.description = description;
	}

	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}