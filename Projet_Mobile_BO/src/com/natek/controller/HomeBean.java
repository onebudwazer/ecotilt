package com.natek.controller;


import fr.project.appui.model_v2.Categories;
import fr.project.appui.model_v2.Category;

public class HomeBean {

	private Categories pCategories;
	private Category pSelectedCategory;
	
	/*###############################################################################
	 * 							Builders
	 * ##############################################################################
	 */
	public HomeBean(){
		pCategories = new Categories();
		
	}
	
	/*###############################################################################
	 * 							Getters & Setters
	 * ##############################################################################
	 */

	public Categories getCategories() {
		return pCategories;
	}

	public void setCategories(Categories categories) {
		pCategories = categories;
	}

	public Category getSelectedCategory() {
		return pSelectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		pSelectedCategory = selectedCategory;
	}

	/*###############################################################################
	 * 							Public methods
	 * ##############################################################################
	 */
	
	public String newCategory(){
		pSelectedCategory = new Category();
		return "newCategory";
	}
	
	public String setCategory(){
		return "setCategory";
	}
	
	public String backToHome(){
		return "backToHome";
	}
	
	public String saveCategory(){
		try{
			System.out.println("application name: "+pSelectedCategory.getNom());
			pCategories.getCategories().add(pSelectedCategory);
			//TODO appeler la methode du buisness
			return "true";
		}
		catch(Exception e){
			e.printStackTrace();
			return "false";
		}
		
	}
	
}
