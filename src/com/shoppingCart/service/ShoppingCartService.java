package com.shoppingCart.service;

import com.shoppingCart.model.Brand;
import com.shoppingCart.model.Category;
import com.shoppingCart.model.Customer;
import com.shoppingCart.model.Order;
import com.shoppingCart.model.Product;
import com.shoppingCart.util.Address;
import com.shoppingCart.util.Color;
import com.shoppingCart.util.Country;
import com.shoppingCart.util.District;
import com.shoppingCart.util.Features;
import com.shoppingCart.util.InputScanner;
import com.shoppingCart.util.LaptopFeatures;
import com.shoppingCart.util.MobileFeatures;
import com.shoppingCart.util.State;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shoppingCart.adminSystem.Admin;
import com.shoppingCart.adminSystem.AdminAuthenticator;
import com.shoppingCart.data.DataManager;

public class ShoppingCartService {

	// Customer's View Options
	public static final int SHOPPING = 1;
	public static final int SEARCH = 2;
	public static final int MY_ORDERS = 3;
	public static final int MY_CART = 4;
	public static final int SIGN_OUT = 5;

	// Direct Buy Options
	public static final int ADD_TO_CART = 1;
	public static final int PURCHASE = 2;
	public static final int BACK = 3;

	// Buy Through Cart Option
	public static final int BUY_PRODUCTS = 1;
//	public static final int DECREASE_COUNT = 2;
	public static final int CLEAR_CART = 2;

	// Search Options
	public static final int VIEW_PRODUCTS = 1;
	public static final int PREVIOUS = 2;

	public static void addProduct(Product product) {
		String category = product.getCategory().getCategoryName();
		String brand = product.getBrand().getBrandName();

		if (DataManager.getData().getCategoryMap().containsKey(category)) {
			product.setCategory(DataManager.getData().getCategoryMap().get(category));

		} else {
			DataManager.getData().getCategoryMap().put(category, product.getCategory());
		}

		if (DataManager.getData().getBrandMap().containsKey(brand)) {
			product.setBrand(DataManager.getData().getBrandMap().get(brand));
//			product.getBrand().setBrand("Nokia");
		} else {
			DataManager.getData().getBrandMap().put(brand, product.getBrand());

		}

		if (!DataManager.getData().getProductMap().containsKey(product.getProductId())) {
			DataManager.getData().getProductMap().put(product.getProductId(), product);

					} else {
			System.out.println("Product Already Exists.");
		}

	}

	public static void addProductToFile(Product product) {
		
		String category = product.getCategory().getCategoryName();

		// File Writer Method
		if (category.equalsIgnoreCase("mobile") || category.equalsIgnoreCase("tablet")) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						"C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/mobile_product_file.csv",
						true));
				writer.write(product.toString());
				writer.newLine();
				writer.close();

			} catch (IOException io) {
				io.printStackTrace();
			}
		}

		else if (category.equalsIgnoreCase("laptop")) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						"C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/laptop_product_file.csv",
						true));
				writer.write(product.toString());
				writer.newLine();
				writer.close();

			} catch (IOException io) {
				io.printStackTrace();
			}
		}

	}

	public static void readProduct() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					"C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/mobile_product_file.csv")));
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				addProduct(parseProduct(line));
			}
			

			BufferedReader readers = new BufferedReader(new FileReader(new File(
					"C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/laptop_product_file.csv")));
			
			readers.readLine();
			while ((line = readers.readLine()) != null) {
				addProduct(parseProduct(line));
			}
			
			reader.close();
			
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public static Product parseProduct(String s) {
//		System.out.println(s);
		String[] st = s.split(",");
//		System.out.println(Arrays.toString(st));
		
		int productId = Integer.parseInt(st[0]);
		String category = st[1];
		String brand = st[2];
		String model = st[3];
		double price = Double.parseDouble(st[4]);
		int stock = Integer.parseInt(st[5]);

		Features features = null;
		if (category.equals("mobile") || category.equals("tablet")) {
			String screenSize = st[6];
			int batteryCapacity = Integer.parseInt(st[7]);
			int ramInGb = Integer.parseInt(st[8]);
			int storageInGb = Integer.parseInt(st[9]);
			int frontCameraCapacity = Integer.parseInt(st[10]);
			int rearCameraCapacity = Integer.parseInt(st[11]);
			String operatingSystem = st[12];
			Color color = Color.valueOf(st[13]);
			Country madeInCountry = Country.valueOf(st[14]);
			int madeInTheYear = Integer.parseInt(st[15]);
			float weight = Float.parseFloat(st[16]);

			features = new MobileFeatures(screenSize, batteryCapacity, ramInGb, storageInGb, frontCameraCapacity,
					rearCameraCapacity, operatingSystem, color, madeInCountry, madeInTheYear, weight);
		}

		else if (category.equals("laptop")) {
			String processor = st[6];
			int processorSpeed = Integer.parseInt(st[7]);
			String operatingSystem = st[8];

			int ramInGb = Integer.parseInt(st[9]);
			int storageInGb = Integer.parseInt(st[10]);
			String screenSize = st[11];
			String screenType = st[12];
			Color color = Color.valueOf(st[13]);
			Country madeInCountry = Country.valueOf(st[14]);
			int madeInTheYear = Integer.parseInt(st[15]);
			float weight = Float.parseFloat(st[16]);

			features = new LaptopFeatures(processor, processorSpeed, operatingSystem, ramInGb, storageInGb, screenSize,
					screenType, color, madeInCountry, madeInTheYear, weight);
		}

		return new Product(productId,category, brand, model, price, stock, features);
	}

	public static void deleteProductFromFile(int productId) {
		String category = DataManager.getData().getProductMap().get(productId).getCategory().getCategoryName();
		
		System.out.println(category);
		if(category.equalsIgnoreCase("mobile") || category.equalsIgnoreCase("tablet")) {
			
			try {
//				BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/mobile_product_file.csv"));
				
				RandomAccessFile file = new RandomAccessFile(new File("C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/mobile_product_file.csv"),"rw");

//				BufferedWriter writer = new BufferedWriter(new FileWriter(
//						"C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/mobile_product_file.csv"));
				
				String line;
				file.readLine();
				
	         
				while((line = file.readLine()) != null) {
					String [] st = line.split(",");
					int id = Integer.parseInt(st[0]);
					
					if(id==productId) {
						System.out.println(line);
						   long currentPosition = file.getFilePointer();

						file.seek(currentPosition);
						file.writeBytes("".repeat(line.length()));
						break;
						
					}
					
					
					
				}
				
				file.close();
				
			}catch(FileNotFoundException io) {
				io.printStackTrace();
			}catch(IOException io) {
				io.printStackTrace();
			}
		}
		
		else if(category.equalsIgnoreCase("laptop")) {
			try {
//				BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/mobile_product_file.csv"));
				
				RandomAccessFile file = new RandomAccessFile(new File("C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/laptop_product_file.csv"),"rw");

//				BufferedWriter writer = new BufferedWriter(new FileWriter(
//						"C:/Users/Admin/Desktop/java workspace/ShoppingCartProject/src/files_storage/mobile_product_file.csv"));
				
				String line;
				file.readLine();
				
	            long currentPosition = file.getFilePointer();

				while((line = file.readLine()) != null) {
					String [] st = line.split(",");
					int id = Integer.parseInt(st[0]);
					
					if(id==productId) {
						file.seek(currentPosition);
						file.writeBytes("".repeat(line.length()));
						break;
					}
					
					currentPosition = file.getFilePointer();
					
				}
				
				file.close();
				
			}catch(FileNotFoundException io) {
				io.printStackTrace();
			}catch(IOException io) {
				io.printStackTrace();
			}
		}

		
	}
	public static boolean addCustomer(Customer customer) {
		if (!isExistingCustomer(customer.getEmailId(), customer.getPassword())) {
			DataManager.getData().getCustomerMap().put(customer.getEmailId(), customer);
			return true;
		}
		System.out.println("Customer Already Exsits.");
		return false;
	}

	public static boolean isExistingCustomer(String emailId, String password) {
		Customer customer = DataManager.getData().getCustomerMap().get(emailId);
		if (customer == null || !customer.getPassword().equals(password)) {
			return false;
		}

		return true;
	}

	public static boolean isAdmin(String userName, String password) {
		Admin admin = Admin.getAdmin();

		if (admin.getUserName().equals(userName) && admin.getPassword().equals(password)) {
			return true;
		}

		return false;
	}

	public static void signUp(String customerName, long mobileNo, String emailId, String password, String doorNo,
			String streetName, String village, District district, State state, int pincode) {

		Customer customer = new Customer(emailId, password, customerName, mobileNo,
				new Address(doorNo, streetName, village, district, state, pincode));

		if (addCustomer(customer)) {
			System.out.println("Signing Up Successful..");
		} else {
			System.out.println("UserName Already in the System. Go To SignIn..");

		}

	}

	public static void showProducts() {
		int i = 1;
		int count = 0;
		for (Product product : DataManager.getData().getProductMap().values()) {
			if (count == 0) {
				System.out.printf("%-7s%-13s%-13s%-15s%-13s%n", "S.No", "Category", "Brand", "Model", "Price");
				System.out.println();
			}
			System.out.printf("%-7s%-13s%-13s%-15s%-13s%n", i++, product.getCategory().getCategoryName(),
					product.getBrand().getBrandName(), product.getModel(), product.getPrice());

			count++;
		}
	}

	public static void stockDeduction(int productId, int count) {
		int stock = DataManager.getData().getProductMap().get(productId).getStock();
		if (isProductAvailable(productId, count)) {
			DataManager.getData().getProductMap().get(productId).setStock(stock - count);
		} else {
			System.out.println("Product Out Of Stock..");
		}
	}

	public static void stockDeduction(Map<Product, Integer> products) {

		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			Product product = entry.getKey();
			int purchaseCount = entry.getValue();

			if (isProductAvailable(product.getProductId(), purchaseCount)) {
				int stock = DataManager.getData().getProductMap().get(product.getProductId()).getStock();

				synchronized (DataManager.getData().getProductMap().get(product.getProductId())) {
					DataManager.getData().getProductMap().get(product.getProductId()).setStock(stock - purchaseCount);
				}
			} else {
				System.out.println("Product Out Of Stock..");
			}

		}

	}

	public static boolean isProductAvailable(int productId, int count) {

		if (DataManager.getData().getProductMap().get(productId).getStock() >= count
				&& DataManager.getData().getProductMap().get(productId).getStock() > 0) {
			return true;
		}

		return false;
	}

	public static void displayProductsLeft(Product product) {
		System.out.println("Only " + product.getStock() + " left");
	}

	public static Set<Product> searchEngine(String keyWord) {
//		String keyWord = InputScanner.getString("Enter the Search KeyWord : ");
		String[] words = keyWord.split("\\s");
		Set<Product> searchProducts = new LinkedHashSet<>();

		for (Map.Entry<Integer, Product> entry : DataManager.getData().getProductMap().entrySet()) {
			Product product = entry.getValue();
			for (int i = 0; i < words.length; i++) {
				if (product.getCategory().getCategoryName().equalsIgnoreCase(words[i])
						|| product.getCategory().getCategoryName().toLowerCase().startsWith(words[i].toLowerCase())) {
					searchProducts.add(product);
				}
				if (product.getBrand().getBrandName().equalsIgnoreCase(words[i])
						|| product.getBrand().getBrandName().toLowerCase().startsWith(words[i].toLowerCase())) {
					searchProducts.add(product);
				}
				if (product.getModel().equalsIgnoreCase(words[i])
						|| product.getModel().toLowerCase().startsWith(words[i].toLowerCase())) {
					searchProducts.add(product);
				}
			}
		}

		return searchProducts;
	}

	public static Features createFeatures(Category category) {

		if (category.getCategoryName().equalsIgnoreCase("mobile")
				|| category.getCategoryName().equalsIgnoreCase("tablet")) {

			String screenSize = InputScanner.getString("Enter The Screen Size : ").intern();
			int batteryCapacity = InputScanner.getInt("Enter The Battery Capacity : ");
			int ramInGb = InputScanner.getInt("Enter The RAM Size : ");
			int storageInGb = InputScanner.getInt("Enter The Storage Size : ");
			int frontCameraCapacity = InputScanner.getInt("Enter The Front Camera Capacity : ");
			int rearCameraCapacity = InputScanner.getInt("Enter The Rear Camera Capacity : ");
			String operatingSystem = InputScanner.getString("Enter The OS : ").intern();

			Color colour = null;
			while (true) {
				String color = InputScanner.getStringInUpper("Enter The Color : ");
				if (Color.isContains(color)) {
					colour = Color.valueOf(color);
				} else {
					System.out.println("Enter The Valid Color ..");
					continue;
				}

				break;
			}

			Country madeInCountry = Country.valueOf(InputScanner.getStringInUpper("Enter The Made In Country : "));
			int madeInTheYear = InputScanner.getInt("Enter The Made In Year : ");
			float weight = InputScanner.getFloat("Enter The Product Weight : ");

			return new MobileFeatures(screenSize, batteryCapacity, ramInGb, storageInGb, frontCameraCapacity,
					rearCameraCapacity, operatingSystem, colour, madeInCountry, madeInTheYear, weight);
		}

		else if (category.getCategoryName().equalsIgnoreCase("laptop")) {

			String processor = InputScanner.getString("Enter The Processor Name : ").intern();
			int processorSpeed = InputScanner.getInt("Enter The Processor Speed : ");
			String operatingSystem = InputScanner.getString("Enter The OS").intern();
			int ramInGb = InputScanner.getInt("Enter The RAM Size : ");
			int storageInGb = InputScanner.getInt("Enter The Storage Size : ");
			String screenSize = InputScanner.getString("Enter The Screen Size : ").intern();
			String displayType = InputScanner.getString("Enter The Display Type : ").intern();

			Color colour = null;
			while (true) {
				String color = InputScanner.getStringInUpper("Enter The Color : ");
				if (Color.isContains(color)) {
					colour = Color.valueOf(color);
				} else {
					System.out.println("Enter The Valid Color ..");
					continue;
				}

				break;
			}

			Country madeInCountry = Country.valueOf(InputScanner.getStringInUpper("Enter The Made In Country : "));
			int madeInTheYear = InputScanner.getInt("Enter The Made In Year : ");
			float weight = InputScanner.getFloat("Enter The Product Weight : ");

			return new LaptopFeatures(processor, processorSpeed, operatingSystem, ramInGb, storageInGb, screenSize,
					displayType, colour, madeInCountry, madeInTheYear, weight);
		}

		return null;
	}

	public static boolean isValidEmail(String email) {
		if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			return true;
		}

		return false;
	}

}
