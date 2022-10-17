package com.qa.woo.ecommerce.service;

import java.util.List;

import com.qa.woo.ecommerce.exception.InvalidInputException;
import com.qa.woo.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.woo.ecommerce.model.Product;
import com.qa.woo.ecommerce.repository.Repository;
import com.qa.woo.ecommerce.validation.InputValidation;

public class ProductServiceImpl implements ProductService {
	
	Repository repository;
	
	public ProductServiceImpl() {
		this.repository = new Repository();
	}

	@Override
	public Product addProduct(Product product) throws ProductAlreadyExistsException {		
		
			Product getProduct = this.repository.getProductById(product.getId());
			if(getProduct != null)
				throw new ProductAlreadyExistsException("Product Already Exists With this ID :" + product.getId());
			else {
				Product addProduct = this.repository.addProduct(getProduct);
				return addProduct;		
		
	}

}

	@Override
	public Product getProductById(int id) throws ProductNotFoundException, InvalidInputException {
		boolean validInput = InputValidation.validInt(id);
		if(!validInput)
			throw new InvalidInputException("Invalid Product Id :" + id + " .. Should be Postive and > 0 ");
		Product product = this.repository.getProductById(id);
		if(product == null)
			throw new ProductNotFoundException("Product not found with the id :" + id);
		else
			return product;
	}

	@Override
	public List<Product> getAllProducts() {
		return this.repository.getAllProducts();
	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {
		Product updatedProduct = null;
		Product productById = this.repository.getProductById(product.getId());
		if(productById == null)
			throw new ProductNotFoundException("Product not found with the id :" + product.getId());
		else
			updatedProduct = this.repository.updateProduct(product);
		return null;
	}
	
}
