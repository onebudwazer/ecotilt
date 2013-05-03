package com.natek.controller;

import fr.ecotilt.appui.model.Category;

public class HomeBean {

	private Category	pSelectedCategory;

	/*
	 * ##########################################################################
	 * ##### Builders
	 * ###########################################################
	 * ###################
	 */
	public HomeBean() {

	}

	/*
	 * ##########################################################################
	 * ##### Getters & Setters
	 * ##################################################
	 * ############################
	 */

	public Category getCategories() {
		return pSelectedCategory;
	}

	public void setCategories(Category categories) {
		pSelectedCategory = categories;
	}

	public Category getSelectedCategory() {
		return pSelectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		pSelectedCategory = selectedCategory;
	}

	/*
	 * ##########################################################################
	 * ##### Public methods
	 * #####################################################
	 * #########################
	 */

	public String newCategory() {
		pSelectedCategory = new Category();
		return "newCategory";
	}

	public String setCategory() {
		return "setCategory";
	}

	public String backToHome() {
		return "backToHome";
	}

	public String saveCategory() {
		try {
			System.out.println("application name: "
					+ pSelectedCategory.getCategoryName());
			pSelectedCategory.getCategoryId();
			// TODO appeler la methode du buisness
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}

	}

}
