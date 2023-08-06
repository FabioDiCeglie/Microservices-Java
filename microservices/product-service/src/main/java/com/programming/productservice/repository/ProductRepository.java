package com.programming.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.programming.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
