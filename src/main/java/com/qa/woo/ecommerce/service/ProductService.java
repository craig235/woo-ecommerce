package com.qa.woo.ecommerce.service;

import java.util.List;

import com.qa.woo.ecommerce.exception.InvalidInputException;
import com.qa.woo.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.woo.ecommerce.model.Product;

public interface ProductService {

	Product addProduct(Product product) throws ProductAlreadyExistsException;
	Product getProductById(int id) throws ProductNotFoundException, InvalidInputException;
	List<Product> getAllProducts();
	Product updateProduct(Product product) throws ProductNotFoundException;
}
