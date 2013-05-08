package fr.ecotilt.appui.model;

import java.util.List;

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
	private long		categoryId;

	@Column(name = "CATEGORY_NAME", nullable = true, length = 100, unique = true)
	private String		categoryName;

	@Column(name = "CATEGORIE_DESCRIPTION")
	private String		description;
	
	private List<AGenericObject> items;

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

	public List<AGenericObject> getItems() {
		return items;
	}

	public void setItems(List<AGenericObject> items) {
		this.items = items;
	}
	
	public boolean addItem(AGenericObject item){
		try{
			items.add(item);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean removeItem(AGenericObject item){
		try{
			items.remove(item);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

}