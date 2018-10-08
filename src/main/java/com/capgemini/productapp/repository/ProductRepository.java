package com.capgemini.productapp.repository;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.productapp.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

	@Query("{ 'productName' : ?0 }")
	List<Product> findOrdersByProductName(String productName);

	@Query("{ 'productCategory' : ?0 }")
	List<Product> findOrdersByproductCategory(String productCategory);
	
//	@Query({"productPrice" : {"$gt" : from , "$lt" : to}})
//	List<Product> findByProductPriceBetween(int from, int to);
}
