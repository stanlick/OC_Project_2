package com.lambazon.util;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.lambazon.model.Product;
import com.lambazon.repository.ProductRepository;
import com.lambazon.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class MaintenanceTrackerTests {
	
	@Mock
	ProductRepository mockRepository;
	
	@InjectMocks
	ProductService service;
	
	@Before
	public void beforeEachTest() {
		MaintenanceTracker.resetAllStatistics();
	}

	@Test
	public void save_new_product() {
		Product p = createProduct("123");
		save(p);
		assertThat(MaintenanceTracker.getNumberOfSavesForProduct(p)).isEqualTo(1);
	}


	
	@Test
	public void save_same_new_product_twice() {
		Product p = createProduct("123");
		save(p);
		save(p);
		assertThat(MaintenanceTracker.getNumberOfSavesForProduct(p)).isEqualTo(2);
	}
	
	@Test
	public void save_new_product_then_delete() {
		Product p = createProduct("123");
		save(p);
		delete(p);
		assertThat(MaintenanceTracker.getNumberOfSavesForProduct(p)).isEqualTo(1);
		assertThat(MaintenanceTracker.getNumberOfDeleted()).isEqualTo(1);
	}


	
	@Test
	public void save_new_product_then_update_four_times() {
		Product p = createProduct("123");
		save(p);save(p);save(p);save(p);save(p);		
		assertThat(MaintenanceTracker.getNumberOfSavesForProduct(p)).isEqualTo(5);
	}
	
	@Test
	public void save_new_product_then_update_then_delete() {
		Product p = createProduct("123");
		save(p);save(p);delete(p);	
		assertThat(MaintenanceTracker.getNumberOfSavesForProduct(p)).isEqualTo(2);
		assertThat(MaintenanceTracker.getNumberOfDeleted()).isEqualTo(1);
	}
	
	@Test
	public void save_four_new_product_then_delete_one() {
		Product p = createProduct("123");
		save(p);save(p);delete(p);	
		assertThat(MaintenanceTracker.getNumberOfSavesForProduct(p)).isEqualTo(2);
		assertThat(MaintenanceTracker.getNumberOfDeleted()).isEqualTo(1);
	}
	
	@Test
	public void save_four_different_new_product_then_delete_two() {
		save(createProduct("123"));
		save(createProduct("234"));
		save(createProduct("456"));
		save(createProduct("678"));
		delete(createProduct("678"));
		assertThat(MaintenanceTracker.getNumberOfSaves()).isEqualTo(4);
		assertThat(MaintenanceTracker.getNumberOfDeleted()).isEqualTo(1);
	}
	
	@Test
	public void test_add_then_update_audit_info() {
		Product p = save(createProduct("678"));
		assertThat(p.getAuditInfo().getAction()).isEqualTo("save");
		p = update(createProduct("678"));
		assertThat(p.getAuditInfo().getAction()).isEqualTo("update");
	}
	
	@Test
	public void test_who_audit_info() {
		System.setProperty("user.name", "Mickey Mouse");
		Product p = save(createProduct("678"));
		assertThat(p.getAuditInfo().getAction()).isEqualTo("save");
		assertThat(p.getAuditInfo().getWho()).isEqualTo("Mickey Mouse");
	}
	
	
	/**
	 * 
	 * Test utility methods
	 *  
	 */ 
	private Product createProduct(String id) {
	    Product product = new Product();
	    product.setId(id);
	    product.setName("Bushnell Binocular Bundle");
	    product.setDescription("Trophy XLT 10x42 Binoculars (Bone Collector Edition) + Deluxe Binocular Harness");
	    product.setType("Hunting");
	    product.setCategory("Sports & Outdoors");
	    product.setPrice(76.99);
	    return product;
	}

	private Product save(Product p) {
		when(mockRepository.save(p)).thenReturn(p);
		return service.save(p);
	}
	private Product update(Product p) {
		when(mockRepository.save(p)).thenReturn(p);
		return service.update(p);
	}
	private void delete(Product p) {
		service.delete(p.getId());
	}

}
