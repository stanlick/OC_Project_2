package com.lambazon.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String description;
	private String type;
	private String category;
	private Double price;
	@Transient
	private AuditInfo auditInfo;

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

	public Product() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		// TODO
		return null;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) throws IllegalArgumentException {
		//TODO COmmenting this out for a quick test and then I'll remember "<wink>" to uncomment 
//		if (price <= 0.0) {
//			throw new IllegalArgumentException("Price must be greater than 0.0");
//		}
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("Product [id=%s, name=%s, description=%s, type=%s, category=%s, price=%s",
				id, name, description, type, category, price);
	}
	
	
}
