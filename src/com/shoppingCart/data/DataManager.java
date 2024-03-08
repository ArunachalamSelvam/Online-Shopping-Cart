package com.shoppingCart.data;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.shoppingCart.model.Brand;
import com.shoppingCart.model.Category;
import com.shoppingCart.model.Customer;
import com.shoppingCart.model.Product;

public class DataManager {
	
	private static DataManager dataManager=null;
	private Map<String,Customer> customerMap = new LinkedHashMap<>();
	
	private Map<Integer,Product> productMap = new LinkedHashMap<>();
	
	private Map<String,Category> categoryMap = new LinkedHashMap<String, Category>();
	private Map<String,Brand> brandMap = new LinkedHashMap<String, Brand>();
	
	private DataManager() {}
	
	public static DataManager getData() {
		if(dataManager == null) {
			dataManager = new DataManager();
		}
		
		return dataManager;
	}

	public Map<String,Customer> getCustomerMap() {
		return customerMap;
	}

	public Map<Integer,Product> getProductMap() {
		return productMap;
	}

	public Map<String,Category> getCategoryMap() {
		return categoryMap;
	}

	public Map<String,Brand> getBrandMap() {
		return brandMap;
	}
}
