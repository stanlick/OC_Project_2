package com.lambazon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lambazon.model.Product;
import com.lambazon.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;


    public void save(Product product) {
        productRepository.save(product);
    }

   
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    
    public Product getById(String id) {
        return productRepository.getOne(id);
    }


    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
