package com.qa.woo.ecommerce;

import java.util.List;
import java.util.Scanner;

import com.qa.woo.ecommerce.controller.ProductController;
import com.qa.woo.ecommerce.model.Product;
import com.qa.woo.ecommerce.ui.UI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ProductController controller = new ProductController();
        UI ui = new UI();
        char exit = 'Y';
       
        while(exit != 'N') {
        int choice = ui.mainMenu();
        switch(choice) {
        case 1:
        		int productId = ui.productIdInput();
        		Product productById = controller.getProductById(productId);
        		if(productById != null)
        		ui.displayProduct(productById);
        		break;
        		
        case 2:
        		List<Product> productsList = controller.getAllProducts();
        		ui.displayProducts(productsList);
        		break;
        		
        case 3:
        		Product product = ui.productInput();
        		Product addedProduct = controller.addProduct(product);
        		ui.displayMessage(":Product Added Successfully:");
        		ui.displayProduct(addedProduct);
        		break;
        }
        
        System.out.println("Thank You.. Do you wish to continue (Y/N): ");
        exit = ui.continueMenu();
        if(exit == 'N')
        	System.exit(0);
        }
    }
}
