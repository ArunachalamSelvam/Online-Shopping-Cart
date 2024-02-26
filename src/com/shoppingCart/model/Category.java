package com.shoppingCart.model;

import com.shoppingCart.util.InputScanner;

public class Category {
	private String categoryName;
	
	public Category() {
		this.categoryName = InputScanner.getStringInUpper("Enter The Category Name : ");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return categoryName;
	}
	public Category(String category) {
		
		this.categoryName = category;
	}


	public String getCategoryName() {
		return categoryName;
	}

	public void setCategory(String category) {
		this.categoryName = category;
	}
}
