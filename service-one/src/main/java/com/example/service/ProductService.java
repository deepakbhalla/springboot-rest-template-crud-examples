package com.example.service;

import java.util.List;
import com.example.model.Product;

public interface ProductService {

    List<Product> getProducts();
    Product getProduct(Long id);
}
