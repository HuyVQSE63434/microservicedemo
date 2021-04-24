package com.example.store.controllers;

import java.util.Arrays;
import java.util.List;

import com.example.store.entities.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StoreController {
    /**
     *
     */
    private static final String HTTP_PRODUCT_SERVICE_GETALL = "http://product-service/products/";

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String home() {
        return "Hello from store Service running at port: " + env.getProperty("local.server.port");
    }

    @RequestMapping("/{id}")
    public Store getGallery(@PathVariable final int id) {
        Store store = new Store();
        store.setId(id);

        List<Object> productList = Arrays.asList(restTemplate.getForObject(HTTP_PRODUCT_SERVICE_GETALL, List.class));
        store.setProducts(productList);
        LOGGER.info("Returning products ... ");
        return store;
    }
    // -------- Admin Area --------
    // This method should only be accessed by users with role of 'admin' // We'll
    // add the logic of role based auth later 
    @RequestMapping("/admin")
    public String homeAdmin() {
        return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
    }
}
