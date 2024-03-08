package com.shoppingCart.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Map;


import com.shoppingCart.util.Address;

public class Order {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	public static int id= 1001;
	private int invoiceId;
	private Map<Product, Integer> productList;
	private int purchasedCount;
	private double totalPrice;
	private Address billingAddress;
	private String paymentType;
	private String orderDateTime;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public Order(Map<Product, Integer> productList, Address billingAddress, String paymentType) {
		
		this.invoiceId = id++;
		this.productList = productList;
	
		this.billingAddress = billingAddress;
		this.paymentType = paymentType;
		this.orderDateTime = LocalDateTime.now().format(formatter); 
		calculateCount();
		calculateTotalPrice();
		
	}
	
	public void printOrder() {
		System.out.println("x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x");
		System.out.println("Purchased Successful.");
		System.out.println("Date and Time : " + orderDateTime);
		System.out.println("Invoice Id : " + invoiceId);
		System.out.println("Billing Address : "+ billingAddress.toString());
		System.out.println("Purchased Product List");
		int i=1;
		for(Map.Entry<Product, Integer> entry : productList.entrySet()) {
			Product product = entry.getKey();
			int count = entry.getValue();
			System.out.println("--------------------------------------------------");
			System.out.print("Item "+ i++ +" : ");
			System.out.println(product.getBrand().getBrandName()+" "+ product.getModel() +" " +product.getCategory().getCategoryName() );
			System.out.println("Count : " + count);
			System.out.println("Price : "+product.getPrice()*count);
			System.out.println("--------------------------------------------------");
			System.out.println();
		}
		
		System.out.println("Payment Type : " + paymentType);
		System.out.println("Total Price : " + totalPrice);
		System.out.println("Total Purchased Count : " + purchasedCount);
		System.out.println("x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x");

	}
	
	private void calculateCount() {
		purchasedCount=0;
		for(Map.Entry<Product, Integer> entry : productList.entrySet()) {
			purchasedCount += entry.getValue();
		}
	}
	private void calculateTotalPrice() {
        totalPrice = 0;
        for(Map.Entry<Product, Integer> entry : productList.entrySet()) {
        	totalPrice += entry.getKey().getPrice()*entry.getValue();
        }
    }
	

	public int getInvoiceId() {
		return invoiceId;
	}
	
	public Map<Product,Integer> getProduct() {
		return productList;
	}
	public void setProduct(Map<Product,Integer> product) {
		this.productList = product;
	}
	public int getPurchasedCount() {
		return purchasedCount;
	}
	public void setPurchasedCount(int purchasedCount) {
		this.purchasedCount = purchasedCount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getOrderDateTime() {
		return orderDateTime;
	}
	
	
	
	
}
