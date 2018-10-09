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

	@Query("{'productPrice':{'$gt' : ?0, '$lt' : ?1}}")
	List<Product> findByProductPriceBetween(double lowPrice, double highPrice);
	
	@Query("{'productCategory':?0},{'productPrice':{'$gt' : ?1, '$lt' : ?2}}")
	List<Product> findProductByProductCustom(String productCategory, double lowPrice, double highPrice);
}
