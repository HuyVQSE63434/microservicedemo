package com.example.product.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.product.entity.Order;
import com.example.product.entity.ProductEntity;
import com.example.product.repository.ProductRepository;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private Environment env;

    @Autowired
    private ProductRepository productRepo;
    
    @RequestMapping(value =  "/")  
    public String home() {  
        return "Hello from Product Service running at port: " + env.getProperty("local.server.port");  
    }  

    @RequestMapping("/dummydata")
    public List<ProductEntity> getProducts(){
        List<ProductEntity> products = Arrays.asList(  
                new ProductEntity(1, "product 1", "brand 1", 10000, "red"),
                new ProductEntity(2, "product 2", "brand 2", 20000, "red"),
                new ProductEntity(3, "product 3", "brand 3", 30000, "red")
        );  
      return products; 
    }

       
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ProductEntity> listAll() {
        List<ProductEntity> listProduct = productRepo.findAll();
        return listProduct;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ProductEntity addProduct(@RequestBody ProductEntity product) {
        ProductEntity result = productRepo.save(product);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findProductById(@PathVariable("id") int id) {
        Optional<ProductEntity> result = productRepo.findById(id);
        if(result.isEmpty()) return ResponseEntity.notFound();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public int addProduct(@PathVariable("id") int id) {
        try {
            productRepo.deleteById(id);
        } catch (Exception e) {
            return HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.SC_OK;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateProduct(@PathVariable("id") int id,@RequestBody ProductEntity product) {
        ProductEntity result = productRepo.findById(id).get();
        if(result != null){
            if((product.getTitle() != null) || (product.getTitle() != "")){
                result.setTitle(product.getTitle());
            }
            if((product.getBrand() != null) || (product.getBrand() != "")){
                result.setBrand(product.getBrand());
            }
            if((product.getColor() != null) || (product.getColor() != "")){
                result.setColor(product.getColor());
            }
            if(product.getPrice() != null){
                result.setPrice(product.getPrice());
            }
            productRepo.save(result);
        }else return ResponseEntity.notFound();
        return result;
    }

    /*this api for order demo, if in real application, we need to create another service to handle this*/
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public Object orderProduct(@RequestBody Order order){
        String missingMessage = "Missing field: ";
        boolean missing = false;
        try {
            if(order.getCustomerName() == null){
                missingMessage = missingMessage.concat("Customer name; ");
                missing = true;
            }
            if(order.getEmail()== null){
                missingMessage = missingMessage.concat("email; ");
                missing = true;
            }
            if(order.getPhoneNumber()== null){
                missingMessage = missingMessage.concat("phone number; ");
                missing = true;
            }
            if(missing){
                return ResponseEntity.badRequest().body(missingMessage);
            }    
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Order successfully!");
    }
}
