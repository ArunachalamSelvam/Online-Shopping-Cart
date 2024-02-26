package com.shoppingCart.adminSystem;

import java.util.Map;

import com.shoppingCart.data.DataManager;
import com.shoppingCart.model.Category;
import com.shoppingCart.model.Product;
import com.shoppingCart.service.ShoppingCartService;
import com.shoppingCart.util.Features;
import com.shoppingCart.util.InputScanner;

public class Admin {
	private static Admin admin;
	private String userName;
	private String password;

	private Admin() {
		this.userName = "admin@gmail.com";
		this.password = "admin";
	}

	public static Admin getAdmin() {
		if (admin == null) {
			return admin = new Admin();
		}

		else {
			return admin;
		}
	}
	
	public Product createProduct() {
		String category = InputScanner.getString("Enter The Category Name : ");
		String brand = InputScanner.getString("Enter The Brand Name : ");
		String model =  InputScanner.getString("Enter The Model Name : ").intern();
		double price = 	InputScanner.getDouble("Enter The Price : ");
		int stock = InputScanner.getInt("Enter The Stock : ");
		
		Features features = ShoppingCartService.createFeatures(new Category(category));
		
		Product product = new Product(category, brand, model, price, stock, features);
		
		return product;
	}
	
//	public void addProduct() {
//		
//		
//		Product p = DataManager.productMap.put(product.getProductId(), product);
//
//		if (p != null) {
//			System.out.println("Product added Successfully.");
//		} else {
//			System.out.println("Product Could not Be Added.");
//		}
//	}

	public void deleteProduct() {
		int productId = InputScanner.getInt("Enter The Product Id : ");
		Product p = DataManager.productMap.remove(productId);

		if (p != null) {
			System.out.println();
			System.out.println("Product Deleted SuccessFully.");
		} else {
			System.out.println("Product Could Not Deleted.");
		}
	}

	public void addStock() {
		int productId = InputScanner.getInt("Enter The Product Id : ");
		Product product = DataManager.productMap.get(productId);

		if (product != null) {
			System.out.println();
			System.out.printf("%-10s%-10s%-10s%-15s%-10s%-10s%n", "Product Id", "Category", "Brand", "Model", "Price",
					"Stock");
			System.out.printf("%-10s%-10s%-10s%-15s%-10s%-10s%n", product.getProductId(),
					product.getCategory().getCategoryName(), product.getBrand().getBrandName(), product.getModel(),
					product.getPrice(), product.getStock());

			System.out.println();
			int stock = product.getStock();
			int count = InputScanner.getInt("Enter The Stock Count You Want to Add : ");
			product.setStock(stock + count);
			DataManager.productMap.put(product.getProductId(), product);
			System.out.println("Product's Stock Added Successfully.");
		} else {
			System.out.println("Product Cannot Be Found.");
		}

	}

	public void viewProducts() {
		int count = 0;

		for (Map.Entry<Integer, Product> entry : DataManager.productMap.entrySet()) {
			Product p = entry.getValue();
			if (count == 0) {
				System.out.printf("%-15s%-15s%-15s%-20s%-13s%-10s%n", "Product Id", "Category", "Brand", "Model",
						"Price", "Stock");
				System.out.println();
			}
			System.out.printf("%-15s%-15s%-15s%-20s%-13s%-10s%n", p.getProductId(), p.getCategory().getCategoryName(),
					p.getBrand().getBrandName(), p.getModel(), p.getPrice(), p.getStock());

			count++;
		}
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
