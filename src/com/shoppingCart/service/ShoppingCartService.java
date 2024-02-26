package com.shoppingCart.service;

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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shoppingCart.adminSystem.Admin;
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

	// Admin's View options
	public static final int ADD_PRODUCT = 1;
	public static final int DELETE_PRODUCT = 2;
	public static final int UPDATE_STOCK = 3;
	public static final int VIEW_PRODUCT = 4;
	public static final int EXIT = 5;

	// Search Options
	public static final int VIEW_PRODUCTS = 1;
	public static final int PREVIOUS = 2;

	public static void addProduct(Product product) {
		String category = product.getCategory().getCategoryName();
		String brand = product.getBrand().getBrandName();

		if (DataManager.categoryMap.containsKey(category)) {
			product.setCategory(DataManager.categoryMap.get(category));
			
		} else {
			DataManager.categoryMap.put(category, product.getCategory());
		}

		if (DataManager.brandMap.containsKey(brand)) {
			product.setBrand(DataManager.brandMap.get(brand));
//			product.getBrand().setBrand("Nokia");
		} else {
			DataManager.brandMap.put(brand, product.getBrand());
			
		}

		if (!DataManager.productMap.containsKey(product.getProductId())) {
			DataManager.productMap.put(product.getProductId(), product);
		} else {
			System.out.println("Product Already Exists.");
		}

	}

	public static void addCustomer(Customer customer) {
		if (!isExistingCustomer(customer.getEmailId(), customer.getPassword())) {
			DataManager.customerMap.put(customer.getEmailId(), customer);
		} else {
			System.out.println("Customer Already Exsits.");
		}
	}

	public static boolean isExistingCustomer(String emailId, String password) {
		Customer customer = DataManager.customerMap.get(emailId);
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

	public static void signUp() {
		
		String customerName = InputScanner.getStringInUpper("Enter Your Name : ").intern();
		long mobileNo = InputScanner.getLong("Enter Your Mobile Number : ");
		String emailId = InputScanner.getStringInLower("Enter Your EmailId : ").intern();
		String password = InputScanner.getString("Enter Your Password : ").intern();
		
		String doorNo = InputScanner.getString("Enter Your Door No : ").intern();
		String streetName = InputScanner.getStringInLower("Enter Your Street Name : ").intern();
		String village = InputScanner.getStringInUpper("Enter Your Village : ").intern();
		District district = District.getDistrict();
		State state = State.getState();

		int pincode = InputScanner.getInt("Enter Your Pincode : ");

		Customer customer = new Customer(emailId, password, customerName, mobileNo,new Address(doorNo, streetName, village, district, state, pincode));

		if (!isExistingCustomer(customer.getEmailId(), customer.getPassword())) {
			DataManager.customerMap.put(customer.getEmailId(), customer);
		} else {
			System.out.println("UserName Already in the System. Go To SignIn..");
		}

	}

	public static void showProducts() {
		int i = 1;
		int count = 0;
		for (Product product : DataManager.productMap.values()) {
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
		int stock = DataManager.productMap.get(productId).getStock();
		if (isProductAvailable(productId, count)) {
			DataManager.productMap.get(productId).setStock(stock - count);
		} else {
			System.out.println("Product Out Of Stock..");
		}
	}

	public static void stockDeduction(Map<Product, Integer> products) {

		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			Product product = entry.getKey();
			int purchaseCount = entry.getValue();

			int stock = DataManager.productMap.get(product.getProductId()).getStock();

			if (isProductAvailable(product.getProductId(), purchaseCount)) {
				DataManager.productMap.get(product.getProductId()).setStock(stock - purchaseCount);
			} else {
				System.out.println("Product Out Of Stock..");
			}

		}

	}

	public static boolean isProductAvailable(int productId, int count) {

		if (DataManager.productMap.get(productId).getStock() >= count
				&& DataManager.productMap.get(productId).getStock() > 0) {
			return true;
		}

		return false;
	}

	public static void displayProductsLeft(Product product) {
		System.out.println("Only " + product.getStock() + " left");
	}

	public static Set<Product> searchEngine() {
		String keyWord = InputScanner.getString("Enter the Search KeyWord : ");
		String[] words = keyWord.split("\\s");
		Set<Product> searchProducts = new LinkedHashSet<>();

		for (Map.Entry<Integer, Product> entry : DataManager.productMap.entrySet()) {
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

			
			return new MobileFeatures(screenSize, batteryCapacity, ramInGb, storageInGb, frontCameraCapacity, rearCameraCapacity, operatingSystem, colour, madeInCountry, madeInTheYear, weight);
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

			
			return new LaptopFeatures(processor, processorSpeed, operatingSystem, ramInGb, storageInGb, screenSize, displayType, colour, madeInCountry, madeInTheYear, weight);
		}

		return null;
	}

	public static void purchaseMethod(Customer customer, Product product) {

		boolean show = true;
		while (show) {
			Product.toString(product);

			System.out.println("\n" + "1. Add to Cart" + "\n" + "2. Buy Now" + "\n" + "3. Back");

			int option = InputScanner.getInt("Enter The Option : ");

			switch (option) {

			case ADD_TO_CART -> {
				int count = InputScanner.getInt("How Many Product Do You Want To Purchase? : ");
				customer.getMyCart().getProducts().put(product, count);
				System.out.println("Product Added To The Cart SuccessFully.");
				System.out.println();
//				isShopping = false;
				show = false;
				break;
			}

			case PURCHASE -> {

				while (true) {
					if (product.getStock() < 5) {
						displayProductsLeft(product);
					}
					int count = InputScanner.getInt("How Many Product Do You Want To Purchase? : ");

					Map<Product, Integer> map = new LinkedHashMap<>();
					map.put(product, count);

					if (isProductAvailable(product.getProductId(), count)) {
						while (true) {
							System.out.println("\t" + "Confirm Your Purchase :" + "\n" + "\t" + "1. Confirm" + "\n"
									+ "\t" + "2. Cancel");
							int choice = InputScanner.getInt("\n" + "\t" + "Enter The Option : ");

							switch (choice) {
							case 1 -> {
								stockDeduction(map);

								Order order = new Order(map, customer.getAddress(), "COD");
								customer.getMyOrder().add(order);
								order.printOrder();
								break;
							}

							case 2 -> {
								System.out.println("Your Order Rejected..");
								break;
							}

							default -> {
								System.out.println("Enter The Valid Option..");
								System.out.println();
								continue;
							}

							}
							break;
						}
					} else {
						System.out.println(
								"Your Product Count Exceeds. Available Product Quantity : " + product.getStock());
						continue;
					}

					break;

				}
//				isShopping = false;
				show = false;
				break;

			}

			case BACK -> {
				break;
			}

			default -> {
				System.out.println("\n" + "Enter The Valid Option.");
				System.out.println();
				continue;
			}

			}

		}

	}

	public static void adminSystem(Admin admin) {

		boolean flag = true;
		while (flag) {
			System.out.println("-----Welcome To Admin System-----");
			int count = 0;

			for (Map.Entry<Integer, Product> entry : DataManager.productMap.entrySet()) {
				Product p = entry.getValue();
				if (p.getStock() < 10) {
					if (count == 0) {
						System.out.printf("%-15s%-15s%-15s%-20s%-13s%-10s%n", "Product Id", "Category", "Brand",
								"Model", "Price", "Stock");
						System.out.println();
					}
					System.out.printf("%-15s%-15s%-15s%-20s%-13s%-10s%n", p.getProductId(), p.getCategory(),
							p.getBrand(), p.getModel(), p.getPrice(), p.getStock());

					count++;
				}
			}
			System.out.println();

			System.out.println("1. Add new Product" + "\n" + "2. Delete Product" + "\n" + "3. Update Existing Stock"
					+ "\n" + "4. View Product List" + "\n" + "5. Exit");

			int option = InputScanner.getInt("Enter The Option : ");
			while (true) {
				switch (option) {

				case ADD_PRODUCT -> {

					Product product = admin.createProduct();
					addProduct(product);
					break;
				}
				case DELETE_PRODUCT -> {
					System.out.println();
					admin.deleteProduct();
					break;
				}

				case UPDATE_STOCK -> {
					admin.addStock();
					break;
				}
				case VIEW_PRODUCT -> {
					System.out.println();
					admin.viewProducts();
					break;
				}
				case EXIT -> {
					System.out.println("Thank You For Signing In..");
					flag = false;
					break;
				}
				default -> {
					System.out.println("Enter Valid Option ..");
					continue;
				}
				}
				break;
			}
		}
	}

	// Customer View
	public static void customerView(Customer customer) {

		flag: while (true) {
			System.out.println();
			System.out.println("------Welcome To ZOHO Shopping------");
			System.out.println();
			System.out.println("Welcome " + customer.getCustomerName());
			System.out.println("1. Go To Shopping." + "\n" + "2. Search Product" + "\n" + "3. My Orders" + "\n"
					+ "4. My Cart" + "\n" + "5. Sign Out");

			int option = InputScanner.getInt("Enter The Option : ");

			switch (option) {
			case SHOPPING -> {
				boolean isShopping = true;
				shopping: while (isShopping) {
					boolean show = true;
					showProducts();

					product: while (true) {
						System.out.println("\n" + "1. Select Product " + "\n" + "2. Previous");
						option = InputScanner.getInt("Enter The Option : ");
						
						switch (option) {
						case VIEW_PRODUCTS -> {
							int index = InputScanner.getInt("Choose the Product : ");					
							
							if(DataManager.productMap.size()>=index) {
							
								Product product = DataManager.productMap
										.get(DataManager.productMap.keySet().stream().skip(index - 1).findFirst().get());
								
								show: while (show) {
								Product.toString(product);

								System.out.println("\n" + "1. Add to Cart" + "\n" + "2. Buy Now" + "\n" + "3. Back");

								option = InputScanner.getInt("Enter The Option : ");

								switch (option) {

								case ADD_TO_CART -> {
									int count = InputScanner.getInt("How Many Product Do You Want To Purchase? : ");
									customer.getMyCart().getProducts().put(product, count);
									System.out.println("Product Added To The Cart SuccessFully.");
									System.out.println();

									continue flag;

								}

								case PURCHASE -> {

									purchase: while (true) {
										if (product.getStock() < 5) {
											displayProductsLeft(product);
										}
										int count = InputScanner.getInt("How Many items are you looking to buy? : ");

										Map<Product, Integer> map = new LinkedHashMap<>();
										map.put(product, count);

										if (isProductAvailable(product.getProductId(), count)) {

											stockDeduction(map);

											Order order = new Order(map, customer.getAddress(), "COD");
											customer.getMyOrder().add(order);
											order.printOrder();

											break purchase;
										} else {
											System.out
													.println("Your Product Count Exceeds. Available Product Quantity : "
															+ product.getStock());
											continue purchase;
										}
									}

									break show;

								}

								case BACK -> {
									break show;
								}

								default -> {
									System.out.println("\n" + "Enter The Valid Option.");
									System.out.println();
									continue show;
								}

								}

							}
						}
						else {
								System.out.println("Enter The Valid Product S.no...");
								continue shopping;
						}

						}

						case PREVIOUS -> {
							continue flag;
						}

						default -> {
							System.out.println("Enter The Valid Option.");
							continue product;
						}
						}
						break product;
					}

				}

			}

			case SEARCH -> {

				search: while (true) {
					Set<Product> searchProducts = searchEngine();

					int i = 1;
					if (!searchProducts.isEmpty()) {
						for (Product product : searchProducts) {
							System.out.println(i++ + ". " + "\t" + product.getCategory().getCategoryName() + "\t"
									+ product.getBrand().getBrandName() + "\t" + product.getModel() + "\t"
									+ product.getPrice());
						}
					}

					else {
						System.out.println("Search keyword Not Found.");
//					isSearch = false;
						break search;
					}

					System.out.println("\n" + "\t" + "1. Select Product" + "\n" + "\t" + "2. Back");
					int choice = InputScanner.getInt("\n" + "Enter The Option : ");

					switch (choice) {

					case VIEW_PRODUCTS -> {
						viewProducts: while (true) {

							boolean show = true;
							while (show) {

								int index = InputScanner.getInt("Choose the Product : ");
								Product product = searchProducts.stream().skip(index - 1).findFirst().get();
								Product.toString(product);

								System.out.println("\n" + "1. Add to Cart" + "\n" + "2. Buy Now" + "\n" + "3. Back");

								option = InputScanner.getInt("Enter The Option : ");

								switch (option) {

								case ADD_TO_CART -> {
									int count = InputScanner.getInt("How Many items are you looking to buy? : ");
									customer.getMyCart().getProducts().put(product, count);
									System.out.println("Product Added To The Cart SuccessFully.");
									System.out.println();

									show = false;
									break;
								}

								case PURCHASE -> {

									while (true) {
										if (product.getStock() < 5) {
											displayProductsLeft(product);
										}
										int count = InputScanner.getInt("How Many items are you looking to buy? : ");

										Map<Product, Integer> map = new LinkedHashMap<>();
										map.put(product, count);

										if (isProductAvailable(product.getProductId(), count)) {
											while (true) {
												System.out.println("\t" + "Confirm Your Purchase :" + "\n" + "\t"
														+ "1. Confirm" + "\n" + "\t" + "2. Cancel");
												choice = InputScanner.getInt("\n" + "\t" + "Enter The Option : ");

												switch (choice) {
												case 1 -> {
													stockDeduction(map);

													Order order = new Order(map, customer.getAddress(), "COD");
													customer.getMyOrder().add(order);
													order.printOrder();
													break;
												}

												case 2 -> {
													System.out.println("Your Order Rejected..");
													break;
												}

												default -> {
													System.out.println("Enter The Valid Option..");
													System.out.println();
													continue;
												}

												}
												break;
											}
										} else {
											System.out
													.println("Your Product Count Exceeds. Available Product Quantity : "
															+ product.getStock());
											continue;
										}
										break;
									}

									show = false;
									break;

								}

								case BACK -> {
									show = false;
									break;
								}

								default -> {
									System.out.println("\n" + "Enter The Valid Option.");
									System.out.println();
									continue;
								}

								}
								continue;

							}
							break;
//						purchaseMethod(customer, product);

						}

						break search;

					}

					case PREVIOUS -> {
						break;
					}

					}

				}
			}

			case MY_ORDERS -> {
				List<Order> myOrders = customer.getMyOrder();

				if (myOrders.isEmpty()) {
					System.out.println("You Are Not Order Anything. Your Orders Is Empty.");
					System.out.println();
				}

				else {
					for (Order order : myOrders) {
						order.printOrder();
					}
				}
				break;
			}

			case MY_CART -> {

				while (true) {
					if (customer.getMyCart().getProducts().isEmpty()) {
						System.out.println("Your Cart is Empty.");
						System.out.println();
						break;
					}

					else {
						customer.getMyCart().viewCart();
						System.out.println("\n" + "\t" + "1. Buy Products" + "\n" + "\t" + "2. Clear Cart");

						int choice = InputScanner.getInt("Enter The Option : ");

						switch (choice) {

						case BUY_PRODUCTS -> {
							customer.buyProducts();
							break;
						}

						case CLEAR_CART -> {
							customer.getMyCart().clearCart();
							break;
						}

						default -> {
							System.out.println("Enter the Valid Option.");
							System.out.println();
							continue;
						}

						}
					}
					break;

				}
			}

			case SIGN_OUT -> {
				System.out.println("Sign Out Successful. Happy Shopping..");
				System.out.println();

				break flag;
			}

			default -> {
				System.out.println("Enter Valid Option.");
				continue flag;
			}

			}
		}

	}
}
