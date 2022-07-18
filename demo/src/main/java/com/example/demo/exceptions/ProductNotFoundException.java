package com.example.demo.exceptions;

public class ProductNotFoundException extends  RuntimeException{
    public ProductNotFoundException(String s){
        super(s);
    }
}
