package com.natek.controller;

import java.util.ArrayList;
import java.util.List;

import fr.ecotilt.appui.model.AGenericObject;
import fr.ecotilt.appui.model.Category;




public class HomeBean {

	private ArrayList<Category> pCategories;
	private Category pSelectedCategory;
	private List<AGenericObject> pitems;
	private AGenericObject pSelectedItem;
	
	/*###############################################################################
	 * 							Builders
	 * ##############################################################################
	 */
	public HomeBean(){
		pCategories = new ArrayList<Category>();
		
	}
	
	/*###############################################################################
	 * 							Getters & Setters
	 * ##############################################################################
	 */

	public ArrayList<Category> getCategories() {
		return pCategories;
	}

	public void setCategories(ArrayList<Category> categories) {
		pCategories = categories;
	}

	public Category getSelectedCategory() {
		return pSelectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		pSelectedCategory = selectedCategory;
	}
	
	public List<AGenericObject> getItems() {
		return pitems;
	}

	public void setItems(List<AGenericObject> items) {
		pitems = items;
	}

	public AGenericObject getSelectedItem() {
		return pSelectedItem;
	}

	public void setSelectedItem(AGenericObject pSelectedItem) {
		this.pSelectedItem = pSelectedItem;
	}

	/*###############################################################################
	 * 							Public methods
	 * ##############################################################################
	 */
	
	public String setCategory(){
		return "setCategory";
	}

	public String newCategory(){
		pSelectedCategory = new Category();
		return "newCategory";
	}
	
	public String saveCategory(){
		try{
			System.out.println("categorie name: "+pSelectedCategory.getCategoryName());
			pCategories.add(pSelectedCategory);
			//TODO appeler la methode du buisness
			return "true";
		}
		catch(Exception e){
			e.printStackTrace();
			return "false";
		}
	}
	
	public String backToHome(){
		return "backToHome";
	}
	
	public String viewCategory(){
		return "viewCategory";
	}
	
	public String saveItem(){
		try{
			System.out.println("item name : "+pSelectedItem.getName());
		pitems.add(pSelectedItem);
		//TODO appeler la methode du business
		return "true";
		}
		catch(Exception e){
			e.printStackTrace();
			return "false";
		}
	}
	
}
