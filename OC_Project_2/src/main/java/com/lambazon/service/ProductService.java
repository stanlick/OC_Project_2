package com.lambazon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.lambazon.model.AuditInfo;
import com.lambazon.model.Product;
import com.lambazon.repository.ProductRepository;
import com.lambazon.util.MaintenanceTracker;


@Service
public class ProductService {


    private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}


	public Product save(Product product) {
    	product.setAuditInfo(new AuditInfo(product, "save"));
    	MaintenanceTracker.save(product);
        return productRepository.save(product);
    }
	
	public Product update(Product product) {
    	product.setAuditInfo(new AuditInfo(product, "update"));
    	MaintenanceTracker.save(product);
        return productRepository.save(product);
    }

   
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    
    public Product getById(String id) {
        return productRepository.getOne(id);
    }


    public void delete(String id) {
    	MaintenanceTracker.delete(id);
=======
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
>>>>>>> branch 'master' of https://github.com/stanlick/OC_Project_2
        productRepository.deleteById(id);
    }
}
