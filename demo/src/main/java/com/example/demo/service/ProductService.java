package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.Collection;

public interface ProductService {
     void createProduct(Product product);
    void updateProduct(int id, Product product);
    void deleteProduct(int id);
    Collection<Product> getProduct();
}
