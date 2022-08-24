package com.example.demo.service;

import com.example.demo.model.JSONResponse;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
@Service
public class ProductServiceImpl implements ProductService{
    static HashMap<Integer, Product> productHashMap = new HashMap<>();
    static JSONResponse jsonResponse = new JSONResponse();

    static {
        Product honey = new Product();
        honey.setId(1);
        honey.setName(" Pineapples");
        productHashMap.put(1, honey);
        Product mango = new Product();
        mango.setId(2);
        mango.setName("Mangoes");

        productHashMap.put(2, mango);
    }

    @Override
    public void createProduct(Product product) {
        productHashMap.put(product.getId(), product);
    }

    @Override
    public void updateProduct(int id, Product product) {

    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public Collection<Product> getProduct() {

        return null;
    }
}
