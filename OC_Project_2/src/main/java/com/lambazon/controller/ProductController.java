package com.lambazon.controller;


import com.lambazon.model.Product;
import com.lambazon.repository.ProductRepository;
import com.lambazon.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "edit"; 
    }
    
    @RequestMapping(path = "products", method = RequestMethod.POST)
	public String saveProduct(Product product) {
		productService.save(product);
		return "redirect:/products";
	}

	@RequestMapping(path = "/products", method = RequestMethod.GET)
	public String getAllProducts(Model model) {
		model.addAttribute("products", productService.getAll());
		return "products";
	}

	@RequestMapping(path = "/products/edit/{id}", method = RequestMethod.GET)
	public String editProduct(Model model, @PathVariable(value = "id") String id) {
		model.addAttribute("product", productService.getById(id));
		return "edit";
	}

	@RequestMapping(path = "/products/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable(name = "id") String id) {
		productService.delete(id);
		return "redirect:/products";
	}
}
