package fr.ecotilt.appui.model;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "CATEGORY")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicUpdate
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID", unique = true)
	private long	categoryId;

	@Column(name = "CATEGORY_NAME", nullable = true, length = 100, unique = true)
	private String	categoryName;

	@Column(name = "CATEGORIE_DESCRIPTION")
	private String	description;

	private Set<AGenericObject> Items;
	
	public Category() {
	}

	public long getCategoryId() {
		return this.categoryId;
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
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

	public Set<AGenericObject> getItems() {
		return Items;
	}

	public void setItems(Set<AGenericObject> items) {
		Items = items;
	}
	
	
	
}