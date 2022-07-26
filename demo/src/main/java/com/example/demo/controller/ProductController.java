package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

@RestController
@ControllerAdvice
public class ProductController {
    public static final Logger logger =
            LoggerFactory.getLogger(ProductController.class);
    static HashMap<Integer, Product> productHashMap = new HashMap<>();

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


    @RequestMapping("/getAllProducts")
    public ResponseEntity getProductController() {

        return new ResponseEntity<>(productHashMap.values(), HttpStatus.OK);
    }

    @RequestMapping("/getProductById")
    public ResponseEntity getProductById(@RequestParam(name = "productId", required = true, defaultValue = "1") int id) {
        String productName = "";
        Product[] product = {null};
        Iterator<Product> iterator = productHashMap.values().iterator();

        productHashMap.forEach((key, value) -> {
            if (value.getId() == id) {
                product[0] = value;
            }

        });

        if (product[0] == null) {
            throw new ProductNotFoundException("Product not found");
        }
        return new ResponseEntity<>(product[0], HttpStatus.OK);
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody Product product, String name) {
        logger.info("This is the product object " + product.toString());
        productHashMap.put(product.getId(), product);
        return new ResponseEntity("Product succesfully created", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@PathVariable(value = "id") int productId, @RequestBody Product productbody) {
        final Product[] product = {null};
        final int[] x = {0};
        productHashMap.forEach((key, value) -> {
            System.out.println("Product is: " + value.toString() + " productbody is " + productbody.getCount() + " Product id " + productId);
            x[0] = productbody.getCount();
            if (value.getId() == productId) {
                System.out.println("product to be updated is " + value.getName() + " ");

                product[0] = value;

                value.setCount(x[0]);

            }
        });
        return new ResponseEntity("Product updated successfuly", HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable("id") int productId){
        productHashMap.remove(productId);

        return  new ResponseEntity("Product deleted succesfully", HttpStatus.OK);
    }





}
