package com.capgemini.productapp.service;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;

@Service
public interface ProductService {

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public Product findProductById(int productId) throws ProductNotFoundException;

	public void deleteProduct(Product product);

	public List<Product> findByCategory(String productCategory);

	public List<Product> findByName(String productName);

}
