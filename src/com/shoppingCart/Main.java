package com.shoppingCart;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shoppingCart.adminSystem.Admin;
import com.shoppingCart.adminSystem.AdminAuthenticator;
import com.shoppingCart.data.DataManager;

import com.shoppingCart.model.Customer;
import com.shoppingCart.model.Order;
import com.shoppingCart.model.Product;
import com.shoppingCart.service.ShoppingCartService;
import com.shoppingCart.util.Address;
import com.shoppingCart.util.Color;
import com.shoppingCart.util.Country;
import com.shoppingCart.util.District;
import com.shoppingCart.util.Features;
import com.shoppingCart.util.InputScanner;
import com.shoppingCart.util.LaptopFeatures;
import com.shoppingCart.util.MobileFeatures;
import com.shoppingCart.util.State;

public class Main {

	public final static int SIGN_IN = 1;
	public static final int SIGN_UP = 2;

	// Admin's View options
	public static final int ADD_PRODUCT = 1;
	public static final int DELETE_PRODUCT = 2;
	public static final int UPDATE_STOCK = 3;
	public static final int VIEW_PRODUCT = 4;
	public static final int EXIT = 5;

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
//		public static final int DECREASE_COUNT = 2;
	public static final int CLEAR_CART = 2;

	// Search Options
	public static final int VIEW_PRODUCTS = 1;
	public static final int PREVIOUS = 2;
	public static final int HOME = 3;

	public static void main(String[] args) throws Exception {

		ShoppingCartService.addCustomer(new Customer("customer@gmail.com", "Arun@1103", "Arunachalam R", 8870610967L,
				new Address("1/212", "muppudathi amman kovil street", "veerakeralamPudhur", District.TENKASI,
						State.TAMIL_NADU, 627861)));
		
		ShoppingCartService.readProduct();
		
		System.out.println();
		boolean flag = true;

		while (flag) {
			System.out.println("------Welcome To ZOHO Shopping------");
			System.out.println();
			System.out.println("1.Sign In" + "\n" + "2.Sign Up");
			System.out.println();

			while (true) {
				int option = InputScanner.getInt("Enter The Option : ");

				switch (option) {

				case SIGN_IN -> {
					System.out.println();
					String userName = InputScanner.getStringInLower("Enter Your UserName/Email Id : ");
					String password = InputScanner.getString("Enter Your Password : ");

					if (ShoppingCartService.isExistingCustomer(userName, password)) {
						System.out.println();
						customerView(DataManager.getData().getCustomerMap().get(userName));
					}

					else if (ShoppingCartService.isAdmin(userName, password)) {

						Admin admin = Admin.getAdmin();
						admin.getAuthenticator().authenticateAdmin(userName, password);

						System.out.println();
						adminSystem(admin);
					}

					else {
						System.out.println("UserName / Password Wrong.");
					}
					break;
				}

				case SIGN_UP -> {
					System.out.println();

					String customerName = InputScanner.getStringInUpper("Enter Your Name : ").intern();
					long mobileNo = InputScanner.getLong("Enter Your Mobile Number : ");
					String emailId = InputScanner.getEmail("Enter Your EmailId : ");
					String password = InputScanner.getString("Enter Your Password : ").intern();

					String doorNo = InputScanner.getString("Enter Your Door No : ").intern();
					String streetName = InputScanner.getStringInLower("Enter Your Street Name : ").intern();
					String village = InputScanner.getStringInUpper("Enter Your Village : ").intern();
					District district = District.getDistrict();
					State state = State.getState();

					int pincode = InputScanner.getInt("Enter Your Pincode : ");

					ShoppingCartService.signUp(customerName, mobileNo, emailId, password, doorNo, streetName, village,
							district, state, pincode);
				}

				default -> {
					System.out.println("Enter The Valid Option..");
					continue;
				}
				}
				break;
			}
		}
	}

	public static void adminSystem(Admin admin) {

		boolean flag = true;
		while (flag) {
			System.out.println("-----Welcome To Admin System-----");
			int count = 0;

			for (Map.Entry<Integer, Product> entry : DataManager.getData().getProductMap().entrySet()) {
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

					String category = InputScanner.getStringInUpper("Enter The Category Name : ");
					String brand = InputScanner.getStringInUpper("Enter The Brand Name : ");
					String model = InputScanner.getStringInUpper("Enter The Model Name : ").intern();
					double price = InputScanner.getDouble("Enter The Price : ");
					int stock = InputScanner.getInt("Enter The Stock : ");

					Features features = null;

					if (category.equalsIgnoreCase("mobile") || category.equalsIgnoreCase("tablet")) {

						String screenSize = InputScanner.getString("Enter The Screen Size : ").intern();
						int batteryCapacity = InputScanner.getInt("Enter The Battery Capacity : ");
						int ramInGb = InputScanner.getInt("Enter The RAM Size : ");
						int storageInGb = InputScanner.getInt("Enter The Storage Size : ");
						int frontCameraCapacity = InputScanner.getInt("Enter The Front Camera Capacity : ");
						int rearCameraCapacity = InputScanner.getInt("Enter The Rear Camera Capacity : ");
						String operatingSystem = InputScanner.getStringInUpper("Enter The OS : ").intern();

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

						Country madeInCountry = Country
								.valueOf(InputScanner.getStringInUpper("Enter The Made In Country : "));
						int madeInTheYear = InputScanner.getInt("Enter The Made In Year : ");
						float weight = InputScanner.getFloat("Enter The Product Weight : ");

						features = new MobileFeatures(screenSize, batteryCapacity, ramInGb, storageInGb,
								frontCameraCapacity, rearCameraCapacity, operatingSystem, colour, madeInCountry,
								madeInTheYear, weight);
					} else if (category.equalsIgnoreCase("laptop")) {

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

						Country madeInCountry = Country
								.valueOf(InputScanner.getStringInUpper("Enter The Made In Country : "));
						int madeInTheYear = InputScanner.getInt("Enter The Made In Year : ");
						float weight = InputScanner.getFloat("Enter The Product Weight : ");

						features = new LaptopFeatures(processor, processorSpeed, operatingSystem, ramInGb, storageInGb,
								screenSize, displayType, colour, madeInCountry, madeInTheYear, weight);
					}

					Product product = admin.createProduct(category, brand, model, price, stock, features);
					ShoppingCartService.addProduct(product);
					ShoppingCartService.addProductToFile(product);
					break;
				}
				case DELETE_PRODUCT -> {
					System.out.println();

					int productId = InputScanner.getInt("Enter The Product Id : ");

					Product product = DataManager.getData().getProductMap().get(productId);

					if (product != null) {
						System.out.println();
						System.out.printf("%-13s%-10s%-10s%-15s%-10s%-10s%n", "ProductId", "Category", "Brand", "Model",
								"Price", "Stock");
						System.out.printf("%-13s%-10s%-10s%-15s%-10s%-10s%n", product.getProductId(),
								product.getCategory().getCategoryName(), product.getBrand().getBrandName(),
								product.getModel(), product.getPrice(), product.getStock());

						System.out.println();

						while (true) {
							System.out.println("Do you want to delete this Product?");
							int choice = InputScanner.getInt("\t" + "1. YES" + "\n" + "\t" + "2. NO");

							switch (choice) {
							case 1 -> {
								admin.deleteProduct(productId);
								break;
							}

							case 2 -> {
								System.out.println("Product Delete cancelled.");
								break;
							}

							default -> {
								System.out.println("Enter the valid option");
								continue;
							}
							}
							break;
						}

					} else {
						System.out.println("Product Cannot Be Found.");
					}

					break;
				}

				case UPDATE_STOCK -> {

					int productId = InputScanner.getInt("Enter The Product Id : ");
					Product product = DataManager.getData().getProductMap().get(productId);

					if (product != null) {
						System.out.println();
						System.out.printf("%-13s%-10s%-10s%-15s%-10s%-10s%n", "ProductId", "Category", "Brand", "Model",
								"Price", "Stock");
						System.out.printf("%-13s%-10s%-10s%-15s%-10s%-10s%n", product.getProductId(),
								product.getCategory().getCategoryName(), product.getBrand().getBrandName(),
								product.getModel(), product.getPrice(), product.getStock());

						System.out.println();

						while (true) {
							System.out.println("Do you want to update the stock to the Product?");
							int choice = InputScanner.getInt("\t" + "1. YES" + "\n" + "\t" + "2. NO");

							switch (choice) {
							case 1 -> {
								int counts = InputScanner.getInt("Enter The Stock Count You Want to Add : ");
								admin.addStock(productId, counts);
								break;
							}

							case 2 -> {
								System.out.println("Stock update cancelled.");
								break;
							}

							default -> {
								System.out.println("Enter the valid option");
								continue;
							}
							}
							break;
						}

					} else {
						System.out.println("Product Cannot Be Found.");
					}
					break;
				}
				case VIEW_PRODUCT -> {
					System.out.println();
					admin.viewProducts();
					break;
				}
				case EXIT -> {
					System.out.println("Thank You For Signing In..");
					admin.getAuthenticator().logoutAdmin();
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
					ShoppingCartService.showProducts();

					product: while (true) {
						System.out.println("\n" + "1. Select Product " + "\n" + "2. Previous");
						option = InputScanner.getInt("Enter The Option : ");

						switch (option) {
						case VIEW_PRODUCTS -> {
							int index = InputScanner.getInt("Choose the Product : ");

							if (DataManager.getData().getProductMap().size() >= index) {

								Product product = DataManager.getData().getProductMap().get(DataManager.getData()
										.getProductMap().keySet().stream().skip(index - 1).findFirst().get());

								show: while (show) {
									Product.toString(product);

									System.out
											.println("\n" + "1. Add to Cart" + "\n" + "2. Buy Now" + "\n" + "3. Back");

									option = InputScanner.getInt("Enter The Option : ");

									switch (option) {

									case ADD_TO_CART -> {

										while (true) {
											int count = InputScanner
													.getInt("How many items are you looking to buy? : ");
											if (count > 0 && count <= product.getStock()) {
												customer.getMyCart().getProducts().put(product, count);
												System.out.println("Product Added To The Cart SuccessFully.");
												break;
											} else {
												System.out.println("Enter the count below " + product.getStock() + ".");
												continue;
											}
										}
										System.out.println();

										continue flag;

									}

									case PURCHASE -> {

										purchase: while (true) {
											if (product.getStock() < 5) {
												ShoppingCartService.displayProductsLeft(product);
											}
											int count = InputScanner
													.getInt("How Many items are you looking to buy? : ");

											if (ShoppingCartService.isProductAvailable(product.getProductId(), count)) {

												Map<Product, Integer> map = new LinkedHashMap<>();
												map.put(product, count);

												ShoppingCartService.stockDeduction(map);

												Order order = new Order(map, customer.getAddress(), "COD");
												customer.getMyOrder().add(order);
												order.printOrder();

												break purchase;
											} else {
												System.out.println(
														"Your Product Count Exceeds. Available Product Quantity : "
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
							} else {
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
					String keyWord = InputScanner.getString("Enter the Search KeyWord : ");

					Set<Product> searchProducts = ShoppingCartService.searchEngine(keyWord);

					view: while (true) {
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
						selectOption: while (true) {
							System.out.println("\n" + "\t" + "1. Select Product" + "\n" + "\t" + "2. Back to search"
									+ "\n" + "\t" + "3. Back to HomePage");
							int choice = InputScanner.getInt("\n" + "Enter The Option : ");

							switch (choice) {

							case VIEW_PRODUCTS -> {
								viewProducts: while (true) {

									int index = InputScanner.getInt("Choose the Product : ");

									if (searchProducts.size() >= index) {
										Product product = searchProducts.stream().skip(index - 1).findFirst().get();
										Product.toString(product);

										boolean show = true;
										while (show) {
											System.out.println(
													"\n" + "1. Add to Cart" + "\n" + "2. Buy Now" + "\n" + "3. Back");

											option = InputScanner.getInt("Enter The Option : ");

											switch (option) {

											case ADD_TO_CART -> {

												while (true) {
													int count = InputScanner
															.getInt("How many items are you looking to buy? : ");
													if (count > 0 && count <= product.getStock()) {
														customer.getMyCart().getProducts().put(product, count);
														System.out.println("Product Added To The Cart SuccessFully.");
														System.out.println();

														break;
													} else {
														System.out.println(
																"Enter the count below " + product.getStock() + ".");
														continue;
													}
												}

												show = false;
												break;
											}

											case PURCHASE -> {

												while (true) {
													if (product.getStock() < 5) {
														ShoppingCartService.displayProductsLeft(product);
													}
													int count = InputScanner
															.getInt("How Many items are you looking to buy? : ");

													Map<Product, Integer> map = new LinkedHashMap<>();
													map.put(product, count);

													if (ShoppingCartService.isProductAvailable(product.getProductId(),
															count)) {
														while (true) {
															System.out.println("\t" + "Confirm Your Purchase :" + "\n"
																	+ "\t" + "1. Confirm" + "\n" + "\t" + "2. Cancel");
															choice = InputScanner
																	.getInt("\n" + "\t" + "Enter The Option : ");

															switch (choice) {
															case 1 -> {
																ShoppingCartService.stockDeduction(map);

																Order order = new Order(map, customer.getAddress(),
																		"COD");
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
																"Your Product Count Exceeds. Available Product Quantity : "
																		+ product.getStock());
														continue;
													}
													break;
												}

												show = false;
												break;

											}

											case BACK -> {
												continue view;
//									show = false;
//									break;
											}

											default -> {
												System.out.println("\n" + "Enter The Valid Option.");
												System.out.println();
												continue search;
											}

											}

										}
										break;
//						purchaseMethod(customer, product);
									} else {
										System.out.println("Enter The Valid Product's S.No.");
										continue view;

									}

								}

							}

							case PREVIOUS -> {
								continue search;

							}

							case HOME -> {
								continue flag;
							}

							default -> {
								System.out.println("Enter The Valid Option.");
								continue selectOption;
							}
							}
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
							customer.buyProductFromCart();
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

	/*
	 * 
	 * public static void purchaseMethod(Customer customer, Product product) {
	 * 
	 * boolean show = true; while (show) { Product.toString(product);
	 * 
	 * System.out.println("\n" + "1. Add to Cart" + "\n" + "2. Buy Now" + "\n" +
	 * "3. Back");
	 * 
	 * int option = InputScanner.getInt("Enter The Option : ");
	 * 
	 * switch (option) {
	 * 
	 * case ADD_TO_CART -> { int count =
	 * InputScanner.getInt("How Many Product Do You Want To Purchase? : ");
	 * customer.getMyCart().getProducts().put(product, count);
	 * System.out.println("Product Added To The Cart SuccessFully.");
	 * System.out.println(); // isShopping = false; show = false; break; }
	 * 
	 * case PURCHASE -> {
	 * 
	 * while (true) { if (product.getStock() < 5) { displayProductsLeft(product); }
	 * int count =
	 * InputScanner.getInt("How Many Product Do You Want To Purchase? : ");
	 * 
	 * Map<Product, Integer> map = new LinkedHashMap<>(); map.put(product, count);
	 * 
	 * if (isProductAvailable(product.getProductId(), count)) { while (true) {
	 * System.out.println("\t" + "Confirm Your Purchase :" + "\n" + "\t" +
	 * "1. Confirm" + "\n" + "\t" + "2. Cancel"); int choice =
	 * InputScanner.getInt("\n" + "\t" + "Enter The Option : ");
	 * 
	 * switch (choice) { case 1 -> { stockDeduction(map);
	 * 
	 * Order order = new Order(map, customer.getAddress(), "COD");
	 * customer.getMyOrder().add(order); order.printOrder(); break; }
	 * 
	 * case 2 -> { System.out.println("Your Order Rejected.."); break; }
	 * 
	 * default -> { System.out.println("Enter The Valid Option..");
	 * System.out.println(); continue; }
	 * 
	 * } break; } } else { System.out.println(
	 * "Your Product Count Exceeds. Available Product Quantity : " +
	 * product.getStock()); continue; }
	 * 
	 * break;
	 * 
	 * } // isShopping = false; show = false; break;
	 * 
	 * }
	 * 
	 * case BACK -> { break; }
	 * 
	 * default -> { System.out.println("\n" + "Enter The Valid Option.");
	 * System.out.println(); continue; }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 */
}