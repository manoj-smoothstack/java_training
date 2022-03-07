package com.smoothstack.springbatch.simpleclient.reader;


import com.smoothstack.springbatch.simpleclient.model.Product;
import com.smoothstack.springbatch.simpleclient.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ProductServiceAdapter {
    Logger logger = LoggerFactory.getLogger(ProductServiceAdapter.class);

    @Autowired
    ProductService service;

    public Product nextProduct() throws InterruptedException {

        Product p = null;
        Thread.sleep(1000);
        try {
            p = service.getProduct();
            logger.info("connected web service .... ok");
        }catch(Exception e){
            logger.info("exception ..." + e.getMessage());
            throw e;
        }
        return p;
    }

}
