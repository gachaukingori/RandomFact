package com.example.demo.controller;

import com.example.demo.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@ControllerAdvice
public class ConsumeWebService {
    @Autowired
    RestTemplate restTemplate;
    public static final Logger logger =
            LoggerFactory.getLogger(ConsumeWebService.class);
    @RequestMapping("/template/getProducts")
    public ResponseEntity getProductFromApi(){
        String url ="http://localhost:4431/getAllProducts";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        // get A response entity from the rest template
        ResponseEntity<Object[]> responseEntity=
                restTemplate.getForEntity(url,Object[].class);

        // get an array of objects from the response entity body
        Object[] objects = responseEntity.getBody();

        // Object mapper from the Jackson library to help read the product POJO to a JSON
        ObjectMapper objectMapper = new ObjectMapper();


        MediaType mediaType = responseEntity.getHeaders().getContentType();
        HttpStatus httpStatus = responseEntity.getStatusCode();
        logger.info(" media type: " + mediaType + " http status "+ httpStatus );

        // Create a Stream from the Objects array
       Stream<Object> objectStream  = Arrays.stream(objects);


       Stream<Object> objectStream1 = objectStream.map((object)->{
           return objectMapper.convertValue(object, Product.class);
       });

        List<Object> productList = objectStream1.collect(Collectors.toList());

    return  new ResponseEntity<>(productList, HttpStatus.OK);
    }


    @RequestMapping(value = "/template/product", method = RequestMethod.POST )
    public String addProductToApi(@RequestBody Product product){
        String url = "http://localhost:4431/createProduct";

        ProductController.logger.info("This is the product object " + product.toString());
        HttpHeaders httpHeaders  = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> httpEntity = new HttpEntity<Product>(product,httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class).getBody();
    }

    @RequestMapping(value = "/template/productid/{productid}", method = RequestMethod.PUT)
    public String updateProductFromApi(@PathVariable(value = "productid") int productid, @RequestBody Product product){
        String url = "http://localhost:4431/updateProduct/"+productid;
        HttpHeaders httpHeaders  = new HttpHeaders();
        HttpEntity<Product> httpEntity = new HttpEntity<Product>(product,httpHeaders);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return restTemplate.exchange(url, HttpMethod.PUT, httpEntity,String.class).getBody();
    }
    @RequestMapping(value = "/template/product/{productid}", method = RequestMethod.DELETE)
    public String deleteProductFromApi(@PathVariable(value = "productid") int productId){
        String url = "http://localhost:4431/deleteProduct/"+productId;
        HttpHeaders httpHeaders  = new HttpHeaders();
        HttpEntity<Integer> httpEntity = new HttpEntity<Integer>(httpHeaders);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return restTemplate.exchange(url, HttpMethod.DELETE, httpEntity,String.class).getBody();
    }

}
