package com.capgemini.productapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(int productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isPresent())
			return optionalProduct.get();
		throw new ProductNotFoundException("Product does not exists");
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public List<Product> findByCategory(String productCategory) {
		return productRepository.findOrdersByproductCategory(productCategory);

	}

	@Override
	public List<Product> findByName(String productName) {
		return productRepository.findOrdersByProductName(productName);

	}

	@Override
	public List<Product> findByRange(Double lowPrice, Double highPrice) {
		return productRepository.findByProductPriceBetween(lowPrice, highPrice);
	}

	@Override
	public List<Product> findByCustom(String productCategory, Double lowPrice, Double highPrice) {
		// TODO Auto-generated method stub
		return productRepository.findProductByProductCustom(productCategory, lowPrice, highPrice);
	}

}
