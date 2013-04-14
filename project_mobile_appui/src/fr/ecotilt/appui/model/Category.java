package fr.ecotilt.appui.model;


import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "CATEGORY")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category {

	private long categoryId;
	private String categoryName;

	public Category() {
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}

	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID", unique = true)
	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "CATEGORY_NAME", nullable = true, length=100, unique = true)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}