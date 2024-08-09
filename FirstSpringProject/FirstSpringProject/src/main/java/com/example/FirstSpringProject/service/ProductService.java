package com.example.FirstSpringProject.service;

import com.example.FirstSpringProject.entity.product;
import com.example.FirstSpringProject.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private productRepository repository;

    public List<product> getProducts() {
        return repository.findAll();
    }

    public Optional<product> getProductById(int id) {
        return repository.findById(id);
    }

    public product addProduct(product product) {
        return repository.save(product);
    }

    public product updateProduct(product product) {
        return repository.save(product);
    }

    public void deleteProduct(int id) {
        repository.deleteById(id);
    }
}
