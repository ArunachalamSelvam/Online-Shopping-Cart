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
	private AdminAuthenticator authenticator;
	private String userName;
	private String password;

	private Admin() {
		this.authenticator = new AdminAuthenticator();
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

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public AdminAuthenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(AdminAuthenticator authenticator) {
		this.authenticator = authenticator;
	}

	public Product createProduct(String category, String brand, String model, double price, int stock,
			Features features) {
		if (authenticator.isAdminLoggedIn()) {

			Product product = new Product(category, brand, model, price, stock, features);

			return product;
		}

		else {
			System.out.println("Access Denied. Admin Not Logged in.");
			return null;
		}
	}

	public void deleteProduct(int productId) {

		if (authenticator.isAdminLoggedIn()) {
			Product product = DataManager.getData().getProductMap().get(productId);

			if (product != null) {
				ShoppingCartService.deleteProductFromFile(productId);
				DataManager.getData().getProductMap().remove(productId);
				System.out.println();
				System.out.println("Product Deleted SuccessFully.");
			} else {
				System.out.println("Product Could Not Deleted.");
			}
		}

		else {
			System.out.println("Access Denied. Admin Not Logged in.");
		}
	}

	public void addStock(int productId, int count) {

		if (authenticator.isAdminLoggedIn()) {

			Product product = DataManager.getData().getProductMap().get(productId);

			if (product != null) {

				int stock = product.getStock();

				product.setStock(stock + count);
				DataManager.getData().getProductMap().put(product.getProductId(), product);
				System.out.println("Product's Stock Added Successfully.");
			} else {
				System.out.println("Product Cannot Be Found.");
			}

		}

		else {
			System.out.println("Access Denied. Admin Not Logged in.");
		}

	}

	public void viewProducts() {

		if (authenticator.isAdminLoggedIn()) {
			int count = 0;

			for (Map.Entry<Integer, Product> entry : DataManager.getData().getProductMap().entrySet()) {
				Product p = entry.getValue();
				if (count == 0) {
					System.out.printf("%-15s%-15s%-15s%-20s%-13s%-10s%n", "Product Id", "Category", "Brand", "Model",
							"Price", "Stock");
					System.out.println();
				}
				System.out.printf("%-15s%-15s%-15s%-20s%-13s%-10s%n", p.getProductId(),
						p.getCategory().getCategoryName(), p.getBrand().getBrandName(), p.getModel(), p.getPrice(),
						p.getStock());

				count++;
			}
		}

		else {
			System.out.println("Access Denied. Admin Not Logged in.");
		}

	}

}
