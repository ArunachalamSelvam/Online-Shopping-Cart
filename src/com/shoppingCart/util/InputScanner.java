package com.shoppingCart.util;

import java.util.Scanner;

import com.shoppingCart.service.ShoppingCartService;

public class InputScanner {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
                
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        
        
    }

    public static double getDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double input = scanner.nextDouble();
                scanner.nextLine();
                return input;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a double.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
    
    public static Float getFloat(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                Float input = scanner.nextFloat();
                scanner.nextLine();
                return input;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a Float.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
    
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public static String getStringInLower(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().toLowerCase();
    }
    
    public static String getStringInUpper(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().toUpperCase();
    }
    
    public static long getLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                long input = scanner.nextLong();
                scanner.nextLine();
                return input;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter an long.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
    
	public static String getEmail(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine();
			
			if (ShoppingCartService.isValidEmail(input)) {
				return input;
			} else {
				System.out.println();
				System.out.println("Please Enter The Valid Email.");
				System.out.println();
				continue;
			}
		}
	}
}
