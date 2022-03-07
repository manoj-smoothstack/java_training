package com.smoothstack.springbatch.simpleproductservice;

import com.smoothstack.springbatch.simpleproductservice.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ProductController {

    @GetMapping("/product")
    public Product getProduct(){
        return new Product(1,"Apple Watch", BigDecimal.valueOf(300.00), 10, "Apple Watch");
    }
}
