package com.shoppingCart.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shoppingCart.data.DataManager;
import com.shoppingCart.service.ShoppingCartService;
import com.shoppingCart.util.Address;
import com.shoppingCart.util.InputScanner;

public class Customer {

	public static int id = 1;
	private int customerId;
	private String emailId;
	private String password;
	private String customerName;
	private long mobileNo;
	private Address address;
	private List<Order> myOrder;
	private Cart myCart;

//	public Customer() {
//		this.customerId = id++;
//		this.customerName = InputScanner.getStringInUpper("Enter Your Name : ");
//		this.mobileNo = InputScanner.getLong("Enter Your Mobile Number : ");
//		this.emailId = InputScanner.getStringInLower("Enter Your EmailId : ");
//		this.password = InputScanner.getString("Enter Your Password : ");
//		this.address = new Address();
//		this.myOrder = new ArrayList<>();
//		this.myCart = new Cart();
//
//	}

	public Customer(String emailId, String password, String customerName, long mobileNo, Address address) {
		super();
		this.customerId = id++;
		this.emailId = emailId;
		this.password = password;
		this.customerName = customerName;
		this.mobileNo = mobileNo;
		this.address = address;
		this.myOrder = new ArrayList<>();
		this.myCart = new Cart();
	}

	public void buyProducts() {

		Map<Product, Integer> map = new LinkedHashMap<Product, Integer>();
		
		
		if (myCart.getProducts().size() > 1) {
			int count = InputScanner.getInt("How Many Products You Want To Purchase? ");

			for (int i = 0; i < count; i++) {
				int index = InputScanner.getInt("Enter The Product Index : ");
				Product product = myCart.getProducts().keySet().stream().skip(index - 1).findFirst().get();
				

				while (true) {
					int productcount = myCart.getProducts().get(product);
					
					if(DataManager.productMap.get(product.getProductId()).getStock()==0) {
						System.out.println("Product Currently Out Of Stock.");
						break;
					}
					
					else {
						if (ShoppingCartService.isProductAvailable(product.getProductId(), productcount)) {
							map.put(product, productcount);
							
							
						} else {
							System.out.println(
									"Your Product Count Exceeds. Available Product Quantity : " + product.getStock());

							System.out.println();

							int reCount = InputScanner.getInt("Enter The Product Count You Want TO Decrease : ");

							myCart.decreaseCount(product, reCount);
							continue;
							
						}

						
					}
					break;

				}
			}
		}

		else {

			Product product = myCart.getProducts().keySet().stream().skip(0).findFirst().get();
			

			while (true) {
				int productcount = myCart.getProducts().get(product);
				if(DataManager.productMap.get(product.getProductId()).getStock()==0) {
					System.out.println("Product Currently Out Of Stock.");
					break;
				}
				else if (ShoppingCartService.isProductAvailable(product.getProductId(), productcount)) {

					while (true) {
						System.out.println("\t" + "Confirm Your Purchase :" + "\n" + "\t" + "1. Confirm"
								+ "\n" + "\t" + "2. Cancel");
						int choice = InputScanner.getInt("\n"+"\t" + "Enter The Option : ");

						switch (choice) {
						case 1 -> {
							map.put(product, productcount);

							Order order = new Order(map, address, "COD");
							myCart.removeItem(map);
							myOrder.add(order);
							order.printOrder();
							break;
						}

						case 2 -> {
							System.out.println("Your Order Rejected..");
							break;
							
						}

						default -> {
							System.out.println("Enter The Valid Option.");
							continue;
						}

						}
						break;
					}
					break;

				} else {
					System.out
							.println("Your Product Count Exceeds. Available Product Quantity : " + product.getStock());

					System.out.println();

					int reCount = InputScanner.getInt("Enter The Product Count You Want TO Decrease : ");

					myCart.decreaseCount(product, reCount);
					continue;
				}

			}

		}

	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getMyOrder() {
		return myOrder;
	}

	public void setMyOrder(List<Order> myOrder) {
		this.myOrder = myOrder;
	}

	public Cart getMyCart() {
		return myCart;
	}

	public void setMyCart(Cart myCart) {
		this.myCart = myCart;
	}

	public int getCustomerId() {
		return customerId;
	}

}
