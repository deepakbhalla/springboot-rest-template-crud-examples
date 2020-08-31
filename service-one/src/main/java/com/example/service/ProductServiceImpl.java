package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

    List<Product> products = new ArrayList<Product>();

    public ProductServiceImpl() {
        products.add(new Product(Long.valueOf(1), "product-1", 1000));
        products.add(new Product(Long.valueOf(2), "product-2", 2000));
        products.add(new Product(Long.valueOf(3), "product-3", 3000));
        products.add(new Product(Long.valueOf(4), "product-4", 4000));
    }

    @Override
    public List<Product> getProducts() {
        return this.products;
    }

    @Override
    public Product getProduct(Long id) {
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()) {
            Product product = iterator.next();
            if(product.getProductID().equals(id)) {
                return product;
            }
        }
        return null;
    }

}
