package com.shoppingCart.model;

import com.shoppingCart.util.InputScanner;

public class Brand {
	private String brandName;
	
	public Brand() {
		this.brandName = InputScanner.getStringInUpper("Enter The Brand Name : ");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return brandName;
	}
	public Brand(String brand) {
		this.brandName = brand;
	}

		
	public String getBrandName() {
		return brandName;
	}

	public void setBrand(String brand) {
		this.brandName = brand;
	}
}
