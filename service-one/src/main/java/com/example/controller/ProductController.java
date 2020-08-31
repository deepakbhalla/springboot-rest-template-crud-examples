package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * Get all the available products.
     * 
     * @return
     */
    @GetMapping
    public List<Product> getProducts() {

        return this.productService.getProducts();
    }

    /**
     * Get a specific product requested by client.
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {

        return this.productService.getProduct(id);
    }

    /**
     * Create a new product.
     * 
     * @param id
     * @param name
     * @param price
     * @return
     */
    @PostMapping
    public Map<String, String> createProduct(@RequestParam(value = "id") Long id,
            @RequestParam(value = "name") String name, @RequestParam(value = "price") Integer price) {

        this.productService.createProduct(id, name, price);
        Map<String, String> map = new HashMap<>();
        map.put("status", "Product added");
        return map;
    }

    /**
     * Delete a product details of which client sends as JSON in request body.
     * 
     * @param product
     * @return
     */
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {

        this.productService.updateProduct(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteProduct(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
        Map<String, String> map = new HashMap<>();
        map.put("status", "Product deleted");
        return map;
    }

}
