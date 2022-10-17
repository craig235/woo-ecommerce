package com.qa.woo.ecommerce.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.qa.woo.ecommerce.enums.DeliveryMode;
import com.qa.woo.ecommerce.exception.InvalidInputException;
import com.qa.woo.ecommerce.model.Product;

public class UI {

	public int mainMenu() {
		System.out.println(" ----------------------------------");
		System.out.println(" :::: Welcome to WOO eCommerce ::::");
		System.out.println(" ----------------------------------");
		System.out.println("1. View Product By Id");
		System.out.println("2. View All Products");
		System.out.println("3. Add New Product");
		System.out.println("4. Update Product");
		System.out.println("5. Delete Product");
		System.out.print("Please enter your choice (1-5): ");
		int choice = 0;

		try {
			Scanner scanner = ScannerUtil.getScanner();
			choice = scanner.nextInt();
			if (!(choice >= 1 && choice <= 5))
				throw new InvalidInputException("Invalid Choice : Enter Only from 1 to 5");
		} catch (InputMismatchException e) {
			System.out.println("Please enter only number from 1 to 5");				
			mainMenu();
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			mainMenu();
		} catch (Exception e) {
			System.out.println("Some internal error occurred . Please try again !!");
			mainMenu();
		}

		return choice;
	}

	public int productIdInput() {
		System.out.println(" ---------------------------------");
		System.out.print(" Enter Product Id: ");
		int productId = 0;
		try  {
			Scanner scanner = ScannerUtil.getScanner();
			productId = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please enter only number ");
			productIdInput();
		} catch (Exception e) {
			System.out.println("Some internal error occurred . Please try again !!");
			e.printStackTrace();
			productIdInput();
		}

		return productId;
	}

	public void displayProduct(Product product) {
	
		
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-8s %-25s %-10s %-10s %-10s %-10s %-15s %-15s %-20s %-15s \n", "ID", "PRODUCT", "PRICE",
				"RATING", "CATEGORY", "IN STOCK", "DISCOUNT(%)", "DELIVERY_MODE", "IS_RETURN_ACCEPTED", "SELLER_NAME");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-8d %-25s %-10.2f %-10.2f %-10s %-10s %-15.2f %-15s %-20s %-15s \n\n", product.getId(),
				product.getName(), product.getPrice(), product.getRating(), product.getCategory(), product.isInStock(),
				product.getDiscountPercentage(), product.getDeliveryMode(), product.isReturnAccepted(),
				product.getSellerName());
		
	}

	public char continueMenu() {
		char exit = 'N';
		try  {
			Scanner scanner = ScannerUtil.getScanner();
			exit = scanner.next().charAt(0);
			if (exit != 'Y' && exit != 'N')
				throw new InvalidInputException("Please enter only Y/N");
		} catch (InputMismatchException e) {
			System.out.println("Please enter only Y/N");
			continueMenu();
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			continueMenu();
		} 
		return exit;
	}

	public void displayProducts(List<Product> productsList) {
		if(productsList.size() == 0)
			System.out.println("No Products found...");
		else {
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-8s %-25s %-10s %-10s %-10s %-10s %-15s %-15s %-20s %-15s \n", "ID", "PRODUCT", "PRICE",
					"RATING", "CATEGORY", "IN STOCK", "DISCOUNT(%)", "DELIVERY_MODE", "IS_RETURN_ACCEPTED", "SELLER_NAME");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			
			for(Product product : productsList) {
				System.out.printf("%-8d %-25s %-10.2f %-10.2f %-10s %-10s %-15.2f %-15s %-20s %-15s \n", product.getId(),
						product.getName(), product.getPrice(), product.getRating(), product.getCategory(), product.isInStock(),
						product.getDiscountPercentage(), product.getDeliveryMode(), product.isReturnAccepted(),
						product.getSellerName());
			}
		}
	}

	public Product productInput() {
		System.out.println(" ---------------------------------");
		System.out.println(" Enter Product Details");
		System.out.println(" ---------------------------------");
		Product product = null;
		try  {
			/*
			 * Should write the validation logic for each input
			 */
			Scanner scanner = ScannerUtil.getScanner();
			System.out.print("Enter Id: ");
			int id = scanner.nextInt();
			System.out.print("Enter Name: ");
			String name = scanner.next();
			System.out.print("Enter Price: ");
			double price = scanner.nextDouble();
			System.out.print("Enter Rating: ");
			float rating = scanner.nextFloat();
			System.out.print("Enter Category: ");
			String category = scanner.next();
			System.out.print("Enter Stock Available (Y/N): ");
			boolean inStock = DataUtil.convertToBoolean(scanner.next());
			System.out.print("Enter Discount Percentage: ");
			float discountPercentage = scanner.nextFloat();
			System.out.print("Enter Delivery Mode (FREE/PAID): ");
			DeliveryMode deliveryMode = Enum.valueOf(DeliveryMode.class, scanner.next().toUpperCase());
			System.out.print("Enter Returns Accepted (Y/N): ");
			boolean isReturnAccepted = DataUtil.convertToBoolean(scanner.next());
			System.out.print("Enter Seller Name: ");
			String sellerName = scanner.next();
			
			product = Product.builder().id(id).name(name).price(price)
					.rating(rating).category(category)
					.inStock(inStock).discountPercentage(discountPercentage)
					.deliveryMode(deliveryMode)
					.isReturnAccepted(isReturnAccepted).sellerName(sellerName)
					.build();
		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Please only enter a number.");
			productIdInput();
		} catch (Exception e) {
			System.out.println("Some internal error occurred. Please try again !!");
			e.printStackTrace();
			productIdInput();
		}
		return product;
	}
	
	public void displayMessage(String message) {
		System.out.println(message);
	}
}
