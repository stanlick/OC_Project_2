package com.lambazon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

	private static List<Product> maintenanceLog = new ArrayList<>();

	
	public static void log(Product product) {
		maintenanceLog.add(product);
	}	

	public static long getNumberOfAdds() {
		long count = 0;
		// compute number of operations
		return count;
	}
	
	public static long getNumberOfUpdates() {
		long count = 0;		
		// compute number of operations
		return count;
	}
	
	public static long getNumberOfDeletes() {
		long count = 0;		
		// compute number of operations	
		return count;
	}
	
	public static void resetLogs() {
		maintenanceLog.clear();
	}
}


