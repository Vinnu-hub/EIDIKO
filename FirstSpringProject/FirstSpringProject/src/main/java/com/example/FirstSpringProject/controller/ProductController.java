package com.example.FirstSpringProject.controller;

import com.example.FirstSpringProject.entity.product;
import com.example.FirstSpringProject.entity.product;
import com.example.FirstSpringProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

     @Autowired
     private ProductService service;

     @GetMapping
     public List<product> getProducts() {
          return service.getProducts();
     }
     @PostMapping
     public product addProduct(@RequestBody product product) {
          return service.addProduct(product);
     }

     @PutMapping("/{id}")
     public product updateProduct(@PathVariable int id, @RequestBody product product) {
          product.setId(id);
          return service.updateProduct(product);
     }

     @DeleteMapping("/{id}")
     public void deleteProduct(@PathVariable int id) {
          service.deleteProduct(id);
     }
}

