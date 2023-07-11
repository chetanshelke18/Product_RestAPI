package com.jbk.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Product {

	@Id
	@Column(unique = true,nullable = false)
	private String productId;
	
	@Column(unique = true,nullable = false)
	private String productName;
	
	@OneToOne
	private Supplier supplier;
	
	@OneToOne
	private Category category;
	
	@Column(nullable = false)
	private int productQTY;
	
	@Column(nullable = false)
	private double productPrice;

	public Product() {
		
	}

	public Product(String productId, String productName, Supplier supplierId, Category categoryId, int productQTY,
			double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQTY = productQTY;
		this.productPrice = productPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getProductQTY() {
		return productQTY;
	}

	public void setProductQTY(int productQTY) {
		this.productQTY = productQTY;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", supplier=" + supplier
				+ ", category=" + category + ", productQTY=" + productQTY + ", productPrice=" + productPrice + "]";
	}

	
	
	}

	