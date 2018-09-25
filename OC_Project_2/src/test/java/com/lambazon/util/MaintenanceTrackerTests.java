package com.lambazon.util;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
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
	
	private long adds, updates, deletes = 0;
	
	@BeforeClass
	public static void beforeRun() {
		MaintenanceTracker.resetLogs();
	}

	@Before
	public void beforeEachTestScenario() {
		adds=MaintenanceTracker.getNumberOfAdds();
		updates=MaintenanceTracker.getNumberOfUpdates();
		deletes=MaintenanceTracker.getNumberOfDeletes();
	}
	
	@Test
	public void save_new_product() {
		Product p = createProduct("123");
		save(p);
		assertThat(MaintenanceTracker.getNumberOfAdds()).isEqualTo(adds + 1);
	}


	
	@Test
	public void save_same_new_product_twice() {
		Product p = createProduct("123");
		save(p);
		save(p);
		assertThat(MaintenanceTracker.getNumberOfAdds()).isEqualTo(adds + 2);
	}
	
	@Test
	public void save_new_product_then_delete() {
		save(createProduct("234"));
		delete(createProduct("234"));
		assertThat(MaintenanceTracker.getNumberOfAdds()).isEqualTo(adds + 1);
		assertThat(MaintenanceTracker.getNumberOfDeletes()).isEqualTo(deletes + 1);
	}


	
	@Test
	public void save_new_product_then_update_four_times() {
		Product p = createProduct("123");
		save(p);save(p);save(p);save(p);save(p);		
		assertThat(MaintenanceTracker.getNumberOfAdds()).isEqualTo(adds + 5);
	}
	
	@Test
	public void save_new_product_then_update_then_delete() {
		Product p = createProduct("123");
		save(createProduct("234"));save(createProduct("888"));delete(createProduct("888"));	
		assertThat(MaintenanceTracker.getNumberOfAdds()).isEqualTo(adds + 2);
		assertThat(MaintenanceTracker.getNumberOfDeletes()).isEqualTo(deletes + 1);
	}
	
	@Test
	public void save_two_new_product_then_delete_one() {
		Product p = createProduct("123");
		save(createProduct("123"));save(createProduct("234"));delete(createProduct("234"));	
		assertThat(MaintenanceTracker.getNumberOfAdds()).isEqualTo(adds + 2);
		assertThat(MaintenanceTracker.getNumberOfDeletes()).isEqualTo(deletes + 1);
	}
	
	@Test
	public void save_four_different_new_product_then_delete_two() {
		save(createProduct("123"));
		save(createProduct("234"));
		save(createProduct("456"));
		save(createProduct("678"));
		delete(createProduct("678"));
		assertThat(MaintenanceTracker.getNumberOfAdds()).isEqualTo(adds + 4);
		assertThat(MaintenanceTracker.getNumberOfDeletes()).isEqualTo(deletes + 1);
	}
	
	@Test
	public void test_add_then_update_audit_info() {
		Product p = save(createProduct("678"));
		assertThat(p.getAuditInfo().getAction()).isEqualTo("add");
		p = update(createProduct("678"));
		assertThat(p.getAuditInfo().getAction()).isEqualTo("update");
	}
	
	@Test
	public void test_who_audit_info() {
		System.setProperty("user.name", "Mickey Mouse");
		Product p = save(createProduct("678"));
		assertThat(p.getAuditInfo().getAction()).isEqualTo("add");
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
		return service.add(p);
	}
	private Product update(Product p) {
		when(mockRepository.save(p)).thenReturn(p);
		return service.update(p);
	}
	private void delete(Product p) {
		service.delete(p);
	}

}
