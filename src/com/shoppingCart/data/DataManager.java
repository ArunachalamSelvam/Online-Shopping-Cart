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
	public static Map<String,Customer> customerMap = new LinkedHashMap<>();
	
	public static Map<Integer,Product> productMap = new LinkedHashMap<>();
	
	public static Map<String,Category> categoryMap = new LinkedHashMap<String, Category>();
	public static Map<String,Brand> brandMap = new LinkedHashMap<String, Brand>();
}
