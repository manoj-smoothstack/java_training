package com.smoothstack.springbatch.serviceitemreader.simpleclient.reader;

import com.smoothstack.springbatch.serviceitemreader.simpleclient.model.Product;
import com.smoothstack.springbatch.serviceitemreader.simpleclient.service.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductServiceAdapter implements InitializingBean {

    @Autowired
    private ProductService service;

    private ArrayList<Product> products;

    @Override
    public void afterPropertiesSet() throws Exception {


        this.products = service.getProducts();

    }

    public Product nextProduct(){
        if ( products.size() >0){
            return products.remove(0);
        }else
            return null;

    }

    public ProductService getService() {
        return service;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
