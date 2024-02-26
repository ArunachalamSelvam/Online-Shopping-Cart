package com.shoppingCart;

import com.shoppingCart.adminSystem.Admin;
import com.shoppingCart.data.DataManager;

import com.shoppingCart.model.Customer;
import com.shoppingCart.model.Product;
import com.shoppingCart.service.ShoppingCartService;
import com.shoppingCart.util.Address;
import com.shoppingCart.util.Color;
import com.shoppingCart.util.Country;
import com.shoppingCart.util.District;
import com.shoppingCart.util.InputScanner;
import com.shoppingCart.util.LaptopFeatures;
import com.shoppingCart.util.MobileFeatures;
import com.shoppingCart.util.State;

public class Main {

	public final static  int SIGN_IN = 1;
	public static final int SIGN_UP = 2;

	public static void main(String[] args) {
		
		DataManager.customerMap.put("customer@gmail.com",
				new Customer("customer@gmail.com", "Arun@1103", "Arunachalam R", 8870610967L,
						new Address("1/212", "muppudathi amman kovil street", "veerakeralamPudhur", District.TENKASI,
								State.TAMIL_NADU, 627861)));
		
	
		ShoppingCartService.addProduct(new Product("MOBILE", "SAMSUNG", "M21", 14000, 10, 
				 new MobileFeatures("6.5 inch", 6000, 4, 64, 20, 48, "Android 11", Color.BLACK,Country.INDIA,2020,0.650f)));
		
		ShoppingCartService.addProduct(new Product("MOBILE", "SAMSUNG", "M51", 20000, 10, 
				 new MobileFeatures("6.5 inch", 6000, 4, 64, 20, 48, "Android 11", Color.SAPPHIRE_BLUE,Country.INDIA,2020,0.650f)));
		
		ShoppingCartService.addProduct(new Product("MOBILE", "APPLE", "15PRO", 140000, 10,
				 new MobileFeatures("6.10 inch", 4000, 8, 256, 20, 48, "Android 11", Color.TITANIUM_GRAY,Country.INDIA, 2023,0.750f)));
		
		ShoppingCartService.addProduct(new Product("MOBILE", "REDMI", "NOTE 8", 20000, 10, 
				new MobileFeatures("7.2 inch", 5500, 8, 128, 20, 64, "Android 12", Color.GOLD, Country.CHINA,2021, 0.850f)));
		
		ShoppingCartService.addProduct(new Product("LAPTOP", "DELL", "LATTITUDE", 100000, 10,
						new LaptopFeatures("intel i5 8th gen", 1.7f, "Windows 10 pro", 8, 512, "35.5Cm", "LCD", Color.BLACK, Country.INDIA, 2021, 2.5f)));

		ShoppingCartService.addProduct(new Product("LAPTOP", "HP", "PAVILION 15", 49990, 10,
						new LaptopFeatures("ryzen 5 5625U", 4.3f, "Windows 11", 8, 512, "39.6Cm", "LED", Color.SILVER, Country.CHINA, 2022,1.750f)));
		
		ShoppingCartService.addProduct(new Product("LAPTOP", "APPLE", "MACBOOK Pro", 189900, 10,				
				new LaptopFeatures("M3 chip with 8 Core cpu", 2.75f, "Mac OS", 8, 1, "35.56Cm", "LED", Color.SPACE_GRAY,Country.INDIA, 2020, 1.550f)));
		
		
		boolean flag = true;

		while (flag) {
			System.out.println("------Welcome To ZOHO Shopping------");
			System.out.println();
			System.out.println("1.Sign In" + "\n" + "2.Sign Up");
			System.out.println();
			
			
			while (true) {
				int option = InputScanner.getInt("Enter The Option : ");
				
				switch(option) {
				
				case SIGN_IN ->{
				System.out.println();	
				String userName = InputScanner.getStringInLower("Enter Your UserName/Email Id : ");
				String password = InputScanner.getString("Enter Your Password : ");

				if (ShoppingCartService.isExistingCustomer(userName, password)) {
					System.out.println();
					ShoppingCartService.customerView(DataManager.customerMap.get(userName));
				}
				
				else if(ShoppingCartService.isAdmin(userName, password)) {
					System.out.println();
					ShoppingCartService.adminSystem(Admin.getAdmin());
				}
				
				else {
					System.out.println("UserName / Password Wrong.");
				}
				break;
				}
				
				case SIGN_UP ->{
					System.out.println();
					ShoppingCartService.signUp();
				}
				
				default ->{
					System.out.println("Enter The Valid Option..");
					continue;
				}
				}
				break;
			}
		}
	}
}