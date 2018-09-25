package com.lambazon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	public Product add(Product product) {
    	product.setAuditInfo(new AuditInfo("add"));
    	MaintenanceTracker.log(product);
        return productRepository.save(product);
    }
	
	public Product update(Product product) {
    	product.setAuditInfo(new AuditInfo("update"));
    	MaintenanceTracker.log(product);
        return productRepository.save(product);
    }

   
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    
    public Product getById(String id) {
        return productRepository.getOne(id);
    }


    public void delete(Product product) {
    	product.setAuditInfo(new AuditInfo("delete"));
    	MaintenanceTracker.log(product);
        productRepository.deleteById(product.getId());
    }
}
