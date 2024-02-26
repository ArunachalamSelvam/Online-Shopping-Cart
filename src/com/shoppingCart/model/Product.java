package com.shoppingCart.model;

import com.shoppingCart.util.Features;


public class Product {
	public static int id = 1;
	private int productId;
	private Category category;
	private Brand brand;
	private String model;
	private double price;
	private int stock;
	
	private Features features;
	
//	public Product() {
//		this.productId = id++;
//		this.category = new Category();
//		this.brand = new Brand();
//		this.model = InputScanner.getString("Enter The Model Name : ").intern();
//		this.price = InputScanner.getDouble("Enter The Price : ");
//		this.stock = InputScanner.getInt("Enter The Stock : ");
//		this.features = ShoppingCartService.createFeatures(category);
//	}
	
	public Product(String category, String brand, String model, double price, int stock, Features features) {
		this.productId= id++;
		this.category = new Category(category);
		this.brand = new Brand(brand);
		this.model = model;
		this.price = price;
		this.stock = stock;
		this.features = features;
	}
	
	
	public static void toString(Product product) {
		System.out.println("\n"+"\t"+product.getBrand().getBrandName()+" "+ product.getModel() +" " +product.getCategory().getCategoryName() +"\n"+"\t" +"Price : " + product.getPrice());
		product.getFeatures().displayFeatures();
	}
	public static void setId(int id) {
		Product.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public Features getFeatures() {
		return features;
	}
	public void setFeatures(Features features) {
		this.features = features;
	}
	
	
}
