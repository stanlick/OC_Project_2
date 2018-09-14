package com.lambazon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lambazon.model.Product;

/**
 * 
 * This class can be enabled to monitor changes to products on the e-Commerce site.  It is designed to 
 * record before and after images products the user is modifying on the web site.  Auditors want to be able 
 * to determine the who, what, when, where and why with respect to inventory products being modified  
 * 
 */


public class MaintenanceTracker {

	private static Map<String, List<Product>> changesByProductId = new HashMap<>();
	private static List<String> deletedProducts = new ArrayList<>();	
	

	
	public static void save(Product product) {
		List<Product> changesForProductId = changesByProductId.get(product.getId());
		if (changesForProductId==null) {
			changesForProductId = new ArrayList<>();
		}
		changesForProductId.add(product);
		
		changesByProductId.put(product.getId(), changesForProductId);
	}

	public static void delete(String id) {
		deletedProducts.add(id);
	}
	
	public static long getNumberOfSavesForProduct(Product product) {
		return changesByProductId.get(product.getId()).size();
	}
	
	public static long getNumberOfSaves() {
		return changesByProductId.size();
	}
	
	public static long getNumberOfDeleted() {
		return deletedProducts.size();
	}
	
	public static void resetAllStatistics() {
		changesByProductId.clear();
		deletedProducts.clear();
	}
}


