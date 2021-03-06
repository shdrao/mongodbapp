package com.capgemini.productapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.service.ProductService;

@RestController
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.addProduct(product),
				HttpStatus.OK);
		logger.info("Product added" + product);
		return responseEntity;
	}

	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		try {
			Product productFromDb = productService.findProductById(product.getProductId());
			if (productFromDb != null)
				return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable int productId) {
		try {
			Product productFromDb = productService.findProductById(productId);
			return new ResponseEntity<Product>(productFromDb, HttpStatus.OK);
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/prod/{productCategory}")
	public ResponseEntity<List> findProductByCategory(@PathVariable String productCategory) {
		List<Product> productFromDb = productService.findByCategory(productCategory);
		return new ResponseEntity<List>(productFromDb, HttpStatus.OK);
	}

	@GetMapping("/name/{productName}")
	public ResponseEntity<List> findProductByName(@PathVariable String productName) {
		List<Product> productFromDb = productService.findByName(productName);
		return new ResponseEntity<List>(productFromDb, HttpStatus.OK);
	}

	@GetMapping("/inrange")
	public ResponseEntity<List> findProductByRange(@RequestParam Double lowPrice, Double highPrice) {
		List<Product> productFromDb = productService.findByRange(lowPrice, highPrice);
		return new ResponseEntity<List>(productFromDb, HttpStatus.OK);
	}
	
	@GetMapping("/custom")
	public ResponseEntity<List> findProductByCustom(@RequestParam Double lowPrice, Double highPrice, String productCategory) {
		List<Product> productFromDb = productService.findByCustom(productCategory, lowPrice, highPrice);
		return new ResponseEntity<List>(productFromDb, HttpStatus.OK);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
		try {
			Product productFromDb = productService.findProductById(productId);
			if (productFromDb != null) {
				productService.deleteProduct(productFromDb);
				return new ResponseEntity<Product>(HttpStatus.OK);
			}
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

}
