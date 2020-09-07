package com.example.service.controller;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.service.model.Product;

@RestController
@RequestMapping(value = "/product/api/v1")
public class ProductController {

    // Resource endpoint url of project 'service-one'
    public static final String SERVICE_ONE_URL = "http://localhost:8080/product";

    // Just to simulate read time out scenario, you can use below url -
    // public static final String SERVICE_ONE_URL = "httpstat.us/200?sleep=8000";

    @Autowired
    RestTemplate restTemplate;

    /**
     * Get all the products.
     * 
     * @return response - ResponseEntity<List<Product>>
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {

        ResponseEntity<List<Product>> response = restTemplate.exchange(SERVICE_ONE_URL, HttpMethod.GET,
                HttpEntity.EMPTY, new ParameterizedTypeReference<List<Product>>() {
                });

        return ResponseEntity.status(HttpStatus.SC_OK).body(response.getBody());
    }

    /**
     * Creates a product. Service 2 accepts JSON request payload. It uses request
     * json to populate service 1 resource url along with query parameters.
     * 
     * To create the service 1 resource url, it uses
     * 'org.springframework.web.util.UriComponentsBuilder'
     * 
     * @param product
     *            - Product JSON in Request Body
     * @return response - ResponseEntity<Map>
     */
    @PostMapping
    public ResponseEntity<Map> createProduct(@RequestBody Product product) {

        // As service 1 Post method accepts query parameters, let's prepare URL by
        // reading request body and passing values to UriComponentsBuilder to populate
        // query parameters.
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SERVICE_ONE_URL)
                .queryParam("id", product.getProductID()).queryParam("name", product.getProductName())
                .queryParam("price", product.getProductPrice());

        // ---------- Different ways of calling another web-service --------

        // Map<String, Object> response =
        // restTemplate.postForObject(builder.toUriString(), HttpEntity.EMPTY,
        // Map.class);

        // OR

        /*
         * Map<String, Object> response1 = (Map<String, Object>)
         * restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
         * HttpEntity.EMPTY, Map.class);
         */

        // OR

        ResponseEntity<Map> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, HttpEntity.EMPTY,
                Map.class);

        return response;
    }

    /**
     * Update a product details of which client sends as JSON in request body.
     * 
     * @param product
     * @return response - ResponseEntity<Product>
     */
    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

        HttpEntity<Product> request = new HttpEntity<>(product);
        ResponseEntity<Product> response = restTemplate.exchange(SERVICE_ONE_URL, HttpMethod.PUT, request,
                Product.class);
        return response;
    }

    /**
     * Delete a product.
     * 
     * @param id
     *            - Path variable
     * @return response - ResponseEntity<Map>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map> deleteProduct(@PathVariable("id") Long id) {

        ResponseEntity<Map> response = restTemplate.exchange((SERVICE_ONE_URL + "/" + id), HttpMethod.DELETE,
                HttpEntity.EMPTY, Map.class);
        return response;
    }
}
