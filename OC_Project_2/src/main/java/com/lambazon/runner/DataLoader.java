package com.lambazon.runner;

import com.lambazon.model.Product;
import com.lambazon.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        Product product1 = new Product();
        product1.setName("Bushnell Binocular Bundle");
        product1.setDescription("Trophy XLT 10x42 Binoculars (Bone Collector Edition) + Deluxe Binocular Harness");
        product1.setType("Hunting");
        product1.setCategory("Sports & Outdoors");
        product1.setPrice(76.99);

        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Under Armour Men's");
        product2.setDescription("Original Series 6” Boxerjock, Pack of 2");
        product2.setType("Clothing");
        product2.setCategory("Men");
        product2.setPrice(45.77);

        productRepository.save(product2);
    }
}
