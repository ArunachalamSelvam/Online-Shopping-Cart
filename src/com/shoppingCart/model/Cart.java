package com.shoppingCart.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.shoppingCart.data.DataManager;
import com.shoppingCart.util.InputScanner;

public class Cart {
	
	private Map<Product, Integer> products;

	public Cart() {
		this.products = new LinkedHashMap<Product, Integer>();
	}

	public void addItem(Product product, int quantity) {
		int n = products.put(product, products.getOrDefault(product, 0) + quantity);
		if (n > 0) {
			System.out.println("Product Added to Cart Successfully.");
		} else {
			System.out.println("Product Could Not Added To Cart.");
		}
	}

	public void decreaseCount(Product product, int quantity) {
		if (products.get(product) > quantity) {
			int currentQuantity = products.getOrDefault(product, 0);
			products.put(product, currentQuantity - quantity);
		} else {
			products.clear();
		}
	}

	public void clearCart() {
		products.clear();

		if (products.isEmpty()) {
			System.out.println("Cart Cleared Successfully.");
		} else {
			System.out.println("Cart Clear Failed.");
		}
	}

	public void removeItem(Map<Product, Integer> map) {

		for (Product product : map.keySet()) {
			products.remove(product);
		}
	}

	public void viewCart() {
		int i = 1;
		if (products.isEmpty()) {
			System.out.println("Your Cart Is Empty.");
		} else {
			for (Map.Entry<Product, Integer> entry : products.entrySet()) {
				Product product = entry.getKey();
				int count = entry.getValue();
				System.out.println(i++ + ". " + product.getBrand().getBrandName() + " " + product.getModel() + " "
						+ product.getCategory().getCategoryName() + "\t" + product.getPrice() + "\t" + count);

			}
		}
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

}
