package com.qa.woo.ecommerce.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qa.woo.ecommerce.enums.DeliveryMode;
import com.qa.woo.ecommerce.model.Product;

public class Repository {

	public Connection getDbConnection() {
		Connection connection = null;

		try {
			/*
			 * 1. Register the Driver com.mysql.cj.jdbc.Driver
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			/*
			 * DriverManager
			 */
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "mysql");
			} catch (SQLException e) {
				System.out.println("Invalid database credentials");
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Connector jar file not found in the class path");
			e.printStackTrace();
		}

		return connection;
	}

	public Product addProduct(Product product) {
		Product addedProduct = null;
		try (Connection con = getDbConnection()) {
			String insertProductQuery = "insert into product_details values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(insertProductQuery);
			pstmt.setInt(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setFloat(4, product.getRating());
			pstmt.setString(5, product.getCategory());
			pstmt.setBoolean(6, product.isInStock());
			pstmt.setFloat(7, product.getDiscountPercentage());
			pstmt.setString(8, product.getDeliveryMode().name());
			pstmt.setBoolean(9, product.isReturnAccepted());
			pstmt.setString(10, product.getSellerName());

			int rows = pstmt.executeUpdate();
			if (rows > 0)
				addedProduct = product;

		} catch (SQLException e) {
			System.out.println("Some internal error occurred .. Please try again !!");
		}
		return addedProduct;
	}

	public Product getProductById(int id) {
		Product product = null;
		try (Connection con = getDbConnection()) {
			String selectProductQuery = "select * from product_details where id = ?";
			PreparedStatement pstmt = con.prepareStatement(selectProductQuery);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				product = Product.builder().id(rs.getInt("id")).name(rs.getString("name")).price(rs.getDouble("price"))
						.rating(rs.getFloat("rating")).category(rs.getString("category"))
						.inStock(rs.getBoolean("in_stock")).discountPercentage(rs.getFloat("discount_percentage"))
						.deliveryMode(DeliveryMode.valueOf(rs.getString("delivery_mode")))
						.isReturnAccepted(rs.getBoolean("is_return_accepted")).sellerName(rs.getString("seller_name"))
						.build();
			}
		} catch (SQLException e) {
			System.out.println("Some internal error occured .. Please try again !!");
		}
		return product;
	}

	public List<Product> getAllProducts() {
		List<Product> productsList = new ArrayList<>();
		try (Connection con = getDbConnection()) {
			String selectProductQuery = "select * from product_details";
			PreparedStatement pstmt = con.prepareStatement(selectProductQuery);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = Product.builder().id(rs.getInt("id")).name(rs.getString("name"))
						.price(rs.getDouble("price")).rating(rs.getFloat("rating")).category(rs.getString("category"))
						.inStock(rs.getBoolean("in_stock")).discountPercentage(rs.getFloat("discount_percentage"))
						.deliveryMode(DeliveryMode.valueOf(rs.getString("delivery_mode")))
						.isReturnAccepted(rs.getBoolean("is_return_accepted")).sellerName(rs.getString("seller_name"))
						.build();
				productsList.add(product);
			}
		} catch (SQLException e) {
			System.out.println("Some internal error occurred .. Please try again !!");
		}
		return productsList;
	}

	public Product updateProduct(Product product) {
		Product updatedProduct = null;
		try (Connection con = getDbConnection()) {
			String insertProductQuery = "update product_details set name = ?, price = ?,"
					+ "rating = ?, category = ?, in_stock = ?, discount_percentage = ?, delivery_mode = ?,"
					+ "is_return_accepted=?, seller_name = ? where id = ?";
			PreparedStatement pstmt = con.prepareStatement(insertProductQuery);
			pstmt.setInt(10, product.getId());
			pstmt.setString(1, product.getName());
			pstmt.setDouble(2, product.getPrice());
			pstmt.setFloat(3, product.getRating());
			pstmt.setString(4, product.getCategory());
			pstmt.setBoolean(5, product.isInStock());
			pstmt.setFloat(6, product.getDiscountPercentage());
			pstmt.setString(7, product.getDeliveryMode().name());
			pstmt.setBoolean(8, product.isReturnAccepted());
			pstmt.setString(9, product.getSellerName());

			int rows = pstmt.executeUpdate();
			if (rows > 0)
				updatedProduct = product;

		} catch (SQLException e) {
			System.out.println("Some internal error occurred .. Please try again !!");
		}
		return updatedProduct;
	}
}
