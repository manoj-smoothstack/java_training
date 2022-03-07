package com.smoothstack.springbatch.simpleclient.service;

import com.smoothstack.springbatch.simpleclient.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {


    public Product getProduct(){
        RestTemplate restTemplate = new RestTemplate();
        String url ="http://localhost:8080/product";
        Product p = restTemplate.getForObject(url, Product.class);
        return p;
    }
}
