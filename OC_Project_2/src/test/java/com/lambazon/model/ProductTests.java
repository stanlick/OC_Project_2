package com.lambazon.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ProductTests {

	
	@Test
	public void test_product_name() { 
		Product p = new Product();
		p.setDescription("Test Name");
		assertThat(p.getDescription()).isEqualTo("Test Name");
	}
	
	@Test
	public void test_product_description() {
		Product p = new Product();
		p.setDescription("Test Description");
		assertThat(p.getDescription()).isNotBlank();
	}

	@Test
	public void test_valid_product_price() {
		Product p = new Product();
		p.setPrice(99.55);
		assertThat(p.getPrice()).isEqualTo(99.55);
	}
	
	
	/**
	 * This test expects an exception to be raised when setting a product price to 0.0
	 * If an exception is not raised, this is a failure
	 */
	@Test(expected=IllegalArgumentException.class)
	public void test_invalid_product_price() {
		Product p = new Product();
		p.setPrice(0.0);
	}
	
	@Test
	public void test_product_type() {
		Product p = new Product();
		p.setType("Test Type");
		assertThat(p.getType()).isEqualTo("Test type");
	}
	
	@Test
	public void test_product_category() {
		Product p = new Product();
		p.setCategory("Test Category");
		assertThat(p.getCategory()).isNotBlank();
	}
	

}
